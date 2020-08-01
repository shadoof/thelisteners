package listeners.util;

import java.util.ResourceBundle;
import static listeners.model.Constants.locale;

public class SpeechUtils {

	public SpeechUtils() {

	}
	
	public static ResourceBundle getNewBundle() {
		
		ResourceBundle.clearCache();
		ResourceBundle rb = ResourceBundle.getBundle("listeners.l10n.SpeechUtils", locale);
		return rb;

	}

}
