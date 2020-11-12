package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class ThankIntent_fr_FR extends ThankIntent {

	public String buildCardTitle() {

		return "Merci beaucoup," + s("vraiment", "profondément");
	}

	public String buildSpeech() {

		String speech = s(s("Tout le", "Cela nous fait") + s("grand") + "plaisir.",
				s("Ce n'est rien.", s("S'il-vous-plaît.") + "N'y pensez plus.")) + breath();
		speech += s("Nous travaillons constamment et" + s("sans relâche", "avec acharnement") + " à " + s("vous comprendre", "vous comprendre, et croire en vous,")
				+ "pour notre bien.");
		return speech + breath();

	}

}
