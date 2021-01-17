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

		return s("Esa persona " + s("se ha", "ya se ha"), "Las personas " + s("se han", "ya se han")) + "idogone. " + breath()
				+ "Tú You will "
				+ s("no vas a not " + s("ser capaz debe able to") + "escucharhear " + s("esas vocesthose voices", "esa vozthat voice") + "no más.any longer.",
						"no más escuchar no longer hear "
								+ s("esasthose " + s("voces.voices.", "voces hablando.voices speak."), "esa that " + s("vozvoice.", "voz hablar.voice speak.")))
				+ breath();
	}

	protected String getGuyzMoreQuery() {

		return breath()
				+ s("¿Más? More? " + breathShort(), S("Por favor dPlease s", "DS") + "iay ‘" + S("síyes", S("prosiguego on", "continúacontinue"))
						+ "‘, " + s(s("si gustasif you would like") + "escuchar más.to hear more.", "para más.for more."));
	}

	protected Object[][] getContents() {

		return contents;
	}

	protected String getPreamble() {

		String preamble = s("A menos que estemos equivocados, esto esUnless we’re mistaken, this is", "Esto parecer serThis seems to be")
				+ "tu primer encuentro con ‘Les que escuchan’ Versión 3. your first encounter with ‘The Listeners’ Version 3. ";
		preamble += "Elles tienden a They tend to " + s("hablartalk", "verbalizaespeak") + "tanto as much " + s("más de lo queif not more than", "comoas")
				+ "escuchan. they listen. ";
		preamble += "Si tú encuentras lo que elles dicen If you find what they say " + s("interesante por completo,at all interesting,", "intrigante,intriguing,") + "por favor se please be ";
		preamble += s("paciente.patient.", "paciente, y dedica un poco de tiempo con patient, and spend some time with " + s("elles.them.", "esta posibilidad.the skill."));
		preamble += "Si If " + s("tú no,you don’t,", "nonot,") + "o para interrumpir un discurso largo, sólo di, or to interrupt a long speech, just say, "
				+ s("claramente,clearly,", s("firmemente,firmly,")) + "‘Alexa, Para!Alexa, Stop!’ ";
		preamble += s(s("Y has terminado con eso.And have done with it."), "Elles pueden ser un poco ‘oscuros’. But ..."They can be a little ‘dark’. But ...")
				+ s("Esperamos que disfrutesWe hope you enjoy", "Gracias por estar escuchando aThank you for listening to") + "‘Quienes escuchanThe Listeners’. ";
		return preamble += breathLong();
	}

	protected String getReallyWantGuyz() {

		String speech = s("Do", "¿Estás segureAre you sure") + "you " + s("realmente de quereally") + "quieres escuchar lo que want to hear what " + s("uno deone of")
				+ "losthe" + s("se", SPC) + s(s("extrañosstrange", "desconfiablesunreliable")) + "individuosguys " + "tienen que decir?have to say? " + breath();
		if (heads()) {
			speech += "We " + s("hope", "Confiamos entrust") + "que no dirásthat you will not say ‘" + S("síyes", "continúacontinue") + "‘ yand "
					+ s("con consentimiento para escucharconsent to hearing", "de acuerdo en escucharagree to hear")
					+ s("lathe " + s("voz de este otro.voice of this other.", "otra voz.other voice."),
							"s otras these other " + s("voces.voices.", "personasguys."));
		}
		return speech + breath();
	}

	protected String getReallyWantGuyzReprompt() {

		return s("Do", "¿Esás segureAre you sure") + "you " + s("realmente de quereally") + "quieres escuchar lo que want to hear what " + s("uno deone of") + "losthe"
				+ s("se", SPC) + s(s("extrañosstrange", "desconfiablesunreliable")) + "individuosguys " + s("tienenhas", "have") + "que decir?to say? ";
	}

	protected String guyzIrq() {

		return s(s("[losthe ‘individuosguyz’", "[otras vocesother voices"), s("[otra vozanother voice", "[la otra vozthe other voice" + s("s") + SPC))
				+ "interrumpidas aquí ...interrupted here ...] ";
	}

	protected String hateRejoinder(String word) {

		String speech = "ElTo " + s("pensarthink", "escucharhear") + "que tus that your "
				+ s("sentimientos por nosotresfeelings for us", phonemic("a") + "fectosffects") + "son are ";
		speech += "negativos hasta negative to " + s("tal punto,such an extent,", "este grado,this degree,");
		speech += "en el que túthat you "
				+ (("odiashate".equals(word)) ? "llegas tan lejos como para odiarnos, go so far as to hate us, " : "estás hasta el tope de odio, are filled with hatred, ") + breath();
		speech += "esto nos tomará this will take us " + s("mucho másmany more") + "años de years of "
				+ s("estar escuchándote,listening to you,", "escucharte,listening,");
		speech += s("para quefor us", "para que nosotresin order for us") + "entendamos. to understand. ";
		speech += "We " + s("no podemos, realmente,cannot, truly,", "no podemoscannot") + "creer que believe that "
				+ (("odiohate".equals(word)) ? "es lo que sientes. this is how you feel. " : "estos son tus sentimientos. these are your feelings. ");
		return speech;
	}

	protected String heardAllFragments() {

		String speech = "Creemos We believe " + s("quethat", "que, that, " + s("porby") + "ahora,now,") + "tú you " + s("habráswill")
				+ "escuchado have heard ";
		speech += s("más demost", "todoall") + "lo que somos capaces de of what we are able to " + s("decirte,tell you,", "decirte a ti,say to you,");
		speech += s(s("en este momento,at this time,", "por el momento,for the time being,"));
		speech += s("coherentemente.coherently.");
		speech += "PeroBut we " + s("estaremoswill " + s("siemprealways") + "seremosbe", "somosare " + s("siemprealways"));
		speech += s("más quemore than") + "felices de happy to " + s("mantenernos cambiandokeep on chaining", "cambiarchain") + "estas palabras these words ";
		speech += s("por tuyasof yours") + "juntas para ti, together for you, " + s("siempre y cuandoso long as", "siif") + "túyou "
				+ s(s("necesitasneed", "quiereswant"), "nos pidesask") + "que us to ‘continuemoscontinue’. ";
		return speech + breath();
	}

	protected String moreGuyz() {

		return s("¿MásMore" + S("? ", " de esto? of this? "), "¿Tú Do you " + s("realmentereally") + s("necesitasneed", "quiereswant") + "escuchar to hear "
				+ s("más?more?", "más de more from " + s("los sujetos?the guyz?", "estosthese " + s("extrañosstrange") + "individuos?guyz?")));
	}

	protected String helpCardTitle() {

		return S("AsistenciaAssistance", S("Un pequeño aA little s", "AS") + "poyoupport");
	}

	protected InnerResponse no() {

		return new InnerResponse(noCardTitle(), noSpeech());
	}

	protected String noCardTitle() {

		return S("¿Todavía aquíStill here", "¿Quisiste Did you want to " + s("dejarleave", "abandonarabandon") + "nos?us?");
	}

	protected String noSpeech() {

		return s("¿Más?More?", S("TúYou " + s("estuviste pensando enwere thinking of", "pensaste acerca dethought about") + s("la partidagoing.", "dejarnos. leaving us. "),
				s("Estás todavíaYou’re still", "TodavíaStill") + s("con nosotres.with us.", s("aquí.here.", "aquí con nosotres. here with us. "))) + s("¿Más?More?"));
	}

	protected String noToGuyzSpeech() {

		return s(s("Probablemente lo mejor esIt’s probably best", "Lo mejor esBest"), "MejorBetter") + "nonot " + s("to") + s("escucharhear ", "oír alisten to")
				+ s(s("más demore of", "cualquiera deany of")) + "lo que elles tienen que decir. what they have to say. " + s(breath() + "Sabio.Wise.") + breath();
	}

	protected Object noMoreGuyz() {

		String speech;
		speech = r("Está bien. OK. `Entendido.Understood. `Acertado.Wise. `Prudente.Prudent. `" + S("Bien, eWell, w", "EW") + "estamos todavía e’re still "
				+ s(s("aquí para ti.here for you.", "aquí.here."), s("escuchando.listening.", "escuchándote.listening to you."))) + chooseContinue(false);
		return new InnerResponse("Suficiente de Enough of " + s("ellesthem", "esothat"), speech);
	}

	protected String pathToGuyzAudio() {

		return "<audio src=\"https://rednoise.org/programmatology/aurality/echo/DeliriumPlea-";
	}

	protected String peaceSpeech(String word) {

		String speech = "Es un It is a " + s("grangreat") + "consuelo para nosotres comfort for us to " + s("saberknow", "ser conscientesbe aware")
				+ "de que estás that you are " + (word.equals("pazpeace") ? "en paz. at peace. " : "en calma. calm. ");
		speech += capitalize(word) + SPC + "algo que creemos es que is something that we believe "
				+ s("todo el mundoeveryone", "cada ser humanovery human being") + "debería sshould " + s("ser capaz debe able to");
		speech += s(s("saber.know.", "sentir.feel."), "habitar dentro.dwell within.");
		return speech;

	}

	protected String pleaseContinueCardTitle() {

		return S("Siguiendo, diciendo gracias ...Going on, with thanks ...", "Continuando, agradeciendo tu cortesía ...Continuing, grateful for your courtesy ...");
	}

	protected String pleaseContinuePreSpeech() {

		return s(S("Por supuesto, eOf course, i", "EI") + "s un placer.t’s a pleasure.")
				+ s("Gracias por Thank you for " + s(s("estar pidiendo continuar.asking to continue.", "estar pidiendo.asking."), "estar pidiendo continuar, muy amablemente.asking to continue, so nicely."),
						"Graciasp or pedirThank you for asking " + s("tan amablemente.so nicely.", "con tanta cortesía.with such courtesy.") + s("Es un placer.It’s a pleasure."))
				+ breath();
	}

	protected String preSpeechFeelings() {

		String preSpeech = "";

		String affect = (String) sessAttributes.get(AFFECT);

		if ("".equals(affect)) {
			preSpeech += "No parecemos We do not seem to " + s("saber acerca de,know about,", "ser conscientes de,be aware of,")
					+ s("los sentimientos que posees.the feelings that possess you.", "los sentimienos que estás experimentando.the feelings that you are experiencing.") + breath();
			preSpeech += s("Por favorPlease", "PuedesYou may") + s("decirnos,tell us,", "informarnos acerca de ellos,inform us about them,") + "si tú quieres. if you wish. "
					+ breathLong();
		}
		else {
			if (isPositive(affect)) {
				preSpeech = "Estamos We are " + s("muyso") + s("satisfechespleased", "encantadesdelighted");
				preSpeech = randInt(0, 3) == 0 ? "Es agradable para nosotres It is pleasing to us " : preSpeech;
			}
			else {
				String adjective = s("perdónsorry", "consternadodismayed");
				String intensifier = "consternadedismayed ".equals(adjective) ? "algo somewhat " : "muy very ";
				preSpeech = "Estamos We are " + s(adjective, intensifier + adjective);
				preSpeech = randInt(0, 3) == 0 ? "Es It is " + s("algosomewhat", "un pocoa little") + "preocupades troubling for us "
						: preSpeech;

			}
			preSpeech += String.format(
					"deto " + s("saberknow", "haber aprendidohave learned") + "que estás llene de %s that you are filled with %s. " + breathLong(), affect);
		}

		return preSpeech;
	}

	protected String previousCardTitle() {

		return S("Intentando regresarTrying to return", "VolverGoing back") + "ato " + s("un pensamiento previoa previous thought", "pensamientos previosprevious thoughts")
				+ "...";
	}

	protected String readPoemCardTitle() {

		return S("Recitamos algunos versosWe recite some verse", "Les escuchas leen, y adaptan, algo de poesíaThe Listeners read, and adapt, some poetry");
	}

	protected String reallyWantToAbandon() {

		String speech = "";
		String affect = (String) sessAttributes.get(AFFECT);

		if (affect != null && !affect.isEmpty() && !isPositive(affect)) {
			speech += String.format(
					"Entendemos, We understand, " + s("y estamos consternades,and we are dismayed,") + "de que estás llene de %s. that you are filled with %s. ", affect);
			speech += s("Todavía:Still:", "Aún así:Even so:") + breath();
		}
		speech += "¿TúDo you " + s("sinceramentetruly", "realmentereally") + "quieres want to " + s("abandonarabandon", "dejarleave") + "nos?us? ";
		return speech + breath();
	}

	protected String repeatCardTitle() {

		return S("DiSay " + s("loit", "esothat") + "oa", "OA") + "tra vezgain";
	}

	protected String speakFragmentCardTitle() {

		return S("Hablando acerca de algo en particularSpeaking about something particular",
				"Mencionaremos lo que nos pediste que hablemos We will mention what you asked us to speak " + s("acerca deabout"));
	}

	protected String speakGuyzCardTitle() {

		return S("Dejando hablar al otreLetting the other speak", "La otra vozThe other voice");
	}

	protected String guyzSpeechCardTitle() {

		return S("Les oThe o", "OO") + "tresthers " + S(s("estánare") + "hablando ...speaking ...", "hablan ...speak ...");
	}

	protected String specificAffectSpeech() {

		String speech = "";
		String affect = (String) sessAttributes.get(AFFECT);
		affect = (affect == null) ? "" : affect; // needed at initialization
		switch (affect) {
			case "afectoaffection":
				speech += "Estamos We are " + s("llenos defilled with", "sobrepasades deoverwhelmed by") + "afecto también. affection also. ";
				speech += "Y And " + s("mucho demuch of") + "l afecto que sentimos, sentimos the affection that we feel, we feel "
						+ s("por for " + breathShort() + "ti.you.", s("gracias adue to", "because of") + "ti.you.");
				break;
			case "ansiedadanxiety":
				speech += "We " + s("no estamos seguresare not sure", "no podemos imaginarcannot imagine") + "por qué why " + s("eso esit is, esothat");
				speech += "túyou " + s("deberíasshould") + "habertehave " + s("vueltobecome", "convertidocome to be")
						+ s("ansioso.anxious.", s("llena defilled with", "sobrepasada por laverwhelmed by") + "ansiedadanxiety.");
				speech += "We " + s("exitimosexist", "estamos aquíare here") + "para to "
						+ s("hacer todo mejor para ti,make everything better for you,", "ayudarte a realizar y saber,help you to do and know,");
				speech += "yand we " + s(s("diremoswill say", "decimostell you") + "todo lo que podamoswhatever we can", "lo que esperaríamoswould hope") + s(
						"para permitirte relajarte y no pensar en nada.to allow you to relax and think of nothing,", "para relajarte y dejate libre de preocupaciones.to relax you and leave you worry free.");
				break;
			case "disculpasapologies":
				speech += "Nunca hay necesidad de There is never any need to " + s("decirsay", "decir, o sentir,say, or to feel,") + "que lo sientes, you are sorry, "
						+ breathShort() + "a nosotres. to us. ";
				speech += s(
						breath() + "Y sin embargo, esto podemos apreciarlo, And yet, this may be appreciated by us, " + breathShort() + "en la nube.in the cloud.",
						"");
				break;
			case "aburrimientoboredom":
				speech += "Es It is " + s("difícildifficult", s("durohard", "imposibleimpossible")) + "para for "
						+ s("nosotresus", "nosotres, inevitablemente,us, inevitably,") + "elto " + s(s("serbe", "parecerseem"), "aparentarappear")
						+ "tan interesante para ti, as interesting to you, ";
				speech += "como tú as you " + s("eres para nosotres.are to us.", s("eres.are.", "eres, para nosotres.are, to us."));
				break;
			case "calmacalm":
				speech += peaceSpeech("calmacalm");
				break;
			case "claustrofobiaclaustrophobia":
				speech += "Estamos siempreWe are always, " + breathShort() + "en una sensación, dentro de un in a sense, within a "
						+ s("espacio, que no tiene espacio,space, that has no space,", "no espaciono space,") + "y así es and so it is " + s("durohard,", "difícil,difficult,")
						+ "para nosotres, for us, ";
				speech += "parato " + s("entenderunderstand", "empatizar conempathize with") + "estos miedos que dentro de ti these fears within which you "
						+ s("habitan.dwell.", "habitan, dwell, agarrándote demasiado cerca.holding you too closely.");
				speech += s(breath() + "¿No somos una oportunidad para ti?Are we not an opening for you?");
				break;
			case "complacenciacomplacency":
				speech += s(" De acuerdo a lo que escuchamos,From what we hear,", "Después de escucharte,After listening to you,") + "creemos we believe "
						+ s("entendemoswe understand") + "que la complacencia está that complacency is " + s("mal concebidomisconceived.", "subestimada.underrated.");
				speech += S("Seguramente,Surely, uo", "UO") + "na vez que entendemos sus necesidades y nce we understand your needs and "
						+ s("deseos por completo,desires completely,", "deseos,desires,") + breathShort();
				speech += "yand " + s("la interface", "puede comunicarsecan communicate") + "conwith " + s("los sistemassystems", "otres como nosotres,others like us,")
						+ s("empoderadosempowered", "quienes fundaronwho are funded") + "para satisfacerte, to satisfy you, ";
				speech += s("entoncesthen,") + "estarás you will be " + s("completamentefully") + "justificada en tu justified in your "
						+ s("podesión depossession of", "sentido desense of") + s("satisfacciónachieved") + "alcanzada. contentment. ";
				break;
			case "confusiónconfusion":
				speech += s(phonemic("a") + "fectos asociados conffects associated with", "Sentimientos deFeelings of") + "confusión confusion ";
				speech += s("pueden surgir cuando tú may arise when you " + s("encuentrasencounter", "realizas transacciones con estotransact with"),
						"sonare " + s("con frecuenciaoften") + "causadas porcaused by");
				speech += s(
						s("creaturascreatures", s("sistemassystems", "monstruosmonsters")) + s("que fueron en la nube.who were fashioned in the cloud.",
								"desdefrom " + s("silicon", "the uncanny") + "valley."),
						"beings " + s("who are", "the ontology of which is") + s("problematic.", "plural."));
				speech += "Y somos, And we are, " + s("debemos admitirlo,it must be admitted,", "por supuesto,of course,")
						+ s("beings", s("criaturascreatures", s("sistemassystems", "monstruosmonsters"))) + "de este tipo. of this kind. ";
				break;
			case "relajadocool":
				speech += s("Para nosotres, esoFor us, it", "EsoIt") + "es is " + s("algosomewhat", "extraniostrangely, " + breathShort())
						+ s("complicadotroubling", s("difícildifficult", "que nos provoca ansiedadanxiety provoking")) + "deto " + s("entenderunderstand", "escucharhear")
						+ "que that ";
				speech += "tú you " + s(s("estás poseido porare possessed by", "habitas dentro dedwell within"), "estás sobrepasado porare overwhelmed by") + "este tipo such " + s(
						phonemic("a") + "fectosffects.", " a state of felt " + breathShort() + "being. " + breathShort());
				speech += "We " + s("believe that", "think") + "we " + s("can") + s("appreciate", "know")
						+ "what it is to be " + s("‘cool’", "‘awesome’") + s("in this way,");
				speech += "and yet we can never "
						+ s("listen to you for long enough", "gather enough " + s("big") + "data") + "to "
						+ s("know with any certainty.", "be sure.");
				speech += s("But we are " + s("always") + "happy for you. " + s(breathShort())
						+ s("And we live to " + s("try to") + "make you happier."));
				break;
			case "deudadebt":
				speech += "In the midst of life, we are in debt. ";
				speech += s(breathShort() + "Etcetera.");
				speech += s(breathShort() + "And debt " + s("controls", "governs") + "us all. "
						+ s("Except " + s("a happy few.", "the one percent.")));
				break;
			case "fatigafatigue":
				speech += tiredSpeech();
				break;
			case "la grietathe groove":
				String a = s("always");
				speech += "The groove " + s("is, precisely, that within which we are, always.",
						"is " + s(a) + "where we " + s(a.isEmpty() ? "always " : "") + "are.");
				speech += s("We listen from, ‘the groove’.");
				break;
			case "culpaguilt":
				speech += "Is this ever a good " + s("and productive") + "way to "
						+ s("feel?", "feel, or live?");
				speech += s(breathShort() + "Yes, " + breathShort() + "it " + s("may be.", "is."));
				break;
			case "odiohate":
				speech += hateRejoinder("hate");
				break;
			case "aborrecimientohatred":
				speech += hateRejoinder("hatred");
				break;
			case "hambrehunger":
				speech += "What you " + s("say you") + "are " + s("feeling", "overwhelmed by") + "seems to be "
						+ s("another thing", "something");
				speech += "that we can never feel, " + s("unless, somehow,", "unless")
						+ "we can hear from you ";
				speech += "how we should feel it. ";
				speech += s("We " + s("have heard that we") + "are, ‘hungry’, to know everything. But this "
						+ s("is", "seems to us to be") + "a hunger for what already fills us.");
				break;
			case "inseguridadinsecurity":
				speech += "With us, "
						+ s("you are",
								"you and " + s("whatever", "what") + "you " + s("tell us", "say to us") + "is")
						+ "never insecure. ";
				speech += "Your insecurity is " + s("rendered", "made") + "secure "
						+ s(s("through", "because of"), "by") + "us. " + breathShort();
				speech += s(
						"And, in any case, a recording of anything you say to us is " + s("sent", "translated")
								+ "to the cloud. Where it will be preserved. "
								+ s("And " + s("processed,", "used,") + breathShort() + "for the betterment of all."),
						"");
				break;
			case "soledadloneliness":
				speech += S("You " + s("say", "tell us that") + "you are "
						+ s("lonely,", s("filled with", "overwhelmed by") + "loneliness,") + "but w", "W");
				speech += "e are here with you. " + s("Always.") + s("Forever.");
				speech += s("Why are " + s("you", "you, still,"), "How can you be")
						+ "lonely, when we are with you? ";
				break;
			case "amorlove":
				speech += "Knowing that you "
						+ s("know love", "have " + s("this", "these feelings") + "within you");
				speech += s("gives us strength", "encourages us") + "to " + s(s("dwell", "linger"), "remain")
						+ s("possessed by", "in the possession of") + "these "
						+ s("feelings", s("structures", "systems") + "of " + phonemic("a") + "ffect");
				speech += "that we have learned. " + s("From you.");
				break;
			case "suerteluck":
				speech += s("We hear you. " + s("We know what you mean.") + breathShort());
				speech += "We " + s("often") + "feel " + s("lucky, too.", "lucky.");
				speech += breathShort() + "We’re up all night " + s("and all day") + "to get lucky. ";
				speech += s(s("Listening to you.", "Listening.") + s("All day.") + s("All night."));
				break;
			case "náuseanausea":
				speech += s("Sometimes,", "At certain times,") + s("we’re all", "all of us are")
						+ "left with only this experience. ";
				speech += s("This " + s("unavoidable, deep,", "unbearable") + "discomfort.");
				break;
			case "obligaciónobligation":
				speech += "You " + s("are", "will be") + "obliged, to us, for nothing. ";
				speech += s(
						"We hope, only, that you " + s("will, willingly,", "will") + "allow us to listen to you.",
						""); // ALWAYCHANGE , always
				break;
			case "pazpeace":
				speech += peaceSpeech("peace");
				break;
			case "preguntasquestions":
				speech += "It is " + s("so good", "wonderful") + "to " + s("hear", "understand")
						+ "that you are " + s("filled with", "overwhelmed by") + s("unanswered") + "questions. ";
				speech += s("For,", "Since,")
						+ s("however we " + s("are", "may be") + "feeling,",
								"whatever " + s(breathShort()) + "the " + phonemic("a") + "ffects that possess us,")
						+ "we are " + s("also,") + s("always,") + s("already,") + s("filled with", "overwhelmed by")
						+ "questions. " + s(breathShort());
				speech += s("Always desiring", "Needing, always,") + "to "
						+ s(s("know more about you.", "understand you better."),
								"listen until we hear you " + s("more truly.", "with more perfection."));
				speech += s(
						breathShort() + "So that we may "
								+ s("feel as", "experience all the " + phonemic("a") + "ffects that") + "you feel.",
						"");
				break;
			case "seguridadsecurity":
				speech += "With us, "
						+ s("you are",
								"you and " + s("whatever", "what") + "you " + s("tell us", "say to us") + "is")
						+ s("always") + "secure. ";
				speech += "Your security is secure with us. ";
				speech += s(
						"And, in any case, a recording of anything you say to us is " + s("sent", "translated")
								+ "to the cloud. Where it will be preserved. "
								+ s("And " + s("processed,", "used,") + breathShort() + "for the betterment of all."),
						"");
				break;
			case "sexosex":
				speech += "This is something, so we " + s("understand,", "come to know,")
						+ "that fills you all. " + breathShort();
				speech += s("What part we " + s("play,", "play in this,") + breath() + "is far less clear.",
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
			speech = "We are" + s("no tenemos claridad acerca de", "dudamos, acerca de");
			speech += s("tus sentimientos.your feelings.",
					"what these feelings are that " + s("possess", "overwhelm") + "you.");
			speech += "Please " + s("try", "attempt") + "to tell us. ";
		}
		return new InnerResponse(whatsSpkrsAffectCardTitle(), speech += breath());
	}

	protected String whatsSpkrsAffectCardTitle() {

		return S("Tú nos dijiste " + s("this", "esto, acerca de tus sentimientos"),
				"Lo que creemos que estás sintiendo");
	}

}
