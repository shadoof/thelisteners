package listeners.util;

import static listeners.util.ConstantUtils.info;

import java.util.Locale;
import java.util.ResourceBundle;

public class SpeechUtils {

	ResourceBundle rb;

	public SpeechUtils(Locale locale) {

		info("@SpeechUtils, locale: " + locale);
		ResourceBundle rb = ResourceBundle.getBundle("listeners.l10n.SpeechUtilsBundle", locale);
		this.rb = rb;
	}
	
	public String chooseContinue() {
		return rb.getString("chooseContinue");
	}

}
