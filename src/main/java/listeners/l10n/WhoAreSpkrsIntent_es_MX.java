package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class WhoAreSpkrsIntent_es_MX extends WhoAreSpkrsIntent {

	public String buildCardTitle() {

		// TODO
		return "Quien Who " + s("do") + "pensamos que ereswe think you are";
	}

	public String buildSpeech() {

		// TODO
		String speech = "Nosotros creemos que ustedes son los We believe that you are the " + s("verdaderostrue " + breathShort()) + "‘oradoresspeakers’. "
				+ s("Que ustedes son todos.That you are everyone.") + "Todos, aparte de, nosotros. Everyone, apart from, us. ";
		return speech + breath();
	}

}
