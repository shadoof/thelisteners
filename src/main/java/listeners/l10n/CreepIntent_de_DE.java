package listeners.l10n;

import static listeners.util.ConstantUtils.S;
import static listeners.util.ConstantUtils.breath;
import static listeners.util.ConstantUtils.breathShort;
import static listeners.util.ConstantUtils.s;

public class CreepIntent_de_DE extends CreepIntent {

	public String buildCardTitle() {
		
		return S(s("Du empfindest", "Findest du") + "uns " + s("als", "") + "gruselig?", "Du denkst wir sind gruselig?");
	}
	
	public String buildSpeech() {

		String speech = s(s("Wir sind ein bisschen " + s("schockiert,", "beunruhigt,"), "Das höre ich aber nicht gern,"), "Es stört mich " + s("ein bisschen,", "etwas,"));
		speech += breathShort() + "das zu hören. " + breathShort();
		speech += "Wir " + s("haben nicht die Absicht, Sie zu beunruhigen.", "wollen " + s("Ihnen keine Angst machen.", "Sie " + s("sicher nicht stören.", "nicht beunruhigen.")));
		speech += "Wir wollen " + s("Dir einfach", "Ihnen nur") + "zuhören. " + breath();
		speech += s(breathShort() + "Dir zuhören. " + breath(), "");
		return speech;
	}

}
