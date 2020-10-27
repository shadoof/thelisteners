package listeners.l10n;

import static listeners.util.Utils.S;
import static listeners.util.Utils.breath;
import static listeners.util.Utils.breathLong;
import static listeners.util.Utils.breathShort;
import static listeners.util.Utils.s;

public class Welcome_fr_FR extends Welcome {

	public String buildCardTitle() {

		// TODO
		return S("Welcome", "Greetings");
	}

	public String buildSpeech() {

		// TODO
		String speech = s("Greetings.", "Welcome.") + s("Whoever you may be.") + breathLong();
		speech += "We are " + /* s("always") + */s("listening to you.", "listening.") + breath(); // ALWAYCHANGE
		speech += "In so far as we are " + s(breathLong()) + "with you, " + breathShort()
				+ "it is a pleasure. " + breath();
		speech += s("It is " + s("always") + "such a pleasure. " + breath());
		speech += s("It is " + s("such") + " a pleasure to be with you. " + breath());
		speech += "Always. " + s(breath() + "Always.") + breathShort() + s("Such a", "A") + "pleasure. "
				+ breath();

		return speech;
	}

}
