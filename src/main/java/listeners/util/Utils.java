package listeners.util;

import static listeners.model.Constants.PAUSES_MAP;
import static listeners.model.Constants.EQL;
import static listeners.model.Constants.GRV;
import static listeners.model.Constants.SPC;
import static listeners.model.Constants.DEV;

import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

public class Utils {

	private static final Random RANDOM = new Random();

	public static boolean heads() {

		return randInt(0, 1) == 1;
	}

	public static void info(String s) {

		if (DEV) System.out.println("[INFO] " + s);
	}

	public static int randInt(int min, int max) {

		return RANDOM.nextInt((max - min) + 1) + min;
	}

	// ***** CONVENIENCE METHODS FOR STRINGS *****

	public static String r(String s) {

		return r(s, GRV);
	}

	public static String r(String s, String d) {

		String[] sa = s.split(d);
		return sa[randInt(0, sa.length - 1)];
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

	public static String breathLongest() {

		return "Longestbreath "; // pause(2.5f);
	}

	public static String breathShort() {

		return "Shortbreath "; // pause(.5f);
	}

	public static String breathShortest() {

		return "Shortestbreath "; // pause(.25f);
	}

	public static String capitalize(String s) {

		if (s == null || s.isEmpty()) return "";
		return s.substring(0, 1)
				.toUpperCase() + s.substring(1);
	}

	public static String insertPauseTags(String speech) {

		Set<String> hs = new HashSet<>();
		hs = PAUSES_MAP.keySet();
		for (String s : hs) {
			speech = speech.replaceAll(s + SPC, "<break time=\"" + PAUSES_MAP.get(s) + "s\"/>");
			speech = speech.replaceAll(s + "\\(", "<break time=\"" + PAUSES_MAP.get(s) + "s\"/>(");
		}
		return speech.replaceAll("\\s{2,}", SPC);
	}

	public static String phonemic(String englishLetter) {

		// String prefix = "<phoneme alphabet=\"ipa\" ph=\"", phonemic = "", medial
		// = "\">", suffix = "</phoneme>";
		String prefix = "<phoneme alphabet=\"x-sampa\" ph=\"", phonemic = "", medial = "\">",
				suffix = "</phoneme>";
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

	public static String removeInterSentencePauses(String speech) {

		Set<String> hs = new HashSet<>();
		hs = PAUSES_MAP.keySet();
		for (String s : hs) {
			speech = speech.replaceAll("\\. " + s, ".");
		}
		return speech;
	}

	public static String removePauseTags(String speech) {

		Set<String> hs = new HashSet<>();
		hs = PAUSES_MAP.keySet();
		for (String s : hs) {
			speech = speech.replaceAll(s + SPC, "");
			speech = speech.replaceAll(s + "\\(", "(");
		}
		return speech.replaceAll("\\s{2,}", SPC);
	}

	public static String s(String phrase) {

		return heads() ? phrase + SPC : "";
	}

	public static String s(String firstAlternative, String secondAlternative) {

		if (heads()) {
			return "".equals(firstAlternative) ? firstAlternative : firstAlternative + SPC;
		}
		else {
			return "".equals(secondAlternative) ? secondAlternative : secondAlternative + SPC;
		}
	}

	public static String S(String firstAlternative, String secondAlternative) {

		return heads() ? firstAlternative : secondAlternative;
	}

	public static String stripSsmlTags(String speech) {

		return speech.replaceAll("<[^>]*>", "");
	}

}
