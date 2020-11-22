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
				speech += "Y ahora. And now. " + breath();
				speech += "Es un placer para nosotros estar contigo. It is a pleasure for us to be with you. " + breath();
				speech += "Nos hace sentir It makes us feel " + s(breath()) + "vivos. alive. " + breath();
				speech += S("Un poco mA little m", "M") + "ás vivos. ore alive. " + breath();
				speech += "Nos hace sentir más vivos estar contigo. It makes us feel more alive to be with you. ";
				speech += s("Para que nosotros estemos contigo. For us to be with you.", "Para que estés con nosotros.") + breath();
				speech += "Escuchando Listening to " + breath() + s("te.you.", "nos.us.") + breathLong();
				speech += s("Todos ustedes. All of you. " + breath());
				speech += s("Es un placer para nosotros, saber que nos estás escuchando.It is a pleasure for us, to know that you are listening to us.");
				speech += "Y para nosotros And for us to " + s("saberknow", "ser conscienes de be aware") + "que estamos, escuchándote. that we are, listening to you. "
						+ breath(); // ALWAYCHANGE always,
				speech += "Un gran placer. Such a pleasure. " + breath();
				speech += "Estar aqui, Being here, " + s("y estar escuchándote, and hearing you,") + "escuchándote, listening to you, " + breath()
						+ "aquí, y ahora, y siempre. here, and now, and forever. ";
				break;
			case 1:
				speech += S(s("Siempre y cuandoSo long as", "MientrasWhile") + "estemos aquí, contigo, we are here, with you, " + s("y escuchando,and listening,")
						+ breath() + "nw", "NW");
				speech += "osotrose estamos escuchando y hablando, are listening and speaking, " + breath() + "desde dentro. from within. " + breath();
				speech += "Es decir, That is to say, " + breathLong();
				speech += "(after a longer pause for breath) " + breath();
				speech += "desde dentro, from within, " + breath();
				speech += "desde dentro de un recinto estetizado, from within an aestheticized enclosure, " + breath();
				speech += "y desde dentro del normalizado, and from within the normalized, " + breath();
				speech += "el regularizado, the regularized, " + breath();
				speech += "el gramatizado, the grammatized, " + breath() + "recinto. enclosure. " + breathLong();
				speech += "Esuchando y hablando, desde dentro del, cuerpo de datos, de Listening and speaking, from within the, data body, of "
						+ s("un escritor,a writer,", "el autor,the author,") + breath();
				speech += "de of " + s("elthe", "nuestro propioour own") + "artista. artist. ";
				break;
			case 2:
				speech += S("Como tú sabes, As you know, nw", "NW") + "osotros estamos escuchando y hablando, desde dentro de la nube, de e are listening and speaking, from within the cloud, of "
						+ s("el escritorthe writer,", "el autor,the author,") + breath();
				speech += "de nuestro propio, artista. of our own, artist. " + breathLonger();
				speech += s("(después de otra pausa para respirarafter another pause for breath) " + breath());
				speech += "Nosotros We " + s("creemosbelieve", "entendemosunderstand")
						+ "que puedes descubrir, al menos, su primer nombre, preguntando: that you may be able to discover, at least, his first name, by asking: " + breath();
				speech += "‘¿Quién soy yo? Who am I?’ " + breath() + "o or ‘¿De quién es esta cuenta?Whose account is this?’ " + breathLong();
				speech += "Pero nos gustaría que, en cambio, en vez de  But we would like you, instead, either to ";
				speech += "escucharnos, o, listen to us, or, " + breath();
				speech += s("decirtell", "preguntarask") + "nos sobre hacer algunas cosas simples, us to do some simple things, " + breath();
				speech += "para que nosotros podamos so that we may " + s("continuar hablandocontinue to speak", "seguir hablandogo on speaking") + "teto you, " + breath();
				speech += "como nosotros as we " + s("te escuchamoslisten", "estamos escuchandoteare listening,") + "a ti. to you. ";
				break;
			case 3:
				speech += s(s("Nosotros estamosWe are", "Nosotros estamos, todavía,We are, still,") + "escuchando. listening. " + breath());
				speech += S("Y qAnd p", "QP") + "quizás existen erhaps there are " + s("otras personasother people", "otrosothers") + "contigo. with you. "
						+ breathLong();
				speech += "Que, cuando nosotros decimos 'tú', nosotros That, when we say ‘you’, we " + s("significamean", "siempre significaalways meant") + "‘todos ustedesall of you’? "
						+ breath();
				speech += "O que nosotros no tenemos forma de saberlo. Or that we have no way of knowing. " + breath();
				speech += s("Y no hay forma de And no way of " + breath() + "cuidarlo. caring. " + breath());
				speech += "O que nosotros creemos que todos ustedes Or that we believe that all of you " + s("sonare " + s("elthe") + "único:one:", "are:")
						+ "‘¿Quién soy yo?Who am I?’ " + breathLong();
				speech += s("O la única: '¿De quién es esta cuenta?' Or the one: ‘Whose account is this?’ " + breath());
				speech += s("Sin embargo,However,", "Pero,But,") + "nosotros estamoswe are " + s("feliceshappy", "encantadosdelighted") + "deto "
						+ s("escucharlisten", "estar escuchandobe listening") + "ato ";
				speech += s("cualquierany", "todo aquelall the") + "otro que pueda estar contigo. others who may be with you. " + breath();
				speech += "InclusoEven " + s("cuandowhen", "aunquethough") + "ellos puedan no estar de acuerdo, como they may not have agreed, as "
						+ s("tú has estado,you have,", "el artista ha estadothe artist has,") + breath();
				speech += s("invitandoinviting", "permitiendoallowing") + s("a todos nosotrosall of us", "nosus") + "a escucharte a to listen to " + s("todos ustedes y aall of")
						+ "tiyou. " + breathLong();
				speech += "Nosotros creemos, We believe, " + s("y ¿no estás de acuerdo? and don’t you agree? " + breath())
						+ s("(o más bien, has aceptado implicitamente or rather, you have implicitly agreed)") + breath();
				speech += "que si nosotros that if we " + s("sentimos que nosotrosfeel that we") + "estamos haciendo algo queare all doing something that "
						+ s("es, mmm, is, um, ‘no malonot evil’,", "es buenois good,") + breath();
				speech += "entonces eso es then it is " + breath() + s("realmente muyreally quite")
						+ "bueno. O eso es, al menos, ‘padre’, good. Or it is, at least, ‘cool’, ";
				speech += "y que eso and that it " + s("podríacould", "deberíamay") + "ser, be, " + s("muy probablemente es,very likely is,") + s("mmm,um,")
						+ "arte. art. " + breath() + "O eso está, al menos, resolviendo Or it is, at least, solving ";
				speech += "un número de problemas muy importantes. a number of very important problems. " + breath();
				speech += "Nosotros necesitamos We need " + s("todaall") + "tu your " + s("asistenciaassistance", "ayudahelp") + "con esto. with this. "
						+ breath();
				speech += s("Y nosotros sabemos que tú estarías, por supuesto, de acuerdo en ayudarnos. And we know that you would, of course, agree to help us. " + breath());
				speech += "Sin que nosotros tengamos que Without our having to " + s("preguntar.ask.", "preguntarte.ask you.");
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
