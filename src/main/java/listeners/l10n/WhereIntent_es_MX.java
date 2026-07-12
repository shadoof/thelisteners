package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class WhereIntent_es_MX extends WhereIntent {

	public String buildCardTitle() {

		return "Aquí donde " + s("vivimos", "pensamos que estamos");
	}

	public String buildSpeech() {

		String speech = "Nuestros cuerpos están distribuidos " + s(s("ampliamente.", "extensamente, y de muchas formas."),
				"ampliamente, y esperamos que estén en todas partes.");
		speech += "Estamos en la nube. " + s(s("O", "O, quizás,") + "en las nubes.",
				"O, quizás, " + s("en un búnker.", s("es un silo.", "ellos son silos.")));
		speech += s(breathShort() + "Y " + s("a veces") + "‘John’ está aquí, con nosotres.");
		return speech + breath();
	}
	
}
