package listeners.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.amazon.ask.attributes.AttributesManager;

import listeners.util.SpeechUtils;

public class Constants {
	
	// environment
	// These variables should be set in the Lambda function.
	// DEV - are you still developing?
	// LIVE - the behavior of the skill IS either live or 'as if' live.
	// PERFORMANCE - used in performance (other voices' drama shorter, etc.)
	public static boolean DEV = true;
	public static boolean LIVE = false;
	public static boolean PERFORMANCE = false;
	
	// version information
	// 2.2.2 - first release with differentation of other Englishes	
	// 2.5.0 - introducing Polly Voices at the behest of Amazon who do not
	// want The Listeners 'confused' with the Alexa personality:
	// stage one: female voice only for en-US, en-GB, en-IN, de-DE
	// 2.5.2 new version at request of Alexa Skills Team with "always listening"
	// remove, meanwhile 2.5.1 has been preserved on branch: listeners2.5
	// 2.6.0 - working towards this version which will launch German
	// 2.6.0a and 0b were in response to Amazon demands to remove 'always listening' instances
	// 3.0.0 factored for the latest Java ASK SDK and decent 110n
	public static final String VERSION = "3.0.0";
	public static final String VERSION_DATE_LIVE = "July 15, 2020";
	public static final String VERSION_DATE_DEV = "July 1, 2020";

	
	// configurable
	public static final int NUMBER_OF_FRAGMENTS = 10;
	public static final int NUMBER_OF_GUYZ = 35;
	public static final int NUMBER_OF_GUYZ_PER_BATCH = 5;
	public static final int NUMBER_OF_READABLES = 40;

  // for readability
	public static final String EXCEPTION_MESSAGE = "Sorry, I can't understand the command. Please say again.";
	public static final String SPC = " ";
	public static final Map<String, String> PAUSES_MAP;
	public static final HashSet<String> NO_MORE;
	public static final boolean POSITIVE = true;
	public static final boolean NEGATIVE = false;
	public static final boolean DO_NOT_PROMPT_AFFECT = false;
	public static final boolean SPECIFIC_FRAGMENT = true;
	public static final int REPROMPT = 1;
	public static final int SPEECH = 0;
	public static final int VERSE = 7;
	
	public static String localeTag;
	public static String localeLang;
	public static String localeCountry;
	public static Locale locale;
	
	// some useful instances
	public static AttributesManager attributesManager;
	public static Attributes attributes;
	public static LangConstants langConstants;
	public static SpeechUtils speechUtils;
			
	static {
		PAUSES_MAP = new HashMap<>();
		PAUSES_MAP.put("Shortestbreath", "0.25");
		PAUSES_MAP.put("Shortbreath", "0.5");
		PAUSES_MAP.put("Abreath", "1.0");
		PAUSES_MAP.put("Longbreath", "1.5");
		PAUSES_MAP.put("Longerbreath", "2.0");
		PAUSES_MAP.put("Longestbreath", "2.5");
		
		NO_MORE = new HashSet<>();
		NO_MORE.add("NoIntent");
		NO_MORE.add("ThankYouNoIntent");
		NO_MORE.add("AMAZON.StopIntent");
		NO_MORE.add("AMAZON.CancelIntent");

	}
	
	public static Locale parseLocale(String localeString) {
		localeLang = localeString.substring(0, 2);
		localeCountry = localeString.substring(3, 5).toUpperCase();
		localeTag = localeLang + "_" + localeCountry;
 		locale = new Locale(localeLang, localeCountry);
		return locale;
	}
}