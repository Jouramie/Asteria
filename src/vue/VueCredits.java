package vue;
import java.util.LinkedList;
import java.util.List;

import modele.Corps;
import controleur.ContPrincipal;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
public class VueCredits implements Vue
{
	@FXML
	private BorderPane borderPane;
	private List<Dessinable> liste;
	
	public VueCredits()
	{
		liste = new LinkedList<Dessinable>();
	}
	
	public String getFXML()
	{
		return "/res/Credits.fxml";
	}
	
	public void initialiser(BorderPane pane)
	{
		borderPane = pane;
		initialiserCorps();
		ContPrincipal.getInstance().getMoteurPhysique().setTailleEcranX(pane.getWidth());
		ContPrincipal.getInstance().getMoteurPhysique().setTailleEcranY(pane.getHeight());
	}

	public void initialiserCorps()
	{
		Pane p = (Pane) borderPane.lookup("#pane");
		p.getChildren().clear();
		
		liste.clear();
		
		List<Corps> listTemp = ContPrincipal.getInstance().getCorps();
		if (listTemp.size() == 0)
		{
			return;
		}
		
		for (Corps c : listTemp)
		{
			if (c instanceof Dessinable)
			{
				liste.add((Dessinable) c);
			}
		}
		
		for (Dessinable c : liste)
		{
			Node n = c.getNoeud();
			
			if (n != null)
			{
				Corps corps = (Corps) c;
				n.translateXProperty().bind(corps.getPositionXProperty());
				n.translateYProperty().bind(corps.getPositionYProperty());
				p.getChildren().add(n);
			}
		}
	}

	public void dessiner(double dt)
	{
		
	}
}