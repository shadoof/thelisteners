package listeners.handlers;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.Attributes.LASTINTENT;
import static listeners.model.Attributes.LISTENERSAFFECT;
import static listeners.model.Attributes.PERSISTENCE;
import static listeners.model.Attributes.READSOFAR;
import static listeners.model.Attributes.getRandomAffect;
import static listeners.model.Attributes.sessAttributes;
import static listeners.model.Constants.DEV;
import static listeners.model.Constants.DIALOG_INTENTS;
import static listeners.model.Constants.NUMBER_OF_READABLES;
import static listeners.model.Constants.locale;
import static listeners.model.Constants.speechUtils;
import static listeners.model.LangConstants.dateString;
import static listeners.util.Utils.randInt;

import java.util.Map;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

import listeners.l10n.L10nSpeech;
import listeners.util.ResponseFinisher;
import listeners.util.UnknownIntentException;

public class LsnrsIntentResponse implements LsnrsResponse {

	protected HandlerInput input;
	protected IntentRequest intentRequest;
	protected Intent intent;
	protected String will;
	protected String intentName;

	LsnrsIntentResponse(HandlerInput input, String will) {

		this.input = input;
		this.intentRequest = (IntentRequest) input.getRequestEnvelope()
				.getRequest();
		this.intent = intentRequest.getIntent();
		this.intentName = intent.getName();
		this.will = will;
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
		if ("firstEncounter".equals(will)) {
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
				sessAttributes.put(AFFECT, getRandomAffect());
				// and a third of these times make listeners’ affect match
				if (randInt(0, 2) == 0) sessAttributes.put(LISTENERSAFFECT, sessAttributes.get(AFFECT));
			}
		}
		
		// filter out dialog intents
		if (DIALOG_INTENTS.contains(intentName)) {
			return new LsnrsDialogIntentResponse(input, will).getResponse();
		}
		
		// filter out slotted intents
		Map<String, Slot> slots = intent.getSlots();
		boolean slotted = (slots == null) ? false : !slots.isEmpty();
		if (slotted) {
			return new LsnrsSlottedIntentResponse(input, will).getResponse();
		}

		// if we adhere to the convention that there are l10n class bundles
		// for ALL simple intentNames we need no ‘switch’ or ‘else if’ logic here.
		// we just load a bundle with the intent.getName():
		L10nSpeech ls = null;
		try {
			ls = (L10nSpeech) ResourceBundle.getBundle("listeners.l10n." + intentName, locale);
		}
		catch (Exception e) {
			if (e instanceof MissingResourceException) {
				// we did not find a simple intent in 110n:
				// and must deal separately with intents that
				// need to know where they are in the session, etc.
				// NB: any firstEncounter preamble is NOT passed
				return new LsnrsContinueIntentResponse(input, will).getResponse();
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
		String ct = DEV ? intentName + " - " + dateString : ls.getCardTitle();

		sessAttributes.put(LASTINTENT, intentName);
		return input.getResponseBuilder()
				.withSpeech(rf.getSpeech())
				.withReprompt(rf.getReprompt())
				.withSimpleCard(ls.getCardTitle(), rf.getCardText())
				.withShouldEndSession(false)
				.build();
	}

}
