package listeners.l10n;

import static listeners.util.ConstantUtils.S;
import static listeners.util.ConstantUtils.breath;
import static listeners.util.ConstantUtils.breathShort;
import static listeners.util.ConstantUtils.s;

public class SecureIntent_en_US extends SecureIntent {

	public String buildSpeech() {

		String speech = s("We're sure", "We really believe") + "that we are "
				+ s("safe,", "secure, " + s("within,", "")) + "within this "
				+ s("container.", "space of ours.");
		speech += "And that you " + s("too", "") + "will be " + s("secure,", "safe,")
				+ s("as you open up to us,", "");
		speech += "allowing " + s("us, always,", "us") + "into these " + s("places", "places of safety")
				+ "where you speak " + s("with us,", "");
		speech += "so that we can hear you. "; // ALWAYCHANGE + s("Listening, " + s("as", "") +
																						// "always.", "")
		speech += breath();
		return speech;
	}

}
