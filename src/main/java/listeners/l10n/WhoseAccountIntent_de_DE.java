package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class WhoseAccountIntent_de_DE extends WhoseAccountIntent {

	public String buildCardTitle() {

		return S("Our profile", "This " + s("little", "") + "account of ours");
	}

	public String buildSpeech() {

		String speech = "These conversations, " + s("and these transactions,", "")
				+ "take place within the scope of, " + (PERFORMANCE ? "‘John’s’ " : "your ") + "profile. "
				+ breath();
		speech += PERFORMANCE
				? s("If we are " + s(s("ever", "") + "part of an installation,", "gallery installed,")
						+ "then everything is sent, " + s("not only to the cloud, but", "") + "to our "
						+ s("app.", "app, as configured by our artist."), "")
				: "";
		return speech + breath();
	}

}
