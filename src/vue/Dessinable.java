package vue;

import javafx.scene.Node;

/**
 * Interface repr�sentant tous les objets dessinables du jeu.
 * @author EquBolduc
 * @version 1.0
 */
public interface Dessinable
{
	/**
	 * Retourne un noeud repr�sentant l'objet.
	 * @return Noeud JavaFX repr�sentant l'objet.
	 */
	Node getNoeud();
}
