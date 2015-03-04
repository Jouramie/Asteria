package controleur;

import java.awt.event.ActionListener;

import objets.ObjetSpatial;
import objets.Planete;
import objets.Vaisseau;
import utils.Vecteur;
import vue.Camera;
import vue.VueNiveau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * Contr�leur pour le createur de niveaux
 * @author Jonathan Samson
 * @version 1.0
 */
public class ContNiveau implements Controleur
{
	public static final double VITESSE_ZOOM = 0.005;
	
	@FXML
	private Pane pane;
	@FXML
	private Button retour;
	@FXML
	private Button sauve;
	@FXML
	private Button erase;
	@FXML
	private ChoiceBox<String> choice;
	@FXML
	private ColorPicker color;
	@FXML
	private Slider masse;

	private VueNiveau vue;
	
	/**
	 * Constructeur du contr�leur.
	 */
	public ContNiveau()
	{
		vue = new VueNiveau();
	}
	
	/**
	 * Affiche la vue du constructeur.
	 */
	public void initialiser()
	{
		ContPrincipal.getInstance().afficherVue(vue, true);
		choice.getItems().addAll("Vaisseau", "Plan�te", "Drapeau");
	}

	/**
	 * Inutile pour l'instant.
	 */
	public void update(double dt)
	{
		
	}
	
	/**
	 * M�thode pour le bouton retour
	 */
	@FXML
	public void retour()
	{
		ContPrincipal.getInstance().selectionnerControleur(new ContMenu());
	}
	/**
	 * M�thode pour le bouton sauvegarder
	 */
	@FXML
	public void sauve()
	{
		System.out.println("SAUVEGARDER");
	}
	/**
	 * M�thode pour le bouton effacer
	 */
	@FXML
	public void erase()
	{
		System.out.println("EFFACER");
	}
	/**
	 * m�thode qui g�re l'ajout d'objets dans la construction.
	 */
	@FXML
	public void mouseClicked(MouseEvent e)
	{
		ObjetSpatial u = null;
		double posX = e.getSceneX();
		double posY = e.getSceneY();
		
		Camera cam = vue.getCamera();
		Point2D point = pane.sceneToLocal(e.getSceneX(), e.getSceneY());
		Vecteur pos = cam.localToGlobal(new Vecteur(point.getX(), point.getY()));
		
		if (choice.getValue() == "Vaisseau")
		{
			u = new Vaisseau(0, null, 0, 0, pos.getX(), pos.getY(), null);
			vue.initialiserCorps();
		}
		else if (choice.getValue() == "Plan�te")
		{
			u = new Planete(6e15, pos.getX(), pos.getY(), "/res/planete1.png");
			vue.initialiserCorps();
		}
		
		ContPrincipal.getInstance().ajouterCorps(u);
		vue.initialiserCorps();
		System.out.println("AJOUTER OBJET S�LECTIONN� � L'ENDROIT " + "("+posX+" , "+posY+")");
	}
	/**
	 * M�thode pour le zoom.
	 */
	@FXML
	public void zoom(ScrollEvent e)
	{
		Camera cam = vue.getCamera();
		
		double delta = e.getDeltaY();
		
		if(delta > 0)
		{
			cam.zoomer(cam.getFacteur() + delta * VITESSE_ZOOM);
		}
		
		else
		{
			cam.zoomer(cam.getFacteur() + delta * VITESSE_ZOOM);
		}
	}
}
