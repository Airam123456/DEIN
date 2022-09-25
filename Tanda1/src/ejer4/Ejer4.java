package ejer4;

import java.util.Arrays;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Persona;

public class Ejer4 extends Application{
	
	private TextField txtNombre, txtApellidos, txtEdad;
	private TableView<Persona> table;
	private ObservableList<Persona> personas;
	private TableColumn<Persona, String> colNombre;
	private TableColumn<Persona, String> colApellido;
	private TableColumn<Persona, String> colEdad;
	private Button btnAgregar, btnGuardar, btnCancelar;
	private TableViewSelectionModel<Persona> tsm;
	private int posicion = -1;
	
	
	public void start (Stage stage) {
		
		GridPane root = new GridPane();
		root.setVgap(10);
		root.setHgap(10);
		root.setPadding(new Insets(10, 10, 10, 10));
		
		
		//Boton agregar
		btnAgregar = new Button("Agregar Persona");
		root.add(btnAgregar, 0,4,3,3);
		
		btnAgregar.setOnAction(e -> nuevaVentana());
		
		//Lista de personas
		personas = FXCollections.observableArrayList();
		//Tabla
		table = new TableView<>(personas);

		//Columnas de la tabla
		colNombre = new TableColumn<>("NOMBRE");
		colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		
		colApellido = new TableColumn<>("APELLIDOS");
		colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		
		colEdad = new TableColumn<>("EDAD");
		colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
		
		table.getColumns().addAll(colNombre, colApellido, colEdad);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		root.add(table, 0,0,3,3);
		
		//Añadir icono
		Image imagen = new Image(getClass().getResource("/picture/agenda.png").toString());
		stage.getIcons().add(imagen);
		
		
		//Mostrar escena
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("PERSONAS");
		stage.show();
		
				
	}	
	public void nuevaVentana() {
		GridPane root = new GridPane();
		root.setHgap(15);
        root.setVgap(15);
        root.setPadding(new Insets(10, 10, 10, 10));
		
		//Nombre
		Label lbNombre = new Label("Nombre");
		root.add(lbNombre, 0,1,1,1);
		txtNombre = new TextField();
		root.add(txtNombre, 1,1,1,1);
		
		//Apellidos
		Label lbApellidos = new Label("Apellidos");
		root.add(lbApellidos, 0,2,1,1);
		txtApellidos =  new TextField();
		root.add(txtApellidos, 1,2,1,1);
		
		//Edad
		Label lbEdad = new Label("Edad");
		root.add(lbEdad, 0,3,1,1);
		txtEdad = new TextField();
		root.add(txtEdad, 1,3,1,1);
		
		btnGuardar = new Button("Guardar");
		root.add(btnGuardar, 0, 4, 1, 1);
		btnGuardar.setOnAction(e -> addPersona());
		
		btnCancelar =  new Button("Cancelar");
		root.add(btnCancelar, 1, 4, 1, 1);
		btnCancelar.setOnAction(e -> cancelar());
		
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.setTitle("Nueva Persona");
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.show();
		
	}
	
	
    public void addPersona() {
    	
    	String nombre = txtNombre.getText();
    	String apellidos = txtApellidos.getText();
    	
		try {
			int edad = Integer.parseInt (txtEdad.getText());
			Persona p = new Persona(nombre, apellidos, edad);
			
			if(!personas.contains(p)) {
				//Comprobar el rago de la edad
				if(p.getEdad() > 120 || p.getEdad() < 0) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.initOwner(this.btnAgregar.getScene().getWindow());
					alert.setHeaderText(null);
					alert.setTitle("Error");
					alert.setContentText("Edad fuera de rango");
					alert.showAndWait();
				}			
				
				// Asegurarse de que el nombre no esta vacio
				else if(p.getNombre().isEmpty()) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.initOwner(this.btnAgregar.getScene().getWindow());
					alert.setHeaderText(null);
					alert.setTitle("Error");
					alert.setContentText("El nombre no puede estar vacio");
					alert.showAndWait();
				}
				
				//Asegurarse de que el apellido no esta vacio
				else if(p.getApellido().isEmpty()) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.initOwner(this.btnAgregar.getScene().getWindow());
					alert.setHeaderText(null);
					alert.setTitle("Error");
					alert.setContentText("El apellido no puede estar vacio");
					alert.showAndWait();
				}
				else {
		
					//Si todo esta bien se lo notificamos al usuario
					personas.add(p);
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.initOwner(this.btnAgregar.getScene().getWindow());
					alert.setHeaderText(null);
					alert.setTitle("Info");
					alert.setContentText("Persona agregada correctamente");
					alert.showAndWait();
					clearFields();
					
					Stage myStage =(Stage) this.btnCancelar.getScene().getWindow();
					myStage.close();
				}	
			}
			// Comprobar que la persona a introducir no exista ya
			else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.initOwner(this.btnAgregar.getScene().getWindow());
				alert.setHeaderText(null);
				alert.setTitle("Error");
				alert.setContentText("Esa persona ya existe");
				alert.showAndWait();
			}
			//Asegurarse de que la edad es un entero
		}catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initOwner(this.btnAgregar.getScene().getWindow());
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("La edad tiene que ser un entero");
			alert.showAndWait();
		}
    }

	
	//Cerrar la ventana
	public void cancelar() {
		Stage myStage =(Stage) this.btnCancelar.getScene().getWindow();
		myStage.close();
	}
	

    // Limpiar los campos
    public void clearFields() {
    	txtNombre.setText(null);
        txtApellidos.setText(null);
        txtEdad.setText(null);
    }
	
	public static void main(String[] args) {
		launch(args);
		
	}

}
