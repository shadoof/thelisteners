package listeners.handlers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static listeners.model.LangConstants.localeTag;
import static listeners.util.ConstantUtils.info;
import static listeners.util.ConstantUtils.s;

import listeners.model.LangConstants;
import listeners.util.ResponseFinisher;
import listeners.l10n.L10nSpeech;
import listeners.l10n.Welcome;

public class TestHandler {

	public static void main(String[] args) {

		new TestHandler();

	}

	public TestHandler() {
		
		String preAmble = "";
				preAmble = s("Unless we're mistaken, this is", "This seems to be") + "your first encounter with 'The Listeners'. ";
				preAmble += "They tend to " + s("talk", "speak") + "as much " + s("if not more than", "as") + "they listen. ";
				preAmble += "If you find what they say " + s("at all interesting,", "intriguing,") + "please be ";
				preAmble += s("patient.","patient, and spend some time with " + s("them.", "the skill."));
				preAmble += "If " + s("you don't,", "not,") + "or to interrupt a long speech, just say, " + s("clearly,", s("firmly,", "")) + "'Alex, Stop!' ";
				preAmble += s(s("And have done with it.",""),"They can be a little 'dark'. But ...") + s("We hope you enjoy","Thank you for listening to") + "'The Listeners'. ";

		LangConstants lc = new LangConstants("en_GB");
		L10nSpeech ls = (Welcome) ResourceBundle.getBundle("listeners.l10n.Welcome", lc.locale);


		info("@TestHandler, cardTitle: “" + ls.getCardTitle() + "”");
		info("@TestHandler, speech: “" + ls.getSpeech() + "”");
		info("@TestHandler, reprompt: “" + ls.getReprompt() + "”");
		info("@TestHandler, postSpeechPrompt: “" + ls.getPostSpeechPrompt() + "”");
		
		ResponseFinisher rf = new ResponseFinisher(localeTag, preAmble, ls.getSpeech(), ls.getPostSpeechPrompt(), ls.getReprompt());
		
		info("@TestHandler, After finishing:");
		info("@TestHandler, speech: “" + rf.getSpeech());
		info("@TestHandler, reprompt: “" + rf.getReprompt());
		

		// Object PICTURE_WORDS = rb.getObject("pictureWords");
		// info("@TestHandler, PICTURE_WORDS: " + PICTURE_WORDS.toString());
	}

}
