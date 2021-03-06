package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class WhoAreSpkrsIntent extends L10nSpeech {

	public Object[][] contents = { { "cardTitle", buildCardTitle() }, { "speech", buildSpeech() } };

	public String buildCardTitle() {

		return "Who " + s("do") + "we think you are";
	}

	public String buildSpeech() {

		String speech = "We believe that you are the " + s("true " + breathShort()) + "‘speakers’. "
				+ s("That you are everyone.") + "Everyone, apart from, us. ";
		return speech + breath();
	}

	protected Object[][] getContents() {

		return contents;
	}

}
