package modele;

import objets.Vaisseau;
import utils.Vecteur;

/**
 * Classe repr�sentant un objectif qui est de placer le vaisseau dans un certain cercle.
 * @author EquBolduc
 * @version 1.0
 */
public class ObjectifRayon implements Objectif
{
	private Vaisseau vaisseau;
	private Vecteur posRayon;
	private double rayon;
	
	/**
	 * Constructeur de l'objectif.
	 * @param vaisseau Vaisseau sur lequel v�rifier l'objectif.
	 * @param posRayon Position du cercle.
	 * @param rayon Rayon du cercle.
	 */
	public ObjectifRayon(Vecteur posRayon, double rayon)
	{
		this.vaisseau = null;
		this.posRayon = posRayon;
		this.rayon = rayon;
	}

	/**
	 * Retourne une description sommaire de l'objectif.
	 */
	public String getDescription()
	{
		return "Atteignez l'objectif indiqu�.";
	}
	
	/**
	 * Retourne la position du rayon de l'objectif.
	 */
	public Vecteur getPosRayon()
	{
		return posRayon;
	}
	
	/**
	 * Retourne le rayon de l'objectif.
	 */
	public double getRayon()
	{
		return rayon;
	}
	
	/**
	 * Modifie le vaisseau sur lequel l'objectif est v�rifi�.
	 * @param vaisseau Le nouveau vaisseau sur lequel l'objectif est v�rifi�.
	 */
	public void setVaisseau(Vaisseau vaisseau)
	{
		if(vaisseau != null)
		{
			this.vaisseau = vaisseau;
		}
	}
	
	/**
	 * Modifie la position du rayon de l'objectif.
	 * @param posRayon La nouvelle position du rayon de l'objectif.
	 */
	public void setPosRayon(Vecteur posRayon)
	{
		if(posRayon != null)
		{
			this.posRayon = posRayon;
		}
	}
	
	/**
	 * Modifie le rayon de l'objectif.
	 * @param rayon Le nouveau rayon de l'objectif.
	 */
	public void setRayon(double rayon)
	{
		if(rayon > 0)
		{
			this.rayon = rayon;
		}
	}
	
	/**
	 * Retourne le vaisseau sur lequel l'objectif est v�rifi�.
	 */
	public Vaisseau getVaisseau()
	{
		return vaisseau;
	}

	/**
	 * V�rifie si le vaisseau se retrouve dans le cercle prescrit.
	 */
	public boolean verifierObjectif()
	{
		boolean resultat = false;
		
		if(vaisseau != null && vaisseau.getPosition().soustraire(posRayon).getNorme() < rayon)
		{
			resultat = true;
		}
		
		return resultat;
	}
}
