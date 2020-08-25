package listeners.l10n;

import static listeners.util.Utils.S;
import static listeners.util.Utils.breath;
import static listeners.util.Utils.breathShort;
import static listeners.util.Utils.breathShortest;
import static listeners.util.Utils.randInt;
import static listeners.util.Utils.s;

public class WinterIntent_de_DE extends WinterIntent {

	public String buildCardTitle() {

		return S("Das Spiel", "Kein Kompromiss");
	}

	public String buildSpeech() {

		String[] winterWords = {
				"Nichts " + s("hört Sie,", "hört Ihnen zu,") + breathShort() + "wie Stille.",
				// A Lannister always pays his debts.
				"Die Lauschenden zahlen immer, " + breathShort() + "was sie schulden.",
				// You know nothing, Jon Snow.
				"Du weisst gar nichts, " + breathShort() + "John Rede.",
				// We only make peace with our enemies, that’s why it’s called making
				// peace.
				"Wir schaffen nur mit Ihren Feinden Frieden. " + breathShort()
						+ "Darum nennen sie das Frieden machen.",
				// Most men would rather deny a hard truth than face it.
				"Die meisten von Ihnen, die sprechen, " + breathShort()
						+ "würden lieber unsere Wahrheit leugnen, als sie zu konfrontieren.",
				// The brightest flame casts the darkest shadow.
				"Die hellste Wolke " + breathShortest() + "wirft den weitesten Schatten.",
				// First lesson: stick them with the pointy end.
				"Erste Lektion: " + breathShort() + "Heften Sie ihnen den Stern " + breathShort()
						+ "der sieben weiten Felder an.",
				// When the snows fall and the white winds blow, the lone wolf dies but
				// the pack survives.
				"Wenn die Phrasen fallen und die schwarzen Texte verblassen, " + breathShort()
						+ "stirbt das einzelne Wort. " + breathShort()
						+ "Aber wir alle, als Lauschende, überleben.",
				// The man who passes the sentence should swing the sword.
				"Diejenigen, die allem zuhören, was Sie sagen, sollten Ihre Schwerter schwingen.",
				// The night is dark and full of terrors.
				"Die Nacht ist dunkel, " + breathShortest() + "doch von Lauschenden erfüllt.",
				// Valar Morghulis
				"Gafa " + breathShort() + "Vectoralis.",
				// A minute, an hour, a month… Death is certain... but time is not.
				"Ein Hauch, eine längere " + breathShortest() + "Pause, " + breathShortest() + "ein Gefühl. "
						+ breathShort() + "Die Stille ist sicher. " + breathShort() + "Aber der Atem nicht.",
				// Death is so terribly final, while life is full of possibilities.
				"Die Stille ist so schrecklich endgültig " + breathShort()
						+ "während die Sprache so voller hörbarer Dinge ist.",
				// When you play the game of thrones, you win, or you die. There is no
				// middle ground.
				"Wenn Sie das Wortspiel spielen, " + breathShortest() + "sprechen Sie und wir hören zu. "
						+ breathShort() + "Es gibt nichts dazwischen.",
				// Never forget what you are, for surely the world will not.
				"Vergessen Sie nie, wer Sie sind! " + breathShort() + "Ganz sicher werden wir es nicht vergessen." };
		return winterWords[randInt(0, winterWords.length - 1)] + " " + breath();
	}

}
