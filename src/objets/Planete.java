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

	public Planete(double pMasse, Vecteur pPosition, boolean pStatique,
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
		statique = pStatique;
		if (pVitesse == null)
			vitesse = new Vecteur();
		else
			vitesse = pVitesse;
	}

	public Planete(double pMasse, double pPositionX, double pPositionY,
			boolean pStatique, Vecteur pVitesse)
	{
		masse = pMasse;
		positionX = new SimpleDoubleProperty(pPositionX);
		positionY = new SimpleDoubleProperty(pPositionY);
		statique = pStatique;
		if (pVitesse == null)
			vitesse = new Vecteur();
		else
			vitesse = pVitesse;
	}

	public void setMasse(double pMasse)
	{
		masse = pMasse;
	}

	public double getMasse()
	{
		return masse;
	}

	public double getPositionX()
	{
		return positionX.get();
	}

	public double getPositionY()
	{
		return positionY.get();
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

	public void setStatique(boolean pStatique)
	{
		statique = pStatique;
	}

	public boolean isStatique()
	{
		return statique;
	}

	public void setVitesse(Vecteur pVitesse)
	{
		vitesse = pVitesse;
	}

	public Vecteur getVitesse()
	{
		return vitesse;
	}

	public Vecteur getForceExt()
	{
		// TODO
		return null;
	}

	public Node getNoeud()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
