package listeners.l10n;

import static listeners.model.LangConstants.locale;
import static listeners.util.ConstantUtils.S;
import static listeners.util.ConstantUtils.breath;
import static listeners.util.ConstantUtils.breathShort;
import static listeners.util.ConstantUtils.breathShortest;
import static listeners.util.ConstantUtils.randInt;
import static listeners.util.ConstantUtils.s;

import listeners.util.SpeechUtils;

public class CreepIntent extends L10nSpeech {

	protected String cardTitle = "";
	protected String speech = "";
	protected String reprompt = "";
	protected String postSpeechPrompt = "";

	public Object[][] contents = { { "cardTitle", buildCardTitle() }, 
			{ "speech", buildSpeech() }, 
			{ "reprompt", buildReprompt() }, 
			{ "postSpeechPrompt", buildPostSpeechPrompt() } 
			};

	public String buildCardTitle() {

		return S("You find us creepy?", "You think we're creepy?");
	}

	public String buildPostSpeechPrompt() {

		return new SpeechUtils(locale).chooseContinue();
	}

	public String buildReprompt() {

		return new SpeechUtils(locale).chooseContinue();
	}

	public String buildSpeech() {

		speech += s("It's a little " + s("shocking", "disturbing"), "We are " + s("shocked", s("a little", "") + "upset")) + breath();
		speech += "to hear that. " + breathShort();
		speech += "We certainly don't " + s("intend", "mean") + "to " + s("disturb you.", "'creep you out'.") + breathShort();
		speech += "We " + s("just", "only") + "want to listen to you. ";
		speech += s(breathShort() + "To hear you.", "");
		speech += breath();
		return speech;
	}

	@Override
	protected Object[][] getContents() {

		return contents;
	}
}
