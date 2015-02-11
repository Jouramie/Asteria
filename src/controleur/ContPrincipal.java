package controleur;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modele.Corps;
import modele.MoteurPhysique;
import vue.Vue;

public class ContPrincipal
{
	private static final ContPrincipal instance = new ContPrincipal();
	
	private Stage stage;
	private BorderPane root;
	private Controleur cont;
	private Vue vue;
	private MoteurPhysique phys;
	
	private List<Corps> corps;
	
	private ContPrincipal()
	{
		root = null;
		cont = null;
		vue = null;
		
		corps = new LinkedList<Corps>();
	}
	
	public void initialiser(Stage stage)
	{
		this.stage = stage;
		
		selectionnerControlleur(new ContMenu());
	}
	
	public void selectionnerControlleur(Controleur c)
	{		
		if(c != null)
		{
			cont = c;
			cont.initialiser();
		}
	}
	
	public void afficherVue(Vue v)
	{
		if(v != null)
		{
			try
			{
				vue = v;
				
				root = (BorderPane)FXMLLoader.load(getClass().getResource(v.getFXML()));
				Scene scene = new Scene(root, 600, 600);
				
				if(v.getCSS() != null)
				{
					scene.getStylesheets().add(getClass().getResource(v.getCSS()).toExternalForm());
				}
				stage.setScene(scene);
				stage.show();
				
				vue.initialiser(root);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void ajouterCorps(Corps c)
	{
		if(c != null)
		{
			corps.add(c);
		}
	}
	
	public void enleverCorps(Corps c)
	{
		corps.remove(c);
	}
	
	public void viderCorps()
	{
		corps.clear();
	}
	
	public static ContPrincipal getInstance()
	{	
		return instance;
	}
}
