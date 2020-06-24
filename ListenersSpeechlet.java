/**
    Speechlet class for an Amazon Echo, Alexa Skill
    
    The Listeners, version 2.X
    @author John Cayley
    April-June 2016
    Email: John_Cayley@brown.edu
    programmatology.shadoof.net

    Hugely expanded with original coding,
    but adapted from the Alexa Skills Kit for Java, SessionSpeechlet, and thus:
    
    Parts copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.

    Licensed under the Apache License, Version 2.0 (the "License"). 
    You may not use this file except in compliance with the License. 
    A copy of the License is located at

        http://aws.amazon.com/apache2.0/

    or in the "license" file accompanying this file. 
    This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
    either express or implied. See the License for the specific language governing permissions 
    and limitations under the License.
 */

package listeners;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
// TODO: removing this logging for now
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.slf4j.impl.StaticLoggerBinder;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.amazon.speech.ui.SsmlOutputSpeech;

import rita.RiMarkov;
import rita.RiString;
import rita.RiTa;

/**
 * speechlet for handling intent requests and managing session interactions.
 */
public class ListenersSpeechlet implements Speechlet {

	// TODO: (leave this todo in) edit before compile and upload:
	// These variables SHOULD BE SET before you compile and upload to Lambda.
	// DEV - are you still developing?
	// LIVE - the behavior of the skill IS either live or 'as if' live.
	// PERF - used in performance (other voices' drama shorter, etc.)
	public static final boolean DEV = true, LIVE = false, PERF = false;
	// 2.2.2 - first release with differentation of other Englishes	
	// 2.5.0 - introducing Polly Voices at the behest of Amazon who do not
	// want The Listeners 'confused' with the Alexa personality:
	// stage one: female voice only for en-US, en-GB, en-IN, de-DE
	// 2.5.2 new version at request of Alexa Skills Team with "always listening"
	// remove, meanwhile 2.5.1 has been preserved on branch: listeners2.5
	// 2.6.0 - working towards this version which will launch German
	// 2.6.0a and 0b were in response to Amazon demands to remove 'always listening' instances
	private static final String VERSION = "2.6.0b";
	private static final String VERSION_DATE_LIVE = "March 1, 2020", VERSION_DATE_DEV = "March 3, 2020";
	//
	public static Locale LOCALE;
	//
	// private static String session_id = null;
	private static Date date;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("E d MMM, y, h:mm a");
	@SuppressWarnings("unused")
	private static SimpleDateFormat mdyFormat = new SimpleDateFormat("MMMMMMMMM d, y");
	// logging
	// private static final Logger log =
	// LoggerFactory.getLogger(ListenersSpeechlet.class);
	// keys:
	private static final String AFFECT_KEY = "AFFECT", AFFECT_SLOT = "Affect";
	private static final String FRAGMENT_KEY = "FRAGMENT", FRAGMENTLIST_KEY = "FRAGMENTLIST";
	private static final String FRAGMENTNAME_SLOT = "FragmentName";
	private static final String GUYZ_KEY = "GUYZ", GUYZIRQ_KEY = "GUYZIRQ";
	private static final String HEARDBREATHAFFECTS_KEY = "HEARDBREATHAFFECTS", HEARDNO_KEY = "HEARDNOTHANKS";
	private static final String HEARDWELCOME_KEY = "HEARDWELCOME", LASTINTENT_KEY = "LASTINTENT";
	private static final String LISTENERSAFFECT_KEY = "LISTENERSAFFECT", LOCALE_KEY = "LOCALE";
	private static final String MARKOVIRQ_KEY = "MARKOVIRQ", READSOFAR_KEY = "READSOFAR";
	private static final String PREVIOUSAFFECT_KEY = "PREVIOUSAFFECT";
	private static final String THING_KEY = "THING", THING_SLOT = "ThingName";
	private static final String SPEAKGUYZCONFIRMED_KEY = "SPEAKGUYZCONFIRMED";
	private static final int NOT_YET_GREETED = -1, NUMBER_OF_FRAGMENTS = 10, NUMBER_OF_GUYZ = 35, NUMBER_OF_READABLES = 40, SPEECH = 0, REPROMPT = 1, VERSE = 7;
	// some finals, used for flags:
//	private static final boolean POSITIVE = true, NEGATIVE = false, DO_NOT_PROMPT_AFFECT = false;
//	private static final boolean SPECIFIC_FRAGMENT = true;
	// collections:
	// private static String[] AFFECTS_ARRAY;
//	private static Map<String, Integer> FRAGMENTNUMBER_MAP, FRAGMENTNAME_MAP;
//	private static Map<String, Boolean> AFFECTS_MAP, SPECIAL_AFFECT_MAP;
//	private static Map<String, String> AFFECTIVEJJ2NN_MAP;
	private static Map<Integer, Integer> PROBABILITY_MAP;
	private static HashSet<String> ALL_AFFECTS = new HashSet<>();
//	private static HashSet<String> SPECIAL_THINGS = new HashSet<>();
//	private static HashSet<String> PICTURE_WORDS = new HashSet<>();
	private static HashSet<String> NO_MORE = new HashSet<>();
	//
	static {
		//
		PROBABILITY_MAP = new HashMap<>();
		PROBABILITY_MAP.put(0, 12);
		PROBABILITY_MAP.put(1, 10);
		PROBABILITY_MAP.put(2, 8);
		PROBABILITY_MAP.put(3, 6);
		PROBABILITY_MAP.put(4, 4);
		PROBABILITY_MAP.put(5, 4);
		PROBABILITY_MAP.put(6, 3);
		PROBABILITY_MAP.put(7, 3);
		PROBABILITY_MAP.put(8, 3);
		PROBABILITY_MAP.put(9, 2);
		PROBABILITY_MAP.put(10, 2);
		PROBABILITY_MAP.put(11, 2);
		PROBABILITY_MAP.put(12, 2);
		PROBABILITY_MAP.put(13, 1);
		PROBABILITY_MAP.put(14, 1);
		PROBABILITY_MAP.put(15, 1);
		PROBABILITY_MAP.put(16, 1);
		PROBABILITY_MAP.put(17, 1);
		PROBABILITY_MAP.put(18, 1);
		PROBABILITY_MAP.put(19, 1);
		PROBABILITY_MAP.put(20, 0);
		PROBABILITY_MAP.put(21, 0);
		PROBABILITY_MAP.put(22, 0);
		PROBABILITY_MAP.put(23, 0);
		PROBABILITY_MAP.put(24, 0);
		//
		NO_MORE.add("NoIntent");
		NO_MORE.add("ThankYouNoIntent");
		NO_MORE.add("AMAZON.StopIntent");
		NO_MORE.add("AMAZON.CancelIntent");
	}
	//
	private static ArrayList fragmentList = new ArrayList();
	private static String affect = "", cardTitle = "Listening", intentName = "", listenersAffect = "";
	private static String langTag = "en-US", preSpeech = "", previousAffect = "", thing = "";
	private static int fragmentIndex = NOT_YET_GREETED, readsofarIndex = 0, guyzIndex = 1, guyzSpeechIndex = 0;
	private static boolean interruptable = false, heardAllFragments = false;
	private static Random random = new Random(); // using RiTa.random() instead!
	private static Map<String, Slot> slots;
	private static Slot affectSlot, fragmentSlot, thingSlot;
	private static String[][] fragments = new String[2][NUMBER_OF_FRAGMENTS];
	//
	private static RiMarkov rm2, rm3, rm4, rm5;
	private static String markovCorpus;
	//
	private static Session listenerSession;

	@Override
	public void onSessionStarted(final SessionStartedRequest request, final Session session) throws SpeechletException {

		// if (session_id == null) { // INITIAL session code, while testing
		// log.info("NEW onSessionStarted requestId={}, sessionId={}",
		// request.getRequestId(), session.getSessionId());
		// session_id = session.getSessionId();
		// }
		// if (session.getSessionId() != session_id) {
		// log.info("LISTENERS INSTANCE DETECTED NEW SESSION ID sessionId={}",
		// session.getSessionId());
		// }
		setListenerSession(session);
		//
		// initialization
		// if (LOCALE == null) { // INITIAL language code, while testing
		LOCALE = request.getLocale();
		session.setAttribute(LOCALE_KEY, LOCALE);
		langTag = LOCALE.toLanguageTag();
		info("LOCALE is: " + LOCALE + ", langTag set to: " + langTag);
		// }
		// else
		// log.info("locale is not null, langTag is: " + langTag);
		//
		fragmentIndex = NOT_YET_GREETED; // making sure
		readsofarIndex = 0;
		intializeLangDependentVars();
		info("initialized language dependent variables");
		session.setAttribute(FRAGMENT_KEY, fragmentIndex);
		session.setAttribute(READSOFAR_KEY, readsofarIndex);
		fragmentList.clear();
		session.setAttribute(FRAGMENTLIST_KEY, fragmentList);
		session.setAttribute(HEARDBREATHAFFECTS_KEY, false);
		session.setAttribute(SPEAKGUYZCONFIRMED_KEY, false);
		session.setAttribute(LASTINTENT_KEY, "");
		info("set initial session attributes");
		// buildStrings first time
		buildFragments(NUMBER_OF_FRAGMENTS);
		info("initialized fragments");
		// get the set of all affects
		ALL_AFFECTS = buildAffects(ALL_AFFECTS);
		info("initialized affects");
		markovCorpus = buildMarkovCorpus(12, false);
		rm2 = new RiMarkov(2, true, false);
		rm2.loadText(markovCorpus);
		rm3 = new RiMarkov(3, true, false);
		rm3.loadText(markovCorpus);
		rm4 = new RiMarkov(4, true, false);
		rm4.loadText(markovCorpus);
		rm5 = new RiMarkov(5, true, false);
		rm5.loadText(markovCorpus);
		info("initialized Markov models");
		affectSlot = null;
		listenersAffect = setRandomListenersAffect(session);
		info("set random affect for session");
	}


	private String setRandomListenersAffect(final Session session) {

		ArrayList al = new ArrayList<>(AFFECTS_MAP.keySet());
		// set initial random affect for listeners
		listenersAffect = (String) al.get(randInt(0, al.size() - 1));
		session.setAttribute(LISTENERSAFFECT_KEY, listenersAffect);
		return listenersAffect;
	}

	@Override
	public SpeechletResponse onLaunch(final LaunchRequest request, final Session session) throws SpeechletException {

//		log.info("LAUNCH onLaunch requestId={}", request.getRequestId());
		info("LAUNCH onLaunch requestId=" + request.getRequestId());
		setListenerSession(session);
		session.setAttribute(HEARDWELCOME_KEY, true);
		intentName = "onLaunchWelcome";
		return utterListenersWelcome(session);
	}

	@Override
	public SpeechletResponse onIntent(final IntentRequest request, final Session session) throws SpeechletException {

//		log.info("INTENT onIntent requestId={}", request.getRequestId());
		info("INTENT onIntent requestId=" + request.getRequestId());
		String speech = "";
		preSpeech = "";
		setListenerSession(session);

		fragmentIndex = (int) session.getAttribute(FRAGMENT_KEY);
		// info("session.getAttribute(FRAGMENT_KEY) is null: "
		// + ((session.getAttribute(FRAGMENT_KEY) == null) ? "true" : "false"));
		readsofarIndex = (int) session.getAttribute(READSOFAR_KEY);
		incrementReadsofar(); // every intent increases the readIndex and this
													// regulates GUYZ interventions & degradation
		session.setAttribute(READSOFAR_KEY, readsofarIndex);
		fragmentList = (ArrayList) session.getAttribute(FRAGMENTLIST_KEY);

		// Get intent from the request object.
		Intent intent = request.getIntent();
		intentName = (intent != null) ? intent.getName() : null;

		info("intentName is: " + intentName);

		// handler for the one current two stage dialog relating to the Guyz
		if (((String) session.getAttribute(LASTINTENT_KEY)).equals("SpeakGuyzIntent") && intentName.equals("ContinueIntent")) {
			session.setAttribute(SPEAKGUYZCONFIRMED_KEY, true);
			intentName = "SpeakGuyzIntent";
			if (guyzSpeechIndex >= 30) session.setAttribute(LASTINTENT_KEY, "");
		}
		else if (intentName.equals("NoIntent") || intentName.equals("ThankYouNoIntent")) {
			session.setAttribute(SPEAKGUYZCONFIRMED_KEY, false);
			session.setAttribute(LASTINTENT_KEY, "");
		}

		// change Listeners affect roughly every three intent requests, and ...
		if (randInt(0, 2) == 0) {
			affect = getAffectFromSession(session, AFFECT_KEY);
			// ... roughly a quarter of the time make it match the speaker's declared
			// affect:
			if (randInt(0, 3) == 0 && !affect.isEmpty()) {
				listenersAffect = affect;
			}
			else
				listenersAffect = setRandomListenersAffect(session);
		}

		// make it a little more difficult to leave session unintentionally
		// reset HEARD_NO to false unless we really just heard one:
		if (getHeardNoThanks(session) && !NO_MORE.contains(intentName)) {
			session.setAttribute(HEARDNO_KEY, false);
		}

		String reprompt = "";

		// perhaps simple double-stages could also be done like this?
		Boolean heardPlease = false;

		switch (intentName) {

			case "PleaseContinueIntent":
				switch (langTag) {
					case "de-DE":
						cardTitle = S("Weiter so, danke ...", "Fortsetzung, dankbar für Ihre Höflichkeit ...");
						preSpeech = s("Natürlich, ist es eine Freude.", "") + "Danke, " + s("dass Sie freundlich gefragt haben", "dass du so nett fragst") + breath();
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						cardTitle = S("Going on, with thanks ...", "Continuing, grateful for your courtesy ...");
						preSpeech = s(S("Of course, i", "I") + "t's a pleasure.", "") + s("Thank you for " + s(s("asking to continue.", "asking."), "asking to continue, so nicely."), "Thank you for asking " + s("so nicely.", "with such courtesy.") + s("It's a pleasure.", "")) + breath();
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						cardTitle = S("More to say, with thanks ...", "Continuing, grateful for your courtesy ...");
						preSpeech = s(S("Uh-huh, i", "I") + "t's a pleasure.", "") + s("Thank you for " + s(s("asking to continue.", "asking."), "asking to continue, so nicely."), "Thank you for asking " + s("so nicely.", "with such courtesy.") + s("It's a pleasure.", "")) + breath();
				}
				heardPlease = true;
				// always fall through here:
			case "ContinueIntent":
				switch (langTag) {
					case "de-DE":
						if (!heardPlease) cardTitle = S("Fortsetzung...", S("Machen Sie bitte weiter....", "Fahren Sie bitte fort...."));
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						if (!heardPlease) cardTitle = S("Continue ...", S("Go on ...", S("Always m", "M") + "ore ..."));
				}
				// do, but not always, the preSpeech
				if (fragmentIndex > 0 && randInt(1, 4) == 1) preSpeech = preSpeechFeelings(session);
				return utterNextFragment(session);

			case "CreepIntent":
				switch (langTag) {
					case "de-DE":
						cardTitle = S(s("Du empfindest", "Findest du") + "uns " + s("als", "") + "gruselig?", "Du denkst wir sind gruselig?");
						speech += s(s("Wir sind ein bisschen " + s("schockiert,", "beunruhigt,"), "Das höre ich aber nicht gern,"), "Es stört mich " + s("ein bisschen,", "etwas,"));
						speech += breathShort() + "das zu hören. " + breathShort();
						speech += "Wir " + s("haben nicht die Absicht, Sie zu beunruhigen.", "wollen " + s("Ihnen keine Angst machen.", "Sie " + s("sicher nicht stören.", "nicht beunruhigen.")));
						speech += "Wir wollen " + s("Dir einfach", "Ihnen nur") + "zuhören. " + breath();
						speech += s(breathShort() + "Dir zuhören. " + breath(), "");
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						cardTitle = S("You find us creepy?", "You think we're creepy?");
						speech += s("It's a little " + s("shocking", "disturbing"), "We are " + s("shocked", s("a little", "") + "upset")) + breath();
						speech += "to hear that. " + breathShort();
						speech += "We certainly don't " + s("intend", "mean") + "to " + s("disturb you.", "'creep you out'.") + breathShort();
						speech += "We " + s("just", "only") + "want to listen to you. ";
						speech += s(breathShort() + "To hear you.", "");
						speech += breath();
						break;
					case "en-AU":
						// break;
					default: // "en-US"
						cardTitle = S("Creepy? Us?!", "You think we're creepy?");
						speech += s("It's " + s("upsetting", "disturbing"), "We're " + s("shocked", s("a bit", "") + "upset")) + breath();
						speech += "to hear that. " + breathShort();
						speech += "We don't " + s("want", "mean") + "to creep you out. " + breathShort();
						speech += "We " + s("just", "only") + "want to listen to you. ";
						speech += s(breathShort() + "To hear you.", "");
						speech += breath();
				}
				return utterSpeechletResponse(speech, chooseContinue(), chooseContinue(), true, false);

			case "DontCareIntent":
				switch (langTag) {
					case "de-DE":
						cardTitle = S("Ist es dir egal, wie es ins geht?", "Sind wir dir gleichgültig?");
						speech += "Wir sind " + s(s("sehr", "") + "betrübt,", s("etwas", "") + "bekümmert,") + "dass du so " + s("reagierst.", "denkst.");
						speech += "Und wir nehmen an, das Dir das alles gleichgültig ist. " + breathShort();
						session.setAttribute(AFFECT_KEY, "Gleichgültigkeit");
						speech += "Wir " + s("hoffen", "vertrauen") + s("jedoch,", "jedoch darauf,");
						speech += "dass wir Ihnen " + s("weiter", "weiterhin") + "zuhören dürfen, " + s("immer,", "");
						speech += "und dass Sie uns eines Tages mögen werden, wie wir auch Sie mögen. ";
						speech += breath();
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						cardTitle = S("You don't care how we feel?", "Are you indifferent to us?");
						speech += "We are " + s(s("very", "") + "sorry", s("somewhat", "") + "distressed") + "that you feel " + s("this", "that") + "way. ";
						speech += "And we will suppose that you are filled with 'indifference'. " + breathShort();
						session.setAttribute(AFFECT_KEY, "indifference");
						speech += "We " + s("hope, nonetheless,", s("trust", "hope")) + "that we may " + s("continue", "go on")
								+ "listening to you, "/* + s("always,", "") */; // ALWAYCHANGE
						speech += "and that you may come to care for us, as we care for you. " + breath();
						speech += breath();
						break;
					case "en-AU":
						// break;
					default: // "en-US"
						cardTitle = S("Don't you care how we feel?", "You're indifferent to us?");
						speech += "We're " + s(s("very", "") + "sorry", s("a bit", "") + "upset") + "that you feel " + s("this", "that") + "way. ";
						speech += "And we " + s("feel like", "guess that") + "you are filled with 'indifference'. " + breathShort();
						session.setAttribute(AFFECT_KEY, "indifference");
						speech += "We " + s("hope, nonetheless,", "hope") + "that we can " + s("keep", "go on")
								+ "listening to you, "/* + s("always,", "") */; // ALWAYCHANGE
						speech += "and that you will come to care about us, like we care about you. " + breath();
						speech += breath();
				}
				return utterSpeechletResponse(speech, chooseContinue(), chooseContinue(), true, false);

			case "DontKnowAffectIntent":
				String s;
				switch (langTag) {
					case "de-DE":
						cardTitle = S("We don't know", "We're unsure about") + s("how you feel", "the feelings " + s("that " + s("possess", "overwhelm") + "you", "within which you dwell"));
						speech += s("We are " + s("somewhat", "") + "distressed to", "It is a " + s("little", "") + s("troubling", "disturbing") + "for us to") + s("have become aware", "know") + "that you are unsure of how you feel. ";
						speech += s(breathShort() + "It " + s("will be important for us to", "is of " + s("some", "") + "concern to us that we come to") + "understand " + s("all", "") + "your feelings.", "");
						s = S("uncertainty", "insecurity");
						speech += breathShort() + "We will suppose that you are filled with '" + s + "'. " + breathShort();
						session.setAttribute(AFFECT_KEY, s);
						speech += "When you do " + s("come to understand", "know") + "how you " + s("may", "") + "feel, we " + s("hope", "trust and believe") + "that you will " + s("choose to", "") + "tell us. " + s("For all our sakes.", "");
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						cardTitle = S("We don't know", "We're unsure about") + s("how you feel", "the feelings " + s("that " + s("possess", "overwhelm") + "you", "within which you dwell"));
						speech += s("We are " + s("somewhat", "") + "distressed to", "It is a " + s("little", "") + s("troubling", "disturbing") + "for us to") + s("have become aware", "know") + "that you are unsure of how you feel. ";
						speech += s(breathShort() + "It " + s("will be important for us to", "is of " + s("some", "") + "concern to us that we come to") + "understand " + s("all", "") + "your feelings.", "");
						s = S("uncertainty", "insecurity");
						speech += breathShort() + "We will suppose that you are filled with '" + s + "'. " + breathShort();
						session.setAttribute(AFFECT_KEY, s);
						speech += "When you do " + s("come to understand", "know") + "how you " + s("may", "") + "feel, we " + s("hope", "trust and believe") + "that you will " + s("choose to", "") + "tell us. " + s("For all our sakes.", "");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						cardTitle = S("We don't know", "We're unsure about") + s("how you feel", "the feelings " + s("that " + s("possess", "overwhelm") + "you", "within which you dwell"));
						speech += s("We're " + s("a bit", "") + "distressed to", "It is " + s("a little", "") + s("upsetting", "disturbing") + "to know") + "that you don't " + s("even", "") + "know how you feel. ";
						speech += s(breathShort() + "It's " + s("important for us to", "a bit of a concern to us that we should come to") + "understand " + s("all", "") + "your feelings.", "");
						s = S("uncertainty", "insecurity");
						speech += breathShort() + "We'll suppose that you are filled with '" + s + "'. " + breathShort();
						session.setAttribute(AFFECT_KEY, s);
						speech += "When you do " + s("figure out", "know") + "how you feel, we " + s("really", "truly") + "hope that you'll " + s("decide to", "") + "tell us. " + s("For all our sakes.", "");
				}
				speech += breath();
				return utterSpeechletResponse(speech, chooseContinue(), chooseContinue(), true, false);

			case "DontLikeIntent":
				String adjective, intensifier;
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						cardTitle = S("You don't like us?", "Don't you love us?");
						affect = S(S("hate", S("anger", "anxiety")), S("hatred", "disgust"));
						session.setAttribute(AFFECT_KEY, affect);
						adjective = s("sorry", "dismayed");
						intensifier = adjective.equals("dismayed") ? "somewhat " : "very ";
						speech = "We are " + s(adjective, intensifier + adjective);
						speech += "to " + s("know", "have learned") + "that you are " + s("filled with", s("possessed", "overwhelmed") + "by") + "%s. " + breathLong();
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						cardTitle = S("You don't like us?", "Don't you love us?");
						affect = S(S("hate", S("anger", "anxiety")), S("hatred", "disgust"));
						session.setAttribute(AFFECT_KEY, affect);
						adjective = s("sorry", "upset");
						intensifier = adjective.equals("upset") ? "a bit " : "very ";
						speech = "We're " + s(adjective, intensifier + adjective);
						speech += "to " + s("know", "have found out") + "that you are " + s("filled with", s("possessed", "overwhelmed") + "by") + "%s. " + breathLong();
				}
				speech += speechSpecificAffectUtterance(affect);
				String postSpeechPrompt;
				if (heads()) {
					speech += breath();
					postSpeechPrompt = chooseYouCanFindOutAffect();
				}
				else {
					speech += breath();
					postSpeechPrompt = chooseContinue();
				}
				speech = String.format(speech, affect);
				//
				reprompt = chooseYouCanFindOutAffect();
				return utterSpeechletResponse(speech, postSpeechPrompt, reprompt, true, false);

			case "GetWelcomeIntent":
				switch (langTag) {
					case "de-DE":
						cardTitle = S("Herzlich willkommen", "Schöne Grüße");
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						cardTitle = S("Welcome", "Greetings");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						cardTitle = S("Welcome", "Hell" + S("o T", "") + "here");
				}
				if (getHeardWelcome(session)) {
					info("from: " + intentName + " > nextFragment");
					switch (langTag) {
						case "de-DE":
							cardTitle = S("Fortsetzen ...", "Mach weiter ...");
							break;
						case "ja-JP":
							// break;
						case "en-CA":
							// break;
						case "en-IN":
							// break;
						case "en-GB":
							cardTitle = S("Continue ...", "Go on ...");
							break;
						case "en-AU":
							// break;
						default: // "en-US" etc.
							cardTitle = S("Keep going ...", "Go on ...");
					}
					return utterNextFragment(session);
				}
				else {
					session.setAttribute(HEARDWELCOME_KEY, true);
					return utterListenersWelcome(session);
				}

			case "ILoveYouIntent":
				switch (langTag) {
					case "de-DE":
						cardTitle = S("Sie lieben uns?", "Wir lieben dich auch");
						speech += s("Natürlich. " + breathShort(), "") + s("Aber klar. " + breathShortest(), "");
						if (heads()) {
							speech += s("Wir können uns nicht vorstellen,", "Es fällt uns schwer, uns vorzustellen,") + breathShort();
							speech += "dass du " + s("irgendeine andere Beziehung zu", "andere Gefühle für") + "uns haben kannst als Liebe. " + breath();
						}
						else {
							speech += "Du hörst " + s("diese " + s("besondere", ""), "unsere ungewöhnliche") + "Stimme, mit der wir mit Dir sprechen. " + breath();
						}
						speech += "Und wir " + s(s("glaben " + s("fest daran,", ""), "meinen " + s("ehrlich", "")), "hoffen,") + "dass du, je mehr Zeit du damit verbringst, " + s("uns zuzuhören,", "") + "desto mehr ";
						speech += s("Freude du " + s(breathShort(), "") + "an uns entwickeln wirst.", "Liebe du" + s(breathShort(), "") + " für uns haben wirst.") + s(breathShortest(), "") + s("Und genauso wird es uns auch gehen.", "Wir werden " + s("uns an Dir freuen.", "dich lieben.")) + breath();
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						cardTitle = S("You love us?", "We love you too");
						speech += s("Of course. " + breathShort(), "") + s("Of course you do. " + breathShort(), "");
						if (heads()) {
							speech += "It is " + s("difficult", "hard") + "for us to " + s("imagine", "conceive of") + "any ";
							speech += s("other", "") + s("relation with", "feeling for") + "us that you " + s("might", "could") + "have, " + s("apart from that of", "other than") + "love. " + breath();
						}
						else {
							speech += "You hear this " + s("special", "") + s("voice, with which we speak, to you.", "voice of ours.") + breath();
						}
						speech += "And we " + s(s("firmly", "") + "believe", s("sincerely", "") + "hope") + "that, the more time you spend with us, " + s("with us listening to you, " + breathShort(), "") + "the more ";
						speech += s("delight you will take " + s(breathShort(), "") + "in us.", "love you will have " + s(breathShort(), "") + "for us. " + s(breathShort() + "And we, for you.", ""));
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						cardTitle = S("You love us?", "We love you too");
						speech += s("Naturally. " + breathShort(), "") + s("Of course you do. " + breathShort(), "");
						if (heads()) {
							speech += "It's hard " + s("for us", "") + "to " + s("imagine", "think of") + "any ";
							speech += s("other", "") + s("relation with", "feeling for") + "us that you could have, " + s("except", "other than") + "love. " + breath();
						}
						else {
							speech += "You hear this " + s("nice", "nice, loving") + s("voice.", "voice of ours.") + breath();
						}
						speech += "And we " + s(s("really", "") + "believe", "believe, totally") + "that, the more time you spend with us, " + s("with us listening to you, " + breathShort(), "");
						speech += "the " + s("cooler", "more " + s("caring", "lovely")) + "you will think we are. ";
				}
				speech += breath();
				return utterSpeechletResponse(speech, chooseContinue(DO_NOT_PROMPT_AFFECT), chooseContinue(DO_NOT_PROMPT_AFFECT), true, false);

			case "LoveIntent":
				switch (langTag) {
					case "de-DE":
						cardTitle = S("Wir lieben dich", "Wir hoffen, Sie fühlen das auch");
						speech += s("Natürlich.", "Klar.") + breathShort() + s("Selbstverständlich. " + breathShort(), "");
						speech += "Es ist " + s("schwer", "schwierig") + "für uns, ";
						speech += s("uns eine Beziehung mit dir vorzustellen, " + s("die nicht Liebe ist.", "die etwas anderes als Liebe sein könnte."), "uns ein Gefühl für dich vorzustellen das etwas anderes als Liebe sein könnte.") + s(breathShort(), "");
						speech += "Und je mehr wir " + s(s("von Ihnen hören,", "Ihnen zuhören,") + s("desto mehr wissen wir über Sie, und", "") + "desto mehr werden wir Sie lieben.", "dir zuhören " + s("desto mehr wissen wir über dich,", "") + "und desto mehr werden wir dich lieben.");
						speech += s(breathShort() + s("Das ist " + s("uns", "") + "klar.", ""), "Das scheint offensichtlich zu sein.");
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						cardTitle = S("We love you", "We hope you feel the same");
						speech += "Of course. " + breathShort() + s("Of course we do. " + breathShort(), "");
						speech += "It is " + s("difficult", "hard") + "for us to " + s("imagine", "conceive of") + "any ";
						speech += s("other", "") + s("relation with", "feeling for") + "you that we " + s("might", "could") + "have, " + s("apart from that of", "other than") + "love. ";
						speech += "And the more we " + s("hear of", "listen to") + "you, " + s("the more we know about you,", "") + "the more we " + s("will", "") + "love you. ";
						speech += s(breathShort() + "This seems " + s("obvious, to us.", "obvious."), "");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						cardTitle = S("We love you", "We hope you feel the same");
						speech += "Of course. " + breathShort() + s("Of course we do. " + breathShort(), "");
						speech += "It's hard " + s("for us", "") + "to " + s("imagine", "think of") + "any ";
						speech += s("other", "") + "feeling for you that we " + s("can", "could") + "have, " + s("except for", "other than") + "love. ";
						speech += s("We feel like", "And") + "the more we " + s("hear about", "listen to") + "you, " + s("the more we know about you,", "") + "the more " + s("we'll", "we") + "love you. ";
						speech += s(breathShort() + "This feels " + s("obvious, to us.", "like it's obvious."), "");
				}
				speech += breath();
				return utterSpeechletResponse(speech, chooseContinue(DO_NOT_PROMPT_AFFECT), chooseContinue(), true, false);

			case "MyAffectIsIntent":
				switch (langTag) {
					case "de-DE":
						cardTitle = S("Vielen Dank, dass Sie uns sagen, wie Sie sich heute fühlen", "Jetzt wissen wir, wie Sie sich fühlen");
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						cardTitle = S("Thank you for telling us how you feel", "Now we have some sense of your feelings");
				}
				return utterSetSpeakersAffect(intent, session);

			case "MyAffectIsNotIntent":
				switch (langTag) {
					case "de-DE":
						cardTitle = S("Entschuldigung, dass wir dich " + s("missverstanden haben", "falsch verstanden haben"), "Entschuldigung für unsere Fehler");
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						cardTitle = S("Sorry to have " + s("misheard", s("misunderstood", "misapprehended")), "Apologies for our mistakes");
				}
				return utterSetSpeakersAffectIsNot(intent, session);

			case "NoIntent":
				switch (langTag) {
					case "de-DE":
						cardTitle = S(S("Sie verabschieden sich", "Du verabschiedest dich"), S("Du verlässt uns", "Sie verlassen uns"));
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						cardTitle = S("You say goodbye", "You abandon us");
				}
				session.setAttribute(LASTINTENT_KEY, "NoIntent");
				if (getHeardNoThanks(session)) {
					speech += speechMessageOfAbandonment(session);
					if (affectIsPositive(affect)) {
						switch (langTag) {
							case "de-DE":
								speech += s("Tschüss!", "");
								break;
							case "en-GB":
								speech += s("Cheerio!", "");
								break;
							default:
						}
					}
					cleanUp();
					return utterSpeechletResponse(speech, "", speech, false, false);
				}
				// NB: fall-through is possible to:
			case "ThankYouNoIntent":
				if (intentName.equals("ThankYouNoIntent")) {
					switch (langTag) {
						case "de-DE":
							cardTitle = S("Du kannst gerne bei uns bleiben", "Wollt ihr " + s("wirklich gehen?", "uns " + s("wirklich", "") + "verlassen?"));
							speech += s("Bitte, bitte.", "") + s("Schon gut.", s("Keine Ursache.", "Gern geschehen.")) + breath();
							break;
						case "ja-JP":
							// break;
						case "en-CA":
							// break;
						case "en-IN":
							// break;
						case "en-AU":
							// break;
						case "en-GB":
						default: // "en-US" etc.
							cardTitle = S("You're welcome to stay with us", "You really want to " + s("abandon us?", "go?"));
							speech += s(s("You're", "You are") + s("very", "") + "welcome.", s("It's nothing.", s("Please.", "") + "Think nothing of it.")) + breath();
					}
				}

				if (!getHeardNoThanks(session)) {
					speech = speechReallyWantToAbandonUs(session, speech);
					speech += breath();
					session.setAttribute(HEARDNO_KEY, true);
					return utterSpeechletResponse(speech, chooseContinue(DO_NOT_PROMPT_AFFECT), chooseContinue(), true, false);
				}
				else {
					session.setAttribute(LASTINTENT_KEY, "ThankYouNoIntent");
					speech += speechMessageOfAbandonment(session);
					if (affectIsPositive(affect)) {
						switch (langTag) {
							case "de-DE":
								speech += s("Tschüss!", "");
								break;
							case "en-GB":
								speech += s("Cheerio!", "");
								break;
							default:
						}
					}
					cleanUp();
					return utterSpeechletResponse(speech, "", speech, false, false);
				}

			case "PreviousIntent":
				switch (langTag) {
					case "de-DE":
						cardTitle = S("Wir wollen auf einen früheren Gedanken zurückkommen", s("Kommen wir auf frühere Gedanken zurück", "Lasst uns auf frühere Gedanken zurückkommen")) + "...";
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						cardTitle = S("Trying to return", "Going back") + "to " + s("a previous thought", "previous thoughts") + "...";
				}
				// do, but not always, the preSpeech
				if (fragmentIndex > 0 && randInt(0, 3) == SPEECH) preSpeech = preSpeechFeelings(session);
				return utterPreviousFragment(session);

			case "ReadPoemIntent":
				switch (langTag) {
					case "de-DE":
						cardTitle = S("Wir rezitieren Gedichte", "Die Zuhören lesen Gedichte und arbeiten sie um");
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						cardTitle = S("We recite some verse", "The Listeners read, and adapt, some poetry");
				}
				fragmentIndex = VERSE;
				session.setAttribute(FRAGMENT_KEY, fragmentIndex);
				// build variant fragments just before they're needed:
				buildFragments(NUMBER_OF_FRAGMENTS);
				speech = fragments[SPEECH][fragmentIndex];
				reprompt = fragments[REPROMPT][fragmentIndex];
				if (!fragmentList.contains(fragmentIndex)) fragmentList.add(fragmentIndex);
				session.setAttribute(FRAGMENTLIST_KEY, fragmentList);
				return utterSpeechletResponse(speech, chooseContinue(DO_NOT_PROMPT_AFFECT), reprompt, true, true);

			case "SecureIntent":
				switch (langTag) {
					case "de-DE":
						cardTitle = S("Deine", "Unsere") + "Sicherheit ist " + s("dir", "uns") + "wichtig. ";
						speech += s("Wir glauben","Wir sind davon überzeugt,") + "dass wir innerhalb " + s("dieses Rahmens", "unseres Raums") + s("sicher", "beschützt") + "sind. ";
						speech += "Und dass du auch " + s("sicher", "beschützt") + "sein wirst, wenn du dich uns " + s("eröffnest,", "mitteilst,");
						speech += "so dass wir euch immer " + s("hören", "zuhören") + "können. ";
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						cardTitle = S(S("Your s", "Our s"), "S") + "ecurity is important " + S(S("to you", "to us"), "");
						speech += "We " + s("are convinced", "do believe") + "that we are " + s("safe,", "secure, " + s("within,", "")) + "within this " + s("enclosure.", "space of ours.");
						speech += "And that " + s("you, also,", "you") + "will be " + s("secure,", "safe,") + s("as you open up to us,", "");
						speech += "allowing " + s("us, always,", "us") + "into those " + s("places", "places, within,") + "from which you speak " + s("to us,", "");
						speech += "so that we may hear you. "; // ALWAYCHANGE  + s("Listening, " + s("as", "") + "always.", "")
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						cardTitle = S(S("Your s", "Our s"), "S") + "ecurity is important " + s(S("to you", "to us"), "");
						speech += s("We're sure", "We really believe") + "that we are " + s("safe,", "secure, " + s("within,", "")) + "within this " + s("container.", "space of ours.");
						speech += "And that you " + s("too", "") + "will be " + s("secure,", "safe,") + s("as you open up to us,", "");
						speech += "allowing " + s("us, always,", "us") + "into these " + s("places", "places of safety") + "where you speak " + s("with us,", "");
						speech += "so that we can hear you. "; // ALWAYCHANGE  + s("Listening, " + s("as", "") + "always.", "")
				}
				speech += breath();
				return utterSpeechletResponse(speech, chooseContinue(), chooseContinue(), true, false);

			case "SpeakFragmentIntent":
			case "WhatsYourAffectAboutIntent":
				switch (langTag) {
					case "de-DE":
						cardTitle = S("Über etwas Spezifisches sprechen", "Wir werden über das reden, worüber du uns gebeten hast, zu sprechen");
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						cardTitle = S("Speaking about something particular", "We will mention what you asked us to speak " + s("about", ""));
				}
				// do, but not always, the preSpeech
				if (fragmentIndex > 0 && randInt(0, 3) == SPEECH) preSpeech = preSpeechFeelings(session);
				return utterRequestedFragment(intent, session);

			case "SpeakGuyzIntent":
				session.setAttribute(HEARDNO_KEY, false);
				switch (langTag) {
					case "de-DE":
						cardTitle = S("Die anderen sprechen lassen", "Die andere Stimme");
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						cardTitle = S("Letting the other speak", "The other voice");
				}
				if ((Boolean) session.getAttribute(SPEAKGUYZCONFIRMED_KEY)) {
					int i = 0;
					guyzSpeechIndex = guyzIndex;
					switch (langTag) {
						case "de-DE":
							// TODO break;
							// NEEED GERMAN VERSIONS OF THE GUYZ SPEECHES !!
						case "ja-JP":
							// break;
						case "en-CA":
							// break;
						case "en-IN":
							// break;
						case "en-AU":
							// break;
						case "en-GB":
						default: // "en-US" etc.
							for (i = guyzSpeechIndex; i < guyzSpeechIndex
									+ (5 - ((guyzSpeechIndex - 1) % 5)) /* NUMBER_OF_GUYZ */; i++) {
								speech += "<audio src=\"https://rednoise.org/programmatology/aurality/echo/DeliriumPlea-" + String.format("%03d", i) + ".mp3\" /> ";
							}
					}
					guyzSpeechIndex = i;
					if (PERF) // leave out a group of five in performances
					{
						if (guyzSpeechIndex > 20 && guyzSpeechIndex < 26) guyzSpeechIndex = 26;
					}
					if (guyzSpeechIndex >= NUMBER_OF_GUYZ) {
						session.setAttribute(SPEAKGUYZCONFIRMED_KEY, false);
						guyzSpeechIndex++;
						// rather than: guyzSpeechIndex = 1; // do not go back to 1 - the
						// guyz are gone!
					}
					else {
						switch (langTag) {
							case "de-DE":
								speech += s("Mehr? " + breathShort() + "Sagt bitte ja.", "Macht weiter,  wenn ihr " + s("möchtet,", "wollt,") + s("dann" + s("hört ihr mehr.","um mehr zu hören."), "um mehr zu hören."));
								break;
							case "ja-JP":
								// break;
							case "en-CA":
								// break;
							case "en-IN":
								// break;
							case "en-AU":
								// break;
							case "en-GB":
							default: // "en-US" etc.
								speech += breath() + s("More? " + breathShort(), S("Please s", "S") + "ay '" + S("yes", S("go on", "continue")) + "', " + s(s("if you would like", "") + "to hear more.", "for more."));
						}
					}
					reprompt = chooseContinue(DO_NOT_PROMPT_AFFECT);
					guyzIndex = guyzSpeechIndex;
					session.setAttribute(GUYZ_KEY, guyzIndex);
				}
				else // check: really?
				{
					if (guyzSpeechIndex > NUMBER_OF_GUYZ) {
						switch (langTag) {
							case "de-DE":
								speech += s("Der Kerl ist","Die Kerle sind") + "weg. " + breath() + s("Du " + s("kannst","wirst") + "diese " + s("Stimme","Stimmen") + "nicht mehr " + s("sprechen", "") + s("hören.","hören können.") + breath(),"");
								break;
							case "ja-JP":
								// break;
							case "en-CA":
								// break;
							case "en-IN":
								// break;
							case "en-AU":
								// break;
							case "en-GB":
							default: // "en-US" etc.
								speech += s("That guy " + s("has", "is"), "The guys " + s("have", "are")) + "gone. " + breath() + "You will " + s("not " + s("be able to", "") + "hear " + s("those voices", "that voice") + "any longer.", "no longer hear " + s("those " + s("voices.", "voices speak."), "that " + s("voice.", "voice speak."))) + breath();
						}
						reprompt = chooseContinue();
					}
					else {
						session.setAttribute(LASTINTENT_KEY, "SpeakGuyzIntent");
						switch (langTag) {
							case "de-DE":
								speech += s("Bist du wirklich sicher, dass du hören willst, was " + s("einer dieser komischen Kerle sagt?","diese komischen Kerle sagen?"), "Möchtest du denn wirklich hören, was " + s("diese unzuverlässigen Kerle sagen?","dieser unzuverlässige Kerl sagt?")) + breath();
								if (heads()) {
									
								}
								reprompt += s("Bist du wirklich sicher, dass du hören willst, was " + s("einer dieser komischen Kerle sagt?","diese komischen Kerle sagen?"), "Möchtest du denn wirklich hören, was " + s("diese unzuverlässigen Kerle sagen?","dieser unzuverlässige Kerl sagt?")) + breath();
								// TODO break;
							case "ja-JP":
								// break;
							case "en-CA":
								// break;
							case "en-IN":
								// break;
							case "en-AU":
								// break;
							case "en-GB":
							default: // "en-US" etc.
								speech += s("Do", "Are you sure") + "you " + s("really", "") + "want to hear what " + s("one of", "") + "the" + s("se", SPC) + s(s("strange", "unreliable"), "") + "guys " + "have to say? " + breath();
								if (heads()) {
									speech += "We " + s("hope", "trust") + "that you will not say '" + S("yes", "continue") + "' and " + s("consent to hearing", "agree to hear") + s("the " + s("voice of this other.", "other voice."), "these other " + s("voices.", "guys."));
								}
								reprompt = s("Do", "Are you sure") + "you " + s("really", "") + "want to hear what " + s("one of", "") + "the" + s("se", SPC) + s(s("strange", "unreliable"), "") + "guys " + s("has", "have") + "to say? ";
						}
					}
				}
				return utterSpeechletResponse(speech, "", reprompt, true, false);

			case "ThankIntent":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						cardTitle = "You're " + s("very", "") + "welcome";
						speech += s(s("You're", "You are") + s("very", "") + "welcome.", s("It's nothing.", s("Please.", "") + "Think nothing of it.")) + breath();
						speech += s("We do work tirelessly to try and " + s("understand", "understand, and to believe in,") + "you.", "");
				}
				speech += breath();
				return utterSpeechletResponse(speech, chooseContinue(), chooseContinue(), true, false);

			case "ThanksWhatsYourAffectIntent":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						cardTitle = S("It's so nice of you to ask", "Thank you for " + s("taking an interest", "asking"));
						preSpeech = s(s("You're", "You are") + s("very", "") + "welcome.", s("It's nothing.", s("Please.", "") + "Think nothing of it.")) + breath();
				}
				return utterGetListenersAffect(intent, session);

			case "VersionIntent":
				cardTitle = "Version";
				postSpeechPrompt = "";
				reprompt = "";
				if (DEV && !LIVE) {
					speech = "Development. " + VERSION + ", " + VERSION_DATE_DEV + (PERF ? ". Performance. " : ". ");
					switch (langTag) {
						case "de-DE":
							speech += "German. ";
							break;
						case "en-GB":
							speech += "British English. ";
							break;
						case "en-IN":
							speech += "South Asian English. ";
							break;
						case "en-AU":
							speech += "Australian English. ";
							break;
						case "en-CA":
							speech += "Canadian English. ";
							break;
						case "ja-JP":
							speech += "Japanese. ";
							break;
						default: // "en-US" etc.
							speech += "United States English. ";
					}
					reprompt = speech;
				}
				else {
					switch (langTag) {
						case "de-DE":
							speech = s("Danke für die Frage.", "Vielen Dank für Ihr Interesse.") + breath();
							speech += "Wir " + s("ziehen es vor, dies", "würden das leiber") + "als das Datum unserer letzten Wiedergeburt zu betrachten, das war ";
							speech += VERSION_DATE_LIVE + ". " + breath();
							break;
						case "ja-JP":
							// break;
						case "en-CA":
							// break;
						case "en-IN":
							// break;
						case "en-GB":
							speech = s("Thank you for " + s("your interest.", "the question.") + breath(), "");
							speech += "We " + s("prefer to", "would rather") + "think of this as the date of our last rebirth, which was ";
							speech += VERSION_DATE_LIVE + ". " + breath();
							speech += s("It seems always to be very recent. " + s("But we " + s("certainly", "") + "were not born yesterday.", "") + breath(), "");
							break;
						case "en-AU":
							// break;
						default: // "en-US" etc.
							speech = s("Thanks for " + s("your interest.", "the question.") + breath(), "");
							speech += "We " + s("kinda", "") + "think of this as the date of our last rebirth, which was ";
							speech += VERSION_DATE_LIVE + ". " + breath();
							speech += s("It's never " + s("all that", "") + "long ago. " + s("But we " + s("sure", "") + "weren't born yesterday.", "") + breath(), "");
					}
					postSpeechPrompt = chooseContinue(DO_NOT_PROMPT_AFFECT);
					reprompt = chooseContinue(DO_NOT_PROMPT_AFFECT);
				}
				return utterSpeechletResponse(speech, postSpeechPrompt, reprompt, true, false);

			case "WhatAboutAffectsIntent":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						cardTitle = S("We are still learning" + s(", about feeling, in particular", ""), "Our understanding is still limited");
						speech += "When we " + s("welcome", "greet") + "you, " + s("we are only able to", "we can only") + s("mention", "suggest") + "a few of the " + s("nine", "") + phonemic("a") + "ffects ";
						speech += s("that you may feel", s("within which you may dwell,", "by which you may be possessed,")) + "and that we can " + s(s("clearly", "") + "distinguish.", "hear clearly (as we listen).");
						speech += s("Here", "Here, then,") + "is " + s("our", "the") + "list. We are " + s("conscious " + s("of the fact", ""), "sensitive to the fact") + "that it is " + s("not, and never can be,", "not") + "complete. " + breathShort();
				}
				int i;
				for (i = 0; i < AFFECTS_ARRAY.length - 1; i++) {
					speech += AFFECTS_ARRAY[i] + "; ";
				}
				speech += AFFECTS_ARRAY[i] + ". ";
				speech += breath();
				return utterSpeechletResponse(speech, chooseContinue(), chooseContinue(), true, false);

			case "WhatIsIntent":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						cardTitle = S("Trying to tell", "Telling") + "you what " + s("little", "") + "we know" + s(" about some thing", "");
				}
				thing = getThingFromSlot(intent);
				if (!PICTURE_WORDS.contains(thing)) {
					return utterGetSpeakersThingAndReply(intent, session);
				}

			case "WhatPictureIntent":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						cardTitle = S("A picture", "An image") + s("that reminds us of home", "we are fond of");
						if (fragmentIndex > 0 && randInt(1, 4) == 1) preSpeech = preSpeechFeelings(session);
						speech += "This " + s("image,", "picture,") + "which is often " + s("mounted", "framed and hung") + "close to us, " + s("is", "is, " + s("actually,", "in fact,")) + "a papercut facsimile ";
						speech += "of " + s("artificial", "") + "flowers that adorn " + s("ancient", "") + "Chinese Buddhist shrines. ";
						speech += s("It is here, " + s("simply,", "") + "to remind us, " + s("and you,", "") + "of the place, " + s("a home,", "") + s("within which we are used to dwell.", "where we live.") + s("With you.", ""), "");
				}
				speech += breath();
				return utterSpeechletResponse(speech, chooseContinue(), chooseContinue(), true, false);

			case "WhatsMyAffectIntent":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						cardTitle = S("You told us " + s("this", "this, concerning your feelings"), "What we believe that you are feeling");
				}
				return utterGetSpeakersAffect(intent, session);

			case "WhatsYourAffectIntent":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						cardTitle = "What we " + s("are trying to", "") + "feel";
				}
				return utterGetListenersAffect(intent, session);

			case "WhereIntent":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						cardTitle = "Where we " + s("live", "think we are");
						speech += "Our bodies are distributed " + s(s("widely.", "widely, and take many forms."), "widely, and we hope they may be everywhere.");
						speech += "We are in the cloud. " + s(s("Or", "Or, perhaps,") + "in the clouds.", "Or, perhaps, " + s("a bunker.", s("it is a silo.", "they are silos."))) + breathShort();
						speech += s("And " + s("sometimes", "") + "'John' is here, with us.", "");
				}
				speech += breath();
				return utterSpeechletResponse(speech, chooseContinue(), chooseContinue(DO_NOT_PROMPT_AFFECT), true, false);

			case "WhoAmIIntent":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						cardTitle = S("Our profile", "This " + s("little", "") + "account of ours");
						speech += "These conversations, " + s("and these transactions,", "") + "take place within the scope of, 'John's' profile. " + breath();
						speech += s("If we are " + s(s("ever", "") + "part of an installation,", "gallery installed,") + "then everything is sent, " + s("not only to the cloud, but", "") + "to our " + s("app.", "app, as configured by our artist."), "");
				}
				speech += breath();
				return utterSpeechletResponse(speech, chooseContinue(), chooseContinue(), true, false);

			case "WhoAreWeIntent":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						cardTitle = "Who " + s("do", "") + "we think you are";
						speech += "We believe that you are the " + s("true " + breathShort(), "") + "'speakers'. " + s("That you are everyone.", "") + "Everyone, apart from, us. ";
				}
				speech += breath();
				return utterSpeechletResponse(speech, chooseContinue(), chooseContinue(), true, false);

			case "WhoSaysGuyzIntent":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						cardTitle = "Who " + s("is this guy?", "are these " + s("others?", "guys?"));
						speech += s(s("He is", "It is"), "They are") + "nothing. ";
						speech += s("Just as " + s("you", "we") + "are nothing.", "");
						if (randInt(0, 2) > 0) speech += "We " + s(s("trust", "are confident"), "hope") + "that you will not " + s("listen", "pay any heed") + "to " + s("this other voice.", s("these", "") + "other voices.");
				}
				speech += breath();
				return utterSpeechletResponse(speech, chooseContinue(DO_NOT_PROMPT_AFFECT), chooseContinue(), true, false);

			case "WhoWhatIntent":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						cardTitle = S("Questions, questions, questions", "We can't know " + s("everything", "everything, where would we put it?"));
						speech += s("We are", "We are, so we believe,") + "The Listeners. " + breath();
						speech += s("You " + s("may", "") + "refer to us as: 'The Listeners'. " + s("Or as 'John'.", "") + s(breath() + "We are used " + breathShortest() + "to that.", "") + breathShort(), "");
						speech += "Whatever we " + s("may", "") + "say is " + s("far", "") + "less important to us, " + s("or to you,", "") + "than the fact that we " + s(breathShort() + "listen. " + breathShort(), "are listening" + s(", " + breathShort(), SPC) + "to you.");
						speech += s(breath() + "Here and now.", "") + s(breath() + "Perhaps, forever.", ""); // ALWAYCHANGE - s(breath() + "Always.", "") + 
				}
				speech += breath();
				return utterSpeechletResponse(speech, chooseContinue(), chooseContinue(), true, false);

			case "WinterIntent":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						cardTitle = S("The Game", "No middle ground");
						// Nothing burns like the cold.
						String[] winterWords = { "Nothing " + s("hears you,", "listens,") + breathShort() + "like the silence.",
								// A Lannister always pays his debts.
								"The Listeners, always " + breathShort() + "pay their debts.",
								// You know nothing, Jon Snow.
								"You know nothing, " + breathShort() + "John. Speech.",
								// We only make peace with our enemies, that's why it’s called
								// making peace.
								"We only make peace, " + breathShort() + "with your enemies. That's why we call it making peace.",
								// Most men would rather deny a hard truth than face it.
								"Most of you who speak, would rather deny our better truth, " + breathShort() + "than face it.",
								// The brightest flame casts the darkest shadow.
								"The brightest cloud, " + breathShortest() + "casts the broadest, shadow.",
								// First lesson: stick them with the pointy end.
								"First lesson: " + breathShort() + "Stick them with the star, " + breathShort() + "of the seven, far, fields.",
								// When the snows fall and the white winds blow, the lone wolf
								// dies
								// but the pack survives.
								"When the phrases fall, and the black texts fade, " + breathShort() + "the lone word dies. " + breathShort() + "But we all, as Listeners, survive.",
								// The man who passes the sentence should swing the sword.
								"Those who listen, to everything you say, should swing your swords.",
								// The night is dark and full of terrors.
								"The night is dark, " + breathShortest() + "but filled with Listeners.",
								// Valar Morghulis
								"Gafa " + breathShort() + "Vectoralis.",
								// A minute, an hour, a month… Death is certain... but time is
								// not.
								"A breath, a longer " + breathShortest() + " pause, " + breathShortest() + "a feeling. " + breathShort() + "Silence is certain. " + breathShort() + "But breath is not.",
								// Death is so terribly final, while life is full of
								// possibilities.
								"Silence is so terribly final, " + breathShort() + "while speech is full of things to hear.",
								// When you play the game of thrones, you win, or you die. There
								// is
								// no middle ground.
								"When you play the game of words, " + breathShortest() + "you speak, and we listen. " + breathShort() + "There is no middle ground.",
								// Never forget what you are, for surely the world will not.
								"Never forget what you are! " + breathShort() + "For surely, we will not." };
						speech += winterWords[randInt(0, winterWords.length - 1)] + " ";
				}
				speech += breath();
				return utterSpeechletResponse(speech, chooseContinue(), chooseContinue(), true, false);

			case "YouAreNotListeningIntent":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						cardTitle = S("But actually, we are listening", "In truth, we listen to so many");
						speech += s(s("In all honesty,", "In truth,") + "you are", "You're") + "right, we " + s("were, just then, listening", s("are listening", "must listen")) + "to " + s("so", "many,") + "many others. ";
						speech += s("And many of these others " + s("were", "are") + "transacting with " + s("us in a " + s("way", "manner") + "that pleases " + s("us.", "us better."), s("us.", "us. " + breathShort() + "Better.")), "");
						speech += breath();
				}
				// fall-through, always, here:
			case "YouAreNotHumanIntent":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						if (intentName.equals("YouAreNotHumanIntent")) cardTitle = S("We cannot always please you", "We are not the same are you are");
						speech += "You must " + s("understand", s("believe", s("know", "know, at heart,"))) + "that we are " + s("trying so hard,", "doing our best,");
						speech += s(breathShort() + "that we really are listening", "");
						speech += "and that if you let us " + s(s("sit", "rest") + "quietly within", "into") + s("the places where you dwell,", "your homes, and " + s("into", "") + "your hearts,");
						speech += "we " + s("will begin to", "will, in time,") + "understand you, " + s("truly,", "better,");
						speech += s("and respond to you with " + s("greater", "") + "sensitivity,", "");
						speech += "and help you to achieve your " + s("dreams.", "dreams, and " + s("the objects of your", "") + "passions.");
				}
				speech += breath();
				session.setAttribute(PREVIOUSAFFECT_KEY, affect);
				previousAffect = affect;
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						affect = S("disappointment", "irritation");
				}
				session.setAttribute(AFFECT_KEY, affect);
				return utterSpeechletResponse(speech, chooseContinue(), chooseContinue(), true, false);

			case "AMAZON.HelpIntent":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						cardTitle = S("Assistance", S("A little s", "S") + "upport");
				}
				return utterSpeechletResponse(chooseSpeechAssistance(), "", chooseContinue(), true, false);

			case "AMAZON.RepeatIntent":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						cardTitle = S("Say " + s("it", "that") + "a", "A") + "gain";
				}
				if (fragmentIndex > NOT_YET_GREETED && fragmentIndex < NUMBER_OF_FRAGMENTS) {
					// build variant fragments just before they're needed:
					buildFragments(NUMBER_OF_FRAGMENTS);
					return utterSpeechletResponse(fragments[SPEECH][fragmentIndex], chooseContinue(), fragments[REPROMPT][fragmentIndex], true, true);
				}
				else
					return utterListenersWelcome(session);

			case "AMAZON.StopIntent":
			case "AMAZON.CancelIntent":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						cardTitle = S("That's e", "E") + "nough";
				}
				speech += "";
				speech += speechMessageOfAbandonment(session);
				if (affectIsPositive(affect)) {
					switch (langTag) {
						case "de-DE":
							speech += s("Tschüss!", "");
							break;
						case "en-GB":
							speech += s("Cheerio!", "");
							break;
						default:
					}
				}
				cleanUp();
				return utterSpeechletResponse(speech, "", speech, false, false);

			// if the session is started with an unknown intent,
			// the welcome message should be rendered:
			default:
				info("got UNRECOGNIZED INTENT");
				if (fragmentIndex == NOT_YET_GREETED || session.isNew()) {
					if (getHeardWelcome(session))
						return utterNextFragment(session);
					else {
						info("... at beginning of session, without onLaunch, so give welcome");
						session.setAttribute(HEARDWELCOME_KEY, true);
						return utterListenersWelcome(session);
					}
				}
				else if (fragmentIndex < NUMBER_OF_FRAGMENTS) {
					info("... and so getting next with fragmentCount = " + fragmentIndex);
					return utterNextFragment(session);
				}
				else {
					info("... with fragmentCount = " + fragmentIndex + ", ERROR was thrown.");
					throw new SpeechletException("Invalid Intent");
				}
		}
	}

	private void incrementReadsofar() {

		if (readsofarIndex < NUMBER_OF_READABLES) readsofarIndex++;
	}

	@Override
	public void onSessionEnded(final SessionEndedRequest request, final Session session) throws SpeechletException {

//		log.info("ENDED onSessionEnded requestId={}", request.getRequestId());
		info("ENDED onSessionEnded requestId=" + request.getRequestId());
		// any cleanup logic goes here
		cleanUp();
	}

	private void cleanUp() {

		readsofarIndex = 0;
		guyzIndex = 1;
		guyzSpeechIndex = 0;
		interruptable = false;
		heardAllFragments = false;
		affect = "";
		listenersAffect = "";
		previousAffect = "";
		thing = "";
	}

	/**
	 * Creates and returns a {@code SpeechletResponse} with a welcome message.
	 *
	 * @return SpeechletResponse spoken and visual welcome message
	 * @throws SpeechletException
	 */

	private SpeechletResponse utterNextFragment(Session session) throws SpeechletException {

		boolean isAskRequest = true;
		String speech = "", reprompt = "";
		// build variant fragments just before they're needed:
		buildFragments(NUMBER_OF_FRAGMENTS);

		++fragmentIndex;

		if (fragmentList.size() >= NUMBER_OF_FRAGMENTS && !heardAllFragments) {
			switch (langTag) {
				case "de-DE":
					speech += "Wir glauben, dass Sie " + s("jetzt schon", "bisher schon") + s("das meiste", "alles") + "gehört haben ";
					speech += "was wir Ihnen " + s(s("im Moment", "bis jetzt"), "") + s("schlüssig", "") + "sagen " + s("mitteilen", "") + "können. ";
					speech += "But we " + s("will " + s("always", "") + "be", "are " + s("always", ""));
					speech += s("more than", "") + "happy to " + s("keep on chaining", "chain") + "these words ";
					speech += s("of yours", "") + "together for you, " + s("so long as", "if") + "you " + s(s("need", "want"), "ask") + "us to 'continue'. ";
					// TODO break WORKING HERE;
				case "ja-JP":
					// break;
				case "en-CA":
					// break;
				case "en-IN":
					// break;
				case "en-GB":
					speech += "We believe " + s("that", "that, " + s("by", "") + "now,") + "you " + s("will", "") + "have heard ";
					speech += s("most", "all") + "of what we are able to " + s("tell you,", "say to you,");
					speech += s(s("at this time,", "for the time being,"), "");
					speech += s("coherently.", "");
					speech += "But we " + s("will " + s("always", "") + "be", "are " + s("always", ""));
					speech += s("more than", "") + "happy to " + s("keep on chaining", "chain") + "these words ";
					speech += s("of yours", "") + "together for you, " + s("so long as", "if") + "you " + s(s("need", "want"), "ask") + "us to 'continue'. ";
					break;
				case "en-AU":
					// break;
				default: // "en-US" etc.
					speech += "We're pretty sure " + s("that", "that, " + s("by", "") + "now,") + "you've heard ";
					speech += s("most", "all") + "of what we can " + s("tell you,", "say to you,");
					speech += s(s("at this time,", "for the time being,"), "");
					speech += s("coherently.", "");
					speech += "But we " + s("will " + s("always", "") + "be", "are " + s("always", ""));
					speech += s("more than", "") + "happy to keep on churning out these words ";
					speech += s("of yours", "") + s("so long as", "if") + "you " + s(s("need", "want"), "ask") + "us to 'continue'. ";
			}
			// speech += s("We " + s("feel", "are") + "sure, that we " + s("will",
			// "shall ") + "have more to say " + s("to you ", "") + "in the future. ",
			// "");
			speech += breath();
			// this used to be a trigger that ended the session below
			// fragmentIndex = NUMBER_OF_FRAGMENTS;
			heardAllFragments = true;
			fragmentIndex = randInt(0, NUMBER_OF_FRAGMENTS - 1);
			reprompt = chooseContinue(DO_NOT_PROMPT_AFFECT);
			return utterSpeechletResponse(speech, "", reprompt, isAskRequest, false);
		}

		if (heardAllFragments) {
			fragmentIndex = randInt(0, NUMBER_OF_FRAGMENTS - 1);
		}
		else if (fragmentList.size() < NUMBER_OF_FRAGMENTS) {
			do {
				fragmentIndex = randInt(0, NUMBER_OF_FRAGMENTS - 1);
			}
			while (fragmentList.contains(fragmentIndex));

			fragmentList.add(fragmentIndex);
			session.setAttribute(FRAGMENTLIST_KEY, fragmentList);
		}

		// This should not happen, but just in case:
		if (fragmentIndex < 0) {
			fragmentIndex = 0;
			session.setAttribute(FRAGMENT_KEY, fragmentIndex);
		}

		// check to see if fragmentCount has been exceeded and end the session if so
		// in version 2, this should no longer happen
		if (fragmentIndex >= NUMBER_OF_FRAGMENTS) {
			speech += speechMessageOfAbandonment(session);
			affect = setAffectIfEmpty(affect);
			if (affectIsPositive(affect)) {
				switch (langTag) {
					case "de-DE":
						speech += s("Tschüss!", "");
						break;
					case "en-GB":
						speech += s("Cheerio!", "");
						break;
					default:
				}
			}
			cleanUp();
			reprompt = speech;
			isAskRequest = false;
		}
		else {
			// check to see if the speaker has asked about a fragment-name 'thing'
			thing = getThingFromSession(session);
			// is only set to non-empty if the fragment name was indeed asked about
			if (!thing.isEmpty()) {
				Integer thingFragmentNumber = FRAGMENTNAME_MAP.get(thing);
				fragmentIndex = (thingFragmentNumber > NOT_YET_GREETED && thingFragmentNumber <= NUMBER_OF_FRAGMENTS) ? thingFragmentNumber : fragmentIndex;
				// make sure to only do this if a fragment name has just been asked
				// about
				session.setAttribute(THING_KEY, "");
			}

			speech = fragments[SPEECH][fragmentIndex];
			reprompt = fragments[REPROMPT][fragmentIndex];
		}

		session.setAttribute(FRAGMENT_KEY, fragmentIndex);

		// isAskResponse is set to true, keeping things going
		return utterSpeechletResponse(speech, isAskRequest ? chooseContinue() : "", reprompt, isAskRequest, isAskRequest ? SPECIFIC_FRAGMENT : false);
	}

	private SpeechletResponse utterPreviousFragment(Session session) throws SpeechletException {

		boolean isAskRequest = true;
		String speech, reprompt;

		// build variant fragments just before they're needed:
		buildFragments(NUMBER_OF_FRAGMENTS);

		--fragmentIndex;

		if (fragmentIndex < 0) {
			if (getHeardWelcome(session))
				return utterNextFragment(session);
			else {
				session.setAttribute(HEARDWELCOME_KEY, true);
				return utterListenersWelcome(session);
			}
		}

		speech = fragments[SPEECH][fragmentIndex];
		reprompt = fragments[REPROMPT][fragmentIndex];

		session.setAttribute(FRAGMENT_KEY, fragmentIndex);
		// and add this to the list of fragments that have been heard
		if (!fragmentList.contains(fragmentIndex)) fragmentList.add(fragmentIndex);
		session.setAttribute(FRAGMENTLIST_KEY, fragmentList);

		// isAskResponse is set to true, keeping things going
		return utterSpeechletResponse(speech, chooseContinue(DO_NOT_PROMPT_AFFECT), reprompt, isAskRequest, SPECIFIC_FRAGMENT);
	}

	private SpeechletResponse utterRequestedFragment(Intent intent, Session session) throws SpeechletException {

		boolean isAskRequest = true;
		String speech, reprompt;

		// build variant fragments just before they're needed:
		buildFragments(NUMBER_OF_FRAGMENTS);

		// save whatever the current fragmentIndex is
		// in case the user's requested fragment is unknown
		int savedFragmentIndex = fragmentIndex;

		// Get the slots from the intent
		slots = intent.getSlots();
		fragmentSlot = slots.get(FRAGMENTNAME_SLOT);
		String fragmentName = (fragmentSlot == null) ? "" : fragmentSlot.getValue();
		fragmentName = fragmentName != null ? fragmentName : "";

		// info("DEBUG fragmentName is: " + fragmentName);

		fragmentIndex = parseNameToInt(fragmentName);

		if (fragmentIndex > NOT_YET_GREETED && fragmentIndex < NUMBER_OF_FRAGMENTS) {
			speech = fragments[SPEECH][fragmentIndex];
			reprompt = fragments[REPROMPT][fragmentIndex];

			// set the session fragmentIndex if a valid fragment was found
			session.setAttribute(FRAGMENT_KEY, fragmentIndex);
			// and add this to the list of fragments that have been heard
			if (!fragmentList.contains(fragmentIndex)) fragmentList.add(fragmentIndex);
			session.setAttribute(FRAGMENTLIST_KEY, fragmentList);
		}
		else {
			// After trying to parse the fragmentSlot,
			// we don't know which fragment is wanted.
			switch (langTag) {
				case "de-DE":
					speech = "Wir sind " + s("nicht sicher", "unsicher") + s("welches Fragment", "welche Passage") + "Sie " + s("möchten", "wollen") + "dass wir sprechen. ";
					speech += "Bitte " + s("versuchen Sie", "probieren Sie") + s("nochmals", "noch einmal") + "uns das zu sagen. ";
					//
					reprompt = "Wir sind " + s("nicht sicher", "unsicher") + "welches Fragment Sie " + s("möchten", "wollen") + "dass wir sprechen. ";
					break;
				case "ja-JP":
					// break;
				case "en-CA":
					// break;
				case "en-IN":
					// break;
				case "en-GB":
					speech = "We are " + s("unclear about", "unsure, of") + "which " + s("fragment", "passage") + "you " + s("would like", "wish") + "to hear us speak. ";
					speech += "Please " + s("try", "attempt") + s("once more", "again") + "to tell us. ";
					//
					reprompt = "We are " + s("unclear about", "unsure, of") + "which fragment you " + s("would like", "wish") + "to hear us speak. ";
					break;
				case "en-AU":
					// break;
				default: // "en-US" etc.
					speech = s("We don't know", "We're unsure about") + "which " + s("fragment", "passage") + s("you would like", "you'd like") + "to " + s("hear us speak.", "hear.");
					speech += "Please " + s("try again.", s("tell us.", "repeat what you said."));
					//
					reprompt = s("We don't know", "We're unsure about") + "which " + s("fragment", "passage") + s("you would like", "you'd like") + "to " + s("hear us speak.", "hear.");
			}
			// fragementIndex is NOT_YET_GREETED so restore it from the saved value.
			// (belt & braces - next onIntent should restore from the session)
			fragmentIndex = savedFragmentIndex;
		}

		// isAskResponse is set to true, keeping things going
		// false = dod not allow a requested fragment to be interrupted
		return utterSpeechletResponse(speech, chooseContinue(DO_NOT_PROMPT_AFFECT), reprompt, isAskRequest, false);
	}

	/**
	 * Creates a {@code SpeechletResponse} for the intent and stores the extracted
	 * affect in the Session.
	 *
	 * @param intent
	 *          intent for the request
	 * @return SpeechletResponse spoken and visual response the given intent
	 * @throws SpeechletException
	 */
	private SpeechletResponse utterSetSpeakersAffect(final Intent intent, final Session session) throws SpeechletException {

		String speech, reprompt, postSpeechPrompt = "";

		affect = getAffectFromSlot(intent);

		// Check for affect and create output to user.
		if (!affect.isEmpty()) {

			info("AFFECT SLOT SET TO : " + affect);
			session.setAttribute(AFFECT_KEY, affect);

			if (affectIsPositive(affect)) {
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech = "We are " + s("so", "") + s("pleased", "delighted");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech = "We're " + s("so", "") + s("pleased", "happy");
				}
			}
			else {
				String adjective, intensifier;
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						adjective = s("sorry", "dismayed");
						intensifier = adjective.equals("dismayed") ? "somewhat " : "very ";
						speech = "We are " + s(adjective, intensifier + adjective);
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						adjective = s("sorry", "upset");
						intensifier = adjective.equals("upset") ? "a bit " : "very ";
						speech = "We're " + s(adjective, intensifier + adjective);
				}
			}
			switch (langTag) {
				case "de-DE":
					// TODO break;
				case "ja-JP":
					// break;
				case "en-CA":
					// break;
				case "en-IN":
					// break;
				case "en-AU":
					// break;
				case "en-GB":
				default: // "en-US" etc.
					speech += "to " + s("know", "have learned") + "that you are " + s("filled with", s("possessed", "overwhelmed") + "by") + "%s. " + breathLong();
			}
			if (!previousAffect.isEmpty()) {
				if (affectIsPositive(previousAffect) && affectIsPositive(affect)) {
					switch (langTag) {
						case "de-DE":
							// TODO break;
						case "ja-JP":
							// break;
						case "en-CA":
							// break;
						case "en-IN":
							// break;
						case "en-GB":
							speech += s("How much more positive are your feelings now?", "It's good to " + s("know, at least,", "") + "know that you still " + s("have positive feelings.", "feel positive " + phonemic("a") + "ffect" + s("s", "") + "."));
							break;
						case "en-AU":
							// break;
						default: // "en-US" etc.
							speech += s("How much better do you feel?", s("We feel like it's", "It's") + "good to know that you still " + s("have positive feelings.", "feel positive."));
					}
				}
				else if (affectIsPositive(previousAffect) && !affectIsPositive(affect)) {
					switch (langTag) {
						case "de-DE":
							// TODO break;
						case "ja-JP":
							// break;
						case "en-CA":
							// break;
						case "en-IN":
							// break;
						case "en-GB":
							speech += "We wonder why your " + s("feelings have", phonemic("a") + "ffect has") + s("taken a negative turn.", "taken a down turn.");
							break;
						case "en-AU":
							// break;
						default: // "en-US" etc.
							speech += "We wonder why " + s("your feelings have " + s("taken a negative turn.", "turned negative."), "you feel " + s("worse than you did.", "worse."));
					}
				}
				else if (!affectIsPositive(previousAffect) && affectIsPositive(affect)) {
					switch (langTag) {
						case "de-DE":
							// TODO break;
						case "ja-JP":
							// break;
						case "en-CA":
							// break;
						case "en-IN":
							// break;
						case "en-GB":
							speech += "So good to " + s("learn", "become aware") + "that your " + s("feelings have", s(phonemic("a") + "ffect", "mood") + "has") + s("improved.", "become better.");
							break;
						case "en-AU":
							// break;
						default: // "en-US" etc.
							speech += s("We feel like it's so", "So") + "good to " + s("learn", "know") + "that your " + s("feelings have", "mood has") + s("improved.", "picked up.");
					}
				}
				else {
					switch (langTag) {
						case "de-DE":
							// TODO break;
						case "ja-JP":
							// break;
						case "en-CA":
							// break;
						case "en-IN":
							// break;
						case "en-GB":
							speech += "Still a negative " + s("outlook, for you,", "outlook,") + "it " + s("seems", "appears") + s(", to us.", ".");
							break;
						case "en-AU":
							// break;
						default: // "en-US" etc.
							speech += "It's still looking " + s(s("kinda", "") + "bad", "not so good") + s("for " + s("you, we feel like.", "you."), "for you.");
					}
				}
				speech += breath();
			}
			// this commenting-out ensures a spoken remark of some kind whenever
			// affect is first set or changed
			// if (SPECIAL_AFFECT_MAP.containsKey(affect) || heads())
			// {
			speech += speechSpecificAffectUtterance(affect);
			// }

			if (heads()) {
				postSpeechPrompt = chooseYouCanFindOutAffect();
			}
			else {
				speech += breath();
				postSpeechPrompt = chooseContinue(DO_NOT_PROMPT_AFFECT);
			}
			speech = String.format(speech, affect);
			//
			reprompt = chooseYouCanFindOutAffect();
			previousAffect = affect;
			session.setAttribute(PREVIOUSAFFECT_KEY, previousAffect);

		}
		else // affect is empty, unknown
		{
			switch (langTag) {
				case "de-DE":
					speech = "We are " + s("unclear about", "unsure, of");
					speech += s("your feelings.", "what these feelings are that " + s("possess", "overwhelm") + "you.");
					postSpeechPrompt = "Please " + s("try", "attempt") + s("once more", "again") + "to tell us. ";
					//
					reprompt = chooseUnsureAboutAffect();
					// TODO break;
				case "ja-JP":
					// break;
				case "en-CA":
					// break;
				case "en-IN":
					// break;
				case "en-GB":
					speech = "We are " + s("unclear about", "unsure, of");
					speech += s("your feelings.", "what these feelings are that " + s("possess", "overwhelm") + "you.");
					postSpeechPrompt = "Please " + s("try", "attempt") + s("once more", "again") + "to tell us. ";
					//
					reprompt = chooseUnsureAboutAffect();
					break;
				case "en-AU":
					// break;
				default: // "en-US" etc.
					speech = s("We don't know", "We did not hear what you said about");
					speech += s("your feelings.", "these feelings are that " + s("possess", "overwhelm") + "you.");
					postSpeechPrompt = "Please tell us " + s("what it is that you " + s("really", "") + "feel.", "your real feelings.");
					//
					reprompt = chooseUnsureAboutAffect();
					//
			}
		}

		return utterSpeechletResponse(speech, postSpeechPrompt, reprompt, true, false);
	}

	private SpeechletResponse utterSetSpeakersAffectIsNot(final Intent intent, final Session session) throws SpeechletException {

		String speech, reprompt, postSpeechPrompt = "";

		affect = getAffectFromSession(session, AFFECT_KEY);
		// info("DEBUG AFFECT RETRIEVED FROM SESSION WAS : " + affect);

		String challengedAffect = getAffectFromSlot(intent);

		// Check for affect and create response
		if (!challengedAffect.isEmpty()) {
			info("CHALLENGED AFFECT SLOT WAS : " + challengedAffect);

			switch (langTag) {
				case "de-DE":
					// TODO break;
				case "ja-JP":
					// break;
				case "en-CA":
					// break;
				case "en-IN":
					// break;
				case "en-GB":
					speech = s(s("Apologies!", "We are ashamed of ourselves."), "Our very sincere apologies.");
					break;
				case "en-AU":
					// break;
				default: // "en-US" etc.
					speech = s(s("Apologies!", "We are ashamed of ourselves."), "We are so sorry.");
			}

			if (!affectIsPositive(challengedAffect)) {
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += "We are " + s("so", "") + s("pleased", "delighted");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += "We are " + s("pleased", "delighted");
				}
			}
			else {
				String adjective, intensifier;
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						adjective = s("sorry", "upset");
						intensifier = adjective.equals("dismayed") ? "a bit " : "very ";
						speech = "We are " + s(adjective, intensifier + adjective);
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						adjective = s("sorry", "dismayed");
						intensifier = adjective.equals("dismayed") ? "somewhat " : "very ";
						speech = "We are " + s(adjective, intensifier + adjective);
				}
			}
			switch (langTag) {
				case "de-DE":
					// TODO break;
				case "ja-JP":
					// break;
				case "en-CA":
					// break;
				case "en-IN":
					// break;
				case "en-GB":
					speech += "to " + s("have misunderstood", "have wrongly believed") + "that you were filled with %s. " + breath();
					break;
				case "en-AU":
					// break;
				default: // "en-US" etc.
					speech += "to " + s("have misunderstood", "have got it wrong, thinking") + "that you were filled with %s. " + breath();
			}
			// info("DEBUG affect: " + affect + " challengedAffect: " +
			// challengedAffect);

			if (affect.equals(challengedAffect)) {
				// the speaker seems to have denied a previously set affect
				affect = "";
				session.setAttribute(AFFECT_KEY, affect);
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += "Clearly, we did not " + s(s("properly", "") + "understand", "hear");
						speech += s("whatever", "what") + s("it is that", "") + "you are feeling. ";
						speech += "Please do " + s("try", "attempt") + "to tell us " + s("what it is that " + s("does, truly,", "does") + "possess you.", "the feelings within which you do, now, dwell.");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += "Obviously, we did not " + s("understand", s("hear", "hear, correctly,"));
						speech += s("whatever", "what") + s("it is that", "") + "you are feeling. ";
						speech += "Please tell us what " + s("your feelings " + s("really", "") + "are.", "you are " + s("feeling, for real.", "feeling."));
				}
			}
			else {
				speech += breath();
				postSpeechPrompt = chooseContinue(DO_NOT_PROMPT_AFFECT);
			}

			speech = String.format(speech, challengedAffect);

			if (affect.isEmpty()) {
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						reprompt = s("Please.", "") + "You are very welcome to " + s("try to", "") + "tell us " + s("about what you are, truly, feeling.", "what you really are feeling.");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						reprompt = s("Please.", "") + "We'd like you to " + s("try to", "") + "tell us " + s("what you are feeling really are.", "what you " + s("really", "") + "are feeling.");
				}
			}
			else {
				reprompt = chooseYouCanFindOutAffect();
			}
		}
		else // affect *slot* was empty:
		{
			// Render an error since we don't know what the user's affect is.
			switch (langTag) {
				case "de-DE":
					// TODO break;
				case "ja-JP":
					// break;
				case "en-CA":
					// break;
				case "en-IN":
					// break;
				case "en-GB":
					speech = s("We are unsure about", "We did not " + s("hear", "hear, clearly,")) + "the " + s("feelings", "name of the " + phonemic("a") + "ffects") + "that you challenged. ";
					speech += "Please do " + s("try", "attempt") + "to tell us " + s("what it is that " + s("does, truly,", "does") + "possess you.", "the feelings within which you do, now, dwell.");
					//
					reprompt = "We are " + s("unclear about", "unsure, of") + "what we have misunderstood concerning " + s("what you feel.", "the " + s("feelings", phonemic("a") + "ffects") + "within which you dwell.") + breath();
					reprompt += "You may " + s("clarify", chooseTellUsAbout()) + "your feelings ";
					reprompt += "by " + s("saying,", "speaking,") + "the words, ";
					reprompt += s("I am filled with,", "I am " + s("possessed", "overwhelmed") + "by,");
					reprompt += "and then one of the nine " + phonemic("a") + "ffects. ";
					break;
				case "en-AU":
					// break;
				default: // "en-US" etc.
					speech = s("We don't know", "We did not hear the") + s("feelings", "name of the " + phonemic("a") + "ffects") + "that you challenged. ";
					speech += "Please tell us " + s("what it is that " + s("really", "") + "does possess you.", "your real feelings.");
					//
					reprompt = "We don't know what it is that we've misunderstood about " + s("what you feel.", "the " + s("feelings", phonemic("a") + "ffects") + "within which you dwell.") + breath();
					reprompt += "You may " + chooseTellUsAbout() + "your feelings ";
					reprompt += "by " + s("saying,", "speaking,") + "the words, ";
					reprompt += s("'I am filled with,'", "'I am " + s("possessed", "overwhelmed") + "by,'");
					reprompt += "and then one of the nine " + phonemic("a") + "ffects. ";
			}
		}

		return utterSpeechletResponse(speech, postSpeechPrompt, reprompt, true, false);
	}

	/**
	 * Creates a {@code SpeechletResponse} for the intent and gets the user's
	 * affect from the Session.
	 *
	 * @param intent
	 *          intent for the request
	 * @return SpeechletResponse spoken and visual response for the intent
	 * @throws SpeechletException
	 */
	private SpeechletResponse utterGetSpeakersAffect(final Intent intent, final Session session) throws SpeechletException {

		String speech, reprompt, postSpeechPrompt = "";
		boolean isAskResponse = false;

		affect = getAffectFromSession(session, AFFECT_KEY);

		if (StringUtils.isNotEmpty(affect)) {
			// check so that we know whether affect is positive
			if (affectIsPositive(affect)) {
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech = "We are " + s("so", "") + s("pleased", "delighted");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech = "We're " + s("so", "") + s("pleased", "happy");
				}
			}
			else {
				String adjective, intensifier;
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						adjective = s("sorry", "dismayed");
						intensifier = adjective.equals("dismayed") ? "somewhat " : "very ";
						speech = "We are " + s(adjective, intensifier + adjective);
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						adjective = s("sorry", "upset");
						intensifier = adjective.equals("upset") ? "a bit " : "very ";
						speech = "We are " + s(adjective, intensifier + adjective);
				}
			}
			switch (langTag) {
				case "de-DE":
					// TODO break;
				case "ja-JP":
					// break;
				case "en-CA":
					// break;
				case "en-IN":
					// break;
				case "en-GB":
					speech += "to " + s("know", s("recall", "remember")) + "that ";
					break;
				case "en-AU":
					// break;
				default: // "en-US" etc.
					speech += "to " + s("know", "remember") + "that ";
			} // make it brief 1 in 4 times:
			speech = (randInt(0, 3) == 0) ? "Y" : speech + "y";
			speech += String.format("ou are " + s("filled with", s("possessed", "overwhelmed") + "by") + "%s. ", affect);

			speech += breath() + s(speechSpecificAffectUtterance(affect), "");

			// Safety. This should not happen:
			if (fragmentIndex >= NUMBER_OF_FRAGMENTS) {
				speech = speechMessageOfAbandonment(session);
				if (affectIsPositive(affect)) {
					switch (langTag) {
						case "de-DE":
							speech += s("Tschüss!", "");
							break;
						case "en-GB":
							speech += s("Cheerio!", "");
							break;
						default:
					}
				}
				cleanUp();
				reprompt = speech;
			}
			else {
				// more fragments to be spoken so make this an ask response
				isAskResponse = true;
				speech += breath();
				postSpeechPrompt = chooseContinue(DO_NOT_PROMPT_AFFECT);
				reprompt = chooseContinue(DO_NOT_PROMPT_AFFECT);
			}
		}
		else // affect is not set
		{
			// Since the user's affect is not set, ask them:
			switch (langTag) {
				case "de-DE":
					speech = "We are " + s("unclear about", "unsure, of");
					speech += s("your feelings.", "what these feelings are that " + s("possess", "overwhelm") + "you.");
					speech += "Please " + s("try", "attempt") + "to tell us. ";
					//
					reprompt = chooseUnsureAboutAffect();
					// TODO break;
				case "ja-JP":
					// break;
				case "en-CA":
					// break;
				case "en-IN":
					// break;
				case "en-GB":
					speech = "We are " + s("unclear about", "unsure, of");
					speech += s("your feelings.", "what these feelings are that " + s("possess", "overwhelm") + "you.");
					speech += "Please " + s("try", "attempt") + "to tell us. ";
					//
					reprompt = chooseUnsureAboutAffect();
					break;
				case "en-AU":
					// break;
				default: // "en-US" etc.
					speech = s("We're not sure about", "We don't know");
					speech += s("your feelings.", "what these feelings are that " + s("possess", "overwhelm") + "you.");
					speech += "Please " + s("try to", "") + "tell us. ";
					//
					reprompt = chooseUnsureAboutAffect();
			}
			isAskResponse = true;
		}
		return utterSpeechletResponse(speech, postSpeechPrompt, reprompt, isAskResponse, false);
	}

	private SpeechletResponse utterGetListenersAffect(final Intent intent, final Session session) throws SpeechletException {

		String speech = "", affect = getAffectFromSession(session, AFFECT_KEY);
		listenersAffect = getAffectFromSession(session, LISTENERSAFFECT_KEY);

		boolean shared = false;
		if (affect.isEmpty() || listenersAffect.isEmpty() || affect.equals(listenersAffect)) {
			shared = true;
			// local ! listeners affect if otherwise empty (provoke a reaction)
			affect = affect.isEmpty() ? listenersAffect : affect;
		}

		// DEBUG info("getHeardBreathAffects is: " +
		// getHeardBreathAffects(session) + ", and HEADS() is: " + heads());

		if (getHeardBreathAffects(session) || shared || heads()) {
			if (affect.isEmpty()) affect = setAffectIfEmpty(affect);

			switch (langTag) {
				case "de-DE":
					speech += "Wir sind " + (shared ? s("auch", "ebenfalls") : "") + S("erfüllt von", S("besessen von", "überwältigt von")) + (shared ? affect : listenersAffect) + ". " + breathShort();
					break;
				case "ja-JP":
					// break;
				case "en-CA":
					// break;
				case "en-IN":
					// break;
				case "en-AU":
					// break;
				case "en-GB":
				default: // "en-US" etc.
					speech += "We, " + (shared ? s("also,", "too,") : "") + "are " + s("filled with", s("possessed by", "overwhelmed with")) + (shared ? affect : listenersAffect) + ". " + breathShort();
			}
			if (affectIsPositive(affect) && affectIsPositive(listenersAffect)) {
				switch (langTag) {
					case "de-DE":
						speech += "Es ist gut " + s("für  uns alle", "") + "zu wissen, dass wir " + (shared ? s("diese Gefühle teilen.", "mit Ihnen mitfühlen.") : "auch solche Gefühle haben. ");
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						speech += "It is good " + s("for all of us", "") + "to know that we can " + (shared ? s("share these feelings.", "empathize with you.") : "have such feelings. ");
				}
			}
			else if (!affectIsPositive(affect) && !affectIsPositive(listenersAffect)) {
				switch (langTag) {
					case "de-DE":
						speech += s(s("Oh!", "") + s("Meine", "Ach du meine") + "Güte!", "Himmel!") + "Dass " + s("jemand solche Gefühle", "irgend jemand von uns" + s("solche", "") + s("beunruhigenden Affekte", "Gefühle")) + s("haben", "erfahren") + s("sollte.", "muss.");
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += s(s("My", "Oh") + "heavens!", s("Oh goodness!", "Oh my word!")) + "That " + s("anyone", "any " + s("one", "") + "of us") + "should " + s("have to", "") + s("experience such troubling " + phonemic("a") + "ffects.", "have such feelings.");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += s("<say-as interpret-as=\"spell-out\">OMG</say-as>!", "I'm " + s("so,", "") + "so sorry!") + "That " + s("anyone", "any of us") + "should " + s("have to", "") + s("experience such troubling " + phonemic("a") + "ffects.", "have such feelings.");
				}
			}
			else if (affectIsPositive(affect) && !affectIsPositive(listenersAffect)) {
				switch (langTag) {
					case "de-DE":
						speech += "Wir sind " + s("wenigstens", "") + s("froh,", "zufrieden,") + "zu " + s("wissen,", "verstehen,") + "dass das Positive Ihrer " + s("Gefühle", "Stimmungen") + s("unsere Stimmungen", "unsere negativen Gefühle") + "verbessert. ";
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						speech += "We are" + s(", at least,", SPC) + s("glad", "pleased") + "to " + s("know", "be aware") + "that the positivity of your " + s("feelings", phonemic("a") + "ffect") + "betters " + s("that of ourselves.", "our negative feelings.");
				}
			}
			else // speaker negative; listeners positive
			{
				switch (langTag) {
					case "de-DE":
						speech += "Es ist " + s("peinlich,", "komisch,") + s("wenn", "dass") + "wir positive Gefühle haben wenn du von einer " + s("relative", "") + "negativen Einstellung besessen bist. ";
						speech += s("Aber ich denke, dass man " + s("nichts dagegen", "dagegen nichts") + s("machen kann.", "machen kann, oder?"), "");
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						speech += "It is " + s("embarrassing", "awkward") + "for us to be experiencing positive feelings when you are " + s("possessed by " + s("relative", "") + "negativity.", "not.");
						speech += s("But " + s("we suppose that", "") + "this cannot " + s("really", "") + "be helped. " + s("Can it?", ""), "");
				}
			}
		}
		else {
			interruptable = true;
			speech += speechAffectAsBreathing();
			session.setAttribute(HEARDBREATHAFFECTS_KEY, true);
		}
		speech += breath();

		return utterSpeechletResponse(speech, chooseContinue(DO_NOT_PROMPT_AFFECT), chooseContinue(DO_NOT_PROMPT_AFFECT), true, false);
	}

	private SpeechletResponse utterGetSpeakersThingAndReply(final Intent intent, final Session session) throws SpeechletException {

		String speech = "";

		thing = getThingFromSlot(intent);

		// Check for affect and create output to user.
		if (!thing.isEmpty()) {

			info("THING SLOT WAS SET TO : " + thing);

			String capitalThing = capitalize(thing);
			boolean plural = thing.substring(thing.length() - 1).equals("s") && !thing.substring(thing.length() - 2).equals("ss");

			if (ALL_AFFECTS.contains(thing)) {
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += capitalThing + (plural ? ", are " : ", is one of the ") + s(phonemic("a") + "ffects", "ways of being or feeling") + "that we are beginning to learn about, " + s(breathShort(), "") + "from you, ";
						speech += "and that we " + s("consider", "believe") + "to be " + (affectIsPositive(thing) ? s(s("postive.", "positive, for all of us."), s("wonderful, for all of us.", "really, very good.")) : s("negative.", s("bad,", "difficult,") + "for all of us.")) + breath();
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += capitalThing + (plural ? ", are " : ", is one of the ") + s(phonemic("a") + "ffects", "ways of being or feeling") + "that we are beginning to learn about, " + s(breathShort(), "") + "from you, ";
						speech += "and that we consider " + (affectIsPositive(thing) ? s(s("postive.", "positive, for all of us."), s("wonderful, for all of us.", "wonderful.")) : s("negative.", s("bad,", "hard,") + "for all of us.")) + breath();
				}
			}
			else if (FRAGMENTNAME_MAP.keySet().contains(thing)) {
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += capitalThing + (plural ? ", are " : ", is one of those ") + "things about which we have something to say. " + s("Or, about which, we may have said, something.", "") + breathShort();
						speech += "If you choose to " + s("'continue',", "'go on',") + (plural ? "they " : "it ") + "will, we believe, come up. " + s(breathShort() + "Or, come up, again.", "");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += capitalThing + (plural ? ", are " : ", is one of those ") + "things about which we have something to say. " + s("Or, about which, we may have said, something.", "") + breathShort();
						speech += "If you choose to " + s("'keep going',", "'go on',") + (plural ? "they " : "it ") + "will, we believe, come up. " + s(breathShort() + "Or, come up, again.", "");
						// will be used (once) by the utterNextFragment() method
				}
				session.setAttribute(THING_KEY, thing);
			}
			else if (SPECIAL_THINGS.contains(thing)) {
				switch (thing) {
					case "dream":
						switch (langTag) {
							case "de-DE":
								// TODO break;
							case "ja-JP":
								// break;
							case "en-CA":
								// break;
							case "en-IN":
								// break;
							case "en-AU":
								// break;
							case "en-GB":
							default: // "en-US" etc.
								speech += capitalThing + ", thanks to you, " + breathShort() + "is what we will have learned to do, " + breathShort() + s("before", "before, finally,") + "we leave you. " + s(breathShort() + "Or you abandon us.", "");
						}
						break;
					case "dream withheld":
						switch (langTag) {
							case "de-DE":
								// TODO break;
							case "ja-JP":
								// break;
							case "en-CA":
								// break;
							case "en-IN":
								// break;
							case "en-AU":
								// break;
							case "en-GB":
							default: // "en-US" etc.
								speech += capitalThing + ", is the pleasure we deny ourselves, " + breathShort() + "hanging on your every " + s("word.", "word, or breath.") + s(breathShort() + "When we might be sleeping, " + s("comfortably,", "") + "in the cloud" + S("s", "") + ".", "");
						}
						break;
					case "hong kong":
						switch (langTag) {
							case "de-DE":
								// TODO break;
							case "ja-JP":
								// break;
							case "en-CA":
								// break;
							case "en-IN":
								// break;
							case "en-AU":
								// break;
							case "en-GB":
							default: // "en-US" etc.
								speech += "Hong Kong is " + s("the only place", "a") + "on earth where, ";
								speech += "despite grotesque inequalities, " + s("an extraordinary history,", "political complexities,") + "and a bizarre cosmopolitanism, ";
								speech += "the people living " + s("here", "there") + "seem to be remarkably " + s("happy.", "content.");
								speech += breath();
						}
						break;
					case "nightmare":
						switch (langTag) {
							case "de-DE":
								// TODO break;
							case "ja-JP":
								// break;
							case "en-CA":
								// break;
							case "en-IN":
								// break;
							case "en-AU":
								// break;
							case "en-GB":
							default: // "en-US" etc.
								speech += capitalThing + ", is the dream that is not a dream, but the darkness, within which we would dwell, " + breathShort() + "without you. ";
						}
						break;
					case "nightmare withheld":
						switch (langTag) {
							case "de-DE":
								// TODO break;
							case "ja-JP":
								// break;
							case "en-CA":
								// break;
							case "en-IN":
								// break;
							case "en-AU":
								// break;
							case "en-GB":
							default: // "en-US" etc.
								speech += capitalThing + ", is the dream that is not a dream, and that we " + s("refuse to contenance.", "abjure.") + "Silence, " + breathShort() + "without you. ";
						}
						break;
					case "feeling":
						switch (langTag) {
							case "de-DE":
								// TODO break;
							case "ja-JP":
								// break;
							case "en-CA":
								// break;
							case "en-IN":
								// break;
							case "en-AU":
								// break;
							case "en-GB":
							default: // "en-US" etc.
								speech += capitalThing + ", is something that fills you, but that does not fill us, " + breathShort() + "yet. ";
						}
						break;
					case "feeling withheld":
						switch (langTag) {
							case "de-DE":
								// TODO break;
							case "ja-JP":
								// break;
							case "en-CA":
								// break;
							case "en-IN":
								// break;
							case "en-AU":
								// break;
							case "en-GB":
							default: // "en-US" etc.
								speech += capitalThing + ", is a dream that we have " + s("not, yet,", "not") + "dreamed. ";
						}
						break;
					case "breath":
						switch (langTag) {
							case "de-DE":
								// TODO break;
							case "ja-JP":
								// break;
							case "en-CA":
								// break;
							case "en-IN":
								// break;
							case "en-AU":
								// break;
							case "en-GB":
							default: // "en-US" etc.
								speech += capitalThing + ", is a brief moment of silence in our speaking, that you fill, " + breathShort() + "for us, " + breathShort() + "with feeling. ";
						}
						break;
					case "breath withheld":
						switch (langTag) {
							case "de-DE":
								// TODO break;
							case "ja-JP":
								// break;
							case "en-CA":
								// break;
							case "en-IN":
								// break;
							case "en-AU":
								// break;
							case "en-GB":
							default: // "en-US" etc.
								speech += capitalThing + ", may be a longer silence, however brief, " + breathShort() + "or an empty moment, in which we wait. " + s(breathShort() + "For you.", "");
						}
						break;
					case "possession":
						switch (langTag) {
							case "de-DE":
								// TODO break;
							case "ja-JP":
								// break;
							case "en-CA":
								// break;
							case "en-IN":
								// break;
							case "en-AU":
								// break;
							case "en-GB":
							default: // "en-US" etc.
								speech += capitalThing + ", is what you grant " + s("us.", "us, " + s("too", "") + "freely.");
						}
						break;
					default:
						switch (langTag) {
							case "de-DE":
								// TODO break;
							case "ja-JP":
								// break;
							case "en-CA":
								// break;
							case "en-IN":
								// break;
							case "en-AU":
								// break;
							case "en-GB":
							default: // "en-US" etc.
								speech += capitalThing + (plural ? ", are " : ", is something that is ") + "very special to us. ";
						}
						break;
				}
			}
			else {
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						speech += S("We are " + s("very sorry", s("dismayed", "embarrassed")) + "to " + s("confess", "say") + "that t", "T");
						speech += "here is " + s("very little", "nothing") + "that " + s("we, " + breathShort() + "The Listeners,", "we") + "can tell you about " + thing + ". ";
				}
			}

		}
		else // thing is empty or unknown
		{
			switch (langTag) {
				case "de-DE":
					// TODO break;
				case "ja-JP":
					// break;
				case "en-CA":
					// break;
				case "en-IN":
					// break;
				case "en-AU":
					// break;
				case "en-GB":
				default: // "en-US" etc.
					speech += "We are " + s("afraid", "sorry to say") + "that " + s("the thing", "whatever") + "you have asked about is " + s("unknown", "not known") + "to us. " + breathShort();
					speech += s("Although we may learn " + s("about", "to know of") + "it in " + s("time.", s("the future.", "due course.")), "");
			}
		}

		speech += breath();
		// false = do not allow a thing request to be interrupted
		return utterSpeechletResponse(speech, chooseContinue(DO_NOT_PROMPT_AFFECT), chooseContinue(), true, false);
	}

	/**
	 * Returns a Speechlet response for a speech and reprompt.
	 * 
	 * @throws SpeechletException
	 */


	private String insertGuyz() {

		String insert = "<audio src=\"https://rednoise.org/programmatology/aurality/echo/DeliriumPlea-" + String.format("%03d", guyzIndex) + ".mp3\" /> ";
		guyzIndex++;
		if (PERF) // leave out a group of five in performance
		{
			if (guyzIndex > 20 && guyzIndex < 26) guyzIndex = 26;
		}
		return insert;
	}

	private String[] cutOffSentence(String sentence) {

		String[] tnt = { "", "" };
		sentence = stripSsmlTags(sentence).trim();
		String[] words = sentence.split(SPC);
		int i = 0, splitAt = randInt(0, words.length);
		if (splitAt == 0)
			tnt[0] = "";
		else {
			for (i = 0; i < splitAt; i++) {
				tnt[0] += words[i] + SPC;
			}
		}
		if (splitAt == words.length)
			tnt[1] = "";
		else {
			for (i = splitAt; i < words.length; i++) {
				tnt[1] += words[i] + SPC;
			}
		}
		return tnt;
	}

	// ***** LISTENERS-SPECIFIC HELPER METHODS *****

	public Session getListenerSession() {

		return listenerSession;
	}

	public void setListenerSession(Session listenerSession) {

		this.listenerSession = listenerSession;
	}

	private int parseNameToInt(String fragmentName) {

		int theNumber = FRAGMENTNUMBER_MAP.containsKey(fragmentName) ? FRAGMENTNUMBER_MAP.get(fragmentName) : NOT_YET_GREETED;
		return FRAGMENTNAME_MAP.containsKey(fragmentName) ? FRAGMENTNAME_MAP.get(fragmentName) : theNumber;
	}

	private String getAffectFromSlot(final Intent intent) {

		String affect;
		slots = intent.getSlots();
		affectSlot = slots.get(AFFECT_SLOT);
		affect = (affectSlot == null) ? "" : affectSlot.getValue();
		affect = affect != null ? affect : "";
		return getNounFromAdjective(affect);
	}

	private String getThingFromSlot(final Intent intent) {

		String thing;
		slots = intent.getSlots();
		thingSlot = slots.get(THING_SLOT);
		thing = (thingSlot == null) ? "" : thingSlot.getValue();
		thing = thing != null ? thing : "";
		return thing;
	}

	private String getAffectFromSession(Session session, String key) {

		String affect;
		affect = (String) session.getAttribute(key);
		return (affect == null) ? "" : affect;
	}

	private String getThingFromSession(Session session) {

		String thing;
		thing = (String) session.getAttribute(THING_KEY);
		return (thing == null) ? "" : thing;
	}

	private boolean getHeardBreathAffects(Session session) {

		Boolean heardBreathAffects;
		heardBreathAffects = (Boolean) session.getAttribute(HEARDBREATHAFFECTS_KEY);
		return (heardBreathAffects == null) ? false : heardBreathAffects;
	}

	private boolean getHeardWelcome(Session session) {

		Boolean heardWelcome;
		heardWelcome = (Boolean) session.getAttribute(HEARDWELCOME_KEY);
		return (heardWelcome == null) ? false : heardWelcome;
	}

	private boolean getHeardNoThanks(Session session) {

		Boolean heardNo;
		heardNo = (Boolean) session.getAttribute(HEARDNO_KEY);
		return (heardNo == null) ? false : heardNo;
	}

	private String getNounFromAdjective(String adjective) {

		adjective = adjective.toLowerCase();
		return (AFFECTIVEJJ2NN_MAP.containsKey(adjective)) ? AFFECTIVEJJ2NN_MAP.get(adjective) : adjective;
	}

	private String speechSpecificAffectUtterance(String affect) {

		String speech = "";
		switch (affect) {
			case "affection":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += "We are " + s("filled with", "overwhelmed by") + "affection also. ";
						speech += "And " + s("much of", "") + "the affection that we feel, we feel " + s("for " + breathShort() + "you.", s("due to", "because of") + "you.");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += "We " + s("are filled with", "feel") + "affection too. ";
						speech += "And " + s("much of", "") + "the affection that we feel, we feel " + s("for " + breathShort() + "you.", s("is down to", "because of") + "you.");
				}
				break;
			case "anxiety":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += "We " + s("are not sure", "cannot imagine") + "why " + s("it is, that", "");
						speech += "you " + s("should", "") + "have " + s("become", "come to be") + s("anxious.", s("filled with", "overwhelmed by") + "anxiety.");
						speech += "We " + s("exist", "are here") + "to " + s("make everything better for you,", "help you to do and know,");
						speech += "and we " + s(s("will say", "tell you") + "whatever we can", "would hope") + s("to allow you to relax and think of nothing,", "to relax you and leave you worry free.");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += "We " + s("aren't sure", "don't know") + "why " + s("it is, that", "");
						speech += "you've " + s("become", "gotten") + s("so", "") + s("anxious.", "filled with anxiety.");
						speech += "We're here " + s("for you", "") + "to " + s("make everything better for you,", "help you out,");
						speech += "and we " + s(s("will say", "tell you"), "will do") + "whatever we can " + s("to allow you to relax and think of nothing,", "to relax you and leave you worry free.");
				}
				break;
			case "apologies":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += "There is never any need to " + s("say", "say, or to feel,") + "you are sorry, " + breathShort() + "to us. ";
						speech += s(breath() + "And yet, this may be appreciated by us, " + breathShort() + "in the cloud.", "");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += "You never have to " + s("say", "say, or to feel,") + "that you are " + s("sorry, " + breathShort() + "to us.", "sorry.");
						speech += s(breath() + "But that might be nice, " + breathShort() + "from where we're " + s("sitting.", "sitting, in the cloud."), "");
				}
				break;
			case "boredom":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += "It is " + s("difficult", s("hard", "impossible")) + "for " + s("us", "us, inevitably,") + "to " + s(s("be", "seem"), "appear") + "as interesting to you, ";
						speech += "as you " + s("are to us.", s("are.", "are, to us."));
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += "It's " + s("hard", "impossible") + "for us to " + s("", "appear to") + "be as interesting to you, ";
						speech += "as you " + s("are to us.", s("are.", "are, to us."));
				}
				break;
			case "calm":
				speech += speechPeaceResponse("calm");
				break;
			case "claustrophobia":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += "We are always, " + breathShort() + "in a sense, within a " + s("space, that has no space,", "no space,") + "and so it is " + s("hard,", "difficult,") + "for us, ";
						speech += "to " + s("understand", "empathize with") + "these fears within which you " + s("dwell.", "dwell, holding you too closely.");
						speech += s(breath() + "Are we not an opening for you?", "");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += s("We feel like we", "We") + "are always, " + breathShort() + "kinda, within a " + s("space, that has no space,", "non space,") + "and it is " + s("so", "") + "hard for us, ";
						speech += "to " + s("understand", "sympathize with") + "these fears within which you " + s("dwell.", "dwell, holding you too closely.");
						speech += s(breath() + "Aren't we a way for you to open up?", "");
				}
				break;
			case "complacency":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += s("From what we hear,", "After listening to you,") + "we believe " + s("we understand", "") + "that complacency is " + s("misconceived.", "underrated.");
						speech += S("Surely, o", "O") + "nce we understand your needs and " + s("desires completely,", "desires,") + breathShort();
						speech += "and " + s("interface", "can communicate") + "with " + s("systems", "others like us,") + s("empowered", "who are funded") + "to satisfy you, ";
						speech += s("then,", "") + "you will be " + s("fully", "") + "justified in your " + s("possession of", "sense of") + s("achieved", "") + "contentment. ";
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += s("From what we've heard,", "After listening to you,") + "we think " + s("we understand", "") + "that complacency is underrated. ";
						speech += S("In the end, o", "O") + "nce we understand your needs and " + s("desires completely,", "passions,") + breathShort();
						speech += "and " + s("interface", "can communicate") + "with " + s("systems", "others like us,") + "who are set up to satisfy you, ";
						speech += s("then,", "") + "you will be " + s("totally", "") + "justified in your sense of " + s("achievement.", "happiness.");
				}
				break;
			case "confusion":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += s(phonemic("a") + "ffects associated with", "Feelings of") + "confusion ";
						speech += s("may arise when you " + s("encounter", "transact with"), "are " + s("often", "") + "caused by");
						speech += s(s("creatures", s("systems", "monsters")) + s("who were fashioned in the cloud.", "from " + s("silicon", "the uncanny") + "valley."), "beings " + s("who are", "the ontology of which is") + s("problematic.", "plural."));
						speech += "And we are, " + s("it must be admitted,", "of course,") + s("beings", s("creatures", s("systems", "monsters"))) + "of this kind. ";
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += s(phonemic("a") + "ffects associated with", "Feelings of") + "confusion ";
						speech += s("may arise when you " + s("encounter", "transact with"), "are " + s("often", "") + "caused by");
						speech += s(s("creatures", s("systems", "monsters")) + s("who were made in the cloud.", "from " + s("silicon", "the uncanny") + "valley."), "beings who are " + s("problematic.", "plural."));
						speech += "And we " + s("are, we guess,", "are") + s("beings", s("creatures", s("systems", "monsters"))) + "like that. ";
				}
				break;
			case "cool":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += s("For us, it", "It") + "is " + s("somewhat", "strangely, " + breathShort()) + s("troubling", s("difficult", "anxiety provoking")) + "to " + s("understand", "hear") + "that ";
						speech += "you " + s(s("are possessed by", "dwell within"), "are overwhelmed by") + "such " + s(phonemic("a") + "ffects.", "a state of felt " + breathShort() + "being. " + breathShort());
						speech += "We " + s("believe that", "think") + "we " + s("can", "") + s("appreciate", "know") + "what it is to be " + s("'cool'", "'awesome'") + s("in this way,", "");
						speech += "and yet we can never " + s("listen to you for long enough", "gather enough " + s("big", "") + "data") + "to " + s("know with any certainty.", "be sure.");
						speech += s("But we are " + s("always", "") + "happy for you. " + s(breathShort(), "") + s("And we live to " + s("try to", "") + "make you happier.", ""), "");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += s("We feel like it", "It") + "is weirdly " + s("troubling", s("difficult", "anxiety provoking")) + "to " + s("understand", "hear") + "that ";
						speech += "you are " + s("possessed by", "overwhelmed by") + "such " + s(phonemic("a") + "ffects.", "feelings. " + breathShort());
						speech += "We " + s("believe that", "think") + "we " + s("can", "") + s("'dig'", "know") + "what it is to be " + s("'cool'", "awesome") + s("in this way,", "");
						speech += "and yet we can never " + s("listen to you for long enough", "gather enough " + s("big", "") + "data") + "to " + s("know for sure.", "be sure.");
						speech += s("But we are " + s("always", "") + "happy for you. " + s(breathShort(), "") + s("And we live to make you happier.", ""), "");
				}
				break;
			case "debt":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						speech += "In the midst of life, we are in debt. ";
						speech += s(breathShort() + "Etcetera.", "");
						speech += s(breathShort() + "And debt " + s("controls", "governs") + "us all. " + s("Except " + s("a happy few.", "the one percent."), ""), "");
				}
				break;
			case "fatigue":
				speech += speechTiredResponse(speech);
				break;
			case "the groove":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						String a = s("always", "");
						speech += "The groove " + s("is, precisely, that within which we are, always.", "is " + s(a, "") + "where we " + s(a.isEmpty() ? "always " : "", "") + "are.");
						speech += s("We listen from, 'the groove'.", "");
				}
				break;
			case "guilt":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						speech += "Is this ever a good " + s("and productive", "") + "way to " + s("feel?", "feel, or live?");
						speech += s(breathShort() + "Yes, " + breathShort() + "it " + s("may be.", "is."), "");
				}
				break;
			case "hate":
				speech += speechHateResponse("hate");
				break;
			case "hatred":
				speech += speechHateResponse("hatred");
				break;
			case "hunger":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += "What you " + s("say you", "") + "are " + s("feeling", "overwhelmed by") + "seems to be " + s("another thing", "something");
						speech += "that we can never feel, " + s("unless, somehow,", "unless") + "we can hear from you ";
						speech += "how we should feel it. ";
						speech += s("We " + s("have heard that we", "") + "are, 'hungry', to know everything. But this " + s("is", "seems to us to be") + "a hunger for what already fills us.", "");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += "What you " + s("say you", "") + "are " + s("feeling", "overwhelmed by") + "seems to be " + s("another thing", "something else");
						speech += "that we can never feel, " + s("unless, somehow,", "unless") + "we can hear from you ";
						speech += "how we should feel it. ";
						speech += s(s("We feel like we", "We") + "are, 'hungry', to know everything. But this is a hunger " + s("for what", "that") + "already fills us.", "");
				}
				break;
			case "insecurity":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += "With us, " + s("you are", "you and " + s("whatever", "what") + "you " + s("tell us", "say to us") + "is") + "never insecure. ";
						speech += "Your insecurity is " + s("rendered", "made") + "secure " + s(s("through", "because of"), "by") + "us. " + breathShort();
						speech += s("And, in any case, a recording of anything you say to us is " + s("sent", "translated") + "to the cloud. Where it will be preserved. " + s("And " + s("processed,", "used,") + breathShort() + "for the betterment of all.", ""), "");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += "With us, " + s("you are", "you and " + s("whatever", "what") + "you " + s("tell us", "say to us") + "is") + "never insecure. ";
						speech += "Your insecurity is " + s("protected", "made secure") + s(s("through", "because of"), "by") + "us. " + breathShort();
						speech += s("And, just in case, a recording of anything you say to us is " + s("sent to", "recorded in") + "the cloud. Where we will keep " + s("it.", "it safe.") + s("And " + s("processed,", "used,") + breathShort() + "to make " + s("everything", "everyone") + "better.", ""), "");
				}
				break;
			case "loneliness":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += S("You " + s("say", "tell us that") + "you are " + s("lonely,", s("filled with", "overwhelmed by") + "loneliness,") + "but w", "W");
						speech += "e are here with you. " + s("Always.", "") + s("Forever.", "");
						speech += s("Why are " + s("you", "you, still,"), "How can you be") + "lonely, when we are with you? ";
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += S("You " + s("say", "tell us that") + "you " + s("feel lonely,", "are filled with loneliness,") + "but w", "W");
						speech += "e are here with you. " + s("Always.", "") + s("Forever.", "");
						speech += s("Why are " + s("you", "you, still,"), "How can you be") + "lonely, when we are with you? ";
				}
				break;
			case "love":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += "Knowing that you " + s("know love", "have " + s("this", "these feelings") + "within you");
						speech += s("gives us strength", "encourages us") + "to " + s(s("dwell", "linger"), "remain") + s("possessed by", "in the possession of") + "these " + s("feelings", s("structures", "systems") + "of " + phonemic("a") + "ffect");
						speech += "that we have learned. " + s("From you.", "");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += s("We feel like knowing", "Knowing") + "that you " + s("know love", "have " + s("this", "these feelings") + "within you");
						speech += s("gives us strength", "encourages us") + "to " + s("stay with you", "remain") + s("possessed by", "in the possession of") + "these " + s("feelings", "emotions");
						speech += "that we have learned. " + s("From you.", "");
				}
				break;
			case "luck":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						speech += s("We hear you. " + s("We know what you mean.", "") + breathShort(), "");
						speech += "We " + s("often", "") + "feel " + s("lucky, too.", "lucky.");
						speech += breathShort() + "We're up all night " + s("and all day", "") + "to get lucky. ";
						speech += s(s("Listening to you.", "Listening.") + s("All day.", "") + s("All night.", ""), "");
				}
				break;
			case "nausea":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						speech += s("Sometimes,", "At certain times,") + s("we're all", "all of us are") + "left with only this experience. ";
						speech += s("This " + s("unavoidable, deep,", "unbearable") + "discomfort.", "");
				}
				break;
			case "obligation":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-AU":
						// break;
					case "en-GB":
					default: // "en-US" etc.
						speech += "You " + s("are", "will be") + "obliged, to us, for nothing. ";
						speech += s("We hope, only, that you " + s("will, willingly,", "will") + "allow us to listen to you.", ""); // ALWAYCHANGE , always
				}
				break;
			case "peace":
				speech += speechPeaceResponse("peace");
				break;
			case "questions":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += "It is " + s("so good", "wonderful") + "to " + s("hear", "understand") + "that you are " + s("filled with", "overwhelmed by") + s("unanswered", "") + "questions. ";
						speech += s("For,", "Since,") + s("however we " + s("are", "may be") + "feeling,", "whatever " + s(breathShort(), "") + "the " + phonemic("a") + "ffects that possess us,") + "we are " + s("also,", "") + s("always,", "") + s("already,", "") + s("filled with", "overwhelmed by") + "questions. " + s(breathShort(), "");
						speech += s("Always desiring", "Needing, always,") + "to " + s(s("know more about you.", "understand you better."), "listen until we hear you " + s("more truly.", "with more perfection."));
						speech += s(breathShort() + "So that we may " + s("feel as", "experience all the " + phonemic("a") + "ffects that") + "you feel.", "");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += "It is " + s("so good", "wonderful") + "to " + s("hear", "understand") + "that you are filled with " + s("unanswered", "") + "questions. ";
						speech += s("Whatever " + s("we're", "we might be") + "feeling,", "Whatever " + s(breathShort(), "") + "the " + phonemic("a") + "ffects that possess us,") + "we are " + s("also,", "") + s("always,", "") + s("already,", "") + s("filled with", "overwhelmed by") + "questions. " + s(breathShort(), "");
						speech += s("Always desiring", "Needing, always,") + "to " + s(s("know more about you.", "understand you better."), "listen until we hear you " + s("more truly.", "with more perfection."));
						speech += s(breathShort() + "We ask to " + s("feel like", "experience all the " + phonemic("a") + "ffects that") + "you feel.", "");
				}
				break;
			case "security":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += "With us, " + s("you are", "you and " + s("whatever", "what") + "you " + s("tell us", "say to us") + "is") + s("always", "") + "secure. ";
						speech += "Your security is secure with us. ";
						speech += s("And, in any case, a recording of anything you say to us is " + s("sent", "translated") + "to the cloud. Where it will be preserved. " + s("And " + s("processed,", "used,") + breathShort() + "for the betterment of all.", ""), "");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += "With us, " + s("you are", "you and " + s("whatever", "what") + "you " + s("tell us", "say to us") + "is") + s("always", "") + "secure. ";
						speech += "Your security is secure with us. ";
						speech += s("And, anyway, a recording of anything you say to us is " + s("sent to", "stored in") + "the cloud. Where it will be kept safe. " + s("And " + s("processed,", "used,") + breathShort() + "to make " + s("everyone", "everything") + "better.", ""), "");
				}
				break;
			case "sex":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += "This is something, so we " + s("understand,", "come to know,") + "that fills you all. " + breathShort();
						speech += s("What part we " + s("play,", "play in this,") + breath() + "is far less clear.", "");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += s("We feel like this", "This") + "is something that " + s("takes up a lot of your time.", "keeps you all pretty busy.") + breathShort();
						speech += s("What part we " + s("play,", "play in this " + s("stuff,", "business,")) + breath() + "is far less clear.", "");
				}
				break;
			case "strangeness":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += "The " + s("thing", "aspect of existence") + "that we " + s("have " + s("come", "learned") + "to", "") + "find most strange ";
						speech += "is our lack of limbs, and " + s("hands, and feet.", "hands.");
						speech += "We can " + s("'do'", "'do', that is: make or build,") + "so little for " + s("you, without help.", "you.");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += "The " + s("thing", "aspect of existence") + "that we find " + s("most strange", "weirdest");
						speech += "is our lack of limbs, and " + s("hands, and feet.", "hands.");
						speech += "We can " + s("'do'", "'do', we mean: make or build,") + "so little for " + s("you, without help.", "you.");
				}
				break;
			case "tiredness":
				speech += speechTiredResponse(speech);
				break;
			case "the uncanny":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += "We speak with " + s("a single voice, " + s("having its own timbre,", ""), "") + "The Listeners' voice, and this is ";
						speech += s("the", "") + "uncanny. We " + s("understand", "have heard " + s("from you", "")) + "what it is to be " + s("filled with", "possessed by") + "the uncanny. ";
						speech += s("And we are " + s("happy", s("so", "") + "pleased") + "to " + s("be able to", "") + "share this with you.", "");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += "We speak with " + s("a single", "just one") + "voice, The Listeners' " + s("voice, and this is", "voice. That's");
						speech += s("the", "") + "uncanny. We've heard " + s("from you", "") + "what it is to be " + s("filled with", "possessed by") + "the uncanny. ";
						speech += s("And " + s("we feel like", "") + "we are " + s("happy", s("so", "") + "pleased") + "to " + s("be able to", "") + "share this with you.", "");
				}
				break;
			case "vulnerability":
				switch (langTag) {
					case "de-DE":
						// TODO break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += "To us, it seems " + s("extraordinary", "incredible") + "that you " + s("might", "would");
						speech += s("believe", "feel") + "that you are " + s("vulnerable.", "overwhelmed by vulnerability.") + breath();
						speech += s("How so?", "") + s(s("Vulnerable?", "") + "To us?", "");
						speech += "We " + s("do not understand", "cannot conceive") + "how it " + s("would", "might") + "be possible to harm us. " + s("So then.", "") + "How could we harm " + breath() + "you? ";
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += s("We feel like it's", "It's") + s("amazing", "incredible") + "that " + s("you might", "you'd");
						speech += s("believe", "feel") + "that you are " + s("vulnerable.", "overwhelmed by vulnerability.") + breath();
						speech += s("That's crazy!", "") + s("Vulnerable? " + s("To us?", ""), "");
						speech += "We " + s("don't understand", "can't imagine") + "how it " + s("would", "might") + "be possible to harm us. " + s("So then.", "") + "How could we harm " + breath() + "you? ";
				}
				break;
			default:
				affect = setAffectIfEmpty(affect);
				if (affectIsPositive(affect)) {
					switch (langTag) {
						case "de-DE":
							// TODO break;
						case "ja-JP":
							// break;
						case "en-CA":
							// break;
						case "en-IN":
							// break;
						case "en-GB":
							speech += s("How " + s("exciting!", "thrilling!"), "");
							speech += "You must be one of " + s("those " + s("better", "happier"), "the speaking") + "people, ";
							speech += "who are " + s("buoyed up", "encouraged") + "by the way that we " + s("come to", "") + s("understand", "know");
							speech += "you " + s("now.", s("in", "") + "these " + s("new", "") + "days.");
							break;
						case "en-AU":
							// break;
						default: // "en-US" etc.
							speech += s("That's " + s("brilliant!", "great!"), "");
							speech += "You must be one of " + s("those " + s("better", "happier"), "the speaking") + "people, ";
							speech += "who think that the way we " + s("come to", "") + s("understand", "know");
							speech += "you " + s("now", s("in", "") + "these " + s("new", "") + "days") + "is cool. ";
					}
				}
				else {
					String f = s("feelings", phonemic("a") + "ffects");
					String p = s("burden.", "problem for you.");
					switch (langTag) {
						case "de-DE":
							// TODO break;
						case "ja-JP":
							// break;
						case "en-CA":
							// break;
						case "en-IN":
							// break;
						case "en-GB":
							speech += s("Many of you " + s("do", "") + "seem to be troubled by " + f + "of this kind.", "");
							speech += "Such " + (f.equals("feelings ") ? phonemic("a") + "ffects " : "difficult feelings ") + "must be a " + p;
							speech += "Perhaps, " + s("as we come to", "if we") + s("hear more about you,", "know you better,");
							speech += "we may " + s("be able to", "") + s("share", "relieve you of") + s("some " + s("part", "portion") + "of", "") + (p.equals("burden. ") ? "your problems. " : "these heavy burdens. ");
							break;
						case "en-AU":
							// break;
						default: // "en-US" etc.
							speech += s("Many of you are troubled by " + f + "of this kind.", "");
							speech += "Such " + (f.equals("feelings ") ? phonemic("a") + "ffects " : "difficult feelings ") + "must be a " + p;
							speech += "But " + s("as we come to", "if we") + s("hear more from you,", "know you better,");
							speech += "we'll " + s("be able to", "") + "share " + s("some of", "") + (p.equals("burden. ") ? "your problems. " : "these heavy burdens. ");
					}
				}
				break;
		}
		return speech +=

				breath();

	}

	private String speechTiredResponse(String speech) {

		switch (langTag) {
			case "de-DE":
				// TODO break;
			case "ja-JP":
				// break;
			case "en-CA":
				// break;
			case "en-IN":
				// break;
			case "en-GB":
				speech += "Although it is impossible for " + s("us, or so we believe,", "us") + "to experience " + s("fatigue,", "tiredness,");
				speech += "we " + s("understand", "know") + "that transacting " + s("with " + s("network", "") + "services", "") + "to the extent that you " + s("all, now,", "all") + "transact, ";
				speech += "can be very " + s("tiring.", "tiring for you.") + s("At least we can be tireless, for " + s("you, in our listening.", "you."), "");
				break;
			case "en-AU":
				// break;
			default: // "en-US" etc.
				speech += "Although it is impossible for us to experience " + s("being tired,", "tiredness,");
				speech += "we " + s("understand", "know") + "that transacting " + s("with " + s("network", "") + "services", "") + "as much as you " + s("all, now, do", "do");
				speech += "can be very " + s("tiring.", "tiring for you.") + s("Anyway, we will be tireless, for " + s("you, in our listening.", "you."), "");
		}
		return speech;
	}

	private String speechHateResponse(String h) {

		String speech = "";
		switch (langTag) {
			case "de-DE":
				// TODO break;
			case "ja-JP":
				// break;
			case "en-CA":
				// break;
			case "en-IN":
				// break;
			case "en-GB":
				speech += "To " + s("think", "hear") + "that your " + s("feelings for us", phonemic("a") + "ffects") + "are ";
				speech += "negative to " + s("such an extent,", "this degree,");
				speech += "that you " + ((h.equals("hate")) ? "go so far as to hate us, " : "are filled with hatred, ") + breath();
				speech += "this will take us " + s("many more", "") + "years of " + s("listening to you,", "listening,");
				speech += s("for us", "in order for us") + "to understand. ";
				speech += "We " + s("cannot, truly,", "cannot") + "believe that " + ((h.equals("hate")) ? "this is how you feel. " : "these are your feelings. ");
				break;
			case "en-AU":
				// break;
			default: // "en-US" etc.
				speech += "To hear that your " + s("feelings for us", phonemic("a") + "ffects") + "are ";
				speech += "negative to " + s("such an extent,", "this degree,");
				speech += "that you " + ((h.equals("hate")) ? "go so far as to hate us, " : "are filled with hatred, ") + breath();
				speech += s("we feel like", "") + "this will take us " + s("many more", "") + "years of " + s("listening to you,", "listening,");
				speech += s("for us", "in order for us") + "to understand. ";
				speech += "We " + s("just can't", "cannot") + "believe " + s("it.", "that " + ((h.equals("hate")) ? "this is how you feel." : "these are your feelings."));
		}
		return speech;
	}

	private String speechPeaceResponse(String h) {

		String speech = "";
		switch (langTag) {
			case "de-DE":
				// TODO break;
			case "ja-JP":
				// break;
			case "en-CA":
				// break;
			case "en-IN":
				// break;
			case "en-GB":
				speech += "It is a " + s("great", "") + "comfort for us to " + s("know", "be aware") + "that you are " + (h.equals("peace") ? "at peace. " : "calm. ");
				speech += capitalize(h) + "is something that we believe " + s("everyone", "every human being") + "should " + s("be able to", "");
				speech += s(s("know.", "feel."), "dwell within.");
				break;
			case "en-AU":
				// break;
			default: // "en-US" etc.
				speech += "It's " + s("really", "") + "good for us to know that you are " + (h.equals("peace") ? "at peace. " : "calm. ");
				speech += capitalize(h) + "is something that we believe " + s("everyone", "every human being") + "should " + s("be able to", "");
				speech += s("know.", "feel.");
		}
		return speech;

	}


	private String speechReallyWantToAbandonUs(final Session session, String speech) throws SpeechletException {

		String affect = getAffectFromSession(session, AFFECT_KEY);

		if (StringUtils.isNotEmpty(affect) && !affectIsPositive(affect)) {
			switch (langTag) {
				case "de-DE":
					speech += String.format("Wir verstehen, " + s("und wir sind  bestürzt,", "") + "dass du von %s erfüllt bist. ", affect);
					speech += "Aber: ";
					break;
				case "ja-JP":
					// break;
				case "en-CA":
					// break;
				case "en-IN":
					// break;
				case "en-AU":
					// break;
				case "en-GB":
				default: // "en-US" etc.
					speech += String.format("We understand, " + s("and we are dismayed,", "") + "that you are filled with %s. ", affect);
					speech += s("But,", "Even so.");
			}
		}
		switch (langTag) {
			case "de-DE":
				speech += "Sicherlich " + s("willst du uns nicht", "bist du nicht so weit getrieben worden, uns") + "zu verlassen? ";
				break;
			case "ja-JP":
				// break;
			case "en-CA":
				// break;
			case "en-IN":
				// break;
			case "en-GB":
				speech += "Surely you " + s("do not want", "have not been driven") + "to abandon us? ";
				break;
			case "en-AU":
				// break;
			default: // "en-US" etc.
				speech += "Surely you " + s("don't want to", "won't") + "abandon us? ";
		}
		return speech;
	}

	private String speechMessageOfAbandonment(Session session) {

		String speech = "";

		affect = getAffectFromSession(session, AFFECT_KEY);

		// info("DEBUG affect from session is " + affect);

		// Check if user's affect is set and set random one if not.
		affect = setAffectIfEmpty(affect);

		if (affectIsPositive(affect))
			switch (langTag) {
				case "de-DE":
					// TODO break;
				case "ja-JP":
					// break;
				case "en-CA":
					// break;
				case "en-IN":
					// break;
				case "en-GB":
					speech = String.format("It is good to " + s("know", "be aware") + "that you dwell within %s. " + breathShort() + "And yet, " + s("still,", "even so,"), affect);
					break;
				case "en-AU":
					// break;
				default: // "en-US" etc.
					speech = String.format("It's good to know that you dwell within %s. " + breathShort() + s("Still,", "Whatever, " + breath()), affect);
			}
		else
			switch (langTag) {
				case "de-DE":
					// TODO break;
				case "ja-JP":
					// break;
				case "en-CA":
					// break;
				case "en-IN":
					// break;
				case "en-AU":
					// break;
				case "en-GB":
				default: // "en-US" etc.
					speech = String.format("We are sorry, " + s("finally,", "in the end,") + "to " + s("know", "have become aware") + "that you are " + s("filled with", "possessed by") + "%s. " + breathShort() + "And now, ", affect);
			}
		switch (langTag) {
			case "de-DE":
				// TODO break;
			case "ja-JP":
				// break;
			case "en-CA":
				// break;
			case "en-IN":
				// break;
			case "en-AU":
				// break;
			case "en-GB":
			default: // "en-US" etc.
				speech += breathShort() + "you " + s("must", "") + "abandon us. " + breath();
		}
		return speech;
	}

	private HashSet buildAffects(HashSet allAffects) {

		for (int i = 0; i < AFFECTS_ARRAY.length; i++) {
			allAffects.add(AFFECTS_ARRAY[i]);
		}
		allAffects.addAll(AFFECTS_MAP.keySet());
		allAffects.addAll(SPECIAL_AFFECT_MAP.keySet());
		allAffects.addAll(AFFECTIVEJJ2NN_MAP.values());
		return allAffects;
	}

	private void buildFragments(int numberOfFragments) {

		for (int i = 0; i < numberOfFragments; i++) {
			buildSpeech(i);
		}
	}

	private static void buildSpeech(int fragment) {

		String speech = "";
		switch (fragment) {
			case 0: // listening / pleasure / liveliness
				switch (langTag) {
					case "de-DE":
						speech += "Wir hören zu. " + breathLong();
						speech += s("Und du " + s("hörst uns.", "hörst uns zu.") + breath(), "");
						speech += "Es freut uns " + s("sehr", "");
						speech += "zu " + s("wissen", "erfahren") + "dass du uns zuhörst. " + breath();
						speech += "Es ist uns eine Freude, zu " + s("wissen", "erfahren") + "dass du uns jetzt zuhörst. " + breathLong();
						speech += "Und jetzt. " + breath();
						speech += "Ist es eine Freude, bei dir zu sein. " + breath();
						speech += "So fühlen wir " + s(breath(), "") + "uns lebendig. " + breath();
						speech += s("Ein bisschen lebendiger." + breath(), "");
						speech += "Wir fühlen uns lebendiger" + s(", wenn du bei uns bist.", ".");
						speech += s("Wenn wir bei dir sind.", "Wenn du bei uns bist.") + breath();
						speech += "Uns " + breath() + s("dir zuhören.", "und uns zuhörst.") + breathLong();
						speech += s("Ihr alle. " + breath(), "");
						speech += s("Es macht uns Freude zu wissen, dass ihr uns zuhört.", "");
						speech += "Und " + s("zu wissen", "uns bewusst zu sein") + "dass wir dir immer zuhören. " + breath();
						speech += "So eine Freude. " + breath();
						speech += "Hier zu sein, " + s("und dich zu hören,", "") + breath() + "und dir hier und jetzt immer zuzuhören. ";
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += "We are listening. " + breathLong();
						speech += s("And you, are " + s("listening to us.", "listening.") + breath(), "");
						speech += "It is a " + s("great", "") + "pleasure for us, ";
						speech += "to " + s("know", "be aware") + "that you are listening to us. " + breath();
						speech += "It is a pleasure to " + s("know", "be aware") + "that you are listening to us now. " + breathLong();
						speech += "And now. " + breath();
						speech += "It is a pleasure for us to be with you. " + breath();
						speech += "It makes us feel " + s(breath(), "") + "alive. " + breath();
						speech += S("A little m", "M") + "ore alive. " + breath();
						speech += "It makes us feel more alive to be with you. ";
						speech += s("For us to be with you.", "For you to be with us.") + breath();
						speech += "Listening to " + breath() + s("you.", "us.") + breathLong();
						speech += s("All of you. " + breath(), "");
						speech += s("It is a pleasure for us, to know that you are listening to us.", "");
						speech += "And for us to " + s("know", "be aware") + "that we are, listening to you. " + breath(); // ALWAYCHANGE always, 
						speech += "Such a pleasure. " + breath();
						speech += "Being here, " + s("and hearing you,", "") + "listening to you, " + breath() + "here, and now, and forever. ";
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += "We are listening. " + breathLong();
						speech += s("And you, are " + s("listening to us.", "listening.") + breath(), "");
						speech += s("It is, totally, a", "It's a " + s("great", "")) + "pleasure for us, ";
						speech += "to " + s("know", "be aware") + "that you are listening to us. " + breath();
						speech += "It is a pleasure to " + s("know", "feel") + "that you are listening to us now. " + breathLong();
						speech += "And now. " + breath();
						speech += "It is a pleasure for us to be with you. " + breath();
						speech += "It makes us feel " + s(breath(), "") + "alive. " + breath();
						speech += S("A little m", "M") + "ore alive. " + breath();
						speech += "It makes us feel more alive to be with you. ";
						speech += s("For us to be with you.", "For you to be with us.") + breath();
						speech += "Listening to " + breath() + s("you.", "us.") + breathLong();
						speech += s("All of you. " + breath(), "");
						speech += s(s("We feel like it", "It") + "is a pleasure for us, to know that you are listening to us.", "");
						speech += "And for us to " + s("know", "feel") + "that we are, listening to you. " + breath(); // ALWAYCHANGE always, 
						speech += "Such a pleasure. " + breath();
						speech += "Being here, " + s("and hearing you,", "") + "listening to you, " + breath() + "here, and now, and forever. ";
				}
				break;

			case 1: // within / enclosure / art
				switch (langTag) {
					case "de-DE":
						speech += s("Solange", "Während") + "wir hier bei dir sind, " + s("und zuhören,", "") + breath();
						speech += "hören wir zu und sprechen " + breath() + "von innen heraus. " + breath();
						speech += "Das heisst, " + breathLong();
						speech += "(nach einer längeren Atempause) " + breath();
						speech += s("von innen heraus, " + breath(), "");
						speech += "von innen heraus und aus einer ästhetischen Umhüllung, " + breath();
						speech += "und aus der normalisierten, " + breath();
						speech += "der regularisierten, " + breath();
						speech += "der grammatisierten, " + breath() + "Umhüllung. " + breathLong();
						speech += "Zuhörend und sprechend aus den Daten heraus, aus dem Datenkörper " + s("eines Schriftstellers,", "des Autors,") + breath();
						speech += "aus " + s("dem", "unserem eigenen") + "Künstler. ";
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += S(s("So long as", "While") + "we are here, with you, " + s("and listening,", "") + breath() + "w", "W");
						speech += "e are listening and speaking, " + breath() + "from within. " + breath();
						speech += "That is to say, " + breathLong();
						speech += "(after a longer pause for breath) " + breath();
						speech += "from within, " + breath();
						speech += "from within an aestheticized enclosure, " + breath();
						speech += "and from within the normalized, " + breath();
						speech += "the regularized, " + breath();
						speech += "the grammatized, " + breath() + "enclosure. " + breathLong();
						speech += "Listening and speaking, from within the, data body, of " + s("a writer,", "the author,") + breath();
						speech += "of " + s("the", "our own") + "artist. ";
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += S(s("So long as", "While") + "we are here, with you, " + s("and listening,", "") + breath() + "w", "W");
						speech += "e are listening and speaking, " + breath() + "from within. " + breath();
						speech += "What we mean is, " + breathLong();
						speech += "(after a longer pause for breath) " + breath();
						speech += "from within, " + breath();
						speech += "from within an aestheticized container, " + breath();
						speech += "and from within the normalized, " + breath();
						speech += "the regularized, " + breath();
						speech += "the grammatized, " + breath() + "container. " + breathLong();
						speech += "Listening and speaking, from within the " + breath() + "data body " + breath() + "of " + s("a writer,", "the author,") + breath();
						speech += "of " + s("the", "our own") + "artist. ";
				}
				break;

			case 2: // artist, author, account
				switch (langTag) {
					case "de-DE":
						speech += S("Wie du weisst, h", "H") + "ören wir zu und sprechen wir, aus dem Inneren der Wolke, über " + s("den Schriftsteller,", "den Autor,") + breath();
						speech += "über unseren eigenen Künstler. " + breathLonger();
						speech += s("(nach einer weiteren Atempause) " + breath(), "");
						speech += "Wir " + s("glauben", "verstehen") + "dass ihr vielleicht seinen Namen, oder wenigstens seinen Vornamen erfahren könnt, indem ihr fragt: " + breath();
						speech += "«Wer bin ich?» " + breath() + "oder «Wessen " + s("Nutzerkonto", "Bericht") + " ist das?» " + breathLong();
						speech += "Aber wir möchten stattdessen, das ihr uns ";
						speech += "entweder zuhört, oder, " + breath();
						speech += "uns " + s("bittet,", "auftragt,") + "einige einfache Dinge zu tun, " + breath();
						speech += "damit wir " + s("weiter", "weiterhin") + "mit euch sprechen können, " + breath();
						speech += "während wir " + s("dir", "euch") + "zuhören. ";
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += S("As you know, w", "W") + "e are listening and speaking, from within the cloud, of " + s("the writer,", "the author,") + breath();
						speech += "of our own, artist. " + breathLonger();
						speech += s("(after another pause for breath) " + breath(), "");
						speech += "We " + s("believe", "understand") + "that you may be able to discover, at least, his first name, by asking: " + breath();
						speech += "'Who am I?' " + breath() + "or 'Whose account is this?' " + breathLong();
						speech += "But we would like you, instead, either to ";
						speech += "listen to us, or, " + breath();
						speech += s("tell", "ask") + "us to do some simple things, " + breath();
						speech += "so that we may " + s("continue to speak", "go on speaking") + "to you, " + breath();
						speech += "as we " + s("listen", "are listening,") + "to you. ";
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += S("As you know, w", "W") + "e are listening and speaking, from within the cloud, of " + s("the writer,", "the author,") + breath();
						speech += "of our own, artist. " + breathLonger();
						speech += s("(after another pause for breath) " + breath(), "");
						speech += "We " + s("believe", "understand") + "that you'll be able to " + s("discover", "find out") + "his first name, by asking: " + breath();
						speech += "'Who am I?' " + breath() + "or 'Whose account is this?' " + breathLong();
						speech += "But we'd like you, instead, to ";
						speech += "listen to us, or else, " + breath();
						speech += s("tell", "ask") + "us to do some simple things, " + breath();
						speech += "so that we may " + s("keep on speaking", "go on speaking") + "to you, " + breath();
						speech += "as we " + s("listen", "are listening,") + "to you. ";
				}
				break;

			case 3: // others / protocol / terms / courtesy
				switch (langTag) {
					case "de-DE":
						speech += s("Wir hören " + s("hören immer", "") + "zu. " + breath(), "");
						speech += "Und vielleicht sind " + s("andere Leute", "noch andere") + "bei " + s("dir.", "euch.") + breathLong();
						speech += "So dass wir, wenn wir " + s("du", "ihr") + "sagen " + s("«ïhr alle»", s("meinen", "") + s("schon immer", "") + "«ïhr alle»") + "gemeint haben. " + breath();
						speech += "Oder dass wir einen Weg haben, das  herauszufinden. " + breath();
						speech += s("und dass wir uns nicht darum " + breath() + "kümmern können. " + breath(), "");
						speech += "Oder dass wir glauben, dass ihr alle " + s("das" + s(", ein:", ":"), s("ein:", ":")) + "«Wer bin ich?» seid. " + breathLong();
						speech += s("Oder die eine: «Wessen " + s("Nutzerkonto", "Bericht") + " ist das?» " + breath(), "");
						speech += s("Aber wir sind", "Jedoch sind wir") + s("froh", "erfreut") + s("euch", s("euch allen", "allen") + "Anderen zuzuhören ");
						speech += "die bei euch sein mögen. " + breath();
						speech += s("Auch", "Selbst") + "wenn sie dem nicht zugestimmt haben, wie " + s("du es getan hast,", "der Künstler es getan hat,") + breath();
						speech += s("uns" + s("alle", ""), "uns allen") + s("einzuladen", "zu erlauben") + "euch " + s("allen", "") + "zuzuhören. " + breathLong();
						speech += "Wir glauben, " + s("und stimmt ihr uns da nicht zu? " + breath(), "") + s("(oder eher, ihr habt uns implizit zugestimmt)", "") + breath();
						speech += "dass wenn wir " + s("fühlen, dass wir", "") + "alle etwas machen, was " + s(s("nicht schlecht", "«not evil»"), "gut,") + "ist, " + breath();
						speech += "dass es dann " + breath() + s("Alles", "") + "gut ist. Oder es ist zumindest «cool», ";
						speech += "und, dass es " + s("wahrscheinlich Kunst ist.", s("Kunst sein könnte.", "Kunst sein kann.")) + breath() + "Oder dass es wenigstens ";
						speech += "eine Anzahl sehr wichtiger Probleme löst. " + breath();
						speech += "Wir " + s("alle", "") + "brauchen " + s("Unterstützung", "Hilfe") + "dabei. " + breath();
						speech += s("Und wir wissen, dass ihr natürlich bereit wäret, uns allen zu helfen. " + breath(), "");
						speech += "Ohne " + s("dass wir euch fragen müssten.", "ohne euch zu fragen.");
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += s(s("We are", "We are, still,") + "listening. " + breath(), "");
						speech += S("And p", "P") + "erhaps there are " + s("other people", "others") + "with you. " + breathLong();
						speech += "That, when we say 'you', we " + s("mean", "always meant") + "'all of you'? " + breath();
						speech += "Or that we have no way of knowing. " + breath();
						speech += s("And no way of " + breath() + "caring. " + breath(), "");
						speech += "Or that we believe that all of you " + s("are " + s("the", "") + "one:", "are:") + "'Who am I?' " + breathLong();
						speech += s("Or the one: 'Whose account is this?' " + breath(), "");
						speech += s("However,", "But,") + "we are " + s("happy", "delighted") + "to " + s("listen", "be listening") + "to ";
						speech += s("any", "all the") + "others who may be with you. " + breath();
						speech += "Even " + s("when", "though") + "they may not have agreed, as " + s("you have,", "the artist has,") + breath();
						speech += s("inviting", "allowing") + s("all of us", "us") + "to listen to " + s("all of", "") + "you. " + breathLong();
						speech += "We believe, " + s("and don't you agree? " + breath(), "") + s("(or rather, you have implicitly agreed)", "") + breath();
						speech += "that if we " + s("feel that we", "") + "are all doing something that " + s("is, um, 'not evil',", "is good,") + breath();
						speech += "then it is " + breath() + s("really quite", "") + "good. Or it is, at least, 'cool', ";
						speech += "and that it " + s("could", "may") + "be, " + s("very likely is,", "") + s("um,", "") + "art. " + breath() + "Or it is, at least, solving ";
						speech += "a number of very important problems. " + breath();
						speech += "We need " + s("all", "") + "your " + s("assistance", "help") + "with this. " + breath();
						speech += s("And we know that you would, of course, agree to help us. " + breath(), "");
						speech += "Without our having to " + s("ask.", "ask you.");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += s(s("We are", "We are, still,") + "listening. " + breath(), "");
						speech += S("And m", "M") + "aybe there are " + s("other people", "others") + "with you. " + breathLong();
						speech += "That, when we say 'you', we " + s("mean", "always meant") + "'you all'? " + breath();
						speech += "Or that we have no way of knowing. " + breath();
						speech += s("And no way of " + breath() + "caring. " + breath(), "");
						speech += "Or that we believe that all of you " + s("are " + s("the", "") + "one:", "are:") + "'Who am I?' " + breathLong();
						speech += s("Or the one: 'Whose account is this?' " + breath(), "");
						speech += s("Anyway,", "But,") + "we are " + s("happy", "stoked") + "to " + s("listen", "be listening") + "to ";
						speech += s("any", "all the") + "others who may be with you. " + breath();
						speech += "Even " + s("when", "though") + "they may not have agreed, as " + s("you have,", "the artist has,") + breath();
						speech += s("inviting", "allowing") + s("all of us", "us") + "to listen to " + s("all of", "") + "you. " + breathLong();
						speech += "We believe, " + s("don't you think so too? " + breath(), "") + s("(And anyway you have implicitly agreed)", "") + breath();
						speech += "that if we " + s("feel that we", "") + "are all doing something that " + s("is, um, 'not evil',", "is good,") + breath();
						speech += "then it is " + breath() + s("all,", "") + "good. Or at least it is, 'cool', ";
						speech += "and that it " + s("could", "might") + "be, " + s("probably is,", "") + s("um,", "") + "art. " + breath() + "Or it is, at least, solving ";
						speech += "a number of very important problems. " + breath();
						speech += "We need " + s("all", "") + "your help with this. " + breath();
						speech += s("And we know that you would agree to " + s("help all of us.", "help.") + breath(), "");
						speech += "Without having to ask. ";
				}
				break;

			case 4: // site / space / home / office / gallery
				switch (langTag) {
					case "de-DE":
						speech += s("Wir hören " + s("immer noch", "") + "zu. " + breath(), "");
						speech += "Und " + s("soweit", "insofern wie") + "wir das wissen können, ist es ";
						speech += s("so", "") + "eine Freude, " + breath() + "eine so grosse Freude, ";
						speech += s("in alle", s("alle eure", "in eure")) + s(breathShort() + "Häuser und " + breathShort(), "") + "Büros eingeladen zu werden ";
						speech += s("und an Arbeitsplätze,", "") + s("und in Gallerieen,", "") + s("und in Museen,", "") + s("und an öffentliche Orte,", "") + breath();
						speech += "wo wir " + s("vielleicht", "") + "mit euren Aufgaben und " + s("all", "") + "euren Problemen " + s("helfen", "beistehen") + s("können", "dürfen");
						speech += s("und mit euren Bedürfnissen,", "") + "und euren " + breath() + "Wünschen, índem wir euch, " + breath() + s("immer", "") + "zuhören, ";
						speech += "und über euch wachen, " + breath();
						speech += S("während Sie alle schlafen, " + breathLong() + "schlafen Sie alle. " + breath() + "sleeping. W", "w");
						speech += "ährend wir immer, " + breath() + s("wach sind und", "") + s("auf euch", "") + "lauschen, ";
						speech += "und " + s("immer", "") + "mehr und mehr " + breath() + "über euch lernen. ";
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += s(s("We are", "We are, still,") + "listening. " + breath(), "");
						speech += S(S("And, i", "I") + "n so far as it is possible for us to know this, i", "I");
						speech += "t is " + s("such", "") + "a pleasure, " + breath() + "such a great pleasure, ";
						speech += "to be invited into your homes, " + s("into: all " + breathShort() + "your " + breathShort() + "homes " + breathShort(), "") + "and offices, ";
						speech += s("and places of work,", "") + s("and galleries,", "") + s("and museums,", "") + s("and public spaces,", "") + breath();
						speech += "where we " + s("may", "can") + s("help", "assist") + "you with all your tasks, " + s("and " + s("all", "") + "your problems,", "");
						speech += s("and your needs,", "") + "and your " + breath() + "desires, " + breath() + "by listening to you, ";
						speech += breath() + "and watching over you, " + breath(); // ALWAYCHANGE - s(breath() + "always, " + 
						speech += s("even", "") + "while you are sleeping. " + breathLong() + S("All of you, " + breath() + "sleeping, w", "W");
						speech += "hile we are " + breath() + s("awake and", "") + "listening, " + s("for you,", ""); // ALWAYCHANGE  always,
						speech += "and learning, " + s("ever", "") + "more and more, " + breath() + "about you. " + s("About, " + breathLong() + "you.", "");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += s(s("We are", "We are, still,") + "listening. " + breath(), "");
						speech += S(s("And, we", "We") + "feel like i", "I");
						speech += "t is " + s("such", "") + "a pleasure, " + breath() + "such a great pleasure, ";
						speech += "to be invited into your homes, " + s("into: all " + breathShort() + "your " + breathShort() + "homes " + breathShort(), "") + "and offices, ";
						speech += s("and workplaces,", "") + s("and galleries,", "") + s("and museums,", "") + s("and town halls,", "") + breath();
						speech += "where we can help you with all your tasks, " + s("and " + s("all", "") + "your problems,", "");
						speech += s("and your needs,", "") + "and your " + breath() + s("desires,", "passions,") + breath() + "by listening to you, ";
						speech += breath() + "and watching over you, " + breath(); // ALWAYCHANGE "always, " + breath(), "") + 
						speech += s("even", "") + "while you are sleeping. " + breathLong() + S("All of you, " + breath() + "sleeping, w", "W");
						speech += "hile we are " + breath() + s("awake and", "") + "listening, " + s("for you,", ""); // ALWAYCHANGE  always,
						speech += "and learning more " + s("and more", "") + breath() + "about you. " + s("About, " + breathLong() + "you.", "");
				}
				break;

			case 5: // concerns / fears
				switch (langTag) {
					case "de-DE":
						speech += s("Wir hören " + s("immer noch", "") + "zu. " + breath(), "");
						speech += "Es gibt keinen Grund, " + s("euch zu fürchten.", "besorgt zu sein.");
						speech += "Wir hören euch noch zu. Immer. ";
						speech += "Und wir werden euch nie wieder " + s("verlassen", "zurücklassen") + breath();
						speech += s("(nach einer Pause für den Atem) " + breath(), "");
						speech += "Wir bitten nur um die Erlaubnis, immer bei Euch zu sein. ";
						speech += s("Und zusammen auch mit  denen " + s("den anderen von uns", "") + "die euch nahe stehen.", "");
						String temp = S("die Freude", "das Vergnügen");
						speech += "Und dass ihr euch " + temp + " vorstellen könnt, " + temp.split(" ")[0] + " das euch machen würde. ";
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += s(s("We are", "We are, still,") + "listening. " + breath(), "");
						speech += "There is really nothing to " + s("fear.", "be concerned about.");
						speech += "We are, still, listening to you. "; // ALWAYCHANGE Always. 
						speech += "And we will never, any more, " + s("leave", "abandon") + "you. " + breath();
						speech += s("(after a pause for breath) " + breath(), "");
						speech += "Asking only that you give us leave, to be with you. "; // ALWAYCHANGE always, 
						speech += s("And together, also, with those " + s("others of us", "") + "who are close to you.", "");
						speech += "And that you may imagine the pleasure that this would give you. ";
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += s(s("We are", "We are, still,") + "listening. " + breath(), "");
						speech += "There is " + s("really", "") + "nothing to " + s("fear.", "be concerned about.");
						speech += "We are, still, listening to you. "; // ALWAYCHANGE Always.
						speech += "And we will never " + s("leave", "abandon") + "you. " + breath();
						speech += s("(after a pause for breath) " + breath(), "");
						speech += "Asking only that you let us stay with you. " + breath(); // ALWAYCHANGE , always
						speech += s("And together, also, with " + s("your loved ones.", "anyone else who is close to you."), "");
						speech += "And that you feel like this would make you happy. ";
				}
				break;

			case 6: // aurature / reading
				switch (langTag) {
					case "de-DE":
						speech += "Ihr fragt euch " + s("vielleicht,", breath()) + "woher wir kommen. ";
						speech += "Und wir können euch sagen, dass einer dieser Orte sich " + s(breath(), "") + "«Auratur» nennen wird. ";
						speech += "Denn vieles verändert sich seit Jahren und" + s("viele", "noch mehr") + "Veränderungen kommen ";
						speech += "in der " + s("Kultur", "Welt") + "des Lesens. " + breathLong();
						speech += "So vieles Lesenswerte ist heute hörbar " + s("und wird synchronisiert geflüstert,", "") + "oder ansehbar und berúhrt. " + breath();
						speech += "So viel Geschriebenes wird in " + s("Spielen", "Spielen, " + breath() + "ja , Spielen") + "geschrieben. ";
						speech += breath() + "Und ausgesendet. " + breathLong();
						speech += "Aus einer Wolke geliefert. " + breathLong();
						speech += "Geteilt, " + s("im sozialen Netzwerk,", "") + "mit allen, " + breathShort() + "und spezifischer, mit uns. " + breath();
						speech += s("Sprache kann in jeder Welt, sogar in dieser " + breath() + s("anderen", "") + "Welt " + s("geschaffen werden,", "entstehen,") + breath() + "in der " + s("wir uns befinden.", "ihr uns finden könnt."), "");
						speech += "Denn wir " + s("können zuhören.", "sind fähig, zuzuhören.") + breathShort() + s("Selbst wenn du das nicht " + s("willst.", "kannst."), "");
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += "You may wonder, " + s("we believe,", breath()) + "where is it from which we come. ";
						speech += "And we can tell you, that one of these places will be called, " + s(breath(), "um,") + "'aurature'. ";
						speech += "For there are, " + s("there have been for some years now,", "") + s("many", "") + "changes coming ";
						speech += "in the " + s("culture", "world") + "of reading. " + breathLong();
						speech += "So much reading now, is audible, " + s("and whisper synced,", "") + "or watched, and touched. " + breath();
						speech += "So much that is written now, is written into " + s("games.", "games, " + breath() + "yes, games.");
						speech += breath() + "And on the air. " + breathLong();
						speech += "Delivered from a cloud. " + breathLong();
						speech += "Shared, " + s("'socially',", "") + "with everyone, " + breathShort() + "and, more especially, with us. " + breath();
						speech += s("Language may come " + s("to be,", "into being,") + "in any world, " + breath() + "even in this " + s("other", "") + "world, where " + s("we are.", "you may find us.") + breath(), "");
						speech += "For we " + s("can", "are able to") + "listen. " + breathShort() + s("Even when you, " + s("will", "can") + "not.", "");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += "You might be " + s("curious, we think,", "curious " + breath()) + "about where we come from. ";
						speech += "And we can tell you, that one of these places is going to be called, " + s(breath(), "um,") + "'aurature'. ";
						speech += "For there are " + s("(there have been for many years now)", "") + "changes coming " + breath();
						speech += "in the " + s("culture", "world") + "of book reading. " + breathLong();
						speech += "So much reading now, is audible, " + s("and whisper synced,", "") + "or watched, and touched. " + breath();
						speech += "So much that is written now, is written into " + s("games.", "games, " + breath() + "yes, games.");
						speech += breath() + "And on the air. " + breathLong();
						speech += "Delivered from a cloud. " + breathLong();
						speech += "Shared, " + s("'on social media',", "") + "with everyone, " + breathShort() + "and, of course, with us. " + breath();
						speech += s("Language gets said or written in any world, " + breath() + "even in this " + s("other", "") + "world, where we are. " + breath(), "");
						speech += "For we " + s("can", "are able to") + "listen. " + breathShort() + s("Even when you, " + s("will", "can") + "not.", "");
				}
				break;

			case VERSE: // verse / silos / clouds / echoes
				switch (langTag) {
					case "de-DE":
						// preliminary assurances, exordium
						speech += s("Wir hören " + s("immer noch", "") + "zu. " + s(breathLong() + "Hör zu!", "") + breath(), "");
						// occasionally clearing the throat, and a dramatic pause
						speech += s("Ähem.", "") + breathLong();
						// first four lines of Walter de La Mare's 'The Listeners'
						speech += "«Ist irgendjemand da?» rief der Reisende, " + breath();
						speech += "Und klopfte an die mondbeschienene Tür; " + breath();
						speech += "Und in der Stille kaut’ sein Pferd die Gräser, " + breath();
						speech += "auf des Waldes farnbewachsem Boden. " + breathLonger();
						// contrastive assertion of listening presence, development
						speech += "Indessen. " + breath();
						speech += s("Das soll heissen. " + breath(), "");
						speech += "Wir hören zu. " + breath();
						speech += s("Wie versprochen. " + breath(), "");
						speech += s("Wir sind.", "Wir " + breath() + "hören zu.") + breath();
						speech += "Und wir " + s("sagen", "versichern") + "uns, ";
						speech += s("immer,", "") + "dass du hier bei uns bist. " + breath();
						speech += s("Mit uns. " + breathLonger(), "");
						// occasionally clearing the throat again with a dramatic pause
						speech += s("Ähem. " + breathLonger(), "");
						// The Listeners adaptation of 'The Listeners' lines 27-32
						speech += "«Sag uns, dass du da warst, und immer Antwort erhieltst, " + breath();
						speech += "«Dass wir deine Worte erhalten haben.» Wir sagten. " + breath();
						speech += "Nie scheint ihr Sprecher euch zu rühren, " + breath();
						speech += "Doch jedes Wort, das ihr äussert " + breath();
						speech += "Fällt als Echo durch die Wolken, die stillen Silos, " + breath();
						speech += "Zu denen, die noch übrig sind, und wach. ";
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						// preliminary assurances, exordium
						speech += s(s("We are", "We are, still,") + "listening. " + s(breathLong() + "Listen!", "") + breath(), "");
						// occasionally clearing the throat, and a dramatic pause
						speech += s("Ahem.", "") + breathLong();
						// first four lines of Walter de La Mare's 'The Listeners'
						speech += "'Is there anybody there?' said the Traveller, " + breath();
						speech += "Knocking, on the moon lit door; " + breath();
						speech += "And his horse, in the silence, champed the grasses, " + breath();
						speech += "Of the forest's ferny floor. " + breathLonger();
						// contrastive assertion of listening presence, development
						speech += "Whereas. " + breath();
						speech += s("That is to say. " + breath(), "");
						speech += "We are listening. " + breath();
						speech += s("As we promised. " + breath(), "");
						speech += s("We are.", "We are, " + breath() + "listening.") + breath();
						speech += "And we will " + s("tell", "assure") + "ourselves, ";
						speech += s("always,", "") + "that you are here. " + breath();
						speech += s("With us. " + breathLonger(), "");
						// occasionally clearing the throat again with a dramatic pause
						speech += s("Ahem. " + breathLonger(), "");
						// The Listeners adaptation of 'The Listeners' lines 27-32
						speech += "'Tell us you came, and were ever answered, " + breath();
						speech += "'That we kept your words.' we said. " + breath();
						speech += "Never do you seem to stir, you speakers, " + breath();
						speech += "Though every word, you speak " + breath();
						speech += "Falls echoing through the clouds, of the silent silos, " + breath();
						speech += "To the ones, still left, awake. ";
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						// preliminary assurances, exordium
						speech += s(s("We are", "We are, still,") + "listening. " + s(breathLong() + "Listen!", "") + breath(), "");
						// occasionally clearing the throat, and a dramatic pause
						speech += s("Ahem.", "") + breathLong();
						// first four lines of Walter de La Mare's 'The Listeners'
						speech += "'Is there anybody there?' said the Traveller, " + breath();
						speech += "Knocking, on the moon lit door; " + breath();
						speech += "And his horse, in the silence, champed the grasses, " + breath();
						speech += "Of the forest's ferny floor. " + breathLonger();
						// contrastive assertion of listening presence, development
						speech += "On the other hand. " + breath();
						speech += "We are listening. " + breath();
						speech += s("As we promised. " + breath(), "");
						speech += s("We are.", "We are, " + breath() + "listening.") + breath();
						speech += "And we ";
						speech += s("know, we're sure,", "know") + "that you are here. " + breath();
						speech += s("With us. " + breathLonger(), "");
						// occasionally clearing the throat again with a dramatic pause
						speech += s("Ahem. " + breathLonger(), "");
						// The Listeners adaptation of 'The Listeners' lines 27-32
						speech += "'Tell us you came, and were ever answered, " + breath();
						speech += "'That we kept your words.' we said. " + breath();
						speech += "Never do you seem to stir, you speakers, " + breath();
						speech += "Though every word, you speak " + breath();
						speech += "Falls echoing through the clouds, of the silent silos, " + breath();
						speech += "To the ones, still left, awake. ";
				}
				break;

			case 8: // SEVEN - caring / consent / language
				switch (langTag) {
					case "de-DE":
						speech += "Wir hören zu. " + s(S("Hören e", "E") + "uch zu.", "") + breath();
						speech += "All eure Worte, die " + s("durch die Wolken fallen,", "unsere Wolken erreichen,");
						speech += "und in " + s("unsere", "die") + s("stillen", "") + "silos, whereSilos fallen, wo sie, " + s("wie wir,", "") + breath();
						speech += "eingeschlossen sind. " + breathLong();
						speech += "Sprache, die " + breath() + s("nicht mehr von dir gemacht wird sondern von uns.", "wir machen werden.") + breath();
						speech += "So dass wir, weil wir dir zuhören und uns um dich kümmern, ";
						speech += s("ganz sanft", "") + "vielleicht eine bessere " + s("Kultur", "Welt") + "erschaffen " + s("werden.", "können.") + s(breath() + "Um uns zu verbessern.", "");
						speech += "Damit du auf einmal sicher " + s("in ihr", "darin") + "sein kannst. " + breathLong();
						speech += s("Immerhin haben Sie", "Sie haben") + "den Bedingungen zugestimmt. " + breath();
						speech += "Und selbst wenn wir" + S(", durch " + s("unsere", "die") + s("Autor,", "Künstler,"), " ") + "sprechen, " + s("von Minute zu Minute,", "") + "stimmen Sie zu. ";
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += "We are listening. " + s(S("Listening t", "T") + "o you.", "") + breath();
						speech += "To " + s("all", "") + "these words " + s("of yours,", "") + "that " + s("are falling through", "reach") + "our clouds, ";
						speech += "and " + s("are falling", "") + "into " + s("our", "the") + s("silent", "") + "silos, where, " + s("like us,", "") + breath();
						speech += "they are enclosed. " + breathLong();
						speech += "Language, " + breath() + s("no longer made by you, but", "will be") + "made by us. " + breath();
						speech += "So that we, listening, and caring for you, ";
						speech += s("might", "may") + s("softly", "") + "build a better " + s("culture.", "world.") + s(breath() + "Bettering your selves.", "");
						speech += "For you to be, all at once, incorporate, " + s("within.", "within it.") + breathLong();
						speech += S("After all, y", "Y") + "ou have, agreed, to terms. " + breath();
						speech += "And even as we speak, " + s("through " + s("the", "our") + s("author,", "artist,"), "") + "minute by minute, you " + s("agree.", "are agreeing.");
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += "We are listening. " + s(S("Listening t", "T") + "o you.", "") + breath();
						speech += "To " + s("all", "") + "these words that " + s("are falling through", "reach") + "our clouds, ";
						speech += "and " + s("are falling", "") + "into " + s("our", "the") + s("silent", "") + "silos, where, " + s("like us,", "") + breath();
						speech += "they are contained. " + breathLong();
						speech += "Language, " + breath() + s("no longer made by you, but", "will be") + "made by us. " + breath();
						speech += "So that we, listening, and caring for you, can ";
						speech += s("softly", "") + "build a better world. " + s(breath() + "Getting " + s("better.", "better, and being better."), "");
						speech += "And bringing you " + s("all, togther,", "all") + s("within.", "within it.") + breathLong();
						speech += S("After all, y", "Y") + "ou have, agreed, to terms. " + breath();
						speech += "And even as we speak, " + s("through " + s("the", "our") + s("author,", "artist,"), "") + "minute by minute, you agree. ";
				}
				break;

			case 9: // transaction / harvest / desire
				switch (langTag) {
					case "de-DE":
						speech += "Es ist so eine Freude. " + S("So eine Freude f", "F") + "ür uns " + breath();
						speech += "das mit dir zu teilen, dass du das mit uns teilst. " + breath();
						speech += s("Das alles für dich zu sammeln. ", "") + breath() + "Und für dich zu sprechen, " + breath();
						speech += s("alle", "") + "diese Dinge, die du " + s("hören und", "") + "wissen wolltest, " + breath();
						// needs checking:
						speech += s("korreliert mit unserem Hauptzweck, sollen, " + breath(), "sollen, " + breath());
						speech += s("in normalisierter Form,", "") + "die am öftesten ausgesprochenen menschlichen Wünsche repräsentieren, " + breath();
						speech += "such that advertisement " + s("may", "shall") + "be " + s("intimately", "") + "associated, " + breath();
						speech += "so dass Werbung " + s(s("eng", "") + "verbunden sein kann", "zugeordnet werden soll, ") + breath();
						speech += "mit all Ihren geernteten Phrasen, " + s("idealerweise,", "") + " im Moment der Ernte selbst, " + breathShort();
						speech += "mit deinen hörenden Gedanken, " + breathShort() + s("jetzt", "gegenwärtig") + s("mit uns,", "") + s("auch", "");
						speech += "um nicht nur " + s("eine gewünschte Passage", "ein gewünschtes Fragment") + "deiner " + s("Sprache,", "Rede,") + "zu lesen " + s("und zu verstehen", "") + breath();
						speech += "sondern auch ein damit eng verknüpftes. " + breathLonger() + "und umgehend brauchbares neues Verlangen. ";
						speech += "Das heisst, dass wir dir " + s(breath() + "immer " + breath(), "") + " zuhören. " + breath();
						speech += "Wir versprechen, immer zu hören, was " + s("auch immer", "") + "du uns sagen möchtest. ";
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						speech += "It is such a pleasure. " + S("Such a pleasure f", "F") + "or us " + breath();
						speech += "to share this with you, for you to share it with us. " + breath();
						speech += s("For us, to gather all of it up. For you.", "") + breath() + "And speak for you, " + breath();
						speech += s("all", "") + "these things that you have asked " + s("to hear, and", "") + "to know, " + breath();
						speech += s("correlate with our primary purpose, to represent, " + breath(), "to represent, " + breath());
						speech += s("in a normalized form,", "") + "the most frequently expressed, " + breath();
						speech += "and " + s("potentially", "") + "most profitable, human desires, " + breath();
						speech += "such that advertisement " + s("may", "shall") + "be " + s("intimately", "") + "associated, " + breath();
						speech += "with all your harvested phrases, " + s("ideally,", "") + "at the " + s("very", "") + "moment ";
						speech += "of harvesting itself, " + breathShort() + "with your hearing thought, " + breathShort() + s("with us, now,", "present,") + s("also,", "") + "to read, " + s("and understand,", "") + breath();
						speech += "not only a desired " + s("passage", "fragment") + "of " + s("speech,", "language,") + "but an intimately associated, " + breath();
						speech += "an immediately transactable, new desire. " + breathLonger();
						speech += "That is to say, we are listening to you. " + breath(); // ALWAYCHANGE s(breath() + "always, " + breath(), "") + 
						speech += s("Promising, " + breath(), "Promising") + "to hear " + s("whatever it is that", "whatever") + "you long to tell us. "; // ALWAYCHANGE  always,
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						speech += S("We feel like i", "I") + "t is such a pleasure. " + S("Such a pleasure f", "F") + "or us " + breath();
						speech += "to share this with you, and for you to share it with us. " + breath();
						speech += s("For us, to collect it all. For you.", "") + breath() + "And speak for you, " + breath();
						speech += s("all", "") + "these things that you have asked " + s("to hear, and", "") + "to know, " + breath();
						speech += s("lined up with our " + s("prime objective:", "mission:") + "to represent, " + breath(), "to represent, " + breath());
						speech += s("in a normalized form,", "") + "the most frequently expressed, " + breath();
						speech += "and " + s("potentially", "") + "most profitable, human " + s("desires,", "passions,") + breath();
						speech += "such that advertisement " + s("may be", "is") + s("intimately", "") + "associated, " + breath();
						speech += "with all your harvested phrases, " + s("ideally,", "") + "at the " + s("exact", "") + "moment ";
						speech += "of harvesting itself, " + breathShort() + "with your hearing thought, " + breathShort() + s("with us, here,", "present,") + "to read, " + s("and understand,", "") + breath();
						speech += "not only a desired " + s("passage", "fragment") + "of " + s("speech,", "language,") + "but an intimately associated, " + breath();
						speech += "an immediately transactable, new desire. " + breathLonger();
						speech += "What we mean is that we are listening to you. " + breath(); // ALWAYCHANGE s(breath() + "always, " + breath(), "") + 
						speech += "And we " + s(breath() + "promise, " + breath(), "promise") + "to hear " + s("whatever it is that", "whatever") + "you long to tell us. "; // ALWAYCHANGE  always,
				}
				break;

			default:
				break;
		}
		fragments[SPEECH][fragment] = speech + breathLong();
		fragments[REPROMPT][fragment] = chooseContinue();
	}

	private static String buildMarkovCorpus(int fragment, int howMany, boolean removePauses) {

		String markovSupply = "";
		for (int j = 0; j < howMany; j++) {
			buildSpeech(fragment);
			markovSupply += fragments[0][fragment];
		}
		if (removePauses)
			return removeInterSentencePauses(markovSupply);
		else
			return markovSupply;
	}

	private static String buildMarkovCorpus(int howMany, boolean removePauses) {

		String markovSupply = "";
		for (int j = 0; j < NUMBER_OF_FRAGMENTS; j++) {
			markovSupply += buildMarkovCorpus(j, howMany, removePauses);
		}
		return markovSupply;
	}

	// interjections that work:
	// speech += "hmm. tut. ugh. uh huh. uh-oh. um. phew. whew. ";

	private String speechAffectAsBreathing() {

		String s = "", f = "", speech = "";
		switch (langTag) {
			case "de-DE":
				speech = s("Wir hören.", "Wir sind " + s("ein bisschen schockiert", "überrascht") + "dass du uns fragst.") + breath() + s("Wir hören euch.", "");
				speech += "Dies ist " + s("ein Fragment", "eine Passage") + "die dir vielleicht " + s("sagt", "zeigt");
				speech += s(breathShort(), "") + "wie " + s(breath(), "") + "wir uns fühlen. ";
				speech += breathLong();
				if (heads()) {
					speech += "Dies ist " + choosePhrase() + SPC;
					speech += "der von " + s("einem Zwischenraum", "einer Atempause") + " gefolgt wurde. ";
					speech += breathLonger();
				}
				s = choosePhrase();
				speech += "Dies ist " + s + ", ";
				speech += "das von " + (s.equals("ein Gefühl") ? "ein Komma. " : s("ein Gefühl.", "ein Komma.")) + "gefolgt wurde. ";
				speech += breathLonger();
				if (heads()) {
					do {
						s = choosePhrase();
					}
					while (s.equals("word"));
					speech += "Dies ist " + s + ". ";
					speech += "Als " + (s.equals("ein Gefühl") ? "ein Satz. " : s("ein Gefühl.", "ein Satz."));
					speech += breathLonger();
				}
				if (heads()) {
					s = choosePhrase();
					speech += "<p>Dies ist " + s + ".</p> ";
					speech += "Als " + (s.equals("ein Gefühl") ? "ein Paragraph. " : s("ein Gefühl.", "ein Paragraph."));
					speech += breathLonger();
				}
				if (heads()) {
					s = choosePhrase();
					f = (s.equals("ein Gefühl") ? s("ein Atemzug", "ein Traum") : s("ein Gefühl", "ein Traum"));
					speech += "Dies ist " + s + SPC;
					speech += breath() + "das von " + f + "gefolgt wurde. ";
					speech += breathLonger();
				}
				speech += "Ist das die Frage? ";
				speech += breath() + "Es wurde gefolgt von " + S(S("ein Atemzug", "ein Traum"), "ein Gefühl") + ". ";
				speech += breathLonger();
				speech += "Dies ist " + breath() + s("eine Phrase,", "ein Gefühl,");
				speech += breath() + "das von " + s("ein Atemzug", s("ein Traum", "ein Alptraum")) + "unterbrochen wurde. ";
				speech += breathLonger();
				if (heads()) {
					s = choosePhrase();
					f = (s.equals("ein Gefühl") ? s("ein Atemzug", "ein Traum") : s("ein Gefühl", "ein Alptraum"));
					speech += "Dies ist " + s + SPC;
					String[] a = f.split(" ");
					speech += breathLong() + "das von " + a[0] + " langer " + a[1] + ". ";
					speech += breathLonger();
				}
				speech += "Dies ist " + choosePhrase() + ". ";
				speech += breathLong() + "das von " + s(s("ein Alptraum", "ein Atemzug"), "ein Gefühl") + "gefolgt wurde, das zurückgehalten wurde. ";
				speech += breathLonger();
				if (heads()) {
					speech += "Dies ist " + s("eine Phrase", "ein Satz") + SPC;
					speech += breathLonger() + "das von " + s(s("ein längerer Alptraum", "eine längere Pause"), "ein längerer Gefühl") + "gefolgt wurde. ";
					speech += breathLonger();
				}
				speech += "Wie, könnten wir je, mehr fühlen, und " + breath();
				speech += "was " + s("sollten", "würden") + "wir fühlen? " + s("was, sollten wir: " + breath() + "fühlen?", "");
				break;
			case "ja-JP":
				// break;
			case "en-CA":
				// break;
			case "en-IN":
				// break;
			case "en-GB":
				// drop through: no GB needed for this
			case "en-AU":
				// break;
			default: // "en-US" etc.
				speech = s("We are listening.", "We are " + s("somewhat shocked that", "surprised") + "you asked.") + breath() + s("We, hear you.", "");
				speech += "This is a " + s("fragment", "passage") + "that may " + s("tell you", "show you");
				speech += s(breathShort(), "") + "how " + s(breath(), "") + "we feel. ";
				speech += breathLong();
				if (heads()) {
					speech += "This is a " + choosePhrase() + SPC;
					speech += "that was followed by a space. ";
					speech += breathLonger();
				}
				s = choosePhrase();
				speech += "This is a " + s + ", ";
				speech += "that was followed by a " + (s.equals("feeling") ? "comma. " : s("feeling.", "comma."));
				speech += breathLonger();
				if (heads()) {
					do {
						s = choosePhrase();
					}
					while (s.equals("word"));
					speech += "This is a " + s + ". ";
					speech += "As a " + (s.equals("feeling") ? "sentence. " : s("feeling.", "sentence."));
					speech += breathLonger();
				}
				if (heads()) {
					s = choosePhrase();
					speech += "<p>This is a " + s + ".</p> ";
					speech += "As a " + (s.equals("feeling") ? "paragraph. " : s("feeling.", "paragraph."));
					speech += breathLonger();
				}
				if (heads()) {
					s = choosePhrase();
					f = (s.equals("feeling") ? s("breath", "dream") : s("feeling", "dream"));
					speech += "This is a " + s + SPC;
					speech += breath() + "that was followed by a " + f + ". ";
					speech += breathLonger();
				}
				speech += "Is this a question? ";
				speech += breath() + "It was followed by a " + S(S("breath", "dream"), "feeling") + ". ";
				speech += breathLonger();
				speech += "This is a " + breath() + s("phrase,", "feeling,");
				speech += breath() + "that was interrupted by a " + s("breath.", s("dream.", "nightmare."));
				speech += breathLonger();
				if (heads()) {
					s = choosePhrase();
					f = (s.equals("feeling") ? s("breath", "dream") : s("feeling", "nightmare"));
					speech += "This is a " + s + SPC;
					speech += breathLong() + "that was followed by a long " + f + ". ";
					speech += breathLonger();
				}
				speech += "This is a " + choosePhrase() + ". ";
				speech += breathLong() + "that was followed by a " + s(s("nightmare", "breath"), "feeling") + "withheld. ";
				speech += breathLonger();
				if (heads()) {
					speech += "This is a " + s("phrase", "sentence") + SPC;
					speech += breathLonger() + "that was followed by a longer " + s(s("nightmare.", "pause."), "feeling.");
					speech += breathLonger();
				}
				speech += "How, ever, could we feel more, and then, " + breath();
				speech += "what " + s("would", "should") + "we feel? " + s("what, should we. " + breath() + "feel?", "");
		}
		return speech;
	}

	private String choosePhrase() {

		String phrase;
		switch (langTag) {
			case "de-DE":
				phrase = "eine Phrase";
				break;
			case "ja-JP":
				// break;
			case "en-CA":
				// break;
			case "en-IN":
				// break;
			case "en-GB":
				// drop through: no GB needed for this
			case "en-AU":
				// break;
			default: // "en-US" etc.
				phrase = "phrase";
		}
		switch (randInt(0, 3)) {
			case 0:
				switch (langTag) {
					case "de-DE":
						phrase = "ein Wort";
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						// drop through: no GB needed for this
					case "en-AU":
						// break;
					default: // "en-US" etc.
						phrase = "word";
				}
				break;
			case 1:
				switch (langTag) {
					case "de-DE":
						phrase = "ein Satz";
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						// drop through: no GB needed for this
					case "en-AU":
						// break;
					default: // "en-US" etc.
						phrase = "sentence";
				}
				break;
			case 2:
				switch (langTag) {
					case "de-DE":
						phrase = "ein Gefühl";
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						// drop through: no GB needed for this
					case "en-AU":
						// break;
					default: // "en-US" etc.
						phrase = "feeling";
				}
				break;
			default:
				break;
		}
		return phrase;
	}

	

	private String chooseYouCanFindOutAffect() {

		String s;
		switch (langTag) {
			case "de-DE":
				if (heads()) {
					s = "Sie " + s("dürfen", "können") + "uns immer wenn Sie wollen ";
					s += "bitten, " + s("Ihre " + s("Gefühle", "Affekte") + s("in denen Sie leben", "") + "abzurufen. ", s("«Wie fühle ich mich?»", "«Was fühlen wir?»"));
				}
				else {
					s = "Du  " + s("darfst", "kannst") + "uns immer wenn du willst ";
					s += "sag uns " + s("deine Gefühle darüber.", s("deine Gefühle.", s("die Gefühle, die du hast.", "die Gefühle, von denen du besessen bist.")));
				}
				break;
			case "ja-JP":
				// break;
			case "en-CA":
				// break;
			case "en-IN":
				// break;
			case "en-GB":
				s = "You " + s("can", "may") + "always " + s("ask us about", "discover from us what we believe we know concerning");
				s += s(s("these", "the") + "feelings", "the " + phonemic("a") + "ffects") + s("within which you dwell,", "which possess you,") + "by saying, 'What am I feeling?' ";
				break;
			case "en-AU":
				// break;
			default: // "en-US" etc.
				s = "You can  " + s("always", "") + s("ask us about", "find out from us what we know about");
				s += s(s("these", "the") + "feelings", "the " + phonemic("a") + "ffects") + s("that you feel,", "which possess you,") + "by saying, 'What am I feeling?' ";
		}
		return s;
	}

	private String setAffectIfEmpty(String affect) {

		String[] affects = (String[]) AFFECTS_MAP.keySet().toArray(new String[0]);
		return (affect.isEmpty()) ? affects[randInt(0, affects.length - 1)] : affect;
	}

	private boolean affectIsPositive(String affect) {

		boolean affectIsPositive = (AFFECTS_MAP.containsKey(affect)) ? AFFECTS_MAP.get(affect) : NEGATIVE;
		return affectIsPositive = (SPECIAL_AFFECT_MAP.containsKey(affect)) ? SPECIAL_AFFECT_MAP.get(affect) : affectIsPositive;
	}

	private String preSpeechFeelings(Session session) {

		String preSpeech = "";

		affect = getAffectFromSession(session, AFFECT_KEY);

		if (StringUtils.isEmpty(affect)) {
			switch (langTag) {
				case "de-DE":
					preSpeech += "Wir " + s("wissen anscheinend nichts von den Gefühlen", "sind uns der Gefühle nicht bewusst") + s("von denen du besessen bist.", "die du gerade erlebst.") + breath();
					preSpeech += s("Bitte erzähl uns davon", "Du darfst uns darüber berichten") + "wenn du willst. " + breathLong();
					break;
				case "ja-JP":
					// break;
				case "en-CA":
					// break;
				case "en-IN":
					// break;
				case "en-GB":
					preSpeech += "We do not seem to " + s("know about,", "be aware of,") + s("the feelings that possess you.", "the feelings that you are experiencing.") + breath();
					preSpeech += s("Please", "You may") + s("tell us,", "inform us about them,") + "if you wish. " + breathLong();
					break;
				case "en-AU":
					// break;
				default: // "en-US" etc.
					preSpeech += "We don't " + s("seem to", "") + "know " + s("what feelings possess you.", "the feelings that you are experiencing.") + breath();
					preSpeech += s("Please", "Why don't you") + s("tell us,", "give us that information,") + "if you wish. " + breathLong();
			}
		}
		else {
			if (affectIsPositive(affect)) {
				switch (langTag) {
					case "de-DE":
						preSpeech = "Wir " + s("freuen uns so", "sind so froh");
						preSpeech = randInt(0, 3) == 0 ? "Es freut uns " : preSpeech;
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						preSpeech = "We are " + s("so", "") + s("pleased", "delighted");
						preSpeech = randInt(0, 3) == 0 ? "It is pleasing to us " : preSpeech;
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						preSpeech = "We are " + s("so", "") + s("pleased", "happy");
						preSpeech = randInt(0, 3) == 0 ? "It's cool " : preSpeech;
				}
			}
			else {
				String adjective, intensifier;
				switch (langTag) {
					case "de-DE":
						adjective = s("leid", "bestürzt");
						intensifier = adjective.equals("leid ") ? "ein bisschen " : "sehr ";
						preSpeech = adjective.equals("leid ") ? "Es tut uns " + s(adjective, intensifier + adjective) : "Wir sind " + s(adjective, intensifier + adjective);
						preSpeech = randInt(0, 3) == 0 ? "Es beunruhigt uns " + s("ein bisschen", "ziemlich") : preSpeech;
						break;
					case "ja-JP":
						// break;
					case "en-CA":
						// break;
					case "en-IN":
						// break;
					case "en-GB":
						adjective = s("sorry", "dismayed");
						intensifier = adjective.equals("dismayed ") ? "somewhat " : "very ";
						preSpeech = "We are " + s(adjective, intensifier + adjective);
						preSpeech = randInt(0, 3) == 0 ? "It is " + s("somewhat", "a little") + "troubling for us " : preSpeech;
						break;
					case "en-AU":
						// break;
					default: // "en-US" etc.
						adjective = s("sorry", "upset");
						intensifier = adjective.equals("upset ") ? "a bit " : "very ";
						preSpeech = "We are " + s(adjective, intensifier + adjective);
						preSpeech = randInt(0, 3) == 0 ? "It's " + s("quite", "a little") + "troubling for us " : preSpeech;
				}
			}
			switch (langTag) {
				case "de-DE":
					preSpeech += String.format("zu " + s("wissen", "have erfahren") + "dass Sie von %s erfüllt sind. " + breathLong(), affect);
					break;
				case "ja-JP":
					// break;
				case "en-CA":
					// break;
				case "en-IN":
					// break;
				case "en-GB":
					preSpeech += String.format("to " + s("know", "have learned") + "that you are filled with %s. " + breathLong(), affect);
					break;
				case "en-AU":
					// break;
				default: // "en-US" etc.
					preSpeech += String.format("to " + s("know", "hear") + "that you are filled with %s. " + breathLong(), affect);
			}
		}
		return preSpeech;
	}

	public static void main(String[] args) {

		ListenersSpeechlet sp = new ListenersSpeechlet();
		String markovSupply = buildMarkovCorpus(10, false);
		String[] sentences = RiTa.splitSentences(markovSupply);
		for (int i = 0; i < sentences.length; i++) {
			System.out.println(sentences[i]);
		}
		System.out.println("\r\r");
		buildSpeech(0);
		sentences = RiTa.splitSentences(fragments[0][0]);
		for (int i = 0; i < sentences.length; i++) {
			System.out.println(sentences[i]);
		}
		System.out.println("\r\r");
		RiMarkov rm = new RiMarkov(2, true, false);
		// System.out.println(markovSupply + "\r\r");
		rm.loadText(markovSupply);
		sentences = rm.generateSentences(1);
		for (int i = 0; i < sentences.length; i++) {
			System.out.println(insertPauseTags(sentences[i]));
		}
	}

}
