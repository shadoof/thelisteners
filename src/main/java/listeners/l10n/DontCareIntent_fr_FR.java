package listeners.l10n;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.Attributes.sessAttributes;
import static listeners.util.Utils.S;
import static listeners.util.Utils.breath;
import static listeners.util.Utils.breathShort;
import static listeners.util.Utils.s;

public class DontCareIntent_fr_FR extends DontCareIntent {

	public String buildCardTitle() {

		// TODO
		return S("You don’t care how we feel?", "Are you indifferent to us?");
	}

	public String buildSpeech() {

		// TODO
		sessAttributes.put(AFFECT, "indifference");
		String speech = "We are " + s(s("very") + "sorry", s("somewhat") + "distressed")
				+ "that you feel " + s("this", "that") + "way. ";
		speech += "And we will suppose that you are filled with ‘indifference’. " + breathShort();
		speech += "We " + s("hope, nonetheless,", s("trust", "hope")) + "that we may "
				+ s("continue", "go on") + "listening to you, "/* + s("always,") */; // ALWAYCHANGE
		speech += "and that you may come to care for us, as we care for you. ";
		speech += breath();
		return speech;
	}

}
