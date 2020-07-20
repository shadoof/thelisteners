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
import static listeners.model.LangConstants.*;

public class LsnrsSlottedIntentResponse extends LsnrsIntentResponse implements LsnrsResponse {

	LsnrsSlottedIntentResponse() {

	}

	@Override
	public Optional<Response> getResponse() throws UnknownIntentException {

		info("@LsnrsSlottedIntentResponse, intent name: " + intent.getName());

		String cardTitle = "";
		String affect = "";
		InnerResponse ir;

		switch (intent.getName()) {
			case "SpkrsAffectIsIntent":
				affect = getFromSlot(AFFECT_SLOT);
				info("@LsnrsSlottedIntentResponse, slot affect: " + affect);
				sessAttributes.put(AFFECT, affect);

				// we need clear cache and get a new bundle:
				// for speechUtils every round
				// NOTE this is now done by SessionMap
				// as extension of HashMap SessionMap's put()
				// does SpeechUtils.getNewBundle()

				cardTitle = speechUtils.getString("spkrsAffectIsCardTitle");
				ir = new InnerResponse();
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
				String challengedAffect = getFromSlot(AFFECT_SLOT);
				info("@LsnrsSlottedIntentResponse, challengedAffect: " + challengedAffect);
				sessAttributes.put(CHALLENGEDAFFECT, challengedAffect);

				ir = new InnerResponse();
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
			case "SpeakFragmentIntent":
			case "WhatsLsnrsAffectAboutIntent":
				cardTitle = speechUtils.getString("speakFragmentCardTitle");

				ir = new RequestedFragmentResponse();
				break;
			case "WhatIsIntent":
				cardTitle = speechUtils.getString("whatIsCardTitle");
				ir = new InnerResponse();
				String thing = getFromSlot(THING_SLOT);
				sessAttributes.put(THING, thing);
				if (PICTURE_WORDS.contains(thing)) {
					ir.speech = speechUtils.getString("whatPictureSpeech");
				}
				else {
					ir.speech = speechUtils.getString("whatIsSpeech");
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

	private String getFromSlot(String slotKey) {

		String value;
		Slot slot = intent.getSlots().get(slotKey);
		value = (slot == null) ? "" : slot.getValue();
		if (slotKey.contentEquals(AFFECT_SLOT)) {
			return langConstants.getNounFromAdjective(value);
		}
		return value;
	}

	private class RequestedFragmentResponse extends InnerResponse {

		private RequestedFragmentResponse() {

			// build variant fragments just before they're needed:
			buildFragments();

			// Get slot from the intent
			Slot fragmentSlot = intent.getSlots().get(FRAGMENTNAME_SLOT);
			String fragmentName = (fragmentSlot == null) ? "" : fragmentSlot.getValue();

			int fragmentIndex = langConstants.parseNameToInt(fragmentName);

			if (fragmentIndex > NOT_YET_GREETED && fragmentIndex < NUMBER_OF_FRAGMENTS) {
				speech = fragments[fragmentIndex];
				reprompt = speechUtils.getString("chooseContinue");

				// set the session fragmentIndex if a valid fragment was found
				sessAttributes.justPut(FRAGMENTINDEX, fragmentIndex);
				// and add this to the list of fragments that have been heard
				ArrayList fl = (ArrayList) sessAttributes.get(FRAGMENTLIST);
				if (!fl.contains(fragmentIndex)) fl.add(fragmentIndex);
				sessAttributes.justPut(FRAGMENTLIST, fl);
				
				// do, but not always, the preSpeech
				if (fragmentIndex > 0 && randInt(0, 3) == 0) preSpeech = speechUtils.getString("preSpeechFeelings");
			}
			else {
				// After trying to parse the fragmentSlot,
				// we don't know which fragment is wanted.
				speech = speechUtils.getString("dontKnowFragmentSpeech");
				reprompt = speechUtils.getString("dontKnowFragmentReprompt");
			}

		}
	}

}
