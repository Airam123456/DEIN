package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Deporte;
import model.Equipo;

import java.sql.SQLException;

import dao.DeporteDAO;
import dao.EquipoDAO;
import javafx.event.ActionEvent;

public class AniadirEquipoController {
	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtIniciales;
	@FXML
	private Button btnAceptar;
	@FXML
	private Button btnCancelar;
	
	private Equipo eq;
	private EquipoDAO existe;

	// Event Listener on Button[#btnAceptar].onAction
	@FXML
	public void aceptar(ActionEvent event) {
		// TODO Autogenerated
		
		int id = 0;
		String nombre = txtNombre.getText();
		String iniciales = txtIniciales.getText();
		String error = "";
		
		if (nombre.isEmpty()) {
			error += "\n El campo Nombre no puede estar vacio";
		}
		
		if(iniciales.isEmpty()) {
			error += "\n El campo Iniciales no puede estar vacio";
		}
		
		eq = new Equipo(id, nombre, iniciales.toUpperCase());
		
		try {
			existe = new EquipoDAO();
			if(existe.existeEquipo(eq) == true)
				error += "\n Equipo ya existente";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Error en la base de Datos");
			alert.showAndWait();
			e.printStackTrace();
		}
		
		if (error.equals("")) {
			
			try {
				existe.insertEquipo(eq);

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.initOwner(this.btnAceptar.getScene().getWindow());
				alert.setHeaderText(null);
				alert.setTitle("Info");
				alert.setContentText("Equipo agregado correctamente");
				alert.showAndWait();

				Stage myStage = (Stage) this.btnCancelar.getScene().getWindow();
				myStage.close();
			} catch (Exception e) {
				// TODO: handle exception
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("Error");
				alert.setContentText("Error en la base de Datos");
				alert.showAndWait();
			}
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
	
	public Equipo getEquipo() {
		return eq;
	}
}
