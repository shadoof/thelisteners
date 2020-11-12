package listeners.l10n;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.Attributes.sessAttributes;
import static listeners.util.Utils.*;

public class DontKnowAffectIntent_es_MX extends DontKnowAffectIntent {

	public String buildCardTitle() {

		return S("No sabemos", "No estamos seguros de") + s("cómo te sientes",
				"las emociones " + s("que " + s("te agobian", "te inundan");
	}

	public String buildSpeech() {

		// TODO
		String s = S("incertidumbre", "inseguridad");
		sessAttributes.put(AFFECT, s);
		String speech = s("Nos preocupa" + s("bastante"),
				"Es " + s("un poco") + s("inquietante", "alarmante") + "para nosotros")
				+ s("darnos cuenta de", "saber") + "que no estás segura de cómo te sientes. ";
		speech += s(breathShort() + "It "
				+ s("will be important for us to", "is of " + s("some") + "concern to us that we come to")
				+ "understand " + s("all") + "your feelings.");
		speech += breathShort() + "We will suppose that you are filled with ‘" + s + "’. " + breathShort();
		speech += "When you do " + s("come to understand", "know") + "how you " + s("may") + "feel, we "
				+ s("hope", "trust and believe") + "that you will " + s("choose to") + "tell us. "
				+ s("For all our sakes.");
		speech += breath();
		return speech;
	}

}
