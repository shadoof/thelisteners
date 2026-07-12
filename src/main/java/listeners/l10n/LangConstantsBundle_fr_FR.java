package listeners.l10n;

import static listeners.model.Constants.NEGATIVE;
import static listeners.model.Constants.POSITIVE;
import static listeners.model.Constants.SPC;
import static listeners.util.Utils.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ListResourceBundle;

//TODO
public class LangConstantsBundle_fr_FR extends LangConstantsBundle {

	protected HashMap getFragmentNumberMap() {

		fragmentNumberMap = new HashMap<>();
		fragmentNumberMap.put("1", 0);
		fragmentNumberMap.put("2", 1);
		fragmentNumberMap.put("3", 2);
		fragmentNumberMap.put("4", 3);
		fragmentNumberMap.put("5", 4);
		fragmentNumberMap.put("6", 5);
		fragmentNumberMap.put("7", 6);
		fragmentNumberMap.put("8", 7);
		fragmentNumberMap.put("9", 8);
		fragmentNumberMap.put("10", 9);
		//
		fragmentNumberMap.put("un", 0);
		fragmentNumberMap.put("deux", 1);
		fragmentNumberMap.put("trois", 2);
		fragmentNumberMap.put("quatre", 3);
		fragmentNumberMap.put("cinq", 4);
		fragmentNumberMap.put("six", 5);
		fragmentNumberMap.put("sept", 6);
		fragmentNumberMap.put("huit", 7);
		fragmentNumberMap.put("neuf", 8);
		fragmentNumberMap.put("dix", 9);
		return fragmentNumberMap;
	}

	protected HashMap getFragmentNameMap() {

		fragmentNameMap = new HashMap<>();
		fragmentNameMap.put("écoute", 0);
		fragmentNameMap.put("plaisir", 0);
		fragmentNameMap.put("vivacité", 0);
		fragmentNameMap.put("dans", 1);
		fragmentNameMap.put("enceintes", 1);
		fragmentNameMap.put("art", 1);
		fragmentNameMap.put("artiste", 2);
		fragmentNameMap.put("auteur", 2);
		fragmentNameMap.put("compte", 2);
		fragmentNameMap.put("autres", 3);
		fragmentNameMap.put("protocole", 3);
		fragmentNameMap.put("termes", 3);
		fragmentNameMap.put("courtoisie", 3);
		fragmentNameMap.put("site", 4);
		fragmentNameMap.put("espace", 4);
		fragmentNameMap.put("maison", 4);
		fragmentNameMap.put("bureau", 4);
		fragmentNameMap.put("galerie", 4);
		fragmentNameMap.put("inquiétudes", 5);
		fragmentNameMap.put("peurs", 5);
		fragmentNameMap.put("aurature", 6);
		fragmentNameMap.put("lecture", 6);
		fragmentNameMap.put("vers", 7);
		fragmentNameMap.put("silos", 7);
		fragmentNameMap.put("nuages", 7);
		fragmentNameMap.put("échos", 7);
		fragmentNameMap.put("attention", 8);
		fragmentNameMap.put("consentement", 8);
		fragmentNameMap.put("langage", 8);
		fragmentNameMap.put("désir", 9);
		fragmentNameMap.put("transactions", 9);
		fragmentNameMap.put("récolte", 9);
		return fragmentNameMap;
	}

	protected String[] getAffectsArray() {

		// Make sure affects names match
		String s = "bonheur excitation curiosité émerveillement surprise colère ";
		s += "dégoût détresse peine douleur mélancolie peur honte humiliation";
		return s.split(SPC);
	}

	protected HashMap getAffectsMap() {

		affectsMap = new HashMap<>();
		affectsMap.put("bonheur", POSITIVE);
		affectsMap.put("excitation", POSITIVE);
		affectsMap.put("curiosité", POSITIVE);
		affectsMap.put("émerveillement", POSITIVE);
		affectsMap.put("surprise", POSITIVE);
		affectsMap.put("colère", NEGATIVE);
		affectsMap.put("dégoût", NEGATIVE);
		affectsMap.put("détresse", NEGATIVE);
		affectsMap.put("peine", NEGATIVE);
		affectsMap.put("douleur", NEGATIVE);
		affectsMap.put("mélancolie", NEGATIVE);
		affectsMap.put("peur", NEGATIVE);
		affectsMap.put("honte", NEGATIVE);
		affectsMap.put("humiliation", NEGATIVE);
		// we only have to add positive recognized affects to this map
		// special affects’ valences are addressed by that map and
		// otherwise affects are assumed to be negative
		affectsMap.put("apathie", POSITIVE); // deliberate!
		affectsMap.put("calme", POSITIVE);
		affectsMap.put("espoir", POSITIVE);
		affectsMap.put("intérêt", POSITIVE);
		affectsMap.put("invulnérabilité", POSITIVE);
		affectsMap.put("joie", POSITIVE);
		affectsMap.put("plaisir", POSITIVE);
		affectsMap.put("relaxation", POSITIVE);
		affectsMap.put("sécurité", POSITIVE);
		affectsMap.put("force", POSITIVE);
		affectsMap.put("niaiserie", POSITIVE);
		affectsMap.put("délice", POSITIVE);
		return affectsMap;
	}

	protected HashMap getSpecialAffectsMap() {

		specialAffectsMap = new HashMap<>();
		specialAffectsMap.put("affection", POSITIVE);
		specialAffectsMap.put("anxiété", NEGATIVE);
		specialAffectsMap.put("excuses", NEGATIVE);
		specialAffectsMap.put("ennui", NEGATIVE);
		specialAffectsMap.put("calme", POSITIVE);
		specialAffectsMap.put("claustrophobie", NEGATIVE);
		specialAffectsMap.put("complaisance", NEGATIVE);
		specialAffectsMap.put("confusion", NEGATIVE);
		specialAffectsMap.put("super", NEGATIVE);
		specialAffectsMap.put("dette", NEGATIVE);
		specialAffectsMap.put("fatigue", NEGATIVE);
		specialAffectsMap.put("trépidation", POSITIVE);
		specialAffectsMap.put("culpabilité", NEGATIVE);
		specialAffectsMap.put("haine", NEGATIVE);
		specialAffectsMap.put("détestation", NEGATIVE);
		specialAffectsMap.put("faim", NEGATIVE);
		specialAffectsMap.put("insécurité", NEGATIVE);
		specialAffectsMap.put("isolement", NEGATIVE);
		specialAffectsMap.put("amour", POSITIVE);
		specialAffectsMap.put("chance", POSITIVE);
		specialAffectsMap.put("nausée", NEGATIVE);
		specialAffectsMap.put("obligation", NEGATIVE);
		specialAffectsMap.put("paix", POSITIVE);
		specialAffectsMap.put("questions", POSITIVE);
		specialAffectsMap.put("sécurité", POSITIVE);
		specialAffectsMap.put("sexe", NEGATIVE);
		specialAffectsMap.put("étrangeté", NEGATIVE);
		specialAffectsMap.put("inquiétude", NEGATIVE);
		specialAffectsMap.put("vulnérabilité", POSITIVE);
		return specialAffectsMap;
	}

	protected HashMap getAffectivejj2nnMap() {

		affectivejj2nnMap = new HashMap<>();
		affectivejj2nnMap.put("abject", "abjection");
		affectivejj2nnMap.put("affectueux", "affection");
		affectivejj2nnMap.put("effrayé", "peur");
		affectivejj2nnMap.put("correct", "apathie");
		affectivejj2nnMap.put("fâché", "colère");
		affectivejj2nnMap.put("agacé", "irritation");
		affectivejj2nnMap.put("anxieux", "anxiété");
		affectivejj2nnMap.put("apathique", "apathie");
		affectivejj2nnMap.put("désolé", "excuses");
		affectivejj2nnMap.put("honteux", "honte");
		affectivejj2nnMap.put("génial", "super");
		affectivejj2nnMap.put("mal", r("anxiété`détresse`honte`tristesse"));
		affectivejj2nnMap.put("perplexe", "perplexité");
		affectivejj2nnMap.put("ennuyé", "ennui");
		affectivejj2nnMap.put("claustrophobe", "claustrophobie");
		affectivejj2nnMap.put("complaisant", "complaisance");
		affectivejj2nnMap.put("confus", "confusion");
		affectivejj2nnMap.put("froid", "apathie");
		affectivejj2nnMap.put("curieux", "curiosité");
		affectivejj2nnMap.put("ravi", "ravissement"); 
		affectivejj2nnMap.put("déçu", "déception");
		affectivejj2nnMap.put("déprimé", "dépression");
		affectivejj2nnMap.put("dégoûté", "dégoût");
		affectivejj2nnMap.put("affligé", "détresse");
		affectivejj2nnMap.put("perturbé", "inconfort");
		affectivejj2nnMap.put("étourdi", "étourdissement");
		affectivejj2nnMap.put("vide", "vacuité"); 
		affectivejj2nnMap.put("excité", "excitation");
		affectivejj2nnMap.put("fabuleux", "joie");
		affectivejj2nnMap.put("correct", "apathie");
		affectivejj2nnMap.put("apeuré", "peur");
		affectivejj2nnMap.put("furieux", "furie");
		affectivejj2nnMap.put("bien", "bonheur");
		affectivejj2nnMap.put("joyeux", "joie");
		affectivejj2nnMap.put("sensationnel", "trépidation");
		affectivejj2nnMap.put("coupable", "culpabilité");
		affectivejj2nnMap.put("joyeux", "joie");
		affectivejj2nnMap.put("optimiste", "espoir");
		affectivejj2nnMap.put("horrifié", r("peur`horreur`détresse`dégoût"));
		affectivejj2nnMap.put("humilié", "humiliation");
		affectivejj2nnMap.put("affamé", "faim");
		affectivejj2nnMap.put("indécis", "indécision");
		affectivejj2nnMap.put("indifférent", "indiférence");
		affectivejj2nnMap.put("insécure", "insécurité");
		affectivejj2nnMap.put("intéressé", "intérêt");
		affectivejj2nnMap.put("invulnérable", "invulnérabilité");
		affectivejj2nnMap.put("irrité", "irritation");
		affectivejj2nnMap.put("seul", "isolement");
		affectivejj2nnMap.put("amoureux", "amour");
		affectivejj2nnMap.put("chanceux", "chance");
		affectivejj2nnMap.put("misérable", "misère");
		affectivejj2nnMap.put("nauséeux", "nausée");
		affectivejj2nnMap.put("nerveux", "nervosité");
		affectivejj2nnMap.put("vide", "vacuité");
		affectivejj2nnMap.put("obligé", "obligation");
		affectivejj2nnMap.put("bizarre", "étrangeté");
		affectivejj2nnMap.put("bof", "apathie");
		affectivejj2nnMap.put("d'accord", "apathie");
		affectivejj2nnMap.put("vieux", "âge");
		affectivejj2nnMap.put("apaisé", "paix");
		affectivejj2nnMap.put("pitoyable", "pitié");
		affectivejj2nnMap.put("heureux", "plaisir");
		affectivejj2nnMap.put("puissant", "puissance");
		affectivejj2nnMap.put("détendu", "calme");
		affectivejj2nnMap.put("triste", "tristesse");
		affectivejj2nnMap.put("sûr", "sûreté");
		affectivejj2nnMap.put("effrayé", "peur");
		affectivejj2nnMap.put("sécurisé", "sécurité");
		affectivejj2nnMap.put("sexuel", "sexe");
		affectivejj2nnMap.put("stupéfait", "surprise");
		affectivejj2nnMap.put("malade", "maladie");
		affectivejj2nnMap.put("frivole", S("niaiserie", "frivolité"));
		affectivejj2nnMap.put("désolé", "détresse");
		affectivejj2nnMap.put("étrange", "étrangeté");
		affectivejj2nnMap.put("fort", "force");
		affectivejj2nnMap.put("stupide", S("stupidité", "ignorance"));
		affectivejj2nnMap.put("surpris", "surprise");
		affectivejj2nnMap.put("terrible", r("anxiété`détresse`honte`tristesse"));
		affectivejj2nnMap.put("terrifié", "terreur");
		affectivejj2nnMap.put("fatigué", "fatigue");
		affectivejj2nnMap.put("troublé", "détresse");
		affectivejj2nnMap.put("inquiétant", "inquiétude");
		affectivejj2nnMap.put("incertain", "incertitude");
		affectivejj2nnMap.put("incomfortable", "inconfort");
		affectivejj2nnMap.put("malheureux", "malheur");
		affectivejj2nnMap.put("pas certain", "insécurité");
		affectivejj2nnMap.put("vexé", "détresse");
		affectivejj2nnMap.put("vulnérable", "vulnérabilité");
		affectivejj2nnMap.put("étrange", "étrangeté");
		affectivejj2nnMap.put("inquiet", "anxiété");
		// a little bit of extra variation
		affectivejj2nnMap.put("correct", S("apathie", "complaisance"));
		affectivejj2nnMap.put("incroyable", S("génial", "complaisance"));
		affectivejj2nnMap.put("confus", S("stupéfaction", "confusion"));
		affectivejj2nnMap.put("super", S("apathie", "excitation"));
		affectivejj2nnMap.put("étourdi", S("étourdissement", "vertige"));
		affectivejj2nnMap.put("fabuleux", S("joie", "délice"));
		affectivejj2nnMap.put("normal", S("apathie", "complaisance"));
		affectivejj2nnMap.put("bien", S("bonheur", "joie"));
		affectivejj2nnMap.put("fantastique", S("bonheur", "complaisance"));
		affectivejj2nnMap.put("rien", r("vide`vacuité`complaisance"));
		affectivejj2nnMap.put("bizarre", S("inquiétude", "étrangeté"));
		affectivejj2nnMap.put("d'accord", S("apathie", "complaisance"));
		affectivejj2nnMap.put("ok", S("apathie", "complaisance"));
		affectivejj2nnMap.put("relax", r("relaxation`calme`détente`paix"));
		affectivejj2nnMap.put("malade", S("maladie", "nausée"));
		affectivejj2nnMap.put("désolé", r("détresse`obligation`excuses`dette"));
		affectivejj2nnMap.put("choqué", S("détresse", "surprise"));
		affectivejj2nnMap.put("las", S("fatigue", "lassitude"));
		affectivejj2nnMap.put("troublé", S("détresse", "anxiété"));
		affectivejj2nnMap.put("incomfortable", S("inconfort", "anxiété"));
		affectivejj2nnMap.put("pas certain", S("insecurité", "incertitude"));
		affectivejj2nnMap.put("bizarre", S("étrangeté", "bizarrerie"));
		return affectivejj2nnMap;
	}

	protected HashSet getSpecialThings() {

		specialThings = new HashSet<>();
		specialThings.add("cauchemar");
		specialThings.add("cauchemar retenu");
		specialThings.add("rêve");
		specialThings.add("rêve retenu");
		specialThings.add("émotion");
		specialThings.add("émotion retenue");
		specialThings.add("hong kong");
		specialThings.add("possession");
		specialThings.add("respiration");
		specialThings.add("respiration retenue");
		return specialThings;
	}

	protected HashSet getPictureWords() {

		pictureWords = new HashSet<>();
		pictureWords.add("collage");
		pictureWords.add("cadre");
		pictureWords.add("image");
		pictureWords.add("coupe de papier");
		pictureWords.add("fleur de papier");
		pictureWords.add("blessure de papier");
		pictureWords.add("toile");
		pictureWords.add("illustration");
		return pictureWords;
	}

}
