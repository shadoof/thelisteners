package listeners.l10n;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;

public class WhereIntent_de_DE extends WhereIntent {

	public String buildCardTitle() {

		return "Wo wir " + S("wohnen", "denken, dass wir sind");
	}

	public String buildSpeech() {

		String speech = "Unsere Körper sind " + r(r("weitgehend `weit ")
				+ "verteilt und haben viele Formen. `weit verbreitet und wir hoffen, dass sie überall sein können. ");
		speech += "Wir sind in der Wolke. " + s("Oder " + s("vielleicht") + "in den Wolken. Oder, vielleicht " 
				+ r("in einem Bunker. `" + r("das ist ein Silo. `da sind Silos. ")));
		speech += s(breathShort() + "Und " + s("manchmal") + "ist 'John' hier, bei uns.");
		return speech + breath();
	}

}
