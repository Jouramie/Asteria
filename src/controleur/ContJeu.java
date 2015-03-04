package controleur;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;
import objets.Planete;
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
		
		Planete p1 = new Planete(6e15, new Vecteur(400, 400),
				"/res/planete1.png");
		ContPrincipal.getInstance().ajouterCorps(p1);
		
		Planete p2 = new Planete(6e15, new Vecteur(600, 0), "/res/planete1.png");
		ContPrincipal.getInstance().ajouterCorps(p2);
		
		Planete p3 = new Planete(3e15, new Vecteur(600, 600),
				"/res/planete1.png");
		ContPrincipal.getInstance().ajouterCorps(p3);
		
		Planete p4 = new Planete(3e15, new Vecteur(0, 600), "/res/planete1.png");
		ContPrincipal.getInstance().ajouterCorps(p4);
		
		vaisseauJoueur = new VaisseauJoueur(7e4, new Vecteur(0, 0), 100, 100,
				new Vecteur(10, 10), new Vecteur(10, 10));
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
		ContPrincipal.getInstance().viderCorps();
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
			vaisseauJoueur.tournerGauche();
			aPressed = false;
			break;
		case RIGHT:
		case D:
			vaisseauJoueur.tournerDroite();
			dPressed = false;
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
