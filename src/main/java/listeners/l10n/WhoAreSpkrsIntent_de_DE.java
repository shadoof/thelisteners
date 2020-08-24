package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class WhoAreSpkrsIntent_de_DE extends WhoAreSpkrsIntent {

	public String buildCardTitle() {

		return "Wer glauben wir, dass " + S("du bist", "Sie sind");
	}

	public String buildSpeech() {

		String speech = "Wir denken, dass Sie die " + s("wahren " + breathShort()) + "‘Sprecher’ sind. "
				+ s("Dass Sie alle Menschen sind.") + "Alle, ausser uns. ";
		return speech + breath();
	}

}
