package objets;

import javafx.beans.property.SimpleDoubleProperty;
import utils.Vecteur;

public class Planete extends ObjetSpatial
{

	public Planete(double pMasse, Vecteur pPosition)
	{
		masse = pMasse;
		if (pPosition == null)
		{
			positionX = new SimpleDoubleProperty();
			positionY = new SimpleDoubleProperty();
		} else
		{
			positionX = new SimpleDoubleProperty(pPosition.getX());
			positionY = new SimpleDoubleProperty(pPosition.getY());
		}
		statique = true;
		vitesse = new Vecteur();
	}

	public Planete(double pMasse, double pPositionX, double pPositionY)
	{
		masse = pMasse;
		positionX = new SimpleDoubleProperty(pPositionX);
		positionY = new SimpleDoubleProperty(pPositionY);
		statique = true;
		vitesse = new Vecteur();
	}

	public Vecteur getForceExt()
	{
		// TODO Story asteroïde
		return null;
	}

}
