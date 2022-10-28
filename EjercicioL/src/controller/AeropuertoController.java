package controller;

import javafx.fxml.FXML;

import javafx.scene.control.ToggleGroup;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import javafx.scene.control.MenuItem;

import javafx.scene.control.RadioButton;

import javafx.scene.control.TableView;

import javafx.scene.control.Menu;

import javafx.scene.control.TableColumn;

public class AeropuertoController {
	@FXML
	private Menu menuAeropuertos;
	@FXML
	private MenuItem menuAeroAniadir;
	@FXML
	private MenuItem menuAeroEditar;
	@FXML
	private MenuItem menuAeroBorrar;
	@FXML
	private MenuItem menuAeroInfo;
	@FXML
	private Menu menuAviones;
	@FXML
	private MenuItem menuAvionAniadir;
	@FXML
	private MenuItem menuAvionActivar;
	@FXML
	private MenuItem menuAvionBorrar;
	@FXML
	private Menu menuAyuda;
	@FXML
	private RadioButton radPublicos;
	@FXML
	private ToggleGroup group1;
	@FXML
	private RadioButton radPrivados;
	@FXML
	private TextField txtFiltrar;
	@FXML
	private TableView table;
	@FXML
	private TableColumn colID;
	@FXML
	private TableColumn colNombre;
	@FXML
	private TableColumn colPais;
	@FXML
	private TableColumn colCiudad;
	@FXML
	private TableColumn colCalle;
	@FXML
	private TableColumn colNumero;
	@FXML
	private TableColumn colAnio;
	@FXML
	private TableColumn colCapacidad;
	@FXML
	private TableColumn colSocios;

	// Event Listener on MenuItem[#menuAeroAniadir].onAction
	@FXML
	public void aeroAniadir(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on MenuItem[#menuAeroEditar].onAction
	@FXML
	public void aeroEditar(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on MenuItem[#menuAeroBorrar].onAction
	@FXML
	public void aeroBorrar(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on MenuItem[#menuAeroInfo].onAction
	@FXML
	public void aeroInfo(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on MenuItem[#menuAvionAniadir].onAction
	@FXML
	public void avionAniadir(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on MenuItem[#menuAvionActivar].onAction
	@FXML
	public void avionActivar(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on MenuItem[#menuAvionBorrar].onAction
	@FXML
	public void avionBorrar(ActionEvent event) {
		// TODO Autogenerated
	}
}