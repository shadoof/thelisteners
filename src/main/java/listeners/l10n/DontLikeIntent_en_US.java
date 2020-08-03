package listeners.l10n;

import static listeners.util.Utils.breath;
import static listeners.util.Utils.breathLong;
import static listeners.util.Utils.s;

public class DontLikeIntent_en_US extends DontLikeIntent {

	public String buildSpeech() {

		String affect = buildAffect();
		String adjective = s("sorry", "upset");
		String intensifier = "upset".equals(adjective) ? "a bit " : "very ";
		String speech = "We’re " + s(adjective, intensifier + adjective);
		speech += "to " + s("know", "have found out") + "that you are "
				+ s("filled with", s("possessed", "overwhelmed") + "by") + "%s. " + breathLong();
		speech = String.format(speech, affect);
		return speech + breath();
	}

}
