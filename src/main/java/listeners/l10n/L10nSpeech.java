package listeners.l10n;

import static listeners.model.Constants.DO_NOT_PROMPT_AFFECT;
import static listeners.model.LangConstants.locale;
import static listeners.model.LangConstants.localeTag;
import static listeners.util.ConstantUtils.S;

import java.util.ListResourceBundle;
import java.util.MissingResourceException;

import listeners.util.SpeechUtils;


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
			ct = "de_DE".equals(localeTag) ? S("Hören", "Höre immer noch zu") : S("Still, l", "L") + "istening";
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
			pst = new SpeechUtils(locale).chooseContinue(DO_NOT_PROMPT_AFFECT);
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
			r = new SpeechUtils(locale).chooseContinue();
		}
		return r;
	}

	public String getSpeech() {
		return getString("speech");
	}

}
