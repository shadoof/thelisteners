package listeners.util;

import static listeners.model.Constants.speechUtils;

import java.util.HashMap;


public class SessionMap extends HashMap<String, Object> {

	public Object put(String key, Object value) {
		Object o = super.put(key, value);
		if (speechUtils != null) speechUtils = SpeechUtils.getNewBundle();
		return o;
	}

	public Object justPut(String key, Object value) {
		
		return super.put(key, value);
	}

}
