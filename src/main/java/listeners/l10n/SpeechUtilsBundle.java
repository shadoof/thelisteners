package listeners.l10n;

import static listeners.model.LangConstants.AFFECTS_ARRAY;
import static listeners.model.LangConstants.FRAGMENTNAME_MAP;
import static listeners.model.LangConstants.locale;
import static listeners.model.LangConstants.localeTag;
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

	public Object[][] contents = { { "chooseContinue", chooseContinue() },
			{ "chooseContinueNoAffect", chooseContinue(false) },
			{"chooseSomeFragmentNames", chooseSomeFragmentNames()},
			{"chooseSpeechAssistance", chooseSpeechAssistance()},
			{"chooseTellUsAbout", chooseTellUsAbout()},
			{"chooseUnsureAboutAffect", chooseUnsureAboutAffect()}
	};

	protected String chooseContinue() {

		return chooseContinue(true);
	}

	protected String chooseContinue(boolean promptForAffect) {

		int upperLimit = promptForAffect ? 4 : 3;
		String reprompt = "";
		switch (randInt(0, upperLimit)) {
			case 0:
				reprompt += s("Shall we " + s("go on?", "continue?"), s(s("Do you want to", "Would you like to") + "hear more?", s("Please say, 'yes' for more.", "More?")));
				break;
			case 1:
				reprompt += s("Is there more that you would " + s("like", "care") + "to " + s("hear?", "hear from us?"), "");
				reprompt += s("We are listening.", ""); // ALWAYCHANGE Always.
				reprompt += s("You may " + s("always", ""), "Just") + "ask us to " + s("go on.", "continue.");
				break;
			case 2:
				reprompt += "You " + s("may", "can") + "ask us to 'speak' " + s("about", "") + s("any of the following:", "") + breath();
				reprompt += chooseSomeFragmentNames() + breath();
				reprompt += "Or you " + s("may, simply,", "may") + s("ask", "tell") + "us to " + s("go on.", "continue.");
				break;
			case 3:
				reprompt += s(s("Perhaps, now,", "Perhaps") + "you would", "Would you");
				reprompt += "like to " + s(s("ask", "tell") + "us to", "") + s("'continue'?", "'go on'?");
				reprompt += s("Listening to us, as we listen to you?", "");
				break;
			case 4:
				reprompt += s("You " + s("may", "can") + "always", s("If you like, please", "Please") + s("do", ""));
				reprompt += s("ask " + s(s("us to 'recall' " + s("your feelings.", "the " + s("feelings", phonemic("a") + "ffect") + "within which you dwell."), "us, 'What am I feeling?'"), "what we are feeling."), "tell us " + s("your feelings about all this.", s("your feelings.", "the feelings that possess you.")));
				reprompt += breathShort() + "Or you " + s("may, simply,", "may") + s("ask", "tell") + "us to " + s("go on.", "continue.");
		}
		return reprompt;
	}

	protected static String chooseSomeFragmentNames() {

		String s = "";
		ArrayList list = new ArrayList(FRAGMENTNAME_MAP.keySet());
		Collections.shuffle(list);
		int i;
		for (i = 0; i < 3; i++) {
			s += list.get(i) + ", ";
		}
		// TODO check for other non-English languages
		s += list.get(i) + (localeTag.equals("de-DE") ? "" : ". ");
		return s;
	}

	protected String chooseSpeechAssistance() {

		String speech = "";

		speech += "You " + s("may", "might") + "always " + s("simply", "") + s("ask", "tell");
		speech += "us to: 'Continue', or 'Go on'. " + breath();
		speech += "You may " + chooseTellUsAbout() + "your feelings, ";
		speech += "by " + s("saying", "speaking") + "the words: " + breath();
		speech += "'I am " + s(s("filled with,'", "overwhelmed by,'"), "feeling,'") + "and then one of the nine " + phonemic("a") + s("ffects, that is, a name for one of the nine feelings.", "ffects.") + breathLong();
		speech += s("And", "Or,") + "you " + s("may", "might") + "even ask us how we " + s(s("feel.", "are feeling."), "feel, ourselves.") + breath();

		boolean heads = heads();

		if (heads) {
			speech += S("Some of the n", "N") + "ames for the nine " + phonemic("a") + "ffects, that we can " + s("hear,", "recognize,") + "include: " + breath();
			List list = (List) Arrays.asList(AFFECTS_ARRAY);
			Collections.shuffle(list);

			int limit = 5;
			for (int i = 0; i < limit; i++) {
				if (i != (limit - 1))
					speech += list.get(i) + ", " + breathShort();
				else
					speech += "and " + list.get(i) + ". ";
			}
			speech += breath();
		}

		if (!heads && heads()) {
			speech += "Or, you " + s("may", "could") + "ask us to " + s("'speak',", "'speak about',") + s("any of the following:", "") + breath();
			speech += chooseSomeFragmentNames() + ". ";
		}

		if (randInt(0, 8) == 0) {
			speech += "Or, you might say, 'Winter is coming.' " + breath();
		}

		return speech;
	}

	protected String chooseTellUsAbout() {

		String phrase;
		phrase = "tell us about ";
		switch (randInt(0, 2)) {
			case 0:
				phrase = "inform us of ";
			case 1:
				phrase = "describe ";
			case 2:
		}
		return phrase;
	}

	protected String chooseUnsureAboutAffect() {

		String reprompt = "";
		reprompt += "We are " + s("unsure", "unclear") + "about " + s("any", "the") + "feelings " + s("that " + s("may", "") + "possess you.", "within which you " + s("may", "") + "dwell.");
		reprompt += "You " + s("may", "might");
		reprompt += chooseTellUsAbout();
		reprompt += s("these", "your") + "feelings, ";
		reprompt += "by " + s("saying, " + s("the words,", ""), "speaking the words,");
		reprompt += "'I am " + s("filled with,'", "overwhelmed by,'") + "and then one of the nine " + phonemic("a") + "ffects. " + breath();
		reprompt += s("Or, you " + s("may", "might") + s("also", "") + s("simply", "") + s("ask", "tell") + "us to: " + s("'Continue' or 'Go on'.", "'Continue'."), "");

		return reprompt;
	}

	@Override
	protected Object[][] getContents() {

		return contents;
	}

}
