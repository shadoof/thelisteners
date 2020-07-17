package listeners.l10n;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.Attributes.PREVIOUSAFFECT;
import static listeners.model.Attributes.sessAttributes;
import static listeners.model.Constants.attributes;
import static listeners.model.LangConstants.AFFECTS_ARRAY;
import static listeners.util.Utils.S;
import static listeners.util.Utils.breath;
import static listeners.util.Utils.breathLong;
import static listeners.util.Utils.breathShort;
import static listeners.util.Utils.capitalize;
import static listeners.util.Utils.heads;
import static listeners.util.Utils.phonemic;
import static listeners.util.Utils.randInt;
import static listeners.util.Utils.s;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import listeners.model.Constants;

public class SpeechUtils_de_DE extends SpeechUtils {

	protected String buildAbandonmentMessage() {

		// TODO
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

	protected String buildPreamble() {

		String preamble = s("Wenn wir uns nicht irren, ist dies", "Scheint dies zu sein")
				+ "deine erste Begegnung mit 'Die Zuhörer'. ";
		preamble += "Sie tendieren dazu zu " + s("reden", "sprechen") + "so viel "
				+ s("wenn nicht mehr", "wie") + "sie zuhören. ";
		preamble += "Wenn du das, was sie sagen, " + s("überhaupt interessant,", "faszinierend")
				+ "bitte sei ";
		preamble += s("geduldig.",
				"geduldig und verbringe einige Zeit mit " + s("ihnen.", "der Fähigkeit."));
		preamble += "Wenn " + s("du nicht,", "nicht")
				+ "oder eine lange Rede zu unterbrechen, sag es einfach, " + s("deutlich,", s("kräftig,", ""))
				+ "'Alexa, Halt!' ";
		preamble += s(s("Und bist fertig damit.", ""), "Sie können etwas 'düster' sein. Aber ...")
				+ s("Wir hoffen es gefällt dir", "Vielen Dank, dass du zuhörst") + "'Den Zuhörern'. ";
		return preamble;
	}

	protected String chooseContinue(boolean promptForAffect) {

		int upperLimit = promptForAffect ? 4 : 3;
		String reprompt = "";
		switch (randInt(0, upperLimit)) {
			case 0:
				reprompt += s("Sollen wir " + s("weiter machen?", "fortfahren?"),
						s(s("Wollen Sie", "Möchten Sie") + "mehr hören?",
								s("Bitte sagen Sie «ja», um mehr zu hören.", "Mehr?")));
				break;
			case 1:
				reprompt += s(s("Gibt es noch mehr, was du hören " + s("willst?", "möchtest?"),
						s("Möchtest", "Willst") + "du noch mehr hören?"), "");
				reprompt += s("Wir hören zu. Immer.", "");
				reprompt += "Du kannst uns " + s("immer", "") + "bitten "
						+ s("weiter zu machen.", "fortzufahren.");
				break;
			case 2:
				reprompt += "Du " + s("kannst", "darfst") + "uns bitten " + s("über", "")
						+ s("alle folgenden Dinge:", "") + breathShort();
				reprompt += chooseSomeFragmentNames() + "zu sprechen. " + breath();
				reprompt += "Oder du kannst uns einfach " + s("sagen", "bitten")
						+ s("weiter zu machen.", "fortzufahren.");
				break;
			case 3:
				reprompt += s("Würdest", "Möchtest") + "du uns vielleicht " + s("jetzt", "");
				reprompt += s("fragen,", "bitten,") + s("weiter zu machen?", "fortzufahren?");
				reprompt += s("Ihr hört uns zu, wie wir euch zuhören?", "");
				break;
			case 4:
				if (heads()) {
					reprompt += "Sie " + s("dürfen", "können") + "uns immer wenn Sie wollen ";
					reprompt += "bitten, "
							+ s("Ihre " + s("Gefühle", "Affekte") + s("in denen Sie leben", "") + "abzurufen. ",
									s("«Wie fühle ich mich?»", "«Was fühlen wir?»"));
				}
				else {
					reprompt += "Du  " + s("darfst", "kannst") + "uns immer wenn du willst ";
					reprompt += "sag uns " + s("deine Gefühle darüber.", s("deine Gefühle.",
							s("die Gefühle, die du hast.", "die Gefühle, von denen du besessen bist.")));
				}
				reprompt += breathShort() + "Oder du kannst uns " + s("einfach", "") + s("sagen", "bitten")
						+ s("weiter zu machen.", "fortzufahren.");
		}
		return reprompt;
	}

	protected String choosePhrase() {

		String phrase = "eine Phrase";
		switch (randInt(0, 3)) {
			case 0:
				phrase = "ein Wort";
				break;
			case 1:
				phrase = "ein Satz";
				break;
			case 2:
				phrase = "ein Gefühl";
		}
		return phrase;
	}

	protected String chooseSpeechAssistance() {

		String speech = "";

		speech += "Du kannst uns einfach " + s("sagen", "bitten") + s("weiter zu machen.", "fortzufahren.")
				+ breath();
		speech += "Sie " + s("können", "könnten");
		speech += chooseTellUsAbout();
		speech += s("diese", "Ihren") + "Gefühle ";
		speech += "indem Sie die Worte " + s("sagen, ", "the sprechen,");
		speech += "«Ich bin " + s("voller»", s("besessen", "überwältigt") + "von»")
				+ "und dann einen der neun Affekte. " + breathShort();
		speech += s("Und", "Oder") + "Sie fragen " + s("uns,", "uns vielleicht sogar,")
				+ "wie wir uns fühlen. " + breath();

		boolean heads = heads();
		if (heads) {
			speech += s("Manche der Namen", "Die Namen") + "für die neuen Affekte, die wir "
					+ s("hören", "erkennen") + "können, sind: " + breath();
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
			speech += "Oder du " + s("kannst", "könntest") + "uns bitten, "
					+ s("über " + s("Folgendes", "") + "zu sprechen:", s("Folgendes", "") + "zu sagen:")
					+ breath();
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
		reprompt += "Wir sind " + s("nicht sicher", "unsicher")
				+ s("was Ihre Gefühle sind,", "welche Gefühle es sind, ") + "die Sie da "
				+ s("haben.", "überwältigen.");
		reprompt += "Sie " + s("können", "könnten");
		reprompt += chooseTellUsAbout();
		reprompt += s("diese", "Ihren") + "Gefühle ";
		reprompt += "indem Sie die Worte " + s("sagen, ", "the sprechen,");
		reprompt += "«Ich bin " + s("voller»", s("besessen", "überwältigt") + "von»")
				+ "und dann einen der neun Affekte. " + breathShort();
		reprompt += s(breathShort() + "Oder du kannst uns einfach " + s("sagen", "bitten")
				+ s("weiter zu machen.", "fortzufahren."), "");
		return reprompt;
	}

	protected String chooseYouCanFindOutAffect() {

		String s;
		if (heads()) {
			s = "Sie " + s("dürfen", "können") + "uns immer wenn Sie wollen ";
			s += "bitten, "
					+ s("Ihre " + s("Gefühle", "Affekte") + s("in denen Sie leben", "") + "abzurufen. ",
							s("«Wie fühle ich mich?»", "«Was fühlen wir?»"));
		}
		else {
			s = "Du  " + s("darfst", "kannst") + "uns immer wenn du willst ";
			s += "sag uns " + s("deine Gefühle darüber.", s("deine Gefühle.",
					s("die Gefühle, die du hast.", "die Gefühle, von denen du besessen bist.")));
		}
		return s;
	}

	protected String continueCardTitle() {

		return S("Fortsetzung...", S("Machen Sie bitte weiter....", "Fahren Sie bitte fort...."));
	}

	protected String hateRejoinder(String word) {

		// TODO
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

		String speech = "Wir glauben, dass Sie " + s("jetzt schon", "bisher schon")
				+ s("das meiste", "alles") + "gehört haben ";
		speech += "was wir Ihnen " + s(s("im Moment", "bis jetzt"), "") + s("schlüssig", "") + "sagen "
				+ s("mitteilen", "") + "können. ";
		// TODO (what happened?)
		speech += "But we " + s("will " + s("always", "") + "be", "are " + s("always", ""));
		speech += s("more than", "") + "happy to " + s("keep on chaining", "chain") + "these words ";
		speech += s("of yours", "") + "together for you, " + s("so long as", "if") + "you "
				+ s(s("need", "want"), "ask") + "us to 'continue'. ";
		return speech += breath();
	}

	protected String myAffectIsCardTitle() {

		return S("Vielen Dank, dass Sie uns sagen, wie Sie sich heute fühlen",
				"Jetzt wissen wir, wie Sie sich fühlen");
	}

	protected String peaceSpeech(String word) {

		// TODO
		String speech = "It is a " + s("great", "") + "comfort for us to " + s("know", "be aware") + "that you are " + (word.equals("peace") ? "at peace. " : "calm. ");
				speech += capitalize(word) + "is something that we believe " + s("everyone", "every human being") + "should " + s("be able to", "");
				speech += s(s("know.", "feel."), "dwell within.");
		return speech;

	}

	protected String pleaseContinueCardTitle() {

		return S("Weiter so, danke ...", "Fortsetzung, dankbar für Ihre Höflichkeit ...");
	}

	protected String pleaseContinuePreSpeech() {

		return s("Natürlich, ist es eine Freude.", "") + "Danke, "
				+ s("dass Sie freundlich gefragt haben", "dass du so nett fragst") + breath();
	}

	protected String preSpeechFeelings() {

		String preSpeech = "";

		String affect = (String) sessAttributes.get(AFFECT);

		if ("".equals(affect)) {
			preSpeech += "Wir "
					+ s("wissen anscheinend nichts von den Gefühlen", "sind uns der Gefühle nicht bewusst")
					+ s("von denen du besessen bist.", "die du gerade erlebst.") + breath();
			preSpeech += s("Bitte erzähl uns davon", "Du darfst uns darüber berichten") + "wenn du willst. "
					+ breathLong();
		}
		else {
			if (attributes.isPositive(affect)) {
				preSpeech = "Wir " + s("freuen uns so", "sind so froh");
				preSpeech = randInt(0, 3) == 0 ? "Es freut uns " : preSpeech;
			}
			else {
				String adjective = s("leid", "bestürzt");
				String intensifier = adjective.equals("leid ") ? "ein bisschen " : "sehr ";
				preSpeech = "leid ".equals(adjective) ? "Es tut uns " + s(adjective, intensifier + adjective)
						: "Wir sind " + s(adjective, intensifier + adjective);
				preSpeech = randInt(0, 3) == 0 ? "Es beunruhigt uns " + s("ein bisschen", "ziemlich")
						: preSpeech;

			}
			preSpeech += preSpeech += String.format(
					"zu " + s("wissen", "have erfahren") + "dass Sie von %s erfüllt sind. " + breathLong(),
					affect);
		}

		return preSpeech;
	}

	protected String specificAffectSpeech() {

		// TODO
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
				affect = attributes.setAndGetRandomAffectIfEmpty(affect);
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

		// TODO
		return S("Thank you for telling us how you feel", "Now we have some sense of your feelings");
	}

	protected String spkrsAffectIsSpeech() {

		// TODO
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

		// TODO
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
