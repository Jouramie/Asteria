package controleur;

import objets.Planete;
import objets.Vaisseau;
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
		Planete p1 = new Planete(6e15, new Vecteur(400, 400));
		ContPrincipal.getInstance().ajouterCorps(p1);
		
		Planete p2 = new Planete(6e15, new Vecteur(600, 0));
		ContPrincipal.getInstance().ajouterCorps(p2);
		
		Vaisseau v = new Vaisseau(7e4, new Vecteur(0, 0), 100, 100, new Vecteur(10, 10), new Vecteur(10, 10));
		ContPrincipal.getInstance().ajouterCorps(v);
		
		ContPrincipal.getInstance().afficherVue(new VueJeu());
	}

	public void update(double dt)
	{

	}
}
