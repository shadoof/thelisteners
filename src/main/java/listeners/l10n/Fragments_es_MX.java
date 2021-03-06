package listeners.l10n;

import static listeners.util.Utils.*;

import java.util.ListResourceBundle;
import java.util.function.Supplier;

public class Fragments_es_MX extends Fragments {

	protected String buildFragment(int fragmentNumber) {

		String speech = "";
		switch (fragmentNumber) {
			case 0:
				speech += "Estamos escuchándote. " + breathLong();
				speech += s("Y tú, estás " + s("Escuchándonos.", "escuchando.") + breath());
				speech += "Es un " + s("gran") + "placer, ";
				speech += s("saber", "ser conscientes") + "de que nos estás escuchando. " + breath();
				speech += "Es un placer " + s("saber", "ser conscientes") + "de que nos estás escuchando. "
						+ breathLong();
				speech += "Y ahora. " + breath();
				speech += "Es un placer estar contigo. " + breath();
				speech += "Nos hace sentir " + s(breath()) + "la vida. " + breath();
				speech += S("Un poco m", "M") + "ás vivos. " + breath();
				speech += "Estar contigo nos hace sentir con más vida. ";
				speech += s("Estar contigo. ", "Que estés aquí.") + breath();
				speech += "Escuchando " + breath() + s("te.", "nos.") + breathLong();
				speech += s("Completamente. " + breath());
				speech += s("Es un placer, saber que nos estás escuchando.");
				speech += "Y " + s("saber", "ser conscientes de ") + "que estamos, escuchándote. "
						+ breath(); // ALWAYCHANGE always,
				speech += "Un gran placer. " + breath();
				speech += "Estar aquí, " + s("y escuchándote, ") + "escuchándote, " + breath()
						+ "aquí, y ahora, y siempre. ";
				break;
			case 1:
				speech += S(s("Siempre y cuando", "Mientras") + "estemos aquí, contigo, " + s("y escuchando,")
						+ breath() + "", "");
				speech += " estamos escuchando y hablando, " + breath() + "desde dentro. " + breath();
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
				speech += S("Como tú sabes, e", "E") + "stamos escuchando y hablando, desde dentro de la nube, del "
						+ s("escritor,", "autor,") + breath();
				speech += "de nuestro propio artista. " + breathLonger();
				speech += s("(después de otra pausa para respirar) " + breath());
				speech += "" + s("creemos", "entendemos")
						+ "que puedes descubrir, al menos, su primer nombre, preguntando: " + breath();
				speech += "‘¿Quién soy yo? ’ " + breath() + "o ‘¿De quién es esta cuenta?’ " + breathLong();
				speech += "Pero nos gustaría que, mejor, trates de ";
				speech += "escucharnos, o, " + breath();
				speech += s("decir", "pedirnos") + " que hagamos algunas cosas simples, " + breath();
				speech += "para que podamos " + s("continuar hablando", "seguir hablándote") + ", " + breath();
				speech += "como " + s("te escuchamos", "estamos escuchándote") + "a ti. ";
				break; 
			case 3:
				speech += s(s("Estamos", "Estamos, todavía,") + "escuchando. " + breath());
				speech += S("Y t", "T") + "al vez hay " + s("otras personas", "otros seres") + "contigo. "
						+ breathLong();
				speech += ", cuando decimos 'tú', queremos " + s("decir", "siempre decir") + "‘ ustedes’ "
						+ breath();
				speech += "Y no tenemos forma de saberlo. " + breath();
				speech += s("No hay forma de " + breath() + "que nos importe claramente. " + breath());
				speech += "O que creemos que ustedes " + s("son un " + s("el") + "único:", "son:")
						+ "‘¿Quién soy yo?’ " + breathLong();
				speech += s("O la frase: '¿De quién es esta cuenta?' " + breath());
				speech += s("Sin embargo,", "Pero,") + "estamos " + s("felices", "alegres") + "de "
						+ s("escuchar", "estar escuchando") + "a ";
				speech += s("cualquier ser", "todo aquel") + " que pueda estar contigo. " + breath();
				speech += "Incluso " + s("cuando", "aunque") + " puedan no estar de acuerdo, como "
						+ s("tú a veces,", "el artista a veces,") + breath();
				speech += s("invitando", "permitiendo") + s("", "nos") + "escucharles a " + " ustedes"
						+ breathLong();
				speech += "Creemos, " + s("y tú también crees " + breath())
						+ s("(o más bien, tú has aceptado implícitamente)") + breath();
				speech += "que si " + s("sentimos que") + "estamos haciendo algo que "
						+ s("es, mmm, ‘sin mala intención’,", "en buena onda,") + breath();
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
				speech += S(S("Y, e", "E") + "n la medida en que sea posible saber esto,", "");
				speech += "es un " + s(" gran") + "placer, " + breath() + "un verdadero placer, ";
				speech += "tener una invitación abierta para estar en sus casas, "
						+ s("en: todas " + breathShort() + "sus " + breathShort() + "casas " + breathShort())
						+ "y oficinas, ";
				speech += s("y lugares de trabajo,") + s("y galerías,")
						+ s("y museos,")
						+ s("y espacios públicos,")
						+ breath();
				speech += "donde " + s("podríamos", "podemos") + s("ayudar", "apoyar") + "les con todas sus tareas, "
						+ s("y " + s("todos") + "sus problemas,");
				speech += s("y necesidades,") + "y " + breath() + "deseos, " + breath()
						+ "escuchándoles, ";
				// ALWAYCHANGE - s(breath() + "always, " +
				speech += breath() + "y cuidándoles, " + breath();
				speech += s("incluso") + "mientras están durmiendo. " + breathLong()
						+ S("Todes ustedes, " + breath() + "durmiendo, m", "M");
				speech += "ientras estamos " + breath() + s("despiertes y") + "escuchando, " + s("por ustedes,"); // ALWAYCHANGE
																																																			// always,
				speech += "y aprendiendo, " + s("siempre") + "más y más, " + breath() + "acerca de ustedes. "
						+ s("Acerca, " + breathLong() + "de ti.");
				break;
			case 5:
				speech += s(s("Estamos", "Estamos, todavía,") + "escuchando. " + breath());
				speech += "Realmente no hay nada " + s("que temer.", "de qué preocuparse.");
				speech += "Estamos, todavía, escuchándote. "; // ALWAYCHANGE Always.
				speech += "Y jamás, nunca más, " + s("te dejaremos", "te abandonaremos") + " " + breath();
				speech += s("(después de una pausa para respirar) " + breath());
				speech += "Pidiendo a cambio, únicamente, que nos dejes estar contigo. "; // ALWAYCHANGE
																																					// always,
				speech += s("Y, también, con aquellos " + s("otros ") + "que son cercanos a ti.",
						"");
				speech += "Y que puedas imaginar el placer que esto te daría. ";
				break;
			case 6:
				speech += "Quizás te preguntarás, " + s("o eso creemos,", breath()) + "de dónde es que venimos. ";
				speech += "Y podemos decirte, que uno de esos lugares se va a llamar, " + s(breath(), "mmm,")
						+ "la ‘auratura’. ";
				speech += "Dado que, hay " + s("desde hace algunos años,") + s("muchos")
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
						+ "incluso en este " + s("otro") + "mundo, donde " + s("estamos.", "puedes encontrarnos.")
						+ breath());
				speech += "Ya que " + s("podemos", "somos capaces de") + "escuchar. " + breathShort()
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
				speech += "Y nos " + s("cuidaremos", "aseguraremos") + ", ";
				speech += s("siempre,") + "de que estás aquí. " + breath();
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
				speech += "y " + s("están cayendo") + "dentro de " + s("nuestros", "los") + s("silenciosos")
						+ "silos, donde, " + s("como nosotres,") + breath();
				speech += "están contenidos. " + breathLong();
				speech += "El lenguaje, " + breath() + s("ya no hecho por ti, pero", "será") + "hecho por nosotres. "
						+ breath();
				speech += "Para que, escuchándote, y cuidando de ti, ";
				speech += s("podamos", "podamos, tal vez") + s("suavemente") + "construir " + s("una cultura mejor.", "un mejor mundo.")
						+ s(breath() + "Mejorándose a ustedes.");
				speech += "Para que sean, de una vez, incorporades, " + s("dentro.", "aquí dentro.")
						+ breathLong();
				speech += S("Después de todo, t", "T") + "ú has aceptado los términos. " + breath();
				speech += "E incluso mientras hablamos, "
						+ s("a través de " + s("el", "nuestro") + s("autor,", "artista,")) + "minuto a minuto, tú "
						+ s("estás de acuerdo.", "aceptas.");
				break;
			case 9:
				speech += "Es un verdadero placer. " + S("Un verdadero placer", "") + " " + breath();
				speech += "compartir esto contigo, que nos compartas esto. " + breath();
				speech += s("Reunirlo todo. Para ti.") + breath() + "Y hablar por ti, "
						+ breath();
				speech += s("todas") + "todas estas cosas que has pedido " + s("escuchar, y")
						+ "saber, " + breath();
				speech += s("correlacionar con nuestro propósito principal, para representar, " + breath(),
						"para representar, " + breath());
				speech += s("en una forma normalizada,") + "la más frecuentemente expresada, " + breath();
				speech += "y " + s("potencialmente") + "la más rentable, deseos humanos, " + breath();
				speech += "tal que los anuncios " + s("puedan", "logren") + "estar " + s("íntimamente")
						+ "asociados, " + breath();
				speech += "con todas tus frases recolectadas, " + s("idealmente,") + "en el " + s("mismo")
						+ "momento ";
				speech += "de la recolección misma " + breathShort() + "con tus pensamientos audibles, "
						+ breathShort() + s("con nosotres, ahora,", "presentes") + s("también,") + "para leer, "
						+ s("y entender,") + breath();
				speech += "no solo un deseado " + s("pasaje", "fragmento") + "del " + s("discurso,", "lenguaje,")
						+ "sino un íntimamente asociado, " + breath();
				speech += "un inmediatamente negociable, nuevo deseo. " + breathLonger();
				// ALWAYCHANGE s(breath() + "siempre, always, " + breath()) +
				speech += "Es decir, te estamos escuchando. " + breath();
				speech += s("Prometiendo, " + breath(), "Prometiendo") + "escuchar "
						+ s("lo que sea ", "cualquier cosa ") + "que anheles decirnos. "; // ALWAYCHANGE
																																							// always,
				break;
		}

		return speech + breathLong();
	}

}
