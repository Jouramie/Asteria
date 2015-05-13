package vue;

import javafx.scene.Node;

/**
 * Interface repr�sentant tous les objets dessinables du jeu.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public interface Dessinable
{
	/**
	 * Retourne un noeud repr�sentant l'objet.
	 * 
	 * @return Noeud JavaFX repr�sentant l'objet.
	 */
	Node getNoeud();
	
	/**
	 * Met � jour le noeud repr�sentant l'objet.
	 * 
	 * @param dt Temps �coul� depuis le dernier frame (en secondes).
	 */
	void maj(double dt);
}
