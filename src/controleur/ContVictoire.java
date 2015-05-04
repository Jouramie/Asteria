package controleur;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import vue.VueVictoire;
/**
 * Contrôleur pour l'écran de réussite du jeu !
 * 
 * @author Jonathan Samson
 * @version 1.0
 */
public class ContVictoire implements Controleur
{
	@FXML
	private Button jouer;
	@FXML
	private Button quitter;
	
	private VueVictoire vue;
	
	/**
	 * Constructeur du contrôleur.
	 */
	public ContVictoire()
	{
		vue = new VueVictoire();
	}
	
	/**
	 * Affiche la vue du menu.
	 */
	public void initialiser()
	{
		ContPrincipal.getInstance().afficherVue(vue, true);
	}
	
	/**
	 * Inutile pour l'instant.
	 */
	public void update(double dt)
	{
		
	}
	
	/**
	 * Callback lorsque le joueur veut quitter.
	 */
	@FXML
	public void quitter()
	{
		System.exit(0);
	}
	
	/**
	 * Callback lorsque le joueur veut jouer.
	 */
	@FXML
	public void menu()
	{
		ContPrincipal.getInstance().selectionnerControleur(new ContMenu());
	}
}