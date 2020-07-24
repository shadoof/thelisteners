package listeners.handlers;

import java.util.ArrayList;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.DialogState;
import com.amazon.ask.model.IntentConfirmationStatus;
import com.amazon.ask.model.Response;

import listeners.util.ResponseFinisher;
import listeners.util.UnknownIntentException;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.*;
import static listeners.model.LangConstants.buildFragments;
import static listeners.model.LangConstants.fragments;
import static listeners.model.LangConstants.FRAGMENTNAME_MAP;

public class LsnrsContinueIntentResponse extends LsnrsIntentResponse implements LsnrsResponse {

	private boolean isEnd = false;

	LsnrsContinueIntentResponse() {

	}

	@Override
	public Optional<Response> getResponse() throws UnknownIntentException {

		String intentName = intent.getName();
		InnerResponse ir = null;

		switch (intentName) {
			case "NoIntent":
				ir = new InnerResponse();
				ir.setCardTitle(speechUtils.getString("noCardTitle"));
				if (intent.getConfirmationStatus() == IntentConfirmationStatus.CONFIRMED) {
				// [ my home-brewed dialog: ] if ((boolean) sessAttributes.get(HEARDNO)) {
					isEnd = true;
					ir.setSpeech(speechUtils.getString("getAbandonmentMessage"));
					if (attributes.isPositive((String) sessAttributes.get(AFFECT))) {
						ir.setSpeech(
								ir.getSpeech() + ("de_DE".equals(localeTag) ? s("Tschüss!", "") : s("Cheerio!", "")));
					}
					break;
				} else if (intent.getConfirmationStatus() == IntentConfirmationStatus.DENIED) {
					return input.getResponseBuilder()
							.build(); // does nothing?
				}
				// NB: fall-through is possible to:
			case "ThanksNoIntent":
				if (ir == null) ir = new InnerResponse();
				if ("ThanksNoIntent".equals(intentName)) {
					ir.setCardTitle(speechUtils.getString("thanksNoCardTitle"));
					ir.setSpeech(speechUtils.getString("thanksNoSpeech"));
				}

//				if ((boolean) sessAttributes.get(HEARDNO)) {
//					isEnd = true;
//					ir.setSpeech(speechUtils.getString("getAbandonmentMessage"));
//					if (attributes.isPositive((String) sessAttributes.get(AFFECT))) {
//						ir.setSpeech(
//								ir.getSpeech() + ("de_DE".equals(localeTag) ? s("Tschüss!", "") : s("Cheerio!", "")));
//					}
//				}

				ir.setSpeech(ir.getSpeech() + speechUtils.getString("reallyWantToAbandon"));

				sessAttributes.justPut(HEARDNO, true);
				sessAttributes.justPut(LASTINTENT, intentName);
				break;
			case "PleaseContinueIntent":
			case "ContinueIntent":
				ir = new NextFragmentResponse(intentName);
				break;
			case "PreviousIntent":
				ir = new InnerResponse();
				ir.setCardTitle(speechUtils.getString("previousCardTitle"));
				// do, but not always, the preSpeech

				// build variant fragments just before they're needed:
				buildFragments();

				int fragmentIndex = (int) sessAttributes.get(FRAGMENTINDEX) - 1;
				fragmentIndex = (fragmentIndex < 0) ? NUMBER_OF_FRAGMENTS - 1 : fragmentIndex;

				sessAttributes.justPut(FRAGMENTINDEX, fragmentIndex);

				ir.setSpeech(fragments[fragmentIndex]);
				if ((int) sessAttributes.get(FRAGMENTINDEX) > NOT_YET_GREETED && randInt(0, 3) == 0)
					ir.setSpeech(speechUtils.getString("preSpeechFeelings") + ir.getSpeech());
				ir.setSpeech(ir.getSpeech() + speechUtils.getString("chooseContinueNoAffect"));

				ir.setReprompt(speechUtils.getString("chooseContinue"));

				// and add this to the list of fragments that have been heard
				ArrayList al = (ArrayList) sessAttributes.get(FRAGMENTLIST);
				if (!al.contains(fragmentIndex)) {
					al.add(fragmentIndex);
					sessAttributes.justPut(FRAGMENTLIST, al);
				}

				// TODO in ResponseFinisher:
				boolean specificFragment = true;
				break;
			case "ReadPoemIntent":
				ir = new InnerResponse();
				ir.setCardTitle(speechUtils.getString("readPoemCardTitle"));
				fragmentIndex = VERSE;
				sessAttributes.justPut(FRAGMENTINDEX, fragmentIndex);
				// build variant fragments just before they're needed:
				buildFragments();
				ir.setSpeech(fragments[fragmentIndex]);
				ir.setReprompt(speechUtils.getString("chooseContinue"));
				al = (ArrayList) sessAttributes.get(FRAGMENTLIST);
				if (!al.contains(fragmentIndex)) {
					al.add(fragmentIndex);
					sessAttributes.justPut(FRAGMENTLIST, al);
				}
				break;
			case "SpeakGuyzIntent":
				sessAttributes.justPut(HEARDNO, false);
				ir = new InnerResponse();
				ir.setCardTitle(speechUtils.getString("speakGuyzCardTitle"));
				if ((boolean) sessAttributes.get(SPEAKGUYZCONFIRMED)) {
					int currentSpeechIndex;
					sessAttributes.justPut(GUYZSPEECHINDEX, sessAttributes.get(GUYZINDEX));
					int guyzSpeechIndex = (int) sessAttributes.get(GUYZSPEECHINDEX);
					for (currentSpeechIndex = guyzSpeechIndex; currentSpeechIndex < guyzSpeechIndex
							+ (5 - ((guyzSpeechIndex - 1) % 5)) /* NUMBER_OF_GUYZ */; currentSpeechIndex++) {
						ir.setSpeech(ir.getSpeech() + speechUtils.getString("pathToGuyzAudio")
								+ String.format("%03d", currentSpeechIndex) + ".mp3\" /> ");
					}
					sessAttributes.justPut(GUYZSPEECHINDEX, currentSpeechIndex); // guyzSpeechIndex = i;
					// leave out a group of five in performances
					if (PERFORMANCE) {
						if ((int) sessAttributes.get(GUYZSPEECHINDEX) > 20
								&& (int) sessAttributes.get(GUYZSPEECHINDEX) < 26) {
							sessAttributes.justPut(GUYZSPEECHINDEX, 26);
						}
					}
					if ((int) sessAttributes.get(GUYZSPEECHINDEX) >= NUMBER_OF_GUYZ) {
						sessAttributes.justPut(SPEAKGUYZCONFIRMED, false);
						// no going back
						// the guyz are gone!
					}
					else {
						ir.setSpeech(ir.getSpeech() + speechUtils.getString("getGuyzMoreQuery"));
					}
					ir.setReprompt(speechUtils.getString("chooseContinueNoAffect"));
					// guyzIndex = guyzSpeechIndex;
					sessAttributes.justPut(GUYZINDEX, sessAttributes.get(GUYZSPEECHINDEX));
					// session.setAttribute(GUYZ_KEY, guyzIndex);
				}
				else // check: really?
				{
					if ((int) sessAttributes.get(GUYZSPEECHINDEX) > NUMBER_OF_GUYZ) {
						ir.setSpeech(ir.getSpeech() + speechUtils.getString("getGuyzAreGone"));
						ir.setReprompt(speechUtils.getString("chooseContinue"));
					}
					else {
						sessAttributes.justPut(LASTINTENT, "SpeakGuyzIntent");
						ir.setSpeech(ir.getSpeech() + speechUtils.getString("getReallyWantGuyz"));
						ir.setReprompt(speechUtils.getString("getReallyWantGuyzReprompt"));
					}
				}
				break;
			case "WhatsLsnrsAffectIntent":
			case "ThanksWhatsLsnrsAffectIntent":
				ir = (InnerResponse) speechUtils.getObject("WhatsLsnrsAffectIntent");
				if (intentName.equals("ThanksWhatsLsnrsAffectIntent")) {
					ir.setCardTitle(speechUtils.getString("thanksWhatsLsnrsAffectCardTitle"));
					ir.setSpeech(speechUtils.getString("thanksWhatsLsnrsAffectPreSpeech") + ir.getSpeech());
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

		return input.getResponseBuilder()
				.withSpeech(rf.getSpeech())
				.withReprompt(rf.getReprompt())
				.withSimpleCard(ir.getCardTitle(), rf.getCardText())
				.withShouldEndSession(isEnd)
				.build();
	}

	private class NextFragmentResponse extends InnerResponse {

		NextFragmentResponse(String intentName) {

			// build variant fragments just before they're needed:
			buildFragments();

			sessAttributes.put(FRAGMENTINDEX, randInt(0, NUMBER_OF_FRAGMENTS - 1));

			ArrayList al = (ArrayList) sessAttributes.get(FRAGMENTLIST);
			if (al.size() >= NUMBER_OF_FRAGMENTS && !((boolean) sessAttributes.get(HEARDALLFRAGMENTS))) {
				setSpeech(speechUtils.getString("heardAllFragments"));

				// this used to be a trigger that ended the session below
				sessAttributes.put(HEARDALLFRAGMENTS, true);
				setReprompt(speechUtils.getString("chooseContinueNoAffect"));
			}

			if (al.size() < NUMBER_OF_FRAGMENTS) {
				int fragmentIndex = 0;
				do {
					fragmentIndex = randInt(0, NUMBER_OF_FRAGMENTS - 1);
				}
				while (al.contains(fragmentIndex));

				al.add(fragmentIndex);
				sessAttributes.put(FRAGMENTLIST, al);
			}

			// check to see if the speaker has asked about a fragment-name 'thing'
			String thing = (String) sessAttributes.get(THING);
			// is only set to non-empty if the fragment name was indeed asked about
			if (!"".equals(thing) && FRAGMENTNAME_MAP.get(thing) != null) {
				int thingFragmentNumber = FRAGMENTNAME_MAP.get(thing);
				if (thingFragmentNumber > NOT_YET_GREETED && thingFragmentNumber <= NUMBER_OF_FRAGMENTS) {
					sessAttributes.put(FRAGMENTINDEX, thingFragmentNumber);
					sessAttributes.put(THING, "");
				}
			}

			setSpeech(fragments[(int) sessAttributes.get(FRAGMENTINDEX)]
					+ speechUtils.getString("chooseContinueNoAffect"));

			setInterruptable(true);

			if (intentName.equals("PleaseContinueIntent")) {
				setCardTitle(speechUtils.getString("pleaseContinueCardTitle"));
				if ((int) sessAttributes.get(FRAGMENTINDEX) > NOT_YET_GREETED && randInt(0, 3) == 0) {
					setSpeech(speechUtils.getString("preSpeechFeelings") + getSpeech());
				}
				else
					setSpeech(speechUtils.getString("pleaseContinuePreSpeech") + getSpeech());
			}
		}
	}

}
