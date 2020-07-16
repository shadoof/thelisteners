package listeners.util;

import static listeners.util.ConstantUtils.info;


public class UnknownIntentException extends Exception {

	public UnknownIntentException(String string) {

		info(string);
	}

}
