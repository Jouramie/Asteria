package modele;
import java.util.ArrayList;
import java.util.List;
import utils.Vecteur;
/**
 * Classe servant � contenir les �l�ments constituant un niveau.
 * 
 * @author �quipe Bolduc
 * @version 1.0
 */
public class Niveau
{
	private List<Corps> corps;
	
	private List<String> objectifs;
	
	private Vecteur pointDepart;
	
	private String titreNiveau;
	
	private Vecteur vitesseDepart;
	
	/**
	 * Constructeur servant � initialiser le niveau.
	 * @param nouveauxCorps Les corps � mettre dans le niveau,
	 * @param nouveauxObjectifs Les objectifs � mettre dans le niveau.
	 * @param nouveauPointDepart Le point de d�part du vaisseau.
	 * @param nouveauTitreNiveau Le titre du niveau.
	 * @param nouvelleVitesseDepart La vitesse du vaisseau au point de d�part.
	 */
	public Niveau(List<Corps> nouveauxCorps, List<String> nouveauxObjectifs, Vecteur nouveauPointDepart, String nouveauTitreNiveau, Vecteur nouvelleVitesseDepart)
	{
		corps = new ArrayList<>();
		
		if(nouveauxCorps != null)
		{
			for(Corps c : nouveauxCorps)
			{
				ajouterCorps(c);
			}
		}
		
		objectifs = new ArrayList<>();
		
		if(nouveauxObjectifs != null)
		{
			for(String s : nouveauxObjectifs)
			{
				ajouterObjectif(s);
			}
		}
		
		setPointDepart(nouveauPointDepart);
		
		setTitreNiveau(nouveauTitreNiveau);
		
		setVitesseDepart(nouvelleVitesseDepart);
	}
	
	/**
	 * Sert � ajouter un corps dans le niveau.
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
	 * Sert � ajouter un objectif dans le niveau.
	 * @param nouvelObjectif Un nouvel objectif.
	 */
	public void ajouterObjectif(String nouvelObjectif)
	{
		if(nouvelObjectif != null)
		{
			objectifs.add(nouvelObjectif);
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
	 * Retourne la liste d'objectifs.
	 * @return La liste d'objectifs.
	 */
	public List<String> getObjectifs()
	{
		return objectifs;
	}
	
	/**
	 * Retourne le point de d�part du vaisseau.
	 * @return Le point de d�part du vaisseau.
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
	 * Retourne la vitesse de d�part du vaisseau.
	 * @return La vitesse de d�part du vaisseau.
	 */
	public Vecteur getVitesseDepart()
	{
		return vitesseDepart;
	}
	
	/**
	 * Sert � modifier le point de d�part du vaisseau.
	 * @param nouveauPointDepart Le nouveau point de d�part du vaisseau.
	 */
	public void setPointDepart(Vecteur nouveauPointDepart)
	{
		if(nouveauPointDepart != null)
		{
			pointDepart = nouveauPointDepart;
		}
	}
	
	/**
	 * Sert � modifier le titre du niveau.
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
	 * Sert � modifier la vitesse de d�part du vaisseau.
	 * @param nouvelleVitesseDepart La nouvelle vitesse de d�part du vaisseau.
	 */
	public void setVitesseDepart(Vecteur nouvelleVitesseDepart)
	{
		if(nouvelleVitesseDepart != null)
		{
			vitesseDepart = nouvelleVitesseDepart;
		}
	}
}