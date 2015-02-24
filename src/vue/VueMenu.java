package vue;

import javafx.scene.layout.BorderPane;

/**
 * Vue du menu. Classe très simpliste.
 * @author EquBolduc
 * @version 1.0
 */
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
