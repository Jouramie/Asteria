package objets;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.paint.Color;
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
	 * Densité (en kg/m) des planètes. TODO: Mofidier la valeur pour qu'elle
	 * soit cohérente.
	 */
	public final static double DENSITE = 1.0 / 2.0;
	/**
	 * Masse par défaut des corps.
	 */
	public final static double MASSE_DEFAUT = 1.0;
	
	protected double masse;
	protected DoubleProperty positionX;
	protected DoubleProperty positionY;
	protected boolean statique;
	protected Vecteur vitesse;
	protected Color couleur;
	
	/**
	 * Constructeur d'objet spatial, prend un vecteur pour la positon
	 * 
	 * @param pMasse
	 *            la masse de l'objet (1 par défaut)
	 * @param pPosition
	 *            le vecteur position de l'objet
	 * @param pStatique
	 *            la staticité de l'objet
	 * @param pVitesse
	 *            le vecteur vitesse de l'objet
	 */
	public ObjetSpatial(double pMasse, Vecteur pPosition, boolean pStatique,
			Vecteur pVitesse)
	{
		setMasse(pMasse);
		positionX = new SimpleDoubleProperty();
		positionY = new SimpleDoubleProperty();
		setPosition(pPosition);
		setStatique(pStatique);
		setVitesse(pVitesse);
	}
	
	/**
	 * Constructeur d'objet spatial, prend des doubles pour la position
	 * 
	 * @param pMasse
	 *            la masse de l'objet (1 par défaut)
	 * @param pPositionX
	 *            la position en X de l'objet
	 * @param pPositionY
	 *            la position en Y de l'objet
	 * @param pStatique
	 *            la staticité de l'objet
	 * @param pVitesse
	 *            le vecteur vitesse de l'objet
	 */
	public ObjetSpatial(double pMasse, double pPositionX, double pPositionY,
			boolean pStatique, Vecteur pVitesse)
	{
		setMasse(pMasse);
		positionX = new SimpleDoubleProperty(pPositionX);
		positionY = new SimpleDoubleProperty(pPositionY);
		setStatique(pStatique);
		setVitesse(pVitesse);
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
		if (pMasse <= 0)
		{
			masse = MASSE_DEFAUT;
		}
		else
		{
			masse = pMasse;
		}
		
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
	
	/**
	 * Si le vecteur est null, met la position à 0.
	 */
	public void setPosition(Vecteur pPosition)
	{
		if (pPosition == null)
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
	
	/**
	 * Si le vecteur est null, met la vitesse à 0.
	 */
	public void setVitesse(Vecteur pVitesse)
	{
		if (pVitesse == null)
		{
			vitesse = new Vecteur();
		}
		else
			vitesse = pVitesse;
	}
	
	public Vecteur getForceExt()
	{
		// TODO
		return new Vecteur();
	}
	
	/**
	 * Met à jour le noeud
	 */
	public void maj()
	{
		
	}
	
	/**
	 * Retourne la forme représentant le corps.
	 */
	public abstract Node getNoeud();
}
