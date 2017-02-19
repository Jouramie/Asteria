package vue;

import utils.Vecteur;

/**
 * Classe responsable de la camera.
 * 
 * @author EquBoldus
 * @version 1.0
 */
public class Camera
{
	/**
	 * Defini la vitesse du zoom.
	 */
	public static final double VITESSE_ZOOM = 10.0;
	
	private double largeur;
	private double hauteur;
	private double targetX;
	private double targetY;
	private double x;
	private double y;
	private double facteur;
	private double targetFacteur;
	
	/**
	 * Constructeur de la camera. La largeur et la hauteur de la fenetre sont de
	 * 0 par defaut. Elles devraient etre modifie afin de faire fonctionner la
	 * camera.
	 */
	public Camera()
	{
		largeur = 0.0;
		hauteur = 0.0;
		targetX = 0.0;
		targetY = 0.0;
		x = 0.0;
		y = 0.0;
		facteur = 1.0;
		targetFacteur = 1.0;
	}
	
	/**
	 * Constructeur prenant
	 * 
	 * @param pLargeur Largeur de la fenetre.
	 * @param pHauteur Hauteur de la fenetre.
	 */
	public Camera(double pLargeur, double pHauteur)
	{
		this();
		setGrandeurs(pLargeur, pHauteur);
	}
	
	/**
	 * Ajuste la grandeur de la fenetre.
	 * 
	 * @param pLargeur Largeur de la fenetre.
	 * @param pHauteur Hauteur de la fenetre.
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
	 * Deplace la camera a l'endroit specifier.
	 * 
	 * @param pX Position X.
	 * @param pY Position Y.
	 */
	public void deplacer(double pX, double pY)
	{
		targetX = pX;
		targetY = pY;
	}
	
	/**
	 * Retourne le vecteur representant la position actuelle de la camera.
	 * 
	 * @return Vecteur de la position de la camera.
	 */
	public Vecteur getDeplacement()
	{
		return new Vecteur(targetX, targetY);
	}
	
	/**
	 * Zoom la camera selon la facteur choisi. 1.0 signifie 1px vaut 1m. 2.0
	 * signifie 1px vaut 2m. 0.5 signifie 1px vaut 0.5m.
	 * 
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
	 * Transforme un point de la fenetre dans le systeme de reference des corps.
	 * 
	 * @param vec Point dans l'espace de la fenetre.
	 * @return Vecteur representant le point de le systeme de reference des
	 *         corps.
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
	 * Retourne la translation qui doit etre appliquee sur les corps.
	 * 
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
	 * 
	 * @return Facteur de zoom.
	 */
	public double getFacteur()
	{
		return facteur;
	}
	
	/**
	 * Met a jour la camera. Sert a creer des transitions fluides.
	 * 
	 * @param dt Temps ecoule depuis le dernier frame.
	 */
	public void update(double dt)
	{
		facteur = bezier(dt, facteur, targetFacteur, VITESSE_ZOOM);
		x = bezier(dt, x, targetX, VITESSE_ZOOM);
		y = bezier(dt, y, targetY, VITESSE_ZOOM);
	}
	
	/**
	 * Cree une courbe pseudo-bezier pour les transitions.
	 * 
	 * @param dt Temps ecoule depuis le dernier frame.
	 * @param valeur Valeur actuelle.
	 * @param cible Valeur cible.
	 * @param vitesse Vitesse a laquelle il faut atteindre la valeur.
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
