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
		Planete p1 = new Planete(100000, new Vecteur(400, 400), new Vecteur(0, 0));
		ContPrincipal.getInstance().ajouterCorps(p1);
		
		Vaisseau v = new Vaisseau(100, new Vecteur(0, 0), 100, 100, new Vecteur(10, 10), false, new Vecteur(50, 0));
		ContPrincipal.getInstance().ajouterCorps(v);
		
		ContPrincipal.getInstance().afficherVue(new VueJeu());
	}

	public void update(double dt)
	{

	}
}
