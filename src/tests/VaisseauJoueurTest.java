package tests;

import static org.junit.Assert.*;
import objets.Vaisseau;
import objets.VaisseauJoueur;

import org.junit.Before;
import org.junit.Test;

import utils.Vecteur;

/**
 * Classe de test de VaisseauJoueur.
 * @author EquBolduc
 */
public class VaisseauJoueurTest
{
	VaisseauJoueur v1, v2, v4;
	
	@Before
	public void testVaisseauJoueur()
	{
		v1 = new VaisseauJoueur(0, null, 0, 0, null, null);
		assertTrue(v1.getPuissance() == Vaisseau.PUISSANCE_DEFAUT);
		assertTrue(v1.getMasse() == Vaisseau.MASSE_DEFAUT);
		assertTrue(v1.getPositionX() == 0 && v1.getPositionY() == 0);
		assertTrue(v1.getVitesse().getX() == 0 && v1.getVitesse().getY() == 0);
		v2 = new VaisseauJoueur(-10, new Vecteur(), -1, -1, new Vecteur(),
				new Vecteur(10, 0));
		assertTrue(v2.getPuissance() == Vaisseau.PUISSANCE_DEFAUT);
		assertTrue(v2.getMasse() == Vaisseau.MASSE_DEFAUT);
		assertTrue(v2.getPositionX() == 0 && v2.getPositionY() == 0);
		assertEquals(10, v2.getVitesse().getX(), 0.0001);
		assertEquals(0, v2.getVitesse().getY(), 0.0001);
		v4 = new VaisseauJoueur(0, null, 0, 0, 0, 0, null);
		assertTrue(v4.getPuissance() == Vaisseau.PUISSANCE_DEFAUT);
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
		assertEquals(200000, v2.getForceExt().getX(), 0.0001);
		assertEquals(0, v2.getForceExt().getY(), 0.0001);
		v2.avancer();
		assertEquals(0, v2.getForceExt().getX(), 0.0001);
		assertEquals(0, v2.getForceExt().getY(), 0.0001);
	}
	
	@Test
	public void testMaj()
	{
		// Méthode nécessite un contexte JavaFx.
	}
	
	@Test
	public void testUpdate()
	{
		v1.tournerDroite();
		v1.tournerGauche();
		v1.avancer();
		v1.update(1.0);
		assertEquals(99.0, v1.getCarburantRestant(), 0.0001);
	}
	
	@Test
	public void testOnCollision()
	{
		v1.onCollision(v2);
		assertEquals(0.0, v1.getSante(), 0.0001);
	}
	
	@Test
	public void testSetAngle()
	{
		v1.setAngle(0.0);
		assertEquals(0.0, v1.getDirection().getAngle(), 0.0001);
	}
}
