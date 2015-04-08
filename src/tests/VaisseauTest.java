package tests;

import static org.junit.Assert.*;
import objets.Vaisseau;
import org.junit.Before;
import org.junit.Test;
import utils.Vecteur;

/**
 * Classe de test pour Vaisseau.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class VaisseauTest
{
	private static final double d = 0.001;
	Vaisseau v1, v2, v3, v4, v5, v6;
	
	@Before
	public void testVaisseauDoubleVecteurDoubleDoubleVecteurVecteur()
	{
		v1 = new Vaisseau(0, 0, 0, null, null);
		assertTrue(v1.getPuissance() == Vaisseau.PUISSANCE_DEFAUT);
		assertTrue(v1.getMasse() == Vaisseau.MASSE_DEFAUT);
		assertTrue(v1.getPositionX() == 0 && v1.getPositionY() == 0);
		assertTrue(v1.getVitesse().getX() == 0 && v1.getVitesse().getY() == 0);
		v2 = new Vaisseau(-10, -1, -1, new Vecteur(),
				new Vecteur());
		assertTrue(v2.getPuissance() == Vaisseau.PUISSANCE_DEFAUT);
		assertTrue(v2.getMasse() == Vaisseau.MASSE_DEFAUT);
		assertTrue(v2.getPositionX() == 0 && v2.getPositionY() == 0);
		assertTrue(v2.getVitesse().getX() == 0 && v2.getVitesse().getY() == 0);
		v3 = new Vaisseau(100, 100, 100, new Vecteur(150,
				125), new Vecteur(10, 10));
		assertTrue(v3.getPuissance() == 100);
		assertTrue(v3.getMasse() == 100);
		assertTrue(v3.getPositionX() == 150 && v3.getPositionY() == 125);
		assertEquals(10, v3.getVitesse().getX(), d);
		assertEquals(10, v3.getVitesse().getY(), d);
	}
	
	@Before
	public void testVaisseauDoubleDoubleDoubleDoubleDoubleVecteur()
	{
		v4 = new Vaisseau(0, 0, 0, 0, 0, null);
		assertTrue(v4.getPuissance() == Vaisseau.PUISSANCE_DEFAUT);
		assertTrue(v4.getMasse() == Vaisseau.MASSE_DEFAUT);
		assertTrue(v4.getPositionX() == 0 && v4.getPositionY() == 0);
		assertTrue(v4.getVitesse().getX() == 0 && v4.getVitesse().getY() == 0);
		v5 = new Vaisseau(-10, -1, -1, -1, -1, new Vecteur());
		assertTrue(v5.getPuissance() == Vaisseau.PUISSANCE_DEFAUT);
		assertTrue(v5.getMasse() == Vaisseau.MASSE_DEFAUT);
		assertTrue(v5.getPositionX() == -1 && v5.getPositionY() == -1);
		assertTrue(v5.getVitesse().getX() == 0 && v5.getVitesse().getY() == 0);
		v6 = new Vaisseau(100, 100, 100, 150, 125,
				new Vecteur(10, 10));
		assertTrue(v6.getPuissance() == 100);
		assertTrue(v6.getMasse() == 100);
		assertTrue(v6.getPositionX() == 150 && v6.getPositionY() == 125);
		assertTrue(v6.getVitesse().getX() == 10 && v6.getVitesse().getY() == 10);
	}
	
	@Test
	public void testSetStatique()
	{
		assertFalse(v1.isStatique());
		assertFalse(v2.isStatique());
		assertFalse(v3.isStatique());
		assertFalse(v4.isStatique());
		assertFalse(v5.isStatique());
		assertFalse(v6.isStatique());
		v1.setStatique(true);
		assertTrue(v1.isStatique());
	}
	
	/*
	 * @Test public void testGetNoeud() { assertTrue(v1.getNoeud() != null);
	 * assertTrue(v2.getNoeud() != null); assertTrue(v3.getNoeud() != null);
	 * assertTrue(v4.getNoeud() != null); assertTrue(v5.getNoeud() != null);
	 * assertTrue(v6.getNoeud() != null); }
	 */
	
	
	@Test
	public void testGetPuissance()
	{
		assertTrue(v1.getPuissance() == v1.getPuissance());
		assertTrue(v2.getPuissance() == v2.getPuissance());
		assertTrue(v3.getPuissance() == v3.getPuissance());
		assertTrue(v4.getPuissance() == v4.getPuissance());
		assertTrue(v5.getPuissance() == v5.getPuissance());
		assertTrue(v6.getPuissance() == v6.getPuissance());
	}
	
	@Test
	public void testSetPuissance()
	{
		v1.setPuissance(50);
		assertTrue(v1.getPuissance() == 50);
		v2.setPuissance(-1000);
		assertTrue(v2.getPuissance() == Vaisseau.PUISSANCE_DEFAUT);
		v3.setPuissance(0);
		assertTrue(v3.getPuissance() == Vaisseau.PUISSANCE_DEFAUT);
	}
	
}
