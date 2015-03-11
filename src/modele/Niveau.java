package modele;
import java.util.ArrayList;
import java.util.List;

import utils.Vecteur;
public class Niveau
{
	private List<Corps> corps;
	
	private Objectif objectif;
	
	private Vecteur pointDepart;
	
	private String titreNiveau;
	
	private Vecteur vitesseDepart;
	
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
	
	public void ajouterCorps(Corps nouveauCorps)
	{
		if(nouveauCorps != null)
		{
			corps.add(nouveauCorps);
		}
	}
	
	public void setObjectif(Objectif nouvelObjectif)
	{
		if(nouvelObjectif != null)
		{
			objectif = nouvelObjectif;
		}
	}
	
	public List<Corps> getCorps()
	{
		return corps;
	}
	
	public Objectif getObjectif()
	{
		return objectif;
	}
	
	public Vecteur getPointDepart()
	{
		return pointDepart;
	}
	
	public String getTitreNiveau()
	{
		return titreNiveau;
	}
	
	public Vecteur getVitesseDepart()
	{
		return vitesseDepart;
	}
	
	public void setPointDepart(Vecteur nouveauPointDepart)
	{
		if(nouveauPointDepart != null)
		{
			pointDepart = nouveauPointDepart;
		}
	}
	
	public void setTitreNiveau(String nouveauTitreNiveau)
	{
		if(nouveauTitreNiveau != null)
		{
			titreNiveau = nouveauTitreNiveau;
		}
	}
	
	public void setVitesseDepart(Vecteur nouvelleVitesseDepart)
	{
		if(nouvelleVitesseDepart != null)
		{
			vitesseDepart = nouvelleVitesseDepart;
		}
	}
}