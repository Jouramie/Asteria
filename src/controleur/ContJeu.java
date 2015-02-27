package controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import objets.Planete;
import objets.Vaisseau;
import utils.Vecteur;
import vue.Camera;
import vue.VueJeu;

/**
 * Contrôleur utilisé lors d'une session de jeu.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class ContJeu implements Controleur
{

	@FXML
	private Pane menuPause;
	
	private VueJeu vue;
	private Vaisseau vaisseau;
	
	/**
	 * Constructeur du contrôleur.
	 */
	public ContJeu()
	{
		vue = new VueJeu();
	}
	
	/**
	 * Initialise les éléments du jeu et affiche la vue. Démarre l'horloge du
	 * contrôleur principal.
	 */
	public void initialiser()
	{
		ContPrincipal.getInstance().demarrerTemps();
		
		Planete p1 = new Planete(6e15, new Vecteur(400, 400));
		ContPrincipal.getInstance().ajouterCorps(p1);
		
		Planete p2 = new Planete(6e15, new Vecteur(600, 0));
		ContPrincipal.getInstance().ajouterCorps(p2);
		
		Planete p3 = new Planete(3e15, new Vecteur(600, 600));
		ContPrincipal.getInstance().ajouterCorps(p3);
		
		Planete p4 = new Planete(3e15, new Vecteur(0, 600));
		ContPrincipal.getInstance().ajouterCorps(p4);
		
		vaisseau = new Vaisseau(7e4, new Vecteur(0, 0), 100, 100, new Vecteur(10, 10), new Vecteur(10, 10));
		ContPrincipal.getInstance().ajouterCorps(vaisseau);
		
		ContPrincipal.getInstance().afficherVue(vue);
	}
	
	
	@FXML
	public void pause(){
		ContPrincipal.getInstance().arreterTemps();
		menuPause.setVisible(true);
	}
	
	@FXML
	public void retour(){
		ContPrincipal.getInstance().viderCorps();
		ContPrincipal.getInstance().selectionnerControleur(new ContMenu());
	}
	
	@FXML
	public void retourjeu(){
		ContPrincipal.getInstance().demarrerTemps();
		menuPause.setVisible(false);
	}
	
	/**
	 * Met à jour le vaisseau.
	 */
	public void update(double dt)
	{
		Camera camera = vue.getCamera();
		camera.déplacer(vaisseau.getPositionX(), vaisseau.getPositionY());
	}
}
