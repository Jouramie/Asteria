package objets;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import modele.Corps;
import utils.Vecteur;
import vue.Dessinable;

public class Planete implements Corps, Dessinable
{
	private double masse;
	private DoubleProperty positionX;
	private DoubleProperty positionY;
	private boolean statique;
	private Vecteur vitesse;

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

	public double getMasse()
	{
		return masse;
	}

	public void setMasse(double pMasse)
	{
		masse = pMasse;
	}

	public double getPositionX()
	{
		return positionX.get();
	}

	public double getPositionY()
	{
		return positionY.get();
	}

	public void setPositionX(double pPositionX)
	{
		positionX.set(pPositionX);		
	}

	public void setPositionY(double pPositionY)
	{
		positionY.set(pPositionY);		
	}

	public DoubleProperty getPositionXProperty()
	{
		return positionX;
	}

	public DoubleProperty getPositionYProperty()
	{
		return positionY;
	}

	public Vecteur getPosition()
	{
		return new Vecteur(positionX.get(), positionY.get());
	}

	public void setPosition(Vecteur pPosition)
	{
		if (pPosition == null)
		{
			positionX = new SimpleDoubleProperty();
			positionY = new SimpleDoubleProperty();
		} else
		{
			positionX = new SimpleDoubleProperty(pPosition.getX());
			positionY = new SimpleDoubleProperty(pPosition.getY());
		}
	}

	public boolean isStatique()
	{
		return statique;
	}

	public void setStatique(boolean pStatique)
	{
		statique = pStatique;
	}

	
	/**
	 * Ne pas utiliser si la planète est statique
	 */
	public Vecteur getVitesse()
	{
		if(statique)
			return new Vecteur();
		return vitesse;
	}

	/**
	 * Ne pas utiliser si la planète est statique
	 */
	public void setVitesse(Vecteur pVitesse)
	{
		vitesse = pVitesse;
	}

	public Vecteur getForceExt()
	{
		// TODO Story asteroïde
		return null;
	}

	public Node getNoeud()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
