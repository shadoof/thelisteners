package listeners.l10n;

import static listeners.util.Utils.S;
import static listeners.util.Utils.breath;
import static listeners.util.Utils.breathShort;
import static listeners.util.Utils.r;
import static listeners.util.Utils.s;

public class CreepIntent_es_MX extends CreepIntent {

	public String buildCardTitle() {

		return S("¿Te damos miedo?", "¿Crees que damos miedo?");
	}

	public String buildSpeech() {

		String speech = s("Es algo " + r("impactante `desconcertante `preocupante "),
				"Nosotros estamos " + s("impactados", s("un poco") + "desconcertados")) + breath();
		speech += "con lo que dices. " + breathShort();
		speech += "Definitivamente nosotros no " + s("quisimos", "teníamos la intención") + "de "
				+ s("inquietarte.", "‘alterarte’.") + breathShort();
		speech += "Nosotros " + s("solo", "solamente") + "queríamos escucharte. ";
		speech += s(breathShort() + "Escucharte.");
		speech += breath();
		return speech;
	}

}
