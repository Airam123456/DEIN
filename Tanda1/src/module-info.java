module HelloFX {
	requires javafx.controls;
	requires javafx.graphics;
	requires java.xml;
	requires javafx.base;
	
	opens ejer1 to javafx.graphics, javafx.fxml;
	opens ejer2 to javafx.graphics, javafx.fxml;
	opens model to javafx.graphics, javafx.fxml, javafx.base;
	opens ejer3 to javafx.graphics, javafx.fxml;
	opens ejer4 to javafx.graphics, javafx.fxml;
	opens ejer5 to javafx.graphics, javafx.fxml;
	opens ejer6 to javafx.graphics, javafx.fxml;
}
