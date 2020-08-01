package listeners.l10n;

import static listeners.model.Constants.NEGATIVE;
import static listeners.model.Constants.POSITIVE;
import static listeners.util.Utils.S;

import java.util.HashMap;
import java.util.HashSet;

public class LangConstantsBundle_de_DE extends LangConstantsBundle {

	// public LangConstantsBundle_de_DE() {
	// build();
	// }
	//
	// private void build() {
	static {
		FRAGMENTNUMBER_MAP = new HashMap<>();
		FRAGMENTNUMBER_MAP.put("1", 0);
		FRAGMENTNUMBER_MAP.put("2", 1);
		FRAGMENTNUMBER_MAP.put("3", 2);
		FRAGMENTNUMBER_MAP.put("4", 3);
		FRAGMENTNUMBER_MAP.put("5", 4);
		FRAGMENTNUMBER_MAP.put("6", 5);
		FRAGMENTNUMBER_MAP.put("7", 6);
		FRAGMENTNUMBER_MAP.put("8", 7);
		FRAGMENTNUMBER_MAP.put("9", 8);
		FRAGMENTNUMBER_MAP.put("10", 9);
		//
		FRAGMENTNUMBER_MAP.put("eins", 0);
		FRAGMENTNUMBER_MAP.put("zwei", 1);
		FRAGMENTNUMBER_MAP.put("drei", 2);
		FRAGMENTNUMBER_MAP.put("vier", 3);
		FRAGMENTNUMBER_MAP.put("fünf", 4);
		FRAGMENTNUMBER_MAP.put("sechs", 5);
		FRAGMENTNUMBER_MAP.put("sieben", 6);
		FRAGMENTNUMBER_MAP.put("acht", 7);
		FRAGMENTNUMBER_MAP.put("neun", 8);
		FRAGMENTNUMBER_MAP.put("zehn", 9);
		//
		FRAGMENTNAME_MAP = new HashMap<>();
		FRAGMENTNAME_MAP.put("das Zuhören", 0);
		FRAGMENTNAME_MAP.put("das Vergnügen", 0);
		FRAGMENTNAME_MAP.put("das am-Leben-sein", 0);
		FRAGMENTNAME_MAP.put("innerhalb", 1);
		FRAGMENTNAME_MAP.put("die Einfriedungen", 1);
		FRAGMENTNAME_MAP.put("die Kunst", 1);
		FRAGMENTNAME_MAP.put("der Künstler", 2);
		FRAGMENTNAME_MAP.put("der Autor", 2);
		FRAGMENTNAME_MAP.put("das Konto", 2);
		FRAGMENTNAME_MAP.put("andere", 3);
		FRAGMENTNAME_MAP.put("das Protokoll", 3);
		FRAGMENTNAME_MAP.put("die Bedingungen", 3);
		FRAGMENTNAME_MAP.put("die Höflichkeit", 3);
		FRAGMENTNAME_MAP.put("der Standort", 4);
		FRAGMENTNAME_MAP.put("der Raum", 4);
		FRAGMENTNAME_MAP.put("das Zuhause", 4);
		FRAGMENTNAME_MAP.put("das Büro", 4);
		FRAGMENTNAME_MAP.put("die Galerie", 4);
		FRAGMENTNAME_MAP.put("die Bedenken", 5);
		FRAGMENTNAME_MAP.put("die Ängste", 5);
		FRAGMENTNAME_MAP.put("die Auratur", 6);
		FRAGMENTNAME_MAP.put("das Lesen", 6);
		FRAGMENTNAME_MAP.put("der Vers", 7);
		FRAGMENTNAME_MAP.put("die Silos", 7);
		FRAGMENTNAME_MAP.put("die Wolken", 7);
		FRAGMENTNAME_MAP.put("die Echos", 7);
		FRAGMENTNAME_MAP.put("fürsorglich", 8);
		FRAGMENTNAME_MAP.put("das Einverständnis", 8);
		FRAGMENTNAME_MAP.put("die Sprache", 8);
		FRAGMENTNAME_MAP.put("das Verlangen", 9);
		FRAGMENTNAME_MAP.put("die Geschäfte", 9);
		FRAGMENTNAME_MAP.put("die Ernte", 9);
		//
		// Make sure GERMAN affects names match
		String[] temp = { "das Glück", "die Aufregung", "die Neugier", "das Staunen", "die Überraschung",
				"der Ärger", "der Ekel", "die Bedrängnis", "die Traurigkeit", "die Trauer", "die Melancholie",
				"die Angst", "die Scham", "die Erniedrigung" };
		AFFECTS_ARRAY = new String[temp.length];
		for (int i = 0; i < temp.length; i++) {
			AFFECTS_ARRAY[i] = temp[i];
		}
		//
		AFFECTS_MAP = new HashMap<>();
		AFFECTS_MAP.put("das Glück", POSITIVE);
		AFFECTS_MAP.put("die Aufregung", POSITIVE);
		AFFECTS_MAP.put("die Neugier", POSITIVE);
		AFFECTS_MAP.put("das Staunen", POSITIVE);
		AFFECTS_MAP.put("die Überraschung", POSITIVE);
		AFFECTS_MAP.put("der Ärger", NEGATIVE);
		AFFECTS_MAP.put("der Ekel", NEGATIVE);
		AFFECTS_MAP.put("die Bedrängnis", NEGATIVE);
		AFFECTS_MAP.put("die Traurigkeit", NEGATIVE);
		AFFECTS_MAP.put("die Trauer", NEGATIVE);
		AFFECTS_MAP.put("die Melancholie", NEGATIVE);
		AFFECTS_MAP.put("die Angst", NEGATIVE);
		AFFECTS_MAP.put("die Scham", NEGATIVE);
		AFFECTS_MAP.put("die Erniedrigung", NEGATIVE);
		// we only have to add positive recognized affects to this map
		// special affects’ valences are addressed by that map and
		// otherwise affects are assumed to be negative
		AFFECTS_MAP.put("die Apathie", POSITIVE); // deliberate!
		AFFECTS_MAP.put("ruhig", POSITIVE);
		AFFECTS_MAP.put("das Interesse", POSITIVE);
		AFFECTS_MAP.put("die Unverwundbarkeit", POSITIVE);
		AFFECTS_MAP.put("die Freude", POSITIVE);
		AFFECTS_MAP.put("das Vergnügen", POSITIVE);
		AFFECTS_MAP.put("das Entspannen", POSITIVE);
		AFFECTS_MAP.put("die Sicherheit", POSITIVE);
		AFFECTS_MAP.put("die Wonne", POSITIVE);
		//
		SPECIAL_AFFECT_MAP = new HashMap<>();
		SPECIAL_AFFECT_MAP.put("affection", POSITIVE);
		SPECIAL_AFFECT_MAP.put("anxiety", NEGATIVE);
		SPECIAL_AFFECT_MAP.put("apologies", NEGATIVE);
		SPECIAL_AFFECT_MAP.put("boredom", NEGATIVE);
		SPECIAL_AFFECT_MAP.put("calm", POSITIVE);
		SPECIAL_AFFECT_MAP.put("claustrophobia", NEGATIVE);
		SPECIAL_AFFECT_MAP.put("complacency", NEGATIVE);
		SPECIAL_AFFECT_MAP.put("confusion", NEGATIVE);
		SPECIAL_AFFECT_MAP.put("cool", NEGATIVE);
		SPECIAL_AFFECT_MAP.put("debt", NEGATIVE);
		SPECIAL_AFFECT_MAP.put("fatigue", NEGATIVE);
		SPECIAL_AFFECT_MAP.put("the groove", POSITIVE);
		SPECIAL_AFFECT_MAP.put("guilt", NEGATIVE);
		SPECIAL_AFFECT_MAP.put("hate", NEGATIVE);
		SPECIAL_AFFECT_MAP.put("hatred", NEGATIVE);
		SPECIAL_AFFECT_MAP.put("hunger", NEGATIVE);
		SPECIAL_AFFECT_MAP.put("insecurity", NEGATIVE);
		SPECIAL_AFFECT_MAP.put("loneliness", NEGATIVE);
		SPECIAL_AFFECT_MAP.put("love", POSITIVE);
		SPECIAL_AFFECT_MAP.put("luck", POSITIVE);
		SPECIAL_AFFECT_MAP.put("nausea", NEGATIVE);
		SPECIAL_AFFECT_MAP.put("obligation", NEGATIVE);
		SPECIAL_AFFECT_MAP.put("peace", POSITIVE);
		SPECIAL_AFFECT_MAP.put("questions", POSITIVE);
		SPECIAL_AFFECT_MAP.put("security", POSITIVE);
		SPECIAL_AFFECT_MAP.put("sex", NEGATIVE);
		SPECIAL_AFFECT_MAP.put("strangeness", NEGATIVE);
		SPECIAL_AFFECT_MAP.put("the uncanny", NEGATIVE);
		SPECIAL_AFFECT_MAP.put("vulnerability", POSITIVE);
		//
		AFFECTIVEJJ2NN_MAP = new HashMap<>();
		AFFECTIVEJJ2NN_MAP.put("abject", "abjection");
		AFFECTIVEJJ2NN_MAP.put("affectionate", "affection");
		AFFECTIVEJJ2NN_MAP.put("afraid", "fear");
		AFFECTIVEJJ2NN_MAP.put("alright", "apathy");
		AFFECTIVEJJ2NN_MAP.put("angry", "anger");
		AFFECTIVEJJ2NN_MAP.put("annoyed", "irritation");
		AFFECTIVEJJ2NN_MAP.put("anxious", "anxiety");
		AFFECTIVEJJ2NN_MAP.put("apathetic", "apathy");
		AFFECTIVEJJ2NN_MAP.put("apologetic", "apologies");
		AFFECTIVEJJ2NN_MAP.put("ashamed", "shame");
		AFFECTIVEJJ2NN_MAP.put("awesome", "cool");
		AFFECTIVEJJ2NN_MAP.put("bewildered", "bewilderment");
		AFFECTIVEJJ2NN_MAP.put("bored", "boredom");
		AFFECTIVEJJ2NN_MAP.put("claustrophobic", "claustrophobia");
		AFFECTIVEJJ2NN_MAP.put("complacent", "complacency");
		AFFECTIVEJJ2NN_MAP.put("confused", "confusion");
		AFFECTIVEJJ2NN_MAP.put("cool", "apathy");
		AFFECTIVEJJ2NN_MAP.put("curious", "curiosity");
		AFFECTIVEJJ2NN_MAP.put("delighted", "delight");
		AFFECTIVEJJ2NN_MAP.put("disappointed", "disappointment");
		AFFECTIVEJJ2NN_MAP.put("depressed", "depression");
		AFFECTIVEJJ2NN_MAP.put("disgusted", "disgust");
		AFFECTIVEJJ2NN_MAP.put("distressed", "distress");
		AFFECTIVEJJ2NN_MAP.put("disturbed", "discomfort");
		AFFECTIVEJJ2NN_MAP.put("dizzy", "dizziness");
		AFFECTIVEJJ2NN_MAP.put("empty", "emptiness");
		AFFECTIVEJJ2NN_MAP.put("excited", "excitement");
		AFFECTIVEJJ2NN_MAP.put("fabulous", "joy");
		AFFECTIVEJJ2NN_MAP.put("fine", "apathy");
		AFFECTIVEJJ2NN_MAP.put("frightened", "fear");
		AFFECTIVEJJ2NN_MAP.put("furious", "fury");
		AFFECTIVEJJ2NN_MAP.put("good", "happiness");
		AFFECTIVEJJ2NN_MAP.put("great", "happiness");
		AFFECTIVEJJ2NN_MAP.put("groovy", "the groove");
		AFFECTIVEJJ2NN_MAP.put("guilty", "guilt");
		AFFECTIVEJJ2NN_MAP.put("happy", "happiness");
		AFFECTIVEJJ2NN_MAP.put("humiliated", "humiliation");
		AFFECTIVEJJ2NN_MAP.put("hungry", "hunger");
		AFFECTIVEJJ2NN_MAP.put("indecisive", "indecision");
		AFFECTIVEJJ2NN_MAP.put("indifferent", "indifference");
		AFFECTIVEJJ2NN_MAP.put("insecure", "insecurity");
		AFFECTIVEJJ2NN_MAP.put("interested", "interest");
		AFFECTIVEJJ2NN_MAP.put("invincible", "invulnerability");
		AFFECTIVEJJ2NN_MAP.put("irritated", "irritation");
		AFFECTIVEJJ2NN_MAP.put("lonely", "loneliness");
		AFFECTIVEJJ2NN_MAP.put("loving", "love");
		AFFECTIVEJJ2NN_MAP.put("lucky", "luck");
		AFFECTIVEJJ2NN_MAP.put("miserable", "misery");
		AFFECTIVEJJ2NN_MAP.put("nauseous", "nausea");
		AFFECTIVEJJ2NN_MAP.put("nervous", "anxiety");
		AFFECTIVEJJ2NN_MAP.put("nothing", "nothingness");
		AFFECTIVEJJ2NN_MAP.put("obliged", "obligation");
		AFFECTIVEJJ2NN_MAP.put("odd", "strangeness");
		AFFECTIVEJJ2NN_MAP.put("okay", "apathy");
		AFFECTIVEJJ2NN_MAP.put("ok", "apathy");
		AFFECTIVEJJ2NN_MAP.put("old", "age");
		AFFECTIVEJJ2NN_MAP.put("peaceful", "peace");
		AFFECTIVEJJ2NN_MAP.put("pitiful", "pity");
		AFFECTIVEJJ2NN_MAP.put("pleased", "pleasure");
		AFFECTIVEJJ2NN_MAP.put("relaxed", "calm");
		AFFECTIVEJJ2NN_MAP.put("sad", "sadness");
		AFFECTIVEJJ2NN_MAP.put("safe", "safety");
		AFFECTIVEJJ2NN_MAP.put("scared", "fear");
		AFFECTIVEJJ2NN_MAP.put("secure", "security");
		AFFECTIVEJJ2NN_MAP.put("sexual", "sex");
		AFFECTIVEJJ2NN_MAP.put("shocked", "surprise");
		AFFECTIVEJJ2NN_MAP.put("sick", "sickness");
		AFFECTIVEJJ2NN_MAP.put("sorry", "distress");
		AFFECTIVEJJ2NN_MAP.put("strange", "strangeness");
		AFFECTIVEJJ2NN_MAP.put("surprised", "surprise");
		AFFECTIVEJJ2NN_MAP.put("terrified", "terror");
		AFFECTIVEJJ2NN_MAP.put("tired", "fatigue");
		AFFECTIVEJJ2NN_MAP.put("troubled", "distress");
		AFFECTIVEJJ2NN_MAP.put("uncanny", "the uncanny");
		AFFECTIVEJJ2NN_MAP.put("uncertain", "uncertainty");
		AFFECTIVEJJ2NN_MAP.put("uncomfortable", "discomfort");
		AFFECTIVEJJ2NN_MAP.put("unhappy", "unhappiness");
		AFFECTIVEJJ2NN_MAP.put("unsure", "insecurity");
		AFFECTIVEJJ2NN_MAP.put("upset", "distress");
		AFFECTIVEJJ2NN_MAP.put("vulnerable", "vulnerability");
		AFFECTIVEJJ2NN_MAP.put("weird", "strangeness");
		AFFECTIVEJJ2NN_MAP.put("worried", "anxiety");
		// a little bit of extra variation
		AFFECTIVEJJ2NN_MAP.put("alright", S("apathy", "complacency"));
		AFFECTIVEJJ2NN_MAP.put("awesome", S("cool", "complacency"));
		AFFECTIVEJJ2NN_MAP.put("bewildered", S("bewilderment", "confusion"));
		AFFECTIVEJJ2NN_MAP.put("cool", S("apathy", "excitement"));
		AFFECTIVEJJ2NN_MAP.put("dizzy", S("dizziness", "vertigo"));
		AFFECTIVEJJ2NN_MAP.put("fabulous", S("joy", "delight"));
		AFFECTIVEJJ2NN_MAP.put("fine", S("apathy", "complacency"));
		AFFECTIVEJJ2NN_MAP.put("good", S("happiness", "joy"));
		AFFECTIVEJJ2NN_MAP.put("great", S("happiness", "complacency"));
		AFFECTIVEJJ2NN_MAP.put("nothing", S(S("emptiness", "nothingness"), "complacency"));
		AFFECTIVEJJ2NN_MAP.put("odd", S("the uncanny", "strangeness"));
		AFFECTIVEJJ2NN_MAP.put("okay", S("apathy", "complacency"));
		AFFECTIVEJJ2NN_MAP.put("ok", S("apathy", "complacency"));
		AFFECTIVEJJ2NN_MAP.put("relaxed", S("relaxation", S("calm", "cool")));
		AFFECTIVEJJ2NN_MAP.put("sick", S("sickness", "nausea"));
		AFFECTIVEJJ2NN_MAP.put("sorry", S("distress", S("obligation", S("apologies", "debt"))));
		AFFECTIVEJJ2NN_MAP.put("shocked", S("distress", "surprise"));
		AFFECTIVEJJ2NN_MAP.put("tired", S("fatigue", "tiredness"));
		AFFECTIVEJJ2NN_MAP.put("troubled", S("distress", "anxiety"));
		AFFECTIVEJJ2NN_MAP.put("uncomfortable", S("discomfort", "anxiety"));
		AFFECTIVEJJ2NN_MAP.put("unsure", S("insecurity", "uncertainty"));
		AFFECTIVEJJ2NN_MAP.put("weird", S("strangeness", "weirdness"));
		//
		SPECIAL_THINGS = new HashSet<>();
		SPECIAL_THINGS.add("der Alptraum");
		SPECIAL_THINGS.add("der zurückgehaltene Alptraum");
		SPECIAL_THINGS.add("der Traum");
		SPECIAL_THINGS.add("der zurückgehaltene Traum");
		SPECIAL_THINGS.add("das Gefühl");
		SPECIAL_THINGS.add("das zurückgehaltene Gefühl");
		SPECIAL_THINGS.add("hong kong");
		SPECIAL_THINGS.add("der Besitz");
		SPECIAL_THINGS.add("der Atem");
		SPECIAL_THINGS.add("der angehaltene Atem");
		//
		PICTURE_WORDS = new HashSet<>();
		PICTURE_WORDS.add("die Collage");
		PICTURE_WORDS.add("der Rahmen");
		PICTURE_WORDS.add("das Bild");
		PICTURE_WORDS.add("die Papierblume");
	}

}
