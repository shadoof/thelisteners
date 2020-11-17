package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class WhoSaysGuyzIntent_es_MX extends WhoSaysGuyzIntent {

	public String buildCardTitle() {

		// TODO
		return "¿Quién Who " + s("es este sujeto?is this guy?", "es son estosare these " + s("otros?others?", "chicos?guys?"));
	}

	public String buildSpeech() {

		// TODO
		String speech = s(s("Ël esHe is", "Eso esIt is"), "Ellos sonThey are") + "nada. nothing. ";
		speech += s("Solo como Just as " + s("tú eresyou", "nosotros somoswe") + "nada.are nothing.");
		if (randInt(0, 2) > 0) speech += "Nosotros We " + s(s("confiamostrust", "estamos segurosare confident"), "esperamoshope")
				+ "que tú no vas athat you will not " + s("escucharlisten", "poner atención algunapay any heed") + "a to "
				+ s("esta otra voz.this other voice.", s("estasthese") + "otras voces.other voices.");
		return speech + breath();
	}

}
