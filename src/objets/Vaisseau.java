package objets;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import modele.Corps;
import utils.Vecteur;
import vue.Dessinable;

public class Vaisseau implements Corps, Dessinable
{
	private double puissanceMax;
	private double puissance;
	private Vecteur direction;
	private double masseVide;
	private double capaciteCarburant;
	private DoubleProperty masseCarburant;
	private DoubleProperty positionX;
	private DoubleProperty positionY;
	private boolean statique;
	private Vecteur vitesse;

	public Vaisseau(double pPuissanceMax, Vecteur pDirection,
			double pMasseVide, double pCapaciteCarburant, Vecteur pPosition,
			boolean pStatique, Vecteur pVitesse)
	{
		puissanceMax = pPuissanceMax;
		puissance = puissanceMax;
		direction = new Vecteur();
		masseVide = pMasseVide;
		capaciteCarburant = pCapaciteCarburant;
		masseCarburant = new SimpleDoubleProperty(capaciteCarburant);
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

	public void tournerGauche()
	{
		// TODO
	}

	public void tournerDroite()
	{
		// TODO
	}

	public void setPuissance(double p)
	{
		// TODO
	}

	public double getMasse()
	{
		return masseVide;
	}

	public void setMasse(double pMasseVide)
	{

	}

	@Override
	public double getPositionX()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPositionY()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPositionX(double pPositionX)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setPositionY(double pPositionX)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public DoubleProperty getPositionXProperty()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleProperty getPositionYProperty()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vecteur getPosition()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPosition(Vecteur pPosition)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isStatique()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setStatique(boolean pStatique)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Vecteur getVitesse()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVitesse(Vecteur pVitesse)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Vecteur getForceExt()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getNoeud()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
