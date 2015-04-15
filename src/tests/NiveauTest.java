package tests;
import static org.junit.Assert.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import modele.Corps;
import modele.Niveau;
import modele.Objectif;
import modele.ObjectifRayon;
import objets.Planete;
import objets.Vaisseau;
import objets.VaisseauJoueur;
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
	
	private String descriptionNiveau;
	
	private Niveau niveau;
	
	private Objectif objectif;
	
	private Vecteur pointDepart;
	
	private String titreNiveau;
	
	private VaisseauJoueur vaisseau;
	
	private double vitesseDepart;
	
	@Before
	public void testNiveau()
	{
		vaisseau = new VaisseauJoueur(0, new Vecteur(0, 0), 100, 0, new Vecteur(50, 50), new Vecteur(10, 0));
		
		corps = new ArrayList<>();
		corps.add(vaisseau);
		corps.add(new Planete(1000, 100, 100, 15));
		corps.add(new Vaisseau(0, 100, 0, new Vecteur(250, 250), new Vecteur(10, 0)));
		
		descriptionNiveau = "Niveau très difficile";
		objectif = new ObjectifRayon(new Vecteur(1000, 1000), 20);
		pointDepart = new Vecteur(10, 10);
		titreNiveau = "Niveau 1";
		vitesseDepart = 10;
		
		niveau = new Niveau(corps, descriptionNiveau, objectif, pointDepart, titreNiveau, vitesseDepart);
	}

	@Test
	public void testAjouterCorps()
	{
		assertEquals(niveau.getCorps().size(), 3);
		niveau.ajouterCorps(new Planete(0, 0, 0, 0));
		assertEquals(niveau.getCorps().size(), 4);
		niveau.ajouterCorps(null);
		assertEquals(niveau.getCorps().size(), 4);
	}
	
	@Test
	public void testChargerNiveau()
	{
		File f = new File("/IOTest.txt");
		niveau.sauvegarderNiveau(f);
		niveau.ajouterCorps(new Planete(10, 10, 10, 10));
		//assertEquals(niveau.getCorps().size() == Niveau.chargerNiveau(f).getCorps().size(), false);
		niveau.sauvegarderNiveau(f);
		//assertEquals(Niveau.chargerNiveau(null) == null, true);
	}

	@Test
	public void testGetCorps()
	{
		assertEquals(niveau.getCorps(), corps);
	}
	
	@Test
	public void testGetDescriptionNiveauNiveau()
	{
		assertEquals(niveau.getDescriptionNiveau(), descriptionNiveau);
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
		assertEquals(niveau.getVitesseDepart() == vitesseDepart, true);
	}
	
	@Test
	public void testSauvegarderNiveau()
	{
		File f = new File("/IOTest.txt");
		niveau.sauvegarderNiveau(f);
		niveau.ajouterCorps(new Planete(10, 10, 10, 10));
		//assertEquals(niveau.getCorps().size() == Niveau.chargerNiveau(f).getCorps().size() + 1, true);
		niveau.sauvegarderNiveau(null);
		niveau.ajouterCorps(new Planete(10, 450, 450, 10));
		//assertEquals(niveau.getCorps().size() == Niveau.chargerNiveau(f).getCorps().size() + 1, false);
	}
	
	@Test
	public void testSetDescriptionNiveau()
	{
		assertEquals(niveau.getDescriptionNiveau(), descriptionNiveau);
		niveau.setDescriptionNiveau("");
		assertNotEquals(niveau.getDescriptionNiveau(), descriptionNiveau);
		niveau.setDescriptionNiveau(null);
		assertNotEquals(niveau.getDescriptionNiveau(), null);
	}
	
	@Test
	public void testSetObjectif()
	{
		assertEquals(niveau.getObjectif(), objectif);
		niveau.setObjectif(new ObjectifRayon(new Vecteur(1500, 1500), 20));
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
		assertEquals(niveau.getVitesseDepart() == vitesseDepart, true);
		niveau.setVitesseDepart(0);
		assertNotEquals(niveau.getVitesseDepart(), vitesseDepart);
		niveau.setVitesseDepart(-1);
		assertNotEquals(niveau.getVitesseDepart(), null);
	}
}