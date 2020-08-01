package listeners;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

import listeners.handlers.ExceptionHandler;
import listeners.handlers.LsnrsRequestHandler;
import listeners.handlers.SessionEndedRequestHandler;
import listeners.handlers.UnsupportedRequestHandler;

public class ListenersStreamHandler extends SkillStreamHandler {

	private static Skill getSkill() {

		return Skills	.standard()
									.addRequestHandlers(new LsnrsRequestHandler(),
											new SessionEndedRequestHandler(),
											new UnsupportedRequestHandler())
									.addExceptionHandler(new ExceptionHandler())
									.withTableName("TheListeners")
									.withAutoCreateTable(true)
									// Add your skill id below : CHANGE BEFORE SHIFTING TO DEPLOY TODO
									.withSkillId("amzn1.ask.skill.8a8f5701-2a77-48ca-9b77-0088ff3a973b")
									.build();

	}

	public ListenersStreamHandler() {

		super(getSkill());
	}
}
