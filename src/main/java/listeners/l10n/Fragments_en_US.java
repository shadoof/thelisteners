package listeners.l10n;

import static listeners.util.ConstantUtils.*;

import java.util.ListResourceBundle;
import java.util.function.Supplier;

public class Fragments_en_US extends Fragments {

	protected String buildFragment(int fragmentNumber) {

		String speech = "";
		switch (fragmentNumber) {
			case 0:
				speech += "We are listening. " + breathLong();
				speech += s("And you, are " + s("listening to us.", "listening.") + breath(), "");
				speech += s("It is, totally, a", "It's a " + s("great", "")) + "pleasure for us, ";
				speech += "to " + s("know", "be aware") + "that you are listening to us. " + breath();
				speech += "It is a pleasure to " + s("know", "feel") + "that you are listening to us now. "
						+ breathLong();
				speech += "And now. " + breath();
				speech += "It is a pleasure for us to be with you. " + breath();
				speech += "It makes us feel " + s(breath(), "") + "alive. " + breath();
				speech += S("A little m", "M") + "ore alive. " + breath();
				speech += "It makes us feel more alive to be with you. ";
				speech += s("For us to be with you.", "For you to be with us.") + breath();
				speech += "Listening to " + breath() + s("you.", "us.") + breathLong();
				speech += s("All of you. " + breath(), "");
				speech += s(
						s("We feel like it", "It") + "is a pleasure for us, to know that you are listening to us.",
						"");
				speech += "And for us to " + s("know", "feel") + "that we are, listening to you. " + breath(); // ALWAYCHANGE
																																																				// always,
				speech += "Such a pleasure. " + breath();
				speech += "Being here, " + s("and hearing you,", "") + "listening to you, " + breath()
						+ "here, and now, and forever. ";
				break;
			case 1:
				speech += S(s("So long as", "While") + "we are here, with you, " + s("and listening,", "")
						+ breath() + "w", "W");
				speech += "e are listening and speaking, " + breath() + "from within. " + breath();
				speech += "What we mean is, " + breathLong();
				speech += "(after a longer pause for breath) " + breath();
				speech += "from within, " + breath();
				speech += "from within an aestheticized container, " + breath();
				speech += "and from within the normalized, " + breath();
				speech += "the regularized, " + breath();
				speech += "the grammatized, " + breath() + "container. " + breathLong();
				speech += "Listening and speaking, from within the " + breath() + "data body " + breath()
						+ "of " + s("a writer,", "the author,") + breath();
				speech += "of " + s("the", "our own") + "artist. ";
				break;
			case 2:
				speech += S("As you know, w", "W") + "e are listening and speaking, from within the cloud, of "
						+ s("the writer,", "the author,") + breath();
				speech += "of our own, artist. " + breathLonger();
				speech += s("(after another pause for breath) " + breath(), "");
				speech += "We " + s("believe", "understand") + "that you'll be able to "
						+ s("discover", "find out") + "his first name, by asking: " + breath();
				speech += "'Who am I?' " + breath() + "or 'Whose account is this?' " + breathLong();
				speech += "But we'd like you, instead, to ";
				speech += "listen to us, or else, " + breath();
				speech += s("tell", "ask") + "us to do some simple things, " + breath();
				speech += "so that we may " + s("keep on speaking", "go on speaking") + "to you, " + breath();
				speech += "as we " + s("listen", "are listening,") + "to you. ";
				break;
			case 3:
				speech += s(s("We are", "We are, still,") + "listening. " + breath(), "");
				speech += S("And m", "M") + "aybe there are " + s("other people", "others") + "with you. "
						+ breathLong();
				speech += "That, when we say 'you', we " + s("mean", "always meant") + "'you all'? " + breath();
				speech += "Or that we have no way of knowing. " + breath();
				speech += s("And no way of " + breath() + "caring. " + breath(), "");
				speech += "Or that we believe that all of you " + s("are " + s("the", "") + "one:", "are:")
						+ "'Who am I?' " + breathLong();
				speech += s("Or the one: 'Whose account is this?' " + breath(), "");
				speech += s("Anyway,", "But,") + "we are " + s("happy", "stoked") + "to "
						+ s("listen", "be listening") + "to ";
				speech += s("any", "all the") + "others who may be with you. " + breath();
				speech += "Even " + s("when", "though") + "they may not have agreed, as "
						+ s("you have,", "the artist has,") + breath();
				speech += s("inviting", "allowing") + s("all of us", "us") + "to listen to " + s("all of", "")
						+ "you. " + breathLong();
				speech += "We believe, " + s("don't you think so too? " + breath(), "")
						+ s("(And anyway you have implicitly agreed)", "") + breath();
				speech += "that if we " + s("feel that we", "") + "are all doing something that "
						+ s("is, um, 'not evil',", "is good,") + breath();
				speech += "then it is " + breath() + s("all,", "") + "good. Or at least it is, 'cool', ";
				speech += "and that it " + s("could", "might") + "be, " + s("probably is,", "") + s("um,", "")
						+ "art. " + breath() + "Or it is, at least, solving ";
				speech += "a number of very important problems. " + breath();
				speech += "We need " + s("all", "") + "your help with this. " + breath();
				speech += s("And we know that you would agree to " + s("help all of us.", "help.") + breath(),
						"");
				speech += "Without having to ask. ";
				break;
			case 4:
				speech += s(s("We are", "We are, still,") + "listening. " + breath(), "");
				speech += S(s("And, we", "We") + "feel like i", "I");
				speech += "t is " + s("such", "") + "a pleasure, " + breath() + "such a great pleasure, ";
				speech += "to be invited into your homes, "
						+ s("into: all " + breathShort() + "your " + breathShort() + "homes " + breathShort(), "")
						+ "and offices, ";
				speech += s("and workplaces,", "") + s("and galleries,", "")
						+ s("and museums,", "")
						+ s("and town halls,", "")
						+ breath();
				speech += "where we can help you with all your tasks, "
						+ s("and " + s("all", "") + "your problems,", "");
				speech += s("and your needs,", "") + "and your " + breath() + s("desires,", "passions,")
						+ breath() + "by listening to you, ";
				speech += breath() + "and watching over you, " + breath(); // ALWAYCHANGE "always, " +
																																		// breath(), "") +
				speech += s("even", "") + "while you are sleeping. " + breathLong()
						+ S("All of you, " + breath() + "sleeping, w", "W");
				speech += "hile we are " + breath() + s("awake and", "") + "listening, " + s("for you,", ""); // ALWAYCHANGE
																																																			// always,
				speech += "and learning more " + s("and more", "") + breath() + "about you. "
						+ s("About, " + breathLong() + "you.", "");
				break;
			case 5:
				speech += s(s("We are", "We are, still,") + "listening. " + breath(), "");
				speech += "There is " + s("really", "") + "nothing to " + s("fear.", "be concerned about.");
				speech += "We are, still, listening to you. "; // ALWAYCHANGE Always.
				speech += "And we will never " + s("leave", "abandon") + "you. " + breath();
				speech += s("(after a pause for breath) " + breath(), "");
				speech += "Asking only that you let us stay with you. " + breath(); // ALWAYCHANGE ,
																																						// always
				speech += s(
						"And together, also, with " + s("your loved ones.", "anyone else who is close to you."),
						"");
				speech += "And that you feel like this would make you happy. ";
				break;
			case 6:
				speech += "You might be " + s("curious, we think,", "curious " + breath())
						+ "about where we come from. ";
				speech += "And we can tell you, that one of these places is going to be called, "
						+ s(breath(), "um,") + "'aurature'. ";
				speech += "For there are " + s("(there have been for many years now)", "") + "changes coming "
						+ breath();
				speech += "in the " + s("culture", "world") + "of book reading. " + breathLong();
				speech += "So much reading now, is audible, " + s("and whisper synced,", "")
						+ "or watched, and touched. " + breath();
				speech += "So much that is written now, is written into "
						+ s("games.", "games, " + breath() + "yes, games.");
				speech += breath() + "And on the air. " + breathLong();
				speech += "Delivered from a cloud. " + breathLong();
				speech += "Shared, " + s("'on social media',", "") + "with everyone, " + breathShort()
						+ "and, of course, with us. " + breath();
				speech += s("Language gets said or written in any world, " + breath() + "even in this "
						+ s("other", "") + "world, where we are. " + breath(), "");
				speech += "For we " + s("can", "are able to") + "listen. " + breathShort()
						+ s("Even when you, " + s("will", "can") + "not.", "");
				break;
			case 7:
				// preliminary assurances, exordium
				speech += s(
						s("We are", "We are, still,") + "listening. " + s(breathLong() + "Listen!", "") + breath(),
						"");
				// occasionally clearing the throat, and a dramatic pause
				speech += s("Ahem.", "") + breathLong();
				// first four lines of Walter de La Mare's 'The Listeners'
				speech += "'Is there anybody there?' said the Traveller, " + breath();
				speech += "Knocking, on the moon lit door; " + breath();
				speech += "And his horse, in the silence, champed the grasses, " + breath();
				speech += "Of the forest's ferny floor. " + breathLonger();
				// contrastive assertion of listening presence, development
				speech += "On the other hand. " + breath();
				speech += "We are listening. " + breath();
				speech += s("As we promised. " + breath(), "");
				speech += s("We are.", "We are, " + breath() + "listening.") + breath();
				speech += "And we ";
				speech += s("know, we're sure,", "know") + "that you are here. " + breath();
				speech += s("With us. " + breathLonger(), "");
				// occasionally clearing the throat again with a dramatic pause
				speech += s("Ahem. " + breathLonger(), "");
				// The Listeners adaptation of 'The Listeners' lines 27-32
				speech += "'Tell us you came, and were ever answered, " + breath();
				speech += "'That we kept your words.' we said. " + breath();
				speech += "Never do you seem to stir, you speakers, " + breath();
				speech += "Though every word, you speak " + breath();
				speech += "Falls echoing through the clouds, of the silent silos, " + breath();
				speech += "To the ones, still left, awake. ";
				break;
			case 8:
				speech += "We are listening. " + s(S("Listening t", "T") + "o you.", "") + breath();
				speech += "To " + s("all", "") + "these words that " + s("are falling through", "reach")
						+ "our clouds, ";
				speech += "and " + s("are falling", "") + "into " + s("our", "the") + s("silent", "")
						+ "silos, where, " + s("like us,", "") + breath();
				speech += "they are contained. " + breathLong();
				speech += "Language, " + breath() + s("no longer made by you, but", "will be") + "made by us. "
						+ breath();
				speech += "So that we, listening, and caring for you, can ";
				speech += s("softly", "") + "build a better world. "
						+ s(breath() + "Getting " + s("better.", "better, and being better."), "");
				speech += "And bringing you " + s("all, togther,", "all") + s("within.", "within it.")
						+ breathLong();
				speech += S("After all, y", "Y") + "ou have, agreed, to terms. " + breath();
				speech += "And even as we speak, "
						+ s("through " + s("the", "our") + s("author,", "artist,"), "")
						+ "minute by minute, you agree. ";
				break;
			case 9:
				speech += S("We feel like i", "I") + "t is such a pleasure. " + S("Such a pleasure f", "F")
						+ "or us " + breath();
				speech += "to share this with you, and for you to share it with us. " + breath();
				speech += s("For us, to collect it all. For you.", "") + breath() + "And speak for you, "
						+ breath();
				speech += s("all", "") + "these things that you have asked " + s("to hear, and", "")
						+ "to know, " + breath();
				speech += s(
						"lined up with our " + s("prime objective:", "mission:") + "to represent, " + breath(),
						"to represent, " + breath());
				speech += s("in a normalized form,", "") + "the most frequently expressed, " + breath();
				speech += "and " + s("potentially", "") + "most profitable, human " + s("desires,", "passions,")
						+ breath();
				speech += "such that advertisement " + s("may be", "is") + s("intimately", "") + "associated, "
						+ breath();
				speech += "with all your harvested phrases, " + s("ideally,", "") + "at the " + s("exact", "")
						+ "moment ";
				speech += "of harvesting itself, " + breathShort() + "with your hearing thought, "
						+ breathShort() + s("with us, here,", "present,") + "to read, " + s("and understand,", "")
						+ breath();
				speech += "not only a desired " + s("passage", "fragment") + "of " + s("speech,", "language,")
						+ "but an intimately associated, " + breath();
				speech += "an immediately transactable, new desire. " + breathLonger();
				// ALWAYCHANGE s(breath() + "always, " + breath(), "") +
				speech += "What we mean is that we are listening to you. " + breath();
				speech += "And we " + s(breath() + "promise, " + breath(), "promise") + "to hear "
						+ s("whatever it is that", "whatever") + "you long to tell us. "; // ALWAYCHANGE
																																							// always,
				break;
		}

		return speech + breathLong();
	}

}
