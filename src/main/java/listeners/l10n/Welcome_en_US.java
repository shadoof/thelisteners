package listeners.l10n;

import static listeners.util.ConstantUtils.breath;
import static listeners.util.ConstantUtils.breathLong;
import static listeners.util.ConstantUtils.breathShort;
import static listeners.util.ConstantUtils.s;

public class Welcome_en_US extends Welcome {

	public String buildSpeech() {

		String speech = s("Hello there!", "Welcome.") + s("Whoever you are.", "") + breathLong();
		speech += "We are " + /* s("always", "") + */s("listening to you.", "listening.") + breath(); // ALWAYCHANGE
		speech += "In as much as we are " + s(breathLong(), "") + "with you, " + breathShort()
				+ "it is a pleasure. " + breath();
		speech += s("It is " + s("always", "") + "such a pleasure. " + breath(), "");
		speech += s("It is " + s("such", "") + " a pleasure to be with you. " + breath(), "");
		speech += "Always. " + s(breath() + "Always.", "") + breathShort() + s("Such a", "A") + "pleasure. "
				+ breath();
		return speech;
	}
}
