package objets;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Vecteur;

/**
 * Classe représentant un vaisseau spatial.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class Vaisseau extends ObjetSpatial
{
	public final static double CONVERTION_CARBURANT = 1.0;
	public final static double PUISSANCE_MAX_DEFAUT = 1.0;
	public final static double PUISSANCE_DEFAUT = 1.0;
	
	private double puissanceMax;
	private double puissance;
	private Vecteur direction;
	private double capaciteCarburant;
	private DoubleProperty carburant;
	private Group group;
	
	/**
	 * Constructeur de vaisseau, prend un vecteur pour la position
	 * 
	 * @param pPuissanceMax
	 *            la puissance maximale du vaisseau
	 * @param pDirection
	 *            la direction du vaisseau
	 * @param pMasse
	 *            la masse du vaisseau
	 * @param pCapaciteCarburant
	 *            la capacité maximale de caburant du vaisseau
	 * @param pPosition
	 *            la position du vaisseau
	 * @param pVitesse
	 *            la vitesse du vaisseau
	 */
	public Vaisseau(double pPuissanceMax, Vecteur pDirection, double pMasse,
			double pCapaciteCarburant, Vecteur pPosition, Vecteur pVitesse)
	{
		super(pMasse, pPosition, false, pVitesse);
		setPuissanceMax(pPuissanceMax);
		setPuissance(pPuissanceMax);
		direction = pDirection;
		capaciteCarburant = pCapaciteCarburant;
		carburant = new SimpleDoubleProperty(capaciteCarburant);
		
		if(direction == null)
		{
			direction = new Vecteur();
		}
	}
	
	/**
	 * Constructeur de vaisseau, prend des doubles pour la position
	 * 
	 * @param pPuissanceMax
	 *            la puissance maximale du vaisseau
	 * @param pDirection
	 *            la direction du vaisseau
	 * @param pMasse
	 *            la masse du vaisseau
	 * @param pCapaciteCarburant
	 *            la capacité maximale de caburant du vaisseau
	 * @param pPositionX
	 *            la positionX de la planète
	 * @param pPositionY
	 *            la positionY de la planète
	 * @param pVitesse
	 *            la vitesse du vaisseau
	 */
	public Vaisseau(double pPuissanceMax, Vecteur pDirection, double pMasse,
			double pCapaciteCarburant, double pPositionX, double pPositionY,
			Vecteur pVitesse)
	{
		super(pMasse, pPositionX, pPositionY, false, pVitesse);
		setPuissanceMax(pPuissanceMax);
		setPuissance(pPuissanceMax);
		direction = pDirection;
		capaciteCarburant = pCapaciteCarburant;
		carburant = new SimpleDoubleProperty(capaciteCarburant);
		
		if(direction == null)
		{
			direction = new Vecteur();
		}
	}
	
	public void tournerGauche()
	{
		// TODO Story 12
	}
	
	public void tournerDroite()
	{
		// TODO Story 12
	}
	
	public double getPuissanceMax()
	{
		return puissanceMax;
	}
	
	public void setPuissanceMax(double pPuissanceMax)
	{
		if (pPuissanceMax <= 0)
			puissanceMax = PUISSANCE_MAX_DEFAUT;
		else
			puissanceMax = pPuissanceMax;
	}
	
	public double getPuissance()
	{
		return puissance;
	}
	
	public void setPuissance(double pPuissance)
	{
		if (pPuissance <= 0)
			puissance = PUISSANCE_DEFAUT;
		else
			puissance = pPuissance;
	}
	
	// public void setCarburantMax(double pCarburantMax)
	// {
	// // TODO story implementation du carburant
	// }
	//
	// public void getCarburantMax(double pCarburantMax)
	// {
	// // TODO story implementation du carburant
	// }
	//
	// public void setCarburant(double pCarburant)
	// {
	// // TODO story implementation du carburant
	// }
	//
	// public void getCarburant(double pCarburant)
	// {
	// // TODO story implementation du carburant
	// }
	//
	// TODO story implementation du carburant
	// @Override
	// public double getMasse()
	// {
	// return masse + CONVERTION_CARBURANT * carburant.get();
	// }
	
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
	
	public Node getNoeud()
	{
		Image texture = new Image("/res/spaceship.png");
		ImageView image = new ImageView(texture);
		image.setFitWidth(40);
		image.setFitHeight(40);
		image.setTranslateX(-20);
		image.setTranslateY(-20);
		
		group = new Group();
		group.getChildren().add(image);
		
		maj();
		
		return group;
	}
	
	/**
	 * Met à jour le noeud représentant le vaisseau
	 */
	public void maj()
	{
		group.setRotate(direction.getAngle() / 2 / Math.PI * 360 + 90);
	}
	
	public int getRayonCollision()
	{
		return 20;
	}
}