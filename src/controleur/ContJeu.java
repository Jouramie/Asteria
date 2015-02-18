package controleur;

import objets.Planete;
import utils.Vecteur;
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
		Planete p1 = new Planete(5000, new Vecteur(0, 0));
		ContPrincipal.getInstance().ajouterCorps(p1);
		
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
