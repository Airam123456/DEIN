package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

import javafx.scene.control.RadioButton;

public class AniadirAvionController {
	@FXML
	private TextField txtModelo;
	@FXML
	private TextField txtAsientos;
	@FXML
	private TextField txtVelMax;
	@FXML
	private RadioButton rbtnActivado;
	@FXML
	private RadioButton rbtnDesactivado;
	@FXML
	private Button btnGuardar;
	@FXML
	private Button btnCancelar;
	@FXML
	private ComboBox cmbAeropuerto;

	// Event Listener on Button[#btnGuardar].onAction
	@FXML
	public void guardar(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnCancelar].onAction
	@FXML
	public void cancelar(ActionEvent event) {
		// TODO Autogenerated
	}
}
