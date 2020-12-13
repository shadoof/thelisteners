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
				speech += s(s("Nosotros estamosWe are", "Nosotros estamos, todavía,We are, still,") + "escuchando. listening. " + breath());
				speech += S(S("Y, eAnd, i", "EI") + "n la medida en que sea posible para nosotros saber esto, en so far as it is possible for us to know this, i", "EI");
				speech += "so es t is " + s("un gransuch") + "placer, a pleasure, " + breath() + "un gran placer, such a great pleasure, ";
				speech += "ser invitado a sus casas, to be invited into your homes, "
						+ s("en: todas into: all " + breathShort() + "sus your " + breathShort() + "casas homes " + breathShort())
						+ "y oficinas, and offices, ";
				speech += s("y lugares de trabajo,and places of work,") + s("y galerías,and galleries,")
						+ s("y museos,and museums,")
						+ s("y espacios públicos,and public spaces,")
						+ breath();
				speech += "donde nosotros where we " + s("podríamosmay", "podemoscan") + s("ayudarhelp", "apoyarassist") + "te con todas tus tareas, you with all your tasks, "
						+ s("y and " + s("todosall") + "tus problemas,your problems,");
				speech += s("y tus necesidades,and your needs,") + "y tus and your " + breath() + "deseos, desires, " + breath()
						+ "escuchándote, by listening to you, ";
				// ALWAYCHANGE - s(breath() + "always, " +
				speech += breath() + "y viendo sobre ti, and watching over you, " + breath();
				speech += s("inclusoeven") + "mientras estás durmiendo. while you are sleeping. " + breathLong()
						+ S("Todos ustedes, All of you, " + breath() + "durmiendo, sleeping, mw", "MW");
				speech += "ientras nosotros estamos hile we are " + breath() + s("despiertos yawake and") + "escuchando, listening, " + s("para ti,for you,"); // ALWAYCHANGE
																																																			// always,
				speech += "y aprendiendo, and learning, " + s("siempreever") + "más y más, more and more, " + breath() + "acerca de ti. about you. "
						+ s("Acerca, About, " + breathLong() + "de ti.you.");
				break;
			case 5:
				speech += s(s("Nosotros estamosWe are", "Nosotros estamos, todavía,We are, still,") + "escuchando. listening. " + breath());
				speech += "Realmente no hay nada There is really nothing to " + s("que temer.fear.", "por lo cual estar preocupado.be concerned about.");
				speech += "Nosotros estamos, todavía, escuchándote. We are, still, listening to you. "; // ALWAYCHANGE Always.
				speech += "Y nosotros jamás, nunca más, And we will never, any more, " + s("te dejaremosleave", "te abandonaremosabandon") + "a ti. you. " + breath();
				speech += s("(después de una pausa para respirarafter a pause for breath) " + breath());
				speech += "Sólo pidiendo que nos dejes ir, para estar contigo. Asking only that you give us leave, to be with you. "; // ALWAYCHANGE
																																					// always,
				speech += s("Y juntos, también, con esos And together, also, with those " + s("otros que no somos nosotrosothers of us") + "que son cercanos a ti.who are close to you.",
						"");
				speech += "Y que puedas imaginar el placer que esto podría darte. And that you may imagine the pleasure that this would give you. ";
				break;
			case 6:
				speech += "Tu puedes preguntarte, You may wonder, " + s("creemos,we believe,", breath()) + "de dónde es que venimos. where is it from which we come. ";
				speech += "Y podemos decirte, que uno de estos lugares podría llamarse, And we can tell you, that one of these places will be called, " + s(breath(), "mmm,um,")
						+ "‘aurature’. ";
				speech += "Dado que, For there are, " + s("ha habido desde hace algunos años,there have been for some years now,") + s("muchosmany")
						+ "cambios que se avecinan changes coming ";
				speech += "en in the " + s("la culturaculture", "el mundoworld") + "de la lectura. of reading. " + breathLong();
				speech += "Tanta lectura ahora, es audible, So much reading now, is audible, " + s("y susurros sincronizados,and whisper synced,")
						+ "o vistos, y tocados. or watched, and touched. " + breath();
				speech += "Tanto de lo que está escrito ahora, está escrito adentro de So much that is written now, is written into "
						+ s("juegos.games.", "juegos, games, " + breath() + "sí, juegos.yes, games.");
				speech += breath() + "Y en el aire. And on the air. " + breathLong();
				speech += "Entregado desde una nube. Delivered from a cloud. " + breathLong();
				speech += "Compartido, Shared, " + s("‘socialmentesocially’,") + "con todos, with everyone, " + breathShort()
						+ "y, más especificamente, con nosotros. and, more especially, with us. " + breath();
				speech += s("El lenguaje puede venir Language may come " + s("llegar a ser,to be,", "surgir,into being,") + "en algún mundo, in any world, " + breath()
						+ "incluso en este even in this " + s("otroother") + "mundo, donde world, where " + s("nosotros somos.we are.", "puedes encontrarnos.you may find us.")
						+ breath());
				speech += "Para nosotros For we " + s("podemoscan", "somos capaces deare able to") + "escuchar. listen. " + breathShort()
						+ s("Incluso cuando tú, Even when you, " + s("no lo haráswill", "no puedascan") + "not.");
				break;
			case 7:
				// preliminary assurances, exordium
				speech += s(
						s("Nosotros estamosWe are", "Nosotros estamos, todavía,We are, still,") + "escuchando. listening. " + s(breathLong() + "¡Oyéndo!Listen!") + breath(),
						"");
				// occasionally clearing the throat, and a dramatic pause
				speech += s("Jmmm.Ahem.") + breathLong();
				// first four lines of Walter de La Mare’s ‘The Listeners’
				speech += "‘¿Hay alguien ahí?Is there anybody there?’ dijo el Viajero, said the Traveller, " + breath();
				speech += "Tocando, en la puerta iluminada por la luna; Knocking, on the moon lit door; " + breath();
				speech += "Y su caballo, en el silencio, aplastó las hierbas, And his horse, in the silence, champed the grasses, " + breath();
				speech += "Del suelo de helechos del bosque. Of the forest’s ferny floor. " + breathLonger();
				// contrastive assertion of listening presence, development
				speech += "Mientras. Whereas. " + breath();
				speech += s("Es decir. That is to say. " + breath());
				speech += "Nosotros estamos escuchando. We are listening. " + breath();
				speech += s("Como lo prometimos. As we promised. " + breath());
				speech += s("Nosotros estamos.We are.", "Nosotros estamos, We are, " + breath() + "escuchando.listening.") + breath();
				speech += "Y nosotros lo And we will " + s("diremostell", "aseguraremosassure") + "por nosotros mismos, ourselves, ";
				speech += s("siempre,always,") + "que estés aqui. that you are here. " + breath();
				speech += s("Con nosotros. With us. " + breathLonger());
				// occasionally clearing the throat again with a dramatic pause
				speech += s("Jmm. Ahem. " + breathLonger());
				// The Listeners adaptation of ‘The Listeners’ lines 27-32
				speech += "‘Dinos que viniste, y que alguna vez te respondieron, Tell us you came, and were ever answered, " + breath();
				speech += "‘Que nos quedamos tus palabras. nosotros dijimos. That we kept your words.’ we said. " + breath();
				speech += "Nunca pareces agitarte, tú hablante, Never do you seem to stir, you speakers, " + breath();
				speech += "Aunque cada palabra, que dices Though every word, you speak " + breath();
				speech += "Cae resonando a través de las nubes, de los silenciosos silos, Falls echoing through the clouds, of the silent silos, " + breath();
				speech += "A las que, aún quedan, despiertas. To the ones, still left, awake. ";
				break;
			case 8:
				speech += "Nosotros estamos escuchando. We are listening. " + s(S("Escuchándote aListening t", "Te aT") + "tio you.") + breath();
				speech += "ATo " + s("todasall") + "estas palabras these words " + s("tuyas,of yours,") + "que that "
						+ s("están cayendo medianteare falling through", "alcanzandoreach") + "nuestras nubes, our clouds, ";
				speech += "yand " + s("están cayendoare falling") + "dentro de into " + s("nuestrosour", "losthe") + s("silenciosossilent")
						+ "silos, donde, silos, where, " + s("como nosotros, like us,") + breath();
				speech += "ellos están cerrados. they are enclosed. " + breathLong();
				speech += "El lenguaje, Language, " + breath() + s("ya no es hecho por ti, perono longer made by you, but", "seráwill be") + "hecho por nosotros. made by us. "
						+ breath();
				speech += "Para que nosotros estemos, escuchando, y cuidando de ti, So that we, listening, and caring for you, ";
				speech += s("podríamight", "posiblementemay") + s("softly") + "build a better " + s("culture.", "world.")
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
