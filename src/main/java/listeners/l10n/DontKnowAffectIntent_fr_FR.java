package listeners.l10n;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.Attributes.sessAttributes;
import static listeners.util.Utils.*;

public class DontKnowAffectIntent_fr_FR extends DontKnowAffectIntent {

	public String buildCardTitle() {

		return r("Nous ne savons pas `Nous ne connaissons pas `Nous ne sommes pas certaines de ")
				+ s("les sentiments", "les émotions")
				+ s("qui vous " + r("habitent`traversent`animate"), "dont vous êtes tributaire");
	}

	public String buildSpeech() {

		String s = S("incertitude", "insécurité");
		sessAttributes.put(AFFECT, s);
		String speech = s("nous sommes " + s("quelquepeu") + "atterrées",
				"Il est " + s("un peu") + s("troublant", "perturbant") + "pour nous")
				+ r("d'apprendre `de savoir `de découvrir ")
				+ "que vous n'êtes pas certain de savoir comment vous vous" + s("sentez.", "portez.");
		speech += s(breathShort() + "Il "
				+ s("sera d'une grande importante que",
						"faut que " + s("du moins nous semble-t-il") + "est crucial à nos yeux que")
				+ "nous comprenions " + s("l'ensemble de") + s("vos émotions.", "vos sentiments."));
		speech += breathShort() + "Nous supposerons que vous êtes remplis de ‘" + s + "’. " + breathShort();
		speech += "Lorsque vous " + s("finirez par comprendre", "saurez") + "comment vous vous sentez, "
				+ s("potentiellement,") + "nous "
				+ s("espérons que",
						"comptons sur vous pour que" + "avons foi que" + "vous faisons confiance pour que")
				+ s("vous nous le direz", "vous choisirez de nous en faire part") + s("Pour le bien de tous.");
		speech += breath();
		return speech;
	}

}
