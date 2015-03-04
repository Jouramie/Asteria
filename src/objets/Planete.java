package objets;

import utils.Vecteur;

/**
 * Classe représentant une planète.
 * @author EquBolduc
 * @version 1.0
 */
public class Planete extends ObjetSpatial
{

	/**
	 * Constructeur de planète, prend un vecteur pour la position
	 * 
	 * @param pMasse la masse de la planète
	 * @param pPosition la position de la planète
	 */
	public Planete(double pMasse, Vecteur pPosition)
	{
		super(pMasse, pPosition, true, new Vecteur());
	}

	/**
	 * Constructeur de planète, prend des doubles pour la position
	 * 
	 * @param pMasse la masse de la planète
	 * @param pPositionX la positionX de la planète
	 * @param pPositionY la positionY de la planète
	 */
	public Planete(double pMasse, double pPositionX, double pPositionY)
	{
		super(pMasse, pPositionX, pPositionY, true, new Vecteur());
	}
	
	public int getRayonCollision()
	{
		return 100;
	}
}