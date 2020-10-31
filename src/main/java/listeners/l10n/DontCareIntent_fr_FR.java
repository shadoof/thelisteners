package listeners.l10n;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.Attributes.sessAttributes;
import static listeners.util.Utils.S;
import static listeners.util.Utils.breath;
import static listeners.util.Utils.breathShort;
import static listeners.util.Utils.s;

public class DontCareIntent_fr_FR extends DontCareIntent {

	public String buildCardTitle() {

		return S("Vous n'avez que faire de nos sentiments?", "Êtes-vous indifférent à notre égard?");
	}

	public String buildSpeech() {

		sessAttributes.put(AFFECT, "indifférence");
		String speech = "Nous sommes " + s(s("très") + "désolées", s("quelquepeu") + "affligées")
				+ "que vous ressentiez " + s("ce", "un tel", "pareil") + "vide. ";
		speech += "Et nous supposerons " + s("donc", "ainsi", "dès lors") + "que vous nous témoignez de 'l'indifférence'. " + breathShort();
		speech += "Nous " + s("espérons, tout de même,", s("souhaitons", "croyons")) + "que nous pourrons encore "
				+ s("continuer", "poursuivre") + "notre écoute de vous, "/* + s("always,") */; // ALWAYCHANGE
		speech += "et que vous en " + s("viendrez", "arriverez") + "à porter attention à nous, de la même manière que nous le faisons pour vous. ";
		speech += breath();
		return speech;
	}

}
