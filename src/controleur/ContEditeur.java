package controleur;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import objets.ObjetSpatial;
import objets.Planete;
import objets.Planete.Texture;
import objets.Vaisseau;
import objets.VaisseauJoueur;
import utils.Vecteur;
import vue.Camera;
import vue.Dessinable;
import vue.VueEditeur;

/**
 * Contrôleur pour le createur de niveaux
 * 
 * @author Jonathan Samson
 * @author Jérémie Bolduc
 * @version 1.0
 */
public class ContEditeur implements Controleur
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
	private ComboBox<String> comboBoxCorps;
	@FXML
	private ComboBox<Texture> comboBoxTexture;
	@FXML
	private ColorPicker colorPickerCouleurAtmosphere;
	@FXML
	private TextField textFieldMasse;
	@FXML
	private TextField textFieldPositionX;
	@FXML
	private TextField textFieldPositionY;
	@FXML
	private TextField textFieldRayon;
	@FXML
	private TextField textFieldRayonObjectif;
	@FXML
	private TextField textFieldCarburantMax;
	@FXML
	private TextField textFieldCarburantDepart;
	@FXML
	private TextField textFieldPuissance;
	@FXML
	private TextField textFieldVitesseDepart;
	@FXML
	private TextField textFieldOrientation;
	@FXML
	private TextField textFieldRayonAtmosphere;
	@FXML
	private VBox vBoxMenu;
	@FXML
	private VBox vBoxMenuCorps;
	@FXML
	private VBox vBoxMenuPlanete;
	@FXML
	private VBox vBoxMenuVaisseau;
	@FXML
	private VBox vBoxMenuVaisseauJoueur;
	@FXML
	private VBox vBoxMenuVaisseauNonJoueur;
	@FXML
	private VBox vBoxMenuObjectif;
	
	private VueEditeur vue;
	private Corps corpsSelect;
	private Objectif objectifSelect;
	private VaisseauJoueur vaisseauJoueur;
	private Objectif objectif;
	private Niveau niveau;
	
	/**
	 * Constructeur du contrôleur.
	 */
	public ContEditeur()
	{
		this(null);
	}
	
	/**
	 * Constructeur du contrôleur avec un niveau en paramètre.
	 */
	public ContEditeur(Niveau niv)
	{
		vue = new VueEditeur();
		setNiveau(niv);
	}
	
	/**
	 * Initialise et affiche la vue de l'éditeur.
	 */
	public void initialiser()
	{
		// Affiche la vue et supprime tous les corps actuels.
		ContPrincipal.getInstance().afficherVue(vue, true);
		ContPrincipal.getInstance().arreterHorloge();
		
		// Initialisation des combo box.
		comboBoxCorps.getItems().addAll("Vaisseau", "Planète", "Portail",
				"Vaisseau Joueur");
		initialiserComboBoxTexture();
		vBoxMenu.setVisible(false);
		
		// Initialisation du niveau.
		vaisseauJoueur = null;
		objectif = null;
		if(niveau == null)
		{
			niveau = new Niveau();
		}
		Platform.runLater(() -> {
			chargerNiveau();
			vue.getCamera().deplacer(vaisseauJoueur.getPositionX(),
					vaisseauJoueur.getPositionY());
			vue.getCamera().zoomer(1.0);
		});
	}
	
	// ===============================================
	// Écouteurs des différents text field et boutons.
	// ===============================================
	@FXML
	public void onRayon(ActionEvent e)
	{
		if(corpsSelect instanceof Planete)
		{
			try
			{
				((Planete) corpsSelect).setRayon((Double.valueOf(textFieldRayon
						.getText())));
				((Planete) corpsSelect).getNoeud();
			}
			catch(NumberFormatException ex)
			{
				textFieldRayon.setText("" + corpsSelect.getRayon());
			}
		}
		if(corpsSelect instanceof Dessinable)
			((Dessinable) corpsSelect).creeNoeud();
	}
	
	@FXML
	public void onRayonAtmosphere(ActionEvent e)
	{
		if(corpsSelect instanceof Planete)
		{
			Planete p = (Planete) corpsSelect;
			try
			{
				p.setRayonAtmosphere(Double.valueOf(textFieldRayonAtmosphere
						.getText()));
				p.getNoeud();
			}
			catch(NumberFormatException ex)
			{
				textFieldRayonAtmosphere.setText("" + p.getRayonAtmosphere());
			}
		}
		if(corpsSelect instanceof Dessinable)
			((Dessinable) corpsSelect).creeNoeud();
	}
	
	@FXML
	public void onCouleurAtmosphere(ActionEvent e)
	{
		if(corpsSelect instanceof Planete)
		{
			Planete p = (Planete) corpsSelect;
			p.setCouleurAtmosphere(colorPickerCouleurAtmosphere.getValue());
			p.getNoeud();
		}
		if(corpsSelect instanceof Dessinable)
			((Dessinable) corpsSelect).creeNoeud();
	}
	
	@FXML
	public void onRayonObjectif(ActionEvent e)
	{
		if(objectifSelect instanceof ObjectifRayon)
		{
			ObjectifRayon or = (ObjectifRayon) objectifSelect;
			try
			{
				or.setRayon((Double.valueOf(textFieldRayonObjectif.getText())));
				or.getNoeud();
			}
			catch(NumberFormatException ex)
			{
				textFieldRayonObjectif.setText("" + or.getRayon());
			}
		}
		if(objectifSelect instanceof Dessinable)
			((Dessinable) objectifSelect).creeNoeud();
	}
	
	@FXML
	public void onMasse(ActionEvent e)
	{
		try
		{
			corpsSelect.setMasse((Double.valueOf(textFieldMasse.getText())));
		}
		catch(NumberFormatException ex)
		{
			textFieldMasse.setText("" + corpsSelect.getMasse());
		}
	}
	
	@FXML
	public void onPositionX(ActionEvent e)
	{
		if(corpsSelect instanceof ObjetSpatial)
		{
			ObjetSpatial obj = (ObjetSpatial) corpsSelect;
			try
			{
				obj.setPositionX(
						(Double.valueOf(textFieldPositionX.getText())), true);
				
				if(obj instanceof VaisseauJoueur)
				{
					niveau.setPointDepart(new Vecteur(Double
							.valueOf(textFieldPositionX.getText()), niveau
							.getPointDepart().getY()));
				}
			}
			catch(NumberFormatException ex)
			{
				textFieldPositionX.setText("" + obj.getPositionX());
			}
		}
		else if(objectifSelect instanceof ObjectifRayon)
		{
			ObjectifRayon or = (ObjectifRayon) objectifSelect;
			try
			{
				or.getPosRayon().setX(
						(Double.valueOf(textFieldPositionX.getText())));
			}
			catch(NumberFormatException ex)
			{
				textFieldPositionX.setText("" + or.getPosRayon().getX());
			}
		}
		if(corpsSelect instanceof Dessinable)
			((Dessinable) corpsSelect).creeNoeud();
		if(objectifSelect instanceof Dessinable)
			((Dessinable) objectifSelect).creeNoeud();
	}
	
	@FXML
	public void onPositionY(ActionEvent e)
	{
		if(corpsSelect instanceof ObjetSpatial)
		{
			ObjetSpatial obj = (ObjetSpatial) corpsSelect;
			
			try
			{
				obj.setPositionY(
						(Double.valueOf(textFieldPositionY.getText())), true);
				
				if(obj instanceof VaisseauJoueur)
				{
					niveau.setPointDepart(new Vecteur(niveau.getPointDepart()
							.getX(), Double.valueOf(textFieldPositionY
							.getText())));
				}
			}
			catch(NumberFormatException ex)
			{
				textFieldPositionY.setText("" + obj.getPositionY());
			}
		}
		else if(objectifSelect instanceof ObjectifRayon)
		{
			ObjectifRayon or = (ObjectifRayon) objectifSelect;
			try
			{
				or.getPosRayon().setY(
						(Double.valueOf(textFieldPositionY.getText())));
			}
			catch(NumberFormatException ex)
			{
				textFieldPositionY.setText("" + or.getPosRayon().getY());
			}
		}
		if(corpsSelect instanceof Dessinable)
			((Dessinable) corpsSelect).creeNoeud();
		if(objectifSelect instanceof Dessinable)
			((Dessinable) objectifSelect).creeNoeud();
	}
	
	@FXML
	public void onSupprimer()
	{
		niveau.supprimerCorps(corpsSelect);
		corpsSelect = null;
		vBoxMenu.setVisible(false);
		chargerNiveau();
	}
	
	@FXML
	public void onTexture(ActionEvent e)
	{
		if(corpsSelect != null)
		{
			((Planete) corpsSelect).setTexture(comboBoxTexture.getValue());
		}
		if(corpsSelect instanceof Dessinable)
			((Dessinable) corpsSelect).creeNoeud();
	}
	
	@FXML
	public void onCarburantMax(ActionEvent e)
	{
		try
		{
			VaisseauJoueur corpsSelect = (VaisseauJoueur) this.corpsSelect;
			corpsSelect.setCarburantMax((Double.valueOf(textFieldCarburantMax
					.getText())));
			textFieldCarburantDepart.setText(""
					+ corpsSelect.getCarburantDepart());
		}
		catch(NumberFormatException ex)
		{
			textFieldCarburantMax.setText(""
					+ ((VaisseauJoueur) corpsSelect).getCarburantMax());
		}
	}
	
	@FXML
	public void onCarburantDepart(ActionEvent e)
	{
		try
		{
			VaisseauJoueur corpsSelect = (VaisseauJoueur) this.corpsSelect;
			corpsSelect.setCarburantDepart((Double
					.valueOf(textFieldCarburantDepart.getText())));
			textFieldCarburantMax.setText("" + corpsSelect.getCarburantMax());
		}
		catch(NumberFormatException ex)
		{
			textFieldCarburantDepart.setText(""
					+ ((VaisseauJoueur) corpsSelect).getCarburantDepart());
		}
	}
	
	@FXML
	public void onPuissance(ActionEvent e)
	{
		try
		{
			VaisseauJoueur corpsSelect = (VaisseauJoueur) this.corpsSelect;
			corpsSelect.setPuissance((Double.valueOf(textFieldPuissance
					.getText())));
		}
		catch(NumberFormatException ex)
		{
			textFieldPuissance.setText(""
					+ ((VaisseauJoueur) corpsSelect).getPuissance());
		}
	}
	
	@FXML
	public void onVitesseDepart(ActionEvent e)
	{
		if(corpsSelect instanceof VaisseauJoueur)
			try
			{
				niveau.setVitesseDepart((Double.valueOf(textFieldVitesseDepart
						.getText())));
			}
			catch(NumberFormatException ex)
			{
				textFieldVitesseDepart.setText("" + niveau.getVitesseDepart());
			}
		else
			try
			{
				Vaisseau corpsSelect = (Vaisseau) this.corpsSelect;
				corpsSelect.getVitesse().setGrandeur(
						(Double.valueOf(textFieldVitesseDepart.getText())));
				corpsSelect.setVitesse(corpsSelect.getVitesse(), true);
			}
			catch(NumberFormatException ex)
			{
				textFieldVitesseDepart.setText(""
						+ corpsSelect.getVitesse().getNorme());
			}
	}
	
	@FXML
	public void onOrientation(ActionEvent e)
	{
		try
		{
			Vaisseau corpsSelect = (Vaisseau) this.corpsSelect;
			corpsSelect.getVitesse().setAngle(
					(Double.valueOf(textFieldOrientation.getText())) / 360 * 2
							* Math.PI);
			corpsSelect.setVitesse(corpsSelect.getVitesse(), true);
		}
		catch(NumberFormatException ex)
		{
			textFieldOrientation.setText(""
					+ corpsSelect.getVitesse().getAngle() / 2 / Math.PI * 360);
		}
	}
	
	@FXML
	public void retour()
	{
		ContPrincipal.getInstance().selectionnerControleur(new ContMenu());
	}
	
	@FXML
	public void onEssayer()
	{
		ContPrincipal.getInstance().selectionnerControleur(
				new ContJeu(niveau, true));
	}
	
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
				JOptionPane.showMessageDialog(null,
						"L'emplacement choisi ne peut pas être modifié!",
						"Erreur", JOptionPane.ERROR_MESSAGE);
				file = (new FileChooser()).showSaveDialog(null);
			}
			
			niveau.sauvegarderNiveau(file);
		}
		catch(Exception e)
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
			
			while(!file.canRead())
			{
				JOptionPane.showMessageDialog(null,
						"L'emplacement choisi ne peut pas être lu!", "Erreur",
						JOptionPane.ERROR_MESSAGE);
				file = (new FileChooser()).showOpenDialog(null);
			}
			
			niveau = Niveau.chargerNiveau(new FileInputStream(file));
			
			chargerNiveau();
			vBoxMenu.setVisible(false);
			vue.getCamera().deplacer(vaisseauJoueur.getPositionX(),
					vaisseauJoueur.getPositionY());
			vue.getCamera().zoomer(1.0);
		}
		catch(Exception e)
		{
		}
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
		niveau.ajouterCorps(vaisseauJoueur);
		chargerNiveau();
	}
	
	/**
	 * Met à jour le contrôleur. Inutile pour l'instant.
	 */
	public void update(double dt)
	{
		
	}
	
	/**
	 * Initialise le combo box de texture. Obligatoire, sinon les textures
	 * disparaissent si on ne fait que les définir dans le FXML.
	 */
	private void initialiserComboBoxTexture()
	{
		comboBoxTexture.getItems().addAll(Texture.BLEUE, Texture.JAUNE,
				Texture.MAGENTA, Texture.ORANGE, Texture.ROUGE, Texture.VERTE);
		
		comboBoxTexture
				.setCellFactory(new Callback<ListView<Texture>, ListCell<Texture>>()
				{
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
								
								if(item == null || empty)
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
	 * Gère les clicks de la souris.
	 */
	@FXML
	public void mouseClicked(MouseEvent event)
	{
		Point2D point = pane.sceneToLocal(event.getSceneX(), event.getSceneY());
		Camera cam = vue.getCamera();
		Vecteur pos = cam
				.localToGlobal(new Vecteur(point.getX(), point.getY()));
		
		switch(event.getButton())
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
	 * Gère le clique gauche.
	 */
	private void mouseClickedPrimary(MouseEvent event, Vecteur pos)
	{
		boolean toucheCorps = false;
		corpsSelect = null;
		
		// Vérifie si un corps est touché.
		for(Corps c: ContPrincipal.getInstance().getCorps())
		{
			if(Math.abs(c.getPosition().getX() - pos.getX()) < c.getRayon()
					&& Math.abs(c.getPosition().getY() - pos.getY()) < c
							.getRayon())
			{
				toucheCorps = true;
				corpsSelect = c;
				break;
			}
		}
		
		if(!toucheCorps)
		{
			if(objectif instanceof ObjectifRayon)
			{
				ObjectifRayon or = (ObjectifRayon) objectif;
				if(Math.abs(or.getPosRayon().getX() - pos.getX()) < or
						.getRayon()
						&& Math.abs(or.getPosRayon().getY() - pos.getY()) < (or
								.getRayon()))
				{
					toucheCorps = true;
					objectifSelect = objectif;
				}
				
			}
		}
		
		// Ajoute le corps voulu.
		if(!toucheCorps && comboBoxCorps.getValue() != null)
		{
			switch(comboBoxCorps.getValue())
			{
			case "Planète":
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
				objectifSelect = niveau.getObjectif();
				((ObjectifRayon) objectifSelect).setPosRayon(pos);
				break;
			case "Vaisseau Joueur":
				corpsSelect = vaisseauJoueur;
				objectifSelect = null;
				vaisseauJoueur.setPosition(pos);
				niveau.setPointDepart(pos);
			}
			chargerNiveau();
		}
		afficherMenuParametre();
	}
	
	/**
	 * Gère la création de planètes dans l'éditeur de niveaux.
	 */
	private void creePlanete(Vecteur pos)
	{
		Planete p = new Planete(Planete.PLANETE_MASSE_DEFAUT, pos,
				Planete.RAYON_DEFAUT, Planete.RAYON_ATMOSPHERE_DEFAUT,
				Planete.COULEUR_ATMOSHPERE_DEFAUT);
		p.setTexture(Planete.TEXTURE_DEFAUT);
		corpsSelect = p;
	}
	
	/**
	 * Gère la création de vaisseau dans l'éditeur de niveaux.
	 */
	private void creeVaisseau(Vecteur pos)
	{
		Vaisseau v = new Vaisseau(Vaisseau.MASSE_DEFAUT, pos, new Vecteur());
		corpsSelect = v;
	}
	
	/**
	 * Affiche le menu pour modifier les paramètres de l'objet sélectionné.
	 */
	private void afficherMenuParametre()
	{
		if(corpsSelect == null && objectifSelect == null)
		{
			return;
		}
		vBoxMenu.setVisible(true);
		if(corpsSelect instanceof Planete)
		{
			Planete corpsSelect = (Planete) this.corpsSelect;
			vBoxMenuCorps.setVisible(true);
			vBoxMenuObjectif.setVisible(false);
			vBoxMenuPlanete.setVisible(true);
			vBoxMenuVaisseau.setVisible(false);
			textFieldRayon.setText("" + corpsSelect.getRayon());
			textFieldMasse.setText("" + corpsSelect.getMasse());
			textFieldPositionX.setText("" + corpsSelect.getPositionX());
			textFieldPositionY.setText("" + corpsSelect.getPositionY());
			textFieldRayonAtmosphere.setText(""
					+ corpsSelect.getRayonAtmosphere());
			colorPickerCouleurAtmosphere.setValue(corpsSelect
					.getCouleurAtmosphere());
			comboBoxTexture.getSelectionModel().select(
					((Planete) corpsSelect).getTexture());
			comboBoxCorps.setValue("Planète");
		}
		
		else if(corpsSelect instanceof Vaisseau)
		{
			Vaisseau corpsSelect = (Vaisseau) this.corpsSelect;
			vBoxMenuCorps.setVisible(true);
			vBoxMenuObjectif.setVisible(false);
			vBoxMenuPlanete.setVisible(false);
			vBoxMenuVaisseau.setVisible(true);
			textFieldMasse.setText("" + corpsSelect.getMasse());
			textFieldPositionX.setText("" + corpsSelect.getPositionX());
			textFieldPositionY.setText("" + corpsSelect.getPositionY());
			if(corpsSelect instanceof VaisseauJoueur)
			{
				VaisseauJoueur vj = (VaisseauJoueur) corpsSelect;
				vBoxMenuVaisseauJoueur.setVisible(true);
				vBoxMenuVaisseauNonJoueur.setVisible(false);
				textFieldCarburantMax.setText("" + vj.getCarburantMax());
				textFieldCarburantDepart.setText("" + vj.getCarburantDepart());
				textFieldPuissance.setText("" + vj.getPuissance());
				textFieldVitesseDepart.setText("" + niveau.getVitesseDepart());
				comboBoxCorps.setValue("Vaisseau Joueur");
			}
			else
			{
				vBoxMenuVaisseauJoueur.setVisible(false);
				vBoxMenuVaisseauNonJoueur.setVisible(true);
				textFieldVitesseDepart.setText(""
						+ corpsSelect.getVitesse().getNorme());
				textFieldOrientation.setText(""
						+ corpsSelect.getVitesse().getAngle() / 2 / Math.PI
						* 360);
				comboBoxCorps.setValue("Vaisseau");
			}
		}
		
		else if(objectifSelect instanceof ObjectifRayon)
		{
			ObjectifRayon or = (ObjectifRayon) objectif;
			vBoxMenuCorps.setVisible(false);
			vBoxMenuObjectif.setVisible(true);
			textFieldPositionX.setText("" + or.getPosRayon().getX());
			textFieldPositionY.setText("" + or.getPosRayon().getY());
			textFieldRayonObjectif.setText("" + or.getRayon());
			comboBoxCorps.setValue("Portail");
		}
	}
	
	/**
	 * Gère le click droit de la souris.
	 */
	private void mouseClickedSecondary(MouseEvent event, Vecteur pos)
	{
		vue.getCamera().deplacer(pos.getX(), pos.getY());
	}
	
	/**
	 * Retourne le niveau de l'éditeur.
	 * 
	 * @return Niveau de l'éditeur.
	 */
	public Niveau getNiveau()
	{
		return niveau;
	}
	
	/**
	 * Modifie le niveau de l'éditeur
	 * 
	 * @param nouvNiveau Nouveau niveau de l'éditeur.
	 */
	public void setNiveau(Niveau nouvNiveau)
	{
		if(nouvNiveau != null)
		{
			niveau = nouvNiveau;
		}
	}
	
	/**
	 * Ajoute tous les corps d'un niveau donné dans la vue.
	 */
	private void chargerNiveau()
	{
		// Vide tous les corps.
		ContPrincipal.getInstance().viderCorps();
		
		// Ajoute les corps un à un.
		for(Corps c: niveau.getCorps())
		{
			ContPrincipal.getInstance().ajouterCorps(c);
			if(c instanceof VaisseauJoueur)
			{
				vaisseauJoueur = (VaisseauJoueur) c;
			}
		}
		
		// Si aucun vaisseau joueur n'est défini, on en ajoute un.
		if(vaisseauJoueur == null)
		{
			vaisseauJoueur = new VaisseauJoueur(
					VaisseauJoueur.PUISSANCE_DEFAUT,
					VaisseauJoueur.MASSE_DEFAUT,
					VaisseauJoueur.CARBURANT_DEFAUT,
					VaisseauJoueur.CARBURANT_DEFAUT);
			niveau.ajouterCorps(vaisseauJoueur);
			ContPrincipal.getInstance().ajouterCorps(vaisseauJoueur);
		}
		vaisseauJoueur.setPosition(niveau.getPointDepart());
		
		// S'il n'y a pas d'objectif, on en ajoute un.
		if(niveau.getObjectif() == null)
		{
			niveau.setObjectif(new ObjectifRayon(new Vecteur(0, 10),
					ObjectifRayon.RAYON_DEFAUT));
		}
		
		// On ajoute les corps dans la scène.
		vue.initialiserCorps();
		
		// On ajoute l'objectif.
		objectif = niveau.getObjectif();
		if(niveau.getObjectif() != null
				&& niveau.getObjectif() instanceof Dessinable)
		{
			vue.ajouterDessinable((Dessinable) niveau.getObjectif());
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
