package listeners.l10n;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.Attributes.sessAttributes;
import static listeners.model.Constants.attributes;
import static listeners.util.Utils.S;
import static listeners.util.Utils.breath;
import static listeners.util.Utils.breathShort;
import static listeners.util.Utils.s;

public class DontKnowAffectIntent extends L10nSpeech {

	public Object[][] contents = { { "cardTitle", buildCardTitle() }, { "speech", buildSpeech() } };

	public String buildCardTitle() {

		return S("We don’t know", "We’re unsure about") + s("how you feel",
				"the feelings " + s("that " + s("possess", "overwhelm") + "you", "within which you dwell"));
	}

	public String buildSpeech() {

		String s = S("uncertainty", "insecurity");
		sessAttributes.put(AFFECT, s);
		String speech = s("We are " + s("somewhat", "") + "distressed to",
				"It is a " + s("little", "") + s("troubling", "disturbing") + "for us to")
				+ s("have become aware", "know") + "that you are unsure of how you feel. ";
		speech += s(breathShort() + "It "
				+ s("will be important for us to", "is of " + s("some", "") + "concern to us that we come to")
				+ "understand " + s("all", "") + "your feelings.", "");
		speech += breathShort() + "We will suppose that you are filled with ‘" + s + "’. " + breathShort();
		speech += "When you do " + s("come to understand", "know") + "how you " + s("may", "") + "feel, we "
				+ s("hope", "trust and believe") + "that you will " + s("choose to", "") + "tell us. "
				+ s("For all our sakes.", "");
		speech += breath();
		return speech;
	}

	protected Object[][] getContents() {

		return contents;
	}

}
