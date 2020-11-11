package listeners.l10n;

import static listeners.util.Utils.*;

public class SecureIntent_fr_FR extends SecureIntent {

	public String buildCardTitle() {

		return S(S("Votre", "Notre", "La")) + "sécurité est importante " + s(S("pour vous", "pour nous"));
	}

	public String buildSpeech() {

		String speech = "Nous " + s("sommes convaincues", "croyons fermement") + "que nous sommes "
				+ s("à l'abri,", "en sécurité, " + s("au sein de,")) + "à l'intieur de "
				+ s("cette enceinte.", "cet espace.");
		speech += "Et que " + s("vous, aussi,", "vous") + s("serez ", "vous sentirez") + s("en sécurité,", "à l'abri,", "hors de portée")
				+ s("à mesure que vous vous confierez à nous,");
		speech += "nous " + s("permettant d'aller,", "donnant accès, toujours") + "au sein de ces " + s("espaces", "espaces intérieurs")
				+ "depuis lesquels vous vous exprimez " + s("à nous,");
		speech += "afin que l'on puisse vous entendre. "; // ALWAYCHANGE + s("Listening, " + s("as") +
																						// "always.")
		speech += breath();
		return speech;
	}

}
