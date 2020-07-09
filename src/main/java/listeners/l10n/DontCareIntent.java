package listeners.l10n;

import static listeners.model.Constants.attributes;
import static listeners.util.ConstantUtils.S;
import static listeners.util.ConstantUtils.breath;
import static listeners.util.ConstantUtils.breathShort;
import static listeners.util.ConstantUtils.s;


public class DontCareIntent extends L10nSpeech {

	public Object[][] contents = { { "cardTitle", buildCardTitle() }, { "speech", buildSpeech() } };

	public String buildCardTitle() {

		return S("You don't care how we feel?", "Are you indifferent to us?");
	}

	public String buildSpeech() {

		attributes.setAffect("indifference");
		String speech = "We are " + s(s("very", "") + "sorry", s("somewhat", "") + "distressed") + "that you feel " + s("this", "that") + "way. ";
		speech += "And we will suppose that you are filled with 'indifference'. " + breathShort();
		speech += "We " + s("hope, nonetheless,", s("trust", "hope")) + "that we may " + s("continue", "go on")
				+ "listening to you, "/* + s("always,", "") */; // ALWAYCHANGE
		speech += "and that you may come to care for us, as we care for you. ";
		speech += breath();
		return speech;
	}

	protected Object[][] getContents() {

		return contents;
	}

}
