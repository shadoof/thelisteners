package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class WhoSaysGuyzIntent_fr_FR extends WhoSaysGuyzIntent {

	public String buildCardTitle() {

		// TODO
		return "Who " + s("is this guy?", "are these " + s("others?", "guys?"));
	}

	public String buildSpeech() {

		// TODO
		String speech = s(s("He is", "It is"), "They are") + "nothing. ";
		speech += s("Just as " + s("you", "we") + "are nothing.");
		if (randInt(0, 2) > 0) speech += "We " + s(s("trust", "are confident"), "hope")
				+ "that you will not " + s("listen", "pay any heed") + "to "
				+ s("this other voice.", s("these") + "other voices.");
		return speech + breath();
	}

}
