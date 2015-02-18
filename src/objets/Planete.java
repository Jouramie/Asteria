package objets;

import utils.Vecteur;

public class Planete extends ObjetSpatial
{

	public Planete(double pMasse, Vecteur pPosition)
	{
		super(pMasse, pPosition, true, new Vecteur());
	}

	public Planete(double pMasse, double pPositionX, double pPositionY)
	{
		super(pMasse, pPositionX, pPositionY, true, new Vecteur());
	}

	public Vecteur getForceExt()
	{
		// TODO Story asteroïde
		return null;
	}

}
