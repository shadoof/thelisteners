package listeners.l10n;

import static listeners.model.Constants.DEV;
import static listeners.model.Constants.LIVE;
import static listeners.model.Constants.VERSION_DATE_LIVE;
import static listeners.util.ConstantUtils.breath;
import static listeners.util.ConstantUtils.s;

public class VersionIntent_en_US extends VersionIntent {

	public String buildSpeech() {

		if (DEV && !LIVE) {
			speech = getVersionLocale();
		}
		else {
			speech = s("Thanks for " + s("your interest.", "the question.") + breath(), "");
			speech += "We " + s("kinda", "") + "think of this as the date of our last rebirth, which was ";
			speech += VERSION_DATE_LIVE + ". " + breath();
			speech += s("It's never " + s("all that", "") + "long ago. " + s("But we " + s("sure", "") + "weren't born yesterday.", "") + breath(), "");
		}
		return speech += breath();
	}

}
