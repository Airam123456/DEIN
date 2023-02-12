package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import config.Propiedades;
import dao.AlumnoDAO;
import dao.HistoricoDAO;
import dao.LibroDAO;
import dao.PrestamoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Alumno;
import model.Historico;
import model.Prestamo;

public class VentanaPrestamosFXMLController implements Initializable{
	@FXML
	private ListView <Prestamo> lstPrestamos;
	@FXML
	private MenuItem mnuCrear;
	@FXML
	private MenuItem mnuDevolver;
	@FXML
	private ComboBox <Alumno> cmbBoxAlumnos;
	@FXML
	private Button btnCrear;
	@FXML
	private Button btnDevolver;
	
	private ObservableList<Prestamo> dataPrestamo;
	private ObservableList<Alumno> dataAlumno;
	
	private PrestamoDAO cPrestamo;
	private AlumnoDAO cAlumno;
	private HistoricoDAO cHistorico;
	private LibroDAO cLibro;
	
	private int iSeleccionado = -1;
	private Prestamo p;

	// Event Listener on ListView[#lstPrestamos].onMouseClicked
	@FXML
	public void seleccionarFila(MouseEvent event) {
		if (lstPrestamos.getSelectionModel().getSelectedItem()!=null) {
			mnuDevolver.setDisable(false);
			btnDevolver.setDisable(false);
			
			iSeleccionado= lstPrestamos.getSelectionModel().getSelectedIndex();
			p = lstPrestamos.getSelectionModel().getSelectedItem();
		}	
	}
	// Event Listener on ComboBox[#cmbBoxAlumnos].onAction
	@FXML
	public void filtrarPorAlumno(ActionEvent event) {
		Alumno a = cmbBoxAlumnos.getSelectionModel().getSelectedItem();
		
		ArrayList<Prestamo> lstPrestamo = cPrestamo.selectPrestamosPorAlumno(a);
		dataPrestamo.clear();
		dataPrestamo.addAll(lstPrestamo);
		mnuDevolver.setDisable(true);
		btnDevolver.setDisable(true);
	}
	
	
	// Event Listener on MenuItem[#mnuCrear].onAction
	@FXML
	public void crearPrestamoMnu(ActionEvent event) {
		Locale locale = new Locale (Propiedades.getValor("language"),Propiedades.getValor("region"));
		ResourceBundle bundle = ResourceBundle.getBundle("config/messages", locale);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GestionPrestamosFXML.fxml"), bundle);
		Parent root;
		try {
			root = loader.load();
			GestionPrestamosFXMLController controller = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			Stage myStage =(Stage) this.btnCrear.getScene().getWindow();
			stage.initOwner(myStage);
			stage.setScene(scene);
			stage.setTitle("Realizar Prestamo");
			stage.showAndWait();
			
			p = controller.getPrestamo();
			if (p!= null) {
				dataPrestamo.add(p);
			}
			

		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// Event Listener on MenuItem[#mnuDevolver].onAction
	@FXML
	public void devolverPrestamoMnu(ActionEvent event) {
		p = lstPrestamos.getSelectionModel().getSelectedItem();
		
		
		try {
			List<String> choices = new ArrayList<>();
			choices.add("Nuevo");
			choices.add("Usado nuevo");
			choices.add("Usado seminuevo");
			choices.add("Usado estropeado");
			choices.add("Restaurado");
			ChoiceDialog<String> dialog = new ChoiceDialog<>(p.getLib().getEstado(), choices);
			dialog.setTitle("Modificar estado");
			dialog.setContentText("Elige el estado del libro devuelto:");
			
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				cLibro.cambiarEstado(p.getLib().getCodigo(), result.get());
			}
			
			cPrestamo.deletePrestamo(p);
			
			Historico h = new Historico(p.getId_prestamo(), p.getAlum().getDni(), p.getLib().getCodigo(), p.getFecha_prestamo(), LocalDate.now());
			cHistorico.insertHistorico(h);
			dataPrestamo.remove(iSeleccionado);
		} catch (SQLException e) {
			Alert alert= new Alert(Alert.AlertType.ERROR);
			alert.initOwner(this.btnCrear.getScene().getWindow());
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("No se ha podido devolver el libro");
			alert.showAndWait();
		}
		
		btnDevolver.setDisable(true);	
	}
	// Event Listener on Button[#btnCrear].onAction
	@FXML
	public void crearPrestamoBtn(ActionEvent event) {
		Locale locale = new Locale (Propiedades.getValor("language"),Propiedades.getValor("region"));
		ResourceBundle bundle = ResourceBundle.getBundle("config/messages", locale);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GestionPrestamosFXML.fxml"), bundle);
		Parent root;
		try {
			root = loader.load();
			GestionPrestamosFXMLController controller = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			Stage myStage =(Stage) this.btnCrear.getScene().getWindow();
			stage.initOwner(myStage);
			stage.setScene(scene);
			stage.setTitle("Realizar Prestamo");
			stage.showAndWait();
			
			p = controller.getPrestamo();
			if (p!= null) {
				dataPrestamo.add(p);
			}
			

		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// Event Listener on Button[#btnDevolver].onAction
	@FXML
	public void devolverPrestamoBtn(ActionEvent event) {
		p = lstPrestamos.getSelectionModel().getSelectedItem();
		
		
		try {
			List<String> choices = new ArrayList<>();
			choices.add("Nuevo");
			choices.add("Usado nuevo");
			choices.add("Usado seminuevo");
			choices.add("Usado estropeado");
			choices.add("Restaurado");
			ChoiceDialog<String> dialog = new ChoiceDialog<>(p.getLib().getEstado(), choices);
			dialog.setTitle("Modificar estado");
			dialog.setContentText("Elige el estado del libro devuelto:");
			
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				cLibro.cambiarEstado(p.getLib().getCodigo(), result.get());
			}
			
			cPrestamo.deletePrestamo(p);
			
			Historico h = new Historico(p.getId_prestamo(), p.getAlum().getDni(), p.getLib().getCodigo(), p.getFecha_prestamo(), LocalDate.now());
			cHistorico.insertHistorico(h);
			dataPrestamo.remove(iSeleccionado);
		} catch (SQLException e) {
			Alert alert= new Alert(Alert.AlertType.ERROR);
			alert.initOwner(this.btnCrear.getScene().getWindow());
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("No se ha podido devolver el libro");
			alert.showAndWait();
		}
		
		btnDevolver.setDisable(true);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cPrestamo = new PrestamoDAO();
		cAlumno =  new AlumnoDAO();
		cHistorico = new HistoricoDAO();
		cLibro = new LibroDAO ();
		
		cargarPrestamos();
		cargarAlumnos();
	}
	
	private void cargarPrestamos () {
		btnDevolver.setDisable(true);
		
		dataPrestamo= FXCollections.observableArrayList();
		lstPrestamos.setItems(dataPrestamo);
		ArrayList <Prestamo> lstPrestamos = cPrestamo.selectPrestamos();
		
		dataPrestamo.clear();
		dataPrestamo.addAll(lstPrestamos);
		
	}
	
	private void cargarAlumnos () {
		dataAlumno = FXCollections.observableArrayList();
		cmbBoxAlumnos.setItems(dataAlumno);
		ArrayList<Alumno> lstAlumnos = cAlumno.selectAlumnos();
		
		dataAlumno.clear();
		dataAlumno.addAll(lstAlumnos);
	}
	
	
}
