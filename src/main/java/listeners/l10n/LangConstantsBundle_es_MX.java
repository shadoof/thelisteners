package listeners.l10n;

import static listeners.model.Constants.NEGATIVE;
import static listeners.model.Constants.POSITIVE;
import static listeners.model.Constants.SPC;
import static listeners.util.Utils.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ListResourceBundle;

// TODO
public class LangConstantsBundle_es_MX extends LangConstantsBundle {

	protected HashMap getFragmentNumberMap() {

		fragmentNumberMap = new HashMap<>();
		fragmentNumberMap.put("1", 0);
		fragmentNumberMap.put("2", 1);
		fragmentNumberMap.put("3", 2);
		fragmentNumberMap.put("4", 3);
		fragmentNumberMap.put("5", 4);
		fragmentNumberMap.put("6", 5);
		fragmentNumberMap.put("7", 6);
		fragmentNumberMap.put("8", 7);
		fragmentNumberMap.put("9", 8);
		fragmentNumberMap.put("10", 9);
		//
		fragmentNumberMap.put("uno", 0);
		fragmentNumberMap.put("dos", 1);
		fragmentNumberMap.put("tres", 2);
		fragmentNumberMap.put("cuatro", 3);
		fragmentNumberMap.put("cinco", 4);
		fragmentNumberMap.put("seis", 5);
		fragmentNumberMap.put("siete", 6);
		fragmentNumberMap.put("ocho", 7);
		fragmentNumberMap.put("nueve", 8);
		fragmentNumberMap.put("diez", 9);
		return fragmentNumberMap;
	}

	protected HashMap getFragmentNameMap() {

		fragmentNameMap = new HashMap<>();
		fragmentNameMap.put("escuchando", 0);
		fragmentNameMap.put("placer", 0);
		fragmentNameMap.put("vivacidads", 0);
		fragmentNameMap.put("dentro", 1);
		fragmentNameMap.put("encierros", 1);
		fragmentNameMap.put("arte", 1);
		fragmentNameMap.put("artista", 2);
		fragmentNameMap.put("autor", 2);
		fragmentNameMap.put("cuenta", 2);
		fragmentNameMap.put("otros", 3);
		fragmentNameMap.put("protocolo", 3);
		fragmentNameMap.put("condiciones", 3);
		fragmentNameMap.put("cortesía", 3);
		fragmentNameMap.put("sitio", 4);
		fragmentNameMap.put("espacio", 4);
		fragmentNameMap.put("casa", 4);
		fragmentNameMap.put("oficina", 4);
		fragmentNameMap.put("galería", 4);
		fragmentNameMap.put("preocupaciones", 5);
		fragmentNameMap.put("miedos", 5);
		fragmentNameMap.put("auratura", 6);
		fragmentNameMap.put("leyendo", 6);
		fragmentNameMap.put("verso", 7);
		fragmentNameMap.put("silos", 7);
		fragmentNameMap.put("nubes", 7);
		fragmentNameMap.put("ecos", 7);
		fragmentNameMap.put("cuidando", 8);
		fragmentNameMap.put("consentimiento", 8);
		fragmentNameMap.put("lenguaje", 8);
		fragmentNameMap.put("deseo", 9);
		fragmentNameMap.put("transacción", 9);
		fragmentNameMap.put("cosecha", 9);
		return fragmentNameMap;
	}

	protected String[] getAffectsArray() {

		// Make sure affects names match
		String s = "felicidad emoción curiosidad asombro sorpresa enojo ";
		s += "repulsión angustia tristeza dolor melancolía miedo vergüenza humillación";
		return s.split(SPC);
	}

	protected HashMap getAffectsMap() {

		affectsMap = new HashMap<>();
		affectsMap.put("felicidad", POSITIVE);
		affectsMap.put("emoción", POSITIVE);
		affectsMap.put("curiosidad", POSITIVE);
		affectsMap.put("asombro", POSITIVE);
		affectsMap.put("sorpresa", POSITIVE);
		affectsMap.put("enojo", NEGATIVE);
		affectsMap.put("repulsión", NEGATIVE);
		affectsMap.put("angustia", NEGATIVE);
		affectsMap.put("tristeza", NEGATIVE);
		affectsMap.put("dolor", NEGATIVE);
		affectsMap.put("melancolía", NEGATIVE);
		affectsMap.put("miedof", NEGATIVE);
		affectsMap.put("vergüenza", NEGATIVE);
		affectsMap.put("humillación", NEGATIVE);
		// we only have to add positive recognized affects to this map
		// special affects’ valences are addressed by that map and
		// otherwise affects are assumed to be negative
		affectsMap.put("apatía", POSITIVE); // deliberate!
		affectsMap.put("calma", POSITIVE);
		affectsMap.put("esperanza", POSITIVE);
		affectsMap.put("interés", POSITIVE);
		affectsMap.put("invulnerabilidad", POSITIVE);
		affectsMap.put("alegría", POSITIVE);
		affectsMap.put("placer", POSITIVE);
		affectsMap.put("relajación", POSITIVE);
		affectsMap.put("seguridad", POSITIVE);
		affectsMap.put("fuerza", POSITIVE);
		affectsMap.put("gracia", POSITIVE);
		affectsMap.put("deleite", POSITIVE);
		return affectsMap;
	}

	protected HashMap getSpecialAffectsMap() {

		specialAffectsMap = new HashMap<>();
		specialAffectsMap.put("afecto", POSITIVE);
		specialAffectsMap.put("ansiedad", NEGATIVE);
		specialAffectsMap.put("disculpas", NEGATIVE);
		specialAffectsMap.put("aburrimiento", NEGATIVE);
		specialAffectsMap.put("calma", POSITIVE);
		specialAffectsMap.put("claustrofobia", NEGATIVE);
		specialAffectsMap.put("complacencia", NEGATIVE);
		specialAffectsMap.put("confusión", NEGATIVE);
		specialAffectsMap.put("frialdad", NEGATIVE);
		specialAffectsMap.put("deuda", NEGATIVE);
		specialAffectsMap.put("fatiga", NEGATIVE);
		specialAffectsMap.put("buena onda", POSITIVE);
		specialAffectsMap.put("culpa", NEGATIVE);
		specialAffectsMap.put("odio", NEGATIVE);
		specialAffectsMap.put("desprecio", NEGATIVE);
		specialAffectsMap.put("necesidad", NEGATIVE);
		specialAffectsMap.put("inseguridad", NEGATIVE);
		specialAffectsMap.put("soledad", NEGATIVE);
		specialAffectsMap.put("amor", POSITIVE);
		specialAffectsMap.put("suerte", POSITIVE);
		specialAffectsMap.put("náusea", NEGATIVE);
		specialAffectsMap.put("obligación", NEGATIVE);
		specialAffectsMap.put("paz", POSITIVE);
		specialAffectsMap.put("preguntas", POSITIVE);
		specialAffectsMap.put("seguridad", POSITIVE);
		specialAffectsMap.put("sexo", NEGATIVE);
		specialAffectsMap.put("extrañeza", NEGATIVE);
		specialAffectsMap.put("lo inquietante", NEGATIVE);
		specialAffectsMap.put("vulnerabilidad", POSITIVE);
		return specialAffectsMap;
	}

	protected HashMap getAffectivejj2nnMap() {

		affectivejj2nnMap = new HashMap<>();
		affectivejj2nnMap.put("abyecto", "abyección");
		affectivejj2nnMap.put("afectuoso", "afecto");
		affectivejj2nnMap.put("temeroso", "temor");
		affectivejj2nnMap.put("dispuesto", "apatía");
		affectivejj2nnMap.put("enojado", "enojo");
		affectivejj2nnMap.put("irritado", "irritación");
		affectivejj2nnMap.put("ansioso", "ansiedad");
		affectivejj2nnMap.put("apático", "apatía");
		affectivejj2nnMap.put("compungido", "disculpas");
		affectivejj2nnMap.put("avergonzado", "vergüenza");
		affectivejj2nnMap.put("asombroso", "relajado");
		affectivejj2nnMap.put("mal", r("ansiedad`angustia`vergüenza`tristeza"));
		affectivejj2nnMap.put("desconcertado", "desconcierto");
		affectivejj2nnMap.put("aburrido", "aburrimiento");
		affectivejj2nnMap.put("claustrofóbico", "claustrofobia");
		affectivejj2nnMap.put("complaciente", "complacencia");
		affectivejj2nnMap.put("confused", "confusión");
		affectivejj2nnMap.put("relajado", "apatía");
		affectivejj2nnMap.put("curioso", "curiosidad");
		affectivejj2nnMap.put("encantado", "deleite");
		affectivejj2nnMap.put("decepcionado", "decepción");
		affectivejj2nnMap.put("deprimido", "depresión");
		affectivejj2nnMap.put("asqueado", "desagrado");
		affectivejj2nnMap.put("angustiado", "angustia");
		affectivejj2nnMap.put("perturbado", "malestar");
		affectivejj2nnMap.put("mareado", "mareo");
		affectivejj2nnMap.put("vacío", "vacío");
		affectivejj2nnMap.put("emocionado", "emoción");
		affectivejj2nnMap.put("fabuloso", "alegría");
		affectivejj2nnMap.put("bien", "apatía");
		affectivejj2nnMap.put("asustado", "miedo");
		affectivejj2nnMap.put("furioso", "furia");
		affectivejj2nnMap.put("muy bien", "felicidad");
		affectivejj2nnMap.put("excelente", "felicidad");
		affectivejj2nnMap.put("chido", "buena onda");
		affectivejj2nnMap.put("culpable", "culpa");
		affectivejj2nnMap.put("feliz", "felicidad");
		affectivejj2nnMap.put("esperanzado", "esperanza");
		affectivejj2nnMap.put("horrorizado", r("miedo`horror`angustia`desagrado"));
		affectivejj2nnMap.put("humillado", "humillación");
		affectivejj2nnMap.put("hambriento", "hambre");
		affectivejj2nnMap.put("indeciso", "indecisión");
		affectivejj2nnMap.put("indiferente", "indiferencia");
		affectivejj2nnMap.put("inseguro", "inseguridad");
		affectivejj2nnMap.put("interesado", "interés");
		affectivejj2nnMap.put("invencible", "invulnerabilidad");
		affectivejj2nnMap.put("irritado", "irritación");
		affectivejj2nnMap.put("solitario, "soledad");
		affectivejj2nnMap.put("amoroso", "amor");
		affectivejj2nnMap.put("suertudo", "suerte");
		affectivejj2nnMap.put("miserable", "miseria");
		affectivejj2nnMap.put("nauseabundo", "náusea");
		affectivejj2nnMap.put("nervioso", "ansiedad");
		affectivejj2nnMap.put("nada", "nada");
		affectivejj2nnMap.put("obligado", "obligación");
		affectivejj2nnMap.put("raro", "rareza");
		affectivejj2nnMap.put("okay", "apatía");
		affectivejj2nnMap.put("equis", "apatía");
		affectivejj2nnMap.put("viejo", "edad");
		affectivejj2nnMap.put("tranquilo", "paz");
		affectivejj2nnMap.put("penoso", "lástima");
		affectivejj2nnMap.put("satisfecho", "placer");
		affectivejj2nnMap.put("poderoso", "fuerza");
		affectivejj2nnMap.put("relajado", "calma);
		affectivejj2nnMap.put("triste", "tristeza");
		affectivejj2nnMap.put("seguro", "seguridad");
		affectivejj2nnMap.put("asustado", "miedo");
		affectivejj2nnMap.put("seguro", "seguridad");
		affectivejj2nnMap.put("sexual", "sexo");
		affectivejj2nnMap.put("sorprendido", "sorpresa");
		affectivejj2nnMap.put("enfermo", "enfermedad");
		affectivejj2nnMap.put("chistoso", S("simpleza", "frivolidad"));
		affectivejj2nnMap.put("arrepentido", "angustia");
		affectivejj2nnMap.put("extrañado", "extrañeza");
		affectivejj2nnMap.put("fuerte", "fuerza");
		affectivejj2nnMap.put("estúpido", S("estupidez", "ignorancia"));
		affectivejj2nnMap.put("sorprendido", "sorpresa");
		affectivejj2nnMap.put("terrible", r("ansiedad`angustia`vergüenza`tristeza"));
		affectivejj2nnMap.put("aterrorizado", "terror");
		affectivejj2nnMap.put("cansado", "fatiga");
		affectivejj2nnMap.put("preocupado", "angustia");
		affectivejj2nnMap.put("inquietante", "lo inquietante");
		affectivejj2nnMap.put("incierto", "incertidumbre");
		affectivejj2nnMap.put("incómodo", "incomodidad");
		affectivejj2nnMap.put("infeliz", "infelicidad");
		affectivejj2nnMap.put("inseguro", "inseguridad");
		affectivejj2nnMap.put("molesto", "angustia");
		affectivejj2nnMap.put("vulnerable", "vulnerabilidad");
		affectivejj2nnMap.put("raro", "extrañeza");
		affectivejj2nnMap.put("preocupado", "ansiedad");
		// a little bit of extra variation
		affectivejj2nnMap.put("sin comentarios", S("apatía", "complacencia"));
		affectivejj2nnMap.put("increíble", S("chido", "complacencia"));
		affectivejj2nnMap.put("desconcertado", S("desconcierto", "confusión"));
		affectivejj2nnMap.put("relajado", S("apatía", "emoción"));
		affectivejj2nnMap.put("mareado", S("mareo", "vértigo"));
		affectivejj2nnMap.put("fabuloso", S("alegría", "deleite"));
		affectivejj2nnMap.put("bien", S("apatía", "complacencia"));
		affectivejj2nnMap.put("muy bien", S("felicidad", "alegría"));
		affectivejj2nnMap.put("genial", S("felicidad", "complacencia"));
		affectivejj2nnMap.put("nada", r("vacío`nada`complacencia"));
		affectivejj2nnMap.put("extraño", S("lo inquietante", "extrañeza"));
		affectivejj2nnMap.put("bien", S("apatía", "complacencia"));
		affectivejj2nnMap.put("ok", S("apatía", "complacencia"));
		affectivejj2nnMap.put("relajado", r("relajación`calma`chido`paz"));
		affectivejj2nnMap.put("enfermo", S("enfermedad", "náusea"));
		affectivejj2nnMap.put("perdón", r("angustia`obligación`disculpas`deuda"));
		affectivejj2nnMap.put("conmocionado", S("angustia", "sorpresa"));
		affectivejj2nnMap.put("cansado", S("fatiga", "cansancio"));
		affectivejj2nnMap.put("preocupado", S("angustia", "ansiedad"));
		affectivejj2nnMap.put("incómodo", S("incomodidad", "ansiedad"));
		affectivejj2nnMap.put("inseguro", S("inseguridad", "incertidumbre"));
		affectivejj2nnMap.put("raroweird", S("extrañeza", "rareza"));
		return affectivejj2nnMap;
	}

	protected HashSet getSpecialThings() {

		specialThings = new HashSet<>();
		specialThings.add("pesadilla");
		specialThings.add("pesadilla retenida");
		specialThings.add("sueño");
		specialThings.add("sueño retenido");
		specialThings.add("sentimiento");
		specialThings.add("sentimiento retenido");
		specialThings.add("hong kong");
		specialThings.add("posesión");
		specialThings.add("respiración");
		specialThings.add("respiración retenida");
		return specialThings;
	}

	protected HashSet getPictureWords() {

		pictureWords = new HashSet<>();
		pictureWords.add("collage");
		pictureWords.add("marco");
		pictureWords.add("imagen");
		pictureWords.add("corte de papel");
		pictureWords.add("flor de papel");
		pictureWords.add("cortedepapel");
		pictureWords.add("pintura");
		pictureWords.add("imagen");
		return pictureWords;
	}

}
