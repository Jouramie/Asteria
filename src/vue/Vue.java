package vue;

import javafx.scene.layout.BorderPane;

/**
 * Interface pour toutes les vues.
 * @author EquBolduc
 * @version 1.0
 */
public interface Vue
{
	/**
	 * Retourne le chemin vers le fichier FXML de la vue.
	 * LE ROOT DU FICHIER FXML DOIT ÊTRE UN BorderPane.
	 * @return Chemin vers FXML.
	 */
	public String getFXML();
	
	/**
	 * Initialise la vue.
	 * Cette méthode est appelée une seule fois.
	 * @param pane BorderPane de la vue.
	 */
	public void initialiser(BorderPane pane);
	
	/**
	 * Cette méthode est appelée à toutes les frames.
	 * Permet de mettre à jour la vue.
	 * @param dt Temps écoulé depuis le dernier frame.
	 */
	void dessiner(double dt);
}
