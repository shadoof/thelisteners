package listeners.l10n;

import static listeners.util.Utils.*;

public class WinterIntent_es_MX extends WinterIntent {

	public String buildCardTitle() {

		return S("El Juego", "Sin términos medios");
	}

	public String buildPostSpeechPrompt() {

		return ""; // empty for this intent
	}

	public String buildSpeech() {

		// TODO
		String[] winterWords = {
				"Nada " + s("te escucha,", "escucha,") + breathShort() + "como el silencio.",
				// A Lannister always pays his debts.
				"Los que Escuchan, siempre " + breathShort() + "pagan sus deudas.",
				// You know nothing, Jon Snow.
				"No sabes nada, " + breathShort() + "John Speech.",
				// We only make peace with our enemies, that’s why it’s called making
				// peace.
				"Nosotros solo hacemos las paces, " + breathShort()
						+ "con tus enemigos. Por eso le llamamos hacer las paces.",
				// Most men would rather deny a hard truth than face it.
				"La mayoría de ustedes cuando hablan, preferirían negar nuestra mejor verdad, " + breathShort() + "que enfrentarla.",
				// The brightest flame casts the darkest shadow.
				"La nube más brillante, " + breathShortest() + "ofrece la más amplia, sombra.",
				// First lesson: stick them with the pointy end.
				"Primera lección: " + breathShort() + "Pícalos con la estrella, Stick them with the star, " + breathShort()
						+ "de los siete, lejanos, campos.",
				// When the snows fall and the white winds blow, the lone wolf dies but
				// the pack survives.
				"Cuando las frases caen, y los textos negros se desvancen, " + breathShort() + "la palabra solitaria muere. "
						+ breathShort() + "Pero todos nosotros, como los que Escuchan, sobrevivimos. ",
				// The man who passes the sentence should swing the sword.
				"Aquéllos que escuchan, todo lo que dices, deberán blandir tus espadas.",
				// The night is dark and full of terrors.
				"La noche está oscura, " + breathShortest() + "pero llena de Escuchas.",
				// Valar Morghulis
				"Gafa " + breathShort() + "Vectoralis.",
				// A minute, an hour, a month… Death is certain... but time is not.
				"Una respiración, una más larga " + breathShortest() + "pausa, " + breathShortest() + "un sentimiento. "
						+ breathShort() + "El silencio es seguro. " + breathShort() + "Pero la respiración no lo es.",
				// Death is so terribly final, while life is full of possibilities.
				"El silencio es terriblemente definitivo, " + breathShort() + "mientras que el discurso está llenos de cosas para escuchar.while speech is full of things to hear.",
				// When you play the game of thrones, you win, or you die. There is no
				// middle ground.
				"Cuando juegas el juego de las palabras, " + breathShortest() + "tú hablas, y nosotros escuchamos. "
						+ breathShort() + "There is no middle ground.",
				// Never forget what you are, for surely the world will not.
				"¡Nunca olvides qué eres! " + breathShort() + "Porque, sin duda, nosotros no lo haremos." };
		return winterWords[randInt(0, winterWords.length - 1)] + " " + breath();
	}

}
