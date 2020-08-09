package listeners.handlers;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.Attributes.FRAGMENTINDEX;
import static listeners.model.Attributes.FRAGMENTLIST;
import static listeners.model.Attributes.HEARDALLFRAGMENTS;
import static listeners.model.Attributes.HEARDBREATHAFFECTS;
import static listeners.model.Attributes.LASTINTENT;
import static listeners.model.Attributes.NOT_YET_GREETED;
import static listeners.model.Attributes.THING;
import static listeners.model.Attributes.sessAttributes;
import static listeners.model.Constants.NUMBER_OF_FRAGMENTS;
import static listeners.model.Constants.VERSE;
import static listeners.model.Constants.speechUtils;
import static listeners.model.LangConstants.FRAGMENTNAME_MAP;
import static listeners.model.LangConstants.buildFragments;
import static listeners.model.LangConstants.fragments;
import static listeners.util.Utils.heads;
import static listeners.util.Utils.randInt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

import listeners.util.ResponseFinisher;
import listeners.util.UnknownIntentException;

public class LsnrsContinueIntentResponse extends LsnrsIntentResponse implements LsnrsResponse {

	LsnrsContinueIntentResponse(HandlerInput input, String relationship) {

		super(input, relationship);
	}

	private boolean isEnd = false;

	@Override
	public Optional<Response> getResponse() throws UnknownIntentException {

		String intentName = intent.getName();
		InnerResponse ir = null;

		switch (intentName) {
			case "PleaseContinueIntent":
			case "ContinueIntent":
				ir = new NextFragmentResponse(intentName);
				break;
			case "PreviousIntent":
				ir = new InnerResponse();
				ir.setCardTitle(speechUtils.getString("previousCardTitle"));
				// do, but not always, the preSpeech

				// build variant fragments just before they’re needed:
				buildFragments();

				int fragmentIndex = (int) sessAttributes.get(FRAGMENTINDEX) - 1;
				fragmentIndex = (fragmentIndex < 0) ? NUMBER_OF_FRAGMENTS - 1 : fragmentIndex;

				sessAttributes.put(FRAGMENTINDEX, fragmentIndex);

				ir.setSpeech(fragments[fragmentIndex]);
				if ((int) sessAttributes.get(FRAGMENTINDEX) > NOT_YET_GREETED && randInt(0, 3) == 0)
					ir.setSpeech(speechUtils.getString("preSpeechFeelings") + ir.getSpeech());
				ir.setSpeech(ir.getSpeech() + speechUtils.getString("chooseContinueNoAffect"));

				ir.setReprompt(speechUtils.getString("chooseContinue"));

				// and add this to the list of fragments that have been heard
				HashSet hs = (HashSet) sessAttributes.get(FRAGMENTLIST);
				hs.add(fragmentIndex);
				sessAttributes.put(FRAGMENTLIST, hs);
				// if (!al.contains(fragmentIndex)) {
				// al.add(fragmentIndex);
				// sessAttributes.put(, al);
				// }
				ir.setInterruptable(true);
				break;
			case "ReadPoemIntent":
				ir = new InnerResponse();
				ir.setCardTitle(speechUtils.getString("readPoemCardTitle"));
				fragmentIndex = VERSE;
				sessAttributes.put(FRAGMENTINDEX, fragmentIndex);
				// build variant fragments just before they’re needed:
				buildFragments();
				ir.setSpeech(fragments[fragmentIndex]);
				ir.setReprompt(speechUtils.getString("chooseContinue"));
				hs = (HashSet) sessAttributes.get(FRAGMENTLIST);
				hs.add(fragmentIndex);
				sessAttributes.put(FRAGMENTLIST, hs);
				// al = (ArrayList) sessAttributes.get();
				// if (!al.contains(fragmentIndex)) {
				// al.add(fragmentIndex);
				// sessAttributes.put(, al);
				// }
				break;
			case "WhatsLsnrsAffectIntent":
			case "ThanksWhatsLsnrsAffectIntent":
				ir = new InnerResponse();
				if (intentName.equals("ThanksWhatsLsnrsAffectIntent")) {
					ir.setCardTitle(speechUtils.getString("thanksWhatsLsnrsAffectCardTitle"));
					ir.setSpeech(speechUtils.getString("thanksWhatsLsnrsAffectPreSpeech") + ir.getSpeech());
				}
				else {
					ir.setCardTitle(speechUtils.getString("whatsLsnrsAffectCardTitle"));
				}
				if ((boolean) sessAttributes.get(HEARDBREATHAFFECTS) || heads()) {
					ir.setSpeech(speechUtils.getString("whatsLsnrsAffectSpeech"));
				}
				else {
					ir.setSpeech(speechUtils.getString("affectAsBreathingSpeech"));
					sessAttributes.put(HEARDBREATHAFFECTS, true);
				}
				ir.setReprompt(speechUtils.getString("chooseContinueNoAffect"));
				break;
			case "WhatAboutAffectsIntent":
				ir = (InnerResponse) speechUtils.getObject(intentName);
				ir.setSpeech(ir.getSpeech() + speechUtils.getString("chooseContinueNoAffect"));
				ir.setReprompt(speechUtils.getString("chooseContinueNoAffect"));
				break;
			case "WhatPictureIntent":
				ir = (InnerResponse) speechUtils.getObject(intentName);
				if ((int) sessAttributes.get(FRAGMENTINDEX) > NOT_YET_GREETED && randInt(0, 3) == 0)
					ir.setSpeech(speechUtils.getString("preSpeechFeelings") + ir.getSpeech());
				break;
			case "WhatsSpkrsAffectIntent":
				ir = (InnerResponse) speechUtils.getObject(intentName);
				ir.setReprompt((((String) sessAttributes.get(AFFECT)).isEmpty())
						? speechUtils.getString("chooseUnsureAboutAffect")
						: speechUtils.getString("chooseContinueNoAffect"));
				break;
			default:
				// no intent name case was matched
				throw new UnknownIntentException(
						"@LsnrsContinueIntentResponse, unknown intent: " + intent.getName());
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
				.withShouldEndSession(isEnd)
				.build();
	}

	private class NextFragmentResponse extends InnerResponse {

		NextFragmentResponse(String intentName) {

			// build variant fragments just before they’re needed:
			buildFragments();

			int fragmentIndex = randInt(0, NUMBER_OF_FRAGMENTS - 1);

			HashSet hs = (HashSet) sessAttributes.get(FRAGMENTLIST);
			// note: the FRAGMENTLIST must be initialized with one value (-1)
			// due to Amazon persistence issues so size() is +1
			if ((hs.size() > NUMBER_OF_FRAGMENTS) && !(boolean) sessAttributes.get(HEARDALLFRAGMENTS)) {
				setInterruptable(false);
				// info("@NextFragmentResponse, setting up heard all");
				setSpeech(speechUtils.getString("heardAllFragments"));

				// this used to be a trigger that ended the session below
				sessAttributes.put(HEARDALLFRAGMENTS, true);
				setReprompt(speechUtils.getString("chooseContinueNoAffect"));
			}

			if (hs.size() <= NUMBER_OF_FRAGMENTS) {
				int i = 0;
				do {
					fragmentIndex = randInt(0, NUMBER_OF_FRAGMENTS - 1);
					i++;
				}
				while (hs.contains(fragmentIndex) && i < 500); // safety

				hs.add(fragmentIndex);
				sessAttributes.put(FRAGMENTLIST, hs);
			}

			sessAttributes.put(FRAGMENTINDEX, fragmentIndex);

			// speech is only not empty if the "heard everything" message has been given
			// if it has been given, a random fragment will be heard
			if (getSpeech().isEmpty()) {
				setInterruptable(true);
				setSpeech(fragments[(int) sessAttributes.get(FRAGMENTINDEX)]
						+ speechUtils.getString("chooseContinueNoAffect"));
			}

			if (intentName.equals("PleaseContinueIntent")) {
				setCardTitle(speechUtils.getString("pleaseContinueCardTitle"));
				if ((int) sessAttributes.get(FRAGMENTINDEX) > NOT_YET_GREETED && randInt(0, 3) == 0) {
					setSpeech(speechUtils.getString("preSpeechFeelings") + getSpeech());
				}
				else
					setSpeech(speechUtils.getString("pleaseContinuePreSpeech") + getSpeech());
			}
			else {
				setCardTitle(speechUtils.getString("continueCardTitle"));
			}
		}
	}

}
