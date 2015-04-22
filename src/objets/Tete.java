package objets;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import utils.Vecteur;
public class Tete extends ObjetSpatial
{
	public static final Texture TEXTURE_DEFAUT = Texture.SIMONPIERRE;
	
	public Tete(double pMasse, Vecteur pPosition, boolean pStatique,
			Vecteur pVitesse)
	{
		super(pMasse, pPosition, pStatique, pVitesse);
	}
	
	public enum Texture
	{
		EMILE("/res/planeteBleue.png"),
		JEREMIE("/res/planeteJaune.png"),
		JONATHAN("/res/planeteMagenta.png"),
		SIMONPIERRE("/res/planeteOrange.png");
		
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

	public Node getNoeud()
	{
		ImageView image = new ImageView("/res/planeteBleue.png");
		image.setFitWidth(200);
		image.setFitHeight(200);
		image.setTranslateX(-100);
		image.setTranslateY(-100);
		return image;
	}
}