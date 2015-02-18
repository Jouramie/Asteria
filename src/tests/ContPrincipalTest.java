package tests;

import static org.junit.Assert.*;
import java.util.List;
import modele.Corps;
import objets.Planete;
import org.junit.Before;
import org.junit.Test;
import controleur.ContPrincipal;
import utils.Vecteur;

/**
 * Classe de test pour le contrôleur principal.
 * @author EquBolduc
 * @version 1.0
 */
public class ContPrincipalTest
{
	private Planete p1, p2;
	
	@Before
	public void testContPrincipal()
	{
		p1 = new Planete(5000, new Vecteur(0, 0));
		p2 = new Planete(10000, new Vecteur(10, 10));
		
		ContPrincipal.getInstance().viderCorps();
	}

	@Test
	public void testAjouterCorps()
	{
		ContPrincipal.getInstance().ajouterCorps(p1);
		ContPrincipal.getInstance().ajouterCorps(p2);
		List<Corps> liste = ContPrincipal.getInstance().getCorps();
		assertEquals(2, liste.size());
		
		ContPrincipal.getInstance().ajouterCorps(null);
		assertEquals(2, liste.size());
	}

	@Test
	public void testEnleverCorps()
	{
		ContPrincipal.getInstance().ajouterCorps(p1);
		ContPrincipal.getInstance().ajouterCorps(p2);
		ContPrincipal.getInstance().enleverCorps(p1);
		List<Corps> liste = ContPrincipal.getInstance().getCorps();
		assertEquals(1, liste.size());
		
		ContPrincipal.getInstance().enleverCorps(null);
		assertEquals(1, liste.size());
	}

	@Test
	public void testViderCorps()
	{
		ContPrincipal.getInstance().ajouterCorps(p1);
		ContPrincipal.getInstance().ajouterCorps(p2);
		ContPrincipal.getInstance().viderCorps();
		List<Corps> liste = ContPrincipal.getInstance().getCorps();
		assertEquals(0, liste.size());
	}

	@Test
	public void testGetInstance()
	{
		ContPrincipal cp1 = ContPrincipal.getInstance();
		ContPrincipal cp2 = ContPrincipal.getInstance();
		
		assertEquals(cp1, cp2);
	}
	
	// Les méthodes suivantes ne sont pas testées, car elles dépendent
		// de FXML.
		/*@Test
		public void testInitialiser()
		{
			fail("Not yet implemented");
		}

		@Test
		public void testSelectionnerControleur()
		{
			
			ContPrincipal.getInstance().selectionnerControleur(c1);
		}

		@Test
		public void testAfficherVue()
		{
			fail("Not yet implemented");
		}

		@Test
		public void testUpdate()
		{
			fail("Not yet implemented");
		}*/
}
