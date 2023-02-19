module EjercicioP {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires EjercicioO;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controller to javafx.graphics, javafx.fxml;
	
}
