package listeners.l10n;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.Attributes.sessionAttributes;
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

	protected String buildAbandonmentMessage() {

		// does not set the sessionAttributes
		String affect = attributes.isEmptyForSession(AFFECT) ? attributes.getRandomAffect()
				: attributes.getAffect();
		String amsg;
		if (attributes.isPositive(affect))
			amsg = String.format("It's good to know that you dwell within %s. " + breathShort()
					+ s("Still,", "Whatever, " + breath()), affect);
		else
			amsg = String.format("We are sorry, " + s("finally,", "in the end,") + "to "
					+ s("know", "have become aware") + "that you are " + s("filled with", "possessed by") + "%s. "
					+ breathShort() + "And now, ", affect);
		return amsg += breathShort() + "you " + s("must", "") + "abandon us. " + breath();
	}

	protected String chooseContinue(boolean promptForAffect) {

		int upperLimit = promptForAffect ? 4 : 3;
		String reprompt = "";
		switch (randInt(0, upperLimit)) {
			case 0:
				reprompt += s("Should we " + s("keep going?", "continue?"),
						s(s("Do you want to", "Would you like to") + "hear more?",
								s("Just say, 'yes' for more.", "More?")));
				break;
			case 1:
				reprompt += s("Is there anything else " + s("you'd like", "you want") + "to "
						+ s("hear?", "hear from us?"), "");
				reprompt += s("We're listening.", "");// ALWAYCHANGE always
				reprompt += s("You could " + s("always", ""), "Just") + "ask us to "
						+ s("keep going.", "continue.");
				break;
			case 2:
				reprompt += "You " + s("could", "can") + "ask us to talk about "
						+ s("any of the following:", "") + breath();
				reprompt += chooseSomeFragmentNames() + breath();
				reprompt += "Or you " + s("could just", "may") + s("ask", "tell") + "us to "
						+ s("keep going.", "continue.");
				break;
			case 3:
				reprompt += s(s("Maybe, now,", "Maybe") + "you'd", "Would you");
				reprompt += "like to " + s(s("ask", "tell") + "us to", "") + s("'continue'?", "'keep going'?");
				reprompt += s("Listening to us, as we listen to you?", "");
				break;
			case 4:
				reprompt += s("You " + s("could", "can") + "always", s("If you want to, please", "Please"));
				reprompt += s(
						"ask " + s(s(
								"us to 'recall' " + s("your feelings.",
										"the " + s("feelings", phonemic("a") + "ffect") + "within which you dwell."),
								"us, 'What am I feeling?'"), "what we are feeling."),
						"tell us " + s("your feelings about all this.",
								s("your feelings.", "the feelings that possess you.")));
				reprompt += breathShort() + "Or you " + s("could, we guess,", "could") + s("ask", "tell")
						+ "us to " + s("go on.", "continue.");
		}
		return reprompt;
	}

	protected String chooseSpeechAssistance() {

		String speech = "";

		speech += "You " + s("can", "might") + "always " + s("ask", "tell");
		speech += "us to: 'Keep going', or 'Go on'. " + breath();
		speech += "You can " + chooseTellUsAbout() + "your feelings, ";
		speech += "by " + s("saying", "speaking") + "the words: " + breath();
		speech += "'I am " + s(s("filled with,'", "overwhelmed by,'"), "feeling,'")
				+ "and then one of the nine " + phonemic("a")
				+ s("ffects, that is, a name for one of the nine feelings.", "ffects.") + breathLong();
		speech += s("And", "Or,") + "you " + s("can", "could") + "ask us how we "
				+ s(s("feel.", "are feeling."), "feel, ourselves.") + breath();

		boolean heads = heads();
		if (heads) {
			speech += S("Some of the n", "N") + "ames for the nine " + phonemic("a") + "ffects, that we can "
					+ s("hear,", "recognize,") + "include: " + breath();
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
			speech += "Or, you " + s("can", "could") + "ask us to " + s("'speak',", "'talk about',")
					+ s("any of these things:", "") + breath();
			speech += chooseSomeFragmentNames() + ". ";
		}

		if (randInt(0, 8) == 0) {
			speech += "Or, you " + s("can", "could") + "say, 'Winter is coming.' " + breath();
		}

		return speech;
	}

	protected String chooseUnsureAboutAffect() {

		String reprompt = "";
		reprompt = "We don't know " + s("your feelings.", "about the feelings that possess you.")
				+ breath();
		reprompt += "You may " + chooseTellUsAbout() + "your feelings ";
		reprompt += "by " + s("saying,", "speaking,") + "the words, ";
		reprompt += s("'I am filled with,'", "'I am " + s("possessed", "overwhelmed") + "by,'");
		reprompt += "and then one of the nine " + phonemic("a") + "ffects. ";
		reprompt += s("Or, you can " + s("also", "") + s("simply", "") + s("ask", "tell") + "us to: "
				+ s("'Continue'.", "'Continue' or 'Keep going'."), "");
		return reprompt;
	}

	protected String chooseYouCanFindOutAffect() {

		String s = "You can  " + s("always", "") + s("ask us about", "find out from us what we know about");
		s += s(s("these", "the") + "feelings", "the " + phonemic("a") + "ffects")
				+ s("that you feel,", "which possess you,") + "by saying, 'What am I feeling?' ";
		return s;
	}

	protected String heardAllFragments() {

		String speech = "We're pretty sure " + s("that", "that, " + s("by", "") + "now,") + "you've heard ";
		speech += s("most", "all") + "of what we can " + s("tell you,", "say to you,");
		speech += s(s("at this time,", "for the time being,"), "");
		speech += s("coherently.", "");
		speech += "But we " + s("will " + s("always", "") + "be", "are " + s("always", ""));
		speech += s("more than", "") + "happy to keep on churning out these words ";
		speech += s("of yours", "") + s("so long as", "if") + "you " + s(s("need", "want"), "ask")
				+ "us to 'continue'. ";
		return speech += breath();
	}

	protected String pleaseContinueCardTitle() {

		return S("More to say, with thanks ...", "Continuing, grateful for your courtesy ...");
	}

	protected String pleaseContinuePreSpeech() {

		return s(S("Uh-huh, i", "I") + "t's a pleasure.", "") + s(
				"Thank you for " + s(s("asking to continue.", "asking."), "asking to continue, so nicely."),
				"Thank you for asking " + s("so nicely.", "with such courtesy.") + s("It's a pleasure.", ""))
				+ breath();
	}

	protected String preSpeechFeelings() {

		String preSpeech = "";

		String affect = (String) sessionAttributes.get(AFFECT);

		if ("".equals(affect)) {
			preSpeech += "We don't " + s("seem to", "") + "know "
					+ s("what feelings possess you.", "the feelings that you are experiencing.") + breath();
			preSpeech += s("Please", "Why don't you") + s("tell us,", "give us that information,")
					+ "if you wish. " + breathLong();
		}
		else {
			if (attributes.isPositive(affect)) {
				preSpeech = "We are " + s("so", "") + s("pleased", "happy");
				preSpeech = randInt(0, 3) == 0 ? "It's cool " : preSpeech;
			}
			else {
				String adjective = s("sorry", "upset");
				String intensifier = adjective.equals("upset ") ? "a bit " : "very ";
				preSpeech = "We are " + s(adjective, intensifier + adjective);
				preSpeech = randInt(0, 3) == 0 ? "It's " + s("quite", "a little") + "troubling for us "
						: preSpeech;

			}
			preSpeech += String
					.format("to " + s("know", "hear") + "that you are filled with %s. " + breathLong(), affect);
		}

		return preSpeech;
	}

}
