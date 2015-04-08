package tests;

import static org.junit.Assert.*;
import modele.ObjectifRayon;
import objets.Vaisseau;

import org.junit.Before;
import org.junit.Test;

import utils.Vecteur;

/**
 * Classe de test pour ObjectifRayon.
 * @author EquBolduc
 * @version 1.0
 */
public class ObjectifRayonTest
{
	private Vaisseau vaisseau;
	private ObjectifRayon obj;
	private Vecteur posRayon;
	private double rayon;
	
	@Before
	public void testObjectifRayon()
	{
		posRayon = new Vecteur(100, 100);
		rayon = 10;
		vaisseau = new Vaisseau(100, 6e10, 1000, 0, 0, new Vecteur(0, 0));
		obj = new ObjectifRayon(posRayon, rayon);
		obj.setVaisseau(vaisseau);
	}

	@Test
	public void testGetDescription()
	{
		assertNotEquals(0, obj.getDescription().length());
	}
	
	@Test
	public void testGetPosRayon()
	{
		assertEquals(posRayon, obj.getPosRayon());
	}
	
	@Test
	public void testGetRayon()
	{
		assertEquals(rayon == obj.getRayon(), true);
	}
	
	@Test
	public void testGetVaisseau()
	{
		assertEquals(vaisseau == obj.getVaisseau(), true);
	}
	
	@Test
	public void testSetPosRayon()
	{
		assertEquals(posRayon, obj.getPosRayon());
		obj.setPosRayon(new Vecteur(10, 10));
		assertNotEquals(posRayon, obj.getPosRayon());
		obj.setPosRayon(null);
		assertNotEquals(null, obj.getPosRayon());
	}
	
	@Test
	public void testSetRayon()
	{
		assertEquals(rayon == obj.getRayon(), true);
		obj.setRayon(100);
		assertNotEquals(rayon == obj.getRayon(), true);
		obj.setRayon(-1);
		assertNotEquals(-1 == obj.getRayon(), true);
	}
	
	@Test
	public void testSetVaisseau()
	{
		assertEquals(vaisseau == obj.getVaisseau(), true);
		obj.setVaisseau(new Vaisseau(10, 6e12, 100, 10, 10, new Vecteur(10, 10)));
		assertNotEquals(vaisseau == obj.getVaisseau(), true);
		obj.setVaisseau(null);
		assertNotEquals(null == obj.getVaisseau(), true);
	}

	@Test
	public void testVerifierObjectif()
	{
		assertEquals(false, obj.verifierObjectif());
		
		vaisseau.setPosition(new Vecteur(100, 100));
		assertEquals(true, obj.verifierObjectif());
		
		vaisseau.setPosition(new Vecteur(95, 95));
		assertEquals(true, obj.verifierObjectif());
	}
}
