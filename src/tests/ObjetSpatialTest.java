package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import javafx.scene.paint.Color;
import objets.ObjetSpatial;
import objets.Planete;

import org.junit.Before;
import org.junit.Test;

import utils.Vecteur;

/**
 * Classe de test pour ObjetSpatial.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class ObjetSpatialTest
{
	//Incertitude toléré
	private static final double d = 0.001;
	ObjetSpatial oS1, oS2, oS3, oS4, oS5, oS6, oS7, oS8;
	
	@Before
	public void beforeObjetSpatial()
	{
		oS1 = new Planete(0, null, 0, 0, Color.WHITE);
		oS2 = new Planete(1, new Vecteur(), 0, 0, Color.WHITE);
		oS3 = new Planete(60, 50, 40, 0, 0, Color.WHITE);
		oS4 = new Planete(60, new Vecteur(50, 40), 0, 0, Color.WHITE);
		oS5 = new Planete(-1, 0, 0, 0, 0, Color.WHITE);
		oS6 = new Planete(500000, 300, 300, 0, 0, Color.WHITE);
		// oS7 = new Vaisseau(1, new Vecteur(), 1, 1, new Vecteur(), new
		// Vecteur(
		// 40, 40));
		// oS8 = new Vaisseau(1, new Vecteur(), 1, 1, null, null);
	}
	
	@Test
	public void testGetMasse()
	{
		assertEquals(ObjetSpatial.MASSE_DEFAUT, oS1.getMasse(), d);
		assertTrue(oS1.getMasse() == ObjetSpatial.MASSE_DEFAUT);
		assertTrue(oS2.getMasse() == ObjetSpatial.MASSE_DEFAUT);
		assertTrue(oS3.getMasse() == 60);
		assertTrue(oS4.getMasse() == 60);
		assertTrue(oS5.getMasse() == ObjetSpatial.MASSE_DEFAUT);
		assertTrue(oS6.getMasse() == 500000);
	}
	
	@Test
	public void testSetMasse()
	{
		oS6.setMasse(500001);
		assertTrue(oS6.getMasse() == 500001);
		oS6.setMasse(40);
		assertTrue(oS6.getMasse() == 40);
		oS6.setMasse(-18);
		assertTrue(oS6.getMasse() == ObjetSpatial.MASSE_DEFAUT);
		oS6.setMasse(0);
		assertTrue(oS6.getMasse() == ObjetSpatial.MASSE_DEFAUT);
		
	}
	
	@Test
	public void testGetPositionX()
	{
		assertTrue(oS1.getPositionX() == 0);
		assertTrue(oS2.getPositionX() == 0);
		assertTrue(oS3.getPositionX() == 50);
		assertTrue(oS4.getPositionX() == 50);
		assertTrue(oS5.getPositionX() == 0);
		assertTrue(oS6.getPositionX() == 300);
		
	}
	
	@Test
	public void testGetPositionY()
	{
		assertTrue(oS1.getPositionY() == 0);
		assertTrue(oS2.getPositionY() == 0);
		assertTrue(oS3.getPositionY() == 40);
		assertTrue(oS4.getPositionY() == 40);
		assertTrue(oS5.getPositionY() == 0);
		assertTrue(oS6.getPositionY() == 300);
	}
	
	@Test
	public void testSetPositionX()
	{
		oS1.setPositionX(10);
		assertTrue(oS1.getPositionX() == 10);
		oS2.setPositionX(-10);
		assertTrue(oS2.getPositionX() == -10);
		oS3.setPositionX(0);
		assertTrue(oS3.getPositionX() == 0);
		oS4.setPositionX(40);
		assertTrue(oS4.getPositionX() == 40);
	}
	
	@Test
	public void testSetPositionY()
	{
		oS1.setPositionY(10);
		assertTrue(oS1.getPositionY() == 10);
		oS2.setPositionY(-10);
		assertTrue(oS2.getPositionY() == -10);
		oS3.setPositionY(0);
		assertTrue(oS3.getPositionY() == 0);
		oS4.setPositionY(40);
		assertTrue(oS4.getPositionY() == 40);
	}
	
	@Test
	public void testGetPositionXProperty()
	{
		assertTrue(oS1.getPositionXProperty().get() == 0);
		assertTrue(oS2.getPositionXProperty().get() == 0);
		assertTrue(oS3.getPositionXProperty().get() == 50);
		assertTrue(oS4.getPositionXProperty().get() == 50);
		assertTrue(oS5.getPositionXProperty().get() == 0);
		assertTrue(oS6.getPositionXProperty().get() == 300);
	}
	
	@Test
	public void testGetPositionYProperty()
	{
		assertTrue(oS1.getPositionYProperty().get() == 0);
		assertTrue(oS2.getPositionYProperty().get() == 0);
		assertTrue(oS3.getPositionYProperty().get() == 40);
		assertTrue(oS4.getPositionYProperty().get() == 40);
		assertTrue(oS5.getPositionYProperty().get() == 0);
		assertTrue(oS6.getPositionYProperty().get() == 300);
	}
	
	@Test
	public void testGetPosition()
	{
		assertTrue(oS1.getPosition().getX() == 0
				&& oS1.getPosition().getY() == 0);
		assertTrue(oS2.getPosition().getX() == 0
				&& oS2.getPosition().getY() == 0);
		assertTrue(oS3.getPosition().getX() == 50
				&& oS3.getPosition().getY() == 40);
		assertTrue(oS4.getPosition().getX() == 50
				&& oS4.getPosition().getY() == 40);
		assertTrue(oS5.getPosition().getX() == 0
				&& oS5.getPosition().getY() == 0);
		assertTrue(oS6.getPosition().getX() == 300
				&& oS6.getPosition().getY() == 300);
	}
	
	@Test
	public void testSetPosition()
	{
		oS1.setPosition(new Vecteur(10, 50));
		assertTrue(oS1.getPositionX() == 10);
		assertTrue(oS1.getPositionY() == 50);
		oS2.setPosition(new Vecteur(-10, -7));
		assertTrue(oS2.getPositionX() == -10);
		assertTrue(oS2.getPositionY() == -7);
		oS3.setPosition(new Vecteur(0, 0));
		assertTrue(oS3.getPositionY() == 0);
		assertTrue(oS3.getPositionX() == 0);
		oS4.setPosition(null);
		assertTrue(oS4.getPositionY() == 0);
		assertTrue(oS4.getPositionX() == 0);
		
	}
	
	@Test
	public void testIsStatique()
	{
		assertTrue(oS1.isStatique());

	}
	
	@Test
	public void testSetStatique()
	{
		oS1.setStatique(false);
		assertFalse(oS1.isStatique());

	}
	
	@Test
	public void testGetVitesse()
	{
		oS1.setVitesse(new Vecteur(60, 60));
		assertTrue(oS1.getVitesse().getX() == 0 && oS1.getVitesse().getY() == 0);

	}
	
	@Test
	public void testSetVitesse()
	{
		oS1.setVitesse(new Vecteur(60, 60));
		oS1.setStatique(false);
		assertTrue(oS1.getVitesse().getX() == 60
				&& oS1.getVitesse().getY() == 60);
		oS2.setVitesse(null);
		assertEquals(new Vecteur().getNorme(),oS2.getVitesse().getNorme() , d);

	}
	
	@Test
	public void testSetPositionXdoubleboolean()
	{
		oS1.setPositionX(-42.6, true);
		assertEquals(-42.6, oS1.getPositionX(), d);
		oS1.setPositionX(42.6, false);
		assertEquals(42.6, oS1.getPositionX(), d);
		oS1.reset();
		assertEquals(-42.6, oS1.getPositionX(), d);
		oS1.setPositionX(0, true);
		assertEquals(0, oS1.getPositionX(), d);
		oS1.setPositionX(500, false);
		assertEquals(500, oS1.getPositionX(), d);
		oS1.reset();
		assertEquals(0, oS1.getPositionX(), d);
	}
	
	@Test
	public void testSetPositionYdoubleboolean()
	{
		oS1.setPositionY(-42.6, true);
		assertEquals(-42.6, oS1.getPositionY(), d);
		oS1.setPositionY(28);
		assertEquals(28, oS1.getPositionY(), d);
		oS1.reset();
		assertEquals(-42.6, oS1.getPositionY(), d);
		oS1.setPositionY(0, true);
		assertEquals(0, oS1.getPositionY(), d);
		oS1.setPositionY(500, false);
		assertEquals(500, oS1.getPositionY(), d);
		oS1.reset();
		assertEquals(0, oS1.getPositionY(), d);
	}
	
	@Test
	public void testSetPositionVecteurboolean()
	{
		oS1.setPosition(new Vecteur(-42.6, -42.6) , true);
		assertEquals(-42.6, oS1.getPositionY(), d);
		assertEquals(-42.6, oS1.getPositionX(), d);
		oS1.setPosition(new Vecteur(42.6, 42.6) , false);
		assertEquals(42.6, oS1.getPositionY(), d);
		assertEquals(42.6, oS1.getPositionX(), d);
		oS1.reset();
		assertEquals(-42.6, oS1.getPositionY(), d);
		assertEquals(-42.6, oS1.getPositionX(), d);
		oS1.setPosition(new Vecteur(100, 100), true);
		assertEquals(100, oS1.getPositionY(), d);
		assertEquals(100, oS1.getPositionX(), d);
		oS1.setPosition(new Vecteur(500, 500) , false);
		assertEquals(500, oS1.getPositionY(), d);
		assertEquals(500, oS1.getPositionX(), d);
		oS1.reset();
		assertEquals(100, oS1.getPositionY(), d);
		assertEquals(100, oS1.getPositionX(), d);
		oS1.setPosition(null, true);
		oS1.reset();
		assertEquals(0, oS1.getPositionY(), d);
		assertEquals(0, oS1.getPositionX(), d);
		
	}
	
	@Test
	public void testReset()
	{
		
	}
	
	
}
