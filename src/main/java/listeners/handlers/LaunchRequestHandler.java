package listeners.handlers;

import static com.amazon.ask.request.Predicates.requestType;
import static listeners.util.ConstantUtils.S;
import static listeners.util.ConstantUtils.breath;
import static listeners.util.ConstantUtils.breathLong;
import static listeners.util.ConstantUtils.breathShort;
import static listeners.util.ConstantUtils.s;
import static listeners.util.SpeechUtils.chooseContinue;
import static listeners.util.SpeechUtils.chooseSpeechAssistance;
import static listeners.util.SpeechUtils.chooseUnsureAboutAffect;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

import listeners.l10n.WelcomeSpeech;
import listeners.model.Attributes;
import listeners.model.LangConstants;
import listeners.util.SpeechFinisher;

public class LaunchRequestHandler implements RequestHandler {

	private String cardTitle; // TODO
	private String affect; // TODO

	@Override
	public boolean canHandle(HandlerInput input) {

		return input.matches(requestType(LaunchRequest.class));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		
    final Locale locale = new Locale(input.getRequestEnvelope().getRequest().getLocale());
		final LangConstants LANG_CONSTANTS = new LangConstants(locale);

		Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
		sessionAttributes.put(Attributes.STATE_KEY, Attributes.START_STATE);

		WelcomeSpeech ws = new WelcomeSpeech(locale.getLanguage() + "-" + locale.getCountry());

		return input.getResponseBuilder().withSpeech(ws.getString("speech"))
				.withReprompt(ws.getString("reprompt"))
				.withShouldEndSession(false).build();
	}

//	private class WelcomeSpeech {
//
//		// NOT NEEEDED? or TODO boolean isAskRequest = true;
//		String speech = "";
//		String reprompt = "";
//		String postSpeechPrompt = "";
//
//		WelcomeSpeech(String langTag) {
//
//			switch (langTag) {
//				case "de-DE":
//					cardTitle = S("Willkommen!", s("Seid gegrüsst!", s("Grüss Dich!", "Grüsst Euch!")));
//					speech += s("Grüsse.", "Willkommen.") + s("Wer auch immer Sie sind.", "") + breathLong();
//					speech += "Wir hören " + s("ihnen", "") + "immer zu. " + breath();
//					speech += "Da wir bei " + s(breathLong(), "") + "Ihnen sind, " + breathShort() + "ist es uns eine Freude. " + breath();
//					speech += S("Es ist " + S("uns i", "I") + "mmer so eine Freude. " + breath(), "");
//					speech += s("Es ist " + s("so", "") + " eine Freude, bei Ihnen zu sein. " + breath(), "");
//					speech += "Immer. " + s(breath() + "Immer.", "") + breathShort() + "So eine " + s("grosse", "") + "Freude. " + breath();
//					break;
//				case "ja-JP":
//					// break;
//				case "en-CA":
//					// break;
//				case "en-IN":
//					// break;
//				case "en-GB":
//					cardTitle = S("Welcome", "Greetings");
//					speech += s("Greetings.", "Welcome.") + s("Whoever you may be.", "") + breathLong();
//					speech += "We are " + /* s("always", "") + */s("listening to you.", "listening.") + breath(); // ALWAYCHANGE
//					speech += "In so far as we are " + s(breathLong(), "") + "with you, " + breathShort() + "it is a pleasure. " + breath();
//					speech += S("It is " + S("always s", "S") + "uch a pleasure. " + breath(), "");
//					speech += s("It is " + s("such", "") + " a pleasure to be with you. " + breath(), "");
//					speech += "Always. " + s(breath() + "Always.", "") + breathShort() + s("Such a", "A") + "pleasure. " + breath();
//					break;
//				case "en-AU":
//					// break;
//				default: // "en-US" etc.
//					speech += s("Hello there!", "Welcome.") + s("Whoever you are.", "") + breathLong();
//					speech += "We are " + /* s("always", "") + */s("listening to you.", "listening.") + breath(); // ALWAYCHANGE
//					speech += "In as much as we are " + s(breathLong(), "") + "with you, " + breathShort() + "it is a pleasure. " + breath();
//					speech += s("It is " + s("always", "") + "such a pleasure. " + breath(), "");
//					speech += s("It is " + s("such", "") + " a pleasure to be with you. " + breath(), "");
//					speech += "Always. " + s(breath() + "Always.", "") + breathShort() + s("Such a", "A") + "pleasure. " + breath();
//			}
//			// affect = getAffectFromSession(session, AFFECT_KEY); TODO
//
//			// help is added to the welcome here:
//			if (StringUtils.isEmpty(affect)) {
//				postSpeechPrompt = chooseSpeechAssistance();
//				reprompt = chooseUnsureAboutAffect();
//			}
//			else {
//				// we set affect to the empty string here;
//				// this happens if user invokes welcome from
//				// within session, thus restarting:
//				//
//				// session.setAttribute(AFFECT_KEY, ""); TODO
//				reprompt += chooseContinue();
//			}
//
//			SpeechFinisher sf = new SpeechFinisher(langTag, speech, reprompt, postSpeechPrompt);
//			speech = sf.getSpeech();
//			reprompt = sf.getReprompt();
//
//		}
//	}
}
