package listeners.handlers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static listeners.util.ConstantUtils.info;
import listeners.model.LangConstants;
import listeners.l10n.WelcomeSpeech;

public class TestHandler {

	public TestHandler() {
		
		LangConstants lc = new LangConstants("en_IN");
		WelcomeSpeech ws = (WelcomeSpeech) ResourceBundle.getBundle("listeners.l10n.WelcomeSpeech", lc.locale);

		// WelcomeSpeech ws = (WelcomeSpeech)
		// ResourceBundle.getBundle("listeners.l10n.WelcomeSpeech", l);

		info("@TestHandler, cardTitle: “" + ws.getString("cardTitle") + "”");
		info("@TestHandler, speech: “" + ws.getString("speech") + "”");
		info("@TestHandler, reprompt: “" + ws.getString("reprompt") + "”");

		// Object PICTURE_WORDS = rb.getObject("pictureWords");
		// info("@TestHandler, PICTURE_WORDS: " + PICTURE_WORDS.toString());
	}

	public static void main(String[] args) {

		new TestHandler();

	}

}
