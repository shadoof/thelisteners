package listeners.l10n;

import static listeners.util.Utils.*;

public class LoveIntent_es_MX extends LoveIntent {

	public String buildCardTitle() {

		return S("Nosotros te amamos", "Nosotros esperamos que tú sientas lo mismo");
	}

	public String buildSpeech() {

		String speech = "Por supuesto. " + breathShort() + s("Por supuesto que lo hacemos. " + breathShort());
		speech += "Es " + s("difícil", "duro") + "para nosotros " + s("imaginar", "concebir") + "cualquier ";
		speech += s("otro") + s("vínculo conrelation with", "sentimiento porfeeling for") + "ti que nosotros you that we " + s("podamosmight", "debamoscould") + "tener, have, " + s("aparte de eseapart from that of", "otro other than") + "amor. love. ";
		speech += "Y cuando más And the more we " + s("te escuchamoshear of", "te oímoslisten to") + "a ti, you, " + s("cuanto más sabemos de ti,the more we know about you,") + "nosotros más the more we " + s("vamos awill") + "amarte. love you. ";
		speech += s(breathShort() + "Esto parece This seems " + s("obvio, para nosotros.obvious, to us.", "obvio.obvious."));
		return speech + breath();
	}
	
}
