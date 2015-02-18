package modele;
import java.util.List;
import utils.Vecteur;
/**
 * Classe servant à faire en sorte que les entités respectent les lois de la physique.
 * @author Équipe Bolduc
 * @version 1.0
 */
public class MoteurPhysique
{
	private static final double GRAVITE = 6.67384E-11;
	
	/**
	 * Cette méthode met à jour la position et la vitesse des différents corps non-statiques.
	 * @param corps Liste des corps qui interagissent ensembles.
	 * @param dt Intervalle de temps depuis la dernière mise à jour.
	 */
	public void update(List<Corps> corps, double dt)
	{
		if(corps != null && dt > 0)
		{
			for(Corps c1 : corps)
			{
				if(c1 != null && !c1.isStatique())
				{
					c1.setPosition(c1.getPosition().additionner(c1.getVitesse().multiplication(dt)));
					c1.setVitesse(c1.getVitesse().additionner(c1.getForceExt().multiplication(1.0 / c1.getMasse()).multiplication(dt)));
				}
			}
			
			for(Corps c1 : corps)
			{
				if(c1 != null && !c1.isStatique())
				{
					Vecteur force = new Vecteur();
					
					for(Corps c2 : corps)
					{
						if(c1 != c2 && c2 != null)
						{
							double distance = c2.getPosition().soustraire(c1.getPosition()).getNorme();
							Vecteur direction = c2.getPosition().soustraire(c1.getPosition()).normaliser();
							force.additionner(direction.multiplication((GRAVITE * c1.getMasse() * c2.getMasse()) / Math.pow(distance, 2)));
						}
					}
					c1.setVitesse(c1.getVitesse().additionner(force.multiplication(1.0 / c1.getMasse()).multiplication(dt)));
				}
			}
		}
	}
}
