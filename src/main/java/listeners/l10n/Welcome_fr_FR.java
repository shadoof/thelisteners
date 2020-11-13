package listeners.l10n;

import static listeners.util.Utils.*;

public class Welcome_fr_FR extends Welcome {

	public String buildCardTitle() {

		// TODO
		return S("Bienvenue", "Salutations");
	}

	public String buildSpeech() {

		// TODO
		String speech = s("Salutations.", "Bienvenue.") + s("Qui que vous soyez.") + breathLong();
		speech += "Nous sommes " + /* s("toujours") + */s("à votre écoute.", "à l'écoute.") + breath(); // ALWAYCHANGE
		speech += "En ceci que nous sommes " + s(breathLong()) + 2("auprès de vous, ", "avec vous,") + breathShort()
				+ "à notre plus grand plaisir. " + breath();
		speech += s("Il s'agit " + s("toujours") + "d'un véritable plaisir. " + breath());
		speech += s("c'est un " + s("immense") + " plaisir d'être en votre compagnie. " + breath());
		speech += "Toujours. " + s(breath() + "Toujours.") + breathShort() + s("Un si grand", "Un") + "plaisir. "
				+ breath();

		return speech;
	}

}
