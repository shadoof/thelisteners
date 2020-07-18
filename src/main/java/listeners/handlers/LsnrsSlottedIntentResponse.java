package listeners.handlers;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

import listeners.util.ResponseFinisher;
import listeners.util.SpeechUtils;
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
		String affect = "";
		InnerResponse ir = new InnerResponse();

		switch (intent.getName()) {
			case "SpkrsAffectIsIntent":
				affect = getAffectFromSlot(intent);
				info("@LsnrsSlottedIntentResponse, slot affect: " + affect);
				sessAttributes.put(AFFECT, affect);

				// we need clear cache and get a new bundle:
				// for speechUtils every round
				// NOTE this is now done by SessionMap
				// as extension of HashMap SessionMap's put()
				// does SpeechUtils.getNewBundle()

				cardTitle = speechUtils.getString("spkrsAffectIsCardTitle");
				ir.speech = speechUtils.getString("spkrsAffectIsSpeech");
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
			case "SpkrsAffectIsNotIntent":
				cardTitle = speechUtils.getString("spkrsAffectIsNot");
				affect = (String) sessAttributes.get(AFFECT);
				String challengedAffect = getAffectFromSlot(intent);
				info("@LsnrsSlottedIntentResponse, challengedAffect: " + challengedAffect);
				sessAttributes.put(CHALLENGEDAFFECT, challengedAffect);

				if (affect.equals(challengedAffect)) {
					// the speaker seems to have denied a previously set affect
					affect = "";
					sessAttributes.put(AFFECT, affect);
				}
				else {
					ir.postSpeechPrompt = speechUtils.getString("chooseContinueNoAffect");
				}

				if (!affect.isEmpty()) {
					ir.reprompt = speechUtils.getString("chooseYouCanFindOutAffect");
				}
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
