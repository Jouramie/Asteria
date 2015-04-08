package controleur;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.StringTokenizer;

import modele.Niveau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import vue.VueSelectionNiveau;

/**
 * Contrôleur pour le choix des niveaux.
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
	
	private VueSelectionNiveau vue;
	
	/**
	 * Constructeur du contrôleur.
	 */
	public ContScreen()
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
	 * Retourne au controleur principal, et à sa vue.
	 * 
	 */
	@FXML
	public void retour()
	{
		ContPrincipal.getInstance().selectionnerControleur(new ContMenu());
	}
	
	/**
	 * Gère les différentes descriptions de niveaux.
	 */
	@FXML
	public void levels(ActionEvent event) throws URISyntaxException
	{
		File file = new File(System.getProperty("java.class.path"));
		Niveau n = null;
		StringTokenizer st = new StringTokenizer(file.getAbsolutePath(),";");
		String chemin = st.nextToken();
		chemin.replace("\\", "\\\\");
		
		if (event.getSource() == b1)
		{
			n = Niveau.chargerNiveau(new File(chemin + "\\levels\\level_01.txt"));
			
		}
		else if (event.getSource() == b2)
		{
			n = Niveau.chargerNiveau(new File(chemin + "\\levels\\level_02.txt"));
		}
		
		else if (event.getSource() == b3)
		{
			n = Niveau.chargerNiveau(new File(chemin + "\\levels\\level_03.txt"));
		}
		
		else if (event.getSource() == b4)
		{
			n = Niveau.chargerNiveau(new File(chemin + "\\levels\\level_04.txt"));
		}
		
		else if (event.getSource() == b5)
		{
			n = Niveau.chargerNiveau(new File(chemin + "\\levels\\level_05.txt"));
		}
		
		else if (event.getSource() == b6)
		{
			n = Niveau.chargerNiveau(new File(chemin + "\\levels\\level_06.txt"));
		}
		
		else if (event.getSource() == b7)
		{
			n = Niveau.chargerNiveau(new File(chemin + "\\levels\\level_07.txt"));
		}
		
		else if (event.getSource() == b8)
		{
			n = Niveau.chargerNiveau(new File(chemin + "\\levels\\level_08.txt"));
		}
		
		else if (event.getSource() == b9)
		{
			n = Niveau.chargerNiveau(new File(chemin + "\\levels\\level_09.txt"));
		}
		
		else if (event.getSource() == b10)
		{
			n = Niveau.chargerNiveau(new File(chemin + "\\levels\\level_10.txt"));
		}
		
		if (n != null)
		{
			label.setText(n.getDescriptionNiveau());	
		}
    }

	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		
	}
	
}
