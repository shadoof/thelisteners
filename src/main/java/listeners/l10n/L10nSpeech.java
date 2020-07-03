package listeners.l10n;

import static listeners.model.LangConstants.localeTag;
import static listeners.util.ConstantUtils.S;

import java.util.ListResourceBundle;
import java.util.MissingResourceException;

public abstract class L10nSpeech extends ListResourceBundle implements ResponseSpeech {

	public Object[][] contents;

	public String getCardTitle() {

		String ct;
		try {
			ct = this.getString("cardTitle");
		}
		catch (Exception e) {
			if (!(e instanceof MissingResourceException)) {
				e.printStackTrace();
			}
			ct = null;
		}
		if (ct == null) {
			ct = "de_DE".equals(localeTag) ? S("Hören", "Höre immer noch zu") : S("Still, l", "L") + "istening";
		}

		return ct;
	}

	@Override
	protected Object[][] getContents() {

		return contents;
	}

	public String getPostSpeechPrompt() {

		try {
			String pst = this.getString("getPostSpeechPrompt");
		}
		catch (Exception e) {
			if (!(e instanceof MissingResourceException)) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public String getReprompt() {

		try {
			String pst = this.getString("reprompt");
		}
		catch (Exception e) {
			if (!(e instanceof MissingResourceException)) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public String getSpeech() {

		return this.getString("speech");
	}

}
