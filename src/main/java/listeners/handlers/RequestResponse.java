package listeners.handlers;

import java.util.Map;

public abstract class RequestResponse implements RequestResponsible {
	
	protected Map<String, Object> persistentAttributes;
	protected Map<String, Object> sessionAttributes;

	RequestResponse(Map<String, Object> persistentAttributes, Map<String, Object> sessionAttributes) {
		this.persistentAttributes = persistentAttributes;
		this.sessionAttributes = sessionAttributes;
	}
}
