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
 * @author Équipe Bolduc
 */
public class MoteurPhysiqueTest
{
	private List<Corps> corps;
	private MoteurPhysique moteur;
	private Vaisseau vaisseau;
	
	@Before
	public void creerCorps()
	{
		moteur = new MoteurPhysique();
		corps = new ArrayList<>();
		vaisseau = new Vaisseau(0, new Vecteur(1, 0), 70000, 0, new Vecteur(0, 100), false, new Vecteur(10, 0));
		corps.add(vaisseau);
		corps.add(new Planete(5.972E24, null, null));
	}
	
	@Test
	public void testUpdate()
	{
		moteur.update(corps, 1);
		assertEquals(vaisseau.getPositionX(), 10, 0.0001);
	}
}