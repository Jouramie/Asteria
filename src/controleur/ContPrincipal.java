package controleur;

import java.util.List;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
	Thread th;
	Temps clock;
	
	private List<Corps> corps;
	
	private ContPrincipal()
	{
		
	}
	
	public void initialiser(Stage stage)
	{
		clock = new Temps();
		th = new Thread(clock);
        th.setDaemon(true);
        th.start();
	}
	
	public void selectionnerControlleur(Controleur c)
	{
		
	}
	
	public void afficherVue(Vue v)
	{
		
	}
	
	public void update(double time)
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
	
	private class Temps extends Task<Void>{
		@Override protected Void call() throws Exception {
			
			double previousTime = 0;
			double currentTime = System.currentTimeMillis();
			
			while(true)
			{
				previousTime = currentTime;
				currentTime = System.currentTimeMillis();
				update ((currentTime - previousTime) / 1000);
				Thread.sleep(5);
			}
		}

	}
}
