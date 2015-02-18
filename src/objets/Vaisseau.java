package objets;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import modele.Corps;
import utils.Vecteur;
import vue.Dessinable;

public class Vaisseau implements Corps, Dessinable
{

	public final static double CONVERTION_CARBURANT = 1;
	private double puissanceMax;
	private double puissance;
	private Vecteur direction;
	private double masseVaisseau;
	private double capaciteCarburant;
	private DoubleProperty carburant;
	private DoubleProperty positionX;
	private DoubleProperty positionY;
	private boolean statique;
	private Vecteur vitesse;

	public Vaisseau(double pPuissanceMax, Vecteur pDirection,
			double pMasseVaisseau, double pCapaciteCarburant,
			Vecteur pPosition, boolean pStatique, Vecteur pVitesse)
	{
		puissanceMax = pPuissanceMax;
		puissance = puissanceMax;
		direction = pDirection;
		masseVaisseau = pMasseVaisseau;
		capaciteCarburant = pCapaciteCarburant;
		carburant = new SimpleDoubleProperty(capaciteCarburant);
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
		// TODO Story 12
	}

	public void tournerDroite()
	{
		// TODO Story 12
	}

	public double getPuissanceMax()
	{
		return puissanceMax;
	}

	public void setPuissanceMax(double pPuissanceMax)
	{
		puissanceMax = pPuissanceMax;
	}

	public double getPuissance()
	{
		return puissance;
	}

	public void setPuissance(double pPuissance)
	{
		puissance = pPuissance;
	}

	@Override
	public double getMasse()
	{
		return masseVaisseau + CONVERTION_CARBURANT * carburant.get();
	}

	public void setMasseVaisseau(double pMasseVaisseau)
	{
		masseVaisseau = pMasseVaisseau;
	}

	@Override
	public double getPositionX()
	{
		return positionX.get();
	}

	@Override
	public double getPositionY()
	{
		return positionY.get();
	}

	@Override
	public void setPositionX(double pPositionX)
	{
		positionX.set(pPositionX);

	}

	@Override
	public void setPositionY(double pPositionY)
	{
		positionY.set(pPositionY);

	}

	@Override
	public DoubleProperty getPositionXProperty()
	{
		return positionX;
	}

	@Override
	public DoubleProperty getPositionYProperty()
	{
		return positionY;
	}

	@Override
	public Vecteur getPosition()
	{
		return new Vecteur(positionX.get(), positionY.get());
	}

	@Override
	public void setPosition(Vecteur pPosition)
	{
		positionX.set(pPosition.getX());
		positionY.set(pPosition.getY());

	}

	@Override
	public boolean isStatique()
	{
		return statique;
	}

	@Override
	/**
	 * Ne pas mettre True sinon le vaisseau reste immobile
	 */
	public void setStatique(boolean pStatique)
	{
		statique = pStatique;
	}

	@Override
	public Vecteur getVitesse()
	{
		if (statique)
			return new Vecteur();
		return vitesse;
	}

	@Override
	public void setVitesse(Vecteur pVitesse)
	{
		vitesse = pVitesse;

	}

	@Override
	public Vecteur getForceExt()
	{
		// TODO Story canon
		return new Vecteur();
	}

	@Override
	public Node getNoeud()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
