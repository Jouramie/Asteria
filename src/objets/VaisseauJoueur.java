package objets;

import modele.Corps;
import utils.Vecteur;

/**
 * Classe repr�sentant un vaisseau contr�lable par l'utilisateur.
 * 
 * @author EquBolduc
 */
public class VaisseauJoueur extends Vaisseau {
	public static final double PUISSANCE_MOTEUR = 200000;
	public static final double PUISSANCE_ROTATION_DEFAUT = 1;

	protected boolean gauche;
	protected boolean droite;
	protected boolean moteur;
	protected double puissanceRotation;

	/**
	 * Constructeur de base.
	 * 
	 * @param pPuissanceMax
	 *            Puissance maximale du moteur (en Newton).
	 * @param pDirection
	 *            Direction initiale du vaisseau.
	 * @param pMasse
	 *            Masse du vaisseau (en kg).
	 * @param pCapaciteCarburant
	 *            Quantit� maximale de carburant (en kg).
	 * @param pPosition
	 *            Position dans l'espace.
	 * @param pVitesse
	 *            Vitesse initiale du vaisseau.
	 */
	public VaisseauJoueur(double pPuissanceMax, Vecteur pDirection,
			double pMasse, double pCapaciteCarburant, Vecteur pPosition,
			Vecteur pVitesse) {
		super(pPuissanceMax, pMasse, pCapaciteCarburant, pPosition, pVitesse);
		initDefaut();
	}

	/**
	 * Autre constructeur du vaisseau.
	 * 
	 * @param pPuissanceMax
	 *            Puissance maximale du moteur (en Newton).
	 * @param pDirection
	 *            Direction initiale du vaisseau.
	 * @param pMasse
	 *            Masse du vaisseau (en kg).
	 * @param pCapaciteCarburant
	 *            Quantit� de carburant maximale (en kg).
	 * @param pPositionX
	 *            Position en X dans l'espace.
	 * @param pPositionY
	 *            Position en Y dans l'espace.
	 * @param pVitesse
	 *            Vitesse initiale du vaisseau.
	 */
	public VaisseauJoueur(double pPuissanceMax, Vecteur pDirection,
			double pMasse, double pCapaciteCarburant, double pPositionX,
			double pPositionY, Vecteur pVitesse) {
		super(pPuissanceMax, pMasse, pCapaciteCarburant, pPositionX,
				pPositionY, pVitesse);
		initDefaut();
	}

	/**
	 * Initialise le vaisseau avec des valeurs par d�faut.
	 */
	private void initDefaut() {
		puissanceRotation = PUISSANCE_ROTATION_DEFAUT;
		direction = vitesse.normaliser();
		gauche = false;
		droite = false;
		moteur = false;
	}

	/**
	 * Commence ou arr�te de faire tourner le vaisseau vers la gauche.
	 */
	public void tournerGauche() {
		gauche = !gauche;
	}

	/**
	 * Commence ou arr�te de faire tourner le vaisseau vers la droite.
	 */
	public void tournerDroite() {
		droite = !droite;
	}

	/**
	 * Commence ou arr�te de faire avancer le vaisseau vers la gauche.
	 */
	public void avancer() {
		moteur = !moteur;
	}

	/**
	 * Modifie la vitesse du vaisseau. Si le vecteur est null, met la vitesse �
	 * 0.
	 */
	public void setVitesse(Vecteur pVitesse) {
		if (pVitesse == null) {
			vitesse = new Vecteur();
		} else {
			vitesse = pVitesse;
		}
	}

	/**
	 * Retourne la force appliqu�e par le moteur (en Newton).
	 * 
	 * @return Force du moteur.
	 */
	public Vecteur getForceExt() {
		Vecteur r = new Vecteur();
		if (moteur && !(carburantRestant.get() <= 0)) {
			r = direction.multiplication(puissance * PUISSANCE_MOTEUR);
		}
		return r;
	}

	/**
	 * Met � jour le noeud repr�sentant le vaisseau
	 */
	public void maj(double dt) {
		if ((gauche || droite) && !(gauche && droite)) {
			if (gauche) {
				direction.setAngle(direction.getAngle() - puissanceRotation
						/ 360 * 2 * Math.PI);
			} else if (droite) {
				direction.setAngle(direction.getAngle() + puissanceRotation
						/ 360 * 2 * Math.PI);
			}
		}

		super.maj(dt);
	}

	/**
	 * Met � jour la quantit� de carburant du vaisseau.
	 * 
	 * @param dt
	 *            Temps �coul� (en secondes).
	 */
	public void update(double dt) {
		if (moteur) {
			setCarburantRestant(getCarburantRestant() - (puissance * dt));
		}
	}

	/**
	 * R�agit � la collision avec un autre corps.
	 */
	public void onCollision(Corps c) {
		setSante(0.0);
	}

	/**
	 * Modifie l'angle de direction du vaisseau.
	 * 
	 * @param angle
	 *            Angle du vaisseau.
	 */
	public void setAngle(double angle) {
		direction.setAngle(angle);
	}
}
