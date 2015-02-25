package controleur;

import objets.Planete;
import objets.Vaisseau;
import utils.Vecteur;
import vue.VueJeu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Contrôleur utilisé lors d'une session de jeu.
 * @author EquBolduc
 * @version 1.0
 */
public class ContJeu implements Controleur
{
	@FXML
	private Button retour;
	
	/**
	 * Constructeur du contrôleur.
	 */
	public ContJeu()
	{
		
	}
	
	/**
	 * Initialise les éléments du jeu et affiche la vue.
	 */
	public void initialiser()
	{	
		Planete p1 = new Planete(6e15, new Vecteur(400, 400));
		ContPrincipal.getInstance().ajouterCorps(p1);
		
		Planete p2 = new Planete(6e15, new Vecteur(600, 0));
		ContPrincipal.getInstance().ajouterCorps(p2);
		
		Planete p3 = new Planete(3e15, new Vecteur(600, 600));
		ContPrincipal.getInstance().ajouterCorps(p3);
		
		Planete p4 = new Planete(3e15, new Vecteur(0, 600));
		ContPrincipal.getInstance().ajouterCorps(p4);
		
		Vaisseau v = new Vaisseau(7e4, new Vecteur(0, 0), 100, 100, new Vecteur(10, 10), new Vecteur(10, 10));
		ContPrincipal.getInstance().ajouterCorps(v);
		
		Vaisseau v2 = new Vaisseau(7e4, new Vecteur(0, 0), 100, 100, new Vecteur(1000, 10), new Vecteur(10, 10));
		ContPrincipal.getInstance().ajouterCorps(v2);
		
		ContPrincipal.getInstance().afficherVue(new VueJeu());
	}

	/**
	 * Met à jour le vaisseau.
	 */
	public void update(double dt)
	{
		// TODO: Mettre à jour le vaisseau selon la position de la souris ou des touches du clavier.
	}
}
