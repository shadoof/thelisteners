package listeners.handlers;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.Attributes.LASTINTENT;
import static listeners.model.Attributes.sessAttributes;
import static listeners.model.Constants.WILL;
import static listeners.model.Constants.locale;
import static listeners.model.Constants.speechUtils;
import static listeners.util.Utils.info;

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

		info("@LsnrsLaunchResponse, getResponse(), WILL: " + WILL);

		String preamble = "";
		ResourceBundle.clearCache();
		Welcome ws = (Welcome) ResourceBundle.getBundle("listeners.l10n.Welcome", locale);
		// ws.getSpeech() is set
		// at launch Welcome gets special treatment
		// for postSpeechPrompt and reprompt
		String postSpeechPrompt = "";
		String reprompt = speechUtils.getString("chooseContinue");

		if ("firstEncounter".equals(WILL)) {
			preamble = speechUtils.getString("getPreamble");
			WILL = "remember";
			postSpeechPrompt = speechUtils.getString("chooseSpeechAssistance");
		}
		else if ("ask".equals(WILL)) {
			// *** 0. or 3. ***
			// no postSpeech because confirmation does not work as expected from launch
			// postSpeechPrompt = speechUtils.getString("askStartOverSpeech");
		}
		else if ("remember".equals(WILL)) {
			postSpeechPrompt = speechUtils.getString("chooseContinueNoAffect");
		}
		else {
			postSpeechPrompt = speechUtils.getString("chooseSpeechAssistance");
		}

		String affect = (String) sessAttributes.get(AFFECT);
		if (affect == null || affect.isEmpty()) {
			// *** 1. or 2. extra help for initial welcome:
			reprompt = speechUtils.getString("chooseUnsureAboutAffect");
		}

		ResponseFinisher rf = ResponseFinisher.builder()
				.withPreamble(preamble)
				.withSpeech(ws.getSpeech())
				.withPostSpeechPrompt(postSpeechPrompt)
				.withReprompt(reprompt)
				.build();

		// ResponseFinisher rf = new ResponseFinisher(localeTag, preamble,
		// ws.getSpeech(), postSpeechPrompt, reprompt);
		sessAttributes.put(LASTINTENT, "Launch");

		if ("ask".equals(WILL)) {
			Intent ask = Intent.builder()
					.withName("AskStartOverIntent")
					.build();
			info("@Launch, trying to delegate: ");
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
