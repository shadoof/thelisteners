package listeners.l10n;

import static listeners.util.Utils.S;
import static listeners.util.Utils.breath;
import static listeners.util.Utils.breathShort;
import static listeners.util.Utils.s;

public class CreepIntent_en_US extends CreepIntent {

	public String buildCardTitle() {

		return S("Creepy? Us?!", "You think we’re creepy?");
	}

	public String buildSpeech() {

		String speech = s("It’s " + s("upsetting", "disturbing"),
				"We’re " + s("shocked", s("a bit", "") + "upset")) + breath();
		speech += "to hear that. " + breathShort();
		speech += "We don’t " + s("want", "mean") + "to creep you out. " + breathShort();
		speech += "We " + s("just", "only") + "want to listen to you. ";
		speech += s(breathShort() + "To hear you.", "");
		speech += breath();
		return speech;
	}

}
