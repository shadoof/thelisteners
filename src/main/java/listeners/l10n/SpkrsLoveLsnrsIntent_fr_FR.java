package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class SpkrsLoveLsnrsIntent_fr_FR extends SpkrsLoveLsnrsIntent {

	public String buildCardTitle() {

		return S("Vous nous aimez?", "Nous vous aimons également");
	}

	public String buildSpeech() {

	
		String speech = s("Bien sûr. " + breathShort()) + s("Bien sûr, c'est ainsi. " + breathShort());
		if (heads()) {
			speech += "Il est " + s("difficile", "ardu") + "pour nous de " + s("s'imaginer", "concevoir") + "toute ";
			speech += s("autre") + s("relation avec", "sentiment pour") + "nous que vous puissiez"
					+ s("avoir", "ressentir") + s("hormis celui", "autre que") + "de l'amour. "
					+ breath();
		}
		else {
			speech += "vous entendez cette " 
					+ s("voix, avec laquelle nous parlons, à vous.", "voix qui est la nôtre.") + breath();
		}
		speech += "et nous " + s("croyons", s("sincèrement,", "fermement,") + "nous espérons")
				+ "que, plus vous nous fréquenterez, "
				+ s("et que nous vous écouterons, " + breathShort()) + "de fois en fois ";
		speech += s("plus votre allégresse augmentera " + s(breathShort()) + "à notre égard.", "plus vous aurez d'amour "
				+ s(breathShort()) + "pour nous. " + s(breathShort() + "Et réciproquement."));
		return speech + breath();
	}
	
	public String getReprompt() {
		
		return speechUtils.getString("chooseContinueNoAffect");
	}

}
