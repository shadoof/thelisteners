package listeners.model;

import static listeners.model.Constants.attributesManager;
import static listeners.model.Constants.RELATIONSHIP;
import static listeners.util.Utils.randInt;
import static listeners.util.Utils.info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.amazon.ask.attributes.AttributesManager;

import listeners.util.SessionMap;

// keys, slot names, and constant values
// for The Listeners session state
// and session to session persistance
// with some attributes-related methods
// some methods expect the Constants.attributesManager

public class Attributes {

	private static Attributes instance;

	private Attributes(Locale locale) {
		
		sessAttributes = initSessionAttributes();
	}

	private Attributes(Locale locale, AttributesManager attributesManager) {

		persAttributes = attributesManager.getPersistentAttributes();
	}

	public static Attributes getInstance(Locale locale) {

		if (instance == null) instance = new Attributes(locale);
		return instance;
	}

	public static Attributes getInstance(Locale locale, AttributesManager attributesManager) {

		if (instance == null) instance = new Attributes(locale, attributesManager);
		return instance;
	}

	// keys recognizable as static final constants
	public static final String AFFECT = "affect";
	public static final String CHALLENGEDAFFECT = "challengedAffect";
	public static final String FRAGMENTINDEX = "fragmentIndex";
	public static final String FRAGMENTLIST = "faragmentList";
	public static final String GUYZINDEX = "guyzIndex";
	public static final String GUYZIRQ = "guyzIRQ";
	public static final String GUYZSPEECHINDEX = "guyzSpeechIndex";
	public static final String HEARDALLFRAGMENTS = "heardAllFragments";
	public static final String HEARDBREATHAFFECTS = "heardBreathAffects";
	public static final String HEARDNO = "heardNoThanks";
	public static final String HEARDWELCOME = "heardWelcome";
	public static final String LASTINTENT = "lastIntent";
	public static final String LISTENERSAFFECT = "listenersAffect";
	public static final String MARKOVIRQ = "markovIRQ";
	public static final String READSOFAR = "readSoFar";
	public static final String PREVIOUSAFFECT = "previousAffect";
	public static final String THING = "thing";
	public static final String SPEAKGUYZCONFIRMED = "speakGuyzConfirmed";

	// slot names
	public static final String AFFECT_SLOT = "Affect";
	public static final String FRAGMENTNAME_SLOT = "FragmentName";
	public static final String THING_SLOT = "ThingName";

	// attributes objects
	public static Map<String, Object> persAttributes;
	public static SessionMap sessAttributes;
//	public static Map<String, Object> sessAttributes;

	// values
	public static final int NOT_YET_GREETED = -1;
	public static ArrayList LIST_OF_FRAGMENTS = new ArrayList();

	public static SessionMap initSessionAttributes() { // was Map<String, Object>

		SessionMap m = new SessionMap();
		m.justPut(AFFECT, "");
		m.justPut(CHALLENGEDAFFECT, "");
		m.justPut(FRAGMENTINDEX, NOT_YET_GREETED);
		LIST_OF_FRAGMENTS.clear();
		m.justPut(FRAGMENTLIST, LIST_OF_FRAGMENTS);
		m.justPut(GUYZINDEX, 0);
		m.justPut(GUYZIRQ, false);
		m.justPut(GUYZSPEECHINDEX, 1);
		m.justPut(HEARDALLFRAGMENTS, false);
		m.justPut(HEARDBREATHAFFECTS, false);
		m.justPut(HEARDNO, false);
		m.justPut(HEARDWELCOME, true);
		m.justPut(LASTINTENT, "");
		ArrayList al;
		String la = "";
		if (LangConstants.AFFECTS_MAP != null) {
			al = new ArrayList<>(LangConstants.AFFECTS_MAP.keySet());
			la = (String) al.get(randInt(0, al.size() - 1));
		}
		m.justPut(LISTENERSAFFECT, la);
		m.justPut(MARKOVIRQ, false);
		m.justPut(READSOFAR, 0);
		m.justPut(PREVIOUSAFFECT, "");
		m.justPut(THING, "");
		m.justPut(RELATIONSHIP, "sessionStart");
		m.justPut(SPEAKGUYZCONFIRMED, false);
		attributesManager.setSessionAttributes((SessionMap) m);
		return (SessionMap) m;
	}

	public String getRandomAffect() {

		ArrayList al = new ArrayList<>(LangConstants.AFFECTS_MAP.keySet());
		return (String) al.get(randInt(0, al.size() - 1));
	}

	public boolean isEmptyForSession(String key) {

		return sessAttributes == null 
			|| sessAttributes.get(key) == null 
			|| ((String) sessAttributes.get(key)).isEmpty();
	}

	public boolean isPositive(String affect) {

		if (affect == null) return false;
		boolean affectIsPositive = (LangConstants.AFFECTS_MAP.containsKey(affect))
				? LangConstants.AFFECTS_MAP.get(affect)
				: false;
		return affectIsPositive = (LangConstants.SPECIAL_AFFECT_MAP.containsKey(affect))
				? LangConstants.SPECIAL_AFFECT_MAP.get(affect)
				: affectIsPositive;
	}

	public String setAndGetRandomAffectIfEmpty(String affect) {

		if (affect == null || affect.isEmpty()) {
			affect = getRandomAffect();
			sessAttributes.put(AFFECT, affect);
		}		
		return affect;
	}

}
