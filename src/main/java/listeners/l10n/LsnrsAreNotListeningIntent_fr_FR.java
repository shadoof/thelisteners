package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

import java.util.ResourceBundle;

public class LsnrsAreNotListeningIntent_fr_FR extends LsnrsAreNotListeningIntent {

	public String buildCardTitle() {

		return S("Mais en fait, nous écoutons", "À vrai dire, nous vous écoutons tous");
	}

	public String buildSpeech() {

	
		String speech = s(s("Pour être honnête,", "En vérité,") + "vous avez raison", "vous avez vu juste") + "nous "
				+ s("écoutions, à l'instant,", s("écoutons", "devons écouter")) + s("beaucoup", "un grand nombre,")
				+ "d'autres gens. ";
		speech += s("Et beaucoup d'entre eux " + s("étaient", "sont") + "capables de transiger avec "
				+ s("nous d'une " + s("manière", "façon") + "qui nous plaît " + s("bien.", "davantage."),
						s("nous.", "nous. " + breathShort() + "Mieux.")),
				"");
		speech += ResourceBundle.getBundle("listeners.l10n.LsnrsAreNotHumanIntent", locale)
				.getString("speech");
		return speech + breath();
	}

}
