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

	// keys recognizable as static final constants
	public static final String AFFECT = "affect";
	public static final String FRAGMENTCOUNT = "fragmentCount";
	public static final String FRAGMENTLIST = "faragmentList";
	public static final String GUYZINDEX = "guyzIndex";
	public static final String GUYZIRQ = "guyzIRQ";
	public static final String GUYZSPEECHINDEX = "guyzSpeechIndex";
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
	
	// values
	public static final int NOT_YET_GREETED = -1;
	public static ArrayList LIST_OF_FRAGMENTS = new ArrayList();
	public static HashSet<String> ALL_AFFECTS = buildAffects();

	public static Map<String, Object> initSessionAttributes() {
		Map<String, Object> m = new HashMap();
		m.put(AFFECT, "");
		m.put(FRAGMENTCOUNT, NOT_YET_GREETED);
		LIST_OF_FRAGMENTS.clear();
		m.put(FRAGMENTLIST, LIST_OF_FRAGMENTS);
		m.put(GUYZINDEX, 0);
		m.put(GUYZIRQ, false);
		m.put(GUYZSPEECHINDEX, 1);
		m.put(HEARDBREATHAFFECTS, false);
		m.put(HEARDNO, false);
		m.put(HEARDWELCOME, true);
		m.put(LASTINTENT, "");
		m.put(LISTENERSAFFECT, getRandomAffect());
		m.put(MARKOVIRQ, false);
		m.put(READSOFAR, 0);
		m.put(PREVIOUSAFFECT, "");
		m.put(THING, "");
		m.put(SPEAKGUYZCONFIRMED, false);
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

	public static String getRandomAffect() {

		ArrayList al = new ArrayList<>(AFFECTS_MAP.keySet());
		return (String) al.get(randInt(0, al.size() - 1));
	}
}
