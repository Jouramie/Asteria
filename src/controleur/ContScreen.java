package controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import vue.VueScreen;

/**
 * Contr�leur pour le choix des niveaux.
 * 
 * @author Jonathan Samson
 * @version 1.0
 */
public class ContScreen implements Controleur
{
	
	@FXML
	Button play;
	
	private VueScreen vue;
	
	/**
	 * Constructeur du contr�leur.
	 */
	public ContScreen()
	{
		vue = new VueScreen();
	}
	
	/**
	 * Affiche la vue du jeu
	 */
	@FXML
	public void play()
	{
		ContPrincipal.getInstance().selectionnerControleur(new ContJeu());
	}
	/**
	 * Affiche la vue du constructeur.
	 */
	public void initialiser()
	{
		ContPrincipal.getInstance().afficherVue(vue, true);
	}

	@FXML
	public void retour()
	{
		ContPrincipal.getInstance().selectionnerControleur(new ContMenu());
	}
	
	/**
	 * m�thode qui g�re l'ajout d'objets dans la construction.
	 */
	@FXML
	public void mouseClicked(MouseEvent e)
	{
		
	}

	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		
	}
	
}
