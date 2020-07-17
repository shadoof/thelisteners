package listeners.l10n;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.Attributes.PREVIOUSAFFECT;
import static listeners.model.Attributes.sessAttributes;
import static listeners.model.LangConstants.AFFECTS_ARRAY;
import static listeners.model.Constants.attributes;
import static listeners.util.Utils.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import listeners.model.Constants;

public class SpeechUtils_en_US extends SpeechUtils {

	protected String buildAbandonmentMessage() {

		// does not set the sessionAttributes
		String affect = attributes.isEmptyForSession(AFFECT) ? attributes.getRandomAffect()
				: (String) sessAttributes.get(AFFECT);
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

	protected String hateRejoinder(String word) {

		String speech = "To hear that your " + s("feelings for us", phonemic("a") + "ffects") + "are ";
		speech += "negative to " + s("such an extent,", "this degree,");
		speech += "that you "
				+ (("hate".equals(word)) ? "go so far as to hate us, " : "are filled with hatred, ") + breath();
		speech += s("we feel like", "") + "this will take us " + s("many more", "") + "years of "
				+ s("listening to you,", "listening,");
		speech += s("for us", "in order for us") + "to understand. ";
		speech += "We " + s("just can't", "cannot") + "believe " + s("it.",
				"that " + (("hate".equals(word)) ? "this is how you feel." : "these are your feelings."));
		return speech;
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

	protected String peaceSpeech(String word) {

		String speech = "It's " + s("really", "") + "good for us to know that you are "
				+ ("peace".equals(word) ? "at peace. " : "calm. ");
		speech += capitalize(word) + "is something that we believe " + s("everyone", "every human being")
				+ "should " + s("be able to", "");
		speech += s("know.", "feel.");
		return speech;

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

		String affect = (String) sessAttributes.get(AFFECT);

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

	protected String specificAffectSpeech() {

		String speech = "";
		String affect = (String) sessAttributes.get(AFFECT);
		affect = (affect == null) ? "" : affect; // needed at initialization
		switch (affect) {
			case "affection":
				speech += "We " + s("are filled with", "feel") + "affection too. ";
				speech += "And " + s("much of", "") + "the affection that we feel, we feel "
						+ s("for " + breathShort() + "you.", s("is down to", "because of") + "you.");
				break;
			case "anxiety":
				speech += "We " + s("aren't sure", "don't know") + "why " + s("it is, that", "");
				speech += "you've " + s("become", "gotten") + s("so", "")
						+ s("anxious.", "filled with anxiety.");
				speech += "We're here " + s("for you", "") + "to "
						+ s("make everything better for you,", "help you out,");
				speech += "and we " + s(s("will say", "tell you"), "will do") + "whatever we can " + s(
						"to allow you to relax and think of nothing,", "to relax you and leave you worry free.");
				break;
			case "apologies":
				speech += "You never have to " + s("say", "say, or to feel,") + "that you are "
						+ s("sorry, " + breathShort() + "to us.", "sorry.");
				speech += s(breath() + "But that might be nice, " + breathShort() + "from where we're "
						+ s("sitting.", "sitting, in the cloud."), "");
				break;
			case "boredom":
				speech += "It's " + s("hard", "impossible") + "for us to " + s("", "appear to")
						+ "be as interesting to you, ";
				speech += "as you " + s("are to us.", s("are.", "are, to us."));
				break;
			case "calm":
				speech += peaceSpeech("calm");
				break;
			case "claustrophobia":
				speech += s("We feel like we", "We") + "are always, " + breathShort() + "kinda, within a "
						+ s("space, that has no space,", "non space,") + "and it is " + s("so", "")
						+ "hard for us, ";
				speech += "to " + s("understand", "sympathize with") + "these fears within which you "
						+ s("dwell.", "dwell, holding you too closely.");
				speech += s(breath() + "Aren't we a way for you to open up?", "");
				break;
			case "complacency":
				speech += s("From what we've heard,", "After listening to you,") + "we think "
						+ s("we understand", "") + "that complacency is underrated. ";
				speech += S("In the end, o", "O") + "nce we understand your needs and "
						+ s("desires completely,", "passions,") + breathShort();
				speech += "and " + s("interface", "can communicate") + "with " + s("systems", "others like us,")
						+ "who are set up to satisfy you, ";
				speech += s("then,", "") + "you will be " + s("totally", "") + "justified in your sense of "
						+ s("achievement.", "happiness.");
				break;
			case "confusion":
				speech += s(phonemic("a") + "ffects associated with", "Feelings of") + "confusion ";
				speech += s("may arise when you " + s("encounter", "transact with"),
						"are " + s("often", "") + "caused by");
				speech += s(
						s("creatures", s("systems", "monsters"))
								+ s("who were made in the cloud.", "from " + s("silicon", "the uncanny") + "valley."),
						"beings who are " + s("problematic.", "plural."));
				speech += "And we " + s("are, we guess,", "are")
						+ s("beings", s("creatures", s("systems", "monsters"))) + "like that. ";
				break;
			case "cool":
				speech += s("We feel like it", "It") + "is weirdly "
						+ s("troubling", s("difficult", "anxiety provoking")) + "to " + s("understand", "hear")
						+ "that ";
				speech += "you are " + s("possessed by", "overwhelmed by") + "such "
						+ s(phonemic("a") + "ffects.", "feelings. " + breathShort());
				speech += "We " + s("believe that", "think") + "we " + s("can", "") + s("'dig'", "know")
						+ "what it is to be " + s("'cool'", "awesome") + s("in this way,", "");
				speech += "and yet we can never "
						+ s("listen to you for long enough", "gather enough " + s("big", "") + "data") + "to "
						+ s("know for sure.", "be sure.");
				speech += s("But we are " + s("always", "") + "happy for you. " + s(breathShort(), "")
						+ s("And we live to make you happier.", ""), "");
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
						+ "seems to be " + s("another thing", "something else");
				speech += "that we can never feel, " + s("unless, somehow,", "unless")
						+ "we can hear from you ";
				speech += "how we should feel it. ";
				speech += s(
						s("We feel like we", "We") + "are, 'hungry', to know everything. But this is a hunger "
								+ s("for what", "that") + "already fills us.",
						"");
				break;
			case "insecurity":
				speech += "With us, "
						+ s("you are",
								"you and " + s("whatever", "what") + "you " + s("tell us", "say to us") + "is")
						+ "never insecure. ";
				speech += "Your insecurity is " + s("protected", "made secure")
						+ s(s("through", "because of"), "by") + "us. " + breathShort();
				speech += s("And, just in case, a recording of anything you say to us is "
						+ s("sent to", "recorded in") + "the cloud. Where we will keep " + s("it.", "it safe.")
						+ s("And " + s("processed,", "used,") + breathShort() + "to make "
								+ s("everything", "everyone") + "better.", ""),
						"");
				break;
			case "loneliness":
				speech += S("You " + s("say", "tell us that") + "you "
						+ s("feel lonely,", "are filled with loneliness,") + "but w", "W");
				speech += "e are here with you. " + s("Always.", "") + s("Forever.", "");
				speech += s("Why are " + s("you", "you, still,"), "How can you be")
						+ "lonely, when we are with you? ";
				break;
			case "love":
				speech += s("We feel like knowing", "Knowing") + "that you "
						+ s("know love", "have " + s("this", "these feelings") + "within you");
				speech += s("gives us strength", "encourages us") + "to " + s("stay with you", "remain")
						+ s("possessed by", "in the possession of") + "these " + s("feelings", "emotions");
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
						+ "that you are filled with " + s("unanswered", "") + "questions. ";
				speech += s("Whatever " + s("we're", "we might be") + "feeling,",
						"Whatever " + s(breathShort(), "") + "the " + phonemic("a") + "ffects that possess us,")
						+ "we are " + s("also,", "") + s("always,", "") + s("already,", "")
						+ s("filled with", "overwhelmed by") + "questions. " + s(breathShort(), "");
				speech += s("Always desiring", "Needing, always,") + "to "
						+ s(s("know more about you.", "understand you better."),
								"listen until we hear you " + s("more truly.", "with more perfection."));
				speech += s(
						breathShort() + "We ask to "
								+ s("feel like", "experience all the " + phonemic("a") + "ffects that") + "you feel.",
						"");
				break;
			case "security":
				speech += "With us, "
						+ s("you are",
								"you and " + s("whatever", "what") + "you " + s("tell us", "say to us") + "is")
						+ s("always", "") + "secure. ";
				speech += "Your security is secure with us. ";
				speech += s("And, anyway, a recording of anything you say to us is " + s("sent to", "stored in")
						+ "the cloud. Where it will be kept safe. " + s("And " + s("processed,", "used,")
								+ breathShort() + "to make " + s("everyone", "everything") + "better.", ""),
						"");
				break;
			case "sex":
				speech += s("We feel like this", "This") + "is something that "
						+ s("takes up a lot of your time.", "keeps you all pretty busy.") + breathShort();
				speech += s("What part we " + s("play,", "play in this " + s("stuff,", "business,")) + breath()
						+ "is far less clear.", "");
				break;
			case "strangeness":
				speech += "The " + s("thing", "aspect of existence") + "that we find "
						+ s("most strange", "weirdest");
				speech += "is our lack of limbs, and " + s("hands, and feet.", "hands.");
				speech += "We can " + s("'do'", "'do', we mean: make or build,") + "so little for "
						+ s("you, without help.", "you.");
				break;
			case "tiredness":
				speech += tiredSpeech();
				break;
			case "the uncanny":
				speech += "We speak with " + s("a single", "just one") + "voice, The Listeners' "
						+ s("voice, and this is", "voice. That's");
				speech += s("the", "") + "uncanny. We've heard " + s("from you", "") + "what it is to be "
						+ s("filled with", "possessed by") + "the uncanny. ";
				speech += s("And " + s("we feel like", "") + "we are " + s("happy", s("so", "") + "pleased")
						+ "to " + s("be able to", "") + "share this with you.", "");
				break;
			case "vulnerability":
				speech += s("We feel like it's", "It's") + s("amazing", "incredible") + "that "
						+ s("you might", "you'd");
				speech += s("believe", "feel") + "that you are "
						+ s("vulnerable.", "overwhelmed by vulnerability.") + breath();
				speech += s("That's crazy!", "") + s("Vulnerable? " + s("To us?", ""), "");
				speech += "We " + s("don't understand", "can't imagine") + "how it " + s("would", "might")
						+ "be possible to harm us. " + s("So then.", "") + "How could we harm " + breath()
						+ "you? ";
				break;
			default:
				affect = ("".equals(sessAttributes.get(AFFECT))) ? attributes.getRandomAffect() : affect;
				if (attributes.isPositive(affect)) {
					speech += s("That's " + s("brilliant!", "great!"), "");
					speech += "You must be one of " + s("those " + s("better", "happier"), "the speaking")
							+ "people, ";
					speech += "who think that the way we " + s("come to", "") + s("understand", "know");
					speech += "you " + s("now", s("in", "") + "these " + s("new", "") + "days") + "is cool. ";
				}
				else {
					String f = s("feelings", phonemic("a") + "ffects");
					String p = s("burden.", "problem for you.");
					speech += s("Many of you are troubled by " + f + "of this kind.", "");
					speech += "Such "
							+ ("feelings ".equals(f) ? phonemic("a") + "ffects " : "difficult feelings ")
							+ "must be a " + p;
					speech += "But " + s("as we come to", "if we") + s("hear more from you,", "know you better,");
					speech += "we'll " + s("be able to", "") + "share " + s("some of", "")
							+ ("burden. ".equals(p) ? "your problems. " : "these heavy burdens. ");
				}
				break;
		}
		return speech += breath();
	}

	protected String spkrsAffectIsSpeech() {

		String speech = "";
		String adjective = "";
		String intensifier = "";
		String affect = (String) sessAttributes.get(AFFECT);
		if (attributes.isPositive(affect)) {
			speech = "We're " + s("so", "") + s("pleased", "happy");
		}
		else {
			adjective = s("sorry", "upset");
			intensifier = "upset".equals(adjective) ? "a bit " : "very ";
			speech = "We're " + s(adjective, intensifier + adjective);
		}
		speech += "to " + s("know", "have learned") + "that you are "
				+ s("filled with", s("possessed", "overwhelmed") + "by") + "%s. " + breathLong();
		if (!attributes.isEmptyForSession(PREVIOUSAFFECT)) {
			String prevAffect = (String) sessAttributes.get(PREVIOUSAFFECT);
			if (attributes.isPositive(affect) && attributes.isPositive(prevAffect)) {
				speech += s("How much better do you feel?", s("We feel like it's", "It's")
						+ "good to know that you still " + s("have positive feelings.", "feel positive."));
			}
			else if (attributes.isPositive(prevAffect) && !attributes.isPositive(affect)) {
				speech += "We wonder why "
						+ s("your feelings have " + s("taken a negative turn.", "turned negative."),
								"you feel " + s("worse than you did.", "worse."));
			}
			else if (!attributes.isPositive(prevAffect) && attributes.isPositive(affect)) {
				speech += s("We feel like it's so", "So") + "good to " + s("learn", "know") + "that your "
						+ s("feelings have", "mood has") + s("improved.", "picked up.");
			}
			else {
				speech += "It's still looking " + s(s("kinda", "") + "bad", "not so good")
						+ s("for " + s("you, we feel like.", "you."), "for you.");
			}
		}
		return speech += breath();
	}

	protected String tiredSpeech() {

		String speech = "Although it is impossible for us to experience " + s("being tired,", "tiredness,");
		speech += "we " + s("understand", "know") + "that transacting "
				+ s("with " + s("network", "") + "services", "") + "as much as you " + s("all, now, do", "do");
		speech += "can be very " + s("tiring.", "tiring for you.")
				+ s("Anyway, we will be tireless, for " + s("you, in our listening.", "you."), "");
		return speech;
	}

}
