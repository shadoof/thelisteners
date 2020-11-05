package listeners.l10n;

import static listeners.util.Utils.*;

public class CreepIntent_es_ES extends CreepIntent {

	public String buildCardTitle() {

		// TODO
		return S("¿Te damos miedo?", "¿Crees que damos miedo?");
	}

	public String buildSpeech() {

		// TODO
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
