package tp1;

import java.awt.event.*;

public class EcouteurSouris implements MouseListener, MouseMotionListener {
	private DFenetre fenetre;
	private int sourisX, sourisY;
	private boolean gauchePresse;

	public EcouteurSouris(DFenetre f) {
		fenetre = f;
		gauchePresse = false;
	}
	public void mouseReleased(MouseEvent me) {

		sourisX = me.getX() / 20;
		sourisY = me.getY() / 20;
		try {
			if (fenetre.getDpartie().aExplose()) {
				fenetre.goPerdu();
				fenetre.arretChrono();
			}
			if (!fenetre.getDpartie().aExplose() && !fenetre.getDpartie().gagne() && !(fenetre.getDpartie().sourisyadrapeau(sourisY, sourisX))) {
				if (me.getButton() == MouseEvent.BUTTON1) {
					gauchePresse = false;
					try {
						fenetre.getDpartie().sourisselection(sourisY, sourisX);
					} catch (NullPointerException npe) {
					}
					try {
						fenetre.getDpartie().devoilerCase(sourisY, sourisX);
					} catch (NullPointerException npe) {
					}
					fenetre.lancerChrono();
					if (fenetre.getDpartie().gagne()) {
						fenetre.goGagne();
						fenetre.arretChrono();
					} else if (fenetre.getDpartie().aExplose()) {
						fenetre.goPerdu();
						fenetre.arretChrono();
					} else
						fenetre.goCool();
				}
			}

		} catch (NullPointerException npe) {
		}
		me.getComponent().repaint();
	}
	public void mousePressed(MouseEvent me) {

		sourisX = me.getX() / 20;
		sourisY = me.getY() / 20;
		if (fenetre.getDpartie().aExplose()) {
			fenetre.goPerdu();
			fenetre.arretChrono();
		}
		if (!fenetre.getDpartie().aExplose() && !fenetre.getDpartie().gagne()) {
			if (me.getButton() == MouseEvent.BUTTON1) {
				gauchePresse = true;
				fenetre.getDpartie().sourisselection(sourisY, sourisX);
				fenetre.goOups();
			}
			if (me.getButton() == MouseEvent.BUTTON3) {
				fenetre.getDpartie().drapeauAction(sourisY, sourisX);
				fenetre.miseAJourCompteur();
			}
		}
		me.getComponent().repaint();
	}
	public void mouseExited(MouseEvent me) {

		if (gauchePresse)
			fenetre.goCool();
	}
	public void mouseEntered(MouseEvent me) {

		if (gauchePresse)
			fenetre.goOups();
	}
	public void mouseDragged(MouseEvent me) {

		int x = me.getX() / 20;
		int y = me.getY() / 20;
		if (((x != sourisX) || (y != sourisY)) && gauchePresse) {
			try {
				fenetre.getDpartie().sourisdeselection(sourisY, sourisX);
			} catch (NullPointerException npe) {
			}
			sourisX = x;
			sourisY = y;
			try {
				fenetre.getDpartie().sourisselection(sourisY, sourisX);
			} catch (NullPointerException npe) {
			}
			me.getComponent().repaint();
		}
	}

	public void mouseMoved(MouseEvent me) {}
	public void mouseClicked(MouseEvent e) {}
}