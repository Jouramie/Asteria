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
	private Vecteur position;
	private DoubleProperty positionX;
	private DoubleProperty positionY;
	private boolean statique;
	private Vecteur vitesse;

	public Planete(double pMasse, Vecteur pPosition, boolean pStatique,
			Vecteur pVitesse)
	{
		masse = pMasse;
		if (pPosition == null)
			position = new Vecteur();
		else
			position = pPosition;
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

	public void setPosition(Vecteur pPosition)
	{
		position = pPosition;
	}

	public void setStatique(boolean pStatique)
	{
		statique = pStatique;
	}

	public void setVitesse(Vecteur pVitesse)
	{
		vitesse = pVitesse;
	}

	public Node getNoeud()
	{
		// TODO Auto-generated method stub
		return null;
	}


	public double getMasse()
	{
		return masse;
	}


	public Vecteur getForceExt()
	{
		// TODO
		return null;
	}


	public Vecteur getPosition()
	{
		return position;
	}
	
	public boolean isStatique()
	{
		return statique;
	}

	public Vecteur getVitesse()
	{
		return vitesse;
	}
}
