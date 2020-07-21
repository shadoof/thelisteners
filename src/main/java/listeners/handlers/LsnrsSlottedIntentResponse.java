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

		String affect = "";
		InnerResponse ir;

		String intentName = intent.getName();
		switch (intentName) {
			case "SpkrsAffectIsIntent":
				affect = getFromSlot(AFFECT_SLOT);
				info("@LsnrsSlottedIntentResponse, slot affect: " + affect);

				// we need clear cache and get a new bundle:
				// for speechUtils every round
				// NOTE this is now done by SessionMap
				// as extension of HashMap: SessionMap's put()
				// does SpeechUtils.getNewBundle()
				sessAttributes.put(AFFECT, affect);
				ir = (InnerResponse) SpeechUtils.getNewBundle().getObject(intentName);
//				ir = (InnerResponse) speechUtils.getObject(intentName);
//				cardTitle = ir.getCardTitle(); // TODO
//				cardTitle = speechUtils.getString("spkrsAffectIsCardTitle");
//				ir = new InnerResponse();
//				ir.speech = speechUtils.getString("spkrsAffectIsSpeech");
				ir.setSpeech(ir.getSpeech() + speechUtils.getString("specificAffectSpeech"));

				if (heads()) {
					ir.setSpeech(ir.getSpeech() + speechUtils.getString("chooseYouCanFindOutAffect"));
				}
				else {
					ir.setSpeech(ir.getSpeech() + speechUtils.getString("chooseContinueNoAffect"));
				}
				ir.setSpeech(String.format(ir.getSpeech(), affect));
				ir.setReprompt(speechUtils.getString("chooseYouCanFindOutAffect"));
				sessAttributes.put(PREVIOUSAFFECT, affect);
				break;
			case "SpkrsAffectIsNotIntent":
				affect = (String) sessAttributes.get(AFFECT);
				String challengedAffect = getFromSlot(AFFECT_SLOT);
				info("@LsnrsSlottedIntentResponse, challengedAffect: " + challengedAffect);
				sessAttributes.put(CHALLENGEDAFFECT, challengedAffect);

				ir = (InnerResponse) SpeechUtils.getNewBundle().getObject(intentName);
				if (affect.equals(challengedAffect)) {
					// the speaker seems to have denied a previously set affect
					affect = "";
					sessAttributes.put(AFFECT, affect);
				}
				else {
					ir.setSpeech(ir.getSpeech() + speechUtils.getString("chooseContinueNoAffect"));
				}

				if (!affect.isEmpty()) {
					ir.setReprompt(speechUtils.getString("chooseYouCanFindOutAffect"));
				}
				break;
			case "SpeakFragmentIntent":
			case "WhatsLsnrsAffectAboutIntent":
				ir = new RequestedFragmentResponse();
				break;
			case "WhatIsIntent":
				String thing = getFromSlot(THING_SLOT);
				sessAttributes.put(THING, thing);
				if (PICTURE_WORDS.contains(thing)) {
					ir = (InnerResponse) SpeechUtils.getNewBundle().getObject("WhatPictureIntent");
				}
				else {
					ir = (InnerResponse) SpeechUtils.getNewBundle().getObject(intentName);
				}
				break;
			default:
				// no intent name case was matched
				throw new UnknownIntentException(
						"@LsnrsSlottedIntentResponse, unknown intent: " + intent.getName());
		}

		ResponseFinisher rf = ResponseFinisher.builder()
				.withSpeech(ir.getSpeech())
				.withReprompt(ir.getReprompt())
				.build();

		return input.getResponseBuilder()
				.withSpeech(rf.getSpeech())
				.withReprompt(rf.getReprompt())
				.withSimpleCard(ir.getCardTitle(), rf.getCardText())
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

			setCardTitle(speechUtils.getString("speakFragmentCardTitle"));

			// build variant fragments just before they're needed:
			buildFragments();

			// Get slot from the intent
			Slot fragmentSlot = intent.getSlots().get(FRAGMENTNAME_SLOT);
			String fragmentName = (fragmentSlot == null) ? "" : fragmentSlot.getValue();

			int fragmentIndex = langConstants.parseNameToInt(fragmentName);

			if (fragmentIndex > NOT_YET_GREETED && fragmentIndex < NUMBER_OF_FRAGMENTS) {
				setSpeech(fragments[fragmentIndex]);
				setReprompt(speechUtils.getString("chooseContinue"));

				// set the session fragmentIndex if a valid fragment was found
				sessAttributes.justPut(FRAGMENTINDEX, fragmentIndex);
				// and add this to the list of fragments that have been heard
				ArrayList fl = (ArrayList) sessAttributes.get(FRAGMENTLIST);
				if (!fl.contains(fragmentIndex)) fl.add(fragmentIndex);
				sessAttributes.justPut(FRAGMENTLIST, fl);
				
				// do, but not always, the preSpeech
				if (fragmentIndex > 0 && randInt(0, 3) == 0) 
					setSpeech(speechUtils.getString("preSpeechFeelings") + getSpeech());
			}
			else {
				// After trying to parse the fragmentSlot,
				// we don't know which fragment is wanted.
				setSpeech(speechUtils.getString("dontKnowFragmentSpeech"));
				setReprompt(speechUtils.getString("dontKnowFragmentReprompt"));
			}

		}
	}

}
