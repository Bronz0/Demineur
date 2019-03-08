package tp1;


import java.awt.event.*;


public class EcouteurGo implements ActionListener {
	DFenetre fenetre;

	public EcouteurGo(DFenetre f) {
		fenetre = f;
	}

	public void actionPerformed(ActionEvent ae) {
		fenetre.arretChrono();
		fenetre.initChrono();
		fenetre.getDpartie().nouvellePartie();
		fenetre.getDpartie().setHauteur(fenetre.getDpartie().getHauteur());
		fenetre.getDpartie().setLargeur(fenetre.getDpartie().getLargeur());
		fenetre.getDpartie().setNbMines(fenetre.getDpartie().getMines());
		fenetre.connecterPartie(fenetre.getDpartie());
	}
}