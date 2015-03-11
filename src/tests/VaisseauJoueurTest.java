package tests;

import static org.junit.Assert.*;
import objets.Vaisseau;
import objets.VaisseauJoueur;

import org.junit.Before;
import org.junit.Test;

import utils.Vecteur;

public class VaisseauJoueurTest
{
	VaisseauJoueur v1, v2, v4;
	
	@Before
	public void testVaisseauJoueur()
	{
		v1 = new VaisseauJoueur(0, null, 0, 0, null, null);
		assertTrue(v1.getPuissanceMax() == Vaisseau.PUISSANCE_MAX_DEFAUT);
		assertTrue(v1.getMasse() == Vaisseau.MASSE_DEFAUT);
		assertTrue(v1.getPositionX() == 0 && v1.getPositionY() == 0);
		assertTrue(v1.getVitesse().getX() == 0 && v1.getVitesse().getY() == 0);
		v2 = new VaisseauJoueur(-10, new Vecteur(), -1, -1, new Vecteur(),
				new Vecteur(10, 0));
		assertTrue(v2.getPuissanceMax() == Vaisseau.PUISSANCE_MAX_DEFAUT);
		assertTrue(v2.getMasse() == Vaisseau.MASSE_DEFAUT);
		assertTrue(v2.getPositionX() == 0 && v2.getPositionY() == 0);
		assertEquals(10, v2.getVitesse().getX(), 0.0001);
		assertEquals(0, v2.getVitesse().getY(), 0.0001);
		v4 = new VaisseauJoueur(0, null, 0, 0, 0, 0, null);
		assertTrue(v4.getPuissanceMax() == Vaisseau.PUISSANCE_MAX_DEFAUT);
		assertTrue(v4.getMasse() == Vaisseau.MASSE_DEFAUT);
		assertTrue(v4.getPositionX() == 0 && v4.getPositionY() == 0);
		assertTrue(v4.getVitesse().getX() == 0 && v4.getVitesse().getY() == 0);
	}
	
	@Test
	public void testGetForceExt()
	{
		assertEquals(0, v2.getForceExt().getX(), 0.0001);
		assertEquals(0, v2.getForceExt().getY(), 0.0001);
		v2.avancer();
		assertEquals(1, v2.getForceExt().getX(), 0.0001);
		assertEquals(0, v2.getForceExt().getY(), 0.0001);
		v2.avancer();
		assertEquals(0, v2.getForceExt().getX(), 0.0001);
		assertEquals(0, v2.getForceExt().getY(), 0.0001);
	}
	
	/*
	 * @Test public void testMaj() { assertEquals(135,
	 * v2.getNoeud().getRotate(), 0.0001); v2.maj(); assertEquals(135,
	 * v2.getNoeud().getRotate(), 0.0001); v2.tournerGauche(); v2.maj();
	 * assertEquals(494, v2.getNoeud().getRotate(), 0.0001); v2.tournerDroite();
	 * v2.maj(); assertEquals(494, v2.getNoeud().getRotate(), 0.0001);
	 * v2.tournerGauche(); v2.maj(); assertEquals(495,
	 * v2.getNoeud().getRotate(), 0.0001); v2.tournerDroite(); v2.maj();
	 * assertEquals(495, v2.getNoeud().getRotate(), 0.0001); }
	 */
}
