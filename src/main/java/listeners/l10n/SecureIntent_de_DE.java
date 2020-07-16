package listeners.l10n;

import static listeners.util.ConstantUtils.S;
import static listeners.util.ConstantUtils.breath;
import static listeners.util.ConstantUtils.breathShort;
import static listeners.util.ConstantUtils.s;

public class SecureIntent_de_DE extends SecureIntent {

	public String buildCardTitle() {

		return S("Deine", "Unsere") + "Sicherheit ist " + s("dir", "uns") + "wichtig. ";
	}

	public String buildSpeech() {

		String speech = s("Wir glauben", "Wir sind davon überzeugt,") + "dass wir innerhalb "
				+ s("dieses Rahmens", "unseres Raums") + s("sicher", "beschützt") + "sind. ";
		speech += "Und dass du auch " + s("sicher", "beschützt") + "sein wirst, wenn du dich uns "
				+ s("eröffnest,", "mitteilst,");
		speech += "so dass wir euch immer " + s("hören", "zuhören") + "können. ";
		speech += breath();
		return speech;
	}

	protected Object[][] getContents() {

		return contents;
	}

}
