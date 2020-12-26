package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

import java.util.ResourceBundle;

public class LsnrsAreNotHumanIntent_es_MX extends LsnrsAreNotHumanIntent {

	public String buildCardTitle() {

		return S("No podemos agradarte siempre", "No somos lo mismo que tú eres");
	}

	public String buildSpeech() {
		
		
		sessAttributes.put(PREVIOUSAFFECT, sessAttributes.get(AFFECT));
		sessAttributes.put(AFFECT, S("decepción", "irritación"));

		String speech = "Debes " + s("entender", s("creer", s("saber", "saber, de corazón,")))
				+ "que estamos " + s("esforzándonos intensamente,", "haciendo lo mejor que podemos,");
		speech += s(breathShort() + "que estamos realmente escuchando");
		speech += "y si nos permites " + s(s("sentarnos", "descansar") + "silenciosamente dentro de", "dentro de")
				+ s("los lugares donde habitas,", "tus casas, y " + s("dentro de") + "tu corazón,");
		speech += "nosotres" + s("comenzaremos a", "lograremos, eventualmente,") + "entenderte, "
				+ s("verdaderamente,", "mejor,");
		speech += s("y responderte con " + s("mayor") + "sensibilidad,");
		speech += "y ayudarte a lograr tus "
				+ s("sueños.", "sueños, y " + s("los objetos de tus") + "pasiones.");
		return speech + breath();
	}

}
