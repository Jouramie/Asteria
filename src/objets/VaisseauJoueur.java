package objets;

import utils.Vecteur;

public class VaisseauJoueur extends Vaisseau
{
	
	public static final double PUISSANCE_ROTATION_DEFAUT = 1;
	
	protected boolean gauche;
	protected boolean droite;
	protected boolean moteur;
	protected double puissanceRotation;
	
	public VaisseauJoueur(double pPuissanceMax, Vecteur pDirection,
			double pMasse, double pCapaciteCarburant, Vecteur pPosition,
			Vecteur pVitesse)
	{
		super(pPuissanceMax, pMasse, pCapaciteCarburant, pPosition,
				pVitesse);
		initDefaut();
	}
	
	public VaisseauJoueur(double pPuissanceMax, Vecteur pDirection,
			double pMasse, double pCapaciteCarburant, double pPositionX,
			double pPositionY, Vecteur pVitesse)
	{
		super(pPuissanceMax, pMasse, pCapaciteCarburant,
				pPositionX, pPositionY, pVitesse);
		initDefaut();
	}
	
	private void initDefaut()
	{
		puissanceRotation = PUISSANCE_ROTATION_DEFAUT;
		direction = vitesse.normaliser();
		gauche = false;
		droite = false;
		moteur = false;
	}
	
	public void tournerGauche()
	{
		gauche = !gauche;
	}
	
	public void tournerDroite()
	{
		droite = !droite;
	}
	
	public void avancer()
	{
		moteur = !moteur;
	}
	
	/**
	 * Si le vecteur est null, met la vitesse à 0.
	 */
	public void setVitesse(Vecteur pVitesse)
	{
		if (pVitesse == null)
		{
			vitesse = new Vecteur();
		}
		else
		{
			vitesse = pVitesse;
		}
	}
	
	public Vecteur getForceExt()
	{
		Vecteur r = new Vecteur();
		if (moteur)
		{
			r = direction.multiplication(puissance);
		}
		return r;
	}
	
	/**
	 * Met à jour le noeud représentant le vaisseau
	 */
	public void maj()
	{
		
		if ((gauche || droite) && !(gauche && droite))
		{
			if (gauche)
			{
				direction.setAngle(direction.getAngle() - puissanceRotation
						/ 360 * 2 * Math.PI);
			}
			else if (droite)
			{
				direction.setAngle(direction.getAngle() + puissanceRotation
						/ 360 * 2 * Math.PI);
			}
		}
		noeud.setRotate(direction.getAngle() / 2 / Math.PI * 360 + 90);
	}
	
}
