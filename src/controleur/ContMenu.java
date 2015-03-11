package controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import vue.VueMenu;

/**
 * Contr�leur pour le menu principal
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class ContMenu implements Controleur
{
	@FXML
	private Button jouer;
	@FXML
	private Button quitter;
	
	/**
	 * Constructeur du contr�leur.
	 */
	public ContMenu()
	{
		
	}
	
	/**
	 * Affiche la vue du menu.
	 */
	public void initialiser()
	{
		ContPrincipal.getInstance().afficherVue(new VueMenu());
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
	public void jouer()
	{
		ContPrincipal.getInstance().selectionnerControleur(new ContJeu());
	}
	
	@FXML
	public void niveau()
	{
		ContPrincipal.getInstance().selectionnerControleur(new ContNiveau());
	}
}
