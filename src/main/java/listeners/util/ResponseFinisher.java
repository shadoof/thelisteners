package listeners.util;

import static listeners.model.Attributes.GUYZINDEX;
import static listeners.model.Attributes.GUYZIRQ;
import static listeners.model.Attributes.MARKOVIRQ;
import static listeners.model.Attributes.READSOFAR;
import static listeners.model.Attributes.sessAttributes;
import static listeners.model.Constants.NUMBER_OF_GUYZ;
import static listeners.model.Constants.PERFORMANCE;
import static listeners.model.Constants.PROBABILITY_MAP;
import static listeners.model.Constants.SPC;
import static listeners.model.Constants.localeTag;
import static listeners.model.Constants.speechUtils;
import static listeners.model.LangConstants.dateString;
import static listeners.model.LangConstants.polyVoiceWrapper;
import static listeners.model.LangConstants.rm2;
import static listeners.model.LangConstants.rm3;
import static listeners.model.LangConstants.rm5;
import static listeners.util.Utils.breath;
import static listeners.util.Utils.capitalize;
import static listeners.util.Utils.info;
import static listeners.util.Utils.insertPauseTags;
import static listeners.util.Utils.randInt;
import static listeners.util.Utils.removePauseTags;
import static listeners.util.Utils.stripSsmlTags;

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

		interruptable = response.irqable;
		speech = response.speech;
		reprompt = response.reprompt;
		cardText = response.cardText;

		info("@ResponseFinisher, interruptable: " + interruptable);
		if (interruptable) {
			// for interruptable responses
			speech = insertInterruptions(speech);
			cardText = speech.replaceAll("<guyz> ", speechUtils.getString("guyzIrq"));
			speech = speech.replaceAll("<guyz> ", "");
			cardText = removePauseTags(cardText);
		}
		else {
			if ("".equals(response.preamble))
				cardText = removePauseTags(response.speech);
			else
				cardText = removePauseTags(response.preamble) + "\nTHE LISTENERS ...\n"
						+ removePauseTags(speech);
		}

		cardText = stripSsmlTags(cardText);

		speech = "<speak>" + insertPauseTags(response.preamble) + polyVoiceWrapper
				+ insertPauseTags(speech.concat(response.postSpeechPrompt)) + "</lang></voice></speak>";

		reprompt = "<speak>" + polyVoiceWrapper + insertPauseTags(reprompt) + "</lang></voice></speak>";
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
		// only ‘interruptable’ is operative currently
		// ‘markovable’ as a property of InnerResponse
		// is not yet implemented. TODO in fullness?

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

		sessAttributes.put(GUYZIRQ, insertGuyz);
		sessAttributes.put(MARKOVIRQ, insertMarkov);

		String[] sents;

		int mCase = readsofarIndex / 5 + 1;
		mCase = (mCase > 7) ? 7 : mCase;

		if (insertMarkov) {
			// set Markov parameters based on progress
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

			sents = RiTa.splitSentences(speech);
			speech = "";
			int irqPoint = randInt(0, sents.length - 1);
			int i;
			for (i = 0; i < irqPoint; i++) {
				speech += sents[i] + SPC;
			}

			String[] markovSents = null;
			int markovN = randInt(nMin, nMax);
			switch (markovN) {
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

				// info("@ResponseFinisher, inserting " + markovSents.length + " n" + markovN + "
				// markovSents[0]: " + markovSents[0]);
				// TODO for other l10n
				speech += ("de_DE".equals(localeTag) ? "Äh. " : "Um. ");

				for (int j = 0; j < markovSents.length; j++) {
					speech += capitalize(markovSents[j]) + SPC;
				}

				speech += speechUtils.getString("excuseMarkov");

			}
			else
				info("@ResponseFinisher, markovSents was null");

			for (i = irqPoint; i < sents.length; i++) {
				speech += sents[i] + SPC;
			}

		} // if (insertMarkov) speech now includes Markov insertions incl. for guyz

		if (insertGuyz && ((int) sessAttributes.get(GUYZINDEX) <= NUMBER_OF_GUYZ)) {

			// info("@ResponseFinisher, inserting guyz");
			sents = RiTa.splitSentences(speech);
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

		} // if (insertGuyz) <guyz> tags are now all in the speech

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
		String guyzPath = speechUtils.getString("pathToGuyzAudio");

		String insert = guyzPath + String.format("%03d", guyzIndex) + ".mp3\" /> ";
		guyzIndex++;
		if (PERFORMANCE) // leave out a group of five in performance
		{
			if (guyzIndex > 20 && guyzIndex < 26) guyzIndex = 26;
		}
		sessAttributes.put(GUYZINDEX, guyzIndex);
		return insert;
	}

	public static class Builder {

		String preamble = "";
		String speech = "";
		String cardText = "";
		String postSpeechPrompt = "";
		boolean irqable = false;
		String reprompt = "";

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

		public Builder withInterruptable(boolean irq) {

			this.irqable = irq;
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
