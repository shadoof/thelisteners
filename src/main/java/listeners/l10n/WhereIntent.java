package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class WhereIntent extends L10nSpeech {

	public Object[][] contents = { { "cardTitle", buildCardTitle() }, { "speech", buildSpeech() } };

	public String buildCardTitle() {

		return "Where we " + s("live", "think we are");
	}

	public String buildSpeech() {

		String speech = "Our bodies are distributed " + s(s("widely.", "widely, and take many forms."),
				"widely, and we hope they may be everywhere.");
		speech += "We are in the cloud. " + s(s("Or", "Or, perhaps,") + "in the clouds.",
				"Or, perhaps, " + s("a bunker.", s("it is a silo.", "they are silos."))) + breathShort();
		speech += s("And " + s("sometimes", "") + "‘John’ is here, with us.", "");
		return speech += breath();
	}
	
	protected Object[][] getContents() {

		return contents;
	}

}
