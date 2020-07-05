package listeners.handlers;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

public interface LsnrsResponsible {
	
	Optional<Response> getResponse(HandlerInput input, String relationship);

}
