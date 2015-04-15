package controleur;

import javax.swing.JOptionPane;
import modele.Corps;
import modele.Objectif;
import modele.Niveau;
import javafx.application.Platform;
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
	
	@FXML
	private Pane pane;
	@FXML
	private VBox menuPause;
	@FXML
	private VBox menuVictoire;
	@FXML
	private VBox menuMort;
	@FXML
	private ProgressBar progressBarCarburant;
	@FXML
	private ProgressBar progressBarSante;
	
	private VueJeu vue;
	private VaisseauJoueur vaisseauJoueur;
	private Niveau niveau;
	private boolean objectifAtteint;
	private boolean mort;
	private boolean menuAffiche;
	private boolean aPressed;
	private boolean dPressed;
	private boolean wPressed;
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
	 * @param nouvNiveau Niveau � charger.
	 */
	public ContJeu(int nouvNumeroNiveau)
	{
		vue = new VueJeu();
		
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
	 * Initialise les �l�ments du jeu et affiche la vue. D�marre l'horloge du
	 * contr�leur principal.
	 */
	public void initialiser()
	{
		ContPrincipal.getInstance().afficherVue(vue, true);
		progressBarCarburant.setPrefWidth(((VBox)progressBarCarburant.getParent()).getWidth());
		progressBarSante.setPrefWidth(((VBox)progressBarSante.getParent()).getWidth());
		
		Niveau niv = Niveau.chargerNiveau(this.getClass().getResourceAsStream("/levels/level_" + numeroNiveau + ".txt"));
		Platform.runLater(() -> {
			chargerNiveau(niv);
		});
	}
	
	@FXML
	public void keyPressed(KeyEvent e)
	{
		if(!listenMouse && !objectifAtteint && !mort && e.getCode() == KeyCode.ESCAPE)
		{
			if (!menuPause.isVisible())
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
			switch (e.getCode())
			{
			case LEFT:
			case A:
				if (!aPressed)
				{
					vaisseauJoueur.tournerGauche();
					aPressed = true;
				}
				break;
			case RIGHT:
			case D:
				if (!dPressed)
				{
					vaisseauJoueur.tournerDroite();
					dPressed = true;
				}
				break;
			case UP:
			case W:
				if (!wPressed)
				{
					vaisseauJoueur.avancer();
					wPressed = true;
				}
				break;
			case H:
				vaisseauJoueur
						.setCarburantRestant(vaisseauJoueur.getCarburantMax());
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
			switch (e.getCode())
			{
			case LEFT:
			case A:
				if (aPressed)
				{
					vaisseauJoueur.tournerGauche();
					aPressed = false;
				}
				break;
			case RIGHT:
			case D:
				if (dPressed)
				{
					vaisseauJoueur.tournerDroite();
					dPressed = false;
				}
				break;
			case UP:
			case W:
				if (wPressed)
				{
					vaisseauJoueur.avancer();
					wPressed = false;
				}
				break;
			default:
				break;
			}
		}
	}
	
	public void afficherMenuPause()
	{
		if (!objectifAtteint && !mort)
		{
			wPressed = true;
			ContPrincipal.getInstance().arreterHorloge();
			menuPause.setVisible(true);
			menuPause.setMinWidth(pane.getWidth());
			menuPause.setMinHeight(pane.getHeight());
			menuPause.toFront();
			menuAffiche = true;
		}
	}
	
	public void cacherMenuPause()
	{
		ContPrincipal.getInstance().demarrerHorloge();
		menuPause.setVisible(false);
		menuAffiche = false;
	}
	
	public void afficherMenuMort()
	{
		wPressed = true;
		ContPrincipal.getInstance().arreterHorloge();
		menuMort.setVisible(true);
		menuMort.setMinWidth(pane.getWidth());
		menuMort.setMinHeight(pane.getHeight());
		menuMort.toFront();
		menuAffiche = true;
	}
	
	public void cacherMenuMort()
	{
		ContPrincipal.getInstance().demarrerHorloge();
		menuMort.setVisible(false);
		menuAffiche = false;
	}
	
	/**
	 * Affiche l'�cran de victoire.
	 */
	public void afficherMenuVictoire()
	{
		wPressed = true;
		if(numeroNiveau < 10)
		{
			ContPrincipal.getInstance().arreterHorloge();
			menuVictoire.setVisible(true);
			menuVictoire.setMinWidth(pane.getWidth());
			menuVictoire.setMinHeight(pane.getHeight());
			menuVictoire.toFront();
			menuAffiche = true;
		}
		else
		{
			retour();
			JOptionPane.showMessageDialog(null, "Vous avez r�ussi le jeu!\nF�licitation!", "Victoire", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	public void cacherMenuVictoire()
	{
		ContPrincipal.getInstance().demarrerHorloge();
		menuVictoire.setVisible(false);
		menuAffiche = false;
	}
	
	/**
	 * Charge un niveau de jeu.
	 * 
	 * @param niv Niveau � charger.
	 */
	public void chargerNiveau(Niveau niv)
	{
		if (niv != null)
		{
			niveau = niv;
			objectifAtteint = false;
			mort = false;
			
			ContPrincipal.getInstance().viderCorps();
			
			for (Corps c : niveau.getCorps())
			{
				ContPrincipal.getInstance().ajouterCorps(c);
				if (c.getClass().equals(
						new VaisseauJoueur(1, new Vecteur(), 0, 0,
								new Vecteur(), new Vecteur()).getClass()))
				{
					vaisseauJoueur = (VaisseauJoueur) c;
				}
			}
			if (vaisseauJoueur == null)
			{
				vaisseauJoueur = new VaisseauJoueur(1, new Vecteur(0, 0), 16e3,
						30, new Vecteur(10, 10), new Vecteur(10, 10));
				ContPrincipal.getInstance().ajouterCorps(vaisseauJoueur);
			}
			vaisseauJoueur.setPosition(niveau.getPointDepart());
			
			vue.initialiserCorps();
			
			Objectif objectif = niveau.getObjectif();
			progressBarCarburant.progressProperty().unbind();
			progressBarCarburant.progressProperty().bind(
					vaisseauJoueur.carburantRestantProperty().divide(
							vaisseauJoueur.carburantMaxProperty()));
			progressBarSante.progressProperty().unbind();
			progressBarSante.progressProperty().bind(vaisseauJoueur.santeProperty());
			
			if (objectif != null)
			{
				objectif.setVaisseau(vaisseauJoueur);
				if (objectif instanceof Dessinable)
				{
					vue.ajouterDessinable((Dessinable) objectif);
				}
			}
			
			ContPrincipal.getInstance().arreterHorloge();
			listenMouse = true;
		}
		else if (niveau == null)
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
		for (Corps c : ContPrincipal.getInstance().getCorps())
		{
			c.reset();
		}
		vaisseauJoueur = null;
		chargerNiveau(niveau);
	}
	
	/**
	 * �couteur lors de la s�lection de la vitesse initiale.
	 * @param e
	 */
	@FXML
	public void mouseMove(MouseEvent e)
	{
		if(listenMouse)
		{
			Point2D point = pane.sceneToLocal(e.getSceneX(), e.getSceneY());
			Camera cam = vue.getCamera();
			Vecteur pos = cam.localToGlobal(new Vecteur(point.getX(), point.getY()));
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
		if(listenMouse)
		{
			Point2D point = pane.sceneToLocal(e.getSceneX(), e.getSceneY());
			Camera cam = vue.getCamera();
			Vecteur pos = cam.localToGlobal(new Vecteur(point.getX(), point.getY()));
			Vecteur sub = pos.soustraire(vaisseauJoueur.getPosition());
			vaisseauJoueur.setVitesse(sub.normaliser().multiplication(niveau.getVitesseDepart()));
			
			listenMouse = false;
			
			ContPrincipal.getInstance().demarrerHorloge();
		}
	}
	
	@FXML
	public void recommencer()
	{
		retourjeu();
		reset();
	}
	
	@FXML
	public void niveauSuivant()
	{
		numeroNiveau ++;
		Niveau niv = Niveau.chargerNiveau(this.getClass().getResourceAsStream("/levels/level_" + numeroNiveau + ".txt"));
		Platform.runLater(() -> {
			chargerNiveau(niv);
		});
		cacherMenuVictoire();
	}
	
	@FXML
	public void retour()
	{
		ContPrincipal.getInstance().viderCorps();
		ContPrincipal.getInstance().selectionnerControleur(new ContMenu());
	}
	
	@FXML
	public void retourjeu()
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
	
	/**
	 * Met � jour la cam�ra et v�rifie l'objectif.
	 */
	public void update(double dt)
	{
		if (vaisseauJoueur != null)
		{
			Camera camera = vue.getCamera();
			camera.deplacer(vaisseauJoueur.getPositionX(), vaisseauJoueur.getPositionY());
			vaisseauJoueur.update(dt);
			verifierObjectif();
		}
	}
	
	/**
	 * V�rifie si l'objectif actuel est atteint et v�rifie si le joueur est mort.
	 */
	private void verifierObjectif()
	{
		if (niveau != null && !objectifAtteint)
		{
			if (niveau.getObjectif() != null
					&& niveau.getObjectif().verifierObjectif())
			{
				objectifAtteint = true;
				afficherMenuVictoire();
			}
			
			if (vaisseauJoueur.getSante() == 0.0)
			{
				mort = true;
				afficherMenuMort();
			}
		}
	}
}
