package listeners.model;

import static listeners.model.Attributes.NOT_YET_GREETED;
import static listeners.model.Constants.NUMBER_OF_FRAGMENTS;
import static listeners.model.Constants.HOW_MANY_FRAGMENT_SETS;
import static listeners.model.Constants.locale;
import static listeners.model.Constants.localeTag;
import static listeners.model.Constants.speechUtils;
import static listeners.util.Utils.info;
import static listeners.util.Utils.removeInterSentencePauses;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TimeZone;

import listeners.l10n.Fragments;
import rita.RiMarkov;

public class LangConstants {

	public static Map<String, Integer> FRAGMENTNUMBER_MAP, FRAGMENTNAME_MAP;
	public static String[] AFFECTS_ARRAY;
	public static Map<String, Boolean> AFFECTS_MAP, SPECIAL_AFFECT_MAP;
	public static Map<String, String> AFFECTIVEJJ2NN_MAP;
	public static HashSet<String> SPECIAL_THINGS = new HashSet<>();
	public static HashSet<String> PICTURE_WORDS = new HashSet<>();
	public static HashSet<String> ALL_AFFECTS;

	public static String polyVoiceWrapper;
	
	private static Date date;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("E d MMM, y, h:mm a");
	// private static SimpleDateFormat
	// mdyFormat = new SimpleDateFormat("MMMMMMMMM d, y");
	public static String dateString;

	public static String[] fragments = new String[NUMBER_OF_FRAGMENTS];
	public static RiMarkov rm2, rm3, rm4, rm5;

	private static LangConstants instance;

	public static LangConstants getInstance(Locale locale) {

		if (instance == null) instance = new LangConstants(locale);
		return instance;
	}

	private LangConstants(Locale locale) {

		// ... although Voices are wrapped according to regions
		// TODO put a note in the documentation, ultimately:
		// for use in other English speaking regions:
		// British English, en-gb *text* is the default for this skill:
		Locale.setDefault(new Locale("en", "GB"));

		// localeTag is actually parsed into Constants by LsnrsRequestHandler
		info("@LangConstants, localeTag: " + localeTag);

		final ResourceBundle rb = ResourceBundle.getBundle("listeners.l10n.LangConstantsBundle", locale);
		// ... and now that the bundled language constants are instantiated
		// we make a new assumption with respect to l1on bundle preparation:
		// all languages except German and US English revert to British

		this.polyVoiceWrapper = setPolyVoiceWrappers(localeTag);

		FRAGMENTNUMBER_MAP = (Map<String, Integer>) rb.getObject("fragmentNumberMap");
		FRAGMENTNAME_MAP = (Map<String, Integer>) rb.getObject("fragmentNameMap");
		AFFECTS_ARRAY = (String[]) rb.getObject("affectsArray");
		AFFECTS_MAP = (Map<String, Boolean>) rb.getObject("affectsMap");
		SPECIAL_AFFECT_MAP = (Map<String, Boolean>) rb.getObject("specialAffectMap");
		AFFECTIVEJJ2NN_MAP = (Map<String, String>) rb.getObject("affectiveJJ2NNmap");
		SPECIAL_THINGS = (HashSet<String>) rb.getObject("specialThings");
		PICTURE_WORDS = (HashSet<String>) rb.getObject("pictureWords");

		ALL_AFFECTS = buildAffects();

		date = new Date();
		dateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		this.dateString = dateFormat.format(date);
		// info("@LanguageConstants, dateString: " + dateString);

		buildFragments();
		info("@LangConstants: initialized fragments");

		String markovCorpus = buildMarkovCorpus(HOW_MANY_FRAGMENT_SETS, false);
		// build RiMarkovs(nFactor, try-to-recognize-sentences, don't-allow-duplicate)
		rm2 = new RiMarkov(2, true, false);
		rm2.loadText(markovCorpus);
		rm3 = new RiMarkov(3, true, false);
		rm3.loadText(markovCorpus);
		rm4 = new RiMarkov(4, true, false);
		rm4.loadText(markovCorpus);
		rm5 = new RiMarkov(5, true, false);
		rm5.loadText(markovCorpus);
		info("@LangConstants: initialized Markov models");

	}

	private static HashSet buildAffects() {

		HashSet<String> hs = new HashSet();
		for (int i = 0; i < AFFECTS_ARRAY.length; i++) {
			hs.add(AFFECTS_ARRAY[i]);
		}
		hs.addAll(AFFECTS_MAP.keySet());
		hs.addAll(SPECIAL_AFFECT_MAP.keySet());
		hs.addAll(AFFECTIVEJJ2NN_MAP.values());
		return hs;
	}

	public static void buildFragments() {

		ResourceBundle.clearCache();
		Fragments fs = (Fragments) ResourceBundle.getBundle("listeners.l10n.Fragments", locale);
		for (int fn = 0; fn < NUMBER_OF_FRAGMENTS; fn++) {
			fragments[fn] = fs.getString("fragment" + fn);
		}
	}

	private static String buildMarkovCorpus(int howManyFragmentSets, boolean removePauses) {

		// we could rebuild here with buildFragments();
		// but try using what was actual built on initialization
		// with the idea that the generated sentences will have
		// more commonality with what the interlocutor will have heard
		String markovSupply = "";
		for (int i = 0; i < howManyFragmentSets; i++) {
			for (int f = 0; f < NUMBER_OF_FRAGMENTS; f++) {
				markovSupply += fragments[f].replaceAll("‘", "");
			}
		}
		return removePauses ? removeInterSentencePauses(markovSupply) : markovSupply;
	}

	private static String setPolyVoiceWrappers(String localeTag) {

		switch (localeTag) {
			case "de_DE":
				return "<voice name='Marlene'><lang xml:lang='de-DE'>";
			case "ja_JP":
				return "<voice name='Mizuki'><lang xml:lang='ja-JP'>";
			case "en_IN":
				return "<voice name='Raveena'><lang xml:lang='en-IN'>";
			case "en_AU":
				return "<voice name='Nicole'><lang xml:lang='en-AU'>";
			case "en_US":
			case "en_CA":
				return "<voice name='Joanna'><lang xml:lang='en-US'>";
			default:
				return "<voice name='Amy'><lang xml:lang='en-GB'>";
		}
	}

	public String getNounFromAdjective(String adjective) {

		adjective = adjective.toLowerCase();
		return (AFFECTIVEJJ2NN_MAP.containsKey(adjective)) ? AFFECTIVEJJ2NN_MAP.get(adjective) : adjective;
	}

	public int parseNameToInt(String fragmentName) {

		int theNumber = FRAGMENTNUMBER_MAP.containsKey(fragmentName) ? FRAGMENTNUMBER_MAP.get(fragmentName)
				: NOT_YET_GREETED;
		return FRAGMENTNAME_MAP.containsKey(fragmentName) ? FRAGMENTNAME_MAP.get(fragmentName) : theNumber;
	}

}
