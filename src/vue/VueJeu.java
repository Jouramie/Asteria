package vue;

import java.util.LinkedList;
import java.util.List;
import utils.Vecteur;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import modele.Corps;
import controleur.ContPrincipal;

/**
 * Vue principale du jeu.
 * @author EquBolduc
 * @version 1.0
 */
public class VueJeu implements Vue
{
	private BorderPane borderPane;
	private Group noeud;
	private List<Dessinable> liste;
	private Camera camera;
	private Scale scale;
	private Translate trans;
	
	/**
	 * Constructeur de la vue du jeu.
	 */
	public VueJeu()
	{
		liste = new LinkedList<Dessinable>();
		camera = new Camera();
		scale = new Scale(0, 0, 0, 0);
		trans = new Translate(0, 0);
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
	 * Ajoute tous les corps dessinables dans la vue.
	 */
	public void initialiser(BorderPane pane)
	{
		borderPane = pane;
		
		camera.setGrandeurs(pane.getWidth(), pane.getHeight());
		
		initialiserCorps();
	}
	
	public void initialiserCorps()
	{
		List<Corps> listTemp = ContPrincipal.getInstance().getCorps();
		if(listTemp.size() == 0)
		{
			return;
		}
		
		noeud = new Group();
		liste.clear();
		
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
			    noeud.getChildren().add(n);
			}
		}
		
		noeud.getTransforms().add(scale);
		noeud.getTransforms().add(trans);
		Pane p = (Pane)borderPane.lookup("#pane");
		p.getChildren().clear();
		p.getChildren().add(noeud);
	}

	/**
	 * Met à jour la caméra.
	 */
	public void dessiner(double dt)
	{
		camera.update(dt);
		
		Vecteur translation = camera.getTranslation();
		trans.setX(translation.getX());
		trans.setY(translation.getY());
		
		double facteur = camera.getFacteur();
		scale.setX(facteur);
		scale.setY(facteur);
		
		for(Dessinable d : liste){
			d.maj();
		}
		
	}
	
	public Camera getCamera()
	{
		return camera;
	}
}
