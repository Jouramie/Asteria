package controleur;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;
import objets.VaisseauJoueur;
import utils.Vecteur;
import vue.Camera;
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
	
	private VueJeu vue;
	private VaisseauJoueur vaisseauJoueur;
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
	}
	
	public void afficherMenuPause()
	{
		ContPrincipal.getInstance().arreterHorloge();
		menuPause.setVisible(true);
		menuPause.toFront();
	}
	
	public void cacherMenuPause()
	{
		ContPrincipal.getInstance().demarrerHorloge();
		menuPause.setVisible(false);
	}
	
	@FXML
	public void retour()
	{
		ContPrincipal.getInstance().getCorps().remove(vaisseauJoueur);
		ContPrincipal.getInstance().selectionnerControleur(new ContMenu());
	}
	
	@FXML
	public void retourjeu()
	{
		cacherMenuPause();
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
	
	@FXML
	public void keyPressed(KeyEvent e)
	{
		switch (e.getCode())
		{
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
	
	/**
	 * Met � jour le vaisseau.
	 */
	public void update(double dt)
	{
		Camera camera = vue.getCamera();
		camera.deplacer(vaisseauJoueur.getPositionX(),
				vaisseauJoueur.getPositionY());
	}
}
