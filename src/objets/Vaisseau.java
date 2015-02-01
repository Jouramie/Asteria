package objets;

import javafx.scene.Node;
import modele.Corps;
import utils.Vecteur;
import vue.Dessinable;

public class Vaisseau implements Corps, Dessinable
{
	public double masseVide;
	public double puissanceMax;
	public double masseCarburant;
	public double puissance;
	public Vecteur direction;
	
	public Vaisseau()
	{
		
	}
	
	public void tournerGauche()
	{
		
	}
	
	public void tournerDroite()
	{
		
	}
	
	public void setPuissance(double p)
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
		return false;
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
