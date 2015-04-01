package controleur;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import modele.Niveau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	Label label;
	@FXML
	Button play;
	@FXML
	Button b1;
	@FXML
	Button b2;
	@FXML
	Button b3;
	@FXML
	Button b4;
	@FXML
	Button b5;
	@FXML
	Button b6;
	@FXML
	Button b7;
	@FXML
	Button b8;
	@FXML
	Button b9;
	@FXML
	Button b10;
	
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

	/**
	 * Retourne au controleur principal, et � sa vue.
	 * 
	 */
	@FXML
	public void retour()
	{
		ContPrincipal.getInstance().selectionnerControleur(new ContMenu());
	}
	
	/**
	 * G�re les diff�rentes descriptions de niveaux.
	 */
	@FXML
	public void levels(ActionEvent event) throws URISyntaxException
	{
		if (event.getSource() == b1)
		{
			label.setText("Le niveau 1 \n Sur les plan�tes lointaines de la galaxie WALID-Z-09 se situent les habitants \n des plan�tes Emilios, Simonus, Jouramiax et Jonathex. \n Retrouvez votre chemin jusqu'� votre destination. Attention, assurez-vous de ne pas manquer \n de carburant ! Bonne chance !");
		}
		
		else if (event.getSource() == b2)
		{
			label.setText("Niveau 2, Description � venir :)");
		}
		
		else if (event.getSource() == b3)
		{
			label.setText("Niveau 3, Description � venir :)");
		}
		
		else if (event.getSource() == b4)
		{
			label.setText("Niveau 4, Description � venir :)");
		}
		
		else if (event.getSource() == b5)
		{
			label.setText("Niveau 5, Description � venir :)");
		}
		
		else if (event.getSource() == b6)
		{
			label.setText("Niveau 6, Description � venir :)");
		}
		
		else if (event.getSource() == b7)
		{
			label.setText("Niveau 7, Description � venir :)");
		}
		
		else if (event.getSource() == b8)
		{
			label.setText("Niveau 8, Description � venir :)");
		}
		
		else if (event.getSource() == b9)
		{
			label.setText("Niveau 9, Description � venir :)");
		}
		
		else if (event.getSource() == b10)
		{
			label.setText("Niveau 10, Description � venir :)");
		}
    }

	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		
	}
	
}
