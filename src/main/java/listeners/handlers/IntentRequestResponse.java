package listeners.handlers;

import static listeners.model.LangConstants.locale;
import static listeners.model.LangConstants.localeTag;
import static listeners.util.ConstantUtils.info;
import static listeners.model.Constants.DEV;

import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;

import listeners.l10n.L10nSpeech;
import listeners.l10n.Welcome;
import listeners.util.ResponseFinisher;
import listeners.util.SpeechUtils;

public class IntentRequestResponse extends RequestResponse implements RequestResponsible {

	// this RequestResponse is for straightforward responses,
	// no slots, no dialog (confirmation) needed

	IntentRequestResponse(Map<String, Object> persistentAttributes, Map<String, Object> sessionAttributes) {

		super(persistentAttributes, sessionAttributes);
	}

	@Override
	public Optional<Response> getResponse(HandlerInput input, String relationship) {
		
		if ("firstEncounter".equals(relationship)) {
			// build first response TODO
			// add intro and 'trigger warning' in Alexa's voice TODO
		}

		IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();
		Intent intent = intentRequest.getIntent();

		// clear cache before building l10n speeches
		ResourceBundle.clearCache();

		// if we adhere to the convention that there are l10n class bundles
		// for ALL simple intentNames we need no 'switch' or 'else if' logic here.
		// we just load a bundle with the intent.getName():
		L10nSpeech ls = (L10nSpeech) ResourceBundle.getBundle("listeners.l10n." + intent.getName(), locale);

		SpeechUtils su = new SpeechUtils(locale);
		
		String postSpeechPrompt = ls.getPostSpeechPrompt();
		if (postSpeechPrompt == null) postSpeechPrompt = su.chooseContinue();
		String reprompt = ls.getReprompt();
		if (reprompt == null) reprompt = su.chooseContinue();

		ResponseFinisher rf = new ResponseFinisher(localeTag, ls.getSpeech(), postSpeechPrompt, reprompt);

		String ct = DEV ? intent.getName() + " - " + rf.getDateString() : ls.getCardTitle();
		
		return input.getResponseBuilder().
				withSpeech(rf.getSpeech()).
				withReprompt(rf.getReprompt()).
				withSimpleCard(ct, rf.getCardText()).
				withShouldEndSession(false).
				build();
	}

}
