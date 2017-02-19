package modele;

import java.util.List;
import objets.Tete;
import utils.Vecteur;

/**
 * Classe servant a faire en sorte que les entites respectent les lois de la
 * physique.
 * 
 * @author equipe Bolduc
 * @version 1.0
 */
public class MoteurPhysique
{
	private static final double GRAVITE = 6.67384E-11;
	private double tailleEcranX;
	private double tailleEcranY;
	
	/**
	 * Sert a initialiser les variables du moteur physique.
	 */
	public MoteurPhysique()
	{
		tailleEcranX = 600;
		tailleEcranY = 600;
	}
	
	/**
	 * Calcule les forces exercees sur les objets a la suite de la collision.
	 * 
	 * @param corps1 Premier corps en collision.
	 * @param corps2 Deuxieme corps en collision.
	 */
	private void calculerCollision(Corps corps1, Corps corps2)
	{
		// Repousse les corps les uns des autres.
		Vecteur deltaS = corps1.getPosition().soustraire(corps2.getPosition());
		double d = deltaS.getNorme();
		
		// evite une division par zero.
		if(d == 0)
		{
			d = 0.00001;
		}
		
		Vecteur transMin = deltaS.multiplication((corps1.getRayon()
				+ corps2.getRayon() - d)
				/ d);
		
		double im1 = 1.0 / corps1.getMasse();
		double im2 = 1.0 / corps2.getMasse();
		
		if(!corps1.isStatique())
		{
			corps1.setPosition(corps1.getPosition().additionner(
					transMin.multiplication(im1 / (im1 + im2))));
		}
		if(!corps2.isStatique())
		{
			corps2.setPosition(corps2.getPosition().soustraire(
					transMin.multiplication(im2 / (im1 + im2))));
		}
		
		// Calcule la nouvelle vitesse.
		// Ces formules sont tirees de ce site web :
		// http://www.vobarian.com/collisions/index.html
		double m1 = corps1.getMasse();
		double m2 = corps2.getMasse();
		Vecteur n = corps1.getPosition().soustraire(corps2.getPosition())
				.normaliser();
		Vecteur t = new Vecteur(-n.getY(), n.getX());
		
		double v1ni = n.multiplication(corps1.getVitesse());
		double v1ti = t.multiplication(corps1.getVitesse());
		double v2ni = n.multiplication(corps2.getVitesse());
		double v2ti = t.multiplication(corps2.getVitesse());
		
		double v1nf = (v1ni * (m1 - m2) + 2 * m2 * v2ni) / (m1 + m2);
		double v1tf = v1ti;
		double v2nf = (v2ni * (m2 - m1) + 2 * m1 * v1ni) / (m1 + m2);
		double v2tf = v2ti;
		
		Vecteur n1 = n.multiplication(v1nf);
		Vecteur t1 = t.multiplication(v1tf);
		Vecteur n2 = n.multiplication(v2nf);
		Vecteur t2 = t.multiplication(v2tf);
		
		Vecteur v1 = n1.additionner(t1);
		Vecteur v2 = n2.additionner(t2);
		
		if(!corps1.isStatique())
		{
			corps1.setVitesse(v1);
		}
		if(!corps2.isStatique())
		{
			corps2.setVitesse(v2);
		}
	}
	
	/**
	 * Detecte les collisions.
	 * 
	 * @param corps La liste des corps pouvant potentiellement entrer en
	 *            collision.
	 */
	private void gererCollisions(List<Corps> corps)
	{
		if(corps != null)
		{
			for(int i = 0; i < corps.size(); i++)
			{
				if(corps.get(i) != null)
				{
					if(corps.get(i) instanceof Tete)
					{
						if(corps.get(i).getPositionX() < 0)
						{
							corps.get(i).setPositionX(0);
							corps.get(i).setVitesse(
									new Vecteur(-corps.get(i).getVitesse()
											.getX(), corps.get(i).getVitesse()
											.getY()));
						}
						
						if(corps.get(i).getPositionX() + 2
								* corps.get(i).getRayon() > tailleEcranX)
						{
							corps.get(i).setPositionX(
									tailleEcranX - 2 * corps.get(i).getRayon());
							corps.get(i).setVitesse(
									new Vecteur(-corps.get(i).getVitesse()
											.getX(), corps.get(i).getVitesse()
											.getY()));
						}
						
						if(corps.get(i).getPositionY() < 0)
						{
							corps.get(i).setPositionY(0);
							corps.get(i).setVitesse(
									new Vecteur(corps.get(i).getVitesse()
											.getX(), -corps.get(i).getVitesse()
											.getY()));
						}
						
						if(corps.get(i).getPositionY() + 2
								* corps.get(i).getRayon() > tailleEcranY)
						{
							corps.get(i).setPositionY(
									tailleEcranY - 2 * corps.get(i).getRayon());
							corps.get(i).setVitesse(
									new Vecteur(corps.get(i).getVitesse()
											.getX(), -corps.get(i).getVitesse()
											.getY()));
						}
					}
					
					for(int j = i + 1; j < corps.size(); j++)
					{
						if(corps.get(j) != null)
						{
							Vecteur diff = corps.get(i).getPosition()
									.soustraire(corps.get(j).getPosition());
							double distance = diff.getNorme();
							double sommeRayons = corps.get(i).getRayon()
									+ corps.get(j).getRayon();
							
							if(distance < sommeRayons)
							{
								calculerCollision(corps.get(i), corps.get(j));
								corps.get(i).onCollision(corps.get(j));
								corps.get(j).onCollision(corps.get(i));
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Retourne la largeur de l'ecran.
	 * 
	 * @return Largeur de l'ecran.
	 */
	public double getTailleEcranX()
	{
		return tailleEcranX;
	}
	
	/**
	 * Modifie la largeur de l'ecran.
	 * 
	 * @param nouvelleTailleEcran La nouvelle largeur de l'ecran.
	 */
	public void setTailleEcranX(double nouvelleTailleEcran)
	{
		if(nouvelleTailleEcran > 0)
		{
			tailleEcranX = nouvelleTailleEcran;
		}
	}
	
	/**
	 * Retourne la hauteur de l'ecran.
	 * 
	 * @return Hauteur de l'ecran.
	 */
	public double getTailleEcranY()
	{
		return tailleEcranY;
	}
	
	/**
	 * Modifie la hauteur de l'ecran.
	 * 
	 * @param nouvelleTailleEcran La nouvelle hauteur de l'ecran.
	 */
	public void setTailleEcranY(double nouvelleTailleEcran)
	{
		if(nouvelleTailleEcran > 0)
		{
			tailleEcranY = nouvelleTailleEcran;
		}
	}
	
	/**
	 * Cette methode met a jour la position et la vitesse des differents corps
	 * non-statiques.
	 * 
	 * @param corps Liste des corps qui interagissent ensembles.
	 * @param dt Intervalle de temps depuis la derniere mise a jour.
	 */
	public void update(List<Corps> corps, double dt)
	{
		if(corps != null && dt > 0)
		{
			for(Corps c1: corps)
			{
				if(c1 != null && !c1.isStatique())
				{
					c1.setPosition(c1.getPosition().additionner(
							c1.getVitesse().multiplication(dt)));
					
					Vecteur force = new Vecteur();
					
					for(Corps c2: corps)
					{
						if(c1 != c2 && c2 != null)
						{
							double distance = c2.getPosition()
									.soustraire(c1.getPosition()).getNorme();
							Vecteur direction = c2.getPosition()
									.soustraire(c1.getPosition()).normaliser();
							force = force
									.additionner(direction
											.multiplication((GRAVITE
													* c1.getMasse() * c2
														.getMasse())
													/ Math.pow(distance, 2)));
						}
					}
					force = force.additionner(c1.getForceExt());
					c1.setVitesse(c1.getVitesse().additionner(
							force.multiplication(1.0 / c1.getMasse())
									.multiplication(dt)));
				}
			}
			
			gererCollisions(corps);
		}
	}
}
