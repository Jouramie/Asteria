package tests;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import modele.Corps;
import modele.Niveau;
import objets.Planete;
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
	
	private List<String> objectifs;
	
	private Vecteur pointDepart;
	
	private String titreNiveau;
	
	private Vecteur vitesseDepart;
	
	@Before
	public void testNiveau()
	{
		corps = new ArrayList<>();
		corps.add(new Planete(1000, 100, 100, 15));
		
		objectifs = new ArrayList<>();
		objectifs.add("Objectif 1");
		
		pointDepart = new Vecteur(10, 10);
		titreNiveau = "Niveau 1";
		vitesseDepart = new Vecteur(10, 0);
		
		niveau = new Niveau(corps, objectifs, pointDepart, titreNiveau, vitesseDepart);
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
	public void testAjouterObjectif()
	{
		assertEquals(niveau.getObjectifs().size(), 1);
		niveau.ajouterObjectif("Objectif 2");
		assertEquals(niveau.getObjectifs().size(), 2);
		niveau.ajouterObjectif(null);
		assertEquals(niveau.getObjectifs().size(), 2);
	}

	@Test
	public void testGetCorps()
	{
		assertEquals(niveau.getCorps(), corps);
	}

	@Test
	public void testGetObjectifs()
	{
		assertEquals(niveau.getObjectifs(), objectifs);
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