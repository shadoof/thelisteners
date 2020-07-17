package listeners.handlers;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

import listeners.util.ResponseFinisher;
import listeners.util.UnknownIntentException;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;
import static listeners.model.LangConstants.buildFragments;
import static listeners.model.LangConstants.fragments;
import static listeners.model.LangConstants.FRAGMENTNAME_MAP;

public class LsnrsSlottedIntentResponse extends LsnrsIntentResponse implements LsnrsResponse {

	LsnrsSlottedIntentResponse() {

	}

	@Override
	public Optional<Response> getResponse() throws UnknownIntentException {

		info("@LsnrsSlottedIntentResponse, intent name: " + intent.getName());

		String cardTitle = "";
		InnerResponse ir = new InnerResponse();

		switch (intent.getName()) {
			case "SpkrsAffectIsIntent":
				String affect = getAffectFromSlot(intent);
				info("@LsnrsSlottedIntentResponse, slot affect: " + affect);
				sessAttributes.put(AFFECT, affect);

				cardTitle = speechUtils.getString("spkrsAffectIsCardTitle");
				ir.speech = speechUtils.getString("spkrsAffectIsSpeech");
				// TODO check: may have to clear cache and get new bundle!
				ir.speech += speechUtils.getString("specificAffectSpeech");

				if (heads()) {
					ir.postSpeechPrompt = speechUtils.getString("chooseYouCanFindOutAffect");
				}
				else {
					ir.speech += breath();
					ir.postSpeechPrompt = speechUtils.getString("chooseContinueNoAffect");
				}
				ir.speech = String.format(ir.speech, affect);
				ir.reprompt = speechUtils.getString("chooseYouCanFindOutAffect");
				sessAttributes.put(PREVIOUSAFFECT, affect);
				break;
			default:
				// no intent name case was matched
				throw new UnknownIntentException(
						"@LsnrsSlottedIntentResponse, unknown intent: " + intent.getName());
		}

		ResponseFinisher rf = ResponseFinisher.builder()
				.withSpeech(ir.speech)
				.withPostSpeechPrompt(ir.postSpeechPrompt)
				.withReprompt(ir.reprompt)
				.build();

		return input.getResponseBuilder()
				.withSpeech(rf.getSpeech())
				.withReprompt(rf.getReprompt())
				.withSimpleCard(cardTitle, rf.getCardText())
				.withShouldEndSession(false)
				.build();

	}

	private static class InnerResponse {

		static String speech = "";
		static String postSpeechPrompt = "";
		static String reprompt = "";

		InnerResponse() {

		}

	}

	private String getAffectFromSlot(Intent intent) {

		String affect;
		Map<String, Slot> slots = intent.getSlots();
		Slot affectSlot = slots.get(AFFECT_SLOT);
		affect = (affectSlot == null) ? "" : affectSlot.getValue();
		return langConstants.getNounFromAdjective(affect);
	}

}
