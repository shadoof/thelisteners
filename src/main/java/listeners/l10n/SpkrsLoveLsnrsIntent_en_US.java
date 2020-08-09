package listeners.l10n;

import static listeners.util.Utils.breath;
import static listeners.util.Utils.breathShort;
import static listeners.util.Utils.heads;
import static listeners.util.Utils.s;

public class SpkrsLoveLsnrsIntent_en_US extends SpkrsLoveLsnrsIntent {

	public String buildSpeech() {

		String speech = s("Naturally. " + breathShort(), "") + s("Of course you do. " + breathShort(), "");
		if (heads()) {
			speech += "It’s hard " + s("for us", "") + "to " + s("imagine", "think of") + "any ";
			speech += s("other", "") + s("relation with", "feeling for") + "us that you could have, "
					+ s("except", "other than") + "love. " + breath();
		}
		else {
			speech += "You hear this " + s("nice", "nice, loving") + s("voice.", "voice of ours.") + breath();
		}
		speech += "And we " + s(s("really", "") + "believe", "believe, totally")
				+ "that, the more time you spend with us, "
				+ s("with us listening to you, " + breathShort(), "");
		speech += "the " + s("cooler", "more " + s("caring", "lovely")) + "you will think we are. ";
		return speech + breath();
	}

}
