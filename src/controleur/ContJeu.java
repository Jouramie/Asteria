package controleur;

import modele.Corps;
import modele.Objectif;
import modele.Niveau;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import objets.VaisseauJoueur;
import utils.Vecteur;
import vue.Camera;
import vue.Dessinable;
import vue.VueJeu;

/**
 * Contr�leur utilis� lors d'une session de jeu.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class ContJeu implements Controleur
{
	public static final double VITESSE_ZOOM = 0.005;
	public static final double MARGE_ECRAN_HORIZ = 350;
	public static final double MARGE_ECRAN_VERT = 250;
	
	@FXML
	private Pane pane;
	@FXML
	private VBox menuPauseJeu;
	@FXML
	private VBox menuPauseEditeur;
	@FXML
	private VBox menuVictoireJeu;
	@FXML
	private VBox menuVictoireEditeur;
	@FXML
	private VBox menuMortJeu;
	@FXML
	private VBox menuMortEditeur;
	@FXML
	private ProgressBar progressBarCarburant;
	@FXML
	private ProgressBar progressBarSante;
	private boolean editeur;
	private VueJeu vue;
	private VaisseauJoueur vaisseauJoueur;
	private Niveau niveau;
	private boolean objectifAtteint;
	private boolean mort;
	private boolean menuAffiche;
	private boolean listenMouse;
	private int numeroNiveau;
	
	/**
	 * Constructeur du contr�leur.
	 */
	public ContJeu()
	{
		this(1);
	}
	
	/**
	 * Constructeur du contr�leur.
	 * 
	 * @param nouvNiveau Niveau � charger.
	 */
	public ContJeu(int nouvNumeroNiveau)
	{
		vue = new VueJeu();
		editeur = false;
		if(nouvNumeroNiveau >= 1 || nouvNumeroNiveau <= 10)
		{
			numeroNiveau = nouvNumeroNiveau;
		}
		else
		{
			numeroNiveau = 1;
		}
	}
	
	/**
	 * Constructeur du contr�leur.
	 * 
	 * @param niv Le niveau dans lequel commence le jeu.
	 * @param editeur Vrai si le jeu est lanc� depuis l'�diteur.
	 */
	public ContJeu(Niveau niv, boolean editeur)
	{
		vue = new VueJeu();
		numeroNiveau = 0;
		niveau = niv;
		this.editeur = editeur;
	}
	
	/**
	 * Initialise les �l�ments du jeu et affiche la vue. D�marre l'horloge du
	 * contr�leur principal.
	 */
	public void initialiser()
	{
		ContPrincipal.getInstance().afficherVue(vue, true);
		progressBarCarburant.setPrefWidth(((VBox) progressBarCarburant
				.getParent()).getWidth());
		progressBarSante.setPrefWidth(((VBox) progressBarSante.getParent())
				.getWidth());
		
		if(numeroNiveau > 0)
		{
			niveau = Niveau.chargerNiveau(this.getClass().getResourceAsStream(
					"/levels/level_" + numeroNiveau + ".txt"));
		}
		Platform.runLater(() -> {
			chargerNiveau(niveau);
		});
	}
	
	@FXML
	public void keyPressed(KeyEvent e)
	{
		if(!objectifAtteint && !mort && e.getCode() == KeyCode.ESCAPE)
		{
			if(!menuPauseJeu.isVisible() && !menuPauseEditeur.isVisible())
			{
				afficherMenuPause();
			}
			else
			{
				cacherMenuPause();
			}
		}
		
		else if(!listenMouse && !objectifAtteint && !mort && !menuAffiche)
		{
			switch(e.getCode())
			{
			case LEFT:
			case A:
				vaisseauJoueur.tournerGauche(true);
				break;
			case RIGHT:
			case D:
				vaisseauJoueur.tournerDroite(true);
				break;
			case UP:
			case W:
				vaisseauJoueur.avancer(true);
				break;
			case H:
				vaisseauJoueur.setCarburantRestant(vaisseauJoueur
						.getCarburantMax());
				vaisseauJoueur.setSante(1);
			default:
				break;
			}
		}
	}
	
	@FXML
	public void keyReleased(KeyEvent e)
	{
		if(!listenMouse)
		{
			switch(e.getCode())
			{
			case LEFT:
			case A:
				vaisseauJoueur.tournerGauche(false);
				break;
			case RIGHT:
			case D:
				vaisseauJoueur.tournerDroite(false);
				break;
			case UP:
			case W:
				vaisseauJoueur.avancer(false);
				break;
			default:
				break;
			}
		}
	}
	
	public void afficherMenuPause()
	{
		if(!objectifAtteint && !mort)
		{
			ContPrincipal.getInstance().arreterHorloge();
			vaisseauJoueur.avancer(false);
			vaisseauJoueur.tournerDroite(false);
			vaisseauJoueur.tournerGauche(false);
			menuPauseJeu.setVisible(!editeur);
			menuPauseEditeur.setVisible(editeur);
			menuPauseJeu.setMinWidth(pane.getWidth());
			menuPauseEditeur.setMinWidth(pane.getWidth());
			menuPauseJeu.setMinHeight(pane.getHeight());
			menuPauseEditeur.setMinHeight(pane.getHeight());
			
			menuPauseJeu.toFront();
			menuPauseEditeur.toFront();
			menuAffiche = true;
		}
	}
	
	public void cacherMenuPause()
	{
		if(!listenMouse)
		{
			ContPrincipal.getInstance().demarrerHorloge();
		}
		menuPauseJeu.setVisible(false);
		menuPauseEditeur.setVisible(false);
		menuAffiche = false;
	}
	
	public void afficherMenuMort()
	{
		ContPrincipal.getInstance().arreterHorloge();
		vaisseauJoueur.avancer(false);
		vaisseauJoueur.tournerDroite(false);
		vaisseauJoueur.tournerGauche(false);
		if(!vaisseauJoueur.isAnimationMort())
		{
			menuMortJeu.setVisible(!editeur);
			menuMortEditeur.setVisible(editeur);
			menuMortJeu.setMinWidth(pane.getWidth());
			menuMortEditeur.setMinWidth(pane.getWidth());
			menuMortJeu.setMinHeight(pane.getHeight());
			menuMortEditeur.setMinHeight(pane.getHeight());
			menuMortJeu.toFront();
			menuMortEditeur.toFront();
			menuAffiche = true;
		}
	}
	
	public void cacherMenuMort()
	{
		if(!listenMouse)
		{
			ContPrincipal.getInstance().demarrerHorloge();
		}
		menuMortJeu.setVisible(false);
		menuMortEditeur.setVisible(false);
		menuAffiche = false;
	}
	
	/**
	 * Affiche l'�cran de victoire.
	 */
	public void afficherMenuVictoire()
	{
		vaisseauJoueur.avancer(false);
		vaisseauJoueur.tournerDroite(false);
		vaisseauJoueur.tournerGauche(false);
		
		if(numeroNiveau < 10)
		{
			ContPrincipal.getInstance().arreterHorloge();
			menuVictoireJeu.setVisible(!editeur);
			menuVictoireEditeur.setVisible(editeur);
			menuVictoireJeu.setMinWidth(pane.getWidth());
			menuVictoireEditeur.setMinWidth(pane.getWidth());
			menuVictoireJeu.setMinHeight(pane.getHeight());
			menuVictoireEditeur.setMinHeight(pane.getHeight());
			menuVictoireJeu.toFront();
			menuVictoireEditeur.toFront();
			menuAffiche = true;
		}
		else
		{
			ContPrincipal.getInstance().viderCorps();
			ContPrincipal.getInstance().selectionnerControleur(
					new ContVictoire());
		}
	}
	
	public void cacherMenuVictoire()
	{
		if(!listenMouse)
		{
			ContPrincipal.getInstance().demarrerHorloge();
		}
		menuVictoireJeu.setVisible(false);
		menuVictoireEditeur.setVisible(false);
		menuAffiche = false;
	}
	
	/**
	 * Charge un niveau de jeu.
	 * 
	 * @param niv Niveau � charger.
	 */
	public void chargerNiveau(Niveau niv)
	{
		if(niv != null)
		{
			niveau = niv;
			objectifAtteint = false;
			mort = false;
			
			// Vide puis ajoute tous les corps du niveau dans le contr�leur
			// principal.
			ContPrincipal.getInstance().viderCorps();
			
			for(Corps c: niveau.getCorps())
			{
				ContPrincipal.getInstance().ajouterCorps(c);
				if(c instanceof VaisseauJoueur)
				{
					vaisseauJoueur = (VaisseauJoueur) c;
				}
			}
			
			// Initialise le vaisseau du joueur si le niveau n'en d�finit pas
			// un.
			if(vaisseauJoueur == null)
			{
				vaisseauJoueur = new VaisseauJoueur(1, 16e3, 30, 30);
				ContPrincipal.getInstance().ajouterCorps(vaisseauJoueur);
			}
			vaisseauJoueur.setPosition(niveau.getPointDepart());
			vue.getCamera().deplacer(vaisseauJoueur.getPositionX(),
					vaisseauJoueur.getPositionY());
			vue.getCamera().zoomer(1.0);
			vue.getCamera().update(0.1);
			
			// Ajoute les corps dans le JavaFX.
			vue.initialiserCorps();
			
			// Bind les barres de carburant et de sant�.
			progressBarCarburant.progressProperty().unbind();
			progressBarCarburant.progressProperty().bind(
					vaisseauJoueur.carburantRestantProperty().divide(
							vaisseauJoueur.carburantMaxProperty()));
			progressBarSante.progressProperty().unbind();
			progressBarSante.progressProperty().bind(
					vaisseauJoueur.santeProperty());
			
			// Ajoute l'objectif du tableau.
			Objectif objectif = niveau.getObjectif();
			
			if(objectif != null)
			{
				objectif.setVaisseau(vaisseauJoueur);
				if(objectif instanceof Dessinable)
				{
					vue.ajouterDessinable((Dessinable) objectif);
				}
			}
			
			// Commence � �couter la souris pour la vitesse de d�part.
			ContPrincipal.getInstance().arreterHorloge();
			listenMouse = true;
		}
		else if(niveau == null)
		{
			ContPrincipal.getInstance().selectionnerControleur(
					new ContSelectionNiveau());
		}
	}
	
	/**
	 * Recharge le niveau.
	 */
	public void reset()
	{
		for(Corps c: ContPrincipal.getInstance().getCorps())
		{
			c.reset();
		}
		vaisseauJoueur = null;
		chargerNiveau(niveau);
	}
	
	/**
	 * �couteur lors de la s�lection de la vitesse initiale.
	 * 
	 * @param e
	 */
	@FXML
	public void mouseMove(MouseEvent e)
	{
		if(listenMouse && !menuAffiche)
		{
			Point2D point = pane.sceneToLocal(e.getSceneX(), e.getSceneY());
			Camera cam = vue.getCamera();
			Vecteur pos = cam.localToGlobal(new Vecteur(point.getX(), point
					.getY()));
			Vecteur sub = pos.soustraire(vaisseauJoueur.getPosition());
			vaisseauJoueur.setAngle(sub.getAngle());
		}
	}
	
	/**
	 * �couteur lors de la s�lection de la vitesse initiale.
	 */
	@FXML
	public void mouseClicked(MouseEvent e)
	{
		if(listenMouse && !menuAffiche)
		{
			Point2D point = pane.sceneToLocal(e.getSceneX(), e.getSceneY());
			Camera cam = vue.getCamera();
			Vecteur pos = cam.localToGlobal(new Vecteur(point.getX(), point
					.getY()));
			Vecteur sub = pos.soustraire(vaisseauJoueur.getPosition());
			vaisseauJoueur.setVitesse(sub.normaliser().multiplication(
					niveau.getVitesseDepart()));
			
			listenMouse = false;
			
			ContPrincipal.getInstance().demarrerHorloge();
		}
	}
	
	@FXML
	public void recommencer(ActionEvent e)
	{
		retourjeu(e);
		reset();
	}
	
	@FXML
	public void niveauSuivant()
	{
		numeroNiveau++;
		vaisseauJoueur = null;
		Niveau niv = Niveau.chargerNiveau(this.getClass().getResourceAsStream(
				"/levels/level_" + numeroNiveau + ".txt"));
		Platform.runLater(() -> {
			chargerNiveau(niv);
		});
		cacherMenuVictoire();
	}
	
	@FXML
	public void editeur()
	{
		ContPrincipal.getInstance().viderCorps();
		for(Corps c: niveau.getCorps())
		{
			c.reset();
		}
		vaisseauJoueur = null;
		ContPrincipal.getInstance().selectionnerControleur(
				new ContEditeur(niveau));
		
	}
	
	@FXML
	public void retour()
	{
		ContPrincipal.getInstance().viderCorps();
		ContPrincipal.getInstance().selectionnerControleur(new ContMenu());
	}
	
	@FXML
	public void retourjeu(ActionEvent e)
	{
		cacherMenuPause();
		cacherMenuVictoire();
		cacherMenuMort();
		vaisseauJoueur.setSante(1.0);
		objectifAtteint = false;
		mort = false;
	}
	
	/**
	 * �couteur sur le mouse wheel pour contr�ler le zoom de la cam�ra.
	 */
	@FXML
	public void zoom(ScrollEvent e)
	{
		if(!objectifAtteint && !mort)
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
	
	/**
	 * Met � jour la cam�ra et v�rifie l'objectif.
	 */
	public void update(double dt)
	{
		if(vaisseauJoueur != null)
		{
			// Met � jour le vaisseau et v�rifie l'objectif.
			vaisseauJoueur.miseAJourPhysique(dt);
			verifierObjectif();
			
			// =========================
			// Mise � jour de la cam�ra.
			// =========================
			
			// Prend les donn�es actuelles.
			Camera camera = vue.getCamera();
			double x = camera.getDeplacement().getX();
			double y = camera.getDeplacement().getY();
			
			// Calcul les marges horizontales de l'�cran.
			double marginLeft = camera.localToGlobal(
					new Vecteur(MARGE_ECRAN_HORIZ, 0)).getX();
			double marginRight = camera.localToGlobal(
					new Vecteur(pane.getWidth() - MARGE_ECRAN_HORIZ, 0)).getX();
			
			// V�rifie les marges � gauche et � droite et ajuste la cam�ra.
			if(vaisseauJoueur.getPositionX() < marginLeft)
			{
				camera.deplacer(x
						- (marginLeft - vaisseauJoueur.getPositionX()), y);
			}
			
			else if(vaisseauJoueur.getPositionX() > marginRight)
			{
				camera.deplacer(x
						+ (vaisseauJoueur.getPositionX() - marginRight), y);
			}
			
			x = camera.getDeplacement().getX();
			y = camera.getDeplacement().getY();
			
			// Calcul les marges verticales de l'�cran.
			double marginTop = camera.localToGlobal(
					new Vecteur(0, MARGE_ECRAN_VERT)).getY();
			double marginBottom = camera.localToGlobal(
					new Vecteur(0, pane.getHeight() - MARGE_ECRAN_VERT)).getY();
			
			// V�rifie les marges en haut et en bas et ajuste la cam�ra.
			if(vaisseauJoueur.getPositionY() < marginTop)
			{
				camera.deplacer(x,
						y - (marginTop - vaisseauJoueur.getPositionY()));
			}
			
			else if(vaisseauJoueur.getPositionY() > marginBottom)
			{
				camera.deplacer(x, y
						+ (vaisseauJoueur.getPositionY() - marginBottom));
			}
		}
	}
	
	/**
	 * V�rifie si l'objectif actuel est atteint et v�rifie si le joueur est
	 * mort.
	 */
	private void verifierObjectif()
	{
		if(niveau != null && !objectifAtteint)
		{
			if(niveau.getObjectif() != null
					&& niveau.getObjectif().verifierObjectif())
			{
				objectifAtteint = true;
				afficherMenuVictoire();
			}
			
			if(vaisseauJoueur.getSante() == 0.0)
			{
				if(!mort)
				{
					mort = true;
					vaisseauJoueur.jouerAnimationMort();
				}
				
				afficherMenuMort();
			}
		}
	}
}
