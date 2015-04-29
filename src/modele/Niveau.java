package modele;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import objets.Planete;
import objets.Planete.Texture;
import objets.Vaisseau;
import objets.VaisseauJoueur;
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
	
	private String descriptionNiveau;
	
	private Objectif objectif;
	
	private Vecteur pointDepart;
	
	private String titreNiveau;
	
	private double vitesseDepart;
	
	public Niveau()
	{
		this(new ArrayList<Corps>(), "", null, new Vecteur(), "", 0);
	}
	
	/**
	 * Constructeur servant à initialiser le niveau.
	 * 
	 * @param nouveauxCorps
	 *            Les corps à mettre dans le niveau.
	 * @param nouvelleDescriptionNiveau
	 *            La description du niveau.
	 * @param nouvelObjectif
	 *            L'objectif à mettre dans le niveau.
	 * @param nouveauPointDepart
	 *            Le point de départ du vaisseau.
	 * @param nouveauTitreNiveau
	 *            Le titre du niveau.
	 * @param nouvelleVitesseDepart
	 *            La vitesse du vaisseau au point de départ.
	 */
	public Niveau(List<Corps> nouveauxCorps, String nouvelleDescriptionNiveau,
			Objectif nouvelObjectif, Vecteur nouveauPointDepart,
			String nouveauTitreNiveau, double nouvelleVitesseDepart)
	{
		corps = new ArrayList<>();
		
		if (nouveauxCorps != null)
		{
			for (Corps c : nouveauxCorps)
			{
				ajouterCorps(c);
			}
		}
		
		setDescriptionNiveau(nouvelleDescriptionNiveau);
		
		setObjectif(nouvelObjectif);
		
		setPointDepart(nouveauPointDepart);
		
		setTitreNiveau(nouveauTitreNiveau);
		
		setVitesseDepart(nouvelleVitesseDepart);
	}
	
	/**
	 * Sert à ajouter un corps dans le niveau.
	 * 
	 * @param nouveauCorps
	 *            Un nouveau corps.
	 */
	public void ajouterCorps(Corps nouveauCorps)
	{
		if (nouveauCorps != null)
		{
			corps.add(nouveauCorps);
		}
	}
	
	/**
	 * Enlève un corps du niveau.
	 * @param c
	 */
	public void supprimerCorps(Corps c)
	{
		if(corps.contains(c))
		{
			corps.remove(c);
		}
	}
	
	/**
	 * Charge le niveau passé en paramètre.
	 * 
	 * @param fichier
	 *            Le niveau à charger.
	 * @return Le niveau chargé.
	 */
	public static Niveau chargerNiveau(InputStream fichier)
	{
		ArrayList<Corps> corps = new ArrayList<>();
		String descriptionNiveau = null;
		Objectif objectif = null;
		Vecteur pointDepart = null;
		String titreNiveau = null;
		double vitesseDepart = 0;
		Niveau niveau = null;
		
		if (fichier != null)
		{
			try
			{
				InputStreamReader isr = new InputStreamReader(fichier);
				BufferedReader bw = new BufferedReader(isr);
				String ligne;
				StringTokenizer st;
				
				while ((ligne = bw.readLine()) != null)
				{
					st = new StringTokenizer(ligne, ";");
					
					if(st.hasMoreTokens())
					{
						switch (st.nextToken().trim().toLowerCase())
						{
						case "planete":
						{
							double masse = Double
									.parseDouble(st.nextToken().trim());
							double positionX = Double.parseDouble(st.nextToken()
									.trim());
							double positionY = Double.parseDouble(st.nextToken()
									.trim());
							double rayon = Double
									.parseDouble(st.nextToken().trim());
							String texture = st.nextToken().trim();
							
							Planete p = new Planete(masse, positionX, positionY,
									rayon);
							p.setTexture(Texture.getTexture(texture));
							corps.add(p);
							break;
						}
						case "vaisseau":
						{
							double puissance = Double.parseDouble(st.nextToken()
									.trim());
							double masse = Double
									.parseDouble(st.nextToken().trim());
							double capaciteCarburant = Double.parseDouble(st
									.nextToken().trim());
							double carburantDepart = Double.parseDouble(st
									.nextToken().trim());
							double positionX = Double.parseDouble(st.nextToken()
									.trim());
							double positionY = Double.parseDouble(st.nextToken()
									.trim());
							double vitesseX = Double.parseDouble(st.nextToken()
									.trim());
							double vitesseY = Double.parseDouble(st.nextToken()
									.trim());
							Vecteur position = new Vecteur(positionX, positionY);
							Vecteur vitesse = new Vecteur(vitesseX, vitesseY);
							
							corps.add(new Vaisseau(puissance, masse,
									capaciteCarburant, carburantDepart, position, vitesse));
							break;
						}
						case "vaisseaujoueur":
						{
							double puissance = Double.parseDouble(st.nextToken()
									.trim());
							double masse = Double
									.parseDouble(st.nextToken().trim());
							double capaciteCarburant = Double.parseDouble(st
									.nextToken().trim());
							double carburantDepart = Double.parseDouble(st
									.nextToken().trim());
							
							corps.add(new VaisseauJoueur(puissance,
									masse, capaciteCarburant, carburantDepart));
							break;
						}
						case "descriptionniveau":
						{
							descriptionNiveau = st.nextToken().trim();
							break;
						}
						case "objectifrayon":
						{
							double posRayonX = Double.parseDouble(st.nextToken()
									.trim());
							double posRayonY = Double.parseDouble(st.nextToken()
									.trim());
							double rayon = Double
									.parseDouble(st.nextToken().trim());
							Vecteur posRayon = new Vecteur(posRayonX, posRayonY);
							
							objectif = new ObjectifRayon(posRayon, rayon);
							break;
						}
						case "pointdepart":
						{
							double pointDepartX = Double.parseDouble(st.nextToken()
									.trim());
							double pointDepartY = Double.parseDouble(st.nextToken()
									.trim());
							
							pointDepart = new Vecteur(pointDepartX, pointDepartY);
							break;
						}
						case "titreniveau":
						{
							titreNiveau = st.nextToken().trim();
							break;
						}
						case "vitessedepart":
						{
							vitesseDepart = Double.parseDouble(st
									.nextToken().trim());
							break;
						}
						}
					}
					}
				bw.close();
				isr.close();
				
				niveau = new Niveau(corps, descriptionNiveau, objectif,
						pointDepart, titreNiveau, vitesseDepart);
			}
			catch (Exception e)
			{
			}
		}
		return niveau;
	}
	
	/**
	 * Retourne la liste de corps.
	 * 
	 * @return La liste de corps.
	 */
	public List<Corps> getCorps()
	{
		return corps;
	}
	
	/**
	 * Retourne la description du niveau.
	 * 
	 * @return La description du niveau.
	 */
	public String getDescriptionNiveau()
	{
		return descriptionNiveau;
	}
	
	/**
	 * Retourne l'objectif.
	 * 
	 * @return L'objectif.
	 */
	public Objectif getObjectif()
	{
		return objectif;
	}
	
	/**
	 * Retourne le point de départ du vaisseau.
	 * 
	 * @return Le point de départ du vaisseau.
	 */
	public Vecteur getPointDepart()
	{
		return pointDepart;
	}
	
	/**
	 * Retourne le titre du niveau.
	 * 
	 * @return Le titre du niveau.
	 */
	public String getTitreNiveau()
	{
		return titreNiveau;
	}
	
	/**
	 * Retourne la vitesse de départ du vaisseau.
	 * 
	 * @return La vitesse de départ du vaisseau.
	 */
	public double getVitesseDepart()
	{
		return vitesseDepart;
	}
	
	/**
	 * Sauvegarde le niveau dans le fichier passé en paramètre.
	 * 
	 * @param fichier
	 *            Fichier dans lequel le niveau est à sauvegarder.
	 */
	public void sauvegarderNiveau(File fichier)
	{
		try
		{
			FileWriter fw = new FileWriter(fichier);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for (Corps c : corps)
			{
				switch (c.getClass().getName().toLowerCase())
				{
				case "objets.planete":
				{
					Planete p = (Planete) c;
					bw.write("Planete ; " + p.getMasse() + " ; "
							+ p.getPositionX() + " ; " + p.getPositionY()
							+ " ; " + p.getRayon() + " ; "
							+ p.getTexture().toString());
					break;
				}
				case "objets.vaisseau":
				{
					Vaisseau v = (Vaisseau) c;
					bw.write("Vaisseau ; " + v.getPuissance() + " ; "
							+ v.getMasse() + " ; " + v.getCarburantMax()
							+ " ; " + v.getCarburantDepart() + " ; " + v.getPositionX() + " ; "
							+ v.getPositionY() + " ; " + v.getVitesse().getX()
							+ " ; " + v.getVitesse().getY());
					break;
				}
				case "objets.vaisseaujoueur":
				{
					VaisseauJoueur vj = (VaisseauJoueur) c;
					bw.write("VaisseauJoueur ; " + vj.getPuissance() + " ; "
							+ vj.getMasse() + " ; " 
							+ vj.getCarburantMax() + " ; "
							+ vj.getCarburantDepart());
					break;
				}
				}
				bw.newLine();
			}
			
			bw.write("DescriptionNiveau ; " + descriptionNiveau);
			bw.newLine();
			
			if (objectif != null)
			{
				switch (objectif.getClass().getName().toLowerCase())
				{
				case "modele.objectifrayon":
				{
					ObjectifRayon or = (ObjectifRayon) objectif;
					bw.write("ObjectifRayon ; " + or.getPosRayon().getX()
							+ " ; " + or.getPosRayon().getY() + " ; "
							+ or.getRayon());
					break;
				}
				}
				bw.newLine();
			}
			
			bw.write("PointDepart ; " + pointDepart.getX() + " ; "
					+ pointDepart.getY());
			bw.newLine();
			
			bw.write("TitreNiveau ; " + titreNiveau);
			bw.newLine();
			
			bw.write("VitesseDepart ; " + vitesseDepart);
			bw.newLine();
			
			bw.close();
			fw.close();
		}
		catch (Exception e)
		{
		}
	}
	
	public void setDescriptionNiveau(String nouvelleDescriptionNiveau)
	{
		if (nouvelleDescriptionNiveau != null)
		{
			descriptionNiveau = nouvelleDescriptionNiveau;
		}
	}
	
	/**
	 * Sert à modifier l'objectif.
	 * 
	 * @param nouvelObjectif
	 *            Le nouvel objectif.
	 */
	public void setObjectif(Objectif nouvelObjectif)
	{
		if (nouvelObjectif != null)
		{
			objectif = nouvelObjectif;
		}
	}
	
	/**
	 * Sert à modifier le point de départ du vaisseau.
	 * 
	 * @param nouveauPointDepart
	 *            Le nouveau point de départ du vaisseau.
	 */
	public void setPointDepart(Vecteur nouveauPointDepart)
	{
		if (nouveauPointDepart != null)
		{
			pointDepart = nouveauPointDepart;
		}
	}
	
	/**
	 * Sert à modifier le titre du niveau.
	 * 
	 * @param nouveauTitreNiveau
	 *            Le nouveau titre du niveau.
	 */
	public void setTitreNiveau(String nouveauTitreNiveau)
	{
		if (nouveauTitreNiveau != null)
		{
			titreNiveau = nouveauTitreNiveau;
		}
	}
	
	/**
	 * Sert à modifier la vitesse de départ du vaisseau.
	 * 
	 * @param nouvelleVitesseDepart
	 *            La nouvelle vitesse de départ du vaisseau.
	 */
	public void setVitesseDepart(double nouvelleVitesseDepart)
	{
		if (nouvelleVitesseDepart >= 0)
		{
			vitesseDepart = nouvelleVitesseDepart;
		}
	}
}
