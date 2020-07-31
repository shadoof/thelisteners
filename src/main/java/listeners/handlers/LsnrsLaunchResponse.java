package listeners.handlers;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.Attributes.PERSISTENCE;
import static listeners.model.Attributes.sessAttributes;
import static listeners.model.Constants.locale;
import static listeners.model.Constants.speechUtils;

import java.util.Optional;
import java.util.ResourceBundle;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.Response;

import listeners.l10n.Welcome;
import listeners.util.ResponseFinisher;

public class LsnrsLaunchResponse implements LsnrsResponse {

	private HandlerInput input;

	public LsnrsLaunchResponse(HandlerInput input) {

		this.input = input;

	}

	public Optional<Response> getResponse() {

		// *** 1. ***
		String preamble = ("firstEncounter".equals((String) sessAttributes.get(PERSISTENCE))) ? speechUtils.getString("getPreamble")
				: "";

		// *** 2. and 3. ***
		ResourceBundle.clearCache();
		Welcome ws = (Welcome) ResourceBundle.getBundle("listeners.l10n.Welcome", locale);

		// Welcome at launch gets special treatment
		// for postSpeechPrompt and reprompt
		String postSpeechPrompt = "", reprompt = "";
		String affect = (String) sessAttributes.get(AFFECT);
		if (affect == null || affect.isEmpty()) {
			// *** 1. or 2. extra help for initial welcome:
			reprompt = speechUtils.getString("chooseUnsureAboutAffect");
		}

		if ("ask".equals((String) sessAttributes.get(PERSISTENCE))) {
			// *** 0. or 3. ***
			postSpeechPrompt = speechUtils.getString("askStartOverSpeech");
			reprompt = speechUtils.getString("chooseContinue");
		}
		else {
			postSpeechPrompt = speechUtils.getString("chooseSpeechAssistance");
		}

		ResponseFinisher rf = ResponseFinisher.builder()
				.withPreamble(preamble)
				.withSpeech(ws.getSpeech())
				.withPostSpeechPrompt(postSpeechPrompt)
				.withReprompt(reprompt)
				.build();

		// ResponseFinisher rf = new ResponseFinisher(localeTag, preamble,
		// ws.getSpeech(), postSpeechPrompt, reprompt);

		if ("ask".equals((String) sessAttributes.get(PERSISTENCE))) {
			Intent ask = Intent.builder()
					.withName("AskStartOverIntent")
					.build();
			return input.getResponseBuilder()
					.addDelegateDirective(ask)
					.withSpeech(rf.getSpeech())
					.build();
		}

		return input.getResponseBuilder()
				.withSpeech(rf.getSpeech())
				.withReprompt(rf.getReprompt())
				.withSimpleCard(ws.getCardTitle(), rf.getCardText())
				.withShouldEndSession(false)
				.build();
	}
}
