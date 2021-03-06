package listeners.l10n;

import static listeners.util.Utils.S;
import static listeners.util.Utils.breath;
import static listeners.util.Utils.breathLong;
import static listeners.util.Utils.breathShort;
import static listeners.util.Utils.s;

public class Welcome_de_DE extends Welcome {

	public String buildCardTitle() {

		return S("Willkommen!", s("Seid gegrüsst!", s("Grüss Dich!", "Grüsst Euch!")));
	}

	public String buildSpeech() {

		String speech = s("Grüsse.", "Willkommen.") + s("Wer auch immer Sie sind.") + breathLong();
		speech += "Wir hören " + s("ihnen") + "immer zu. " + breath();
		speech += "Da wir bei " + s(breathLong()) + "Ihnen sind, " + breathShort()
				+ "ist es uns eine Freude. " + breath();
		speech += s("Es ist " + S("uns i", "I") + "mmer so eine Freude. " + breath());
		speech += s("Es ist " + s("so") + " eine Freude, bei Ihnen zu sein. " + breath());
		speech += "Immer. " + s(breath() + "Immer.") + breathShort() + "So eine " + s("grosse")
				+ "Freude. " + breath();
		return speech;
	}
}
