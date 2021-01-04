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

		String speech = s(s("La verdad,", "A decir verdad,") + "tú estás", "estás") + "en lo cierto, "
				+ s("estábamos, justo ahorita, escuchando", s("estamos escuchando", "debemos escuchar")) + "a " + s("muchísimas, ", "muchas, ")
				+ "muchas personas. ";
		speech += s("Y muchas de ellas " + s("estuvieron haciendo", "están haciendo") + "transacciones "
				+ s("con nosotres de " + s("formas", "maneras") + "que nos agradan " + s("mucho.", "más."),
						s("que nos agradan más.", "que nos complacen mucho más." + breathShort())),
				"");
		speech += ResourceBundle.getBundle("listeners.l10n.LsnrsAreNotHumanIntent", locale)
				.getString("speech");
		return speech + breath();
	}

}
