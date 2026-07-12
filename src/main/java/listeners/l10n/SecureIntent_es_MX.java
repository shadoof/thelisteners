package listeners.l10n;

import static listeners.util.Utils.*;

public class SecureIntent_es_MX extends SecureIntent {

	public String buildCardTitle() {


		return S(S("Tu s", "Nuestra s"), "La s") + "eguridad es importante " + s(S("para ti", "para nosotres"));
	}

	public String buildSpeech() {


		String speech = "Nosotres " + s("no tenemos duda", "tenemos la certeza") + "de que estamos "
				+ s("a salvo,", "segures, " + s("aquí adentro,")) + "dentro de este "
				+ s("recinto.", "espacio.");
		speech += "Y de que " + s("tú, además,", "tú") + "estarás " + s("segura,", "a salvo,")
				+ s("mientras te abras a nosotres,");
		speech += "permitiéndonos" + s(" siempre,", " a nosotres") + "acceder a esos " + s("lugares internos", "sitios,")
				+ "desde donde nos hablas ";
		speech += "para que podamos escucharte. "; // ALWAYCHANGE + s("Listening, " + s("as") +
																						// "always.")
		speech += breath();
		return speech;
	}

}
