package ejer6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Persona;

public class Ejer6 extends Application{
	
	private TextField txtNombre, txtApellidos, txtEdad, txtFiltrar;
	private TableView<Persona> table;
	private ObservableList<Persona> personas;
	private TableColumn<Persona, String> colNombre;
	private TableColumn<Persona, String> colApellido;
	private TableColumn<Persona, String> colEdad;
	private Button btnAgregar, btnGuardar, btnCancelar, btnModificar, btnEliminar, btnImportar, btnExportar;
	private TableViewSelectionModel<Persona> tsm;
	private int posicion;
	private Label lbFiltrar;
	
	
	
	private Persona p;
	
	
	
	
	public void start (Stage stage) {
		
		GridPane root = new GridPane();
		root.setVgap(10);
		root.setHgap(10);
		root.setPadding(new Insets(10, 10, 10, 10));
		
		
		//Boton agregar
		btnAgregar = new Button("Agregar Persona");
		btnAgregar.setOnAction(e -> nuevaVentana(table,"a"));
		
		//Boton modificar
		btnModificar = new Button("Modificar Persona");
		btnModificar.setOnAction(e -> nuevaVentana(table,"m"));
		
		//Boton eliminar
		btnEliminar = new Button("Eliminar Persona");
		btnEliminar.setOnAction(e -> eliminar());
		
		btnModificar.setDisable(true);
		btnEliminar.setDisable(true);
		
		//Filtrar
		lbFiltrar = new Label("Filtrar por nombre: ");
		txtFiltrar = new TextField();
		
		//Boton importar
		btnImportar =  new Button("Importar");
		btnImportar.setOnAction(e -> importar(stage));
		
		//Boton exportar
		btnExportar = new Button("Exportar");
		btnExportar.setOnAction(e -> exportar(stage));
		
		//Lista de personas
		personas = FXCollections.observableArrayList();
		//Tabla
		table = new TableView<>(personas);
		
		EventHandler<MouseEvent> selecionarDato = e -> seleccionar();
		table.addEventHandler(MouseEvent.MOUSE_CLICKED, selecionarDato);

		//Columnas de la tabla
		colNombre = new TableColumn<>("NOMBRE");
		colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		
		colApellido = new TableColumn<>("APELLIDOS");
		colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		
		colEdad = new TableColumn<>("EDAD");
		colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
		colEdad.setStyle( "-fx-alignment: CENTER-RIGHT;");
		
		table.getColumns().addAll(colNombre, colApellido, colEdad);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		root.add(table, 0,1,1,1);
		
		//A??adir icono
		Image imagen = new Image(getClass().getResource("/picture/agenda.png").toString());
		stage.getIcons().add(imagen);
		
		//Hace que la tabla crezca
		ColumnConstraints cc1 = new ColumnConstraints();
		RowConstraints rc1 = new RowConstraints();
		RowConstraints rc2 = new RowConstraints();
		cc1.setHgrow(Priority.ALWAYS);
		rc2.setVgrow(Priority.ALWAYS);
		root.getColumnConstraints().add(cc1);
		root.getRowConstraints().addAll(rc1,rc2);
		
		
		//Metemos los botones en un flow pane
		FlowPane flow1 = new FlowPane(5,5);
		flow1.setAlignment(Pos.CENTER);
		flow1.getChildren().addAll(btnAgregar, btnModificar, btnEliminar);
		root.add(flow1, 0, 2, 1, 1);
		
		//Metemos los botones en un flow pane
		FlowPane flow2 = new FlowPane(15,15);
		flow2.setPadding(new Insets(15));
		flow2.setAlignment(Pos.CENTER);
		flow2.getChildren().addAll(lbFiltrar, txtFiltrar, btnImportar,btnExportar);
		root.add(flow2, 0, 0, 1, 1);
		
		
		
		//Mostrar escena
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("PERSONAS");
		stage.show();
		
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
	private void importar(Stage stage) {
		
		FileChooser fileDialog = new FileChooser();
		fileDialog.setTitle("Open CSV");
		fileDialog.setInitialDirectory(new File("/home/dm2/Escritorio"));
		fileDialog.getExtensionFilters().add(new ExtensionFilter("Archivo CSV", "*.csv", "*.csv"));
		fileDialog.setSelectedExtensionFilter(fileDialog.getExtensionFilters().get(0));
		File archivo = fileDialog.showOpenDialog(stage);
		
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
	
	private void exportar(Stage stage) {
		
		FileChooser fileDialog = new FileChooser();
		fileDialog.setInitialDirectory(new File("/home/dm2/Escritorio"));
		fileDialog.setInitialFileName(".csv");
		File archivo = fileDialog.showSaveDialog(stage);
		
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
	
	public void nuevaVentana(TableView<Persona> table, String tipo) {
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
		
		if(tipo.equals("m")) {
			
			tsm = table.getSelectionModel();
			txtNombre.setText(((Persona) tsm.getSelectedItem()).getNombre());
			txtApellidos.setText(((Persona) tsm.getSelectedItem()).getApellido());
			txtEdad.setText(((Persona) tsm.getSelectedItem()).getEdad()+"");
			posicion = table.getSelectionModel().getSelectedIndex();
		}
		
		
		//Botones
		btnGuardar = new Button("Guardar");
		//root.add(btnGuardar, 0, 4, 1, 1);
		btnGuardar.setOnAction(e -> addPersona(tipo));
		
		btnCancelar =  new Button("Cancelar");
		//root.add(btnCancelar, 1, 4, 1, 1);
		btnCancelar.setOnAction(e -> cancelar());
		
		//Metemos los botones en un flow pane
		FlowPane flow = new FlowPane(5,5);
		flow.setAlignment(Pos.CENTER);
		flow.getChildren().addAll(btnGuardar, btnCancelar);
		root.add(flow, 0, 4, 2, 1);
		
		
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        if(tipo.equals("a")) {
            newStage.setTitle("Nueva Persona");
        }
        else {
            newStage.setTitle("Modifivar Persona");
        }
        
        btnModificar.setDisable(true);
		btnEliminar.setDisable(true);
        
        newStage.setResizable(false);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.show();
		
	}
	
	
    public void addPersona(String tipo) {
    	
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
		
					//Se comprueba se estamos agregando o modificando
					if(tipo.equals("a")) {
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
					else {
						//Si todo esta bien se lo notificamos al usuario
						personas.set(posicion,p);
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.initOwner(this.btnAgregar.getScene().getWindow());
						alert.setHeaderText(null);
						alert.setTitle("Info");
						alert.setContentText("Persona modificada correctamente");
						alert.showAndWait();
						clearFields();
						
						Stage myStage =(Stage) this.btnCancelar.getScene().getWindow();
						myStage.close();
					}

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
    
    //Sellecionar entrarda y habilitar botones
	public void seleccionar() {
		tsm = table.getSelectionModel();
		btnModificar.setDisable(false);
		btnEliminar.setDisable(false);
	}

	
	//Cerrar la ventana
	public void cancelar() {
		Stage myStage =(Stage) this.btnCancelar.getScene().getWindow();
		myStage.close();
	}
	
	public void eliminar() {
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
