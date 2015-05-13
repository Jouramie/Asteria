package objets;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import utils.Vecteur;

/**
 * Classe repr�sentant un vaisseau spatial.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class Vaisseau extends ObjetSpatial
{
	public final static double MASSE_DEFAUT = 16e3;
	protected boolean premierGetNoeud;
	
	protected Node noeud;
	protected Rotate noeudRotate;
	
	/**
	 * Constructeur de vaisseau, prend un vecteur pour la position
	 * 
	 * @param pMasse la masse du vaisseau
	 * @param pPosition la position du vaisseau
	 * @param pVitesse la vitesse du vaisseau
	 */
	public Vaisseau(double pMasse, Vecteur pPosition, Vecteur pVitesse)
	{
		super(pMasse, pPosition, false, pVitesse);
		init();
	}
	
	/**
	 * Constructeur de vaisseau, prend des doubles pour la position
	 * 
	 * @param pMasse la masse du vaisseau
	 * @param pPositionX la positionX de la plan�te
	 * @param pPositionY la positionY de la plan�te
	 * @param pVitesse la vitesse du vaisseau
	 */
	public Vaisseau(double pMasse, double pPositionX, double pPositionY,
			Vecteur pVitesse)
	{
		super(pMasse, pPositionX, pPositionY, false, pVitesse);
		init();
	}
	
	/**
	 * Initialise le vaisseau avec des valeurs par d�faut.
	 */
	private void init()
	{
		premierGetNoeud = true;
	}
	
	/**
	 * Ne pas mettre True sinon le vaisseau reste immobile
	 */
	public void setStatique(boolean pStatique)
	{
		statique = pStatique;
	}
	
	/**
	 * Si le vecteur est null, met la vitesse � 0.
	 */
	public void setVitesse(Vecteur pVitesse)
	{
		if(pVitesse == null)
		{
			vitesse = new Vecteur();
		}
		else
		{
			vitesse = pVitesse;
		}
	}
	
	/**
	 * Cr�e le noeud JavaFX du vaisseau.
	 */
	private void creeNoeud()
	{
		Group group = new Group();
		
		Image textureVaisseau = new Image("/res/vaisseau.png");
		;
		
		ImageView image = new ImageView(textureVaisseau);
		image.setFitWidth(40);
		image.setFitHeight(40);
		image.setTranslateX(-20);
		image.setTranslateY(-20);
		group.getChildren().add(image);
		
		noeudRotate = new Rotate(0, 0, 0);
		group.getTransforms().add(noeudRotate);
		
		noeud = group;
	}
	
	/**
	 * Retourne le noeud JavaFX repr�sentant le vaisseau. Le noeud ne change pas
	 * entre chaque appel de la m�thode.
	 * 
	 * @return Noeud JavaFX du vaisseau.
	 */
	public Node getNoeud()
	{
		if(premierGetNoeud)
		{
			creeNoeud();
			premierGetNoeud = false;
		}
		return noeud;
	}
	
	/**
	 * Retourne le rayon de collision du vaisseau.
	 * 
	 * @return Rayon de collision (en m).
	 */
	public double getRayon()
	{
		return 20.0;
	}
	
	public Vecteur getForceExt()
	{
		return new Vecteur();
	}
	
	public void maj(double dt)
	{
		noeudRotate.setAngle(vitesse.getAngle() / 2 / Math.PI * 360 + 90);
	}
}
