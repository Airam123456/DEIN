package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.EventoDAO;
import dao.OlimpiadasDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.MenuItem;

import javafx.scene.control.ListView;

import javafx.scene.control.ComboBox;

import javafx.scene.control.RadioButton;

import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Evento;
import model.Olimpiada;
import javafx.scene.control.Menu;

public class OlimpiadasController implements Initializable {
	@FXML
	private Menu menuDeportista;
	@FXML
	private MenuItem aniadirDeportista;
	@FXML
	private MenuItem gestionarDeportista;
	@FXML
	private Menu menuDeporte;
	@FXML
	private MenuItem aniadirDeporte;
	@FXML
	private MenuItem gestionarDeporte;
	@FXML
	private Menu menuEquipos;
	@FXML
	private MenuItem aniadirEquipos;
	@FXML
	private MenuItem gestionarEquipos;
	@FXML
	private ComboBox cboxOlimpiadas;
	@FXML
	private Button aniadirOlimpiada;
	@FXML
	private Button editarOlimpiada;
	@FXML
	private Button borrarOlimpiada;
	@FXML
	private ListView listEventos;
	@FXML
	private Button aniadirEvento;
	@FXML
	private Button editarEvento;
	@FXML
	private Button borrarEvento;
	@FXML
	private Button verParticipaciones;
	@FXML
	private RadioButton rbtnVerano;
	@FXML
	private ToggleGroup estacion;
	@FXML
	private RadioButton rbtnInvierno;
	
	private Olimpiada o;
	private OlimpiadasDAO cargarOlimpiada;
	private EventoDAO cargarEvento;
	
	private ObservableList<Olimpiada> olimpiadas;
	private ObservableList<Evento> eventos;

	// Event Listener on MenuItem[#aniadirDeportista].onAction
	@FXML
	public void aniadirDeportista(ActionEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on MenuItem[#gestionarDeportista].onAction
	@FXML
	public void gestionarDeportista(ActionEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on MenuItem[#aniadirDeporte].onAction
	@FXML
	public void aniadirDeporte(ActionEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on MenuItem[#gestionarDeporte].onAction
	@FXML
	public void gestionarDeporte(ActionEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on MenuItem[#aniadirEquipos].onAction
	@FXML
	public void aniadirEquipos(ActionEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on MenuItem[#gestionarEquipos].onAction
	@FXML
	public void gestionarEquipos(ActionEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ComboBox[#cboxOlimpiadas].onAction
	@FXML
	public void selectOlimpiadas(ActionEvent event) {
		// TODO Autogenerated

		eventos = FXCollections.observableArrayList();

		try {
			cargarEvento = new EventoDAO();
			eventos.addAll(cargarEvento
					.selectEventosPorOlimpiada((Olimpiada) cboxOlimpiadas.getSelectionModel().getSelectedItem()));

			listEventos.setItems(eventos);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Event Listener on Button[#aniadirOlimpiada].onAction
	@FXML
	public void aniadirOlimpiada(ActionEvent event) {
		// TODO Autogenerated
		
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AniadirOlimpiada.fxml"));
			Parent root = loader.load();
			AniadirOlimpiadaController controller = loader.getController();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setResizable(false);
			Stage myStage = (Stage) this.aniadirOlimpiada.getScene().getWindow();
			newStage.initOwner(myStage);
			newStage.setScene(newScene);
			newStage.setTitle("Nueva Olimpiada");
			newStage.showAndWait();
			
			o = controller.getOlimpiada();
			cargarOlimpiada.insertOlimpiada(o);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
		}
	
	}

	// Event Listener on Button[#editarOlimpiada].onAction
	@FXML
	public void editarOlimpiada(ActionEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on Button[#borrarOlimpiada].onAction
	@FXML
	public void borrarOlimpiada(ActionEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ListView[#listEventos].onMouseClicked
	@FXML
	public void selectEvento(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on Button[#aniadirEvento].onAction
	@FXML
	public void aniadirEvento(ActionEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on Button[#editarEvento].onAction
	@FXML
	public void editarEvento(ActionEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on Button[#borrarEvento].onAction
	@FXML
	public void borrarEvento(ActionEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on Button[#verParticipaciones].onAction
	@FXML
	public void verParticipaciones(ActionEvent event) {
		// TODO Autogenerated
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		olimpiadas = FXCollections.observableArrayList();

		try {
			cargarOlimpiada = new OlimpiadasDAO();

			if(rbtnVerano.isSelected()) {
				olimpiadas.addAll(cargarOlimpiada.selectOlimpiadasVerano());
			}
			else {
				olimpiadas.addAll(cargarOlimpiada.selectOlimpiadasInvierno());
			}

			cboxOlimpiadas.setItems(olimpiadas);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
