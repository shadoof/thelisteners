package listeners.util;

import static listeners.model.Attributes.*;
import static listeners.model.Constants.*;
import static listeners.model.LangConstants.*;
import static listeners.util.Utils.*;
import static listeners.model.LangConstants.dateString;
import static listeners.model.LangConstants.polyVoiceWrapper;
import static listeners.util.Utils.*;

import java.util.Map;

import rita.RiTa;

// get one of these with the pattern: ResponseFinisher.builder().withSpeech(speech).build();
public class ResponseFinisher {

	private String cardText;
	private String speech;
	private boolean interruptable;
	private String reprompt;

	public static Builder builder() {

		return new Builder();
	}

	public ResponseFinisher(Builder response) {

		if (response.interruptable) {
			// cardTitle is handled by insertInterruptions()
			// for interruptable responses
			this.speech = insertInterruptions(response.speech);
			this.cardText = removePauseTags(response.speech);
		}
		else {
			if ("".equals(response.preamble))
				this.cardText = removePauseTags(response.speech);
			else
				this.cardText = removePauseTags(response.preamble) + "\nTHE LISTENERS ...\n"
						+ removePauseTags(response.speech);
		}
		
		this.cardText = stripSsmlTags(this.cardText);

		this.speech = "<speak>" + insertPauseTags(response.preamble) + polyVoiceWrapper
				+ insertPauseTags(response.speech.concat(response.postSpeechPrompt))
				+ "</lang></voice></speak>";

		this.reprompt = "<speak>" + polyVoiceWrapper + insertPauseTags(response.reprompt)
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

	private String insertInterruptions(String speech) {

		// Version 2.x had this note:
		// only interruptable is operative currently
		// markovable as a property of InnerResponse
		// is not yet implemented. TODO ?

		// manipulate speech based on readIndex:
		int readsofarIndex = (int) sessAttributes.get(READSOFAR);
		boolean insertMarkov, insertGuyz;
		if (readsofarIndex > 24) {
			insertMarkov = true;
			insertGuyz = true;
		}
		else {
			int r = PROBABILITY_MAP.get(readsofarIndex);
			insertMarkov = randInt(0, r) == 0;
			r--;
			insertGuyz = randInt(0, (r < 0) ? 0 : r) == 0;
		}

		sessAttributes.justPut(GUYZIRQ, insertGuyz);
		sessAttributes.justPut(MARKOVIRQ, insertMarkov);

		String[] sents = RiTa.splitSentences(speech);

		int mCase = readsofarIndex / 5 + 1;
		mCase = (mCase > 7) ? 7 : mCase;

		if (insertMarkov) {
			// set Markov paramaters based on progress
			int minNumOfSents = 1, maxNumOfSents = 2, nMin = 4, nMax = 5;
			switch (mCase) {
				case 2:
					maxNumOfSents = 3;
					break;

				case 3:
					minNumOfSents = 2;
					maxNumOfSents = 3;
					break;

				case 4:
					minNumOfSents = 2;
					maxNumOfSents = 4;
					nMin = 3;
					break;

				case 5:
					minNumOfSents = 3;
					maxNumOfSents = 4;
					nMin = 3;
					break;

				case 6:
					minNumOfSents = 4;
					maxNumOfSents = 6;
					nMin = 3;
					nMax = 4;
					break;

				case 7:
					minNumOfSents = 4;
					maxNumOfSents = 8;
					nMin = 2;
					nMax = 3;
					break;

				default: // case 1: use initial values
					break;
			}

			speech = "";
			int irqPoint = randInt(0, sents.length - 1);
			int i;
			for (i = 0; i < irqPoint; i++) {
				speech += sents[i] + SPC;
			}
			String[] markovSents = null;
			switch (randInt(nMin, nMax)) {
				case 2:
					markovSents = rm2.generateSentences(randInt(minNumOfSents, maxNumOfSents));
					break;
				case 3:
					markovSents = rm3.generateSentences(randInt(minNumOfSents, maxNumOfSents));
					break;
				case 4:
					markovSents = rm3.generateSentences(randInt(minNumOfSents, maxNumOfSents));
					break;
				case 5:
					markovSents = rm5.generateSentences(randInt(minNumOfSents, maxNumOfSents));
					break;
				default:
					break;
			}

			if (markovSents != null) {

				// TODO for other l10n
				speech += ("de_DE".equals(localeTag) ? "Äh. " : "Um. ");

				for (int j = 0; j < markovSents.length; j++) {
					speech += markovSents[j] + SPC;
				}

				speech += speechUtils.getString("excuseMarkov");

			}

			for (i = irqPoint; i < sents.length; i++) {
				speech += sents[i] + SPC;
			}

			sents = RiTa.splitSentences(speech);
		}

		if (insertGuyz && ((int) sessAttributes.get(GUYZINDEX) <= NUMBER_OF_GUYZ)) {
			int irqPoint = randInt(0, sents.length - 1);
			speech = "";
			int i;
			for (i = 0; i < irqPoint; i++) {
				speech += sents[i] + SPC;
			}
			String[] topntail = cutOffSentence(sents[irqPoint]);
			speech += topntail[0];
			// "<guyz> " to be replaced in card text
			speech += "<guyz> " + insertGuyz();
			if (mCase > 4) // do two insertions if late in the game
				speech += insertGuyz();
			speech += topntail[1];
			for (i = irqPoint + 1; i < sents.length; i++) {
				speech += sents[i] + SPC;
			}
		}

		String guyzIrq = speechUtils.getString("guyzIrq");

		cardText = speech.replaceAll("<guyz> ", guyzIrq);

		speech = speech.replaceAll("<guyz> ", "");

		return speech += breath();
	}

	private String[] cutOffSentence(String sentence) {

		String[] tnt = { "", "" };
		sentence = stripSsmlTags(sentence).trim();
		String[] words = sentence.split(SPC);
		int i = 0, splitAt = randInt(0, words.length);
		if (splitAt == 0)
			tnt[0] = "";
		else {
			for (i = 0; i < splitAt; i++) {
				tnt[0] += words[i] + SPC;
			}
		}
		if (splitAt == words.length)
			tnt[1] = "";
		else {
			for (i = splitAt; i < words.length; i++) {
				tnt[1] += words[i] + SPC;
			}
		}
		return tnt;
	}

	private String insertGuyz() {

		int guyzIndex = (int) sessAttributes.get(GUYZINDEX);
		String insert = "<audio src=\"https://rednoise.org/programmatology/aurality/echo/DeliriumPlea-"
				+ String.format("%03d", guyzIndex) + ".mp3\" /> ";
		guyzIndex++;
		if (PERFORMANCE) // leave out a group of five in performance
		{
			if (guyzIndex > 20 && guyzIndex < 26) guyzIndex = 26;
		}
		sessAttributes.justPut(GUYZINDEX, guyzIndex);
		return insert;
	}

	public static class Builder {

		private String preamble = "";
		private String speech = "";
		private String postSpeechPrompt = "";
		private boolean interruptable = false;
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

		public Builder withInterruptable(boolean interruptable) {

			this.interruptable = interruptable;
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
