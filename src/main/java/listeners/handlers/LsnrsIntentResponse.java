package listeners.handlers;

import static listeners.model.Attributes.AFFECT;
import static listeners.model.Attributes.GUYZSPEECHINDEX;
import static listeners.model.Attributes.HEARDNO;
import static listeners.model.Attributes.LASTINTENT;
import static listeners.model.Attributes.LISTENERSAFFECT;
import static listeners.model.Attributes.SPEAKGUYZCONFIRMED;
import static listeners.model.Attributes.sessionAttributes;
import static listeners.model.Constants.DEV;
import static listeners.model.Constants.NO_MORE;
import static listeners.model.Constants.NUMBER_OF_GUYZ;
import static listeners.model.Constants.NUMBER_OF_GUYZ_PER_BATCH;
import static listeners.model.Constants.attributes;
import static listeners.model.Constants.locale;
import static listeners.model.Constants.speechUtils;
import static listeners.model.LangConstants.dateString;
import static listeners.util.ConstantUtils.info;
import static listeners.util.ConstantUtils.randInt;

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
	}
	
	LsnrsIntentResponse() {
		
	}

	@Override
	public Optional<Response> getResponse()
			throws UnknownIntentException {

		String preamble = ("firstEncounter".equals(relationship)) ? speechUtils.getString("getPreamble")
				: "";

		// generating variety here (needs assessing for actual effect) TODO
		// if affect for the session is set
		if (!"".equals(sessionAttributes.get(AFFECT))) {
			// change affect roughly 1 of 3 intent requests ...
			if (randInt(0, 2) == 0) {
				sessionAttributes.put(AFFECT, attributes.getRandomAffect());
				// and a third of these times make listeners' affect match
				if (randInt(0, 2) == 0) sessionAttributes.put(LISTENERSAFFECT, sessionAttributes.get(AFFECT));
			}
		}

		// The following code block is based on Listeners 2.x,
		// hacking a 2-stage dialog (3) ways for a few intents.
		// should be possible to replace this with the new Dialog interface TODO
		// NB: confirmationStatus is already a field of the Intent class !
		// (1)
		if (("SpeakGuyzIntent".equals((String) sessionAttributes.get(LASTINTENT)))
				&& "ContinueIntent".equals(intent.getName())) {
			sessionAttributes.put(SPEAKGUYZCONFIRMED, true);
			intent = Intent.builder()
					.withName("SpeakGuyzIntent")
					.build();
			if ((int) sessionAttributes.get(GUYZSPEECHINDEX) >= (NUMBER_OF_GUYZ - NUMBER_OF_GUYZ_PER_BATCH))
				sessionAttributes.put(LASTINTENT, "");
		}
		else if ("NoIntent".equals(intent.getName()) || "ThankYouNoIntent".equals(intent.getName())) {
			sessionAttributes.put(SPEAKGUYZCONFIRMED, false);
			sessionAttributes.put(LASTINTENT, "");
		}
		// (2) make it a little more difficult to leave session unintentionally
		// reset HEARD_NO to false unless we really just heard one:
		if ((boolean) sessionAttributes.get(HEARDNO) && !NO_MORE.contains(intent.getName())) {
			sessionAttributes.put(HEARDNO, false);
		}
		// (3) one two-stage also done with this in 2.x
		Boolean heardPlease = false;

		// actual reponse text construction block:
		// clear cache before building l10n speeches
		ResourceBundle.clearCache();
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
				LsnrsContinueIntentResponse lcir = new LsnrsContinueIntentResponse();
				info("GETS HERE with input: " + input + " and relationship: " + relationship);
				return new LsnrsContinueIntentResponse().getResponse();
			}
		}

		ResponseFinisher rf = ResponseFinisher.builder()
				// possible firstEncounter preamble
				// when there is a simple intent response
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
				.withSimpleCard(ct, rf.getCardText())
				.withShouldEndSession(false)
				.build();
	}

}
