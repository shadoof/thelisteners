package listeners.l10n;

import static listeners.util.Utils.*;

public class Welcome_es_MX extends Welcome {

	public String buildCardTitle() {

		return S("Bienvenida", "Saludos");
	}

	public String buildSpeech() {

		String speech = s("Saludos.", "Bienvenida.") + s("Seas quien seas.") + breathLong();
		speech += "Nosotres estamos " + /* s("siemprealways") + */s("escuchándote.", "escuchando.") + breath(); // ALWAYCHANGE
		speech += "En la medida en que estamos " + s(breathLong()) + "contigo, " + breathShort()
				+ "es un placer. " + breath();
		speech += s("Es " + s("siempre") + "un verdadero placer. " + breath());
		speech += s("Es " + s("un verdadero") + " placer estar contigo. " + breath());
		speech += "Siempre. " + s(breath() + "Siempre.") + breathShort() + s("Un verdadero", "Un") + "placer. "
				+ breath();

		return speech;
	}

}
