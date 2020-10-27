package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class WhoAreSpkrsIntent_fr_FR extends WhoAreSpkrsIntent {

	public String buildCardTitle() {

		// TODO
		return "Who " + s("do") + "we think you are";
	}

	public String buildSpeech() {

		// TODO
		String speech = "We believe that you are the " + s("true " + breathShort()) + "‘speakers’. "
				+ s("That you are everyone.") + "Everyone, apart from, us. ";
		return speech + breath();
	}

}
