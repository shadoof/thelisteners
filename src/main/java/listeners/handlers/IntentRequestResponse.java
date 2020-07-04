package listeners.handlers;

import static listeners.model.LangConstants.*;
import static listeners.util.ConstantUtils.*;
import static listeners.model.Constants.*;
import static listeners.model.Attributes.*;

import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;

import listeners.l10n.L10nSpeech;
import listeners.l10n.Welcome;
import listeners.util.ResponseFinisher;
import listeners.util.SpeechUtils;

public class IntentRequestResponse extends RequestResponse implements RequestResponsible {

	// this RequestResponse is for straightforward responses,
	// no slots, no dialog (confirmation) needed

	IntentRequestResponse(Map<String, Object> persistentAttributes, Map<String, Object> sessionAttributes) {

		super(persistentAttributes, sessionAttributes);
	}

	@Override
	public Optional<Response> getResponse(HandlerInput input, String relationship) {

		if ("firstEncounter".equals(relationship)) {
			// build first response TODO
			// add intro and 'trigger warning' in Alexa's voice TODO
		}

		IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();
		Intent intent = intentRequest.getIntent();

		// generating variety here (needs assessing for actual effect) TODO
		// if affect for the session is set
		if (!"".equals(sessionAttributes.get(AFFECT))) {
			// change affect roughly 1 of 3 intent requests ...
			if (randInt(0, 2) == 0) {
				sessionAttributes.put(AFFECT, getRandomAffect());
				// and a third of these times make listeners affect match
				if (randInt(0, 2) == 0) sessionAttributes.put(LISTENERSAFFECT, sessionAttributes.get(AFFECT));
			}
		}

		// The following code block is based on Listeners 2.x,
		// hacking a 2-stage dialog (3) ways for a few intents.
		// should be possible to replace this with the new Dialog interface TODO
		// NB: confirmationStatus is already a field of the Intent class !
		// (1)
		if (("SpeakGuyzIntent".equals((String) sessionAttributes.get(LASTINTENT))) && "ContinueIntent".equals(intent.getName())) {
			sessionAttributes.put(SPEAKGUYZCONFIRMED, true);
			intent = Intent.builder().withName("SpeakGuyzIntent").build();
			if ((int) sessionAttributes.get(GUYZSPEECHINDEX) >= (NUMBER_OF_GUYZ - NUMBER_OF_GUYZ_PER_BATCH)) sessionAttributes.put(LASTINTENT, "");
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
		Boolean heardPlease = false; // TODO
		
		// actual reponse text construction block:
		// clear cache before building l10n speeches
		ResourceBundle.clearCache();
		// if we adhere to the convention that there are l10n class bundles
		// for ALL simple intentNames we need no 'switch' or 'else if' logic here.
		// we just load a bundle with the intent.getName():
		L10nSpeech ls = (L10nSpeech) ResourceBundle.getBundle("listeners.l10n." + intent.getName(), locale);
		SpeechUtils su = new SpeechUtils(locale);
		ResponseFinisher rf = new ResponseFinisher(localeTag, ls.getSpeech(), ls.getPostSpeechPrompt(), ls.getReprompt());
		// while developing replace cardTitle with intentName - dated
		String ct = DEV ? intent.getName() + " - " + rf.getDateString() : ls.getCardTitle();

		return input.getResponseBuilder().
				withSpeech(rf.getSpeech()).
				withReprompt(rf.getReprompt()).
				withSimpleCard(ct, rf.getCardText()).
				withShouldEndSession(false).
				build();
	}

}
