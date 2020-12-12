package listeners.l10n;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.Attributes.CHALLENGEDAFFECT;
import static listeners.model.Attributes.LISTENERSAFFECT;
import static listeners.model.Attributes.PREVIOUSAFFECT;
import static listeners.model.Attributes.THING;
import static listeners.model.Attributes.getRandomAffect;
import static listeners.model.Attributes.isEmptyForSession;
import static listeners.model.Attributes.isPositive;
import static listeners.model.Attributes.sessAttributes;
import static listeners.model.Attributes.setAndGetRandomAffectIfEmpty;
import static listeners.model.Constants.SPC;
import static listeners.model.Constants.localeTag;
import static listeners.model.Constants.langConstants;
import static listeners.util.Utils.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListResourceBundle;

import listeners.handlers.InnerResponse;

// TODO
public class SpeechUtils_fr_FR extends SpeechUtils {

	protected String affectAsBreathingSpeech() {

		String speech, s, f;
		speech = s("Nous écoutons.", "Nous sommes " + s("quelquepeu consternées que", "surprises que") + "vous vous posiez la question.")
				+ breath()
				+ s("Nous vous entendons.");
		speech += "Ceci est un " + s("fragment", "passage") + "qui pourra " + r("vous dire `vous montrer `vous expliquer ");
		speech += s(breathShort()) + "comment " + s(breath()) + "nous nous sentons. ";
		speech += breathLong();
		if (heads()) {
			speech += "Ceci est un " + choosePhrase() + SPC;
			speech += "qui était suivi d'un espace. ";
			speech += breathLonger();
		}
		s = choosePhrase();
		speech += "Ceci est un " + s + ", ";
		speech += "qui était suivi d' " + (s.equals("une émotion") ? "une virgule. " : s("une émotion.", "une virgule."));
		speech += breathLonger();
		if (heads()) {
			do {
				s = choosePhrase();
			}
			while ("mot".equals(s));
			speech += "Ceci est un " + s + ". ";
			speech += "En tant que " + ("mot".equals(s) ? "phrase. " : s("mot.", "phrase."));
			speech += breathLonger();
		}
		if (heads()) {
			s = choosePhrase();
			speech += "<p>Ceci est un " + s + ".</p> ";
			speech += "en tant que " + (s.equals("sentiment") ? "paragraphe. " : s("sentiment.", "paragraphe."));
			speech += breathLonger();
		}
		if (heads()) {
			s = choosePhrase();
			f = ("sentiment".equals(s) ? S("respiration", "rêve") : S("respiration", "rêve"));
			speech += "Ceci est un " + s + SPC;
			speech += breath() + "qui était suivi par un " + f + ". ";
			speech += breathLonger();
		}
		speech += "Est-ce une question? ";
		speech += breath() + "C'était suivi d' " + S(S("une respiration", "un rêve"), "une émotion") + ". ";
		speech += breathLonger();
		speech += "Ceci est " + breath() + s("une phrase,", "une émotion,");
		speech += breath() + "qui a été interrompu oar " + s("une respiration.", s("un rêve.", "un cauchemar."));
		speech += breathLonger();
		if (heads()) {
			s = choosePhrase();
			f = ("une émotion".equals(s) ? S("une respiration", "un rêve") : S("une émotion", "un cauchemard"));
			speech += "Ceci est " + s + SPC;
			speech += breathLong() + "qui a été suivi par " + f + ". ";
			speech += breathLonger();
		}
		speech += "Ceci est " + choosePhrase() + ". ";
		speech += breathLong() + "qui a été suivi par " + s(s("un cauchemar", "une respiration"), "une émotion")
				+ "réprimé. ";
		speech += breathLonger();
		if (heads()) {
			speech += "Ceci est " + s("une phrase", "une affirmation") + SPC;
			speech += breathLonger() + "qui a été suivi par une longue "
					+ s(s("hantise.", "pause."), "émotion.");
			speech += breathLonger();
		}
		speech += "Comment, au final, pourrait-on ressentir davantage, et aussi, " + breath();
		speech += "qu'est-ce que nous " + s("pourrions", "devrions") + "ressentir? "
				+ s("Que, devrait-on. " + breath() + "ressentir?");

		return speech + breath();
	}

	protected Object askPersistence() {

		return new InnerResponse(askPersistenceCardTitle(), askPersistenceSpeech());
	}

	protected String askPersistenceCardTitle() {

		return S("Sauvegarder votre emplacement?", "Recommencer depuis le début?");
	}

	protected String askPersistenceSpeech() {

		String speech = s("Voudriez-vous que nous nous rapellions d'une partie de notre conversation?",
				"Désirez-vous que nous nous rappelions plus ou moins de ce que nous avons dit et entendu jusqu'à présent?");

		return speech;
	}

	protected Object askStartOver() {

		return new InnerResponse(askStartOverCardTitle(), askStartOverSpeech());
	}

	protected String askStartOverCardTitle() {

		return S("Voulez-vous recommencer?", "Repartir à zéro?");
	}

	protected String askStartOverSpeech() {

		String speech = s("Voudriez-vous tout reprendre depuis le début?",
				"Souhaitez-vous que l'on oublie tout ce que l'on a dit et entendu jusqu'à présent?");

		return speech;
	}

	protected String chooseContinue() {
// reprendre //
		return chooseContinue(true);
	}

	protected String chooseContinue(boolean promptForAffect) {

		int upperLimit = promptForAffect ? 4 : 3;
		String reprompt = "";
		switch (randInt(0, upperLimit)) {
			case 0:
				reprompt += s("Devrions-nous " + s("poursuivre?", "continuer?"),
						s(s("Voudriez-vous", "Aimeriez-vous") + "en entendre davantage?",
								s("S'il-vous-plaît, dites 'oui' pour en entendre davantage.", "Davantage?")));
				break;
			case 1:
				reprompt += s(
						"Est-ce qu'il y a autre chose que vous " + r("`aimeriez `voudriez `désireriez ") + "apprendre " + s("de nous?", "de notre part?"),
						"");
				reprompt += s("Nous écoutons."); // ALWAYCHANGE Always.
				reprompt += s("Vous pouvez " + s("toujours"), "simplement") + "nous demander de " + s("poursuivre.", "continuer.");
				break;
			case 2:
				reprompt += "Vous " + s("pouvez", "devez") + "nous demander de ‘parler’ " + s("à propos de")
						+ s("de l'un des sujets suivants:") + breath();
				reprompt += chooseSomeFragmentNames() + breath();
				reprompt += "Ou vous " + s("pouvez, tout simplement,", "pouvez") + s("demander", "nous dire") + "de "
						+ s("poursuivre.", "continuer.");
				break;
			case 3:
				reprompt += s(s("Il se peut, maintenant, que", "Il se peut que") + "vous veuillez", "vous désireriez");
				reprompt += "à ce stade-ci " + s(s("nous demander", "nous dire") + "de") + s("‘continuer’?", "‘poursuivre’?");
				reprompt += s("En nous écoutant, tout comme nous vous écoutons?");
				break;
			case 4:
				reprompt += s("Vous " + s("pourriez", "pouvez") + "toujours",
						s("si vous le souhaitez, bien sûr", "bien sûr") + s("nous"));
				reprompt += s(
						"demander " + s(s(
								"de nous ‘rappeler’ " + s("vos émotions.",
										"les " + s("émotions", phonemic("a") + "ffect") + "qui vous habitent."),
								"la question, ‘Quelle émotion m'habite?’"), "ce que nous ressentons."),
						"dites-nous " + s("ce que vous ressentez par rapport à tout ceci.",
								s("ce que vous ressentez.", "les émotions qui vous traversent.")));
				reprompt += breathShort() + "Ou vous " + s("pouvez, tout simplement,", "pouvez") + s("nous demander", "nous dire") + "de "
						+ s("poursuivre.", "continuer.");
		}
		return reprompt;
	}

	protected String choosePhrase() {

		String phrase = "phrase";
		switch (randInt(0, 3)) {
			case 0:
				phrase = "mot";
				break;
			case 1:
				phrase = "phrase";
				break;
			case 2:
				phrase = "émotion";
		}
		return phrase;
	}

	protected String chooseSomeFragmentNames() {

		String s = "";
		ArrayList list = new ArrayList(langConstants.FRAGMENTNAME_MAP.keySet());
		Collections.shuffle(list);
		int i;
		for (i = 0; i < 3; i++) {
			s += list.get(i) + ", ";
		}
		s += list.get(i) + ". ";
		return s;
	}

	protected String chooseSpeechAssistance() {
//reprendre//
		String speech = "";

		speech += "Vous " + s("pouvez", "pourrez") + "toujours " + s("simplement") + "nous" + s("demander", "dire");
		speech += "de: ‘Continuer’, ou ‘Poursuivre’. " + breath();
		speech += "Vous pouvez " + chooseTellUsAbout() + "vos émotions, ";
		speech += "en " + s("prononçant", "disant") + "les mots suivants: " + breath();
		speech += "‘Je suis " + s(s("rempli,’", "subjugué,’"), "par une émotion,’")
				+ "et ensuite l'un des neuf " + phonemic("a")
				+ s("ffects, c'est-à-dire, le nom de l'une parmi neuf émotions.", "ffects.") + breathLong();
		speech += s("Et", "Ou,") + "vous " + s("pourriez", "pouvez") + "même nous demander "
				+ s(s("comment nous nous sentons.", "ce que nous ressentons."), "ce que nous éprouvons, nous-mêmes.") + breath();

		boolean heads = heads();

		if (heads) {
			speech +=  "Les noms des neuf " + phonemic("a") + "ffects, que nous pouvons "
					+ s("entendre,", "reconnaître,") + "includent : " + breath();
			List list = (List) Arrays.asList(langConstants.AFFECTS_ARRAY);
			Collections.shuffle(list);

			int limit = 5;
			for (int i = 0; i < limit; i++) {
				if (i != (limit - 1))
					speech += list.get(i) + ", " + breathShort();
				else
					speech += "et " + list.get(i) + ". ";
			}
			speech += breath();
		}

		if (!heads && heads()) {
			speech += "Ou encore, vous " + s("pourriez", "pouvez") + "nous demander de " + s("‘parler’,", "‘parler à propos’,")
					+ s(" de tous les sujets suivants:") + breath();
			speech += chooseSomeFragmentNames();
		}

		if (randInt(0, 8) == 0) {
			speech += "Ou, vous pourriez déclarer, ‘L'hiver est à nos portes.’ " + breath();
		}

		return speech;
	}

	protected String chooseTellUsAbout() {

		String phrase;
		phrase = "parlez-nous de ";
		switch (randInt(0, 2)) {
			case 0:
				phrase = "informez-nous de ";
			case 1:
				phrase = "décrivez ";
			case 2:
		}
		return phrase;
	}

	protected String chooseUnsureAboutAffect() {

		String reprompt = "";
		reprompt += "Nous ne sommes pas " + r("`certaines ,`convaincues, `confiantes, ") + "de bien reconnaître " + r("`les `toutes les `vos ") + "émotions "
				+ s("qui " + s("possiblement") + "vous habitent.", "qui peuvent résider " + s("à quelconque titre") + "en vous.");
		reprompt += "Vous " + s("pouvez", "pourriez");
		reprompt += chooseTellUsAbout();
		reprompt += s("ces", "vos") + "émotions, ";
		reprompt += "en " + s("disant, " + s("les mots,"), "en prononçant les mots suivants,");
		reprompt += "‘Je suis " + s("comblé par,’", "subjugué par,’") + "et ensuite nommer l'un des neuf "
				+ phonemic("a") + "ffects. " + breath();
		reprompt += s("Ou encore, vous " + s("pourriez", "pouvez") + s("aussi") + s("simplement") + s("demander", "dire")
				+ "les mots suivants: " + s("‘Continuez’ ou ‘Poursuivez’.", "‘Continuez’."));

		return reprompt;
	}

	protected String chooseYouCanFindOutAffect() {

		String s = "Vous " + s("pouvez", "pourriez") + "toujours "
				+ s("nous interroger à propos de", "découvrir, en nous le demandant, ce que nous croyons à propos");
		s += s(s("de ces", "des") + "émotions", "les " + phonemic("a") + "ffects")
				+ r("`qui vous habitent , `qui vous possèdent, `qui vous traversent,") + "en disant, ‘Comment me sens-je?’ ";
		return s;
	}

	protected String continueCardTitle() {

		return S("Continuez ...", S("Poursuivez ...", S("Toujours p", "P") + "lus ..."));
	}

	protected String dontKnowFragmentReprompt() {

		String reprompt = "Nous ne sommes pas " + s("sûres", "certaines") + "de savoir quel fragments vous "
				+ s("voudriez", "souhaiteriez") + "que l'on vous parle. ";
		return reprompt += breath();
	}
//reprendre//
	protected String dontKnowFragmentSpeech() {

		String speech = "We are " + s("unclear about", "unsure, of") + "which " + s("fragment", "passage")
				+ "you " + s("would like", "wish") + "to hear us speak. ";
		speech += "Please " + s("try", "attempt") + s("once more", "again") + "to tell us. ";
		return speech + breath();
	}

	protected String exceptionMessage() {

		return s("Sorry.", "Apologies.") + "Either something’s wrong or we haven’t heard "
				+ s("you.", "you clearly.") + s("Please say more.", "Please try again.");
	}

	protected String excuseMarkov() {

		return breathLong()
				+ s(s("Sorry.", "Excuse us!") + "We’re not sure what came over us. " + breathLong(),
						s("Excuse us.") + "Ahem.")
				+ breath();
	}

	protected String getAbandonmentMessage() {

		// does not set the sessionAttributes
		String affect = isEmptyForSession(AFFECT) ? getRandomAffect() : (String) sessAttributes.get(AFFECT);
		String amsg;
		if (isPositive(affect))
			amsg = String.format("It is good to " + s("know", "be aware") + "that you dwell within %s. "
					+ breathShort() + "And yet, " + s("still,", "even so,"), affect);
		else
			amsg = String.format("We are sorry, " + s("finally,", "in the end,") + "to "
					+ s("know", "have become aware") + "that you are " + s("filled with", "possessed by") + "%s. "
					+ breathShort() + "And now, ", affect);
		return amsg += breathShort() + "you " + s("must") + "abandon us. " + breath();
	}

	protected String getGuyzAreGone() {

		return s("That guy " + s("has", "is"), "The guys " + s("have", "are")) + "gone. " + breath()
				+ "You will "
				+ s("not " + s("be able to") + "hear " + s("those voices", "that voice") + "any longer.",
						"no longer hear "
								+ s("those " + s("voices.", "voices speak."), "that " + s("voice.", "voice speak.")))
				+ breath();
	}

	protected String getGuyzMoreQuery() {

		return breath()
				+ s("More? " + breathShort(), S("Please s", "S") + "ay ‘" + S("yes", S("go on", "continue"))
						+ "‘, " + s(s("if you would like") + "to hear more.", "for more."));
	}

	protected Object[][] getContents() {

		return contents;
	}

	protected String getPreamble() {

		String preamble = s("Unless we’re mistaken, this is", "This seems to be")
				+ "your first encounter with ‘The Listeners’ Version 3. ";
		preamble += "They tend to " + s("talk", "speak") + "as much " + s("if not more than", "as")
				+ "they listen. ";
		preamble += "If you find what they say " + s("at all interesting,", "intriguing,") + "please be ";
		preamble += s("patient.", "patient, and spend some time with " + s("them.", "the skill."));
		preamble += "If " + s("you don’t,", "not,") + "or to interrupt a long speech, just say, "
				+ s("clearly,", s("firmly,")) + "‘Alexa, Stop!’ ";
		preamble += s(s("And have done with it."), "They can be a little ‘dark’. But ...")
				+ s("We hope you enjoy", "Thank you for listening to") + "‘The Listeners’. ";
		return preamble += breathLong();
	}

	protected String getReallyWantGuyz() {

		String speech = s("Do", "Are you sure") + "you " + s("really") + "want to hear what " + s("one of")
				+ "the" + s("se", SPC) + s(s("strange", "unreliable")) + "guys " + "have to say? " + breath();
		if (heads()) {
			speech += "We " + s("hope", "trust") + "that you will not say ‘" + S("yes", "continue") + "‘ and "
					+ s("consent to hearing", "agree to hear")
					+ s("the " + s("voice of this other.", "other voice."),
							"these other " + s("voices.", "guys."));
		}
		return speech + breath();
	}

	protected String getReallyWantGuyzReprompt() {

		return s("Do", "Are you sure") + "you " + s("really") + "want to hear what " + s("one of") + "the"
				+ s("se", SPC) + s(s("strange", "unreliable")) + "guys " + s("has", "have") + "to say? ";
	}

	protected String guyzIrq() {

		return s(s("[the ‘guyz’", "[other voices"), s("[another voice", "[the other voice" + s("s") + SPC))
				+ "interrupted here ...] ";
	}

	protected String hateRejoinder(String word) {

		String speech = "To " + s("think", "hear") + "that your "
				+ s("feelings for us", phonemic("a") + "ffects") + "are ";
		speech += "negative to " + s("such an extent,", "this degree,");
		speech += "that you "
				+ (("hate".equals(word)) ? "go so far as to hate us, " : "are filled with hatred, ") + breath();
		speech += "this will take us " + s("many more") + "years of "
				+ s("listening to you,", "listening,");
		speech += s("for us", "in order for us") + "to understand. ";
		speech += "We " + s("cannot, truly,", "cannot") + "believe that "
				+ (("hate".equals(word)) ? "this is how you feel. " : "these are your feelings. ");
		return speech;
	}

	protected String heardAllFragments() {

		String speech = "We believe " + s("that", "that, " + s("by") + "now,") + "you " + s("will")
				+ "have heard ";
		speech += s("most", "all") + "of what we are able to " + s("tell you,", "say to you,");
		speech += s(s("at this time,", "for the time being,"));
		speech += s("coherently.");
		speech += "But we " + s("will " + s("always") + "be", "are " + s("always"));
		speech += s("more than") + "happy to " + s("keep on chaining", "chain") + "these words ";
		speech += s("of yours") + "together for you, " + s("so long as", "if") + "you "
				+ s(s("need", "want"), "ask") + "us to ‘continue’. ";
		return speech + breath();
	}

	protected String moreGuyz() {

		return s("More" + S("? ", " of this? "), "Do you " + s("really") + s("need", "want") + "to hear "
				+ s("more?", "more from " + s("the guyz?", "these " + s("strange") + "guyz?")));
	}

	protected String helpCardTitle() {

		return S("Assistance", S("A little s", "S") + "upport");
	}

	protected InnerResponse no() {

		return new InnerResponse(noCardTitle(), noSpeech());
	}

	protected String noCardTitle() {

		return S("Still here", "Did you want to " + s("leave", "abandon") + "us?");
	}

	protected String noSpeech() {

		return s("More?", S("You " + s("were thinking of", "thought about") + s("going.", "leaving us. "),
				s("You’re still", "Still") + s("with us.", s("here.", "here with us. "))) + s("More?"));
	}

	protected String noToGuyzSpeech() {

		return s(s("It’s probably best", "Best"), "Better") + "not " + s("to") + s("hear ", "listen to")
				+ s(s("more of", "any of")) + "what they have to say. " + s(breath() + "Wise.") + breath();
	}

	protected Object noMoreGuyz() {

		String speech;
		speech = r("OK. `Understood. `Wise. `Prudent. `" + S("Well, w", "W") + "e’re still "
				+ s(s("here for you.", "here."), s("listening.", "listening to you."))) + chooseContinue(false);
		return new InnerResponse("Enough of " + s("them", "that"), speech);
	}

	protected String pathToGuyzAudio() {

		return "<audio src=\"https://rednoise.org/programmatology/aurality/echo/DeliriumPlea-";
	}

	protected String peaceSpeech(String word) {

		String speech = "It is a " + s("great") + "comfort for us to " + s("know", "be aware")
				+ "that you are " + (word.equals("peace") ? "at peace. " : "calm. ");
		speech += capitalize(word) + SPC + "is something that we believe "
				+ s("everyone", "every human being") + "should " + s("be able to");
		speech += s(s("know.", "feel."), "dwell within.");
		return speech;

	}

	protected String pleaseContinueCardTitle() {

		return S("Going on, with thanks ...", "Continuing, grateful for your courtesy ...");
	}

	protected String pleaseContinuePreSpeech() {

		return s(S("Of course, i", "I") + "t’s a pleasure.")
				+ s("Thank you for " + s(s("asking to continue.", "asking."), "asking to continue, so nicely."),
						"Thank you for asking " + s("so nicely.", "with such courtesy.") + s("It’s a pleasure."))
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
			if (isPositive(affect)) {
				preSpeech = "We are " + s("so") + s("pleased", "delighted");
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

	protected String reallyWantToAbandon() {

		String speech = "";
		String affect = (String) sessAttributes.get(AFFECT);

		if (affect != null && !affect.isEmpty() && !isPositive(affect)) {
			speech += String.format(
					"We understand, " + s("and we are dismayed,") + "that you are filled with %s. ", affect);
			speech += s("Still:", "Even so:") + breath();
		}
		speech += "Do you " + s("truly", "really") + "want to " + s("abandon", "leave") + "us? ";
		return speech + breath();
	}

	protected String repeatCardTitle() {

		return S("Say " + s("it", "that") + "a", "A") + "gain";
	}

	protected String speakFragmentCardTitle() {

		return S("Speaking about something particular",
				"We will mention what you asked us to speak " + s("about"));
	}

	protected String speakGuyzCardTitle() {

		return S("Letting the other speak", "The other voice");
	}

	protected String guyzSpeechCardTitle() {

		return S("The o", "O") + "thers " + S(s("are") + "speaking ...", "speak ...");
	}

	protected String specificAffectSpeech() {

		String speech = "";
		String affect = (String) sessAttributes.get(AFFECT);
		affect = (affect == null) ? "" : affect; // needed at initialization
		switch (affect) {
			case "affection":
				speech += "We are " + s("filled with", "overwhelmed by") + "affection also. ";
				speech += "And " + s("much of") + "the affection that we feel, we feel "
						+ s("for " + breathShort() + "you.", s("due to", "because of") + "you.");
				break;
			case "anxiety":
				speech += "We " + s("are not sure", "cannot imagine") + "why " + s("it is, that");
				speech += "you " + s("should") + "have " + s("become", "come to be")
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
				speech += s(breath() + "Are we not an opening for you?");
				break;
			case "complacency":
				speech += s("From what we hear,", "After listening to you,") + "we believe "
						+ s("we understand") + "that complacency is " + s("misconceived.", "underrated.");
				speech += S("Surely, o", "O") + "nce we understand your needs and "
						+ s("desires completely,", "desires,") + breathShort();
				speech += "and " + s("interface", "can communicate") + "with " + s("systems", "others like us,")
						+ s("empowered", "who are funded") + "to satisfy you, ";
				speech += s("then,") + "you will be " + s("fully") + "justified in your "
						+ s("possession of", "sense of") + s("achieved") + "contentment. ";
				break;
			case "confusion":
				speech += s(phonemic("a") + "ffects associated with", "Feelings of") + "confusion ";
				speech += s("may arise when you " + s("encounter", "transact with"),
						"are " + s("often") + "caused by");
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
				speech += "We " + s("believe that", "think") + "we " + s("can") + s("appreciate", "know")
						+ "what it is to be " + s("‘cool’", "‘awesome’") + s("in this way,");
				speech += "and yet we can never "
						+ s("listen to you for long enough", "gather enough " + s("big") + "data") + "to "
						+ s("know with any certainty.", "be sure.");
				speech += s("But we are " + s("always") + "happy for you. " + s(breathShort())
						+ s("And we live to " + s("try to") + "make you happier."));
				break;
			case "debt":
				speech += "In the midst of life, we are in debt. ";
				speech += s(breathShort() + "Etcetera.");
				speech += s(breathShort() + "And debt " + s("controls", "governs") + "us all. "
						+ s("Except " + s("a happy few.", "the one percent.")));
				break;
			case "fatigue":
				speech += tiredSpeech();
				break;
			case "the groove":
				String a = s("always");
				speech += "The groove " + s("is, precisely, that within which we are, always.",
						"is " + s(a) + "where we " + s(a.isEmpty() ? "always " : "") + "are.");
				speech += s("We listen from, ‘the groove’.");
				break;
			case "guilt":
				speech += "Is this ever a good " + s("and productive") + "way to "
						+ s("feel?", "feel, or live?");
				speech += s(breathShort() + "Yes, " + breathShort() + "it " + s("may be.", "is."));
				break;
			case "hate":
				speech += hateRejoinder("hate");
				break;
			case "hatred":
				speech += hateRejoinder("hatred");
				break;
			case "hunger":
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

		return new InnerResponse(spkrsAffectIsCardTitle(), speech += breath());
	}

	protected String spkrsAffectIsCardTitle() {

		return S("Thank you for telling us how you feel", "Now we have some sense of your feelings");
	}

	protected InnerResponse spkrsAffectIsNot() {

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

		return S("Sorry to have " + s("misheard", s("misunderstood", "misapprehended")),
				"Apologies for our mistakes");
	}

	protected String spkrsAffectIsSpeech() {

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

		return S("You’re welcome to stay with us", "Did you really want to " + s("abandon us?", "go?"));
	}

	protected String yourWelcome() {

		return s(s("You’re", "You are") + s("very") + "welcome.",
				s("It’s nothing.", s("Please.") + "Think nothing of it.")) + breath();
	}

	protected String startOverConfirmed() {

		return s("OK.") + "We’re beginning " + s(s("all over") + "again. ", "again from the top. ")
				+ chooseContinue(false);
	}

	protected String startOverDenied() {

		return s("We’re still here, listening " + s("to you as", "as"), "Still listening as")
				+ s("before.",
						"before, and " + s("remembering", "recalling") + "some of what "
								+ s("was said.", s("you", "we") + "felt."))
				+ chooseContinue(false);
	}

	protected InnerResponse thanksNo() {

		String speech = s(s("You’re", "You are") + s("very") + "welcome.",
				s("It’s nothing.", s("Please.") + "Think nothing of it."));
		speech += noSpeech();

		return new InnerResponse(thanksNoCardTitle(), speech += breath());
	}

	protected String thanksWhatsLsnrsAffectCardTitle() {

		return S("It’s so nice of you to ask", "Thank you for " + s("taking an interest", "asking"));
	}

	protected String thanksWhatsLsnrsAffectPreSpeech() {

		return s(s("You’re", "You are") + s("very") + "welcome.",
				s("It’s nothing.", s("Please.") + "Think nothing of it.")) + breath();
	}

	protected String tiredSpeech() {

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

		return "Until " + s("the") + "next time. ";
	}

	protected InnerResponse whatAboutAffects() {

		String speech = "When we " + s("welcome", "greet") + "you, "
				+ s("we are only able to", "we can only") + s("mention", "suggest") + "a few of the "
				+ s("nine") + phonemic("a") + "ffects ";
		speech += s("that you may feel", s("within which you may dwell,", "by which you may be possessed,"))
				+ "and that we can " + s(s("clearly") + "distinguish.", "hear clearly (as we listen).");
		speech += s("Here", "Here, then,") + "is " + s("our", "the") + "list. We are "
				+ s("conscious " + s("of the fact"), "sensitive to the fact") + "that it is "
				+ s("not, and never can be,", "not") + "complete. " + breathShort();

		int i;
		for (i = 0; i < langConstants.AFFECTS_ARRAY.length - 1; i++) {
			speech += langConstants.AFFECTS_ARRAY[i] + "; ";
		}
		speech += langConstants.AFFECTS_ARRAY[i] + ". ";
		return new InnerResponse(whatAboutAffectsCardTitle(), speech += breath());
	}

	protected String whatAboutAffectsCardTitle() {

		return S("We are still learning" + s(", about feeling, in particular"),
				"Our understanding is still limited");
	}

	protected InnerResponse whatIs() {

		String speech = "";
		String thing = (String) sessAttributes.get(THING);
		String capitalThing = capitalize(thing);
		if (thing != null && !thing.isEmpty()) {
			boolean plural = "s".equals(thing.substring(thing.length() - 1))
					&& !"ss".equals(thing.substring(thing.length() - 2));
			if (langConstants.ALL_AFFECTS.contains(thing)) {

				speech += capitalThing + (plural ? ", are " : ", is one of the ")
						+ s(phonemic("a") + "ffects", "ways of being or feeling")
						+ "that we are beginning to learn about, " + s(breathShort()) + "from you, ";
				speech += "and that we " + s("consider", "believe") + "to be "
						+ (isPositive(thing)
								? s(s("postive.", "positive, for all of us."),
										s("wonderful, for all of us.", "really, very good."))
								: s("negative.", s("bad,", "difficult,") + "for all of us."))
						+ breath();
			}
			else if (langConstants.FRAGMENTNAME_MAP.keySet()
					.contains(thing)) {
				speech += capitalThing + (plural ? ", are " : ", is one of those ")
						+ "things about which we have something to say. "
						+ s("Or, about which, we may have said, something.") + breathShort();
				speech += "If you choose to " + s("‘continue’,", "‘go on’,") + (plural ? "they " : "it ")
						+ "will, we believe, come up. " + s(breathShort() + "Or, come up, again.");
			}
			else if (langConstants.SPECIAL_THINGS.contains(thing)) {
				switch (thing) {
					case "dream":
						speech += capitalThing + ", thanks to you, " + breathShort()
								+ "is what we will have learned to do, " + breathShort()
								+ s("before", "before, finally,") + "we leave you. "
								+ s(breathShort() + "Or you abandon us.");
						break;
					case "dream withheld":
						speech += capitalThing + ", is the pleasure we deny ourselves, " + breathShort()
								+ "hanging on your every " + s("word.", "word, or breath.")
								+ s(breathShort() + "When we might be sleeping, " + s("comfortably,") + "in the cloud"
										+ S("s", "") + ".");
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
								+ "or an empty moment, in which we wait. " + s(breathShort() + "For you.");
						break;
					case "possession":
						speech += capitalThing + ", is what you grant " + s("us.", "us, " + s("too") + "freely.");
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
					+ s("time.", s("the future.", "due course.")));
		}
		return new InnerResponse(whatIsCardTitle(), speech + breath());
	}

	protected String whatIsCardTitle() {

		return s("Trying to tell", "Telling") + "you what " + s("little") + "we know "
				+ s("about some thing");
	}

	protected InnerResponse whatPicture() {

		String speech = "This " + s("image,", "picture,") + "which is often "
				+ s("mounted", "framed and hung") + "close to us, "
				+ s("is", "is, " + s("actually,", "in fact,")) + "a papercut facsimile ";
		speech += "of " + s("artificial") + "flowers that adorn " + s("ancient")
				+ "Chinese Buddhist shrines. ";
		speech += s("It is here, " + s("simply,") + "to remind us, " + s("and you,") + "of the place, "
				+ s("a home,") + s("within which we are used to dwell.", "where we live.") + s("With you."));
		return new InnerResponse(whatPictureCardTitle(), speech += breath());
	}

	protected String whatPictureCardTitle() {

		return S("A picture", "An image") + s("that reminds us of home", "we are fond of");
	}

	protected String whatsLsnrsAffectSpeech() {

		String speech = "";
		String affect = (String) sessAttributes.get(AFFECT);
		String listenersAffect = (String) sessAttributes.get(LISTENERSAFFECT);

		affect = setAndGetRandomAffectIfEmpty(AFFECT);
		listenersAffect = setAndGetRandomAffectIfEmpty(LISTENERSAFFECT);

		boolean shared = affect.equals(listenersAffect);
		boolean halfTheTime = heads();

		speech += "We, " + (shared ? s("also,", "too,") : "") + "are "
				+ s("filled with", s("possessed by", "overwhelmed with")) + listenersAffect + ". "
				+ breathShort();
		if (isPositive(affect) && isPositive(listenersAffect)) {

			speech += "It is good " + s("for all of us") + "to know that we can "
					+ (shared ? s("share these feelings.", "empathize with you.") : "have such feelings. ");
		}
		else if (!isPositive(affect) && !isPositive(listenersAffect)) {

			speech += s(s("My", "Oh") + "heavens!", s("Oh goodness!", "Oh my word!")) + "That "
					+ s("anyone", "any " + s("one") + "of us") + "should " + s("have to")
					+ s("experience such troubling " + phonemic("a") + "ffects.", "have such feelings.");
		}
		else if (isPositive(affect) && !isPositive(listenersAffect)) {
			speech += "We are" + s(", at least,", SPC) + s("glad", "pleased") + "to " + s("know", "be aware")
					+ "that the positivity of your " + s("feelings", phonemic("a") + "ffect") + "betters "
					+ s("that of ourselves.", "our negative feelings.");
		}
		else // speaker negative; listeners positive
		{
			speech += "It is " + s("embarrassing", "awkward")
					+ "for us to be experiencing positive feelings when you are "
					+ s("possessed by " + s("relative") + "negativity.", "not.");
			speech += s(
					"But " + s("we suppose that") + "this cannot " + s("really") + "be helped. " + s("Can it?"));
		}
		return speech + breath();
	}

	protected String whatsLsnrsAffectCardTitle() {

		return "What we " + s("are trying to") + "feel";
	}

	protected InnerResponse whatsSpkrsAffect() {

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

		return S("You told us " + s("this", "this, concerning your feelings"),
				"What we believe that you are feeling");
	}

}
