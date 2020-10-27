package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class SpkrsLoveLsnrsIntent_fr_FR extends SpkrsLoveLsnrsIntent {

	public String buildCardTitle() {

		// TODO
		return S("You love us?", "We love you too");
	}

	public String buildSpeech() {

		// TODO
		String speech = s("Of course. " + breathShort()) + s("Of course you do. " + breathShort());
		if (heads()) {
			speech += "It is " + s("difficult", "hard") + "for us to " + s("imagine", "conceive of") + "any ";
			speech += s("other") + s("relation with", "feeling for") + "us that you "
					+ s("might", "could") + "have, " + s("apart from that of", "other than") + "love. "
					+ breath();
		}
		else {
			speech += "You hear this " + s("special")
					+ s("voice, with which we speak, to you.", "voice of ours.") + breath();
		}
		speech += "And we " + s(s("firmly") + "believe", s("sincerely") + "hope")
				+ "that, the more time you spend with us, "
				+ s("with us listening to you, " + breathShort()) + "the more ";
		speech += s("delight you will take " + s(breathShort()) + "in us.", "love you will have "
				+ s(breathShort()) + "for us. " + s(breathShort() + "And we, for you."));
		return speech + breath();
	}
	
	public String getReprompt() {
		
		return speechUtils.getString("chooseContinueNoAffect");
	}

}
