package objets;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import modele.Corps;
import utils.Vecteur;

/**
 * Classe représentant un vaisseau contrôlable par l'utilisateur.
 * 
 * @author EquBolduc
 */
public class VaisseauJoueur extends Vaisseau
{
	public static final double PUISSANCE_MOTEUR = 200000;
	public static final double PUISSANCE_ROTATION_DEFAUT = 1;
	public final static double CARBURANT_DEFAUT = 20;
	public final static double PUISSANCE_DEFAUT = 1.0;
	public final static double VITESSE_ANIM_FLAMME = 6;
	public final static double VITESSE_ANIM_MORT = 8;
	public final static int NB_FRAMES_ANIM_MORT = 9;
	
	private double puissance;
	private DoubleProperty carburantMax;
	private DoubleProperty carburantRestant;
	private DoubleProperty sante;
	private ImageView imageFlamme;
	private double currentExplosion;
	private double currentFlamme;
	private boolean animationMort;
	private double carburantDepart;
	private Vecteur direction;
	private boolean gauche;
	private boolean droite;
	private boolean moteur;
	private double puissanceRotation;
	
	/**
	 * Constructeur du vaisseauJoueur de base.
	 * 
	 * @param pPuissance Puissance maximale du moteur (en Newton).
	 * @param pMasse Masse du vaisseau (en kg).
	 * @param pCarburantMax Quantité de carburant maximale (en kg).
	 * @param pCarburantDepart Quantité de carburant de départ (en kg).
	 * @param pPositionX Position en X dans l'espace.
	 * @param pPositionY Position en Y dans l'espace.
	 * @param pVitesse Vitesse initiale du vaisseau.
	 */
	public VaisseauJoueur(double pPuissance, double pMasse,
			double pCarburantMax, double pCarburantDepart)
	{
		super(pMasse, new Vecteur(0, 0), new Vecteur(1, 1));
		initDefaut(pPuissance, pCarburantMax, pCarburantDepart);
	}
	
	/**
	 * Initialise le vaisseau avec des valeurs par défaut.
	 */
	private void initDefaut(double pPuissanceMax, double pCarburantMax,
			double pCarburantDepart)
	{
		setPuissance(pPuissanceMax);
		carburantMax = new SimpleDoubleProperty();
		setCarburantMax(pCarburantMax);
		direction = new Vecteur();
		setDirection(vitesse.normaliser());
		
		if(pCarburantDepart >= 0)
		{
			carburantDepart = pCarburantDepart;
		}
		else
		{
			carburantDepart = 0;
		}
		
		carburantRestant = new SimpleDoubleProperty();
		setCarburantRestant(carburantDepart);
		currentExplosion = 1.0;
		currentFlamme = 0.0;
		sante = new SimpleDoubleProperty(1.0);
		puissanceRotation = PUISSANCE_ROTATION_DEFAUT;
		gauche = false;
		droite = false;
		moteur = false;
		animationMort = false;
	}
	
	/**
	 * Commence ou arrête de faire tourner le vaisseau vers la gauche.
	 */
	public void tournerGauche(boolean tourner)
	{
		gauche = tourner;
	}
	
	/**
	 * Commence ou arrête de faire tourner le vaisseau vers la droite.
	 */
	public void tournerDroite(boolean tourner)
	{
		droite = tourner;
	}
	
	/**
	 * Commence ou arrête de faire avancer le vaisseau vers la gauche.
	 */
	public void avancer(boolean avancer)
	{
		moteur = avancer;
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
	 * @param pPuissance Puissance (en Newton).
	 */
	public void setPuissance(double pPuissance)
	{
		if(pPuissance <= 0)
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
	 * @param sante Nouvelle santé.
	 */
	public void setSante(double pSante)
	{
		if(pSante <= 1.0 & pSante >= 0.0)
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
	 * @param pCarburantMax Quantité maximale de carburant (en kg).
	 */
	public void setCarburantMax(double pCarburantMax)
	{
		if(pCarburantMax <= 0)
			carburantMax.set(CARBURANT_DEFAUT);
		else
		{
			carburantMax.set(pCarburantMax);
		}
		
		if(carburantMax.get() < carburantDepart)
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
	 * @param pCarburantRestant Quantité de carburant (en kg).
	 */
	public void setCarburantRestant(double pCarburantRestant)
	{
		if(pCarburantRestant < 0)
			carburantRestant.set(0);
		else if(pCarburantRestant > carburantMax.get())
			carburantRestant.set(carburantMax.get());
		else
		{
			carburantRestant.set(pCarburantRestant);
		}
	}
	
	/**
	 * Modifie la quantité de carburant que le vaisseau a au départ.
	 * 
	 * @param pCarburantDepart Quantité de carburant au départ (en kg).
	 */
	public void setCarburantDepart(double pCarburantDepart)
	{
		if(pCarburantDepart > carburantMax.get())
		{
			setCarburantMax(pCarburantDepart);
		}
		
		carburantDepart = pCarburantDepart;
		
		if(pCarburantDepart < 0)
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
	 * Retourne la force appliquée par le moteur (en Newton).
	 * 
	 * @return Force du moteur.
	 */
	public Vecteur getForceExt()
	{
		Vecteur r = new Vecteur();
		if(moteur && !(carburantRestant.get() <= 0))
		{
			r = direction.multiplication(puissance * PUISSANCE_MOTEUR);
		}
		return r;
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
	 * Change la direction du vaisseau.
	 * 
	 * @param nouvDirection Nouvelle direction du vaisseau.
	 */
	public void setDirection(Vecteur nouvDirection)
	{
		if(nouvDirection != null)
		{
			direction = nouvDirection;
		}
	}
	
	/**
	 * Démarre l'animation d'explosion,
	 */
	public void jouerAnimationMort()
	{
		if(!animationMort)
		{
			animationMort = true;
			ImageView image = new ImageView("/res/explosion_1.png");
			image.setFitWidth(150);
			image.setFitHeight(150);
			image.setTranslateX(-75);
			image.setTranslateY(-100);
			((Group) noeud).getChildren().clear();
			((Group) noeud).getChildren().add(image);
			noeudRotate.setAngle(0);
			noeud.toFront();
		}
	}
	
	/**
	 * Retourne l'état de l'animation d'explosion.
	 * 
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
		if(gauche ^ droite)
		{
			if(gauche)
			{
				direction.setAngle(direction.getAngle() - puissanceRotation
						/ 360 * 2 * Math.PI);
			}
			else if(droite)
			{
				direction.setAngle(direction.getAngle() + puissanceRotation
						/ 360 * 2 * Math.PI);
			}
		}
		
		if(getForceExt().getNorme() > 0)
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
			if(currentExplosion < Math
					.floor(currentExplosion += VITESSE_ANIM_MORT * dt)
					&& currentExplosion < NB_FRAMES_ANIM_MORT + 1)
			{
				((ImageView) ((Group) noeud).getChildren().get(0))
						.setImage(new Image("/res/explosion_"
								+ (int) Math.floor(currentExplosion) + ".png"));
			}
			else if(currentExplosion >= NB_FRAMES_ANIM_MORT + 1)
			{
				((Group) noeud).getChildren().clear();
				currentExplosion = 1.0;
				animationMort = false;
				premierGetNoeud = true;
			}
		}
	}
	
	/**
	 * Crée le noeud JavaFX du vaisseau.
	 */
	private void creeNoeud()
	{
		Group group = new Group();
		
		Image textureVaisseau = new Image("/res/vaisseauJoueur.png");
		
		ImageView image = new ImageView(textureVaisseau);
		image.setFitWidth(40);
		image.setFitHeight(40);
		image.setTranslateX(-20);
		image.setTranslateY(-20);
		group.getChildren().add(image);
		
		Image textureFlamme = new Image("/res/flamme.png");
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
	 * Met à jour la quantité de carburant du vaisseau.
	 * 
	 * @param dt Temps écoulé (en secondes).
	 */
	public void update(double dt)
	{
		if(moteur)
		{
			setCarburantRestant(getCarburantRestant() - (puissance * dt));
		}
	}
	
	/**
	 * Réagit à la collision avec un autre corps.
	 */
	public void onCollision(Corps c)
	{
		if(c instanceof Vaisseau && c.getMasse() < getMasse() * 2)
		{
			setSante(getSante() - 0.5);
		}
		else
		{
			moteur = false;
			setSante(0.0);
		}
	}
	
	/**
	 * Modifie l'angle de direction du vaisseau.
	 * 
	 * @param angle Angle du vaisseau.
	 */
	public void setAngle(double angle)
	{
		direction.setAngle(angle);
	}
	
	/**
	 * Remet les corps à leur conditions de départ.
	 */
	public void reset()
	{
		setCarburantRestant(carburantDepart);
		setSante(1);
		super.reset();
	}
}
