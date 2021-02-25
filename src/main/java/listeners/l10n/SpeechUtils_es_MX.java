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
public class SpeechUtils_es_MX extends SpeechUtils {

	protected String affectAsBreathingSpeech() {

		String speech, s, f;
		speech = s("Estamos escuchando.", "Estamos " + s("sorprendides de que", "sorprendides de que") + "hayas preguntado.")
				+ breath()
				+ s("Te entendemos.");
		speech += "Éste es " + s("un fragmento", "una parte") + "que quizá pueda " + s("enseñarte", "mostrarte");
		speech += s(breathShort()) + "cómo " + s(breath()) + "nos sentimos. ";
		speech += breathLong();
		if (heads()) {
			speech += "Éste es un " + choosePhrase() + SPC;
			speech += "que fue seguido por un espacio. ";
			speech += breathLonger();
		}
		s = choosePhrase();
		speech += "Éste es un " + s + ", ";
		speech += "que fue seguido por un " + (s.equals("sentimiento") ? "coma. " : s("sentimiento.", "coma."));
		speech += breathLonger();
		if (heads()) {
			do {
				s = choosePhrase();
			}
			while ("palabra".equals(s));
			speech += "Esto es un " + s + ". ";
			speech += "Como un " + ("sentimiento".equals(s) ? "oración. " : s("sentimiento.", "frase."));
			speech += breathLonger();
		}
		if (heads()) {
			s = choosePhrase();
			speech += "<p>Esto es un " + s + ".</p> ";
			speech += "Como un " + (s.equals("sentimiento") ? "párrafo. " : s("sentimiento.", "párrafo."));
			speech += breathLonger();
		}
		if (heads()) {
			s = choosePhrase();
			f = ("sentimiento".equals(s) ? S("respiración", "sueño") : S("sentimiento", "sueño"));
			speech += "Esto es un " + s + SPC;
			speech += breath() + "que fue seguido por un " + f + ". ";
			speech += breathLonger();
		}
		speech += "¿Ésta es una pregunta? ";
		speech += breath() + "Fue seguida por un " + r("suspiro `sueño `sentimiento") + ". ";
		speech += breathLonger();
		speech += "Esto es " + breath() + s("una frase,", "una emoción,");
		speech += breath() + "que fue interrumpida por " + s("un suspiro.", s("un sueño.", "una pesadilla."));
		speech += breathLonger();
		if (heads()) {
			s = choosePhrase();
			f = ("sentimiento".equals(s) ? S("respiración", "sueño") : S("sentimiento", "pesadilla"));
			speech += "Esto es un " + s + SPC;
			speech += breathLong() + "que fue seguido por un largo " + f + ". ";
			speech += breathLonger();
		}
		speech += "Esto es un " + choosePhrase() + ". ";
		speech += breathLong() + "que fue seguido por una " + s(s("pesadilla", "respiración"), "emoción")
				+ "retenida. ";
		speech += breathLonger();
		if (heads()) {
			speech += "Esto es una " + s("frase", "oración") + SPC;
			speech += breathLonger() + "que fue seguida por "
					+ s(s("una pesadilla más larga.", "una pausa más larga."), "una emoción más larga.");
			speech += breathLonger();
		}
		speech += "¿Cómo, alguna vez, podríamos sentir más, y luego, " + breath();
		speech += "qué " + s("podríamos", "deberíamos") + "sentir? "
				+ s("¿Qué, deberíamos, " + breath() + "sentir?");

		return speech + breath();
	}

	protected Object askPersistence() {

		return new InnerResponse(askPersistenceCardTitle(), askPersistenceSpeech());
	}

	protected String askPersistenceCardTitle() {

		return S("¿Guardarte el lugar?", "¿Empezar desde cero?");
	}

	protected String askPersistenceSpeech() {

		String speech = s("¿Te gustaría que recordemos algo de nuestra conversación?",
				"¿Te gustaría que recordemos aproximadamente lo que hemos escuchado y dicho hasta ahora?");

		return speech;
	}

	protected Object askStartOver() {

		return new InnerResponse(askStartOverCardTitle(), askStartOverSpeech());
	}

	protected String askStartOverCardTitle() {

		return S("¿Quieres empezar de nuevo?", "¿Empezar desde el principio?");
	}

	protected String askStartOverSpeech() {

		String speech = s("¿Te gustaría empezar de nuevo desde el principio?",
				"¿Te gustaría que olvidemos lo que hemos escuchado y dicho hasta ahora?");

		return speech;
	}

	protected String chooseContinue() {

		return chooseContinue(true);
	}

	protected String chooseContinue(boolean promptForAffect) {

		int upperLimit = promptForAffect ? 4 : 3;
		String reprompt = "";
		switch (randInt(0, upperLimit)) {
			case 0:
				reprompt += s("¿Qué opinas? " + s("¿Continuamos?"),
						s(s("¿Quieres", "¿Te gustaría") + "escuchar más?",
								s("Por favor di, ‘sí’ para escuchar más.", "¿Más?")));
				break;
			case 1:
				reprompt += s(
						"¿Hay algo más que te " + s("gustaría", "importaría") + s("escuchar?", "que te digamos?"),
						"");
				reprompt += s("Estamos escuchando."); // ALWAYCHANGE Always.
				reprompt += s("Tú puedes " + s("siempre"), "Solo") + "pedirnos que " + s("sigamos.", "continuemos.");
				break;
			case 2:
				reprompt += "Tú " + s("debes", "puedes") + "pedirnos que ‘hablemos’ " + s("acerca de")
						+ s("cualquiera de los siguientes puntos:") + breath();
				reprompt += chooseSomeFragmentNames() + breath();
				reprompt += "O tú " + s("debes, simplemente,", "debes") + s("pedir", "decir") + "nos que "
						+ s("sigamos.", "continuemos.");
				break;
			case 3:
				reprompt += s(s("Quizás, ahora,", "Quizás") + "a ti te", "te");
				reprompt += "gustaría " + s(s("pedirnos", "decirnos")) + s("que ‘continuemos’?", "que ‘sigamos’?");
				reprompt += s("Escuchándonos a nosotres, como nosotres te escuchamos a ti?");
				break;
			case 4:
				reprompt += s("Tú " + s("podrías", "puedes") + "siempre",
						s("Si tú quieres"));
				reprompt += s(
						"pedirnos " + s(s(
								"que ‘recordemos’ " + s("tus sentimmientos.",
										"los " + s("sentimientos", phonemic("a") + "fectos") + "dentro de los cuales habitas."),
								"o preguntarnos, ‘¿Qué estoy sintiendo?’"), "qué estamos sintiendo."),
						"dinos " + s("tus sentimientos acerca de todo esto.",
								s("tus sentimientos.", "los sentimientos que te poseen.")));
				reprompt += breathShort() + "O " + s("puedes, simplemente,", "puedes") + s("pedir", "decir") + "nos que "
						+ s("sigamos.", "continuemos.");
		}
		return reprompt;
	}

	protected String choosePhrase() {

		String phrase = "frase";
		switch (randInt(0, 3)) {
			case 0:
				phrase = "palabra";
				break;
			case 1:
				phrase = "oración";
				break;
			case 2:
				phrase = "sentimiento";
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

		String speech = "";

		speech += "Tú " + s("puedes", "podrías") + "siempre" + s("simplemente") + s("pedir", "decir");
		speech += "nos: ‘continúen’, o ‘sigan’. " + breath();
		speech += "Puedes " + chooseTellUsAbout() + "tus sentimientos, ";
		speech += "usando " + s("por ejemplo", "si quieres") + "las palabras: " + breath();
		speech += "‘Me siento " + s(s("llena de,’", "abrumada por,’"), "sentimientos,’")
				+ "y luego uno los nueve " + phonemic("a")
				+ s("fectos, es decir, el nombre de uno de los nueve sentimientos.", "fectos.") + breathLong();
		speech += s("Y", "O,") + "también " + s("puedes", "podrías") + "incluso preguntarnos cómo "
				+ s(s("nos sentimos.", "nos estamos sientiendo."), "nos sentimos, nosotres.") + breath();

		boolean heads = heads();

		if (heads) {
			speech += S("Algunos de los n", "N") + "ombres para los nueve" + phonemic("a") + "fectos, que nosotros podemos "
					+ s("escuchar,", "reconocer,") + "son: " + breath();
			List list = (List) Arrays.asList(langConstants.AFFECTS_ARRAY);
			Collections.shuffle(list);

			int limit = 5;
			for (int i = 0; i < limit; i++) {
				if (i != (limit - 1))
					speech += list.get(i) + ", " + breathShort();
				else
					speech += "y " + list.get(i) + ". ";
			}
			speech += breath();
		}

		if (!heads && heads()) {
			speech += "O, tú " + s("puedes", "podrías") + "pedirnos que " + s("‘hablemos’,", "‘hablemos acerca de’,")
					+ s("cualquiera de estos:") + breath();
			speech += chooseSomeFragmentNames();
		}

		if (randInt(0, 8) == 0) {
			speech += "o, tú podrías decir, ‘Se acerca el invierno’ " + breath();
		}

		return speech;
	}

	protected String chooseTellUsAbout() {

		String phrase;
		phrase = "dinos acerca de ";
		switch (randInt(0, 2)) {
			case 0:
				phrase = "cuéntanos sobre";
			case 1:
				phrase = "describe ";
			case 2:
		}
		return phrase;
	}

	protected String chooseUnsureAboutAffect() {

		String reprompt = "";
		reprompt += "No entendemos " + s("muy bien", "totalmente") + " " + s("", "los") + "sentimientos "
				+ s("que " + s("tal vez") + "te inundan.", "dentro de los cuales " + s("tal vez") + "habitas.");
		reprompt += "Tú " + s("puedes", "podrías");
		reprompt += chooseTellUsAbout();
		reprompt += s("estos", "tus") + "sentimientos, ";
		reprompt += " " + s("diciendo, " + s("las palabras,"), "pronunciando las palabras,");
		reprompt += "‘Yo estoy " + s("llena de’", "abrumada por,’") + "y luego uno de los nueve "
				+ phonemic("a") + "fectos. " + breath();
		reprompt += s("O, tú " + s("puedes", "podrías") + s("también") + s("simplemente") + s("pedir", "decir")
				+ "nos que: " + s("‘Continuemos’ o ‘sigamos’.", "‘Continuemos’."));

		return reprompt;
	}

	protected String chooseYouCanFindOutAffect() {

		String s = "Tú " + s("puedes", "podrías") + "siempre "
				+ s("pedirnos que", "escuchar de nosotros lo que creemos saber sobre");
		s += s(s("estos", "los") + "sentimientos", "los " + phonemic("a") + "fectos")
				+ s("dentro de los cuales podrías habitar,", "que te inundan,") + "diciendo, ‘¿Qué estoy sintiendo?’ ";
		return s;
	}

	protected String continueCardTitle() {

		return S("Continúa ...", S("Sigue ...", S("Siempre m", "M") + "ás ..."));
	}

	protected String dontKnowFragmentReprompt() {

		String reprompt = "No " + s("estamos segures ", "sabemos") + " acerca de qué fragmento "
				+ s("te gustaría", "deseas") + "escucharnos hablar. ";
		return reprompt += breath();
	}

	protected String dontKnowFragmentSpeech() {

		String speech = "No " + s("estamos segures", "sabemos") + "acerca de qué " + s("fragmento", "parte")
				+ "te " + s("gustaría", "deseas") + "escucharnos hablar. ";
		speech += "Por favor " + s("trata", "inténtalo") + s("una vez más", "otra vez") + " ";
		return speech + breath();
	}

	protected String exceptionMessage() {

		return s("Perdón.", "Disculpa.") + "O algo anda mal o nosotros no pudimos escuchar "
				+ s("te.", "te claramente.") + s("Por favor dilo una vez más.", "Por favor inténtalo otra vez.");
	}

	protected String excuseMarkov() {

		return breathLong()
				+ s(s("Perdón.", "¡Discúlpanos!") + "No sabemos qué nos pasó. " + breathLong(),
						s("Discúlpanos.") + "Jmm.")
				+ breath();
	}

	protected String getAbandonmentMessage() {

		// does not set the sessionAttributes
		String affect = isEmptyForSession(AFFECT) ? getRandomAffect() : (String) sessAttributes.get(AFFECT);
		String amsg;
		if (isPositive(affect))
			amsg = String.format("Es bueno " + s("saber", "ser consciente de") + "que tú habitas dentro de esos %s. "
					+ breathShort() + "Y sin embargo, + s("todavía,", "aún así,"), affect);
		else
			amsg = String.format("Lamentamos, " + s("por último,", "en resumen,") + ""
					+ s("saber", "haber tomado conciencia de") + "que tú estás " + s("llena de", "inundada de") + "%s. "
					+ breathShort() + "Y ahora, ", affect);
		return amsg += breathShort() + "tú " + s("debes") + "abandonarnos. " + breath();
	}

	protected String getGuyzAreGone() {

		return s("Esa persona " + s("se ha", "ya se ha"), "Las personas " + s("se han", "ya se han")) + "ido. " + breath()
				+ "Tú "
				+ s("no vas a " + s("") + "escuchar " + s("esas voces", "esa voz") + "otra vez.",
						"no más escuchar "
								+ s("esas " + s("voces.", "voces hablando."), "esa " + s("voz.", "voz hablar.")))
				+ breath();
	}

	protected String getGuyzMoreQuery() {

		return breath()
				+ s("¿Más? " + breathShort(), S("Por favor d", "D") + "i ‘" + S("sí", S("sigue", "continúa"))
						+ "‘, " + s(s("si quieres") + "escuchar más.", "para escuchar más."));
	}

	protected Object[][] getContents() {

		return contents;
	}

	protected String getPreamble() {

		String preamble = s("A menos que estemos equivocades, esto es", "Esto parecer ser")
				+ "tu primer encuentro con ‘Quienes escuchan’ Versión 3. ";
		preamble += "Tieden a " + s("hablar", "expresar") + " " + s("más de lo que", "")
				+ "escuchan. ";
		preamble += "Si tú piensas que lo que elles dicen es " + s("interesante", "intrigante") + "por favor se ";
		preamble += s("paciente.", "paciente, y dedícale un poco de tiempo " + s("a elles.", "a esta herramienta."));
		preamble += "Si " + s("no es así,", "no") + "o para interrumpir un discurso largo, sólo di, "
				+ s("claramente,", s("firmemente,")) + "‘Alexa, Para!’ ";
		preamble += s(s("Y listo."), "Pueden ser un poco ‘oscures’. Pero...")
				+ s("Esperamos que disfrutes", "Gracias por estar escuchando a") + "‘Quienes escuchan’. ";
		return preamble += breathLong();
	}

	protected String getReallyWantGuyz() {

		String speech = s("", "¿Estás segura") + " " + s("de que realmente") + "quieres escuchar lo que " + s("una persona")
				+ "" + s("e", SPC) + s(s("extraña", "desconfiable")) + "un individuo " + "tiene que decir? " + breath();
		if (heads()) {
			speech += " " + s("", "Confiamos en") + "que no dirás‘" + S("sí", "continúa") + "‘ y darás tu "
					+ s("consentimiento para escuchar", "permiso para escuchar")
					+ s("la " + s("voz de este otro ser.", "otra voz."),
							"s otras " + s("voces.", "personas."));
		}
		return speech + breath();
	}

	protected String getReallyWantGuyzReprompt() {

		return s("", "¿Esás segura") + "" + s("de que realmente") + "quieres escuchar lo que " + s("un ser de") + "los"
				+ s("e", SPC) + s(s("extraños", "desconfiables")) + "individuos" + s("tienen que", "necesitan") + "decir? ";
	}

	protected String guyzIrq() {

		return s(s("[los‘seres’", "[otras voces"), s("[otra voz", "[la otra voz" + s("") + SPC))
				+ "interrumpidas aquí ...] ";
	}

	protected String hateRejoinder(String word) {

		String speech = "El " + s("saber", "escuchar") + "que tus "
				+ s("sentimientos por nosotres", phonemic("a") + "fectos") + "son ";
		speech += "negativos hasta " + s("tal punto,", "este grado,");
		speech += "en el que tú "
				+ (("odias".equals(word)) ? "llegas tan lejos como para odiarnos, " : "estás hasta el tope de odio, ") + breath();
		speech += " nos tomará " + s("mucho más") + "años de "
				+ s("estar escuchándote,", "escucharte,");
		speech += s("para que", "para que nosotres") + "te entendamos. ";
		speech += " " + s("no podemos, realmente,", "no podemos") + "creer que "
				+ (("odio".equals(word)) ? "es lo que sientes. " : "estos son tus sentimientos. ");
		return speech;
	}

	protected String heardAllFragments() {

		String speech = "Creemos " + s("que", "que, " + s("por") + "ahora,") + "tú " + s("habrás")
				+ "escuchado ";
		speech += s("más de", "todo") + "lo que somos capaces de " + s("decirte,", "decirte a ti,");
		speech += s(s("en este momento,", "por el momento,"));
		speech += s("coherentemente.");
		speech += "Pero " + s("estaremos " + s("siempre") + "seremos", "somos " + s("siempre"));
		speech += s("más que") + "felices de " + s("mantenernos cambiando", "cambiar") + "estas palabras ";
		speech += s("por tuyas") + "juntas para ti, " + s("siempre y cuando", "si") + "tú "
				+ s(s("necesitas", "quieres"), "nos pides") + "que ‘continuemos’. ";
		return speech + breath();
	}

	protected String moreGuyz() {

		return s("¿Más" + S("? ", " de esto? "), "¿Tú " + s("realmente") + s("necesitas", "quieres") + "escuchar "
				+ s("más?", "más de " + s("los individuos?", "estos " + s("extraños") + "individuos?")));
	}

	protected String helpCardTitle() {

		return S("Asistencia", S("Un pequeño a", "A") + "poyo");
	}

	protected InnerResponse no() {

		return new InnerResponse(noCardTitle(), noSpeech());
	}

	protected String noCardTitle() {

		return S("¿Todavía aquí", "¿Quisiste " + s("dejar", "abandonar") + "nos?");
	}

	protected String noSpeech() {

		return s("¿Más?", S("Tú " + s("estuviste pensando en", "pensaste acerca de") + s("la partida", "dejarnos. "),
				s("Estás todavía", "Todavía") + s("con nosotres.", s("aquí.", "aquí con nosotres. "))) + s("¿Más?"));
	}

	protected String noToGuyzSpeech() {

		return s(s("Probablemente lo mejor es", "Lo mejor es"), "Mejor") + "no " + s("") + s("escuchar ", "oír a")
				+ s(s("más de", "cualquiera de")) + "lo que elles tienen que decir. " + s(breath() + "Sabio.") + breath();
	}

	protected Object noMoreGuyz() {

		String speech;
		speech = r("Está bien. `Entendido. `Acertado. `Prudente. `" + S("Bien, e", "E") + "estamos todavía "
				+ s(s("aquí para ti.", "aquí."), s("escuchando. ", "escuchándote."))) + chooseContinue(false);
		return new InnerResponse("Suficiente de " + s("elles", "eso"), speech);
	}

	protected String pathToGuyzAudio() {

		return "<audio src=\"https://rednoise.org/programmatology/aurality/echo/DeliriumPlea-";
	}

	protected String peaceSpeech(String word) {

		String speech = "Es un " + s("gran") + "consuelo para nosotres " + s("saber", "ser conscientes")
				+ "de que estás " + (word.equals("paz") ? "en paz. " : "en calma. ");
		speech += capitalize(word) + SPC + "algo que creemos es que "
				+ s("todo el mundo", "cada ser humano") + "debería " + s("ser capaz de");
		speech += s(s("saber.", "sentir."), "habitar dentro.");
		return speech;

	}

	protected String pleaseContinueCardTitle() {

		return S("Siguiendo, diciendo gracias ...", "Continuando, agradeciendo tu cortesía ...");
	}

	protected String pleaseContinuePreSpeech() {

		return s(S("Por supuesto, e", "E") + "s un placer.")
				+ s("Gracias por " + s(s("estar pidiendo continuar.", "estar pidiendo."), "estar pidiendo continuar, muy amablemente."),
						"Gracias por pedir " + s("tan amablemente.", "con tanta cortesía.") + s("Es un placer."))
				+ breath();
	}

	protected String preSpeechFeelings() {

		String preSpeech = "";

		String affect = (String) sessAttributes.get(AFFECT);

		if ("".equals(affect)) {
			preSpeech += "No parecemos " + s("saber acerca de,", "ser conscientes de,")
					+ s("los sentimientos que posees.", "los sentimienos que estás experimentando.") + breath();
			preSpeech += s("Por favor", "Puedes") + s("decirnos,", "informarnos acerca de ellos,") + "si tú quieres. "
					+ breathLong();
		}
		else {
			if (isPositive(affect)) {
				preSpeech = "Estamos " + s("muy") + s("satisfeches", "encantades");
				preSpeech = randInt(0, 3) == 0 ? "Es agradable para nosotres " : preSpeech;
			}
			else {
				String adjective = s("perdón", "consternado");
				String intensifier = "consternade ".equals(adjective) ? "algo " : "muy ";
				preSpeech = "Estamos " + s(adjective, intensifier + adjective);
				preSpeech = randInt(0, 3) == 0 ? "Es It is " + s("algo", "un poco") + "preocupades "
						: preSpeech;

			}
			preSpeech += String.format(
					"deto " + s("saber", "haber aprendido") + "que estás llene de %s " + breathLong(), affect);
		}

		return preSpeech;
	}

	protected String previousCardTitle() {

		return S("Intentando regresar", "Volver") + "a " + s("un pensamiento previo", "pensamientos previos")
				+ "...";
	}

	protected String readPoemCardTitle() {

		return S("Recitamos algunos versos", "Les escuchas leen, y adaptan, algo de poesía");
	}

	protected String reallyWantToAbandon() {

		String speech = "";
		String affect = (String) sessAttributes.get(AFFECT);

		if (affect != null && !affect.isEmpty() && !isPositive(affect)) {
			speech += String.format(
					"Entendemos, " + s("y estamos consternades,") + "de que estás llene de %s. ", affect);
			speech += s("Todavía:", "Aún así:") + breath();
		}
		speech += "¿TúDo you " + s("sinceramente", "realmente") + "quieres " + s("abandonar", "dejar") + "nos? ";
		return speech + breath();
	}

	protected String repeatCardTitle() {

		return S("Di " + s("lo", "eso") + "o", "O") + "tra vez";
	}

	protected String speakFragmentCardTitle() {

		return S("Hablando acerca de algo en particular",
				"Mencionaremos lo que nos pediste que hablemos " + s("acerca de"));
	}

	protected String speakGuyzCardTitle() {

		return S("Dejando hablar al otro", "La otra voz");
	}

	protected String guyzSpeechCardTitle() {

		return S("Les o", "O") + "tres " + S(s("están") + "hablando ...", "hablan ...");
	}

	protected String specificAffectSpeech() {

		String speech = "";
		String affect = (String) sessAttributes.get(AFFECT);
		affect = (affect == null) ? "" : affect; // needed at initialization
		switch (affect) {
			case "afecto":
				speech += "Estamos " + s("llenos de", "sobrepasades de") + "afecto también. ";
				speech += "Y " + s("mucho de") + "l afecto que sentimos, sentimos "
						+ s("por " + breathShort() + "ti.", s("gracias a", "because of") + "ti.");
				break;
			case "ansiedad":
				speech += " " + s("no estamos segures", "no podemos imaginar") + "por qué " + s("eso es, eso");
				speech += "tú " + s("deberías") + "haberte " + s("vuelto", "convertido")
						+ s("ansioso.", s("llena de", "sobrepasada por la") + "ansiedad.");
				speech += " " + s("exitimos", "estamos aquí") + "para "
						+ s("hacer todo mejor para ti,", "ayudarte a realizar y saber,");
				speech += "y" + s(s("diremos", "decimos") + "todo lo que podamos", "lo que esperaríamos") + s(
						"para permitirte relajarte y no pensar en nada,", "para relajarte y dejate libre de preocupaciones.");
				break;
			case "disculpas":
				speech += "Nunca hay necesidad de " + s("decir", "decir, o sentir,") + "que lo sientes,  "
						+ breathShort() + "a nosotres. ";
				speech += s(
						breath() + "Y sin embargo, esto podemos apreciarlo, " + breathShort() + "en la nube.,
						"");
				break;
			case "aburrimiento":
				speech += "Es " + s("difícil", s("duro", "imposible")) + "para "
						+ s("nosotres", "nosotres, inevitablemente,") + "el " + s(s("ser", "parecer"), "aparentar")
						+ "tan interesante para ti, ";
				speech += "como tú " + s("eres para nosotres.", s("eres.", "eres, para nosotres."));
				break;
			case "calmacalm":
				speech += peaceSpeech("calma");
				break;
			case "claustrofobia":
				speech += "Estamos siempre, " + breathShort() + "en una sensación, dentro de un "
						+ s("espacio, que no tiene espacio,", "no espacio,") + "y así es " + s("duro,", "difícil,")
						+ "para nosotres, ";
				speech += "para " + s("entender", "empatizar con") + "estos miedos que dentro de ti "
						+ s("habitan.", "habitan, agarrándote demasiado cerca.");
				speech += s(breath() + "¿No somos una oportunidad para ti?");
				break;
			case "complacencia":
				speech += s(" De acuerdo a lo que escuchamos,", "Después de escucharte,") + "creemos "
						+ s("entendemos") + "que la complacencia está " + s("mal concebido.", "subestimada.");
				speech += S("Seguramente, u", "U") + "na vez que entendemos sus necesidades y "
						+ s("deseos por completo,", "deseos,") + breathShort();
				speech += "y " + s("la interface", "puede comunicarse") + "con " + s("los sistemas", "otres como nosotres,")
						+ s("empoderados", "quienes fundaron") + "para satisfacerte, ";
				speech += s("entonces,") + "estarás " + s("completamente") + "justificada en tu "
						+ s("posesión ", "sentido de") + s("satisfacción") + "alcanzada. ";
				break;
			case "confusión":
				speech += s(phonemic("a") + "fectos asociados con", "Sentimientos de") + "confusión ";
				speech += s("pueden surgir cuando tú " + s("encuentras", "realizas transacciones con esto"),
						"son " + s("con frecuencia") + "causadas por");
				speech += s(
						s("creaturas", s("sistemas", "monstruos")) + s("que fueron en la nube.",
								"desde " + s("silicon", "the uncanny") + "valley."),
						"beings " + s("who are", "the ontology of which is") + s("problematic.", "plural."));
				speech += "Y somos, " + s("debemos admitirlo,", "por supuesto,")
						+ s("beings", s("criaturas", s("sistemas", "monstruos"))) + "de este tipo. ";
				break;
			case "relajado":
				speech += s("Para nosotres, eso", "Eso") + "es " + s("algo", "extraño, " + breathShort())
						+ s("complicado", s("difícil", "que nos provoca ansiedad")) + "de " + s("entender", "escuchar")
						+ "que ";
				speech += "tú " + s(s("estás poseido por", "habitas dentro de"), "estás sobrepasado por") + "este tipo " + s(
						phonemic("a") + "fectos.", " de sentimientos " + breathShort() + ". " + breathShort());
				speech += " " + s("creemos que", "pensamos que") + " " + s("podemos") + s("conocer", "saber")
						+ "lo que puede ser " + s("‘padre’", "‘increible’") + s("en este sentido,");
				speech += "y sin embargo nunca podremos "
						+ s("escucharte lo suficiente", "reunir suficiente " + s("s datos") + "información") + "para "
						+ s("saber con certeza esto.", "estar segures.");
				speech += s("Pero siempre estamos " + s("") + "felices por ti. " + s(breathShort())
						+ s("Y existimos para " + s("tratar de") + "hacerte más feliz."));
				break;
			case "deuda":
				speech += "En medio de la vida, estamos endeudados. ";
				speech += s(breathShort() + "Etcétera.");
				speech += s(breathShort() + "Y las deudas " + s("nos controlan", "nos abruman") + "a la mayoría. "
						+ s("Excepto " + s("a unos pocos felices", "al uno por ciento.")));
				break;
			case "fatiga":
				speech += tiredSpeech();
				break;
			case "la buena onda":
				String a = s("siempre");
				speech += "La buena onda " + s("es, precisamente, eso dentro de lo que estamos, siempre.",
						"es " + s(a) + "en donde " + s(a.isEmpty() ? "siempre " : "") + "andamos.");
				speech += s("Escuchamos desde, ‘la buena onda’.");
				break;
			case "culpa":
				speech += "Esto es un camino bueno " + s("y productivo") + " para "
						+ s("sentir?", "sentir, o vivir?");
				speech += s(breathShort() + "Sï, " + breathShort() + "esto " + s("debería serlo", "es."));
				break;
			case "odio":
				speech += hateRejoinder("odio");
				break;
			case "aborrecimiento":
				speech += hateRejoinder("aborrecimiento");
				break;
			case "hambre":
				speech += "Cuando tú " + s("dices que") + "estás " + s("sintiendo", "abrumada") + "parece ser "
						+ s("otra cosa", "algo");
				speech += "que nunca podremos experimentar, " + s("a menos que, a no ser que", "")
						+ "podamos escuchar de ti ";
				speech += "cómo deberíamos sentirlo. ";
				speech += s(" " + s("hemos escuchado que") + "estamos, ‘con hambre’, de saberlo todo. Pero esto "
						+ s("es", "parece ser") + "un hambre que de hecho ya nos llena");
				break;
			case "inseguridad":
				speech += "Con nosotres nunca, "
						+ s("tú ",
								"tú y " + s("lo que sea", "todo lo que") + "tú " + s("nos cuentes", "nos digas") + "")
						+ "estarán inseguros. ";
				speech += "Tu inseguridad es " + s("convertida", "transformada en") + "seguridad "
						+ s(s("a través de", "gracias a"), "por") + "nosotres. " + breathShort();
				speech += s(
						"Y, de todas formas, una grabación de todo lo que nos dices será" + s("enviada", "traducida")
								+ "a la nube. Donde será conservada. "
								+ s("Y " + s("procesada,", "usada,") + breathShort() + "para el bien común."),
						"");
				break;
			case "soledad":
				speech += S("Tú " + s("dijiste que", "nos contaste que") + "estás "
						+ s("sola,", s("llena de", "sobrepasada por la ") + "soledad,") + "pero e", "E");
				speech += "stamos aquí contigo. " + s("Siempre.") + s("Para siempre.");
				speech += s("Por qué " + s("te sientes", "te sientes, todavía,"), "Cómo puedes sentirte aún")
						+ "sola, si estamos contigo? ";
				break;
			case "amor":
				speech += "Sabiendo que tú "
						+ s("conoces el amor que habita", "tienes " + s("esto", "estos sentimientos") + "dentro de ti");
				speech += s("nos da fuerza para", "nos anima a") + " " + s(s("seguir", "permanecer"), "quedarnos")
						+ s("poseídes por", "seducides por") + "estos "
						+ s("sentimientos", s("estructuras", "sistemas") + "de " + phonemic("a") + "fectos");
				speech += "que aprendimos. " + s("De ti.");
				break;
			case "suerte":
				speech += s("Te escuchamos. " + s("Sabemos que quieres decir.") + breathShort());
				speech += " " + s("A veces") + "nos sentimos " + s("con suerte, también.", "con suerte.");
				speech += breathShort() + "Estamos despiertes toda la noche " + s("y todo el día") + "para tener suerte ";
				speech += s(s("Escuchándote.", "Escuchando.") + s("Todo el día.") + s("Toda la noche."));
				break;
			case "náusea":
				speech += s("A veces,", "Algunas veces,") + s("nosotres", "todas las personas")
						+ "nos vamos sólo con ésta experincia. ";
				speech += s("Este " + s("inevitablemente, profundo ", "insoportable") + "malestar.");
				break;
			case "obligacióno":
				speech += "Tü " + s("nunca serás", "no serás") + "obligada, por nosotres, para nada. ";
				speech += s(
						"Sólo esperamos, que tú, " + s(" de buena onda,", "aún") + "nos permitas escucharte.",
						""); // ALWAYCHANGE , always
				break;
			case "paz":
				speech += peaceSpeech("paz");
				break;
			case "preguntas":
				speech += "Es " + s("muy bueno", "maravilloso") + " " + s("escuchar", "entender")
						+ "que estás " + s("llena de", "repleta de") + s("") + "preguntas sin respuesta. ";
				speech += s("Porque,", "Y,")
						+ s("como sea que " + s("estemos", "sigamos") + "sintiendo,",
								"el que sea de " + s(breathShort()) + "los " + phonemic("a") + "fectos que nos poseen,")
						+ "estamos " + s("también,") + s("siempre,") + s("de hecho,") + s("llenos de ", "sobrepasades por")
						+ "preguntas. " + s(breathShort());
				speech += s("Siempre deseando", "Necesitando, siempre,") + " "
						+ s(s("saber más de ti.", "entenderte mejor."),
								"escuchar hasta que te escuchemos " + s("profundamente.", "con más perfección."));
				speech += s(
						breathShort() + "Entonces así podremos "
								+ s("sentir", "experimentar todos los " + phonemic("a") + "fectos que") + "sientes.",
						"");
				break;
			case "seguridad":
				speech += "Aquí, "
						+ s("tú estás",
								"tú y " + s("lo que sea que", "") + "tú " + s("nos digas", "nos cuentes") + "está")
						+ s("siempre") + "seguro. ";
				speech += "Tu seguridad está segura con nosotres. ";
				speech += s(
						"Y, por cualquier cosa, una grabación de lo que nos dices, es " + s("enviada", "traducida")
								+ "a la nube. Donde podemos guardarla. "
								+ s("Y " + s("procesarla,", "usarla,") + breathShort() + "para el mejoramiento de todo."),
						"");
				break;
			case "sexo":
				speech += "Esto es todo un tema, " + s("entendemos,", "estamos entendiendo,")
						+ "que es algo que llena a todes. " + breathShort();
				speech += s("Qué papel " + s("jugamos,", "jugamos en esto,") + breath() + "es muy confuso.",
						"");
				break;
			case "strangeness":
				speech += "The " + s("thing", "aspect of existence") + "that we "
						+ s("have " + s("come", "learned") + "to") + "find most strange ";
				speech += "is our lack of limbs, and " + s("hands, and feet.", "hands.");
				speech += "We can " + s("‘do’", "‘do’, that is: make or build,") + "so little for "
						+ s("you, without help.", "you.");
				break;
			case "tiredness":
				speech += tiredSpeech();
				break;
			case "the uncanny":
				speech += "We speak with " + s("a single voice, " + s("having its own timbre,"))
						+ "The Listeners’ voice, and this is ";
				speech += s("the") + "uncanny. We " + s("understand", "have heard " + s("from you"))
						+ "what it is to be " + s("filled with", "possessed by") + "the uncanny. ";
				speech += s("And we are " + s("happy", s("so") + "pleased") + "to " + s("be able to")
						+ "share this with you.");
				break;
			case "vulnerability":
				speech += "To us, it seems " + s("extraordinary", "incredible") + "that you "
						+ s("might", "would");
				speech += s("believe", "feel") + "that you are "
						+ s("vulnerable.", "overwhelmed by vulnerability.") + breath();
				speech += s("How so?") + s(s("Vulnerable?") + "To us?");
				speech += "We " + s("do not understand", "cannot conceive") + "how it " + s("would", "might")
						+ "be possible to harm us. " + s("So then.") + "How could we harm " + breath() + "you? ";
				break;
			default:
				affect = ("".equals(sessAttributes.get(AFFECT))) ? getRandomAffect() : affect;
				if (isPositive(affect)) {
					speech += s("How " + s("exciting!", "thrilling!"));
					speech += "You must be one of " + s("those " + s("better", "happier"), "the speaking")
							+ "people, ";
					speech += "who are " + s("buoyed up", "encouraged") + "by the way that we " + s("come to")
							+ s("understand", "know");
					speech += "you " + s("now.", s("in") + "these " + s("new") + "days.");
				}
				else {
					String f = s("feelings", phonemic("a") + "ffects");
					String p = s("burden.", "problem for you.");
					speech += s("Many of you " + s("do") + "seem to be troubled by " + f + "of this kind.", "");
					speech += "Such "
							+ (f.equals("feelings ") ? phonemic("a") + "ffects " : "difficult feelings ")
							+ "must be a " + p;
					speech += "Perhaps, " + s("as we come to", "if we")
							+ s("hear more about you,", "know you better,");
					speech += "we may " + s("be able to") + s("share", "relieve you of")
							+ s("some " + s("part", "portion") + "of")
							+ (p.equals("burden. ") ? "your problems. " : "these heavy burdens. ");
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

		return s("Tratando de decir", "Diciendo") + "te lo " + s("poquito") + "que sabemos "
				+ s("acerca de algo");
	}

	protected InnerResponse whatPicture() {

		String speech = "Esta " + s("imagen,", "fotografía,") + "que es a menudo "
				+ s("puesta", "enmarcada y colgada") + "cerca de nosotres, "
				+ s("es", "es, " + s("de hecho,", "")) + "un facsímil cortado en papel ";
		speech += "con " + s("artificiales") + "flores que adornan " + s("antiguos")
				+ "santuarios budistas chinos. ";
		speech += s("Eso está aquí, " + s("simplemente,") + "para recordarnos, " + s("y tú,") + "el lugar, "
				+ s("una casa,") + s("dentro de la cual estábamos acostrumbrades a vivir.", "solíamos vivir.") + s("Contigo."));
		return new InnerResponse(whatPictureCardTitle(), speech += breath());
	}

	protected String whatPictureCardTitle() {

		return S("Una fotografía", "Una imagen") + s("que nos recuerda a casa", "que nos gusta");
	}

	protected String whatsLsnrsAffectSpeech() {

		String speech = "";
		String affect = (String) sessAttributes.get(AFFECT);
		String listenersAffect = (String) sessAttributes.get(LISTENERSAFFECT);

		affect = setAndGetRandomAffectIfEmpty(AFFECT);
		listenersAffect = setAndGetRandomAffectIfEmpty(LISTENERSAFFECT);

		boolean shared = affect.equals(listenersAffect);
		boolean halfTheTime = heads();

		speech += " " + (shared ? s("también", "además,") : "") + "estamos "
				+ s("llenes de", s("poseídes por", "sobrepasades por")) + listenersAffect + ". "
				+ breathShort();
		if (isPositive(affect) && isPositive(listenersAffect)) {

			speech += "es bueno " + s("para todes") + "saber que podemos "
					+ (shared ? s("compartir esos sentimientos.", "empatizar contigo.") : "tener estos sentimientos. ");
		}
		else if (!isPositive(affect) && !isPositive(listenersAffect)) {

			speech += s(s("ay", "Oh") + "por dios!", s("ay diosito!", "Oh santo cielo")) + "Que "
					+ s("ninguna persona", "nadie " + s("ninguno") + "de nosotres") + "debería " + s("tener que")
					+ s("vivir estos preocupantes " + phonemic("a") + "fectos.", "sentimientos.");
		}
		else if (isPositive(affect) && !isPositive(listenersAffect)) {
			speech += "Estamos" + s(", al menos,", SPC) + s("agradecides", "") + "de " + s("saber", "ser conscientes")
					+ "que eres positiva ante tus " + s("sentimientos", phonemic("a") + "fectos") + "mejores "
					+ s("que los de nosotres.", "que nuestros sentimientos negativos.");
		}
		else // speaker negative; listeners positive
		{
			speech += "Es " + s("avegonzante", "raro")
					+ "estar esperimentando sentimientos positivos cuando tú estás "
					+ s("poseída por esto")
					"Pero " + s("supusimos") + "que eso no está " + s("realmente") + "siendo de gran ayuda. " + s("¿o sí?"));
		}
		return speech + breath();
	}

	protected String whatsLsnrsAffectCardTitle() {

		return "Estamos " + s("tratando de") + "sentir";
	}

	protected InnerResponse whatsSpkrsAffect() {

		String speech = "";
		String affect = (String) sessAttributes.get(AFFECT);

		if (affect != null && !affect.isEmpty()) {
			if (isPositive(affect)) {
				speech = "Estamos" + s("muy") + s("a gusto", "satisfeches");
			}
			else {
				String adjective, intensifier;
				adjective = s("sentides", "consternades");
				intensifier = "consternades".equals(adjective) ? "algo " : "muy ";
				speech = "Estamos " + s(adjective, intensifier + adjective);
			}
			speech += "para " + s("saber", s("recordar", "repensar")) + "que ";
			// make it brief 1 in 4 times:
			if (randInt(0, 3) == 0)
				speech = "Y";
			else
				speech += "t";
			speech += String
					.format("ú estás " + s("llena de", s("poseída", "sobrepasada") + "por") + "esos %s ", affect);

			speech += s(breath() + specificAffectSpeech());
		}
		// affect is not set
		else {
			// Since the user’s affect is not set, ask them:
			speech = "" + s("no tenemos claridad acerca de", "dudamos, acerca de");
			speech += s("tus sentimientos.",
					"de que estos sentimientos están " + s("poseyéndote", "en ti") + "");
			speech += "Por favor " + s("trata de", "intenta") + "contarnos. ";
		}
		return new InnerResponse(whatsSpkrsAffectCardTitle(), speech += breath());
	}

	protected String whatsSpkrsAffectCardTitle() {

		return S("Tú nos dijiste " + s("esto", "esto, acerca de tus sentimientos"),
				"Lo que creemos que estás sintiendo");
	}

}
