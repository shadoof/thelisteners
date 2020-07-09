package listeners.l10n;

import static listeners.model.Constants.attributes;
import static listeners.util.ConstantUtils.S;
import static listeners.util.ConstantUtils.breath;
import static listeners.util.ConstantUtils.breathShort;
import static listeners.util.ConstantUtils.s;


public class DontCareIntent_en_US extends DontCareIntent {

	public String buildCardTitle() {

		return S("Don't you care how we feel?", "You're indifferent to us?");
	}

	public String buildSpeech() {

		attributes.setAffect("indifference");
		String speech = "We're " + s(s("very", "") + "sorry", s("a bit", "") + "upset") + "that you feel " + s("this", "that") + "way. ";
		speech += "And we " + s("feel like", "guess that") + "you are filled with 'indifference'. " + breathShort();
		speech += "We " + s("hope, nonetheless,", "hope") + "that we can " + s("keep", "go on")
				+ "listening to you, "/* + s("always,", "") */; // ALWAYCHANGE
		speech += "and that you will come to care about us, like we care about you. ";
		speech += breath();
		return speech;
	}

}
