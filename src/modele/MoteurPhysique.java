package modele;

import java.util.List;
import utils.Vecteur;

/**
 * Classe servant � faire en sorte que les entit�s respectent les lois de la
 * physique.
 * 
 * @author �quipe Bolduc
 * @version 1.0
 */
public class MoteurPhysique
{
	private static final double GRAVITE = 6.67384E-11;
	
	/**
	 * Calcule les forces exerc�es sur les objets � la suite de la collision.
	 * 
	 * @param corps1
	 *            Premier corps en collision.
	 * @param corps2
	 *            Deuxi�me corps en collision.
	 */
	private void calculerCollision(Corps corps1, Corps corps2)
	{
		// Repousse les corps les uns des autres.
		Vecteur deltaS = corps1.getPosition().soustraire(corps2.getPosition());
		double d = deltaS.getNorme();
		
		// �vite une division par z�ro.
		if (d == 0)
		{
			d = 0.00001;
		}
		
		Vecteur transMin = deltaS.multiplication((corps1.getRayon()
				+ corps2.getRayon() - d)
				/ d);
		
		double im1 = 1.0 / corps1.getMasse();
		double im2 = 1.0 / corps2.getMasse();
		
		if (!corps1.isStatique())
		{
			corps1.setPosition(corps1.getPosition().additionner(
					transMin.multiplication(im1 / (im1 + im2))));
		}
		if (!corps2.isStatique())
		{
			corps2.setPosition(corps2.getPosition().soustraire(
					transMin.multiplication(im2 / (im1 + im2))));
		}
		
		// Calcule la nouvelle vitesse.
		// Ces formules sont tir�es de ce site web :
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
		
		if (!corps1.isStatique())
		{
			corps1.setVitesse(v1);
		}
		if (!corps2.isStatique())
		{
			corps2.setVitesse(v2);
		}
	}
	
	/**
	 * D�tecte les collisions.
	 * 
	 * @param corps
	 *            La liste des corps pouvant potentiellement entrer en
	 *            collision.
	 */
	private void gererCollisions(List<Corps> corps)
	{
		if (corps != null)
		{
			for (int i = 0; i < corps.size(); i++)
			{
				if (corps.get(i) != null)
				{
					for (int j = i + 1; j < corps.size(); j++)
					{
						if (corps.get(j) != null)
						{
							Vecteur diff = corps.get(i).getPosition().soustraire(corps.get(j).getPosition());
							double distance = diff.getNorme();
							double sommeRayons = corps.get(i).getRayon() + corps.get(j).getRayon();
							
							if (distance < sommeRayons)
							{
								corps.get(i).onCollision(corps.get(j));
								corps.get(j).onCollision(corps.get(i));
								calculerCollision(corps.get(i), corps.get(j));
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Cette m�thode met � jour la position et la vitesse des diff�rents corps
	 * non-statiques.
	 * 
	 * @param corps
	 *            Liste des corps qui interagissent ensembles.
	 * @param dt
	 *            Intervalle de temps depuis la derni�re mise � jour.
	 */
	public void update(List<Corps> corps, double dt)
	{
		if (corps != null && dt > 0)
		{
			for (Corps c1 : corps)
			{
				if (c1 != null && !c1.isStatique())
				{
					c1.setPosition(c1.getPosition().additionner(
							c1.getVitesse().multiplication(dt)));
					
					Vecteur force = new Vecteur();
					
					for (Corps c2 : corps)
					{
						if (c1 != c2 && c2 != null)
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
