package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.TextField;

import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Deportista;
import model.Olimpiada;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.DeporteDAO;
import dao.DeportistaDAO;
import javafx.event.ActionEvent;

import javafx.scene.control.RadioButton;

public class AniadirDeportistaController implements Initializable{
	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtPeso;
	@FXML
	private TextField txtAltura;
	@FXML
	private Button btnAceptar;
	@FXML
	private Button btnCancelar;
	@FXML
	private RadioButton rbtnMasculino;
	@FXML
	private ToggleGroup sexo;
	@FXML
	private RadioButton rbtnFemenino;
	@FXML
    private Button btnImagen;
    @FXML
    private ImageView image;
	
	private Deportista d;
	private DeportistaDAO existe; 
	private InputStream foto;
	private File archivo;

	// Event Listener on Button[#btnAceptar].onAction
	@FXML
	public void aceptar(ActionEvent event) {
		// TODO Autogenerated
		
		int id = 0;
		String nombre = txtNombre.getText();
		String sexo;
		int peso = 0;
		int altura = 0;
		String error = "";
		
		if(rbtnMasculino.isSelected()) 
			sexo = "M";
		else
			sexo = "F";

		if(nombre.isEmpty()) {
			error += "\n El campo Nombre no puede estar vacio";
		}
		
		if(nombre.length()>11) {
			error += "\n El campo Nombre no puede tener mas de 11 caracteres";
		}
		
		try {
			peso = Integer.parseInt(txtPeso.getText());
			if (peso > 500 || peso < 0) {
				error += "\n Peso fuera de rango.\n Si no desea definir un peso ponga 0";
			}
		} catch (Exception e) {
			error += "\n El Peso tiene que ser un entero.\n Si no desea definir un peso ponga 0";
		}

		try {
			altura = Integer.parseInt(txtAltura.getText());
			if (altura > 250 || altura < 0) {
				error += "\n Altura fuera de rango. Tiene que ir en cm.\n Si no desea definir una altura ponga 0";
			}
		} catch (Exception e) {
			error += "\n La Altura tiene que se un entero en cm.\n Si no desea definir una altura ponga 0";
		}

		
		d = new Deportista(id, nombre, sexo, peso, altura,foto);
		
		try {
			existe = new DeportistaDAO();
			if(existe.existeDeportista(d) == true)
				error += "\n Deportista ya existente";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Error en la base de Datos");
			alert.showAndWait();
			e.printStackTrace();
		}
		
		if(error.equals("")) {
			
			try {
				existe.insertDeportista(d);
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.initOwner(this.btnAceptar.getScene().getWindow());
				alert.setHeaderText(null);
				alert.setTitle("Info");
				alert.setContentText("Deportista agregada correctamente");
				alert.showAndWait();

				Stage myStage =(Stage) this.btnCancelar.getScene().getWindow();
				myStage.close();
			} catch (Exception e) {
				// TODO: handle exception
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("Error");
				alert.setContentText("Error en la base de Datos");
				alert.showAndWait();
			}
		}
		else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText(error);
			alert.showAndWait();
		}
		
	}
    @FXML
    void importarImagen(ActionEvent event) {
    	importar(new Stage());
    }
    

	private void importar(Stage stage) {

		FileChooser fileDialog = new FileChooser();
		fileDialog.setTitle("Open Image");
		fileDialog.setInitialDirectory(new File("/home/dm2/Escritorio"));
		archivo = fileDialog.showOpenDialog(stage);
		
		try {
			
			foto = (InputStream) new FileInputStream(archivo);
			image.setImage(new Image(foto));
			
		} catch (FileNotFoundException e) {
			
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("No se ha podido importar la imagen");
			alert.showAndWait();
			e.printStackTrace();
		}
	}
	
	// Event Listener on Button[#btnCancelar].onAction
	@FXML
	public void cancelar(ActionEvent event) {
		// TODO Autogenerated
		
		Stage myStage =(Stage) this.btnCancelar.getScene().getWindow();
		myStage.close();
	}
	
	public Deportista getDeportista() {
		return d;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		txtPeso.setPromptText("Indefinido = 0");
		txtPeso.getParent().requestFocus();
		txtAltura.setPromptText("Indefinido = 0");
		txtAltura.getParent().requestFocus();
		
	}
}
