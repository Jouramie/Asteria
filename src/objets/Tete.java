package objets;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import utils.Vecteur;
public class Tete extends ObjetSpatial
{
	private Texture texture;
	public static final Texture TEXTURE_DEFAUT = Texture.SIMONPIERRE;
	
	public Tete(double pMasse, Vecteur pPosition, boolean pStatique,
			Vecteur pVitesse)
	{
		super(pMasse, pPosition, pStatique, pVitesse);
	}
	
	public enum Texture
	{
		EMILE("/res/emile.png"),
		JEREMIE("/res/je.png"),
		JONATHAN("/res/jo.png"),
		SIMONPIERRE("/res/spd.png");
		
		private final String texture;
		
		/**
		 * Constructeur de texture.
		 * 
		 * @param pTexture l'emplacement du fichier contenant la texture.
		 */
		Texture(String pTexture)
		{
			texture = pTexture;
		}
		
		/**
		 * Retourne la texture selon son nom.
		 * 
		 * @param tex le nom de la texture recherché.
		 * @return la texture.
		 */
		public static Texture getTexture(String tex)
		{
			switch(tex.toLowerCase())
			{
			case "emile":
				return Texture.EMILE;
				
			case "jeremie":
				return Texture.JEREMIE;
				
			case "jonathan":
				return Texture.JONATHAN;
				
			case "simonpierre":
				return Texture.SIMONPIERRE;
				
			default:
				return TEXTURE_DEFAUT;
			}
		}
		
		/**
		 * @return l'emplacement du fichier contenant la texture.
		 */
		public String getTexture()
		{
			return texture;
		}
	}

	public double getRayon()
	{
		return 100.0;
	}

	public Vecteur getForceExt()
	{
		return new Vecteur();
	}

	public void maj(double dt)
	{
	}

	/**
	 * Change la texture de la tête.
	 * 
	 * @param pTexture la nouvelle texture de la tête.
	 */
	public void setTexture(Texture pTexture)
	{
		if (pTexture == null)
			texture = TEXTURE_DEFAUT;
		else
			texture = pTexture;
	}
	
	/**
	 * @return la texture de la tête.
	 */
	public Texture getTexture()
	{
		return texture;
	}
	
	public Node getNoeud()
	{
		ImageView image = new ImageView(texture.getTexture());
		image.setFitWidth(200);
		image.setFitHeight(200);
		return image;
	}
}