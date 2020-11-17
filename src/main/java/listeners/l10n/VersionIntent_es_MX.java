package listeners.l10n;

import static listeners.model.Constants.DEV;
import static listeners.model.Constants.LSNRS_DATE;
import static listeners.model.Constants.PERFORMANCE;
import static listeners.model.Constants.VERSION;
import static listeners.model.Constants.localeTag;
import static listeners.model.Constants.speechUtils;
import static listeners.util.Utils.*;

// TODO
public class VersionIntent_es_MX extends VersionIntent {

	public String buildCardTitle() {

		return "VersiónVersion";
	}

	public String buildPostSpeechPrompt() {

		if (DEV) {
			return ""; // empty string
		}
		else
			return speechUtils.getString("elegirContinuarNoAfectachooseContinueNoAffect");
	}

	public String buildReprompt() {

			return speechUtils.getString("elegirContinuarNoAfectachooseContinueNoAffect");
	}

	public String buildSpeech() {

		String s;
		if (DEV) {
			s = getVersionLocale();
		}
		else {
			s = s("Gracias por Thank you for " + s("tu interés.your interest.", "la pregunta.the question.") + breath());
			s += "Nosotros We " + s("preferimosprefer to", "preferiríamoswould rather")
					+ "pensar esto como la fecha de nuestro último renacimiento, eso fue think of this as the date of our last rebirth, which was ";
			s += LSNRS_DATE + ". " + breath();
			s += s("Eso siempre parece ser muy reciente. It seems always to be very recent. "
					+ s("Pero nosotros But we " + s("indudablementecertainly") + "no nacimos ayer.were not born yesterday.") + breath());
		}
		return s += breath();
	}

	protected String getVersionLocale() {

		String s = "Development. " + VERSION + ", " + LSNRS_DATE
				+ (PERFORMANCE ? ". Performance. " : ". ");
		switch (localeTag) {
			case "de_DE":
				s += "Alemán. German. ";
				break;
			case "en_GB":
				s += "Inglés Británico. British English. ";
				break;
			case "en_IN":
				s += "Inglés del Sur de Asia. South Asian English. ";
				break;
			case "en_AU":
				s += "Ingles Australiano. Australian English. ";
				break;
			case "en_CA":
				s += "Inglés Canadiense. Canadian English. ";
				break;
			case "ja_JP":
				s += "Japonés. Japanese. ";
				break;
			default: // "en_US" etc.
				s += "Inglés Estadounidense. United States English. ";
		}
		return s;
	}

}
