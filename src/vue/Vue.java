package vue;



import javafx.scene.layout.BorderPane;

public interface Vue
{
	public String getFXML();
	public String getCSS();
	
	public void initialiser(BorderPane pane);
	
	void dessiner(double dt);
}
