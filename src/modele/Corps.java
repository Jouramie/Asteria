package modele;

import utils.Vecteur;

public interface Corps
{	
	double getMasse();
	void setMasse(double masse);
	Vecteur getForceExt();
	
	void setPosition();
	Vecteur getPosition();
	
	boolean isStatique();
	void setVitesse();
	Vecteur getVitesse();
}