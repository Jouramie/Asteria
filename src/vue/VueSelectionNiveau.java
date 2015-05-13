package vue;

import javafx.scene.layout.BorderPane;
import vue.VueJeu;

public class VueSelectionNiveau extends VueJeu
{
	
	/**
	 * Retourne le chemin vers le fichier FXML de la vue. LE ROOT DU FICHIER
	 * FXML DOIT ÊTRE UN BorderPane.
	 * 
	 * @return Chemin vers FXML.
	 */
	@Override
	public String getFXML()
	{
		return "/res/SelectionNiveau.fxml";
	}
	
	/**
	 * Initialise la vue. Cette méthode est appelée une seule fois.
	 * 
	 * @param pane BorderPane de la vue.
	 */
	public void initialiser(BorderPane pane)
	{
		
	}
	
	/**
	 * Cette méthode est appelée à toutes les frames. Permet de mettre à jour la
	 * vue.
	 * 
	 * @param dt Temps écoulé depuis le dernier frame.
	 */
	public void dessiner(double dt)
	{
		
	}
	
}
