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
 * Classe repr�sentant une plan�te.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class Planete extends ObjetSpatial {

	public static final Texture TEXTURE_DEFAUT = Texture.RAYEE_ROUGE;
	public static final double RAYON_DEFAUT = 100;
	public static final boolean STATIC_DEFAUT = true;
	public static final Vecteur VITESSE_DEFAUT = new Vecteur();

	public enum Texture {
		RAYEE_ROUGE("/res/planete1.png");

		private final String texture;

		Texture(String pTexture) {
			texture = pTexture;
		}

		public String getTexture() {
			return texture;
		}
	}

	private Texture texture;
	private double rayon;

	/**
	 * Constructeur de plan�te, prend un vecteur pour la position
	 * 
	 * @param pMasse
	 *            la masse de la plan�te
	 * @param pPosition
	 *            la position de la plan�te
	 */
	public Planete(double pMasse, Vecteur pPosition, double pRayon) {
		super(pMasse, pPosition, true, new Vecteur());
		init(pRayon);
	}

	/**
	 * Constructeur de plan�te, prend des doubles pour la position
	 * 
	 * @param pMasse
	 *            la masse de la plan�te
	 * @param pPositionX
	 *            la positionX de la plan�te
	 * @param pPositionY
	 *            la positionY de la plan�te
	 */
	public Planete(double pMasse, double pPositionX,
			double pPositionY,  double pRayon) {
		super(pMasse, pPositionX, pPositionY, true, new Vecteur());
		init(pRayon);
	}

	private void init(double pRayon) {
		setRayon(pRayon);
	}

	public void setTexture(Texture pTexture) {
		if (pTexture == null)
			texture = TEXTURE_DEFAUT;
		else
			texture = pTexture;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setRayon(double pRayon) {
		if (pRayon < 0) {
			rayon = RAYON_DEFAUT;
		} else {
			rayon = pRayon;
		}
	}

	public double getRayon() {
		return rayon;
	}

	@Deprecated
	public int getRayonCollision() {
		return 100;
	}

	public Node getNoeud() {
		setTexture(texture);
		Image texture = new Image(this.texture.getTexture());
		Circle cercle = new Circle(rayon + 30);
		RadialGradient grad = new RadialGradient(0, 0, 0, 0, rayon + 32, false,
				CycleMethod.REPEAT, new Stop(0.7, Color.ORANGE), new Stop(1,
						Color.TRANSPARENT));
		cercle.setFill(grad);

		ImageView image = new ImageView(texture);
		image.setFitWidth(rayon * 2);
		image.setFitHeight(rayon * 2);
		image.setTranslateX(-rayon);
		image.setTranslateY(-rayon);

		Group group = new Group();
		group.getChildren().add(cercle);
		group.getChildren().add(image);

		return group;
	}
}