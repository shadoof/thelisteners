package listeners.handlers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static listeners.util.ConstantUtils.info;
import listeners.model.LangConstants;
import listeners.l10n.L10nSpeech;
import listeners.l10n.WelcomeSpeech;

public class TestHandler {

	public TestHandler() {
		
		LangConstants lc = new LangConstants("de_DE");
		L10nSpeech ls = (L10nSpeech) ResourceBundle.getBundle("listeners.l10n.VersionIntent", lc.locale);

		// WelcomeSpeech ws = (WelcomeSpeech)
		// ResourceBundle.getBundle("listeners.l10n.WelcomeSpeech", l);

		info("@TestHandler, cardTitle: “" + ls.getString("cardTitle") + "”");
		info("@TestHandler, speech: “" + ls.getString("speech") + "”");
		info("@TestHandler, reprompt: “" + ls.getString("reprompt") + "”");
		info("@TestHandler, postSpeechPrompt: “" + ls.getString("postSpeechPrompt") + "”");

		// Object PICTURE_WORDS = rb.getObject("pictureWords");
		// info("@TestHandler, PICTURE_WORDS: " + PICTURE_WORDS.toString());
	}

	public static void main(String[] args) {

		new TestHandler();

	}

}
