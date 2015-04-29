package tests;

import static org.junit.Assert.*;
import objets.Vaisseau;
import objets.VaisseauJoueur;
import org.junit.Before;
import org.junit.Test;
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
		v1 = new VaisseauJoueur(0, 0, 0, 0);
		assertTrue(v1.getPuissance() == Vaisseau.PUISSANCE_DEFAUT);
		assertTrue(v1.getMasse() == Vaisseau.MASSE_DEFAUT);
		assertTrue(v1.getPositionX() == 0 && v1.getPositionY() == 0);
		assertTrue(v1.getVitesse().getX() == 1 && v1.getVitesse().getY() == 1);
		v2 = new VaisseauJoueur(-1, -1, -1, -1);
		assertTrue(v2.getPuissance() == Vaisseau.PUISSANCE_DEFAUT);
		assertTrue(v2.getMasse() == Vaisseau.MASSE_DEFAUT);
		assertTrue(v2.getPositionX() == 0 && v2.getPositionY() == 0);
		v4 = new VaisseauJoueur(0, 0, 0, 0);
		assertTrue(v4.getPuissance() == Vaisseau.PUISSANCE_DEFAUT);
		assertTrue(v4.getMasse() == Vaisseau.MASSE_DEFAUT);
		assertTrue(v4.getPositionX() == 0 && v4.getPositionY() == 0);
		assertTrue(v4.getVitesse().getX() == 1 && v4.getVitesse().getY() == 1);
	}
	
	@Test
	public void testGetForceExt()
	{
		assertEquals(0, v2.getForceExt().getX(), 0.0001);
		assertEquals(0, v2.getForceExt().getY(), 0.0001);
		v2.setAngle(0);
		v2.avancer(true);
		v2.setCarburantMax(9e9);
		v2.setCarburantRestant(9e9);
		assertEquals(200000, v2.getForceExt().getNorme(), 0.0001);
		v2.avancer(false);
		assertEquals(0, v2.getForceExt().getX(), 0.0001);
		assertEquals(0, v2.getForceExt().getY(), 0.0001);
	}
	
	@Test
	public void testMaj()
	{
		assertTrue(true);
	}
	
	@Test
	public void testUpdate()
	{
		v1.tournerDroite(true);
		v1.tournerGauche(true);
		v1.avancer(true);
		v1.setCarburantMax(100);
		v1.setCarburantRestant(100);
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
