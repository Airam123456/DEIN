package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.Label;

import javafx.scene.layout.FlowPane;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class EjercicioGController {
	@FXML
	private Label lbFiltrar;
	@FXML
	private TextField txtFiltrar;
	@FXML
	private Button btnImportar;
	@FXML
	private Button btnExportar;
	@FXML
	private TableView table;
	@FXML
	private TableColumn colNombre;
	@FXML
	private TableColumn colApellido;
	@FXML
	private TableColumn colEdad;
	@FXML
	private FlowPane flow1;
	@FXML
	private Button btnAgregar;

}
