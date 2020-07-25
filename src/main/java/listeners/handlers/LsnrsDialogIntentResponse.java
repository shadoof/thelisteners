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
import com.amazon.ask.model.Response;

import listeners.util.ResponseFinisher;
import listeners.util.UnknownIntentException;

public class LsnrsDialogIntentResponse extends LsnrsIntentResponse implements LsnrsResponse {

	private boolean isEnd = false;

	LsnrsDialogIntentResponse() {

	}

	@Override
	public Optional<Response> getResponse() throws UnknownIntentException {

		String intentName = intent.getName();
		InnerResponse ir = (InnerResponse) speechUtils.getObject(intentName);
		ResponseFinisher rf;

		// TODO
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
			case "NoIntent":
			case "ThanksNoIntent":

				info("@LsnrsDialogIntentResponse, " + intentName);

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
					info("@LsnrsDialogIntentResponse, CONFIRMED dialogState: " + intentRequest.getDialogState());
					intent = Intent.builder()
							.withName("AMAZON.StopIntent")
							.build();
					return input.getResponseBuilder()
							.addDelegateDirective(intent)
							.build();
				}

				info("@LsnrsDialogIntentResponse, DENIED dialogState: " + intentRequest.getDialogState());

				sessAttributes.justPut(LASTINTENT, intentName);
				break;
			default:
				// no intent name case was matched
				throw new UnknownIntentException(
						"@LsnrsContinueIntentResponse, unknown intent: " + intent.getName());
		}

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

}
