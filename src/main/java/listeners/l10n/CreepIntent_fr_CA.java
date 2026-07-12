package listeners.l10n;

import static listeners.util.Utils.*;

public class CreepIntent_fr_CA extends CreepIntent {

	public String buildCardTitle() {

		// TODO
		return S("Croyez-vous que nous vous rendons inconfortable?", "Pensez-vous que nous vous rendons inconfortable?");
	}

	public String buildSpeech() {

		// TODO
		String speech = s("C’est un peu " + r("choquant `perturbant `troublant "),
				"Nous sommes " + s("choqués", s("un peu") + "vexés")) + breath();
		speech += "d’apprendre cela. " + breathShort();
		speech += "Nous n’avons certainement pas " + s("l’intention", "la volonté") + "de "
				+ s("vous occasioner un malaise.", "‘rendre inconfortable’.") + breathShort();
		speech += "Nous voulons " + s("seulement", "simplement") + "vous écouter. ";
		speech += s(breathShort() + "Vous écouter.");
		speech += breath();
		return speech;
	}

}
