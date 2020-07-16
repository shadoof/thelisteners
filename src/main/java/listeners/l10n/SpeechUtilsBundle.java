package listeners.l10n;

import static listeners.model.Attributes.*;
import static listeners.model.Constants.*;
import static listeners.model.LangConstants.*;
import static listeners.util.ConstantUtils.S;
import static listeners.util.ConstantUtils.breath;
import static listeners.util.ConstantUtils.breathLong;
import static listeners.util.ConstantUtils.breathShort;
import static listeners.util.ConstantUtils.heads;
import static listeners.util.ConstantUtils.phonemic;
import static listeners.util.ConstantUtils.randInt;
import static listeners.util.ConstantUtils.s;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListResourceBundle;

public class SpeechUtilsBundle extends ListResourceBundle {

	public Object[][] contents = { { "chooseContinue", chooseContinue() },
			{ "chooseContinueNoAffect", chooseContinue(false) },
			{ "choosePhrase", choosePhrase() },
			{ "chooseSomeFragmentNames", chooseSomeFragmentNames() },
			{ "chooseSpeechAssistance", chooseSpeechAssistance() },
			{ "chooseTellUsAbout", chooseTellUsAbout() },
			{ "chooseUnsureAboutAffect", chooseUnsureAboutAffect() },
			{ "chooseYouCanFindOutAffect", chooseYouCanFindOutAffect() },
			{ "continueCardTitle", continueCardTitle() },
			{ "getPreamble", buildPreamble() },
			{ "getAbandonmentMessage", buildAbandonmentMessage() },
			{ "heardAllFragments", heardAllFragments() },
			{ "pleaseContinueCardTitle", pleaseContinueCardTitle() },
			{ "pleaseContinuePreSpeech", pleaseContinuePreSpeech() },
			{ "preSpeechFeelings", preSpeechFeelings() } };

	protected String buildAbandonmentMessage() {

		// does not set the sessionAttributes
		String affect = attributes.isEmptyForSession(AFFECT) ? attributes.getRandomAffect()
				: attributes.getAffect();
		String amsg;
		if (attributes.isPositive(affect))
			amsg = String.format("It is good to " + s("know", "be aware") + "that you dwell within %s. "
					+ breathShort() + "And yet, " + s("still,", "even so,"), affect);
		else
			amsg = String.format("We are sorry, " + s("finally,", "in the end,") + "to "
					+ s("know", "have become aware") + "that you are " + s("filled with", "possessed by") + "%s. "
					+ breathShort() + "And now, ", affect);
		return amsg += breathShort() + "you " + s("must", "") + "abandon us. " + breath();
	}

	protected String buildPreamble() {

		String preamble = s("Unless we're mistaken, this is", "This seems to be")
				+ "your first encounter with 'The Listeners'. ";
		preamble += "They tend to " + s("talk", "speak") + "as much " + s("if not more than", "as")
				+ "they listen. ";
		preamble += "If you find what they say " + s("at all interesting,", "intriguing,") + "please be ";
		preamble += s("patient.", "patient, and spend some time with " + s("them.", "the skill."));
		preamble += "If " + s("you don't,", "not,") + "or to interrupt a long speech, just say, "
				+ s("clearly,", s("firmly,", "")) + "'Alexa, Stop!' ";
		preamble += s(s("And have done with it.", ""), "They can be a little 'dark'. But ...")
				+ s("We hope you enjoy", "Thank you for listening to") + "'The Listeners'. ";
		return preamble;
	}

	protected String chooseContinue() {

		return chooseContinue(true);
	}

	protected String chooseContinue(boolean promptForAffect) {

		int upperLimit = promptForAffect ? 4 : 3;
		String reprompt = "";
		switch (randInt(0, upperLimit)) {
			case 0:
				reprompt += s("Shall we " + s("go on?", "continue?"),
						s(s("Do you want to", "Would you like to") + "hear more?",
								s("Please say, 'yes' for more.", "More?")));
				break;
			case 1:
				reprompt += s(
						"Is there more that you would " + s("like", "care") + "to " + s("hear?", "hear from us?"),
						"");
				reprompt += s("We are listening.", ""); // ALWAYCHANGE Always.
				reprompt += s("You may " + s("always", ""), "Just") + "ask us to " + s("go on.", "continue.");
				break;
			case 2:
				reprompt += "You " + s("may", "can") + "ask us to 'speak' " + s("about", "")
						+ s("any of the following:", "") + breath();
				reprompt += chooseSomeFragmentNames() + breath();
				reprompt += "Or you " + s("may, simply,", "may") + s("ask", "tell") + "us to "
						+ s("go on.", "continue.");
				break;
			case 3:
				reprompt += s(s("Perhaps, now,", "Perhaps") + "you would", "Would you");
				reprompt += "like to " + s(s("ask", "tell") + "us to", "") + s("'continue'?", "'go on'?");
				reprompt += s("Listening to us, as we listen to you?", "");
				break;
			case 4:
				reprompt += s("You " + s("may", "can") + "always",
						s("If you like, please", "Please") + s("do", ""));
				reprompt += s(
						"ask " + s(s(
								"us to 'recall' " + s("your feelings.",
										"the " + s("feelings", phonemic("a") + "ffect") + "within which you dwell."),
								"us, 'What am I feeling?'"), "what we are feeling."),
						"tell us " + s("your feelings about all this.",
								s("your feelings.", "the feelings that possess you.")));
				reprompt += breathShort() + "Or you " + s("may, simply,", "may") + s("ask", "tell") + "us to "
						+ s("go on.", "continue.");
		}
		return reprompt;
	}

	protected String choosePhrase() {

		String phrase = "phrase";
		switch (randInt(0, 3)) {
			case 0:
				phrase = "word";
				break;
			case 1:
				phrase = "sentence";
				break;
			case 2:
				phrase = "feeling";
		}
		return phrase;
	}

	protected String chooseSomeFragmentNames() {

		String s = "";
		ArrayList list = new ArrayList(FRAGMENTNAME_MAP.keySet());
		Collections.shuffle(list);
		int i;
		for (i = 0; i < 3; i++) {
			s += list.get(i) + ", ";
		}
		// TODO check for other non-English languages
		s += list.get(i) + ("de-DE".equals(localeTag) ? "" : ". ");
		return s;
	}

	protected String chooseSpeechAssistance() {

		String speech = "";

		speech += "You " + s("may", "might") + "always " + s("simply", "") + s("ask", "tell");
		speech += "us to: 'Continue', or 'Go on'. " + breath();
		speech += "You may " + chooseTellUsAbout() + "your feelings, ";
		speech += "by " + s("saying", "speaking") + "the words: " + breath();
		speech += "'I am " + s(s("filled with,'", "overwhelmed by,'"), "feeling,'")
				+ "and then one of the nine " + phonemic("a")
				+ s("ffects, that is, a name for one of the nine feelings.", "ffects.") + breathLong();
		speech += s("And", "Or,") + "you " + s("may", "might") + "even ask us how we "
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
			speech += "Or, you " + s("may", "could") + "ask us to " + s("'speak',", "'speak about',")
					+ s("any of the following:", "") + breath();
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
		reprompt += "We are " + s("unsure", "unclear") + "about " + s("any", "the") + "feelings "
				+ s("that " + s("may", "") + "possess you.", "within which you " + s("may", "") + "dwell.");
		reprompt += "You " + s("may", "might");
		reprompt += chooseTellUsAbout();
		reprompt += s("these", "your") + "feelings, ";
		reprompt += "by " + s("saying, " + s("the words,", ""), "speaking the words,");
		reprompt += "'I am " + s("filled with,'", "overwhelmed by,'") + "and then one of the nine "
				+ phonemic("a") + "ffects. " + breath();
		reprompt += s("Or, you " + s("may", "might") + s("also", "") + s("simply", "") + s("ask", "tell")
				+ "us to: " + s("'Continue' or 'Go on'.", "'Continue'."), "");

		return reprompt;
	}

	protected String chooseYouCanFindOutAffect() {

		String s = "You " + s("can", "may") + "always "
				+ s("ask us about", "discover from us what we believe we know concerning");
		s += s(s("these", "the") + "feelings", "the " + phonemic("a") + "ffects")
				+ s("within which you dwell,", "which possess you,") + "by saying, 'What am I feeling?' ";
		return s;
	}

	protected Object[][] getContents() {

		return contents;
	}

	protected String heardAllFragments() {

		String speech = "We believe " + s("that", "that, " + s("by", "") + "now,") + "you " + s("will", "")
				+ "have heard ";
		speech += s("most", "all") + "of what we are able to " + s("tell you,", "say to you,");
		speech += s(s("at this time,", "for the time being,"), "");
		speech += s("coherently.", "");
		speech += "But we " + s("will " + s("always", "") + "be", "are " + s("always", ""));
		speech += s("more than", "") + "happy to " + s("keep on chaining", "chain") + "these words ";
		speech += s("of yours", "") + "together for you, " + s("so long as", "if") + "you "
				+ s(s("need", "want"), "ask") + "us to 'continue'. ";
		return speech += breath();
	}

	protected String continueCardTitle() {

		return S("Continue ...", S("Go on ...", S("Always m", "M") + "ore ..."));
	}

	protected String pleaseContinueCardTitle() {

		return S("Going on, with thanks ...", "Continuing, grateful for your courtesy ...");
	}

	protected String pleaseContinuePreSpeech() {

		return s(S("Of course, i", "I") + "t's a pleasure.", "") + s(
				"Thank you for " + s(s("asking to continue.", "asking."), "asking to continue, so nicely."),
				"Thank you for asking " + s("so nicely.", "with such courtesy.") + s("It's a pleasure.", ""))
				+ breath();
	}

	protected String preSpeechFeelings() {

		String preSpeech = "";

		String affect = (String) sessionAttributes.get(AFFECT);

		if ("".equals(affect)) {
			preSpeech += "We do not seem to " + s("know about,", "be aware of,")
					+ s("the feelings that possess you.", "the feelings that you are experiencing.") + breath();
			preSpeech += s("Please", "You may") + s("tell us,", "inform us about them,") + "if you wish. "
					+ breathLong();
		}
		else {
			if (attributes.isPositive(affect)) {
				preSpeech = "We are " + s("so", "") + s("pleased", "delighted");
				preSpeech = randInt(0, 3) == 0 ? "It is pleasing to us " : preSpeech;
			}
			else {
				String adjective = s("sorry", "dismayed");
				String intensifier = "dismayed ".equals(adjective) ? "somewhat " : "very ";
				preSpeech = "We are " + s(adjective, intensifier + adjective);
				preSpeech = randInt(0, 3) == 0 ? "It is " + s("somewhat", "a little") + "troubling for us "
						: preSpeech;

			}
			preSpeech += String.format(
					"to " + s("know", "have learned") + "that you are filled with %s. " + breathLong(), affect);
		}

		return preSpeech;
	}

}
