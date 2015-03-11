package controleur;

import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import modele.Corps;
import objets.ObjetSpatial;
import objets.Planete;
import objets.Planete.Texture;
import objets.Vaisseau;
import utils.Vecteur;
import vue.Camera;
import vue.VueNiveau;

/**
 * Contr�leur pour le createur de niveaux
 * 
 * @author Jonathan Samson
 * @version 1.0
 */
public class ContNiveau implements Controleur
{
	public static final double VITESSE_ZOOM = 0.005;
	
	public static final double VITESSE_CAM = 1000;
	
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
	
	private boolean leftPressed;
	private boolean rightPressed;
	private boolean upPressed;
	private boolean downPressed;
	
	/**
	 * Constructeur du contr�leur.
	 */
	public ContNiveau()
	{
		vue = new VueNiveau();
		leftPressed = false;
		rightPressed = false;
		upPressed = false;
		downPressed = false;
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
		Camera cam = vue.getCamera();
		if(leftPressed)
		{
			double x = cam.getDeplacement().getX();
			double y = cam.getDeplacement().getY();
			
			x -= VITESSE_CAM * dt;
			
			cam.deplacer(x, y);
		}
		
		else if(rightPressed)
		{
			double x = cam.getDeplacement().getX();
			double y = cam.getDeplacement().getY();
			
			x += VITESSE_CAM * dt;
			
			cam.deplacer(x, y);
		}
		
		if(upPressed)
		{
			double x = cam.getDeplacement().getX();
			double y = cam.getDeplacement().getY();
			
			y -= VITESSE_CAM * dt;
			
			cam.deplacer(x, y);
		}
		
		else if(downPressed)
		{
			double x = cam.getDeplacement().getX();
			double y = cam.getDeplacement().getY();
			
			y += VITESSE_CAM * dt;
			
			cam.deplacer(x, y);
		}
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
		List<Corps> c = ContPrincipal.getInstance().getCorps();
		c.clear();
		vue.initialiserCorps();
	}
	
	/**
	 * m�thode qui g�re l'ajout d'objets dans la construction.
	 */
	@FXML
	public void mouseClicked(MouseEvent e)
	{
		ContPrincipal.getInstance().arreterHorloge();
		Point2D point = pane.sceneToLocal(e.getSceneX(), e.getSceneY());
		ObjetSpatial u = null;
		Camera cam = vue.getCamera();
		Vecteur pos = cam
				.localToGlobal(new Vecteur(point.getX(), point.getY()));
		
		if (e.getButton() == MouseButton.PRIMARY)
		{
			if (choice.getValue() == "Vaisseau")
			{
				u = new Vaisseau(0, 0, 0, pos.getX(), pos.getY(), null);
			}
			else if (choice.getValue() == "Plan�te")
			{
				u = new Planete(6e15, pos.getX(), pos.getY(), 100);
				((Planete) u).setTexture(Texture.RAYEE_ROUGE);
			}
			ContPrincipal.getInstance().ajouterCorps(u);
			vue.initialiserCorps();
			pane.requestFocus();
		}
		else if (e.getButton() == MouseButton.SECONDARY)
		{
			for (Corps c : ContPrincipal.getInstance().getCorps())
			{
				if (Math.abs(c.getPosition().getX() - pos.getX()) < c
						.getRayon()
						&& Math.abs(c.getPosition().getY() - pos.getY()) < c
								.getRayon())
				{
					
				}
			}
		}
	}
	
	@FXML
	public void keyPressed(KeyEvent e)
	{
		switch (e.getCode())
		{
			case A:
			{
				leftPressed = true;
				break;
			}
			case D:
			{
				rightPressed = true;
				break;
			}
			case W:
			{
				upPressed = true;
				break;
			}
			case S:
			{
				downPressed = true;
				break;
			}
			
			default:
				break;
		}
	}
	
	@FXML
	public void keyReleased(KeyEvent e)
	{
		switch (e.getCode())
		{
			case A:
			{
				leftPressed = false;
				break;
			}
			case D:
			{
				rightPressed = false;
				break;
			}
			case W:
			{
				upPressed = false;
				break;
			}
			case S:
			{
				downPressed = false;
				break;
			}
			
			default:
				break;
		}
	}
	
	/**
	 * M�thode pour le zoom.
	 */
	@FXML
	public void zoom(ScrollEvent e)
	{
		Camera cam = vue.getCamera();
		
		double delta = e.getDeltaY();
		
		if (delta > 0)
		{
			cam.zoomer(cam.getFacteur() + delta * VITESSE_ZOOM);
		}
		
		else
		{
			cam.zoomer(cam.getFacteur() + delta * VITESSE_ZOOM);
		}
	}
}
