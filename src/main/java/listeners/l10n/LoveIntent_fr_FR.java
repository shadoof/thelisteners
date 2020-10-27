package listeners.l10n;

import static listeners.util.Utils.S;
import static listeners.util.Utils.breath;
import static listeners.util.Utils.breathShort;
import static listeners.util.Utils.s;

public class LoveIntent_fr_FR extends LoveIntent {

	public String buildCardTitle() {

		return S("We love you", "We hope you feel the same");
	}

	public String buildSpeech() {

		String speech = "Of course. " + breathShort() + s("Of course we do. " + breathShort());
		speech += "It is " + s("difficult", "hard") + "for us to " + s("imagine", "conceive of") + "any ";
		speech += s("other") + s("relation with", "feeling for") + "you that we " + s("might", "could") + "have, " + s("apart from that of", "other than") + "love. ";
		speech += "And the more we " + s("hear of", "listen to") + "you, " + s("the more we know about you,") + "the more we " + s("will") + "love you. ";
		speech += s(breathShort() + "This seems " + s("obvious, to us.", "obvious."));
		return speech + breath();
	}
	
}
