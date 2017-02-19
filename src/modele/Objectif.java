package modele;

import objets.Vaisseau;

/**
 * Interface definissant un objectif d'un niveau.
 * 
 * @author EqBolduc
 * @version 1.0
 */
public interface Objectif
{
	/**
	 * Retourne la description de l'objectif.
	 * 
	 * @return Chaîne de caractere decrivant l'objectif.
	 */
	String getDescription();
	
	/**
	 * Verifie si l'objectif a ete atteint.
	 * 
	 * @return Vrai si l'objectif est atteint, faux sinon.
	 */
	boolean verifierObjectif();
	
	/**
	 * Assigne un vaisseau a l'objectif.
	 * 
	 * @param v Vaisseau.
	 */
	public void setVaisseau(Vaisseau v);
}
