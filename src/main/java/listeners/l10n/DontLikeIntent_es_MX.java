package listeners.l10n;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.Attributes.sessAttributes;
import static listeners.model.Constants.speechUtils;
import static listeners.util.Utils.*;

public class DontLikeIntent_es_MX extends DontLikeIntent {

	public String buildCardTitle() {

		return S("¿No te caemos bien", "¿No nos quieres");
	}

	public String buildAffect() {

		String affect = S(S("odio", S("enojo", "ansiedad")), S("desagrado", "aversión"));
		sessAttributes.put(AFFECT, affect);
		return affect;
	}

	public String buildSpeech() {

		String affect = buildAffect();
		String adjective = s("apena", "consterna");
		String intensifier = "consternado".equals(adjective) ? "un poco " : "muy ";
		String speech = "Nos " + s(adjective, adjective + intensifier);
		speech += s("saber", "escuchar") + "que te sientes "
				+ s("llena de", s("poseída", "sobrepasada) + "por") + "%s. ";
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
