package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ResourceBundle;

import com.dein.productolabelwidget.ProductoLabel;

public class EjercicioPController implements Initializable{
	@FXML
	private ProductoLabel producto1;
	@FXML
	private ProductoLabel producto2;
	@FXML
	private ProductoLabel producto3;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		producto1.setStock(10);
		producto1.setName("Pan");
		producto1.setImage(new Image(getClass().getResource("/images/pan.png").toString()));
		
		
		producto2.setStock(200);
		producto2.setName("Manzana");
		producto2.setImage(new Image(getClass().getResource("/images/manzana.png").toString()));
		
		
		producto3.setStock(50);
		producto3.setName("Leche");
		producto3.setImage(new Image(getClass().getResource("/images/leche.png").toString()));
	}
	
}
