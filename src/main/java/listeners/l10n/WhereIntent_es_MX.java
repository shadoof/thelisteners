package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class WhereIntent_es_MX extends WhereIntent {

	public String buildCardTitle() {

		// TODO
		return "Donde nosotros Where we " + s("vivimoslive", "pensamos que estamosthink we are");
	}

	public String buildSpeech() {

		// TODO
		String speech = "Nuestros cuerpos están distribuidos Our bodies are distributed " + s(s("ampliamente.widely.", "extensamente, y de muchas formas.widely, and take many forms."),
				"ampliamente, y nosotros esperamos que estén en todas partes.widely, and we hope they may be everywhere.");
		speech += "Nosotros estamos en la nube. We are in the cloud. " + s(s("OOr", "O, quizás,Or, perhaps,") + "en las nubes.in the clouds.",
				"O, quizás, Or, perhaps, " + s("un búnker.a bunker.", s("es un silo.it is a silo.", "ellos son silos.they are silos.")));
		speech += s(breathShort() + "Y And " + s("a vecessometimes") + "‘John’ está aquí, con nosotros.’John’ is here, with us.");
		return speech + breath();
	}
	
}
