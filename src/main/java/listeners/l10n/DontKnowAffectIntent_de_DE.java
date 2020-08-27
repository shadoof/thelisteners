package listeners.l10n;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.Attributes.sessAttributes;
import static listeners.util.Utils.S;
import static listeners.util.Utils.breath;
import static listeners.util.Utils.breathShort;
import static listeners.util.Utils.s;

public class DontKnowAffectIntent_de_DE extends DontKnowAffectIntent {

	public String buildCardTitle() {

		return "Wir sind " + s("nicht sicher", "unsicher")
		+ s("was Ihre Gefühle sind,", "welche Gefühle es sind, ") + "die Sie da "
		+ S("haben", "überwältigen");
	}

	// TODO
	public String buildSpeech() {

		String s = S("uncertainty", "insecurity");
		sessAttributes.put(AFFECT, s);
		String speech = s("We’re " + s("a bit") + "distressed to",
				"It is " + s("a little") + s("upsetting", "disturbing") + "to know") + "that you don’t "
				+ s("even") + "know how you feel. ";
		speech += s(breathShort() + "It’s "
				+ s("important for us to", "a bit of a concern to us that we should come to") + "understand "
				+ s("all") + "your feelings.");
		speech += breathShort() + "We’ll suppose that you are filled with ‘" + s + "’. " + breathShort();
		speech += "When you do " + s("figure out", "know") + "how you feel, we " + s("really", "truly")
				+ "hope that you’ll " + s("decide to") + "tell us. " + s("For all our sakes.");
		speech += breath();
		return speech;
	}

}
