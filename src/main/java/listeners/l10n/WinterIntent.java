package listeners.l10n;

import static listeners.util.Utils.S;
import static listeners.util.Utils.breath;
import static listeners.util.Utils.breathShort;
import static listeners.util.Utils.breathShortest;
import static listeners.util.Utils.randInt;
import static listeners.util.Utils.s;

public class WinterIntent extends L10nSpeech {

	public Object[][] contents = { { "cardTitle", buildCardTitle() }, { "speech", buildSpeech() },
			{ "postSpeechPrompt", buildPostSpeechPrompt() } };

	public String buildCardTitle() {

		return S("The Game", "No middle ground");
	}

	public String buildPostSpeechPrompt() {

		return ""; // empty for this intent
	}

	public String buildSpeech() {

		// TODO German
		String[] winterWords = {
				"Nothing " + s("hears you,", "listens,") + breathShort() + "like the silence.",
				// A Lannister always pays his debts.
				"The Listeners, always " + breathShort() + "pay their debts.",
				// You know nothing, Jon Snow.
				"You know nothing, " + breathShort() + "John. Speech.",
				// We only make peace with our enemies, that's why it’s called making
				// peace.
				"We only make peace, " + breathShort()
						+ "with your enemies. That's why we call it making peace.",
				// Most men would rather deny a hard truth than face it.
				"Most of you who speak, would rather deny our better truth, " + breathShort() + "than face it.",
				// The brightest flame casts the darkest shadow.
				"The brightest cloud, " + breathShortest() + "casts the broadest, shadow.",
				// First lesson: stick them with the pointy end.
				"First lesson: " + breathShort() + "Stick them with the star, " + breathShort()
						+ "of the seven, far, fields.",
				// When the snows fall and the white winds blow, the lone wolf dies but
				// the pack survives.
				"When the phrases fall, and the black texts fade, " + breathShort() + "the lone word dies. "
						+ breathShort() + "But we all, as Listeners, survive.",
				// The man who passes the sentence should swing the sword.
				"Those who listen, to everything you say, should swing your swords.",
				// The night is dark and full of terrors.
				"The night is dark, " + breathShortest() + "but filled with Listeners.",
				// Valar Morghulis
				"Gafa " + breathShort() + "Vectoralis.",
				// A minute, an hour, a month… Death is certain... but time is not.
				"A breath, a longer " + breathShortest() + " pause, " + breathShortest() + "a feeling. "
						+ breathShort() + "Silence is certain. " + breathShort() + "But breath is not.",
				// Death is so terribly final, while life is full of possibilities.
				"Silence is so terribly final, " + breathShort() + "while speech is full of things to hear.",
				// When you play the game of thrones, you win, or you die. There is no
				// middle ground.
				"When you play the game of words, " + breathShortest() + "you speak, and we listen. "
						+ breathShort() + "There is no middle ground.",
				// Never forget what you are, for surely the world will not.
				"Never forget what you are! " + breathShort() + "For surely, we will not." };
		return winterWords[randInt(0, winterWords.length - 1)] + " " + breath();
	}

	protected Object[][] getContents() {

		return contents;
	}

}
