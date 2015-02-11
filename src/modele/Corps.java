package modele;

import javafx.beans.property.DoubleProperty;
import utils.Vecteur;

public interface Corps
{	
	double getMasse();
	void setMasse(double pMasse);
	
	double getPositionX();
	double getPositionY();
	void setPositionX(double pPositionX);
	void setPositionY(double pPositionX);
	DoubleProperty getPositionXProperty();
	DoubleProperty getPositionYProperty();
	Vecteur getPosition();
	void setPosition(Vecteur pPosition);
	
	boolean isStatique();
	void setStatique(boolean pStatique);
	Vecteur getVitesse();
	void setVitesse(Vecteur pVitesse);
	
	Vecteur getForceExt();
}