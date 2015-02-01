package objets;

import javafx.scene.Node;
import modele.Corps;
import utils.Vecteur;
import vue.Dessinable;

public class Planete implements Corps, Dessinable
{
	double masse;
	Vecteur position;
	
	public Planete()
	{
		
	}
	
	public double getMasse()
	{
		return 0;
	}

	public void setPosition()
	{
		
	}

	public Vecteur getPosition()
	{
		return null;
	}
	
	public boolean isStatique()
	{
		return true;
	}

	public void setVitesse()
	{
		
	}

	public Vecteur getVitesse()
	{
		return null;
	}

	public Vecteur getForceExt()
	{
		return null;
	}
	
	public Node getNoeud()
	{
		return null;
	}
}
