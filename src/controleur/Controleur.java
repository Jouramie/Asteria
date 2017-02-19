package controleur;

/**
 * Interface utilise par tous les controleurs.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public interface Controleur
{
	/**
	 * Initialise le controleur. Cette methode est appelee qu'une seule fois.
	 * C'est generalement ici que l'on charge la vue.
	 */
	void initialiser();
	
	/**
	 * Met a jour le modele. Cette methode est appelee a chaque frame. C'est ici
	 * que les modifications sur le vaisseau ou autres ont lieu.
	 * 
	 * @param dt Temps ecoule depuis le dernier frame (en secondes)
	 */
	void update(double dt);
}
