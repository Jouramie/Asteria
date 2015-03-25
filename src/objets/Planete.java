package objets;

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
 * Classe représentant une planète.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class Planete extends ObjetSpatial
{
	
	public static final Texture TEXTURE_DEFAUT = Texture.RAYEE_ROUGE;
	public static final double RAYON_DEFAUT = 100;
	public static final boolean STATIC_DEFAUT = true;
	public static final Vecteur VITESSE_DEFAUT = new Vecteur();
	public static final double RAYON_ATMOSPHERE_DEFAUT = 30;
	public static final Color COULEUR_ATMOSHPERE_DEFAUT = Color.ORANGE;
	
	public enum Texture
	{
		RAYEE_BLEUE("/res/planeteRayeeBleue.png"),
		RAYEE_JAUNE("/res/planeteRayeeJaune.png"),
		RAYEE_MAGENTA("/res/planeteRayeeMagenta.png"),
		RAYEE_ORANGE("/res/planeteRayeeOrange.png"),
		RAYEE_ROUGE("/res/planeteRayeeRouge.png"),
		RAYEE_VERTE("/res/planeteRayeeVerte.png");
		
		private final String texture;
		
		Texture(String pTexture)
		{
			texture = pTexture;
		}
		
		public String getTexture()
		{
			return texture;
		}
	}
	
	private Texture texture;
	private double rayon;
	private double rayonAtmosphere;
	
	protected Group group;
	protected Color couleurAtmosphere;
	
	/**
	 * Constructeur de planète, prend un vecteur pour la position
	 * 
	 * @param pMasse
	 *            la masse de la planète
	 * @param pPosition
	 *            la position de la planète
	 */
	public Planete(double pMasse, Vecteur pPosition, double pRayon)
	{
		super(pMasse, pPosition, true, new Vecteur());
		init(pRayon);
	}
	
	/**
	 * Constructeur de planète, prend des doubles pour la position
	 * 
	 * @param pMasse
	 *            la masse de la planète
	 * @param pPositionX
	 *            la positionX de la planète
	 * @param pPositionY
	 *            la positionY de la planète
	 */
	public Planete(double pMasse, double pPositionX, double pPositionY,
			double pRayon)
	{
		super(pMasse, pPositionX, pPositionY, true, new Vecteur());
		init(pRayon);
	}
	
	private void init(double pRayon)
	{
		setRayon(pRayon);
		setRayonAtmosphere(RAYON_ATMOSPHERE_DEFAUT);
		group = new Group();
	}
	
	public void setTexture(Texture pTexture)
	{
		if (pTexture == null)
			texture = TEXTURE_DEFAUT;
		else
			texture = pTexture;
	}
	
	public Texture getTexture()
	{
		return texture;
	}
	
	public void setRayon(double pRayon)
	{
		if (pRayon < 0)
		{
			rayon = RAYON_DEFAUT;
		}
		else
		{
			rayon = pRayon;
		}
	}
	
	public double getRayon()
	{
		return rayon;
	}
	
	public void setRayonAtmosphere(double pRayonAtmosphere)
	{
		if (pRayonAtmosphere < 0)
		{
			rayonAtmosphere = RAYON_ATMOSPHERE_DEFAUT;
		}
		else
		{
			rayonAtmosphere = pRayonAtmosphere;
		}
	}
	
	public double getRayonAtmosphere()
	{
		return rayonAtmosphere;
	}
	
	public int getRayonCollision()
	{
		return (int) rayon;
	}
	
	public Node getNoeud()
	{
		setTexture(texture);
		Image texture = new Image(this.texture.getTexture());
		Circle cercle = new Circle(rayon + rayonAtmosphere);
		RadialGradient grad = new RadialGradient(0, 0, 0, 0, rayon + rayonAtmosphere + 2, false,
				CycleMethod.REPEAT, new Stop(0.7, Color.ORANGE), new Stop(1,
						Color.TRANSPARENT));
		cercle.setFill(grad);
		
		ImageView image = new ImageView(texture);
		image.setFitWidth(rayon * 2);
		image.setFitHeight(rayon * 2);
		image.setTranslateX(-rayon);
		image.setTranslateY(-rayon);
		
		group.getChildren().add(cercle);
		group.getChildren().add(image);
		return group;
	}
	
	public Vecteur getForceExt()
	{
		return new Vecteur();
	}
	
	public void maj(){
		group.getChildren().clear();
		getNoeud();
	}
	
	@Override
	public void maj(double dt)
	{
		
	}
}
