package listeners.l10n;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.Attributes.sessAttributes;
import static listeners.util.Utils.S;
import static listeners.util.Utils.breath;
import static listeners.util.Utils.breathLong;
import static listeners.util.Utils.r;
import static listeners.util.Utils.s;

public class DontLikeIntent_de_DE extends DontLikeIntent {

	public String buildCardTitle() {

		return S("Du magst uns nicht?", "Liebst du uns nicht?");
	}

	public String buildSpeech() {

		String speech;
		String affect = r("der Hass`der Zorn`der Ekel");
		sessAttributes.put(AFFECT, affect);
		String adjective = s("bestürzt");
		if ("bestürzt ".equals(adjective)) {
			String intensifier = r("ein bisschen `etwas `sehr ");
			speech = "Wir sind " + s(adjective, intensifier + adjective);
		}
		else
			speech = "Es tut uns Leid ";
		speech += "dass Sie von %s " + r("erfüllt `besessen `überwältigt ") + "sind. ";
		speech = String.format(speech, affect);
		return speech + breath();
	}

}
