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
	
	double masse;
	protected DoubleProperty positionX;
	protected DoubleProperty positionY;
	protected boolean statique;
	protected Vecteur vitesse;
	protected Color couleur;
	
	protected double positionXDepart;
	protected double positionYDepart;
	protected Vecteur vitesseDepart;
	
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
		if (pPosition == null)
		{
			init(pMasse, 0, 0, pStatique,
					pVitesse);
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
		init(pMasse, pPositionX, pPositionY, pStatique, pVitesse);
	}
	
	/**
	 * Initialise les attibuts de la classe.
	 */
	private void init(double pMasse, double pPositionX, double pPositionY,
			boolean pStatique, Vecteur pVitesse)
	{
		masse = pMasse;
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
	
	/**
	 * Si pDepart est vrai, modifie aussi la position de départ.
	 */
	public void setPositionX(double pPositionX, boolean pDepart)
	{
		if (pDepart)
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
	 * Si pDepart est vrai, modifie aussi la position de départ.
	 */
	public void setPositionY(double pPositionY, boolean pDepart)
	{
		if (pDepart)
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
	
	/**
	 * Si pDepart est vrai, modifie aussi la position de départ.
	 */
	public void setPosition(Vecteur pPosition, boolean pDepart)
	{
		if (pDepart)
			if (pPosition == null)
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
	
	/**
	 * Si pDepart est vrai, modifie la vitesse de départ.
	 */
	public void setVitesse(Vecteur pVitesse, boolean pDepart)
	{
		if (pDepart)
			if (pVitesse == null)
			{
				vitesseDepart = new Vecteur();
			}
			else
				vitesseDepart = pVitesse.clone();
		setVitesse(pVitesse);
	}
	
	public abstract Vecteur getForceExt();
	
	/**
	 * Met à jour le noeud
	 * @param dt Temps écoulé depuis le dernier frame (en secondes).
	 */
	public abstract void maj(double dt);
	
	/**
	 * Retourne la forme représentant le corps.
	 */
	public abstract Node getNoeud();
	
	/**
	 * Remet les corps à leur position et leur vitesse de départ.
	 */
	public void reset()
	{
		setPositionX(positionXDepart);
		setPositionY(positionYDepart);
		setVitesse(vitesseDepart);
	}
	
	/**
	 * Callback lors d'une collision.
	 * @param c Corps avec lequel on est en collision.
	 */
	public void onCollision(Corps c)
	{
		
	}
}
