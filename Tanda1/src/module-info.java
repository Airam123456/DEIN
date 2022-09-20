module HelloFX {
	requires javafx.controls;
	requires javafx.graphics;
	requires java.xml;
	requires javafx.base;
	
	opens ejer1 to javafx.graphics, javafx.fxml;
	opens ejer2 to javafx.graphics, javafx.fxml;
}
