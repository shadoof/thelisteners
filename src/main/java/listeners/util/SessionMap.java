package listeners.util;

import static listeners.model.Constants.speechUtils;
import static listeners.model.Attributes.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SessionMap extends HashMap<String, Object> {
	
	// *** NOT USED - retained for reference ***
	
	private static final HashSet<String> attributesThatForceSpeechVariation;
	
	static {
		attributesThatForceSpeechVariation = new HashSet();
		String[] s = {AFFECT, CHALLENGEDAFFECT, LISTENERSAFFECT, PREVIOUSAFFECT, THING};
		for (int i = 0; i < s.length; i++) {
			attributesThatForceSpeechVariation.add(s[i]);			
		}
	}

	public Object put(String key, Object value) {

		Object o = super.put(key, value);
		if (attributesThatForceSpeechVariation.contains(key)) {
			if (speechUtils != null) speechUtils = SpeechUtils.getNewBundle();
		}
		return o;
	}

	public void getValuesFromMap(Map<String, Object> persAttributes) {

		for (Map.Entry<String, Object> entry : persAttributes.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof BigDecimal) {
				// info(key + ": " + ((BigDecimal) value).intValue());
				put(key, ((BigDecimal) value).intValue());
			}
			else
				put(key, value);
		}

	}

}
