package controleur;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import modele.Corps;
import modele.Niveau;
import objets.Planete;
import objets.Planete.Texture;
import objets.Vaisseau;
import objets.VaisseauJoueur;
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
	private ChoiceBox<String> choiceBoxCorps;
	@FXML
	private ChoiceBox<Image> choiceBoxTexture;
	@FXML
	private TextField textFieldMasse;
	@FXML
	private TextField textFieldPositionX;
	@FXML
	private TextField textFieldPositionY;
	@FXML
	private TextField textFieldRayon;
	@FXML
	private TextField textFieldVitesseDepart;
	@FXML
	private VBox vBoxMenuPlanete;
	@FXML
	private VBox vBoxMenuVaisseau;
	@FXML
	private VBox vBoxMenu;
	
	private VueNiveau vue;
	
	private boolean leftPressed;
	private boolean rightPressed;
	private boolean upPressed;
	private boolean downPressed;
	private Corps corpsSelect;
	
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
		choiceBoxCorps.getItems().addAll("Vaisseau", "Plan�te", "Drapeau");
		textFieldRayon.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
			{
				try
				{
					((Planete) corpsSelect).setRayon((Double
							.valueOf(textFieldRayon.getText())));
					System.out.println(corpsSelect.getRayon());
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
				
			}
			
		});
		ContPrincipal.getInstance().arreterHorloge();
	}
	
	/**
	 * Inutile pour l'instant.
	 */
	public void update(double dt)
	{
		Camera cam = vue.getCamera();
		if (leftPressed)
		{
			double x = cam.getDeplacement().getX();
			double y = cam.getDeplacement().getY();
			
			x -= VITESSE_CAM * dt;
			
			cam.deplacer(x, y);
		}
		
		else if (rightPressed)
		{
			double x = cam.getDeplacement().getX();
			double y = cam.getDeplacement().getY();
			
			x += VITESSE_CAM * dt;
			
			cam.deplacer(x, y);
		}
		
		if (upPressed)
		{
			double x = cam.getDeplacement().getX();
			double y = cam.getDeplacement().getY();
			
			y -= VITESSE_CAM * dt;
			
			cam.deplacer(x, y);
		}
		
		else if (downPressed)
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
		try
		{
			File file = (new FileChooser()).showSaveDialog(null);
			
			if(!file.exists())
			{
				file.createNewFile();
			}
			
			while(!file.canWrite())
			{
				JOptionPane.showMessageDialog(null, "L'emplacement choisi ne peut pas �tre modifi�!", "Erreur", JOptionPane.ERROR_MESSAGE);
				file = (new FileChooser()).showSaveDialog(null);
			}
			
			//appeler la m�thode pour sauvegarder le niveau dans Niveau.
		}
		catch(Exception e)
		{
		}
	}
	
	/**
	 * M�thode pour le bouton charger.
	 */
	@FXML
	public void charge()
	{
		try
		{
			File file = (new FileChooser()).showOpenDialog(null);
			
			while(!file.canRead())
			{
				JOptionPane.showMessageDialog(null, "L'emplacement choisi ne peut pas �tre lu!", "Erreur", JOptionPane.ERROR_MESSAGE);
				file = (new FileChooser()).showOpenDialog(null);
			}
			
			Niveau.chargerNiveau(file);//cela devra �tre pass� en param�tre � la m�thode charger niveau de ContNiveau
		}
		catch(Exception e)
		{
		}
		
		vue.initialiserCorps();
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
	public void mouseClicked(MouseEvent event)
	{
		Point2D point = pane.sceneToLocal(event.getSceneX(), event.getSceneY());
		Camera cam = vue.getCamera();
		Vecteur pos = cam
				.localToGlobal(new Vecteur(point.getX(), point.getY()));
		switch (event.getButton())
		{
		case PRIMARY:
			mouseClickedPrimary(event, pos);
			break;
		case SECONDARY:
			mouseClickedSecondary(event, pos);
			break;
		default:
			break;
		}
	}
	
	private void mouseClickedPrimary(MouseEvent event, Vecteur pos)
	{
		boolean toucheCorps = false;
		corpsSelect = null;
		for (Corps c : ContPrincipal.getInstance().getCorps())
		{
			if (Math.abs(c.getPosition().getX() - pos.getX()) < c.getRayon()
					&& Math.abs(c.getPosition().getY() - pos.getY()) < c
							.getRayon())
			{
				toucheCorps = true;
				corpsSelect = c;
				break;
			}
		}
		if (!toucheCorps && choiceBoxCorps.getValue() != null)
		{
			switch (choiceBoxCorps.getValue())
			{
			case "Plan�te":
				corpsSelect = new Planete(6e15, pos.getX(), pos.getY(), 100);
				((Planete) corpsSelect).setTexture(Texture.RAYEE_ROUGE);
				break;
			case "Vaisseau":
				corpsSelect = new Vaisseau(0, 1000, 0, pos.getX(), pos.getY(),
						null);
				break;
			}
			ContPrincipal.getInstance().ajouterCorps(corpsSelect);
			vue.initialiserCorps();
			pane.requestFocus();
			
		}
		selectionnerCorps();
		
	}
	
	private void selectionnerCorps()
	{
		vBoxMenu.setVisible(true);
		if (corpsSelect.getClass().equals(Planete.class))
		{
			vBoxMenuPlanete.setVisible(true);
			vBoxMenuVaisseau.setVisible(false);
			textFieldRayon.setText("" + corpsSelect.getRayon());
			
		}
		else if (corpsSelect.getClass().equals(Vaisseau.class))
		{
			vBoxMenuPlanete.setVisible(false);
			vBoxMenuVaisseau.setVisible(true);
		}
	}
	
	private void mouseClickedSecondary(MouseEvent event, Vecteur pos)
	{
		// TODO ???
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
		case ESCAPE:
		{
			retour();
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
