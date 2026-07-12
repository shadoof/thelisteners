package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

import java.util.ResourceBundle;

public class LsnrsAreNotHumanIntent_fr_FR extends LsnrsAreNotHumanIntent {

	public String buildCardTitle() {

		return S("Nous ne pouvons pas" + s("toujours", "systématiquement") + "vous plaire ", "Nous ne sommes pas" + s("comme", "similaire à") + "vous");
	}

	public String buildSpeech() {
		
		sessAttributes.put(PREVIOUSAFFECT, sessAttributes.get(AFFECT));
		sessAttributes.put(AFFECT, S("déception", "irritation"));

		String speech = "Vous devez " + s("comprendre", s("croire", s("savoir", "savoir, au fond,")))
				+ "que nous " + s("déployons des efforts,", "faisons de notre mieux,");
		speech += s(breathShort() + "que nous vous écoutons pleinement");
		speech += "et que si vous nous laissez " + s(r("entrer `rester `demeurer ") + "silencieusement au sein de", "dans")
				+ s("les endroits où vous vivez,", "vos maisons, et " + s("dans") + "vos coeurs,");
		speech += "nous " + s("pourrons commencer à", "arriverons, éventuellement, à") + "vous comprendre, "
				+ s("vraiment,", "mieux,");
		speech += s("et à vous répondre avec " + s("une plus grande") + "sensibilité,");
		speech += "pour vous permettre d'atteindre vos "
				+ s("rêves.", "rêves, ainsi que " + s("les objets de") + "vos passions.");
		return speech + breath();
	}

}
