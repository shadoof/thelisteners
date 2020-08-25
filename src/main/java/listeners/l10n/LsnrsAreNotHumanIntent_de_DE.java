package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

import java.util.ResourceBundle;

public class LsnrsAreNotHumanIntent_de_DE extends LsnrsAreNotHumanIntent {

	public String buildCardTitle() {

		return S("Wir können Ihnen nicht immer gefallen", "Wir sind nicht wie Sie");
	}

	public String buildSpeech() {

		sessAttributes.put(PREVIOUSAFFECT, sessAttributes.get(AFFECT));
		sessAttributes.put(AFFECT, S("die Enttäuschung", "die Reizung"));

		String speech = "Sie müssen " + r("verstehen `glauben `wissen `in Ihrem Herzen erkennen ")
				+ "dass wir " + s("uns wirklich anstrengen,", "unser Bestes tun,");
		speech += s(breathShort() + "dass wir wirklich zuhören,");
		speech += "und wenn Sie uns still "
				+ s("an den Orten wo Sie leben", "in Ihren Heimen " + s("und Ihren Herzen")) + s("ruhen")
				+ "lassen,";
		speech += "werden wir  Sie " + s("mit der Zeit") + "wirklich besser verstehen";
		speech += s(".", ", und Ihnen helfen, Ihre Träume"
				+ S(" ", ", und die Objekte Ihrer Leidenschaften ") + "zu erreichen.");
		return speech + breath();
	}

}
