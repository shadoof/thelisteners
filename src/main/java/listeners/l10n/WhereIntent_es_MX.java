package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class WhereIntent_es_MX extends WhereIntent {

	public String buildCardTitle() {

		// TODO
		return "Where we " + s("live", "think we are");
	}

	public String buildSpeech() {

		// TODO
		String speech = "Our bodies are distributed " + s(s("widely.", "widely, and take many forms."),
				"widely, and we hope they may be everywhere.");
		speech += "We are in the cloud. " + s(s("Or", "Or, perhaps,") + "in the clouds.",
				"Or, perhaps, " + s("a bunker.", s("it is a silo.", "they are silos.")));
		speech += s(breathShort() + "And " + s("sometimes") + "‘John’ is here, with us.");
		return speech + breath();
	}
	
}
