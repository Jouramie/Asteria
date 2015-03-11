package tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import modele.Corps;
import modele.MoteurPhysique;
import objets.Planete;
import objets.Vaisseau;
import org.junit.Before;
import org.junit.Test;
import utils.Vecteur;

/**
 * Classe de tests pour MoteurPhysique.
 * 
 * @author Équipe Bolduc
 */
public class MoteurPhysiqueTest
{
	private List<Corps> corps1, corps2, corps3;
	private MoteurPhysique moteur;
	private Vaisseau vaisseau1, vaisseau2, vaisseau3;
	
	@Before
	public void creerCorps()
	{
		moteur = new MoteurPhysique();
		
		corps1 = new ArrayList<>();
		vaisseau1 = new Vaisseau(0, new Vecteur(1, 0), 100, 0, new Vecteur(0,
				130), new Vecteur(10, 0));
		corps1.add(vaisseau1);
		corps1.add(new Planete(15707.96327, new Vecteur(0, 0), 100));
		corps1.add(null);
		
		corps2 = new ArrayList<>();
		vaisseau2 = new Vaisseau(0, new Vecteur(1, 0), 100, 0, new Vecteur(65,
				10), new Vecteur(10, 0));
		corps2.add(vaisseau2);
		corps2.add(new Planete(100, new Vecteur(210, 10), 100));
		
		corps3 = new ArrayList<>();
		vaisseau3 = new Vaisseau(0, new Vecteur(1, 0), 100, 0, new Vecteur(65,
				10), new Vecteur(10, 0));
		corps3.add(new Planete(100, new Vecteur(210, 10), 100));
		corps3.add(vaisseau3);
	}
	
	@Test
	public void testUpdate()
	{
		moteur.update(corps1, 0.5);
		assertEquals(vaisseau1.getPositionX(), 5.0, 0.001);
		moteur.update(corps1, 0.5);
		assertEquals(vaisseau1.getPositionX(), 10.0, 0.001);
		moteur.update(corps1, 0);
		assertEquals(vaisseau1.getPositionX(), 10.0, 0.001);
		moteur.update(null, 1);
		assertEquals(vaisseau1.getPositionX(), 10.0, 0.001);
		moteur.update(null, 0);
		assertEquals(vaisseau1.getPositionX(), 10.0, 0.001);
		
		assertEquals(vaisseau2.getVitesse().getX(), 10, 0.001);
		moteur.update(corps2, 2);
		assertEquals(vaisseau2.getVitesse().getX(), 10, 0.001);
		moteur.update(corps2, 0.5);
		assertEquals(vaisseau2.getVitesse().getX(), 0, 0.001);
		
		assertEquals(vaisseau3.getVitesse().getX(), 10, 0.001);
		moteur.update(corps3, 2);
		assertEquals(vaisseau3.getVitesse().getX(), 10, 0.001);
		moteur.update(corps3, 0.5);
		assertEquals(vaisseau3.getVitesse().getX(), 0, 0.001);
	}
}
