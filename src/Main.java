import controleur.ContPrincipal;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * D�marrage de l'application
 * @author EquBolduc
 * @version 1.0
 */
public class Main extends Application
{
	/**
	 * D�marre l'application.
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
			e.printStackTrace();
		}
	}
	
	/** M�thode main
	 * @param args Param�tres en ligne de commande.
	 */
	public static void main(String[] args)
	{
		launch(args);
	}
}
