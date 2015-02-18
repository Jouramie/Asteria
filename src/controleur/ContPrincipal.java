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
 * Contrôleur principal servant à charger les contrôleurs
 * et les vues. Contient aussi le modèle.
 * Implémentez comme un singleton.
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
	Thread th;
	Temps clock;
	
	private List<Corps> corps;
	
	/**
	 * Constructeur de la classe.
	 * Notez que cette méthode est privée pour éviter d'avoir plus d'une instance.
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
	 * Démarre l'horloge interne.
	 * @param stage Stage JavaFX.
	 */
	public void initialiser(Stage stage)
	{
		this.stage = stage;
		clock = new Temps();
		th = new Thread(clock);
        th.setDaemon(true);
        th.start();
		selectionnerControleur(new ContMenu());
	}
	
	/**
	 * Charge un contrôleur.
	 * @param c Contrôleur à charger.
	 */
	public void selectionnerControleur(Controleur c)
	{		
		if(c != null)
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
		if(v != null)
		{
			try
			{
				vue = v;
				
				root = (BorderPane)FXMLLoader.load(getClass().getResource(v.getFXML()));
				Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				
				Platform.runLater(()->
				{
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
	 * @param time Temps écoulé depuis le dernier frame (secondes).
	 */
	public void update(double time)
	{
		phys.update(corps, time);
	}
	
	/**
	 * Ajoute un corps physique au modèle.
	 * @param c Corps à ajouter.
	 */
	public void ajouterCorps(Corps c)
	{
		if(c != null)
		{
			corps.add(c);
		}
	}
	
	/**
	 * Supprime un corps physique du modèle.
	 * @param c Corps a supprimer.
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
	 * @return Liste de corps gérés par le moteur physique.
	 */
	public List<Corps> getCorps()
	{
		return corps;
	}
	
	/**
	 * Méthode statique permettant d'accéder au contrôleur principal
	 * depuis n'importe quelle classe.
	 * @return Contrôleur principal.
	 */
	public static ContPrincipal getInstance()
	{	
		return instance;
	}
	
	/**
	 * Classe responsable de l'horloge interne.
	 * @author Jonathan Samson
	 */
	private class Temps extends Task<Void>{
		private long previousTime;
		private long currentTime;
		
		@Override protected Void call() throws Exception {
			
			previousTime = 0;
			currentTime = System.currentTimeMillis();
			
			while(true)
			{
				previousTime = currentTime;
				currentTime = System.currentTimeMillis();
				Platform.runLater(() -> {
					update ((double)(currentTime - previousTime) / 1000);
				});
				Thread.sleep(5);
			}
		}

	}
}
