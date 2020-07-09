package listeners.handlers;

import java.util.Map;

public abstract class LsnrsResponse implements LsnrsResponsible {

	protected Map<String, Object> persistentAttributes;
	protected Map<String, Object> sessionAttributes;

	public LsnrsResponse(Map<String, Object> persistentAttributes,
			Map<String, Object> sessionAttributes) {

		this.persistentAttributes = persistentAttributes;
		this.sessionAttributes = sessionAttributes;
	}
}
