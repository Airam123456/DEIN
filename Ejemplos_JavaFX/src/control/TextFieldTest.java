// TextFieldTest.java
package control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TextFieldTest extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Create a TextFiled with an empty string as its initial text
		TextField firstNameFld = new TextField();
		TextField lastNameFld = new TextField();
	
		// Both fields should be wide enough to display 15 chars
		firstNameFld.setPrefColumnCount(15);
		lastNameFld.setPrefColumnCount(15);
		
		
		
		// Set ActionEvent handlers for both fields
		firstNameFld.setOnAction(e -> nameChanged("First Name"));
		lastNameFld.setOnAction(e -> nameChanged("Last Name"));
	
		GridPane root = new GridPane();
		root.setHgap(10);
		root.setVgap(5);
		root.addRow(0, new Label("First Name:"), firstNameFld);
		root.addRow(1, new Label("Last Name:"), lastNameFld);
		root.setStyle("-fx-padding: 10;" + 
		              "-fx-border-style: solid inside;" + 
		              "-fx-border-width: 2;" +
		              "-fx-border-insets: 5;" + 
		              "-fx-border-radius: 5;" + 
		              "-fx-border-color: blue;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Using TextField Controls");
		stage.show();
	}
	
	public void nameChanged(String fieldName) {
		System.out.println("Action event fired on " + fieldName);
	}
	
	
}
