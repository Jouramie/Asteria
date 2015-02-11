package modele;

import javafx.beans.property.DoubleProperty;
import utils.Vecteur;

public interface Corps
{	
	double getMasse();
	void setMasse(double pMasse);
	Vecteur getForceExt();
	
	void setPosition(Vecteur pPosition);
	Vecteur getPosition();
	double getPositionX();
	double getPositionY();
	DoubleProperty getPositionXProperty();
	DoubleProperty getPositionYProperty();
	
	boolean isStatique();
	void setStatique(boolean pStatique);
	Vecteur getVitesse();
	void setVitesse(Vecteur pVitesse);
}