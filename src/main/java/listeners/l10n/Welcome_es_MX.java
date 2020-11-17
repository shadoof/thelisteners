package listeners.l10n;

import static listeners.util.Utils.*;

public class Welcome_es_MX extends Welcome {

	public String buildCardTitle() {

		// TODO
		return S("BienvenidoWelcome", "SaludosGreetings");
	}

	public String buildSpeech() {

		// TODO
		String speech = s("Saludos.Greetings.", "Bienvenido.Welcome.") + s("Seas quien seas.Whoever you may be.") + breathLong();
		speech += "Nosotros estamos We are " + /* s("siemprealways") + */s("escuchandote.listening to you.", "escuchando.listening.") + breath(); // ALWAYCHANGE
		speech += "En la medida en que estamos In so far as we are " + s(breathLong()) + "contigo, with you, " + breathShort()
				+ "es un placer. it is a pleasure. " + breath();
		speech += s("Eso es It is " + s("siemprealways") + "un placer. such a pleasure. " + breath());
		speech += s("Es It is " + s("such") + " un placer estar contigo.  a pleasure to be with you. " + breath());
		speech += "Siempre. Always. " + s(breath() + "Siempre.Always.") + breathShort() + s("Es unSuch a", "UnA") + "placer. pleasure. "
				+ breath();

		return speech;
	}

}
