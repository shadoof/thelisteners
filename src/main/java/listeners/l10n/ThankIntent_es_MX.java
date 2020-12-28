package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class ThankIntent_es_MX extends ThankIntent {

	public String buildCardTitle() {

		return "Con " + s("mucho") + "gusto";
	}

	public String buildSpeech() {

		String speech = s(s("Con") + s("mucho") + "gusto.",
				s("No es nada.", s("Por favor.") + "No tienes que agradecernos.")) + breath();
		speech += s("Nosotres trabajamos incansablemente para tratar de " + s("entenderte a", "entenderte, y creer en,")
				+ "ti.you.");
		return speech + breath();

	}

}
