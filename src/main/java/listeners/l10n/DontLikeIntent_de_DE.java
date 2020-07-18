package listeners.l10n;

import static listeners.model.Constants.attributes;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.S;
import static listeners.util.Utils.breath;
import static listeners.util.Utils.breathLong;
import static listeners.util.Utils.breathShort;
import static listeners.util.Utils.s;

public class DontLikeIntent_de_DE extends DontLikeIntent {

	// TODO
	public String buildCardTitle() {

		return S("You don't like us?", "Don't you love us?");
	}

	public String buildSpeech() {

		String affect = S(S("hate", S("anger", "anxiety")), S("hatred", "disgust"));
		sessAttributes.put(AFFECT, affect);
		String adjective = s("sorry", "dismayed");
		String intensifier = "dismayed".equals(adjective) ? "somewhat " : "very ";
		String speech = "We are " + s(adjective, intensifier + adjective);
		speech += "to " + s("know", "have learned") + "that you are "
				+ s("filled with", s("possessed", "overwhelmed") + "by") + "%s. " + breathLong();
		return speech;
	}

}
