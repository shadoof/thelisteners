package listeners.handlers;

import static com.amazon.ask.request.Predicates.requestType;
import static listeners.model.Attributes.FRAGMENTCOUNT_KEY;
import static listeners.model.Attributes.NOT_YET_GREETED;
import static listeners.util.ConstantUtils.info;
import static listeners.model.LangConstants.localeTag;

import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

import listeners.l10n.WelcomeSpeech;
import listeners.model.LangConstants;
import listeners.util.ResponseFinisher;

public class LaunchRequestHandler implements RequestHandler {

	private String affect; // TODO

	@Override
	public boolean canHandle(HandlerInput input) {

		return input.matches(requestType(LaunchRequest.class));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {

		final LangConstants lc = new LangConstants(input.getRequestEnvelope().getRequest().getLocale());

		Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();

		if (!sessionAttributes.keySet().contains(FRAGMENTCOUNT_KEY) || (sessionAttributes.get(FRAGMENTCOUNT_KEY).equals(NOT_YET_GREETED))) {

			info("@Launch: no fragmentCount or NOT_YET_GREETED");

			WelcomeSpeech ws = (WelcomeSpeech) ResourceBundle.getBundle("listeners.l10n.WelcomeSpeech",lc.locale);

			// TODO l10n for SpeechFinisher
			String postSpeechPrompt = "";
			ResponseFinisher sf = new ResponseFinisher(lc.localeTag, ws.getString("speech"), ws.getString("reprompt"), postSpeechPrompt);

			sessionAttributes.put(FRAGMENTCOUNT_KEY, 0);

			return input.getResponseBuilder().
					withSpeech(sf.getSpeech()).
					withReprompt(sf.getReprompt()).
					withSimpleCard(sf.getCardTitle(), sf.getCardText()).
					withShouldEndSession(true). // TODO: revert to false
					build();
		}
		else {
			// TODO NOT_YET_GREETED -> launch request in mid session
			info("@Launch: launch request in mid session");
			sessionAttributes.put(FRAGMENTCOUNT_KEY, NOT_YET_GREETED);
			return null;
		}
	}
}
