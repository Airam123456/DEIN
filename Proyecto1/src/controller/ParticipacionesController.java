package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.DeporteDAO;
import dao.DeportistaDAO;
import dao.EquipoDAO;
import dao.ParticipacionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ComboBox;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Deportista;
import model.Equipo;
import model.Evento;
import model.Participacion;

public class ParticipacionesController implements Initializable {
	@FXML
	private TableView<Participacion> tableParticipaciones;
	@FXML
	private TableColumn<Participacion, String> columEdad;

	@FXML
	private TableColumn<Participacion, String> columEquipo;

	@FXML
	private TableColumn<Participacion, String> columMedalla;

	@FXML
	private TableColumn<Participacion, String> columNombre;
	@FXML
	private ListView listDeportista;
	@FXML
	private Button btnAgregar;
	@FXML
	private Button btnEditar;
	@FXML
	private Button btnBorrar;
	@FXML
	private Button btnCancelar;
	@FXML
	private ComboBox<Equipo> cboxEquipo;
	@FXML
	private ComboBox<String> cboxMedalla;
	@FXML
	private TextField txtEdad;
	@FXML
	private TextField txtNombre;

	private int idEvento;
	private Evento e;

	private ObservableList<Deportista> deportistas;
	private DeportistaDAO cargarDeportistas;
	private Deportista d, dAnterior;

	private ObservableList<Participacion> participaciones;
	private ParticipacionDAO cargarParticipaciones;
	private Participacion p;

	private ObservableList<Equipo> equipos;
	private EquipoDAO cargarEquipos;
	private Equipo eq;

	private ObservableList<String> medallas = FXCollections.observableArrayList();
	private String med;
	private int edad;

	// Event Listener on TableView[#tableParticipaciones].onMouseClicked
	@FXML
	void selectParticipaciones(MouseEvent event) {
		btnBorrar.setDisable(false);
		btnEditar.setDisable(false);

		p = (Participacion) tableParticipaciones.getSelectionModel().getSelectedItem();

		txtNombre.setText(p.getDeportista().getNombre());
		cboxEquipo.getSelectionModel().select(p.getEquipo());
		cboxMedalla.getSelectionModel().select(p.getMedalla());
		txtEdad.setText(Integer.toString(p.getEdad()));
		listDeportista.getSelectionModel().select(p.getDeportista());
		dAnterior = p.getDeportista();
	}

	// Event Listener on ListView[#listDeportista].onMouseClicked
	@FXML
	public void selectDeportista(MouseEvent event) {
		// TODO Autogenerated
		d = (Deportista) listDeportista.getSelectionModel().getSelectedItem();
		txtNombre.setText(d.getNombre());
	}

	// Event Listener on Button[#btnAgregar].onAction
	@FXML
	public void agregar(ActionEvent event) {
		// TODO Autogenerated

		String error = "";

		try {
			edad = Integer.parseInt(txtEdad.getText());
			if (edad > 100 || edad < 18) {
				error += "\n La Edad tiene que estar entre 18 y 100 a??os";
			}
		} catch (Exception e) {
			error += "\n La Edad tiene que ser un entero";
		}

		if (d == null) {
			error += "\n Elija Deportista";
		}

		if (eq == null) {
			error += "\n Escoja un Equipo";
		}

		if (med == null) {
			error += "\n Elija la Medalla";
		}
		p = new Participacion(d, e, eq, edad, med);

		if (error.equals("")) {
			try {
				cargarParticipaciones = new ParticipacionDAO();
				if (cargarParticipaciones.existeParticipacion(p) == true)
					error += "\n Participacion ya existente";

			} catch (SQLException e) {
				// TODO Auto-generated catch block

			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText(error);
			alert.showAndWait();
		}

		if (error.equals("")) {

			try {
				cargarParticipaciones.insertParticipacion(p);

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.initOwner(this.btnAgregar.getScene().getWindow());
				alert.setHeaderText(null);
				alert.setTitle("Info");
				alert.setContentText("Paricipacion agregada correctamente");
				alert.showAndWait();

				cargarTabla(e);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("Error");
				alert.setContentText("Error en la base de Datos");
				alert.showAndWait();
				e1.printStackTrace();
			}

		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText(error);
			alert.showAndWait();
		}

	}

	// Event Listener on Button[#btnEditar].onAction
	@FXML
	public void editar(ActionEvent event) {
		// TODO Autogenerated

		String error = "";
		d = dAnterior;

		try {
			edad = Integer.parseInt(txtEdad.getText());
			if (edad > 100 || edad < 18) {
				error += "\n La Edad tiene que estar entre 18 y 100 a??os";
			}
		} catch (Exception e) {
			error += "\n La Edad tiene que ser un entero";
		}

		if (d == null) {
			error += "\n Elija Deportista";
		}

		if (eq == null) {
			error += "\n Escoja un Equipo";
		}

		if (med == null || med == "0") {
			error += "\n Elija la Medalla";
		}

		if (error.equals("")) {

			try {
				cargarParticipaciones = new ParticipacionDAO();
				p = new Participacion(d, e, eq, edad, med);
				cargarParticipaciones.updateParticipacion(p);

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.initOwner(this.btnAgregar.getScene().getWindow());
				alert.setHeaderText(null);
				alert.setTitle("Info");
				alert.setContentText("Paricipacion modificada correctamente");
				alert.showAndWait();

				cargarTabla(e);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("Error");
				alert.setContentText("Elije Medalla");
				alert.showAndWait();
			}
			btnBorrar.setDisable(true);
			btnEditar.setDisable(true);

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

		d = dAnterior;
		p = new Participacion(d, e, eq, edad, med);

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.initOwner(this.btnBorrar.getScene().getWindow());
		alert.setHeaderText(null);
		alert.setTitle("Borrar Participacion");
		alert.setContentText("Seguro que desea BORRAR esta Participacion?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			try {
				cargarParticipaciones = new ParticipacionDAO();
				if (cargarParticipaciones.deleteParticipacion(p)) {

					alert = new Alert(Alert.AlertType.INFORMATION);
					alert.initOwner(this.btnEditar.getScene().getWindow());
					alert.setHeaderText(null);
					alert.setTitle("Info");
					alert.setContentText("Participacion borrada correctamente");
					alert.showAndWait();
					txtEdad.clear();
					cboxEquipo.getSelectionModel().clearSelection();
					cboxMedalla.getSelectionModel().clearSelection();
					
					cargarTabla(e);

				} else {
					alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle("Error");
					alert.setContentText("No se puede borrar, existen dependencias");
					alert.showAndWait();
				}
				btnBorrar.setDisable(true);
				btnEditar.setDisable(true);

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

	// Event Listener on ComboBox[#cboxEquipo].onAction
	@FXML
	public void seleccionarEquipo(ActionEvent event) {
		// TODO Autogenerated
		eq = (Equipo) cboxEquipo.getSelectionModel().getSelectedItem();
	}

	// Event Listener on ComboBox[#cboxMedalla].onAction
	@FXML
	public void seleccionarMedalla(ActionEvent event) {
		// TODO Autogenerated
		med = cboxMedalla.getSelectionModel().getSelectedItem();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		medallas.addAll("Gold", "Silver", "Bronze", "Sin medalla");
		cboxMedalla.setItems(medallas);

		deportistas = FXCollections.observableArrayList();
		try {
			cargarDeportistas = new DeportistaDAO();
			deportistas.addAll(cargarDeportistas.selectDeportista());
			listDeportista.setItems(deportistas);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();

			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Error al cargar los deportistas");
			alert.showAndWait();
		}

		equipos = FXCollections.observableArrayList();
		try {
			cargarEquipos = new EquipoDAO();
			equipos.addAll(cargarEquipos.selectEquipos());
			cboxEquipo.setItems(equipos);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Error al cargar los equiposs");
			alert.showAndWait();
		}

		btnBorrar.setDisable(true);
		btnEditar.setDisable(true);
	}

	public void setEvento(Evento evento) {
		e = evento;
		idEvento = e.getId_evento();
		cargarTabla(e);

	}

	public void cargarTabla(Evento e) {
		participaciones = FXCollections.observableArrayList();
		try {
			cargarParticipaciones = new ParticipacionDAO();
			ArrayList<Participacion> participacionDAO;
			participacionDAO = cargarParticipaciones.selectParticipacionesPorEvento(e);
			participacionDAO.forEach(part -> participaciones.add(part));

			tableParticipaciones.setItems(participaciones);

			columNombre.setCellValueFactory(new PropertyValueFactory<Participacion, String>("nombre"));
			columNombre.prefWidthProperty().bind(tableParticipaciones.widthProperty().multiply(0.5));

			columEquipo.setCellValueFactory(new PropertyValueFactory<Participacion, String>("iniciales"));
			columEquipo.prefWidthProperty().bind(tableParticipaciones.widthProperty().multiply(0.20));

			columEdad.setCellValueFactory(new PropertyValueFactory<Participacion, String>("edad"));
			columEdad.prefWidthProperty().bind(tableParticipaciones.widthProperty().multiply(0.10));
			columEdad.setStyle( "-fx-alignment: CENTER-RIGHT;");

			columMedalla.setCellValueFactory(new PropertyValueFactory<Participacion, String>("medalla"));
			columMedalla.prefWidthProperty().bind(tableParticipaciones.widthProperty().multiply(0.20));

		} catch (Exception er) {
			// TODO: handle exception
			er.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Error al cargar las participaciones");
			alert.showAndWait();
		}

	}

}
