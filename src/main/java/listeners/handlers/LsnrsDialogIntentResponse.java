package listeners.handlers;

import static listeners.model.Attributes.*;
import static listeners.model.Constants.*;
import static listeners.model.Constants.*;
import static listeners.model.LangConstants.*;
import static listeners.util.Utils.*;

import java.util.ArrayList;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.DialogState;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentConfirmationStatus;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;

import listeners.util.ResponseFinisher;
import listeners.util.UnknownIntentException;

public class LsnrsDialogIntentResponse extends LsnrsIntentResponse implements LsnrsResponse {

	LsnrsDialogIntentResponse(HandlerInput input, String relationship) {

		super(input, relationship);
	}

	private boolean isEnd = false;

	@Override
	public Optional<Response> getResponse() throws UnknownIntentException {

		String intentName = intent.getName();
		InnerResponse ir = new InnerResponse();
		ResponseFinisher rf;

		// TODO (longer term)
		// more can be done with this could:
		// as it is: auto delegation is desabled
		// to allow custom and l10n confirmations
		// reference is here:
		// This is all done properly with:
		// https://developer.amazon.com/en-US/docs/alexa/custom-skills/dialog-interface-reference.html
		// note: auto confirmation is done (int the interaction model) with
		// objects that are Confirm.Indent.<numberID> suggesting
		// that one can also game this slightly.
		info("@LsnrsDialogIntentResponse, " + intentName);
		switch (intentName) {
			case "AskPersistenceIntent":
				ir = (InnerResponse) speechUtils.getObject(intentName);

				if (intent.getConfirmationStatus() == IntentConfirmationStatus.DENIED) {
					sessAttributes.put(PERSISTENCE, "ask");
				}
				
				// attributesManager.setPersistentAttributes(sessAttributes);
				attributesManager.savePersistentAttributes();
				
				intent = Intent.builder()
						.withName("AMAZON.StopIntent")
						.build();
				return input.getResponseBuilder()
						.addDelegateDirective(intent)
						.build();
				
			case "AskStartOverIntent":
				info("@LsnrsDialogIntentResponse, " + intentName + ", confirmation: "
						+ intent.getConfirmationStatus());

				ir = (InnerResponse) speechUtils.getObject(intentName);

				rf = ResponseFinisher.builder()
						.withSpeech(ir.getSpeech())
						.build();

				if (intent.getConfirmationStatus() == IntentConfirmationStatus.NONE) {
					info(intentName + " no confirmation.");
					return input.getResponseBuilder()
							.addConfirmIntentDirective(intent)
							.withSpeech(rf.getSpeech())
							.build();
				}

				if (intent.getConfirmationStatus() == IntentConfirmationStatus.CONFIRMED) {
					info(intentName + " confirmed.");

					sessAttributes = attributes.initSessionAttributes();
					attributesManager.setSessionAttributes(sessAttributes);

					ir.setSpeech(speechUtils.getString("startOverConfirmed")); // TODO
				}

				if (intent.getConfirmationStatus() == IntentConfirmationStatus.DENIED) {
					info(intentName + " denied.");

					ir.setSpeech(speechUtils.getString("startOverDenied")); // TODO
				}
				break;
			case "NoIntent":
			case "ThanksNoIntent":

				ir = (InnerResponse) speechUtils.getObject(intentName);

				String preSpeech = (intentName.contentEquals("ThanksNoIntent"))
						? speechUtils.getString("yourWelcome")
						: "";

				rf = ResponseFinisher.builder()
						.withSpeech(preSpeech + speechUtils.getString("reallyWantToAbandon"))
						.build();

				if (intent.getConfirmationStatus() == IntentConfirmationStatus.NONE) {
					return input.getResponseBuilder()
							.addConfirmIntentDirective(intent)
							.withSpeech(rf.getSpeech())
							.build();
				}

				if (intent.getConfirmationStatus() == IntentConfirmationStatus.CONFIRMED) {
					info("@LsnrsDialogIntentResponse, noIntent CONFIRMED: " + intentRequest.getDialogState());
					Intent askPersistenceIntent = Intent.builder()
							.withName("AskPersistenceIntent")
							.withConfirmationStatus(IntentConfirmationStatus.CONFIRMED)
							.build();
					return input.getResponseBuilder()
							.addDelegateDirective(askPersistenceIntent)
							.build();
				}

				info("@LsnrsDialogIntentResponse, noIntent DENIED dialogState: "
						+ intentRequest.getDialogState());
				break;
			case "SpeakGuyzIntent":
				info("@LsnrsDialogIntentResponse, " + intentName);

				ir = new InnerResponse();
				ir.setCardTitle(speechUtils.getString("speakGuyzCardTitle"));

				if ((int) sessAttributes.get(GUYZINDEX) > NUMBER_OF_GUYZ) {
					ir.setSpeech(ir.getSpeech() + speechUtils.getString("getGuyzAreGone"));
					ir.setReprompt(speechUtils.getString("chooseContinue"));
				}
				else if (intent.getConfirmationStatus() == IntentConfirmationStatus.NONE) {
					info("@LsnrsDialogIntentResponse, SpeakGuyzIntent NONE dialogState: "
							+ intentRequest.getDialogState());

					rf = ResponseFinisher.builder()
							.withSpeech(speechUtils.getString("getReallyWantGuyz"))
							.build();

					return input.getResponseBuilder()
							.addConfirmIntentDirective(intent)
							.withSpeech(rf.getSpeech())
							.build();
				}
				else if (intent.getConfirmationStatus() == IntentConfirmationStatus.CONFIRMED) {
					Intent guyzSpeechIntent = Intent.builder()
							.withName("GuyzSpeechIntent")
							.withConfirmationStatus(IntentConfirmationStatus.CONFIRMED)
							.build();

					return input.getResponseBuilder()
							.addDelegateDirective(guyzSpeechIntent)
							.build();
				}
				else if (intent.getConfirmationStatus() == IntentConfirmationStatus.DENIED) {
					info("@LsnrsDialogIntentResponse, SpeakGuyzIntent DENIED dialogState: "
							+ intentRequest.getDialogState());
					ir.setSpeech(speechUtils.getString("noToGuyzSpeech"));
				}
				break;
			case "GuyzSpeechIntent":
				info("@LsnrsDialogIntentResponse, " + intentName + ", confirmation: "
						+ intent.getConfirmationStatus());

				if (intent.getConfirmationStatus() == IntentConfirmationStatus.CONFIRMED) {
					info("@LsnrsDialogIntentResponse, GuyzSpeechIntent NONE dialogState: "
							+ intentRequest.getDialogState());

					ir = new GuyzSpeech();

					rf = ResponseFinisher.builder()
							.withSpeech(ir.getSpeech())
							.withPostSpeechPrompt(speechUtils.getString("moreGuyz"))
							.build();

					return input.getResponseBuilder()
							.addConfirmIntentDirective(intent)
							.withSpeech(rf.getSpeech())
							.build();
				}
				else if (intent.getConfirmationStatus() == IntentConfirmationStatus.DENIED) {
					ir = new InnerResponse();
					ir.setSpeech(speechUtils.getString("noMoreGuyzSpeech"));
				}
				break;
			default:
				// no intent name case was matched
				throw new UnknownIntentException(
						"@LsnrsDialogIntentResponse, unknown intent: " + intent.getName());
		}

		sessAttributes.put(LASTINTENT, intentName);

		rf = ResponseFinisher.builder()
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

	private class GuyzSpeech extends InnerResponse {

		GuyzSpeech() {

			setCardTitle(speechUtils.getString("guyzSpeechCardTitle"));

			int currentIndex;
			int guyzIndex = (int) sessAttributes.get(GUYZINDEX);
			for (currentIndex = guyzIndex; currentIndex < guyzIndex
					+ (5 - ((guyzIndex - 1) % 5)); currentIndex++) {
				setSpeech(getSpeech() + speechUtils.getString("pathToGuyzAudio")
						+ String.format("%03d", currentIndex) + ".mp3\" />");
			}
			sessAttributes.put(GUYZINDEX, currentIndex); // guyzIndex = i;
			// leave out a group of five in performances
			if (PERFORMANCE) {
				if ((int) sessAttributes.get(GUYZINDEX) > 20 && (int) sessAttributes.get(GUYZINDEX) < 26) {
					sessAttributes.put(GUYZINDEX, 26);
				}
			}
			setReprompt(speechUtils.getString("chooseContinueNoAffect"));
		}
	}
}
