package vue;

import javafx.scene.layout.BorderPane;

/**
 * Interface pour toutes les vues.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public interface Vue
{
	/**
	 * Retourne le chemin vers le fichier FXML de la vue. LE ROOT DU FICHIER
	 * FXML DOIT eTRE UN BorderPane.
	 * 
	 * @return Chemin vers FXML.
	 */
	public String getFXML();
	
	/**
	 * Initialise la vue. Cette methode est appelee une seule fois.
	 * 
	 * @param pane BorderPane de la vue.
	 */
	public void initialiser(BorderPane pane);
	
	/**
	 * Cette methode est appelee a toutes les frames. Permet de mettre a jour la
	 * vue.
	 * 
	 * @param dt Temps ecoule depuis le dernier frame.
	 */
	void dessiner(double dt);
}
