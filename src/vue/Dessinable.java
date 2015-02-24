package vue;

import javafx.scene.Node;

/**
 * Interface représentant tous les objets dessinables du jeu.
 * @author EquBolduc
 * @version 1.0
 */
public interface Dessinable
{
	/**
	 * Retourne un noeud représentant l'objet.
	 * @return Noeud JavaFX représentant l'objet.
	 */
	Node getNoeud();
}
