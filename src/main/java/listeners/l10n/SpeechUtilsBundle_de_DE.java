package listeners.l10n;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.Constants.attributes;
import static listeners.model.LangConstants.AFFECTS_ARRAY;
import static listeners.util.ConstantUtils.breath;
import static listeners.util.ConstantUtils.breathShort;
import static listeners.util.ConstantUtils.heads;
import static listeners.util.ConstantUtils.randInt;
import static listeners.util.ConstantUtils.s;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import listeners.model.Constants;

public class SpeechUtilsBundle_de_DE extends SpeechUtilsBundle {

	protected String chooseContinue(boolean promptForAffect) {

		int upperLimit = promptForAffect ? 4 : 3;
		String reprompt = "";
		switch (randInt(0, upperLimit)) {
			case 0:
				reprompt += s("Sollen wir " + s("weiter machen?", "fortfahren?"), s(s("Wollen Sie", "Möchten Sie") + "mehr hören?", s("Bitte sagen Sie «ja», um mehr zu hören.", "Mehr?")));
				break;
			case 1:
				reprompt += s(s("Gibt es noch mehr, was du hören " + s("willst?", "möchtest?"), s("Möchtest", "Willst") + "du noch mehr hören?"), "");
				reprompt += s("Wir hören zu. Immer.", "");
				reprompt += "Du kannst uns " + s("immer", "") + "bitten " + s("weiter zu machen.", "fortzufahren.");
				break;
			case 2:
				reprompt += "Du " + s("kannst", "darfst") + "uns bitten " + s("über", "") + s("alle folgenden Dinge:", "") + breathShort();
				reprompt += chooseSomeFragmentNames() + "zu sprechen. " + breath();
				reprompt += "Oder du kannst uns einfach " + s("sagen", "bitten") + s("weiter zu machen.", "fortzufahren.");
				break;
			case 3:
				reprompt += s("Würdest", "Möchtest") + "du uns vielleicht " + s("jetzt", "");
				reprompt += s("fragen,", "bitten,") + s("weiter zu machen?", "fortzufahren?");
				reprompt += s("Ihr hört uns zu, wie wir euch zuhören?", "");
				break;
			case 4:
				if (heads()) {
					reprompt += "Sie " + s("dürfen", "können") + "uns immer wenn Sie wollen ";
					reprompt += "bitten, " + s("Ihre " + s("Gefühle", "Affekte") + s("in denen Sie leben", "") + "abzurufen. ", s("«Wie fühle ich mich?»", "«Was fühlen wir?»"));
				}
				else {
					reprompt += "Du  " + s("darfst", "kannst") + "uns immer wenn du willst ";
					reprompt += "sag uns " + s("deine Gefühle darüber.", s("deine Gefühle.", s("die Gefühle, die du hast.", "die Gefühle, von denen du besessen bist.")));
				}
				reprompt += breathShort() + "Oder du kannst uns " + s("einfach", "") + s("sagen", "bitten") + s("weiter zu machen.", "fortzufahren.");
		}
		return reprompt;
	}

	protected String chooseSpeechAssistance() {

		String speech = "";

		speech += "Du kannst uns einfach " + s("sagen", "bitten") + s("weiter zu machen.", "fortzufahren.") + breath();
		speech += "Sie " + s("können", "könnten");
		speech += chooseTellUsAbout();
		speech += s("diese", "Ihren") + "Gefühle ";
		speech += "indem Sie die Worte " + s("sagen, ", "the sprechen,");
		speech += "«Ich bin " + s("voller»", s("besessen", "überwältigt") + "von»") + "und dann einen der neun Affekte. " + breathShort();
		speech += s("Und", "Oder") + "Sie fragen " + s("uns,", "uns vielleicht sogar,") + "wie wir uns fühlen. " + breath();

		boolean heads = heads();
		if (heads) {
			speech += s("Manche der Namen", "Die Namen") + "für die neuen Affekte, die wir " + s("hören", "erkennen") + "können, sind: " + breath();
			List list = (List) Arrays.asList(AFFECTS_ARRAY);
			Collections.shuffle(list);

			int limit = 5;
			for (int i = 0; i < limit; i++) {
				if (i != (limit - 1))
					speech += list.get(i) + ", " + breathShort();
				else
					speech += "und " + list.get(i) + ". ";
			}
			speech += breath();
		}

		if (!heads && heads()) {
			speech += "Oder du " + s("kannst", "könntest") + "uns bitten, " + s("über " + s("Folgendes", "") + "zu sprechen:", s("Folgendes", "") + "zu sagen:") + breath();
			speech += chooseSomeFragmentNames() + ". ";
		}

		if (randInt(0, 8) == 0) {
			speech += "Oder du könntest sagen «Der Winter kommt.» " + breath();
		}

		return speech;
	}

	protected String chooseTellUsAbout() {

		String phrase;
		phrase = "erzähle uns von ";
		switch (randInt(0, 2)) {
			case 0:
				phrase = "informiere uns über ";
				break;
			case 1:
				phrase = "Beschreibe ";
				break;
			case 2:
		}
		return phrase;
	}

	protected String chooseUnsureAboutAffect() {

		String reprompt = "";
		reprompt += "Wir sind " + s("nicht sicher", "unsicher") + s("was Ihre Gefühle sind,", "welche Gefühle es sind, ") + "die Sie da " + s("haben.", "überwältigen.");
		reprompt += "Sie " + s("können", "könnten");
		reprompt += chooseTellUsAbout();
		reprompt += s("diese", "Ihren") + "Gefühle ";
		reprompt += "indem Sie die Worte " + s("sagen, ", "the sprechen,");
		reprompt += "«Ich bin " + s("voller»", s("besessen", "überwältigt") + "von»") + "und dann einen der neun Affekte. " + breathShort();
		reprompt += s(breathShort() + "Oder du kannst uns einfach " + s("sagen", "bitten") + s("weiter zu machen.", "fortzufahren."), "");
		return reprompt;
	}
	
	protected String buildAbandonmentMessage() {
		// TODO German
		// does not set the sessionAttributes
		String affect = attributes.isEmptyForSession(AFFECT) ? attributes.getRandomAffect() : attributes.getAffect();
		String amsg;
		if (attributes.isPositive(affect))
			amsg = String.format("It's good to know that you dwell within %s. " + breathShort() + s("Still,", "Whatever, " + breath()), affect);
		else
			amsg = String.format("We are sorry, " + s("finally,", "in the end,") + "to " + s("know", "have become aware") + "that you are " + s("filled with", "possessed by") + "%s. " + breathShort() + "And now, ", affect);
		return amsg += breathShort() + "you " + s("must", "") + "abandon us. " + breath();
	}

}
