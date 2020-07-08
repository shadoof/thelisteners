package listeners.handlers;

import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.requestType;
import static listeners.model.Attributes.LISTENERSAFFECT;
import static listeners.model.Attributes.initSessionAttributes;
import static listeners.model.Constants.DEV;
import static listeners.model.Constants.LIVE;
import static listeners.model.Constants.PERFORMANCE;
import static listeners.model.Constants.localeTag;
import static listeners.model.Constants.attributesManager;
import static listeners.model.Constants.attributes;
import static listeners.model.Constants.langConstants;
import static listeners.model.Constants.speechUtils;
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

		return input.matches(requestType(LaunchRequest.class)) || input.matches(requestType(IntentRequest.class));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {

		// get environment variables
		// which should be set up up in Lambda but will
		// default to a Development set: true, false, false
		DEV = (System.getenv("DEV") == null) ? DEV : Boolean.parseBoolean(System.getenv("DEV"));
		LIVE = (System.getenv("LIVE") == null) ? LIVE : Boolean.parseBoolean(System.getenv("LIVE"));
		PERFORMANCE = (System.getenv("PERFORMANCE") == null) ? PERFORMANCE : Boolean.parseBoolean(System.getenv("PERFORMANCE"));

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

		langConstants = LangConstants.getInstance(locale);

		speechUtils = SpeechUtils.getInstance(locale);

		String relationship = "normal";
		if (persistentAttributes.isEmpty() && sessionAttributes.isEmpty()) {
			// very first encounter
			info("@ListenersRequestHandler: firstEncounter");
			persistentAttributes.put("relationship", "normal");

			// initializing session attributes, only if empty
			if (sessionAttributes.isEmpty()) sessionAttributes = initSessionAttributes();
			info("@ListenersRequestHandler: sessionAttributes are " + (sessionAttributes.isEmpty() ? "empty" : "initialized"));
			// Listeners affect is set to a random affect:
			info("@ListenersRequestHandler, listenersAffect: " + sessionAttributes.get(LISTENERSAFFECT));
		}
		
		// same test again while developing STOP & CANCEL TODO
		if (persistentAttributes.isEmpty() && sessionAttributes.isEmpty()) {
			relationship = "firstEncounter";
			if (input.matches(requestType(LaunchRequest.class))) {
				return new LsnrsLaunchResponse(persistentAttributes, sessionAttributes).getResponse(input, relationship);
			}
			else {
				return new LsnrsIntentResponse(persistentAttributes, sessionAttributes).getResponse(input, relationship);
			}
		}

		// only here after a firstEncounter and if a session has started
		info("@ListenersRequestHandler, persistentAttributes: " + persistentAttributes.toString());

		if (input.matches(requestType(LaunchRequest.class))) {
			if (sessionAttributes.isEmpty()) {
				info("@ListenersRequestHandler: firstExchange");
				relationship = "ask";
			}

			if (sessionAttributes.get("relationship") != null && sessionAttributes.get("relationship")
																																						.equals("normal")) {
				info("@ListenersRequestHandler: not first exchange but perhaps startingOver");
				relationship = "ask";
			}

			if ("ask".equals(relationship)) {
				// TODO
				// use Dialog interface if possible to confirm startingOver
				// for now:
				relationship = "normal";
				sessionAttributes.put("relationship", "normal");
			}
			else {
				sessionAttributes.put("relationship", "normal");
			}

			return new LsnrsLaunchResponse(persistentAttributes, sessionAttributes).getResponse(input, relationship);
		}

		// must be an IntentRequest
		info("@ListenersRequestHandler: handling an IntentRequest");

		// we should deal with some AMAZON intents right away, here
		if (input.matches(intentName("AMAZON.StopIntent").or(intentName("AMAZON.CancelIntent")))) {

			String cardTitle = "de_DE".equals(localeTag) ? S("Genug.", "Nicht mehr.") : S("That's e", "E") + "nough";
			ResponseFinisher rf = ResponseFinisher.builder()
																						.withSpeech(speechUtils.getAbandonmentMessage())
																						.build();

			return input.getResponseBuilder()
									.withSpeech(rf.getSpeech())
									.withSimpleCard(cardTitle, rf.getCardText())
									.withShouldEndSession(true)
									.build();
		}

		return new LsnrsIntentResponse(persistentAttributes, sessionAttributes).getResponse(input, relationship);
	}
}
