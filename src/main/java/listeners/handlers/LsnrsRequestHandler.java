package listeners.handlers;

import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.requestType;
import static listeners.model.Attributes.*;
import static listeners.model.Constants.*;
import static listeners.util.Utils.S;
import static listeners.util.Utils.s;
import static listeners.util.Utils.info;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentConfirmationStatus;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

import listeners.l10n.Welcome;
import listeners.model.Attributes;
import listeners.model.Constants;
import listeners.model.LangConstants;
import listeners.util.ResponseFinisher;
import listeners.util.SessionMap;
import listeners.util.SpeechUtils;
import listeners.util.UnknownIntentException;

public class LsnrsRequestHandler implements RequestHandler {

	// private static Logger LOG = getLogger(ListenersRequestHandler.class);

	@Override
	public boolean canHandle(HandlerInput input) {

		return input.matches(requestType(LaunchRequest.class).or(requestType(IntentRequest.class)));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {

		// get environment variables
		// which should be set up up in Lambda but will
		// default to a Development set: true, false, false
		DEV = (System.getenv("DEV") == null) ? true : Boolean.parseBoolean(System.getenv("DEV"));
		LIVE = (System.getenv("LIVE") == null) ? false : Boolean.parseBoolean(System.getenv("LIVE"));
		PERFORMANCE = (System.getenv("PERFORMANCE") == null) ? false
				: Boolean.parseBoolean(System.getenv("PERFORMANCE"));

		// Attributes and LangConstants depend on locale so
		// we assemble locale constants in top-level model.Constants:
		Locale locale = Constants.parseLocale(input.getRequestEnvelope()
				.getRequest()
				.getLocale());

		// get the AttributesManager and put it in Constants
		attributesManager = input.getAttributesManager();
		// get singleton instance of Attributes and put it in Constants
		attributes = Attributes.getInstance(locale);
		// also housed in Constants:
		langConstants = LangConstants.getInstance(locale); // singleton
		// refresh speechUtils each time (cache is cleared after bundle is fetched)
		speechUtils = SpeechUtils.getNewBundle();

		// possibilities are
		// *** 0. ask with either request
		// *** 1. firstEncounter launch request
		// *** 2. sessionStart launch request
		// *** 3. normal launch request
		// *** 4. firstEncounter intent request
		// *** 5. sessionStart intent request
		// *** 6. normal intent request (most common)

		persAttributes = attributesManager.getPersistentAttributes();
		info("@LsnrsRequestHandler, pers persistence: " + persAttributes.get(PERSISTENCE));

		if (persAttributes.isEmpty()) {
			// very first encounter 1. and 4.
			sessAttributes = attributes.initSessionAttributes(); // needed when testing ..
			// .. otherwise sessAttribute can be carried over *within* a session
			attributesManager.setSessionAttributes(sessAttributes);
			sessAttributes.put(PERSISTENCE, "firstEncounter");
			persAttributes.put(PERSISTENCE, "forget");
			attributesManager.savePersistentAttributes();
		}
		else if ("forget".equals(persAttributes.get(PERSISTENCE))) {
			sessAttributes = attributes.initSessionAttributes();
			sessAttributes.put(LASTINTENT, "AskPersistenceIntent");
			attributesManager.setSessionAttributes(sessAttributes);
			sessAttributes.put(PERSISTENCE, "session");
		}
		else if ("remember".equals(persAttributes.get(PERSISTENCE))) {
			// retrieve what was saved from the last session
			attributesManager.setSessionAttributes(persAttributes);
			// session persistence is changed later:
			// after launch or if the invocation is any intent
		}
		else if ("ask".equals(persAttributes.get(PERSISTENCE))) {
			// persistence will be "ask" at beginning of the session
			// info("@lsnrsRequestHandler, pers persistence is ‘ask’:"
			// + "ask".equals(persAttributes.get(PERSISTENCE)));

			attributesManager.setSessionAttributes(persAttributes);

			info("@lsnrsRequestHandler, session persistence is ‘ask’:"
					+ "ask".equals(sessAttributes.get(PERSISTENCE)));
		}
		else if (!"session".equals(persAttributes.get(PERSISTENCE))) {
			info("@lsnrsRequestHandler, bad persistent state: " + persAttributes.get(PERSISTENCE));
		}

		persAttributes.put(PERSISTENCE, "session");
		attributesManager.savePersistentAttributes();

		attributesManager.setSessionAttributes(sessAttributes);

		info("@ListenersRequestHandler, sess persistence: " + sessAttributes.get(PERSISTENCE));

		if (input.matches(requestType(LaunchRequest.class))) {
			// *** 1. 2. and 3. ***
			return new LsnrsLaunchResponse(input).getResponse();
		}
		else {
			// ANY Intent request
			// deal with AMAZON built-in intents here
			if ("remember".equals(sessAttributes.get(PERSISTENCE)))
				sessAttributes.put(PERSISTENCE, "session");
			String intentName = ((IntentRequest) input.getRequestEnvelope()
					.getRequest()).getIntent()
							.getName();
			InnerResponse ir;
			String cardTitle = "";
			String speech = "";
			String reprompt = speechUtils.getString("chooseContinue");
			boolean match = false, endSession = false;
			switch (intentName) {
				case "AMAZON.HelpIntent":
					cardTitle = speechUtils.getString("helpCardTitle");
					speech = speechUtils.getString("chooseSpeechAssistance");
					match = true;
					break;
				case "AMAZON.RepeatIntent":
					cardTitle = speechUtils.getString("repeatCardTitle");
					int fragmentIndex = (int) sessAttributes.get(FRAGMENTINDEX);
					if (fragmentIndex > NOT_YET_GREETED && fragmentIndex < NUMBER_OF_FRAGMENTS) {
						// build variant fragments just before they’re needed:
						langConstants.buildFragments();
						speech = langConstants.fragments[fragmentIndex]
								+ speechUtils.getString("chooseContinueNoAffect");
					}
					else {
						Welcome ws = (Welcome) ResourceBundle.getBundle("listeners.l10n.Welcome", locale);
						speech = ws.getSpeech() + speechUtils.getString("chooseContinueNoAffect");
					}
					match = true;
					break;
				case "AMAZON.StopIntent":
				case "AMAZON.CancelIntent":
					cardTitle = "de_DE".equals(localeTag) ? S("Genug.", "Nicht mehr.")
							: S("That’s e", "E") + "nough";
					speech = speechUtils.getString("getAbandonmentMessage");
					String bye = "de_DE".equals(localeTag) ? s("Tschüss!", "") : s("Cheerio!", "");
					speech += attributes.isPositive((String) sessAttributes.get(AFFECT)) ? bye : "";
					speech += "remember".equals(sessAttributes.get(PERSISTENCE))
							? "Until " + s("the", "") + "next time. "
							: "";

					if (!"AskPersistenceIntent".equals(sessAttributes.get(LASTINTENT))
							&& !"launch".equals(sessAttributes.get(LASTINTENT))) {
						info("@LsnrsRequestHandler: direct Stop or Cancel ‘ask’ persistence next time ...");
						// on STOP or CANCEL we set relationship to "ask"
						sessAttributes.put(LASTINTENT, intentName);
						sessAttributes.put(PERSISTENCE, "ask");
						attributesManager.setPersistentAttributes(sessAttributes);
						if (!LIVE) {
							info("@LsnrsRequestHandler: ... but LIVE was false and persistence has been cleared");
							// adjust if needed when developing
							persAttributes.clear();
							attributesManager.setPersistentAttributes(persAttributes);
						}
						attributesManager.savePersistentAttributes();
					}
					endSession = true;
					match = true;
					break;
			}
			if (match) {
				ResponseFinisher rf = ResponseFinisher.builder()
						.withSpeech(speech)
						.withReprompt(reprompt)
						.build();
				sessAttributes.put(LASTINTENT, intentName);
				return input.getResponseBuilder()
						.withSpeech(rf.getSpeech())
						.withSimpleCard(cardTitle, rf.getCardText())
						.withShouldEndSession(endSession)
						.build();
			}

			// ALL other modeled or Unknown intents
			try {
				// *** 4. 5. and 6. ***
				return new LsnrsIntentResponse(input, (String) sessAttributes.get(PERSISTENCE)).getResponse();
			}
			catch (UnknownIntentException e) {
				info("@LsnrsRequestHandler, UnknownIntentException: " + e.getMessage());
				return null;
			}
		}
	}
}
