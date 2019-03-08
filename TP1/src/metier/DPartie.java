package metier;

import java.util.Random;

public class DPartie {
	private int hauteur, largeur, nbMines;

	private DCase[][] matrice;
	private int caseNonMineeRestante;
	private boolean explosion;
	int etatpartie;

	public DPartie() {
		hauteur = 9;
		largeur = 9;
		nbMines = 10;
		nouvellePartie();
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public void setNbMines(int nbMines) {
		this.nbMines = nbMines;
	}

	public void nouvellePartie() {

		explosion = false;
		matrice = new DCase[hauteur][largeur];
		for (int i = 0; i < hauteur; i++)
			for (int j = 0; j < largeur; j++)
				matrice[i][j] = new DCase();
		miner();
		preparerAlentour();
		caseNonMineeRestante = hauteur * largeur - nbMines;
	}

	public EtatCase getEtatCase(int i, int j) {
		if (!aExplose() && !gagne()) {
			if (matrice[i][j].yaDrapreau())
				return EtatCase.DRAPEAU;
			if (!matrice[i][j].estDecouverte()) {
				if (matrice[i][j].select())
					return EtatCase.SELECTION;
				else
					return EtatCase.INCONNUE;
			}
			switch (matrice[i][j].getMinesAlentour()) {
			case 0: return EtatCase.VIDE;
			case 1: return EtatCase.UN;
			case 2:	return EtatCase.DEUX;
			case 3: return EtatCase.TROIS;
			case 4: return EtatCase.QUATRE;
			case 5:	return EtatCase.CINQ;
			case 6:	return EtatCase.SIX;
			case 7:	return EtatCase.SEPT;
			case 8:	return EtatCase.HUIT;
			default:return EtatCase.MINE;
			}
		} else {
			if (aExplose()) {
				if ((matrice[i][j].yaDrapreau()) && !(matrice[i][j].estMine()))
					return EtatCase.CROIX;
				if (matrice[i][j].estMine())
					return EtatCase.MINE;
				if (!matrice[i][j].estDecouverte())
					return EtatCase.INCONNUE;
				switch (matrice[i][j].getMinesAlentour()) {
				case 0:	return EtatCase.VIDE;
				case 1:	return EtatCase.UN;
				case 2:	return EtatCase.DEUX;
				case 3: return EtatCase.TROIS;
				case 4:	return EtatCase.QUATRE;
				case 5:	return EtatCase.CINQ;
				case 6:	return EtatCase.SIX;
				case 7:	return EtatCase.SEPT;
				case 8:	return EtatCase.HUIT;
				default:return EtatCase.MINE;
				}
			} else {
				switch (matrice[i][j].getMinesAlentour()) {
				case 0:	return EtatCase.VIDE;
				case 1:	return EtatCase.UN;
				case 2:	return EtatCase.DEUX;
				case 3:	return EtatCase.TROIS;
				case 4:	return EtatCase.QUATRE;
				case 5:	return EtatCase.CINQ;
				case 6:	return EtatCase.SIX;
				case 7:	return EtatCase.SEPT;
				case 8:	return EtatCase.HUIT;
				default:return EtatCase.DRAPEAU;
				}
			}
		}
	}

	public int getHauteur() {
		return hauteur;
	}

	public int getLargeur() {
		return largeur;
	}

	public int getMines() {
		return nbMines;
	}

	public void devoilerCase(int i, int j) {
		/* Case découverte */
		try {
			matrice[i][j].setDecouverte();
			caseNonMineeRestante--;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		/* on regarde si la case est minée */
		try {
			if (matrice[i][j].estMine())
				explosion = true;
			else {
				/* propagation éventuelle */
				if (matrice[i][j].getMinesAlentour() == 0) {
					try {
						if (!matrice[i - 1][j - 1].estDecouverte())
							devoilerCase(i - 1, j - 1);
					} catch (ArrayIndexOutOfBoundsException e) {
					}
					try {
						if (!matrice[i - 1][j].estDecouverte())
							devoilerCase(i - 1, j);
					} catch (ArrayIndexOutOfBoundsException e) {
					}
					try {
						if (!matrice[i - 1][j + 1].estDecouverte())
							devoilerCase(i - 1, j + 1);
					} catch (ArrayIndexOutOfBoundsException e) {
					}
					try {
						if (!matrice[i][j - 1].estDecouverte())
							devoilerCase(i, j - 1);
					} catch (ArrayIndexOutOfBoundsException e) {
					}
					try {
						if (!matrice[i][j + 1].estDecouverte())
							devoilerCase(i, j + 1);
					} catch (ArrayIndexOutOfBoundsException e) {
					}
					try {
						if (!matrice[i + 1][j - 1].estDecouverte())
							devoilerCase(i + 1, j - 1);
					} catch (ArrayIndexOutOfBoundsException e) {
					}
					try {
						if (!matrice[i + 1][j].estDecouverte())
							devoilerCase(i + 1, j);
					} catch (ArrayIndexOutOfBoundsException e) {
					}
					try {
						if (!matrice[i + 1][j + 1].estDecouverte())
							devoilerCase(i + 1, j + 1);
					} catch (ArrayIndexOutOfBoundsException e) {
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}

	public void drapeauAction(int i, int j) {
		matrice[i][j].drapeauAction();
	}

	private void preparerAlentour() {
		int minesCompteur;
		for (int i = 0; i < hauteur; i++)
			for (int j = 0; j < largeur; j++) {
				minesCompteur = 0;
				if (!matrice[i][j].estMine()) {
					try {
						if (matrice[i - 1][j - 1].estMine())
							minesCompteur++;
					} catch (ArrayIndexOutOfBoundsException e) {
					}
					try {
						if (matrice[i - 1][j].estMine())
							minesCompteur++;
					} catch (ArrayIndexOutOfBoundsException e) {
					}
					try {
						if (matrice[i - 1][j + 1].estMine())
							minesCompteur++;
					} catch (ArrayIndexOutOfBoundsException e) {
					}
					try {
						if (matrice[i][j - 1].estMine())
							minesCompteur++;
					} catch (ArrayIndexOutOfBoundsException e) {
					}
					try {
						if (matrice[i][j + 1].estMine())
							minesCompteur++;
					} catch (ArrayIndexOutOfBoundsException e) {
					}
					try {
						if (matrice[i + 1][j - 1].estMine())
							minesCompteur++;
					} catch (ArrayIndexOutOfBoundsException e) {
					}
					try {
						if (matrice[i + 1][j].estMine())
							minesCompteur++;
					} catch (ArrayIndexOutOfBoundsException e) {
					}
					try {
						if (matrice[i + 1][j + 1].estMine())
							minesCompteur++;
					} catch (ArrayIndexOutOfBoundsException e) {
					}
					/* les mines ont étés comptés */
					matrice[i][j].setMinesAlentour(minesCompteur);
				}
			}
	}

	private void miner() {
		int x, y;
		int i = 0;
		Random alea = new Random();
		while (i < nbMines) {
			x = alea.nextInt(hauteur);
			y = alea.nextInt(largeur);
			if (!matrice[x][y].estMine()) {
				matrice[x][y].poserBombe();
				i++;
			}
		}
	}

	public int nbrDrapeau() {
		int compteur = 0;
		for (int i = 0; i < hauteur; i++)
			for (int j = 0; j < largeur; j++) {
				if (matrice[i][j].yaDrapreau())
					compteur++;
			}
		return compteur;
	}

	public boolean aExplose() {
		return explosion;
	}

	public int getCaseNonMineeRestante() {
		return caseNonMineeRestante;
	}

	public boolean sourisyadrapeau(int x, int y) {
		return matrice[x][y].yaDrapreau();
	}

	public void sourisselection(int x, int y) {
		matrice[x][y].selectionner();
	}

	public void sourisdeselection(int x, int y) {
		matrice[x][y].deselectionner();
	}

	public boolean gagne() {
		return (getCaseNonMineeRestante() == 0);
	}

}