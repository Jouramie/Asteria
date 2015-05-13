package objets;

import modele.Corps;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import utils.Vecteur;

/**
 * Classe repr�sentant une plan�te.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class Planete extends ObjetSpatial
{
	
	public static final Texture TEXTURE_DEFAUT = Texture.ROUGE;
	public static final double RAYON_DEFAUT = 100;
	public static final boolean STATIC_DEFAUT = true;
	public static final Vecteur VITESSE_DEFAUT = new Vecteur();
	public static final double RAYON_ATMOSPHERE_DEFAUT = 30;
	public static final Color COULEUR_ATMOSHPERE_DEFAUT = Color.ORANGE;
	public static final double PLANETE_MASSE_DEFAUT = 6e15;
	
	/**
	 * Enum des textures possible pour une plan�te.
	 * 
	 * @author EquBolduc
	 */
	public enum Texture
	{
		BLEUE("/res/planeteBleue.png"), JAUNE("/res/planeteJaune.png"), MAGENTA(
				"/res/planeteMagenta.png"), ORANGE("/res/planeteOrange.png"), ROUGE(
				"/res/planeteRouge.png"), VERTE("/res/planeteVerte.png");
		
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
			case "bleue":
				return Texture.BLEUE;
				
			case "jaune":
				return Texture.JAUNE;
				
			case "magenta":
				return Texture.MAGENTA;
				
			case "orange":
				return Texture.ORANGE;
				
			case "rouge":
				return Texture.ROUGE;
				
			case "verte":
				return Texture.VERTE;
				
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
	
	private Texture texture;
	private double rayon;
	private double rayonAtmosphere;
	

	protected Group noeud;
	protected Color couleurAtmosphere;
	
	/**
	 * Constructeur de plan�te, prend un vecteur pour la position
	 * 
	 * @param pMasse la masse de la plan�te
	 * @param pPosition la position de la plan�te
	 */
	public Planete(double pMasse, Vecteur pPosition, double pRayon,
			double pRayonAtmosphere, Color pCouleurAtmosphere)
	{
		super(pMasse, pPosition, true, new Vecteur());
		init(pRayon, pRayonAtmosphere, pCouleurAtmosphere);
	}
	
	/**
	 * Constructeur de plan�te, prend des doubles pour la position
	 * 
	 * @param pMasse la masse de la plan�te
	 * @param pPositionX la positionX de la plan�te
	 * @param pPositionY la positionY de la plan�te
	 */
	public Planete(double pMasse, double pPositionX, double pPositionY,
			double pRayon, double pRayonAtmosphere, Color pCouleurAtmosphere)
	{
		super(pMasse, pPositionX, pPositionY, true, new Vecteur());
		init(pRayon, pRayonAtmosphere, pCouleurAtmosphere);
	}
	
	/**
	 * Initialise les attributs de la classe.
	 * 
	 * @param pRayon le rayon.
	 */
	private void init(double pRayon, double pRayonAtmosphere,
			Color pCouleurAtmosphere)
	{
		setRayon(pRayon);
		setRayonAtmosphere(pRayonAtmosphere);
		setCouleurAtmosphere(pCouleurAtmosphere);
		noeud = new Group();
	}
	
	/**
	 * Change la texture de la plan�te.
	 * 
	 * @param pTexture la nouvelle texture de la plan�te.
	 */
	public void setTexture(Texture pTexture)
	{

		if(pTexture == null)
			texture = TEXTURE_DEFAUT;
		else
			texture = pTexture;
	}
	
	/**
	 * @return la texture de la plan�te.
	 */
	public Texture getTexture()
	{
		return texture;
	}
	
	/**
	 * Modifie le rayon de la plan�te.
	 * 
	 * @param pRayon le nouveau rayon de la plan�te.
	 */
	public void setRayon(double pRayon)
	{
		if(pRayon < 0)
		{
			rayon = RAYON_DEFAUT;
		}
		else
		{
			rayon = pRayon;
		}
	}
	
	/**
	 * Retourne le rayon de la plan�te
	 * 
	 * @return le rayon de la plan�te.
	 */
	public double getRayon()
	{
		return rayon;
	}
	
	/**
	 * Modifie le rayon de l'atmosphere de la plan�te.
	 * 
	 * @param pRayonAtmosphere le nouveau rayon de la plan�te.
	 */
	public void setRayonAtmosphere(double pRayonAtmosphere)
	{
		if(pRayonAtmosphere < 0)
		{
			rayonAtmosphere = RAYON_ATMOSPHERE_DEFAUT;
		}
		else
		{
			rayonAtmosphere = pRayonAtmosphere;
		}
	}
	
	/**
	 * Retourne le rayon de l'atmosph�re de la plan�te.
	 * 
	 * @return le rayon de l'atmosph�re de la plan�te. (en pixel)
	 */
	public double getRayonAtmosphere()
	{
		return rayonAtmosphere;
	}
	
	/**
	 * Modifie la cou leur de l'atmosph�re de la plan�te.
	 * 
	 * @param pCouleurAtmosphere la nouvelle couleur de la plan�te.
	 */
	public void setCouleurAtmosphere(Color pCouleurAtmosphere)
	{
		couleurAtmosphere = pCouleurAtmosphere;
	}
	
	/**
	 * Retourne la couleur de l'atmosph�re de la plan�te.
	 * 
	 * @return la couleur de l'atmnosph�re de la plan�te.
	 */
	public Color getCouleurAtmosphere()
	{
		return couleurAtmosphere;
	}
	
	/**
	 * Retourne un noeud correspondant � l'aspect graphique de la plan�te.
	 * 
	 * @return un group contenant les composants graphique de la plan�te.
	 */
	public Node getNoeud()
	{
		if (noeud.getChildren().isEmpty())
			creeNoeud();
		return noeud;
	}
	
	/**
	 * Cr�e un noeud repr�sentant l'objet.
	 */	
	public void creeNoeud()
	{
		noeud.getChildren().clear();
		setTexture(texture);
		Image texture = new Image(this.texture.getTexture());
		Circle cercle = new Circle(rayon + rayonAtmosphere);
		RadialGradient grad = new RadialGradient(0, 0, 0, 0, rayon
				+ rayonAtmosphere + 2, false, CycleMethod.REPEAT, new Stop(
				rayon / (rayon + rayonAtmosphere), couleurAtmosphere),
				new Stop(1, Color.TRANSPARENT));
		cercle.setFill(grad);
		cercle.setOpacity(0.45);
		
		ImageView image = new ImageView(texture);
		image.setFitWidth(rayon * 2);
		image.setFitHeight(rayon * 2);
		image.setTranslateX(-rayon);
		image.setTranslateY(-rayon);
		
		noeud.getChildren().add(cercle);
		noeud.getChildren().add(image);
	}
	
	/**
	 * Retourne une force ext�rieur appliqu� sur la plan�te.
	 * 
	 * @return toujours un vecteur null.
	 */
	public Vecteur getForceExt()
	{
		return new Vecteur();
	}
	
	/**
	 * Met � jour le noeud repr�sentant l'objet.
	 * 
	 * @param dt
	 *            Temps �coul� depuis le dernier frame (en secondes).
	 */
	public void miseAJourGraphique(double dt)
	{	
	}
	
	/**
	 * Met � jour les attributs
	 * 
	 * @param dt
	 *            Temps �coul� depuis le dernier frame (en secondes).
	 */
	public void miseAJourPhysique(double dt)
	{
	}
	
	/**
	 * Callback lorsqu'une collision a lieu.
	 * 
	 * @param c
	 *            Autre corps en collision.
	 */
	public void onCollision(Corps c)
	{
	}

}
