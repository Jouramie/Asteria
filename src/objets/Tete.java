package objets;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Vecteur;

/**
 * Classe repr�sentant une t�te.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class Tete extends ObjetSpatial
{
	private Texture texture;
	private double rayon;
	public static final Texture TEXTURE_DEFAUT = Texture.SIMONPIERRE;
	public static final double RAYON_DEFAUT = 100.0;
	
	/**
	 * Constructeur de la classe Tete.
	 * 
	 * @param pMasse Masse de la t�te.
	 * @param pRayon Rayon de la t�te.
	 * @param pPosition Position initiale de la t�te.
	 * @param pVitesse Vitesse initiale de la t�te.
	 */
	public Tete(double pMasse, double pRayon, Vecteur pPosition,
			Vecteur pVitesse)
	{
		super(pMasse, pPosition, false, pVitesse);
		init(pRayon, TEXTURE_DEFAUT);
	}
	
	/**
	 * Enum des textures possible pour une t�te.
	 * 
	 * @author EquBolduc
	 */
	public enum Texture
	{
		EMILE("/res/emile.png"), JEREMIE("/res/jeremie.png"), JONATHAN(
				"/res/jonathan.png"), SIMONPIERRE("/res/simon_pierre.png");
		
		private final String texture;
		
		/**
		 * Constructeur de texture.
		 * 
		 * @param pTexture l'emplacement du fichier contenant la texture.
		 */
		Texture(String pTexture)
		{
			texture = pTexture;
		}
		
		/**
		 * Retourne la texture selon son nom.
		 * 
		 * @param tex le nom de la texture recherch�.
		 * @return la texture.
		 */
		public static Texture getTexture(String tex)
		{
			switch(tex.toLowerCase())
			{
			case "emile":
				return Texture.EMILE;
				
			case "jeremie":
				return Texture.JEREMIE;
				
			case "jonathan":
				return Texture.JONATHAN;
				
			case "simonpierre":
				return Texture.SIMONPIERRE;
				
			default:
				return TEXTURE_DEFAUT;
			}
		}
		
		/**
		 * @return l'emplacement du fichier contenant la texture.
		 */
		public String getTexture()
		{
			return texture;
		}
	}
	
	/**
	 * Initialise les attributs de la classe Tete.
	 * 
	 * @param pRayon Rayon de la t�te.
	 * @param textureDefaut Texture de la t�te.
	 */
	private void init(double pRayon, Texture textureDefaut)
	{
		setRayon(pRayon);
		texture = TEXTURE_DEFAUT;
	}
	
	/**
	 * Retourne le rayon de la t�te.
	 */
	public double getRayon()
	{
		return rayon;
	}
	
	/**
	 * Change le rayon de la t�te. Dois �tre plus grand que 0.
	 */
	public void setRayon(double nouvRayon)
	{
		if(nouvRayon < 0)
		{
			rayon = RAYON_DEFAUT;
		}
		else
		{
			rayon = nouvRayon;
		}
	}
	
	/**
	 * Retourne la force ext�rieure exerc�e sur cette t�te.
	 */
	public Vecteur getForceExt()
	{
		return new Vecteur();
	}
	
	/**
	 * Met � jour l'apparence de la t�te.
	 */
	public void maj(double dt)
	{
	}
	
	/**
	 * Change la texture de la t�te.
	 * 
	 * @param pTexture la nouvelle texture de la t�te.
	 */
	public void setTexture(Texture pTexture)
	{
		if(pTexture == null)
			texture = TEXTURE_DEFAUT;
		else
			texture = pTexture;
	}
	
	/**
	 * @return la texture de la t�te.
	 */
	public Texture getTexture()
	{
		return texture;
	}
	
	/**
	 * Retourne le noeud pour l'affichage.
	 */
	public Node getNoeud()
	{
		Image img = new Image(texture.getTexture());
		ImageView image = new ImageView(img);
		image.setFitWidth(2 * rayon);
		image.setFitHeight(2 * rayon);
		return image;
	}
}
