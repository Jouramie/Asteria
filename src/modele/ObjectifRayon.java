package modele;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import objets.Vaisseau;
import utils.Vecteur;
import vue.Dessinable;

/**
 * Classe representant un objectif qui est de placer le vaisseau dans un certain cercle.
 * @author EquBolduc
 * @version 1.0
 */
public class ObjectifRayon implements Objectif, Dessinable
{
	public static final double VITESSE_ROTATION = 50;
	public static final double RAYON_DEFAUT = 50;
	
	private Vaisseau vaisseau;
	private Vecteur posRayon;
	private double rayon;
	private Group portal;
	private Rotate noeudRotate;
	
	/**
	 * Constructeur de l'objectif.
	 * @param vaisseau Vaisseau sur lequel verifier l'objectif.
	 * @param posRayon Position du cercle.
	 * @param rayon Rayon du cercle.
	 */
	public ObjectifRayon(Vecteur posRayon, double rayon)
	{
		this.vaisseau = null;
		this.posRayon = posRayon;
		this.rayon = rayon;
		this.portal = new Group();
	}

	/**
	 * Retourne une description sommaire de l'objectif.
	 */
	public String getDescription()
	{
		return "Atteignez l'objectif indique.";
	}
	
	/**
	 * Retourne la position du rayon de l'objectif.
	 */
	public Vecteur getPosRayon()
	{
		return posRayon;
	}
	
	/**
	 * Retourne le rayon de l'objectif.
	 */
	public double getRayon()
	{
		return rayon;
	}
	
	/**
	 * Modifie le vaisseau sur lequel l'objectif est verifie.
	 * @param vaisseau Le nouveau vaisseau sur lequel l'objectif est verifie.
	 */
	public void setVaisseau(Vaisseau vaisseau)
	{
		if(vaisseau != null)
		{
			this.vaisseau = vaisseau;
		}
	}
	
	/**
	 * Modifie la position du rayon de l'objectif.
	 * @param posRayon La nouvelle position du rayon de l'objectif.
	 */
	public void setPosRayon(Vecteur posRayon)
	{
		if(posRayon != null)
		{
			this.posRayon = posRayon;
		}
	}
	
	/**
	 * Modifie le rayon de l'objectif.
	 * @param rayon Le nouveau rayon de l'objectif.
	 */
	public void setRayon(double rayon)
	{
		if(rayon > 0)
		{
			this.rayon = rayon;
		}
	}
	
	/**
	 * Retourne le vaisseau sur lequel l'objectif est verifie.
	 */
	public Vaisseau getVaisseau()
	{
		return vaisseau;
	}

	/**
	 * Verifie si le vaisseau se retrouve dans le cercle prescrit.
	 */
	public boolean verifierObjectif()
	{
		boolean resultat = false;
		
		if(vaisseau != null && vaisseau.getPosition().soustraire(posRayon).getNorme() < rayon)
		{
			resultat = true;
		}
		
		return resultat;
	}

	/**
	 * Retourne un noeud representant le portail de fin.
	 * @return Retourne le noeud.
	 */
	public Node getNoeud()
	{
		if(portal.getChildren().isEmpty())
			creeNoeud();
		
		return portal;
	}

	/**
	 * Met a jour le portail (animation de rotation).
	 * @param dt Temps ecoule depuis le dernier frame (en secondes).
	 */
	public void miseAJourGraphique(double dt)
	{
		if(portal.getChildren().isEmpty())
			creeNoeud();
		noeudRotate.setAngle(noeudRotate.getAngle() + VITESSE_ROTATION * dt);
	}
	
	/**
	 * Cree un noeud representant l'objet.
	 */	
	public void creeNoeud()
	{
		portal.getChildren().clear();
		
		ImageView image = new ImageView(new Image("/res/portail.png"));
		image.setFitWidth(rayon * 2);
		image.setFitHeight(rayon * 2);
		image.setTranslateX(posRayon.getX() - rayon);
		image.setTranslateY(posRayon.getY() - rayon);
		portal.getChildren().add(image);
		
		noeudRotate = new Rotate(0, rayon, rayon);
		image.getTransforms().add(noeudRotate);
		
	}


}
