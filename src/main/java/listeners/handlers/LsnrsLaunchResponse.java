package listeners.handlers;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.Attributes.sessAttributes;
import static listeners.model.Constants.locale;
import static listeners.model.Constants.speechUtils;

import java.util.Optional;
import java.util.ResourceBundle;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

import listeners.l10n.Welcome;
import listeners.util.ResponseFinisher;

public class LsnrsLaunchResponse implements LsnrsResponse {
	
	HandlerInput input;
	String relationship;

	public LsnrsLaunchResponse(HandlerInput input, String relationship) {
		
		this.input = input;
		this.relationship = relationship;

	}

	public Optional<Response> getResponse() {

		// *** 1. ***
		String preamble = ("firstEncounter".equals(relationship)) ? speechUtils.getString("getPreamble") : "";

		// *** 2. and 3. ***
		ResourceBundle.clearCache();
		Welcome ws = (Welcome) ResourceBundle.getBundle("listeners.l10n.Welcome", locale);

		// Welcome at launch gets special treatment
		// for postSpeechPrompt and reprompt
		String postSpeechPrompt = "", reprompt = "";
		String affect = (String) sessAttributes.get(AFFECT);
		if (affect == null || affect.isEmpty()) {
			// *** 1. or 2. extra help for initial welcome:
			postSpeechPrompt = speechUtils.getString("chooseSpeechAssistance");
			reprompt = speechUtils.getString("chooseUnsureAboutAffect");
		}
		else {
			// *** 3. ***
			reprompt = speechUtils.getString("chooseContinue");
		}

		// TODO l10n for ResponseFinisher
		ResponseFinisher rf = ResponseFinisher.builder()
				.withPreamble(preamble)
				.withSpeech(ws.getSpeech())
				.withPostSpeechPrompt(postSpeechPrompt)
				.withReprompt(reprompt)
				.build();

		// ResponseFinisher rf = new ResponseFinisher(localeTag, preamble,
		// ws.getSpeech(), postSpeechPrompt, reprompt);

		return input.getResponseBuilder()
				.withSpeech(rf.getSpeech())
				.withReprompt(rf.getReprompt())
				.withSimpleCard(ws.getCardTitle(), rf.getCardText())
				.withShouldEndSession(false)
				.build();
	}
}
