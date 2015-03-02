package utils;

/**
 * Classe utilitaire représentant un vecteur 2D.
 * 
 * @author Équipe Bolduc
 * @version 1.0
 */
public class Vecteur
{
	private double x;
	private double y;
	
	/**
	 * Constructeur par défaut. Crée un vecteur nul.
	 */
	public Vecteur()
	{
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Constructeur du vecteur.
	 * 
	 * @param x
	 *            Composante en X.
	 * @param y
	 *            Composante en Y.
	 */
	public Vecteur(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Modifie la valeur de la composante en X.
	 * 
	 * @param x
	 *            Composante en X.
	 */
	public void setX(double x)
	{
		this.x = x;
	}
	
	/**
	 * Modifie la valeur de la composante en Y.
	 * 
	 * @param y
	 *            Composante en Y.
	 */
	public void setY(double y)
	{
		this.y = y;
	}
	
	/**
	 * Retourne la composante en X.
	 * 
	 * @return Composante en X.
	 */
	public double getX()
	{
		return this.x;
	}
	
	/**
	 * Retourne la composante en Y.
	 * 
	 * @return Composante en Y.
	 */
	public double getY()
	{
		return this.y;
	}
	
	/**
	 * Retourne l'angle en radians du vecteur, par rapport à l'horizontale.
	 * 
	 * @return Angle en radian.
	 */
	public double getAngle()
	{
		double resultat = 0.0;
		
		if (getX() != 0)
		{
			resultat = Math.atan(getY() / getX());
		}
		
		else if (getY() == 0)
		{
			resultat = 0;
		}
		
		else if (getY() > 0)
		{
			resultat = Math.PI / 2;
		}
		
		else
		{
			resultat = 3 * Math.PI / 2;
		}
		
		if (x < 0.0 && y < 0.0)
		{
			resultat += Math.PI;
		}
		else if (y < 0.0)
		{
			resultat += Math.PI * 2;
		}
		else if (x < 0.0)
		{
			resultat += Math.PI;
		}
		return resultat;
	}
	
	/**
	 * Retourne la norme (la grandeur) du vecteur.
	 * 
	 * @return Norme du vecteur.
	 */
	public double getNorme()
	{
		return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2));
	}
	
	/**
	 * Additionne deux vecteurs ensembles et retourne un nouveau vecteur
	 * représentant le résultat de la somme.
	 * 
	 * @param v
	 *            Vecteur à additionner
	 * @return Résultat de la somme.
	 */
	public Vecteur additionner(Vecteur v)
	{
		Vecteur resultat = new Vecteur();
		
		resultat.setX(getX() + v.getX());
		resultat.setY(getY() + v.getY());
		
		return resultat;
	}
	
	/**
	 * Soustrait deux vecteurs ensembles et retourne un nouveau vecteur
	 * représentant le résultat de la somme.
	 * 
	 * @param v
	 *            Vecteur à soustraire.
	 * @return Résultat de la somme.
	 */
	public Vecteur soustraire(Vecteur v)
	{
		Vecteur resultat = new Vecteur();
		
		resultat.setX(getX() - v.getX());
		resultat.setY(getY() - v.getY());
		
		return resultat;
	}
	
	/**
	 * Multiplie le vecteur par un scalaire. Retourne un nouveau vecteur
	 * représentant le résultat.
	 * 
	 * @param s
	 *            Scalaire à multiplier.
	 * @return Vecteur représentant le résultat.
	 */
	public Vecteur multiplication(double s)
	{
		Vecteur resultat = new Vecteur();
		
		resultat.setX(this.getX() * s);
		resultat.setY(this.getY() * s);
		
		return resultat;
	}
	
	/**
	 * Calcul le produit scalaire des deux vecteurs.
	 * 
	 * @param v
	 *            Vecteur à multiplier
	 * @return Produit scalaire du vecteur.
	 */
	public double multiplication(Vecteur v)
	{
		return this.getX() * v.getX() + this.getY() * v.getY();
	}
	
	/**
	 * Retourne un vecteur de même direction, mais dont la norme est de 1.
	 * 
	 * @return Vecteur normalisé.
	 */
	public Vecteur normaliser()
	{
		Vecteur resultat = new Vecteur();
		
		if (this.getNorme() != 0)
		{
			resultat.setX(this.getX() / this.getNorme());
			resultat.setY(this.getY() / this.getNorme());
		}
		
		return resultat;
	}
}
