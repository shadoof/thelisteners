package listeners.l10n;

import static listeners.util.Utils.S;
import static listeners.util.Utils.breath;
import static listeners.util.Utils.breathShort;
import static listeners.util.Utils.s;

public class CreepIntent extends L10nSpeech {

	public Object[][] contents = { { "cardTitle", buildCardTitle() }, { "speech", buildSpeech() } };

	public String buildCardTitle() {

		return S("You find us creepy?", "You think we're creepy?");
	}

	public String buildSpeech() {

		String speech = s("It's a little " + s("shocking", "disturbing"),
				"We are " + s("shocked", s("a little", "") + "upset")) + breath();
		speech += "to hear that. " + breathShort();
		speech += "We certainly don't " + s("intend", "mean") + "to "
				+ s("disturb you.", "'creep you out'.") + breathShort();
		speech += "We " + s("just", "only") + "want to listen to you. ";
		speech += s(breathShort() + "To hear you.", "");
		speech += breath();
		return speech;
	}

	protected Object[][] getContents() {

		return contents;
	}

}
