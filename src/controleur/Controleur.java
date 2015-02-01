package controleur;

public interface Controleur
{
	void initialiser();
	void update(double dt);
	
	void ajouterEcouteurs();
	void supprimerEcouteur();
}
