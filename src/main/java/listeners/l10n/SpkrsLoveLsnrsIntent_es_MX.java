package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class SpkrsLoveLsnrsIntent_es_MX extends SpkrsLoveLsnrsIntent {

	public String buildCardTitle() {

		// TODO
		return S("¿Tú nos amas?You love us?", "Nosotros te amamos tambiénWe love you too");
	}

	public String buildSpeech() {

		// TODO
		String speech = s("Claro. Of course. " + breathShort()) + s("Por supuesto que sí. Of course you do. " + breathShort());
		if (heads()) {
			speech += "Es It is " + s("difícildifficult", "durohard") + "para nosotros for us to " + s("imaginarimagine", "concebirconceive of") + "alguna any ";
			speech += s("otraother") + s("relación conrelation with", "sensación confeeling for") + "nosotros que tú us that you "
					+ s("podríamight", "could") + "tener, have, " + s("otra aparte de la que esapart from that of", "otra que no esother than") + "amor. love. "
					+ breath();
		}
		else {
			speech += "Escucha esta You hear this " + s("especialspecial")
					+ s("voz, con la que te hablamos, a ti.voice, with which we speak, to you.", "nuestra voz.voice of ours.") + breath();
		}
		speech += "Y nosotros And we " + s(s("firmementefirmly") + "creemosbelieve", s("sinceramentesincerely") + "esperamoshope")
				+ "que, cuanto más tiempo pases con nosotros, that, the more time you spend with us, "
				+ s("con nosotros escuchándote, with us listening to you, " + breathShort()) + "más the more ";
		speech += s("delicias tomarás delight you will take " + s(breathShort()) + "en nosotros.in us.", "amor tendrás love you will have "
				+ s(breathShort()) + "para nosotros. for us. " + s(breathShort() + "Y nosotros, para ti.And we, for you."));
		return speech + breath();
	}
	
	public String getReprompt() {
		
		return speechUtils.getString("chooseContinueNoAffect");
	}

}
