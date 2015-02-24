package objets;

import utils.Vecteur;

/**
 * Classe repr�sentant une plan�te.
 * @author EquBolduc
 * @version 1.0
 */
public class Planete extends ObjetSpatial
{

	/**
	 * Constructeur de plan�te, prend un vecteur pour la position
	 * 
	 * @param pMasse la masse de la plan�te
	 * @param pPosition la position de la plan�te
	 */
	public Planete(double pMasse, Vecteur pPosition)
	{
		super(pMasse, pPosition, true, new Vecteur());
	}

	/**
	 * Constructeur de plan�te, prend des doubles pour la position
	 * 
	 * @param pMasse la masse de la plan�te
	 * @param pPositionX la positionX de la plan�te
	 * @param pPositionY la positionY de la plan�te
	 */
	public Planete(double pMasse, double pPositionX, double pPositionY)
	{
		super(pMasse, pPositionX, pPositionY, true, new Vecteur());
	}
}
