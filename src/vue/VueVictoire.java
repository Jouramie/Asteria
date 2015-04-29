package vue;
import javafx.scene.layout.BorderPane;
/**
 * Vue de la fenêtre de réussite du jeu.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class VueVictoire implements Vue
{
	/**
	 * Constructeur de la vue de la victoire.
	 */
	public VueVictoire()
	{
		
	}
	
	/**
	 * Retourne le chemin vers le fichier FXML de la vue.
	 * 
	 * @return Chemin vers FXML.
	 */
	public String getFXML()
	{
		return "/res/Victoire.fxml";
	}
	
	/**
	 * Initialise la vue.
	 */
	public void initialiser(BorderPane pane)
	{
		
	}
	
	/**
	 * Met à jour la caméra.
	 */
	public void dessiner(double dt)
	{
		
	}
}
