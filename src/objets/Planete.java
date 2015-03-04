package objets;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Vecteur;

/**
 * Classe repr�sentant une plan�te.
 * @author EquBolduc
 * @version 1.0
 */
public class Planete extends ObjetSpatial
{
	private Image texture;

	/**
	 * Constructeur de plan�te, prend un vecteur pour la position
	 * 
	 * @param pMasse la masse de la plan�te
	 * @param pPosition la position de la plan�te
	 */
	public Planete(double pMasse, Vecteur pPosition, String tex)
	{
		super(pMasse, pPosition, true, new Vecteur());
		
		texture = new Image(tex);
	}

	/**
	 * Constructeur de plan�te, prend des doubles pour la position
	 * 
	 * @param pMasse la masse de la plan�te
	 * @param pPositionX la positionX de la plan�te
	 * @param pPositionY la positionY de la plan�te
	 */
	public Planete(double pMasse, double pPositionX, double pPositionY, String tex)
	{
		super(pMasse, pPositionX, pPositionY, true, new Vecteur());
		texture = new Image(tex);
	}
	
	public int getRayonCollision()
	{
		return 100;
	}
	
	public Node getNoeud()
	{
		double rayon = getRayonCollision();
		
		ImageView image = new ImageView(texture);
		image.setFitWidth(rayon * 2);
		image.setFitHeight(rayon * 2);
		image.setTranslateX(-rayon);
		image.setTranslateY(-rayon);
		
		Group group = new Group();
		group.getChildren().add(image);
		
		return group;
	}
}