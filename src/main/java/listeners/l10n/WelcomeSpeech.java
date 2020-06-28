package listeners.l10n;

import static listeners.util.ConstantUtils.S;
import static listeners.util.ConstantUtils.breath;
import static listeners.util.ConstantUtils.breathLong;
import static listeners.util.ConstantUtils.breathShort;
import static listeners.util.ConstantUtils.s;
import static listeners.util.ConstantUtils.info;
import static listeners.util.SpeechUtils.chooseContinue;
import static listeners.util.SpeechUtils.chooseSpeechAssistance;
import static listeners.util.SpeechUtils.chooseUnsureAboutAffect;

import java.util.ListResourceBundle;

import org.apache.commons.lang3.StringUtils;

import listeners.util.SpeechFinisher;

public class WelcomeSpeech extends ListResourceBundle {

	protected String cardTitle = "";
	protected String speech = "";
	protected String reprompt = "";
	
	protected String buildSpeech() {
		speech = s("Greetings.", "Welcome.") + s("Whoever you may be.", "") + breathLong();
		speech += "We are " + /* s("always", "") + */s("listening to you.", "listening.") + breath(); // ALWAYCHANGE
		speech += "In so far as we are " + s(breathLong(), "") + "with you, " + breathShort() + "it is a pleasure. " + breath();
		speech += S("It is " + S("always s", "S") + "uch a pleasure. " + breath(), "");
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
		// }

		return speech;
	}
	
	protected String buildCardTitle() {
		return S("Welcome", "Greetings");
	}

	protected String buildReprompt() {
		return chooseContinue();
	}

	public Object[][] contents = { { "cardTitle", buildCardTitle() }, 
			{ "speech", buildSpeech() }, 
			{ "reprompt", buildReprompt() }, 
			};

	@Override
	protected Object[][] getContents() {

		return contents;
	}

}
