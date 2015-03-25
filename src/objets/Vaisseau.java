package objets;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
	public final static double CONVERTION_CARBURANT = 1.0;
	public final static double PUISSANCE_MAX_DEFAUT = 1.0;
	
	public final static double VITESSE_ANIM_FLAMME = 6;
	
	protected double puissanceMax;
	protected double puissance;
	protected Vecteur direction;
	protected double capaciteCarburant;
	protected DoubleProperty carburant;
	private boolean premierGetNoeud;
	
	protected Node noeud;
	protected Rotate noeudRotate;
	protected ImageView imageFlamme;
	protected double currentFlamme;
	
	protected double sante;
	
	protected double carburantDepart;
	
	/**
	 * Constructeur de vaisseau, prend un vecteur pour la position
	 * 
	 * @param pPuissanceMax
	 *            la puissance maximale du vaisseau
	 * @param pDirection
	 *            la direction du vaisseau
	 * @param pMasse
	 *            la masse du vaisseau
	 * @param pCapaciteCarburant
	 *            la capacit� maximale de caburant du vaisseau
	 * @param pPosition
	 *            la position du vaisseau
	 * @param pVitesse
	 *            la vitesse du vaisseau
	 */
	public Vaisseau(double pPuissanceMax, double pMasse,
			double pCapaciteCarburant, Vecteur pPosition, Vecteur pVitesse)
	{
		super(pMasse, pPosition, false, pVitesse);
		init(pPuissanceMax,pCapaciteCarburant);
	}
	
	/**
	 * Constructeur de vaisseau, prend des doubles pour la position
	 * 
	 * @param pPuissanceMax
	 *            la puissance maximale du vaisseau
	 * @param pDirection
	 *            la direction du vaisseau
	 * @param pMasse
	 *            la masse du vaisseau
	 * @param pCapaciteCarburant
	 *            la capacit� maximale de caburant du vaisseau
	 * @param pPositionX
	 *            la positionX de la plan�te
	 * @param pPositionY
	 *            la positionY de la plan�te
	 * @param pVitesse
	 *            la vitesse du vaisseau
	 */
	public Vaisseau(double pPuissanceMax, double pMasse,
			double pCapaciteCarburant, double pPositionX, double pPositionY,
			Vecteur pVitesse)
	{
		super(pMasse, pPositionX, pPositionY, false, pVitesse);
		init(pPuissanceMax, pCapaciteCarburant);
	}
	
	private void init(double pPuissanceMax, double pCapaciteCarburant){
		setPuissanceMax(pPuissanceMax);
		setPuissance(pPuissanceMax);
		direction = new Vecteur();
		capaciteCarburant = pCapaciteCarburant;
		carburant = new SimpleDoubleProperty(capaciteCarburant);
		premierGetNoeud = true;
		
		currentFlamme = 0.0;
		sante = 1.0;
	}
	
	public double getPuissanceMax()
	{
		return puissanceMax;
	}
	
	public void setPuissanceMax(double pPuissanceMax)
	{
		if (pPuissanceMax <= 0)
			puissanceMax = PUISSANCE_MAX_DEFAUT;
		else
			puissanceMax = pPuissanceMax;
	}
	
	public double getPuissance()
	{
		return puissance;
	}
	
	public void setPuissance(double pPuissance)
	{
		if (pPuissance <= 0)
			puissance = PUISSANCE_MAX_DEFAUT;
		else
			puissance = pPuissance;
	}
	
	/**
	 * Retourne la sant� actuelle du vaisseau.
	 * @return Sant� actuelle (entre 0.0 et 1.0).
	 */
	public double getSante()
	{
		return sante;
	}
	
	/**
	 * Modifie la sant� du vaisseau.
	 * Doit �tre situ�e entre 0.0 et 1.0 inclusivement.
	 * @param sante Nouvelle sant�.
	 */
	public void setSante(double sante)
	{
		if(sante <= 1.0 && sante >= 0.0)
		{
			this.sante = sante;
		}
	}
	
	// public void setCarburantMax(double pCarburantMax)
	// {
	// // TODO story implementation du carburant
	// }
	//
	// public void getCarburantMax(double pCarburantMax)
	// {
	// // TODO story implementation du carburant
	// }
	//
	// public void setCarburant(double pCarburant)
	// {
	// // TODO story implementation du carburant
	// }
	//
	// public void getCarburant(double pCarburant)
	// {
	// // TODO story implementation du carburant
	// }
	//
	// TODO story implementation du carburant
	// @Override
	// public double getMasse()
	// {
	// return masse + CONVERTION_CARBURANT * carburant.get();
	// }
	
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
		if (pVitesse == null)
		{
			vitesse = new Vecteur();
		}
		else
		{
			vitesse = pVitesse;
			direction = vitesse.normaliser();
		}
	}
	
	public Vecteur getForceExt()
	{
		return new Vecteur();
	}
	
	public Vecteur getDirection()
	{
		return direction;
	}
	
	private void creeNoeud()
	{
		Group group = new Group();
		
		Image texture = new Image("/res/spaceship.png");
		ImageView image = new ImageView(texture);
		image.setFitWidth(40);
		image.setFitHeight(40);
		image.setTranslateX(-20);
		image.setTranslateY(-20);
		group.getChildren().add(image);
		
		Image textureFlamme = new Image("/res/flame.png");
		imageFlamme = new ImageView(textureFlamme);
		imageFlamme.setFitWidth(15);
		imageFlamme.setFitHeight(40);
		imageFlamme.setTranslateX(-7);
		imageFlamme.setTranslateY(22);
		group.getChildren().add(imageFlamme);
		
		noeudRotate = new Rotate(0, 0, 0);
		group.getTransforms().add(noeudRotate);
		
		noeud = group;
	}
	
	public Node getNoeud()
	{
		if (premierGetNoeud)
		{
			creeNoeud();
			premierGetNoeud = false;
		}
		return noeud;
	}
	
	/**
	 * Met � jour le noeud repr�sentant le vaisseau
	 */
	public void maj(double dt)
	{
		if(getForceExt().getNorme() > 0)
		{
			currentFlamme += VITESSE_ANIM_FLAMME * dt;
			currentFlamme = Math.min(1.0, currentFlamme);
		}
		else
		{
			currentFlamme -= VITESSE_ANIM_FLAMME * dt;
			currentFlamme = Math.max(0.0, currentFlamme);
		}
		imageFlamme.setOpacity(currentFlamme);
		
		noeudRotate.setAngle(direction.getAngle() / 2 / Math.PI * 360 + 90);
	}
	
	public double getRayon()
	{
		return 20.0;
	}
}
