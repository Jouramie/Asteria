package controleur;

import modele.Corps;
import modele.Objectif;

import java.io.File;

import modele.Niveau;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
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
	private VBox menuPause;
	
	@FXML
	private VBox menuVictoire;
	
	@FXML
	private VBox menuMort;
	
	private VueJeu vue;
	private VaisseauJoueur vaisseauJoueur;
	private Niveau niveau;
	private boolean objectifAtteint;
	private boolean mort;
	private boolean aPressed;
	private boolean dPressed;
	private boolean wPressed;
	
	/**
	 * Constructeur du contr�leur.
	 */
	public ContJeu()
	{
		vue = new VueJeu();
	}
	
	/**
	 * Initialise les �l�ments du jeu et affiche la vue. D�marre l'horloge du
	 * contr�leur principal.
	 */
	public void initialiser()
	{
		ContPrincipal.getInstance().demarrerHorloge();
		
		vaisseauJoueur = new VaisseauJoueur(2167.27e2, new Vecteur(0, 0), 16e3,
				100, new Vecteur(10, 10), new Vecteur(10, 10));
		ContPrincipal.getInstance().ajouterCorps(vaisseauJoueur);
		
		ContPrincipal.getInstance().afficherVue(vue, true);
		
		File f = (new FileChooser()).showOpenDialog(null);
		Niveau niv = Niveau.chargerNiveau(f);
		chargerNiveau(niv);
	}
	
	@FXML
	public void keyPressed(KeyEvent e)
	{
		switch (e.getCode())
		{
		case P:
		case ESCAPE:
		{
			if (!menuPause.isVisible())
			{
				afficherMenuPause();
			}
			else
			{
				cacherMenuPause();
			}
			break;
		}
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
	
		case R:
			if (!menuPause.isVisible())
			{
				reset();
			}
			break;
		default:
			break;
		}
	}

	@FXML
	public void keyReleased(KeyEvent e)
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

	public void afficherMenuPause()
	{
		if(!objectifAtteint && !mort)
		{
			ContPrincipal.getInstance().arreterHorloge();
			menuPause.setVisible(true);
			menuPause.toFront();
		}
	}
	
	public void cacherMenuPause()
	{
		ContPrincipal.getInstance().demarrerHorloge();
		menuPause.setVisible(false);
	}
	
	public void afficherMenuMort()
	{
		ContPrincipal.getInstance().arreterHorloge();
		menuMort.setVisible(true);
		menuMort.toFront();
	}
	
	public void cacherMenuMort()
	{
		ContPrincipal.getInstance().demarrerHorloge();
		menuMort.setVisible(false);
	}
	
	/**
	 * Affiche l'�cran de victoire.
	 */
	public void afficherMenuVictoire()
	{
		ContPrincipal.getInstance().arreterHorloge();
		menuVictoire.setVisible(true);
		menuVictoire.toFront();
	}
	
	public void cacherMenuVictoire()
	{
		ContPrincipal.getInstance().demarrerHorloge();
		menuVictoire.setVisible(false);
	}
	
	/**
	 * Charge un niveau de jeu.
	 * @param niv Niveau � charger.
	 */
	public void chargerNiveau(Niveau niv)
	{
		niveau = niv;
		objectifAtteint = false;
		mort = false;
		
		ContPrincipal.getInstance().viderCorps();
		
		for(Corps c : niveau.getCorps())
		{
			ContPrincipal.getInstance().ajouterCorps(c);
		}
		
		ContPrincipal.getInstance().ajouterCorps(vaisseauJoueur);
		vaisseauJoueur.setPosition(niveau.getPointDepart());
		vaisseauJoueur.setVitesse(niveau.getVitesseDepart());
		
		vue.initialiserCorps();
		
		Objectif objectif = niveau.getObjectif();
		
		if(objectif != null)
		{
			objectif.setVaisseau(vaisseauJoueur);
			if(objectif instanceof Dessinable)
			{
				vue.ajouterDessinable((Dessinable)objectif);
			}
		}
	}

	public void reset()
	{
		for(Corps c : ContPrincipal.getInstance().getCorps())
		{
			c.reset();
		}
		chargerNiveau(niveau);
	}
	
	@FXML
	public void recommencer()
	{
		reset();
		retourjeu();
	}
	
	@FXML
	public void retour()
	{
		ContPrincipal.getInstance().enleverCorps(vaisseauJoueur);
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
	
	/**
	 * Met � jour le vaisseau.
	 */
	public void update(double dt)
	{
		Camera camera = vue.getCamera();
		camera.deplacer(vaisseauJoueur.getPositionX(), vaisseauJoueur.getPositionY());
		
		verifierObjectif();
	}
	
	/**
	 * V�rifie si l'objectif actuel est atteint.
	 */
	private void verifierObjectif()
	{
		if(niveau != null && !objectifAtteint)
		{
			if(niveau.getObjectif() != null && niveau.getObjectif().verifierObjectif())
			{
				objectifAtteint = true;
				afficherMenuVictoire();
			}
			
			if(vaisseauJoueur.getSante() == 0.0)
			{
				mort = true;
				afficherMenuMort();
			}
		}
	}
}
