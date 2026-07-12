package listeners.l10n;

import static listeners.model.Constants.DEV;
import static listeners.model.Constants.LSNRS_DATE;
import static listeners.model.Constants.PERFORMANCE;
import static listeners.model.Constants.VERSION;
import static listeners.model.Constants.localeTag;
import static listeners.model.Constants.speechUtils;
import static listeners.util.Utils.*;

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
			s = s("Merci de votre " + s("intérêt.", "question.") + breath());
			s += "Nous " + s("préférons", "aimerions davantage")
					+ "que vous considériez cette date comme celle de notre plus récente réincarnation, qui était ";
			s += LSNRS_DATE + ". " + breath();
			s += s("Il nous semble toujours que c'est très récent. "
					+ s("Mais nous ne sommes " + s("certainement") + "pas nées de la dernière pluie.") + breath());
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
