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
		fragmentNumberMap.put("one", 0);
		fragmentNumberMap.put("two", 1);
		fragmentNumberMap.put("three", 2);
		fragmentNumberMap.put("four", 3);
		fragmentNumberMap.put("five", 4);
		fragmentNumberMap.put("six", 5);
		fragmentNumberMap.put("seven", 6);
		fragmentNumberMap.put("eight", 7);
		fragmentNumberMap.put("nine", 8);
		fragmentNumberMap.put("ten", 9);
		return fragmentNumberMap;
	}

	protected HashMap getFragmentNameMap() {

		fragmentNameMap = new HashMap<>();
		fragmentNameMap.put("listening", 0);
		fragmentNameMap.put("pleasure", 0);
		fragmentNameMap.put("liveliness", 0);
		fragmentNameMap.put("within", 1);
		fragmentNameMap.put("enclosures", 1);
		fragmentNameMap.put("art", 1);
		fragmentNameMap.put("artist", 2);
		fragmentNameMap.put("author", 2);
		fragmentNameMap.put("account", 2);
		fragmentNameMap.put("others", 3);
		fragmentNameMap.put("protocol", 3);
		fragmentNameMap.put("terms", 3);
		fragmentNameMap.put("courtesy", 3);
		fragmentNameMap.put("site", 4);
		fragmentNameMap.put("space", 4);
		fragmentNameMap.put("home", 4);
		fragmentNameMap.put("office", 4);
		fragmentNameMap.put("gallery", 4);
		fragmentNameMap.put("concerns", 5);
		fragmentNameMap.put("fears", 5);
		fragmentNameMap.put("aurature", 6);
		fragmentNameMap.put("reading", 6);
		fragmentNameMap.put("verse", 7);
		fragmentNameMap.put("silos", 7);
		fragmentNameMap.put("clouds", 7);
		fragmentNameMap.put("echoes", 7);
		fragmentNameMap.put("caring", 8);
		fragmentNameMap.put("consent", 8);
		fragmentNameMap.put("language", 8);
		fragmentNameMap.put("desire", 9);
		fragmentNameMap.put("transactions", 9);
		fragmentNameMap.put("harvest", 9);
		return fragmentNameMap;
	}

	protected String[] getAffectsArray() {

		// Make sure affects names match
		String s = "happiness excitement curiosity wonder surprise anger ";
		s += "disgust distress sadness grief melancholy fear shame humiliation";
		return s.split(SPC);
	}

	protected HashMap getAffectsMap() {

		affectsMap = new HashMap<>();
		affectsMap.put("happiness", POSITIVE);
		affectsMap.put("excitement", POSITIVE);
		affectsMap.put("curiosity", POSITIVE);
		affectsMap.put("wonder", POSITIVE);
		affectsMap.put("surprise", POSITIVE);
		affectsMap.put("anger", NEGATIVE);
		affectsMap.put("disgust", NEGATIVE);
		affectsMap.put("distress", NEGATIVE);
		affectsMap.put("sadness", NEGATIVE);
		affectsMap.put("grief", NEGATIVE);
		affectsMap.put("melancholy", NEGATIVE);
		affectsMap.put("fear", NEGATIVE);
		affectsMap.put("shame", NEGATIVE);
		affectsMap.put("humiliation", NEGATIVE);
		// we only have to add positive recognized affects to this map
		// special affects’ valences are addressed by that map and
		// otherwise affects are assumed to be negative
		affectsMap.put("apathy", POSITIVE); // deliberate!
		affectsMap.put("calm", POSITIVE);
		affectsMap.put("hope", POSITIVE);
		affectsMap.put("interest", POSITIVE);
		affectsMap.put("invulnerability", POSITIVE);
		affectsMap.put("joy", POSITIVE);
		affectsMap.put("pleasure", POSITIVE);
		affectsMap.put("relaxation", POSITIVE);
		affectsMap.put("safety", POSITIVE);
		affectsMap.put("strength", POSITIVE);
		affectsMap.put("silliness", POSITIVE);
		affectsMap.put("delight", POSITIVE);
		return affectsMap;
	}

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
		affectivejj2nnMap.put("bad", r("anxiety`distress`shame`sadness"));
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
		affectivejj2nnMap.put("hopeful", "hope");
		affectivejj2nnMap.put("horrified", r("fear`horror`distress`disgust"));
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
		affectivejj2nnMap.put("powerful", "strength");
		affectivejj2nnMap.put("relaxed", "calm");
		affectivejj2nnMap.put("sad", "sadness");
		affectivejj2nnMap.put("safe", "safety");
		affectivejj2nnMap.put("scared", "fear");
		affectivejj2nnMap.put("secure", "security");
		affectivejj2nnMap.put("sexual", "sex");
		affectivejj2nnMap.put("shocked", "surprise");
		affectivejj2nnMap.put("sick", "sickness");
		affectivejj2nnMap.put("silly", S("silliness", "frivolity"));
		affectivejj2nnMap.put("sorry", "distress");
		affectivejj2nnMap.put("strange", "strangeness");
		affectivejj2nnMap.put("strong", "strength");
		affectivejj2nnMap.put("stupid", S("stupidity", "ignorance"));
		affectivejj2nnMap.put("surprised", "surprise");
		affectivejj2nnMap.put("terrible", r("anxiety`distress`shame`sadness"));
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
		affectivejj2nnMap.put("nothing", r("emptiness`nothingness`complacency"));
		affectivejj2nnMap.put("odd", S("the uncanny", "strangeness"));
		affectivejj2nnMap.put("okay", S("apathy", "complacency"));
		affectivejj2nnMap.put("ok", S("apathy", "complacency"));
		affectivejj2nnMap.put("relaxed", r("relaxation`calm`cool`peace"));
		affectivejj2nnMap.put("sick", S("sickness", "nausea"));
		affectivejj2nnMap.put("sorry", r("distress`obligation`apologies`debt"));
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
		specialThings.add("nightmare");
		specialThings.add("nightmare withheld");
		specialThings.add("dream");
		specialThings.add("dream withheld");
		specialThings.add("feeling");
		specialThings.add("feeling withheld");
		specialThings.add("hong kong");
		specialThings.add("possession");
		specialThings.add("breath");
		specialThings.add("breath withheld");
		return specialThings;
	}

	protected HashSet getPictureWords() {

		pictureWords = new HashSet<>();
		pictureWords.add("collage");
		pictureWords.add("frame");
		pictureWords.add("image");
		pictureWords.add("paper cut");
		pictureWords.add("paper flower");
		pictureWords.add("papercut");
		pictureWords.add("painting");
		pictureWords.add("picture");
		return pictureWords;
	}

}
