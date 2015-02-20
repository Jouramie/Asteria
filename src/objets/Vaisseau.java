package objets;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import utils.Vecteur;

public class Vaisseau extends ObjetSpatial
{

	public final static double CONVERTION_CARBURANT = 1;
	private double puissanceMax;
	private double puissance;
	private Vecteur direction;
	private double masse;
	private double capaciteCarburant;
	private DoubleProperty carburant;

	/**
	 * Constructeur de vaisseau, prend un vecteur pour la position
	 * 
	 * @param pPuissanceMax la puissance maximale du vaisseau
	 * @param pDirection la direction du vaisseau
	 * @param pMasse la masse du vaisseau
	 * @param pCapaciteCarburant la capacité maximale de caburant du vaisseau
	 * @param pPosition la position du vaisseau
	 * @param pVitesse la vitesse du vaisseau
	 */
	public Vaisseau(double pPuissanceMax, Vecteur pDirection,
			double pMasse, double pCapaciteCarburant,
			Vecteur pPosition, Vecteur pVitesse)
	{
		super(pMasse, pPosition, false, pVitesse);
		puissanceMax = pPuissanceMax;
		puissance = puissanceMax;
		direction = pDirection;
		capaciteCarburant = pCapaciteCarburant;
		carburant = new SimpleDoubleProperty(capaciteCarburant);
	}
	
	/**
	 * Constructeur de vaisseau, prend des doubles pour la position
	 * 
	 * @param pPuissanceMax la puissance maximale du vaisseau
	 * @param pDirection la direction du vaisseau
	 * @param pMasse la masse du vaisseau
	 * @param pCapaciteCarburant la capacité maximale de caburant du vaisseau
	 * @param pPositionX la positionX de la planète
	 * @param pPositionY la positionY de la planète 
	 * @param pVitesse la vitesse du vaisseau
	 */
	public Vaisseau(double pPuissanceMax, Vecteur pDirection,
			double pMasse, double pCapaciteCarburant,
			double pPositionX, double pPositionY, Vecteur pVitesse)
	{
		super(pMasse, pPositionX, pPositionY, false, pVitesse);
		puissanceMax = pPuissanceMax;
		puissance = puissanceMax;
		direction = pDirection;
		capaciteCarburant = pCapaciteCarburant;
		carburant = new SimpleDoubleProperty(capaciteCarburant);
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
		puissanceMax = pPuissanceMax;
	}

	public double getPuissance()
	{
		return puissance;
	}

	public void setPuissance(double pPuissance)
	{
		puissance = pPuissance;
	}

	@Override
	public double getMasse()
	{
		return masse + CONVERTION_CARBURANT * carburant.get();
	}

	@Override
	/**
	 * Ne pas mettre True sinon le vaisseau reste immobile
	 */
	public void setStatique(boolean pStatique)
	{
		statique = pStatique;
	}
	
	@Override
	public Node getNoeud(){
		Polygon r = new Polygon(0, 0, 100, 0, 50, 150);
		r.setFill(Color.ORANGE);
		return r;
	}

}
