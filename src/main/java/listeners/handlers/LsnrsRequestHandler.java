package listeners.handlers;

import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.requestType;
import static listeners.model.Attributes.*;
import static listeners.model.Constants.*;
import static listeners.util.ConstantUtils.S;
import static listeners.util.ConstantUtils.info;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

import listeners.model.Attributes;
import listeners.model.Constants;
import listeners.model.LangConstants;
import listeners.util.ResponseFinisher;
import listeners.util.SpeechUtils;

public class LsnrsRequestHandler implements RequestHandler {

	// private static Logger LOG = getLogger(ListenersRequestHandler.class);

	@Override
	public boolean canHandle(HandlerInput input) {

		return input.matches(requestType(LaunchRequest.class))
				|| input.matches(requestType(IntentRequest.class));
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
		Locale locale = Constants.parseLocale(input	.getRequestEnvelope()
																								.getRequest()
																								.getLocale());

		// get the AttributesManager and put it in Constants
		attributesManager = input.getAttributesManager();
		// get singleton instance of Attributes and put it in Constants
		attributes = Attributes.getInstance(locale);
		Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
		Map<String, Object> sessionAttributes = attributesManager.getSessionAttributes();
		// these instances are also housed in Constants:
		langConstants = LangConstants.getInstance(locale); // singleton
		speechUtils = SpeechUtils.getInstance(locale); // can make new instances

		String relationship = "normal"; // this is the default
		if ((persistentAttributes.isEmpty() && sessionAttributes.isEmpty())
				// TODO remove (this is for dev and debugging):
//				|| (persistentAttributes.get("relationship") != null && persistentAttributes.get("relationship")
//																																										.equals("ask"))
				) {
			// very first encounter
			info("@ListenersRequestHandler: firstEncounter");
			persistentAttributes.put("relationship", relationship);

			// initializing session attributes, only if empty
			if (sessionAttributes.isEmpty()) sessionAttributes = initSessionAttributes();
			info("@ListenersRequestHandler: sessionAttributes are "
					+ (sessionAttributes.isEmpty() ? "empty" : "initialized"));

			// Listeners affect has been set to a random affect: // TODO remove later:
			info("@ListenersRequestHandler, listenersAffect: " + sessionAttributes.get(LISTENERSAFFECT));
		}

		// TODO may have to change once Dialogs are in place
		attributesManager.savePersistentAttributes();

		// if it was a firstEncounter sessionAttributes are NOT empty
		if (!sessionAttributes.isEmpty()) {
			relationship = "firstEncounter";
			if (input.matches(requestType(LaunchRequest.class))) {
				return new LsnrsLaunchResponse(persistentAttributes, sessionAttributes).getResponse(input,
						relationship);
			}
			else {
				return new LsnrsIntentResponse(persistentAttributes, sessionAttributes).getResponse(input,
						relationship);
			}
		}

		// otherwise there are still a few scenarios
		// but must initializeSession attributes, if empty
		if (sessionAttributes.isEmpty()) sessionAttributes = initSessionAttributes();
		info("@ListenersRequestHandler: sessionAttributes are "
				+ (sessionAttributes.isEmpty() ? "empty" : "initialized"));

		// is it a non-firstEncounter launch request?
		if (input.matches(requestType(LaunchRequest.class))) {
			// is it at beginning of session?
			if ((int) sessionAttributes.get(FRAGMENTCOUNT) == NOT_YET_GREETED) {
				info("@ListenersRequestHandler: launch request at beginning of session");
				sessionAttributes.put(FRAGMENTCOUNT, 0);
				relationship = "ask"; // about startingOver
			}

			if (persistentAttributes.get("relationship") != null && persistentAttributes.get("relationship")
																																									.equals("normal")) {
				info("@ListenersRequestHandler: not first exchange but perhaps startingOver");
				relationship = "ask";
			}

			if ("ask".equals(relationship)) {
				// TODO
				// use Dialog interface if possible to confirm startingOver
				// for now:
				relationship = "normal";
				persistentAttributes.put("relationship", "normal");
			}
			else {
				persistentAttributes.put("relationship", "normal");
			}

			return new LsnrsLaunchResponse(persistentAttributes, sessionAttributes).getResponse(input,
					relationship);
		}

		// must be a non-firstEncounter IntentRequest
		info("@ListenersRequestHandler: non-firstEncounter IntentRequest");

		// we deal with STOP and CANCEL AMAZON intents right away, here
		if (input.matches(intentName("AMAZON.StopIntent").or(intentName("AMAZON.CancelIntent")))) {

			String cardTitle = "de_DE".equals(localeTag) ? S("Genug.", "Nicht mehr.")
					: S("That's e", "E") + "nough";
			String speech = speechUtils.getAbandonmentMessage();
			String bye = "de_DE".equals(localeTag) ? S("Tschüss!", "")
					: S("Cheerio!", "");
			speech += attributes.isPositive((String) sessionAttributes.get(AFFECT)) ? bye : "";
			ResponseFinisher rf = ResponseFinisher.builder()
																						.withSpeech(speechUtils.getAbandonmentMessage())
																						.build();

			// for now, on STOP or CANCEL we clear persistentAttributes
			// and set relationship to "ask"
			persistentAttributes.clear();
			persistentAttributes.put("relationship", "ask"); // comment this for firstEncounter
			attributesManager.savePersistentAttributes();
			return input.getResponseBuilder()
									.withSpeech(rf.getSpeech())
									.withSimpleCard(cardTitle, rf.getCardText())
									.withShouldEndSession(true)
									.build();
		}

		info("@ListenersRequestHandler:" + persistentAttributes + " " + sessionAttributes + " " + input
				+ " " + relationship);

		return new LsnrsIntentResponse(persistentAttributes, sessionAttributes).getResponse(input,
				relationship);

		// only here after a firstEncounter and if a session has started
		// info("@ListenersRequestHandler, persistentAttributes: " +
		// persistentAttributes.toString());

	}

}
