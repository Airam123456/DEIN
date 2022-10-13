package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Persona;
import javafx.scene.input.MouseEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class EjercicioHController implements Initializable{
	@FXML
	private Label lbFiltrar;
	@FXML
	private TextField txtFiltrar;
	@FXML
	private Button btnImportar;
	@FXML
	private Button btnExportar;
	@FXML
	private TableView <Persona> table;
	@FXML
	private TableColumn<Persona, String> colNombre;
	@FXML
	private TableColumn<Persona, String> colApellido;
	@FXML
	private TableColumn<Persona, String> colEdad;
	@FXML
	private FlowPane flow1;
	@FXML
	private Button btnAgregar;
	@FXML
	private Button btnModificar;
	@FXML
	private Button btnEliminar;
	
	private ObservableList<Persona> personas;

	private Persona p;
	
	private int posicion;
	
	
	
	

	// Event Listener on Button[#btnImportar].onAction
	@FXML
	public void importar(ActionEvent event) {
		// TODO Autogenerated
		Stage newStage = new Stage();
		
		FileChooser fileDialog = new FileChooser();
		fileDialog.setTitle("Open CSV");
		fileDialog.setInitialDirectory(new File("/home/dm2/Escritorio"));
		fileDialog.getExtensionFilters().add(new ExtensionFilter("Archivo CSV", "*.csv", "*.csv"));
		fileDialog.setSelectedExtensionFilter(fileDialog.getExtensionFilters().get(0));
		File archivo = fileDialog.showOpenDialog(newStage);
		
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(archivo));
			int fila = 0;
			String linea = buffer.readLine();
			while(linea != null) {
				if(fila != 0) {
					String[] partes = linea.split(",");
					Persona p = new Persona(partes[0],partes[1],Integer.parseInt(partes[2]));
					personas.add(p);
				}
				fila++;
				linea = buffer.readLine();
			}
			buffer.close();
			table.refresh();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	// Event Listener on Button[#btnExportar].onAction
	@FXML
	public void exportar(ActionEvent event) {
		// TODO Autogenerated
		Stage newStage = new Stage();
		
		FileChooser fileDialog = new FileChooser();
		fileDialog.setInitialDirectory(new File("/home/dm2/Escritorio"));
		fileDialog.setInitialFileName(".csv");
		File archivo = fileDialog.showSaveDialog(newStage);
		
		try {
			PrintWriter write = new PrintWriter(archivo);
			String datos = "Nombre,Apellido,Edad \n";
			for (int i = 0; i < table.getItems().size(); i++) {
				datos += table.getItems().get(i).getNombre() + "," + table.getItems().get(i).getApellido() + "," + table.getItems().get(i).getEdad() + "\n";
				
			}
			write.write(datos);
			write.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	// Event Listener on TableView[#table].onMouseClicked
	@FXML
	public void seleccionar(MouseEvent event) {
		// TODO Autogenerated
		if (table.getSelectionModel().getSelectedItem() != null) {
			posicion = table.getSelectionModel().getSelectedIndex();
			btnModificar.setDisable(false);
			btnEliminar.setDisable(false);
			p = table.getSelectionModel().getSelectedItem();
		}
		
	}
	// Event Listener on Button[#btnAgregar].onAction
	@FXML
	public void agregrar(ActionEvent event) {
		// TODO Autogenerated
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AgregrarPersonaEjerH.fxml"));
	        Parent root = loader.load();
	        AgregrarPersonaEjerHController controller = loader.getController();
	        Scene newScene = new Scene(root);
	        Stage newStage = new Stage();
	        newStage.initModality(Modality.APPLICATION_MODAL);
	        newStage.setResizable(false);
			Stage myStage = (Stage) this.btnAgregar.getScene().getWindow();
			newStage.initOwner(myStage);
			newStage.setScene(newScene);
			newStage.setTitle("Nuevo Usuario");
			newStage.showAndWait();
	          
    		p = controller.getPersona();
	          
			if (!personas.contains(p) && p != null) {
				personas.add(p);
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.initOwner(this.btnAgregar.getScene().getWindow());
				alert.setHeaderText(null);
				alert.setTitle("Info");
				alert.setContentText("Persona agregada correctamente");
				alert.showAndWait();
			}
			else {
				if(p != null) {
					Alert alert= new Alert(Alert.AlertType.ERROR);
					alert.initOwner(this.btnAgregar.getScene().getWindow());
					alert.setHeaderText(null);
					alert.setTitle("ERROR");
					alert.setContentText("Esa persona ya existe");
					alert.showAndWait();
				}
			}
	          
		} catch (IOException e) {
			// TODO: handle exception
			Alert alert = new Alert(Alert.AlertType.ERROR);
	          alert.setHeaderText(null);
	          alert.setTitle("Error");
	          alert.setContentText(e.getMessage());
	          alert.showAndWait();
		}
	}
	// Event Listener on Button[#btnModificar].onAction
	@FXML
	public void modificar(ActionEvent event) {
		// TODO Autogenerated
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AgregrarPersonaEjerH.fxml"));
	        Parent root = loader.load();
	        AgregrarPersonaEjerHController controller = loader.getController();
	        controller.setPersona(p);
	        Scene newScene = new Scene(root);
	        Stage newStage = new Stage();
	        newStage.initModality(Modality.APPLICATION_MODAL);
	        newStage.setResizable(false);
			Stage myStage = (Stage) this.btnModificar.getScene().getWindow();
			newStage.initOwner(myStage);
			newStage.setScene(newScene);
			newStage.setTitle("Modificar Usuario");
			newStage.showAndWait();
	          
    		p = controller.getPersona();
	          
			if (!personas.contains(p) && p != null) {
				personas.set(posicion, p);
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.initOwner(this.btnModificar.getScene().getWindow());
				alert.setHeaderText(null);
				alert.setTitle("Info");
				alert.setContentText("Persona modificada correctamente");
				alert.showAndWait();
			}
			else {
				if(p != null) {
					Alert alert= new Alert(Alert.AlertType.ERROR);
					alert.initOwner(this.btnModificar.getScene().getWindow());
					alert.setHeaderText(null);
					alert.setTitle("ERROR");
					alert.setContentText("Esa persona ya existe");
					alert.showAndWait();
				}
			}
			btnModificar.setDisable(true);
			btnEliminar.setDisable(true);
	          
		} catch (IOException e) {
			// TODO: handle exception
			Alert alert = new Alert(Alert.AlertType.ERROR);
	          alert.setHeaderText(null);
	          alert.setTitle("Error");
	          alert.setContentText(e.getMessage());
	          alert.showAndWait();
		}

	}
	// Event Listener on Button[#btnEliminar].onAction
	@FXML
	public void eliminar(ActionEvent event) {
		// TODO Autogenerated
		Persona p = table.getSelectionModel().getSelectedItem();
		personas.remove(p);
		
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.initOwner(this.btnEliminar.getScene().getWindow());
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText("Persona eliminada correctamente");
		alert.showAndWait();
		btnModificar.setDisable(true);
		btnEliminar.setDisable(true);
	}
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		personas = FXCollections.observableArrayList();
		table.setItems(personas);
		colNombre.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));
		colApellido.setCellValueFactory(new PropertyValueFactory<Persona, String>("apellido"));
		colEdad.setCellValueFactory(new PropertyValueFactory<Persona, String>("edad"));
		colEdad.setStyle( "-fx-alignment: CENTER-RIGHT;");
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		btnModificar.setDisable(true);
		btnEliminar.setDisable(true);	
		
		
		
		// Esta parte a continuacion esta copiada de: https://code.makery.ch/blog/javafx-8-tableview-sorting-filtering/
		
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Persona> filteredData = new FilteredList<>(personas, p -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		txtFiltrar.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(person -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (person.getNombre().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (person.getApellido().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Persona> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
		
	}
}