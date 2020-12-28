package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class WhoAreSpkrsIntent_es_MX extends WhoAreSpkrsIntent {

	public String buildCardTitle() {

		return "Quién " + "pensamos que eres";
	}

	public String buildSpeech() {

		String speech = "Creemos que ustedes son los " + s("verdaderos " + breathShort()) + "‘hablantes’. "
				+ s("Que ustedes son todos.") + "Todos, aparte de, nosotres. ";
		return speech + breath();
	}

}
