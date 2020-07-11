package listeners.l10n;

import static listeners.model.Constants.attributes;
import static listeners.util.ConstantUtils.S;
import static listeners.util.ConstantUtils.breath;
import static listeners.util.ConstantUtils.breathShort;
import static listeners.util.ConstantUtils.s;

public class DontKnowAffectIntent_de_DE extends DontKnowAffectIntent {

	public String buildCardTitle() {

		// TODO break;
		return S("We don't know", "We're unsure about") + s("how you feel",
				"the feelings " + s("that " + s("possess", "overwhelm") + "you", "within which you dwell"));
	}

	public String buildSpeech() {

		String s = S("uncertainty", "insecurity");
		attributes.setAffect(s);
		String speech = s("We're " + s("a bit", "") + "distressed to",
				"It is " + s("a little", "") + s("upsetting", "disturbing") + "to know") + "that you don't "
				+ s("even", "") + "know how you feel. ";
		speech += s(breathShort() + "It's "
				+ s("important for us to", "a bit of a concern to us that we should come to") + "understand "
				+ s("all", "") + "your feelings.", "");
		speech += breathShort() + "We'll suppose that you are filled with '" + s + "'. " + breathShort();
		speech += "When you do " + s("figure out", "know") + "how you feel, we " + s("really", "truly")
				+ "hope that you'll " + s("decide to", "") + "tell us. " + s("For all our sakes.", "");
		speech += breath();
		return speech;
	}

}