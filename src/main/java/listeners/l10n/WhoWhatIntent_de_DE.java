package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class WhoWhatIntent_de_DE extends WhoWhatIntent {

	public String buildCardTitle() {

		return S("Fragen, Fragen, Fragen",
				"Wir können nicht alles wissen. " + s("Wohin würden wir alles tun?"));
	}

	public String buildSpeech() {

		String speech = s("Wir sind", "Wir glauben, wir sind") + "die " + s("Lauschenden.", "Zuhörer.")
				+ breath();
		speech += s("Sie können uns: ‘Die Zuhörer’ nennen. " + s("Oder ‘John’.")
				+ s(breath() + "Wir sind das " + breathShortest() + "gewohnt.") + breathShort());
		speech += s("Was wir auch", "Was immer wir") + "sagen ist uns " + s("oder Ihnen")
				+ "viel wichtiger als die Tatsache, dass "
				+ breathShort() + s("wir Ihnen zuhören.", "wir hören, " + breathShort() + "was Sie sagen.");
		// ALWAYCHANGE - s(breath() + "Always.") +
		speech += s(breath() + "Hier und jetzt.") + s(breath() + "Vielleicht für immer. ");
		return speech + breath();
	}

}
