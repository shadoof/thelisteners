package listeners.handlers;

import static listeners.util.Utils.*;
import static listeners.model.Constants.*;
import static listeners.model.LangConstants.*;

import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;

import listeners.l10n.L10nSpeech;
import listeners.l10n.Welcome;
import listeners.l10n.Fragments;
import listeners.model.Attributes;
import listeners.model.Constants;
import listeners.model.LangConstants;
import listeners.util.ResponseFinisher;

public class TestHandler {

	public static void main(String[] args) {

		new TestHandler();

	}

	public TestHandler() {

		Locale locale = Constants.parseLocale("en-us");

		attributes = Attributes.getInstance(locale);
		langConstants = LangConstants.getInstance(locale);
		speechUtils = ResourceBundle.getBundle("listeners.l10n.SpeechUtils", locale);
				
//		// fragments (10 speeches The Listeners like to make)
//		// have been built and stored in LangConstants instance
//		
//		int f = randInt(0, 9);
//		info("@TestHander, fragments[" + f +"]:");
//		info(removePauseTags(fragments[f]) + "\n");
//
//		// Markov generation testing
//
//		info("@TestHander, rm2:");
//		int numOfSents = 3;
//		String[] sents = rm2.generateSentences(numOfSents);
//		for (int i = 0; i < sents.length; i++) {
//			info(removePauseTags(sents[i]) + ((i == (sents.length - 1)) ? "\n" : ""));
//		}
//
//		info("@TestHander, rm3:");
//		numOfSents = 3;
//		sents = rm3.generateSentences(numOfSents);
//		for (int i = 0; i < sents.length; i++) {
//			info(removePauseTags(sents[i]) + ((i == (sents.length - 1)) ? "\n" : ""));
//		}
//
//		info("@TestHander, rm4:");
//		numOfSents = 3;
//		sents = rm4.generateSentences(numOfSents);
//		for (int i = 0; i < sents.length; i++) {
//			info(removePauseTags(sents[i]) + ((i == (sents.length - 1)) ? "\n" : ""));
//		}
//
//		info("@TestHander, rm5:");
//		numOfSents = 3;
//		sents = rm5.generateSentences(numOfSents);
//		for (int i = 0; i < sents.length; i++) {
//			info(removePauseTags(sents[i]) + ((i == (sents.length - 1)) ? "\n" : ""));
//		}

		// Response Assembly Testing:

		 String preamble = speechUtils.getString("getPreamble");

		 L10nSpeech ls = (Welcome) ResourceBundle.getBundle("listeners.l10n.Welcome", locale);
		
		 info("@TestHandler, cardTitle: “" + ls.getCardTitle() + "”");
		 info("@TestHandler, speech: “" + ls.getSpeech() + "”");
		 info("@TestHandler, reprompt: “" + ls.getReprompt() + "”");
		 info("@TestHandler, postSpeechPrompt: “" + ls.getPostSpeechPrompt() + "”");
		
		 ResponseFinisher rf = ResponseFinisher.builder()
		 .withPreamble(preamble)
		 .withSpeech(ls.getSpeech())
		 .withPostSpeechPrompt(ls.getPostSpeechPrompt())
		 .withReprompt(ls.getReprompt())
		 .build();

		 info("@TestHandler, After finishing:");
		 info("@TestHandler, speech: “" + rf.getSpeech());
		 info("@TestHandler, reprompt: “" + rf.getReprompt());

		// Object PICTURE_WORDS = rb.getObject("pictureWords");
		// info("@TestHandler, PICTURE_WORDS: " + PICTURE_WORDS.toString());
	}

}
