import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;


public class Main extends Application implements Initializable
{	
	public void start(Stage primaryStage)
	{
		try
		{
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/interface.fxml"));
			Scene scene = new Scene(root, 600, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void initialize(URL url, ResourceBundle rb)
	{
		
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}