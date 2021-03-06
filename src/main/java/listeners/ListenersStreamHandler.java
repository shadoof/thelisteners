package listeners;

import static listeners.model.Constants.DEV;
import static listeners.util.Utils.info;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

import listeners.handlers.ExceptionHandler;
import listeners.handlers.LsnrsRequestHandler;
import listeners.handlers.SessionEndedRequestHandler;
import listeners.handlers.UnsupportedRequestHandler;

public class ListenersStreamHandler extends SkillStreamHandler {

	private static Skill getSkill() {
		
		// skill ID should be in this environment variable for xeno 'other voices'
		String skillID = System.getenv("SKILL_ID");
		// ID for Live or Development Listeners
		if (skillID == null) {
			skillID = "amzn1.echo-sdk-ams.app.586aa4c5-12ca-496b-b7f1-3e93880f35de";
			info("environment was null, from The Listeners: " + skillID);
		}
		else {
			info("skillID taken from enviroment, normally Listeners Xeno: " + skillID);
		}

		return Skills.standard()
				.addRequestHandlers(new LsnrsRequestHandler(),
						new SessionEndedRequestHandler(),
						new UnsupportedRequestHandler())
				.addExceptionHandler(new ExceptionHandler())
				.withTableName("TheListeners")
				.withAutoCreateTable(true)
				// Add your skill id below : CHANGE BEFORE SHIFTING TO DEPLOY TODO
				// .withSkillId("amzn1.ask.skill.8a8f5701-2a77-48ca-9b77-0088ff3a973b")
				.withSkillId(skillID)
				.build();

	}

	public ListenersStreamHandler() {

		super(getSkill());
	}
}
