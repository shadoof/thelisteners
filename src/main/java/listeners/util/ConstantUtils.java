package listeners.util;

import static listeners.model.Constants.PAUSES_MAP;
import static listeners.model.Constants.SPC;
import static listeners.model.Constants.DEV;

import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

public class ConstantUtils {

	private static final Random RANDOM = new Random();

	// ***** GENERAL HELPER / CONVENIENCE METHODS *****

	public static int randInt(int min, int max) {

		return RANDOM.nextInt((max - min) + 1) + min;
	}

	public static void info(String s) {
		
		if (DEV) 
		System.out.println("[INFO] " + s);
	}

	public static boolean heads() {

		return randInt(0, 1) == 1;
	}

	
	// ***** STRING CONVENIENCE METHODS *****
	
	public static String capitalize(String s) {

		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	public static String s(String firstAlternative, String secondAlternative) {

		return heads() ? firstAlternative.equals("") ? firstAlternative : firstAlternative + SPC : secondAlternative.equals("") ? secondAlternative : secondAlternative + SPC;
	}

	public static String S(String firstAlternative, String secondAlternative) {

		return heads() ? firstAlternative : secondAlternative;
	}

	public static String stripSsmlTags(String speech) {

		return speech.replaceAll("<[^>]*>", "");
	}

	public static String insertPauseTags(String speech) {

		Set<String> hs = new HashSet<>();
		hs = PAUSES_MAP.keySet();
		for (String s : hs) {
			speech = speech.replaceAll(s + SPC, "<break time=\"" + PAUSES_MAP.get(s) + "s\"/>");
			speech = speech.replaceAll(s + "\\(", "<break time=\"" + PAUSES_MAP.get(s) + "s\"/>(");
		}
		return speech;
	}

	public static String locTag(Locale locale) {
		return locale.getLanguage() + "_";
	}
	
	public static String removeInterSentencePauses(String speech) {

		Set<String> hs = new HashSet<>();
		hs = PAUSES_MAP.keySet();
		for (String s : hs) {
			speech = speech.replaceAll("\\. " + s, ".");
		}
		return speech;
	}

	public static String phonemic(String englishLetter) {

		// String prefix = "<phoneme alphabet=\"ipa\" ph=\"", phonemic = "", medial
		// = "\">", suffix = "</phoneme>";
		String prefix = "<phoneme alphabet=\"x-sampa\" ph=\"", phonemic = "", medial = "\">", suffix = "</phoneme>";
		switch (englishLetter) {
			case "a":
				// phonemic = prefix + "æ:" + medial + englishLetter + suffix;
				phonemic = "{:";
				break;

			default:
				break;
		}
		return prefix + phonemic + medial + englishLetter + suffix;
	}

	@SuppressWarnings("unused")
	public static String pause(float seconds) {

		return "<break time=\"" + seconds + "s\"/>";
	}

	public static String breathShortest() {

		return "Shortestbreath "; // pause(.25f);
	}

	public static String breathShort() {

		return "Shortbreath "; // pause(.5f);
	}

	public static String breath() {

		return "Abreath "; // pause(1f);
	}

	public static String breathLong() {

		return "Longbreath "; // pause(1.5f);
	}

	public static String breathLonger() {

		return "Longerbreath "; // pause(2f);
	}

	@SuppressWarnings("unused")
	public static String breathLongest() {

		return "Longestbreath "; // pause(2.5f);
	}

}
