package modele;

import objets.Vaisseau;
import utils.Vecteur;

/**
 * Classe représentant un objectif qui est de placer le vaisseau dans un certain cercle.
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
	 * @param vaisseau Vaisseau sur lequel vérifier l'objectif.
	 * @param posRayon Position du cercle.
	 * @param rayon Rayon du cercle.
	 */
	public ObjectifRayon(Vaisseau vaisseau, Vecteur posRayon, double rayon)
	{
		this.vaisseau = vaisseau;
		this.posRayon = posRayon;
		this.rayon = rayon;
	}

	/**
	 * Retourne une description sommaire de l'objectif.
	 */
	public String getDescription()
	{
		return "Atteignez l'objectif indiqué.";
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
	 * Modifie le vaisseau sur lequel l'objectif est vérifié.
	 * @param vaisseau Le nouveau vaisseau sur lequel l'objectif est vérifié.
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
	 * Retourne le vaisseau sur lequel l'objectif est vérifié.
	 */
	public Vaisseau getVaisseau()
	{
		return vaisseau;
	}

	/**
	 * Vérifie si le vaisseau se retrouve dans le cercle prescrit.
	 */
	public boolean verifierObjectif()
	{
		boolean resultat = false;
		
		if(vaisseau.getPosition().soustraire(posRayon).getNorme() < rayon)
		{
			resultat = true;
		}
		
		return resultat;
	}
}
