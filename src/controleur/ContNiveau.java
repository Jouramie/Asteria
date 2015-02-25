package controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Contrôleur pour le createur de niveaux
 * @author Jonathan Samson
 * @version 1.0
 */
public class ContNiveau implements Controleur
{
	@FXML
	private Button X;
	@FXML
	private Button Y;
	
	/**
	 * Constructeur du contrôleur.
	 */
	public ContNiveau()
	{
		
	}
	
	/**
	 * Affiche la vue du constructeur.
	 */
	public void initialiser()
	{
		ContPrincipal.getInstance().afficherVue(new VueNiveau());
	}

	/**
	 * Inutile pour l'instant.
	 */
	public void update(double dt)
	{
		
	}
	@FXML
	public void niveau()
	{
		
	}
}
