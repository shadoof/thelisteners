package listeners.l10n;

import static listeners.model.Constants.DEV;
import static listeners.model.Constants.LSNRS_DATE;
import static listeners.model.Constants.PERFORMANCE;
import static listeners.model.Constants.VERSION;
import static listeners.model.Constants.localeTag;
import static listeners.model.Constants.speechUtils;
import static listeners.util.Utils.*;

public class VersionIntent_es_MX extends VersionIntent {

	public String buildCardTitle() {

		return "Versión";
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
			s = s("Gracias por " + s("tu interés.", "la pregunta.") + breath());
			s += "Nosotres" + s("preferimos", "preferiríamos")
					+ "pensar ésta como la fecha de nuestro último renacimiento, que fue ";
			s += LSNRS_DATE + ". " + breath();
			s += s("Siempre parece muy reciente. "
					+ s("Pero " + s("no cabe duda que") + "no nacimos ayer.") + breath());
		}
		return s += breath();
	}

	protected String getVersionLocale() {

		String s = "Desarrollo. " + VERSION + ", " + LSNRS_DATE
				+ (PERFORMANCE ? ". Ejecución. " : ". ");
		switch (localeTag) {
			case "de_DE":
				s += "Alemán. ";
				break;
			case "en_GB":
				s += "Inglés Británico. ";
				break;
			case "en_IN":
				s += "Inglés del Sur de Asia. ";
				break;
			case "en_AU":
				s += "Inglés Australiano. ";
				break;
			case "en_CA":
				s += "Inglés Canadiense. ";
				break;
			case "ja_JP":
				s += "Japonés. ";
				break;
			default: // "en_US" etc.
				s += "Inglés Estadounidense. ";
		}
		return s;
	}

}
