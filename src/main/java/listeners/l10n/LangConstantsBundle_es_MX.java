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
		fragmentNumberMap.put("nuevee", 8);
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
		specialAffectsMap.put("afectoaffection", POSITIVE);
		specialAffectsMap.put("ansiedadanxiety", NEGATIVE);
		specialAffectsMap.put("disculpasapologies", NEGATIVE);
		specialAffectsMap.put("aburrimientoboredom", NEGATIVE);
		specialAffectsMap.put("calmacalm", POSITIVE);
		specialAffectsMap.put("claustrofobiaclaustrophobia", NEGATIVE);
		specialAffectsMap.put("complacenciacomplacency", NEGATIVE);
		specialAffectsMap.put("confusiónconfusion", NEGATIVE);
		specialAffectsMap.put("fríocool", NEGATIVE);
		specialAffectsMap.put("trampadebt", NEGATIVE);
		specialAffectsMap.put("fatigafatigue", NEGATIVE);
		specialAffectsMap.put("the groove", POSITIVE);
		specialAffectsMap.put("culpaguilt", NEGATIVE);
		specialAffectsMap.put("odiohate", NEGATIVE);
		specialAffectsMap.put("detestarhatred", NEGATIVE);
		specialAffectsMap.put("necesidadhunger", NEGATIVE);
		specialAffectsMap.put("inseguridadinsecurity", NEGATIVE);
		specialAffectsMap.put("soledadloneliness", NEGATIVE);
		specialAffectsMap.put("amorlove", POSITIVE);
		specialAffectsMap.put("suerteluck", POSITIVE);
		specialAffectsMap.put("náuseanausea", NEGATIVE);
		specialAffectsMap.put("obligaciónobligation", NEGATIVE);
		specialAffectsMap.put("pazpeace", POSITIVE);
		specialAffectsMap.put("preguntasquestions", POSITIVE);
		specialAffectsMap.put("seguridadsecurity", POSITIVE);
		specialAffectsMap.put("sexosex", NEGATIVE);
		specialAffectsMap.put("extrañezastrangeness", NEGATIVE);
		specialAffectsMap.put("el extrañothe uncanny", NEGATIVE);
		specialAffectsMap.put("vulnerabilidadvulnerability", POSITIVE);
		return specialAffectsMap;
	}

	protected HashMap getAffectivejj2nnMap() {

		affectivejj2nnMap = new HashMap<>();
		affectivejj2nnMap.put("abyectoabject", "abyecciónabjection");
		affectivejj2nnMap.put("afectuosoaffectionate", "afectoaffection");
		affectivejj2nnMap.put("temerosoafraid", "temorfear");
		affectivejj2nnMap.put("dispuestoalright", "apatíaapathy");
		affectivejj2nnMap.put("enojadoangry", "enojoanger");
		affectivejj2nnMap.put("irritadoannoyed", "irritaciónirritation");
		affectivejj2nnMap.put("ansiosoanxious", "ansiedadanxiety");
		affectivejj2nnMap.put("apáticoapathetic", "apatíaapathy");
		affectivejj2nnMap.put("compungidoapologetic", "disculpasapologies");
		affectivejj2nnMap.put("avergonzadoashamed", "vergüenzashame");
		affectivejj2nnMap.put("asombrosoawesome", "relajadocool");
		affectivejj2nnMap.put("malobad", r("ansiedadanxiety`angustiadistress`vergüenzashame`tristezasadness"));
		affectivejj2nnMap.put("desconcertadobewildered", "desconciertobewilderment");
		affectivejj2nnMap.put("aburridobored", "aburrimientoboredom");
		affectivejj2nnMap.put("claustrofóbicoclaustrophobic", "claustrofobiaclaustrophobia");
		affectivejj2nnMap.put("complacientecomplacent", "complacenciacomplacency");
		affectivejj2nnMap.put("confusedconfundido", "confusiónconfusion");
		affectivejj2nnMap.put("relajadocool", "apatíaapathy");
		affectivejj2nnMap.put("curiosocurious", "curiosidadcuriosity");
		affectivejj2nnMap.put("encantadodelighted", "deleitedelight");
		affectivejj2nnMap.put("decepcionadodisappointed", "decepcióndisappointment");
		affectivejj2nnMap.put("deprimidodepressed", "depresióndepression");
		affectivejj2nnMap.put("disgustadodisgusted", "disgustodisgust");
		affectivejj2nnMap.put("angustiadodistressed", "angustiadistress");
		affectivejj2nnMap.put("perturbadodisturbed", "malestardiscomfort");
		affectivejj2nnMap.put("mareadodizzy", "mareodizziness");
		affectivejj2nnMap.put("empáticoempty", "empatíaemptiness");
		affectivejj2nnMap.put("emocionadoexcited", "emociónexcitement");
		affectivejj2nnMap.put("fabulosofabulous", "alegrejoy");
		affectivejj2nnMap.put("bienfine", "apatíaapathy");
		affectivejj2nnMap.put("asustadofrightened", "miedofear");
		affectivejj2nnMap.put("furiosofurious", "furiafury");
		affectivejj2nnMap.put("buenogood", "felicidadhappiness");
		affectivejj2nnMap.put("biengreat", "felicidadhappiness");
		affectivejj2nnMap.put("maravillosogroovy", "el maravillosothe groove");
		affectivejj2nnMap.put("culpableguilty", "culpaguilt");
		affectivejj2nnMap.put("felizhappy", "felicidadhappiness");
		affectivejj2nnMap.put("esperanzadohopeful", "esperanzahope");
		affectivejj2nnMap.put("horrorizadohorrified", r("miedofear`horror`angustiadistress`disgustodisgust"));
		affectivejj2nnMap.put("humilladohumiliated", "humillaciónhumiliation");
		affectivejj2nnMap.put("hambrientohungry", "hambrehunger");
		affectivejj2nnMap.put("indecisoindecisive", "indecisiónindecision");
		affectivejj2nnMap.put("indiferenteindifferent", "indiferenciaindifference");
		affectivejj2nnMap.put("inseguroinsecure", "inseguridadinsecurity");
		affectivejj2nnMap.put("interesadointerested", "interesarinterest");
		affectivejj2nnMap.put("invencibleinvincible", "invulnerabilidadinvulnerability");
		affectivejj2nnMap.put("irritadoirritated", "irritaciónirritation");
		affectivejj2nnMap.put("solitariolonely", "soledadloneliness");
		affectivejj2nnMap.put("amorosoloving", "amorlove");
		affectivejj2nnMap.put("suertudolucky", "suerteluck");
		affectivejj2nnMap.put("miserablemiserable", "miseriamisery");
		affectivejj2nnMap.put("nauseabundonauseous", "náuseanausea");
		affectivejj2nnMap.put("nerviosonervous", "ansiedadanxiety");
		affectivejj2nnMap.put("nadanothing", "nadanothingness");
		affectivejj2nnMap.put("obligadoobliged", "obligaciónobligation");
		affectivejj2nnMap.put("raroodd", "rarezastrangeness");
		affectivejj2nnMap.put("satisfechookay", "apatíaapathy");
		affectivejj2nnMap.put("bienok", "apatíaapathy");
		affectivejj2nnMap.put("viejoold", "edadage");
		affectivejj2nnMap.put("pacíficopeaceful", "pazpeace");
		affectivejj2nnMap.put("lamentablepitiful", "lástimapity");
		affectivejj2nnMap.put("satisfechopleased", "placerpleasure");
		affectivejj2nnMap.put("poderosopowerful", "fuertestrength");
		affectivejj2nnMap.put("relajadorelaxed", "calmacalm");
		affectivejj2nnMap.put("tristesad", "tristezasadness");
		affectivejj2nnMap.put("segurosafe", "seguridadsafety");
		affectivejj2nnMap.put("asustadoscared", "miedofear");
		affectivejj2nnMap.put("segurosecure", "seguridadsecurity");
		affectivejj2nnMap.put("sexualsexual", "sexosex");
		affectivejj2nnMap.put("sorprendidoshocked", "sorpresasurprise");
		affectivejj2nnMap.put("enfermosick", "enfermedadsickness");
		affectivejj2nnMap.put("tontosilly", S("necedadsilliness", "frivolidadfrivolity"));
		affectivejj2nnMap.put("arrepentidosorry", "angustiadistress");
		affectivejj2nnMap.put("extrañadostrange", "extrañezastrangeness");
		affectivejj2nnMap.put("fuertestrong", "fuerzastrength");
		affectivejj2nnMap.put("estúpidostupid", S("estupidezstupidity", "ignoranciaignorance"));
		affectivejj2nnMap.put("sorprendidosurprised", "sorpresasurprise");
		affectivejj2nnMap.put("terrible", r("ansiedadanxiety`angustiadistress`vergüenzashame`tristezasadness"));
		affectivejj2nnMap.put("aterrorizadoterrified", "terror");
		affectivejj2nnMap.put("cansadotired", "fatigafatigue");
		affectivejj2nnMap.put("preocupadotroubled", "angustiadistress");
		affectivejj2nnMap.put("misteriosouncanny", "el misteriothe uncanny");
		affectivejj2nnMap.put("inciertouncertain", "incertidumbreuncertainty");
		affectivejj2nnMap.put("incómodouncomfortable", "incomodidaddiscomfort");
		affectivejj2nnMap.put("infelizunhappy", "infelicidadunhappiness");
		affectivejj2nnMap.put("insegurounsure", "inseguridadinsecurity");
		affectivejj2nnMap.put("trastornadoupset", "angustiadistress");
		affectivejj2nnMap.put("vulnerable", "vulnerabilidadvulnerability");
		affectivejj2nnMap.put("raroweird", "extrañezastrangeness");
		affectivejj2nnMap.put("preocupadoworried", "ansiedadanxiety");
		// a little bit of extra variation
		affectivejj2nnMap.put("buenoalright", S("apatíaapathy", "complacenciacomplacency"));
		affectivejj2nnMap.put("increíbleawesome", S("frescocool", "complacenciacomplacency"));
		affectivejj2nnMap.put("desconcertadobewildered", S("desconciertobewilderment", "confusiónconfusion"));
		affectivejj2nnMap.put("relajadocool", S("apatíaapathy", "emociónexcitement"));
		affectivejj2nnMap.put("mareadodizzy", S("mareodizziness", "vértigovertigo"));
		affectivejj2nnMap.put("fabulosofabulous", S("alegríajoy", "gozodelight"));
		affectivejj2nnMap.put("bienfine", S("apatíaapathy", "complacenciacomplacency"));
		affectivejj2nnMap.put("buenogood", S("felicidadhappiness", "alegríajoy"));
		affectivejj2nnMap.put("genialgreat", S("felicidadhappiness", "complacenciacomplacency"));
		affectivejj2nnMap.put("nadanothing", r("empatíaemptiness`nadanothingness`complacenciacomplacency"));
		affectivejj2nnMap.put("extrañoodd", S("el extrañothe uncanny", "extrañezastrangeness"));
		affectivejj2nnMap.put("bienokay", S("apatíaapathy", "complacenciacomplacency"));
		affectivejj2nnMap.put("muy bienok", S("apatíaapathy", "complacenciacomplacency"));
		affectivejj2nnMap.put("relajadorelaxed", r("relajacionrelaxation`calmacalm`frescuracool`pazpeace"));
		affectivejj2nnMap.put("enfermosick", S("enfermedadsickness", "náuseanausea"));
		affectivejj2nnMap.put("perdónsorry", r("angustiadistress`obligaciónobligation`disculpasapologies`deudadebt"));
		affectivejj2nnMap.put("conmocionadoshocked", S("angustiadistress", "sorpresasurprise"));
		affectivejj2nnMap.put("cansadotired", S("fatigafatigue", "cansanciotiredness"));
		affectivejj2nnMap.put("preocupadotroubled", S("angustiadistress", "ansiedadanxiety"));
		affectivejj2nnMap.put("incómodouncomfortable", S("incomodidaddiscomfort", "ansiedadanxiety"));
		affectivejj2nnMap.put("insegurounsure", S("inseguridadinsecurity", "incertidumbreuncertainty"));
		affectivejj2nnMap.put("raroweird", S("extrañezastrangeness", "rarezaweirdness"));
		return affectivejj2nnMap;
	}

	protected HashSet getSpecialThings() {

		specialThings = new HashSet<>();
		specialThings.add("pesadillanightmare");
		specialThings.add("pesadilla retenidanightmare withheld");
		specialThings.add("sueñodream");
		specialThings.add("sueño retenidodream withheld");
		specialThings.add("sentimientofeeling");
		specialThings.add("sentimiento retenidofeeling withheld");
		specialThings.add("hong konghong kong");
		specialThings.add("posesiónpossession");
		specialThings.add("respiraciónbreath");
		specialThings.add("respiración retenidabreath withheld");
		return specialThings;
	}

	protected HashSet getPictureWords() {

		pictureWords = new HashSet<>();
		pictureWords.add("collage");
		pictureWords.add("marcoframe");
		pictureWords.add("imagenimage");
		pictureWords.add("corte de papelpaper cut");
		pictureWords.add("flor de papelpaper flower");
		pictureWords.add("cortedepapelpapercut");
		pictureWords.add("pinturapainting");
		pictureWords.add("imagenpicture");
		return pictureWords;
	}

}
