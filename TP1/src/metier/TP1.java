package metier;

import tp1.DFenetre;
import tp1.EcouteurFenetre;
import tp1.EcouteurGo;


public class TP1 {

	public static void main(String[] args) {
		DPartie partie = new DPartie();
		DFenetre fenetre = new DFenetre(partie);
		EcouteurGo e = new EcouteurGo(fenetre);
		fenetre.getGo().addActionListener(e);
		EcouteurFenetre ecouteurFenetre = new EcouteurFenetre(fenetre);
		fenetre.addWindowListener(ecouteurFenetre);
	}

}
