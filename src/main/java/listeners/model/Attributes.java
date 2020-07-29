package listeners.model;

import static listeners.model.Constants.attributesManager;
import static listeners.model.Constants.langConstants;
import static listeners.model.Attributes.AFFECT_SLOT;
import static listeners.util.Utils.randInt;
import static listeners.util.Utils.info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.model.Slot;

import listeners.util.SessionMap;

// keys, slot names, and constant values
// for The Listeners session state
// and session to session persistance
// with some attributes-related methods
// some methods need the Constants.attributesManager

public class Attributes {

	private static Attributes instance;

	private Attributes(Locale locale) {

		sessAttributes = initSessionAttributes();
	}

	// private Attributes(Locale locale, AttributesManager attributesManager) {
	//
	// }
	//
	public static Attributes getInstance(Locale locale) {

		if (instance == null) instance = new Attributes(locale);
		return instance;
	}

	// public static Attributes getInstance(Locale locale, AttributesManager attributesManager) {
	//
	// if (instance == null) instance = new Attributes(locale, attributesManager);
	// return instance;
	// }
	//
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
	public static final String PERSISTENCE = "persistence";
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
	// public static Map<String, Object> sessAttributes;

	// values
	public static final int NOT_YET_GREETED = -1;
	public static ArrayList LIST_OF_FRAGMENTS = new ArrayList();

	public static SessionMap initSessionAttributes() { // was Map<String, Object>

		SessionMap m = new SessionMap();
		m.put(AFFECT, "");
		m.put(CHALLENGEDAFFECT, "");
		m.put(FRAGMENTINDEX, NOT_YET_GREETED);
		LIST_OF_FRAGMENTS.clear();
		m.put(FRAGMENTLIST, LIST_OF_FRAGMENTS);
		m.put(GUYZINDEX, 1);
		m.put(GUYZIRQ, false);
		// m.put(GUYZSPEECHINDEX, 1);
		m.put(HEARDALLFRAGMENTS, false);
		m.put(HEARDBREATHAFFECTS, false);
		m.put(HEARDWELCOME, true); // TODO probably no longer needed
		m.put(LASTINTENT, "");
		ArrayList al;
		String la = "";
		if (LangConstants.AFFECTS_MAP != null) {
			al = new ArrayList<>(LangConstants.AFFECTS_MAP.keySet());
			la = (String) al.get(randInt(0, al.size() - 1));
		}
		m.put(LISTENERSAFFECT, la);
		m.put(MARKOVIRQ, false);
		m.put(PERSISTENCE, "normal");
		m.put(READSOFAR, 0);
		m.put(PREVIOUSAFFECT, "");
		m.put(THING, "");
		return (SessionMap) m;
	}

	public String getRandomAffect() {

		ArrayList al = new ArrayList<>(LangConstants.AFFECTS_MAP.keySet());
		return (String) al.get(randInt(0, al.size() - 1));
	}

	public boolean isEmptyForSession(String key) {

		return sessAttributes == null || sessAttributes.get(key) == null
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

	public String setAndGetRandomAffectIfEmpty(String affectKey) {

		String affect = (String) sessAttributes.get(affectKey);
		if (affect == null || affect.isEmpty()) {
			affect = getRandomAffect();
			sessAttributes.put(affectKey, affect);
		}
		return affect;
	}

}
