package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Deporte;
import model.Deportista;

import java.sql.SQLException;

import dao.DeporteDAO;
import javafx.event.ActionEvent;

public class AniadirDeporteController {
	@FXML
	private TextField txtNombre;
	@FXML
	private Button btnAceptar;
	@FXML
	private Button btnCancelar;

	private Deporte de;
	private DeporteDAO existe;

	// Event Listener on Button[#btnAceptar].onAction
	@FXML
	public void aceptar(ActionEvent event) {
		// TODO Autogenerated

		int id = 0;
		String nombre = txtNombre.getText();
		String error = "";

		if (nombre.isEmpty()) {
			error += "\n El campo Nombre no puede estar vacio";
		}
		
		de = new Deporte(id, nombre);
		
		try {
			existe = new DeporteDAO();
			if(existe.existeDeporte(de) == true)
				error += "\n Deporte ya existente";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


		if (error.equals("")) {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initOwner(this.btnAceptar.getScene().getWindow());
			alert.setHeaderText(null);
			alert.setTitle("Info");
			alert.setContentText("Deporte agregado correctamente");
			alert.showAndWait();

			Stage myStage = (Stage) this.btnCancelar.getScene().getWindow();
			myStage.close();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText(error);
			alert.showAndWait();
		}

	}

	// Event Listener on Button[#btnCancelar].onAction
	@FXML
	public void cancelar(ActionEvent event) {
		// TODO Autogenerated
		Stage myStage =(Stage) this.btnCancelar.getScene().getWindow();
		myStage.close();
	}
	
	public Deporte getDeporte() {
		return de;
	}
}
