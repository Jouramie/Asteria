package vue;

import javafx.scene.layout.BorderPane;

public class VueMenu implements Vue
{
	public VueMenu()
	{
		
	}

	public String getFXML()
	{
		return "/res/Test.fxml";
	}

	public String getCSS()
	{
		return "/res/test.css";
	}

	public void initialiser(BorderPane pane)
	{

	}

	public void dessiner(double dt)
	{

	}

}
