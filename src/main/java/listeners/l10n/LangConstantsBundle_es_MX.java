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
		fragmentNumberMap.put("unoone", 0);
		fragmentNumberMap.put("dostwo", 1);
		fragmentNumberMap.put("tresthree", 2);
		fragmentNumberMap.put("cuatrofour", 3);
		fragmentNumberMap.put("cincofive", 4);
		fragmentNumberMap.put("seissix", 5);
		fragmentNumberMap.put("sieteseven", 6);
		fragmentNumberMap.put("ochoeight", 7);
		fragmentNumberMap.put("nuevenine", 8);
		fragmentNumberMap.put("diezten", 9);
		return fragmentNumberMap;
	}

	protected HashMap getFragmentNameMap() {

		fragmentNameMap = new HashMap<>();
		fragmentNameMap.put("escuchandolistening", 0);
		fragmentNameMap.put("placerpleasure", 0);
		fragmentNameMap.put("vivacidadliveliness", 0);
		fragmentNameMap.put("dentrowithin", 1);
		fragmentNameMap.put("encierrosenclosures", 1);
		fragmentNameMap.put("arteart", 1);
		fragmentNameMap.put("artistaartist", 2);
		fragmentNameMap.put("autorauthor", 2);
		fragmentNameMap.put("cuentaaccount", 2);
		fragmentNameMap.put("otrosothers", 3);
		fragmentNameMap.put("protocoloprotocol", 3);
		fragmentNameMap.put("condicionesterms", 3);
		fragmentNameMap.put("cortesíacourtesy", 3);
		fragmentNameMap.put("sitiosite", 4);
		fragmentNameMap.put("espaciospace", 4);
		fragmentNameMap.put("casahome", 4);
		fragmentNameMap.put("oficinaoffice", 4);
		fragmentNameMap.put("galeríagallery", 4);
		fragmentNameMap.put("preocupacionesconcerns", 5);
		fragmentNameMap.put("miedosfears", 5);
		fragmentNameMap.put("auraturaaurature", 6);
		fragmentNameMap.put("leyendoreading", 6);
		fragmentNameMap.put("versoverse", 7);
		fragmentNameMap.put("silossilos", 7);
		fragmentNameMap.put("nubesclouds", 7);
		fragmentNameMap.put("ecosechoes", 7);
		fragmentNameMap.put("cuidandocaring", 8);
		fragmentNameMap.put("consentimientoconsent", 8);
		fragmentNameMap.put("lenguajelanguage", 8);
		fragmentNameMap.put("deseodesire", 9);
		fragmentNameMap.put("transaccióntransactions", 9);
		fragmentNameMap.put("cosechaharvest", 9);
		return fragmentNameMap;
	}

	protected String[] getAffectsArray() {

		// Make sure affects names match
		String s = "felicidad emoción curiosidad asombro sorpresa enojo happiness excitement curiosity wonder surprise anger ";
		s += "disgusto angustia tristeza dolor melancolía miedo vergüenza humillacióndisgust distress sadness grief melancholy fear shame humiliation";
		return s.split(SPC);
	}

	protected HashMap getAffectsMap() {

		affectsMap = new HashMap<>();
		affectsMap.put("felicidadhappiness", POSITIVE);
		affectsMap.put("emociónexcitement", POSITIVE);
		affectsMap.put("curiosidadcuriosity", POSITIVE);
		affectsMap.put("asombrowonder", POSITIVE);
		affectsMap.put("sorpresasurprise", POSITIVE);
		affectsMap.put("enojoanger", NEGATIVE);
		affectsMap.put("disgustodisgust", NEGATIVE);
		affectsMap.put("angustiadistress", NEGATIVE);
		affectsMap.put("tristezasadness", NEGATIVE);
		affectsMap.put("dolorgrief", NEGATIVE);
		affectsMap.put("melancolíamelancholy", NEGATIVE);
		affectsMap.put("miedofear", NEGATIVE);
		affectsMap.put("vergüenzashame", NEGATIVE);
		affectsMap.put("humillaciónhumiliation", NEGATIVE);
		// we only have to add positive recognized affects to this map
		// special affects’ valences are addressed by that map and
		// otherwise affects are assumed to be negative
		affectsMap.put("apatíaapathy", POSITIVE); // deliberate!
		affectsMap.put("calmacalm", POSITIVE);
		affectsMap.put("esperanzahope", POSITIVE);
		affectsMap.put("interésinterest", POSITIVE);
		affectsMap.put("invulnerabilidadinvulnerability", POSITIVE);
		affectsMap.put("alegríajoy", POSITIVE);
		affectsMap.put("placerpleasure", POSITIVE);
		affectsMap.put("relajaciónrelaxation", POSITIVE);
		affectsMap.put("seguridadsafety", POSITIVE);
		affectsMap.put("fuerzastrength", POSITIVE);
		affectsMap.put("torpezasilliness", POSITIVE);
		affectsMap.put("gozodelight", POSITIVE);
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
