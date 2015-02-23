package tests;

import static org.junit.Assert.*;
import javafx.scene.shape.Circle;

import javax.swing.plaf.PanelUI;

import objets.Vaisseau;

import org.junit.Before;
import org.junit.Test;

import utils.Vecteur;

public class VaisseauTest
{
	Vaisseau v1, v2, v3, v4, v5, v6;
	
	@Before
	public void testVaisseauDoubleVecteurDoubleDoubleVecteurVecteur()
	{
		v1 = new Vaisseau(0, null, 0, 0, null, null);
		assertTrue(v1.getPuissanceMax() == Vaisseau.PUISSANCE_MAX_DEFAUT);
		assertTrue(v1.getMasse() == Vaisseau.MASSE_DEFAUT);
		assertTrue(v1.getPositionX() == 0 && v1.getPositionY() == 0);
		assertTrue(v1.getVitesse().getX() == 0 && v1.getVitesse().getY() == 0);
		v2 = new Vaisseau(-10, new Vecteur(), -1, -1, new Vecteur(), new Vecteur());
		assertTrue(v2.getPuissanceMax() == Vaisseau.PUISSANCE_MAX_DEFAUT);
		assertTrue(v2.getMasse() == Vaisseau.MASSE_DEFAUT);
		assertTrue(v2.getPositionX() == 0 && v2.getPositionY() == 0);
		assertTrue(v2.getVitesse().getX() == 0 && v2.getVitesse().getY() == 0);
		v3 = new Vaisseau(100, new Vecteur(1, 0), 100, 100, new Vecteur(150, 125), new Vecteur(10, 10));
		assertTrue(v3.getPuissanceMax() == 100);
		assertTrue(v3.getMasse() == 100);
		assertTrue(v3.getPositionX() == 150 && v3.getPositionY() == 125);
		assertTrue(v3.getVitesse().getX() == 10 && v3.getVitesse().getY() == 10);
	}

	@Before
	public void testVaisseauDoubleVecteurDoubleDoubleDoubleDoubleVecteur()
	{
		v4 = new Vaisseau(0, null, 0, 0, 0, 0, null);
		assertTrue(v4.getPuissanceMax() == Vaisseau.PUISSANCE_MAX_DEFAUT);
		assertTrue(v4.getMasse() == Vaisseau.MASSE_DEFAUT);
		assertTrue(v4.getPositionX() == 0 && v4.getPositionY() == 0);
		assertTrue(v4.getVitesse().getX() == 0 && v4.getVitesse().getY() == 0);
		v5 = new Vaisseau(-10, new Vecteur(), -1, -1, -1, -1, new Vecteur());
		assertTrue(v5.getPuissanceMax() == Vaisseau.PUISSANCE_MAX_DEFAUT);
		assertTrue(v5.getMasse() == Vaisseau.MASSE_DEFAUT);
		assertTrue(v5.getPositionX() == -1 && v5.getPositionY() == -1 );
		assertTrue(v5.getVitesse().getX() == 0 && v5.getVitesse().getY() == 0);
		v6 = new Vaisseau(100, new Vecteur(1, 0), 100, 100, 150, 125, new Vecteur(10, 10));
		assertTrue(v6.getPuissanceMax() == 100);
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
	public void testGetNoeud()
	{
		assertTrue(v1.getNoeud() != null);
		assertTrue(v2.getNoeud() != null);
		assertTrue(v3.getNoeud() != null);
		assertTrue(v4.getNoeud() != null);
		assertTrue(v5.getNoeud() != null);
		assertTrue(v6.getNoeud() != null);
	}
	
	@Test
	public void testGetPuissanceMax()
	{
		assertTrue(v1.getPuissanceMax() == Vaisseau.PUISSANCE_MAX_DEFAUT);
		assertTrue(v2.getPuissanceMax() == Vaisseau.PUISSANCE_MAX_DEFAUT);
		assertTrue(v3.getPuissanceMax() == 100);
		assertTrue(v4.getPuissanceMax() == Vaisseau.PUISSANCE_MAX_DEFAUT);
		assertTrue(v5.getPuissanceMax() == Vaisseau.PUISSANCE_MAX_DEFAUT);
		assertTrue(v6.getPuissanceMax() == 100);
	}
	
	@Test
	public void testSetPuissanceMax()
	{
		v1.setPuissanceMax(50);
		assertTrue(v1.getPuissanceMax() == 50);
		v2.setPuissanceMax(-1000);
		assertTrue(v2.getPuissanceMax() == Vaisseau.PUISSANCE_MAX_DEFAUT);
		v3.setPuissanceMax(0);
		assertTrue(v3.getPuissanceMax() == Vaisseau.PUISSANCE_MAX_DEFAUT);
	}
	
	@Test
	public void testGetPuissance()
	{
		assertTrue(v1.getPuissance() == v1.getPuissanceMax());
		assertTrue(v2.getPuissance() == v2.getPuissanceMax());
		assertTrue(v3.getPuissance() == v3.getPuissanceMax());
		assertTrue(v4.getPuissance() == v4.getPuissanceMax());
		assertTrue(v5.getPuissance() == v5.getPuissanceMax());
		assertTrue(v6.getPuissance() == v6.getPuissanceMax());
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
