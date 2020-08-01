package listeners.handlers;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.SessionEndedRequest;
import org.slf4j.Logger;

import static listeners.model.Constants.attributesManager;
import static listeners.model.Attributes.sessAttributes;
import static listeners.model.Attributes.PERSISTENCE;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;
import static org.slf4j.LoggerFactory.getLogger;

public class SessionEndedRequestHandler implements RequestHandler {

	private static Logger LOG = getLogger(SessionEndedRequestHandler.class);

	@Override
	public boolean canHandle(HandlerInput input) {

		return input.matches(requestType(SessionEndedRequest.class));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {

		SessionEndedRequest sessionEndedRequest = (SessionEndedRequest) input	.getRequestEnvelope()
																																					.getRequest();
		LOG.debug("Session ended with reason: " + sessionEndedRequest	.getReason()
																																	.toString());
		// save attributes
		sessAttributes.put(PERSISTENCE, "ask");
		attributesManager.setPersistentAttributes(sessAttributes);
		attributesManager.savePersistentAttributes();
		
		return Optional.empty();
	}

}
