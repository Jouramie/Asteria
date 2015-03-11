package modele;
import java.util.ArrayList;
import java.util.List;
import utils.Vecteur;
/**
 * Classe servant à contenir les éléments constituant un niveau.
 * 
 * @author Équipe Bolduc
 * @version 1.0
 */
public class Niveau
{
	private List<Corps> corps;
	
	private Objectif objectif;
	
	private Vecteur pointDepart;
	
	private String titreNiveau;
	
	private Vecteur vitesseDepart;
	
	/**
	 * Constructeur servant à initialiser le niveau.
	 * @param nouveauxCorps Les corps à mettre dans le niveau,
	 * @param nouvelObjectif L'objectif à mettre dans le niveau.
	 * @param nouveauPointDepart Le point de départ du vaisseau.
	 * @param nouveauTitreNiveau Le titre du niveau.
	 * @param nouvelleVitesseDepart La vitesse du vaisseau au point de départ.
	 */
	public Niveau(List<Corps> nouveauxCorps, Objectif objectif, Vecteur nouveauPointDepart, String nouveauTitreNiveau, Vecteur nouvelleVitesseDepart)
	{
		corps = new ArrayList<>();
		
		if(nouveauxCorps != null)
		{
			for(Corps c : nouveauxCorps)
			{
				ajouterCorps(c);
			}
		}
		
		setObjectif(objectif);
		
		setPointDepart(nouveauPointDepart);
		
		setTitreNiveau(nouveauTitreNiveau);
		
		setVitesseDepart(nouvelleVitesseDepart);
	}
	
	/**
	 * Sert à ajouter un corps dans le niveau.
	 * @param nouveauCorps Un nouveau corps.
	 */
	public void ajouterCorps(Corps nouveauCorps)
	{
		if(nouveauCorps != null)
		{
			corps.add(nouveauCorps);
		}
	}
	
	/**
	 * Retourne la liste de corps.
	 * @return La liste de corps.
	 */
	public List<Corps> getCorps()
	{
		return corps;
	}
	
	/**
	 * Retourne l'objectif.
	 * @return L'objectif.
	 */
	public Objectif getObjectif()
	{
		return objectif;
	}
	
	/**
	 * Retourne le point de départ du vaisseau.
	 * @return Le point de départ du vaisseau.
	 */
	public Vecteur getPointDepart()
	{
		return pointDepart;
	}
	
	/**
	 * Retourne le titre du niveau.
	 * @return Le titre du niveau.
	 */
	public String getTitreNiveau()
	{
		return titreNiveau;
	}
	
	/**
	 * Retourne la vitesse de départ du vaisseau.
	 * @return La vitesse de départ du vaisseau.
	 */
	public Vecteur getVitesseDepart()
	{
		return vitesseDepart;
	}
	
	/**
	 * Sert à modifier l'objectif.
	 * @param nouvelObjectif Le nouvel objectif.
	 */
	public void setObjectif(Objectif nouvelObjectif)
	{
		if(nouvelObjectif != null)
		{
			objectif = nouvelObjectif;
		}
	}
	
	/**
	 * Sert à modifier le point de départ du vaisseau.
	 * @param nouveauPointDepart Le nouveau point de départ du vaisseau.
	 */
	public void setPointDepart(Vecteur nouveauPointDepart)
	{
		if(nouveauPointDepart != null)
		{
			pointDepart = nouveauPointDepart;
		}
	}
	
	/**
	 * Sert à modifier le titre du niveau.
	 * @param nouveauTitreNiveau Le nouveau titre du niveau.
	 */
	public void setTitreNiveau(String nouveauTitreNiveau)
	{
		if(nouveauTitreNiveau != null)
		{
			titreNiveau = nouveauTitreNiveau;
		}
	}
	
	/**
	 * Sert à modifier la vitesse de départ du vaisseau.
	 * @param nouvelleVitesseDepart La nouvelle vitesse de départ du vaisseau.
	 */
	public void setVitesseDepart(Vecteur nouvelleVitesseDepart)
	{
		if(nouvelleVitesseDepart != null)
		{
			vitesseDepart = nouvelleVitesseDepart;
		}
	}
}