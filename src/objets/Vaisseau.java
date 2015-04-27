package objets;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import utils.Vecteur;

/**
 * Classe représentant un vaisseau spatial.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class Vaisseau extends ObjetSpatial
{
	
	public final static double CARBURANT_DEFAUT = 20;
	public final static double PUISSANCE_DEFAUT = 1.0;
	public final static double VITESSE_ANIM_FLAMME = 6;
	public final static double VITESSE_ANIM_MORT = 8;
	public final static double MASSE_DEFAUT = 16e3;
	
	protected double puissanceMax;
	protected double puissance;
	protected Vecteur direction;
	protected DoubleProperty carburantMax;
	protected DoubleProperty carburantRestant;
	protected DoubleProperty sante;
	private boolean premierGetNoeud;
	
	protected Node noeud;
	protected Rotate noeudRotate;
	protected ImageView imageFlamme;
	protected ImageView imageExplosion;
	protected double currentExplosion;
	protected double currentFlamme;
	protected boolean animationMort;
	protected double carburantDepart;
	
	/**
	 * Constructeur de vaisseau, prend un vecteur pour la position
	 * 
	 * @param pPuissance
	 *            la puissance maximale du vaisseau
	 * @param pDirection
	 *            la direction du vaisseau
	 * @param pMasse
	 *            la masse du vaisseau
	 * @param pCarburantMax
	 *            la capacité maximale de caburant du vaisseau
	 * @param pPosition
	 *            la position du vaisseau
	 * @param pVitesse
	 *            la vitesse du vaisseau
	 */
	public Vaisseau(double pPuissance, double pMasse, double pCarburantMax,
			double pCarburantDepart, Vecteur pPosition, Vecteur pVitesse)
	{
		super(pMasse, pPosition, false, pVitesse);
		init(pPuissance, pCarburantMax, pCarburantDepart);
	}
	
	/**
	 * Constructeur de vaisseau, prend des doubles pour la position
	 * 
	 * @param pPuissance
	 *            la puissance maximale du vaisseau
	 * @param pDirection
	 *            la direction du vaisseau
	 * @param pMasse
	 *            la masse du vaisseau
	 * @param pCarburantMax
	 *            la capacité maximale de caburant du vaisseau
	 * @param pPositionX
	 *            la positionX de la planète
	 * @param pPositionY
	 *            la positionY de la planète
	 * @param pVitesse
	 *            la vitesse du vaisseau
	 */
	public Vaisseau(double pPuissance, double pMasse, double pCarburantMax,
			double pCarburantDepart, double pPositionX, double pPositionY,
			Vecteur pVitesse)
	{
		super(pMasse, pPositionX, pPositionY, false, pVitesse);
		init(pPuissance, pCarburantMax, pCarburantDepart);
	}
	
	/**
	 * Constructeur de vaisseau, sert à la construction de vaisseauJoueur.
	 * 
	 * @param pPuissance
	 *            la puissance maximale du vaisseau
	 * @param pDirection
	 *            la direction du vaisseau
	 * @param pMasse
	 *            la masse du vaisseau
	 * @param pCarburantMax
	 *            la capacité maximale de caburant du vaisseau
	 * @param pPositionX
	 *            la positionX de la planète
	 * @param pPositionY
	 *            la positionY de la planète
	 * @param pVitesse
	 *            la vitesse du vaisseau
	 */
	protected Vaisseau(double pPuissance, double pMasse, double pCarburantMax,
			double pCarburantDepart)
	{
		super(pMasse, 1, 1, false, new Vecteur(1, 1));
		init(pPuissance, pCarburantMax, pCarburantDepart);
	}
	
	/**
	 * Initialise le vaisseau avec des valeurs par défaut.
	 * 
	 * @param pPuissanceMax
	 *            Puissance maximale du réacteur.
	 * @param pCarburantMax
	 *            Quantité de carburant maximale.
	 */
	private void init(double pPuissanceMax, double pCarburantMax,
			double pCarburantDepart)
	{
		setPuissanceMax(pPuissanceMax);
		setPuissance(pPuissanceMax);
		direction = new Vecteur();
		carburantMax = new SimpleDoubleProperty();
		setCarburantMax(pCarburantMax);
		carburantDepart = pCarburantDepart;
		carburantRestant = new SimpleDoubleProperty();
		setCarburantRestant(carburantDepart);
		
		premierGetNoeud = true;
		currentExplosion = 1.0;
		currentFlamme = 0.0;
		sante = new SimpleDoubleProperty(1.0);
	}
	
	/**
	 * Retourne la puissance maximale du réacteur (en Newton).
	 * 
	 * @return Puissance maximale du réacteur.
	 */
	public double getPuissanceMax()
	{
		return puissanceMax;
	}
	
	/**
	 * Modifie la puissance maximale du réacteur.
	 * 
	 * @param pPuissanceMax
	 *            Puissance maximale (en Newton).
	 */
	public void setPuissanceMax(double pPuissanceMax)
	{
		if (pPuissanceMax <= 0)
			puissanceMax = PUISSANCE_DEFAUT;
		else
			puissanceMax = pPuissanceMax;
	}
	
	/**
	 * Retourne la puissance actuelle du moteur.
	 * 
	 * @return Puissance actuelle (en Newton).
	 */
	public double getPuissance()
	{
		return puissance;
	}
	
	/**
	 * Modifie la puissance actuelle du moteur.
	 * 
	 * @param pPuissance
	 *            Puissance (en Newton).
	 */
	public void setPuissance(double pPuissance)
	{
		if (pPuissance <= 0)
			puissance = PUISSANCE_DEFAUT;
		else
			puissance = pPuissance;
	}
	
	/**
	 * Retourne la santé actuelle du vaisseau.
	 * 
	 * @return Santé actuelle (entre 0.0 et 1.0).
	 */
	public double getSante()
	{
		return sante.get();
	}
	
	/**
	 * Modifie la santé du vaisseau. Doit être située entre 0.0 et 1.0
	 * inclusivement.
	 * 
	 * @param sante
	 *            Nouvelle santé.
	 */
	public void setSante(double pSante)
	{
		if (pSante <= 1.0 & pSante >= 0.0)
		{
			sante.set(pSante);
		}
	}
	
	/**
	 * Retourne une propriété observable de la santé du vaisseau.
	 * 
	 * @return Propriété observable (en %).
	 */
	public DoubleProperty santeProperty()
	{
		return sante;
	}
	
	/**
	 * Modifie la quantité maximale de carburant du vaisseau.
	 * 
	 * @param pCarburantMax
	 *            Quantité maximale de carburant (en kg).
	 */
	public void setCarburantMax(double pCarburantMax)
	{
		if (pCarburantMax <= 0)
			carburantMax.set(CARBURANT_DEFAUT);
		else
		{
			carburantMax.set(pCarburantMax);
		}
		
		if (carburantMax.get() < carburantDepart)
		{
			setCarburantDepart(carburantMax.get());
		}
	}
	
	/**
	 * Retourne la quantité maximale de carburant.
	 * 
	 * @return Quantité maximale de carburant (en kg).
	 */
	public double getCarburantMax()
	{
		return carburantMax.get();
	}
	
	/**
	 * Retourne la quantité restante de carburant dans le vaisseau.
	 * 
	 * @return Quantité de carburant restante (en kg).
	 */
	public double getCarburantRestant()
	{
		return carburantRestant.get();
	}
	
	/**
	 * Modifie la quantité de carburant restante.
	 * 
	 * @param pCarburantRestant
	 *            Quantité de carburant (en kg).
	 */
	public void setCarburantRestant(double pCarburantRestant)
	{
		if (pCarburantRestant < 0)
			carburantRestant.set(0);
		else if (pCarburantRestant > carburantMax.get())
			carburantRestant.set(carburantMax.get());
		else
		{
			carburantRestant.set(pCarburantRestant);
		}
	}
	
	/**
	 * Modifie la quantité de carburant que le vaisseau a au départ.
	 * 
	 * @param pCarburantDepart
	 *            Quantité de carburant au départ (en kg).
	 */
	public void setCarburantDepart(double pCarburantDepart)
	{
		if (pCarburantDepart > carburantMax.get())
		{
			setCarburantMax(pCarburantDepart);
		}
		
		carburantDepart = pCarburantDepart;
		
		if (pCarburantDepart < 0)
		{
			carburantDepart = 0;
		}
		
		setCarburantRestant(pCarburantDepart);
	}
	
	/**
	 * Retourne la quantité de carburant que le vaisseau a au départ.
	 * 
	 * @return Quantité de carburant de départ (en kg).
	 */
	public double getCarburantDepart()
	{
		return carburantDepart;
	}
	
	/**
	 * Retourne une propriété observable de la quantité de carburant maximale.
	 * 
	 * @return Propriété observable (en kg).
	 */
	public DoubleProperty carburantMaxProperty()
	{
		return carburantMax;
	}
	
	/**
	 * Retourne une propriété observable de la quantité de carburant restante.
	 * 
	 * @return Propriété observable (en kg).
	 */
	public DoubleProperty carburantRestantProperty()
	{
		return carburantRestant;
	}
	
	/**
	 * Ne pas mettre True sinon le vaisseau reste immobile
	 */
	public void setStatique(boolean pStatique)
	{
		statique = pStatique;
	}
	
	/**
	 * Si le vecteur est null, met la vitesse à 0.
	 */
	public void setVitesse(Vecteur pVitesse)
	{
		if (pVitesse == null)
		{
			vitesse = new Vecteur();
		}
		else
		{
			vitesse = pVitesse;
			direction = vitesse.normaliser();
		}
	}
	
	/**
	 * Retourne la force appliquée par le réacteur (en Newton).
	 * 
	 * @return Vecteur de la force appliquée par le réacteur.
	 */
	public Vecteur getForceExt()
	{
		return new Vecteur();
	}
	
	/**
	 * Retourne la direction du vaisseau.
	 * 
	 * @return Vecteur normaliser représentant la direction du vaisseau.
	 */
	public Vecteur getDirection()
	{
		return direction;
	}
	
	/**
	 * Crée le noeud JavaFX du vaisseau.
	 */
	private void creeNoeud()
	{
		Group group = new Group();
		
		Image textureVaisseau = null;
		
		if (this instanceof VaisseauJoueur)
		{
			textureVaisseau = new Image("/res/spaceship.png");
		}
		else
		{
			textureVaisseau = new Image("/res/spaceship-ennemy.png");
		}
		
		ImageView image = new ImageView(textureVaisseau);
		image.setFitWidth(40);
		image.setFitHeight(40);
		image.setTranslateX(-20);
		image.setTranslateY(-20);
		group.getChildren().add(image);
		
		Image textureFlamme = new Image("/res/flame.png");
		imageFlamme = new ImageView(textureFlamme);
		imageFlamme.setFitWidth(15);
		imageFlamme.setFitHeight(40);
		imageFlamme.setTranslateX(-7);
		imageFlamme.setTranslateY(22);
		group.getChildren().add(imageFlamme);
		
		noeudRotate = new Rotate(0, 0, 0);
		group.getTransforms().add(noeudRotate);
		
		noeud = group;
	}
	
	/**
	 * Retourne le noeud JavaFX représentant le vaisseau. Le noeud ne change pas
	 * entre chaque appel de la méthode.
	 * 
	 * @return Noeud JavaFX du vaisseau.
	 */
	public Node getNoeud()
	{
		if(!animationMort && premierGetNoeud)
		{
			creeNoeud();
			premierGetNoeud = false;
		}
		return noeud;
	}
	
	/**
	 * Démarre l'animation d'explosion,
	 */
	public void jouerAnimationMort()
	{
		animationMort = true;
		ImageView image = new ImageView("/res/explosion_1.png");
		image.setFitWidth(150);
		image.setFitHeight(150);
		image.setTranslateX(-75);
		image.setTranslateY(-100);
		((Group)noeud).getChildren().clear();
		((Group)noeud).getChildren().add(image);
		noeudRotate.setAngle(0);
		noeud.toFront();
	}
	
	/**
	 * Retourne l'état de l'animation d'explosion.
	 * @return Vrai si l'ainmation d'explosion est en cours.
	 */
	public boolean isAnimationMort()
	{
		return animationMort;
	}
	
	/**
	 * Met à jour le noeud représentant le vaisseau
	 */
	public void maj(double dt)
	{
		if (getForceExt().getNorme() > 0)
		{
			currentFlamme += VITESSE_ANIM_FLAMME * dt;
			currentFlamme = Math.min(1.0, currentFlamme);
		}
		else
		{
			currentFlamme -= VITESSE_ANIM_FLAMME * dt;
			currentFlamme = Math.max(0.0, currentFlamme);
		}
		
		if(!animationMort)
		{
			imageFlamme.setOpacity(currentFlamme);
			noeudRotate.setAngle(direction.getAngle() / 2 / Math.PI * 360 + 90);
		}
		else
		{
			if(currentExplosion < Math.floor(currentExplosion += VITESSE_ANIM_MORT * dt) && currentExplosion < 10)
			{
				((ImageView)((Group)noeud).getChildren().get(0)).setImage(new Image("/res/explosion_" + (int)Math.floor(currentExplosion) + ".png"));
			}
			else if(currentExplosion >= 10)
			{
				((Group)noeud).getChildren().clear();
				currentExplosion = 1.0;
				animationMort = false;
				premierGetNoeud = true;
			}
		}
	}
	
	/**
	 * Retourne le rayon de collision du vaisseau.
	 * 
	 * @return Rayon de collision (en m).
	 */
	public double getRayon()
	{
		return 20.0;
	}
	
	/**
	 * Remet les corps à leur position et leur vitesse de départ.
	 */
	public void reset()
	{
		setCarburantRestant(carburantDepart);
		setSante(1);
		setPositionX(positionXDepart);
		setPositionY(positionYDepart);
		setVitesse(vitesseDepart);
	}
}
