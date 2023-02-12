package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Alumno;
import model.Libro;

import java.sql.SQLException;

import dao.AlumnoDAO;
import javafx.event.ActionEvent;

public class GestionAlumnoFXMLController {
	@FXML
	private TextField txtFieldDni;
	@FXML
	private TextField txtFieldNombre;
	@FXML
	private TextField txtFieldApellido1;
	@FXML
	private TextField txtFieldApellido2;
	@FXML
	private Button btnAceptar;
	@FXML
	private Button btnCancelar;
	
	private Alumno alum;
	private AlumnoDAO cAlumno;

	// Event Listener on Button[#btnAceptar].onAction
	@FXML
	public void aceptar(ActionEvent event) {
		cAlumno = new AlumnoDAO();

		String dni = txtFieldDni.getText();
		String nombre = txtFieldNombre.getText();
		String ape1 = txtFieldApellido1.getText();
		String ape2 = txtFieldApellido2.getText();
		

		if(nombre.equals("") || ape1.equals("") || ape2.equals("")) {
			Alert alert= new Alert(Alert.AlertType.ERROR);
			alert.initOwner(this.btnCancelar.getScene().getWindow());
			alert.setHeaderText(null);
			alert.setTitle("ERROR");
			alert.setContentText("Debes rellenar todos los campos");
			alert.showAndWait();
		}
		else {
			if (alum!= null) {
				String dniAntiguo = alum.getDni();
				alum = new Alumno(dni, nombre, ape1, ape2);
				if (dni.length()==9) {
					try {
						cAlumno.updateAlumno(alum, dniAntiguo);
						
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setHeaderText(null);
						alert.setTitle("Exito");
						alert.setContentText("Alumno Actualizado");
						alert.showAndWait();
						
						Stage myStage =(Stage) this.btnCancelar.getScene().getWindow();
						myStage.close();
						
					} catch (SQLException e) {
						Alert alert= new Alert(Alert.AlertType.ERROR);
						alert.initOwner(this.btnCancelar.getScene().getWindow());
						alert.setHeaderText(null);
						alert.setTitle("ERROR");
						alert.setContentText("Ha ocurrido un error en la actualizacion del alumno");
						alert.showAndWait();
					}
				}else {
					Alert alert= new Alert(Alert.AlertType.ERROR);
					alert.initOwner(this.btnCancelar.getScene().getWindow());
					alert.setHeaderText(null);
					alert.setTitle("ERROR");
					alert.setContentText("El campo DNI no puede estar vacio y debe tener 9 digitos");
					alert.showAndWait();
				}
				
				
			}else {
				alum = new Alumno(dni, nombre, ape1, ape2);
				
				if (dni.length()==9) {
					try {
						cAlumno.insertAlumno(alum);
						
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setHeaderText(null);
						alert.setTitle("Exito");
						alert.setContentText("Alumno creado");
						alert.showAndWait();
						
						Stage myStage =(Stage) this.btnCancelar.getScene().getWindow();
						myStage.close();
						
					} catch (SQLException e) {
						Alert alert= new Alert(Alert.AlertType.ERROR);
						alert.initOwner(this.btnCancelar.getScene().getWindow());
						alert.setHeaderText(null);
						alert.setTitle("ERROR");
						alert.setContentText("Ha ocurrido un error en la creacion del alumno");
						alert.showAndWait();
					}
				}
				else {
					Alert alert= new Alert(Alert.AlertType.ERROR);
					alert.initOwner(this.btnCancelar.getScene().getWindow());
					alert.setHeaderText(null);
					alert.setTitle("ERROR");
					alert.setContentText("El campo DNI no puede estar vacio y debe tener 9 digitos");
					alert.showAndWait();
				}
				
			}
		}
	}
	// Event Listener on Button[#btnCancelar].onAction
	@FXML
	public void cancelar(ActionEvent event) {
		Stage myStage =(Stage) this.btnCancelar.getScene().getWindow();
		myStage.close();
	}
	
	public Alumno getAlumno () {
		return alum;
	}
	
	public void setAlumno (Alumno a) {
		alum = a;
		txtFieldDni.setText(a.getDni());
		txtFieldNombre.setText(a.getNombre());
		txtFieldApellido1.setText(a.getApellido1());
		txtFieldApellido2.setText(a.getApellido2());
	}
	
}
