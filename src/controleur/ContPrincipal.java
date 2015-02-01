package controleur;

import java.util.List;

import javafx.stage.Stage;
import modele.Corps;
import modele.MoteurPhysique;
import vue.Vue;

public class ContPrincipal
{
	private static ContPrincipal instance;
	
	private Stage stage;
	private Controleur cont;
	private Vue vue;
	private MoteurPhysique phys;
	
	private List<Corps> corps;
	
	private ContPrincipal()
	{
		
	}
	
	public void initialiser(Stage stage)
	{
		
	}
	
	public void selectionnerControlleur(Controleur c)
	{
		
	}
	
	public void afficherVue(Vue v)
	{
		
	}
	
	public void ajouterCorps(Corps c)
	{
		
	}
	
	public void enleverCorps(Corps c)
	{
		
	}
	
	public void viderCorps()
	{
		
	}
	
	public static ContPrincipal getInstance()
	{
		if(instance == null)
		{
			instance = new ContPrincipal();
		}
		
		return instance;
	}
}
