package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

import java.util.ResourceBundle;

public class LsnrsAreNotListeningIntent_es_MX extends LsnrsAreNotListeningIntent {

	public String buildCardTitle() {

		return S("Pero de hecho, estamos escuchando", "En realidad, escuchamos a muchas personas.");
	}

	public String buildSpeech() {

		// TODO
		String speech = s(s("La verdad,", "A decir verdad,") + "tú estás", "estás") + "en lo cierto, "
				+ s("estábamos, justo ahorita, escuchando", s("estamos escuchando", "debemos escuchar")) + "a to " + s("variosso", "muchos,many,")
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
