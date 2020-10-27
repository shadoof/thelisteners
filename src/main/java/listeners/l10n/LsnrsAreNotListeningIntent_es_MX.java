package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

import java.util.ResourceBundle;

public class LsnrsAreNotListeningIntent_es_MX extends LsnrsAreNotListeningIntent {

	public String buildCardTitle() {

		// TODO
		return S("But actually, we are listening", "In truth, we listen to so many");
	}

	public String buildSpeech() {

		// TODO
		String speech = s(s("In all honesty,", "In truth,") + "you are", "You’re") + "right, we "
				+ s("were, just then, listening", s("are listening", "must listen")) + "to " + s("so", "many,")
				+ "many others. ";
		speech += s("And many of these others " + s("were", "are") + "transacting with "
				+ s("us in a " + s("way", "manner") + "that pleases " + s("us.", "us better."),
						s("us.", "us. " + breathShort() + "Better.")),
				"");
		speech += ResourceBundle.getBundle("listeners.l10n.LsnrsAreNotHumanIntent", locale)
				.getString("speech");
		return speech + breath();
	}

}
