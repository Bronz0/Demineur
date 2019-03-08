package tp1;

import metier.DPartie;
import metier.EtatCase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class DFenetre extends JFrame {
	private static final long serialVersionUID = 1L;
	private JMenuBar barreMenus;
	private JMenu jeu, options, aPropos;
	public JRadioButtonMenuItem debutant, intermediaire, expert, perso;
	public JMenuItem nouvelle, quitter, aide, createur, design, stat;
	private JLabel minesRestantes;
	private DChronoLabel temps;
	private JPanel nord;
	private JButton go;
	private DPartie partie;
	private DImageur imageur;
	private DPanneau centre;

	public DFenetre(DPartie p) {
		super("Demineur");
		menu();

		imageur = new DImageur(p);
		miseEnPage();
		ecouterMenu();
		connecterPartie(p);

	}

	public void connecterPartie(DPartie p) {
		partie = p;

		miseAJourCompteur();
		goCool();
		/* partie centrale : damier */
		if (centre != null)
			getContentPane().remove(centre);
		centre = new DPanneau(this);
		EcouteurSouris ecouteurSouris = new EcouteurSouris(this);
		centre.addMouseListener(ecouteurSouris);
		centre.addMouseMotionListener(ecouteurSouris);
		getContentPane().add(centre, BorderLayout.CENTER);
		/* Affichage */
		this.setSize(20 * p.getLargeur() + 6, 20 * p.getHauteur() + 50 + 23 + 20);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.repaint();
	}

	public String etatpartie() {
		if (partie.aExplose())
			return "perdu";
		else if (partie.gagne())
			return "gagne";
		else
			return "encoure";
	}

	private void menu() {
		/* creation du menu de jeu */
		jeu = new JMenu("Jeu");
		nouvelle = new JMenuItem("Nouvelle partie");
		nouvelle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		nouvelle.setToolTipText("Partie avec les mêmes paramètres");
		jeu.add(nouvelle);
		jeu.addSeparator();
		ButtonGroup groupRadio = new ButtonGroup();
		debutant = new JRadioButtonMenuItem("Débutant");
		debutant.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		debutant.setToolTipText("81 cases 10 mines");
		intermediaire = new JRadioButtonMenuItem("Intermédiaire");
		intermediaire.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		intermediaire.setToolTipText("256 cases 40 mines");
		expert = new JRadioButtonMenuItem("Expert");
		expert.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		expert.setToolTipText("480 cases 99 mines");
		perso = new JRadioButtonMenuItem("Personnalisé...");


		perso.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		perso.setToolTipText("Partie avec vos votres paramètres");
		jeu.add(debutant);
		jeu.add(intermediaire);
		jeu.add(expert);
		jeu.add(perso);
		groupRadio.add(debutant);
		groupRadio.add(intermediaire);
		groupRadio.add(expert);
		groupRadio.add(perso);
		jeu.addSeparator();
		quitter = new JMenuItem("Quitter");
		quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		jeu.add(quitter);
		/* creation du menu de options */
		options = new JMenu("Options");
		design = new JMenuItem("Graphisme");
		design.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		design.setToolTipText("Pour choisir le style d’image");
		options.add(design);
		options.addSeparator();
		stat = new JMenuItem("Statistiques");
		stat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		stat.setToolTipText("Pour connaitre les scores");
		options.add(stat);
		/* creation du menu A propos */
		aPropos = new JMenu("?");
		aide = new JMenuItem("Aide");
		aide.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		aide.setToolTipText("Pour obtenir de l’aide");
		aPropos.add(aide);
		aPropos.addSeparator();
		createur = new JMenuItem("Créateurs");
		createur.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		createur.setToolTipText("Par qui ?");
		aPropos.add(createur);
		/* ajout des menus à la barre */

		barreMenus = new JMenuBar();
		barreMenus.add(jeu);
		barreMenus.add(options);
		barreMenus.add(aPropos);

		this.setJMenuBar(barreMenus);

	}

	public void ecouterMenu() {
		debutant.addActionListener(e -> {
			arretChrono();
			initChrono();
			getDpartie().setHauteur(9);
			getDpartie().setLargeur(9);
			getDpartie().setNbMines(10);
			getDpartie().nouvellePartie();
			connecterPartie(getDpartie());
		});
		intermediaire.addActionListener(e -> {
			arretChrono();
			initChrono();
			getDpartie().setHauteur(16);
			getDpartie().setLargeur(16);
			getDpartie().setNbMines(40);
			getDpartie().nouvellePartie();
			connecterPartie(getDpartie());
		});
		expert.addActionListener(e -> {
			arretChrono();
			initChrono();
			getDpartie().setHauteur(16);
			getDpartie().setLargeur(30);
			getDpartie().setNbMines(99);
			getDpartie().nouvellePartie();
			connecterPartie(getDpartie());
		});
		perso.addActionListener(e -> {
			@SuppressWarnings("unused")
			Pref pref = new Pref(this);
		});
		nouvelle.addActionListener(e -> {
			arretChrono();
			initChrono();
			getDpartie().setHauteur(getDpartie().getHauteur());
			getDpartie().setLargeur(getDpartie().getLargeur());
			getDpartie().setNbMines(getDpartie().getMines());
			getDpartie().nouvellePartie();
			connecterPartie(getDpartie());
		});
		quitter.addActionListener(e -> {
			System.exit(0);
		});
		design.addActionListener(e -> {
			@SuppressWarnings("unused")
			DImageChooser choix = new DImageChooser(getImageur(), getPanneauCentral());
		});
		stat.addActionListener(e -> {
		});
		aide.addActionListener(e -> {
			File f = new File("./Aide");
			@SuppressWarnings("unused")
			Aide a = new Aide(f);
		});
		createur.addActionListener(e -> {
			JOptionPane.showMessageDialog(this, "",
					"Créateurs...", JOptionPane.INFORMATION_MESSAGE);
		});
	}

	public JMenuItem getNouvelle() {
		return nouvelle;
	}

	public JMenuItem getDebutant() {
		return debutant;
	}

	public JMenuItem getIntermediaire() {
		return intermediaire;
	}

	public JMenuItem getExpert() {
		return expert;
	}

	public JMenuItem getPerso() {
		return perso;
	}

	public JMenuItem getDesign() {
		return design;
	}
	public JMenuItem getQuitter() {
		return quitter;
	}

	public JMenuItem getAide() {
		return aide;
	}

	public JMenuItem getCreateur() {
		return createur;
	}

	private void miseEnPage() {
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout(5, 5));
		/* partie haute de l’IHM */
		nord = new JPanel();
		nord.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(5, 0, 1, 0);
		/* en haut a gauche */
		minesRestantes = new JLabel("00");
		// nord.add(minesRestantes, BorderLayout.WEST);
		nord.add(minesRestantes, gbc);
		/* centre */
		go = new JButton();
		go.addActionListener(e -> {
			arretChrono();
			initChrono();
			partie.setHauteur(partie.getHauteur());
			partie.setLargeur(partie.getLargeur());
			partie.setNbMines(partie.getMines());
			partie.nouvellePartie();
			connecterPartie(partie);
		});
		goCool();
		nord.add(go, gbc);
		temps = new DChronoLabel();
		nord.add(temps, gbc);
		c.add(nord, BorderLayout.NORTH);
	}

	public JButton getGo() {
		return go;
	}

	public DPanneau getPanneauCentral() {
		return centre;
	}

	public DImageur getImageur() {
		return imageur;
	}

	public void goPerdu() {
		go.setIcon(new ImageIcon(imageur.getRepertoire() + "/Perdu.PNG"));
	}

	public void goGagne() {
		go.setIcon(new ImageIcon(imageur.getRepertoire() + "/Gagne.PNG"));
	}

	public void goOups() {
		go.setIcon(new ImageIcon(imageur.getRepertoire() + "/Oups.PNG"));
	}

	public void goCool() {
		go.setIcon(new ImageIcon(imageur.getRepertoire() + "/Cool.PNG"));
	}

	public void miseAJourCompteur() {
		int nb = partie.getMines() - partie.nbrDrapeau();
		Integer integer = new Integer(nb);
		if ((nb > 9) || (nb < 0))
			minesRestantes.setText(integer.toString());
		else
			minesRestantes.setText("0" + integer.toString());
	}

	public void lancerChrono() {

		if (!temps.estActif())
			temps.getThread().start();
	}

	public void arretChrono() {
		temps.stop();
		temps.stop();
	}

	public void initChrono() {
		temps.initChrono();
	}

	public void pauseChrono() {
		temps.mettreEnPause();
	}

	public void repriseChrono() {
		temps.enleverPause();
	}

	public int getChrono() {
		return temps.getTime();
	}

	public DPartie getDpartie() {
		return partie;
	}

	public ImageIcon getIcone(EtatCase Etat) {
		return new ImageIcon(imageur.getIcon(Etat));
	}

}