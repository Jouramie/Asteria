package vue;

import java.util.LinkedList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import modele.Corps;
import controleur.ContPrincipal;

/**
 * Vue principale du jeu.
 * @author EquBolduc
 * @version 1.0
 */
public class VueJeu implements Vue
{
	@FXML
	private Pane pane;
	private List<Dessinable> liste;
	
	/**
	 * Constructeur de la vue du jeu.
	 */
	public VueJeu()
	{
		liste = new LinkedList<Dessinable>();
	}
	
	/**
	 * Retourne le chemin vers le fichier FXML de la vue.
	 * @return Chemin vers FXML.
	 */
	public String getFXML()
	{
		return "/res/Jeu.fxml";
	}

	/**
	 * Retourne le chemin vers le fichier CSS de la vue.
	 * Null pour l'instant.
	 * @return Chemin vers CSS.
	 */
	public String getCSS()
	{
		return null;
	}

	/**
	 * Ajoute tous les corps dessinables dans la vue.
	 */
	public void initialiser(BorderPane pane)
	{
		List<Corps> listTemp = ContPrincipal.getInstance().getCorps();
		if(listTemp.size() == 0)
		{
			return;
		}
		
		for (Corps c : listTemp)
		{
		    if(c instanceof Dessinable)
		    {
		    	liste.add((Dessinable) c);
		    }
		}
		for (Dessinable c : liste)
		{
			Node n = c.getNoeud();
			
			if(n != null)
			{
				Corps corps = (Corps)c;
				n.translateXProperty().bind(corps.getPositionXProperty());
				n.translateYProperty().bind(corps.getPositionYProperty());
			    pane.getChildren().add(n);
			}
		}
	}

	/**
	 * Inutile pour l'instant.
	 */
	public void dessiner(double dt)
	{

	}
}
