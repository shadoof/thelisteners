package listeners.l10n;

import static listeners.util.Utils.S;
import static listeners.util.Utils.breath;
import static listeners.util.Utils.breathShort;
import static listeners.util.Utils.s;

public class SecureIntent extends L10nSpeech {

	public Object[][] contents = { { "cardTitle", buildCardTitle() }, { "speech", buildSpeech() } };

	public String buildCardTitle() {

		return S(S("Your s", "Our s"), "S") + "ecurity is important " + s(S("to you", "to us"));
	}

	public String buildSpeech() {

		String speech = "We " + s("are convinced", "do believe") + "that we are "
				+ s("safe,", "secure, " + s("within,")) + "within this "
				+ s("enclosure.", "space of ours.");
		speech += "And that " + s("you, also,", "you") + "will be " + s("secure,", "safe,")
				+ s("as you open up to us,");
		speech += "allowing " + s("us, always,", "us") + "into those " + s("places", "places, within,")
				+ "from which you speak " + s("to us,");
		speech += "so that we may hear you. "; // ALWAYCHANGE + s("Listening, " + s("as") +
																						// "always.")
		speech += breath();
		return speech;
	}

	protected Object[][] getContents() {

		return contents;
	}

}
