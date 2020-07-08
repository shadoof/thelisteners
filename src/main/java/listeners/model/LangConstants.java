package listeners.model;

import static listeners.model.Constants.localeTag;
import static listeners.model.LangConstants.AFFECTIVEJJ2NN_MAP;
import static listeners.model.LangConstants.AFFECTS_ARRAY;
import static listeners.model.LangConstants.AFFECTS_MAP;
import static listeners.model.LangConstants.SPECIAL_AFFECT_MAP;
import static listeners.util.ConstantUtils.info;
import listeners.util.SpeechUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TimeZone;

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
	
	private static LangConstants instance;
	
	public static LangConstants getInstance(Locale locale) {
		if (instance == null)
			return new LangConstants(locale);
		else
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
	
	private String setPolyVoiceWrappers(String localeTag) {

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
}
