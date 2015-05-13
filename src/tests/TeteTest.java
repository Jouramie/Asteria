package tests;

import static org.junit.Assert.*;
import objets.Planete;
import objets.Tete;
import org.junit.Before;
import org.junit.Test;
import utils.Vecteur;

/**
 * Classe de tests pour Tete.
 * 
 * @author Équipe Bolduc
 */
public class TeteTest
{
	double d = 0.001;
	
	Tete t1, t2, t3, t4;
	
	@Before
	public void testTete()
	{
		t1 = new Tete(1, 0, new Vecteur(), new Vecteur());
		assertEquals(Planete.MASSE_DEFAUT, t1.getMasse(), d);
		assertEquals(0, t1.getPositionX(), d);
		assertEquals(0, t1.getPositionY(), d);
		t2 = new Tete(1, 100, new Vecteur(), new Vecteur());
		assertEquals(1, t2.getMasse(), d);
		assertEquals(0, t2.getPositionX(), d);
		assertEquals(0, t2.getPositionY(), d);
		t3 = new Tete(10, 30, new Vecteur(), new Vecteur());
		assertEquals(10, t3.getMasse(), d);
		assertEquals(0, t3.getPositionX(), d);
		assertEquals(0, t3.getPositionY(), d);
		t4 = new Tete(60, 100, new Vecteur(50, 40), new Vecteur());
		assertEquals(60, t4.getMasse(), d);
		assertEquals(50, t4.getPositionX(), d);
		assertEquals(40, t4.getPositionY(), d);
	}
	
	@Test
	public void testSetRayon()
	{
		t1.setRayon(10);
		assertEquals(10, t1.getRayon(), d);
		t2.setRayon(100);
		assertEquals(100, t2.getRayon(), d);
		t3.setRayon(20);
		assertEquals(20, t3.getRayon(), d);
		t4.setRayon(-100);
		assertEquals(Planete.RAYON_DEFAUT, t4.getRayon(), d);
	}
	
	@Test
	public void testGetRayon()
	{
		assertEquals(0, t1.getRayon(), d);
		assertEquals(Tete.RAYON_DEFAUT, t2.getRayon(), d);
		assertEquals(100, t4.getRayon(), d);
		assertEquals(30, t3.getRayon(), d);
	}
	
	@Test
	public void testSetTexture()
	{
		t1.setTexture(Tete.Texture.EMILE);
		assertEquals(t1.getTexture().name(), Tete.Texture.EMILE.name());
		t2.setTexture(null);
		assertEquals(t2.getTexture().name(), Tete.TEXTURE_DEFAUT.name());
	}
	
	@Test
	public void testGetTexture()
	{
		t3.setTexture(Tete.Texture.JEREMIE);
		assertEquals(t3.getTexture().name(), Tete.Texture.JEREMIE.name());
		t4.setTexture(null);
		assertEquals(t4.getTexture().name(), Tete.TEXTURE_DEFAUT.name());
	}
	
	@Test
	public void testGetForceExt()
	{
		assertEquals(0, t1.getForceExt().getNorme(), d);
	}
	
	@Test
	public void testTexture()
	{
		// public static Texture getTexture(String tex)
		assertEquals(Tete.Texture.JONATHAN, Tete.Texture.getTexture("jonathan"));
		assertEquals(Tete.Texture.EMILE, Tete.Texture.getTexture("EMILE"));
		assertEquals(Tete.Texture.SIMONPIERRE,
				Tete.Texture.getTexture("simonpierre"));
		assertEquals(Tete.Texture.JEREMIE, Tete.Texture.getTexture("jeremie"));
		assertEquals(Tete.TEXTURE_DEFAUT,
				Tete.Texture.getTexture("oiasdfkhaskjfh"));
		
		// public String getTexture()
		assertEquals("/res/emile.png", Tete.Texture.EMILE.getTexture());
		
	}
}
