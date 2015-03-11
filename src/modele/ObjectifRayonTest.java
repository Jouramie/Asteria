package modele;

import static org.junit.Assert.*;
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
	private Vaisseau v;
	private ObjectifRayon obj;
	
	@Before
	public void testObjectifRayon()
	{
		v = new Vaisseau(100, 6e10, 1000, 0, 0, new Vecteur(0, 0));
		obj = new ObjectifRayon(v, new Vecteur(100, 100), 10);
	}

	@Test
	public void testGetDescription()
	{
		assertNotEquals(0, obj.getDescription().length());
	}

	@Test
	public void testVerifierObjectif()
	{
		assertEquals(false, obj.verifierObjectif());
		
		v.setPosition(new Vecteur(100, 100));
		assertEquals(true, obj.verifierObjectif());
		
		v.setPosition(new Vecteur(95, 95));
		assertEquals(true, obj.verifierObjectif());
	}
}
