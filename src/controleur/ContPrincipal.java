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
 * Contr�leur principal servant � charger les contr�leurs et les vues. Contient
 * aussi le mod�le. Impl�mentez comme un singleton.
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
	 * Constructeur de la classe. Notez que cette m�thode est priv�e pour �viter
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
	 * D�marre l'horloge interne.
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
	 * Arr�te l'horloge interne.
	 */
	public void arreterTemps(){
		onoff = false;
	}
	
	public BorderPane getRoot(){
		return root;
	}
	
	/**
	 * Charge un contr�leur.
	 * 
	 * @param c
	 *            Contr�leur � charger.
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
	 * Charge une vue FXML et affiche la fen�tre.
	 */
	public void afficherVue(Vue v)
	{
		if (v != null)
		{
			try
			{
				vue = v;
				
				root = (BorderPane) FXMLLoader.load(getClass().getResource(
						v.getFXML()));
				Scene scene = new Scene(root, root.getPrefWidth(),
						root.getPrefHeight());
				stage.setScene(scene);
				stage.setResizable(false);
				stage.setTitle("Op�ration : Ast�ria");
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
	 * M�thode appel�e � chaque frame par l'horloge interne.
	 * 
	 * @param time
	 *            Temps �coul� depuis le dernier frame (secondes).
	 */
	public void update(double time)
	{
		phys.update(corps, time);
	}
	
	/**
	 * Ajoute un corps physique au mod�le.
	 * 
	 * @param c
	 *            Corps � ajouter.
	 */
	public void ajouterCorps(Corps c)
	{
		if (c != null)
		{
			corps.add(c);
		}
	}
	
	/**
	 * Supprime un corps physique du mod�le.
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
	 * @return Liste de corps g�r�s par le moteur physique.
	 */
	public List<Corps> getCorps()
	{
		return corps;
	}
	
	/**
	 * M�thode statique permettant d'acc�der au contr�leur principal depuis
	 * n'importe quelle classe.
	 * 
	 * @return Contr�leur principal.
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
