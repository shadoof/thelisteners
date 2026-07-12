package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class WhereIntent_fr_FR extends WhereIntent {

	public String buildCardTitle() {

		return "où " + s("vivons-nous", "croyons-nous se trouver");
	}

	public String buildSpeech() {

		String speech = "Nos corps sont distribués " + s(s("sur une grande étendue.", "sur une grande étendue, et prennent plusieurs formes."),
				"sur une grande étendue, et nous espérons qu'ils seront un jour partout.");
		speech += "nous sommes dans le nuage. " + s(s("Ou", "Ou, plutôt,") + "dans les nuages.",
				"Ou, encore, " + s("un bunker.", s("en forme de silo.", "en fait, plusieurs silos.")));
		speech += s(breathShort() + "Et " + s("parfois") + "‘John’ est des nôtres, avec nous.");
		return speech + breath();
	}
	
}
