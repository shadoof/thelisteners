package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class WhoSaysGuyzIntent_fr_FR extends WhoSaysGuyzIntent {

	public String buildCardTitle() {

		
		return "Qui " + s("est cet homme?", "sont ces " + s("autres?", "gens?"));
	}

	public String buildSpeech() {

	
		String speech = s(s("Il n'est", "Ce n'est"), "Ils ne sont") + "rien. ";
		speech += s("tout comme " + s("tu es", "nous ne sommes") + "rien.");
		if (randInt(0, 2) > 0) speech += "Nous " + s(s("croyons", "sommes confiantes à l'effet"), "espérons")
				+ "que vous " + s("N'écouterez pas", "ne porterez pas attention", "n'accorderez aucune valeur") + "à "
				+ s("cette autre voix.", "ces autres voix.");
		return speech + breath();
	}

}
