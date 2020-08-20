package listeners.l10n;

import static listeners.model.Constants.DEV;
import static listeners.model.Constants.LSNRS_DATE;
import static listeners.util.Utils.breath;
import static listeners.util.Utils.s;

public class VersionIntent_de_DE extends VersionIntent {

	public String buildSpeech() {

		String s;
		if (DEV) {
			s = getVersionLocale();
		}
		else {
			s = s("Danke für die Frage.", "Vielen Dank für Ihr Interesse.") + breath();
			s += "Wir " + s("ziehen es vor, dies", "würden das leiber")
					+ "als das Datum unserer letzten Wiedergeburt zu betrachten, das war ";
			s += LSNRS_DATE + ". " + breath();
			s += s("Es scheint immer vor Kurzem zu sein."
					+ s("Aber wir sind nicht von gestern."));
		}
		return s += breath();
	}

}
