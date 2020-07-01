package listeners.handlers;

import static listeners.model.Attributes.FRAGMENTCOUNT_KEY;
import static listeners.util.ConstantUtils.info;
import static listeners.model.LangConstants.locale;
import static listeners.model.LangConstants.localeTag;

import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

import listeners.l10n.WelcomeSpeech;
import listeners.model.LangConstants;
import listeners.util.ResponseFinisher;

public class LaunchRequestResponse implements RequestResponse {

	private String affect; // TODO

	public Optional<Response> getResponse(HandlerInput input) {

		// TODO Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
		// TODO sessionAttributes.put(FRAGMENTCOUNT_KEY, 0);

		ResourceBundle.clearCache();
		WelcomeSpeech ws = (WelcomeSpeech) ResourceBundle.getBundle("listeners.l10n.WelcomeSpeech", locale);

		// TODO l10n for SpeechFinisher
		String postSpeechPrompt = "";
		ResponseFinisher rf = new ResponseFinisher(localeTag, ws.getString("speech"), ws.getString("reprompt"), postSpeechPrompt);

		return input.getResponseBuilder().
				withSpeech(rf.getSpeech()).
				withReprompt(rf.getReprompt()).
				withSimpleCard(rf.getCardTitle(), rf.getCardText()).
				withShouldEndSession(false).
				build();
	}
}
