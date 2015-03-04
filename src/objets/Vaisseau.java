package objets;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import utils.Vecteur;

/**
 * Classe repr�sentant un vaisseau spatial.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class Vaisseau extends ObjetSpatial
{
	public final static double CONVERTION_CARBURANT = 1.0;
	public final static double PUISSANCE_MAX_DEFAUT = 1.0;
	
	protected double puissanceMax;
	protected double puissance;
	protected Vecteur direction;
	protected double capaciteCarburant;
	protected DoubleProperty carburant;

	
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
	 *            la capacit� maximale de caburant du vaisseau
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
		if(pDirection == null)
		{
			direction = new Vecteur();
		}else
		direction = pDirection;
		capaciteCarburant = pCapaciteCarburant;
		carburant = new SimpleDoubleProperty(capaciteCarburant);
		creeNoeud();
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
	 *            la capacit� maximale de caburant du vaisseau
	 * @param pPositionX
	 *            la positionX de la plan�te
	 * @param pPositionY
	 *            la positionY de la plan�te
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
		if(pDirection == null)
		{
			direction = new Vecteur();
		}else
		capaciteCarburant = pCapaciteCarburant;
		carburant = new SimpleDoubleProperty(capaciteCarburant);
		creeNoeud();
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
			puissance = PUISSANCE_MAX_DEFAUT;
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
	 * Si le vecteur est null, met la vitesse � 0.
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
	
	public Vecteur getForceExt()
	{
		return new Vecteur();
	}
	
	public Vecteur getDirection()
	{
		return direction;
	}
	
	private void creeNoeud(){
		noeud = new Group();
		
		Polygon fond = new Polygon(-15, -(Math.sqrt(Math.pow(30, 2) - Math.pow(15, 2))) / 2, 15, -(Math.sqrt(Math.pow(30, 2) - Math.pow(15, 2))) / 2, 0, (Math.sqrt(Math.pow(30, 2) - Math.pow(15, 2))) / 2);
		fond.setFill(Color.ORANGE);
		Polygon vitre = new Polygon(-12, (-(Math.sqrt(Math.pow(30, 2) - Math.pow(15, 2))) / 2) + Math.sqrt(3), 8, (-(Math.sqrt(Math.pow(30, 2) - Math.pow(15, 2))) / 2) + Math.sqrt(3), -2, (Math.sqrt(Math.pow(20, 2)) / 2) - Math.sqrt(3) );
		vitre.setFill(Color.LIGHTBLUE);
		((Group) noeud).getChildren().addAll(fond, vitre);
		noeud.setRotate(135);
	}
	
	public Node getNoeud()
	{
		return noeud;
	}
	
	/**
	 * Met � jour le noeud repr�sentant le vaisseau
	 */
	public void maj()
	{
		noeud.setRotate(direction.getAngle() / 2 / Math.PI * 360 + 135);
	}
	
	public int getRayonCollision()
	{
		return 20;
	}
}