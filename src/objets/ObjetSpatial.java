package objets;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import modele.Corps;
import utils.Vecteur;
import vue.Dessinable;

/**
 * Classe de base pour les objets spatiaux.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public abstract class ObjetSpatial implements Corps, Dessinable
{
	/**
	 * Masse par defaut des corps.
	 */
	public final static double MASSE_DEFAUT = 1.0;
	
	protected double masse;
	protected DoubleProperty positionX;
	protected DoubleProperty positionY;
	protected boolean statique;
	protected Vecteur vitesse;
	
	protected double positionXDepart;
	protected double positionYDepart;
	protected Vecteur vitesseDepart;
	
	/**
	 * Constructeur d'objet spatial, prend un vecteur pour la positon
	 * 
	 * @param pMasse la masse de l'objet (1 par defaut)
	 * @param pPosition le vecteur position de l'objet
	 * @param pStatique la staticite de l'objet
	 * @param pVitesse le vecteur vitesse de l'objet
	 */
	public ObjetSpatial(double pMasse, Vecteur pPosition, boolean pStatique,
			Vecteur pVitesse)
	{
		if(pPosition == null)
		{
			init(pMasse, 0, 0, pStatique, pVitesse);
		}
		else
		{
			init(pMasse, pPosition.getX(), pPosition.getY(), pStatique,
					pVitesse);
		}
	}
	
	/**
	 * Constructeur d'objet spatial, prend des doubles pour la position
	 * 
	 * @param pMasse la masse de l'objet (1 par defaut)
	 * @param pPositionX la position en X de l'objet
	 * @param pPositionY la position en Y de l'objet
	 * @param pStatique la staticite de l'objet
	 * @param pVitesse le vecteur vitesse de l'objet
	 */
	public ObjetSpatial(double pMasse, double pPositionX, double pPositionY,
			boolean pStatique, Vecteur pVitesse)
	{
		init(pMasse, pPositionX, pPositionY, pStatique, pVitesse);
	}
	
	/**
	 * Initialise les attibuts de la classe.
	 */
	private void init(double pMasse, double pPositionX, double pPositionY,
			boolean pStatique, Vecteur pVitesse)
	{
		setMasse(pMasse);
		positionX = new SimpleDoubleProperty(pPositionX);
		positionXDepart = pPositionX;
		positionY = new SimpleDoubleProperty(pPositionY);
		positionYDepart = pPositionY;
		setStatique(pStatique);
		setVitesse(pVitesse, true);
	}
	
	public double getMasse()
	{
		return masse;
	}
	
	/**
	 * Modifie la masse de l'objet, masse minimum : 1.0 .
	 */
	public void setMasse(double pMasse)
	{
		if (pMasse < 1)
		{
			if (this instanceof Vaisseau)
				masse = Vaisseau.MASSE_DEFAUT;
			else if (this instanceof Planete)
				masse = Planete.MASSE_DEFAUT;
			else
				masse = MASSE_DEFAUT;
		}
		else
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
	
	/**
	 * Si pDepart est vrai, modifie aussi la position de depart.
	 */
	public void setPositionX(double pPositionX, boolean pDepart)
	{
		if(pDepart)
		{
			positionXDepart = pPositionX;
		}
		setPositionX(pPositionX);
	}
	
	public void setPositionY(double pPositionY)
	{
		positionY.set(pPositionY);
	}
	
	/**
	 * Si pDepart est vrai, modifie aussi la position de depart.
	 */
	public void setPositionY(double pPositionY, boolean pDepart)
	{
		if(pDepart)
		{
			positionYDepart = pPositionY;
		}
		setPositionY(pPositionY);
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
	
	/**
	 * Si le vecteur est null, met la position a 0.
	 */
	public void setPosition(Vecteur pPosition)
	{
		if(pPosition == null)
		{
			positionX.set(0);
			positionY.set(0);
		}
		else
		{
			positionX.set(pPosition.getX());
			positionY.set(pPosition.getY());
		}
	}
	
	/**
	 * Si pDepart est vrai, modifie aussi la position de depart.
	 */
	public void setPosition(Vecteur pPosition, boolean pDepart)
	{
		if(pDepart)
			if(pPosition == null)
			{
				positionXDepart = 0;
				positionYDepart = 0;
			}
			else
			{
				positionXDepart = pPosition.getX();
				positionYDepart = pPosition.getY();
			}
		setPosition(pPosition);
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
		if(statique)
			return new Vecteur();
		return vitesse;
	}
	
	/**
	 * Si le vecteur est null, met la vitesse a 0.
	 */
	public void setVitesse(Vecteur pVitesse)
	{


		if(pVitesse == null)
			vitesse = new Vecteur();
		else
			vitesse = pVitesse;
	}
	
	/**
	 * Si pDepart est vrai, modifie la vitesse de depart.
	 */
	public void setVitesse(Vecteur pVitesse, boolean pDepart)
	{

		if(pDepart)
			if(pVitesse == null)
				vitesseDepart = new Vecteur();
			else
				vitesseDepart = pVitesse.clone();
		setVitesse(pVitesse);
	}
	
	public abstract Vecteur getForceExt();
	
	/**
	 * Remet les corps a leur position et leur vitesse de depart.
	 */
	public void reset()
	{
		setPositionX(positionXDepart);
		setPositionY(positionYDepart);
		setVitesse(vitesseDepart);
	}
	
}
