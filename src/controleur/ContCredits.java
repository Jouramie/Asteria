package controleur;

import javafx.application.Platform;
import objets.Tete;
import utils.Vecteur;
import vue.VueCredits;
import vue.VueJeu;

public class ContCredits implements Controleur
{
	private VueCredits vue;
	
	public ContCredits()
	{
		vue = new VueCredits();
	}
	
	public void initialiser()
	{
		ContPrincipal.getInstance().afficherVue(vue, true);
		Platform.runLater(() -> {
			chargerCredits();
		});
	}

	private void chargerCredits()
	{
		ContPrincipal.getInstance().ajouterCorps(new Tete(1, new Vecteur(40, 40), false, new Vecteur(50, 50)));
		ContPrincipal.getInstance().ajouterCorps(new Tete(1, new Vecteur(140, 40), false, new Vecteur(-50, 50)));
		ContPrincipal.getInstance().ajouterCorps(new Tete(1, new Vecteur(40, 140), false, new Vecteur(50, -50)));
		ContPrincipal.getInstance().ajouterCorps(new Tete(1, new Vecteur(140, 140), false, new Vecteur(-50, -50)));
		vue.initialiserCorps();
		ContPrincipal.getInstance().demarrerHorloge();
	}

	public void update(double dt)
	{
		
	}
}