package listeners.handlers;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

import listeners.util.UnknownIntentException;

public interface LsnrsResponse {

	Optional<Response> getResponse() throws UnknownIntentException;

}
