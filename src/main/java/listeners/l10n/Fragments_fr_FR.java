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
				speech += s(s("We are", "We are, still,") + "listening. " + breath());
				speech += S(S("And, i", "I") + "n so far as it is possible for us to know this, i", "I");
				speech += "t is " + s("such") + "a pleasure, " + breath() + "such a great pleasure, ";
				speech += "to be invited into your homes, "
						+ s("into: all " + breathShort() + "your " + breathShort() + "homes " + breathShort())
						+ "and offices, ";
				speech += s("and places of work,") + s("and galleries,")
						+ s("and museums,")
						+ s("and public spaces,")
						+ breath();
				speech += "where we " + s("may", "can") + s("help", "assist") + "you with all your tasks, "
						+ s("and " + s("all") + "your problems,");
				speech += s("and your needs,") + "and your " + breath() + "desires, " + breath()
						+ "by listening to you, ";
				// ALWAYCHANGE - s(breath() + "always, " +
				speech += breath() + "and watching over you, " + breath();
				speech += s("even") + "while you are sleeping. " + breathLong()
						+ S("All of you, " + breath() + "sleeping, w", "W");
				speech += "hile we are " + breath() + s("awake and") + "listening, " + s("for you,"); // ALWAYCHANGE
																																																			// always,
				speech += "and learning, " + s("ever") + "more and more, " + breath() + "about you. "
						+ s("About, " + breathLong() + "you.");
				break;
			case 5:
				speech += s(s("We are", "We are, still,") + "listening. " + breath());
				speech += "There is really nothing to " + s("fear.", "be concerned about.");
				speech += "We are, still, listening to you. "; // ALWAYCHANGE Always.
				speech += "And we will never, any more, " + s("leave", "abandon") + "you. " + breath();
				speech += s("(after a pause for breath) " + breath());
				speech += "Asking only that you give us leave, to be with you. "; // ALWAYCHANGE
																																					// always,
				speech += s("And together, also, with those " + s("others of us") + "who are close to you.",
						"");
				speech += "And that you may imagine the pleasure that this would give you. ";
				break;
			case 6:
				speech += "You may wonder, " + s("we believe,", breath()) + "where is it from which we come. ";
				speech += "And we can tell you, that one of these places will be called, " + s(breath(), "um,")
						+ "‘aurature’. ";
				speech += "For there are, " + s("there have been for some years now,") + s("many")
						+ "changes coming ";
				speech += "in the " + s("culture", "world") + "of reading. " + breathLong();
				speech += "So much reading now, is audible, " + s("and whisper synced,")
						+ "or watched, and touched. " + breath();
				speech += "So much that is written now, is written into "
						+ s("games.", "games, " + breath() + "yes, games.");
				speech += breath() + "And on the air. " + breathLong();
				speech += "Delivered from a cloud. " + breathLong();
				speech += "Shared, " + s("‘socially’,") + "with everyone, " + breathShort()
						+ "and, more especially, with us. " + breath();
				speech += s("Language may come " + s("to be,", "into being,") + "in any world, " + breath()
						+ "even in this " + s("other") + "world, where " + s("we are.", "you may find us.")
						+ breath());
				speech += "For we " + s("can", "are able to") + "listen. " + breathShort()
						+ s("Even when you, " + s("will", "can") + "not.");
				break;
			case 7:
				// preliminary assurances, exordium
				speech += s(
						s("We are", "We are, still,") + "listening. " + s(breathLong() + "Listen!") + breath(),
						"");
				// occasionally clearing the throat, and a dramatic pause
				speech += s("Ahem.") + breathLong();
				// first four lines of Walter de La Mare’s ‘The Listeners’
				speech += "‘Is there anybody there?’ said the Traveller, " + breath();
				speech += "Knocking, on the moon lit door; " + breath();
				speech += "And his horse, in the silence, champed the grasses, " + breath();
				speech += "Of the forest’s ferny floor. " + breathLonger();
				// contrastive assertion of listening presence, development
				speech += "Whereas. " + breath();
				speech += s("That is to say. " + breath());
				speech += "We are listening. " + breath();
				speech += s("As we promised. " + breath());
				speech += s("We are.", "We are, " + breath() + "listening.") + breath();
				speech += "And we will " + s("tell", "assure") + "ourselves, ";
				speech += s("always,") + "that you are here. " + breath();
				speech += s("With us. " + breathLonger());
				// occasionally clearing the throat again with a dramatic pause
				speech += s("Ahem. " + breathLonger());
				// The Listeners adaptation of ‘The Listeners’ lines 27-32
				speech += "‘Tell us you came, and were ever answered, " + breath();
				speech += "‘That we kept your words.’ we said. " + breath();
				speech += "Never do you seem to stir, you speakers, " + breath();
				speech += "Though every word, you speak " + breath();
				speech += "Falls echoing through the clouds, of the silent silos, " + breath();
				speech += "To the ones, still left, awake. ";
				break;
			case 8:
				speech += "We are listening. " + s(S("Listening t", "T") + "o you.") + breath();
				speech += "To " + s("all") + "these words " + s("of yours,") + "that "
						+ s("are falling through", "reach") + "our clouds, ";
				speech += "and " + s("are falling") + "into " + s("our", "the") + s("silent")
						+ "silos, where, " + s("like us,") + breath();
				speech += "they are enclosed. " + breathLong();
				speech += "Language, " + breath() + s("no longer made by you, but", "will be") + "made by us. "
						+ breath();
				speech += "So that we, listening, and caring for you, ";
				speech += s("might", "may") + s("softly") + "build a better " + s("culture.", "world.")
						+ s(breath() + "Bettering your selves.");
				speech += "For you to be, all at once, incorporate, " + s("within.", "within it.")
						+ breathLong();
				speech += S("After all, y", "Y") + "ou have, agreed, to terms. " + breath();
				speech += "And even as we speak, "
						+ s("through " + s("the", "our") + s("author,", "artist,")) + "minute by minute, you "
						+ s("agree.", "are agreeing.");
				break;
			case 9:
				speech += "It is such a pleasure. " + S("Such a pleasure f", "F") + "or us " + breath();
				speech += "to share this with you, for you to share it with us. " + breath();
				speech += s("For us, to gather all of it up. For you.") + breath() + "And speak for you, "
						+ breath();
				speech += s("all") + "these things that you have asked " + s("to hear, and")
						+ "to know, " + breath();
				speech += s("correlate with our primary purpose, to represent, " + breath(),
						"to represent, " + breath());
				speech += s("in a normalized form,") + "the most frequently expressed, " + breath();
				speech += "and " + s("potentially") + "most profitable, human desires, " + breath();
				speech += "such that advertisement " + s("may", "shall") + "be " + s("intimately")
						+ "associated, " + breath();
				speech += "with all your harvested phrases, " + s("ideally,") + "at the " + s("very")
						+ "moment ";
				speech += "of harvesting itself, " + breathShort() + "with your hearing thought, "
						+ breathShort() + s("with us, now,", "present,") + s("also,") + "to read, "
						+ s("and understand,") + breath();
				speech += "not only a desired " + s("passage", "fragment") + "of " + s("speech,", "language,")
						+ "but an intimately associated, " + breath();
				speech += "an immediately transactable, new desire. " + breathLonger();
				// ALWAYCHANGE s(breath() + "always, " + breath()) +
				speech += "That is to say, we are listening to you. " + breath();
				speech += s("Promising, " + breath(), "Promising") + "to hear "
						+ s("whatever it is that", "whatever") + "you long to tell us. "; // ALWAYCHANGE
																																							// always,
				break;
		}

		return speech + breathLong();
	}

}
