package controleur;

import java.net.URISyntaxException;
import modele.Niveau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import vue.VueSelectionNiveau;

/**
 * Contr�leur pour le choix des niveaux.
 * 
 * @author Jonathan Samson
 * @version 1.0
 */
public class ContSelectionNiveau implements Controleur
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
	
	private VueSelectionNiveau vue;
	
	/**
	 * Constructeur du contr�leur.
	 */
	public ContSelectionNiveau()
	{
		vue = new VueSelectionNiveau();
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
		Niveau n = null;
		
		if (event.getSource() == b1)
		{
			n = Niveau.chargerNiveau(this.getClass().getResourceAsStream("/levels/level_1.txt"));
			
		}
		else if (event.getSource() == b2)
		{
			n = Niveau.chargerNiveau(this.getClass().getResourceAsStream("/levels/level_2.txt"));
		}
		
		else if (event.getSource() == b3)
		{
			n = Niveau.chargerNiveau(this.getClass().getResourceAsStream("/levels/level_3.txt"));
		}
		
		else if (event.getSource() == b4)
		{
			n = Niveau.chargerNiveau(this.getClass().getResourceAsStream("/levels/level_4.txt"));
		}
		
		else if (event.getSource() == b5)
		{
			n = Niveau.chargerNiveau(this.getClass().getResourceAsStream("/levels/level_5.txt"));
		}
		
		else if (event.getSource() == b6)
		{
			n = Niveau.chargerNiveau(this.getClass().getResourceAsStream("/levels/level_6.txt"));
		}
		
		else if (event.getSource() == b7)
		{
			n = Niveau.chargerNiveau(this.getClass().getResourceAsStream("/levels/level_7.txt"));
		}
		
		else if (event.getSource() == b8)
		{
			n = Niveau.chargerNiveau(this.getClass().getResourceAsStream("/levels/level_8.txt"));
		}
		
		else if (event.getSource() == b9)
		{
			n = Niveau.chargerNiveau(this.getClass().getResourceAsStream("/levels/level_9.txt"));
		}
		
		else if (event.getSource() == b10)
		{
			n = Niveau.chargerNiveau(this.getClass().getResourceAsStream("/levels/level_10.txt"));
		}
		
		if (n != null)
		{
			label.setText("Titre :  " + n.getTitreNiveau() + "\n" + "Description :  " + n.getDescriptionNiveau());	
		}
    }

	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		
	}
	
}
