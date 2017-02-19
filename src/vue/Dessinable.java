package vue;

import javafx.scene.Node;

/**
 * Interface representant tous les objets dessinables du jeu.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public interface Dessinable
{
	/**
	 * Retourne un noeud representant l'objet.
	 * 
	 * @return Noeud JavaFX representant l'objet.
	 */
	Node getNoeud();
	
	/**
	 * Cree un noeud representant l'objet.
	 */	
	void creeNoeud();
	
	/**
	 * Met a jour le noeud representant l'objet.
	 * 
	 * @param dt Temps ecoule depuis le dernier frame (en secondes).
	 */
	void miseAJourGraphique(double dt);
}
