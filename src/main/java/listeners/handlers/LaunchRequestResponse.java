package listeners.handlers;

import static listeners.model.Attributes.FRAGMENTCOUNT_KEY;
import static listeners.model.Attributes.AFFECT_KEY;
import static listeners.util.ConstantUtils.info;
import static listeners.util.ConstantUtils.s;
import static listeners.model.LangConstants.locale;
import static listeners.model.LangConstants.localeTag;

import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

import listeners.l10n.Welcome;
import listeners.model.LangConstants;
import listeners.util.ResponseFinisher;
import listeners.util.SpeechUtils;

public class LaunchRequestResponse extends RequestResponse {

	public LaunchRequestResponse(Map<String, Object> persistentAttributes, Map<String, Object> sessionAttributes) {

		super(persistentAttributes, sessionAttributes);
	}

	public Optional<Response> getResponse(HandlerInput input, String relationship) {

		String preamble = "";
		if ("firstEncounter".equals(relationship)) {
				preamble = s("Unless we're mistaken, this is", "This seems to be") + "your first encounter with 'The Listeners'. ";
				preamble += "They tend to " + s("talk", "speak") + "as much " + s("if not more than", "as") + "they listen. ";
				preamble += "If you find what they say " + s("at all interesting,", "intriguing,") + "please be ";
				preamble += s("patient.","patient, and spend some time with " + s("them.", "the skill."));
				preamble += "If " + s("you don't,", "not,") + "or to interrupt a long speech, just say, " + s("clearly,", s("firmly,", "")) + "'Alexa, Stop!' ";
				preamble += s(s("And have done with it.",""),"They can be a little 'dark'. But ...") + s("We hope you enjoy","Thank you for listening to") + "'The Listeners'. ";
		}

		ResourceBundle.clearCache();
		Welcome ws = (Welcome) ResourceBundle.getBundle("listeners.l10n.Welcome", locale);
		
		SpeechUtils su = new SpeechUtils(locale);
		
		String postSpeechPrompt = "", reprompt = "";
		String affect = (String) sessionAttributes.get(AFFECT_KEY);
		if (affect == null || "".equals(affect)) {
			// extra help for initial welcome:
			postSpeechPrompt = su.chooseSpeechAssistance();
			reprompt = su.chooseUnsureAboutAffect();
		}
		else {
			// not sure if this can happen
			// (LauchRequest triggered from within a session)
			// if it can, set affect to the empty string
			sessionAttributes.put(AFFECT_KEY, "");
			reprompt = su.chooseContinue();
		}

		// TODO l10n for ResponseFinisher
		ResponseFinisher rf = new ResponseFinisher(localeTag, preamble, ws.getString("speech"), postSpeechPrompt, reprompt);

		return input.getResponseBuilder().
				withSpeech(rf.getSpeech()).
				withReprompt(rf.getReprompt()).
				withSimpleCard(rf.getCardTitle(), rf.getCardText()).
				withShouldEndSession(false).
				build();
	}
}
