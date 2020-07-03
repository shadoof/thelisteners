package listeners.handlers;

import static com.amazon.ask.request.Predicates.requestType;
import static listeners.model.Attributes.FRAGMENTCOUNT_KEY;
import static listeners.model.Attributes.NOT_YET_GREETED;
import static listeners.model.Attributes.AMANAGER;
import static listeners.model.Attributes.LISTENERSAFFECT_KEY;
import static listeners.model.Attributes.initSessionAttributes;
import static listeners.util.ConstantUtils.info;
import static org.slf4j.LoggerFactory.getLogger;
import static listeners.model.LangConstants.localeTag;
import static listeners.model.Constants.DEV;
import static listeners.model.Constants.LIVE;
import static listeners.model.Constants.PERFORMANCE;

import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import org.slf4j.Logger;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

import listeners.l10n.Welcome;
import listeners.model.LangConstants;
import listeners.util.ResponseFinisher;

public class ListenersRequestHandler implements RequestHandler {

	private static Logger LOG = getLogger(ListenersRequestHandler.class);
	private String affect; // TODO

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

		// set up LanguageConstants
		final LangConstants lc = new LangConstants(input.getRequestEnvelope().getRequest().getLocale());

		AMANAGER = input.getAttributesManager();

		Map<String, Object> persistentAttributes = AMANAGER.getPersistentAttributes();
		Map<String, Object> sessionAttributes = AMANAGER.getSessionAttributes();

		String relationship = "normal";
		if (persistentAttributes.isEmpty() && sessionAttributes.isEmpty()) {
			// very first encounter
			info("@ListenersRequestHandler: firstEncounter");
			persistentAttributes.put("relationship", "normal");
			
			// initializing session attributes, only if empty
			if (sessionAttributes.isEmpty()) sessionAttributes = initSessionAttributes();
			// Listeners affect is set to a random affect:
			info("@ListenersRequestHandler: listenersAffect" + sessionAttributes.get(LISTENERSAFFECT_KEY));
			
			
			relationship = "firstEncounter";
			if (input.matches(requestType(LaunchRequest.class))) {
				return new LaunchRequestResponse(persistentAttributes, sessionAttributes).getResponse(input, relationship);
			} else {
				return new IntentRequestResponse(persistentAttributes, sessionAttributes).getResponse(input, relationship);
			}
		}
		
		// only here after a firstEncounter and if a session has started
		info("@ListenersRequestHandler, persistentAttributes: " + persistentAttributes.toString());

		if (input.matches(requestType(LaunchRequest.class))) {
			if (sessionAttributes.isEmpty()) {
				info("@ListenersRequestHandler: firstExchange");
				relationship = "ask";
			}
			
			if (sessionAttributes.get("relationship") != null && sessionAttributes.get("relationship").equals("normal")) {
				info("@ListenersRequestHandler: not first exchange but perhaps startingOver");
				relationship = "ask";
			}

			if ("ask".equals(relationship)) {
				// TODO
				// use Dialog interface if possible to confirm startingOver
				// for now:
				relationship = "normal";
				sessionAttributes.put("relationship","normal");
			} else {
				sessionAttributes.put("relationship","normal");
			}

			return new LaunchRequestResponse(persistentAttributes, sessionAttributes).getResponse(input, relationship);
		}

		// must be an IntentRequest
		info("@ListenersRequestHandler: handling an IntentRequest");
		return new IntentRequestResponse(persistentAttributes, sessionAttributes).getResponse(input, relationship);
	}
}
