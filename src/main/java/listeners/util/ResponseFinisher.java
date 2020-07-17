package listeners.util;

import static listeners.model.Constants.PERFORMANCE;
import static listeners.model.LangConstants.dateString;
import static listeners.model.LangConstants.polyVoiceWrapper;
import static listeners.util.Utils.*;

import java.util.Map;

// get one of these with the pattern: ResponseFinisher.builder().withSpeech(speech).build();
public class ResponseFinisher {

	private static String speech;
	private static String cardText;
	private static String reprompt;

	public static Builder builder() {

		return new Builder();
	}

	public ResponseFinisher(Builder builder) {

		if ("".equals(builder.preamble))
			this.cardText = removePauseTags(builder.speech);
		else
			this.cardText = removePauseTags(builder.preamble) + "\nTHE LISTENERS ...\n" + removePauseTags(builder.speech);
		
		this.speech = "<speak>" + insertPauseTags(builder.preamble) + polyVoiceWrapper
				+ insertPauseTags(builder.speech.concat(builder.postSpeechPrompt)) + "</lang></voice></speak>";

		this.reprompt = "<speak>" + polyVoiceWrapper + insertPauseTags(builder.reprompt)
				+ "</lang></voice></speak>";
	}

	public String getCardText() {

		return PERFORMANCE ? cardText + " - " + dateString : cardText;
	}

	public String getReprompt() {

		return reprompt;
	}

	public String getSpeech() {

		return speech;
	}

	public ResponseFinisher(String localeTag, String speech, String postSpeechPrompt, String reprompt,
			Map<String, Object> sessionAttributes) {

		// this(localeTag, speech, postSpeechPrompt, reprompt);


		// TODO: session related *** FROM HERE ... ***
		// Session session = getListenerSession();
		//
		// if (specificFragment || interruptable) {
		// // manipulate speech based on readIndex:
		// int readsofarIndex = (int) session.getAttribute(READSOFAR_KEY);
		// boolean insertMarkov, insertGuyz;
		// if (readsofarIndex > 24) {
		// insertMarkov = true;
		// insertGuyz = true;
		// }
		// else {
		// int r = PROBABILITY_MAP.get(readsofarIndex);
		// insertMarkov = randInt(0, r) == 0;
		// r--;
		// insertGuyz = randInt(0, (r < 0) ? 0 : r) == 0;
		// }
		//
		// session.setAttribute(GUYZIRQ_KEY, insertGuyz);
		// session.setAttribute(MARKOVIRQ_KEY, insertMarkov);
		// String[] sents = RiTa.splitSentences(speech);
		// int mCase = readsofarIndex / 5 + 1;
		// mCase = (mCase > 7) ? 7 : mCase;
		//
		// if (insertMarkov) {
		// // set Markov paramaters based on progress
		// int minNumOfSents = 1, maxNumOfSents = 2, nMin = 4, nMax = 5;
		// switch (mCase) {
		// case 2:
		// maxNumOfSents = 3;
		// break;
		//
		// case 3:
		// minNumOfSents = 2;
		// maxNumOfSents = 3;
		// break;
		//
		// case 4:
		// minNumOfSents = 2;
		// maxNumOfSents = 4;
		// nMin = 3;
		// break;
		//
		// case 5:
		// minNumOfSents = 3;
		// maxNumOfSents = 4;
		// nMin = 3;
		// break;
		//
		// case 6:
		// minNumOfSents = 4;
		// maxNumOfSents = 6;
		// nMin = 3;
		// nMax = 4;
		// break;
		//
		// case 7:
		// minNumOfSents = 4;
		// maxNumOfSents = 8;
		// nMin = 2;
		// nMax = 3;
		// break;
		//
		// default: // case 1: use initial values
		// break;
		// }
		//
		// speech = "";
		// int irqPoint = randInt(0, sents.length - 1);
		// int i;
		// for (i = 0; i < irqPoint; i++) {
		// speech += sents[i] + SPC;
		// }
		// String[] markovSents = null;
		// switch (randInt(nMin, nMax)) {
		// case 2:
		// markovSents = rm2.generateSentences(randInt(minNumOfSents,
		// maxNumOfSents));
		// break;
		// case 3:
		// markovSents = rm3.generateSentences(randInt(minNumOfSents,
		// maxNumOfSents));
		// break;
		// case 4:
		// markovSents = rm3.generateSentences(randInt(minNumOfSents,
		// maxNumOfSents));
		// break;
		// case 5:
		// markovSents = rm5.generateSentences(randInt(minNumOfSents,
		// maxNumOfSents));
		// break;
		// default:
		// break;
		// }
		// if (markovSents != null) {
		// switch (langTag) {
		// case "de-DE":
		// speech += "Äh. ";
		// break;
		// case "ja-JP":
		// // break;
		// case "en-CA":
		// case "en-IN":
		// case "en-AU":
		// case "en-GB":
		// default: // "en-US" etc.
		// speech += "Um. ";
		// }
		// for (int j = 0; j < markovSents.length; j++) {
		// speech += markovSents[j] + SPC;
		// }
		// switch (langTag) {
		// case "de-DE":
		// speech += breathLong() + s(s("Entschuldigen uns.", "Entschuldigen Sie
		// uns!") + "Wir sind nicht sicher, warum wir das gesagt haben. " +
		// breathLong(), s("Bitte entschuldigen Sie uns.", "") + "Ähm.");
		// break;
		// case "ja-JP":
		// // break;
		// case "en-CA":
		// case "en-IN":
		// case "en-AU":
		// case "en-GB":
		// default: // "en-US" etc.
		// speech += breathLong() + s(s("Sorry.", "Excuse us!") + "We're not sure
		// what came over us. " + breathLong(), s("Excuse us.", "") + "Ahem.");
		// }
		// }
		// for (i = irqPoint; i < sents.length; i++) {
		// speech += sents[i] + SPC;
		// }
		// sents = RiTa.splitSentences(speech);
		// }
		//
		// if (insertGuyz && (guyzIndex <= NUMBER_OF_GUYZ)) {
		// int irqPoint = randInt(0, sents.length - 1);
		// speech = "";
		// int i;
		// for (i = 0; i < irqPoint; i++) {
		// speech += sents[i] + SPC;
		// }
		// String[] topntail = cutOffSentence(sents[irqPoint]);
		// speech += topntail[0];
		// // "<guyz> " to be replaced in card text
		// speech += "<guyz> " + insertGuyz();
		// if (mCase > 4) // do two insertions if late in the game
		// speech += insertGuyz();
		// session.setAttribute(GUYZ_KEY, guyzIndex);
		// // false);
		// speech += topntail[1];
		// for (i = irqPoint + 1; i < sents.length; i++) {
		// speech += sents[i] + SPC;
		// }
		// }
		// }
		//
		// speech = insertPauseTags((isAskResponse ? preSpeech : "") + speech +
		// (isAskResponse ? postSpeechPrompt : ""));
		//
		// String guyzIrq = "";
		// switch (langTag) {
		// case "de-DE":
		// guyzIrq = s("[die «Leute»", "[andere Stimmen") + "unterbrochen hier ...]
		// ";
		// break;
		// case "ja-JP":
		// // break;
		// case "en-CA":
		// // break;
		// case "en-IN":
		// // break;
		// case "en-AU":
		// // break;
		// case "en-GB":
		// default: // "en-US" etc.
		// guyzIrq = s(s("[the 'guyz'", "[other voices"), s("[another voice", "[the
		// other voice" + s("s", "") + SPC)) + "interrupted here ...] ";
		// }
		// cardText = speech.replaceAll("<guyz> ", guyzIrq);
		//
		// speech = speech.replaceAll("<guyz> ", "");
		// *** TO HERE ***

		// Create SSML speech output
		// SsmlOutputSpeech ssmlSpeech = new SsmlOutputSpeech();

		// speech = insertPauseTags(speech);
		// speech = "<speak>" + polyVoiceWrapper + speech +
		// "</lang></voice></speak>";
		// ssmlSpeech.setSsml("<speak>" + speech + "</speak>");

		// Create reprompt. NB: now using SSML
		// SsmlOutputSpeech ssmlReprompt = new SsmlOutputSpeech();

		// reprompt = insertPauseTags(reprompt);
		// reprompt = "<speak>" + polyVoiceWrapper + reprompt +
		// "</lang></voice></speak>";
		// ssmlReprompt.setSsml("<speak>" + reprompt + "</speak>");
		// Reprompt repromptObject = new Reprompt();
		// repromptObject.setOutputSpeech(ssmlReprompt);

		// only interruptable is operative currently
		// TODO interruptable = false;
		// markovable = false;

		// if (isAskResponse) {
		// return SpeechletResponse.newAskResponse(ssmlSpeech, repromptObject,
		// card);
		//
		// }
		// else // a tell=end-session response may be set by
		// {
		// // NOTE: 'newTellResponse' ends the session
		// return SpeechletResponse.newTellResponse(ssmlSpeech, card);
		// }
		// this.speech = speech;
		// this.reprompt = reprompt;
		// this.postSpeechPrompt = postSpeechPrompt;
	}

	public static class Builder {

		private String preamble = "";
		private String speech = "";
		private String postSpeechPrompt = "";
		private String reprompt = "";

		private Builder() {

		}

		public Builder withPreamble(String preamble) {

			this.preamble = preamble;
			return this;
		}

		public Builder withSpeech(String speech) {

			this.speech = speech;
			return this;
		}

		public Builder withPostSpeechPrompt(String postSpeechPrompt) {

			this.postSpeechPrompt = postSpeechPrompt;
			return this;
		}

		public Builder withReprompt(String reprompt) {

			this.reprompt = reprompt;
			return this;
		}

		public ResponseFinisher build() {

			return new ResponseFinisher(this);
		}
	}

}
