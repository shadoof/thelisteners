package listeners.l10n;

import static listeners.util.Utils.*;

public class CreepIntent_es_MX extends CreepIntent {

	public String buildCardTitle() {

		return S("¿Te damos miedo?", "¿Crees que damos miedo?");
	}

	public String buildSpeech() {

		String speech = s("Es algo " + r("impactante `desconcertante `preocupante "),
				"Nos " + s("perturba", "desconcierta" + s("un poco"))) + breath();
		speech += "lo que dices. " + breathShort();
		speech += "Definitivamente no " + s("queríamos", "teníamos la intención de") 
				+ s("inquietarte.", "alterarte.") + breathShort();
		speech += "" + s("Sólo", "solamente") + "queríamos escucharte. ";
		speech += s(breathShort() + "Oírte.");
		speech += breath();
		return speech;
	}

}
