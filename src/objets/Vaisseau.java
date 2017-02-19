package objets;
import modele.Corps;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import utils.Vecteur;

/**
 * Classe representant un vaisseau spatial.
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
	 * @param pPositionX la positionX de la planete
	 * @param pPositionY la positionY de la planete
	 * @param pVitesse la vitesse du vaisseau
	 */
	public Vaisseau(double pMasse, double pPositionX, double pPositionY,
			Vecteur pVitesse)
	{
		super(pMasse, pPositionX, pPositionY, false, pVitesse);
		init();
	}
	
	/**
	 * Initialise le vaisseau avec des valeurs par defaut.
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
	 * Si le vecteur est null, met la vitesse a 0.
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
	 * Cree le noeud JavaFX du vaisseau.
	 */
	public void creeNoeud()
	{
		Group group = new Group(); //TODO
		
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
	 * Retourne le noeud JavaFX representant le vaisseau. Le noeud ne change pas
	 * entre chaque appel de la methode.
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
	
	/**
	 * Retourne la force exterieure appliquee sur le corps. Par exemple, la
	 * force peut etre creee par un reacteur.
	 * 
	 * @return Vecteur representant la force (en Newton)
	 */
	public Vecteur getForceExt()
	{
		return new Vecteur();
	}
	
	/**
	 * Met a jour le noeud representant l'objet.
	 * 
	 * @param dt Temps ecoule depuis le dernier frame (en secondes).
	 */
	public void miseAJourGraphique(double dt)
	{
		noeudRotate.setAngle(vitesse.getAngle() / 2 / Math.PI * 360 + 90);
	}
	
	/**
	 * Callback lorsqu'une collision a lieu.
	 * 
	 * @param c Autre corps en collision.
	 */
	public void onCollision(Corps c)
	{
	}
	
	/**
	 * Met a jour les attributs
	 * 
	 * @param dt Temps ecoule depuis le dernier frame (en secondes).
	 */
	public void miseAJourPhysique(double dt)
	{
	}
}
