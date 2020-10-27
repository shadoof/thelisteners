package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

import java.util.ResourceBundle;

public class LsnrsAreNotHumanIntent_fr_FR extends LsnrsAreNotHumanIntent {

	public String buildCardTitle() {

		// TODO
		return S("We cannot always please you", "We are not the same are you are");
	}

	public String buildSpeech() {
		
		// TODO
		sessAttributes.put(PREVIOUSAFFECT, sessAttributes.get(AFFECT));
		sessAttributes.put(AFFECT, S("disappointment", "irritation"));

		String speech = "You must " + s("understand", s("believe", s("know", "know, at heart,")))
				+ "that we are " + s("trying so hard,", "doing our best,");
		speech += s(breathShort() + "that we really are listening");
		speech += "and that if you let us " + s(s("sit", "rest") + "quietly within", "into")
				+ s("the places where you dwell,", "your homes, and " + s("into") + "your hearts,");
		speech += "we " + s("will begin to", "will, in time,") + "understand you, "
				+ s("truly,", "better,");
		speech += s("and respond to you with " + s("greater") + "sensitivity,");
		speech += "and help you to achieve your "
				+ s("dreams.", "dreams, and " + s("the objects of your") + "passions.");
		return speech + breath();
	}

}
