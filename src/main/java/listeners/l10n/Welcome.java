package listeners.l10n;

import static listeners.model.LangConstants.locale;
import static listeners.util.ConstantUtils.S;
import static listeners.util.ConstantUtils.breath;
import static listeners.util.ConstantUtils.breathLong;
import static listeners.util.ConstantUtils.breathShort;
import static listeners.util.ConstantUtils.s;

import listeners.util.SpeechUtils;

public class Welcome extends L10nSpeech {

	public Object[][] contents = { { "cardTitle", buildCardTitle() }, 
			{ "speech", buildSpeech() } 
			};
	
	public String buildCardTitle() {
		return S("Welcome", "Greetings");
	}

	public String buildSpeech() {
		String speech = s("Greetings.", "Welcome.") + s("Whoever you may be.", "") + breathLong();
		speech += "We are " + /* s("always", "") + */s("listening to you.", "listening.") + breath(); // ALWAYCHANGE
		speech += "In so far as we are " + s(breathLong(), "") + "with you, " + breathShort() + "it is a pleasure. " + breath();
		speech += S("It is " + S("always s", "S") + "uch a pleasure. " + breath(), "");
		speech += s("It is " + s("such", "") + " a pleasure to be with you. " + breath(), "");
		speech += "Always. " + s(breath() + "Always.", "") + breathShort() + s("Such a", "A") + "pleasure. " + breath();

		return speech;
	}

	@Override
	protected Object[][] getContents() {

		return contents;
	}
}
