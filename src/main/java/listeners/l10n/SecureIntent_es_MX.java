package listeners.l10n;

import static listeners.util.Utils.*;

public class SecureIntent_es_MX extends SecureIntent {

	public String buildCardTitle() {


		return S(S("Tu s", "Nuestra s"), "La s") + "eguridad es importante " + s(S("para ti", "para nosotres"));
	}

	public String buildSpeech() {


		String speech = "Nosotres " + s("estamos convencides", "creemos") + "que estamos "
				+ s("a salvo,", "segures, " + s("dentro,")) + "dentro de este "
				+ s("recinto.", "espacio nuestro.");
		speech += "Y que " + s("tú, además,", "tú") + "estarás " + s("segura,", "a salvo,")
				+ s("mientras te abras a nosotres,");
		speech += "permitiendo allowing " + s("nos, siempre,us, always,", "nosus") + "en esos into those " + s("lugaresplaces", "lugares,dentro,places, within,")
				+ "desde donde hablas from which you speak " + s("a nosotros,to us,");
		speech += "para que podamos escucharte. so that we may hear you. "; // ALWAYCHANGE + s("Listening, " + s("as") +
																						// "always.")
		speech += breath();
		return speech;
	}

}
