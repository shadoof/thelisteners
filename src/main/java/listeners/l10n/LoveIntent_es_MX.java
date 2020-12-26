package listeners.l10n;

import static listeners.util.Utils.*;

public class LoveIntent_es_MX extends LoveIntent {

	public String buildCardTitle() {

		return S("Nosotres te amamos", "Esperamos que tú sientas lo mismo");
	}

	public String buildSpeech() {

		String speech = "Por supuesto. " + breathShort() + s("Por supuesto que lo hacemos. " + breathShort());
		speech += "Es " + s("difícil", "duro") + "para nosotros " + s("imaginar", "concebir") + "cualquier ";
		speech += s("otro") + s("vínculo con", "sentimiento por") + "ti que nosotres " + s("podamos", "debamos") + "tener, " + s("aparte de", "además de") + "amor. ";
		speech += "Y cuanto más " + s("te escuchemos", "te oigamos") + "a ti, " + s("cuanto más sepamos de ti,,") + "más " + s("vamos a") + "amarte. ";
		speech += s(breathShort() + "Esto parece " + s("obvio, para nosotres.", "obvio."));
		return speech + breath();
	}
	
}
