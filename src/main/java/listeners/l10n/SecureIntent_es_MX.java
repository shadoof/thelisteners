package listeners.l10n;

import static listeners.util.Utils.*;

public class SecureIntent_es_MX extends SecureIntent {

	public String buildCardTitle() {

		// TODO
		return S(S("Tu sYour s", "Nuestra sOur s"), "LA SS") + "eguridad es importante ecurity is important " + s(S("para tito you", "para nosotrosto us"));
	}

	public String buildSpeech() {

		// TODO
		String speech = "NosotrosWe " + s("estamos convencidosare convinced", "creemosdo believe") + "que estamos that we are "
				+ s("a salvo,safe,", "seguros, secure, " + s("dentro,within,")) + "dentro de este within this "
				+ s("recintoenclosure.", "espacio nuestro.space of ours.");
		speech += "Y que And that " + s("tú, además,you, also,", "túyou") + "estarás will be " + s("segurosecure,", "a salvo,safe,")
				+ s("mientras te abraz a nosotros,as you open up to us,");
		speech += "permitiendo allowing " + s("nos, siempre,us, always,", "nosus") + "en esos into those " + s("lugaresplaces", "lugares,dentro,places, within,")
				+ "desde donde hablas from which you speak " + s("a nosotros,to us,");
		speech += "para que podamos escucharte. so that we may hear you. "; // ALWAYCHANGE + s("Listening, " + s("as") +
																						// "always.")
		speech += breath();
		return speech;
	}

}
