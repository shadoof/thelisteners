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

	protected static HandlerInput input;
	protected static IntentRequest intentRequest;
	protected static Intent intent;
	protected static String relationship;

	LsnrsIntentResponse(HandlerInput input, String relationship) {

		this.input = input;
		intentRequest = (IntentRequest) input.getRequestEnvelope()
				.getRequest();
		intent = intentRequest.getIntent();
		this.relationship = relationship;
		// while DEV
		if (intent.getSlots() != null) {
			info("@LsnrsIntentResponse, slots: " + intent.getSlots() + (intent.getSlots()
					.isEmpty() ? " are empty" : ""));
		}
		else
			info("@LsnrsIntentResponse, intent.getSlots() is null ");
	}

	LsnrsIntentResponse() {

	}

	@Override
	public Optional<Response> getResponse() throws UnknownIntentException {

	  // every intent increases readSoFar and this
		// regulates GUYZ interventions & degradation
		if ((int) sessAttributes.get(READSOFAR) < NUMBER_OF_READABLES) {
			sessAttributes.justPut(READSOFAR, (int) sessAttributes.get(READSOFAR) + 1);
		}

		// *** 4. ***
		String preamble = ("firstEncounter".equals(relationship)) ? speechUtils.getString("getPreamble")
				: "";
		
		// *** 5. and 6. *** treated the same
		// generating variety here (needs assessing for actual effect) TODO
		// if affect for the session is set
		if (!"".equals(sessAttributes.get(AFFECT))) {
			// change affect roughly 1 of 3 intent requests ...
			if (randInt(0, 2) == 0) {
				sessAttributes.put(AFFECT, attributes.getRandomAffect());
				// and a third of these times make listeners' affect match
				if (randInt(0, 2) == 0) sessAttributes.put(LISTENERSAFFECT, sessAttributes.get(AFFECT));
			}
		}
		
		// filtering out slotted intents now
		// just for convenience
		// since following kludge code does not apply to slotted intents
		if (intent.getSlots() != null) {
			return new LsnrsSlottedIntentResponse().getResponse();
		}

		// The following code block is based on Listeners 2.x,
		// hacking a 2-stage dialog (3) ways for a few intents.
		// should be possible to replace this with the new Dialog interface TODO
		// NB: confirmationStatus is already a field of the Intent class !
		// (1)
		if (("SpeakGuyzIntent".equals((String) sessAttributes.get(LASTINTENT)))
				&& "ContinueIntent".equals(intent.getName())) {
			sessAttributes.put(SPEAKGUYZCONFIRMED, true);
			intent = Intent.builder()
					.withName("SpeakGuyzIntent")
					.build();
			if ((int) sessAttributes.get(GUYZSPEECHINDEX) >= (NUMBER_OF_GUYZ - NUMBER_OF_GUYZ_PER_BATCH))
				sessAttributes.put(LASTINTENT, "");
		}
		else if ("NoIntent".equals(intent.getName()) || "ThanksNoIntent".equals(intent.getName())) {
			sessAttributes.put(SPEAKGUYZCONFIRMED, false);
			sessAttributes.put(LASTINTENT, "");
		}
		// (2) make it a little more difficult to leave session unintentionally
		// reset HEARD_NO to false unless we really just heard one:
		info("@LsnrsIntentResponse, sessAttributes.get(HEARDNO): " + sessAttributes.get(HEARDNO));
		if ((boolean) sessAttributes.get(HEARDNO) && !NO_MORE.contains(intent.getName())) {
			sessAttributes.put(HEARDNO, false);
		}
		// (3) one two-stage also done with this in 2.x
		Boolean heardPlease = false;

		// if we adhere to the convention that there are l10n class bundles
		// for ALL simple intentNames we need no 'switch' or 'else if' logic here.
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
				return new LsnrsContinueIntentResponse().getResponse();
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

		return input.getResponseBuilder()
				.withSpeech(rf.getSpeech())
				.withReprompt(rf.getReprompt())
				.withSimpleCard(ls.getCardTitle(), rf.getCardText())
				.withShouldEndSession(false)
				.build();
	}

}
