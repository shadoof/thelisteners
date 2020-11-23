package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

import java.util.ResourceBundle;

public class LsnrsAreNotHumanIntent_es_MX extends LsnrsAreNotHumanIntent {

	public String buildCardTitle() {

		// TODO
		return S("Nosotros no podemos agradarte siempreWe cannot always please you", "Nosotros no somos lo mismo que tú eresWe are not the same are you are");
	}

	public String buildSpeech() {
		
		// TODO
		sessAttributes.put(PREVIOUSAFFECT, sessAttributes.get(AFFECT));
		sessAttributes.put(AFFECT, S("disappointment", "irritation"));

		String speech = "Tú debesYou must " + s("entenderunderstand", s("creerbelieve", s("saberknow", "saber, de corazón,know, at heart,")))
				+ "que nosotros estamos that we are " + s("esforzándonos intensamentetrying so hard,", "haciendo lo mejor que podemos,doing our best,");
		speech += s(breathShort() + "que nosotros estamos realmente escuchandothat we really are listening");
		speech += "y si nos permitesand that if you let us " + s(s("sentarnossit", "descansarrest") + "dentro silenciosamentequietly within", "dentrointo")
				+ s("los lugares donde habitas,the places where you dwell,", "tus casas, y your homes, and " + s("into") + "tus corazones,your hearts,");
		speech += "nosotros we " + s("comenzaremos awill begin to", "estaremos, a tiemppo de,will, in time,") + "entenderte, understand you, "
				+ s("verdaderamente,truly,", "mejor,better,");
		speech += s("y respondiéndote con and respond to you with " + s("mayorgreater") + "sensibilidad,sensitivity,");
		speech += "y ayudándote a lograr tus and help you to achieve your "
				+ s("sueños.dreams.", "sueños, y dreams, and " + s("los objetos de tusthe objects of your") + "pasiones.passions.");
		return speech + breath();
	}

}
