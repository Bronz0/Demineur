package metier;

public interface IDPartie {

	public void setHauteur(int hauteur);
	public void setLargeur(int largeur);
	public void setNbMines(int nbMines);
	public void nouvellePartie();
	public EtatCase getEtatCase(int i, int j);
	public int getHauteur();
	public int getLargeur();
	public int getMines();
	public void devoilerCase(int i, int j);
	public void drapeauAction(int i, int j);
	public int nbrDrapeau();
	public boolean aExplose();
	public int getCaseNonMineeRestante();
	public boolean sourisyadrapeau(int x, int y);
	public void sourisselection(int x, int y);
	public void sourisdeselection(int x, int y);
	public boolean gagne();

}
