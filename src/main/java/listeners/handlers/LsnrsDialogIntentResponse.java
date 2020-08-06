package listeners.handlers;

import static listeners.model.Attributes.GUYZINDEX;
import static listeners.model.Attributes.LASTINTENT;
import static listeners.model.Attributes.PERSISTENCE;
import static listeners.model.Attributes.sessAttributes;
import static listeners.model.Constants.NUMBER_OF_GUYZ;
import static listeners.model.Constants.PERFORMANCE;
import static listeners.model.Constants.attributes;
import static listeners.model.Constants.attributesManager;
import static listeners.model.Constants.speechUtils;
import static listeners.util.Utils.info;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentConfirmationStatus;
import com.amazon.ask.model.Response;

import listeners.util.ResponseFinisher;
import listeners.util.UnknownIntentException;

public class LsnrsDialogIntentResponse extends LsnrsIntentResponse implements LsnrsResponse {

	LsnrsDialogIntentResponse(HandlerInput input, String will) {

		super(input, will);
	}

	private boolean isEnd = false;

	@Override
	public Optional<Response> getResponse() throws UnknownIntentException {

		String intentName = intent.getName();
		String postSpeechPrompt = "";
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
		switch (intentName) {
			case "AskPersistenceIntent":
				info("@LsnrsDialogIntentResponse, " + intentName);
				info("sessAttributes: " + sessAttributes);
				info("last intent: " + sessAttributes.get(LASTINTENT));
				info("confirmation: " + intent.getConfirmationStatus());
				info("will: " + will);

				ir = (InnerResponse) speechUtils.getObject(intentName);

				if (intent.getConfirmationStatus() == IntentConfirmationStatus.DENIED) {
					sessAttributes.put(PERSISTENCE, "forget");
					info("PERSISTENCE is now: " + sessAttributes.get(PERSISTENCE));
				}
				else {
					sessAttributes.put(PERSISTENCE, "remember");
					info("PERSISTENCE is now: " + sessAttributes.get(PERSISTENCE));
				}

				if (intentName.equals(sessAttributes.get(LASTINTENT))) { // || "forget".equals(will)

					Intent updatedIntent = Intent.builder()
							.withName("AMAZON.StopIntent")
							.build();

					return input.getResponseBuilder()
							.addDelegateDirective(updatedIntent)
							.build();
				}
				else {
					rf = ResponseFinisher.builder()
							.withSpeech(ir.getSpeech())
							.build();

					info("@LsnrsDialogIntentResponse, confirming AskPersistenceIntent and setting lastIntent to: "
							+ intentName);
					sessAttributes.put(LASTINTENT, intentName);
					return input.getResponseBuilder()
							.addConfirmIntentDirective(intent)
							.withSpeech(rf.getSpeech())
							.build();
				}

			case "AskStartOverIntent":
				info("@LsnrsDialogIntentResponse, " + intentName + ", confirmation: "
						+ intent.getConfirmationStatus());

				ir = (InnerResponse) speechUtils.getObject(intentName);

				rf = ResponseFinisher.builder()
						.withSpeech(ir.getSpeech())
						.build();

				if (intent.getConfirmationStatus() == IntentConfirmationStatus.NONE) {
					info(intentName + " no confirmation.");
					sessAttributes.put(LASTINTENT, intentName);
					return input.getResponseBuilder()
							.addConfirmIntentDirective(intent)
							.withSpeech(rf.getSpeech())
							.build();
				}

				if (intent.getConfirmationStatus() == IntentConfirmationStatus.CONFIRMED) {
					info(intentName + " confirmed.");

					sessAttributes = attributes.initSessionAttributes();
					attributesManager.setSessionAttributes(sessAttributes);

					ir.setSpeech(speechUtils.getString("startOverConfirmed"));
				}

				if (intent.getConfirmationStatus() == IntentConfirmationStatus.DENIED) {
					info(intentName + " denied.");

					ir.setSpeech(speechUtils.getString("startOverDenied"));
				}
				break;
			case "NoIntent":
			case "ThanksNoIntent":
				info("@LsnrsDialogIntentResponse, " + intentName + ", confirmation: "
						+ intent.getConfirmationStatus());

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
					info("@LsnrsDialogIntentResponse, noIntent CONFIRMED");
					Intent askPersistenceIntent = Intent.builder()
							.withName("AskPersistenceIntent")
							.build();

					info("@LsnrsDialogIntentResponse, delegating to AskPersistenceIntent with lastIntent: "
							+ intentName);
					sessAttributes.put(LASTINTENT, intentName);
					return input.getResponseBuilder()
							.addDelegateDirective(askPersistenceIntent)
							.build();
				}

				info("@LsnrsDialogIntentResponse, noIntent DENIED");
				
				postSpeechPrompt = speechUtils.getString("chooseContinueNoAffect");
				
				break;
			case "SpeakGuyzIntent":

				ir = new InnerResponse();
				ir.setCardTitle(speechUtils.getString("speakGuyzCardTitle"));

				if ((int) sessAttributes.get(GUYZINDEX) > NUMBER_OF_GUYZ) {
					ir.setSpeech(ir.getSpeech() + speechUtils.getString("getGuyzAreGone"));
					ir.setReprompt(speechUtils.getString("chooseContinue"));
				}
				else if (intent.getConfirmationStatus() == IntentConfirmationStatus.NONE) {
					info("@LsnrsDialogIntentResponse, SpeakGuyzIntent NONE");

					rf = ResponseFinisher.builder()
							.withSpeech(speechUtils.getString("getReallyWantGuyz"))
							.build();

					return input.getResponseBuilder()
							.addConfirmIntentDirective(intent)
							.withSpeech(rf.getSpeech())
							.build();
				}
				else if (intent.getConfirmationStatus() == IntentConfirmationStatus.CONFIRMED) {
					Intent updatedIntent = Intent.builder()
							.withName("GuyzSpeechIntent")
							.withConfirmationStatus(IntentConfirmationStatus.CONFIRMED)
							.build();

					sessAttributes.put(LASTINTENT, intentName);
					return input.getResponseBuilder()
							.addDelegateDirective(updatedIntent)
							.build();
				}
				else if (intent.getConfirmationStatus() == IntentConfirmationStatus.DENIED) {
					info("@LsnrsDialogIntentResponse, SpeakGuyzIntent DENIED");
					ir.setSpeech(speechUtils.getString("noToGuyzSpeech"));
				}
				break;
			case "GuyzSpeechIntent":
				info("@LsnrsDialogIntentResponse, " + intentName + ", confirmation: "
						+ intent.getConfirmationStatus());

				if (intent.getConfirmationStatus() == IntentConfirmationStatus.CONFIRMED) {
					info("@LsnrsDialogIntentResponse, GuyzSpeechIntent NONE");

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
					ir = (InnerResponse) speechUtils.getObject("noMoreGuyz");
				}
				break;
			default:
				// no intent name case was matched
				throw new UnknownIntentException(
						"@LsnrsDialogIntentResponse, unknown intent: " + intent.getName());
		}

		rf = ResponseFinisher.builder()
				.withSpeech(ir.getSpeech())
				.withPostSpeechPrompt(postSpeechPrompt)
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
