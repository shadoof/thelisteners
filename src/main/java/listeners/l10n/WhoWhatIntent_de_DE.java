package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

// TODO
public class WhoWhatIntent_de_DE extends WhoWhatIntent {

	public String buildCardTitle() {

		return S("Questions, questions, questions",
				"We can’t know " + s("everything", "everything, where would we put it?"));
	}

	public String buildSpeech() {

		String speech = s("We are", "We are, so we believe,") + "The Listeners. " + breath();
		speech += s("You " + s("may") + "refer to us as: ‘The Listeners’. " + s("Or as ‘John’.")
				+ s(breath() + "We are used " + breathShortest() + "to that.") + breathShort());
		speech += "Whatever we " + s("may") + "say is " + s("far") + "less important to us, "
				+ s("or to you,") + "than the fact that we " + s(breathShort() + "listen. " + breathShort(),
						"are listening" + s(", " + breathShort(), SPC) + "to you.");
		// ALWAYCHANGE - s(breath() + "Always.") +
		speech += s(breath() + "Here and now.") + s(breath() + "Perhaps, forever.");
		return speech + breath();
	}

}
