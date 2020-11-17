package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class WhoseAccountIntent_es_MX extends WhoseAccountIntent {

	public String buildCardTitle() {

		return S("Nuestro perfil", "Esta" + s("pequeña") + "cuenta nuestra");
	}

	public String buildSpeech() {

		// TODO
		String speech = "Estas conversaciones, " + s("y estas transacciones")
				+ "toman lugar dentro del alcance de, take place within the scope of, " + (PERFORMANCE ? "‘John’s’ " : "tu your ") + "perfil. profile. "
				+ breath();
		speech += PERFORMANCE
				? s("Si nosotros somos If we are " + s(s("siempreever") + "parte de una instalación,part of an installation,", "una galería instalada,gallery installed,")
						+ "entonces todo está enviado, then everything is sent, " + s("no solo a la nube, peronot only to the cloud, but") + "a nuestrato our "
						+ s("aplicación.app.", "aplicación, app, según lo configurado por nuestro artista.as configured by our artist."))
				: "";
		return speech + breath();
	}

}
