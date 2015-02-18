package vue;

import javafx.scene.layout.BorderPane;

public class VueJeu implements Vue
{
	public VueJeu()
	{
		
	}
	
	public String getFXML()
	{
		return "/res/Jeu.fxml";
	}

	public String getCSS()
	{
		return null;
	}

	public void initialiser(BorderPane pane)
	{

	}

	public void dessiner(double dt)
	{

	}

}
