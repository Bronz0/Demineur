package tp1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pref implements ActionListener, FocusListener {
	JFrame maFen;
	JTextField jtfLargeur, jtfHauteur, jtfMines;
	JLabel jlLargeur, jlHauteur, jlMines;
	JButton btnOk, btnAnnuler;
	static int NB_MINES_MIN = 10;
	static int NB_MINES_MAX = 665;
	static int DIMENSION_MIN = 9;
	static int DIMENSION_MAX = 25;

	boolean validation = false;
	private DFenetre fenetre;

	public Pref(DFenetre fen) {
		maFen = new JFrame("Preferences");
		fenetre = fen;
		System.out.println("Choix");
		miseEnPage();
		ajoutEcouteurs();
		maFen.setSize(300, 200);
		maFen.setVisible(true);
	}
	private JPanel label() {
		/* Creation */
		jlLargeur = new JLabel("Largeur");
		jlHauteur = new JLabel("Hauteur");
		jlMines = new JLabel("Mines");
		JPanel panel = new JPanel();
		/* Ajout */
		panel.setLayout(new GridLayout(3, 1, 15, 5));
		panel.add(jlLargeur);
		panel.add(jlHauteur);
		panel.add(jlMines);
		/* Retour */
		return panel;
	}
	private JPanel saisie() {
		/* Creation */
		jtfLargeur = new JTextField(Integer.toString(fenetre.getDpartie().getLargeur()));
		jtfHauteur = new JTextField(Integer.toString(fenetre.getDpartie().getHauteur()));
		jtfMines = new JTextField(Integer.toString(fenetre.getDpartie().getMines()));
		JPanel panel = new JPanel();
		/* Ajout */
		panel.setLayout(new GridLayout(3, 1, 15, 5));
		panel.add(jtfLargeur);
		panel.add(jtfHauteur);
		panel.add(jtfMines);
		/* Retour */
		return panel;
	}
	private JPanel boutons() {
		/* Creation */
		btnOk = new JButton("Ok");
		btnAnnuler = new JButton("Annuler");
		/* Ajout */
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(btnOk, BorderLayout.NORTH);
		panel.add(btnAnnuler, BorderLayout.SOUTH);
		/* Retour */
		return panel;
	}
	private void ajoutEcouteurs() {
		btnOk.addActionListener(this);
		btnAnnuler.addActionListener(this);
		jtfLargeur.addFocusListener(this);
		jtfHauteur.addFocusListener(this);
		jtfMines.addFocusListener(this);
	}
	private void miseEnPage() {
		Container c = maFen.getContentPane();
		// Ajout des lables, zones de saisie et des boutons
		Container container = new Container();
		container.setLayout(new BorderLayout(20, 0));
		container.add(label(), BorderLayout.WEST);
		container.add(saisie(), BorderLayout.CENTER);
		container.add(boutons(), BorderLayout.EAST);
		c.setLayout(new BorderLayout());
		c.add(container, BorderLayout.NORTH);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnOk)) {
			validation = true;
			maFen.setVisible(false);
			int a, b, c;
			a = Integer.parseInt(jtfHauteur.getText());
			b = Integer.parseInt(jtfLargeur.getText());
			c = Integer.parseInt(jtfMines.getText());
			if (c > b * a)
				c = a * b - 5;
			fenetre.arretChrono();
			fenetre.initChrono();
			fenetre.getDpartie().setHauteur(a);
			fenetre.getDpartie().setLargeur(b);
			fenetre.getDpartie().setNbMines(c);
			fenetre.getDpartie().nouvellePartie();
			fenetre.connecterPartie(fenetre.getDpartie());
		} else {
			validation = false;
			maFen.setVisible(false);// fermer la fenetre
		}
	}
	public void focusGained(FocusEvent e) {
	}
	public void focusLost(FocusEvent e) {
		JTextField jtf = (JTextField) e.getSource();
		int val = Integer.parseInt(jtf.getText());
		if (jtf.equals(jtfMines)) {
			if (val < NB_MINES_MIN)
				jtf.setText(Integer.toString(NB_MINES_MIN));
			if (val > NB_MINES_MAX)
				jtf.setText(Integer.toString(NB_MINES_MAX));
		} else {
			if (val < DIMENSION_MIN)
				jtf.setText(Integer.toString(DIMENSION_MIN));
			if (val > DIMENSION_MAX)
				jtf.setText(Integer.toString(DIMENSION_MAX));
		}
	}
	public boolean getValidation() {
		return validation;
	}

}