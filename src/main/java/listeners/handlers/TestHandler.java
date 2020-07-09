package listeners.handlers;

import static listeners.util.ConstantUtils.info;
import static listeners.util.ConstantUtils.s;
import static listeners.model.Constants.*;

import java.util.Locale;
import java.util.ResourceBundle;

import listeners.l10n.L10nSpeech;
import listeners.l10n.Welcome;
import listeners.model.Attributes;
import listeners.model.Constants;
import listeners.model.LangConstants;
import listeners.util.ResponseFinisher;
import listeners.util.SpeechUtils;

public class TestHandler {

	public static void main(String[] args) {

		new TestHandler();

	}

	public TestHandler() {

		String preamble = "";
		preamble = s("Unless we're mistaken, this is", "This seems to be")
				+ "your first encounter with 'The Listeners'. ";
		preamble += "They tend to " + s("talk", "speak") + "as much " + s("if not more than", "as")
				+ "they listen. ";
		preamble += "If you find what they say " + s("at all interesting,", "intriguing,") + "please be ";
		preamble += s("patient.", "patient, and spend some time with " + s("them.", "the skill."));
		preamble += "If " + s("you don't,", "not,") + "or to interrupt a long speech, just say, "
				+ s("clearly,", s("firmly,", "")) + "'Alex, Stop!' ";
		preamble += s(s("And have done with it.", ""), "They can be a little 'dark'. But ...")
				+ s("We hope you enjoy", "Thank you for listening to") + "'The Listeners'. ";

		Locale locale = Constants.parseLocale("de-de");
		// test version without passing an AttributesManager
		// (as second arg)
		attributes = Attributes.getInstance(locale);
		langConstants = LangConstants.getInstance(locale);
		speechUtils = SpeechUtils.getInstance(locale);

		L10nSpeech ls = (Welcome) ResourceBundle.getBundle("listeners.l10n.Welcome", locale);

		info("@TestHandler, cardTitle: “" + ls.getCardTitle() + "”");
		info("@TestHandler, speech: “" + ls.getSpeech() + "”");
		info("@TestHandler, reprompt: “" + ls.getReprompt() + "”");
		info("@TestHandler, postSpeechPrompt: “" + ls.getPostSpeechPrompt() + "”");

		ResponseFinisher rf = ResponseFinisher.builder()
																					.withPreamble(preamble)
																					.withPostSpeechPrompt(ls.getPostSpeechPrompt())
																					.withReprompt(ls.getReprompt())
																					.build();

		// ResponseFinisher rf = new ResponseFinisher(localeTag, preAmble,
		// ls.getSpeech(), ls.getPostSpeechPrompt(), ls.getReprompt());

		info("@TestHandler, After finishing:");
		info("@TestHandler, speech: “" + rf.getSpeech());
		info("@TestHandler, reprompt: “" + rf.getReprompt());

		// Object PICTURE_WORDS = rb.getObject("pictureWords");
		// info("@TestHandler, PICTURE_WORDS: " + PICTURE_WORDS.toString());
	}

}
