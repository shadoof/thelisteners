package listeners.handlers;

import static listeners.model.Constants.speechUtils;

// for consistency of references
public class InnerResponse {

	// cardTitle and speeech
	// shouild not be null so let them
	// throw this error if coding is wrong
	private String cardTitle;
	private String speech;
	private String reprompt;

	public String getReprompt() {

		if (reprompt == null || reprompt.isEmpty())
			reprompt = speechUtils.getString("chooseContinue");
		return reprompt;
	}

	public void setReprompt(String reprompt) {

		this.reprompt = reprompt;
	}

	public InnerResponse() {

	}

	public InnerResponse(String cardTitle, String speech) {

		this.cardTitle = cardTitle;
		this.speech = speech;
	}

	public String getCardTitle() {

		return cardTitle;
	}

	public String getSpeech() {

		return speech;
	}

	public void setCardTitle(String cardTitle) {

		this.cardTitle = cardTitle;
	}

	public void setSpeech(String speech) {

		this.speech = speech;
	}

}
