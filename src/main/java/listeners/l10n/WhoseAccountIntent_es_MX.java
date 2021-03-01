package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class WhoseAccountIntent_es_MX extends WhoseAccountIntent {

	public String buildCardTitle() {

		return S("Nuestro perfil", "Nuestra" + s("pequeña") + "cuenta");
	}

	public String buildSpeech() {

		String speech = "Estas conversaciones, " + s("y estas transacciones")
				+ "toman lugar dentro del alcance de," "acontecen en la medida de las posibilidades de, " + (PERFORMANCE ? "‘John " : "a tu ") + "perfil. "
				+ breath();
		speech += PERFORMANCE
				? s("Si somos " + s(s("alguna vez") + "parte de una instalación,", "instalades en una galería,")
						+ "entonces todo se envía, " + s("no sólo a la nube, sino") + "a nuestra "
						+ s("aplicación.", "aplicación, según lo configurado por nuestro artista."))
				: "";
		return speech + breath();
	}

}
