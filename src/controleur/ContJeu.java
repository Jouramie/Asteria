package controleur;

import modele.Corps;
import java.util.ArrayList;

import modele.Niveau;
import modele.Objectif;
import modele.ObjectifRayon;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;
import objets.Planete;
import objets.VaisseauJoueur;
import objets.Planete.Texture;
import utils.Vecteur;
import vue.Camera;
import vue.VueJeu;

/**
 * Contrôleur utilisé lors d'une session de jeu.
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
	
	private VueJeu vue;
	private VaisseauJoueur vaisseauJoueur;
	private Niveau niveau;
	private boolean objectifAtteint;
	private boolean aPressed;
	private boolean dPressed;
	private boolean wPressed;
	
	/**
	 * Constructeur du contrôleur.
	 */
	public ContJeu()
	{
		vue = new VueJeu();
	}
	
	/**
	 * Initialise les éléments du jeu et affiche la vue. Démarre l'horloge du
	 * contrôleur principal.
	 */
	public void initialiser()
	{
		ContPrincipal.getInstance().demarrerHorloge();
		
		vaisseauJoueur = new VaisseauJoueur(2167.27e2, new Vecteur(0, 0), 16e3,
				100, new Vecteur(10, 10), new Vecteur(10, 10));
		ContPrincipal.getInstance().ajouterCorps(vaisseauJoueur);
		
		ContPrincipal.getInstance().afficherVue(vue, true);
		
		ArrayList<Corps> corps = new ArrayList<Corps>();
		Planete p = new Planete(6e2, 100, 100, 10);
		p.setTexture(Texture.RAYEE_ROUGE);
		corps.add(p);
		
		Objectif obj = new ObjectifRayon(vaisseauJoueur, new Vecteur(100, 0), 20);
		Niveau niv = new Niveau(corps, obj, new Vecteur(0, 0), "Test", new Vecteur(10, 10));
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
				System.out.println("Recommencer");
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
		ContPrincipal.getInstance().arreterHorloge();
		menuPause.setVisible(true);
		menuPause.toFront();
	}
	
	public void cacherMenuPause()
	{
		ContPrincipal.getInstance().demarrerHorloge();
		menuPause.setVisible(false);
	}
	
	/**
	 * Charge un niveau de jeu.
	 * @param niv Niveau à charger.
	 */
	public void chargerNiveau(Niveau niv)
	{
		niveau = niv;
		objectifAtteint = false;
		
		ContPrincipal.getInstance().viderCorps();
		
		for(Corps c : niveau.getCorps())
		{
			ContPrincipal.getInstance().ajouterCorps(c);
		}
		
		ContPrincipal.getInstance().ajouterCorps(vaisseauJoueur);
		vaisseauJoueur.setPosition(niveau.getPointDepart());
		vaisseauJoueur.setVitesse(niveau.getVitesseDepart());
	}

	/**
	 * Affiche l'écran de victoire.
	 */
	public void afficherMenuVictoire()
	{
		ContPrincipal.getInstance().arreterHorloge();
		menuVictoire.setVisible(true);
		menuVictoire.toFront();
	}

	public void reset(){
		for(Corps c : ContPrincipal.getInstance().getCorps()){
			c.reset();
		}
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
	 * Met à jour le vaisseau.
	 */
	public void update(double dt)
	{
		Camera camera = vue.getCamera();
		camera.deplacer(vaisseauJoueur.getPositionX(), vaisseauJoueur.getPositionY());
		
		verifierObjectif();
	}
	
	/**
	 * Vérifie si l'objectif actuel est atteint.
	 */
	private void verifierObjectif()
	{
		if(niveau != null && !objectifAtteint)
		{
			if(niveau.getObjectif().verifierObjectif())
			{
				objectifAtteint = true;
				afficherMenuVictoire();
			}
		}
	}
}
