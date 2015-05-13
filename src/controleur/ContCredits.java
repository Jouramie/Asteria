package controleur;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import objets.Tete;
import utils.Vecteur;
import vue.VueCredits;

/**
 * Contr�leur utilis� lors du lancement des cr�dits.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class ContCredits implements Controleur
{
	private VueCredits vue;
	
	/**
	 * Contr�leur de la classe ContCredits.
	 */
	public ContCredits()
	{
		vue = new VueCredits();
	}
	
	/**
	 * Affiche la vue des cr�dits.
	 */
	public void initialiser()
	{
		ContPrincipal.getInstance().afficherVue(vue, true);
		Platform.runLater(() -> {
			chargerCredits();
		});
	}
	
	/**
	 * Charge les �l�ments visibles dans les cr�dits.
	 */
	private void chargerCredits()
	{
		ContPrincipal.getInstance().viderCorps();
		Tete simon = new Tete(1, 100, new Vecteur(50, 50),
				new Vecteur(200, 175));
		simon.setTexture(Tete.Texture.SIMONPIERRE);
		ContPrincipal.getInstance().ajouterCorps(simon);
		
		Tete j� = new Tete(1, 100, new Vecteur(450, 50), new Vecteur(-175, 200));
		j�.setTexture(Tete.Texture.JEREMIE);
		ContPrincipal.getInstance().ajouterCorps(j�);
		
		Tete jo = new Tete(1, 100, new Vecteur(50, 450), new Vecteur(175, -175));
		jo.setTexture(Tete.Texture.JONATHAN);
		ContPrincipal.getInstance().ajouterCorps(jo);
		
		Tete emile = new Tete(1, 100, new Vecteur(450, 450), new Vecteur(-175,
				-200));
		emile.setTexture(Tete.Texture.EMILE);
		ContPrincipal.getInstance().ajouterCorps(emile);
		
		vue.initialiserCorps();
		ContPrincipal.getInstance().demarrerHorloge();
	}
	
	@FXML
	public void keyPressed(KeyEvent e)
	{
		if(e.getCode() == KeyCode.ESCAPE)
		{
			ContPrincipal.getInstance().viderCorps();
			ContPrincipal.getInstance().selectionnerControleur(new ContMenu());
		}
	}
	
	public void update(double dt)
	{
		
	}
}
