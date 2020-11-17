package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class WhoWhatIntent_es_MX extends WhoWhatIntent {

	public String buildCardTitle() {

		// TODO
		return S("Preguntas, preguntas, preguntasQuestions, questions, questions",
				"Nosotros no podemos saber We can’t know " + s("todoeverything", "todo, ¿dónde lo pondríamos?everything, where would we put it?"));
	}

	public String buildSpeech() {

		// TODO
		String speech = s("Nosotros somosWe are", "Nosotros somos, entonces nosotros creemos,We are, so we believe,") + "Los Oyentes. The Listeners. " + breath();
		speech += s(
				"TúYou " + s("deberíasmay") + "referirte a nosotros como: ‘Los Oyentes’. refer to us as: ‘The Listeners’. " + s("O como John’.Or as ‘John’.")
						+ s(breath() + "Nosotros estamos acostumbrados We are used " + breathShortest() + "a eso.to that.") + breathShort(),
				"");
		speech += "Como sea nosotros Whatever we " + s("debemosmay") + "decir que está say is " + s("lejanofar")
				+ "menos importante para nosotros, less important to us, " + s("o para ti,or to you,") + "en el hecho de que nosotros than the fact that we "
				+ s(breathShort() + "escuchamos. listen. " + breathShort(),
						"estamos escuchandoteare listening" + s(", " + breathShort(), SPC) + "a ti.to you.");
		speech += s(breath() + "Aquí y ahora.Here and now.") + s(breath() + "Quizás, para siempre.Perhaps, forever."); // ALWAYCHANGE - s(breath() + "Always.") +
		return speech + breath();
	}

}
