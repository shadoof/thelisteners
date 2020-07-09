package listeners.util;

import static listeners.util.ConstantUtils.info;

import java.util.Locale;
import java.util.ResourceBundle;

public class SpeechUtils {

	ResourceBundle rb;

	private SpeechUtils(Locale locale) {

		// info("@SpeechUtils, getting another bundle for locale: " + locale);
		ResourceBundle rb = ResourceBundle.getBundle("listeners.l10n.SpeechUtilsBundle", locale);
		this.rb = rb;
	}

	public static SpeechUtils getInstance(Locale locale) {

		return new SpeechUtils(locale);
	}

	public String chooseContinue() {

		return rb.getString("chooseContinue");
	}

	public String chooseContinue(boolean b) {

		return b ? chooseContinue() : rb.getString("chooseContinueNoAffect");
	}

	public String chooseSpeechAssistance() {

		return rb.getString("chooseSpeechAssistance");
	}

	public String chooseUnsureAboutAffect() {

		return rb.getString("chooseUnsureAboutAffect");
	}

	public String getAbandonmentMessage() {

		return rb.getString("getAbandonmentMessage");
	}

}
