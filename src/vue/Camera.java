package vue;

import utils.Vecteur;

public class Camera
{
	private double largeur;
	private double hauteur;
	private double x;
	private double y;
	private double targetX;
	private double targetY;
	private double facteur;
	
	public Camera()
	{
		largeur = 0.0;
		hauteur = 0.0;
		x = 0.0;
		y = 0.0;
		targetX = 0.0;
		targetY = 0.0;
		facteur = 1.0;
	}
	
	public Camera(double pLargeur, double pHauteur)
	{
		this();
		setGrandeurs(pLargeur, pHauteur);
	}
	
	public void setGrandeurs(double pLargeur, double pHauteur)
	{
		if(pLargeur > 0)
		{
			largeur = pLargeur;
		}
		
		if(pHauteur > 0)
		{
			hauteur = pHauteur;
		}
	}
	
	public void déplacer(double pX, double pY)
	{
		targetX = pX;
		targetY = pY;
	}
	
	public void zoomer(double pFacteur)
	{
		if(pFacteur > 0)
		{
			facteur = pFacteur;
		}
	}
	
	public Vecteur getTranslation()
	{
		Vecteur resultat = new Vecteur();
		
		double posX = (1.0 / facteur) * largeur / 2 - x;
		double posY = (1.0 / facteur) * hauteur / 2 - y;
		
		resultat.setX(posX);
		resultat.setY(posY);
		
		return resultat;
	}
	
	public double getFacteur()
	{
		return facteur;
	}
	
	public void update(double dt)
	{
		x = targetX;
		y = targetY;
	}
}
