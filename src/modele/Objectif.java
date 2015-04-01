package modele;

import objets.Vaisseau;

/**
 * Interface définissant un objectif d'un niveau.
 * @author EqBolduc
 * @version 1.0
 */
public interface Objectif
{
	/**
	 * Retourne la description de l'objectif.
	 * @return Chaîne de caractère décrivant l'objectif.
	 */
	String getDescription();
	
	/**
	 * Vérifie si l'objectif a été atteint.
	 * @return Vrai si l'objectif est atteint, faux sinon.
	 */
	boolean verifierObjectif();
	
	/**
	 * Assigne un vaisseau à l'objectif.
	 * @param v Vaisseau.
	 */
	public void setVaisseau(Vaisseau v);
}
