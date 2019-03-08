package tp1;

import metier.DPartie;
import metier.EtatCase;

public class DImageur {
	@SuppressWarnings("unused")
	private DPartie partie;
	public String repertoire = "./Images/Classic";

	public DImageur(DPartie p) {
		partie = p;
	}

	public String getRepertoire() {
		return repertoire;
	}

	public void setRepertoire(String s) {
		repertoire = s;
	}

	public String getIcon(EtatCase cas) {
		switch (cas.name()) {
		case "MINE":
			return repertoire + "/Mine.PNG";
		case "DRAPEAU":
			return repertoire + "/Drapeau.PNG";
		case "CROIX":
			return repertoire + "/Croix.PNG";
		case "SELECTION":
			return repertoire + "/Select.PNG";
		case "VIDE":
			return repertoire + "/Vide.PNG";
		case "UN":
			return repertoire + "/1.PNG";
		case "DEUX":
			return repertoire + "/2.PNG";
		case "TROIS":
			return repertoire + "/3.PNG";
		case "QUATRE":
			return repertoire + "/4.PNG";
		case "CINQ":
			return repertoire + "/5.PNG";
		case "SIX":
			return repertoire + "/6.PNG";
		case "SEPT":
			return repertoire + "/7.PNG";
		case "HUIT":
			return repertoire + "/8.PNG";
		default:
			return repertoire + "/Inconnue.PNG";
		}
	}
}