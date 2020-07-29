package listeners.handlers;

import static listeners.model.Constants.speechUtils;
import static listeners.util.Utils.info;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Optional;

import org.slf4j.Logger;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

import listeners.util.ResponseFinisher;

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
