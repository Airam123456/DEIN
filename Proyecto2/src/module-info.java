module Proyecto2 {
	requires javafx.controls;
	requires java.desktop;
	requires javafx.web;
	requires javafx.fxml;
	requires javafx.swing;
	requires javafx.media;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;
	requires org.kordamp.ikonli.javafx;

	opens application to javafx.graphics, javafx.fxml;
//	opens controller to javafx.graphics, javafx.fxml;
	opens model to javafx.base, javafx.fxml;
//	opens dao to javafx.base;
}
