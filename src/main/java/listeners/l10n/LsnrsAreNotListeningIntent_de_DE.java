package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

import java.util.ResourceBundle;

public class LsnrsAreNotListeningIntent_de_DE extends LsnrsAreNotListeningIntent {

	public String buildCardTitle() {

		return S("Aber eigentlich hören wir zu", "In Wahrheit hören wir so vielen zu");
	}

	public String buildSpeech() {

		String speech = s("Ganz ehrlich", "In Wahrheit") + s("sind Sie", "haben Sie") + "Recht, wir "
				+ r("hörten Ihnen gerade zu`hören Ihnen zu`müssen wir Ihnen zuhören") + ", aber auch "
				+ s("so vielen", "vielen") + "anderen. ";
		speech += s("Und viele dieser anderen "
				+ s("gehen mit uns in einer Weise um", "tauschen sich mit uns in einer Weise aus")
				+ "die uns besser gefällt. " + breathShort() + "Besser.");
		speech += ResourceBundle.getBundle("listeners.l10n.LsnrsAreNotHumanIntent", locale)
				.getString("speech");
		return speech + breath();
	}

}
