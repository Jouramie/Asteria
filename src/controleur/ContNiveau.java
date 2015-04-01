package controleur;

import java.io.File;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import javax.swing.JOptionPane;

import modele.Corps;
import modele.Niveau;
import objets.Planete;
import objets.Planete.Texture;
import objets.Vaisseau;
import utils.Vecteur;
import vue.Camera;
import vue.VueNiveau;

/**
 * Contrôleur pour le createur de niveaux
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
	private ComboBox<Label> comboBoxTexture;
	@FXML
	private TextField textFieldMasse;
	@FXML
	private TextField textFieldPositionX;
	@FXML
	private TextField textFieldPositionY;
	@FXML
	private TextField textFieldRayon;
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
	
	private Niveau niveau;
	
	/**
	 * Constructeur du contrôleur.
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
		niveau = new Niveau();
		
		ContPrincipal.getInstance().afficherVue(vue, true);
		ContPrincipal.getInstance().arreterHorloge();
		choiceBoxCorps.getItems().addAll("Vaisseau", "Planète");
		
		/*comboBoxTexture.getItems().clear();
		Label temp = new Label("Magenta");
		temp.setGraphic(new ImageView("/res/planeteMagenta.png"));
		comboBoxTexture.getItems().add(temp);*/
		
		ContPrincipal.getInstance().arreterHorloge();
	}
	
	@FXML
	public void onRayon(ActionEvent e)
	{
		try
		{
			((Planete) corpsSelect).setRayon((Double.valueOf(textFieldRayon.getText())));
			((Planete) corpsSelect).maj();
		}
		catch (NumberFormatException ex)
		{
			textFieldRayon.setText("" + corpsSelect.getRayon());
		}
	}
	
	@FXML
	public void onMasse(ActionEvent e)
	{
		try
		{
			corpsSelect.setMasse((Double.valueOf(textFieldMasse.getText())));
			
		}
		catch (NumberFormatException ex)
		{
			textFieldMasse.setText("" + corpsSelect.getMasse());
		}
	}
	
	@FXML
	public void onPositionX(ActionEvent e)
	{
		try
		{
			corpsSelect.setPositionX((Double.valueOf(textFieldPositionX
					.getText())));
		}
		catch (NumberFormatException ex)
		{
			textFieldPositionX.setText("" + corpsSelect.getPositionX());
		}
	}
	
	@FXML
	public void onPositionY(ActionEvent e)
	{
		try
		{
			corpsSelect.setPositionY((Double.valueOf(textFieldPositionY.getText())));
		}
		catch (NumberFormatException ex)
		{
			textFieldPositionY.setText("" + corpsSelect.getPositionY());
		}
	}
	
	@FXML
	public void onTexture(ActionEvent e)
	{
		switch (comboBoxTexture.getValue().getText())
		{
		case "Rouge":
			((Planete) corpsSelect).setTexture(Texture.ROUGE);
			break;
		case "Bleue":
			((Planete) corpsSelect).setTexture(Texture.BLEUE);
			break;
		case "Jaune":
			((Planete) corpsSelect).setTexture(Texture.JAUNE);
			break;
		case "Orange":
			((Planete) corpsSelect).setTexture(Texture.ORANGE);
			break;
		case "Verte":
			((Planete) corpsSelect).setTexture(Texture.VERTE);
			break;
		case "Magenta":
			((Planete) corpsSelect).setTexture(Texture.MAGENTA);
			break;
		}
		chargerNiveau();
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
	 * Méthode pour le bouton retour
	 */
	@FXML
	public void retour()
	{
		ContPrincipal.getInstance().selectionnerControleur(new ContMenu());
	}
	
	/**
	 * Méthode pour le bouton sauvegarder
	 */
	@FXML
	public void sauve()
	{
		try
		{
			File file = (new FileChooser()).showSaveDialog(null);
			
			if (!file.exists())
			{
				file.createNewFile();
			}
			
			while (!file.canWrite())
			{
				JOptionPane.showMessageDialog(null,
						"L'emplacement choisi ne peut pas être modifié!",
						"Erreur", JOptionPane.ERROR_MESSAGE);
				file = (new FileChooser()).showSaveDialog(null);
			}
			
			niveau.sauvegarderNiveau(file);
		}
		catch (Exception e)
		{
		}
	}
	
	/**
	 * Méthode pour le bouton charger.
	 */
	@FXML
	public void charge()
	{
		try
		{
			File file = (new FileChooser()).showOpenDialog(null);
			
			while (!file.canRead())
			{
				JOptionPane.showMessageDialog(null,
						"L'emplacement choisi ne peut pas être lu!", "Erreur",
						JOptionPane.ERROR_MESSAGE);
				file = (new FileChooser()).showOpenDialog(null);
			}
			
			niveau = Niveau.chargerNiveau(file);
			
			chargerNiveau();
		}
		catch (Exception e)
		{
		}
		
		vue.initialiserCorps();
	}
	
	/**
	 * Méthode pour le bouton effacer
	 */
	@FXML
	public void erase()
	{
		List<Corps> c = ContPrincipal.getInstance().getCorps();
		c.clear();
		vue.initialiserCorps();
		niveau.getCorps().clear();
	}
	
	/**
	 * méthode qui gère l'ajout d'objets dans la construction.
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
	
	/**
	 * méthode qui gère l'ajout d'objets dans la construction.
	 */
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
			case "Planète":
				creePlanete(pos);
				break;
			case "Vaisseau":
				corpsSelect = new Vaisseau(0, 1000, 0, pos.getX(), pos.getY(),
						null);
				break;
			}
			niveau.ajouterCorps(corpsSelect);
			chargerNiveau();
			pane.requestFocus();
			
		}
		selectionnerCorps();
	}
	
	
	/**
	 * méthode qui gère la création de planètes dans l'éditeur de niveaux.
	 */
	private void creePlanete(Vecteur pos)
	{
		double masse;
		double rayon;
		try
		{
			masse = Double.valueOf(textFieldMasse.getText());
		}
		catch (NumberFormatException e)
		{
			masse = Planete.PLANETE_MASSE_DEFAUT;
		}
		try
		{
			rayon = Double.valueOf(textFieldRayon.getText());
		}
		catch (NumberFormatException e)
		{
			rayon = Planete.RAYON_DEFAUT;
		}
		
		corpsSelect = new Planete(masse, pos, rayon);
		try
		{
			switch (comboBoxTexture.getValue().getText())
			{
			case "Rouge":
				((Planete) corpsSelect).setTexture(Texture.ROUGE);
				break;
			case "Bleue":
				((Planete) corpsSelect).setTexture(Texture.BLEUE);
				break;
			case "Jaune":
				((Planete) corpsSelect).setTexture(Texture.JAUNE);
				break;
			case "Orange":
				((Planete) corpsSelect).setTexture(Texture.ORANGE);
				break;
			case "Verte":
				((Planete) corpsSelect).setTexture(Texture.VERTE);
				break;
			case "Magenta":
				((Planete) corpsSelect).setTexture(Texture.MAGENTA);
				break;
			}
		}
		catch (NullPointerException e)
		{
			((Planete) corpsSelect).setTexture(Planete.TEXTURE_DEFAUT);
		}
	}
	
	/**
	 * méthode qui gère la sélection d'un objet de l'éditeur dans le but de la modifier.
	 */
	private void selectionnerCorps()
	{
		if(corpsSelect == null)
		{
			return;
		}
		
		vBoxMenu.setVisible(true);
		if (corpsSelect.getClass().equals(Planete.class))
		{
			vBoxMenuPlanete.setVisible(true);
			vBoxMenuVaisseau.setVisible(false);
			textFieldRayon.setText("" + corpsSelect.getRayon());
			textFieldMasse.setText("" + corpsSelect.getMasse());
			textFieldPositionX.setText("" + corpsSelect.getPositionX());
			textFieldPositionY.setText("" + corpsSelect.getPositionY());
			switch (((Planete) corpsSelect).getTexture())
			{
			case ROUGE:
				for (Label l : comboBoxTexture.getItems())
				{
					if (l.getText().equals("Rouge"))
					{
						comboBoxTexture.setValue(l);
					}
				}
				break;
			case BLEUE:
				for (Label l : comboBoxTexture.getItems())
				{
					if (l.getText().equals("Bleue"))
					{
						comboBoxTexture.setValue(l);
					}
				}
				break;
			case JAUNE:
				for (Label l : comboBoxTexture.getItems())
				{
					if (l.getText().equals("Jaune"))
					{
						comboBoxTexture.setValue(l);
					}
				}
				break;
			case MAGENTA:
				for (Label l : comboBoxTexture.getItems())
				{
					if (l.getText().equals("Rouge"))
					{
						comboBoxTexture.setValue(l);
					}
				}
				break;
			case ORANGE:
				for (Label l : comboBoxTexture.getItems())
				{
					if (l.getText().equals("Orange"))
					{
						comboBoxTexture.setValue(l);
					}
				}
				break;
			case VERTE:
				for (Label l : comboBoxTexture.getItems())
				{
					if (l.getText().equals("Verte"))
					{
						comboBoxTexture.setValue(l);
					}
				}
				break;
			}
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
	
	/**
	 * Ajoute tous les corps d'un niveau donné dans la vue.
	 */
	private void chargerNiveau()
	{
		ContPrincipal.getInstance().viderCorps();
		
		for (Corps c : niveau.getCorps())
		{
			ContPrincipal.getInstance().ajouterCorps(c);
		}
		
		vue.initialiserCorps();
	}
	
	// Cette méthode ne sert pas encore.
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
	
	// Cette méthode ne sert pas encore.
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
	 * Méthode pour le zoom.
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
