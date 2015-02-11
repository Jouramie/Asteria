import controleur.ContPrincipal;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
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
	
	public static void main(String[] args)
	{
		launch(args);
	}
}
