package listeners.handlers;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.Attributes.AFFECT_SLOT;
import static listeners.model.Attributes.CHALLENGEDAFFECT;
import static listeners.model.Attributes.FRAGMENTINDEX;
import static listeners.model.Attributes.FRAGMENTLIST;
import static listeners.model.Attributes.FRAGMENTNAME_SLOT;
import static listeners.model.Attributes.LASTINTENT;
import static listeners.model.Attributes.NOT_YET_GREETED;
import static listeners.model.Attributes.PREVIOUSAFFECT;
import static listeners.model.Attributes.THING;
import static listeners.model.Attributes.THING_SLOT;
import static listeners.model.Attributes.sessAttributes;
import static listeners.model.Constants.NUMBER_OF_FRAGMENTS;
import static listeners.model.Constants.langConstants;
import static listeners.model.Constants.speechUtils;
import static listeners.model.LangConstants.FRAGMENTNAME_MAP;
import static listeners.model.LangConstants.PICTURE_WORDS;
import static listeners.model.LangConstants.buildFragments;
import static listeners.model.LangConstants.fragments;
import static listeners.util.Utils.heads;
import static listeners.util.Utils.randInt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

import listeners.util.ResponseFinisher;
import listeners.util.SpeechUtils;
import listeners.util.UnknownIntentException;

public class LsnrsSlottedIntentResponse extends LsnrsIntentResponse implements LsnrsResponse {

	LsnrsSlottedIntentResponse(HandlerInput input, String relationship) {

		super(input, relationship);
	}

	@Override
	public Optional<Response> getResponse() throws UnknownIntentException {

		String affect = "";
		InnerResponse ir;

		String intentName = intent.getName();
		switch (intentName) {
			case "SpkrsAffectIsIntent":
				affect = getFromSlot(AFFECT_SLOT);
				// info("@LsnrsSlottedIntentResponse, slot affect: " + affect);

				sessAttributes.put(AFFECT, affect);
				// using SpeechUtils.getNewBundle() ensures refresh for affect
				speechUtils = SpeechUtils.getNewBundle();

				ir = (InnerResponse) speechUtils.getObject(intentName);
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
				// info("@LsnrsSlottedIntentResponse, challengedAffect: " + challengedAffect);
				sessAttributes.put(CHALLENGEDAFFECT, challengedAffect);

				speechUtils = SpeechUtils.getNewBundle();
				ir = (InnerResponse) speechUtils.getObject(intentName);

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
				speechUtils = SpeechUtils.getNewBundle();
				if (PICTURE_WORDS.contains(thing)) {
					ir = (InnerResponse) speechUtils.getObject("WhatPictureIntent");
				}
				else {
					ir = (InnerResponse) speechUtils.getObject(intentName);
				}
				break;
			default:
				// no intent name case was matched
				throw new UnknownIntentException(
						"@LsnrsSlottedIntentResponse, unknown intent: " + intent.getName());
		}

		ResponseFinisher rf = ResponseFinisher.builder()
				.withSpeech(ir.getSpeech())
				.withInterruptable(ir.isInterruptable())
				.withReprompt(ir.getReprompt())
				.build();

		sessAttributes.put(LASTINTENT, intentName);
		return input.getResponseBuilder()
				.withSpeech(rf.getSpeech())
				.withReprompt(rf.getReprompt())
				.withSimpleCard(ir.getCardTitle(), rf.getCardText())
				.withShouldEndSession(false)
				.build();

	}

	private String getFromSlot(String slotKey) {

		String value;
		Slot slot = intent.getSlots()
				.get(slotKey);
		value = (slot == null) ? "" : slot.getValue();
		if (slotKey.contentEquals(AFFECT_SLOT)) {
			return langConstants.getNounFromAdjective(value);
		}
		return value;
	}

	private class RequestedFragmentResponse extends InnerResponse {

		private RequestedFragmentResponse() {

			setCardTitle(speechUtils.getString("speakFragmentCardTitle"));

			// build variant fragments just before they’re needed:
			buildFragments();

			// Get slot from the intent
			Slot fragmentSlot = intent.getSlots()
					.get(FRAGMENTNAME_SLOT);
			String fragmentName = (fragmentSlot == null) ? "" : fragmentSlot.getValue();
			int fragmentIndex = langConstants.parseNameToInt(fragmentName);

			if (fragmentIndex > NOT_YET_GREETED && fragmentIndex < NUMBER_OF_FRAGMENTS) {
				setSpeech(fragments[fragmentIndex]);

				setReprompt(speechUtils.getString("chooseContinue"));

				// set the session fragmentIndex if a valid fragment was found
				sessAttributes.put(FRAGMENTINDEX, fragmentIndex);
				// and add this to the list of fragments that have been heard
				LinkedHashSet listOfFragments = (LinkedHashSet) sessAttributes.get(FRAGMENTLIST);
				listOfFragments.add(fragmentIndex);
				sessAttributes.put(FRAGMENTLIST,listOfFragments);
//				ArrayList fl = (ArrayList) sessAttributes.get();
//				if (!fl.contains(fragmentIndex)) {
//					fl.add(fragmentIndex);
//					sessAttributes.put(, fl);
//				}

				// do, but not always, the preSpeech
				if (fragmentIndex > 0 && randInt(0, 3) == 0)
					setSpeech(speechUtils.getString("preSpeechFeelings") + getSpeech());
			}
			else {
				// After trying to parse the fragment and thing Slots,
				// we don’t know which fragment is wanted.
				setSpeech(speechUtils.getString("dontKnowFragmentSpeech"));
				setReprompt(speechUtils.getString("dontKnowFragmentReprompt"));
			}
		}
	}

}
