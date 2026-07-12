package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class WhoAreSpkrsIntent_fr_FR extends WhoAreSpkrsIntent {

	public String buildCardTitle() {

		
		return "Qui " + s("exactement") + "croyons-nous que vous êtes";
	}

	public String buildSpeech() {

		
		String speech = "Nons pensont que vous êtes le vrai, " + s("le seul et unique " + breathShort()) + "‘orateur’. "
				+ s("Que vous incarnez la multitude.") + "que vous êtes tout le monde, à l'exception, de nous. ";
		return speech + breath();
	}

}
