module Examen2 {
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	requires jasperreports;
	requires javafx.web;
	requires javafx.base;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controllers to javafx.graphics, javafx.fxml;
	opens model to javafx.base;
}
