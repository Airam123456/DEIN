module Proyecto2 {
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	requires org.kordamp.ikonli.javafx;
	requires jasperreports;
	requires javafx.web;
	requires javafx.base;
	requires javafx.swing;
	requires jPDFViewerFX;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controllers to javafx.graphics, javafx.fxml;
	opens model to javafx.base;
}
