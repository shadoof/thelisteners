package listeners.l10n;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.LangConstants.AFFECTS_ARRAY;
import static listeners.model.Constants.attributes;
import static listeners.util.ConstantUtils.S;
import static listeners.util.ConstantUtils.breath;
import static listeners.util.ConstantUtils.breathLong;
import static listeners.util.ConstantUtils.breathShort;
import static listeners.util.ConstantUtils.heads;
import static listeners.util.ConstantUtils.phonemic;
import static listeners.util.ConstantUtils.randInt;
import static listeners.util.ConstantUtils.s;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import listeners.model.Constants;

public class SpeechUtilsBundle_en_US extends SpeechUtilsBundle {

	protected String chooseContinue(boolean promptForAffect) {

		int upperLimit = promptForAffect ? 4 : 3;
		String reprompt = "";
		switch (randInt(0, upperLimit)) {
			case 0:
				reprompt += s("Should we " + s("keep going?", "continue?"), s(s("Do you want to", "Would you like to") + "hear more?", s("Just say, 'yes' for more.", "More?")));
				break;
			case 1:
				reprompt += s("Is there anything else " + s("you'd like", "you want") + "to " + s("hear?", "hear from us?"), "");
				reprompt += s("We're listening.", "");// ALWAYCHANGE always
				reprompt += s("You could " + s("always", ""), "Just") + "ask us to " + s("keep going.", "continue.");
				break;
			case 2:
				reprompt += "You " + s("could", "can") + "ask us to talk about " + s("any of the following:", "") + breath();
				reprompt += chooseSomeFragmentNames() + breath();
				reprompt += "Or you " + s("could just", "may") + s("ask", "tell") + "us to " + s("keep going.", "continue.");
				break;
			case 3:
				reprompt += s(s("Maybe, now,", "Maybe") + "you'd", "Would you");
				reprompt += "like to " + s(s("ask", "tell") + "us to", "") + s("'continue'?", "'keep going'?");
				reprompt += s("Listening to us, as we listen to you?", "");
				break;
			case 4:
				reprompt += s("You " + s("could", "can") + "always", s("If you want to, please", "Please"));
				reprompt += s("ask " + s(s("us to 'recall' " + s("your feelings.", "the " + s("feelings", phonemic("a") + "ffect") + "within which you dwell."), "us, 'What am I feeling?'"), "what we are feeling."), "tell us " + s("your feelings about all this.", s("your feelings.", "the feelings that possess you.")));
				reprompt += breathShort() + "Or you " + s("could, we guess,", "could") + s("ask", "tell") + "us to " + s("go on.", "continue.");
		}
		return reprompt;
	}

	protected String chooseSpeechAssistance() {

		String speech = "";

		speech += "You " + s("can", "might") + "always " + s("ask", "tell");
		speech += "us to: 'Keep going', or 'Go on'. " + breath();
		speech += "You can " + chooseTellUsAbout() + "your feelings, ";
		speech += "by " + s("saying", "speaking") + "the words: " + breath();
		speech += "'I am " + s(s("filled with,'", "overwhelmed by,'"), "feeling,'") + "and then one of the nine " + phonemic("a") + s("ffects, that is, a name for one of the nine feelings.", "ffects.") + breathLong();
		speech += s("And", "Or,") + "you " + s("can", "could") + "ask us how we " + s(s("feel.", "are feeling."), "feel, ourselves.") + breath();

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
			speech += "Or, you " + s("can", "could") + "ask us to " + s("'speak',", "'talk about',") + s("any of these things:", "") + breath();
			speech += chooseSomeFragmentNames() + ". ";
		}

		if (randInt(0, 8) == 0) {
			speech += "Or, you " + s("can", "could") + "say, 'Winter is coming.' " + breath();
		}

		return speech;
	}

	protected String chooseUnsureAboutAffect() {

		String reprompt = "";
		reprompt = "We don't know " + s("your feelings.", "about the feelings that possess you.") + breath();
		reprompt += "You may " + chooseTellUsAbout() + "your feelings ";
		reprompt += "by " + s("saying,", "speaking,") + "the words, ";
		reprompt += s("'I am filled with,'", "'I am " + s("possessed", "overwhelmed") + "by,'");
		reprompt += "and then one of the nine " + phonemic("a") + "ffects. ";
		reprompt += s("Or, you can " + s("also", "") + s("simply", "") + s("ask", "tell") + "us to: " + s("'Continue'.", "'Continue' or 'Keep going'."), "");
		return reprompt;
	}
	
	protected String buildAbandonmentMessage() {

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
