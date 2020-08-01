package listeners.handlers;

import static listeners.model.Attributes.*;
import static listeners.model.Constants.*;
import static listeners.model.LangConstants.FRAGMENTNAME_MAP;
import static listeners.model.LangConstants.dateString;
import static listeners.util.Utils.info;
import static listeners.util.Utils.randInt;

import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;

import listeners.l10n.L10nSpeech;
import listeners.util.ResponseFinisher;
import listeners.util.UnknownIntentException;

public class LsnrsIntentResponse implements LsnrsResponse {

	protected HandlerInput input;
	protected IntentRequest intentRequest;
	protected Intent intent;
	protected String relationship;

	LsnrsIntentResponse(HandlerInput input, String relationship) {

		this.input = input;
		this.intentRequest = (IntentRequest) input.getRequestEnvelope()
				.getRequest();
		this.intent = intentRequest.getIntent();
		this.relationship = relationship;
	}

	@Override
	public Optional<Response> getResponse() throws UnknownIntentException {

	  // every intent increases readSoFar and this
		// regulates GUYZ interventions & degradation
		// info("@LsnrsIntentResponse, sessAttributes: " + sessAttributes);
		if ((int) sessAttributes.get(READSOFAR) < NUMBER_OF_READABLES) {
			sessAttributes.put(READSOFAR, (int) sessAttributes.get(READSOFAR) + 1);
		}

		// *** 4. ***
		String preamble = "";
		if ("firstEncounter".equals(relationship)) {
			preamble = speechUtils.getString("getPreamble");
			sessAttributes.put(PERSISTENCE, "session");
		}
		
		// *** 5. and 6. *** treated the same
		// generating variety here (needs assessing for actual effect)
		// TODO (longer term)
		// if affect for the session is set
		if (!"".equals(sessAttributes.get(AFFECT))) {
			// change affect roughly 1 of 3 intent requests ...
			if (randInt(0, 2) == 0) {
				sessAttributes.put(AFFECT, attributes.getRandomAffect());
				// and a third of these times make listeners’ affect match
				if (randInt(0, 2) == 0) sessAttributes.put(LISTENERSAFFECT, sessAttributes.get(AFFECT));
			}
		}
		
		// filter out dialog intents
		if (DIALOG_INTENTS.contains(intent.getName())) {
			return new LsnrsDialogIntentResponse(input, relationship).getResponse();
		}
		
		// filter out slotted intents
		// just for convenience
		// since following kludge code does not apply to slotted intents
		if (intent.getSlots() != null) {
			return new LsnrsSlottedIntentResponse(input, relationship).getResponse();
		}

		// if we adhere to the convention that there are l10n class bundles
		// for ALL simple intentNames we need no ‘switch’ or ‘else if’ logic here.
		// we just load a bundle with the intent.getName():
		L10nSpeech ls = null;
		try {
			ls = (L10nSpeech) ResourceBundle.getBundle("listeners.l10n." + intent.getName(), locale);
		}
		catch (Exception e) {
			if (e instanceof MissingResourceException) {
				// we did not find a simple intent in 110n:
				// and must deal separately with intents that
				// need to know where they are in the session, etc.
				// NB: any firstEncounter preamble is NOT passed
				return new LsnrsContinueIntentResponse(input, relationship).getResponse();
			}
		}

		// finish assembly of response for an unslotted simple response
		ResponseFinisher rf = ResponseFinisher.builder()
				// the possible firstEncounter preamble
				.withPreamble(preamble)
				.withSpeech(ls.getSpeech())
				.withPostSpeechPrompt(ls.getPostSpeechPrompt())
				.withReprompt(ls.getReprompt())
				.build();

		// while developing replace cardTitle with intentName - dated
		String ct = DEV ? intent.getName() + " - " + dateString : ls.getCardTitle();

		sessAttributes.put(LASTINTENT, intent.getName());
		return input.getResponseBuilder()
				.withSpeech(rf.getSpeech())
				.withReprompt(rf.getReprompt())
				.withSimpleCard(ls.getCardTitle(), rf.getCardText())
				.withShouldEndSession(false)
				.build();
	}

}
