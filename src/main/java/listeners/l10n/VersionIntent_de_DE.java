package listeners.l10n;

import static listeners.model.Constants.DEV;
import static listeners.model.Constants.LIVE;
import static listeners.model.Constants.VERSION_DATE_LIVE;
import static listeners.util.ConstantUtils.breath;
import static listeners.util.ConstantUtils.s;

public class VersionIntent_de_DE extends VersionIntent {

	public String buildSpeech() {

		String s;
		if (DEV && !LIVE) {
			s = getVersionLocale();
		}
		else {
			s = s("Danke für die Frage.", "Vielen Dank für Ihr Interesse.") + breath();
			s += "Wir " + s("ziehen es vor, dies", "würden das leiber")
					+ "als das Datum unserer letzten Wiedergeburt zu betrachten, das war ";
			// TODO l10n for VERSION_DATE_LIVE
			s += VERSION_DATE_LIVE + ". " + breath();
		}
		return s += breath();
	}

}
