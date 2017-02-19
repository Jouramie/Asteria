package vue;

import javafx.scene.layout.BorderPane;
import vue.VueJeu;

public class VueSelectionNiveau extends VueJeu
{
	
	/**
	 * Retourne le chemin vers le fichier FXML de la vue. LE ROOT DU FICHIER
	 * FXML DOIT eTRE UN BorderPane.
	 * 
	 * @return Chemin vers FXML.
	 */
	@Override
	public String getFXML()
	{
		return "/res/SelectionNiveau.fxml";
	}
	
	/**
	 * Initialise la vue. Cette methode est appelee une seule fois.
	 * 
	 * @param pane BorderPane de la vue.
	 */
	public void initialiser(BorderPane pane)
	{
		
	}
	
	/**
	 * Cette methode est appelee a toutes les frames. Permet de mettre a jour la
	 * vue.
	 * 
	 * @param dt Temps ecoule depuis le dernier frame.
	 */
	public void dessiner(double dt)
	{
		
	}
	
}
