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

	private Image texture;

	/**
	 * Constructeur de plan�te, prend un vecteur pour la position
	 * 
	 * @param pMasse
	 *            la masse de la plan�te
	 * @param pPosition
	 *            la position de la plan�te
	 */
	public Planete(double pMasse, Vecteur pPosition, Texture pTexture) {
		super(pMasse, pPosition, true, new Vecteur());
		texture = new Image(pTexture.getTexture());
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
	public Planete(double pMasse, double pPositionX, double pPositionY,
			String tex) {
		super(pMasse, pPositionX, pPositionY, true, new Vecteur());
		texture = new Image(tex);
	}

	public int getRayonCollision() {
		return 100;
	}

	public Node getNoeud() {
		double rayon = getRayonCollision();

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