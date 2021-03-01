package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class WhoWhatIntent_es_MX extends WhoWhatIntent {

	public String buildCardTitle() {

		return S("Preguntas, preguntas, preguntas",
				"Nosotres no podemos saberlo " + s("todo", "todo, ¿dónde lo pondríamos?"));
	}

	public String buildSpeech() {

		String speech = s("Somos", "Somos, o eso creemos,") + "Quienes Escuchan. " + breath();
		speech += s(
				"Tú" + s("puedes") + "referirte a nosotres como: ‘Quienes Escuchan’. " + s("O como ‘John’.")
						+ s(breath() + "Estamos acostumbrades " + breathShortest() + "a eso.") + breathShort(),
				"");
		speech += "Lo que sea que " + s("podamos") + "decir, es " + s("mucho")
				+ "menos importante para nosotres, " + s("o para ti,") + "que el hecho de que "
				+ s(breathShort() + "escuchamos. " + breathShort(),
						"estamos escuchándote" + s(", " + breathShort(), SPC) + "a ti.");
		speech += s(breath() + "Aquí y ahora.") + s(breath() + "Quizá, para siempre."); // ALWAYCHANGE - s(breath() + "Always.") +
		return speech + breath();
	}

}
