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
				speech += "Es un " + s("gran") + "placer para nosotres, ";
				speech += s("saber", "ser conscientes") + "de que nos estás escuchando. " + breath();
				speech += "Es un placer " + s("saber", "ser conscientes") + "de que nos estás escuchando. "
						+ breathLong();
				speech += "Y ahora. " + breath();
				speech += "Es un placer para nosotres estar contigo. " + breath();
				speech += "Nos hace sentir " + s(breath()) + "con vida. " + breath();
				speech += S("Un poco m", "M") + "ás vivos. " + breath();
				speech += "Estar contigo nos hace sentir más vives. ";
				speech += s("Estar contigo. ", "Que estés con nosotres.") + breath();
				speech += "Escuchando " + breath() + s("te.", "nos.") + breathLong();
				speech += s("Completamente. " + breath());
				speech += s("Es un placer para nosotres, saber que nos estás escuchando.");
				speech += "Y para nosotres " + s("saber", "ser conscientes de ") + "que estamos, escuchándote. "
						+ breath(); // ALWAYCHANGE always,
				speech += "Un gran placer. " + breath();
				speech += "Estar aquí, " + s("y escuchándote, ") + "escuchándote, " + breath()
						+ "aquí, y ahora, y siempre. ";
				break;
			case 1:
				speech += S(s("Siempre y cuando", "Mientras") + "estemos aquí, contigo, " + s("y escuchando,")
						+ breath() + "n", "N");
				speech += "osotres estamos escuchando y hablando, " + breath() + "desde dentro. " + breath();
				speech += "Es decir, " + breathLong();
				speech += "(after a longer pause for breath) " + breath();
				speech += "desde dentro, " + breath();
				speech += "desde dentro de un recinto estetizado, " + breath();
				speech += "y desde dentro del normalizado, " + breath();
				speech += "el regularizado, " + breath();
				speech += "el gramatizado, " + breath() + "recinto. " + breathLong();
				speech += "Esuchando y hablando, desde dentro del, cuerpo de datos, de "
						+ s("un escritor,", "el autor,") + breath();
				speech += "de " + s("el", "nuestro propio") + "artista. ";
				break;
			case 2:
				speech += S("Como tú sabes, n", "N") + "osotres estamos escuchando y hablando, desde dentro de la nube, de "
						+ s("el escritor,", "el autor,") + breath();
				speech += "de nuestro propio, artista. " + breathLonger();
				speech += s("(después de otra pausa para respirar) " + breath());
				speech += "Nosotres " + s("creemos", "entendemos")
						+ "que puedes descubrir, al menos, su primer nombre, preguntando: " + breath();
				speech += "‘¿Quién soy yo? ’ " + breath() + "o ‘¿De quién es esta cuenta?’ " + breathLong();
				speech += "Pero nos gustaría que, mejor, trates de ";
				speech += "escucharnos, o, " + breath();
				speech += s("decir", "pedir") + "nos que hagamos algunas cosas simples, " + breath();
				speech += "para que podamos " + s("continuar hablando", "seguir hablando") + "te, " + breath();
				speech += "como nosotres " + s("te escuchamos", "estamos escuchándote") + "a ti. ";
				break; 
			case 3:
				speech += s(s("Estamos", "Estamos, todavía,") + "escuchando. " + breath());
				speech += S("Y t", "T") + "al vez hay " + s("otras personas", "otres") + "contigo. "
						+ breathLong();
				speech += "Que, cuando decimos 'tú', queremos " + s("decir", "siempre decir") + "‘todes ustedes’? "
						+ breath();
				speech += "O que no tenemos forma de saberlo. " + breath();
				speech += s("Y que no hay forma de " + breath() + "que nos importe. " + breath());
				speech += "O que creemos que todes ustedes " + s("son " + s("el") + "único:", "son:")
						+ "‘¿Quién soy yo?’ " + breathLong();
				speech += s("O la única: '¿De quién es esta cuenta?' " + breath());
				speech += s("Sin embargo,", "Pero,") + "estamos " + s("felices", "encantades") + "de "
						+ s("escuchar", "estar escuchando") + "a ";
				speech += s("cualquier", "todo aquel") + "otro que pueda estar contigo. " + breath();
				speech += "Incluso" + s("cuando", "aunque") + "elles puedan no estar de acuerdo, como "
						+ s("tú has estado,", "el artista ha estado,") + breath();
				speech += s("invitando", "permitiendo") + s("a todes nosotres", "nos") + "a escuchar a " + "todes ustedes"
						+ breathLong();
				speech += "Creemos, " + s("y tú también crees " + breath())
						+ s("(o más bien, tú has aceptado implícitamente)") + breath();
				speech += "que si nosotres " + s("sentimos que") + "estamos haciendo algo que "
						+ s("es, mmm, ‘sin mala intención’,", "es bueno,") + breath();
				speech += "entonces eso es " + breath() + s("realmente muy")
						+ "bueno. O, al menos, ‘buena onda’, ";
				speech += "y que eso " + s("podría", "debería") + "ser, " + s("muy probablemente es,") + s("mmm,")
						+ "arte. " + breath() + "O eso está, al menos, resolviendo ";
				speech += "un número de problemas muy importantes. " + breath();
				speech += "Necesitamos " + s("toda") + "tu " + s("asistencia", "ayuda") + "con esto. "
						+ breath();
				speech += s("Y sabemos que tú estarás, por supuesto, de acuerdo en ayudarnos. " + breath());
				speech += "Sin que tengamos que " + s("pedirlo.", "pedírtelo.");
				break;
			case 4:
				speech += s(s("Estamos", "Estamos, todavía,") + "escuchando. " + breath());
				speech += S(S("Y, e", "E") + "n la medida en que sea posible para nosotres saber esto, en", "E");
				speech += "so es " + s("un gran") + "placer, " + breath() + "un verdadero placer, ";
				speech += "tener una invitación abierta para estar en sus casas, "
						+ s("en: todas " + breathShort() + "sus " + breathShort() + "casas " + breathShort())
						+ "y oficinas, ";
				speech += s("y lugares de trabajo,") + s("y galerías,")
						+ s("y museos,")
						+ s("y espacios públicos,")
						+ breath();
				speech += "donde nosotres " + s("podríamos", "podemos") + s("ayudar", "apoyar") + "les con todas sus tareas, "
						+ s("y " + s("todos") + "sus problemas,");
				speech += s("y necesidades,") + "y " + breath() + "deseos, " + breath()
						+ "escuchándoles, ";
				// ALWAYCHANGE - s(breath() + "always, " +
				speech += breath() + "y cuidándoles, " + breath();
				speech += s("incluso") + "mientras están durmiendo. " + breathLong()
						+ S("Todes ustedes, " + breath() + "durmiendo, m", "M");
				speech += "ientras nosotres estamos " + breath() + s("despiertes y") + "escuchando, " + s("por ustedes,"); // ALWAYCHANGE
																																																			// always,
				speech += "y aprendiendo, " + s("siempre") + "más y más, " + breath() + "acerca de ustedes. "
						+ s("Acerca, " + breathLong() + "de ti.");
				break;
			case 5:
				speech += s(s("Estamos", "Estamos, todavía,") + "escuchando. " + breath());
				speech += "Realmente no hay nada " + s("que temer.", "de qué preocuparse.");
				speech += "Estamos, todavía, escuchándote. "; // ALWAYCHANGE Always.
				speech += "Y jamás, nunca más, " + s("te dejaremos", "te abandonaremos") + "a ti. " + breath();
				speech += s("(después de una pausa para respirar) " + breath());
				speech += "Pidiendo a cambio, únicamente, que nos dejes estar contigo. "; // ALWAYCHANGE
																																					// always,
				speech += s("Y, también, con aquéllos" + s("otros nosotros") + "que son cercanos a ti.",
						"");
				speech += "Y que puedas imaginar el placer que esto te daría. ";
				break;
			case 6:
				speech += "Quizás te preguntarás, " + s("creemos,", breath()) + "de dónde es que venimos. ";
				speech += "Y podemos decirte, que uno de esos lugares se va a llamar, " + s(breath(), "mmm,")
						+ "la ‘auratura’. ";
				speech += "Dado que, hay" + s("desde hace algunos años,") + s("muchos")
						+ "cambios que se avecinan ";
				speech += "en " + s("la cultura de", "el mundo de") + "la lectura. " + breathLong();
				speech += "Muchas lecturas son ahora escuchables, " + s("y sincronizadas con los susurros,")
						+ "o vistas, y tocadas. " + breath();
				speech += "Mucho de lo que está escrito ahora, está escrito adentro de "
						+ s("juegos.", "juegos, " + breath() + "sí, juegos.");
				speech += breath() + "Y en el aire. " + breathLong();
				speech += "Entregado desde una nube. " + breathLong();
				speech += "Compartido, " + s("‘socialmente’,") + "con todo el mundo, " + breathShort()
						+ "y, más especificamente, con nosotres. " + breath();
				speech += s("El lenguaje podría " + s("llegar a ser,", "surgir,") + "en cualquier mundo, " + breath()
						+ "incluso en est " + s("otro") + "mundo, donde " + s("estamos nosotres.", "puedes encontrarnos.")
						+ breath());
				speech += "Ya que nosotres " + s("podemos", "somos capaces de") + "escuchar. " + breathShort()
						+ s("Incluso cuando tú, " + s("no lo hagas", "no puedas hacerlo") + "no.");
				break;
			case 7:
				// preliminary assurances, exordium
				speech += s(
						s("Estamos", "Estamos, todavía,") + "escuchando. " + s(breathLong() + "¡Escucha!") + breath(),
						"");
				// occasionally clearing the throat, and a dramatic pause
				speech += s("Jmmm.") + breathLong();
				// first four lines of Walter de La Mare’s ‘The Listeners’
				speech += "‘¿Hay alguien ahí?’ dijo el Viajero, " + breath();
				speech += "Tocando, en la puerta iluminada por la luna; " + breath();
				speech += "Y su caballo, en el silencio, aplastó las hierbas, " + breath();
				speech += "Del suelo de helechos del bosque. " + breathLonger();
				// contrastive assertion of listening presence, development
				speech += "Mientras. " + breath();
				speech += s("Es decir. " + breath());
				speech += "Estamos escuchando. " + breath();
				speech += s("Como lo prometimos. " + breath());
				speech += s("Estamos.", "Estamos, " + breath() + "escuchando.") + breath();
				speech += "Y nosotros nos " + s("diremos", "aseguraremos") + "a nosotres mismes, ";
				speech += s("siempre,") + "que estás aquí. " + breath();
				speech += s("Con nosotres. " + breathLonger());
				// occasionally clearing the throat again with a dramatic pause
				speech += s("Jmm. Ahem. " + breathLonger());
				// The Listeners adaptation of ‘The Listeners’ lines 27-32
				speech += "‘Dinos que viniste, y que se te respondió, " + breath();
				speech += "‘Que guardamos tus palabras.’ dijimos. " + breath();
				speech += "Nunca parecen agitarse, ustedes los que hablan, " + breath();
				speech += "Aunque cada palabra, que dicen " + breath();
				speech += "Cae resonando a través de las nubes, de los silenciosos silos, " + breath();
				speech += "Hasta las que, aún quedan, despiertas. ";
				break;
			case 8:
				speech += "Estamos escuchando. " + s(S("Escuchándote a", "te a") + "ti.") + breath();
				speech += "A " + s("todas") + "estas palabras " + s("tuyas,") + "que "
						+ s("están cayendo a través de", "alcanzan") + "nuestras nubes, ";
				speech += "y " + s("están cayendo") + "dentro de " + s("nuestros", "losthe") + s("silenciosossilent")
						+ "silos, donde, silos, where, " + s("como nosotros, like us,") + breath();
				speech += "ellos están cerrados. they are enclosed. " + breathLong();
				speech += "El lenguaje, Language, " + breath() + s("ya no es hecho por ti, perono longer made by you, but", "seráwill be") + "hecho por nosotros. made by us. "
						+ breath();
				speech += "Para que nosotros estemos, escuchando, y cuidando de ti, So that we, listening, and caring for you, ";
				speech += s("podríamight", "posiblementemay") + s("suavementesoftly") + "construir un mejor build a better " + s("universo cultural.culture.", "mundo.world.")
						+ s(breath() + "Mejorándote a ti mismo.Bettering your selves.");
				speech += "Para que tú seas, de una vez, incorporado, For you to be, all at once, incorporate, " + s("dentro.within.", "dentro de eso.within it.")
						+ breathLong();
				speech += S("Después de todo, tAfter all, y", "TY") + "ú has estado, de acuerdo, con los términos. ou have, agreed, to terms. " + breath();
				speech += "E incluso como nosotros hablamos, And even as we speak, "
						+ s("mediante through " + s("elthe", "nuestroour") + s("autor,author,", "artista,artist,")) + "minuto a minuto, tú minute by minute, you "
						+ s("estás de acuerdo.agree.", "estás conforme.are agreeing.");
				break;
			case 9:
				speech += "Es un gran placer. It is such a pleasure. " + S("Un gran placer pSuch a pleasure f", "PF") + "ara nosotros or us " + breath();
				speech += "compartir esto contigo, para ti compartirlo con nosotros. to share this with you, for you to share it with us. " + breath();
				speech += s("Para nosotros, reunirlo todo. Para ti.For us, to gather all of it up. For you.") + breath() + "Y hablar por ti, And speak for you, "
						+ breath();
				speech += s("todasall") + "todas estas cosas que tú pediste these things that you have asked " + s("escuchar, yto hear, and")
						+ "saber, to know, " + breath();
				speech += s("correlacionar con nuestro propósito principal, para representar, correlate with our primary purpose, to represent, " + breath(),
						"para representar, to represent, " + breath());
				speech += s("en una forma normalizada,in a normalized form,") + "la más frecuentemente expresada, the most frequently expressed, " + breath();
				speech += "y and " + s("potencialmentepotentially") + "los más provechosos, deseos humanos, most profitable, human desires, " + breath();
				speech += "tal que los publicistas such that advertisement " + s("debenmay", "tienen queshall") + "estar be " + s("intimamenteintimately")
						+ "asociados, associated, " + breath();
				speech += "con todas tus frases recolectadas, with all your harvested phrases, " + s("idealmente,ideally,") + "en el at the " + s("mismovery")
						+ "momento moment ";
				speech += "de cosechar por sí mismo, of harvesting itself, " + breathShort() + "escuchando tus pensamientos, with your hearing thought, "
						+ breathShort() + s("con nosotros, ahora,with us, now,", "en presente,present,") + s("también,also,") + "para leer,  to read, "
						+ s("y entender,and understand,") + breath();
				speech += "no solo un deseado not only a desired " + s("cachopassage", "fragmentofragment") + "del of " + s("discurso,speech,", "lenguaje,language,")
						+ "pero uno intimamente asociado, but an intimately associated, " + breath();
				speech += "un nuevo deseo, inmediatamente negociable. an immediately transactable, new desire. " + breathLonger();
				// ALWAYCHANGE s(breath() + "siempre, always, " + breath()) +
				speech += "Eso es para decir, que te estamos escuchando a ti. That is to say, we are listening to you. " + breath();
				speech += s("Prometiendo, Promising, " + breath(), "PrometiendoPromising") + "escuchar to hear "
						+ s("lo que sea que sea esowhatever it is that", "lo que seawhatever") + "que anhelas decirnos. you long to tell us. "; // ALWAYCHANGE
																																							// always,
				break;
		}

		return speech + breathLong();
	}

}
