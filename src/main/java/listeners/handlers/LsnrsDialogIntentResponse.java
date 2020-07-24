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
		InnerResponse ir = null;

		switch (intentName) {
			case "NoIntent":
				info("@LsnrsDialogIntentResponse, NoIntent");
				ir = new InnerResponse();
				ir.setCardTitle(speechUtils.getString("noCardTitle"));

				// if (intentRequest.getDialogState() != DialogState.COMPLETED) {
				// return input.getResponseBuilder()
				// .addDelegateDirective(intent)
				// .build();
				// }

				if (intent.getConfirmationStatus() == IntentConfirmationStatus.CONFIRMED) {
					// [ my home-brewed dialog: ] if ((boolean) sessAttributes.get(HEARDNO)) {
					isEnd = true;
					ir.setSpeech(speechUtils.getString("getAbandonmentMessage"));
					if (attributes.isPositive((String) sessAttributes.get(AFFECT))) {
						ir.setSpeech(
								ir.getSpeech() + ("de_DE".equals(localeTag) ? s("Tschüss!", "") : s("Cheerio!", "")));
					}
					break;
				}

//				return input.getResponseBuilder()
//						.addDelegateDirective(Intent.builder()
//								.withName("OkIntent")
//								.build())
//						.build();
				
				ir.setSpeech("Still with us. ");

//				ir.setSpeech(ir.getSpeech() + speechUtils.getString("reallyWantToAbandon"));

				sessAttributes.justPut(LASTINTENT, intentName);
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

}
