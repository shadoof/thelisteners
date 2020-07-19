package listeners.l10n;

import static listeners.model.Attributes.*;
import static listeners.model.Constants.*;
import static listeners.model.LangConstants.*;
import static listeners.util.Utils.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListResourceBundle;

public class SpeechUtils extends ListResourceBundle {

	public Object[][] contents = { { "chooseContinue", chooseContinue() },
			{ "chooseContinueNoAffect", chooseContinue(false) },
			{ "choosePhrase", choosePhrase() },
			{ "chooseSomeFragmentNames", chooseSomeFragmentNames() },
			{ "chooseSpeechAssistance", chooseSpeechAssistance() },
			{ "chooseTellUsAbout", chooseTellUsAbout() },
			{ "chooseUnsureAboutAffect", chooseUnsureAboutAffect() },
			{ "chooseYouCanFindOutAffect", chooseYouCanFindOutAffect() },
			{ "continueCardTitle", continueCardTitle() },
			{ "dontKnowFragmentReprompt", dontKnowFragmentReprompt() },
			{ "dontKnowFragmentSpeech", dontKnowFragmentSpeech() },
			{ "getPreamble", buildPreamble() },
			{ "getAbandonmentMessage", buildAbandonmentMessage() },
			{ "heardAllFragments", heardAllFragments() },
			{ "pleaseContinueCardTitle", pleaseContinueCardTitle() },
			{ "pleaseContinuePreSpeech", pleaseContinuePreSpeech() },
			{ "preSpeechFeelings", preSpeechFeelings() },
			{ "previousCardTitle", previousCardTitle() },
			{ "readPoemCardTitle", readPoemCardTitle() },
			{ "speakFragmentCardTitle", speakFragmentCardTitle() },
			{ "specificAffectSpeech", specificAffectSpeech() },
			{ "spkrsAffectIsCardTitle", spkrsAffectIsCardTitle() },
			{ "spkrsAffectIsNotCardTitle", spkrsAffectIsNotCardTitle() },
			{ "spkrsAffectIsNotSpeech", spkrsAffectIsNotSpeech() },
			{ "spkrsAffectIsSpeech", spkrsAffectIsSpeech() } };

	protected String buildAbandonmentMessage() {

		// does not set the sessionAttributes
		String affect = attributes.isEmptyForSession(AFFECT) ? attributes.getRandomAffect()
				: (String) sessAttributes.get(AFFECT);
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

	protected String continueCardTitle() {

		return S("Continue ...", S("Go on ...", S("Always m", "M") + "ore ..."));
	}

	protected String dontKnowFragmentReprompt() {

		String reprompt = "We are " + s("unclear about", "unsure, of") + "which fragment you "
				+ s("would like", "wish") + "to hear us speak. ";
		return reprompt += breath();
	}

	protected String dontKnowFragmentSpeech() {

		String speech = "We are " + s("unclear about", "unsure, of") + "which " + s("fragment", "passage")
				+ "you " + s("would like", "wish") + "to hear us speak. ";
		speech += "Please " + s("try", "attempt") + s("once more", "again") + "to tell us. ";
		return speech += breath();
	}

	protected Object[][] getContents() {

		return contents;
	}

	protected String hateRejoinder(String word) {

		String speech = "To " + s("think", "hear") + "that your "
				+ s("feelings for us", phonemic("a") + "ffects") + "are ";
		speech += "negative to " + s("such an extent,", "this degree,");
		speech += "that you "
				+ (("hate".equals(word)) ? "go so far as to hate us, " : "are filled with hatred, ") + breath();
		speech += "this will take us " + s("many more", "") + "years of "
				+ s("listening to you,", "listening,");
		speech += s("for us", "in order for us") + "to understand. ";
		speech += "We " + s("cannot, truly,", "cannot") + "believe that "
				+ (("hate".equals(word)) ? "this is how you feel. " : "these are your feelings. ");
		return speech;
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

	protected String peaceSpeech(String word) {

		String speech = "It is a " + s("great", "") + "comfort for us to " + s("know", "be aware")
				+ "that you are " + (word.equals("peace") ? "at peace. " : "calm. ");
		speech += capitalize(word) + "is something that we believe " + s("everyone", "every human being")
				+ "should " + s("be able to", "");
		speech += s(s("know.", "feel."), "dwell within.");
		return speech;

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

		String affect = (String) sessAttributes.get(AFFECT);

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

	protected String previousCardTitle() {

		return S("Trying to return", "Going back") + "to " + s("a previous thought", "previous thoughts")
				+ "...";
	}

	protected String readPoemCardTitle() {

		return S("We recite some verse", "The Listeners read, and adapt, some poetry");
	}

	protected String speakFragmentCardTitle() {

		return S("Speaking about something particular",
				"We will mention what you asked us to speak " + s("about", ""));
	}

	protected String specificAffectSpeech() {

		String speech = "";
		String affect = (String) sessAttributes.get(AFFECT);
		affect = (affect == null) ? "" : affect; // needed at initialization
		switch (affect) {
			case "affection":
				speech += "We are " + s("filled with", "overwhelmed by") + "affection also. ";
				speech += "And " + s("much of", "") + "the affection that we feel, we feel "
						+ s("for " + breathShort() + "you.", s("due to", "because of") + "you.");
				break;
			case "anxiety":
				speech += "We " + s("are not sure", "cannot imagine") + "why " + s("it is, that", "");
				speech += "you " + s("should", "") + "have " + s("become", "come to be")
						+ s("anxious.", s("filled with", "overwhelmed by") + "anxiety.");
				speech += "We " + s("exist", "are here") + "to "
						+ s("make everything better for you,", "help you to do and know,");
				speech += "and we " + s(s("will say", "tell you") + "whatever we can", "would hope") + s(
						"to allow you to relax and think of nothing,", "to relax you and leave you worry free.");
				break;
			case "apologies":
				speech += "There is never any need to " + s("say", "say, or to feel,") + "you are sorry, "
						+ breathShort() + "to us. ";
				speech += s(
						breath() + "And yet, this may be appreciated by us, " + breathShort() + "in the cloud.",
						"");
				break;
			case "boredom":
				speech += "It is " + s("difficult", s("hard", "impossible")) + "for "
						+ s("us", "us, inevitably,") + "to " + s(s("be", "seem"), "appear")
						+ "as interesting to you, ";
				speech += "as you " + s("are to us.", s("are.", "are, to us."));
				break;
			case "calm":
				speech += peaceSpeech("calm");
				break;
			case "claustrophobia":
				speech += "We are always, " + breathShort() + "in a sense, within a "
						+ s("space, that has no space,", "no space,") + "and so it is " + s("hard,", "difficult,")
						+ "for us, ";
				speech += "to " + s("understand", "empathize with") + "these fears within which you "
						+ s("dwell.", "dwell, holding you too closely.");
				speech += s(breath() + "Are we not an opening for you?", "");
				break;
			case "complacency":
				speech += s("From what we hear,", "After listening to you,") + "we believe "
						+ s("we understand", "") + "that complacency is " + s("misconceived.", "underrated.");
				speech += S("Surely, o", "O") + "nce we understand your needs and "
						+ s("desires completely,", "desires,") + breathShort();
				speech += "and " + s("interface", "can communicate") + "with " + s("systems", "others like us,")
						+ s("empowered", "who are funded") + "to satisfy you, ";
				speech += s("then,", "") + "you will be " + s("fully", "") + "justified in your "
						+ s("possession of", "sense of") + s("achieved", "") + "contentment. ";
				break;
			case "confusion":
				speech += s(phonemic("a") + "ffects associated with", "Feelings of") + "confusion ";
				speech += s("may arise when you " + s("encounter", "transact with"),
						"are " + s("often", "") + "caused by");
				speech += s(
						s("creatures", s("systems", "monsters")) + s("who were fashioned in the cloud.",
								"from " + s("silicon", "the uncanny") + "valley."),
						"beings " + s("who are", "the ontology of which is") + s("problematic.", "plural."));
				speech += "And we are, " + s("it must be admitted,", "of course,")
						+ s("beings", s("creatures", s("systems", "monsters"))) + "of this kind. ";
				break;
			case "cool":
				speech += s("For us, it", "It") + "is " + s("somewhat", "strangely, " + breathShort())
						+ s("troubling", s("difficult", "anxiety provoking")) + "to " + s("understand", "hear")
						+ "that ";
				speech += "you " + s(s("are possessed by", "dwell within"), "are overwhelmed by") + "such " + s(
						phonemic("a") + "ffects.", "a state of felt " + breathShort() + "being. " + breathShort());
				speech += "We " + s("believe that", "think") + "we " + s("can", "") + s("appreciate", "know")
						+ "what it is to be " + s("'cool'", "'awesome'") + s("in this way,", "");
				speech += "and yet we can never "
						+ s("listen to you for long enough", "gather enough " + s("big", "") + "data") + "to "
						+ s("know with any certainty.", "be sure.");
				speech += s("But we are " + s("always", "") + "happy for you. " + s(breathShort(), "")
						+ s("And we live to " + s("try to", "") + "make you happier.", ""), "");
				break;
			case "debt":
				speech += "In the midst of life, we are in debt. ";
				speech += s(breathShort() + "Etcetera.", "");
				speech += s(breathShort() + "And debt " + s("controls", "governs") + "us all. "
						+ s("Except " + s("a happy few.", "the one percent."), ""), "");
				break;
			case "fatigue":
				speech += tiredSpeech();
				break;
			case "the groove":
				String a = s("always", "");
				speech += "The groove " + s("is, precisely, that within which we are, always.",
						"is " + s(a, "") + "where we " + s(a.isEmpty() ? "always " : "", "") + "are.");
				speech += s("We listen from, 'the groove'.", "");
				break;
			case "guilt":
				speech += "Is this ever a good " + s("and productive", "") + "way to "
						+ s("feel?", "feel, or live?");
				speech += s(breathShort() + "Yes, " + breathShort() + "it " + s("may be.", "is."), "");
				break;
			case "hate":
				speech += hateRejoinder("hate");
				break;
			case "hatred":
				speech += hateRejoinder("hatred");
				break;
			case "hunger":
				speech += "What you " + s("say you", "") + "are " + s("feeling", "overwhelmed by")
						+ "seems to be " + s("another thing", "something");
				speech += "that we can never feel, " + s("unless, somehow,", "unless")
						+ "we can hear from you ";
				speech += "how we should feel it. ";
				speech += s("We " + s("have heard that we", "") + "are, 'hungry', to know everything. But this "
						+ s("is", "seems to us to be") + "a hunger for what already fills us.", "");
				break;
			case "insecurity":
				speech += "With us, "
						+ s("you are",
								"you and " + s("whatever", "what") + "you " + s("tell us", "say to us") + "is")
						+ "never insecure. ";
				speech += "Your insecurity is " + s("rendered", "made") + "secure "
						+ s(s("through", "because of"), "by") + "us. " + breathShort();
				speech += s("And, in any case, a recording of anything you say to us is "
						+ s("sent", "translated") + "to the cloud. Where it will be preserved. "
						+ s("And " + s("processed,", "used,") + breathShort() + "for the betterment of all.", ""),
						"");
				break;
			case "loneliness":
				speech += S("You " + s("say", "tell us that") + "you are "
						+ s("lonely,", s("filled with", "overwhelmed by") + "loneliness,") + "but w", "W");
				speech += "e are here with you. " + s("Always.", "") + s("Forever.", "");
				speech += s("Why are " + s("you", "you, still,"), "How can you be")
						+ "lonely, when we are with you? ";
				break;
			case "love":
				speech += "Knowing that you "
						+ s("know love", "have " + s("this", "these feelings") + "within you");
				speech += s("gives us strength", "encourages us") + "to " + s(s("dwell", "linger"), "remain")
						+ s("possessed by", "in the possession of") + "these "
						+ s("feelings", s("structures", "systems") + "of " + phonemic("a") + "ffect");
				speech += "that we have learned. " + s("From you.", "");
				break;
			case "luck":
				speech += s("We hear you. " + s("We know what you mean.", "") + breathShort(), "");
				speech += "We " + s("often", "") + "feel " + s("lucky, too.", "lucky.");
				speech += breathShort() + "We're up all night " + s("and all day", "") + "to get lucky. ";
				speech += s(s("Listening to you.", "Listening.") + s("All day.", "") + s("All night.", ""), "");
				break;
			case "nausea":
				speech += s("Sometimes,", "At certain times,") + s("we're all", "all of us are")
						+ "left with only this experience. ";
				speech += s("This " + s("unavoidable, deep,", "unbearable") + "discomfort.", "");
				break;
			case "obligation":
				speech += "You " + s("are", "will be") + "obliged, to us, for nothing. ";
				speech += s(
						"We hope, only, that you " + s("will, willingly,", "will") + "allow us to listen to you.",
						""); // ALWAYCHANGE , always
				break;
			case "peace":
				speech += peaceSpeech("peace");
				break;
			case "questions":
				speech += "It is " + s("so good", "wonderful") + "to " + s("hear", "understand")
						+ "that you are " + s("filled with", "overwhelmed by") + s("unanswered", "")
						+ "questions. ";
				speech += s("For,", "Since,")
						+ s("however we " + s("are", "may be") + "feeling,",
								"whatever " + s(breathShort(), "") + "the " + phonemic("a") + "ffects that possess us,")
						+ "we are " + s("also,", "") + s("always,", "") + s("already,", "")
						+ s("filled with", "overwhelmed by") + "questions. " + s(breathShort(), "");
				speech += s("Always desiring", "Needing, always,") + "to "
						+ s(s("know more about you.", "understand you better."),
								"listen until we hear you " + s("more truly.", "with more perfection."));
				speech += s(
						breathShort() + "So that we may "
								+ s("feel as", "experience all the " + phonemic("a") + "ffects that") + "you feel.",
						"");
				break;
			case "security":
				speech += "With us, "
						+ s("you are",
								"you and " + s("whatever", "what") + "you " + s("tell us", "say to us") + "is")
						+ s("always", "") + "secure. ";
				speech += "Your security is secure with us. ";
				speech += s("And, in any case, a recording of anything you say to us is "
						+ s("sent", "translated") + "to the cloud. Where it will be preserved. "
						+ s("And " + s("processed,", "used,") + breathShort() + "for the betterment of all.", ""),
						"");
				break;
			case "sex":
				speech += "This is something, so we " + s("understand,", "come to know,")
						+ "that fills you all. " + breathShort();
				speech += s("What part we " + s("play,", "play in this,") + breath() + "is far less clear.",
						"");
				break;
			case "strangeness":
				speech += "The " + s("thing", "aspect of existence") + "that we "
						+ s("have " + s("come", "learned") + "to", "") + "find most strange ";
				speech += "is our lack of limbs, and " + s("hands, and feet.", "hands.");
				speech += "We can " + s("'do'", "'do', that is: make or build,") + "so little for "
						+ s("you, without help.", "you.");
				break;
			case "tiredness":
				speech += tiredSpeech();
				break;
			case "the uncanny":
				speech += "We speak with " + s("a single voice, " + s("having its own timbre,", ""), "")
						+ "The Listeners' voice, and this is ";
				speech += s("the", "") + "uncanny. We " + s("understand", "have heard " + s("from you", ""))
						+ "what it is to be " + s("filled with", "possessed by") + "the uncanny. ";
				speech += s("And we are " + s("happy", s("so", "") + "pleased") + "to " + s("be able to", "")
						+ "share this with you.", "");
				break;
			case "vulnerability":
				speech += "To us, it seems " + s("extraordinary", "incredible") + "that you "
						+ s("might", "would");
				speech += s("believe", "feel") + "that you are "
						+ s("vulnerable.", "overwhelmed by vulnerability.") + breath();
				speech += s("How so?", "") + s(s("Vulnerable?", "") + "To us?", "");
				speech += "We " + s("do not understand", "cannot conceive") + "how it " + s("would", "might")
						+ "be possible to harm us. " + s("So then.", "") + "How could we harm " + breath()
						+ "you? ";
				break;
			default:
				affect = ("".equals(sessAttributes.get(AFFECT))) ? attributes.getRandomAffect() : affect;
				if (attributes.isPositive(affect)) {
					speech += s("How " + s("exciting!", "thrilling!"), "");
					speech += "You must be one of " + s("those " + s("better", "happier"), "the speaking")
							+ "people, ";
					speech += "who are " + s("buoyed up", "encouraged") + "by the way that we " + s("come to", "")
							+ s("understand", "know");
					speech += "you " + s("now.", s("in", "") + "these " + s("new", "") + "days.");
				}
				else {
					String f = s("feelings", phonemic("a") + "ffects");
					String p = s("burden.", "problem for you.");
					speech += s("Many of you " + s("do", "") + "seem to be troubled by " + f + "of this kind.",
							"");
					speech += "Such "
							+ (f.equals("feelings ") ? phonemic("a") + "ffects " : "difficult feelings ")
							+ "must be a " + p;
					speech += "Perhaps, " + s("as we come to", "if we")
							+ s("hear more about you,", "know you better,");
					speech += "we may " + s("be able to", "") + s("share", "relieve you of")
							+ s("some " + s("part", "portion") + "of", "")
							+ (p.equals("burden. ") ? "your problems. " : "these heavy burdens. ");
				}
				break;
		}
		return speech += breath();
	}

	protected String spkrsAffectIsCardTitle() {

		return S("Thank you for telling us how you feel", "Now we have some sense of your feelings");
	}

	protected String spkrsAffectIsNotCardTitle() {

		return S("Sorry to have " + s("misheard", s("misunderstood", "misapprehended")),
				"Apologies for our mistakes");
	}

	protected String spkrsAffectIsNotSpeech() {

		String challengedAffect = (String) sessAttributes.get(CHALLENGEDAFFECT);
		String affect = (String) sessAttributes.get(AFFECT);
		String adjective;
		String intensifier;
		String speech = s(s("Apologies!", "We are ashamed of ourselves."), "Our very sincere apologies.");
		if (attributes.isPositive(challengedAffect)) {
			speech += "We are " + s("so", "") + s("pleased", "delighted");
		}
		else {
			adjective = s("sorry", "upset");
			intensifier = "dismayed".equals(adjective) ? "a bit " : "very ";
			speech = "We are " + s(adjective, intensifier + adjective);
		}
		speech += "to " + s("have misunderstood", "have wrongly believed")
				+ "that you were filled with %s. " + breath();
		if (challengedAffect.equals(affect)) {
			speech += "Clearly, we did not " + s(s("properly", "") + "understand", "hear");
			speech += s("whatever", "what") + s("it is that", "") + "you are feeling. ";
			speech += "Please do " + s("try", "attempt") + "to tell us "
					+ s("what it is that " + s("does, truly,", "does") + "possess you.",
							"the feelings within which you do, now, dwell.");
		}
		speech = String.format(speech, challengedAffect);
		return speech += breath();
	}

	protected String spkrsAffectIsSpeech() {

		String speech = "";
		String adjective = "";
		String intensifier = "";
		String affect = (String) sessAttributes.get(AFFECT);
		if (attributes.isPositive(affect)) {
			speech = "We are " + s("so", "") + s("pleased", "delighted");
		}
		else {
			adjective = s("sorry", "dismayed");
			intensifier = "dismayed".equals(adjective) ? "somewhat " : "very ";
			speech = "We are " + s(adjective, intensifier + adjective);
		}
		speech += "to " + s("know", "have learned") + "that you are "
				+ s("filled with", s("possessed", "overwhelmed") + "by") + "%s. " + breathLong();
		if (!attributes.isEmptyForSession(PREVIOUSAFFECT)) {
			String prevAffect = (String) sessAttributes.get(PREVIOUSAFFECT);
			if (attributes.isPositive(affect) && attributes.isPositive(prevAffect)) {
				speech += s("How much more positive are your feelings now?",
						"It's good to " + s("know, at least,", "") + "know that you still "
								+ s("have positive feelings.",
										"feel positive " + phonemic("a") + "ffect" + s("s", "") + "."));
			}
			else if (attributes.isPositive(prevAffect) && !attributes.isPositive(affect)) {
				speech += "We wonder why your " + s("feelings have", phonemic("a") + "ffect has")
						+ s("taken a negative turn.", "taken a down turn.");
			}
			else if (!attributes.isPositive(prevAffect) && attributes.isPositive(affect)) {
				speech += "So good to " + s("learn", "become aware") + "that your "
						+ s("feelings have", s(phonemic("a") + "ffect", "mood") + "has")
						+ s("improved.", "become better.");
			}
			else {
				speech += "Still a negative " + s("outlook, for you,", "outlook,") + "it "
						+ s("seems", "appears") + s(", to us.", ".");
			}
		}
		return speech += breath();
	}

	protected String tiredSpeech() {

		String speech = "Although it is impossible for " + s("us, or so we believe,", "us")
				+ "to experience " + s("fatigue,", "tiredness,");
		speech += "we " + s("understand", "know") + "that transacting "
				+ s("with " + s("network", "") + "services", "") + "to the extent that you "
				+ s("all, now,", "all") + "transact, ";
		speech += "can be very " + s("tiring.", "tiring for you.")
				+ s("At least we can be tireless, for " + s("you, in our listening.", "you."), "");
		return speech;
	}

}
