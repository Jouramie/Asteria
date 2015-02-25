package controleur;

import javafx.scene.layout.BorderPane;
import vue.Vue;

public class VueNiveau implements Vue {

	@Override
	public String getFXML() {
		return "/res/niveau.fxml";
	}

	@Override
	public String getCSS() {
		return null;
	}

	@Override
	public void initialiser(BorderPane pane) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dessiner(double dt) {
		// TODO Auto-generated method stub

	}

}
