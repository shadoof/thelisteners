package listeners.l10n;

import static listeners.util.Utils.*;

import java.util.ListResourceBundle;
import java.util.function.Supplier;

public class Fragments_es_MX extends Fragments {

	protected String buildFragment(int fragmentNumber) {

		// TODO
		String speech = "";
		switch (fragmentNumber) {
			case 0:
				speech += "Estamos escuchándote. " + breathLong();
				speech += s("Y tú, estás " + s("Escuchándonos.", "escuchando.") + breath());
				speech += "Es un " + s("gran") + "placer para nosotros, ";
				speech += s("saber", "ser conscientes") + "de que nos estás escuchando. " + breath();
				speech += "Es un placer " + s("saber", "ser consciente") + "de que nos estás escuchando. "
						+ breathLong();
				speech += "And now. " + breath();
				speech += "It is a pleasure for us to be with you. " + breath();
				speech += "It makes us feel " + s(breath()) + "alive. " + breath();
				speech += S("A little m", "M") + "ore alive. " + breath();
				speech += "It makes us feel more alive to be with you. ";
				speech += s("For us to be with you.", "For you to be with us.") + breath();
				speech += "Listening to " + breath() + s("you.", "us.") + breathLong();
				speech += s("All of you. " + breath());
				speech += s("It is a pleasure for us, to know that you are listening to us.");
				speech += "And for us to " + s("know", "be aware") + "that we are, listening to you. "
						+ breath(); // ALWAYCHANGE always,
				speech += "Such a pleasure. " + breath();
				speech += "Being here, " + s("and hearing you,") + "listening to you, " + breath()
						+ "here, and now, and forever. ";
				break;
			case 1:
				speech += S(s("So long as", "While") + "we are here, with you, " + s("and listening,")
						+ breath() + "w", "W");
				speech += "e are listening and speaking, " + breath() + "from within. " + breath();
				speech += "That is to say, " + breathLong();
				speech += "(after a longer pause for breath) " + breath();
				speech += "from within, " + breath();
				speech += "from within an aestheticized enclosure, " + breath();
				speech += "and from within the normalized, " + breath();
				speech += "the regularized, " + breath();
				speech += "the grammatized, " + breath() + "enclosure. " + breathLong();
				speech += "Listening and speaking, from within the, data body, of "
						+ s("a writer,", "the author,") + breath();
				speech += "of " + s("the", "our own") + "artist. ";
				break;
			case 2:
				speech += S("As you know, w", "W") + "e are listening and speaking, from within the cloud, of "
						+ s("the writer,", "the author,") + breath();
				speech += "of our own, artist. " + breathLonger();
				speech += s("(after another pause for breath) " + breath());
				speech += "We " + s("believe", "understand")
						+ "that you may be able to discover, at least, his first name, by asking: " + breath();
				speech += "‘Who am I?’ " + breath() + "or ‘Whose account is this?’ " + breathLong();
				speech += "But we would like you, instead, either to ";
				speech += "listen to us, or, " + breath();
				speech += s("tell", "ask") + "us to do some simple things, " + breath();
				speech += "so that we may " + s("continue to speak", "go on speaking") + "to you, " + breath();
				speech += "as we " + s("listen", "are listening,") + "to you. ";
				break;
			case 3:
				speech += s(s("We are", "We are, still,") + "listening. " + breath());
				speech += S("And p", "P") + "erhaps there are " + s("other people", "others") + "with you. "
						+ breathLong();
				speech += "That, when we say ‘you’, we " + s("mean", "always meant") + "‘all of you’? "
						+ breath();
				speech += "Or that we have no way of knowing. " + breath();
				speech += s("And no way of " + breath() + "caring. " + breath());
				speech += "Or that we believe that all of you " + s("are " + s("the") + "one:", "are:")
						+ "‘Who am I?’ " + breathLong();
				speech += s("Or the one: ‘Whose account is this?’ " + breath());
				speech += s("However,", "But,") + "we are " + s("happy", "delighted") + "to "
						+ s("listen", "be listening") + "to ";
				speech += s("any", "all the") + "others who may be with you. " + breath();
				speech += "Even " + s("when", "though") + "they may not have agreed, as "
						+ s("you have,", "the artist has,") + breath();
				speech += s("inviting", "allowing") + s("all of us", "us") + "to listen to " + s("all of")
						+ "you. " + breathLong();
				speech += "We believe, " + s("and don’t you agree? " + breath())
						+ s("(or rather, you have implicitly agreed)") + breath();
				speech += "that if we " + s("feel that we") + "are all doing something that "
						+ s("is, um, ‘not evil’,", "is good,") + breath();
				speech += "then it is " + breath() + s("really quite")
						+ "good. Or it is, at least, ‘cool’, ";
				speech += "and that it " + s("could", "may") + "be, " + s("very likely is,") + s("um,")
						+ "art. " + breath() + "Or it is, at least, solving ";
				speech += "a number of very important problems. " + breath();
				speech += "We need " + s("all") + "your " + s("assistance", "help") + "with this. "
						+ breath();
				speech += s("And we know that you would, of course, agree to help us. " + breath());
				speech += "Without our having to " + s("ask.", "ask you.");
				break;
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
