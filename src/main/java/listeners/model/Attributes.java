package listeners.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.amazon.ask.attributes.AttributesManager;

import static listeners.model.LangConstants.AFFECTS_ARRAY;
import static listeners.model.LangConstants.AFFECTS_MAP;
import static listeners.model.LangConstants.AFFECTIVEJJ2NN_MAP;
import static listeners.model.LangConstants.SPECIAL_AFFECT_MAP;
import static listeners.util.ConstantUtils.randInt;

// keys, slot names, and constant values for The Listeners session state
// and session to session persistance

public final class Attributes {
	
	// manager
	public static AttributesManager AMANAGER;

	// keys
	public static final String AFFECT_KEY = "affect";
	public static final String FRAGMENTCOUNT_KEY = "fragmentCount";
	public static final String FRAGMENTLIST_KEY = "faragmentList";
	public static final String GUYZINDEX_KEY = "guyzIndex";
	public static final String GUYZIRQ_KEY = "guyzIRQ";
	public static final String HEARDBREATHAFFECTS_KEY = "heardBreathAffects";
	public static final String HEARDNO_KEY = "heardNoThanks";
	public static final String HEARDWELCOME_KEY = "heardWelcome";
	public static final String LASTINTENT_KEY = "lastIntent";
	public static final String LISTENERSAFFECT_KEY = "listenersAffect";
	public static final String MARKOVIRQ_KEY = "markovIRQ";
	public static final String READSOFAR_KEY = "readSoFar";
	public static final String PREVIOUSAFFECT_KEY = "previousAffect";
	public static final String THING_KEY = "thing";
	public static final String SPEAKGUYZCONFIRMED_KEY = "speakGuyzConfirmed";
	
	// slot names
	public static final String AFFECT_SLOT = "Affect";
	public static final String FRAGMENTNAME_SLOT = "FragmentName";
	public static final String THING_SLOT = "ThingName";
	
	// values
	public static final int NOT_YET_GREETED = -1;
	public static ArrayList FRAGMENTLIST = new ArrayList();
	public static HashSet<String> ALL_AFFECTS = buildAffects();

	public static Map<String, Object> initSessionAttributes() {
		Map<String, Object> m = new HashMap();
		m.put(AFFECT_KEY, "");
		m.put(FRAGMENTCOUNT_KEY, NOT_YET_GREETED);
		FRAGMENTLIST.clear();
		m.put(FRAGMENTLIST_KEY, FRAGMENTLIST);
		m.put(GUYZINDEX_KEY, 0);
		m.put(GUYZIRQ_KEY, false);
		m.put(HEARDBREATHAFFECTS_KEY, false);
		m.put(HEARDNO_KEY, false);
		m.put(HEARDWELCOME_KEY, true);
		m.put(LASTINTENT_KEY, "");
		m.put(LISTENERSAFFECT_KEY, setRandomListenersAffect());
		m.put(MARKOVIRQ_KEY, false);
		m.put(READSOFAR_KEY, 0);
		m.put(PREVIOUSAFFECT_KEY, "");
		m.put(THING_KEY, "");
		m.put(SPEAKGUYZCONFIRMED_KEY, false);
		return m;
	}

	private static HashSet buildAffects() {
		
		// must be done *after* LangConstants are initialized
		HashSet<String> hs = new HashSet();
		for (int i = 0; i < AFFECTS_ARRAY.length; i++) {
			hs.add(AFFECTS_ARRAY[i]);
		}
		hs.addAll(AFFECTS_MAP.keySet());
		hs.addAll(SPECIAL_AFFECT_MAP.keySet());
		hs.addAll(AFFECTIVEJJ2NN_MAP.values());
		return hs;
	}

	private static String setRandomListenersAffect() {

		ArrayList al = new ArrayList<>(AFFECTS_MAP.keySet());
		return (String) al.get(randInt(0, al.size() - 1));
	}
}
