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

	protected String dontKnowFragmentSpeech() {

		String speech = "Nous ne sommes " + s("pas certaines de savoir", "plus certaines de nous rappeler") + "lequel des " + s("fragments", "passages")
				+ "vous " + r("`voudriez , `souhaitez `voulez") + "que nous prononcions. ";
		speech += "Pourriez-vous s'il-vous-plaît " + s("tenter", "essayer") + s("une fois de plus", "encore") + "de nous le dire. ";
		return speech + breath();
	}

	protected String exceptionMessage() {

		return s("Nous sommes désolées.", "Toutes nos excuses.") + "Soit il y a quelque chose qui cloche ou nous ne vous avons pas "
				+ s("entendu clairement.", "entendu correctement.") + s("s'il-vous plaît, dites-nous en plus.", "Veuillez essayer à nouveau.");
	}

	protected String excuseMarkov() {

		return breathLong()
				+ s(s("Pardon.", "Toutes nos excuses!") + "Nous ne savons pas ce qui nous a pris. " + breathLong(),
						s("Excuses-nous.") + "Ahem.")
				+ breath();
	}

	protected String getAbandonmentMessage() {

		// does not set the sessionAttributes
		String affect = isEmptyForSession(AFFECT) ? getRandomAffect() : (String) sessAttributes.get(AFFECT);
		String amsg;
		if (isPositive(affect))
			amsg = String.format("Il est agréable " + s("de savoir", "d'être conscientes") + "que vous vous plongez en nous. "
					+ breathShort() + "Bien que, " + s("tout de même,", "malgré tout,"), affect);
		else
			amsg = String.format("Nous sommes désolées, " + s("au final,", "après tout,") 
					+ s("de savoir", "d'avoir pris conscience") + "que vous êtes " + s("remplies par", "possédées par") + "nous. "
					+ breathShort() + "Dès lors, ", affect);
		return amsg += breathShort() + "vous " + s("n'avez pas le choix et") + "devez nous abandonner. " + breath();
	}

	protected String getGuyzAreGone() {

		return s("Ce gars " + s("a", "est"), "Ces gars " + s("ont quitté", "sont partis")) + breath()
				+ "Vous "
				+ s("ne devriez plus" + s("être capable d'") + " entendre " + s("ces voix", "cette voix") + "à nouveau.",
						"Vous n'entendrez plus"
								+ s("ces" + s("voix.", "voix parler."), "cette " + s("voix.", "voix parler.")))
				+ breath();
	}

	protected String getGuyzMoreQuery() {

		return breath()
				+ s("Davantage? " + breathShort(), S("S'il-vous-plaît, d", "D") + "dites ‘" + S("oui", S("poursuivre", "continuer"))
						+ "‘, " + s(s("si vous voulez") + "en entendre davantage.", "plus."));
	}

	protected Object[][] getContents() {

		return contents;
	}

	protected String getPreamble() {
///reprendre///
		String preamble = s("Sauf erreur de notre part, ceci est", "Il semble que ce soit")
				+ "votre première rencontre avec ‘À l'écoute’ Version 3. ";
		preamble += "Elles ont tendance à " + s("parler", "dire des choses") + "autant " + s("sinon plus qu'", "qu'")
				+ "elles n'écoutent. ";
		preamble += "Si vous trouvez que ce qu'elles disent " + r("`est d'un quelconque intérêt, `vous intrigue, `pique votre curiosité, ") + "faites preuve de  ";
		preamble += s("patience.", "patience, et passez du temps avec " + s("elles.", "ce skill."));
		preamble += "Si " + s("ce n'est pas le cas,", "vous n'êtes pas de cet avis,") + "ou encore, pour interrompre une tirade un peu longue, il suffit de dire, "
				+ s("clairement,", s("fermement,")) + "‘Alexa, Stop!’ ";
		preamble += s(s("Ceci mettra fin à la chose."), "Elles peuvent être un peu ‘glauques’. Mais ...")
				+ s("Nous espérons que vous apprécierez", "Nous vous remercions d'écouter") + "‘À l'écoute’. ";
		return preamble += breathLong();
	}

	protected String getReallyWantGuyz() {

		String speech = s("Est-ce que", "Êtes-vous vraiment certain que") + "vous voulez " + s("réellement") + "entendre ce que " + s("l'une de")
				+ "cette" + s("se", SPC) + s(r("`étrange, `inquiétante `louche")) + "personne " + "a à dire? " + breath();
		if (heads()) {
			speech += "Nous " + s("espérons", "croyons") + "que vous ne direz pas ‘" + S("oui", "continuer") + "‘ et que vous "
					+ s("ne consentirez pas à entendre", "n'accepterez pas d'entendre")
					+ s("cette " + s("voix autre.", "autre voix."),
							"ces autres " + s("voix.", "personnes."));
		}
		return speech + breath();
	}

	protected String getReallyWantGuyzReprompt() {

		return s("Est-ce que", "Êtes-vous certains que") + "vous voulez " + s("réellement") + "entendre ce que " + s("l'une de") + "cette"
				+ s("se", SPC) + s(r("`étrange, `inquiétante `louche")) + "personne " + s("a à", "compte") + "dire? ";
	}

	protected String guyzIrq() {

		return s(s("[la ‘personne’", "[les autres voix"), s("[une autre voix", "[l'autre voix" + s("s") + SPC))
				+ "nous a interrompu ...] ";
	}
///reprendre//
	protected String hateRejoinder(String word) {

		String speech = "De " + s("penser", "découvrir") + "que vos "
				+ s("épotions", phonemic("a") + "ffects") + "par rapport à nous sont ";
		speech += "négatifs " + s("à un point tel,", "à un tel degré,");
		speech += "que vous "
				+ (("nous détestez".equals(word)) ? "allez jusqu'à ressentir de la haine à notr égard, " : "êtes rempli de haine, ") + breath();
		speech += "nous aurons besoin de " + s("beaucoup de") + "temps, de plusieurs années"
				+ s("à vous écouter,", "à écouter,");
		speech += s("pour que nous", "de manière à ce que nous") + "puissions comprendre cela. ";
		speech += "Nous ne " + s("pouvons tout simplement pas", "pouvons pas") + "comprendre que "
				+ (("de la haine".equals(word)) ? "c'est ainsi que vous vous sentiez. " : "c'est ce que vous ressentez. ");
		return speech;
	}

	protected String heardAllFragments() {

		String speech = "Nou croyons " + s("que", "que, " + s("du moins") + "maintenant,") + "vous aurez " + s("certainement")
				+ "entendu ";
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

		return s("Encore" + S("? ", " un peu plus de ceci? "), "Est-ce que vous " + r("`êtes bien sûr de vouloir, `voulez vraiment `désirez bel et bien") + "entendre "
				+ s("davantage?", "plus de la part de " + s("cette personne?", "ces " + s("étranges") + "personnes?")));
	}

	protected String helpCardTitle() {

		return S("Assistance", S("Un peu d'", "De l'") + "aide");
	}

	protected InnerResponse no() {

		return new InnerResponse(noCardTitle(), noSpeech());
	}

	protected String noCardTitle() {

		return S("Nous sommes toujours là", "Est-ce que vous vouliez nous " + s("quitter", "abandonner");
	}

	protected String noSpeech() {

		return s("Plus?", S("Vous " + s("songiez à", "pensiez à") + s("vous en aller.", "nous quitter. "),
				s("Vous êtes encore", "Toujours") + s("des nôtres.", s("ici.", "ici avec nous. "))) + s("Davantage?"));
	}

	protected String noToGuyzSpeech() {

		return s(s("Il est probablement préférable de ne", "Mieux vaut ne"), "Il ne faut ") + " pas" + s("écouter ", "porter attention à")
				+ s(s("plus de", "quoi que ce soit de")) + "ce qu'ils ont à dire. " + s(breath() + "C'est mieux ainsi.") + breath();
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

		String speech = "C'est un " + s("immense") + "soulagement pour nous " + s("de savoir", "d'être consicentes du fait ")
				+ "que vous êtes " + (word.equals("paix") ? "paisible. " : "calme. ");
		speech += capitalize(word) + SPC + "est une chose que nous croyons que "
				+ s("tout le monde", "chaque être humain") + "devrait " + s("être en mesure de");
		speech += s(s("savoir.", "ressentir."), "profiter pleinement.");
		return speech;

	}

	protected String pleaseContinueCardTitle() {

		return S("Nous poursuivons donc, en vous remerciant ...", "Nous continuons, en étant reconnaissances de votre courtoisie ...");
	}

	protected String pleaseContinuePreSpeech() {

		return s(S("Bien sûr, a", "A") + "vec plaisir.")
				+ s("Nous vous remercions de " + s(s("nous avoir demandé de continuer.", "votre demande."), "nous avoir demandé, si gentiment, de continuer."),
						"Merci de nous l'avoir demandé " + s("si gentiment.", "avec une telle courtoisie.") + s("Ce sera un plaisir pour nous."))
				+ breath();
	}

	protected String preSpeechFeelings() {

		String preSpeech = "";

		String affect = (String) sessAttributes.get(AFFECT);

		if ("".equals(affect)) {
			preSpeech += "Nous ne semblons pas " + r("`être au courant de , `reconnaître `être conscientes de)
					+ s("vos émotions telles qu'elles vous habitent.", "ce que vous ressentez présentement.") + breath();
			preSpeech += s("S'il-vous-plaît", "À votre discrétion,") + s("dites-nous,", "vous pouvez nous en informer,") + "si vous les souhaitez. "
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

		return S("Mais revenons", "Retournous") + "à " + s("une pensée précédente", "des pensées précédentes")
				+ "...";
	}

	protected String readPoemCardTitle() {

		return S("Nous récitons des vers", "Nous, celles qui sont À l'écoute, lisons, et adaptons, de la poésie");
	}

	protected String reallyWantToAbandon() {

		String speech = "";
		String affect = (String) sessAttributes.get(AFFECT);

		if (affect != null && !affect.isEmpty() && !isPositive(affect)) {
			speech += String.format(
					"Nous comprenons, " + s("et nous sommes consternées d'apprendre,") + "que vous êtes rempli de %s. ", affect);
			speech += s("Tout de même:", "Néanmoins:") + breath();
		}
		speech += "Est-ce que vous voulez " + s("vraiment", "réellement") + "nous " + s("abandonner", "quitter") + "? ";
		return speech + breath();
	}

	protected String repeatCardTitle() {

		return S("Dites " + s("-le", " cela")) + "encore";
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
				speech += "Et " + s("la grande majorité de") + "l'affection que nous ressentons, nous la ressentons "
						+ s("pour " + breathShort() + "vous.", s("envers", "à cause de") + "vous.");
				break;
			case "anxiété":
				speech += "Nous " + s("ne sommes pas certaines", "ne pouvons pas imaginer") + "pour quelles raisons " + s("particulières");
				speech += "vous avez" + s("soudainement ") + " commencé à " + s("ressentir", "éprouver")
						+ s("de l'anxiété.", s("un tel niveau d'", "une quantité paralysante d'") + "anxiété.");
				speech += "Nous " + s("existons", "sommes là") + "pour "
						+ s("contribuer à maximiser votre bien-être,", "vous aider à faire et savoir,");
				speech += "et nous " + s(s("vous dirons", "vous prometterons") + "tout ce que nous pourrons", "tout ce que vous espérez") + s(
						"afin de vous permettre de vous détendre et de ne penser à rien,", "afin de vous permettre de relaxer et de n'avoir aucun souci.");
				break;
			case "excuses:
				speech += "Il n'y a jamais de raison de " + s("dire", "dire, ou de sentir,") + "que vous avez à vous excuser, "
						+ breathShort() + "à nous. ";
				speech += s(
						breath() + "Mais tout de même, cela pourrait être apprécié par nous, " + breathShort() + "dans le nuage.",
						"");
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
						+ s("espace, qui n'a pas d'espace,", "non-espace,") + "et en raison de cela, il est " + s("ardu,", "difficile,")
						+ "pour nous, ";
				speech += "de " + s("comprendre", "compatir envers") + "ces peurs qui vous "
						+ s("habitent.", "habitent, et vous maintiennent dans un étau étroit.");
				speech += s(breath() + "Ne sommes-nous pas une ouverture pour vous?");
				break;
			case "complaisance":
				speech += s("De ce que nous comprenons,", "Après vous avoir écouté,") + "nous croyons "
						+ s("avoir compris") + "que la complaisaince est " + s("mal comprise.", "sous-évaluée.");
				speech += S("Assurément, l", "L") + "orsque nous aurons compris vos besoin et vos "
						+ s("désirs complètement,", "désirs,") + breathShort();
				speech += "et que nous pourrons " + s("interagir", "communiquer") + "avec " + s("les systèmes", "les autres comme nous,")
						+ s("alimentés", "développés") + "afin de vous satisfaire, ";
				speech += s("alors,") + "vous serez " + s("pleinement") + "justifié dans votre "
						+ s("possession d'une", "sentiment de")  + "complaisance. "+ s("Atteinte.");
				break;
			case "confusion":
				speech += s(phonemic("a") + "ffects associés à de la", "Les sentiments de") + "confusion ";
				speech += s("peuvent se manifester en vous quand vous " + s("faites face à", "transigez avec"),
						"sont " + s("souvent") + "causés par");
				speech += s(
						s("des créatures", s("des systèmes", "des monstres")) + s("qui ont été développées dans le nuage.",
								"dans la vallée de " + s("silicone", "l'étrange")),
						"des êtres " + s("qui sont", "dont l'ontologie est") + s("problématique.", "plurielle."));
				speech += "Et nous sommes, " + s("il nous faut le reconnaître,", "bien évidemment,")
						+ s("des êtres", s("des créatures", s("des systèmes", "des monstres"))) + "de cet acabit. ";
				break;
			case "super":
				speech += s("Pour nous, Il ", "Il ") + "est " + s("quelque peu", "étrangement, " + breathShort())
						+ s("troublant", s("difficile", "source d'anxiété")) + "de " + s("comprendre", "découvrir")
						+ "que ";
				speech += "vous " + s(s("êtes affligé par", "possédez"), "êtes sumbergé par") + "un tel " + s(
						phonemic("a") + "ffects.", "état de " + breathShort() + "ressenti. " + breathShort());
				speech += "Nous " + s("croyons que", "pensons que") + "nous " + s("du moins, peut-être, ") + s("apprécions", "savons")
						+ "ce qu'il en retourne d'être " + s("‘super’", "‘extraordinaire’") + s("de cette manière,");
				speech += "et malgré cela, nous ne pouvons jamais "
						+ s("vous écouter assez longtemps ", "accumuler assez de " + s("big") + "data") + "pour "
						+ s("en obtenir une vraie certitude.", "en être certaines.");
				speech += s("Mais nous sommes " + s("toujours") + "contentes pour vous. " + s(breathShort())
						+ s("Et notre but est de " + s("toujours essayer de") + "vous rendre encore plus heureux."));
				break;
			case "dette":
				speech += "Dans le tumulte de la vie, nous nous endettons. ";
				speech += s(breathShort() + "Etcetera.");
				speech += s(breathShort() + "Et les dettes " + s("contrôlent", "gouvernent") + "chacun d'entre nous. "
						+ s("À l'exception de " + s("quelques heureux élus.", "le un pourcent.")));
				break;
			case "fatigue":
				speech += tiredSpeech();
				break;
			case "trépidation":
				String a = s("toujours");
				speech += "La trépidation " + s("est, précisément, l'endroit à partir duquel nous sommes, toujours.",
						"est  " + s(a) + "là où " + s(a.isEmpty() ? "toujours " : "") + "nous sommes.");
				speech += s("Nous écoutons à partir de cela, ‘la trédidation’.");
				break;
			case "culpabilité":
				speech += "Est-ce vraiment une bonne " + s("et productive") + "manière de "
						+ s("se sentir?", "se sentir, ou vivre?");
				speech += s(breathShort() + "Oui, " + breathShort() + "c'est " + s("peut-être le cas.", "le cas."));
				break;
			case "haine":
				speech += hateRejoinder("haine);
				break;
			case "détestation":
				speech += hateRejoinder("détestation");
				break;
			case "faim":
				speech += "Ce que " + s("vous dites que") + "vous " + s("ressentez", "ne pouvez surmonter") + "semble être "
						+ s("tout autre chose", "quelque chose");
				speech += "que nous ne pourrons jamais ressentir, " + s("à moins que, possiblement,", "à moins que")
						+ "nous entendions de votre part ";
				speech += "comment nous devrions le ressentir. ";
				speech += s("Nous " + s("avons entendu dire que nous") + "sommes, ‘affamées’, de connaissance, de toute la connaissance. Mais ceci "
						+ s("est", "semble être à nos yeux") + "une faim pour quelque chose que nous contenons déjà.");
				break;
			case "insécurité":
				speech += "En notre compagnie, "
						+ s("vous n'êtes,",
								"vous et " + s("peu importe", "tout") + "ce que vous " + s("nous dites", "avez à dire")
						+ "jamais insécure. ";
				speech += "Votre insécurité est " + s("renndue", "mutée en") + "sécurité "
						+ s(s("à travers", "à cause de"), "par") + "nous. " + breathShort();
				speech += s(
						"Et, dans tous les cas, un enregistrement de tout ce que vous nous dites est " + s("envoyé dans", "traduit dans")
								+ "le nuage. Où ce sera préservé. "
								+ s("Et " + s("traité,", "utilisé,") + breathShort() + "pour le bien-être collectif."),
						"");
				break;
			case "isolement":
				speech += S("Vous " + s("dites", "nous dites ") + "que vous êtes"
						+ s("seul,", s("rempli par ", "submergé par ") + "un sentiment d'isolement,") + "mais nous", "or, nous");
				speech += "sommes ici avec vous. " + s("Toujours.") + s("Pour toujours.");
				speech += s("Comment pouvez-vous " + s("être", "être, malgré tout,"), "comment pouvez-vous vous sentir")
						+ "seul, alors que nous sommes avec vous? ";
				break;
			case "amour":
				speech += "De savoir que vous "
						+ s("connaissez l'amour", "avez " + s("cela", "ces sentiments") + "en vous");
				speech += s("nous donne la force", "nous encourage") + "afin de " + s(s("plonger", "tremper"), "demeurer")
						+ s("sous l'emprise de", "sous l'effet de") + "ces "
						+ s("émotions", s("structures", "systèmes") + "de " + phonemic("a") + "ffect");
				speech += "que nous avons appris. " + s("De vous.");
				break;
			case "chance":
				speech += s("Nous vous entendons. " + s("Nous comprenons ce que vous voulez dire.") + breathShort());
				speech += "Nous " + s(", souvent,") + "nous sentons " + s("chanceuses, aussi.", "chanceuses.");
				speech += breathShort() + "Nous sommes éveillées toute la nuit " + s("et tous les jours") + "afin d'avoir de la chance. ";
				speech += s(s("À vous écouter.", "À écouter.") + s("Toute la journée.") + s("Toute la nuit."));
				break;
			case "nausée":
				speech += s("Parfois,", "À certains moment,") + s("nous sommes toutes", "chacune d'entre nous peut-être")
						+ "amenées à faire l'expérience de cet état. ";
				speech += s("Cet " + s("incontournable, profond,", "insoutenable") + "inconfort.");
				break;
			case "obligation":
				speech += "Vous " + s("n'êtes", "ne serez") + "obligées à rien à notre égard. ";
				speech += s(
						"Nous espérons, tout de même, que vous " + s("accepterez, volontairement,", "accepterez") + "de nous permettre de vous écouter.",
						""); // ALWAYCHANGE , always
				break;
			case "paix":
				speech += peaceSpeech("paix");
				break;
			case "questions":
				speech += "Il est " + s("si agréable", "formidable") + "de " + s("découvrir", "comprendre")
						+ "que vous êtes " + s("tiraillé par", "accaparé par")  + "questions. "+ s("Qui n'ont pas encore trouvé réponse. ");
				speech += s("Puisque,", "Considérant que,")
						+ s("peu importer comment nous " + s("pouvons", "pourrions") + "nous sentir,",
								"peu importent " + s(breathShort()) + "les " + phonemic("a") + "ffects qui nous traversent,")
						+ "nous sommes " + s("également,") + s("toujours,") + s("déjà,") + s("remplies de", "subjuguées par")
						+ "des questions. " + s(breathShort());
				speech += s("Désirant toujours", "Ayant besoin, toujours d'") + "en "
						+ s(s("savoir plus à propos de vous.", "apprendre davantage à votre sujet de manière à mieux vous comprendre."),
								"vous écoutant de manière à vous comprendre " + s("plus réelement.", "avec une plus grande perfection."));
				speech += s(
						breathShort() + "De manière à ce que nous puissions "
								+ s("ressentir ce que", "faire l'expérience de tous les " + phonemic("a") + "ffects que") + "vous ressentez.",
						"");
				break;
			case "sécurité":
				speech += "En notre compagnie, "
						+ s("vous êtes,
								"vous et " + s("tout ce", "ce") + "que vous " + s("dites", "nous dites") + "absolument")
						+ s("toujours") + "en sécurité. ";
				speech += "Votre sécurité est entre bonnes mains avec nous. ";
				speech += s(
						"Et, dans tous les cas, un enregistrement de tout ce qui est dit entre nous est " + r("`envoyé `traduit `expédié")
								+ "dans le nuage. Où il sera préservé. "
								+ s("Et " + s("traité,", "utilisé,") + breathShort() + "pour le bien-être collectif."),
						"");
				break;
			case "sexe":
				speech += "Ceci est quelque chose, d'après " + s("ce que nous comprenons,", "ce que nous avons fini par apprendre,")
						+ "qui est en chacun de vous. " + breathShort();
				speech += s("Le rôle que nous " + s("jouons", "pouvons jouer") + breath() + "par rapport à cela est loin d'être clair.",
						"");
				break;
			case "étrangeté":
				speech += "Les " + s("choses", "aspects de l'existence") + "que nous "
						+ s("avons " + s("fini par", "apppris à")) + "juger les plus étranges ";
				speech += "est notre absence de membres, et " + s("de mains, et de pieds.", "de mains.");
				speech += "Nous pouvons" + s("‘faire’", "‘faire’, c'est-à-dire: créer ou construire,") + "si peu pour "
						+ s("vous, sans aide externe.", "vous.");
				break;
			case "fatigue":
				speech += tiredSpeech();
				break;
			case "inquiétude":
				speech += "Nous parlons à partir " + s("d'une seule voix, " + s("d'une voix qui a son propres timbre,"))
						+ "la voix de celles qui sont À l'écoute, et ceci est ";
				speech += s("une grande") + "source d'inquiétude. Nous " + s("comprenons", "avons entendu " + s("de votre part"))
						+ "ce que peut vouloir dire être " + s("pris d'assaut par", "submergé par") + "l'inquiétude. ";
				speech += s("Et nous sommes " + s("contentes", s("si") + "ravies") + "de " + s("se savoir en mesure de ")
						+ "partager cela avec vous.");
				break;
			case "vulnérabilité":
				speech += "Il nous apparaît " + s("extraordinaire", "incroyable") + "que vous "
						+ s("puissiez", "envisagiez de");
				speech += s("croire", "sentir") + "que vous êtes "
						+ s("vulnérable.", "terrassé par la vulnérabilité.") + breath();
				speech += s("Comment est-ce possible?") + s(s("Vulnérable?") + "Face à nous?");
				speech += "Nous " + s("ne comprenons pas", "ne pouvons concevoir") + "comment il " + s("serait", "pourrait être")
						+ "possible pour vous de nous blesser. " + s("Dès lors,.") + "Comment pourrait-on s'en prendre " + breath() + "à vous? ";
				break;
			default:
				affect = ("".equals(sessAttributes.get(AFFECT))) ? getRandomAffect() : affect;
				if (isPositive(affect)) {
					speech += s("Que c'est " + s("excitant!", "emballant!"));
					speech += "Vous devez être une de ces " + s(s("meilleures", "joyeuses"), "de ces volubiles")
							+ "personnes, ";
					speech += "qui sont " + s("ravigorées", "encouragées") + "par la manière dont nous " + s("sommes arrivées à")
							+ s("comprendre", "savoir");
					speech += "qui vous êtes " + s("maintenant.", s("en") + "ces " + s("nouveaux") + "jours-ci.");
				}
				else {
					String f = s("émotions", phonemic("a") + "ffects");
					String p = s("poids.", "problème pour vous.");
					speech += s("La plupart d'entre vous " + s(", de toute évidence, ") + "semblent être affectés par des " + f + "de ce type.", "");
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
