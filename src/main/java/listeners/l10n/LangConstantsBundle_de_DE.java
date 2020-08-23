package listeners.l10n;

import static listeners.model.Constants.GRV;
import static listeners.model.Constants.NEGATIVE;
import static listeners.model.Constants.POSITIVE;
import static listeners.util.Utils.S;

import java.util.HashMap;
import java.util.HashSet;

public class LangConstantsBundle_de_DE extends LangConstantsBundle {

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
		fragmentNumberMap.put("eins", 0);
		fragmentNumberMap.put("zwei", 1);
		fragmentNumberMap.put("drei", 2);
		fragmentNumberMap.put("vier", 3);
		fragmentNumberMap.put("fünf", 4);
		fragmentNumberMap.put("sechs", 5);
		fragmentNumberMap.put("sieben", 6);
		fragmentNumberMap.put("acht", 7);
		fragmentNumberMap.put("neun", 8);
		fragmentNumberMap.put("zehn", 9);
		return fragmentNumberMap;
	}

	protected HashMap getFragmentNameMap() {

		fragmentNameMap = new HashMap<>();
		fragmentNameMap.put("das Zuhören", 0);
		fragmentNameMap.put("das Vergnügen", 0);
		fragmentNameMap.put("das am-Leben-sein", 0);
		fragmentNameMap.put("innerhalb", 1);
		fragmentNameMap.put("die Einfriedungen", 1);
		fragmentNameMap.put("die Kunst", 1);
		fragmentNameMap.put("der Künstler", 2);
		fragmentNameMap.put("der Autor", 2);
		fragmentNameMap.put("das Konto", 2);
		fragmentNameMap.put("andere", 3);
		fragmentNameMap.put("das Protokoll", 3);
		fragmentNameMap.put("die Bedingungen", 3);
		fragmentNameMap.put("die Höflichkeit", 3);
		fragmentNameMap.put("der Standort", 4);
		fragmentNameMap.put("der Raum", 4);
		fragmentNameMap.put("das Zuhause", 4);
		fragmentNameMap.put("das Büro", 4);
		fragmentNameMap.put("die Galerie", 4);
		fragmentNameMap.put("die Bedenken", 5);
		fragmentNameMap.put("die Ängste", 5);
		fragmentNameMap.put("die Auratur", 6);
		fragmentNameMap.put("das Lesen", 6);
		fragmentNameMap.put("der Vers", 7);
		fragmentNameMap.put("die Silos", 7);
		fragmentNameMap.put("die Wolken", 7);
		fragmentNameMap.put("die Echos", 7);
		fragmentNameMap.put("fürsorglich", 8);
		fragmentNameMap.put("das Einverständnis", 8);
		fragmentNameMap.put("die Sprache", 8);
		fragmentNameMap.put("das Verlangen", 9);
		fragmentNameMap.put("die Geschäfte", 9);
		fragmentNameMap.put("die Ernte", 9);
		return fragmentNameMap;
	}

	protected String[] getAffectsArray() {

		// Make sure GERMAN affects names match
		String s = "das Glück`die Aufregung`die Neugier`das Staunen`die Überraschung`der Ärger`der Ekel`";
		s += "die Bedrängnis`die Traurigkeit`die Trauer`die Melancholie`die Angst`die Scham`die Erniedrigung";
		return s.split(GRV);
	}

	protected HashMap getAffectsMap() {

		affectsMap = new HashMap<>();
		affectsMap.put("das Glück", POSITIVE);
		affectsMap.put("die Aufregung", POSITIVE);
		affectsMap.put("die Neugier", POSITIVE);
		affectsMap.put("das Staunen", POSITIVE);
		affectsMap.put("die Überraschung", POSITIVE);
		affectsMap.put("der Ärger", NEGATIVE);
		affectsMap.put("der Ekel", NEGATIVE);
		affectsMap.put("die Bedrängnis", NEGATIVE);
		affectsMap.put("die Traurigkeit", NEGATIVE);
		affectsMap.put("die Trauer", NEGATIVE);
		affectsMap.put("die Melancholie", NEGATIVE);
		affectsMap.put("die Angst", NEGATIVE);
		affectsMap.put("die Scham", NEGATIVE);
		affectsMap.put("die Erniedrigung", NEGATIVE);
		// we only have to add positive recognized affects to this map
		// special affects’ valences are addressed by that map and
		// otherwise affects are assumed to be negative
		affectsMap.put("die Apathie", POSITIVE); // deliberate!
		affectsMap.put("ruhig", POSITIVE);
		affectsMap.put("das Interesse", POSITIVE);
		affectsMap.put("die Unverwundbarkeit", POSITIVE);
		affectsMap.put("die Freude", POSITIVE);
		affectsMap.put("das Vergnügen", POSITIVE);
		affectsMap.put("das Entspannen", POSITIVE);
		affectsMap.put("die Sicherheit", POSITIVE);
		affectsMap.put("die Wonne", POSITIVE);
		return affectsMap;
	}

	// TODO
	protected HashMap getSpecialAffectsMap() {

		specialAffectsMap = new HashMap<>();
		specialAffectsMap.put("affection", POSITIVE);
		specialAffectsMap.put("anxiety", NEGATIVE);
		specialAffectsMap.put("apologies", NEGATIVE);
		specialAffectsMap.put("boredom", NEGATIVE);
		specialAffectsMap.put("calm", POSITIVE);
		specialAffectsMap.put("claustrophobia", NEGATIVE);
		specialAffectsMap.put("complacency", NEGATIVE);
		specialAffectsMap.put("confusion", NEGATIVE);
		specialAffectsMap.put("cool", NEGATIVE);
		specialAffectsMap.put("debt", NEGATIVE);
		specialAffectsMap.put("fatigue", NEGATIVE);
		specialAffectsMap.put("the groove", POSITIVE);
		specialAffectsMap.put("guilt", NEGATIVE);
		specialAffectsMap.put("hate", NEGATIVE);
		specialAffectsMap.put("hatred", NEGATIVE);
		specialAffectsMap.put("hunger", NEGATIVE);
		specialAffectsMap.put("insecurity", NEGATIVE);
		specialAffectsMap.put("loneliness", NEGATIVE);
		specialAffectsMap.put("love", POSITIVE);
		specialAffectsMap.put("luck", POSITIVE);
		specialAffectsMap.put("nausea", NEGATIVE);
		specialAffectsMap.put("obligation", NEGATIVE);
		specialAffectsMap.put("peace", POSITIVE);
		specialAffectsMap.put("questions", POSITIVE);
		specialAffectsMap.put("security", POSITIVE);
		specialAffectsMap.put("sex", NEGATIVE);
		specialAffectsMap.put("strangeness", NEGATIVE);
		specialAffectsMap.put("the uncanny", NEGATIVE);
		specialAffectsMap.put("vulnerability", POSITIVE);
		return specialAffectsMap;
	}

	// TODO
	protected HashMap getAffectivejj2nnMap() {

		affectivejj2nnMap = new HashMap<>();
		affectivejj2nnMap.put("abject", "abjection");
		affectivejj2nnMap.put("affectionate", "affection");
		affectivejj2nnMap.put("afraid", "fear");
		affectivejj2nnMap.put("alright", "apathy");
		affectivejj2nnMap.put("angry", "anger");
		affectivejj2nnMap.put("annoyed", "irritation");
		affectivejj2nnMap.put("anxious", "anxiety");
		affectivejj2nnMap.put("apathetic", "apathy");
		affectivejj2nnMap.put("apologetic", "apologies");
		affectivejj2nnMap.put("ashamed", "shame");
		affectivejj2nnMap.put("awesome", "cool");
		affectivejj2nnMap.put("bewildered", "bewilderment");
		affectivejj2nnMap.put("bored", "boredom");
		affectivejj2nnMap.put("claustrophobic", "claustrophobia");
		affectivejj2nnMap.put("complacent", "complacency");
		affectivejj2nnMap.put("confused", "confusion");
		affectivejj2nnMap.put("cool", "apathy");
		affectivejj2nnMap.put("curious", "curiosity");
		affectivejj2nnMap.put("delighted", "delight");
		affectivejj2nnMap.put("disappointed", "disappointment");
		affectivejj2nnMap.put("depressed", "depression");
		affectivejj2nnMap.put("disgusted", "disgust");
		affectivejj2nnMap.put("distressed", "distress");
		affectivejj2nnMap.put("disturbed", "discomfort");
		affectivejj2nnMap.put("dizzy", "dizziness");
		affectivejj2nnMap.put("empty", "emptiness");
		affectivejj2nnMap.put("excited", "excitement");
		affectivejj2nnMap.put("fabulous", "joy");
		affectivejj2nnMap.put("fine", "apathy");
		affectivejj2nnMap.put("frightened", "fear");
		affectivejj2nnMap.put("furious", "fury");
		affectivejj2nnMap.put("good", "happiness");
		affectivejj2nnMap.put("great", "happiness");
		affectivejj2nnMap.put("groovy", "the groove");
		affectivejj2nnMap.put("guilty", "guilt");
		affectivejj2nnMap.put("happy", "happiness");
		affectivejj2nnMap.put("humiliated", "humiliation");
		affectivejj2nnMap.put("hungry", "hunger");
		affectivejj2nnMap.put("indecisive", "indecision");
		affectivejj2nnMap.put("indifferent", "indifference");
		affectivejj2nnMap.put("insecure", "insecurity");
		affectivejj2nnMap.put("interested", "interest");
		affectivejj2nnMap.put("invincible", "invulnerability");
		affectivejj2nnMap.put("irritated", "irritation");
		affectivejj2nnMap.put("lonely", "loneliness");
		affectivejj2nnMap.put("loving", "love");
		affectivejj2nnMap.put("lucky", "luck");
		affectivejj2nnMap.put("miserable", "misery");
		affectivejj2nnMap.put("nauseous", "nausea");
		affectivejj2nnMap.put("nervous", "anxiety");
		affectivejj2nnMap.put("nothing", "nothingness");
		affectivejj2nnMap.put("obliged", "obligation");
		affectivejj2nnMap.put("odd", "strangeness");
		affectivejj2nnMap.put("okay", "apathy");
		affectivejj2nnMap.put("ok", "apathy");
		affectivejj2nnMap.put("old", "age");
		affectivejj2nnMap.put("peaceful", "peace");
		affectivejj2nnMap.put("pitiful", "pity");
		affectivejj2nnMap.put("pleased", "pleasure");
		affectivejj2nnMap.put("relaxed", "calm");
		affectivejj2nnMap.put("sad", "sadness");
		affectivejj2nnMap.put("safe", "safety");
		affectivejj2nnMap.put("scared", "fear");
		affectivejj2nnMap.put("secure", "security");
		affectivejj2nnMap.put("sexual", "sex");
		affectivejj2nnMap.put("shocked", "surprise");
		affectivejj2nnMap.put("sick", "sickness");
		affectivejj2nnMap.put("sorry", "distress");
		affectivejj2nnMap.put("strange", "strangeness");
		affectivejj2nnMap.put("surprised", "surprise");
		affectivejj2nnMap.put("terrified", "terror");
		affectivejj2nnMap.put("tired", "fatigue");
		affectivejj2nnMap.put("troubled", "distress");
		affectivejj2nnMap.put("uncanny", "the uncanny");
		affectivejj2nnMap.put("uncertain", "uncertainty");
		affectivejj2nnMap.put("uncomfortable", "discomfort");
		affectivejj2nnMap.put("unhappy", "unhappiness");
		affectivejj2nnMap.put("unsure", "insecurity");
		affectivejj2nnMap.put("upset", "distress");
		affectivejj2nnMap.put("vulnerable", "vulnerability");
		affectivejj2nnMap.put("weird", "strangeness");
		affectivejj2nnMap.put("worried", "anxiety");
		// a little bit of extra variation
		affectivejj2nnMap.put("alright", S("apathy", "complacency"));
		affectivejj2nnMap.put("awesome", S("cool", "complacency"));
		affectivejj2nnMap.put("bewildered", S("bewilderment", "confusion"));
		affectivejj2nnMap.put("cool", S("apathy", "excitement"));
		affectivejj2nnMap.put("dizzy", S("dizziness", "vertigo"));
		affectivejj2nnMap.put("fabulous", S("joy", "delight"));
		affectivejj2nnMap.put("fine", S("apathy", "complacency"));
		affectivejj2nnMap.put("good", S("happiness", "joy"));
		affectivejj2nnMap.put("great", S("happiness", "complacency"));
		affectivejj2nnMap.put("nothing", S(S("emptiness", "nothingness"), "complacency"));
		affectivejj2nnMap.put("odd", S("the uncanny", "strangeness"));
		affectivejj2nnMap.put("okay", S("apathy", "complacency"));
		affectivejj2nnMap.put("ok", S("apathy", "complacency"));
		affectivejj2nnMap.put("relaxed", S("relaxation", S("calm", "cool")));
		affectivejj2nnMap.put("sick", S("sickness", "nausea"));
		affectivejj2nnMap.put("sorry", S("distress", S("obligation", S("apologies", "debt"))));
		affectivejj2nnMap.put("shocked", S("distress", "surprise"));
		affectivejj2nnMap.put("tired", S("fatigue", "tiredness"));
		affectivejj2nnMap.put("troubled", S("distress", "anxiety"));
		affectivejj2nnMap.put("uncomfortable", S("discomfort", "anxiety"));
		affectivejj2nnMap.put("unsure", S("insecurity", "uncertainty"));
		affectivejj2nnMap.put("weird", S("strangeness", "weirdness"));
		return affectivejj2nnMap;
	}

	protected HashSet getSpecialThings() {

		specialThings = new HashSet<>();
		specialThings.add("der Alptraum");
		specialThings.add("der zurückgehaltene Alptraum");
		specialThings.add("der Traum");
		specialThings.add("der zurückgehaltene Traum");
		specialThings.add("das Gefühl");
		specialThings.add("das zurückgehaltene Gefühl");
		specialThings.add("hong kong");
		specialThings.add("der Besitz");
		specialThings.add("der Atem");
		specialThings.add("der angehaltene Atem");
		return specialThings;
	}

	protected HashSet getPictureWords() {

		pictureWords = new HashSet<>();
		pictureWords.add("die Collage");
		pictureWords.add("der Rahmen");
		pictureWords.add("das Bild");
		pictureWords.add("die Papierblume");
		return pictureWords;
	}

}
