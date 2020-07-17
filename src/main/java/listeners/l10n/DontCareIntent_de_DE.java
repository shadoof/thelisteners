package listeners.l10n;

import static listeners.model.Constants.attributes;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.S;
import static listeners.util.Utils.breath;
import static listeners.util.Utils.breathShort;
import static listeners.util.Utils.s;

public class DontCareIntent_de_DE extends DontCareIntent {

	public String buildCardTitle() {

		return S("Ist es dir egal, wie es ins geht?", "Sind wir dir gleichgültig?");
	}

	public String buildSpeech() {

		sessAttributes.put(AFFECT, "Gleichgültigkeit");
		String speech = "Wir sind " + s(s("sehr", "") + "betrübt,", s("etwas", "") + "bekümmert,")
				+ "dass du so " + s("reagierst.", "denkst.");
		speech += "Und wir nehmen an, das Dir das alles gleichgültig ist. " + breathShort();
		speech += "Wir " + s("hoffen", "vertrauen") + s("jedoch,", "jedoch darauf,");
		speech += "dass wir Ihnen " + s("weiter", "weiterhin") + "zuhören dürfen, " + s("immer,", "");
		speech += "und dass Sie uns eines Tages mögen werden, wie wir auch Sie mögen. ";
		speech += breath();
		return speech;
	}

}
