package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Alumno;
import model.Historico;
import model.Libro;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.AlumnoDAO;
import dao.HistoricoDAO;
import dao.LibroDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class VentanaHistoricoFXMLController implements Initializable{
	@FXML
	private TextField txtFiltro;
	@FXML
	private ComboBox <Libro> cmbBoxLibro;
	@FXML
	private ComboBox <Alumno> cmbBoxAlumno;
	@FXML
	private TableView <Historico> tblHistorico;
	@FXML
	private TableColumn <Historico, Integer> colId;
	@FXML
	private TableColumn <Historico, String> colDni;
	@FXML
	private TableColumn<Historico, Integer> colLibro;
	@FXML
	private TableColumn <Historico, LocalDate> colFechaPrestamo;
	@FXML
	private TableColumn <Historico, LocalDate> colFechaDev;
	
	private ObservableList<Historico> dataHistorico;
	private HistoricoDAO cHistorico;
	
	private ObservableList <Libro> dataLibro;
	private LibroDAO cLibro;
	
	private ObservableList<Alumno> dataAlumno;
	private AlumnoDAO cAlumno;

	// Event Listener on ComboBox[#cmbBoxLibro].onAction
	@FXML
	public void filtrarLibro(ActionEvent event) {
		Libro l = cmbBoxLibro.getSelectionModel().getSelectedItem();
		
		dataHistorico.clear();
		dataHistorico.addAll(cHistorico.selectHistoricosPorLibro(l));
	}
	// Event Listener on ComboBox[#cmbBoxAlumno].onAction
	@FXML
	public void filtrarAlumno(ActionEvent event) {
		Alumno a = cmbBoxAlumno.getSelectionModel().getSelectedItem();
		dataHistorico.clear();
		dataHistorico.addAll(cHistorico.selectHistoricosPorAlumno(a));
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		cargarLibros();
		cargarAlumnos();
		cargarHistorico();
		
		filtro();
	}
	
	private void filtro() {
		//Filtro
		FilteredList<Historico> filteredData = new FilteredList<>(dataHistorico, p -> true);
		
		
		txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(hist -> {
				
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if ((hist.getId_prestamo()+"").toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (hist.getDni_alumno().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if ((hist.getCodigo_libro() + "").toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (hist.getFecha_prestamo().toString().contains(lowerCaseFilter)) {
					return true;
				}else if (hist.getFecha_devolucion().toString().contains(lowerCaseFilter)) {
					return true;
				}
				return false; 
			});
		});
		

		SortedList<Historico> sortedData = new SortedList<>(filteredData);
		

		sortedData.comparatorProperty().bind(tblHistorico.comparatorProperty());
		

		tblHistorico.setItems(sortedData);
	}
	
	private void cargarLibros () {
		cLibro = new LibroDAO();
		dataLibro = FXCollections.observableArrayList();
		cmbBoxLibro.setItems(dataLibro);
		ArrayList <Libro> lstLibros = cLibro.selectLibrosDisponibles();
		
		dataLibro.clear();
		dataLibro.addAll(lstLibros);
	}
	
	private void cargarAlumnos() {
		cAlumno = new AlumnoDAO();
		
		dataAlumno = FXCollections.observableArrayList();
		cmbBoxAlumno.setItems(dataAlumno);
		ArrayList<Alumno> lstAlumnos = cAlumno.selectAlumnos();
		
		dataAlumno.clear();
		dataAlumno.addAll(lstAlumnos);
	}
	
	private void cargarHistorico() {
		cHistorico = new HistoricoDAO();
		dataHistorico = FXCollections.observableArrayList();
		tblHistorico.setItems(dataHistorico);
		colId.setCellValueFactory(new PropertyValueFactory<Historico, Integer>("id_prestamo"));
		colDni.setCellValueFactory(new PropertyValueFactory<Historico, String>("dni_alumno"));
		colLibro.setCellValueFactory(new PropertyValueFactory<Historico, Integer>("codigo_libro"));
		colFechaPrestamo.setCellValueFactory(new PropertyValueFactory<Historico, LocalDate>("fecha_prestamo"));
		colFechaDev.setCellValueFactory(new PropertyValueFactory<Historico, LocalDate>("fecha_devolucion"));
		
		ArrayList<Historico> lstHistorico = cHistorico.selectHistoricos();
		dataHistorico.clear();
		dataHistorico.addAll(lstHistorico);
		
	}
}
