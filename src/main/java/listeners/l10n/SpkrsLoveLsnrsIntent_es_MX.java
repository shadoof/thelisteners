package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class SpkrsLoveLsnrsIntent_es_MX extends SpkrsLoveLsnrsIntent {

	public String buildCardTitle() {

		return S("¿Nos quieres?", ("Nosotres también te apreciamos", "nosotres también te queremos"));
	}

	public String buildSpeech() {

		String speech = s("Claro. " + breathShort()) + s("Por supuesto que nos quieres. " + breathShort());
		if (heads()) {
			speech += "Sería " + s("difícil", "raro") + "para nosotres " + s("imaginar", "concebir") + "cualquier";
			speech += s("otro") + s("vínculo con", "sentimiento por ") + "nosotres que "
					+ s("pudieras", "pudieses") + "tener, " + s("aparte de uno de", "además del ") + "amor. "
					+ breath();
		}
		else {
			speech += "Tú escuchas nuestra " + s("especial")
					+ s("voz, con la que te hablamos.", "voz, con la cual nos dirigimos a ti.") + breath();
		}
		speech += "Creemos" + s(s("firmemente"),  + "esperamos" s("sinceramente"))
				+ "que, entre más tiempo pases con nosotres, "
				+ s("con nosotres escuchándote, " + breathShort()) + "más ";
		speech += s("encantada estarás " + s(breathShort()) + "con nosotres.", "amor tendrás "
				+ s(breathShort()) + "para nosotres. " + s(breathShort() + "Y nosotres, para ti."));
		return speech + breath();
	}
	
	public String getReprompt() {
		
		return speechUtils.getString("chooseContinueNoAffect");
	}

}
