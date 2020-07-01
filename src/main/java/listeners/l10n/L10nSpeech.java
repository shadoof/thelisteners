package listeners.l10n;

import java.util.ListResourceBundle;

public abstract class L10nSpeech extends ListResourceBundle implements ResponseSpeech {

	public Object[][] contents;
	
	@Override
	protected Object[][] getContents() {

		return contents;
	}

}
