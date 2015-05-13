package controleur;

/**
 * Interface utilisé par tous les contrôleurs.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public interface Controleur
{
	/**
	 * Initialise le contrôleur. Cette méthode est appelée qu'une seule fois.
	 * C'est généralement ici que l'on charge la vue.
	 */
	void initialiser();
	
	/**
	 * Met à jour le modèle. Cette méthode est appelée à chaque frame. C'est ici
	 * que les modifications sur le vaisseau ou autres ont lieu.
	 * 
	 * @param dt Temps écoulé depuis le dernier frame (en secondes)
	 */
	void update(double dt);
}
