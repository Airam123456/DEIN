package ejer2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Persona;

public class Ejer2 extends Application{
	
	private TextField txtNombre, txtApellidos, txtEdad;
	private TableView<Persona> table;
	private ObservableList<Persona> personas;
	
	
	public void start (Stage stage) {
		
		GridPane root = new GridPane();
		root.setVgap(10);
		root.setHgap(10);
		root.setPadding(new Insets(10, 10, 10, 10));
		
		
		Label lbNombre = new Label("Nombre");
		root.add(lbNombre, 0,1,1,1);
		txtNombre = new TextField();
		root.add(txtNombre, 0,2,1,1);
		
		Label lbApellidos = new Label("Apellidos");
		root.add(lbApellidos, 0,3,1,1);
		txtApellidos =  new TextField();
		root.add(txtApellidos, 0,4,1,1);
		
		Label lbEdad = new Label("Edad");
		root.add(lbEdad, 0,5,1,1);
		txtEdad = new TextField();
		root.add(txtEdad, 0,6,1,1);
		
		Button btnAgregar = new Button("Agregar Persona");
		root.add(btnAgregar, 0,7,1,1);
		
		btnAgregar.setOnAction(e -> addPersona());
		
		personas = FXCollections.observableArrayList();
		table = new TableView<>(personas);
		
		
		TableColumn<Persona, String> colNombre = new TableColumn<>("NOMBRE");
		colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		
		
		TableColumn<Persona, String> colApellido = new TableColumn<>("APELLIDOS");
		colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		
		TableColumn<Persona, String> colEdad = new TableColumn<>("EDAD");
		colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
		
		table.getColumns().addAll(colNombre, colApellido, colEdad);
		
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("PERSONAS");
		stage.show();
		
	}
	
	
	
	String nombre = txtNombre.getText();
	String apellidos = txtApellidos.getText();
	int edad = Integer.parseInt (txtEdad.getText());
	
    public Persona getPersona() {
        return new Persona(nombre, apellidos, edad);
    }
	
	
    public void addPersona() {
        Persona p = getPersona();
        table.getItems().add(p);
        clearFields();
    }

    public void clearFields() {
    	txtNombre.setText(null);
        txtApellidos.setText(null);
        txtEdad.setText(null);
    }
	
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
