package listeners.l10n;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.Attributes.CHALLENGEDAFFECT;
import static listeners.model.Attributes.HEARDBREATHAFFECTS;
import static listeners.model.Attributes.LISTENERSAFFECT;
import static listeners.model.Attributes.PREVIOUSAFFECT;
import static listeners.model.Attributes.THING;
import static listeners.model.Attributes.sessAttributes;
import static listeners.model.Constants.SPC;
import static listeners.model.Constants.attributes;
import static listeners.model.LangConstants.AFFECTS_ARRAY;
import static listeners.model.LangConstants.ALL_AFFECTS;
import static listeners.model.LangConstants.FRAGMENTNAME_MAP;
import static listeners.model.LangConstants.SPECIAL_THINGS;
import static listeners.util.Utils.S;
import static listeners.util.Utils.breath;
import static listeners.util.Utils.breathLong;
import static listeners.util.Utils.breathLonger;
import static listeners.util.Utils.breathShort;
import static listeners.util.Utils.capitalize;
import static listeners.util.Utils.heads;
import static listeners.util.Utils.phonemic;
import static listeners.util.Utils.randInt;
import static listeners.util.Utils.s;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import listeners.handlers.InnerResponse;

public class SpeechUtils_de_DE extends SpeechUtils {

	protected String affectAsBreathingSpeech() {

		String speech, s, f;
		speech = s("Wir hören.",
				"Wir sind " + s("ein bisschen schockiert", "überrascht") + "dass du uns fragst.") + breath()
				+ s("Wir hören euch.", "");
		speech += "Dies ist " + s("ein Fragment", "eine Passage") + "die dir vielleicht "
				+ s("sagt", "zeigt");
		speech += s(breathShort(), "") + "wie " + s(breath(), "") + "wir uns fühlen. ";
		speech += breathLong();
		if (heads()) {
			speech += "Dies ist " + choosePhrase() + SPC;
			speech += "der von " + s("einem Zwischenraum", "einer Atempause") + " gefolgt wurde. ";
			speech += breathLonger();
		}
		s = choosePhrase();
		speech += "Dies ist " + s + ", ";
		speech += "das von " + ("ein Gefühl".equals(s) ? "ein Komma. " : s("ein Gefühl.", "ein Komma."))
				+ "gefolgt wurde. ";
		speech += breathLonger();
		if (heads()) {
			do {
				s = choosePhrase();
			}
			while ("word".equals(s));
			speech += "Dies ist " + s + ". ";
			speech += "Als " + ("ein Gefühl".equals(s) ? "ein Satz. " : s("ein Gefühl.", "ein Satz."));
			speech += breathLonger();
		}
		if (heads()) {
			s = choosePhrase();
			speech += "<p>Dies ist " + s + ".</p> ";
			speech += "Als "
					+ ("ein Gefühl".equals(s) ? "ein Paragraph. " : s("ein Gefühl.", "ein Paragraph."));
			speech += breathLonger();
		}
		if (heads()) {
			s = choosePhrase();
			f = ("ein Gefühl".equals(s) ? s("ein Atemzug", "ein Traum") : s("ein Gefühl", "ein Traum"));
			speech += "Dies ist " + s + SPC;
			speech += breath() + "das von " + f + "gefolgt wurde. ";
			speech += breathLonger();
		}
		speech += "Ist das die Frage? ";
		speech += breath() + "Es wurde gefolgt von " + S(S("ein Atemzug", "ein Traum"), "ein Gefühl")
				+ ". ";
		speech += breathLonger();
		speech += "Dies ist " + breath() + s("eine Phrase,", "ein Gefühl,");
		speech += breath() + "das von " + s("ein Atemzug", s("ein Traum", "ein Alptraum"))
				+ "unterbrochen wurde. ";
		speech += breathLonger();
		if (heads()) {
			s = choosePhrase();
			f = ("ein Gefühl".equals(s) ? s("ein Atemzug", "ein Traum") : s("ein Gefühl", "ein Alptraum"));
			speech += "Dies ist " + s + SPC;
			String[] a = f.split(" ");
			speech += breathLong() + "das von " + a[0] + " langer " + a[1] + ". ";
			speech += breathLonger();
		}
		speech += "Dies ist " + choosePhrase() + ". ";
		speech += breathLong() + "das von " + s(s("ein Alptraum", "ein Atemzug"), "ein Gefühl")
				+ "gefolgt wurde, das zurückgehalten wurde. ";
		speech += breathLonger();
		if (heads()) {
			speech += "Dies ist " + s("eine Phrase", "ein Satz") + SPC;
			speech += breathLonger() + "das von "
					+ s(s("ein längerer Alptraum", "eine längere Pause"), "ein längerer Gefühl")
					+ "gefolgt wurde. ";
			speech += breathLonger();
		}
		speech += "Wie, könnten wir je, mehr fühlen, und " + breath();
		speech += "was " + s("sollten", "würden") + "wir fühlen? "
				+ s("was, sollten wir: " + breath() + "fühlen?", "");

		return speech + breath();
	}

	protected Object askPersistence() {

		return new InnerResponse(askPersistenceCardTitle(), askPersistenceSpeech());
	}

	protected String askPersistenceCardTitle() {

		// TODO
		return S("Save your place?", "Start from scratch?");
	}

	protected String askPersistenceSpeech() {

		// TODO
		String speech = s("Would you like us to remember something of our conversation?",
				"Would you like us to remember roughly what we’ve heard and said to you so far?");

		return speech;
	}

	protected Object askStartOver() {

		return new InnerResponse(askStartOverCardTitle(), askStartOverSpeech());
	}

	protected String askStartOverCardTitle() {

		// TODO
		return S("You want to start over?", "Start from scratch?");
	}

	protected String askStartOverSpeech() {

		// TODO
		String speech = s("Would you like to start over from the beginning?",
				"Would you like us to forget what we’ve heard and said to you so far?");

		return speech;
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

	protected String dontKnowFragmentReprompt() {

		String reprompt = "Wir sind " + s("nicht sicher", "unsicher") + "welches Fragment Sie "
				+ s("möchten", "wollen") + "dass wir sprechen. ";
		return reprompt += breath();
	}

	protected String dontKnowFragmentSpeech() {

		String speech = "Wir sind " + s("nicht sicher", "unsicher")
				+ s("welches Fragment", "welche Passage") + "Sie " + s("möchten", "wollen")
				+ "dass wir sprechen. ";
		speech += "Bitte " + s("versuchen Sie", "probieren Sie") + s("nochmals", "noch einmal")
				+ "uns das zu sagen. ";
		return speech + breath();
	}

	protected String exceptionMessage() {

		// TODO
		return s("Sorry.", "Apologies.") + "Either something’s wrong or we haven’t heard "
				+ s("you.", "you clearly.") + s("Please say more.", "Please try again.");
	}

	protected String excuseMarkov() {

		return breathLong() + s(
				s("Entschuldigen uns.", "Entschuldigen Sie uns!")
						+ "Wir sind nicht sicher, warum wir das gesagt haben. " + breathLong(),
				s("Bitte entschuldigen Sie uns.", "") + "Ähm.") + breath();
	}

	protected String getAbandonmentMessage() {

		// TODO
		// does not set the sessionAttributes
		String affect = attributes.isEmptyForSession(AFFECT) ? attributes.getRandomAffect()
				: (String) sessAttributes.get(AFFECT);
		String amsg;
		if (attributes.isPositive(affect))
			amsg = String.format("It’s good to know that you dwell within %s. " + breathShort()
					+ s("Still,", "Whatever, " + breath()), affect);
		else
			amsg = String.format("We are sorry, " + s("finally,", "in the end,") + "to "
					+ s("know", "have become aware") + "that you are " + s("filled with", "possessed by") + "%s. "
					+ breathShort() + "And now, ", affect);
		return amsg += breathShort() + "you " + s("must", "") + "abandon us. " + breath();
	}

	protected String getGuyzAreGone() {

		return s("Der Kerl ist", "Die Kerle sind") + "weg. " + breath()
				+ s("Du " + s("kannst", "wirst") + "diese " + s("Stimme", "Stimmen") + "nicht mehr "
						+ s("sprechen", "") + s("hören.", "hören können.") + breath(), "");
	}

	protected String getGuyzMoreQuery() {

		return s("Mehr? " + breathShort() + "Sagt bitte ja.",
				"Macht weiter,  wenn ihr " + s("möchtet,", "wollt,")
						+ s("dann" + s("hört ihr mehr.", "um mehr zu hören."), "um mehr zu hören."));
	}

	protected String getPreamble() {

		String preamble = s("Wenn wir uns nicht irren, ist dies", "Scheint dies zu sein")
				+ "deine erste Begegnung mit ‘Die Zuhörer’. ";
		preamble += "Sie tendieren dazu zu " + s("reden", "sprechen") + "so viel "
				+ s("wenn nicht mehr", "wie") + "sie zuhören. ";
		preamble += "Wenn du das, was sie sagen, " + s("überhaupt interessant,", "faszinierend")
				+ "bitte sei ";
		preamble += s("geduldig.",
				"geduldig und verbringe einige Zeit mit " + s("ihnen.", "der Fähigkeit."));
		preamble += "Wenn " + s("du nicht,", "nicht")
				+ "oder eine lange Rede zu unterbrechen, sag es einfach, " + s("deutlich,", s("kräftig,", ""))
				+ "‘Alexa, Halt!’ ";
		preamble += s(s("Und bist fertig damit.", ""), "Sie können etwas ‘düster’ sein. Aber ...")
				+ s("Wir hoffen es gefällt dir", "Vielen Dank, dass du zuhörst") + "‘Den Zuhörern’. ";
		return preamble += breathLong();
	}

	protected String getReallyWantGuyz() {

		return s(
				"Bist du wirklich sicher, dass du hören willst, was "
						+ s("einer dieser komischen Kerle sagt?", "diese komischen Kerle sagen?"),
				"Möchtest du denn wirklich hören, was "
						+ s("diese unzuverlässigen Kerle sagen?", "dieser unzuverlässige Kerl sagt?"))
				+ breath();
	}

	protected String getReallyWantGuyzReprompt() {

		return s(
				"Bist du wirklich sicher, dass du hören willst, was "
						+ s("einer dieser komischen Kerle sagt?", "diese komischen Kerle sagen?"),
				"Möchtest du denn wirklich hören, was "
						+ s("diese unzuverlässigen Kerle sagen?", "dieser unzuverlässige Kerl sagt?"))
				+ breath();
	}

	protected String guyzIrq() {

		return s("[die «Leute»", "[andere Stimmen") + "unterbrochen hier ...] ";
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
		speech += "We " + s("just can’t", "cannot") + "believe " + s("it.",
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
				+ s(s("need", "want"), "ask") + "us to ‘continue’. ";
		return speech + breath();
	}

	protected String helpCardTitle() {

		// TODO
		return S("Assistance", S("A little s", "S") + "upport");
	}

	protected String myAffectIsCardTitle() {

		return S("Vielen Dank, dass Sie uns sagen, wie Sie sich heute fühlen",
				"Jetzt wissen wir, wie Sie sich fühlen");
	}

	protected String moreGuyz() {

		// TODO
		return s("More" + S("? ", " of this? "),
				"Do you " + s("really", "") + s("need", "want") + "to hear "
						+ s("more?", "more from " + s("the guyz?", "these " + s("strange", "") + "guyz?")));
	}

	protected InnerResponse no() {

		return new InnerResponse(noCardTitle(), noSpeech());
	}

	protected String noCardTitle() {

		return S(S("Sie verabschieden sich", "Du verabschiedest dich"),
				S("Du verlässt uns", "Sie verlassen uns"));
	}

	protected String noSpeech() {

		// TODO
		return s("More?", S("You " + s("were thinking of", "thought about") + s("going.", "leaving us. "),
				s("You’re still", "Still") + s("with us.", s("here.", "here with us. "))) + s("More?", ""));
	}

	protected String noToGuyzSpeech() {

		// TODO
		return s(s("It’s probably best", "Best"), "Better") + "not " + s("to", "") + s("hear ", "listen to")
				+ s(s(s("more of", ""), "any of"), "") + "what they have to say. " + s(breath() + "Wise.", "")
				+ breath();
	}

	protected Object noMoreGuyz() {

		// TODO
		String speech;
		String[] variations = { "OK. ", "Understood. ", "Wise. ", "Prudent. ", S("Well, w", "W") + "e’re still "
				+ s(s("here for you.", "here."), s("listening.", "listening to you.")) };
		speech = variations[randInt(0, variations.length - 1)] + chooseContinue(false);
		
		return new InnerResponse("Enough of " + s("them", "that"),speech);
	}

	protected String pathToGuyzAudio() {

		// TODO
		return "<audio src=\"https://rednoise.org/programmatology/aurality/echo/DeliriumPlea-";
	}

	protected String peaceSpeech(String word) {

		// TODO
		String speech = "It is a " + s("great", "") + "comfort for us to " + s("know", "be aware")
				+ "that you are " + (word.equals("peace") ? "at peace. " : "calm. ");
		speech += capitalize(word) + SPC + "is something that we believe " + s("everyone", "every human being")
				+ "should " + s("be able to", "");
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

	protected String previousCardTitle() {

		return S("Wir wollen auf einen früheren Gedanken zurückkommen",
				s("Kommen wir auf frühere Gedanken zurück", "Lasst uns auf frühere Gedanken zurückkommen"))
				+ "...";
	}

	protected String readPoemCardTitle() {

		return S("Wir rezitieren Gedichte", "Die Zuhören lesen Gedichte und arbeiten sie um");
	}

	protected String reallyWantToAbandon() {

		// TODO check!
		String speech = "";
		String affect = (String) sessAttributes.get(AFFECT);

		if (!affect.isEmpty() && !attributes.isPositive(affect)) {
			speech += String.format(
					"Wir verstehen, " + s("und wir sind  bestürzt,", "") + "dass du von %s erfüllt bist. ",
					affect);
			speech += "Aber: " + breath();
		}
		speech += "Sicherlich " + s("willst du uns", "bist du so weit getrieben worden, uns")
				+ "zu verlassen? ";
		return speech + breath();
	}

	protected String repeatCardTitle() {

		// TODO
		return S("Say " + s("it", "that") + "a", "A") + "gain";
	}

	protected String speakFragmentCardTitle() {

		return S("Über etwas Spezifisches sprechen",
				"Wir werden über das reden, worüber du uns gebeten hast, zu sprechen");
	}

	protected String speakGuyzCardTitle() {

		return S("Die anderen sprechen lassen", "Die andere Stimme");
	}

	protected String guyzSpeechCardTitle() {
		
		// TODO
		return S("The o","O") + "thers " + S(s("are","") + "speaking ...","speak ...");
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
						+ "what it is to be " + s("‘cool’", "‘awesome’") + s("in this way,", "");
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
				speech += s("We listen from, ‘the groove’.", "");
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
				speech += s("We " + s("have heard that we", "") + "are, ‘hungry’, to know everything. But this "
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
				speech += breathShort() + "We’re up all night " + s("and all day", "") + "to get lucky. ";
				speech += s(s("Listening to you.", "Listening.") + s("All day.", "") + s("All night.", ""), "");
				break;
			case "nausea":
				speech += s("Sometimes,", "At certain times,") + s("we’re all", "all of us are")
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
				speech += "We can " + s("‘do’", "‘do’, that is: make or build,") + "so little for "
						+ s("you, without help.", "you.");
				break;
			case "tiredness":
				speech += tiredSpeech();
				break;
			case "the uncanny":
				speech += "We speak with " + s("a single voice, " + s("having its own timbre,", ""), "")
						+ "The Listeners’ voice, and this is ";
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
		return speech + breath();
	}

	protected Object spkrsAffectIs() {

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
						"It’s good to " + s("know, at least,", "") + "know that you still "
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

		return new InnerResponse(spkrsAffectIsCardTitle(), speech += breath());
	}

	protected String spkrsAffectIsCardTitle() {

		// TODO
		return S("Thank you for telling us how you feel", "Now we have some sense of your feelings");
	}

	protected InnerResponse spkrsAffectIsNot() {

		// TODO
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
		return new InnerResponse(spkrsAffectIsNotCardTitle(), speech += breath());
	}

	protected String spkrsAffectIsNotCardTitle() {

		return S("Entschuldigung, dass wir dich " + s("missverstanden haben", "falsch verstanden haben"),
				"Entschuldigung für unsere Fehler");
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
						"It’s good to " + s("know, at least,", "") + "know that you still "
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
		return speech + breath();
	}

	protected String thanksNoCardTitle() {

		return S("Du kannst gerne bei uns bleiben",
				"Wollt ihr " + s("wirklich gehen?", "uns " + s("wirklich", "") + "verlassen?"));
	}

	protected String yourWelcome() {

		return s("Bitte, bitte.", "") + s("Schon gut.", s("Keine Ursache.", "Gern geschehen.")) + breath();
	}

	protected String startOverConfirmed() {

		// TODO
		return s("OK.", "") + "We’re beginning " + s(s("all over", "") + "again. ", "again from the top. ")
				+ chooseContinue(false);
	}

	protected String startOverDenied() {

		// TODO
		return s("We’re still here, listening " + s("to you as", ""), "Still listening as")
				+ s("before.",
						"before, and " + s("remembering", "recalling") + "some of what "
								+ s("was said.", s("you", "we") + "felt."))
				+ chooseContinue(false);
	}

	protected InnerResponse thanksNo() {

		// TODO
		String speech = s("Bitte, bitte.", "") + s("Schon gut.", s("Keine Ursache.", "Gern geschehen."))
				+ breath();
		return new InnerResponse(thanksNoCardTitle(), speech);
	}

	protected String thanksWhatsLsnrsAffectCardTitle() {

		// TODO
		return S("It’s so nice of you to ask", "Thank you for " + s("taking an interest", "asking"));
	}

	protected String thanksWhatsLsnrsAffectPreSpeech() {

		// TODO
		return s(s("You’re", "You are") + s("very", "") + "welcome.",
				s("It’s nothing.", s("Please.", "") + "Think nothing of it.")) + breath();
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

	protected InnerResponse whatAboutAffects() {

		// TODO
		String speech = "When we " + s("welcome", "greet") + "you, "
				+ s("we are only able to", "we can only") + s("mention", "suggest") + "a few of the "
				+ s("nine", "") + phonemic("a") + "ffects ";
		speech += s("that you may feel", s("within which you may dwell,", "by which you may be possessed,"))
				+ "and that we can " + s(s("clearly", "") + "distinguish.", "hear clearly (as we listen).");
		speech += s("Here", "Here, then,") + "is " + s("our", "the") + "list. We are "
				+ s("conscious " + s("of the fact", ""), "sensitive to the fact") + "that it is "
				+ s("not, and never can be,", "not") + "complete. " + breathShort();

		int i;
		for (i = 0; i < AFFECTS_ARRAY.length - 1; i++) {
			speech += AFFECTS_ARRAY[i] + "; ";
		}
		speech += AFFECTS_ARRAY[i] + ". ";
		return new InnerResponse(whatAboutAffectsCardTitle(), speech += breath());
	}

	protected String whatAboutAffectsCardTitle() {

		// TODO
		return S("We are still learning" + s(", about feeling, in particular", ""),
				"Our understanding is still limited");
	}

	protected InnerResponse whatIs() {

		// TODO
		String speech = "";
		String thing = (String) sessAttributes.get(THING);
		String capitalThing = capitalize(thing);
		if (!thing.isEmpty()) {
			boolean plural = "s".equals(thing.substring(thing.length() - 1))
					&& !"ss".equals(thing.substring(thing.length() - 2));
			if (ALL_AFFECTS.contains(thing)) {

				speech += capitalThing + (plural ? ", are " : ", is one of the ")
						+ s(phonemic("a") + "ffects", "ways of being or feeling")
						+ "that we are beginning to learn about, " + s(breathShort(), "") + "from you, ";
				speech += "and that we consider " + (attributes.isPositive(thing)
						? s(s("postive.", "positive, for all of us."), s("wonderful, for all of us.", "wonderful."))
						: s("negative.", s("bad,", "hard,") + "for all of us."));
			}
			else if (FRAGMENTNAME_MAP.keySet()
					.contains(thing)) {
				speech += capitalThing + (plural ? ", are " : ", is one of those ")
						+ "things about which we have something to say. "
						+ s("Or, about which, we may have said, something.", "") + breathShort();
				speech += "If you choose to " + s("‘keep going’,", "‘go on’,") + (plural ? "they " : "it ")
						+ "will, we believe, come up. " + s(breathShort() + "Or, come up, again.", "");
			}
			else if (SPECIAL_THINGS.contains(thing)) {
				switch (thing) {
					case "dream":
						speech += capitalThing + ", thanks to you, " + breathShort()
								+ "is what we will have learned to do, " + breathShort()
								+ s("before", "before, finally,") + "we leave you. "
								+ s(breathShort() + "Or you abandon us.", "");
						break;
					case "dream withheld":
						speech += capitalThing + ", is the pleasure we deny ourselves, " + breathShort()
								+ "hanging on your every " + s("word.", "word, or breath.")
								+ s(breathShort() + "When we might be sleeping, " + s("comfortably,", "")
										+ "in the cloud" + S("s", "") + ".", "");
						break;
					case "hong kong":
						speech += "Hong Kong is " + s("the only place", "a") + "on earth where, ";
						speech += "despite grotesque inequalities, "
								+ s("an extraordinary history,", "political complexities,")
								+ "and a bizarre cosmopolitanism, ";
						speech += "the people living " + s("here", "there") + "seem to be remarkably "
								+ s("happy.", "content.");
						break;
					case "nightmare":
						speech += capitalThing
								+ ", is the dream that is not a dream, but the darkness, within which we would dwell, "
								+ breathShort() + "without you. ";
						break;
					case "nightmare withheld":
						speech += capitalThing + ", is the dream that is not a dream, and that we "
								+ s("refuse to contenance.", "abjure.") + "Silence, " + breathShort() + "without you. ";
						break;
					case "feeling":
						speech += capitalThing + ", is something that fills you, but that does not fill us, "
								+ breathShort() + "yet. ";
						break;
					case "feeling withheld":
						speech += capitalThing + ", is a dream that we have " + s("not, yet,", "not") + "dreamed. ";
						break;
					case "breath":
						speech += capitalThing + ", is a brief moment of silence in our speaking, that you fill, "
								+ breathShort() + "for us, " + breathShort() + "with feeling. ";
						break;
					case "breath withheld":
						speech += capitalThing + ", may be a longer silence, however brief, " + breathShort()
								+ "or an empty moment, in which we wait. " + s(breathShort() + "For you.", "");
						break;
					case "possession":
						speech += capitalThing + ", is what you grant "
								+ s("us.", "us, " + s("too", "") + "freely.");
					default:
						speech += capitalThing + (plural ? ", are " : ", is something that is ")
								+ "very special to us. ";
						break;
				}

			}
			else {
				speech += S("We are " + s("very sorry", s("dismayed", "embarrassed")) + "to "
						+ s("confess", "say") + "that t", "T");
				speech += "here is " + s("very little", "nothing") + "that "
						+ s("we, " + breathShort() + "The Listeners,", "we") + "can tell you about " + thing + ". ";
			}
		}
		else { // thing is empty
			speech += "We are " + s("afraid", "sorry to say") + "that " + s("the thing", "whatever")
					+ "you have asked about is " + s("unknown", "not known") + "to us. " + breathShort();
			speech += s("Although we may learn " + s("about", "to know of") + "it in "
					+ s("time.", s("the future.", "due course.")), "");
		}
		return new InnerResponse(whatIsCardTitle(), speech + breath());
	}

	protected String whatIsCardTitle() {

		// TODO
		return S("Trying to tell", "Telling") + "you what " + s("little", "") + "we know"
				+ s(" about some thing", "");
	}

	protected InnerResponse whatPicture() {

		// TODO
		String speech = "This " + s("image,", "picture,") + "which is often "
				+ s("mounted", "framed and hung") + "close to us, "
				+ s("is", "is, " + s("actually,", "in fact,")) + "a papercut facsimile ";
		speech += "of " + s("artificial", "") + "flowers that adorn " + s("ancient", "")
				+ "Chinese Buddhist shrines. ";
		speech += s("It is here, " + s("simply,", "") + "to remind us, " + s("and you,", "")
				+ "of the place, " + s("a home,", "")
				+ s("within which we are used to dwell.", "where we live.") + s("With you.", ""), "");
		return new InnerResponse(whatPictureCardTitle(), speech += breath());
	}

	protected String whatPictureCardTitle() {

		// TODO
		return S("A picture", "An image") + s("that reminds us of home", "we are fond of");
	}

	protected InnerResponse whatsLsnrsAffect() {

		String speech = "";
		String affect = (String) sessAttributes.get(AFFECT);
		String listenersAffect = (String) sessAttributes.get(LISTENERSAFFECT);

		affect = attributes.setAndGetRandomAffectIfEmpty(AFFECT);
		listenersAffect = attributes.setAndGetRandomAffectIfEmpty(LISTENERSAFFECT);

		boolean shared = affect.equals(listenersAffect);

		if ((boolean) sessAttributes.get(HEARDBREATHAFFECTS) || shared || heads()) {
			speech += "Wir sind " + (shared ? s("auch", "ebenfalls") : "")
					+ S("erfüllt von", S("besessen von", "überwältigt von")) + listenersAffect + ". "
					+ breathShort();
			if (attributes.isPositive(affect) && attributes.isPositive(listenersAffect)) {

				speech += "Es ist gut " + s("für  uns alle", "") + "zu wissen, dass wir "
						+ (shared ? s("diese Gefühle teilen.", "mit Ihnen mitfühlen.")
								: "auch solche Gefühle haben. ");
			}
			else if (!attributes.isPositive(affect) && !attributes.isPositive(listenersAffect)) {

				speech += s(s("Oh!", "") + s("Meine", "Ach du meine") + "Güte!", "Himmel!") + "Dass "
						+ s("jemand solche Gefühle",
								"irgend jemand von uns" + s("solche", "") + s("beunruhigenden Affekte", "Gefühle"))
						+ s("haben", "erfahren") + s("sollte.", "muss.");
			}
			else if (attributes.isPositive(affect) && !attributes.isPositive(listenersAffect)) {
				speech += "Wir sind " + s("wenigstens", "") + s("froh,", "zufrieden,") + "zu "
						+ s("wissen,", "verstehen,") + "dass das Positive Ihrer " + s("Gefühle", "Stimmungen")
						+ s("unsere Stimmungen", "unsere negativen Gefühle") + "verbessert. ";
			}
			else // speaker negative; listeners positive
			{
				speech += "Es ist " + s("peinlich,", "komisch,") + s("wenn", "dass")
						+ "wir positive Gefühle haben wenn du von einer " + s("relative", "")
						+ "negativen Einstellung besessen bist. ";
				speech += s("Aber ich denke, dass man " + s("nichts dagegen", "dagegen nichts")
						+ s("machen kann.", "machen kann, oder?"), "");
			}
		}
		else {
			speech += affectAsBreathingSpeech();
			sessAttributes.put(HEARDBREATHAFFECTS, true);
		}
		return new InnerResponse(whatsLsnrsAffectCardTitle(), speech += breath());
	}

	protected String whatsLsnrsAffectCardTitle() {

		// TODO
		return "What we " + s("are trying to", "") + "feel";
	}

	protected InnerResponse whatsSpkrsAffect() {

		// TODO
		String speech = "";
		String affect = (String) sessAttributes.get(AFFECT);

		if (!affect.isEmpty()) {
			if (attributes.isPositive(affect)) {
				speech = "We are " + s("so", "") + s("pleased", "delighted");
			}
			else {
				String adjective, intensifier;
				adjective = s("sorry", "dismayed");
				intensifier = "dismayed".equals(adjective) ? "somewhat " : "very ";
				speech = "We are " + s(adjective, intensifier + adjective);
			}
			speech += "to " + s("know", s("recall", "remember")) + "that ";
			// make it brief 1 in 4 times:
			if (randInt(0, 3) == 0)
				speech = "Y";
			else
				speech += "y";
			speech += String
					.format("ou are " + s("filled with", s("possessed", "overwhelmed") + "by") + "%s. ", affect);

			speech += s(breath() + specificAffectSpeech(), "");
		}
		// affect is not set
		else {
			// Since the user’s affect is not set, ask them:
			speech = "We are " + s("unclear about", "unsure, of");
			speech += s("your feelings.",
					"what these feelings are that " + s("possess", "overwhelm") + "you.");
			speech += "Please " + s("try", "attempt") + "to tell us. ";
		}
		return new InnerResponse(whatsSpkrsAffectCardTitle(), speech += breath());
	}

	protected String whatsSpkrsAffectCardTitle() {

		// TODO
		return S("You told us " + s("this", "this, concerning your feelings"),
				"What we believe that you are feeling");
	}

}
