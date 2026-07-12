package listeners.l10n;

import static listeners.util.Utils.*;

public class WinterIntent_fr_FR extends WinterIntent {

	public String buildCardTitle() {

		
		return S("La Partie", "Aucun terrain neutre");
	}

	public String buildPostSpeechPrompt() {

		return ""; // empty for this intent
	}

	public String buildSpeech() {

		// TODO
		String[] winterWords = {
				"Rien " + s("ne vous écoute,", "ne vous entend,") + breathShort() + "autant que le silence.",
				// A Lannister always pays his debts.
				"Les auditrices, toujours " + breathShort() + "acquittent leur dû.",
				// You know nothing, Jon Snow.
				"Vous ne savez rien, " + breathShort() + "John Speech.",
				// We only make peace with our enemies, that’s why it’s called making
				// peace.
				"Nous ne faisons la paix, " + breathShort()
						+ "qu'avec nos ennemis. C'est pour cela qu'il s'agit de faire la paix.",
				// Most men would rather deny a hard truth than face it.
				"La plupart d'entre vous qui parler, préfèreriez nier notre meilleure vérité, " + breathShort() + "que la confronter.",
				// The brightest flame casts the darkest shadow.
				"Le plus étincelant nuage, " + breathShortest() + "camoufle la plus imposante, obscurité.",
				// First lesson: stick them with the pointy end.
				"Première leçon: " + breathShort() + "Atteignez-les avec l'étoile, " + breathShort()
						+ "des sept territoires lointants.",
				// When the snows fall and the white winds blow, the lone wolf dies but
				// the pack survives.
				"Lorsque les phrases s'affaisent, et que le texte noir s'évanouit, " + breathShort() + "le mot solitaire s'éteint. "
						+ breathShort() + "Mais nous, les Auditrices, survivons.",
				// The man who passes the sentence should swing the sword.
				"Ceux et celles qui vous écoutent, qui entendent chacun de vos mots, devraient brandir les armes.",
				// The night is dark and full of terrors.
				"La nuit est sombre, " + breathShortest() + "mais peuplée d'Auditrices.",
				// Valar Morghulis
				"Gafam " + breathShort() + "Vectoralis.",
				// A minute, an hour, a month… Death is certain... but time is not.
				"Une respiration, une pause " + breathShortest() + "appuyée, " + breathShortest() + "un sentiment. "
						+ breathShort() + "Le silence est une certitude. " + breathShort() + "Mais la respiration ne l'est pas.",
				// Death is so terribly final, while life is full of possibilities.
				"Le silence est fait d'une finalité terrible, " + breathShort() + "tandis que le discours est fait de choses à entendre.",
				// When you play the game of thrones, you win, or you die. There is no
				// middle ground.
				"Lorsque vous jouez à la Partie des mots, " + breathShortest() + "vous parlez, et nous écoutons. "
						+ breathShort() + "Il n'y a pas de terrain neutre.",
				// Never forget what you are, for surely the world will not.
				"N'oubliez jamais qui vous êtes! " + breathShort() + "car, assurément, nous ne l'oublierons pas." };
		return winterWords[randInt(0, winterWords.length - 1)] + " " + breath();
	}

}
