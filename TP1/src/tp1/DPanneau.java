package tp1;

import javax.swing.*;
import java.awt.*;

public class DPanneau extends JPanel {

	private static final long serialVersionUID = 1L;

	private DFenetre fenetre;

	public DPanneau(DFenetre fenetre) {
		super();

		this.fenetre = fenetre;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < fenetre.getDpartie().getHauteur(); i++)
			for (int j = 0; j < fenetre.getDpartie().getLargeur(); j++) {
				g.drawImage(fenetre.getIcone(fenetre.getDpartie().getEtatCase(i, j)).getImage(), j * 20, i * 20, this);
			}
	}
}
