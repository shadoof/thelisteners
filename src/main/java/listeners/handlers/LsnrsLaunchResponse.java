package listeners.handlers;

import static listeners.model.Attributes.*;
import static listeners.model.Constants.locale;
import static listeners.model.Constants.speechUtils;

import java.util.Optional;
import java.util.ResourceBundle;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.Response;

import listeners.l10n.Welcome;
import listeners.util.ResponseFinisher;
import static listeners.util.Utils.*;

public class LsnrsLaunchResponse implements LsnrsResponse {

	private HandlerInput input;

	public LsnrsLaunchResponse(HandlerInput input) {

		this.input = input;

	}

	public Optional<Response> getResponse() {

		info("@LsnrsLaunchResponse, getResponse(), sessPersistence: " + sessAttributes.get(PERSISTENCE));
		// *** 1. ***
		String preamble = "";
		if ("firstEncounter".equals((String) sessAttributes.get(PERSISTENCE))) {
			preamble = speechUtils.getString("getPreamble");
			sessAttributes.put(PERSISTENCE, "session");
		}

		// *** 2. and 3. ***
		ResourceBundle.clearCache();
		Welcome ws = (Welcome) ResourceBundle.getBundle("listeners.l10n.Welcome", locale);

		// Welcome at launch gets special treatment
		// for postSpeechPrompt and reprompt
		String postSpeechPrompt = "";
		String reprompt = speechUtils.getString("chooseContinue");;
		String affect = (String) sessAttributes.get(AFFECT);
		if (affect == null || affect.isEmpty()) {
			// *** 1. or 2. extra help for initial welcome:
			reprompt = speechUtils.getString("chooseUnsureAboutAffect");
		}

		if ("ask".equals((String) sessAttributes.get(PERSISTENCE))) {
			// *** 0. or 3. ***
			// no postSpeech because confirmation does not work as expected from launch
			// postSpeechPrompt = speechUtils.getString("askStartOverSpeech");
		} else if ("remember".equals(sessAttributes.get(PERSISTENCE))) {
			postSpeechPrompt = speechUtils.getString("chooseContinueNoAffect");
			sessAttributes.put(PERSISTENCE, "session");
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
		sessAttributes.put(LASTINTENT, "launch");

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
