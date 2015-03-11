package modele;
import java.util.ArrayList;
import java.util.List;

import utils.Vecteur;
public class Niveau
{
	private List<Corps> corps;
	
	private List<String> objectifs;
	
	private Vecteur pointDepart;
	
	private String titreNiveau;
	
	private Vecteur vitesseDepart;
	
	public Niveau(List<Corps> nouveauxCorps, List<String> nouveauxObjectifs, Vecteur nouveauPointDepart, String nouveauTitreNiveau, Vecteur nouvelleVitesseDepart)
	{
		if(nouveauxCorps != null)
		{
			for(Corps c : nouveauxCorps)
			{
				ajouterCorps(c);
			}
		}
		else
		{
			corps = new ArrayList<>();
		}
		
		if(nouveauxObjectifs != null)
		{
			for(String s : nouveauxObjectifs)
			{
				ajouterObjectif(s);
			}
		}
		else
		{
			objectifs = new ArrayList<>();
		}
		
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
	
	public void ajouterObjectif(String nouvelObjectif)
	{
		if(nouvelObjectif != null)
		{
			objectifs.add(nouvelObjectif);
		}
	}
	
	public List<Corps> getCorps()
	{
		return corps;
	}
	
	public List<String> getObjectifs()
	{
		return objectifs;
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