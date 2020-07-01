package listeners.handlers;

import static com.amazon.ask.request.Predicates.requestType;
import static listeners.model.Attributes.FRAGMENTCOUNT_KEY;
import static listeners.model.Attributes.NOT_YET_GREETED;
import static listeners.util.ConstantUtils.info;
import static org.slf4j.LoggerFactory.getLogger;
import static listeners.model.LangConstants.localeTag;

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

import listeners.l10n.WelcomeSpeech;
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

		final LangConstants lc = new LangConstants(input.getRequestEnvelope().getRequest().getLocale());

		Map<String, Object> persistentAttributes = input.getAttributesManager().getPersistentAttributes();

		if (persistentAttributes == null) {
			// very first encounter
			info("@ListenersRequestHandler: first encounter"); // TODO
			// build first response
		}
		else if (persistentAttributes.get("relationship") != null && persistentAttributes.get("relationship").equals("StartingOver")) {
			// another encounter but is this an IntentRequest?
			info("@ListenersRequestHandler: another encounter but StartingOver"); // TODO
			// build response
		}
		// TODO get sessionAttributes

		if (input.matches(requestType(LaunchRequest.class))) {
			// neither very first nor StartingOver
			info("@ListenersRequestHandler: neither very first nor StartingOver");

			return new LaunchRequestResponse().getResponse(input);
		}
		// must be an IntentRequest
		info("@ListenersRequestHandler: handling an IntentRequest");
		return new IntentRequestResponse().getResponse(input);
	}
}
