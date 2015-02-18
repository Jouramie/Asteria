package objets;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import utils.Vecteur;
import vue.Dessinable;
import modele.Corps;

public abstract class ObjetSpacial implements Corps, Dessinable
{

	protected double masse;
	protected DoubleProperty positionX;
	protected DoubleProperty positionY;
	protected boolean statique;
	protected Vecteur vitesse;

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
	 * Retourne un vecteur nul si l'objet est statique
	 */
	public Vecteur getVitesse()
	{
		if (statique)
			return new Vecteur();
		return vitesse;
	}

	public void setVitesse(Vecteur pVitesse)
	{
		vitesse = pVitesse;
	}

	public Vecteur getForceExt()
	{
		// TODO
		return new Vecteur();
	}

	public Node getNoeud()
	{
		return new Circle(positionX.get(), positionY.get(), Math.sqrt(masse
				/ Math.PI));
	}

}
