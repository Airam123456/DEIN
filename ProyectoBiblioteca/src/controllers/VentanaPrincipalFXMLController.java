package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import com.qoppa.pdf.PDFException;
import com.qoppa.pdfViewerFX.PDFViewer;

import config.ConexionDB;
import config.Propiedades;
import dao.AlumnoDAO;
import dao.LibroDAO;
import dao.PrestamoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;

import javafx.scene.control.ListView;

import javafx.scene.control.MenuItem;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Alumno;
import model.Libro;
import model.Prestamo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class VentanaPrincipalFXMLController implements Initializable {
	@FXML
	private TextField txtFieldAlumno;
	@FXML
	private Button btnCrearAlumno;
	@FXML
	private Button btnEditarAlumno;
	@FXML
	private Button btnBorrarAlumno;
	@FXML
	private ListView<Alumno> lstAlumno;
	@FXML
	private MenuItem menuCrearAlumno;
	@FXML
	private MenuItem menuEditarAlumno;
	@FXML
	private MenuItem menuBorrarAlumno;
	@FXML
	private TextField txtFieldLibros;
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
	private MenuItem menuCrearLibro;
	@FXML
	private MenuItem menuEditarLibro;
	@FXML
	private MenuItem menuBorrarLibro;
	@FXML
	private Button btnCrearLibro;
	@FXML
	private Button btnEditarLibro;
	@FXML
	private Button btnBorrarLibro;
	@FXML
	private Button btnPrestar;
	@FXML
	private MenuItem menuGestionarPrestamo;
	@FXML
	private MenuItem menuGestionarHistorico;
	@FXML
	private MenuItem menuInformeLibros;
	@FXML
	private MenuItem menuInformeGraficos;
	@FXML
	private MenuItem menuInformeCalculos;
	@FXML
	private MenuItem menuGuia;


	private PDFViewer visorPDF;

	private ObservableList<Libro> dataLibro;
	private int iSeleccionadoLibro = -1;

	private Libro l;
	private LibroDAO cLibro;

	private ObservableList<Alumno> dataAlumno;
	private int iSeleccionadoAlumno = -1;

	private Alumno a;
	private AlumnoDAO cAlumno;

	private PrestamoDAO cPrestamo;
	private Prestamo p;

	@FXML
	public void informeLibros(ActionEvent event) {
		try {
			ConexionDB con = new ConexionDB();
			JasperReport report = (JasperReport) JRLoader
					.loadObject(getClass().getResource("/reports/informe2_Libros.jasper"));
			JasperPrint jprint = JasperFillManager.fillReport(report, null, con.getConexion());
			JasperViewer viewer = new JasperViewer(jprint, false);
			viewer.setVisible(true);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Error al cargar el informe");
			alert.showAndWait();
		}
	}

	@FXML
	public void informeCalculos(ActionEvent event) {
		try {
			ConexionDB con = new ConexionDB();
			JasperReport report = (JasperReport) JRLoader
					.loadObject(getClass().getResource("/reports/informe4_Calculos.jasper"));
			JasperPrint jprint = JasperFillManager.fillReport(report, null, con.getConexion());
			JasperViewer viewer = new JasperViewer(jprint, false);
			viewer.setVisible(true);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Error al cargar el informe");
			alert.showAndWait();
		}
	}

	@FXML
	public void informeGraficos(ActionEvent event) {
		try {
			ConexionDB con = new ConexionDB();
			JasperReport report = (JasperReport) JRLoader
					.loadObject(getClass().getResource("/reports/informe3_Graficos.jasper"));
			JasperPrint jprint = JasperFillManager.fillReport(report, null, con.getConexion());
			JasperViewer viewer = new JasperViewer(jprint, false);
			viewer.setVisible(true);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Error al cargar el informe");
			alert.showAndWait();
		}
	}


	@FXML
	public void abrirGuia(ActionEvent event) {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VisorAyuda.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			Stage myStage = (Stage) this.btnCrearLibro.getScene().getWindow();
			stage.initOwner(myStage);
			stage.setScene(scene);
			stage.setTitle("Guia Rapida");
			stage.showAndWait();

		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();			
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Error al cargar html");
			alert.showAndWait();
		}

	}

	// Event Listener on Button[#btnCrearAlumno].onAction
	@FXML
	public void crearAlumnoBtn(ActionEvent event) {
		Locale locale = new Locale(Propiedades.getValor("language"), Propiedades.getValor("region"));
		ResourceBundle bundle = ResourceBundle.getBundle("config/messages", locale);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GestionAlumnoFXML.fxml"), bundle);
		Parent root;
		try {
			root = loader.load();
			GestionAlumnoFXMLController controller = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			Stage myStage = (Stage) this.btnCrearLibro.getScene().getWindow();
			stage.initOwner(myStage);
			stage.setScene(scene);
			stage.setTitle("Agregar Alumno");
			stage.showAndWait();

			a = controller.getAlumno();
			if (a != null) {
				dataAlumno.add(a);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Error de base de datos");
			alert.showAndWait();
		}
	}

	// Event Listener on MenuItem[#menuCrearAlumno].onAction
	@FXML
	public void crearAlumno(ActionEvent event) {
		Locale locale = new Locale(Propiedades.getValor("language"), Propiedades.getValor("region"));
		ResourceBundle bundle = ResourceBundle.getBundle("config/messages", locale);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GestionAlumnoFXML.fxml"), bundle);
		Parent root;
		try {
			root = loader.load();
			GestionAlumnoFXMLController controller = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			Stage myStage = (Stage) this.btnCrearLibro.getScene().getWindow();
			stage.initOwner(myStage);
			stage.setScene(scene);
			stage.setTitle("Agregar Alumno");
			stage.showAndWait();

			a = controller.getAlumno();

			if (a != null) {
				dataAlumno.add(a);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Error de base de datos");
			alert.showAndWait();
		}
	}

	// Event Listener on Button[#btnEditarAlumno].onAction
	@FXML
	public void editarAlumnoBtn(ActionEvent event) {
		Locale locale = new Locale(Propiedades.getValor("language"), Propiedades.getValor("region"));
		ResourceBundle bundle = ResourceBundle.getBundle("config/messages", locale);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GestionAlumnoFXML.fxml"), bundle);
		Parent root;
		try {
			root = loader.load();
			GestionAlumnoFXMLController controller = loader.getController();
			controller.setAlumno(a);
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			Stage myStage = (Stage) this.btnCrearLibro.getScene().getWindow();
			stage.initOwner(myStage);
			stage.setScene(scene);
			stage.setTitle("Actualizar Alumno");
			stage.showAndWait();

			a = controller.getAlumno();

			if (a != null) {
				dataAlumno.set(iSeleccionadoAlumno, a);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Error de base de datos");
			alert.showAndWait();
		}
	}

	// Event Listener on MenuItem[#menuEditarAlumno].onAction
	@FXML
	public void editarAlumno(ActionEvent event) {
		Locale locale = new Locale(Propiedades.getValor("language"), Propiedades.getValor("region"));
		ResourceBundle bundle = ResourceBundle.getBundle("config/messages", locale);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GestionAlumnoFXML.fxml"), bundle);
		Parent root;
		try {
			root = loader.load();
			GestionAlumnoFXMLController controller = loader.getController();
			controller.setAlumno(a);
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			Stage myStage = (Stage) this.btnCrearLibro.getScene().getWindow();
			stage.initOwner(myStage);
			stage.setScene(scene);
			stage.setTitle("Actualizar Alumno");
			stage.showAndWait();

			a = controller.getAlumno();
			if (a != null) {
				dataAlumno.set(iSeleccionadoAlumno, a);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Error de base de datos");
			alert.showAndWait();
		}
	}

	// Event Listener on Button[#btnBorrarAlumno].onAction
	@FXML
	public void borrarAlumnoBtn(ActionEvent event) {
		Alumno a = lstAlumno.getSelectionModel().getSelectedItem();

		try {
			if (!cPrestamo.existeAlumno(a)) {
				cAlumno.deleteAlumno(a);
				dataAlumno.remove(iSeleccionadoAlumno);
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.initOwner(this.btnBorrarAlumno.getScene().getWindow());
				alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("No se puede borrar un alumno con un prestamo");
				alert.showAndWait();
			}

		} catch (SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initOwner(this.btnBorrarAlumno.getScene().getWindow());
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("No se ha podido borrar el alumno");
			alert.showAndWait();
		}

		iSeleccionadoAlumno = -1;
		btnEditarAlumno.setDisable(true);
		btnBorrarAlumno.setDisable(true);

		menuEditarAlumno.setDisable(true);
		menuBorrarAlumno.setDisable(true);
	}

	// Event Listener on MenuItem[#menuBorrarAlumno].onAction
	@FXML
	public void borrarAlumno(ActionEvent event) {
		Alumno a = lstAlumno.getSelectionModel().getSelectedItem();

		try {
			if (!cPrestamo.existeAlumno(a)) {
				cAlumno.deleteAlumno(a);
				dataAlumno.remove(iSeleccionadoAlumno);
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.initOwner(this.btnBorrarAlumno.getScene().getWindow());
				alert.setHeaderText(null);
				alert.setTitle("ERROR");
				alert.setContentText("No se puede borrar un alumno con un prestamo");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initOwner(this.btnBorrarAlumno.getScene().getWindow());
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("No se ha podido borrar el alumno");
			alert.showAndWait();
		}

		iSeleccionadoAlumno = -1;
		btnEditarAlumno.setDisable(true);
		btnBorrarAlumno.setDisable(true);
		menuEditarAlumno.setDisable(true);
		menuBorrarAlumno.setDisable(true);
	}

	// Event Listener on ListView[#lstAlumno].onMouseClicked
	@FXML
	public void seleccionarFilaLst(MouseEvent event) {
		if (lstAlumno.getSelectionModel().getSelectedItem() != null) {
			btnEditarAlumno.setDisable(false);
			btnBorrarAlumno.setDisable(false);

			menuBorrarAlumno.setDisable(false);
			menuEditarAlumno.setDisable(false);
			iSeleccionadoAlumno = lstAlumno.getSelectionModel().getSelectedIndex();
			a = lstAlumno.getSelectionModel().getSelectedItem();

//			if (tblLibro.getSelectionModel().getSelectedItem() != null) {
//				btnPrestar.setDisable(false);
//			} else {
//				btnPrestar.setDisable(true);
//			}
		}
	}

	// Event Listener on TableView[#tblLibro].onMouseClicked
	@FXML
	public void seleccionarFilaTbl(MouseEvent event) {
		if (tblLibro.getSelectionModel().getSelectedItem() != null) {
			btnEditarLibro.setDisable(false);
			btnBorrarLibro.setDisable(false);

			menuEditarLibro.setDisable(false);
			menuBorrarLibro.setDisable(false);
			iSeleccionadoLibro = tblLibro.getSelectionModel().getSelectedIndex();
			l = tblLibro.getSelectionModel().getSelectedItem();

//			if (lstAlumno.getSelectionModel().getSelectedItem() != null) {
//				btnPrestar.setDisable(false);
//			} else {
//				btnPrestar.setDisable(true);
//			}
		}
	}

	// Event Listener on MenuItem[#menuCrearLibro].onAction
	@FXML
	public void crearLibro(ActionEvent event) {
		Locale locale = new Locale(Propiedades.getValor("language"), Propiedades.getValor("region"));
		ResourceBundle bundle = ResourceBundle.getBundle("config/messages", locale);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GestionLibroFXML.fxml"), bundle);
		Parent root;
		try {
			root = loader.load();
			GestionLibroFXMLController controller = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			Stage myStage = (Stage) this.btnCrearLibro.getScene().getWindow();
			stage.initOwner(myStage);
			stage.setScene(scene);
			stage.setTitle("Agregar Libro");
			stage.showAndWait();

			l = controller.getLibro();

			if (l != null) {
				dataLibro.add(l);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Error al crear libro");
			alert.showAndWait();
		}
	}

	// Event Listener on Button[#btnCrearLibro].onAction
	@FXML
	public void crearLibroBtn(ActionEvent event) {
		Locale locale = new Locale(Propiedades.getValor("language"), Propiedades.getValor("region"));
		ResourceBundle bundle = ResourceBundle.getBundle("config/messages", locale);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GestionLibroFXML.fxml"), bundle);
		Parent root;
		try {
			root = loader.load();
			GestionLibroFXMLController controller = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			Stage myStage = (Stage) this.btnCrearLibro.getScene().getWindow();
			stage.initOwner(myStage);
			stage.setScene(scene);
			stage.setTitle("Agregar Libro");
			stage.showAndWait();

			l = controller.getLibro();

			if (l != null) {
				dataLibro.add(l);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Error al crear libro");
			alert.showAndWait();
		}
	}

	// Event Listener on MenuItem[#menuEditarLibro].onAction
	@FXML
	public void editarLibro(ActionEvent event) {
		Locale locale = new Locale(Propiedades.getValor("language"), Propiedades.getValor("region"));
		ResourceBundle bundle = ResourceBundle.getBundle("config/messages", locale);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GestionLibroFXML.fxml"), bundle);
		Parent root;
		try {
			root = loader.load();
			GestionLibroFXMLController controller = loader.getController();
			controller.setLibro(l);
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			Stage myStage = (Stage) this.btnCrearLibro.getScene().getWindow();
			stage.initOwner(myStage);
			stage.setScene(scene);
			stage.setTitle("Actualizar Libro");
			stage.showAndWait();

			l = controller.getLibro();

			if (l != null) {
				dataLibro.set(iSeleccionadoLibro, l);
			}
			menuEditarLibro.setDisable(true);
			menuBorrarLibro.setDisable(true);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Error de base de datos");
			alert.showAndWait();
		}
	}

	// Event Listener on Button[#btnEditarLibro].onAction
	@FXML
	public void editarLibroBtn(ActionEvent event) {
		Locale locale = new Locale(Propiedades.getValor("language"), Propiedades.getValor("region"));
		ResourceBundle bundle = ResourceBundle.getBundle("config/messages", locale);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GestionLibroFXML.fxml"), bundle);
		Parent root;
		try {
			root = loader.load();
			GestionLibroFXMLController controller = loader.getController();
			controller.setLibro(l);
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			Stage myStage = (Stage) this.btnCrearLibro.getScene().getWindow();
			stage.initOwner(myStage);
			stage.setScene(scene);
			stage.setTitle("Actualizar Libro");
			stage.showAndWait();

			l = controller.getLibro();

			if (l != null) {
				dataLibro.set(iSeleccionadoLibro, l);
			}

			menuEditarLibro.setDisable(true);
			menuBorrarLibro.setDisable(true);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Error de base de datos");
			alert.showAndWait();
		}
	}

	// Event Listener on MenuItem[#menuBorrarLibro].onAction
	@FXML
	public void borrarLibro(ActionEvent event) {
		Libro lib = tblLibro.getSelectionModel().getSelectedItem();

		if (!cPrestamo.existeLibro(lib)) {
			dataLibro.remove(iSeleccionadoLibro);

			cLibro.cambiarBaja(lib);
			iSeleccionadoLibro = -1;
			btnEditarLibro.setDisable(true);
			btnBorrarLibro.setDisable(true);

			menuEditarLibro.setDisable(true);
			menuBorrarLibro.setDisable(true);
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("No se puede dar de baja un libro prestado");
			alert.showAndWait();
		}
	}

	// Event Listener on Button[#btnBorrarLibro].onAction
	@FXML
	public void borrarLibroBtn(ActionEvent event) {
		Libro lib = tblLibro.getSelectionModel().getSelectedItem();

		if (!cPrestamo.existeLibro(lib)) {
			dataLibro.remove(iSeleccionadoLibro);

			cLibro.cambiarBaja(lib);
			iSeleccionadoLibro = -1;
			btnEditarLibro.setDisable(true);
			btnBorrarLibro.setDisable(true);

			menuEditarLibro.setDisable(true);
			menuBorrarLibro.setDisable(true);
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("No se puede dar de baja un libro prestado");
			alert.showAndWait();
		}
	}

	// Event Listener on Button[#btnPrestar].onAction
	@FXML
	public void realizarPrestamo(ActionEvent event) {
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
			Stage myStage =(Stage) this.btnPrestar.getScene().getWindow();
			stage.initOwner(myStage);
			stage.setScene(scene);
			stage.setTitle("Realizar Prestamo");
			stage.showAndWait();

		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Ha habido un problema con el prestamo");
			alert.showAndWait();
		}
		
		cargarAlumnos();
		cargarLibrosDisponibles();
		
	}

	// Event Listener on MenuItem[#menuGestionarPrestamo].onAction
	@FXML
	public void gestionarPrestamo(ActionEvent event) {
		Locale locale = new Locale(Propiedades.getValor("language"), Propiedades.getValor("region"));
		ResourceBundle bundle = ResourceBundle.getBundle("config/messages", locale);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanaPrestamosFXML.fxml"), bundle);
		Parent root;
		try {
			root = loader.load();
			VentanaPrestamosFXMLController controller = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			Stage myStage = (Stage) this.btnCrearLibro.getScene().getWindow();
			stage.initOwner(myStage);
			stage.setScene(scene);
			stage.setTitle("Prestamos");
			stage.showAndWait();

			cargarLibrosDisponibles();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Error al cargar prestamos");
			alert.showAndWait();
		}
	}

	// Event Listener on MenuItem[#menuGestionarHistorico].onAction
	@FXML
	public void gestionarHistorico(ActionEvent event) {
		Locale locale = new Locale(Propiedades.getValor("language"), Propiedades.getValor("region"));
		ResourceBundle bundle = ResourceBundle.getBundle("config/messages", locale);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanaHistoricoFXML.fxml"), bundle);
		Parent root;
		try {
			root = loader.load();
			VentanaHistoricoFXMLController controller = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			Stage myStage = (Stage) this.btnCrearLibro.getScene().getWindow();
			stage.initOwner(myStage);
			stage.setScene(scene);
			stage.setTitle("Historico");
			stage.showAndWait();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Error al cargar historico");
			alert.showAndWait();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		cAlumno = new AlumnoDAO();
		cLibro = new LibroDAO();
		cPrestamo = new PrestamoDAO();

		cargarLibrosDisponibles();
		cargarAlumnos();

		filtro();
		
		txtFieldAlumno.setPromptText("Filtrar Alumnos");
		txtFieldAlumno.getParent().requestFocus();
		txtFieldLibros.setPromptText("Filtrar Libros");
		txtFieldLibros.getParent().requestFocus();

	}

	private void filtro() {
		// Filtro
		FilteredList<Libro> filteredData = new FilteredList<>(dataLibro, p -> true);

		txtFieldLibros.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(libro -> {

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				if (libro.getTitulo().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (libro.getAutor().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if ((libro.getCodigo() + "").toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (libro.getEstado().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (libro.getEditorial().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				}
				return false;
			});
		});

		SortedList<Libro> sortedData = new SortedList<>(filteredData);

		sortedData.comparatorProperty().bind(tblLibro.comparatorProperty());

		tblLibro.setItems(sortedData);
	}

	private void cargarAlumnos() {

		dataAlumno = FXCollections.observableArrayList();
		lstAlumno.setItems(dataAlumno);

		btnEditarAlumno.setDisable(true);
		btnBorrarAlumno.setDisable(true);

		menuEditarAlumno.setDisable(true);
		menuBorrarAlumno.setDisable(true);
		//btnPrestar.setDisable(true);

		ArrayList<Alumno> listaAlumnos = cAlumno.selectAlumnos();
		dataAlumno.addAll(listaAlumnos);
	}

	private void cargarLibrosDisponibles() {

		dataLibro = FXCollections.observableArrayList();
		tblLibro.setItems(dataLibro);
		colCodigoLibro.setCellValueFactory(new PropertyValueFactory<Libro, Integer>("Codigo"));
		colTitulo.setCellValueFactory(new PropertyValueFactory<Libro, String>("Titulo"));
		colAutorLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("Autor"));
		colEstadoLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("Estado"));
		btnEditarLibro.setDisable(true);
		btnBorrarLibro.setDisable(true);

		menuBorrarLibro.setDisable(true);
		menuEditarLibro.setDisable(true);
		//btnPrestar.setDisable(true);

		ArrayList<Libro> listaLibros = cLibro.selectLibrosDisponibles();
		dataLibro.clear();
		dataLibro.addAll(listaLibros);
		tblLibro.refresh();
	}
}
