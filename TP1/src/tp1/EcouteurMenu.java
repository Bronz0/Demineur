package tp1;

import java.awt.event.*;

import javax.swing.*;

import java.io.*;

public class EcouteurMenu implements ActionListener {
	private DFenetre fenetre;

	public EcouteurMenu(DFenetre f) {
		fenetre = f;
	}
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == fenetre.getNouvelle()) {
			fenetre.arretChrono();
			fenetre.initChrono();
			fenetre.getDpartie().nouvellePartie();
			fenetre.getDpartie().setHauteur(fenetre.getDpartie().getHauteur());
			fenetre.getDpartie().setLargeur(fenetre.getDpartie().getLargeur());
			fenetre.getDpartie().setNbMines(fenetre.getDpartie().getMines());

			fenetre.connecterPartie(fenetre.getDpartie());
		}
		if (ae.getSource() == fenetre.getDebutant()) {
			fenetre.arretChrono();
			fenetre.initChrono();
			fenetre.getDpartie().nouvellePartie();
			fenetre.getDpartie().setHauteur(9);
			fenetre.getDpartie().setLargeur(9);
			fenetre.getDpartie().setNbMines(10);
			fenetre.connecterPartie(fenetre.getDpartie());
		}
		if (ae.getSource() == fenetre.getIntermediaire()) {
			fenetre.arretChrono();
			fenetre.initChrono();

			fenetre.getDpartie().nouvellePartie();
			fenetre.getDpartie().setHauteur(16);
			fenetre.getDpartie().setLargeur(16);
			fenetre.getDpartie().setNbMines(40);
			fenetre.connecterPartie(fenetre.getDpartie());
		}
		if (ae.getSource() == fenetre.getExpert()) {
			fenetre.arretChrono();
			fenetre.initChrono();

			fenetre.getDpartie().nouvellePartie();
			fenetre.getDpartie().setHauteur(16);
			fenetre.getDpartie().setLargeur(30);
			fenetre.getDpartie().setNbMines(99);
			fenetre.connecterPartie(fenetre.getDpartie());
		}
		if (ae.getSource() == fenetre.getPerso()) {
			@SuppressWarnings("unused")
			Pref pref = new Pref(fenetre);
		}
		if (ae.getSource() == fenetre.getDesign()) {
			@SuppressWarnings("unused")
			DImageChooser choix = new DImageChooser(fenetre.getImageur(), fenetre.getPanneauCentral());
		}
		if (ae.getSource() == fenetre.getQuitter())
			System.exit(0);
		if (ae.getSource() == fenetre.getAide()) {
			File f = new File("./Aide");
			@SuppressWarnings("unused")
			Aide a = new Aide(f);
		}
		if (ae.getSource() == fenetre.getCreateur())
			JOptionPane.showMessageDialog(fenetre, "Realisé © par Igor DAURIAC et Nicolas FRANCOIS, Projet IHM",
					"CrÃ©ateurs...", JOptionPane.INFORMATION_MESSAGE);
	}
}