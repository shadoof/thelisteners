package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class DontLikeIntent extends L10nSpeech {

	String affect = "";

	public Object[][] contents = { { "cardTitle", buildCardTitle() }, { "speech", buildSpeech() } };

	public String buildCardTitle() {

		return S("You don't like us?", "Don't you love us?");
	}

	public void buildAffect() {

		affect = S(S("hate", S("anger", "anxiety")), S("hatred", "disgust"));
		sessAttributes.put(AFFECT, affect);
	}

	public String buildSpeech() {

		buildAffect();
		String adjective = s("sorry", "dismayed");
		String intensifier = "dismayed".equals(adjective) ? "somewhat " : "very ";
		String speech = "We are " + s(adjective, intensifier + adjective);
		speech += "to " + s("know", "have learned") + "that you are "
				+ s("filled with", s("possessed", "overwhelmed") + "by") + "%s. " + breathLong();
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

	protected Object[][] getContents() {

		return contents;
	}

}
