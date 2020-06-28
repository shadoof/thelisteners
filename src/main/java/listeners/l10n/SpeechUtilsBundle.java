package listeners.l10n;

import static listeners.model.LangConstants.AFFECTS_ARRAY;
import static listeners.model.LangConstants.FRAGMENTNAME_MAP;
import static listeners.util.ConstantUtils.S;
import static listeners.util.ConstantUtils.breath;
import static listeners.util.ConstantUtils.breathLong;
import static listeners.util.ConstantUtils.breathShort;
import static listeners.util.ConstantUtils.heads;
import static listeners.util.ConstantUtils.info;
import static listeners.util.ConstantUtils.phonemic;
import static listeners.util.ConstantUtils.randInt;
import static listeners.util.ConstantUtils.s;

import java.util.ListResourceBundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class SpeechUtilsBundle extends ListResourceBundle {

	// static String langTag = "en-US"; // TODO

	public SpeechUtilsBundle(Locale locale) {

		info("@SpeechUtils, locale: " + locale);
		final ResourceBundle rb = ResourceBundle.getBundle("listeners.l10n.SpeechUtilsBundle", locale);

	}

	public Object[][] contents = { { "chooseContinue", chooseContinue() } 
			};

	// *** langTag-dependent speech utilities ***

	public static String chooseContinue() {

		return chooseContinue(true);
	}

	public static String chooseContinue(boolean promptForAffect) {

		int upperLimit = promptForAffect ? 4 : 3;
		String reprompt = "";
		switch (randInt(0, upperLimit)) {
			case 0:
				switch (langTag) {
					case "de-DE":
						reprompt += s("Sollen wir " + s("weiter machen?", "fortfahren?"), s(s("Wollen Sie", "Möchten Sie") + "mehr hören?", s("Bitte sagen Sie «ja», um mehr zu hören.", "Mehr?")));
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						reprompt += s("Shall we " + s("go on?", "continue?"), s(s("Do you want to", "Would you like to") + "hear more?", s("Please say, 'yes' for more.", "More?")));
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						reprompt += s("Should we " + s("keep going?", "continue?"), s(s("Do you want to", "Would you like to") + "hear more?", s("Just say, 'yes' for more.", "More?")));
				}
				break;
			case 1:
				switch (langTag) {
					case "de-DE":
						reprompt += s(s("Gibt es noch mehr, was du hören " + s("willst?", "möchtest?"), s("Möchtest", "Willst") + "du noch mehr hören?"), "");
						reprompt += s("Wir hören zu. Immer.", "");
						reprompt += "Du kannst uns " + s("immer", "") + "bitten " + s("weiter zu machen.", "fortzufahren.");
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						reprompt += s("Is there more that you would " + s("like", "care") + "to " + s("hear?", "hear from us?"), "");
						reprompt += s("We are listening.", ""); // ALWAYCHANGE Always.
						reprompt += s("You may " + s("always", ""), "Just") + "ask us to " + s("go on.", "continue.");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						reprompt += s("Is there anything else " + s("you'd like", "you want") + "to " + s("hear?", "hear from us?"), "");
						reprompt += s("We're listening.", "");// ALWAYCHANGE always
						reprompt += s("You could " + s("always", ""), "Just") + "ask us to " + s("keep going.", "continue.");
				}
				break;
			case 2:
				switch (langTag) {
					case "de-DE":
						reprompt += "Du " + s("kannst", "darfst") + "uns bitten " + s("über", "") + s("alle folgenden Dinge:", "") + breathShort();
						reprompt += chooseSomeFragmentNames() + "zu sprechen. " + breath();
						reprompt += "Oder du kannst uns einfach " + s("sagen", "bitten") + s("weiter zu machen.", "fortzufahren.");
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						reprompt += "You " + s("may", "can") + "ask us to 'speak' " + s("about", "") + s("any of the following:", "") + breath();
						reprompt += chooseSomeFragmentNames() + breath();
						reprompt += "Or you " + s("may, simply,", "may") + s("ask", "tell") + "us to " + s("go on.", "continue.");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						reprompt += "You " + s("could", "can") + "ask us to talk about " + s("any of the following:", "") + breath();
						reprompt += chooseSomeFragmentNames() + breath();
						reprompt += "Or you " + s("could just", "may") + s("ask", "tell") + "us to " + s("keep going.", "continue.");
				}
				break;
			case 3:
				switch (langTag) {
					case "de-DE":
						reprompt += s("Würdest", "Möchtest") + "du uns vielleicht " + s("jetzt", "");
						reprompt += s("fragen,", "bitten,") + s("weiter zu machen?", "fortzufahren?");
						reprompt += s("Ihr hört uns zu, wie wir euch zuhören?", "");
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						reprompt += s(s("Perhaps, now,", "Perhaps") + "you would", "Would you");
						reprompt += "like to " + s(s("ask", "tell") + "us to", "") + s("'continue'?", "'go on'?");
						reprompt += s("Listening to us, as we listen to you?", "");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						reprompt += s(s("Maybe, now,", "Maybe") + "you'd", "Would you");
						reprompt += "like to " + s(s("ask", "tell") + "us to", "") + s("'continue'?", "'keep going'?");
						reprompt += s("Listening to us, as we listen to you?", "");
				}
				break;
			case 4:
				switch (langTag) {
					case "de-DE":
						if (heads()) {
							reprompt += "Sie " + s("dürfen", "können") + "uns immer wenn Sie wollen ";
							reprompt += "bitten, " + s("Ihre " + s("Gefühle", "Affekte") + s("in denen Sie leben", "") + "abzurufen. ", s("«Wie fühle ich mich?»", "«Was fühlen wir?»"));
						}
						else {
							reprompt += "Du  " + s("darfst", "kannst") + "uns immer wenn du willst ";
							reprompt += "sag uns " + s("deine Gefühle darüber.", s("deine Gefühle.", s("die Gefühle, die du hast.", "die Gefühle, von denen du besessen bist.")));
						}
						reprompt += breathShort() + "Oder du kannst uns " + s("einfach", "") + s("sagen", "bitten") + s("weiter zu machen.", "fortzufahren.");
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						reprompt += s("You " + s("may", "can") + "always", s("If you like, please", "Please") + s("do", ""));
						reprompt += s("ask " + s(s("us to 'recall' " + s("your feelings.", "the " + s("feelings", phonemic("a") + "ffect") + "within which you dwell."), "us, 'What am I feeling?'"), "what we are feeling."), "tell us " + s("your feelings about all this.", s("your feelings.", "the feelings that possess you.")));
						reprompt += breathShort() + "Or you " + s("may, simply,", "may") + s("ask", "tell") + "us to " + s("go on.", "continue.");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						reprompt += s("You " + s("could", "can") + "always", s("If you want to, please", "Please"));
						reprompt += s("ask " + s(s("us to 'recall' " + s("your feelings.", "the " + s("feelings", phonemic("a") + "ffect") + "within which you dwell."), "us, 'What am I feeling?'"), "what we are feeling."), "tell us " + s("your feelings about all this.", s("your feelings.", "the feelings that possess you.")));
						reprompt += breathShort() + "Or you " + s("could, we guess,", "could") + s("ask", "tell") + "us to " + s("go on.", "continue.");
				}
		}
		return reprompt;
	}

	public static String chooseSomeFragmentNames() {

		String s = "";
		ArrayList list = new ArrayList(FRAGMENTNAME_MAP.keySet());
		Collections.shuffle(list);
		int i;
		for (i = 0; i < 3; i++) {
			s += list.get(i) + ", ";
		}
		// TODO check for other non-English languages
		s += list.get(i) + (langTag.equals("de-DE") ? "" : ". ");
		return s;
	}

	public static String chooseSpeechAssistance() {

		String speech = "";

		switch (langTag) {
			case "de-DE":
				speech += "Du kannst uns einfach " + s("sagen", "bitten") + s("weiter zu machen.", "fortzufahren.") + breath();
				speech += "Sie " + s("können", "könnten");
				speech += chooseTellUsAbout();
				speech += s("diese", "Ihren") + "Gefühle ";
				speech += "indem Sie die Worte " + s("sagen, ", "the sprechen,");
				speech += "«Ich bin " + s("voller»", s("besessen", "überwältigt") + "von»") + "und dann einen der neun Affekte. " + breathShort();
				speech += s("Und", "Oder") + "Sie fragen " + s("uns,", "uns vielleicht sogar,") + "wie wir uns fühlen. " + breath();
				break;
			case "ja-JP":
				// break;
			case "en-CA":
				// break;
			case "en-IN":
				// break;
			case "en-GB":
				speech += "You " + s("may", "might") + "always " + s("simply", "") + s("ask", "tell");
				speech += "us to: 'Continue', or 'Go on'. " + breath();
				speech += "You may " + chooseTellUsAbout() + "your feelings, ";
				speech += "by " + s("saying", "speaking") + "the words: " + breath();
				speech += "'I am " + s(s("filled with,'", "overwhelmed by,'"), "feeling,'") + "and then one of the nine " + phonemic("a") + s("ffects, that is, a name for one of the nine feelings.", "ffects.") + breathLong();
				speech += s("And", "Or,") + "you " + s("may", "might") + "even ask us how we " + s(s("feel.", "are feeling."), "feel, ourselves.") + breath();
				break;
			case "en-AU":
				// break;
			default: // "en-US" etc.
				speech += "You " + s("can", "might") + "always " + s("ask", "tell");
				speech += "us to: 'Keep going', or 'Go on'. " + breath();
				speech += "You can " + chooseTellUsAbout() + "your feelings, ";
				speech += "by " + s("saying", "speaking") + "the words: " + breath();
				speech += "'I am " + s(s("filled with,'", "overwhelmed by,'"), "feeling,'") + "and then one of the nine " + phonemic("a") + s("ffects, that is, a name for one of the nine feelings.", "ffects.") + breathLong();
				speech += s("And", "Or,") + "you " + s("can", "could") + "ask us how we " + s(s("feel.", "are feeling."), "feel, ourselves.") + breath();
		}
		boolean heads = heads();
		if (heads) {
			switch (langTag) {
				case "de-DE":
					speech += s("Manche der Namen", "Die Namen") + "für die neuen Affekte, die wir " + s("hören", "erkennen") + "können, sind: " + breath();
					break;
				case "ja-JP":
					// break;
				case "en-CA":
					// break;
				case "en-IN":
					// break;
				case "en-GB":
					speech += S("Some of the n", "N") + "ames for the nine " + phonemic("a") + "ffects, that we can " + s("hear,", "recognize,") + "include: " + breath();
					break;
				case "en-AU":
					// break;
				default: // "en-US" etc.
					speech += S("Some of the n", "N") + "ames for the nine " + phonemic("a") + "ffects, that we can " + s("hear,", "recognize,") + "include: " + breath();
			}
			List list = (List) Arrays.asList(AFFECTS_ARRAY);
			Collections.shuffle(list);

			int limit = 5;
			for (int i = 0; i < limit; i++) {
				// TODO for non-English
				if (i != (limit - 1))
					speech += list.get(i) + ", " + breathShort();
				else
					speech += (langTag.equals("de-DE") ? "und " : "and ") + list.get(i) + ". ";
			}
			speech += breath();
		}

		if (!heads && heads()) {
			switch (langTag) {
				case "de-DE":
					speech += "Oder du " + s("kannst", "könntest") + "uns bitten, " + s("über " + s("Folgendes", "") + "zu sprechen:", s("Folgendes", "") + "zu sagen:") + breath();
					break;
				case "ja-JP":
					// break;
				case "en-CA":
					// break;
				case "en-IN":
					// break;
				case "en-GB":
					speech += "Or, you " + s("may", "could") + "ask us to " + s("'speak',", "'speak about',") + s("any of the following:", "") + breath();
					break;
				case "en-AU":
					// break;
				default: // "en-US" etc.
					speech += "Or, you " + s("can", "could") + "ask us to " + s("'speak',", "'talk about',") + s("any of these things:", "") + breath();
			}
			speech += chooseSomeFragmentNames() + ". ";
		}

		if (randInt(0, 8) == 0) {
			switch (langTag) {
				case "de-DE":
					speech += "Oder du könntest sagen «Der Winter kommt.» " + breath();
					break;
				case "ja-JP":
					// break;
				case "en-CA":
					// break;
				case "en-IN":
					// break;
				case "en-GB":
					speech += "Or, you might say, 'Winter is coming.' " + breath();
					break;
				case "en-AU":
					// break;
				default: // "en-US" etc.
					speech += "Or, you " + s("can", "could") + "say, 'Winter is coming.' " + breath();
			}
		}

		return speech;
	}

	public static String chooseTellUsAbout() {

		String phrase;
		switch (langTag) {
			case "de-DE":
				phrase = "erzähle uns von ";
				break;
			case "ja-JP":
				// break;
			case "en-CA":
				// break;
			case "en-IN":
				// break;
			case "en-GB":
				// drop through: no GB needed for this
			case "en-AU":
				// break;
			default: // "en-US" etc.
				phrase = "tell us about ";
		}
		switch (randInt(0, 2)) {
			case 0:
				switch (langTag) {
					case "de-DE":
						phrase = "informiere uns über ";
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						// drop through: no GB needed for this
					case "en-AU":
						// break;
					default: // "en-US" etc.
						phrase = "inform us of ";
				}
				break;
			case 1:
				switch (langTag) {
					case "de-DE":
						phrase = "Beschreibe ";
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						// drop through: no GB needed for this
					case "en-AU":
						// break;
					default: // "en-US" etc.
						phrase = "describe ";
				}
				break;
			case 2:
		}
		return phrase;
	}

	public static String chooseUnsureAboutAffect() {

		String reprompt = "";
		switch (langTag) {
			case "de-DE":
				reprompt += "Wir sind " + s("nicht sicher", "unsicher") + s("was Ihre Gefühle sind,", "welche Gefühle es sind, ") + "die Sie da " + s("haben.", "überwältigen.");
				reprompt += "Sie " + s("können", "könnten");
				reprompt += chooseTellUsAbout();
				reprompt += s("diese", "Ihren") + "Gefühle ";
				reprompt += "indem Sie die Worte " + s("sagen, ", "the sprechen,");
				reprompt += "«Ich bin " + s("voller»", s("besessen", "überwältigt") + "von»") + "und dann einen der neun Affekte. " + breathShort();
				reprompt += s(breathShort() + "Oder du kannst uns einfach " + s("sagen", "bitten") + s("weiter zu machen.", "fortzufahren."), "");
				break;
			case "ja-JP":
				// break;
			case "en-CA":
				// break;
			case "en-IN":
				// break;
			case "en-GB":
				reprompt += "We are " + s("unsure", "unclear") + "about " + s("any", "the") + "feelings " + s("that " + s("may", "") + "possess you.", "within which you " + s("may", "") + "dwell.");
				reprompt += "You " + s("may", "might");
				reprompt += chooseTellUsAbout();
				reprompt += s("these", "your") + "feelings, ";
				reprompt += "by " + s("saying, " + s("the words,", ""), "speaking the words,");
				reprompt += "'I am " + s("filled with,'", "overwhelmed by,'") + "and then one of the nine " + phonemic("a") + "ffects. " + breath();
				reprompt += s("Or, you " + s("may", "might") + s("also", "") + s("simply", "") + s("ask", "tell") + "us to: " + s("'Continue' or 'Go on'.", "'Continue'."), "");
				break;
			case "en-AU":
				// break;
			default: // "en-US" etc.
				reprompt = "We don't know " + s("your feelings.", "about the feelings that possess you.") + breath();
				reprompt += "You may " + chooseTellUsAbout() + "your feelings ";
				reprompt += "by " + s("saying,", "speaking,") + "the words, ";
				reprompt += s("'I am filled with,'", "'I am " + s("possessed", "overwhelmed") + "by,'");
				reprompt += "and then one of the nine " + phonemic("a") + "ffects. ";
				reprompt += s("Or, you can " + s("also", "") + s("simply", "") + s("ask", "tell") + "us to: " + s("'Continue'.", "'Continue' or 'Keep going'."), "");
		}
		return reprompt;
	}

	@Override
	protected Object[][] getContents() {

		return contents;
	}

}
