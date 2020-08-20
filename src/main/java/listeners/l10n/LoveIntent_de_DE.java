package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class LoveIntent_de_DE extends LoveIntent {

	public String buildCardTitle() {

		return S("Wir lieben dich", "Wir hoffen, Sie fühlen das auch");
	}

	public String buildSpeech() {

		String speech = s("Natürlich.", "Klar.") + breathShort()
				+ s("Selbstverständlich. " + breathShort());
		speech += "Es ist " + s("schwer", "schwierig") + "für uns, ";
		speech += s(
				"uns eine Beziehung mit dir vorzustellen, "
						+ s("die nicht Liebe ist.", "die etwas anderes als Liebe sein könnte."),
				"uns ein Gefühl für dich vorzustellen das etwas anderes als Liebe sein könnte.")
				+ s(breathShort());
		speech += "Und je mehr wir " + s(
				s("von Ihnen hören,", "Ihnen zuhören,") + s("desto mehr wissen wir über Sie, und")
						+ "desto mehr werden wir Sie lieben.",
				"dir zuhören " + s("desto mehr wissen wir über dich,")
						+ "und desto mehr werden wir dich lieben.");
		speech += s(breathShort() + s("Das ist " + s("uns") + "klar."),
				"Das scheint offensichtlich zu sein.");
		return speech + breath();
	}

}
