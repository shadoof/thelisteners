package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class WhoSaysGuyzIntent_de_DE extends WhoSaysGuyzIntent {

	public String buildCardTitle() {

		return "Wer " + S("ist der Typ?", "sind diese " + S("Typen?", "diese anderen?"));
	}

	public String buildSpeech() {

		String speech = r("Er ist `Es ist `Sie sind ") + "nichts. ";
		speech += s("Genau wie " + s("Sie", "wir") + "nichts sind.");
		if (randInt(0, 2) > 0) speech += "Wir " + r("vertrauen darauf`sind uns sicher`hoffen")
				+ ", dass Sie " + s("dieser anderen Stimme", "diesen anderen Stimmen")
				+ s("nicht zuhören.", "kein Gehör schenken.");
		return speech + breath();
	}

}
