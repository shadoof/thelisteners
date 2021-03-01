package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class SpkrsLoveLsnrsIntent_es_MX extends SpkrsLoveLsnrsIntent {

	public String buildCardTitle() {

		return S("¿Tú nos amas?", "Nosotres también te amamos");
	}

	public String buildSpeech() {

		String speech = s("Claro. " + breathShort()) + s("Por supuesto que nos amas. " + breathShort());
		if (heads()) {
			speech += "Es " + s("difícil", "extraño") + "para nosotres " + s("imaginar", "concebir") + "cualquier";
			speech += s("otro") + s("vínculo con", "sentimiento por ") + "nosotres que "
					+ s("podrías", "pudieras") + "tener, " + s("aparte de uno de", "además del ") + "amor. "
					+ breath();
		}
		else {
			speech += "Tú escuchas nuestra " + s("especial")
					+ s("voz, con la que te hablamos.", "voz, con la cual nos dirigimos a ti.") + breath();
		}
		speech += "Creemos" + s(s("firmemente"),  + "esperamos" s("sinceramente"))
				+ "que, cuanto más tiempo pases con nosotres, "
				+ s("con nosotres escuchándote, " + breathShort()) + "más ";
		speech += s("encantada estarás " + s(breathShort()) + "con nosotres.", "amor tendrás "
				+ s(breathShort()) + "para nosotres. " + s(breathShort() + "Y nosotres, para ti."));
		return speech + breath();
	}
	
	public String getReprompt() {
		
		return speechUtils.getString("chooseContinueNoAffect");
	}

}
