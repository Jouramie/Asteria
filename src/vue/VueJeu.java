package vue;

import java.util.LinkedList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import modele.Corps;
import controleur.ContPrincipal;

public class VueJeu implements Vue
{
	@FXML
	private Pane pane;
	
	private List<Dessinable> liste;
	
	public VueJeu()
	{
		liste = new LinkedList<Dessinable>();
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
				System.out.println(corps.getPositionXProperty());
				n.translateXProperty().bind(corps.getPositionXProperty());
				n.translateYProperty().bind(corps.getPositionYProperty());
			    pane.getChildren().add(n);
			}
		}
	}

	public void dessiner(double dt)
	{

	}

}
