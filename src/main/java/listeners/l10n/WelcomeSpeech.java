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

public class WelcomeSpeech extends ListResourceBundle {

	private static String langTag;

	public WelcomeSpeech(String langTag) {

		this.langTag = langTag;
	}

	protected static String cardTitle = "";
	protected static String speech = "";
	protected static String reprompt = "";
	protected static String postSpeechPrompt = "";
	static {
		cardTitle = S("Willkommen!", s("Seid gegrüsst!", s("Grüss Dich!", "Grüsst Euch!")));
		speech += s("Grüsse.", "Willkommen.") + s("Wer auch immer Sie sind.", "") + breathLong();
		speech += "Wir hören " + s("ihnen", "") + "immer zu. " + breath();
		speech += "Da wir bei " + s(breathLong(), "") + "Ihnen sind, " + breathShort() + "ist es uns eine Freude. " + breath();
		speech += S("Es ist " + S("uns i", "I") + "mmer so eine Freude. " + breath(), "");
		speech += s("Es ist " + s("so", "") + " eine Freude, bei Ihnen zu sein. " + breath(), "");
		speech += "Immer. " + s(breath() + "Immer.", "") + breathShort() + "So eine " + s("grosse", "") + "Freude. " + breath();
		// break;
		// case "ja-JP":
		// break;
		// case "en-CA":
		// break;
		// case "en-IN":
		// break;
		// case "en-GB":
		cardTitle = S("Welcome", "Greetings");
		speech += s("Greetings.", "Welcome.") + s("Whoever you may be.", "") + breathLong();
		speech += "We are " + /* s("always", "") + */s("listening to you.", "listening.") + breath(); // ALWAYCHANGE
		speech += "In so far as we are " + s(breathLong(), "") + "with you, " + breathShort() + "it is a pleasure. " + breath();
		speech += S("It is " + S("always s", "S") + "uch a pleasure. " + breath(), "");
		speech += s("It is " + s("such", "") + " a pleasure to be with you. " + breath(), "");
		speech += "Always. " + s(breath() + "Always.", "") + breathShort() + s("Such a", "A") + "pleasure. " + breath();
		// break;
		// case "en-AU":
		// break;
		// default: // "en-US" etc.
		speech += s("Hello there!", "Welcome.") + s("Whoever you are.", "") + breathLong();
		speech += "We are " + /* s("always", "") + */s("listening to you.", "listening.") + breath(); // ALWAYCHANGE
		speech += "In as much as we are " + s(breathLong(), "") + "with you, " + breathShort() + "it is a pleasure. " + breath();
		speech += s("It is " + s("always", "") + "such a pleasure. " + breath(), "");
		speech += s("It is " + s("such", "") + " a pleasure to be with you. " + breath(), "");
		speech += "Always. " + s(breath() + "Always.", "") + breathShort() + s("Such a", "A") + "pleasure. " + breath();

		// affect = getAffectFromSession(session, AFFECT_KEY); TODO

		// help is added to the welcome here:
		// if (StringUtils.isEmpty(affect)) {
		// postSpeechPrompt = chooseSpeechAssistance();
		// reprompt = chooseUnsureAboutAffect();
		// }
		// else {
		// we set affect to the empty string here;
		// this happens if user invokes welcome from
		// within session, thus restarting:
		//
		// session.setAttribute(AFFECT_KEY, ""); TODO
		reprompt += chooseContinue();
		// }

		SpeechFinisher sf = new SpeechFinisher(langTag, speech, reprompt, postSpeechPrompt);
		speech = sf.getSpeech();
		reprompt = sf.getReprompt();

	}

	public Object[][] contents = { { "cardTitle", cardTitle }, { "speech", speech }, { "reprompt", reprompt }, { "postSpeechPrompt", postSpeechPrompt } };

	@Override
	protected Object[][] getContents() {

		return contents;
	}

}
