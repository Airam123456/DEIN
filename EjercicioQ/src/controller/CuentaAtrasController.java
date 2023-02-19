package controller;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class CuentaAtrasController implements Initializable{
	@FXML
	private Label minutoDecena;
	@FXML
	private Label minutoUnidad;
	@FXML
	private Label segundoDecena;
	@FXML
	private Label segundoUnidad;
	
	private int tiempo;
	
	
	public void atras (int minutos)  {
		int segundos = 0;
		
		while (!(minutos == 0 && segundos == 0)) {
			
			
			 if (minutos < 10) {
	             System.out.print("0" + minutos);
	             minutoDecena.setText("0");
	             minutoUnidad.setText(String.valueOf(minutos));
	         } else {
	        	 System.out.print(minutos);
	        	 minutoDecena.setText(String.valueOf(minutos/10));
	        	 minutoUnidad.setText(String.valueOf(minutos%10));
	         }
			 
			 if (segundos < 10) {
	             System.out.print("0" + segundos);
				 segundoDecena.setText("0");
				 segundoUnidad.setText(String.valueOf(segundos));
	         } else {
	             System.out.print(segundos);
	        	 segundoDecena.setText(String.valueOf(segundos/10));
	        	 segundoUnidad.setText(String.valueOf(segundos%10));
	         }
			 
			 if(segundos == 0) {
				 if (minutos != 0) {
	                    minutos--;
	                    segundos = 59;
	                }
			 }else {
				 segundos--;
			 }
			 
			 System.out.println();
			 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

//		minutoDecena.setText("0");
//		minutoUnidad.setText("0");
//		segundoDecena.setText("0");
//		segundoUnidad.setText("0");
		
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
	

}
