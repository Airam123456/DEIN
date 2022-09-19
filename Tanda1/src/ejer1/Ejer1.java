package ejer1;
	


import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Ejer1 extends Application {
	
	private CheckBox cbxPregunta;
	private ListView<String> sports;
	private TextField tfProfesion;
	private TextField tfHermanos;
	private ComboBox<String> edades;
	
	@Override
	public void start(Stage Stage) {
		GridPane root = new GridPane();
		root.setVgap(10);
		root.setHgap(10);
		root.setPadding(new Insets(10, 10, 10, 10));
		
		Label lbtitulo = new Label("ENCUESTA");
		lbtitulo.setStyle("-fx-font-weight: bold; -fx-font-size: 25px" );
		GridPane.setHalignment(lbtitulo, HPos.CENTER);
		
		root.add(lbtitulo, 0, 0, 4,1);
		
		Label lbProfesion = new Label("Profesión: ");
		root.add(lbProfesion, 0, 1,1,1);
		tfProfesion = new TextField();
		root.add(tfProfesion, 1, 1,3,1);
		
		Label lbHermanos = new Label("Nº Hermanos: ");
		root.add(lbHermanos, 0, 2,1,1);
		tfHermanos = new TextField();
		root.add(tfHermanos, 1, 2,1,1);
		
		Label lbEdad = new Label("Edad: ");
		root.add(lbEdad, 2, 2,1,1);
		edades = new ComboBox<>();
        edades.getItems().addAll("Menores de 18", "Entre 18 y 30", "Entre 31 y 50", "Entre 51 y 70", "Mayores de 70");
        root.add(edades, 3, 2,1,1);
        
        Label lbSexo = new Label("Sexo: ");
        root.add(lbSexo, 0, 3, 1, 1);
        RadioButton rbtHombre = new RadioButton("Hombre");
        root.add(rbtHombre, 1, 3, 1, 1);
        RadioButton rbtMujer = new RadioButton("Mujer");
        root.add(rbtMujer, 2, 3, 1, 1);
        RadioButton rbtOtro = new RadioButton("Otro");
        root.add(rbtOtro, 3, 3, 1, 1);
        ToggleGroup group = new ToggleGroup();
        group.getToggles().addAll(rbtHombre,rbtMujer,rbtOtro);
        
        
        cbxPregunta = new CheckBox("¿Práctica algún deporte?");
        cbxPregunta.selectedProperty().addListener(this::changedcb);
        root.add(cbxPregunta, 0, 4, 1, 1);
        
        Label lbCual = new Label("¿Cúal?");
        root.add(lbCual, 2, 4, 1, 1);
        sports = new ListView<>();
        sports.getItems().addAll("Tenis", "Futbol", "Baloncesto");
        sports.setDisable(true);
        root.add(sports, 2, 5, 1, 1);
        
        
        
        Label lbGrado = new Label("Marque su grado de afición");
        root.add(lbGrado, 0, 6, 4, 1);
		
		Label lbCompras = new Label("Compras: ");
		root.add(lbCompras, 0, 7, 1, 1);
        Slider slCompras = new Slider(0, 10, 5);
        slCompras.setMajorTickUnit(1);
        slCompras.setShowTickLabels(true);
        slCompras.setShowTickMarks(true);
        slCompras.setSnapToTicks(true);
        root.add(slCompras, 1, 7, 3, 1);

		Label lbTele = new Label("Ver Televisión: ");
		root.add(lbTele, 0, 8, 1, 1);
        Slider slTele = new Slider(0, 10, 5);
        slTele.setMajorTickUnit(1);
        slTele.setShowTickLabels(true);
        slTele.setShowTickMarks(true);
        slTele.setSnapToTicks(true);
        root.add(slTele, 1, 8, 3, 1);
        

		Label lbCine = new Label("Ir al Cine: ");
		root.add(lbCine, 0, 9, 1, 1);
        Slider sbCine = new Slider(0, 10, 5);
        sbCine.setMajorTickUnit(1);
        sbCine.setShowTickLabels(true);
        sbCine.setShowTickMarks(true);
        sbCine.setSnapToTicks(true);
        root.add(sbCine, 1, 9, 3, 1);
        
        

        

		Button btnAceptar = new Button("Aceptar");
		root.add(btnAceptar, 0 , 10, 2, 1);
		btnAceptar.setOnAction(e -> mostrarAlertInfo(Stage));
		GridPane.setHalignment(btnAceptar, HPos.CENTER);
		
		
		Button btnCancelar = new Button("Cancelar");
		root.add(btnCancelar, 2 , 10, 2, 1);
		btnCancelar.setOnAction(e -> cancel());
		GridPane.setHalignment(btnCancelar, HPos.CENTER);
		
		
		
		Scene scene = new Scene(root);
		Stage.setScene(scene);
		Stage.setTitle("Encuesta");
		Stage.show();

	}
	
	public void changedcb(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if (cbxPregunta.isSelected()) {
            sports.setDisable(false);
        } else  {
            sports.setDisable(true);
        }
    }
	
	
	
	
	private void mostrarAlertInfo(Window win) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.initOwner(win);
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText("Profesión: " + tfProfesion.getText() + 
		"\n Nº de hermanos: " + tfHermanos.getText() +
		"\n Edad: " + edades.getSelectionModel().getSelectedItem() +
		"\n Sexo: ");
		
		alert.showAndWait();
	}
	
    public void cancel() {
    	System.exit(0);
    }
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
