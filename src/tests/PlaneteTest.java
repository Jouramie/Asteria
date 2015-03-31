package tests;

import static org.junit.Assert.*;
import objets.Planete;
import objets.Planete.Texture;

import org.junit.Before;
import org.junit.Test;

import utils.Vecteur;

/**
 * Classe de test pour Planete.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class PlaneteTest
{
	double d = 0.001;
	
	Planete p1, p2, p3, p4, p5, p6;
	
	@Before
	public void beforePlaneteDoubleVecteur()
	{
		p1 = new Planete(0, null, 0);
		assertEquals(Planete.MASSE_DEFAUT, p1.getMasse(), d);
		assertEquals(0, p1.getPositionX(), d);
		assertEquals(0, p1.getPositionY(), d);
		p2 = new Planete(1, new Vecteur(), -1);
		assertEquals(1, p2.getMasse(), d);
		assertEquals(0, p2.getPositionX(), d);
		assertEquals(0, p2.getPositionY(), d);
		p4 = new Planete(60, new Vecteur(50, 40), 100);
		assertEquals(60, p4.getMasse(), d);
		assertEquals(50, p4.getPositionX(), d);
		assertEquals(40, p4.getPositionY(), d);
	}
	
	@Before
	public void beforePlaneteDoubleDoubleDouble()
	{
		p3 = new Planete(60, 50, 40, 30);
		assertEquals(60, p3.getMasse(), d);
		assertEquals(50, p3.getPositionX(), d);
		assertEquals(40, p3.getPositionY(), d);
		p5 = new Planete(-1, 0, 0, 0);
		assertEquals(Planete.MASSE_DEFAUT, p5.getMasse(), d);
		assertEquals(0, p5.getPositionX(), d);
		assertEquals(0, p5.getPositionY(), d);
		p6 = new Planete(500000, 300, 300, 100);
		assertEquals(500000, p6.getMasse(), d);
		assertEquals(300, p6.getPositionX(), d);
		assertEquals(300, p6.getPositionY(), d);
	}
	
	@Test
	public void testSetRayon()
	{
		p1.setRayon(10);
		assertEquals(10, p1.getRayon(), d);
		p2.setRayon(100);
		assertEquals(100, p2.getRayon(), d);
		p3.setRayon(20);
		assertEquals(20, p3.getRayon(), d);
		p4.setRayon(-100);
		assertEquals(Planete.RAYON_DEFAUT, p4.getRayon(), d);
		p5.setRayon(-40);
		assertEquals(Planete.RAYON_DEFAUT, p5.getRayon(), d);
		p6.setRayon(0);
		assertEquals(0, p6.getRayon(), d);
	}
	
	@Test
	public void testGetRayon()
	{
		assertEquals(0, p1.getRayon(), d);
		assertEquals(Planete.RAYON_DEFAUT, p2.getRayon(), d);
		assertEquals(100, p4.getRayon(), d);
		assertEquals(30, p3.getRayon(), d);
		assertEquals(0, p5.getRayon(), d);
		assertEquals(100, p6.getRayon(), d);
	}
	
	@Test
	public void testSetTexture()
	{
		p1.setTexture(Texture.ROUGE);
		assertEquals(p1.getTexture().name(), Texture.ROUGE.name());
		p2.setTexture(null);
		assertEquals(p2.getTexture().name(), Planete.TEXTURE_DEFAUT.name());
	}
	
	@Test
	public void testGetTexture()
	{
		p3.setTexture(Texture.ROUGE);
		assertEquals(p3.getTexture().name(), Texture.ROUGE.name());
		p4.setTexture(null);
		assertEquals(p4.getTexture().name(), Planete.TEXTURE_DEFAUT.name());
	}
	
}
