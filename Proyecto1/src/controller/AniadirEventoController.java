package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Deporte;
import model.Evento;
import model.Olimpiada;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.DeporteDAO;
import dao.EventoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

public class AniadirEventoController implements Initializable {
	@FXML
	private TextField txtNombre;
	@FXML
	private Button btnAceptar;
	@FXML
	private Button btnCancelar;
	@FXML
	private ComboBox cboxDeporte;

	private Evento e;
	private EventoDAO cargarEvento;
	private Deporte d;
	private Olimpiada o;
	private DeporteDAO cargarDeportes;

	private ObservableList<Deporte> deportes;
	int idDeporte = 0;
	int idEvento = 0;
	private Boolean edit = false;

	// Event Listener on Button[#btnAceptar].onAction
	@FXML
	public void aceptar(ActionEvent event) {
		// TODO Autogenerated

		String nombre = txtNombre.getText();
		String error = "";

		if (nombre.isEmpty()) {
			error += "\n El campo Nombre no puede estar vacio";
		}

		if (d == null) {
			error += "\n Escoja un Deporte";
		}

		
		
		if (error.equals("")) {
			e = new Evento(idEvento, nombre, o, d);
			
			try {
				cargarEvento = new EventoDAO();
				if (edit) {
					cargarEvento.updateEvento(e);
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.initOwner(this.btnAceptar.getScene().getWindow());
					alert.setHeaderText(null);
					alert.setTitle("Info");
					alert.setContentText("Evento Editado correctamente");
					alert.showAndWait();

					Stage myStage = (Stage) this.btnCancelar.getScene().getWindow();
					myStage.close();

				} else {

					cargarEvento.insertEvento(e);
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.initOwner(this.btnAceptar.getScene().getWindow());
					alert.setHeaderText(null);
					alert.setTitle("Info");
					alert.setContentText("Evento agregado correctamente");
					alert.showAndWait();

					Stage myStage = (Stage) this.btnCancelar.getScene().getWindow();
					myStage.close();
				}

			} catch (Exception e) {
				// TODO: handle exception
//				System.out.println("Erorr");
				System.out.println(e.getMessage());
				e.printStackTrace();
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
		Stage myStage = (Stage) this.btnCancelar.getScene().getWindow();
		myStage.close();
	}

	// Event Listener on ComboBox[#cboxDeporte].onAction
	@FXML
	public void seleccionarDeporte(ActionEvent event) {
		// TODO Autogenerated

		try {
			cargarDeportes = new DeporteDAO();
			d = (Deporte) cboxDeporte.getSelectionModel().getSelectedItem();
			idDeporte = d.getId();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		deportes = FXCollections.observableArrayList();

		try {
			cargarDeportes = new DeporteDAO();
			deportes.addAll(cargarDeportes.selectDeportes());
			cboxDeporte.setItems(deportes);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Evento getEvento() {
		return e;
	}

	public void setOlimpiada(Olimpiada olimpiada) {
		o = olimpiada;
	}

	public void setEvento(Evento evento, Boolean respuesta) {
		edit = respuesta;
		e = evento;
		idEvento = e.getId_evento();
		txtNombre.setText(e.getNombre());
		cboxDeporte.getSelectionModel().select(e.getDeporte());
	}

}
