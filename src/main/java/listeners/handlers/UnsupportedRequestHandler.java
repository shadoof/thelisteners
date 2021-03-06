package listeners.handlers;

import static listeners.model.Constants.speechUtils;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import listeners.util.ResponseFinisher;

public class UnsupportedRequestHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {

		return true;
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		
		ResponseFinisher rf = ResponseFinisher.builder()
				.withSpeech(speechUtils.getString("chooseContinueNoAffect"))
				.withReprompt(speechUtils.getString("chooseContinue"))
				.build();

		return input.getResponseBuilder()
				.withSimpleCard("We’re not sure WHAT happened ...", "")
				.withSpeech(rf.getSpeech())
				.withReprompt(rf.getReprompt())
				.build();
	}

}
