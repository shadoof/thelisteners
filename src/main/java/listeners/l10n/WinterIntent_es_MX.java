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

		String[] winterWords = {
				"Nada " + s("te escucha,", "escucha,") + breathShort() + "como el silencio.",
				// A Lannister always pays his debts.
				"Quienes Escuchan, siempre " + breathShort() + "pagan sus deudas.",
				// You know nothing, Jon Snow.
				"No sabes nada, " + breathShort() + "John Speech.",
				// We only make peace with our enemies, that’s why it’s called making
				// peace.
				"Sólo hacemos las paces, " + breathShort()
						+ "con nuestros enemigos. Por eso se le dice hacer las paces.",
				// Most men would rather deny a hard truth than face it.
				"La mayoría de ustedes, quienes hablan, preferirían negar nuestra mejor verdad, " + breathShort() + "que enfrentarla.",
				// The brightest flame casts the darkest shadow.
				"La nube más brillante, " + breathShortest() + "es la que brinda la sombra más amplia.",
				// First lesson: stick them with the pointy end.
				"Primera lección: " + breathShort() + "Pícalos con la estrella, Stick them with the star, " + breathShort()
						+ "de los siete, lejanos, campos.",
				// When the snows fall and the white winds blow, the lone wolf dies but
				// the pack survives.
				"Cuando las frases caen, y los textos negros se desvancen, " + breathShort() + "la palabra solitaria muere. "
						+ breathShort() + "Pero nosotres, Quienes Escuchan, sobrevivimos. ",
				// The man who passes the sentence should swing the sword.
				"Quienes escuchan, todo lo que dices, deberán blandir las espadas.",
				// The night is dark and full of terrors.
				"La noche está oscura, " + breathShortest() + "pero llena de Escuchas.",
				// Valar Morghulis
				"Gafa " + breathShort() + "Vectoralis.",
				// A minute, an hour, a month… Death is certain... but time is not.
				"Una respiración, una más larga " + breathShortest() + "pausa, " + breathShortest() + "un sentimiento. "
						+ breathShort() + "El silencio es seguro. " + breathShort() + "Pero la respiración no lo es.",
				// Death is so terribly final, while life is full of possibilities.
				"El silencio es terriblemente definitivo, " + breathShort() + "mientras que el discurso está lleno de cosas que escuchar.",
				// When you play the game of thrones, you win, or you die. There is no
				// middle ground.
				"Cuando juegas el juego de las palabras, " + breathShortest() + "tú hablas, y nosotres escuchamos. "
						+ breathShort() + "No hay términos medios.",
				// Never forget what you are, for surely the world will not.
				"¡Nunca olvides qué eres! " + breathShort() + "Porque, sin duda, nosotres no lo haremos." };
		return winterWords[randInt(0, winterWords.length - 1)] + " " + breath();
	}

}
