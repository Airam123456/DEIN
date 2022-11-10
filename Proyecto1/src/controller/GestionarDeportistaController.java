package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.security.auth.callback.ConfirmationCallback;

import dao.DeportistaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ListView;

import javafx.scene.control.RadioButton;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Deportista;

public class GestionarDeportistaController implements Initializable {
	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtPeso;
	@FXML
	private TextField txtAltura;
	@FXML
	private Button btnEditar;
	@FXML
	private Button btnBorrar;
	@FXML
	private Button btnCancelar;
	@FXML
	private RadioButton rbtnMasculino;
	@FXML
	private ToggleGroup sexo;
	@FXML
	private RadioButton rbtnFemenino;
	@FXML
	private ListView listDeportistas;

	private ObservableList<Deportista> deportistas;
	private DeportistaDAO cargarDeportistas;
	private Deportista d;
	int id;

	// Event Listener on Button[#btnEditar].onAction
	@FXML
	public void editar(ActionEvent event) {
		// TODO Autogenerated

		String nombre = txtNombre.getText();
		String sexo;
		int peso = 0;
		int altura = 0;
		String error = "";

		if (rbtnMasculino.isSelected())
			sexo = "M";
		else
			sexo = "F";

		if (nombre.isEmpty()) {
			error += "\n El campo Nombre no puede estar vacio";
		}

		try {
			peso = Integer.parseInt(txtPeso.getText());
			if (peso > 500 || peso < 30) {
				error += "\n Peso fuera de rango";
			}
		} catch (Exception e) {
			error += "\n El Peso tiene que ser un entero";
		}

		try {
			altura = Integer.parseInt(txtAltura.getText());
			if (altura > 250 || altura < 120) {
				error += "\n Altura fuera de rango. Tiene que ir en cm";
			}
		} catch (Exception e) {
			error += "\n La Altura tiene que se un entero en cm";
		}

		d = new Deportista(id, nombre, sexo, peso, altura);

		try {
			cargarDeportistas = new DeportistaDAO();
			if (cargarDeportistas.existeDeportista(d) == true)
				error += "\n Deportista ya existente";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (error.equals("")) {

			cargarDeportistas.updateDeportista(d);

			deportistas = FXCollections.observableArrayList();

			try {
				cargarDeportistas = new DeportistaDAO();
				deportistas.addAll(cargarDeportistas.selectDeportista());
				listDeportistas.setItems(deportistas);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initOwner(this.btnEditar.getScene().getWindow());
			alert.setHeaderText(null);
			alert.setTitle("Info");
			alert.setContentText("Deportista modificada correctamente");
			alert.showAndWait();

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

		d = new Deportista(id);

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.initOwner(this.btnBorrar.getScene().getWindow());
		alert.setHeaderText(null);
		alert.setTitle("Borrar Deportista");
		alert.setContentText("Seguro que desea BORRAR este Deportista?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			try {
				if(cargarDeportistas.deleteDeportista(d)) {
					deportistas = FXCollections.observableArrayList();

					alert = new Alert(Alert.AlertType.INFORMATION);
					alert.initOwner(this.btnEditar.getScene().getWindow());
					alert.setHeaderText(null);
					alert.setTitle("Info");
					alert.setContentText("Deportista borrada correctamente");
					alert.showAndWait();
					txtNombre.clear();
					txtPeso.clear();
					txtAltura.clear();

					try {
						cargarDeportistas = new DeportistaDAO();
						deportistas.addAll(cargarDeportistas.selectDeportista());
						listDeportistas.setItems(deportistas);

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle("Error");
					alert.setContentText("No se puede borrar, existen dependencias");
					alert.showAndWait();
				}



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

	// Event Listener on ListView[#listDeportistas].onMouseClicked
	@FXML
	public void seleccionar(MouseEvent event) {
		// TODO Autogenerated
		d = (Deportista) listDeportistas.getSelectionModel().getSelectedItem();

		id = d.getId();
		String nombre = d.getNombre();
		String genero = d.getSexo();
		int peso = d.getPeso();
		int altura = d.getAltura();
		System.out.println(genero);

		txtNombre.setText(nombre);
		
		if(genero.equals("F"))
			rbtnFemenino.setSelected(true);
		else
			rbtnMasculino.setSelected(true);
		

		txtPeso.setText(Integer.toString(peso));
		txtAltura.setText(Integer.toString(altura));

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		deportistas = FXCollections.observableArrayList();

		try {
			cargarDeportistas = new DeportistaDAO();
			deportistas.addAll(cargarDeportistas.selectDeportista());
			listDeportistas.setItems(deportistas);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
