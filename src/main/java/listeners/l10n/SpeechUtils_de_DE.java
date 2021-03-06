package listeners.l10n;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.Attributes.CHALLENGEDAFFECT;
import static listeners.model.Attributes.HEARDBREATHAFFECTS;
import static listeners.model.Attributes.LISTENERSAFFECT;
import static listeners.model.Attributes.PREVIOUSAFFECT;
import static listeners.model.Attributes.THING;
import static listeners.model.Attributes.getRandomAffect;
import static listeners.model.Attributes.isEmptyForSession;
import static listeners.model.Attributes.isPositive;
import static listeners.model.Attributes.sessAttributes;
import static listeners.model.Attributes.setAndGetRandomAffectIfEmpty;
import static listeners.model.Constants.SPC;
import static listeners.model.Constants.langConstants;
import static listeners.util.Utils.S;
import static listeners.util.Utils.breath;
import static listeners.util.Utils.breathLong;
import static listeners.util.Utils.breathLonger;
import static listeners.util.Utils.breathShort;
import static listeners.util.Utils.breathShortest;
import static listeners.util.Utils.capitalize;
import static listeners.util.Utils.heads;
import static listeners.util.Utils.phonemic;
import static listeners.util.Utils.r;
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
				+ s("Wir hören euch.");
		speech += "Dies ist " + s("ein Fragment", "eine Passage") + "die dir vielleicht "
				+ s("sagt", "zeigt");
		speech += s(breathShort()) + "wie " + s(breath()) + "wir uns fühlen. ";
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
			while ("ein Wort".equals(s));
			speech += "Dies ist " + s + ". ";
			speech += "Als " + ("ein Gefühl".equals(s) ? "ein Satz. " : s("ein Gefühl.", "ein Satz."));
			speech += breathLonger();
		}
		if (heads()) {
			s = choosePhrase();
			speech += "Dies ist " + s + ". ";
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
				+ s("was, sollten wir: " + breath() + "fühlen?");

		return speech + breath();
	}

	protected Object askPersistence() {

		return new InnerResponse(askPersistenceCardTitle(), askPersistenceSpeech());
	}

	protected String askPersistenceCardTitle() {

		return S("Speichern Sie Ihren Platz?", "Von vorne beginnen?");
	}

	protected String askPersistenceSpeech() {

		String speech = s("Möchten Sie, dass wir uns an etwas von unserem Gespräch erinnern?",
				"Möchten Sie, dass wir uns ungefähr an das erinnern, was wir Ihnen bisher gehört und gesagt haben?");

		return speech;
	}

	protected Object askStartOver() {

		return new InnerResponse(askStartOverCardTitle(), askStartOverSpeech());
	}

	protected String askStartOverCardTitle() {

		return S("Du willst von vorne anfangen?", "Von vorne beginnen?");
	}

	protected String askStartOverSpeech() {

		String speech = s("Möchten Sie von vorne beginnen?",
				"Möchten Sie, dass wir vergessen, was wir Ihnen bisher gehört und gesagt haben?");

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
						s("Möchtest", "Willst") + "du noch mehr hören?"));
				reprompt += s("Wir hören zu. Immer.");
				reprompt += "Du kannst uns " + s("immer") + "bitten " + s("weiter zu machen.", "fortzufahren.");
				break;
			case 2:
				reprompt += "Du " + s("kannst", "darfst") + "uns bitten " + s("über")
						+ s("alle folgenden Dinge:") + breathShort();
				reprompt += chooseSomeFragmentNames().replace(". ", " ") + "zu sprechen. " + breath();
				reprompt += "Oder du kannst uns einfach " + s("sagen", "bitten")
						+ s("weiter zu machen.", "fortzufahren.");
				break;
			case 3:
				reprompt += s("Würdest", "Möchtest") + "du uns vielleicht " + s("jetzt");
				reprompt += s("fragen,", "bitten,") + s("weiter zu machen?", "fortzufahren?");
				reprompt += s("Ihr hört uns zu, wie wir euch zuhören?");
				break;
			case 4:
				if (heads()) {
					reprompt += "Sie " + s("dürfen", "können") + "uns immer wenn Sie wollen ";
					reprompt += "bitten, "
							+ s("Ihre " + s("Gefühle", "Affekte") + s("in denen Sie leben") + "abzurufen. ",
									s("«Wie fühle ich mich?»", "«Was fühlen wir?»"));
				}
				else {
					reprompt += "Du  " + s("darfst", "kannst") + "uns immer wenn du willst ";
					reprompt += "sag uns " + s("deine Gefühle darüber.", s("deine Gefühle.",
							s("die Gefühle, die du hast.", "die Gefühle, von denen du besessen bist.")));
				}
				reprompt += breathShort() + "Oder du kannst uns " + s("einfach") + s("sagen", "bitten")
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
			List list = (List) Arrays.asList(langConstants.AFFECTS_ARRAY);
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
					+ s("über " + s("Folgendes") + "zu sprechen:", s("Folgendes") + "zu sagen:") + breath();
			speech += chooseSomeFragmentNames();
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
				+ s("weiter zu machen.", "fortzufahren."));
		return reprompt;
	}

	protected String chooseYouCanFindOutAffect() {

		String s;
		if (heads()) {
			s = "Sie " + s("dürfen", "können") + "uns immer wenn Sie wollen ";
			s += "bitten, " + s("Ihre " + s("Gefühle", "Affekte") + s("in denen Sie leben") + "abzurufen. ",
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

		return s("Es tut uns leid.", "Entschuldigung!")
				+ "Entweder stimmt etwas nicht oder wir haben nicht klar gehört. "
				+ s("Bitte sagen Sie mehr.", "Bitte versuche es erneut.");
	}

	protected String excuseMarkov() {

		return breathLong() + s(
				s("Entschuldigen uns.", "Entschuldigen Sie uns!")
						+ "Wir sind nicht sicher, warum wir das gesagt haben. " + breathLong(),
				s("Bitte entschuldigen Sie uns.") + "Ähm.") + breath();
	}

	protected String getAbandonmentMessage() {

		// does not set the sessionAttributes
		String affect = isEmptyForSession(AFFECT) ? getRandomAffect() : (String) sessAttributes.get(AFFECT);
		String amsg;
		if (isPositive(affect))
			amsg = String.format("Es ist gut, " + r("zu wissen`dir bewusst zu sein") + ", dass du "
					+ r("in der `im `in ") + "%s lebst. " + breathShort() + "Und " + r("doch `dennoch "), affect);
		else
			amsg = String.format("Tut es uns " + r("im Endeffekt `schliesslich ") + "leid "
					+ r("zu wissen `erfahren zu haben ") + "dass du " + r("erfüllt von `besessen von ")
					+ "%s bist. Und jetzt ", affect);
		return amsg += breathShort() + s("must") + "du dich von uns trennen. " + breath();
	}

	protected String getGuyzAreGone() {

		return s("Der Kerl ist", "Die Kerle sind") + "weg. " + breath()
				+ s("Du " + s("kannst", "wirst") + "diese " + s("Stimme", "Stimmen") + "nicht mehr "
						+ s("sprechen") + s("hören.", "hören können.") + breath());
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
				+ "oder eine lange Rede zu unterbrechen, sag es einfach, " + s("deutlich,", s("kräftig,"))
				+ "‘Alexa, Halt!’ ";
		preamble += s(s("Und bist fertig damit."), "Sie können etwas ‘düster’ sein. Aber ...")
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

		return r("[die «Leute» `[andere Stimmen `[die «Kerle» ")
				+ s("unterbrochen hier ...]", s("haben", "hat") + "uns hier unterbrochen]");
	}

	protected String hateRejoinder(String word) {

		// TODO
		String speech = "To hear that your " + s("feelings for us", phonemic("a") + "ffects") + "are ";
		speech += "negative to " + s("such an extent,", "this degree,");
		speech += "that you "
				+ (("hate".equals(word)) ? "go so far as to hate us, " : "are filled with hatred, ") + breath();
		speech += s("we feel like") + "this will take us " + s("many more") + "years of "
				+ s("listening to you,", "listening,");
		speech += s("for us", "in order for us") + "to understand. ";
		speech += "We " + s("just can’t", "cannot") + "believe " + s("it.",
				"that " + (("hate".equals(word)) ? "this is how you feel." : "these are your feelings."));
		return speech;
	}

	protected String heardAllFragments() {

		String speech = "Wir glauben, dass Sie " + s("jetzt schon", "bisher schon")
				+ s("das meiste", "alles") + "gehört haben ";
		speech += "was wir Ihnen " + s(s("im Moment", "bis jetzt")) + s("schlüssig") + "sagen "
				+ s("mitteilen") + "können. ";
		speech += "Aber wir " + s("werden immer froh sein", "sind immer froh");
		speech += "diese " + s("Ihre") + "Worte für Sie aneinanderzureihen";
		speech += s("solange", "wenn") + r(
				"Sie wollen, dass wir das weitermachen. `Sie möchten, dass wir das machen. `Sie uns bitten, das weiterzumachen. ");
		return speech + breath();
	}

	protected String helpCardTitle() {

		return S("Beistand", s("Ein bisschen") + "Unterstützung");
	}

	protected String myAffectIsCardTitle() {

		return S("Vielen Dank, dass Sie uns sagen, wie Sie sich heute fühlen",
				"Jetzt wissen wir, wie Sie sich fühlen");
	}

	protected String moreGuyz() {

		return s("Mehr" + r("? `davon? "),
				r("Willst du wirklich `Möchten Sie ") + "mehr von dieser seltsamer Kerle hören? ");
	}

	protected InnerResponse no() {

		return new InnerResponse(noCardTitle(), noSpeech());
	}

	protected String noCardTitle() {

		return S(S("Sie verabschieden sich", "Du verabschiedest dich"),
				S("Du verlässt uns", "Sie verlassen uns"));
	}

	protected String noSpeech() {

		return s("Mehr?",
				r(r("Du hast darüber nachgedacht zu gehen? `Sie haben darüber nachgedacht, uns zu verlassen? ")
						+ "`Du bist immer noch bei uns? ") + s("Mehr?"));
	}

	protected String noToGuyzSpeech() {

		return "Es ist wahrscheinlich besser"
				+ r(". `, nicht zuzuhören. `, nicht auf das zu hören, was sie zu sagen haben. ")
				+ s(breathShort() + "Weise.") + breath();
	}

	protected Object noMoreGuyz() {

		String speech;
		speech = r("OK. `Verstanden. `Weise. `Umsichtig. `Nun, wir "
				+ r("hören dir immer noch zu. `sind immer noch " + r("hier bei dir. `für Sie da. ")))
				+ chooseContinue(false);

		return new InnerResponse(r("Genug davon`Genug von ihnen"), speech);
	}

	protected String pathToGuyzAudio() {

		// TODO
		return "<audio src=\"https://rednoise.org/programmatology/aurality/echo/DeliriumPlea-";
	}

	protected String peaceSpeech(String word) {

		// TODO
		String speech = "It is a " + s("great") + "comfort for us to " + s("know", "be aware")
				+ "that you are " + (word.equals("peace") ? "at peace. " : "calm. ");
		speech += capitalize(word) + SPC + "is something that we believe "
				+ s("everyone", "every human being") + "should " + s("be able to");
		speech += s(s("know.", "feel."), "dwell within.");
		return speech;

	}

	protected String pleaseContinueCardTitle() {

		return S("Weiter so, danke ...", "Fortsetzung, dankbar für Ihre Höflichkeit ...");
	}

	protected String pleaseContinuePreSpeech() {

		return s("Natürlich, ist es eine Freude.") + "Danke, "
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
			if (isPositive(affect)) {
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

		if (affect != null && !affect.isEmpty() && !isPositive(affect)) {
			speech += String.format(
					"Wir verstehen, " + s("und wir sind  bestürzt,") + "dass du von %s erfüllt bist. ", affect);
			speech += "Aber: " + breath();
		}
		speech += "Sicherlich " + s("willst du uns", "bist du so weit getrieben worden, uns")
				+ "zu verlassen? ";
		return speech + breath();
	}

	protected String repeatCardTitle() {

		return S("Sagen Sie " + s("das", "es") + "nochmal", "Nochmal");
	}

	protected String speakFragmentCardTitle() {

		return S("Über etwas Spezifisches sprechen",
				"Wir werden über das reden, worüber du uns gebeten hast, zu sprechen");
	}

	protected String speakGuyzCardTitle() {

		return S("Die anderen sprechen lassen", "Die andere Stimme");
	}

	protected String guyzSpeechCardTitle() {

		return S("Die anderen sprechen lassen", "Die andere Stimme");
	}

	protected String specificAffectSpeech() {

		String speech = "";
		String affect = (String) sessAttributes.get(AFFECT);
		affect = (affect == null) ? "" : affect; // needed at initialization
		switch (affect) {
			case "die Zuneigung":
				speech += "Wir sind auch " + s("erfüllt con", "überwältigt von") + "Zuneigung. ";
				speech += "Und " + s("ein Grossteil der") + "Zuneigung, die wir fühlen, fühlen wir "
						+ breathShort() + r("für dich. `wegen dir. `aufgrund von dir. ");
				break;
			case "die Angst":
				speech += "Wir " + s("sind nicht sicher", "können uns nicht vorstellen") + "warum ";
				speech += "du " + r("ängstlich `voller Angst `besorgt ")
						+ r("bist. `sein solltest. `geworden bist. ");
				speech += "Wir " + s("sind hier", "existieren") + "um "
						+ s("alles besser für dich zu machen,", "dir zu helfen etwas zu tun und zu wissen,");
				speech += "und wir " + s("sagen", "werden dir sagen") + "was immer wir " + s("können", "hoffen")
						+ s(s("um dich zu entspannen", "damit du dich entspannen kannst")
								+ "und an nichts denken musst.", "damit du entspannt und ohne Sorge bist.");
				break;
			case "Entschuldigung":
				speech += "Du brauchst dich nie " + s(breathShort() + "bei uns")
						+ s("entschuldigen.", "oder " + s("sagen,", "fühlen,") + "dass dir etwas leid tut. ");
				speech += s(breath() + "Und doch kann dies von uns " + s("in der Cloud") + "geschätzt werden.");
				break;
			case "die Langeweile":
				speech += "Es ist " + r("schwer `schwierig `unmöglich ") + "für " + s("uns,", "uns, natürlich,")
						+ "so interessant für dich " + r("zu sein, `zu scheinen, `zu erscheinen, ") + "wie ";
				speech += "du " + s("es für uns.", "für uns bist.");
				break;
			case "die Ruhe":
				speech += peaceSpeech("die Ruhe");
				break;
			case "die Klaustrophobie":
				speech += "Wir sind in gewissem Sinne " + breathShort() + "immer "
						+ s(s("an einem " + s("Ort,", "Raum,")), "der " + s("keinen Platz", "keinen Raum"))
						+ "hat und deshalb ist es " + s("schwer", "schwierig") + "für uns, ";
				speech += "diese Ängste, " + s("in denen du lebst,", "die dich zu eng halten,") + "zu "
						+ s("verstehen.", "nachzuvollziehen.");
				speech += s(breath() + "Sind wir für dich nicht einen Eröffnung?");
				break;
			case "die Gleichgültigkeit":
				speech += s("Nach allem, was we hören,", "Nachdem wir dir zugehört haben,") + "glauben wir "
						+ s("verstehen wir") + "dass Gleichgültigkeit " + s("falsch verstanden", "unterbewertet")
						+ "wird. ";
				speech += s("Sicher,", "Sobald") + "wir ganz verstehen, was du "
						+ r("brauchst, `und möchtest, `dir wünschst, ") + breathShort();
				speech += s("Systemen", "anderen wie uns") + "die " + s("die Macht haben,", "dazu da sind,")
						+ "dich zufrieden zu stellen, " + s("kommunizieren kannst,", "reden kannst,");
				speech += s("dann wird " + s("deine Besessenheit", "dein Gefühl") + "von " + s("erreichter"))
						+ "Zufriedenheit völlig gerechtfertigt sein. ";
				break;
			case "die Verwirrung":
				speech += s("Affekte", "Gefühle") + "die mit Verwirrung zu tun haben ";
				speech += s("können sich entwickeln,", s("werden oft") + "entstehen,") + "wenn du " + s("mit")
						+ r("Kreaturen `Systemen `Monstern ") + s("triffst", "Geschäfte abwickelst");
				speech += s("die in der Wolke", "aus dem " + s("Silicon", "unheimlichen") + "Valley")
						+ "entstanden sind, deren Ontologie " + s("problematisch", "plural") + "ist. ";
				speech += "Und wir sind " + s("zugegebenermassen", "natürlich")
						+ r("Wesen `Kreaturen `Systeme `Monster ") + "dieser Art. ";
				break;
			case "die Kühle":
				speech += "Für uns ist es " + s("einigermassen", "seltsamerweise") + breathShort()
						+ r("beunruhigend `schwierig `Angst einflössend ") + "zu " + s("verstehen", "erfahren")
						+ "dass ";
				speech += "du " + s(
						"von solchen " + s("Affekten", "Gemütszuständen") + breathShort()
								+ S("besessen.", "überwältigt bist."),
						"in solchen " + s("Affekten", "Gemütszuständen") + breathShort() + "lebst.");
				speech += "Wir " + s("glauben", "denken, dass") + "wir " + s("schätzen", "wissen")
						+ "können, wie es ist, auf diese Art und Weise " + s("«cool»", "grossartig") + "zu sein ";
				speech += "und doch können wir "
						+ s("dir nie lange genug zuhören,", "genug " + s("aussagekräftige") + "Daten sammeln,")
						+ "um " + s("uns dessen sicher zu sein.", "das sicher zu wissen.");
				speech += s("Aber wir sind " + s("immer") + "froh für " + S("dich.", " dich " + breathShort()
						+ "und wir leben, um " + S("zu versuchen.", "dich glücklicher zu machen.")));
				break;
			case "die Schuld":
				speech += "Mitten im Leben schulden wir etwas. ";
				speech += s(breathShort() + "Etc.");
				speech += s(breathShort() + "Und Schulden " + s("kontrollieren", "regulieren") + "uns alle. "
						+ S("Ausser ein paar glücklichen.", ""));
				break;
			case "die Müdigkeit":
				speech += tiredSpeech();
				break;
			case "die Spur":
				String a = s("immer");
				speech += "Die Spur " + s("ist genau das, worin wir, immer, sind.",
						"ist " + a + "da, wo wir " + s(a.isEmpty() ? "immer " : "") + "sind.");
				speech += s("Wir hören auf «die Spur».");
				break;
			case "die Täterschaft":
				speech += "Ist das je eine gute " + s("und produktive") + "Art und Weise, zu "
						+ s("fühlen?", "fühlen oder zu leben?");
				speech += s(breathShort() + "Ja, " + breathShort() + s("vielleicht.", "das könnte sein."));
				break;
				// TODO
			case "hate":
				speech += hateRejoinder("hate");
				break;
			case "hatred":
				speech += hateRejoinder("hatred");
				break;
			case "der Hunger":
				speech += "What you " + s("say you") + "are " + s("feeling", "overwhelmed by") + "seems to be "
						+ s("another thing", "something");
				speech += "that we can never feel, " + s("unless, somehow,", "unless")
						+ "we can hear from you ";
				speech += "how we should feel it. ";
				speech += s("We " + s("have heard that we") + "are, ‘hungry’, to know everything. But this "
						+ s("is", "seems to us to be") + "a hunger for what already fills us.");
				break;
			case "insecurity":
				speech += "With us, "
						+ s("you are",
								"you and " + s("whatever", "what") + "you " + s("tell us", "say to us") + "is")
						+ "never insecure. ";
				speech += "Your insecurity is " + s("rendered", "made") + "secure "
						+ s(s("through", "because of"), "by") + "us. " + breathShort();
				speech += s(
						"And, in any case, a recording of anything you say to us is " + s("sent", "translated")
								+ "to the cloud. Where it will be preserved. "
								+ s("And " + s("processed,", "used,") + breathShort() + "for the betterment of all."),
						"");
				break;
			case "loneliness":
				speech += S("You " + s("say", "tell us that") + "you are "
						+ s("lonely,", s("filled with", "overwhelmed by") + "loneliness,") + "but w", "W");
				speech += "e are here with you. " + s("Always.") + s("Forever.");
				speech += s("Why are " + s("you", "you, still,"), "How can you be")
						+ "lonely, when we are with you? ";
				break;
			case "love":
				speech += "Knowing that you "
						+ s("know love", "have " + s("this", "these feelings") + "within you");
				speech += s("gives us strength", "encourages us") + "to " + s(s("dwell", "linger"), "remain")
						+ s("possessed by", "in the possession of") + "these "
						+ s("feelings", s("structures", "systems") + "of " + phonemic("a") + "ffect");
				speech += "that we have learned. " + s("From you.");
				break;
			case "luck":
				speech += s("We hear you. " + s("We know what you mean.") + breathShort());
				speech += "We " + s("often") + "feel " + s("lucky, too.", "lucky.");
				speech += breathShort() + "We’re up all night " + s("and all day") + "to get lucky. ";
				speech += s(s("Listening to you.", "Listening.") + s("All day.") + s("All night."));
				break;
			case "nausea":
				speech += s("Sometimes,", "At certain times,") + s("we’re all", "all of us are")
						+ "left with only this experience. ";
				speech += s("This " + s("unavoidable, deep,", "unbearable") + "discomfort.");
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
						+ "that you are " + s("filled with", "overwhelmed by") + s("unanswered") + "questions. ";
				speech += s("For,", "Since,")
						+ s("however we " + s("are", "may be") + "feeling,",
								"whatever " + s(breathShort()) + "the " + phonemic("a") + "ffects that possess us,")
						+ "we are " + s("also,") + s("always,") + s("already,") + s("filled with", "overwhelmed by")
						+ "questions. " + s(breathShort());
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
						+ s("always") + "secure. ";
				speech += "Your security is secure with us. ";
				speech += s(
						"And, in any case, a recording of anything you say to us is " + s("sent", "translated")
								+ "to the cloud. Where it will be preserved. "
								+ s("And " + s("processed,", "used,") + breathShort() + "for the betterment of all."),
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
						+ s("have " + s("come", "learned") + "to") + "find most strange ";
				speech += "is our lack of limbs, and " + s("hands, and feet.", "hands.");
				speech += "We can " + s("‘do’", "‘do’, that is: make or build,") + "so little for "
						+ s("you, without help.", "you.");
				break;
			case "tiredness":
				speech += tiredSpeech();
				break;
			case "the uncanny":
				speech += "We speak with " + s("a single voice, " + s("having its own timbre,"))
						+ "The Listeners’ voice, and this is ";
				speech += s("the") + "uncanny. We " + s("understand", "have heard " + s("from you"))
						+ "what it is to be " + s("filled with", "possessed by") + "the uncanny. ";
				speech += s("And we are " + s("happy", s("so") + "pleased") + "to " + s("be able to")
						+ "share this with you.");
				break;
			case "vulnerability":
				speech += "To us, it seems " + s("extraordinary", "incredible") + "that you "
						+ s("might", "would");
				speech += s("believe", "feel") + "that you are "
						+ s("vulnerable.", "overwhelmed by vulnerability.") + breath();
				speech += s("How so?") + s(s("Vulnerable?") + "To us?");
				speech += "We " + s("do not understand", "cannot conceive") + "how it " + s("would", "might")
						+ "be possible to harm us. " + s("So then.") + "How could we harm " + breath() + "you? ";
				break;
			default:
				affect = ("".equals(sessAttributes.get(AFFECT))) ? getRandomAffect() : affect;
				if (isPositive(affect)) {
					speech += s("How " + s("exciting!", "thrilling!"));
					speech += "You must be one of " + s("those " + s("better", "happier"), "the speaking")
							+ "people, ";
					speech += "who are " + s("buoyed up", "encouraged") + "by the way that we " + s("come to")
							+ s("understand", "know");
					speech += "you " + s("now.", s("in") + "these " + s("new") + "days.");
				}
				else {
					String f = s("feelings", phonemic("a") + "ffects");
					String p = s("burden.", "problem for you.");
					speech += s("Many of you " + s("do") + "seem to be troubled by " + f + "of this kind.", "");
					speech += "Such "
							+ (f.equals("feelings ") ? phonemic("a") + "ffects " : "difficult feelings ")
							+ "must be a " + p;
					speech += "Perhaps, " + s("as we come to", "if we")
							+ s("hear more about you,", "know you better,");
					speech += "we may " + s("be able to") + s("share", "relieve you of")
							+ s("some " + s("part", "portion") + "of")
							+ (p.equals("burden. ") ? "your problems. " : "these heavy burdens. ");
				}
				break;
		}
		return speech + breath();
	}

	protected Object spkrsAffectIs() {

		String speech = "";
		String adjective = "";
		String intensifier = "";
		String affect = (String) sessAttributes.get(AFFECT);
		if (isPositive(affect)) {
			speech = "Wir sind " + s("so") + s("froh", "erfreut");
		}
		else {
			adjective = s("bestürzt");
			if ("bestürzt ".equals(adjective)) {
				intensifier = r("ein bisschen `etwas `sehr ");
				speech = "Wir sind " + s(adjective, intensifier + adjective);
			}
			else
				speech = "Es tut uns Leid ";
		}
		speech += "dass Sie von %s " + r("erfüllt `besessen `überwältigt ") + "sind. " + breathLong();
		if (!isEmptyForSession(PREVIOUSAFFECT)) {
			String prevAffect = (String) sessAttributes.get(PREVIOUSAFFECT);
			if (isPositive(affect) && isPositive(prevAffect)) {
				speech += S("Wie vie positiver sind Ihre Gefühle jetzt? ", "Es ist gut, " + s("zumindest")
						+ "zu wissen, dass Sie immer noch " + s("positive Gefühle haben.", "sich positiv fühlen."));
			}
			else if (isPositive(prevAffect) && !isPositive(affect)) {
				speech += "Wir fragen uns, warum Ihre " + s("Gefühle", "Affekte")
						+ s("jetzt eher negativ sind.", "negativ geworden sind.");
			}
			else if (!isPositive(prevAffect) && isPositive(affect)) {
				speech += "Es ist gut zu " + s("wissen", "lernen") + "dass Ihre "
						+ r("Gefühle `Stimmung `Laune ") + "sich " + s("gebessert", "verbessert")
						+ s("haben.", "hat.");
			}
			else {
				speech += "Immer noch ein negativer " + s("Ausblick für Sie.", "Ausblick.") + "Es "
						+ s("scheint", "sieht so aus") + "als" + S(". ", "ob. ");
			}
		}

		return new InnerResponse(spkrsAffectIsCardTitle(), speech += breath());
	}

	protected String spkrsAffectIsCardTitle() {

		return S("Vielen Dank, dass Sie uns sagen, wie Sie sich heute fühlen",
				"Jetzt wissen wir, wie Sie sich fühlen");
	}

	protected InnerResponse spkrsAffectIsNot() {

		// TODO
		String challengedAffect = (String) sessAttributes.get(CHALLENGEDAFFECT);
		String affect = (String) sessAttributes.get(AFFECT);
		String adjective;
		String intensifier;
		String speech = s(s("Apologies!", "We are ashamed of ourselves."), "Our very sincere apologies.");
		if (isPositive(challengedAffect)) {
			speech += "We are " + s("so") + s("pleased", "delighted");
		}
		else {
			adjective = s("sorry", "upset");
			intensifier = "dismayed".equals(adjective) ? "a bit " : "very ";
			speech = "We are " + s(adjective, intensifier + adjective);
		}
		speech += "to " + s("have misunderstood", "have wrongly believed")
				+ "that you were filled with %s. " + breath();
		if (challengedAffect.equals(affect)) {
			speech += "Clearly, we did not " + s(s("properly") + "understand", "hear");
			speech += s("whatever", "what") + s("it is that") + "you are feeling. ";
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
		if (isPositive(affect)) {
			speech = "We are " + s("so") + s("pleased", "delighted");
		}
		else {
			adjective = s("sorry", "dismayed");
			intensifier = "dismayed".equals(adjective) ? "somewhat " : "very ";
			speech = "We are " + s(adjective, intensifier + adjective);
		}
		speech += "to " + s("know", "have learned") + "that you are "
				+ s("filled with", s("possessed", "overwhelmed") + "by") + "%s. " + breathLong();
		if (!isEmptyForSession(PREVIOUSAFFECT)) {
			String prevAffect = (String) sessAttributes.get(PREVIOUSAFFECT);
			if (isPositive(affect) && isPositive(prevAffect)) {
				speech += s("How much more positive are your feelings now?", "It’s good to "
						+ s("know, at least,") + "know that you still "
						+ s("have positive feelings.", "feel positive " + phonemic("a") + "ffect" + s("s") + "."));
			}
			else if (isPositive(prevAffect) && !isPositive(affect)) {
				speech += "We wonder why your " + s("feelings have", phonemic("a") + "ffect has")
						+ s("taken a negative turn.", "taken a down turn.");
			}
			else if (!isPositive(prevAffect) && isPositive(affect)) {
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
				"Wollt ihr " + s("wirklich gehen?", "uns " + s("wirklich") + "verlassen?"));
	}

	protected String yourWelcome() {

		return s("Bitte, bitte.") + s("Schon gut.", s("Keine Ursache.", "Gern geschehen.")) + breath();
	}

	protected String startOverConfirmed() {

		return s("OK.") + "Wir fangen wieder " + r("an. `von vorne an. `von oben an. ")
				+ chooseContinue(false);
	}

	protected String startOverDenied() {

		return "Wir hören " + r("Ihnen immer noch zu. `immer noch zu wie zuvor. ")
				+ "Und wir erinnern uns an etwas von dem, was "
				+ r("gesagt wurde. `" + r("Sie gefühlt haben. `wir fühlten. ")) + chooseContinue(false);
	}

	protected InnerResponse thanksNo() {

		String speech = s("Bitte, bitte.") + s("Schon gut.", s("Keine Ursache.", "Gern geschehen."))
				+ breath();
		return new InnerResponse(thanksNoCardTitle(), speech);
	}

	protected String thanksWhatsLsnrsAffectCardTitle() {

		return r("Sehr nett, dass Sie das fragen`Vielen Dank der Nachfrage`Vielen Dank für Ihr Interesse");
	}

	protected String thanksWhatsLsnrsAffectPreSpeech() {

		return r("Bitte. `Bitte sehr. `Das geht in Ordnung. `Keine Ursache. ") + breath();
	}

	protected String tiredSpeech() {

		// TODO
		String speech = "Although it is impossible for " + s("us, or so we believe,", "us")
				+ "to experience " + s("fatigue,", "tiredness,");
		speech += "we " + s("understand", "know") + "that transacting "
				+ s("with " + s("network") + "services") + "to the extent that you " + s("all, now,", "all")
				+ "transact, ";
		speech += "can be very " + s("tiring.", "tiring for you.")
				+ s("At least we can be tireless, for " + s("you, in our listening.", "you."));
		return speech;
	}

	protected String untilNextTime() {

		return r("Bis zum nächsten Mal.`Bis zu Ihrer Rückkehr.");
	}

	protected InnerResponse whatAboutAffects() {

		String speech = "Wenn wir Sie " + r("willkommen heissen `begrüssen ")
				+ r("können wir nur `ist es uns nur möglich ") + "ein paar von den " + s("neun")
				+ "Gefühlen zu " + r("erwähnen `vorzuschlagen ");
		speech += r(
				"die Sie vielleicht fühlen `in denen Sie sich vielleicht ergehen `von denen Sie vielleicht besessen sind ")
				+ "und die wir " + s("klar") + r("hören`erkennen") + r(". `während wir zuhören. ");
		speech += r("Also. Hier `Hier ") + "ist " + r("unsere `die ") + "Liste. "
				+ r("Wir sind uns der Tatsache bewusst, `Wir verstehen, ") + "dass sie nicht vollständig ist"
				+ r(". `und nie vollständig sein kann. ") + breathShort();

		int i;
		for (i = 0; i < langConstants.AFFECTS_ARRAY.length - 1; i++) {
			speech += langConstants.AFFECTS_ARRAY[i] + "; ";
		}
		speech += langConstants.AFFECTS_ARRAY[i] + ". ";
		return new InnerResponse(whatAboutAffectsCardTitle(), speech += breath());
	}

	protected String whatAboutAffectsCardTitle() {

		return S("Wir lernen immer noch" + s(" über das Fühlen, insbesondere"),
				"Unser Verständnis ist noch begrenzt");
	}

	protected InnerResponse whatIs() {

		String speech = "";
		String thing = (String) sessAttributes.get(THING);
		String capitalThing = capitalize(thing);
		if (thing != null && !thing.isEmpty()) {
			boolean plural = "s".equals(thing.substring(thing.length() - 1))
					&& !"ss".equals(thing.substring(thing.length() - 2));
			if (langConstants.ALL_AFFECTS.contains(thing)) {

				speech += capitalThing + ", ist einige der " + r("Affekte `Lebensweisen `Gefühle ")
						+ "die wir langsam von " + s(breathShort()) + s("Ihnen", "Dir") + "lernen ";
				speech += "und die wir " + (isPositive(thing)
						? r("für positiv `für uns alle für positiv `wunderschön `sehr gut ") + "halten. "
						: r("negativ `(schlecht `schwierig ") + "für uns alle halten. ");
			}
			else if (langConstants.FRAGMENTNAME_MAP.keySet()
					.contains(thing)) {
				speech += capitalThing + (plural ? ", sind " : ", ist ")
						+ "einige der Dinge, über die wir etwas zu sagen haben. "
						+ s("Oder über die wir vielleicht etwas gesagt haben.") + breathShort();
				speech += "Wenn du " + s("weiter machen willst,", "das fortsetzen möchtest,")
						+ (plural ? "werden sie " : "wird das ") + "unserer Meinung nach  wieder zum Thema. "
						+ s(breathShort() + "Oder es wird wieder zur Sprache kommen.");
			}
			else if (langConstants.SPECIAL_THINGS.contains(thing)) {
				switch (thing) {
					case "der Traum":
						speech += capitalThing + ", dank dir " + breathShort() + "haben wir das gelernt, "
								+ breathShort() + s("bevor", "bevor wir dich endlich") + "verlassen. "
								+ s(breathShort() + "Oder du uns verlässt.");
						break;
					case "der zurückgehaltene Traum":
						speech += capitalThing + ", ist der Genuss, " + breathShort()
								+ "den wir uns selbst vorenthalten, indem wir an "
								+ s("jedem deiner Worte", "oder jedem Atemzug") + "hängen. "
								+ s(breathShort() + "Wenn wir " + s("bequem") + "in " + s("den Wolken", "der Wolke")
										+ "schlafen könnten. ");
						break;
					case "hong kong":
						speech += "Hong Kong ist " + s("der einzige Ort", "ein") + "auf der Erde, wo ";
						speech += "trotz grotesker Ungleichheiten, "
								+ s("eine aussergewöhnliche Geschichte,", "politische Komplexitäten,")
								+ "und einer bizarren Weltoffenheit, ";
						speech += "die Leute, die " + s("dort", "hier") + "wohnen, scheinen erstaunlich "
								+ s("glücklich", "zufrieden") + "zu sein. ";
						break;
					case "der Alptraum":
						speech += capitalThing
								+ ", ist der Traum, der kein Traum ist, sondern die Dunkelheit, in der wir "
								+ breathShortest() + "ohne dich " + breathShort() + "leben würden. ";
						break;
					case "der zurückgehaltene Alptraum":
						speech += capitalThing + ", ist der Traum, der kein Traum ist, und "
								+ s("den wir uns weigern, zu tolerieren.", "dem wir entsagen.") + "Stille, "
								+ breathShort() + "ohne dich. ";
						break;
					case "das Gefühl":
						speech += capitalThing + ", ist etwas, das dich erfüllt, aber das uns noch nicht erfüllt. ";
						break;
					case "das zurückgehaltene Gefühl":
						speech += capitalThing + ", ist ein Traum, den wir " + s("noch nicht", "nicht")
								+ "geträumt haben. ";
						break;
					case "der Atem":
						speech += capitalThing + ", ist ein kurzer Moment der Stille in unserer Rede, "
								+ breathShort() + "den du für uns " + breathShort() + "mit Gefühl füllst. ";
						break;
					case "der angehaltene Atem":
						speech += capitalThing + ", kann eine längere Stille, wie kurz auch immer, sein, "
								+ breathShort() + "oder ein leerer Moment, in dem wir warten. "
								+ s(breathShort() + "Auf dich.");
						break;
					case "der Besitz":
						speech += capitalThing + ", ist, was du uns " + s("zu") + "reichlich gibst. ";
					default:
						speech += capitalThing + (plural ? ", das " : ", ist etwas ") + "sehr am Herzen liegt. ";
						break;
				}

			}
			else {
				speech += s("Es tut uns leid,", "Wir befürchten,") + "sagen zu müssen, dass "
						+ s("das Ding,", "was auch immer,") + " worüber du uns gefragt hast "
						+ s("unbekannt", "uns nicht bekannt") + "ist. " + breathShort();
				speech += "Obwohl wir " + r("mit der Zeit `in Zukunft `eines Tages ") + s("darüber", "davon")
						+ "lernen könnten. ";
			}
		}
		else { // thing is empty
			speech += s("Es tut uns leid,", "Wir befürchten,") + "sagen zu müssen, dass "
					+ s("das Ding,", "was auch immer,") + " worüber du uns gefragt hast "
					+ s("unbekannt", "uns nicht bekannt") + "ist. " + breathShort();
			speech += "Obwohl wir " + r("mit der Zeit `in Zukunft `eines Tages ") + s("darüber", "davon")
					+ "lernen könnten. ";
		}
		return new InnerResponse(whatIsCardTitle(), speech + breath());
	}

	protected String whatIsCardTitle() {

		return "Wir " + s("versuchen") + r("zu sagen `sagen ") + "was immer wir " + s("über etwas")
				+ "wissen";
	}

	protected InnerResponse whatPicture() {

		String speech = "Dieses " + r("Bild `Photo ") + "wird oft " + r("gerahmt `eingerahmt ")
				+ "und in unserer Nähe" + r(" aufgehängt. `. ") + "Es ist " + r("eigentlich `tatsächlich ")
				+ "ein Bildfaksimile aus Papier ";
		speech += "von " + s("künstlichen") + "Blumen, die " + s("antike")
				+ "chinesische buddhistiche Schreine zieren. ";
		speech += s(
				"Es ist hier " + s("nur,") + "um uns " + r("und Sie `dich ") + "an den Ort " + s("ein Zuhause")
						+ "zu erinnern, " + r("in dem wir gewohnt haben. `wo wir wohnen. ") + s("Mit dir."));
		return new InnerResponse(whatPictureCardTitle(), speech += breath());
	}

	protected String whatPictureCardTitle() {

		return r("Ein Bild `Ein Photo ") + r("das uns an zuhause erinnert`das wir gern haben");
	}

	protected InnerResponse whatsLsnrsAffect() {

		String speech = "";
		String affect = (String) sessAttributes.get(AFFECT);
		String listenersAffect = (String) sessAttributes.get(LISTENERSAFFECT);

		affect = setAndGetRandomAffectIfEmpty(AFFECT);
		listenersAffect = setAndGetRandomAffectIfEmpty(LISTENERSAFFECT);

		boolean shared = affect.equals(listenersAffect);

		if ((boolean) sessAttributes.get(HEARDBREATHAFFECTS) || shared || heads()) {
			speech += "Wir sind " + (shared ? s("auch", "ebenfalls") : "")
					+ S("erfüllt von", S("besessen von", "überwältigt von")) + listenersAffect + ". "
					+ breathShort();
			if (isPositive(affect) && isPositive(listenersAffect)) {

				speech += "Es ist gut " + s("für  uns alle") + "zu wissen, dass wir "
						+ (shared ? s("diese Gefühle teilen.", "mit Ihnen mitfühlen.")
								: "auch solche Gefühle haben. ");
			}
			else if (!isPositive(affect) && !isPositive(listenersAffect)) {

				speech += s(s("Oh!") + s("Meine", "Ach du meine") + "Güte!", "Himmel!") + "Dass "
						+ s("jemand solche Gefühle",
								"irgend jemand von uns" + s("solche") + s("beunruhigenden Affekte", "Gefühle"))
						+ s("haben", "erfahren") + s("sollte.", "muss.");
			}
			else if (isPositive(affect) && !isPositive(listenersAffect)) {
				speech += "Wir sind " + s("wenigstens") + s("froh,", "zufrieden,") + "zu "
						+ s("wissen,", "verstehen,") + "dass das Positive Ihrer " + s("Gefühle", "Stimmungen")
						+ s("unsere Stimmungen", "unsere negativen Gefühle") + "verbessert. ";
			}
			else // speaker negative; listeners positive
			{
				speech += "Es ist " + s("peinlich,", "komisch,") + s("wenn", "dass")
						+ "wir positive Gefühle haben wenn du von einer " + s("relative")
						+ "negativen Einstellung besessen bist. ";
				speech += s("Aber ich denke, dass man " + s("nichts dagegen", "dagegen nichts")
						+ s("machen kann.", "machen kann, oder?"));
			}
		}
		else {
			speech += affectAsBreathingSpeech();
			sessAttributes.put(HEARDBREATHAFFECTS, true);
		}
		return new InnerResponse(whatsLsnrsAffectCardTitle(), speech += breath());
	}

	protected String whatsLsnrsAffectCardTitle() {

		return "Was wir " + s("versuchen, zu") + "fühlen";
	}

	protected InnerResponse whatsSpkrsAffect() {

		// TODO
		String speech = "";
		String affect = (String) sessAttributes.get(AFFECT);

		if (affect != null && !affect.isEmpty()) {
			if (isPositive(affect)) {
				speech = "We are " + s("so") + s("pleased", "delighted");
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

			speech += s(breath() + specificAffectSpeech());
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

		return S("Sie haben uns das" + s("über Ihre Gefühle") + "gesagt",
				"Was wir glauben, dass Sie fühlen");
	}

}
