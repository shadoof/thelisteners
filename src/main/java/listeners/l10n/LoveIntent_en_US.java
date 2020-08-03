package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class LoveIntent_en_US extends LoveIntent {

	public String buildSpeech() {

		String speech = "Of course. " + breathShort() + s("Of course we do. " + breathShort(), "");
		speech += "It’s hard " + s("for us", "") + "to " + s("imagine", "think of") + "any ";
		speech += s("other", "") + "feeling for you that we " + s("can", "could") + "have, "
				+ s("except for", "other than") + "love. ";
		speech += s("We feel like", "And") + "the more we " + s("hear about", "listen to") + "you, "
				+ s("the more we know about you,", "") + "the more " + s("we’ll", "we") + "love you. ";
		speech += s(breathShort() + "This feels " + s("obvious, to us.", "like it’s obvious."), "");
		return speech + breath();
	}

}
