package listeners.l10n;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.Attributes.sessAttributes;
import static listeners.util.Utils.*;

public class DontKnowAffectIntent_es_MX extends DontKnowAffectIntent {

	public String buildCardTitle() {

		return S("No conocemos ", "No estamos segures de ") + s("cómo te sientes",
				"las emociones que " + S("te agobian", "te inundan"));
	}

	public String buildSpeech() {

		String s = S("incertidumbre", "inseguridad");
		sessAttributes.put(AFFECT, s);
		String speech = s("Nos preocupa " + s("bastante"),
				"Es " + s("un poco") + s("inquietante", "alarmante") + "para nosotres")
				+ s("darnos cuenta de", "saber") + "que no estás segura de cómo te sientes. ";
		speech += s("Sería", "Es") + s("muy") + ("importante para nosotres llegar a un")
				+ "entendimiento de " + s("todas") + ("tus emociones.");
		speech += breathShort() + "Supondremos que estás llena de ‘" + s + "’. " + breathShort();
		speech += "Cuando " + s("llegues a entender", "sepas") + s("por fin") + "cómo "  + "te sientes, "
				+ s("esperamos", "confiamos y creemos en") + "que " + s("decidirás") + "decirnos. " + s(breathShort()
				+ "Por el bien de todes.");
		speech += breath();
		return speech;
	}

}
