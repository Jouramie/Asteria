package controleur;

import vue.VueJeu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ContJeu implements Controleur
{
	@FXML
	private Button retour;
	
	public ContJeu()
	{
		
	}
	
	public void initialiser()
	{
		ContPrincipal.getInstance().afficherVue(new VueJeu());
	}

	public void update(double dt)
	{

	}

	public void ajouterEcouteurs()
	{

	}

	public void supprimerEcouteur()
	{
		
	}

}
