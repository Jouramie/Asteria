package controleur;

import vue.VueCredits;

public class ContCredits implements Controleur
{
	public void initialiser()
	{
		ContPrincipal.getInstance().afficherVue(new VueCredits(), true);
	}

	public void update(double dt)
	{
		
	}
}