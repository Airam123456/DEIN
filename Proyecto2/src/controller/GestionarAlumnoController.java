package controller;

import java.sql.SQLException;

import dao.AlumnoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Alumno;

public class GestionarAlumnoController {

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField txtApellido1;

    @FXML
    private TextField txtApellido2;

    @FXML
    private TextField txtDNI;

    @FXML
    private TextField txtNombre;
    
    private Alumno alumno;
    private AlumnoDAO cargarAlumno;

    @FXML
    void aceptar(ActionEvent event) {
    	
		String dni = txtDNI.getText();
		String nombre = txtNombre.getText();
		String ape1 = txtApellido1.getText();
		String ape2 = txtApellido2.getText();
		
		String error = "";
		
		if(nombre.isEmpty()) {
			error += "\n El campo Nombre no puede estar vacio";
		}
		
		if(ape1.isEmpty()) {
			error += "\n El campo Primer Apellido no puede estar vacio";
		}
		
		if(ape2.isEmpty()) {
			error += "\n El campo Segundo Apellido no puede estar vacio";
		}
		
		if(dni.isEmpty() || dni.length()!=9) {
			error += "\n El campo DNI no puede estar vacio y debe tener 9 digitos";
		}
		
		alumno = new Alumno(dni, nombre, ape1, ape2);
		
		try {
			cargarAlumno = new AlumnoDAO();
			if(cargarAlumno.existeAlumno(alumno) == true) {
				error += "\n Alumno ya existente";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(error.equals("")) {
			
			try {
				cargarAlumno.insertAlumno(alumno);
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.initOwner(this.btnAceptar.getScene().getWindow());
				alert.setHeaderText(null);
				alert.setTitle("Info");
				alert.setContentText("Alumno agregada correctamente");
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
    void cancelar(ActionEvent event) {
    	Stage myStage =(Stage) this.btnCancelar.getScene().getWindow();
		myStage.close();

    }

}
