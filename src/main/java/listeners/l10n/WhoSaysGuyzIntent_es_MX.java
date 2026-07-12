package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class WhoSaysGuyzIntent_es_MX extends WhoSaysGuyzIntent {

	public String buildCardTitle() {

		return "¿Quién" + s(" es esta persona?", "es son estas " + s("otras personas?", "personas?"));
	}

	public String buildSpeech() {

		String speech = s(s("Él no es", "Eso no es"), "Ellos no son") + "nada. ";
		speech += s("Así como " + s("tú no eres", "nosotres no somos") + "nada.");
		if (randInt(0, 2) > 0) speech += "Nosotres " + s(s("confiamos", "sabemos con seguridad"), "esperamos")
				+ "que tú no vas a " + s("escuchar", "hacer caso") + "a "
				+ s("esa otra voz.", s("estas") + "otras voces.");
		return speech + breath();
	}

}
