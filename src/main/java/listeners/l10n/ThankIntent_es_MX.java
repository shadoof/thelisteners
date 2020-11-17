package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class ThankIntent_es_MX extends ThankIntent {

	public String buildCardTitle() {

		// TODO
		return "Tú eres You’re " + s("muyvery") + "bienvenidowelcome";
	}

	public String buildSpeech() {

		// TODO
		String speech = s(s("Tú eresYou’re", "You are") + s("muyvery") + "bienvenido.welcome.",
				s("Eso es nada.It’s nothing.", s("Por favor.Please.") + "No pienses en eso.Think nothing of it.")) + breath();
		speech += s("Nosotros trabajamos incansablemente para tratar de We do work tirelessly to try and " + s("entenderte aunderstand", "entender, y creer en,understand, and to believe in,")
				+ "ti.you.");
		return speech + breath();

	}

}
