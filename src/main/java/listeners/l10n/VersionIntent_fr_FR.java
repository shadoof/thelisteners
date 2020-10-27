package listeners.l10n;

import static listeners.model.Constants.DEV;
import static listeners.model.Constants.LSNRS_DATE;
import static listeners.model.Constants.PERFORMANCE;
import static listeners.model.Constants.VERSION;
import static listeners.model.Constants.localeTag;
import static listeners.model.Constants.speechUtils;
import static listeners.util.Utils.breath;
import static listeners.util.Utils.s;

// TODO
public class VersionIntent_fr_FR extends VersionIntent {

	public String buildCardTitle() {

		return "Version";
	}

	public String buildPostSpeechPrompt() {

		if (DEV) {
			return ""; // empty string
		}
		else
			return speechUtils.getString("chooseContinueNoAffect");
	}

	public String buildReprompt() {

			return speechUtils.getString("chooseContinueNoAffect");
	}

	public String buildSpeech() {

		String s;
		if (DEV) {
			s = getVersionLocale();
		}
		else {
			s = s("Thank you for " + s("your interest.", "the question.") + breath());
			s += "We " + s("prefer to", "would rather")
					+ "think of this as the date of our last rebirth, which was ";
			s += LSNRS_DATE + ". " + breath();
			s += s("It seems always to be very recent. "
					+ s("But we " + s("certainly") + "were not born yesterday.") + breath());
		}
		return s += breath();
	}

	protected String getVersionLocale() {

		String s = "Development. " + VERSION + ", " + LSNRS_DATE
				+ (PERFORMANCE ? ". Performance. " : ". ");
		switch (localeTag) {
			case "de_DE":
				s += "German. ";
				break;
			case "en_GB":
				s += "British English. ";
				break;
			case "en_IN":
				s += "South Asian English. ";
				break;
			case "en_AU":
				s += "Australian English. ";
				break;
			case "en_CA":
				s += "Canadian English. ";
				break;
			case "ja_JP":
				s += "Japanese. ";
				break;
			default: // "en_US" etc.
				s += "United States English. ";
		}
		return s;
	}

}
