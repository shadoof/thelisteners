package listeners.l10n;

import static listeners.util.Utils.*;

public class WinterIntent_es_MX extends WinterIntent {

	public String buildCardTitle() {

		// TODO
		return S("El JuegoThe Game", "Sin término medioNo middle ground");
	}

	public String buildPostSpeechPrompt() {

		return ""; // empty for this intent
	}

	public String buildSpeech() {

		// TODO
		String[] winterWords = {
				"Nothing " + s("te escucha,hears you,", "escucha,listens,") + breathShort() + "como el silencio.like the silence.",
				// A Lannister always pays his debts.
				"Los Oyentes, siempre The Listeners, always " + breathShort() + "pagan sus deudas.pay their debts.",
				// You know nothing, Jon Snow.
				"No sabes nada, You know nothing, " + breathShort() + "John Speech.",
				// We only make peace with our enemies, that’s why it’s called making
				// peace.
				"Nosotros solo hacemos las paces, We only make peace, " + breathShort()
						+ "con tus enemigos. Por eso le decimos haciendo las paces.with your enemies. That’s why we call it making peace.",
				// Most men would rather deny a hard truth than face it.
				"La mayoría de ustedes cuando hablan, preferirían negar nuestra mejor verdad, Most of you who speak, would rather deny our better truth, " + breathShort() + "que enfrentarla.than face it.",
				// The brightest flame casts the darkest shadow.
				"La nube más brillante,The brightest cloud, " + breathShortest() + "ofrece la más amplia, sombra.casts the broadest, shadow.",
				// First lesson: stick them with the pointy end.
				"Primera lección: First lesson: " + breathShort() + "Pícalos con la estrella, Stick them with the star, " + breathShort()
						+ "de los siete, lejanos, campos.of the seven, far, fields.",
				// When the snows fall and the white winds blow, the lone wolf dies but
				// the pack survives.
				"Cuando las frases caen, y los textos negros se desvancen, When the phrases fall, and the black texts fade, " + breathShort() + "la única palabra muere. the lone word dies. "
						+ breathShort() + "Pero todos nosotros, como Oyentes, sobrevivimos. But we all, as Listeners, survive.",
				// The man who passes the sentence should swing the sword.
				"Los que escuchen, todo lo que dices, deberán blandir tus espadas.Those who listen, to everything you say, should swing your swords.",
				// The night is dark and full of terrors.
				"La noche está oscura, The night is dark, " + breathShortest() + "pero llena de Oyentes.but filled with Listeners.",
				// Valar Morghulis
				"Gafa " + breathShort() + "Vectoralis.",
				// A minute, an hour, a month… Death is certain... but time is not.
				"Una respiración, una más larga A breath, a longer " + breathShortest() + "pausa, pause, " + breathShortest() + "un sentimiento. a feeling. "
						+ breathShort() + "El silencio es seguro. Silence is certain. " + breathShort() + "Pero la respiración no lo es. But breath is not.",
				// Death is so terribly final, while life is full of possibilities.
				"El silencio es terriblemente definitivo, Silence is so terribly final, " + breathShort() + "mientras que los discursos están llenos de cosas para escuchar.while speech is full of things to hear.",
				// When you play the game of thrones, you win, or you die. There is no
				// middle ground.
				"Cuando juegas el juego de las palabras, When you play the game of words, " + breathShortest() + "tú hablas, y nosotros escuchamos. you speak, and we listen. "
						+ breathShort() + "There is no middle ground.",
				// Never forget what you are, for surely the world will not.
				"¡Nunca olvides qué eres tú! Never forget what you are! " + breathShort() + "Seguramente, nosotros no lo haremos.For surely, we will not." };
		return winterWords[randInt(0, winterWords.length - 1)] + " " + breath();
	}

}
