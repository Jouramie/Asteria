package vue;

import java.util.LinkedList;
import java.util.List;
import modele.Corps;
import controleur.ContPrincipal;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * Vue des crédits.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class VueCredits implements Vue
{
	private BorderPane borderPane;
	private List<Dessinable> liste;
	
	/**
	 * Constructeur de la vue des crédits.
	 */
	public VueCredits()
	{
		liste = new LinkedList<Dessinable>();
	}
	
	/**
	 * Retourne le chemin vers le fichier FXML de la vue.
	 * 
	 * @return Chemin vers FXML.
	 */
	public String getFXML()
	{
		return "/res/Credits.fxml";
	}
	
	/**
	 * Ajoute tous les corps dessinables dans la vue.
	 */
	public void initialiser(BorderPane pane)
	{
		borderPane = pane;
		Pane p = (Pane) borderPane.lookup("#pane");
		initialiserCorps();
		ContPrincipal.getInstance().getMoteurPhysique()
				.setTailleEcranX(p.getWidth());
		ContPrincipal.getInstance().getMoteurPhysique()
				.setTailleEcranY(p.getHeight());
	}
	
	public void initialiserCorps()
	{
		Pane p = (Pane) borderPane.lookup("#pane");
		p.getChildren().clear();
		
		liste.clear();
		
		List<Corps> listTemp = ContPrincipal.getInstance().getCorps();
		if(listTemp.size() == 0)
		{
			return;
		}
		
		for(Corps c: listTemp)
		{
			if(c instanceof Dessinable)
			{
				liste.add((Dessinable) c);
			}
		}
		
		for(Dessinable c: liste)
		{
			Node n = c.getNoeud();
			
			if(n != null)
			{
				Corps corps = (Corps) c;
				n.translateXProperty().bind(corps.getPositionXProperty());
				n.translateYProperty().bind(corps.getPositionYProperty());
				p.getChildren().add(n);
			}
		}
	}
	
	/**
	 * Met à jour la caméra.
	 */
	public void dessiner(double dt)
	{
		
	}
}
