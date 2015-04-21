package objets;

import modele.Corps;
import utils.Vecteur;

/**
 * Classe représentant un vaisseau contrôlable par l'utilisateur.
 * 
 * @author EquBolduc
 */
public class VaisseauJoueur extends Vaisseau
{
	public static final double PUISSANCE_MOTEUR = 200000;
	public static final double PUISSANCE_ROTATION_DEFAUT = 1;
	
	protected boolean gauche;
	protected boolean droite;
	protected boolean moteur;
	protected double puissanceRotation;
	
	/**
	 * Utilisez plutôt le constructeur comprenant le carburant de départ et le carburant maximum.
	 * 
	 * @param pPuissance
	 *            Puissance maximale du moteur (en Newton).
	 * @param pDirection
	 *            Direction initiale du vaisseau.
	 * @param pMasse
	 *            Masse du vaisseau (en kg).
	 * @param pCarburantMax
	 *            Quantité maximale de carburant (en kg).
	 * @param pPosition
	 *            Position dans l'espace.
	 * @param pVitesse
	 *            Vitesse initiale du vaisseau.
	 */
	@Deprecated
	public VaisseauJoueur(double pPuissance, Vecteur pDirection,
			double pMasse, double pCarburantMax, Vecteur pPosition,
			Vecteur pVitesse)
	{
		super(pPuissance, pMasse, pCarburantMax, pPosition, pVitesse);
		initDefaut();
	}
	
	/**
	 * Utilisez plutôt le constructeur comprenant le carburant de départ et le carburant maximum.
	 * 
	 * @param pPuissance
	 *            Puissance maximale du moteur (en Newton).
	 * @param pDirection
	 *            Direction initiale du vaisseau.
	 * @param pMasse
	 *            Masse du vaisseau (en kg).
	 * @param pCarburantMax
	 *            Quantité de carburant maximale (en kg).
	 * @param pPositionX
	 *            Position en X dans l'espace.
	 * @param pPositionY
	 *            Position en Y dans l'espace.
	 * @param pVitesse
	 *            Vitesse initiale du vaisseau.
	 */
	@Deprecated
	public VaisseauJoueur(double pPuissance, Vecteur pDirection,
			double pMasse, double pCarburantMax, double pPositionX,
			double pPositionY, Vecteur pVitesse)
	{
		super(pPuissance, pMasse, pCarburantMax, pPositionX,
				pPositionY, pVitesse);
		initDefaut();
	}
	
	/**
	 * Constructeur du vaisseauJoueur de base.
	 * 
	 * @param pPuissance
	 *            Puissance maximale du moteur (en Newton).
	 * @param pMasse
	 *            Masse du vaisseau (en kg).
	 * @param pCarburantMax
	 *            Quantité de carburant maximale (en kg).
	 * @param pCarburantDepart
	 *            Quantité de carburant de départ (en kg).
	 * @param pPositionX
	 *            Position en X dans l'espace.
	 * @param pPositionY
	 *            Position en Y dans l'espace.
	 * @param pVitesse
	 *            Vitesse initiale du vaisseau.
	 */
	public VaisseauJoueur(double pPuissance, double pMasse,
			double pCarburantMax, double pCarburantDepart)
	{
		super(pPuissance, pMasse, pCarburantMax, pCarburantDepart);
		initDefaut();
	}

	
	/**
	 * Initialise le vaisseau avec des valeurs par défaut.
	 */
	private void initDefaut()
	{
		puissanceRotation = PUISSANCE_ROTATION_DEFAUT;
		direction = vitesse.normaliser();
		gauche = false;
		droite = false;
		moteur = false;
	}
	
	/**
	 * Commence ou arrête de faire tourner le vaisseau vers la gauche.
	 */
	public void tournerGauche()
	{
		gauche = !gauche;
	}
	
	/**
	 * Commence ou arrête de faire tourner le vaisseau vers la droite.
	 */
	public void tournerDroite()
	{
		droite = !droite;
	}
	
	/**
	 * Commence ou arrête de faire avancer le vaisseau vers la gauche.
	 */
	public void avancer()
	{
		moteur = !moteur;
	}
	
	/**
	 * Modifie la vitesse du vaisseau. Si le vecteur est null, met la vitesse à
	 * 0.
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
	
	/**
	 * Retourne la force appliquée par le moteur (en Newton).
	 * 
	 * @return Force du moteur.
	 */
	public Vecteur getForceExt()
	{
		Vecteur r = new Vecteur();
		if (moteur && !(carburantRestant.get() <= 0))
		{
			r = direction.multiplication(puissance * PUISSANCE_MOTEUR);
		}
		return r;
	}
	
	/**
	 * Met à jour le noeud représentant le vaisseau
	 */
	public void maj(double dt)
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
		
		super.maj(dt);
	}
	
	/**
	 * Met à jour la quantité de carburant du vaisseau.
	 * 
	 * @param dt
	 *            Temps écoulé (en secondes).
	 */
	public void update(double dt)
	{
		if (moteur)
		{
			setCarburantRestant(getCarburantRestant() - (puissance * dt));
		}
	}
	
	/**
	 * Réagit à la collision avec un autre corps.
	 */
	public void onCollision(Corps c)
	{
		if (c.getClass().equals(Vaisseau.class)
				&& c.getMasse() < getMasse() * 2)
		{
			setSante(getSante() - 0.5);
		}
		else
		{
			moteur = false;
			setSante(0.0);
		}
	}
	
	/**
	 * Modifie l'angle de direction du vaisseau.
	 * 
	 * @param angle
	 *            Angle du vaisseau.
	 */
	public void setAngle(double angle)
	{
		direction.setAngle(angle);
	}
}
