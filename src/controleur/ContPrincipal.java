package controleur;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
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
	private boolean contCharge;
	private Vue vue;
	private boolean vueChargee;
	private MoteurPhysique phys;
	private boolean horlogeDemarree;
	private Horloge horloge;
	
	private static List<Corps> corps;
	
	/**
	 * Constructeur de la classe. Notez que cette méthode est privée pour éviter
	 * d'avoir plus d'une instance.
	 */
	private ContPrincipal()
	{
		root = null;
		cont = null;
		contCharge = false;
		vue = null;
		vueChargee = false;
		
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
		
		horlogeDemarree = false;
		horloge = new Horloge();
		Thread th = new Thread(horloge);
		th.setDaemon(true);
		th.start();
		stage.getIcons().clear();
		stage.getIcons().add(new Image("/res/favicon.png"));
	}
	
	/**
	 * Démarre l'horloge interne.
	 */
	public void demarrerHorloge()
	{
		horlogeDemarree = true;
	}
	
	/**
	 * Arrête l'horloge interne.
	 */
	public void arreterHorloge()
	{
		horlogeDemarree = false;
	}
	
	public boolean isHorlogeDemarree()
	{
		return horlogeDemarree;
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
			contCharge = true;
		}
	}
	
	/**
	 * Charge une vue FXML en mode fenêtré.
	 * 
	 * @param v
	 *            Vue à charger.
	 */
	public void afficherVue(Vue v)
	{
		afficherVue(v, false);
	}
	
	/**
	 * Charge une vue FXML et affiche la fenêtre.
	 * 
	 * @param v
	 *            Vue à charger.
	 * @param fullscreen
	 *            Détermine si la vue doit être plein écran.
	 */
	public void afficherVue(Vue v, boolean fullscreen)
	{
		if (v != null)
		{
			try
			{
				vueChargee = false;
				vue = v;
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource(v.getFXML()));
				loader.setController(cont);
				root = (BorderPane) loader.load();
				
				Scene scene = null;
				if (fullscreen)
				{
					Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
					scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
				}
				else
				{
					scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
				}
				
				stage.setScene(scene);
				stage.setResizable(false);
				stage.setTitle("Opération : Astéria");
				stage.setFullScreen(fullscreen);
				stage.setFullScreenExitHint("");
				stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
				stage.show();
				
				Platform.runLater(() -> {
					vue.initialiser(root);
					vueChargee = true;
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
		if (horlogeDemarree)
		{
			phys.update(corps, time);
		}
		
		if (cont != null && contCharge)
		{
			cont.update(time);
		}
		
		if (vue != null && vueChargee)
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
	private class Horloge extends Task<Void>
	{
		private long previousTime;
		private long currentTime;
		
		@Override
		protected Void call() throws Exception
		{
			
			previousTime = 0;
			currentTime = System.currentTimeMillis();
			
			while (true)
			{
				previousTime = currentTime;
				currentTime = System.currentTimeMillis();
				Platform.runLater(() -> {
					update((double) (currentTime - previousTime) / 1000);
				});
				Thread.sleep(5);
			}
		}
		
	}
}
