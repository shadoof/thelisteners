package listeners.l10n;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.Attributes.sessAttributes;
import static listeners.model.Constants.speechUtils;
import static listeners.util.Utils.*;

public class DontLikeIntent_es_MX extends DontLikeIntent {

	public String buildCardTitle() {

		// TODO
		return S("¿No te gustamos?You don’t like us?", "¿No nos amas?Don’t you love us?");
	}

	public String buildAffect() {

		// TODO
		String affect = S(S("odiohate", S("enojoanger", "ansiedadanxiety")), S("aborreccimientohatred", "disgustodisgust"));
		sessAttributes.put(AFFECT, affect);
		return affect;
	}

	public String buildSpeech() {

		// TODO
		String affect = buildAffect();
		String adjective = s("perdónsorry", "dismayed");
		String intensifier = "dismayed".equals(adjective) ? "somewhat " : "very ";
		String speech = "We are " + s(adjective, intensifier + adjective);
		speech += "to " + s("sabemosknow", "aprendimoshave learned") + "que tú eresthat you are "
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
