package listeners.l10n;

import static listeners.util.Utils.*;

public class SecureIntent_es_MX extends SecureIntent {

	public String buildCardTitle() {


		return S(S("Tu s", "Nuestra s"), "La s") + "eguridad es importante " + s(S("para ti", "para nosotres"));
	}

	public String buildSpeech() {


		String speech = "Nosotres " + s("estamos convencides", "creemos") + "de que estamos "
				+ s("a salvo,", "segures, " + s("dentro,")) + "dentro de este "
				+ s("recinto.", "espacio nuestro.");
		speech += "Y de que " + s("tú, además,", "tú") + "estarás " + s("segura,", "a salvo,")
				+ s("mientras te abras a nosotres,");
		speech += "permitiendo" + s("nos, siempre,", "nos") + "entrar en esos " + s("lugares", "lugares,dentro,")
				+ "desde donde nos hablas ";
		speech += "para que podamos escucharte. "; // ALWAYCHANGE + s("Listening, " + s("as") +
																						// "always.")
		speech += breath();
		return speech;
	}

}
