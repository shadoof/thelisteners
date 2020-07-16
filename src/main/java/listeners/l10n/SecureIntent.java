package listeners.l10n;

import static listeners.util.ConstantUtils.S;
import static listeners.util.ConstantUtils.breath;
import static listeners.util.ConstantUtils.breathShort;
import static listeners.util.ConstantUtils.s;

public class SecureIntent extends L10nSpeech {

	public Object[][] contents = { { "cardTitle", buildCardTitle() }, { "speech", buildSpeech() } };

	public String buildCardTitle() {

		return S(S("Your s", "Our s"), "S") + "ecurity is important " + S(S("to you", "to us"), "");
	}

	public String buildSpeech() {

		String speech = "We " + s("are convinced", "do believe") + "that we are "
				+ s("safe,", "secure, " + s("within,", "")) + "within this "
				+ s("enclosure.", "space of ours.");
		speech += "And that " + s("you, also,", "you") + "will be " + s("secure,", "safe,")
				+ s("as you open up to us,", "");
		speech += "allowing " + s("us, always,", "us") + "into those " + s("places", "places, within,")
				+ "from which you speak " + s("to us,", "");
		speech += "so that we may hear you. "; // ALWAYCHANGE + s("Listening, " + s("as", "") +
																						// "always.", "")
		speech += breath();
		return speech;
	}

	protected Object[][] getContents() {

		return contents;
	}

}
