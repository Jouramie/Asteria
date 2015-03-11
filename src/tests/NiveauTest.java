package tests;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import modele.Corps;
import modele.Niveau;
import modele.Objectif;
import modele.ObjectifRayon;
import objets.Planete;
import objets.Vaisseau;

import org.junit.Before;
import org.junit.Test;

import utils.Vecteur;
/**
 * Classe de tests pour Niveau.
 * 
 * @author Équipe Bolduc
 */
public class NiveauTest
{
	private List<Corps> corps;
	
	private Niveau niveau;
	
	private Objectif objectif;
	
	private Vecteur pointDepart;
	
	private String titreNiveau;
	
	private Vecteur vitesseDepart;
	
	@Before
	public void testNiveau()
	{
		corps = new ArrayList<>();
		corps.add(new Planete(1000, 100, 100, 15));
		
		objectif = new ObjectifRayon(new Vaisseau(0, 100, 0, new Vecteur(50, 50), new Vecteur(10, 0)), new Vecteur(1000, 1000), 20);
		pointDepart = new Vecteur(10, 10);
		titreNiveau = "Niveau 1";
		vitesseDepart = new Vecteur(10, 0);
		
		niveau = new Niveau(corps, objectif, pointDepart, titreNiveau, vitesseDepart);
	}

	@Test
	public void testAjouterCorps()
	{
		assertEquals(niveau.getCorps().size(), 1);
		niveau.ajouterCorps(new Planete(0, 0, 0, 0));
		assertEquals(niveau.getCorps().size(), 2);
		niveau.ajouterCorps(null);
		assertEquals(niveau.getCorps().size(), 2);
	}

	@Test
	public void testGetCorps()
	{
		assertEquals(niveau.getCorps(), corps);
	}

	@Test
	public void testGetObjectif()
	{
		assertEquals(niveau.getObjectif(), objectif);
	}

	@Test
	public void testGetPointDepart()
	{
		assertEquals(niveau.getPointDepart(), pointDepart);
	}
	
	@Test
	public void testGetTitreNiveau()
	{
		assertEquals(niveau.getTitreNiveau(), titreNiveau);
	}
	
	@Test
	public void testGetVitesseDepart()
	{
		assertEquals(niveau.getVitesseDepart(), vitesseDepart);
	}
	
	@Test
	public void testSetObjectif()
	{
		assertEquals(niveau.getObjectif(), objectif);
		niveau.setObjectif(new ObjectifRayon(new Vaisseau(0, 100, 0, new Vecteur(50, 50), new Vecteur(10, 0)), new Vecteur(1500, 1500), 20));
		assertNotEquals(niveau.getObjectif(), objectif);
		niveau.setObjectif(null);
		assertNotEquals(niveau.getObjectif(), null);
	}

	@Test
	public void testSetPointDepart()
	{
		assertEquals(niveau.getPointDepart(), pointDepart);
		niveau.setPointDepart(new Vecteur(0, 0));
		assertNotEquals(niveau.getPointDepart(), pointDepart);
		niveau.setPointDepart(null);
		assertNotEquals(niveau.getPointDepart(), null);
	}
	
	@Test
	public void testSetTitreNiveau()
	{
		assertEquals(niveau.getTitreNiveau(), titreNiveau);
		niveau.setTitreNiveau("");
		assertNotEquals(niveau.getTitreNiveau(), titreNiveau);
		niveau.setTitreNiveau(null);
		assertNotEquals(niveau.getTitreNiveau(), null);
	}
	
	@Test
	public void testSetVitesseDepart()
	{
		assertEquals(niveau.getVitesseDepart(), vitesseDepart);
		niveau.setVitesseDepart(new Vecteur(0, 0));
		assertNotEquals(niveau.getVitesseDepart(), vitesseDepart);
		niveau.setVitesseDepart(null);
		assertNotEquals(niveau.getVitesseDepart(), null);
	}
}