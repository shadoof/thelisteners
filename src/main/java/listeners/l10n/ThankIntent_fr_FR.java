package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class ThankIntent_fr_FR extends ThankIntent {

	public String buildCardTitle() {

		// TODO
		return "You’re " + s("very") + "welcome";
	}

	public String buildSpeech() {

		// TODO
		String speech = s(s("You’re", "You are") + s("very") + "welcome.",
				s("It’s nothing.", s("Please.") + "Think nothing of it.")) + breath();
		speech += s("We do work tirelessly to try and " + s("understand", "understand, and to believe in,")
				+ "you.");
		return speech + breath();

	}

}
