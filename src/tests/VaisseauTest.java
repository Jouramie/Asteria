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
		v1 = new Vaisseau(0, 0, 100, 100, new Vecteur(), new Vecteur());
		assertTrue(v1.getPuissance() == Vaisseau.PUISSANCE_DEFAUT);
		assertTrue(v1.getMasse() == Vaisseau.MASSE_DEFAUT);
		assertTrue(v1.getPositionX() == 0 && v1.getPositionY() == 0);
		assertTrue(v1.getVitesse().getX() == 0 && v1.getVitesse().getY() == 0);
		v2 = new Vaisseau(-10, -1, -1, -1, new Vecteur(),
				new Vecteur());
		assertTrue(v2.getPuissance() == Vaisseau.PUISSANCE_DEFAUT);
		assertTrue(v2.getMasse() == Vaisseau.MASSE_DEFAUT);
		assertTrue(v2.getPositionX() == 0 && v2.getPositionY() == 0);
		assertTrue(v2.getVitesse().getX() == 0 && v2.getVitesse().getY() == 0);
		v3 = new Vaisseau(100, 100, 100, 100, new Vecteur(150,
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
		v4 = new Vaisseau(0, 0, 0, 0, null, null);
		assertTrue(v4.getPuissance() == Vaisseau.PUISSANCE_DEFAUT);
		assertTrue(v4.getMasse() == Vaisseau.MASSE_DEFAUT);
		assertTrue(v4.getPositionX() == 0 && v4.getPositionY() == 0);
		assertTrue(v4.getVitesse().getX() == 0 && v4.getVitesse().getY() == 0);
		v5 = new Vaisseau(-10, -1, -1, -1, new Vecteur(-1, -1), new Vecteur());
		assertTrue(v5.getPuissance() == Vaisseau.PUISSANCE_DEFAUT);
		assertTrue(v5.getMasse() == Vaisseau.MASSE_DEFAUT);
		assertTrue(v5.getPositionX() == -1 && v5.getPositionY() == -1);
		assertTrue(v5.getVitesse().getX() == 0 && v5.getVitesse().getY() == 0);
		v6 = new Vaisseau(100, 100, 100, 150, new Vecteur(150, 125), new Vecteur(10, 10));
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
	
	@Test
	public void testSetCarburantMax()
	{
		v1.setCarburantMax(76.0);
		assertEquals(76.0, v1.getCarburantMax(), 0.0001);
		v1.setCarburantMax(-76);
		assertEquals(20.0, v1.getCarburantMax(), 0.0001);
		v1.setCarburantMax(0);
		assertEquals(20.0, v1.getCarburantMax(), 0.0001);
	}
	
	@Test
	public void testGetCarburantMax()
	{
		assertEquals(100.0, v1.getCarburantMax(), 0.0001);
		assertEquals(20.0, v2.getCarburantMax(), 0.0001);
		assertEquals(100.0, v3.getCarburantMax(), 0.0001);
		assertEquals(20.0, v4.getCarburantMax(), 0.0001);
		assertEquals(20.0, v5.getCarburantMax(), 0.0001);
	}
	
	@Test
	public void testSetCarburantRestant()
	{
		v1.setCarburantRestant(76.0);
		assertEquals(76.0, v1.getCarburantRestant(), 0.0001);
		v1.setCarburantRestant(-76);
		assertEquals(0.0, v1.getCarburantRestant(), 0.0001);
		v1.setCarburantRestant(0);
		assertEquals(0.0, v1.getCarburantRestant(), 0.0001);
		v1.setCarburantRestant(80000);
		assertEquals(100.0, v1.getCarburantRestant(), 0.0001);
	}
	
	@Test
	public void testGetCarburantRestant()
	{
		assertEquals(100.0, v1.getCarburantRestant(), 0.0001);
		assertEquals(0.0, v2.getCarburantRestant(), 0.0001);
		assertEquals(100.0, v3.getCarburantRestant(), 0.0001);
		assertEquals(0.0, v4.getCarburantRestant(), 0.0001);
		assertEquals(0.0, v5.getCarburantRestant(), 0.0001);
	}
	
	@Test
	public void testSetCarburantDepart()
	{
		v1.setCarburantDepart(76.0);
		assertEquals(76.0, v1.getCarburantDepart(), 0.0001);
		v1.setCarburantDepart(-76);
		assertEquals(0.0, v1.getCarburantDepart(), 0.0001);
		v1.setCarburantDepart(8000);
		assertEquals(8000.0, v1.getCarburantMax(), 0.0001);
		assertEquals(8000.0, v1.getCarburantDepart(), 0.0001);
		v1.setCarburantDepart(0);
		assertEquals(0.0, v1.getCarburantDepart(), 0.0001);
	}
	
	@Test
	public void testGetCarburantDepart()
	{
		assertEquals(100.0, v1.getCarburantDepart(), 0.0001);
		assertEquals(0.0, v2.getCarburantDepart(), 0.0001);
		assertEquals(100.0, v3.getCarburantDepart(), 0.0001);
		assertEquals(0.0, v4.getCarburantDepart(), 0.0001);
		assertEquals(0.0, v5.getCarburantDepart(), 0.0001);
	}
	
	@Test
	public void testGetPuissanceMax()
	{
		assertEquals(Vaisseau.PUISSANCE_DEFAUT, v1.getPuissanceMax(), 0.0001);
		assertEquals(Vaisseau.PUISSANCE_DEFAUT, v2.getPuissanceMax(), 0.0001);
		assertEquals(100, v3.getPuissanceMax(), 0.0001);
		assertEquals(Vaisseau.PUISSANCE_DEFAUT, v4.getPuissanceMax(), 0.0001);
		assertEquals(Vaisseau.PUISSANCE_DEFAUT, v5.getPuissanceMax(), 0.0001);
	}
	
	@Test
	public void testCarburantMaxProperty()
	{
		assertTrue(v1.carburantMaxProperty() != null);
		assertTrue(v2.carburantMaxProperty() != null);
		assertTrue(v3.carburantMaxProperty() != null);
		assertTrue(v4.carburantMaxProperty() != null);
		assertTrue(v5.carburantMaxProperty() != null);
	}
	
	@Test
	public void testCarburantRestantProperty()
	{
		assertTrue(v1.carburantRestantProperty() != null);
		assertTrue(v2.carburantRestantProperty() != null);
		assertTrue(v3.carburantRestantProperty() != null);
		assertTrue(v4.carburantRestantProperty() != null);
		assertTrue(v5.carburantRestantProperty() != null);
	}
	
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
	
	@Test
	public void testGetSante()
	{
		assertEquals(1.0, v1.getSante(), 0.0001);
		assertEquals(1.0, v2.getSante(), 0.0001);
		assertEquals(1.0, v3.getSante(), 0.0001);
		assertEquals(1.0, v4.getSante(), 0.0001);
		assertEquals(1.0, v5.getSante(), 0.0001);
	}
	
	@Test
	public void testSetSante()
	{
		v1.setSante(0.5);
		assertEquals(0.5, v1.getSante(), 0.0001);
		
		v1.setSante(100);
		assertEquals(0.5, v1.getSante(), 0.0001);
		
		v1.setSante(-100);
		assertEquals(0.5, v1.getSante(), 0.0001);
	}
	
	@Test
	public void testGetForceExt()
	{
		assertEquals(0.0, v1.getForceExt().getNorme(), 0.0001);
		assertEquals(0.0, v2.getForceExt().getNorme(), 0.0001);
		assertEquals(0.0, v3.getForceExt().getNorme(), 0.0001);
		assertEquals(0.0, v4.getForceExt().getNorme(), 0.0001);
		assertEquals(0.0, v5.getForceExt().getNorme(), 0.0001);
	}
	
	@Test
	public void testGetDirection()
	{
		assertEquals(0.0, v1.getDirection().getNorme(), 0.0001);
		assertEquals(0.0, v2.getDirection().getNorme(), 0.0001);
		assertEquals(0.0, v3.getDirection().getNorme(), 0.0001);
		assertEquals(0.0, v4.getDirection().getNorme(), 0.0001);
		assertEquals(0.0, v5.getDirection().getNorme(), 0.0001);
	}
	
	@Test
	public void testGetNoeud()
	{
		// Nécessite un contexte JavaFX.
		/*
		Node noeud = v1.getNoeud();
		assertTrue(noeud instanceof Group);
		assertTrue(((Group)(noeud)).getChildren().size() != 0);
		assertEquals(noeud, v1.getNoeud());
		*/
	}
	
	@Test
	public void testMaj()
	{
		// Nécessite un contexte JavaFX.
	}
	
	@Test
	public void testGetRayon()
	{
		assertEquals(20.0, v1.getRayon(), 0.0001);
		assertEquals(20.0, v2.getRayon(), 0.0001);
		assertEquals(20.0, v3.getRayon(), 0.0001);
		assertEquals(20.0, v4.getRayon(), 0.0001);
		assertEquals(20.0, v5.getRayon(), 0.0001);
	}
	
	@Test
	public void testReset()
	{
		v1.reset();
		assertEquals(100.0, v1.getCarburantRestant(), 0.0001);
		assertEquals(1.0, v1.getSante(), 0.0001);
		assertEquals(0.0, v1.getPosition().getX(), 0.0001);
		assertEquals(0.0, v1.getPosition().getY(), 0.0001);
		assertTrue(v1.getVitesse().getNorme() == 0.0);
	}
}
