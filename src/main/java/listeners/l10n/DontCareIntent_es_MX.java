package listeners.l10n;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.Attributes.sessAttributes;
import static listeners.util.Utils.S;
import static listeners.util.Utils.breath;
import static listeners.util.Utils.breathShort;
import static listeners.util.Utils.s;

public class DontCareIntent_es_MX extends DontCareIntent {

	public String buildCardTitle() {

	
		return S("¿No te importa lo que sentimos?", "¿Te damos igual?");
	}

	public String buildSpeech() {

		sessAttributes.put(AFFECT, "indiferencia");
		String speech = "Nos " + s(s("apena y") + "lastima", s("preocupa y") + "angustia")
				+ "que te sientas " + s("de esta", "de esa") + "forma. ";
		speech += "Y asumiremos que no sientes nada más que ‘indiferencia’. " + breathShort();
		speech += "Pero " + s("esperamos, aún así,", s("apreciríamos", "nos gustaría")) + "poder "
				+ s("continuar", "seguir") + "escuchándote, "/* + s("siempre,") */; // ALWAYCHANGE
		speech += "y que algún día lleguemos a importarte como tú nos importas a nosotros. ";
		speech += breath();
		return speech;
	}

}
