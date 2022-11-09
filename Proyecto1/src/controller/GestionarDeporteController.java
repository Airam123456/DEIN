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
import dao.DeportistaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ListView;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Deporte;
import model.Deportista;

public class GestionarDeporteController implements Initializable{
	@FXML
	private TextField txtDeporte;
	@FXML
	private Button btnEditar;
	@FXML
	private Button btnBorrar;
	@FXML
	private Button btnCancelar;
	@FXML
	private ListView listDeportes;
	
	private ObservableList<Deporte> deportes;
	private DeporteDAO cargarDeportes;
	private Deporte de;
	int id;

	// Event Listener on Button[#btnEditar].onAction
	@FXML
	public void editar(ActionEvent event) {
		// TODO Autogenerated
		
		String deporte = txtDeporte.getText();
		String error = "";
		
		if (deporte.isEmpty()) {
			error += "\n El campo Deporte no puede estar vacio";
		}
		
		de = new Deporte(id, deporte);
		
		try {
			cargarDeportes = new DeporteDAO();
			if (cargarDeportes.existeDeporte(de) == true)
				error += "\n Deporte ya existente";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (error.equals("")) {

			cargarDeportes.updateDeporte(de);

			deportes = FXCollections.observableArrayList();

			try {
				cargarDeportes = new DeporteDAO();
				deportes.addAll(cargarDeportes.selectDeportes());
				listDeportes.setItems(deportes);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initOwner(this.btnEditar.getScene().getWindow());
			alert.setHeaderText(null);
			alert.setTitle("Info");
			alert.setContentText("Deporte modificado correctamente");
			alert.showAndWait();

		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText(error);
			alert.showAndWait();
		}
		
		
	}
	// Event Listener on Button[#btnBorrar].onAction
	@FXML
	public void borrar(ActionEvent event) {
		// TODO Autogenerated
		
		String deporte = txtDeporte.getText();
		de = new Deporte(id, deporte);
		
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.initOwner(this.btnBorrar.getScene().getWindow());
		alert.setHeaderText(null);
		alert.setTitle("Borrar Deporte");
		alert.setContentText("Seguro que desea BORRAR este Deporte?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			try {
				cargarDeportes.deleteDeporte(de);

				deportes = FXCollections.observableArrayList();

				alert = new Alert(Alert.AlertType.INFORMATION);
				alert.initOwner(this.btnEditar.getScene().getWindow());
				alert.setHeaderText(null);
				alert.setTitle("Info");
				alert.setContentText("Deporte borrado correctamente");
				alert.showAndWait();
				txtDeporte.clear();

				try {
					cargarDeportes = new DeporteDAO();
					deportes.addAll(cargarDeportes.selectDeportes());
					listDeportes.setItems(deportes);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
		
	}
	// Event Listener on Button[#btnCancelar].onAction
	@FXML
	public void cancelar(ActionEvent event) {
		// TODO Autogenerated
		Stage myStage = (Stage) this.btnCancelar.getScene().getWindow();
		myStage.close();
	}
	// Event Listener on ListView[#listDeportistas].onMouseClicked
	@FXML
	public void seleccionar(MouseEvent event) {
		// TODO Autogenerated
		
		de = (Deporte) listDeportes.getSelectionModel().getSelectedItem();

		id = de.getId();
		String deporte = de.getNombre();
		txtDeporte.setText(deporte); 
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		deportes = FXCollections.observableArrayList();

		try {
			cargarDeportes = new DeporteDAO();
			deportes.addAll(cargarDeportes.selectDeportes());
			listDeportes.setItems(deportes);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
