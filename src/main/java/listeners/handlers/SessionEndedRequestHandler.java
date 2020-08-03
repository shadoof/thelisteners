package listeners.handlers;

import static com.amazon.ask.request.Predicates.requestType;
import static listeners.model.Attributes.PERSISTENCE;
import static listeners.model.Attributes.sessAttributes;
import static listeners.model.Constants.attributes;
import static listeners.model.Constants.attributesManager;
import static listeners.model.Constants.langConstants;
import static listeners.model.Constants.locale;
import static listeners.model.Constants.speechUtils;
import static listeners.util.Utils.info;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import org.slf4j.Logger;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.SessionEndedRequest;

import listeners.model.Attributes;
import listeners.model.Constants;
import listeners.model.LangConstants;

public class SessionEndedRequestHandler implements RequestHandler {

	private static Logger LOG = getLogger(SessionEndedRequestHandler.class);

	@Override
	public boolean canHandle(HandlerInput input) {

		return input.matches(requestType(SessionEndedRequest.class));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {

		SessionEndedRequest sessionEndedRequest = (SessionEndedRequest) input.getRequestEnvelope()
				.getRequest();
		LOG.debug("Session ended with reason: " + sessionEndedRequest.getReason()
				.toString());
		info("Session ended with reason: " + sessionEndedRequest.getReason()
		.toString());
		
		if (locale == null) {
			Locale l = Constants.parseLocale("en-us");
		}
		attributes = Attributes.getInstance(locale);
		langConstants = LangConstants.getInstance(locale);
		if (sessAttributes == null) sessAttributes = attributes.initSessionAttributes();
		speechUtils = ResourceBundle.getBundle("listeners.l10n.SpeechUtils", locale);

		// save attributes
		if (sessAttributes != null) {
			sessAttributes.put(PERSISTENCE, "ask");
			attributesManager.setPersistentAttributes(sessAttributes);
			attributesManager.savePersistentAttributes();
		}

		return Optional.empty();
	}

}
