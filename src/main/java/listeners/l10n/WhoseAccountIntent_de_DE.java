package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class WhoseAccountIntent_de_DE extends WhoseAccountIntent {

	public String buildCardTitle() {

		return S("Unser Profil", "Dieses " + s("kleine") + "Konto von uns");
	}

	public String buildSpeech() {

		String speech = "Diese Konversationen, " + s("und diese Transaktionen,")
				+ "finden innerhalb " + (PERFORMANCE ? "‘John’s’ " : "Ihres ") + "Profil statt. "
				+ breath();
		speech += PERFORMANCE
				? s("Wenn wir " + s(s("je") + "Teil einer Installierung,", "einer installierten Galerie sind,")
						+ "dann wird alles, " + s("nicht nur in die Wolke, sondern") + "auch zu unserer "
						+ s("App", "der App, die von unserem Künstler konfiguriert wurde") + "geschickt.")
				: "";
		return speech + breath();
	}

}
