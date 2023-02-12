package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Libro;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.LibroDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

public class GestionLibroFXMLController implements Initializable{
	@FXML
	private TextField txtCodigo;
	@FXML
	private TextField txtTitulo;
	@FXML
	private TextField txtAutor;
	@FXML
	private TextField txtEditorial;
	@FXML
	private ComboBox <String> cmbBoxEstado;
	@FXML
	private Button btnAceptar;
	@FXML
	private Button btnCancelar;
	
	private ObservableList<String> data;
	private Libro l;
	private LibroDAO cLibro;

	// Event Listener on Button[#btnAceptar].onAction
	@FXML
	public void aceptar(ActionEvent event) {
			cLibro = new LibroDAO();
			
			String titulo = txtTitulo.getText();
			String autor = txtAutor.getText();
			String editorial = txtEditorial.getText();
			try {
				int codigo = Integer.parseInt(txtCodigo.getText());
				String estado = cmbBoxEstado.getSelectionModel().getSelectedItem();
				
				if (codigo < 0 || String.valueOf(codigo).length()< 6) {
					Alert alert= new Alert(Alert.AlertType.ERROR);
					alert.initOwner(this.btnCancelar.getScene().getWindow());
					alert.setHeaderText(null);
					alert.setTitle("ERROR");
					alert.setContentText("El codigo debe ser numerico y no mas de 6 digitos");
					alert.showAndWait();
				}
				else {
					if (l!= null) {
						int codigoAntiguo = l.getCodigo();
						l = new Libro (codigo,titulo, autor, editorial, estado,0);
						
						try {
							cLibro.updateLibro(l, codigoAntiguo);
							
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setHeaderText(null);
							alert.setTitle("Exito");
							alert.setContentText("Libro Actualizado");
							alert.showAndWait();
							
							Stage myStage =(Stage) this.btnCancelar.getScene().getWindow();
							myStage.close();
							
						} catch (SQLException e) {
							Alert alert= new Alert(Alert.AlertType.ERROR);
							alert.initOwner(this.btnCancelar.getScene().getWindow());
							alert.setHeaderText(null);
							alert.setTitle("ERROR");
							alert.setContentText("Ha ocurrido un error en la actualizacion del libro");
							alert.showAndWait();
						}
						
					}else {
						l = new Libro (codigo,titulo, autor, editorial, estado,0);
						
						try {
							cLibro.insertLibro(l);
							
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setHeaderText(null);
							alert.setTitle("Exito");
							alert.setContentText("Libro Creado");
							alert.showAndWait();
							
							Stage myStage =(Stage) this.btnCancelar.getScene().getWindow();
							myStage.close();
						} catch (SQLException e) {
							Alert alert= new Alert(Alert.AlertType.ERROR);
							alert.initOwner(this.btnCancelar.getScene().getWindow());
							alert.setHeaderText(null);
							alert.setTitle("ERROR");
							alert.setContentText("Ha ocurrido un error en la creacion del libro");
							alert.showAndWait();
						}
					}
					
					
				}
			}catch (NumberFormatException e) {
					Alert alert= new Alert(Alert.AlertType.ERROR);
					alert.initOwner(this.btnCancelar.getScene().getWindow());
					alert.setHeaderText(null);
					alert.setTitle("ERROR");
					alert.setContentText("El codigo debe ser un numero");
					alert.showAndWait();
			}
		}
	

	
	// Event Listener on Button[#btnCancelar].onAction
	@FXML
	public void cancelar(ActionEvent event) {
		Stage myStage =(Stage) this.btnCancelar.getScene().getWindow();
		myStage.close();
	}
	
	public void setLibro (Libro lib) {
		l = lib;
		txtTitulo.setText(l.getTitulo());
		txtCodigo.setText(l.getCodigo() + "");
		txtAutor.setText(l.getAutor());
		txtEditorial.setText(l.getEditorial());
		cmbBoxEstado.getSelectionModel().select(l.getEstado());

	}
	
	public Libro getLibro () {
		return l;
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		data = FXCollections.observableArrayList();
		data.addAll("Nuevo","Usado nuevo", "Usado seminuevo", "Usado estropeado", "Restaurado");
		cmbBoxEstado.setItems(data);
		
		cLibro = new LibroDAO();
	}
}
