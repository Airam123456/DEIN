package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import conexion.ConexionDB;
import javafx.event.ActionEvent;

import javafx.scene.control.RadioButton;

public class Informe2Controller {
	@FXML
	private RadioButton rbtnInforme;
	@FXML
	private ToggleGroup radio;
	@FXML
	private RadioButton rbtnInformeCalculos;
	@FXML
	private RadioButton rbtnInformeSubinformes;
	@FXML
	private Button btnAceptar;
	@FXML
	private Button btnCancelar;
	private String ruta = "/informes/Agenda_1.jasper";

	// Event Listener on RadioButton[#rbtnInforme].onAction
	@FXML
	public void persona(ActionEvent event) {
		// TODO Autogenerated
		ruta = "/informes/Agenda_1.jasper";
	}
	// Event Listener on RadioButton[#rbtnInformeCalculos].onAction
	@FXML
	public void calculos(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on RadioButton[#rbtnInformeSubinformes].onAction
	@FXML
	public void subinfomre(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnAceptar].onAction
	@FXML
	public void aceptar(ActionEvent event) {
		// TODO Autogenerated
		
		try {
			ConexionDB con = new ConexionDB();
			JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource(ruta));
			JasperPrint jprint = JasperFillManager.fillReport(report, null, con.getConexion());
	        JasperViewer viewer = new JasperViewer(jprint, false);
	        viewer.setTitle("Agenda");
	        viewer.setVisible(true);
	    } catch (Exception e) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setHeaderText(null);
	        alert.setTitle("ERROR");
	        alert.setContentText("Ha ocurrido un error");
	        alert.showAndWait();
	        e.printStackTrace();
	    }
	
	}
	// Event Listener on Button[#btnCancelar].onAction
	@FXML
	public void cancelar(ActionEvent event) {
		// TODO Autogenerated
		Stage myStage =(Stage) this.btnCancelar.getScene().getWindow();
		myStage.close();
	}
}
