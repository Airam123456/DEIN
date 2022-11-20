package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.DeporteDAO;
import dao.EquipoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ListView;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Equipo;

public class GestionarEquipoController implements Initializable {
	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtIniciales;
	@FXML
	private Button btnEditar;
	@FXML
	private Button btnBorrar;
	@FXML
	private Button btnCancelar;
	@FXML
	private ListView listEquipos;

	private ObservableList<Equipo> equipos;
	private EquipoDAO cargarEquipo;
	private Equipo eq;
	int id;

	// Event Listener on Button[#btnEditar].onAction
	@FXML
	public void editar(ActionEvent event) {
		// TODO Autogenerated

		String nombre = txtNombre.getText();
		String iniciales = txtIniciales.getText();
		String error = "";

		if (nombre.isEmpty()) {
			error += "\n El campo Nombre no puede estar vacio";
		}

		if (iniciales.isEmpty()) {
			error += "\n El campo Iniciales no puede estar vacio";
		}
		
		if(iniciales.length()>3) {
			error += "\n El campo Iniciales no puede tener mas de 3 caracteres";
		}

		eq = new Equipo(id, nombre, iniciales.toUpperCase());

		try {
			cargarEquipo = new EquipoDAO();
			if (cargarEquipo.existeEquipo(eq) == true)
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
			cargarEquipo.updateEquipo(eq);
			equipos = FXCollections.observableArrayList();

			try {
				cargarEquipo = new EquipoDAO();
				equipos.addAll(cargarEquipo.selectEquipos());
				listEquipos.setItems(equipos);

			} catch (Exception e) {
				// TODO: handle exception
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("Error");
				alert.setContentText("Error en la base de Datos");
				alert.showAndWait();
				e.printStackTrace();
			}
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initOwner(this.btnEditar.getScene().getWindow());
			alert.setHeaderText(null);
			alert.setTitle("Info");
			alert.setContentText("Equipo modificado correctamente");
			alert.showAndWait();

		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText(error);
			alert.showAndWait();
		}
		btnEditar.setDisable(true);
		btnBorrar.setDisable(true);

	}

	// Event Listener on Button[#btnBorrar].onAction
	@FXML
	public void borrar(ActionEvent event) {
		// TODO Autogenerated

		eq = new Equipo(id);

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.initOwner(this.btnBorrar.getScene().getWindow());
		alert.setHeaderText(null);
		alert.setTitle("Borrar Equipo");
		alert.setContentText("Seguro que desea BORRAR este Equipo?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			try {

				if (cargarEquipo.deleteEquipo(eq)) {
					equipos = FXCollections.observableArrayList();

					alert = new Alert(Alert.AlertType.INFORMATION);
					alert.initOwner(this.btnEditar.getScene().getWindow());
					alert.setHeaderText(null);
					alert.setTitle("Info");
					alert.setContentText("Equipo borrado correctamente");
					alert.showAndWait();
					txtNombre.clear();
					txtIniciales.clear();

					try {
						cargarEquipo = new EquipoDAO();
						equipos.addAll(cargarEquipo.selectEquipos());
						listEquipos.setItems(equipos);

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						alert = new Alert(Alert.AlertType.ERROR);
						alert.setHeaderText(null);
						alert.setTitle("Error");
						alert.setContentText("Error en la base de Datos");
						alert.showAndWait();
						e.printStackTrace();
					}
				} else {
					alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle("Error");
					alert.setContentText("No se puede borrar, existen dependencias");
					alert.showAndWait();
				}

			} catch (Exception e) {
				// TODO: handle exception
				alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("Error");
				alert.setContentText("No se puede borrar, existen dependencias");
				alert.showAndWait();
			}
		}
		btnEditar.setDisable(true);
		btnBorrar.setDisable(true);

	}

	// Event Listener on Button[#btnCancelar].onAction
	@FXML
	public void cancelar(ActionEvent event) {
		// TODO Autogenerated
		Stage myStage = (Stage) this.btnCancelar.getScene().getWindow();
		myStage.close();
	}

	// Event Listener on ListView[#listEquipos].onMouseClicked
	@FXML
	public void seleccionar(MouseEvent event) {
		// TODO Autogenerated

		eq = (Equipo) listEquipos.getSelectionModel().getSelectedItem();
		id = eq.getId();

		String nombre = eq.getNombre();
		String iniciales = eq.getIniciales();

		txtNombre.setText(nombre);
		txtIniciales.setText(iniciales);
		
		btnEditar.setDisable(false);
		btnBorrar.setDisable(false);


	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		equipos = FXCollections.observableArrayList();

		try {
			cargarEquipo = new EquipoDAO();
			equipos.addAll(cargarEquipo.selectEquipos());
			listEquipos.setItems(equipos);

		} catch (Exception e) {
			// TODO: handle exception
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Error en la base de Datos");
			alert.showAndWait();
			e.printStackTrace();
		}
		btnEditar.setDisable(true);
		btnBorrar.setDisable(true);

	}
}
