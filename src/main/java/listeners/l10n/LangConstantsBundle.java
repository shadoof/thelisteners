package listeners.l10n;

import static listeners.model.Constants.NEGATIVE;
import static listeners.model.Constants.POSITIVE;
import static listeners.model.Constants.SPC;
import static listeners.util.Utils.rS;
import static listeners.util.Utils.S;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ListResourceBundle;
import java.util.Map;

public class LangConstantsBundle extends ListResourceBundle {

	protected static Map<String, Integer> FRAGMENTNUMBER_MAP, FRAGMENTNAME_MAP;
	protected static String[] AFFECTS_ARRAY;
	protected static Map<String, Boolean> AFFECTS_MAP, SPECIAL_AFFECT_MAP;
	protected static Map<String, String> AFFECTIVEJJ2NN_MAP;
	protected static HashSet<String> SPECIAL_THINGS;
	protected static HashSet<String> PICTURE_WORDS;

	// public LangConstantsBundle() {
	// super();
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
		FRAGMENTNUMBER_MAP.put("one", 0);
		FRAGMENTNUMBER_MAP.put("two", 1);
		FRAGMENTNUMBER_MAP.put("three", 2);
		FRAGMENTNUMBER_MAP.put("four", 3);
		FRAGMENTNUMBER_MAP.put("five", 4);
		FRAGMENTNUMBER_MAP.put("six", 5);
		FRAGMENTNUMBER_MAP.put("seven", 6);
		FRAGMENTNUMBER_MAP.put("eight", 7);
		FRAGMENTNUMBER_MAP.put("nine", 8);
		FRAGMENTNUMBER_MAP.put("ten", 9);
		//
		FRAGMENTNAME_MAP = new HashMap<>();
		FRAGMENTNAME_MAP.put("listening", 0);
		FRAGMENTNAME_MAP.put("pleasure", 0);
		FRAGMENTNAME_MAP.put("liveliness", 0);
		FRAGMENTNAME_MAP.put("within", 1);
		FRAGMENTNAME_MAP.put("enclosures", 1);
		FRAGMENTNAME_MAP.put("art", 1);
		FRAGMENTNAME_MAP.put("artist", 2);
		FRAGMENTNAME_MAP.put("author", 2);
		FRAGMENTNAME_MAP.put("account", 2);
		FRAGMENTNAME_MAP.put("others", 3);
		FRAGMENTNAME_MAP.put("protocol", 3);
		FRAGMENTNAME_MAP.put("terms", 3);
		FRAGMENTNAME_MAP.put("courtesy", 3);
		FRAGMENTNAME_MAP.put("site", 4);
		FRAGMENTNAME_MAP.put("space", 4);
		FRAGMENTNAME_MAP.put("home", 4);
		FRAGMENTNAME_MAP.put("office", 4);
		FRAGMENTNAME_MAP.put("gallery", 4);
		FRAGMENTNAME_MAP.put("concerns", 5);
		FRAGMENTNAME_MAP.put("fears", 5);
		FRAGMENTNAME_MAP.put("aurature", 6);
		FRAGMENTNAME_MAP.put("reading", 6);
		FRAGMENTNAME_MAP.put("verse", 7);
		FRAGMENTNAME_MAP.put("silos", 7);
		FRAGMENTNAME_MAP.put("clouds", 7);
		FRAGMENTNAME_MAP.put("echoes", 7);
		FRAGMENTNAME_MAP.put("caring", 8);
		FRAGMENTNAME_MAP.put("consent", 8);
		FRAGMENTNAME_MAP.put("language", 8);
		FRAGMENTNAME_MAP.put("desire", 9);
		FRAGMENTNAME_MAP.put("transactions", 9);
		FRAGMENTNAME_MAP.put("harvest", 9);
		//
		// Make sure affects names match
		String[] enTemp = { "happiness", "excitement", "curiosity", "wonder", "surprise", "anger",
				"disgust", "distress", "sadness", "grief", "melancholy", "fear", "shame", "humiliation" };
		AFFECTS_ARRAY = new String[enTemp.length];
		for (int i = 0; i < enTemp.length; i++) {
			AFFECTS_ARRAY[i] = enTemp[i];
		}
		//
		AFFECTS_MAP = new HashMap<>();
		AFFECTS_MAP.put("happiness", POSITIVE);
		AFFECTS_MAP.put("excitement", POSITIVE);
		AFFECTS_MAP.put("curiosity", POSITIVE);
		AFFECTS_MAP.put("wonder", POSITIVE);
		AFFECTS_MAP.put("surprise", POSITIVE);
		AFFECTS_MAP.put("anger", NEGATIVE);
		AFFECTS_MAP.put("disgust", NEGATIVE);
		AFFECTS_MAP.put("distress", NEGATIVE);
		AFFECTS_MAP.put("sadness", NEGATIVE);
		AFFECTS_MAP.put("grief", NEGATIVE);
		AFFECTS_MAP.put("melancholy", NEGATIVE);
		AFFECTS_MAP.put("fear", NEGATIVE);
		AFFECTS_MAP.put("shame", NEGATIVE);
		AFFECTS_MAP.put("humiliation", NEGATIVE);
		// we only have to add positive recognized affects to this map
		// special affects’ valences are addressed by that map and
		// otherwise affects are assumed to be negative
		AFFECTS_MAP.put("apathy", POSITIVE); // deliberate!
		AFFECTS_MAP.put("calm", POSITIVE);
		AFFECTS_MAP.put("hope", POSITIVE);
		AFFECTS_MAP.put("interest", POSITIVE);
		AFFECTS_MAP.put("invulnerability", POSITIVE);
		AFFECTS_MAP.put("joy", POSITIVE);
		AFFECTS_MAP.put("pleasure", POSITIVE);
		AFFECTS_MAP.put("relaxation", POSITIVE);
		AFFECTS_MAP.put("safety", POSITIVE);
		AFFECTS_MAP.put("strength", POSITIVE);
		AFFECTS_MAP.put("silliness", POSITIVE);
		AFFECTS_MAP.put("delight", POSITIVE);
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
		AFFECTIVEJJ2NN_MAP.put("bad", rS("anxiety distress shame sadness".split(SPC)));
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
		AFFECTIVEJJ2NN_MAP.put("hopeful", "hope");
		AFFECTIVEJJ2NN_MAP.put("horrified", rS("fear horror distress disgust".split(SPC)));
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
		AFFECTIVEJJ2NN_MAP.put("powerful", "strength");
		AFFECTIVEJJ2NN_MAP.put("relaxed", "calm");
		AFFECTIVEJJ2NN_MAP.put("sad", "sadness");
		AFFECTIVEJJ2NN_MAP.put("safe", "safety");
		AFFECTIVEJJ2NN_MAP.put("scared", "fear");
		AFFECTIVEJJ2NN_MAP.put("secure", "security");
		AFFECTIVEJJ2NN_MAP.put("sexual", "sex");
		AFFECTIVEJJ2NN_MAP.put("shocked", "surprise");
		AFFECTIVEJJ2NN_MAP.put("sick", "sickness");
		AFFECTIVEJJ2NN_MAP.put("silly", S("silliness", "frivolity"));
		AFFECTIVEJJ2NN_MAP.put("sorry", "distress");
		AFFECTIVEJJ2NN_MAP.put("strange", "strangeness");
		AFFECTIVEJJ2NN_MAP.put("strong", "strength");
		AFFECTIVEJJ2NN_MAP.put("stupid", S("stupidity", "ignorance"));
		AFFECTIVEJJ2NN_MAP.put("surprised", "surprise");
		AFFECTIVEJJ2NN_MAP.put("terrible", rS("anxiety distress shame sadness".split(SPC)));
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
		AFFECTIVEJJ2NN_MAP.put("nothing", rS("emptiness nothingness complacency".split(SPC)));
		AFFECTIVEJJ2NN_MAP.put("odd", S("the uncanny", "strangeness"));
		AFFECTIVEJJ2NN_MAP.put("okay", S("apathy", "complacency"));
		AFFECTIVEJJ2NN_MAP.put("ok", S("apathy", "complacency"));
		AFFECTIVEJJ2NN_MAP.put("relaxed", rS("relaxation calm cool peace".split(SPC)));
		AFFECTIVEJJ2NN_MAP.put("sick", S("sickness", "nausea"));
		AFFECTIVEJJ2NN_MAP.put("sorry", rS("distress obligation apologies debt".split(SPC)));
		AFFECTIVEJJ2NN_MAP.put("shocked", S("distress", "surprise"));
		AFFECTIVEJJ2NN_MAP.put("tired", S("fatigue", "tiredness"));
		AFFECTIVEJJ2NN_MAP.put("troubled", S("distress", "anxiety"));
		AFFECTIVEJJ2NN_MAP.put("uncomfortable", S("discomfort", "anxiety"));
		AFFECTIVEJJ2NN_MAP.put("unsure", S("insecurity", "uncertainty"));
		AFFECTIVEJJ2NN_MAP.put("weird", S("strangeness", "weirdness"));
		//
		SPECIAL_THINGS = new HashSet<>();
		SPECIAL_THINGS.add("nightmare");
		SPECIAL_THINGS.add("nightmare withheld");
		SPECIAL_THINGS.add("dream");
		SPECIAL_THINGS.add("dream withheld");
		SPECIAL_THINGS.add("feeling");
		SPECIAL_THINGS.add("feeling withheld");
		SPECIAL_THINGS.add("hong kong");
		SPECIAL_THINGS.add("possession");
		SPECIAL_THINGS.add("breath");
		SPECIAL_THINGS.add("breath withheld");
		//
		PICTURE_WORDS = new HashSet<>();
		PICTURE_WORDS.add("collage");
		PICTURE_WORDS.add("frame");
		PICTURE_WORDS.add("image");
		PICTURE_WORDS.add("paper cut");
		PICTURE_WORDS.add("paper flower");
		PICTURE_WORDS.add("papercut");
		PICTURE_WORDS.add("painting");
		PICTURE_WORDS.add("picture");

	}

	public Object[][] contents = {

			{ "fragmentNumberMap", FRAGMENTNUMBER_MAP },
			{ "fragmentNameMap", FRAGMENTNAME_MAP },
			{ "affectsArray", AFFECTS_ARRAY },
			{ "affectsMap", AFFECTS_MAP },
			{ "specialAffectMap", SPECIAL_AFFECT_MAP },
			{ "affectiveJJ2NNmap", AFFECTIVEJJ2NN_MAP },
			{ "specialThings", SPECIAL_THINGS },
			{ "pictureWords", PICTURE_WORDS }

	};

	protected Object[][] getContents() {

		return contents;
	}

}
