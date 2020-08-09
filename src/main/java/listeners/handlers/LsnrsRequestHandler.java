package listeners.handlers;

import static com.amazon.ask.request.Predicates.requestType;
import static listeners.model.Attributes.AFFECT;
import static listeners.model.Attributes.FRAGMENTINDEX;
import static listeners.model.Attributes.LASTINTENT;
import static listeners.model.Attributes.NOT_YET_GREETED;
import static listeners.model.Attributes.PERSISTENCE;
import static listeners.model.Attributes.getValuesFrom;
import static listeners.model.Attributes.initSessionAttributes;
import static listeners.model.Attributes.isPositive;
import static listeners.model.Attributes.persAttributes;
import static listeners.model.Attributes.sessAttributes;
import static listeners.model.Constants.DEV;
import static listeners.model.Constants.LIVE;
import static listeners.model.Constants.LSNRS_DATE;
import static listeners.model.Constants.NUMBER_OF_FRAGMENTS;
import static listeners.model.Constants.PERFORMANCE;
import static listeners.model.Constants.WILL;
import static listeners.model.Constants.attributesManager;
import static listeners.model.Constants.langConstants;
import static listeners.model.Constants.locale;
import static listeners.model.Constants.localeTag;
import static listeners.model.Constants.speechUtils;
import static listeners.util.Utils.S;
import static listeners.util.Utils.info;
import static listeners.util.Utils.s;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentConfirmationStatus;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

import listeners.l10n.Welcome;
import listeners.model.Constants;
import listeners.model.LangConstants;
import listeners.util.ResponseFinisher;
import listeners.util.SpeechUtils;
import listeners.util.UnknownIntentException;

public class LsnrsRequestHandler implements RequestHandler {

	// private static Logger LOG = getLogger(LsnrsRequestHandler.class);

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
		LSNRS_DATE = System.getenv("LSNRS_DATE");
		LSNRS_DATE = (LSNRS_DATE == null) ? "August 1, 2020" : LSNRS_DATE;

		// get the AttributesManager and put it in Constants
		attributesManager = input.getAttributesManager();
		persAttributes = attributesManager.getPersistentAttributes();

		WILL = System.getenv("WILL");
		if (WILL == null || WILL.isEmpty()) {
			WILL = (String) persAttributes.get(PERSISTENCE);
			// info("@LsnrsRequestHandler, persAttributes" + persAttributes);
			// info("from persAttributes, PERSISTENCE:" + persAttributes.get(PERSISTENCE));
			if (WILL == null && LIVE) {
				// this is a firstEncounter measure
				persAttributes.put(PERSISTENCE, "remember");
				attributesManager.savePersistentAttributes();
			}
		}

		info("@LsnrsRequestHandler, WILL: " + WILL); // only null on firstEncounter

		// ... although Voices are wrapped according to regions
		// TODO put a note in the documentation, ultimately:
		// for use in other English speaking regions:
		// British English, en-gb *text* is the default for this skill:
		Locale.setDefault(new Locale("en", "GB"));
		// LangConstants depend on locale so
		// we assemble locale constants in top-level model.Constants:
		String ls = input.getRequestEnvelope()
				.getRequest()
				.getLocale();
		info("@LsnrsRequestHandler, setting locale to: " + ls);
		locale = Constants.parseLocale(ls);
		// langConstants also housed in model.Constants:
		langConstants = LangConstants.getInstance(locale); // singleton

		// possibilities are
		// *** 0. ask with either request
		// *** 1. firstEncounter launch request
		// *** 2. sessionStart launch request
		// *** 3. normal launch request
		// *** 4. firstEncounter intent request
		// *** 5. sessionStart intent request
		// *** 6. normal intent request (most common)

		// determining session attributes
		if (WILL == null || WILL.isEmpty()) {
			// very first encounter 1. and 4.
			sessAttributes = initSessionAttributes();
			WILL = "firstEncounter";
		}
		else if ("remember".equals(WILL) || "ask".equals(WILL)) {
			// either retrieve what was saved from the last closed session
			// or use what is there if still in the same session
			if (sessAttributes == null) sessAttributes = getValuesFrom(persAttributes);
		}
		else if ("forget".equals(WILL)) {
			// when forget is set, initialized attributes
			// have been persisted, so
			// only initialize again if they are null for some reason
			if (sessAttributes == null) {
				sessAttributes = initSessionAttributes();
				persAttributes.put(PERSISTENCE, "forget");
				attributesManager.savePersistentAttributes();
			}
		}
		attributesManager.setSessionAttributes(sessAttributes);

		// speechUtils cannot be fetched until
		// sessAttributes are initialized or retrieved
		// refresh speechUtils each time (cache is also cleared)
		speechUtils = SpeechUtils.getNewBundle();

		if (input.matches(requestType(LaunchRequest.class))) {
			// *** 1. 2. and 3. ***
			info("@LsnrsRequestHandler, LaunchRequest");
			// *** what we'd like to do
			// with Auto Delegation disabled
			// but ? we can't change
			// the requestType of any input even for a dialog ***
			// if ("ask".equals(WILL)) {
			// Intent launchAsk = Intent.builder()
			// .withName("LaunchAskIntent")
			// .build();
			// return input.getResponseBuilder()
			// .addDelegateDirective(launchAsk)
			// .withSpeech("delegating to: LaunchAskIntent")
			// .build();
			// }
			return new LsnrsLaunchResponse(input).getResponse();
		}
		else {
			// ANY Intent request
			// dealing with AMAZON built-in intents
			// and some others here:
			LsnrsIntentResponse lir = new LsnrsIntentResponse(input, WILL);
			info("@LsnrsRequestHandler, IntentRequest: " + lir.intentName);
			// if ("LaunchAskIntent".equals(lir.intentName)) {
			// info("@LaunchAskIntent: does get here ...");
			// return input.getResponseBuilder()
			// .withSpeech("Worked")
			// .withShouldEndSession(true)
			// .build();
			// }
			if (lir.intentName.startsWith("AMAZON.")) {
				InnerResponse ir;
				ResponseFinisher rf;
				boolean endSession = false;
				switch (lir.intentName) {
					case "AMAZON.StopIntent":
					case "AMAZON.CancelIntent":
						// set up response
						ir = new InnerResponse();
						ir.setCardTitle(
								"de_DE".equals(localeTag) ? S("Genug.", "Nicht mehr.") : S("That’s e", "E") + "nough");
						ir.setSpeech(speechUtils.getString("getAbandonmentMessage"));
						String bye = "de_DE".equals(localeTag) ? s("Tschüss!", "") : s("Cheerio!", "");
						ir.setSpeech(ir.getSpeech()
								+ (isPositive((String) sessAttributes.get(AFFECT)) ? bye : ""));
						ir.setSpeech(ir.getSpeech() + ("remember".equals(sessAttributes.get(PERSISTENCE))
								? "Until " + s("the", "") + "next time. "
								: ""));

						endSession = true;

						// check to see if we came here directly
						if (!"AskPersistenceIntent".equals(sessAttributes.get(LASTINTENT))) {
							info("@LsnrsRequestHandler: direct Stop or Cancel ‘ask’ persistence next time ...");

							// on direct STOP or CANCEL we set relationship to "ask"
							sessAttributes.put(PERSISTENCE, "ask");

							// this is only used for testing, simulating nothing persisted
							// usually environment vars are: LIVE true and WILL empty
							// to clear persistence:
							// 1. set LIVE false and WILL forget
							// 2. one AMAZON.StopIntent
							// 3. set LIVE true and WILL empty
							if (!LIVE) {
								info("@LsnrsRequestHandler: ... but LIVE was false and persistence has been cleared");
								// adjust if needed when developing
								persAttributes.clear();
								attributesManager.setPersistentAttributes(persAttributes);
								attributesManager.savePersistentAttributes();
								break;
							}
						}
						// got here from AskPersistence:
						else {
							if ("forget".equals(sessAttributes.get(PERSISTENCE))) {
								sessAttributes = initSessionAttributes();
								sessAttributes.put(PERSISTENCE, "forget");
							}
						}
						// this is the only place to savePersistentAttributes
						sessAttributes.put(LASTINTENT, lir.intentName);
						attributesManager.setPersistentAttributes(sessAttributes);
						info("@StopIntent, shld be saving persistents with: " + sessAttributes.get(PERSISTENCE));
						attributesManager.savePersistentAttributes();
						break;
					case "AMAZON.StartOverIntent":

						ir = (InnerResponse) speechUtils.getObject("AskStartOverIntent");
						rf = ResponseFinisher.builder()
								.withSpeech(ir.getSpeech())
								.build();

						if (lir.intent.getConfirmationStatus() == IntentConfirmationStatus.NONE) {
							return input.getResponseBuilder()
									.addConfirmIntentDirective(lir.intent)
									.withSpeech(rf.getSpeech())
									.build();
						}
						else if (lir.intent.getConfirmationStatus() == IntentConfirmationStatus.CONFIRMED) {
							sessAttributes = initSessionAttributes();
							attributesManager.setSessionAttributes(sessAttributes);
							ir.setSpeech(speechUtils.getString("startOverConfirmed"));
						}
						else {
							ir.setSpeech(speechUtils.getString("startOverDenied"));
						}
						break;
					case "AMAZON.HelpIntent":
						ir = new InnerResponse(speechUtils.getString("helpCardTitle"),
								speechUtils.getString("chooseSpeechAssistance"));
						break;
					case "AMAZON.RepeatIntent":
						ir = new InnerResponse();
						ir.setCardTitle(speechUtils.getString("repeatCardTitle"));
						int fragmentIndex = (int) sessAttributes.get(FRAGMENTINDEX);
						if (fragmentIndex > NOT_YET_GREETED && fragmentIndex < NUMBER_OF_FRAGMENTS) {
							// build variant fragments just before they’re needed:
							langConstants.buildFragments();
							ir.setSpeech(langConstants.fragments[fragmentIndex]
									+ speechUtils.getString("chooseContinueNoAffect"));
						}
						else {
							Welcome ws = (Welcome) ResourceBundle.getBundle("listeners.l10n.Welcome", locale);
							ir.setSpeech(ws.getSpeech() + speechUtils.getString("chooseContinueNoAffect"));
						}
						break;
					default:
						ir = null;
				}

				rf = ResponseFinisher.builder()
						.withSpeech(ir.getSpeech())
						.withReprompt(ir.getReprompt())
						.build();
				sessAttributes.put(LASTINTENT, lir.intentName);
				return input.getResponseBuilder()
						.withSpeech(rf.getSpeech())
						.withSimpleCard(ir.getCardTitle(), rf.getCardText())
						.withShouldEndSession(endSession)
						.build();
			}

			// ALL other modeled or Unknown intents
			try {
				// *** 4. 5. and 6. ***
				return new LsnrsIntentResponse(input, WILL).getResponse();
			}
			catch (UnknownIntentException e) {
				info("@LsnrsRequestHandler, UnknownIntentException: " + e.getMessage());
				return null;
			}
		}
	}
}
