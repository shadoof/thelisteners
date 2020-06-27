package listeners.handlers;

import java.util.Locale;

import listeners.model.LangConstants;

public class TestHandler {

	public TestHandler() {

    final Locale locale = new Locale("en-us");
		final LangConstants LANG_CONSTANTS = new LangConstants(locale);
}

	public static void main(String[] args) {
		new TestHandler();

	}

}
