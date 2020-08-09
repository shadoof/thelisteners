package listeners.l10n;

import static listeners.util.Utils.S;
import static listeners.util.Utils.breath;
import static listeners.util.Utils.breathShort;
import static listeners.util.Utils.breathShortest;
import static listeners.util.Utils.heads;
import static listeners.util.Utils.s;

public class SpkrsLoveLsnrsIntent_de_DE extends SpkrsLoveLsnrsIntent {

	public String buildCardTitle() {

		return S("Sie lieben uns?", "Wir lieben dich auch");
	}

	public String buildSpeech() {

		String speech = s("Natürlich. " + breathShort(), "") + s("Aber klar. " + breathShortest(), "");
		if (heads()) {
			speech += s("Wir können uns nicht vorstellen,", "Es fällt uns schwer, uns vorzustellen,")
					+ breathShort();
			speech += "dass du " + s("irgendeine andere Beziehung zu", "andere Gefühle für")
					+ "uns haben kannst als Liebe. " + breath();
		}
		else {
			speech += "Du hörst " + s("diese " + s("besondere", ""), "unsere ungewöhnliche")
					+ "Stimme, mit der wir mit Dir sprechen. " + breath();
		}
		speech += "Und wir "
				+ s(s("glaben " + s("fest daran,", ""), "meinen " + s("ehrlich", "")), "hoffen,")
				+ "dass du, je mehr Zeit du damit verbringst, " + s("uns zuzuhören,", "") + "desto mehr ";
		speech += s("Freude du " + s(breathShort(), "") + "an uns entwickeln wirst.",
				"Liebe du" + s(breathShort(), "") + " für uns haben wirst.") + s(breathShortest(), "")
				+ s("Und genauso wird es uns auch gehen.",
						"Wir werden " + s("uns an Dir freuen.", "dich lieben."));
		return speech + breath();
	}

}
