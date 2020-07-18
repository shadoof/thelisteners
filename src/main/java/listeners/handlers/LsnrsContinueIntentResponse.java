package listeners.handlers;

import java.util.ArrayList;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

import listeners.util.ResponseFinisher;
import listeners.util.UnknownIntentException;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.Utils.info;
import static listeners.util.Utils.randInt;
import static listeners.model.LangConstants.buildFragments;
import static listeners.model.LangConstants.fragments;
import static listeners.model.LangConstants.FRAGMENTNAME_MAP;

public class LsnrsContinueIntentResponse extends LsnrsIntentResponse implements LsnrsResponse {

	LsnrsContinueIntentResponse() {

	}

	@Override
	public Optional<Response> getResponse() throws UnknownIntentException {

		boolean heardPlease = false;
		String preSpeech = "";
		String cardTitle = "";
		InnerResponse ir = new InnerResponse();

		switch (intent.getName()) {
			case "PleaseContinueIntent":
				cardTitle = speechUtils.getString("pleaseContinueCardTitle");
				preSpeech = speechUtils.getString("pleaseContinuePreSpeech");
				heardPlease = true;
				// always fall through here:
			case "ContinueIntent":

				if (!heardPlease) {
					cardTitle = speechUtils.getString("continueCardTitle");
				}
				// do, but not always, the preSpeech
				if ((int) sessAttributes.get(FRAGMENTINDEX) > 0 && randInt(1, 4) == 1) {
					preSpeech = speechUtils.getString("preSpeechFeelings");
				}
				ir = new NextFragmentResponse();
				break;
			case "PreviousIntent":
				cardTitle = speechUtils.getString("previousCardTitle");
				// do, but not always, the preSpeech
				if ((int) sessAttributes.get(FRAGMENTINDEX) > 0 && randInt(0, 3) == 0)
					preSpeech = speechUtils.getString("preSpeechFeelings");

				// build variant fragments just before they're needed:
				buildFragments();

				int fragmentIndex = (int) sessAttributes.get(FRAGMENTINDEX) - 1;
				fragmentIndex = (fragmentIndex < 0) ? NUMBER_OF_FRAGMENTS - 1 : fragmentIndex;

				sessAttributes.justPut(FRAGMENTINDEX, fragmentIndex);

				ir.speech = fragments[fragmentIndex];
				ir.reprompt = speechUtils.getString("chooseContinue");

				// and add this to the list of fragments that have been heard
				ArrayList al = (ArrayList) sessAttributes.get(FRAGMENTLIST);
				if (!al.contains(fragmentIndex)) {
					al.add(fragmentIndex);
					sessAttributes.justPut(FRAGMENTLIST, al);
				}
				ir.postSpeechPrompt = speechUtils.getString("chooseContinueNoAffect");
				
				// TODO in ResponseFinisher:
				boolean specificFragment = true;
				break;
			default:
				// no intent name case was matched
				throw new UnknownIntentException(
						"@LsnrsContinueIntentResponse, unknown intent: " + intent.getName());
		}

		ResponseFinisher rf = ResponseFinisher.builder()
				.withSpeech(preSpeech + ir.speech)
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

	}

	private class NextFragmentResponse extends InnerResponse {

		NextFragmentResponse() {

			postSpeechPrompt = speechUtils.getString("chooseContinueNoAffect");

			// build variant fragments just before they're needed:
			buildFragments();

			sessAttributes.put(FRAGMENTINDEX, randInt(0, NUMBER_OF_FRAGMENTS - 1));

			ArrayList al = (ArrayList) sessAttributes.get(FRAGMENTLIST);
			if (al.size() >= NUMBER_OF_FRAGMENTS && !((boolean) sessAttributes.get(HEARDALLFRAGMENTS))) {
				speech += speechUtils.getString("heardAllFragments");

				// this used to be a trigger that ended the session below
				sessAttributes.put(HEARDALLFRAGMENTS, true);
				reprompt = speechUtils.getString("chooseContinueNoAffect");
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

			speech = fragments[(int) sessAttributes.get(FRAGMENTINDEX)];
			if ("".equals(reprompt)) reprompt = speechUtils.getString("chooseContinue");
		}
	}
}
