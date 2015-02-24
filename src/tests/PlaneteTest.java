package tests;

import static org.junit.Assert.*;
import objets.Planete;
import org.junit.Test;
import utils.Vecteur;

/**
 * Classe de test pour Planete.
 * @author EquBolduc
 * @version 1.0
 */
public class PlaneteTest
{
	
Planete p1, p2, p3, p4, p5, p6;
	
	@Test
	public void testPlaneteDoubleVecteur()
	{
		p1 = new Planete(0, null);
		assertTrue(p1.getMasse() == Planete.MASSE_DEFAUT);
		assertTrue(p1.getPositionX() == 0);
		assertTrue(p1.getPositionY() == 0);
		assertTrue(p1.isStatique());
		assertTrue(p1.getVitesse().getX() == 0 && p1.getVitesse().getY() == 0);
		p2 = new Planete(1, new Vecteur());
		assertTrue(p2.getMasse() == 1);
		assertTrue(p2.getPositionX() == 0);
		assertTrue(p2.getPositionY() == 0);
		assertTrue(p2.isStatique());
		assertTrue(p2.getVitesse().getX() == 0 && p2.getVitesse().getY() == 0);
		p4 = new Planete(60, new Vecteur(50, 40));
		assertTrue(p4.getMasse() == 60);
		assertTrue(p4.getPositionX() == 50);
		assertTrue(p4.getPositionY() == 40);
		assertTrue(p4.isStatique());
		assertTrue(p4.getVitesse().getX() == 0 && p4.getVitesse().getY() == 0);
	}
	
	@Test
	public void testPlaneteDoubleDoubleDouble()
	{
		p3 = new Planete(60, 50, 40);
		assertTrue(p3.getMasse() == 60);
		assertTrue(p3.getPositionX() == 50);
		assertTrue(p3.getPositionY() == 40);
		assertTrue(p3.isStatique());
		assertTrue(p3.getVitesse().getX() == 0 && p3.getVitesse().getY() == 0);
		p5 = new Planete(-1, 0, 0);
		assertTrue(p5.getMasse() == Planete.MASSE_DEFAUT);
		assertTrue(p5.getPositionX() == 0);
		assertTrue(p5.getPositionY() == 0);
		assertTrue(p5.isStatique());
		assertTrue(p5.getVitesse().getX() == 0 && p5.getVitesse().getY() == 0);
		p6 = new Planete(500000, 300, 300);
		assertTrue(p6.getMasse() == 500000);
		assertTrue(p6.getPositionX() == 300);
		assertTrue(p6.getPositionY() == 300);
		assertTrue(p6.isStatique());
		assertTrue(p6.getVitesse().getX() == 0 && p6.getVitesse().getY() == 0);
	}
	
}
