package listeners.l10n;

import static listeners.model.Constants.DEV;
import static listeners.model.Constants.LSNRS_DATE;
import static listeners.util.Utils.breath;
import static listeners.util.Utils.s;

public class VersionIntent_en_US extends VersionIntent {

	public String buildSpeech() {

		String s;
		if (DEV) {
			s = getVersionLocale();
		}
		else {
			s = s("Thanks for " + s("your interest.", "the question.") + breath());
			s += "We " + s("kinda") + "think of this as the date of our last rebirth, which was ";
			s += LSNRS_DATE + ". " + breath();
			s += s("It’s never " + s("all that") + "long ago. "
					+ s("But we " + s("sure") + "weren’t born yesterday.") + breath());
		}
		return s += breath();
	}

}
