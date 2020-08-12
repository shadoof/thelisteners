package listeners.handlers;

import static listeners.model.Attributes.PERSISTENCE;
import static listeners.model.Attributes.initSessionAttributes;
import static listeners.model.Attributes.sessAttributes;
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
import com.amazon.ask.model.Response;

import listeners.model.Constants;
import listeners.model.LangConstants;
import listeners.util.ResponseFinisher;
import listeners.util.SpeechUtils;

public class ExceptionHandler implements com.amazon.ask.dispatcher.exception.ExceptionHandler {

	private static Logger LOG = getLogger(SessionEndedRequestHandler.class);

	@Override
	public boolean canHandle(HandlerInput input, Throwable throwable) {

		return true;
	}

	@Override
	public Optional<Response> handle(HandlerInput input, Throwable throwable) {

		LOG.error("Error message is " + throwable.getMessage());
		info("Cause: " + throwable.getCause());
		throwable.printStackTrace();

		if (locale == null) {
			Locale l = Constants.parseLocale("en-gb");
		}
		langConstants = LangConstants.getInstance(locale);
		if (sessAttributes == null) sessAttributes = initSessionAttributes();
		speechUtils = SpeechUtils.getNewBundle();

		// save attributes just in case
		if (sessAttributes != null) {
			info("@ExceptionHandler: saving attributes with PERSISTENCE: " + sessAttributes.get(PERSISTENCE));
			attributesManager.setPersistentAttributes(sessAttributes);
			attributesManager.savePersistentAttributes();
		}

		ResponseFinisher rf = ResponseFinisher.builder()
				.withSpeech(speechUtils.getString("exceptionMessage"))
				.withReprompt(speechUtils.getString("chooseContinueNoAffect"))
				.build();

		return input.getResponseBuilder()
				.withSimpleCard("Not reading you ...", rf.getCardText())
				.withSpeech(rf.getSpeech())
				.withReprompt(rf.getReprompt())
				.build();
	}

}
