package listeners.l10n;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.Attributes.sessAttributes;
import static listeners.model.Constants.speechUtils;
import static listeners.util.Utils.*;

public class DontLikeIntent_fr_FR extends DontLikeIntent {

	public String buildCardTitle() {

		return S("Vous ne nous appréciez pas?", "Est-ce que vous nous aimez?");
	}

	public String buildAffect() {

		String affect = S(S("haine", S("colère", "anxiété")), S("détestation", "dégoût"));
		sessAttributes.put(AFFECT, affect);
		return affect;
	}

	public String buildSpeech() {

		// TODO
		String affect = buildAffect();
		String adjective = s("désolé", "consterné");
		String intensifier = "consterné".equals(adjective) ? "quelquepeu " : "très ";
		String speech = "Nous sommes " + s(adjective, intensifier + adjective);
		speech += r("de découvrir `de savoir `d'apprendre ") + "que vous êtes "
				+ s("rempli de", s("possésé `consumé `submergé") + "par") + "%s. ";
		speech += speechUtils.getString("specificAffectSpeech");
		speech = String.format(speech, affect);
		return speech + breath();

	}

	public String getPostSpeechPrompt() {

		if (heads())
			return speechUtils.getString("chooseYouCanFindOutAffect");
		else
			return speechUtils.getString("chooseContinue");
	}

	public String getReprompt() {

		return speechUtils.getString("chooseYouCanFindOutAffect");
	}

}
