package listeners.util;

import static listeners.util.Utils.info;


public class UnknownIntentException extends Exception {

	public UnknownIntentException(String string) {

		info(string);
	}

}
