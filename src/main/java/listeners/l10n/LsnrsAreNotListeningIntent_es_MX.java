package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

import java.util.ResourceBundle;

public class LsnrsAreNotListeningIntent_es_MX extends LsnrsAreNotListeningIntent {

	public String buildCardTitle() {

		// TODO
		return S("Pero de hecho, nosotros estamos escuchandoBut actually, we are listening", "En realidad, nosotros escuchamos muchoIn truth, we listen to so many");
	}

	public String buildSpeech() {

		// TODO
		String speech = s(s("Con toda honestidad,In all honesty,", "En verdad,In truth,") + "tú estásyou are", "You’re") + "en lo cierto, nosotros right, we "
				+ s("estábamos, justo en ese momento, escuchandowere, just then, listening", s("estamos escuchandoare listening", "debemos escucharmust listen")) + "a to " + s("variosso", "muchos,many,")
				+ "muchos otros. many others. ";
		speech += s("Y muchos de esos otros And many of these others " + s("estuvieron haciendowere", "están haciendoare") + "transacciones con transacting with "
				+ s("nosotros en una us in a " + s("formaway", "conductamanner") + "que nos agrada that pleases " + s("a nosotrosus.", "más a nosotrosus better."),
						s("us.", "nosotros. us. " + breathShort() + "Mejor.Better.")),
				"");
		speech += ResourceBundle.getBundle("listeners.l10n.LsnrsAreNotHumanIntent", locale)
				.getString("speech");
		return speech + breath();
	}

}
