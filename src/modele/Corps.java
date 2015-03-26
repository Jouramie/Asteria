package modele;

import javafx.beans.property.DoubleProperty;
import utils.Vecteur;

/**
 * Interface utilis� pour tous les corps physiques.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public interface Corps
{
	/**
	 * Retourne la masse du corps.
	 * 
	 * @return Masse du corps (en kg)
	 */
	double getMasse();
	
	/**
	 * Retourne la position par rapport � X.
	 * 
	 * @return Position X (en m)
	 */
	double getPositionX();
	
	/**
	 * Retourne la position par rapport � Y.
	 * 
	 * @return Position Y (en m)
	 */
	double getPositionY();
	
	/**
	 * Modifie la position en X.
	 * 
	 * @param pPositionX
	 *            Position X (en m)
	 */
	void setPositionX(double pPositionX);
	
	/**
	 * Modifie la position en Y.
	 * 
	 * @param pPositionX
	 *            Position Y (en m)
	 */
	void setPositionY(double pPositionX);
	
	/**
	 * Retourne la propri�t� pour la position en X. Utile pour le binding.
	 * 
	 * @return Propri�t� de la position en X.
	 */
	DoubleProperty getPositionXProperty();
	
	/**
	 * Retourne la propri�t� pour la position en Y. Utile pour le binding.
	 * 
	 * @return Propri�t� de la position en Y.
	 */
	DoubleProperty getPositionYProperty();
	
	/**
	 * Retourne un vecteur repr�sentant la position du corps.
	 * 
	 * @return Vecteur de position.
	 */
	Vecteur getPosition();
	
	/**
	 * Modifie la position d'un vaisseau � l'aide d'un vecteur.
	 * 
	 * @param pPosition
	 *            Vecteur de la position.
	 */
	void setPosition(Vecteur pPosition);
	
	/**
	 * D�termine si un corps doit �tre statique (toujours immobile)
	 * 
	 * @return Vrai si le corps est statique, faux sinon.
	 */
	boolean isStatique();
	
	/**
	 * Modifie le fait qu'un corps est statique (toujours immobile).
	 * 
	 * @param pStatique
	 *            Vrai si le corps est statique, faux sinon.
	 */
	void setStatique(boolean pStatique);
	
	/**
	 * Retourne la vitesse actuelle du corps.
	 * 
	 * @return Vecteur de vitesse du corps.
	 */
	Vecteur getVitesse();
	
	/**
	 * Modifie la vitesse du vaisseau.
	 * 
	 * @param pVitesse
	 *            Vecteur repr�sentant la vitesse du vaisseau.
	 */
	void setVitesse(Vecteur pVitesse);
	
	/**
	 * Retourne la force ext�rieure appliqu�e sur le corps. Par exemple, la
	 * force peut �tre cr��e par un r�acteur.
	 * 
	 * @return Vecteur repr�sentant la force (en Newton)
	 */
	Vecteur getForceExt();
	
	/**
	 * Retourne le rayon de la taille d'un objet autour de son point central.
	 * 
	 * @return Rayon de la taille d'un objet autour de son point central.
	 */
	double getRayon();
	
	/**
	 * Remet les corps � leur position et leur vitesse de d�part.
	 */
	void reset();
	
	/**
	 * Callback lorsqu'une collision a lieu.
	 * @param c Autre corps en collision.
	 */
	void onCollision(Corps c);

	void setMasse(double pMasse);
}
