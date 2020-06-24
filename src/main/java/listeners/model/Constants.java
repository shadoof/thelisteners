package listeners.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {
	
  public static final String EXCEPTION_MESSAGE = "Sorry, I can't understand the command. Please say again.";
	public static final String SPC = " ";
	public static final Map<String, String> PAUSES_MAP;
	public static final boolean POSITIVE = true, NEGATIVE = false, DO_NOT_PROMPT_AFFECT = false;
	public static final boolean SPECIFIC_FRAGMENT = true;
	
	static {
		PAUSES_MAP = new HashMap<>();
		PAUSES_MAP.put("Shortestbreath", "0.25");
		PAUSES_MAP.put("Shortbreath", "0.5");
		PAUSES_MAP.put("Abreath", "1.0");
		PAUSES_MAP.put("Longbreath", "1.5");
		PAUSES_MAP.put("Longerbreath", "2.0");
		PAUSES_MAP.put("Longestbreath", "2.5");
	}

}
