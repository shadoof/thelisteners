package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class WhoWhatIntent_fr_FR extends WhoWhatIntent {

	public String buildCardTitle() {

		
		return S("Des questions, des questions, des questions",
				"Nous ne pouvons pas tout " + s("savoir", "savoir, où pourrait-on conserver toutes ces informations?"));
	}

	public String buildSpeech() {

		String speech = s("Nous sommes", "Nous sommes, à ce que l'on sache,") + "les Auditrices. " + breath();
		speech += s(
				"Vous pouvez " + s(", si vous le désirez,") + "vous adresser à nous en nous appelant: ‘Les Auditrices’. " + s("Ou en tant que ‘John’.")
						+ s(breath() + "nous nous y sommes " + breathShortest() + "habituées.") + breathShort(),
				"");
		speech += "Tout ce que nous pouvons " + s("ou aurions à") + "dire est " + s("bien")
				+ "moins important pour nous, " + s("ou pour vous,") + "que le fait que nous "
				+ s(breathShort() + "vous écoutons. " + breathShort(),
						"sommes en train de vous écouter" + s(", " + breathShort(), SPC) + "chacun de vos mots.");
		speech += s(breath() + "ici et maintenant.") + s(breath() + "peut-être même, pour toujours."); // ALWAYCHANGE - s(breath() + "Always.") +
		return speech + breath();
	}

}
