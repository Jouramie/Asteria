package controleur;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;

import javax.swing.JOptionPane;

import modele.Corps;
import modele.Niveau;
import modele.Objectif;
import modele.ObjectifRayon;
import objets.Planete;
import objets.Planete.Texture;
import objets.Vaisseau;
import objets.VaisseauJoueur;
import utils.Vecteur;
import vue.Camera;
import vue.Dessinable;
import vue.VueEditeur;

/**
 * Contr�leur pour le createur de niveaux
 * 
 * @author Jonathan Samson
 * @author J�r�mie Bolduc
 * @version 1.0
 */
public class ContEditeur implements Controleur
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
	private ComboBox<String> comboBoxCorps;
	@FXML
	private ComboBox<Texture> comboBoxTexture;
	@FXML
	private TextField textFieldMasse;
	@FXML
	private TextField textFieldPositionX;
	@FXML
	private TextField textFieldPositionY;
	@FXML
	private TextField textFieldRayon;
	@FXML
	private TextField textFieldCarburantMax;
	@FXML
	private TextField textFieldCarburantDepart;
	@FXML
	private VBox vBoxMenuPlanete;
	@FXML
	private VBox vBoxMenuVaisseau;
	@FXML
	private VBox vBoxMenu;
	
	private VueEditeur vue;
	
	private boolean leftPressed;
	private boolean rightPressed;
	private boolean upPressed;
	private boolean downPressed;
	private Corps corpsSelect;
	private Objectif objectifSelect;
	private VaisseauJoueur vaisseauJoueur;
	private Objectif objectif;
	
	private Niveau niveau;
	
	/**
	 * Constructeur du contr�leur.
	 */
	public ContEditeur()
	{
		vue = new VueEditeur();
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
		comboBoxCorps.getItems().addAll("Vaisseau", "Plan�te", "Portail",
				"Vaisseau Joueur");
		initialiserComboBoxTexture();
		vBoxMenu.setVisible(false);
		ContPrincipal.getInstance().viderCorps();
		vaisseauJoueur = null;
		objectif = null;
		Platform.runLater(() -> {
			chargerNiveau();
		});
		
	}
	
	@FXML
	public void onRayon(ActionEvent e)
	{
		try
		{
			((Planete) corpsSelect).setRayon((Double.valueOf(textFieldRayon
					.getText())));
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
			corpsSelect.setPositionY((Double.valueOf(textFieldPositionY
					.getText())));
		}
		catch (NumberFormatException ex)
		{
			textFieldPositionY.setText("" + corpsSelect.getPositionY());
		}
	}
	
	@FXML
	public void onSupprimer()
	{
		niveau.supprimerCorps(corpsSelect);
		chargerNiveau();
	}
	
	@FXML
	public void onTexture(ActionEvent e)
	{
		if (corpsSelect != null)
		{
			((Planete) corpsSelect).setTexture(comboBoxTexture.getValue());
			chargerNiveau();
		}
	}
	
	@FXML
	public void onCarburantMax(ActionEvent e)
	{
		try
		{
			Vaisseau corpsSelect = (Vaisseau) this.corpsSelect;
			corpsSelect.setCarburantMax((Double.valueOf(textFieldCarburantMax
					.getText())));
			textFieldCarburantDepart.setText(""
					+ corpsSelect.getCarburantDepart());
		}
		catch (NumberFormatException ex)
		{
			textFieldCarburantMax.setText(""
					+ ((Vaisseau) corpsSelect).getCarburantMax());
		}
	}
	
	@FXML
	public void onCarburantDepart(ActionEvent e)
	{
		try
		{
			Vaisseau corpsSelect = (Vaisseau) this.corpsSelect;
			corpsSelect.setCarburantDepart((Double
					.valueOf(textFieldCarburantDepart.getText())));
			textFieldCarburantMax.setText("" + corpsSelect.getCarburantMax());
		}
		catch (NumberFormatException ex)
		{
			textFieldCarburantDepart.setText(""
					+ ((Vaisseau) corpsSelect).getCarburantDepart());
		}
	}
	
	@FXML
	public void onComboBoxCorps(ActionEvent e)
	{
		
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
			
			if (!file.exists())
			{
				file.createNewFile();
			}
			
			while (!file.canWrite())
			{
				JOptionPane.showMessageDialog(null,
						"L'emplacement choisi ne peut pas �tre modifi�!",
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
	 * M�thode pour le bouton charger.
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
						"L'emplacement choisi ne peut pas �tre lu!", "Erreur",
						JOptionPane.ERROR_MESSAGE);
				file = (new FileChooser()).showOpenDialog(null);
			}
			
			niveau = Niveau.chargerNiveau(new FileInputStream(file));
			
			chargerNiveau();
		}
		catch (Exception e)
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
		niveau.getCorps().clear();
		niveau.ajouterCorps(vaisseauJoueur);
		chargerNiveau();
	}
	
	private void initialiserComboBoxTexture()
	{
		comboBoxTexture.getItems().addAll(Texture.BLEUE, Texture.JAUNE,
				Texture.MAGENTA, Texture.ORANGE, Texture.ROUGE, Texture.VERTE);
		
		comboBoxTexture
				.setCellFactory(new Callback<ListView<Texture>, ListCell<Texture>>()
				{
					@Override
					public ListCell<Texture> call(ListView<Texture> p)
					{
						return new ListCell<Texture>()
						{
							private final ImageView imageView;
							{
								setContentDisplay(ContentDisplay.LEFT);
								imageView = new ImageView();
								imageView.setPreserveRatio(true);
								imageView.setFitWidth(32);
							}
							
							@Override
							protected void updateItem(Texture item,
									boolean empty)
							{
								super.updateItem(item, empty);
								
								if (item == null || empty)
								{
									setGraphic(null);
								}
								else
								{
									imageView.setImage(new Image(item
											.getTexture()));
									setGraphic(imageView);
									setText(item.toString());
								}
							}
						};
					}
				});
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
	
	/**
	 * m�thode qui g�re l'ajout d'objets dans la construction.
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
		if (!toucheCorps)
		{
			if (objectif instanceof ObjectifRayon)
			{
				ObjectifRayon or = (ObjectifRayon) objectif;
				if (Math.abs(or.getPosRayon().getX() - pos.getX()) < or
						.getRayon()
						&& Math.abs(or.getPosRayon().getY() - pos.getY()) < (or
								.getRayon()))
				{
				toucheCorps = true;
				objectifSelect = objectif;
				System.out.println("ASDFAS");
				}
				
			}
			
		}
		if (!toucheCorps && comboBoxCorps.getValue() != null)
		{
			switch (comboBoxCorps.getValue())
			{
			case "Plan�te":
				creePlanete(pos);
				niveau.ajouterCorps(corpsSelect);
				objectifSelect = null;
				break;
			case "Vaisseau":
				creeVaisseau(pos);
				niveau.ajouterCorps(corpsSelect);
				objectifSelect = null;
				break;
			case "Portail":
				corpsSelect = null;
				objectifSelect = new ObjectifRayon(pos, 50.0);
				niveau.setObjectif(objectifSelect);
				break;
			case "Vaisseau Joueur":
				corpsSelect = vaisseauJoueur;
				objectifSelect = null;
				vaisseauJoueur.setPosition(pos);
			}
			chargerNiveau();
		}
		afficherMenuParametre();
	}
	
	/**
	 * m�thode qui g�re la cr�ation de plan�tes dans l'�diteur de niveaux.
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
		
		Planete p = new Planete(masse, pos, rayon);
		p.setTexture(comboBoxTexture.getValue());
		
		corpsSelect = p;
	}
	
	/**
	 * m�thode qui g�re la cr�ation de vaisseau dans l'�diteur de niveaux.
	 */
	private void creeVaisseau(Vecteur pos)
	{
		double masse;
		double carburantDepart;
		double carburantMax;
		
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
			carburantDepart = Double
					.valueOf(textFieldCarburantDepart.getText());
		}
		catch (NumberFormatException e)
		{
			carburantDepart = Vaisseau.CARBURANT_DEFAUT;
		}
		
		try
		{
			carburantMax = Double.valueOf(textFieldCarburantMax.getText());
		}
		catch (NumberFormatException e)
		{
			carburantMax = Vaisseau.CARBURANT_DEFAUT;
		}
		Vaisseau v = new Vaisseau(1, masse, carburantMax, carburantDepart, pos,
				new Vecteur());
		
		corpsSelect = v;
	}
	
	private void creeVaisseauJoueur(Vecteur pos)
	{
		double masse;
		double carburantDepart;
		double carburantMax;
		
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
			carburantDepart = Double
					.valueOf(textFieldCarburantDepart.getText());
		}
		catch (NumberFormatException e)
		{
			carburantDepart = Vaisseau.CARBURANT_DEFAUT;
		}
		
		try
		{
			carburantMax = Double.valueOf(textFieldCarburantMax.getText());
		}
		catch (NumberFormatException e)
		{
			carburantMax = Vaisseau.CARBURANT_DEFAUT;
		}
		// TODO ajouter la puissance et la vitesse de d�part dans l'�diteur
		VaisseauJoueur v = new VaisseauJoueur(1, masse, carburantMax,
				carburantDepart);
		
		corpsSelect = v;
	}
	
	/**
	 * Affiche le menu pour modifier les param�tres de l'objet s�lectionn�.
	 */
	private void afficherMenuParametre()
	{		
		vBoxMenu.setVisible(true);
		if (corpsSelect instanceof Planete)
		{
			vBoxMenu.setVisible(true);
			vBoxMenuPlanete.setVisible(true);
			vBoxMenuVaisseau.setVisible(false);
			textFieldRayon.setText("" + corpsSelect.getRayon());
			textFieldMasse.setText("" + corpsSelect.getMasse());
			textFieldPositionX.setText("" + corpsSelect.getPositionX());
			textFieldPositionY.setText("" + corpsSelect.getPositionY());
			comboBoxTexture.getSelectionModel().select(
					((Planete) corpsSelect).getTexture());
			comboBoxCorps.setValue("Plan�te");
		}
		else if (corpsSelect instanceof Vaisseau)
		{
			Vaisseau corpsSelect = (Vaisseau) this.corpsSelect;
			vBoxMenu.setVisible(true);
			vBoxMenuPlanete.setVisible(false);
			vBoxMenuVaisseau.setVisible(true);
			textFieldMasse.setText("" + corpsSelect.getMasse());
			textFieldPositionX.setText("" + corpsSelect.getPositionX());
			textFieldPositionY.setText("" + corpsSelect.getPositionY());
			textFieldCarburantMax.setText("" + corpsSelect.getCarburantMax());
			textFieldCarburantDepart.setText(""
					+ corpsSelect.getCarburantDepart());
			comboBoxCorps.setValue("Vaisseau");
			if (corpsSelect instanceof VaisseauJoueur)
				comboBoxCorps.setValue("Vaisseau Joueur");
		}
		else if (objectifSelect instanceof ObjectifRayon)
		{
			System.out.println("afassd");
			vBoxMenu.setVisible(false);
		}
		System.out.println(objectifSelect);
		System.out.println(corpsSelect);
	}
	
	private void mouseClickedSecondary(MouseEvent event, Vecteur pos)
	{
		// Inutile pour l'instant.
	}
	
	/**
	 * Ajoute tous les corps d'un niveau donn� dans la vue.
	 */
	private void chargerNiveau()
	{
		ContPrincipal.getInstance().viderCorps();
		for (Corps c : niveau.getCorps())
		{
			ContPrincipal.getInstance().ajouterCorps(c);
			if (c instanceof VaisseauJoueur)
				vaisseauJoueur = (VaisseauJoueur) c;
		}
		if (vaisseauJoueur == null)
		{
			corpsSelect = new VaisseauJoueur(VaisseauJoueur.PUISSANCE_DEFAUT,
					VaisseauJoueur.MASSE_DEFAUT,
					VaisseauJoueur.CARBURANT_DEFAUT,
					VaisseauJoueur.CARBURANT_DEFAUT);
			corpsSelect.setPosition(niveau.getPointDepart());
			niveau.ajouterCorps(corpsSelect);
			ContPrincipal.getInstance().ajouterCorps(corpsSelect);
			vaisseauJoueur = (VaisseauJoueur) corpsSelect;
		}
		objectif = niveau.getObjectif();
		vue.initialiserCorps();
		
		if (niveau.getObjectif() != null
				&& niveau.getObjectif() instanceof Dessinable)
		{
			vue.ajouterDessinable((Dessinable) niveau.getObjectif());
		}
	}
	
	// Cette m�thode ne sert pas encore.
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
