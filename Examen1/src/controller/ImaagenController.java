package controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ImaagenController {
	@FXML
	private ImageView imagenGrande;

	// Event Listener on ImageView[#imagenGrande].onMouseClicked
	@FXML
	public void cerrar(MouseEvent event) {
		// TODO Autogenerated
//		Stage myStage = (Stage) this.imagenGrande.getScene().getWindow();
//		myStage.close();
	}
	
	public void setImagen(ImageView imagen) {
		imagenGrande = imagen;
		
		
	}
}
