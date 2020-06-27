package listeners.model;

import static listeners.util.ConstantUtils.info;

import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class LangConstants {

	public static Map<String, Integer> FRAGMENTNUMBER_MAP, FRAGMENTNAME_MAP;
	public static String[] AFFECTS_ARRAY;
	public static Map<String, Boolean> AFFECTS_MAP, SPECIAL_AFFECT_MAP;
	public static Map<String, String> AFFECTIVEJJ2NN_MAP;
	public static HashSet<String> SPECIAL_THINGS = new HashSet<>();
	public static HashSet<String> PICTURE_WORDS = new HashSet<>();

	public LangConstants(Locale locale) {
		info("@LangConstants(): " + locale.toString());
		
		final ResourceBundle rb = ResourceBundle.getBundle("listeners.l10n.LangConstantsBundle", locale);
		
		FRAGMENTNUMBER_MAP = (Map<String, Integer>) rb.getObject("fragmentNumberMap");
		FRAGMENTNAME_MAP = (Map<String, Integer>) rb.getObject("fragmentNameMap");
		AFFECTS_ARRAY = (String[]) rb.getObject("affectsArray");
		AFFECTS_MAP = (Map<String, Boolean>) rb.getObject("affectsMap");
		SPECIAL_AFFECT_MAP = (Map<String, Boolean>) rb.getObject("specialAffectMap");
		AFFECTIVEJJ2NN_MAP = (Map<String, String>) rb.getObject("affectiveJJ2NNmap");
		SPECIAL_THINGS = (HashSet<String>) rb.getObject("specialThings");
		PICTURE_WORDS = (HashSet<String>) rb.getObject("pictureWords");
	}
}
