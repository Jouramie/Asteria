package vue;

import utils.Vecteur;

/**
 * Classe responsable de la caméra.
 * @author EquBoldus
 * @version 1.0
 */
public class Camera
{
	/**
	 * Défini la vitesse du zoom.
	 */
	public static final double VITESSE_ZOOM = 15.0;
	
	private double largeur;
	private double hauteur;
	private double x;
	private double y;
	private double targetX;
	private double targetY;
	private double facteur;
	private double targetFacteur;
	
	/**
	 * Constructeur de la caméra.
	 * La largeur et la hauteur de la fenêtre sont de 0 par défaut.
	 * Elles devraient être modifié afin de faire fonctionner la caméra.
	 */
	public Camera()
	{
		largeur = 0.0;
		hauteur = 0.0;
		x = 0.0;
		y = 0.0;
		targetX = 0.0;
		targetY = 0.0;
		facteur = 1.0;
		targetFacteur = 1.0;
	}
	
	/**
	 * Constructeur prenant 
	 * @param pLargeur Largeur de la fenêtre.
	 * @param pHauteur Hauteur de la fenêtre.
	 */
	public Camera(double pLargeur, double pHauteur)
	{
		this();
		setGrandeurs(pLargeur, pHauteur);
	}
	
	/**
	 * Ajuste la grandeur de la fenêtre.
	 * @param pLargeur Largeur de la fenêtre.
	 * @param pHauteur Hauteur de la fenêtre.
	 */
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
	
	/**
	 * Déplace la caméra à l'endroit spécifier.
	 * @param pX Position X.
	 * @param pY Position Y.
	 */
	public void deplacer(double pX, double pY)
	{
		targetX = pX;
		targetY = pY;
	}
	
	/**
	 * Zoom la caméra selon la facteur choisi.
	 * 1.0 signifie 1px vaut 1m.
	 * 2.0 signifie 1px vaut 2m.
	 * 0.5 signifie 1px vaut 0.5m.
	 * @param pFacteur Facteur de zoom.
	 */
	public void zoomer(double pFacteur)
	{
		if(pFacteur > 0)
		{
			targetFacteur = pFacteur;
		}
	}
	
	/**
	 * Transforme un point de la fenêtre dans le système de référence des corps.
	 * @param vec Point dans l'espace de la fenêtre.
	 * @return Vecteur représentant le point de le système de référence des corps.
	 */
	public Vecteur localToGlobal(Vecteur vec)
	{
		Vecteur resultat = new Vecteur();
		
		double posX = (vec.getX() / facteur) + x - (largeur / (2 * facteur));
		double posY = (vec.getY() / facteur) + y - (hauteur / (2 * facteur));
		
		resultat.setX(posX);
		resultat.setY(posY);
		
		return resultat;
	}
	
	/**
	 * Retourne la translation qui doit être appliquée sur les corps.
	 * @return Vecteur de transformation.
	 */
	public Vecteur getTranslation()
	{
		Vecteur resultat = new Vecteur();
		
		double posX = (1.0 / facteur) * largeur / 2 - x;
		double posY = (1.0 / facteur) * hauteur / 2 - y;
		
		resultat.setX(posX);
		resultat.setY(posY);
		
		return resultat;
	}
	
	/**
	 * Retourne le facteur de zoom.
	 * @return Facteur de zoom.
	 */
	public double getFacteur()
	{
		return facteur;
	}
	
	/**
	 * Met à jour la caméra. Sert à créer des transitions fluides.
	 * @param dt Temps écoulé depuis le dernier frame.
	 */
	public void update(double dt)
	{
		x = targetX;
		y = targetY;
		
		facteur = bezier(dt, facteur, targetFacteur, VITESSE_ZOOM);
	}
	
	/**
	 * Crée une courbe pseudo-bézier pour les transitions.
	 * @param dt Temps écoulé depuis le dernier frame.
	 * @param valeur Valeur actuelle.
	 * @param cible Valeur cible.
	 * @param vitesse Vitesse à laquelle il faut atteindre la valeur.
	 * @return Nouvelle valeur actuelle.
	 */
	private double bezier(double dt, double valeur, double cible, double vitesse)
	{
		double resultat = valeur;
		
		double diff = cible - valeur;
		if(diff > 0)
		{
			resultat += Math.min(diff, vitesse * diff * dt);
		}
		
		else if(diff < 0)
		{
			resultat -= Math.max(diff, vitesse * -diff * dt);
		}
		
		return resultat;
	}
}
