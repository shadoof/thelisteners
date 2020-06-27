package listeners.l10n;

import static listeners.util.ConstantUtils.S;
import static listeners.util.ConstantUtils.breath;
import static listeners.util.ConstantUtils.breathLong;
import static listeners.util.ConstantUtils.breathShort;
import static listeners.util.ConstantUtils.s;
import static listeners.util.SpeechUtils.chooseContinue;
import static listeners.util.SpeechUtils.chooseSpeechAssistance;
import static listeners.util.SpeechUtils.chooseUnsureAboutAffect;

import java.util.ListResourceBundle;

import org.apache.commons.lang3.StringUtils;

import listeners.util.SpeechFinisher;

public class WelcomeSpeech_de_DE extends WelcomeSpeech {

	private static String langTag;

	public WelcomeSpeech_de_DE(String langTag) {

		super(langTag);
	}

	static {
		cardTitle = S("Willkommen!", s("Seid gegrüsst!", s("Grüss Dich!", "Grüsst Euch!")));
		speech += s("Grüsse.", "Willkommen.") + s("Wer auch immer Sie sind.", "") + breathLong();
		speech += "Wir hören " + s("ihnen", "") + "immer zu. " + breath();
		speech += "Da wir bei " + s(breathLong(), "") + "Ihnen sind, " + breathShort() + "ist es uns eine Freude. " + breath();
		speech += S("Es ist " + S("uns i", "I") + "mmer so eine Freude. " + breath(), "");
		speech += s("Es ist " + s("so", "") + " eine Freude, bei Ihnen zu sein. " + breath(), "");
		speech += "Immer. " + s(breath() + "Immer.", "") + breathShort() + "So eine " + s("grosse", "") + "Freude. " + breath();

		reprompt += chooseContinue();

		SpeechFinisher sf = new SpeechFinisher(langTag, speech, reprompt, postSpeechPrompt);
		speech = sf.getSpeech();
		reprompt = sf.getReprompt();

	}

}
