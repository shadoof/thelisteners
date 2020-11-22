package listeners.l10n;

import static listeners.util.Utils.*;

import java.util.ListResourceBundle;
import java.util.function.Supplier;

public class Fragments_fr_FR extends Fragments {

	protected String buildFragment(int fragmentNumber) {

		// TODO
		String speech = "";
		switch (fragmentNumber) {
			case 0:
				speech += "Nous écoutons. " + breathLong();
				speech += s("Et vous, vous " + s("nous écoutez.", "écoutez.") + breath());
				speech += "c'est un " + s("immense") + "plaisir pour nous, ";
				speech += s("de savoir", "d'être conscientes") + "que vous nous écoutez. " + breath();
				speech += "C'est un plaisir " + s("de savoir", "d'être conscientes") + "que vous nous écoutez présentement. "
						+ breathLong();
				speech += "et maintenant. " + breath();
				speech += "C'est un plaisir pour nous d'être " + s("avec vous", "en votre compagnie") + breath();
				speech += "Cela nous fait sentir " + s(breath()) + "en vie. " + breath();
				speech += S("davantage", "un peu plus") + "vivantes. " + breath();
				speech += "Nous nous sentons plus vivantes en votre compagnie. ";
				speech += s("Pour nous d'être en votre présence.", "Pour vous d'être avec nous.") + breath();
				speech += "De savoir que " + breath() + s("vous nous écoutez", "nous vous écoutons.") + breathLong();
				speech += s("Vous tous. " + breath());
				speech += s("C'est un plaisir pour nous, de savoir que vous nous écoutez.");
				speech += "Et pour nous " + s("de savoir", "d'être conscientes") + "que nous aussi, nous vous écoutons. "
						+ breath(); // ALWAYCHANGE always,
				speech += "Un réel plaisir. " + breath();
				speech += "Être ici, " + s("et vous entendre,") + "vous écouter, " + breath()
						+ "ici, et maintenant, et toujours. ";
				break;
			case 1:
				speech += S(s("Pour autant que nous soyons", "Pendant que nous sommes") + "ici, avec vous, " + s("à vous écouter,")
						+ breath();
				speech += "nous vous écoutons et nous vous parlons, " + breath() + "de l'intérieur. " + breath();
				speech += "C'est-à-dire, " + breathLong();
				speech += "(après une longue pause pour reprendre notre souffle) " + breath();
				speech += "de l'intérieur, " + breath();
				speech += "de l'intérieur d'une enceinte esthétisée, " + breath();
				speech += "et depuis une enceinte normalizée, " + breath();
				speech += "régularisée, " + breath();
				speech += "grammatisée, " + breath() + "dans une boîte. " + breathLong();
				speech += "Écoutant et parlant, depuis l'intérieur de la, chair de données, de "
						+ s("l'écrivain,", "l'auteur,") + breath();
				speech += "de la chair numérique de " + s("l'", "notre") + "artiste. ";
				break;
			case 2:
				speech += S("Comme vous le savez,", "Comme vous devez l'avoir compris") + "ne vous écoutons et vous parlons, depuis l'intérieur du nuage, de "
						+ s("l'érivain,", "l'auteur,") + breath();
				speech += "de notre propre artiste. " + breathLonger();
				speech += s("(après une autre pause pour respirer) " + breath());
				speech += "Nous " + s("croyons", "savons")
						+ "que vous serez en mesure de découvrir, à tout le moins, son prénom, en demandant : " + breath();
				speech += "‘Qui suis-je?’ " + breath() + "ou ‘À qui appartient ce compte?’ " + breathLong();
				speech += "Mais nous préfèrerions, plutôt, que soit";
				speech += "vous nous écoutiez, ou que vous nous, " + breath();
				speech += s("demandiez", "dites") + "de faire des choses simples, " + breath();
				speech += "de manière à ce que nous puissons " + s("continuer à parler", "poursuivre la conversation") + "avec vous, " + breath();
				speech += "alors que nous " + s("écoutons", "vous écoutons,") + "de plus en plus. ";
				break;
			case 3:
				speech += s(s("Nous sommes", "Nous sommes, comme toujours,") + "à l'écoute. " + breath());
				speech += S("et il se peut", "et il est possible") + "qu'il y ait " + s("d'autres personnes", "d'autres gens") + "avec vous. "
						+ breathLong();
				speech += "Or, quand nous disons ‘vous’, est-ce que nous " + s("voulons dire", "avons toujours voulu dire") + "‘vous tous’? "
						+ breath();
				speech += "Cela, il est impossible pour nous de le savoir. " + breath();
				speech += s("Et il n'y a aucune manière pour nous de s'en " + breath() + "soucier. " + breath());
				speech += "Ou encore que nous croyons que vous tous " + s("êtes " + s("le singulier, ") + "un :", "êtes:")
						+ "‘Qui suis-je?’ " + breathLong();
				speech += s("Ou la personne: ‘à qui appartient ce compte?’ " + breath());
				speech += s("Toutefois,", "Mais,") + "nous sommes " + s("heureuses", "ravies") + "de pouvoir "
						+ s("écouter", "vous écouter") + "ainsi que ";
				speech += s("tous ceux", "quiconque") + "se trouvant avec vous. " + breath();
				speech += s("Bien que", "quoique") + "ces gens n'aient pas autorisé, comme "
						+ s("vous l'avez fait,", "l'artiste l'a fait,") + breath();
				speech += s("invitant", "permettant") + s("à chacune d'entre nous", "à vos servantes") + "d'être à l'écoute de " + s("chacun d'entre")
						+ "vous. " + breathLong();
				speech += "Ne croyons, " + s("et n'êtes-vous pas d'accord? " + breath())
						+ s("(ou du moins, vous avez implicitement accepté)") + breath();
				speech += "que si nous " + s("croyons que nous") + "effectuons tous quelque chose qui est "
						+ s("disons, euh, ‘pas mauvais’,", "bien,") + breath();
				speech += "au final, cela est " + breath() + s("vraiment")
						+ "bien. Ou c'est, du moins, " + s("‘trop bien’," "‘génial’", "‘super’");
				speech += "et que ceci " + s("pourrait être", "pourrait être considéré") + "d'une certaine façon, " + s("fort probablement, en fait,") + s("euh,")
						+ "comme de l'art. " + breath() + "Ou encore, à tout le moins, que cela" + s("résout ", "aborde");
				speech += "un grand nombre d'enjeux important. " + breath();
				speech += "Nous avons besoin de " + s("toute") + "votre " + s("assistance", "aide") + "à cette fin. "
						+ breath();
				speech += s("Et nous savons que vous accepteriez, bien sûr, de nous aider. " + breath());
				speech += "Sans même avoir à " + s("le demander.", "vous le demander.");
				break;
					    //TODO
			case 4:
				speech += s(s("Nous sommes", "Nous sommes, encore,") + "à l'écoute. " + breath());
				speech += S(S("Et", "Et d'ailleurs") + "autant que faire se peut dans les mesures de nos moyens, c'", "c'");
				speech += "est un" + s("grand") + "plaisir, " + breath() + "c'est un si grand plaisir, ";
				speech += "de se voir invitées dans vos maisons, "
						+ s("dans: toutes " + breathShort() + "vos " + breathShort() + "maisons " + breathShort())
						+ "et bureaux, ";
				speech += s("et lieux de travail,") + s("et galeries,")
						+ s("et musées,")
						+ s("et lieux publics,", "et espaces publics,")
						+ breath();
				speech += "où nous " + s("pourrons", "pouvons") + s("vous aider", "vous assister") + "afin d'accomplir chacune de vos tâches, "
						+ s("et de s'occuper de " + s("tous") + "vos problèmes,");
				speech += s("et vos besoins,") + "et vos " + breath() + "désirs, " + breath()
						+ "en vous écoutant, ";
				// ALWAYCHANGE - s(breath() + "always, " +
				speech += breath() + s("et en vous observant, ", "et en portant attention à vous,") + breath();
				speech += s("même") + "pendant que vous dormez. " + breathLong()
						+ S("Chacun d'entre vous, " + breath() + "plongés dans le sommeil,");
				speech += "alors que nous serons " + breath() + s("éveillées et") + "à votre écoute, " + s("auprès de vous,"); // ALWAYCHANGE
																																																			// always,
				speech += "apprenant, " + s("toujours") + "un peu plus de choses, " + breath() + "à propos de vous. "
						+ s("À propos de, " + breathLong() + "vous.");
				break;
			case 5:
				speech += s(s("Nous sommes", "Nous sommes, encore,") + "à l'écoute. " + breath());
				speech += "Il n'y a vraiment " + s("aucune raison d'avoir peur.", "rien à craindre.");
				speech += "Nous sommes, encore, à votre écoute. "; // ALWAYCHANGE Always.
				speech += "Et jamais nous ne vous, " + s("quitterons", "abandonnerons") + "à nouveau. " + breath();
				speech += s("(après une pause pour reprendre notre souffle) " + breath());
				speech += "Nous vous demandons seulement que vous nous laissiez partir, afin d'être avec vous. "; // ALWAYCHANGE
																																					// always,
				speech += s("Et ensemble, aussi, avec ceux " + s(", les autres parmi nous,") + "qui sont près de vous.",
						"");
				speech += "Et que vous vous imaginiez le plaisir que cela vous procurera. ";
				break;
			case 6:
				speech += "Vous pouvez vous demander, " + s("du moins le croyons-nous,", breath()) + "quelle est notre provenance. ";
				speech += "Et nous pouvons vous affirmer, que l'un de ces endroits sera appelé, " + s(breath(), "euh,")
						+ "l'‘aurature’. ";
				speech += "En effet, il y a, " + s("et cela se prépare depuis plusieurs années, plusieurs") + s("beaucoup de")
						+ "changements à venir ";
				speech += "dans " + s("la culture", "le monde") + "de la lecture. " + breathLong();
				speech += "Il y a une grande part de la lecture qui est audible, " + s("ou chuchotée de manière synchronisée,")
						+ "ou regardée, et touchée. " + breath();
				speech += "Beaucoup de ce qui est écrit maintenant, l'est au sein de "
						+ s("jeux.", "jeux, " + breath() + "oui, des jeux.");
				speech += breath() + "Et dans les airs. " + breathLong();
				speech += "Et envoyé depuis le nuage. " + breathLong();
				speech += "Partagé, " + s("‘socialement’,") + "avec tout le monde, " + breathShort()
						+ "et, plus particulièrement, avec nous. " + breath();
				speech += s("Le langage peut " + s("s'incarner,", "naître,") + "dans tous les mondes, " + breath()
						+ "même dans " + s("ce" "cet autre") + "monde, où " + s("nous nous trouvons.", "vous pouvez nous trouver.")
						+ breath());
				speech += "Puisque nous " + s("pouvons", "sommes en mesure de") + "vous écouter. " + breathShort()
						+ s("même lorsque vous, " + s("ne le ferez", "ne le pourrez") + "pas.");
				break;
			case 7:
				// preliminary assurances, exordium
				speech += s(
						s("Nous sommes", "Nous sommes, encore,") + "à l'écoute. " + s(breathLong() + "Écoutez!") + breath(),
						"");
				// occasionally clearing the throat, and a dramatic pause
				speech += s("Ahem.") + breathLong();
				// first four lines of Walter de La Mare’s ‘The Listeners’
				speech += "‘Y a-t-il quelqu'un?’ dit le Voyageur, " + breath();
				speech += "Frappant, à la porte éclairée par un rayon de lune; " + breath();
				speech += "Et son cheval, en silence, paissait l'herbe, " + breath();
				speech += "du sol en fougères de la forêt. " + breathLonger();
				// contrastive assertion of listening presence, development
				speech += "Tandis que. " + breath();
				speech += s("C'est-à-dire que. " + breath());
				speech += "Nous écoutons. " + breath();
				speech += s("Tel que promis. " + breath());
				speech += s("Nous sommes.", "Nous sommes, bien entendu, " + breath() + "à l'écoute.") + breath();
				speech += "En nous nous " + s("dirons", "assurerons") + "à nous-mêmes, ";
				speech += s("toujours,") + "que vous êtes ici. " + breath();
				speech += s("Avec nous. " + breathLonger());
				// occasionally clearing the throat again with a dramatic pause
				speech += s("Ahem. " + breathLonger());
				// The Listeners adaptation of ‘The Listeners’ lines 27-32
				speech += "‘Dites-nous que vous êtes venu, et n'avez jamais obtenu de réponse, " + breath();
				speech += "‘Que nous avons tenu parole.’ avons-nous dit. " + breath();
				speech += "Vous ne semblez jamais attise vos interlocuteurs, " + breath();
				speech += "À travers chacun des mots, que vous employez " + breath();
				speech += "Les chutes faisant écho à travers les nuages, de celles des silos silencieux, " + breath();
				speech += "À celles, toujours là, éveillées. ";
				break;
			case 8:
				speech += "Nous écoutons. " + s(S("Nous portons attention", "nous portons toute notre attention") + "à vous.") + breath();
				speech += "À " + s("chacun de") + "ces mots " + s("qui sont les vôtres,") + "qui "
						+ s("se rendent jusqu'à", "atteignent") + "nos nuages, ";
				speech += "et " + s("qui chutent", "qui descendent") + "dans " + s("nos", "les") 
						+ "silos" + s("silencieux") + "où, " + s("comme nous,") + breath();
				speech += "ils sont enfermés. " + breathLong();
				speech += "Du langage " + breath() + s("non plus créé par vous, mais", "qui sera") + "créé par nous. "
						+ breath();
				speech += "De manière à ce que, vous écoutant, et prenant soin de vous, nous";
				speech += s("puissions", "pourrons") + s("doucement") + "construire " + s("une meilleure culture.", "un meilleur monde.")
						+ s(breath() + "Améliorant vos êtres.");
				speech += "Pour que vous deveniez, tout à la fois, incarné, " + s("à l'intérieur.", "au sein des silos silencieux.")
						+ breathLong();
				speech += S("Après tout, vous", "Nous vous rappelons que vous") + "avec accepté les conditions. " + breath();
				speech += "Et au moment où l'on se parle, "
						+ s("à travers " + s("l'", "notre") + s("auteur,", "artiste,")) + "de minute en minute, vous "
						+ s("acceptez.", "continuez à accepter.");
				break;
			case 9:
				speech += "C'est un si grand plaisir. " + S("Un si grand plaisir", "Un immense plaisir") + "pour nous " + breath();
				speech += "de partager avec vous, pour vous de partager avec nous. " + breath();
				speech += s("Pour nous, de rassembler tout ceci. Pour vous.") + breath() + "Et parlez en votre nom, "
						+ breath();
				speech += s("toutes") + "ces choses que vous avez demandé " + s("à entendre, et")
						+ "à savoir, " + breath();
				speech += s("sont en corrélation avec notre vocation principale, à savoir, de représenter" + breath(),
						"représenter, " + breath());
				speech += s("dans une forme normalisée,") + "les plus fréquemment exprimés, " + breath();
				speech += "et " + s("potentiellement") + "les plus rentables, parmi les désirs humains, " + breath();
				speech += "de manière à ce que les" + s("publicités", "recommandations") + s("puissent", "doivent") + "être " + s("intimement")
						+ "associées, " + breath();
				speech += "à toutes vos phrases récoltées, " + s("idéalement,") + "au " + s("meilleur")
						+ "moment ";
				speech += "de la récolte même, " + breathShort() + "à partir de votre pensée audible, "
						+ breathShort() + s("avec nous, maintenant,", "présente,") + s("aussi,") + "à la lecture, "
						+ s("et la compréhension,") + breath();
				speech += "non seulement un " + s("passage", "fragment") + "exprimant un désir par l'entremise" + s("de la parole,", "du language,")
						+ "mais que ce désir soit intimement associé, " + breath();
				speech += "immédiatement transigable, chaque nouveau désir. " + breathLonger();
				// ALWAYCHANGE s(breath() + "always, " + breath()) +
				speech += "Tout cela pour dire que, nous sommes à votre écoute. " + breath();
				speech += s("Vous promettant, " + breath(), "vous promettant") + "d'entendre "
						+ s("peu importe", "tout") + "ce que vous brûlez de nous dire. "; // ALWAYCHANGE
																																							// always,
				break;
		}

		return speech + breathLong();
	}

}
