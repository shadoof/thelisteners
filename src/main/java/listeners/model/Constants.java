package listeners.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {
	
	// TODO environment
	public static final boolean DEV = true;
	public static final boolean LIVE = false;
	public static final boolean PERFORMANCE = false;
	
	// configurable
	public static final int NUMBER_OF_FRAGMENTS = 10;
	public static final int NUMBER_OF_GUYZ = 35;
	public static final int NUMBER_OF_READABLES = 40;

  // for readability
	public static final String EXCEPTION_MESSAGE = "Sorry, I can't understand the command. Please say again.";
	public static final String SPC = " ";
	public static final Map<String, String> PAUSES_MAP;
	public static final boolean POSITIVE = true;
	public static final boolean NEGATIVE = false;
	public static final boolean DO_NOT_PROMPT_AFFECT = false;
	public static final boolean SPECIFIC_FRAGMENT = true;
	public static final int REPROMPT = 1;
	public static final int SPEECH = 0;
	public static final int VERSE = 7;
			
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