package listeners.l10n;

import static listeners.util.Utils.S;
import static listeners.util.Utils.breath;
import static listeners.util.Utils.breathShort;
import static listeners.util.Utils.s;

public class OkIntent extends L10nSpeech {

	public Object[][] contents = { { "cardTitle", buildCardTitle() }, { "speech", buildSpeech() } };

	public String buildCardTitle() {

		return S("Still with us.", "Ok.");
	}

	public String buildSpeech() {

		return "Still with us. " + breath();
	}

	protected Object[][] getContents() {

		return contents;
	}

}
