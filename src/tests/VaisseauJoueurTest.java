package tests;

import static org.junit.Assert.*;
import objets.Vaisseau;
import objets.VaisseauJoueur;
import org.junit.Before;
import org.junit.Test;
import utils.Vecteur;

/**
 * Classe de test de VaisseauJoueur.
 * 
 * @author EquBolduc
 */
public class VaisseauJoueurTest
{
	VaisseauJoueur v1, v2, v3, v4;
	
	@Before
	public void testVaisseauJoueur()
	{
		v1 = new VaisseauJoueur(0, 0, 100, 100);
		assertTrue(v1.getPuissance() == VaisseauJoueur.PUISSANCE_DEFAUT);
		assertTrue(v1.getCarburantMax() == 100);
		assertTrue(v1.getCarburantDepart() == 100);
		assertTrue(v1.getMasse() == Vaisseau.MASSE_DEFAUT);
		assertTrue(v1.getPositionX() == 0 && v1.getPositionY() == 0);
		assertTrue(v1.getVitesse().getX() == 1 && v1.getVitesse().getY() == 1);
		v2 = new VaisseauJoueur(-1, -1, -1, -1);
		assertTrue(v2.getPuissance() == VaisseauJoueur.PUISSANCE_DEFAUT);
		assertTrue(v2.getCarburantMax() == VaisseauJoueur.CARBURANT_DEFAUT);
		assertTrue(v2.getCarburantDepart() == 0);
		assertTrue(v2.getMasse() == Vaisseau.MASSE_DEFAUT);
		assertTrue(v2.getPositionX() == 0 && v2.getPositionY() == 0);
		v3 = new VaisseauJoueur(-1, -1, 100, 100);
		assertTrue(v3.getPuissance() == VaisseauJoueur.PUISSANCE_DEFAUT);
		assertTrue(v3.getCarburantMax() == 100);
		assertTrue(v3.getCarburantDepart() == 100);
		assertTrue(v3.getMasse() == Vaisseau.MASSE_DEFAUT);
		assertTrue(v3.getPositionX() == 0 && v3.getPositionY() == 0);
		v4 = new VaisseauJoueur(0, 0, 0, 0);
		assertTrue(v4.getPuissance() == VaisseauJoueur.PUISSANCE_DEFAUT);
		assertTrue(v4.getCarburantMax() == VaisseauJoueur.CARBURANT_DEFAUT);
		assertTrue(v4.getCarburantDepart() == 0);
		assertTrue(v4.getMasse() == Vaisseau.MASSE_DEFAUT);
		assertTrue(v4.getPositionX() == 0 && v4.getPositionY() == 0);
		assertTrue(v4.getVitesse().getX() == 1 && v4.getVitesse().getY() == 1);
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
	public void testGetDirection()
	{
		assertEquals(1.0, v1.getDirection().getNorme(), 0.0001);
		assertEquals(1.0, v2.getDirection().getNorme(), 0.0001);
		assertEquals(1.0, v3.getDirection().getNorme(), 0.0001);
		assertEquals(1.0, v4.getDirection().getNorme(), 0.0001);
	}
	
	@Test
	public void testSetDirection()
	{
		v1.setDirection(new Vecteur(1, 0));
		assertEquals(0.0, v1.getDirection().getAngle(), 0.0001);
		v2.setDirection(null);
		assertEquals(Math.PI / 4, v2.getDirection().getAngle(), 0.0001);
		v3.setDirection(new Vecteur(0, 1));
		assertEquals(Math.PI / 2, v3.getDirection().getAngle(), 0.0001);
		v4.setDirection(new Vecteur(0, 0));
		assertEquals(0.0, v4.getDirection().getAngle(), 0.0001);
	}
	
	@Test
	public void testGetCarburantMax()
	{
		assertEquals(100.0, v1.getCarburantMax(), 0.0001);
		assertEquals(20.0, v2.getCarburantMax(), 0.0001);
		assertEquals(100.0, v3.getCarburantMax(), 0.0001);
		assertEquals(20.0, v4.getCarburantMax(), 0.0001);
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
	}
	
	@Test
	public void testCarburantMaxProperty()
	{
		assertTrue(v1.carburantMaxProperty() != null);
		assertTrue(v2.carburantMaxProperty() != null);
		assertTrue(v3.carburantMaxProperty() != null);
		assertTrue(v4.carburantMaxProperty() != null);
	}
	
	@Test
	public void testCarburantRestantProperty()
	{
		assertTrue(v1.carburantRestantProperty() != null);
		assertTrue(v2.carburantRestantProperty() != null);
		assertTrue(v3.carburantRestantProperty() != null);
		assertTrue(v4.carburantRestantProperty() != null);
	}
	
	@Test
	public void testGetPuissance()
	{
		assertTrue(v1.getPuissance() == v1.getPuissance());
		assertTrue(v2.getPuissance() == v2.getPuissance());
		assertTrue(v3.getPuissance() == v3.getPuissance());
		assertTrue(v4.getPuissance() == v4.getPuissance());
	}
	
	@Test
	public void testSetPuissance()
	{
		v1.setPuissance(50);
		assertTrue(v1.getPuissance() == 50);
		v2.setPuissance(-1000);
		assertTrue(v2.getPuissance() == VaisseauJoueur.PUISSANCE_DEFAUT);
		v3.setPuissance(0);
		assertTrue(v3.getPuissance() == VaisseauJoueur.PUISSANCE_DEFAUT);
	}
	
	@Test
	public void testGetSante()
	{
		assertEquals(1.0, v1.getSante(), 0.0001);
		assertEquals(1.0, v2.getSante(), 0.0001);
		assertEquals(1.0, v3.getSante(), 0.0001);
		assertEquals(1.0, v4.getSante(), 0.0001);
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
		assertEquals(0, v2.getForceExt().getX(), 0.0001);
		assertEquals(0, v2.getForceExt().getY(), 0.0001);
		v2.avancer(true);
		v2.setCarburantMax(9e9);
		v2.setCarburantRestant(9e9);
		assertEquals(200000, v2.getForceExt().getNorme(), 0.0001);
		v2.avancer(false);
		assertEquals(0, v2.getForceExt().getX(), 0.0001);
		assertEquals(0, v2.getForceExt().getY(), 0.0001);
		v2.setCarburantRestant(0);
		v2.avancer(true);
		assertEquals(0, v2.getForceExt().getNorme(), 0.0001);
	}
	
	@Test
	public void testUpdate()
	{
		v1.tournerDroite(true);
		v1.tournerGauche(true);
		v1.avancer(true);
		v1.setCarburantMax(100);
		v1.setCarburantRestant(100);
		v1.miseAJourPhysique(1.0);
		assertEquals(99.0, v1.getCarburantRestant(), 0.0001);
		v1.avancer(false);
		v1.miseAJourPhysique(1.0);
		assertEquals(99.0, v1.getCarburantRestant(), 0.0001);
	}
	
	@Test
	public void testOnCollision()
	{
		v1.onCollision(v2);
		assertEquals(0.5, v1.getSante(), 0.0001);
		v1.setSante(1.0);
		v4.setMasse(v1.getMasse() * 2 + 1);
		v1.onCollision(v4);
		assertEquals(0.0, v1.getSante(), 0.0001);
	}
	
	@Test
	public void testSetAngle()
	{
		v1.setAngle(0.0);
		assertEquals(0.0, v1.getDirection().getAngle(), 0.0001);
	}
	
	@Test
	public void testReset()
	{
		v1.reset();
		assertEquals(100.0, v1.getCarburantRestant(), 0.0001);
		assertEquals(1.0, v1.getSante(), 0.0001);
		assertEquals(0.0, v1.getPosition().getX(), 0.0001);
		assertEquals(0.0, v1.getPosition().getY(), 0.0001);
		assertTrue(v1.getVitesse().getNorme() == Math.sqrt(2));
	}
}
