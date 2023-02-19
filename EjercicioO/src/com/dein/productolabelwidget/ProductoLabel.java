package com.dein.productolabelwidget;
	
import java.io.IOException;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


public class ProductoLabel extends GridPane {
	@FXML
	protected Label nombreProductoLabel;
	@FXML
	protected Label stockProductoLabel;
	@FXML
	private ImageView imagenProductoImageView;

	public ProductoLabel() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/dein/productolabelwidget/fxml/ProductoLabel.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	public void setStock(double cantidad) {
		String style;
		if (cantidad > 100) {
			style = "verde";
		} else if (cantidad > 20) {
			style = "azul";
		} else {
			style = "rojo";
		}
		stockProductoLabel.setText(cantidad + "");
		this.getStyleClass().clear();
		this.getStyleClass().add(style);
	}

	public void setImage(Image imagen) {
		imagenProductoImageView.setImage(imagen);
	}

	public void setName(String nombre) {
		nombreProductoLabel.setText(nombre + "");
	}
}
