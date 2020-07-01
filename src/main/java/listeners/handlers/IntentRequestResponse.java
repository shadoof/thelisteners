package listeners.handlers;

import static listeners.model.LangConstants.locale;
import static listeners.model.LangConstants.localeTag;
import static listeners.util.ConstantUtils.info;

import java.util.Optional;
import java.util.ResourceBundle;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;

import listeners.l10n.L10nSpeech;
import listeners.l10n.WelcomeSpeech;
import listeners.util.ResponseFinisher;

public class IntentRequestResponse implements RequestResponse {

	// this RequestResponse is for straightforward responses,
	// no slots, no dialog (confirmation) needed

	@Override
	public Optional<Response> getResponse(HandlerInput input) {

		IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();
		Intent intent = intentRequest.getIntent();

		// clear cache before building l10n speeches
		ResourceBundle.clearCache();

		// if we adhere to the convention that there is are l10n class bundles
		// for all simple intentName we need no 'switch' or 'else if' logic here.
		// we just load a bundle with the intent.getName():
		L10nSpeech ls = (L10nSpeech) ResourceBundle.getBundle("listeners.l10n." + intent.getName(), locale);

		// TODO l10n for SpeechFinisher
		ResponseFinisher rf = new ResponseFinisher(localeTag, ls.getString("speech"), ls.getString("reprompt"), ls.getString("postSpeechPrompt"));

		return input.getResponseBuilder().
				withSpeech(rf.getSpeech()).
				withReprompt(rf.getReprompt()).
				withSimpleCard(rf.getCardTitle(), rf.getCardText()).
				withShouldEndSession(false).
				build();
	}

}
