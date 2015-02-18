package controleur;

import java.net.URL;
import java.util.ResourceBundle;
import vue.VueMenu;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class ContMenu implements Controleur, Initializable
{
	@FXML
	private Button jouer;
	@FXML
	private Button quitter;
	
	public ContMenu()
	{
		
	}
	
	public void initialiser()
	{
		ContPrincipal.getInstance().afficherVue(new VueMenu());
	}

	public void update(double dt)
	{
		
	}

	public void initialize(URL arg0, ResourceBundle arg1)
	{
		
	}

	@FXML
	public void quitter()
	{
		System.exit(0);
	}
	
	@FXML
	public void jouer()
	{
		ContPrincipal.getInstance().selectionnerControleur(new ContJeu());
	}
}
