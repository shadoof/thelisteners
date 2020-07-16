package listeners.l10n;

import static listeners.model.Constants.localeTag;
import static listeners.model.Constants.speechUtils;
import static listeners.util.ConstantUtils.S;

import java.util.ListResourceBundle;
import java.util.MissingResourceException;


public abstract class L10nSpeech extends ListResourceBundle implements ResponseSpeech {

	public Object[][] contents;

	@Override
	public abstract String buildCardTitle();

	@Override
	public abstract String buildSpeech();

	public String getCardTitle() {

		String ct;
		try {
			ct = getString("cardTitle");
		}
		catch (Exception e) {
			if (!(e instanceof MissingResourceException)) {
				e.printStackTrace();
			}
			ct = "de_DE".equals(localeTag) ? S("Hören", "Höre immer noch zu")
					: S("Still, l", "L") + "istening";
		}
		return ct;
	}

	protected abstract Object[][] getContents();

	public String getPostSpeechPrompt() {

		String pst;
		try {
			pst = getString("postSpeechPrompt");
		}
		catch (Exception e) {
			if (!(e instanceof MissingResourceException)) {
				e.printStackTrace();
			}
			pst = speechUtils.getString("chooseContinueNoAffect");
		}
		return pst;
	}

	public String getReprompt() {

		String r;
		try {
			r = getString("reprompt");
		}
		catch (Exception e) {
			if (!(e instanceof MissingResourceException)) {
				e.printStackTrace();
			}
			r = speechUtils.getString("chooseContinue");
		}
		return r;
	}

	public String getSpeech() {

		return getString("speech");
	}

}
