package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import model.Persona;

public class AgregrarPersonaEjerHController {
	@FXML
	private Label lbNombre;
	@FXML
	private Label lbApellidos;
	@FXML
	private Label lbEdad;
	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtApellido;
	@FXML
	private TextField txtEdad;
	@FXML
	private FlowPane flow2;
	@FXML
	private Button btnGuardar;
	@FXML
	private Button btnCancelar;
	
	private Persona p;
	

	// Event Listener on Button[#btnGuardar].onAction
	@FXML
	public void guardar(ActionEvent event) {
		// TODO Autogenerated
		String nombre = txtNombre.getText();
		String apellidos = txtApellido.getText();	
		int edad=0;
		String error="";
		
		if(nombre.isEmpty()) {
			error += "\n El nombre no puede estar vacio";
		}
		if(apellidos.isEmpty()) {
			error += "\n El apellido no puede estar vacio";
		}
		try {
			edad = Integer.parseInt (txtEdad.getText());
			if(edad > 120 || edad < 0) {
				error += "\n Edad fuera de rango";
			}
		} catch (Exception e) {
			error += "\n La edad tiene que ser un entero";
		}
		
		if(error.equals("")) {
			p = new Persona(nombre, apellidos, edad);
			Stage myStage =(Stage) this.btnCancelar.getScene().getWindow();
			myStage.close();
		}
		else {
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
	
	public Persona getPersona () {
		return p;
	}
	
	public void setPersona (Persona persona) {
		p = persona;
		txtNombre.setText(p.getNombre());
		txtApellido.setText(p.getApellido());
		txtEdad.setText(p.getEdad() + "");
		
	}
}
