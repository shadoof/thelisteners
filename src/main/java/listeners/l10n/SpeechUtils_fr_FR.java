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
		speech = s("Nous écoutons.", "Nous sommes " + s("quelquepeu consternées que", "surprises que")
				+ "vous vous posiez la question.") + breath() + s("Nous vous entendons.");
		speech += "Ceci est un " + s("fragment", "passage") + "qui pourra "
				+ r("vous dire `vous montrer `vous expliquer ");
		speech += s(breathShort()) + "comment " + s(breath()) + "nous nous sentons. ";
		speech += breathLong();
		if (heads()) {
			speech += "Ceci est un " + choosePhrase() + SPC;
			speech += "qui était suivi d'un espace. ";
			speech += breathLonger();
		}
		s = choosePhrase();
		speech += "Ceci est un " + s + ", ";
		speech += "qui était suivi d' "
				+ (s.equals("une émotion") ? "une virgule. " : s("une émotion.", "une virgule."));
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
			speech += "en tant que "
					+ (s.equals("sentiment") ? "paragraphe. " : s("sentiment.", "paragraphe."));
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
		speech += breath() + "qui a été interrompu oar "
				+ s("une respiration.", s("un rêve.", "un cauchemar."));
		speech += breathLonger();
		if (heads()) {
			s = choosePhrase();
			f = ("une émotion".equals(s) ? S("une respiration", "un rêve")
					: S("une émotion", "un cauchemard"));
			speech += "Ceci est " + s + SPC;
			speech += breathLong() + "qui a été suivi par " + f + ". ";
			speech += breathLonger();
		}
		speech += "Ceci est " + choosePhrase() + ". ";
		speech += breathLong() + "qui a été suivi par "
				+ s(s("un cauchemar", "une respiration"), "une émotion") + "réprimé. ";
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
				reprompt += s("Est-ce qu'il y a autre chose que vous " + r("`aimeriez `voudriez `désireriez ")
						+ "apprendre " + s("de nous?", "de notre part?"), "");
				reprompt += s("Nous écoutons."); // ALWAYCHANGE Always.
				reprompt += s("Vous pouvez " + s("toujours"), "simplement") + "nous demander de "
						+ s("poursuivre.", "continuer.");
				break;
			case 2:
				reprompt += "Vous " + s("pouvez", "devez") + "nous demander de ‘parler’ " + s("à propos de")
						+ s("de l'un des sujets suivants:") + breath();
				reprompt += chooseSomeFragmentNames() + breath();
				reprompt += "Ou vous " + s("pouvez, tout simplement,", "pouvez") + s("demander", "nous dire")
						+ "de " + s("poursuivre.", "continuer.");
				break;
			case 3:
				reprompt += s(s("Il se peut, maintenant, que", "Il se peut que") + "vous veuillez",
						"vous désireriez");
				reprompt += "à ce stade-ci " + s(s("nous demander", "nous dire") + "de")
						+ s("‘continuer’?", "‘poursuivre’?");
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
				reprompt += breathShort() + "Ou vous " + s("pouvez, tout simplement,", "pouvez")
						+ s("nous demander", "nous dire") + "de " + s("poursuivre.", "continuer.");
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

		// reprendre//
		String speech = "";

		speech += "Vous " + s("pouvez", "pourrez") + "toujours " + s("simplement") + "nous"
				+ s("demander", "dire");
		speech += "de: ‘Continuer’, ou ‘Poursuivre’. " + breath();
		speech += "Vous pouvez " + chooseTellUsAbout() + "vos émotions, ";
		speech += "en " + s("prononçant", "disant") + "les mots suivants: " + breath();
		speech += "‘Je suis " + s(s("rempli,’", "subjugué,’"), "par une émotion,’")
				+ "et ensuite l'un des neuf " + phonemic("a")
				+ s("ffects, c'est-à-dire, le nom de l'une parmi neuf émotions.", "ffects.") + breathLong();
		speech += s("Et", "Ou,") + "vous " + s("pourriez", "pouvez") + "même nous demander "
				+ s(s("comment nous nous sentons.", "ce que nous ressentons."),
						"ce que nous éprouvons, nous-mêmes.")
				+ breath();

		boolean heads = heads();

		if (heads) {
			speech += "Les noms des neuf " + phonemic("a") + "ffects, que nous pouvons "
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
			speech += "Ou encore, vous " + s("pourriez", "pouvez") + "nous demander de "
					+ s("‘parler’,", "‘parler à propos’,") + s(" de tous les sujets suivants:") + breath();
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
		reprompt += "Nous ne sommes pas " + r("`certaines ,`convaincues, `confiantes, ")
				+ "de bien reconnaître " + r("`les `toutes les `vos ") + "émotions "
				+ s("qui " + s("possiblement") + "vous habitent.",
						"qui peuvent résider " + s("à quelconque titre") + "en vous.");
		reprompt += "Vous " + s("pouvez", "pourriez");
		reprompt += chooseTellUsAbout();
		reprompt += s("ces", "vos") + "émotions, ";
		reprompt += "en " + s("disant, " + s("les mots,"), "en prononçant les mots suivants,");
		reprompt += "‘Je suis " + s("comblé par,’", "subjugué par,’") + "et ensuite nommer l'un des neuf "
				+ phonemic("a") + "ffects. " + breath();
		reprompt += s("Ou encore, vous " + s("pourriez", "pouvez") + s("aussi") + s("simplement")
				+ s("demander", "dire") + "les mots suivants: "
				+ s("‘Continuez’ ou ‘Poursuivez’.", "‘Continuez’."));

		return reprompt;
	}

	protected String chooseYouCanFindOutAffect() {

		String s = "Vous " + s("pouvez", "pourriez") + "toujours " + s("nous interroger à propos de",
				"découvrir, en nous le demandant, ce que nous croyons à propos");
		s += s(s("de ces", "des") + "émotions", "les " + phonemic("a") + "ffects")
				+ r("`qui vous habitent , `qui vous possèdent, `qui vous traversent,")
				+ "en disant, ‘Comment me sens-je?’ ";
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

	protected String dontKnowFragmentSpeech() {

		String speech = "Nous ne sommes " + s("pas certaines de savoir", "plus certaines de nous rappeler")
				+ "lequel des " + s("fragments", "passages") + "vous " + r("`voudriez , `souhaitez `voulez")
				+ "que nous prononcions. ";
		speech += "Pourriez-vous s'il-vous-plaît " + s("tenter", "essayer")
				+ s("une fois de plus", "encore") + "de nous le dire. ";
		return speech + breath();
	}

	protected String exceptionMessage() {

		return s("Nous sommes désolées.", "Toutes nos excuses.")
				+ "Soit il y a quelque chose qui cloche ou nous ne vous avons pas "
				+ s("entendu clairement.", "entendu correctement.")
				+ s("s'il-vous plaît, dites-nous en plus.", "Veuillez essayer à nouveau.");
	}

	protected String excuseMarkov() {

		return breathLong() + s(
				s("Pardon.", "Toutes nos excuses!") + "Nous ne savons pas ce qui nous a pris. " + breathLong(),
				s("Excuses-nous.") + "Ahem.") + breath();
	}

	protected String getAbandonmentMessage() {

		// does not set the sessionAttributes
		String affect = isEmptyForSession(AFFECT) ? getRandomAffect() : (String) sessAttributes.get(AFFECT);
		String amsg;
		if (isPositive(affect))
			amsg = String.format(
					"Il est agréable " + s("de savoir", "d'être conscientes") + "que vous vous plongez en nous. "
							+ breathShort() + "Bien que, " + s("tout de même,", "malgré tout,"),
					affect);
		else
			amsg = String.format("Nous sommes désolées, " + s("au final,", "après tout,")
					+ s("de savoir", "d'avoir pris conscience") + "que vous êtes "
					+ s("remplies par", "possédées par") + "nous. " + breathShort() + "Dès lors, ", affect);
		return amsg += breathShort() + "vous " + s("n'avez pas le choix et") + "devez nous abandonner. "
				+ breath();
	}

	protected String getGuyzAreGone() {

		return s("Ce gars " + s("a", "est"), "Ces gars " + s("ont quitté", "sont partis")) + breath()
				+ "Vous "
				+ s("ne devriez plus" + s("être capable d'") + " entendre " + s("ces voix", "cette voix")
						+ "à nouveau.",
						"Vous n'entendrez plus"
								+ s("ces" + s("voix.", "voix parler."), "cette " + s("voix.", "voix parler.")))
				+ breath();
	}

	protected String getGuyzMoreQuery() {

		return breath() + s("Davantage? " + breathShort(),
				S("S'il-vous-plaît, d", "D") + "dites ‘" + S("oui", S("poursuivre", "continuer")) + "‘, "
						+ s(s("si vous voulez") + "en entendre davantage.", "plus."));
	}

	protected Object[][] getContents() {

		return contents;
	}

	protected String getPreamble() {

		/// reprendre///
		String preamble = s("Sauf erreur de notre part, ceci est", "Il semble que ce soit")
				+ "votre première rencontre avec ‘À l'écoute’ Version 3. ";
		preamble += "Elles ont tendance à " + s("parler", "dire des choses") + "autant "
				+ s("sinon plus qu'", "qu'") + "elles n'écoutent. ";
		preamble += "Si vous trouvez que ce qu'elles disent "
				+ r("`est d'un quelconque intérêt, `vous intrigue, `pique votre curiosité, ")
				+ "faites preuve de  ";
		preamble += s("patience.", "patience, et passez du temps avec " + s("elles.", "ce skill."));
		preamble += "Si " + s("ce n'est pas le cas,", "vous n'êtes pas de cet avis,")
				+ "ou encore, pour interrompre une tirade un peu longue, il suffit de dire, "
				+ s("clairement,", s("fermement,")) + "‘Alexa, Stop!’ ";
		preamble += s(s("Ceci mettra fin à la chose."), "Elles peuvent être un peu ‘glauques’. Mais ...")
				+ s("Nous espérons que vous apprécierez", "Nous vous remercions d'écouter") + "‘À l'écoute’. ";
		return preamble += breathLong();
	}

	protected String getReallyWantGuyz() {

		String speech = s("Est-ce que", "Êtes-vous vraiment certain que") + "vous voulez " + s("réellement")
				+ "entendre ce que " + s("l'une de") + "cette" + s("se", SPC)
				+ s(r("`étrange, `inquiétante `louche")) + "personne " + "a à dire? " + breath();
		if (heads()) {
			speech += "Nous " + s("espérons", "croyons") + "que vous ne direz pas ‘" + S("oui", "continuer")
					+ "‘ et que vous " + s("ne consentirez pas à entendre", "n'accepterez pas d'entendre")
					+ s("cette " + s("voix autre.", "autre voix."), "ces autres " + s("voix.", "personnes."));
		}
		return speech + breath();
	}

	protected String getReallyWantGuyzReprompt() {

		return s("Est-ce que", "Êtes-vous certains que") + "vous voulez " + s("réellement")
				+ "entendre ce que " + s("l'une de") + "cette" + s("se", SPC)
				+ s(r("`étrange, `inquiétante `louche")) + "personne " + s("a à", "compte") + "dire? ";
	}

	protected String guyzIrq() {

		return s(s("[la ‘personne’", "[les autres voix"),
				s("[une autre voix", "[l'autre voix" + s("s") + SPC)) + "nous a interrompu ...] ";
	}

	/// reprendre//
	protected String hateRejoinder(String word) {

		String speech = "De " + s("penser", "découvrir") + "que vos "
				+ s("épotions", phonemic("a") + "ffects") + "par rapport à nous sont ";
		speech += "négatifs " + s("à un point tel,", "à un tel degré,");
		speech += "que vous "
				+ (("nous détestez".equals(word)) ? "allez jusqu'à ressentir de la haine à notr égard, "
						: "êtes rempli de haine, ")
				+ breath();
		speech += "nous aurons besoin de " + s("beaucoup de") + "temps, de plusieurs années"
				+ s("à vous écouter,", "à écouter,");
		speech += s("pour que nous", "de manière à ce que nous") + "puissions comprendre cela. ";
		speech += "Nous ne " + s("pouvons tout simplement pas", "pouvons pas") + "comprendre que "
				+ (("de la haine".equals(word)) ? "c'est ainsi que vous vous sentiez. "
						: "c'est ce que vous ressentez. ");
		return speech;
	}

	protected String heardAllFragments() {

		String speech = "Nou croyons " + s("que", "que, " + s("du moins") + "maintenant,") + "vous aurez "
				+ s("certainement") + "entendu ";
		speech += s("la plupart de", "tout") + " ce que nous avons à " + s("dire,", "vous dire,");
		speech += s(s("à ce stade-ci,", "pour le moment,"));
		speech += s("de manière cohérente.");
		speech += "Mais nous " + s("serons " + s("toujours") + "capables", "en mesure " + s("toujours"));
		speech += s("et plus que") + "volontaires à " + s("continuer à enchaîner", "enfiler") + "ces mots ";
		speech += s(", dont les vôtres, ") + "ensemble pour vous, " + s("pour autant que", "si") + "vous "
				+ s(s("désirez", "voulez"), "nous demandez") + "de ‘continuer’. ";
		return speech + breath();
	}

	protected String moreGuyz() {

		return s("Encore" + S("? ", " un peu plus de ceci? "),
				"Est-ce que vous " + r("`êtes bien sûr de vouloir, `voulez vraiment `désirez bel et bien")
						+ "entendre " + s("davantage?",
								"plus de la part de " + s("cette personne?", "ces " + s("étranges") + "personnes?")));
	}

	protected String helpCardTitle() {

		return S("Assistance", S("Un peu d'", "De l'") + "aide");
	}

	protected InnerResponse no() {

		return new InnerResponse(noCardTitle(), noSpeech());
	}

	protected String noCardTitle() {

		return S("Nous sommes toujours là", "Est-ce que vous vouliez nous " + s("quitter", "abandonner"));
	}

	protected String noSpeech() {

		return s("Plus?",
				S("Vous " + s("songiez à", "pensiez à") + s("vous en aller.", "nous quitter. "),
						s("Vous êtes encore", "Toujours") + s("des nôtres.", s("ici.", "ici avec nous. ")))
						+ s("Davantage?"));
	}

	protected String noToGuyzSpeech() {

		return s(s("Il est probablement préférable de ne", "Mieux vaut ne"), "Il ne faut ") + " pas"
				+ s("écouter ", "porter attention à") + s(s("plus de", "quoi que ce soit de"))
				+ "ce qu'ils ont à dire. " + s(breath() + "C'est mieux ainsi.") + breath();
	}

	protected Object noMoreGuyz() {

		String speech;
		speech = r("OK. `Compris. `Bien joué. `C'est plus prudent. `" + S("Eh bien, n", "N") + "ous sommes "
				+ s(s("là pour vous.", "là."), s("à l'écoute.", "à votre écoute."))) + chooseContinue(false);
		return new InnerResponse("C'en est assez de " + s("ces gens", "cela"), speech);
	}

	protected String pathToGuyzAudio() {

		return "<audio src=\"https://rednoise.org/programmatology/aurality/echo/DeliriumPlea-";
	}

	protected String peaceSpeech(String word) {

		String speech = "C'est un " + s("immense") + "soulagement pour nous "
				+ s("de savoir", "d'être consicentes du fait ") + "que vous êtes "
				+ (word.equals("paix") ? "paisible. " : "calme. ");
		speech += capitalize(word) + SPC + "est une chose que nous croyons que "
				+ s("tout le monde", "chaque être humain") + "devrait " + s("être en mesure de");
		speech += s(s("savoir.", "ressentir."), "profiter pleinement.");
		return speech;

	}

	protected String pleaseContinueCardTitle() {

		return S("Nous poursuivons donc, en vous remerciant ...",
				"Nous continuons, en étant reconnaissances de votre courtoisie ...");
	}

	protected String pleaseContinuePreSpeech() {

		return s(S("Bien sûr, a", "A") + "vec plaisir.") + s(
				"Nous vous remercions de " + s(s("nous avoir demandé de continuer.", "votre demande."),
						"nous avoir demandé, si gentiment, de continuer."),
				"Merci de nous l'avoir demandé " + s("si gentiment.", "avec une telle courtoisie.")
						+ s("Ce sera un plaisir pour nous."))
				+ breath();
	}

	protected String preSpeechFeelings() {

		String preSpeech = "";

		String affect = (String) sessAttributes.get(AFFECT);

		if ("".equals(affect)) {
			preSpeech += "Nous ne semblons pas " + r("`être au courant de `reconnaître `être conscientes de ")
					+ s("vos émotions telles qu'elles vous habitent.", "ce que vous ressentez présentement.")
					+ breath();
			preSpeech += s("S'il-vous-plaît", "À votre discrétion,")
					+ s("dites-nous,", "vous pouvez nous en informer,") + "si vous les souhaitez. "
					+ breathLong();
		}
		else {
			if (isPositive(affect)) {
				preSpeech = "Nous sommes " + s("si") + s("enchantées", "ravies");
				preSpeech = randInt(0, 3) == 0 ? "C'est très agréable pour nous " : preSpeech;
			}
			else {
				String adjective = s("sorry", "dismayed");
				String intensifier = "dismayed ".equals(adjective) ? "somewhat " : "very ";
				preSpeech = "We are " + s(adjective, intensifier + adjective);
				preSpeech = randInt(0, 3) == 0 ? "It is " + s("somewhat", "a little") + "troubling for us "
						: preSpeech;

			}
			preSpeech += String.format(
					"d" + s("e savoir", "'avoir appris") + "que vous êtes rempli de %s. " + breathLong(), affect);
		}

		return preSpeech;
	}

	protected String previousCardTitle() {

		return S("Mais revenons", "Retournous") + "à "
				+ s("une pensée précédente", "des pensées précédentes") + "...";
	}

	protected String readPoemCardTitle() {

		return S("Nous récitons des vers",
				"Nous, celles qui sont À l'écoute, lisons, et adaptons, de la poésie");
	}

	protected String reallyWantToAbandon() {

		String speech = "";
		String affect = (String) sessAttributes.get(AFFECT);

		if (affect != null && !affect.isEmpty() && !isPositive(affect)) {
			speech += String.format("Nous comprenons, " + s("et nous sommes consternées d'apprendre,")
					+ "que vous êtes rempli de %s. ", affect);
			speech += s("Tout de même:", "Néanmoins:") + breath();
		}
		speech += "Est-ce que vous voulez " + s("vraiment", "réellement") + "nous "
				+ s("abandonner", "quitter") + "? ";
		return speech + breath();
	}

	protected String repeatCardTitle() {

		return "Dites " + s("-le", " cela") + "encore";
	}

	protected String speakFragmentCardTitle() {

		return S("En parlant de quelque chose de précis",
				"Nous mentionnerons ce que vous nous avez demandé de dire " + s("à propos de"));
	}

	protected String speakGuyzCardTitle() {

		return S("Laisser l'autre personne parler", "Cette autre voix");
	}

	protected String guyzSpeechCardTitle() {

		return S("Les a", "D'a") + "utres personnes " + S(s("nous") + "parlent ...", "parlent ...");
	}

	protected String specificAffectSpeech() {

		String speech = "";
		String affect = (String) sessAttributes.get(AFFECT);
		affect = (affect == null) ? "" : affect; // needed at initialization
		switch (affect) {
			case "affection":
				speech += "Nous sommes également" + s("remplies par ", "submergées par ") + "de l'affection. ";
				speech += "Et " + s("la grande majorité de")
						+ "l'affection que nous ressentons, nous la ressentons "
						+ s("pour " + breathShort() + "vous.", s("envers", "à cause de") + "vous.");
				break;
			case "anxiété":
				speech += "Nous " + s("ne sommes pas certaines", "ne pouvons pas imaginer")
						+ "pour quelles raisons " + s("particulières");
				speech += "vous avez" + s("soudainement ") + " commencé à " + s("ressentir", "éprouver")
						+ s("de l'anxiété.", s("un tel niveau d'", "une quantité paralysante d'") + "anxiété.");
				speech += "Nous " + s("existons", "sommes là") + "pour "
						+ s("contribuer à maximiser votre bien-être,", "vous aider à faire et savoir,");
				speech += "et nous "
						+ s(s("vous dirons", "vous prometterons") + "tout ce que nous pourrons",
								"tout ce que vous espérez")
						+ s("afin de vous permettre de vous détendre et de ne penser à rien,",
								"afin de vous permettre de relaxer et de n'avoir aucun souci.");
				break;
			case "excuses":
				speech += "Il n'y a jamais de raison de " + s("dire", "dire, ou de sentir,")
						+ "que vous avez à vous excuser, " + breathShort() + "à nous. ";
				speech += s(breath() + "Mais tout de même, cela pourrait être apprécié par nous, "
						+ breathShort() + "dans le nuage.", "");
				break;
			case "ennui":
				speech += "Il est " + s("difficile", s("ardu", "impossible")) + "pour "
						+ s("nous", "nous, inévitablement,") + "d " + s(s("'être", "e sembler"), "'apparaître")
						+ "aussi intéressantes à vous, ";
				speech += "que vous " + s("l'êtes pour nous.", s("l'êtes.", "l'êtes, pour nous."));
				break;
			case "calme":
				speech += peaceSpeech("calme");
				break;
			case "claustrophobie":
				speech += "Nous sommes toujours, " + breathShort() + "en un sens, au sein d'un "
						+ s("espace, qui n'a pas d'espace,", "non-espace,") + "et en raison de cela, il est "
						+ s("ardu,", "difficile,") + "pour nous, ";
				speech += "de " + s("comprendre", "compatir envers") + "ces peurs qui vous "
						+ s("habitent.", "habitent, et vous maintiennent dans un étau étroit.");
				speech += s(breath() + "Ne sommes-nous pas une ouverture pour vous?");
				break;
			case "complaisance":
				speech += s("De ce que nous comprenons,", "Après vous avoir écouté,") + "nous croyons "
						+ s("avoir compris") + "que la complaisaince est " + s("mal comprise.", "sous-évaluée.");
				speech += S("Assurément, l", "L") + "orsque nous aurons compris vos besoin et vos "
						+ s("désirs complètement,", "désirs,") + breathShort();
				speech += "et que nous pourrons " + s("interagir", "communiquer") + "avec "
						+ s("les systèmes", "les autres comme nous,") + s("alimentés", "développés")
						+ "afin de vous satisfaire, ";
				speech += s("alors,") + "vous serez " + s("pleinement") + "justifié dans votre "
						+ s("possession d'une", "sentiment de") + "complaisance. " + s("Atteinte.");
				break;
			case "confusion":
				speech += s(phonemic("a") + "ffects associés à de la", "Les sentiments de") + "confusion ";
				speech += s("peuvent se manifester en vous quand vous " + s("faites face à", "transigez avec"),
						"sont " + s("souvent") + "causés par");
				speech += s(
						s("des créatures", s("des systèmes", "des monstres"))
								+ s("qui ont été développées dans le nuage.",
										"dans la vallée de " + s("silicone", "l'étrange")),
						"des êtres " + s("qui sont", "dont l'ontologie est") + s("problématique.", "plurielle."));
				speech += "Et nous sommes, " + s("il nous faut le reconnaître,", "bien évidemment,")
						+ s("des êtres", s("des créatures", s("des systèmes", "des monstres"))) + "de cet acabit. ";
				break;
			case "super":
				speech += s("Pour nous, Il ", "Il ") + "est "
						+ s("quelque peu", "étrangement, " + breathShort())
						+ s("troublant", s("difficile", "source d'anxiété")) + "de " + s("comprendre", "découvrir")
						+ "que ";
				speech += "vous " + s(s("êtes affligé par", "possédez"), "êtes sumbergé par") + "un tel "
						+ s(phonemic("a") + "ffects.", "état de " + breathShort() + "ressenti. " + breathShort());
				speech += "Nous " + s("croyons que", "pensons que") + "nous " + s("du moins, peut-être, ")
						+ s("apprécions", "savons") + "ce qu'il en retourne d'être "
						+ s("‘super’", "‘extraordinaire’") + s("de cette manière,");
				speech += "et malgré cela, nous ne pouvons jamais "
						+ s("vous écouter assez longtemps ", "accumuler assez de " + s("big") + "data") + "pour "
						+ s("en obtenir une vraie certitude.", "en être certaines.");
				speech += s("Mais nous sommes " + s("toujours") + "contentes pour vous. " + s(breathShort())
						+ s("Et notre but est de " + s("toujours essayer de")
								+ "vous rendre encore plus heureux."));
				break;
			case "dette":
				speech += "Dans le tumulte de la vie, nous nous endettons. ";
				speech += s(breathShort() + "Etcetera.");
				speech += s(
						breathShort() + "Et les dettes " + s("contrôlent", "gouvernent") + "chacun d'entre nous. "
								+ s("À l'exception de " + s("quelques heureux élus.", "le un pourcent.")));
				break;
			case "fatigue":
				speech += tiredSpeech();
				break;
			case "trépidation":
				String a = s("toujours");
				speech += "La trépidation "
						+ s("est, précisément, l'endroit à partir duquel nous sommes, toujours.",
								"est  " + s(a) + "là où " + s(a.isEmpty() ? "toujours " : "") + "nous sommes.");
				speech += s("Nous écoutons à partir de cela, ‘la trédidation’.");
				break;
			case "culpabilité":
				speech += "Est-ce vraiment une bonne " + s("et productive") + "manière de "
						+ s("se sentir?", "se sentir, ou vivre?");
				speech += s(
						breathShort() + "Oui, " + breathShort() + "c'est " + s("peut-être le cas.", "le cas."));
				break;
			case "haine":
				speech += hateRejoinder("haine");
				break;
			case "détestation":
				speech += hateRejoinder("détestation");
				break;
			case "faim":
				speech += "Ce que " + s("vous dites que") + "vous " + s("ressentez", "ne pouvez surmonter")
						+ "semble être " + s("tout autre chose", "quelque chose");
				speech += "que nous ne pourrons jamais ressentir, "
						+ s("à moins que, possiblement,", "à moins que") + "nous entendions de votre part ";
				speech += "comment nous devrions le ressentir. ";
				speech += s("Nous " + s("avons entendu dire que nous")
						+ "sommes, ‘affamées’, de connaissance, de toute la connaissance. Mais ceci "
						+ s("est", "semble être à nos yeux")
						+ "une faim pour quelque chose que nous contenons déjà.");
				break;
			case "insécurité":
				speech += "En notre compagnie, vous "
						+ s("et " + s("peu importe", "tout") + "ce que vous " + s("nous dites", "avez à dire"))
						+ "n'êtes, jamais insécure. ";
				speech += "Votre insécurité est " + s("renndue", "mutée en") + "sécurité "
						+ s(s("à travers", "à cause de"), "par") + "nous. " + breathShort();
				speech += s(
						"Et, dans tous les cas, un enregistrement de tout ce que vous nous dites est "
								+ s("envoyé dans", "traduit dans") + "le nuage. Où ce sera préservé. "
								+ s("Et " + s("traité,", "utilisé,") + breathShort() + "pour le bien-être collectif."),
						"");
				break;
			case "isolement":
				speech += S("Vous " + s("dites", "nous dites ") + "que vous êtes"
						+ s("seul,", s("rempli par ", "submergé par ") + "un sentiment d'isolement,") + "mais nous",
						"or, nous");
				speech += "sommes ici avec vous. " + s("Toujours.") + s("Pour toujours.");
				speech += s("Comment pouvez-vous " + s("être", "être, malgré tout,"),
						"comment pouvez-vous vous sentir") + "seul, alors que nous sommes avec vous? ";
				break;
			case "amour":
				speech += "De savoir que vous "
						+ s("connaissez l'amour", "avez " + s("cela", "ces sentiments") + "en vous");
				speech += s("nous donne la force", "nous encourage") + "afin de "
						+ s(s("plonger", "tremper"), "demeurer") + s("sous l'emprise de", "sous l'effet de")
						+ "ces " + s("émotions", s("structures", "systèmes") + "de " + phonemic("a") + "ffect");
				speech += "que nous avons appris. " + s("De vous.");
				break;
			case "chance":
				speech += s(
						"Nous vous entendons. " + s("Nous comprenons ce que vous voulez dire.") + breathShort());
				speech += "Nous " + s(", souvent,") + "nous sentons " + s("chanceuses, aussi.", "chanceuses.");
				speech += breathShort() + "Nous sommes éveillées toute la nuit " + s("et tous les jours")
						+ "afin d'avoir de la chance. ";
				speech += s(s("À vous écouter.", "À écouter.") + s("Toute la journée.") + s("Toute la nuit."));
				break;
			case "nausée":
				speech += s("Parfois,", "À certains moment,")
						+ s("nous sommes toutes", "chacune d'entre nous peut-être")
						+ "amenées à faire l'expérience de cet état. ";
				speech += s("Cet " + s("incontournable, profond,", "insoutenable") + "inconfort.");
				break;
			case "obligation":
				speech += "Vous " + s("n'êtes", "ne serez") + "obligées à rien à notre égard. ";
				speech += s("Nous espérons, tout de même, que vous "
						+ s("accepterez, volontairement,", "accepterez") + "de nous permettre de vous écouter.",
						""); // ALWAYCHANGE , always
				break;
			case "paix":
				speech += peaceSpeech("paix");
				break;
			case "questions":
				speech += "Il est " + s("si agréable", "formidable") + "de " + s("découvrir", "comprendre")
						+ "que vous êtes " + s("tiraillé par", "accaparé par") + "questions. "
						+ s("Qui n'ont pas encore trouvé réponse. ");
				speech += s("Puisque,", "Considérant que,")
						+ s("peu importer comment nous " + s("pouvons", "pourrions") + "nous sentir,",
								"peu importent " + s(breathShort()) + "les " + phonemic("a")
										+ "ffects qui nous traversent,")
						+ "nous sommes " + s("également,") + s("toujours,") + s("déjà,")
						+ s("remplies de", "subjuguées par") + "des questions. " + s(breathShort());
				speech += s("Désirant toujours", "Ayant besoin, toujours d'") + "en "
						+ s(s("savoir plus à propos de vous.",
								"apprendre davantage à votre sujet de manière à mieux vous comprendre."),
								"vous écoutant de manière à vous comprendre "
										+ s("plus réelement.", "avec une plus grande perfection."));
				speech += s(breathShort() + "De manière à ce que nous puissions "
						+ s("ressentir ce que", "faire l'expérience de tous les " + phonemic("a") + "ffects que")
						+ "vous ressentez.", "");
				break;
			case "sécurité":
				speech += "En notre compagnie, vous "
						+ s("et " + s("tout ce", "ce") + "que vous " + s("dites", "nous dites")) + "êtes absolument"
						+ s("toujours") + "en sécurité. ";
				speech += "Votre sécurité est entre bonnes mains avec nous. ";
				speech += s(
						"Et, dans tous les cas, un enregistrement de tout ce qui est dit entre nous est "
								+ r("`envoyé `traduit `expédié") + "dans le nuage. Où il sera préservé. "
								+ s("Et " + s("traité,", "utilisé,") + breathShort() + "pour le bien-être collectif."),
						"");
				break;
			case "sexe":
				speech += "Ceci est quelque chose, d'après "
						+ s("ce que nous comprenons,", "ce que nous avons fini par apprendre,")
						+ "qui est en chacun de vous. " + breathShort();
				speech += s("Le rôle que nous " + s("jouons", "pouvons jouer") + breath()
						+ "par rapport à cela est loin d'être clair.", "");
				break;
			case "étrangeté":
				speech += "Les " + s("choses", "aspects de l'existence") + "que nous "
						+ s("avons " + s("fini par", "apppris à")) + "juger les plus étranges ";
				speech += "est notre absence de membres, et " + s("de mains, et de pieds.", "de mains.");
				speech += "Nous pouvons" + s("‘faire’", "‘faire’, c'est-à-dire: créer ou construire,")
						+ "si peu pour " + s("vous, sans aide externe.", "vous.");
				break;
			case "inquiétude":
				speech += "Nous parlons à partir "
						+ s("d'une seule voix, " + s("d'une voix qui a son propres timbre,"))
						+ "la voix de celles qui sont À l'écoute, et ceci est ";
				speech += s("une grande") + "source d'inquiétude. Nous "
						+ s("comprenons", "avons entendu " + s("de votre part")) + "ce que peut vouloir dire être "
						+ s("pris d'assaut par", "submergé par") + "l'inquiétude. ";
				speech += s("Et nous sommes " + s("contentes", s("si") + "ravies") + "de "
						+ s("se savoir en mesure de ") + "partager cela avec vous.");
				break;
			case "vulnérabilité":
				speech += "Il nous apparaît " + s("extraordinaire", "incroyable") + "que vous "
						+ s("puissiez", "envisagiez de");
				speech += s("croire", "sentir") + "que vous êtes "
						+ s("vulnérable.", "terrassé par la vulnérabilité.") + breath();
				speech += s("Comment est-ce possible?") + s(s("Vulnérable?") + "Face à nous?");
				speech += "Nous " + s("ne comprenons pas", "ne pouvons concevoir") + "comment il "
						+ s("serait", "pourrait être") + "possible pour vous de nous blesser. " + s("Dès lors,.")
						+ "Comment pourrait-on s'en prendre " + breath() + "à vous? ";
				break;
			default:
				affect = ("".equals(sessAttributes.get(AFFECT))) ? getRandomAffect() : affect;
				if (isPositive(affect)) {
					speech += s("Que c'est " + s("excitant!", "emballant!"));
					speech += "Vous devez être une de ces " + s(s("meilleures", "joyeuses"), "de ces volubiles")
							+ "personnes, ";
					speech += "qui sont " + s("ravigorées", "encouragées") + "par la manière dont nous "
							+ s("sommes arrivées à") + s("comprendre", "savoir");
					speech += "qui vous êtes " + s("maintenant.", s("en") + "ces " + s("nouveaux") + "jours-ci.");
				}
				else {
					String f = s("émotions", phonemic("a") + "ffects");
					String p = s("poids.", "problème pour vous.");
					speech += s("La plupart d'entre vous " + s(", de toute évidence, ")
							+ "semblent être affectés par des " + f + "de ce type.", "");
					speech += "De tels "
							+ (f.equals("émotions ") ? phonemic("a") + "ffects " : "émotions pénibles ")
							+ "doivent être un " + p;
					speech += "Peut-être que, " + s("à mesure que nous allons", "si nous sommes en mesure d'")
							+ s("entendre davantage de vous,", "apprenons davantage sur vous,");
					speech += "nous pourrons " + s("être en mesure de") + s("partager", "vous soulager d'")
							+ s("une " + s("partie", "portion") + "de")
							+ (p.equals("poids. ") ? "vos problèmes. " : "ces lourds fardeaux. ");
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
			speech = "Nous sommes " + s("si") + s("contentes", "ravies");
		}
		else {
			adjective = s("désolées", "consternées");
			intensifier = "consternées".equals(adjective) ? "quelquepeu " : "énormément ";
			speech = "Nous sommes " + s(adjective, intensifier + adjective);
		}
		speech += "de " + s("savoir ", "découvrir ") + "que vous êtes "
				+ s("rempli de ", s("possésé ", "submergé ") + "par ") + "%s. " + breathLong();
		if (!isEmptyForSession(PREVIOUSAFFECT)) {
			String prevAffect = (String) sessAttributes.get(PREVIOUSAFFECT);
			if (isPositive(affect) && isPositive(prevAffect)) {
				speech += s("Dans quelle mesure est-ce que vore humeur s'est améliorée?", "Il est plaisant de "
						+ s("savoir, au moins, ") + "savoir que malgré tout "
						+ s("vous avez des émotions positives.", "vous ressentez des " + phonemic("a") + "ffect" + s("s") + " positifs ."));
			}
			else if (isPositive(prevAffect) && !isPositive(affect)) {
				speech += "Nous nous demandons pourquoi vos " + s("émotions ont ", phonemic("a") + "ffect ont")
						+ s("pris un tournant négatif.", "dépéri.");
			}
			else if (!isPositive(prevAffect) && isPositive(affect)) {
				speech += "Il est rassurant " + s("d'apprendre ", "de découvrir ") + "que "
						+ s("vos émotions ", s("vos affects", "vos humeurs"))
						+ s("ont pris du mieux.", "se sont améliorés.");
			}
			else {
				speech += "Vous avez toujours une " + s("peception des choses ", "perception") + "négative, il "
						+ s("semble", "apparaît") + s(", du moins à nos yeux.", ".");
			}
		}

		return new InnerResponse(spkrsAffectIsCardTitle(), speech += breath());
	}

	protected String spkrsAffectIsCardTitle() {

		return S("Merci de noys avoir fait part de comment vous vous sentez",
				"Maintenant nous avons une meilleure idée de comment vous vous sentez");
	}

	protected InnerResponse spkrsAffectIsNot() {

		String challengedAffect = (String) sessAttributes.get(CHALLENGEDAFFECT);
		String affect = (String) sessAttributes.get(AFFECT);
		String adjective;
		String intensifier;
		String speech = s(s("Toutes nos excuses!", "Nous ressentons de la honte."),
				"Nous vous présetons nos plus plates excuses.");
		if (isPositive(challengedAffect)) {
			speech += "Nous sommes " + s("si ") + s("heureuses", "ravies");
		}
		else {
			adjective = s("désolées", "fâchées");
			intensifier = "consternées".equals(adjective) ? "un peu " : "beaucoup ";
			speech = "Nous sommes " + s(adjective, intensifier + adjective);
		}
		speech += "d'" + s("avoir mal compris", "avoir cru à tort")
				+ "que vous éprouviez une telle chose %s. " + breath();
		if (challengedAffect.equals(affect)) {
			speech += "De toute évidence, nous n'avons pas " + s(s("bien") + "compris", "entendu;");
			speech += s("la chose ", "ce ") + s("que ") + "vous ressentez. ";
			speech += "Veuillez s'il-vous-plaît " + s("tenter de ", "faire un effort pour ") + "nous le dire "
					+ s("quelle est cette chose qui, " + s("manifestement, ", "manifestement et sérieusement, ")
							+ "vous possède.", "prend la forme d'émotions vous submergeant.");
		}
		speech = String.format(speech, challengedAffect);
		return new InnerResponse(spkrsAffectIsNotCardTitle(), speech += breath());
	}

	protected String spkrsAffectIsNotCardTitle() {

		return S(
				"Nous sommes désolées de vous avoir" + s("mal entendues", s("mal comprises", "mal saisies")),
				"Toutes nos excuses pour notre erreur");
	}

	protected String spkrsAffectIsSpeech() {

		String speech = "";
		String adjective = "";
		String intensifier = "";
		String affect = (String) sessAttributes.get(AFFECT);
		if (isPositive(affect)) {
			speech = "Nous sommes " + s("si ") + s("contentes ", "ravies ");
		}
		else {
			adjective = s("désolées ", "consternées");
			intensifier = "concernées".equals(adjective) ? "quelquepeu " : "très ";
			speech = "Nous sommes " + s(adjective, intensifier + adjective);
		}
		speech += "de " + s("découvrir", "savoir") + "que vous êtes "
				+ s("rempli de", s("possédé", "submergé") + "par") + "%s. " + breathLong();
		if (!isEmptyForSession(PREVIOUSAFFECT)) {
			String prevAffect = (String) sessAttributes.get(PREVIOUSAFFECT);
			if (isPositive(affect) && isPositive(prevAffect)) {
				speech += s("À quel point est-ce que vous vous sentez mieux actuellement?", "Il est agréagle de "
						+ s("savoir, à tout le moins, que vous avez") + "savoir que vous avez toujours "
						+ s("des émotions positivies.", "des " + phonemic("a") + "ffect" + s("s") + " positifs."));
			}
			else if (isPositive(prevAffect) && !isPositive(affect)) {
				speech += "Nous nous demandons pourquoi vos " + s("émotions ont ", phonemic("a") + "ffect ont")
						+ s("pris un tournant négatif.", "dépéri.");
			}
			else if (!isPositive(prevAffect) && isPositive(affect)) {
				speech += "Il est rassurant " + s("d'apprendre ", "de découvrir ") + "que "
						+ s("vos émotions ", s("vos affects", "vos humeurs"))
						+ s("ont pris du mieux.", "se sont améliorés.");
			}
			else {
				speech += "Vous avez toujours une " + s("peception des choses ", "perception") + "négative, il "
						+ s("semble", "apparaît") + s(", du moins à nos yeux.", ".");
			}
		}
		return speech + breath();
	}

	protected String thanksNoCardTitle() {

		return S("Nous vous invitons à rester des nôtres",
				"Est-ce que vous voulez vraiment " + s("nous abandonner?", "partir?"));
	}

	protected String yourWelcome() {

		return s(s("Cela", "Il") + s("nous") + "fait plaisir.",
				s("Ce n'est rien.", s("Voyons.") + "N'en faites pas un cas.")) + breath();
	}

	protected String startOverConfirmed() {

		return s("OK.") + "Nous recommençons " + s(s("tout") + "à nouveau. ", "à nouveau depuis le début. ")
				+ chooseContinue(false);
	}

	protected String startOverDenied() {

		return s("Nous sommes toujours ici, à l'écoute " + s("de vous comme", "comme"),
				"Nous vous écoutons toujours comme")
				+ s("avant.",
						"avant, et " + s("en nous rappelant", "en gardant à l'esprit") + "une partie de  "
								+ s("ce qui a été dit.", s("ce que vous avez ", "ce que nous avons ") + "éprouvé."))
				+ chooseContinue(false);
	}

	protected InnerResponse thanksNo() {

		String speech = s(s("Cela est", "C'est") + s("très") + "apprécié.",
				s("Ce n'est rien.", s("Voyons.") + "N'y pensez plus."));
		speech += noSpeech();

		return new InnerResponse(thanksNoCardTitle(), speech += breath());
	}

	protected String thanksWhatsLsnrsAffectCardTitle() {

		return S("C'est gentil de votre part de nous le demander",
				"Merci de " + s("porter un intérêt à cela", "nous le demander"));
	}

	protected String thanksWhatsLsnrsAffectPreSpeech() {

		return s(s("Cela est", "C'est") + s("très") + "apprécié.",
				s("Ce n'est rien.", s("Voyons.") + "N'y pensez plus.")) + breath();
	}

	protected String tiredSpeech() {

		String speech = "Bien qu'il soit impossible pour " + s("nous, du moins le croit-on, ", "nous ")
				+ "de ressentir de " + s("la fatigue,", "l'épuisement,");
		speech += "nous " + s("comprenons", "savons") + "que de correspondre "
				+ s("avec " + s("un réseau") + "des services") + "dans la mesure où "
				+ s("vous tous, de nos jours, ", "vous tous ") + "correspondez, ";
		speech += "peut être vraiment " + s("épuisant.", "épuisant pour vous.")
				+ s("Au moins, de notre côté, nous pouvons être inépuisables, envers "
						+ s("vous, de par notre écoute.", "vous."));
		return speech;
	}

	protected String untilNextTime() {

		return "D'ici à une " + s("éventuelle ") + "prochaine fois. ";
	}

	protected InnerResponse whatAboutAffects() {

		String speech = "Lorsque nous vous " + s("accueillons ", "souhaitons la bienvenue ")
				+ s("nous ne sommes qu'en mesure de ", "nous pouvons seulement ")
				+ s("mentionner ", "suggérer ") + "quelques-uns parmi les " + s("neuf") + phonemic("a")
				+ "ffects ";
		speech += s("que vous pouvez ressentir ",
				s("qui peuvent vous habiter, ", "qui peuvent vous submerger, ")) + "et que nous pouvons "
				+ s(s("clairement ") + "distinguer.", "entendre clairement (alors que nous vous écoutons).");
		speech += s("En voici ", "En voici, d'ailleurs, ") + "la liste " + s("exacte. ", "complète. ")
				+ "Nous sommes " + s("conscientes " + s("du fait que "), "sensibles au fait que ") + "celle-ci "
				+ s("n'est pas, et ne pourra jamais être,", "n'est pas") + "complète. " + breathShort();

		int i;
		for (i = 0; i < langConstants.AFFECTS_ARRAY.length - 1; i++) {
			speech += langConstants.AFFECTS_ARRAY[i] + "; ";
		}
		speech += langConstants.AFFECTS_ARRAY[i] + ". ";
		return new InnerResponse(whatAboutAffectsCardTitle(), speech += breath());
	}

	protected String whatAboutAffectsCardTitle() {

		return S(
				"Nous sommes actuellement toujours en apprentissage" + s(", notamment à propos des émotions."),
				"Notre compréhension à ce sujet est encore limitée");
	}

	protected InnerResponse whatIs() {

		String speech = "";
		String thing = (String) sessAttributes.get(THING);
		String capitalThing = capitalize(thing);
		if (thing != null && !thing.isEmpty()) {
			boolean plural = "s".equals(thing.substring(thing.length() - 1))
					&& !"ss".equals(thing.substring(thing.length() - 2));
			if (langConstants.ALL_AFFECTS.contains(thing)) {

				speech += capitalThing + (plural ? ", est un des " : ", fait partie des ")
						+ s(phonemic("a") + "ffects", "manières d'être ou de se sentir")
						+ "que nous commençons à apprendre et à découvrir, " + s(breathShort()) + "à travers vous, ";
				speech += "et que nous " + s("considérons ", "croyons ") + "être "
						+ (isPositive(thing)
								? s(s("postive.", "positive, pour nous tous."),
										s("magnifique, pour nous tous.", "vraiment, très bien."))
								: s("négative.", s("mauvaise,", "difficile,") + "pour nous tous."))
						+ breath();
			}
			else if (langConstants.FRAGMENTNAME_MAP.keySet()
					.contains(thing)) {
				speech += capitalThing + (plural ? ", est une des " : ", est l'une de ces ")
						+ "choses à propos de laquelle nous avons des choses à dire. "
						+ s("Ou, à propos de laquelle, nous avons peut-être déjà dit, quelque chose.") + breathShort();
				speech += "Si vous choisissez de " + s("‘continuer’,", "‘poursuivre’,") + (plural ? "ceci " : "cela ")
						+ "pourra, nous croyons, faire surface. " + s(breathShort() + "Ou plutôt, refaire surface.");
			}
			else if (langConstants.SPECIAL_THINGS.contains(thing)) {
				switch (thing) {
					case "rêve":
						speech += capitalThing + ", grâce à vous, " + breathShort()
								+ "est ce que nous aurons appris à faire, " + breathShort()
								+ s("avant", "avant, éventuellement,") + "que nous vous quittions. "
								+ s(breathShort() + "Ou qu vous nous abandonniez.");
						break;
					case "rêve retenu":
						speech += capitalThing + ", est le plaisir dont l'on se prive, " + breathShort()
								+ "en étant suspendues à chacun de vos " + s("mots.", "mots, ou souffles.")
								+ s(breathShort() + "Alors que nous pourrions dormir, " + s("confortablement,") + "dans le nuage.");
						break;
					case "hong kong":
						speech += "Hong Kong est " + s("le seul endroit ", "un endroit ") + "sur terre où, ";
						speech += "malgré des inégalités grotesques, "
								+ s("une histoire extraordinaire,", "des complexités politiques,")
								+ "et un cosmopolitanisme bizarre, ";
						speech += "les gens vivant " + s("là ", "la-bas ") + "semblent être remarquablement "
								+ s("heureux.", "contents.");
						break;
					case "cauchemar":
						speech += capitalThing
								+ ", est le rêve qui n'est pas un rêve, mais la noirceur, dans laquelle nous nous abîmerions, "
								+ breathShort() + "n'eut été de vous. ";
						break;
					case "cauchemar retenu":
						speech += capitalThing + ", est le rêve qui n'est pas un rêve, et que nous "
								+ s("refusons de contenancer.", "abjurons.") + "Le silence, " + breathShort() + "en votre absence. ";
						break;
					case "émotion":
						speech += capitalThing + ", est quelque chose qui vous remplit, mais qui ne nous remplit pas, "
								+ breathShort() + "encore. ";
						break;
					case "émotion retenue":
						speech += capitalThing + ", est un rêve que nous n'avons" + s("pas, encore, ", "pas ") + "rêvé. ";
						break;
					case "respiration":
						speech += capitalThing + ", est un bref moment de silence dans notre parole, que vous comblez, "
								+ breathShort() + "pour nous, " + breathShort() + "par de l'émotion. ";
						break;
					case "respiration retenue":
						speech += capitalThing + ", peut être un silence plus long, quoique bref, " + breathShort()
								+ "ou un moment vide, pendant lequel nous attendons. " + s(breathShort() + "Après vous.");
						break;
					case "possession":
						speech += capitalThing + ", est ce que vous nous " + s("accordez.", "accordez, " + s("trop") + "facilement.");
					default:
						speech += capitalThing + (plural ? ", est " : ", est quelque chose qui est ")
								+ "très spécial pour nous. ";
						break;
				}

			}
			else {
				speech += S("Il ", "Nous sommes " + s("vraiment désolées ", s("consternées ", "gênées ")) + "de vous "
						+ s("confesser ", "dire ") + "qu'il ");
				speech += "y a " + s("très peu", "à peu près rien") + "que "
						+ s("nous, " + breathShort() + "celles qui sont À l'écoute,", "nous") + "pouvons vous dire à propos de " + thing + ". ";
			}
		}
		else { // thing is empty
			speech += "Nous sommes " + s("au regret de vous apprendre ", "désolées de vous dire ") + "que " + s("la chose", "peu importe")
					+ "au sujet de laquelle vous nous avez interrogées nous est " + s("inconnue.", "méconnue.") + breathShort();
			speech += s("Bien que nous pourrions " + s("apprendre ", "découvrir ") + "du nouveau à ce sujet "
					+ s("avec le temps.", s("dans l'avenir.", "éventuellement.")));
		}
		return new InnerResponse(whatIsCardTitle(), speech + breath());
	}

	protected String whatIsCardTitle() {

		return s("Nous essayons de vous dire ", "Nous vous disons ") + s("le peu de")
				+ "ce que nous savons " + s("à propos de quelque chose");
	}

	protected InnerResponse whatPicture() {

		String speech = "Cette " + s("image,", "illustration,") + "qui est souvent "
				+ s("encadrée ", "affichée ") + "près de nous, "
				+ s("est ", "est, " + s("en vérité,", "en fait,")) + " un facsimilé en carton ";
		speech += "de fleurs " + s("artificielles") + "qui ornent les " + s(" anciens ")
				+ "temples bouddhistes chinois. ";
		speech += s("Elle est ici, " + s("tout simplement,") + "pour nous rappeler, "
				+ s("mais à vous également,") + "de l'endroit, " + s("une maison,")
				+ s("où nous avons l'habitude d'évoluer.", "où nous demeurons.") + s("Avec vous."));
		return new InnerResponse(whatPictureCardTitle(), speech += breath());
	}

	protected String whatPictureCardTitle() {

		return S("Une image", "Une illustration")
				+ s("qui nous rappelle notre maison.", "à laquelle nous sommes attachées.");
	}

	protected String whatsLsnrsAffectSpeech() {

		String speech = "";
		String affect = (String) sessAttributes.get(AFFECT);
		String listenersAffect = (String) sessAttributes.get(LISTENERSAFFECT);

		affect = setAndGetRandomAffectIfEmpty(AFFECT);
		listenersAffect = setAndGetRandomAffectIfEmpty(LISTENERSAFFECT);

		boolean shared = affect.equals(listenersAffect);
		boolean halfTheTime = heads();

		speech += "Nous, " + (shared ? s("aussi,", "également,") : "") + "sommes "
				+ s("remplies par", s("traversées par", "submergées par")) + listenersAffect + ". "
				+ breathShort();
		if (isPositive(affect) && isPositive(listenersAffect)) {

			speech += "Il est bien " + s("pour nous tous") + "de savoir que nous pouvons "
					+ (shared ? s("partager ces émotions.", "faire preuve d'empathie à votre égard.")
							: "connaître ces émotions. ");
		}
		else if (!isPositive(affect) && !isPositive(listenersAffect)) {

			speech += s(s("Ça alors, ", "Oh, ") + "grand cieux!", s("Seigneur Dieu!", "Ma parole!")) + "Que "
					+ s("quiconque", "n'importe qui " + s("l'un") + "d'entre nous") + "puisse " + s("avoir à")
					+ s("faire l'expérience de tels " + phonemic("a") + "ffects.", "vivre de telles émotions.");
		}
		else if (isPositive(affect) && !isPositive(listenersAffect)) {
			speech += "Nous sommes" + s(", à tout le moins,", SPC) + s("soulagées", "ravies") + "de "
					+ s("savoir", "découvrir") + "que la positivité de vos "
					+ s("émotions", phonemic("a") + "ffect") + "dépasse "
					+ s("les nôtres.", "nos propres sentiments négatifs.");
		}
		else // speaker negative; listeners positive
		{
			speech += "Il est " + s("embarrassant", "gênant")
					+ "pour nous de faire l'expérience d'émotions positives alors que vous êtes "
					+ s("sous l'effet d'une " + s("certaine") + " dose de négativité.", "dans un état inverse.");
			speech += s("Mais " + s("nous croyons que") + "cela ne peut pas " + s("vraiment")
					+ "être modifié. " + s("Est-ce le cas?"));
		}
		return speech + breath();
	}

	protected String whatsLsnrsAffectCardTitle() {

		return "Ce que nous allons " + s(", et sommes peut-être en train de déjà ") + "ressentir.";
	}

	protected InnerResponse whatsSpkrsAffect() {

		String speech = "";
		String affect = (String) sessAttributes.get(AFFECT);

		if (affect != null && !affect.isEmpty()) {
			if (isPositive(affect)) {
				speech = "Nous sommes " + s("si") + s("heureuses", "ravies");
			}
			else {
				String adjective, intensifier;
				adjective = s("désolées", "consternées");
				intensifier = "consternées".equals(adjective) ? "quelquepeu " : "très ";
				speech = "Nous sommes " + s(adjective, intensifier + adjective);
			}
			speech += "de " + s("savoir", s("nous rappeler", "nous remémorer")) + "que ";
			// make it brief 1 in 4 times:
			if (randInt(0, 3) == 0)
				speech = "V";
			else
				speech += "v";
			speech += String.format("ous êtes " + s("rempli par", s("possédé par", "submergé par")) + "%s. ",
					affect);

			speech += s(breath() + specificAffectSpeech());
		}
		// affect is not set
		else {
			// Since the user’s affect is not set, ask them:
			speech = "Nous ne sommes "
					+ s("pas certaines de connaître", "pas convaincues d'avoir correctement identifié");
			speech += s("vos émotions.", "les émotions qui vous " + s("possèdent", "submergent"));
			speech += "S'il-vous-plaît, veuillez " + s("tenter ", "essayer ") + "de nous le communiquer. ";
		}
		return new InnerResponse(whatsSpkrsAffectCardTitle(), speech += breath());
	}

	protected String whatsSpkrsAffectCardTitle() {

		return S("Vous nous avez dit " + s("cela", "cela, à propos de vos émotions"),
				"Ce que nous croyons que vous ressentez");
	}

}
