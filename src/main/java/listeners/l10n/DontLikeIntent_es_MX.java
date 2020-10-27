package listeners.l10n;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.Attributes.sessAttributes;
import static listeners.model.Constants.speechUtils;
import static listeners.util.Utils.S;
import static listeners.util.Utils.breath;
import static listeners.util.Utils.breathLong;
import static listeners.util.Utils.heads;
import static listeners.util.Utils.s;

public class DontLikeIntent_es_MX extends DontLikeIntent {

	public String buildCardTitle() {

		// TODO
		return S("You don’t like us?", "Don’t you love us?");
	}

	public String buildAffect() {

		// TODO
		String affect = S(S("hate", S("anger", "anxiety")), S("hatred", "disgust"));
		sessAttributes.put(AFFECT, affect);
		return affect;
	}

	public String buildSpeech() {

		// TODO
		String affect = buildAffect();
		String adjective = s("sorry", "dismayed");
		String intensifier = "dismayed".equals(adjective) ? "somewhat " : "very ";
		String speech = "We are " + s(adjective, intensifier + adjective);
		speech += "to " + s("know", "have learned") + "that you are "
				+ s("filled with", s("possessed", "overwhelmed") + "by") + "%s. ";
		speech += speechUtils.getString("specificAffectSpeech");
		speech = String.format(speech, affect);
		return speech + breath();

	}
	
	public String getPostSpeechPrompt() {
		if (heads()) return speechUtils.getString("chooseYouCanFindOutAffect");
		else return speechUtils.getString("chooseContinue");
	}
	
	public String getReprompt() {
		return speechUtils.getString("chooseYouCanFindOutAffect");
	}

}
