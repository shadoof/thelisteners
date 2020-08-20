package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class ThankIntent_de_DE extends ThankIntent {
	
	public String buildCardTitle() {

		return r("Bitte.`Gar nicht für.`Gern geschehen.");
	}

	public String buildSpeech() {

		String speech = r("Bitte. `Bitte, bitte. `Ist schon gut.") + breath();
		speech += S("Wir arbeiten dauernd daran, dich zu verstehen", s(" und an dich zu glauben.", "."));
		return speech + breath();

	}

}
