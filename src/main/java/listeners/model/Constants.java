package listeners.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import com.amazon.ask.attributes.AttributesManager;

public class Constants {

	// environment
	// These variables should be set in the Lambda function.
	// DEV - are you still developing?
	// LIVE - the behavior of the skill is either live or ‘as if’ live.
	// PERFORMANCE - used in performance (other voices’ drama is shorter, etc.)
	public static boolean DEV = true;
	public static boolean LIVE = false;
	public static boolean PERFORMANCE = false;
	public static String WILL = "";
	public static String LSNRS_DATE = "";

	// version information
	// 2.2.2 - first release with differentation of other Englishes
	// 2.5.0 - introducing Polly Voices at the behest of Amazon who do not
	// want The Listeners ‘confused’ with the Alexa personality:
	// stage one: female voice only for en-US, en-GB, en-IN, de-DE
	// 2.5.2 new version at request of Alexa Skills Team with "always listening"
	// remove, meanwhile 2.5.1 has been preserved on branch: listeners2.5
	// 2.6.0 - working towards this version which will launch German
	// 2.6.0a and 0b were in response to Amazon demands to remove ‘always
	// listening’ instances
	// 3.0.0 refactored for the latest Java ASK SDK and decent 110n
	// 3.0.1 uses of Attributes class is static
	// 3.0.2 version with de_DE
	public static final String VERSION = "3.0.1";

	// configurable
	public static final int NUMBER_OF_FRAGMENTS = 10;
	public static final int HOW_MANY_FRAGMENT_SETS = 1;
	public static final int NUMBER_OF_GUYZ = 35;
	public static final int NUMBER_OF_GUYZ_PER_BATCH = 5;
	public static final int NUMBER_OF_READABLES = 40;

	// for readability
	public static final String SPC = " ", GRV = "`", EQL = "=";
	public static final HashSet<String> DIALOG_INTENTS;
	public static final Map<String, String> PAUSES_MAP;
	public static final Map<Integer, Integer> PROBABILITY_MAP;
	public static final HashSet<String> NO_MORE;
	public static final boolean POSITIVE = true;
	public static final boolean NEGATIVE = false;
	public static final boolean DO_NOT_PROMPT_AFFECT = false;
	public static final boolean SPECIFIC_FRAGMENT = true;
	public static final int VERSE = 7;

	public static Locale locale;
	public static String localeCountry;
	public static String localeLang;
	public static String localeTag; // most used
	public static String polyVoice;

	// some useful instances
	public static AttributesManager attributesManager;
	public static LangConstants langConstants;
	public static ResourceBundle speechUtils;

	static {
		DIALOG_INTENTS = new HashSet<>();
		DIALOG_INTENTS.add("NoIntent");
		DIALOG_INTENTS.add("ThanksNoIntent");
		DIALOG_INTENTS.add("SpeakGuyzIntent");
		DIALOG_INTENTS.add("GuyzSpeechIntent");
		DIALOG_INTENTS.add("AskStartOverIntent");
		DIALOG_INTENTS.add("AskPersistenceIntent");

		PAUSES_MAP = new HashMap<>();
		PAUSES_MAP.put("Shortestbreath", "0.25");
		PAUSES_MAP.put("Shortbreath", "0.5");
		PAUSES_MAP.put("Abreath", "1.0");
		PAUSES_MAP.put("Longbreath", "1.5");
		PAUSES_MAP.put("Longerbreath", "2.0");
		PAUSES_MAP.put("Longestbreath", "2.5");

		PROBABILITY_MAP = new HashMap<>();
		PROBABILITY_MAP.put(0, 12);
		PROBABILITY_MAP.put(1, 10);
		PROBABILITY_MAP.put(2, 8);
		PROBABILITY_MAP.put(3, 6);
		PROBABILITY_MAP.put(4, 4);
		PROBABILITY_MAP.put(5, 4);
		PROBABILITY_MAP.put(6, 3);
		PROBABILITY_MAP.put(7, 3);
		PROBABILITY_MAP.put(8, 3);
		PROBABILITY_MAP.put(9, 2);
		PROBABILITY_MAP.put(10, 2);
		PROBABILITY_MAP.put(11, 2);
		PROBABILITY_MAP.put(12, 2);
		PROBABILITY_MAP.put(13, 1);
		PROBABILITY_MAP.put(14, 1);
		PROBABILITY_MAP.put(15, 1);
		PROBABILITY_MAP.put(16, 1);
		PROBABILITY_MAP.put(17, 1);
		PROBABILITY_MAP.put(18, 1);
		PROBABILITY_MAP.put(19, 1);
		PROBABILITY_MAP.put(20, 0);
		PROBABILITY_MAP.put(21, 0);
		PROBABILITY_MAP.put(22, 0);
		PROBABILITY_MAP.put(23, 0);
		PROBABILITY_MAP.put(24, 0);

		NO_MORE = new HashSet<>();
		NO_MORE.add("NoIntent");
		NO_MORE.add("ThanksNoIntent");
		NO_MORE.add("AMAZON.StopIntent");
		NO_MORE.add("AMAZON.CancelIntent");

	}

	public static Locale parseLocale(String localeString) {

		localeLang = localeString.substring(0, 2);
		localeCountry = localeString.substring(3, 5)
				.toUpperCase();
		localeTag = localeLang + "_" + localeCountry;
		locale = new Locale(localeLang, localeCountry);
		switch (localeTag) {
			case "de_DE":
				polyVoice = "<voice name='Marlene'><lang xml:lang='de-DE'>";
				break;
			case "ja_JP":
				polyVoice = "<voice name='Mizuki'><lang xml:lang='ja-JP'>";
				break;
			case "en_IN":
				polyVoice = "<voice name='Raveena'><lang xml:lang='en-IN'>";
				break;
			case "en_AU":
				polyVoice = "<voice name='Nicole'><lang xml:lang='en-AU'>";
				break;
			case "en_US":
			case "en_CA":
				polyVoice = "<voice name='Joanna'><lang xml:lang='en-US'>";
				break;
			default:
				polyVoice = "<voice name='Amy'><lang xml:lang='en-GB'>";
		}

		return locale;
	}
}