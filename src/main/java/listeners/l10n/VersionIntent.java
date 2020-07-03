package listeners.l10n;

import static listeners.model.Constants.DEV;
import static listeners.model.Constants.DO_NOT_PROMPT_AFFECT;
import static listeners.model.Constants.LIVE;
import static listeners.model.Constants.PERFORMANCE;
import static listeners.model.Constants.VERSION;
import static listeners.model.Constants.VERSION_DATE_DEV;
import static listeners.model.Constants.VERSION_DATE_LIVE;
import static listeners.model.LangConstants.locale;
import static listeners.model.LangConstants.localeTag;
import static listeners.util.ConstantUtils.breath;
import static listeners.util.ConstantUtils.s;

import listeners.util.SpeechUtils;

public class VersionIntent extends L10nSpeech {

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

		return "Version";
	}

	public String buildPostSpeechPrompt() {

		if (DEV && !LIVE) {
			return postSpeechPrompt; // empty string
		}
		else
			return new SpeechUtils(locale).chooseContinue(DO_NOT_PROMPT_AFFECT);
	}

	public String buildReprompt() {

		if (DEV && !LIVE) {
			return speech;
		}
		else
			return new SpeechUtils(locale).chooseContinue(DO_NOT_PROMPT_AFFECT);
	}

	public String buildSpeech() {

		if (DEV && !LIVE) {
			speech = getVersionLocale();
		}
		else {
			speech = s("Thank you for " + s("your interest.", "the question.") + breath(), "");
			speech += "We " + s("prefer to", "would rather") + "think of this as the date of our last rebirth, which was ";
			speech += VERSION_DATE_LIVE + ". " + breath();
			speech += s("It seems always to be very recent. " + s("But we " + s("certainly", "") + "were not born yesterday.", "") + breath(), "");
		}
		return speech += breath();
	}

	@Override
	protected Object[][] getContents() {

		return contents;
	}

	protected String getVersionLocale() {

		String s = "Development. " + VERSION + ", " + VERSION_DATE_DEV + (PERFORMANCE ? ". Performance. " : ". ");
		switch (localeTag) {
			case "de_DE":
				s += "German. ";
				break;
			case "en_GB":
				s += "British English. ";
				break;
			case "en_IN":
				s += "South Asian English. ";
				break;
			case "en_AU":
				s += "Australian English. ";
				break;
			case "en_CA":
				s += "Canadian English. ";
				break;
			case "ja_JP":
				s += "Japanese. ";
				break;
			default: // "en_US" etc.
				s += "United States English. ";
		}
		return s;
	}
}
