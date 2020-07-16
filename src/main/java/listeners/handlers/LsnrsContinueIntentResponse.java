package listeners.handlers;

import java.util.ArrayList;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

import listeners.util.ResponseFinisher;
import listeners.util.UnknownIntentException;

import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;
import static listeners.util.ConstantUtils.info;
import static listeners.util.ConstantUtils.randInt;
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

		info("GETS HERE with intent name: " +  intent.getName());

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
				if ((int) sessionAttributes.get(FRAGMENTINDEX) > 0 && randInt(1, 4) == 1) {
					preSpeech = speechUtils.getString("preSpeechFeelings");
				}
				NextFragmentResponse nfr = new NextFragmentResponse();
				
				ResponseFinisher rf = ResponseFinisher.builder()
						.withSpeech(preSpeech + nfr.speech)
						.withReprompt(nfr.reprompt)
						.build();

				return input.getResponseBuilder()
						.withSpeech(rf.getSpeech())
						.withReprompt(rf.getReprompt())
						.withSimpleCard(cardTitle, rf.getCardText())
						.withShouldEndSession(false)
						.build();
		}

		// no intent name case was matched
		throw new UnknownIntentException("@LsnrsContinueIntentResponse, nnknown intent: " + intent.getName()); 
	}

	private class NextFragmentResponse {

		String speech = "";
		String reprompt = "";

		NextFragmentResponse() {

			// build variant fragments just before they're needed:
			buildFragments();

			sessionAttributes.put(FRAGMENTINDEX, randInt(0, NUMBER_OF_FRAGMENTS - 1));

			ArrayList al = (ArrayList) sessionAttributes.get(FRAGMENTLIST);
			if (al.size() >= NUMBER_OF_FRAGMENTS && !((boolean) sessionAttributes.get(HEARDALLFRAGMENTS))) {
				speech += speechUtils.getString("heardAllFragments");

				// this used to be a trigger that ended the session below
				sessionAttributes.put(HEARDALLFRAGMENTS, true);
				reprompt = speechUtils.getString("chooseContinueNoAffect");
			}

			if (al.size() < NUMBER_OF_FRAGMENTS) {
				int fragmentIndex = 0;
				do {
					fragmentIndex = randInt(0, NUMBER_OF_FRAGMENTS - 1);
				}
				while (al.contains(fragmentIndex));

				al.add(fragmentIndex);
				sessionAttributes.put(FRAGMENTLIST, al);
			}

			// check to see if the speaker has asked about a fragment-name 'thing'
			String thing = (String) sessionAttributes.get(THING);
			// is only set to non-empty if the fragment name was indeed asked about
			if (!"".equals(thing)) {
				int thingFragmentNumber = FRAGMENTNAME_MAP.get(thing);
				if (thingFragmentNumber > NOT_YET_GREETED && thingFragmentNumber <= NUMBER_OF_FRAGMENTS) {
					sessionAttributes.put(FRAGMENTINDEX, thingFragmentNumber);
					sessionAttributes.put(THING, "");
				}
			}

			speech = fragments[(int) sessionAttributes.get(FRAGMENTINDEX)];
			if ("".equals(reprompt)) reprompt = speechUtils.getString("chooseContinue");		}
	}
}
