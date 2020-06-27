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

public class WelcomeSpeech_en_GB extends WelcomeSpeech {

	public WelcomeSpeech_en_GB(String langTag) {

		super(langTag);
	}

	private static String langTag;

	static {
		cardTitle = S("Welcome", "Greetings");
		speech += s("Greetings.", "Welcome.") + s("Whoever you may be.", "") + breathLong();
		speech += "We are " + /* s("always", "") + */s("listening to you.", "listening.") + breath(); // ALWAYCHANGE
		speech += "In so far as we are " + s(breathLong(), "") + "with you, " + breathShort() + "it is a pleasure. " + breath();
		speech += S("It is " + S("always s", "S") + "uch a pleasure. " + breath(), "");
		speech += s("It is " + s("such", "") + " a pleasure to be with you. " + breath(), "");
		speech += "Always. " + s(breath() + "Always.", "") + breathShort() + s("Such a", "A") + "pleasure. " + breath();

		reprompt += chooseContinue();
		
		SpeechFinisher sf = new SpeechFinisher(langTag, speech, reprompt, postSpeechPrompt);
		speech = sf.getSpeech();
		reprompt = sf.getReprompt();
	}

}
