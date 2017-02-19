import controleur.ContPrincipal;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Demarrage de l'application
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class Main extends Application
{
	/**
	 * Demarre l'application.
	 */
	public void start(Stage primaryStage)
	{
		try
		{
			ContPrincipal cont = ContPrincipal.getInstance();
			cont.initialiser(primaryStage);
		}
		catch(Exception e)
		{
		}
	}
	
	/**
	 * Methode main
	 * 
	 * @param args Parametres en ligne de commande.
	 */
	public static void main(String[] args)
	{
		launch(args);
	}
}
