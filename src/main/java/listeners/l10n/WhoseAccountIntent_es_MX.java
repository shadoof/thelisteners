package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class WhoseAccountIntent_es_MX extends WhoseAccountIntent {

	public String buildCardTitle() {

		return S("Nuestro perfil", "Esta" + s("pequeña") + "cuenta nuestra");
	}

	public String buildSpeech() {

		String speech = "Estas conversaciones, " + s("y estas transacciones")
				+ "toman lugar dentro del alcance de, take place within the scope of, " + (PERFORMANCE ? "‘John’s’ " : "tu your ") + "perfil. profile. "
				+ breath();
		speech += PERFORMANCE
				? s("Si somos " + s(s("alguna vez") + "parte de una instalación,", "instalades en una galería,")
						+ "entonces todo se envía, " + s("no sólo a la nube, sino") + "a nuestra "
						+ s("aplicación.", "aplicación, según lo configurado por nuestro artista."))
				: "";
		return speech + breath();
	}

}
