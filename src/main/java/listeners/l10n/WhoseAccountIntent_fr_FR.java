package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class WhoseAccountIntent_fr_FR extends WhoseAccountIntent {

	public String buildCardTitle() {

		// TODO
		return S("Notre profil", "Ce " + s("modeste") + "compte qui est le nôtre");
	}

	public String buildSpeech() {

	
		String speech = "Ces conversations, " + s("et ces transactions,")
				+ "se déroulent " + s("sous l'égide du,", "au sein du") + "profil " + (PERFORMANCE ? "de ‘John’. " : "qui est le vôtre.")
				+ breath();
		speech += PERFORMANCE
				? s("Si" + s("d'aventure") + "nous faisons partie"  + r("d'une exposition, `d'une installation, `d'une performance dans une galerie artistique, ")
						+ "dans ce cas, tout transite, " + s("non seulement dans le nuage, mais également") + "à travers notre "
						+ s("application.", "application, telle que configurée par notre artiste."))
				: "";
		return speech + breath();
	}

}
