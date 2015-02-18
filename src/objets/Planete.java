package objets;

import javafx.beans.property.SimpleDoubleProperty;
import utils.Vecteur;

public class Planete extends ObjetSpacial
{
	
	public Planete(double pMasse, Vecteur pPosition,
			Vecteur pVitesse)
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
		if (pVitesse == null)
			vitesse = new Vecteur();
		else
			vitesse = pVitesse;
	}

	public Planete(double pMasse, double pPositionX, double pPositionY, Vecteur pVitesse)
	{
		masse = pMasse;
		positionX = new SimpleDoubleProperty(pPositionX);
		positionY = new SimpleDoubleProperty(pPositionY);
		statique = true;
		if (pVitesse == null)
			vitesse = new Vecteur();
		else
			vitesse = pVitesse;
	}

	public Vecteur getForceExt()
	{
		// TODO Story asteroïde
		return null;
	}

}
