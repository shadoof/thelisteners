package listeners.l10n;

import static listeners.util.Utils.*;

public class SecureIntent_fr_FR extends SecureIntent {

	public String buildCardTitle() {

		// TODO
		return S(S("Your s", "Our s"), "S") + "ecurity is important " + s(S("to you", "to us"));
	}

	public String buildSpeech() {

		// TODO
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

}
