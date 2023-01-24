package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.AlumnoDAO;
import dao.LibroDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Alumno;
import model.Libro;

public class BibliotecaController implements Initializable{


    @FXML
    private Button btnBorrarLibro;

    @FXML
    private Button btnCrearAlumno;

    @FXML
    private Button btnCrearLibro;

    @FXML
    private Button btnEditarAlumno;

    @FXML
    private Button btnEditarLibro;

    @FXML
    private Button btnPrestar;
    
    @FXML
	private TableView<Libro> tblLibro;
	@FXML
	private TableColumn<Libro, Integer> colCodigoLibro;
	@FXML
	private TableColumn<Libro, String> colTitulo;
	@FXML
	private TableColumn<Libro, String> colAutorLibro;
	@FXML
	private TableColumn<Libro, String> colEstadoLibro;

    @FXML
    private Label lblAlumno;

    @FXML
    private Label lblLibro;

    @FXML
    private ListView lstAlumno;

    @FXML
    private MenuItem menuBorrarLibro;

    @FXML
    private MenuItem menuCrearAlumno;

    @FXML
    private MenuItem menuCrearLibro;

    @FXML
    private MenuItem menuEditarAlumno;

    @FXML
    private MenuItem menuEditarLibro;

    @FXML
    private MenuItem menuGestionarHistorico;

    @FXML
    private MenuItem menuGestionarPrestamo;

    @FXML
    private MenuItem menuGuia;

    @FXML
    private MenuItem menuInformeCalculos;

    @FXML
    private MenuItem menuInformeGraficos;

    @FXML
    private MenuItem menuInformeLibros;

    @FXML
    private MenuItem menuManual;

    @FXML
    private TextField txtBuscarAlumno;

    @FXML
    private TextField txtBuscarLibros;
    
    
    
    private AlumnoDAO cargarAlumnos;
    private LibroDAO cargarLibros;
    
    
    private ObservableList<Alumno> alumnos;
    private ObservableList<Libro> libros;
    
    private Alumno a;
    

    @FXML
    void abrirGuia(ActionEvent event) {

    }

    @FXML
    void abrirManual(ActionEvent event) {

    }


    @FXML
    void borrarLibro(ActionEvent event) {

    }

    @FXML
    void borrarLibroBtn(ActionEvent event) {

    }

    @FXML
    void crearAlumno(ActionEvent event) {

    }

    @FXML
    void crearAlumnoBtn(ActionEvent event) {

    }

    @FXML
    void crearLibro(ActionEvent event) {

    }

    @FXML
    void crearLibroBtn(ActionEvent event) {

    }

    @FXML
    void editarAlumno(ActionEvent event) {

    }

    @FXML
    void editarAlumnoBtn(ActionEvent event) {

    }

    @FXML
    void editarLibro(ActionEvent event) {

    }

    @FXML
    void editarLibroBtn(ActionEvent event) {

    }

    @FXML
    void gestionarHistorico(ActionEvent event) {

    }

    @FXML
    void gestionarPrestamo(ActionEvent event) {

    }

    @FXML
    void informeCalculos(ActionEvent event) {

    }

    @FXML
    void informeGraficos(ActionEvent event) {

    }

    @FXML
    void informeLibros(ActionEvent event) {

    }

    @FXML
    void realizarPrestamo(ActionEvent event) {

    }

    @FXML
    void seleccionarFilaLst(MouseEvent event) {
    	
    	a = (Alumno) lstAlumno.getSelectionModel().getSelectedItem();
    	
    	
    	
    	lblAlumno.setText(a.getNombre() + " " + a.getApellido1() + " " + a.getApellido2());
    	

    }

    @FXML
    void seleccionarFilaTbl(MouseEvent event) {

    }
    
    private void cargarAlumnos() {
    	alumnos = FXCollections.observableArrayList();
		try {
			cargarAlumnos = new AlumnoDAO();
			alumnos.addAll(cargarAlumnos.selectAlumnos());
			lstAlumno.setItems(alumnos);
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Error de acceso a la base de datos");
			alert.showAndWait();
			e.printStackTrace();
		}
    }
    
    private void cargarLibros() {
    	libros = FXCollections.observableArrayList();
    	
    	
    	try {
			cargarLibros = new LibroDAO();
			ArrayList<Libro> librosDAO;
			
			librosDAO = cargarLibros.selectLibrosDisponibles();
			librosDAO.forEach(lib -> libros.add(lib));
			
			tblLibro.setItems(libros);

			colCodigoLibro.setCellValueFactory(new PropertyValueFactory<Libro, Integer>("Codigo"));
			colTitulo.setCellValueFactory(new PropertyValueFactory<Libro, String>("Titulo"));
			colAutorLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("Autor"));
			colEstadoLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("Estado"));

			tblLibro.refresh();
			
			
		} catch (Exception er) {
			// TODO: handle exception
			er.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Error al cargar los Libros");
			alert.showAndWait();
		}
    }
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		cargarAlumnos();
		cargarLibros();
		
		
		
	}

}
