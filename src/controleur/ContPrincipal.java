package controleur;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modele.Corps;
import modele.MoteurPhysique;
import vue.Vue;

/**
 * Contrôleur principal servant à charger les contrôleurs et les vues. Contient
 * aussi le modèle. Implémentez comme un singleton.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class ContPrincipal
{
	private static final ContPrincipal instance = new ContPrincipal();
	
	private Stage stage;
	private BorderPane root;
	private Controleur cont;
	private Vue vue;
	private MoteurPhysique phys;
	private Thread th;
	private Temps clock;
	private static boolean onoff;
	
	private List<Corps> corps;
	
	/**
	 * Constructeur de la classe. Notez que cette méthode est privée pour éviter
	 * d'avoir plus d'une instance.
	 */
	private ContPrincipal()
	{
		root = null;
		cont = null;
		vue = null;
		
		corps = new LinkedList<Corps>();
		phys = new MoteurPhysique();
	}
	
	/**
	 * Initialise la vue avec le Stage de JavaFX.
	 * 
	 * @param stage
	 *            Stage JavaFX.
	 */
	public void initialiser(Stage stage)
	{
		this.stage = stage;
		selectionnerControleur(new ContMenu());
		onoff = true;
	}
	
	/**
	 * Démarre l'horloge interne.
	 */
	public void demarrerTemps()
	{
		onoff = true;
		clock = new Temps();
		th = new Thread(clock);
		th.setDaemon(true);
		th.start();
	}
	
	/**
	 * Arrête l'horloge interne.
	 */
	public void arreterTemps(){
		onoff = false;
	}
	
	public boolean isOnOff(){
		return onoff;
	}
	
	
	/**
	 * Charge un contrôleur.
	 * 
	 * @param c
	 *            Contrôleur à charger.
	 */
	public void selectionnerControleur(Controleur c)
	{
		if (c != null)
		{
			cont = c;
			cont.initialiser();
		}
	}
	
	/**
	 * Charge une vue FXML et affiche la fenêtre.
	 */
	public void afficherVue(Vue v)
	{
		if (v != null)
		{		
			try
			{
				vue = v;
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource(v.getFXML()));
				loader.setController(cont);
				root = (BorderPane)loader.load();
				Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
				stage.setScene(scene);
				stage.setResizable(false);
				stage.setTitle("Opération : Astéria");
				stage.show();
				
				Platform.runLater(() -> {
					vue.initialiser(root);
				});
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Méthode appelée à chaque frame par l'horloge interne.
	 * 
	 * @param time
	 *            Temps écoulé depuis le dernier frame (secondes).
	 */
	public void update(double time)
	{
		phys.update(corps, time);
		
		if(cont != null)
		{
			cont.update(time);
		}
		
		if(vue != null)
		{
			vue.dessiner(time);
		}
	}
	
	/**
	 * Ajoute un corps physique au modèle.
	 * 
	 * @param c
	 *            Corps à ajouter.
	 */
	public void ajouterCorps(Corps c)
	{
		if (c != null)
		{
			corps.add(c);
		}
	}
	
	/**
	 * Supprime un corps physique du modèle.
	 * 
	 * @param c
	 *            Corps a supprimer.
	 */
	public void enleverCorps(Corps c)
	{
		corps.remove(c);
	}
	
	/**
	 * Supprime tous les corps du moteur physique.
	 */
	public void viderCorps()
	{
		corps.clear();
	}
	
	/**
	 * Retourne tous les corps du moteur physique.
	 * 
	 * @return Liste de corps gérés par le moteur physique.
	 */
	public List<Corps> getCorps()
	{
		return corps;
	}
	
	/**
	 * Méthode statique permettant d'accéder au contrôleur principal depuis
	 * n'importe quelle classe.
	 * 
	 * @return Contrôleur principal.
	 */
	public static ContPrincipal getInstance()
	{
		return instance;
	}
	
	/**
	 * Classe responsable de l'horloge interne.
	 * 
	 * @author Jonathan Samson
	 */
	private class Temps extends Task<Object>
	{
		private long previousTime;
		private long currentTime;
		
		@Override
		protected Object call() throws Exception
		{
			
			previousTime = 0;
			currentTime = System.currentTimeMillis();
			
			while (onoff)
			{
				previousTime = currentTime;
				currentTime = System.currentTimeMillis();
				Platform.runLater(() -> {
					update((double) (currentTime - previousTime) / 1000);
				});
				Thread.sleep(5);
			}
			return new Object();
		}
		
	}
}
