package listeners.l10n;

import static listeners.util.Utils.*;

public class LoveIntent_fr_FR extends LoveIntent {

	public String buildCardTitle() {

		return S("Nous vous aimons", "Nous espérons que nos sentiments sont réciproques");
	}

	public String buildSpeech() {

		String speech = "Bien sûr. " + breathShort() + s("Bien sûr que oui. " + breathShort());
		speech += "Il est " + s("difficile", "pénible") + "pour nous " + s("d'imaginer", "de concevoir") + "quelconque ";
		speech += s("autre") + s("rapport avec", "sentiment pour") + "vous que nous " + s("pourrions", "puissions") + "avoir, " + s("autre que", "différent") + "de l'amour. ";
		speech += "Et plus nous " + s("vous écoutons", "portons attention à vous") + s("plus nous en" + s("apprenons", "découvrons") + "sur vous,") + "plus nous " + s("vous aimons.", "vous aimerons.") ;
		speech += s(breathShort() + "Ceci " + s("Est une évidence pour nous.", "nous semble évident."));
		return speech + breath();
	}
	
}
