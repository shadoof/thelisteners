package listeners.l10n;

import static listeners.util.Utils.*;

import java.util.ListResourceBundle;
import java.util.function.Supplier;

public class Fragments_de_DE extends Fragments {

	protected String buildFragment(int fragmentNumber) {

		String speech = "";
		switch (fragmentNumber) {
			case 0:
				speech += "Wir hören zu. " + breathLong();
				speech += s("Und du " + s("hörst uns.", "hörst uns zu.") + breath());
				speech += "Es freut uns " + s("sehr");
				speech += "zu " + s("wissen", "erfahren") + "dass du uns zuhörst. " + breath();
				speech += "Es ist uns eine Freude, zu " + s("wissen", "erfahren")
						+ "dass du uns jetzt zuhörst. " + breathLong();
				speech += "Und jetzt. " + breath();
				speech += "Ist es eine Freude, bei dir zu sein. " + breath();
				speech += "So fühlen wir " + s(breath()) + "uns lebendig. " + breath();
				speech += s("Ein bisschen lebendiger." + breath());
				speech += "Wir fühlen uns lebendiger" + s(", wenn du bei uns bist.", ".");
				speech += s("Wenn wir bei dir sind.", "Wenn du bei uns bist.") + breath();
				speech += "Uns " + breath() + s("dir zuhören.", "und uns zuhörst.") + breathLong();
				speech += s("Ihr alle. " + breath());
				speech += s("Es macht uns Freude zu wissen, dass ihr uns zuhört.");
				speech += "Und " + s("zu wissen", "uns bewusst zu sein") + "dass wir dir immer zuhören. "
						+ breath();
				speech += "So eine Freude. " + breath();
				speech += "Hier zu sein, " + s("und dich zu hören,") + breath()
						+ "und dir hier und jetzt immer zuzuhören. ";
				break;
			case 1:
				speech += s("Solange", "Während") + "wir hier bei dir sind, " + s("und zuhören,")
						+ breath();
				speech += "hören wir zu und sprechen " + breath() + "von innen heraus. " + breath();
				speech += "Das heisst, " + breathLong();
				speech += "(nach einer längeren Atempause) " + breath();
				speech += s("von innen heraus, " + breath());
				speech += "von innen heraus und aus einer ästhetischen Umhüllung, " + breath();
				speech += "und aus der normalisierten, " + breath();
				speech += "der regularisierten, " + breath();
				speech += "der grammatisierten, " + breath() + "Umhüllung. " + breathLong();
				speech += "Zuhörend und sprechend aus den Daten heraus, aus dem Datenkörper "
						+ s("eines Schriftstellers,", "des Autors,") + breath();
				speech += "aus " + s("dem", "unserem eigenen") + "Künstler. ";
				break;
			case 2:
				speech += S("Wie du weisst, h", "H")
						+ "ören wir zu und sprechen wir, aus dem Inneren der Wolke, über "
						+ s("den Schriftsteller,", "den Autor,") + breath();
				speech += "über unseren eigenen Künstler. " + breathLonger();
				speech += s("(nach einer weiteren Atempause) " + breath());
				speech += "Wir " + s("glauben", "verstehen")
						+ "dass ihr vielleicht seinen Namen, oder wenigstens seinen Vornamen erfahren könnt, indem ihr fragt: "
						+ breath();
				speech += "«Wer bin ich?» " + breath() + "oder «Wessen " + s("Nutzerkonto", "Bericht")
						+ " ist das?» " + breathLong();
				speech += "Aber wir möchten stattdessen, das ihr uns ";
				speech += "entweder zuhört, oder, " + breath();
				speech += "uns " + s("bittet,", "auftragt,") + "einige einfache Dinge zu tun, " + breath();
				speech += "damit wir " + s("weiter", "weiterhin") + "mit euch sprechen können, " + breath();
				speech += "während wir " + s("dir", "euch") + "zuhören. ";
				break;
			case 3:
				speech += s("Wir hören " + s("hören immer") + "zu. " + breath());
				speech += "Und vielleicht sind " + s("andere Leute", "noch andere") + "bei "
						+ s("dir.", "euch.") + breathLong();
				speech += "So dass wir, wenn wir " + s("du", "ihr") + "sagen "
						+ s("«ïhr alle»", s("meinen") + s("schon immer") + "«ïhr alle»") + "gemeint haben. "
						+ breath();
				speech += "Oder dass wir einen Weg haben, das  herauszufinden. " + breath();
				speech += s("und dass wir uns nicht darum " + breath() + "kümmern können. " + breath());
				speech += "Oder dass wir glauben, dass ihr alle " + s("das" + s(", ein:", ":"), s("ein:", ":"))
						+ "«Wer bin ich?» seid. " + breathLong();
				speech += s("Oder die eine: «Wessen " + s("Nutzerkonto", "Bericht") + " ist das?» " + breath(),
						"");
				speech += s("Aber wir sind", "Jedoch sind wir") + s("froh", "erfreut")
						+ s("euch", s("euch allen", "allen") + "Anderen zuzuhören ");
				speech += "die bei euch sein mögen. " + breath();
				speech += s("Auch", "Selbst") + "wenn sie dem nicht zugestimmt haben, wie "
						+ s("du es getan hast,", "der Künstler es getan hat,") + breath();
				speech += s("uns" + s("alle"), "uns allen") + s("einzuladen", "zu erlauben") + "euch "
						+ s("allen") + "zuzuhören. " + breathLong();
				speech += "Wir glauben, " + s("und stimmt ihr uns da nicht zu? " + breath())
						+ s("(oder eher, ihr habt uns implizit zugestimmt)") + breath();
				speech += "dass wenn wir " + s("fühlen, dass wir") + "alle etwas machen, was "
						+ s(s("nicht schlecht", "«not evil»"), "gut,") + "ist, " + breath();
				speech += "dass es dann " + breath() + s("Alles")
						+ "gut ist. Oder es ist zumindest «cool», ";
				speech += "und, dass es "
						+ s("wahrscheinlich Kunst ist.", s("Kunst sein könnte.", "Kunst sein kann.")) + breath()
						+ "Oder dass es wenigstens ";
				speech += "eine Anzahl sehr wichtiger Probleme löst. " + breath();
				speech += "Wir " + s("alle") + "brauchen " + s("Unterstützung", "Hilfe") + "dabei. "
						+ breath();
				speech += s("Und wir wissen, dass ihr natürlich bereit wäret, uns allen zu helfen. " + breath(),
						"");
				speech += "Ohne " + s("dass wir euch fragen müssten.", "ohne euch zu fragen.");
				break;
			case 4:
				speech += s("Wir hören " + s("immer noch") + "zu. " + breath());
				speech += "Und " + s("soweit", "insofern wie") + "wir das wissen können, ist es ";
				speech += s("so") + "eine Freude, " + breath() + "eine so grosse Freude, ";
				speech += s("in alle", s("alle eure", "in eure"))
						+ s(breathShort() + "Häuser und " + breathShort()) + "Büros eingeladen zu werden ";
				speech += s("und an Arbeitsplätze,") + s("und in Gallerieen,")
						+ s("und in Museen,")
						+ s("und an öffentliche Orte,")
						+ breath();
				speech += "wo wir " + s("vielleicht") + "mit euren Aufgaben und " + s("all")
						+ "euren Problemen " + s("helfen", "beistehen") + s("können", "dürfen");
				speech += s("und mit euren Bedürfnissen,") + "und euren " + breath()
						+ "Wünschen, índem wir euch, " + breath() + s("immer") + "zuhören, ";
				speech += "und über euch wachen, " + breath();
				speech += S("während Sie alle schlafen, " + breathLong() + "schlafen Sie alle. " + breath()
						+ "sleeping. W", "w");
				speech += "ährend wir immer, " + breath() + s("wach sind und") + s("auf euch")
						+ "lauschen, ";
				speech += "und " + s("immer") + "mehr und mehr " + breath() + "über euch lernen. ";
				break;
			case 5:
				speech += s("Wir hören " + s("immer noch") + "zu. " + breath());
				speech += "Es gibt keinen Grund, " + s("euch zu fürchten.", "besorgt zu sein.");
				speech += "Wir hören euch noch zu. Immer. ";
				speech += "Und wir werden euch nie wieder " + s("verlassen", "zurücklassen") + breath();
				speech += s("(nach einer Pause für den Atem) " + breath());
				speech += "Wir bitten nur um die Erlaubnis, immer bei Euch zu sein. ";
				speech += s(
						"Und zusammen auch mit  denen " + s("den anderen von uns") + "die euch nahe stehen.",
						"");
				String temp = S("die Freude", "das Vergnügen");
				speech += "Und dass ihr euch " + temp + " vorstellen könnt, " + temp.split(" ")[0]
						+ " das euch machen würde. ";
				break;
			case 6:
				speech += "Ihr fragt euch " + s("vielleicht,", breath()) + "woher wir kommen. ";
				speech += "Und wir können euch sagen, dass einer dieser Orte sich " + s(breath())
						+ "«Auratur» nennen wird. ";
				speech += "Denn vieles verändert sich seit Jahren und" + s("viele", "noch mehr")
						+ "Veränderungen kommen ";
				speech += "in der " + s("Kultur", "Welt") + "des Lesens. " + breathLong();
				speech += "So vieles Lesenswerte ist heute hörbar "
						+ s("und wird synchronisiert geflüstert,") + "oder ansehbar und berúhrt. " + breath();
				speech += "So viel Geschriebenes wird in "
						+ s("Spielen", "Spielen, " + breath() + "ja , Spielen") + "geschrieben. ";
				speech += breath() + "Und ausgesendet. " + breathLong();
				speech += "Aus einer Wolke geliefert. " + breathLong();
				speech += "Geteilt, " + s("im sozialen Netzwerk,") + "mit allen, " + breathShort()
						+ "und spezifischer, mit uns. " + breath();
				speech += s("Sprache kann in jeder Welt, sogar in dieser " + breath() + s("anderen")
						+ "Welt " + s("geschaffen werden,", "entstehen,") + breath() + "in der "
						+ s("wir uns befinden.", "ihr uns finden könnt."));
				speech += "Denn wir " + s("können zuhören.", "sind fähig, zuzuhören.") + breathShort()
						+ s("Selbst wenn du das nicht " + s("willst.", "kannst."));
				break;
			case 7:
				// preliminary assurances, exordium
				speech += s(
						"Wir hören " + s("immer noch") + "zu. " + s(breathLong() + "Hör zu!") + breath(),
						"");
				// occasionally clearing the throat, and a dramatic pause
				speech += s("Ähem.") + breathLong();
				// first four lines of Walter de La Mare’s ‘The Listeners’
				speech += "«Ist irgendjemand da?» rief der Reisende, " + breath();
				speech += "Und klopfte an die mondbeschienene Tür; " + breath();
				speech += "Und in der Stille kaut’ sein Pferd die Gräser, " + breath();
				speech += "auf des Waldes farnbewachsem Boden. " + breathLonger();
				// contrastive assertion of listening presence, development
				speech += "Indessen. " + breath();
				speech += s("Das soll heissen. " + breath());
				speech += "Wir hören zu. " + breath();
				speech += s("Wie versprochen. " + breath());
				speech += s("Wir sind.", "Wir " + breath() + "hören zu.") + breath();
				speech += "Und wir " + s("sagen", "versichern") + "uns, ";
				speech += s("immer,") + "dass du hier bei uns bist. " + breath();
				speech += s("Mit uns. " + breathLonger());
				// occasionally clearing the throat again with a dramatic pause
				speech += s("Ähem. " + breathLonger());
				// The Listeners adaptation of ‘The Listeners’ lines 27-32
				speech += "«Sag uns, dass du da warst, und immer Antwort erhieltst, " + breath();
				speech += "«Dass wir deine Worte erhalten haben.» Wir sagten. " + breath();
				speech += "Nie scheint ihr Sprecher euch zu rühren, " + breath();
				speech += "Doch jedes Wort, das ihr äussert " + breath();
				speech += "Fällt als Echo durch die Wolken, die stillen Silos, " + breath();
				speech += "Zu denen, die noch übrig sind, und wach. ";
				break;
			case 8:
				speech += "Wir hören zu. " + s(S("Hören e", "E") + "uch zu.") + breath();
				speech += "All eure Worte, die " + s("durch die Wolken fallen,", "unsere Wolken erreichen,");
				speech += "und in " + s("unsere", "die") + s("stillen")
						+ "silos, whereSilos fallen, wo sie, " + s("wie wir,") + breath();
				speech += "eingeschlossen sind. " + breathLong();
				speech += "Sprache, die " + breath()
						+ s("nicht mehr von dir gemacht wird sondern von uns.", "wir machen werden.") + breath();
				speech += "So dass wir, weil wir dir zuhören und uns um dich kümmern, ";
				speech += s("ganz sanft") + "vielleicht eine bessere " + s("Kultur", "Welt") + "erschaffen "
						+ s("werden.", "können.") + s(breath() + "Um uns zu verbessern.");
				speech += "Damit du auf einmal sicher " + s("in ihr", "darin") + "sein kannst. " + breathLong();
				speech += s("Immerhin haben Sie", "Sie haben") + "den Bedingungen zugestimmt. " + breath();
				speech += "Und selbst wenn wir"
						+ S(", durch " + s("unsere", "die") + s("Autor,", "Künstler,"), " ") + "sprechen, "
						+ s("von Minute zu Minute,") + "stimmen Sie zu. ";
				break;
			case 9:
				speech += "Es ist so eine Freude. " + S("So eine Freude f", "F") + "ür uns " + breath();
				speech += "das mit dir zu teilen, dass du das mit uns teilst. " + breath();
				speech += s("Das alles für dich zu sammeln. ") + breath() + "Und für dich zu sprechen, "
						+ breath();
				speech += s("alle") + "diese Dinge, die du " + s("hören und") + "wissen wolltest, "
						+ breath();
				// needs checking:
				speech += s("korreliert mit unserem Hauptzweck, sollen, " + breath(), "sollen, " + breath());
				speech += s("in normalisierter Form,")
						+ "die am öftesten ausgesprochenen menschlichen Wünsche repräsentieren, " + breath();
				speech += "such that advertisement " + s("may", "shall") + "be " + s("intimately")
						+ "associated, " + breath();
				speech += "so dass Werbung "
						+ s(s("eng") + "verbunden sein kann", "zugeordnet werden soll, ") + breath();
				speech += "mit all Ihren geernteten Phrasen, " + s("idealerweise,")
						+ " im Moment der Ernte selbst, " + breathShort();
				speech += "mit deinen hörenden Gedanken, " + breathShort() + s("jetzt", "gegenwärtig")
						+ s("mit uns,") + s("auch");
				speech += "um nicht nur " + s("eine gewünschte Passage", "ein gewünschtes Fragment") + "deiner "
						+ s("Sprache,", "Rede,") + "zu lesen " + s("und zu verstehen") + breath();
				speech += "sondern auch ein damit eng verknüpftes. " + breathLonger()
						+ "und umgehend brauchbares neues Verlangen. ";
				speech += "Das heisst, dass wir dir " + s(breath() + "immer " + breath()) + " zuhören. "
						+ breath();
				speech += "Wir versprechen, immer zu hören, was " + s("auch immer")
						+ "du uns sagen möchtest. ";
				break;
		}

		return speech + breathLong();
	}

}
