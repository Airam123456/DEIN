package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.DAOProducto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.MenuItem;

import javafx.scene.control.ContextMenu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Producto;
import javafx.scene.control.CheckBox;

import javafx.scene.control.TableView;

import javafx.scene.control.Menu;

import javafx.scene.control.TableColumn;

import javafx.scene.input.ContextMenuEvent;

public class ProductosController implements Initializable {
	@FXML
	private Menu menuAyuda;
	@FXML
	private MenuItem btnAcercaDe;
	@FXML
	private Button btnCrear;
	@FXML
	private Button btnActualizar;
	@FXML
	private Button btnLimpiar;
	@FXML
	private TableView tasble;
	@FXML
	private TableColumn columCodigo;
	@FXML
	private TableColumn columNombre;
	@FXML
	private TableColumn columPrecio;
	@FXML
	private TableColumn columDisponible;
	@FXML
	private ContextMenu contextMenu;
	@FXML
	private MenuItem verImagenMenuContex;
	@FXML
	private MenuItem borrarProdcuto;
	@FXML
	private TextField txtCodigo;
	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtPrecio;
	@FXML
	private CheckBox checkDisponible;
	@FXML
	private Button btnImagen;
	@FXML
	private ImageView imagenPeque;

	private ObservableList<Producto> productos;
	private Producto p;
	private DAOProducto dao;
	private InputStream foto;
	private File archivo;

	// Event Listener on Menu[#menuAyuda].onAction
	@FXML
	public void ayuda(ActionEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on MenuItem[#btnAcercaDe].onAction
	@FXML
	public void acercaDe(ActionEvent event) {
		// TODO Autogenerated

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText("Gestion de productoss 1.0 \nAuotor: Airam Brito Triana");
		alert.showAndWait();
	}

	// Event Listener on Button[#btnCrear].onAction
	@FXML
	public void crear(ActionEvent event) {
		// TODO Autogenerated

		String codigo = txtCodigo.getText();
		String nombre = txtNombre.getText();
		double precio = 0;
		int disp = 0;

		String error = "";

		if (nombre.isEmpty()) {
			error += "\n El campo Nombre no puede estar vacio";
		}

		if (codigo.isEmpty()) {
			error += "\n El campo Codigo no puede estar vacio";
		}

		if (codigo.length() != 5) {
			error += "\n El campo Codigo tiene que ser de 5 caracteres";
		}

		try {
			precio = Double.parseDouble(txtPrecio.getText());

		} catch (Exception e) {
			error += "\n El Precio tiene que se un decimal";
		}

		if (checkDisponible.isSelected())
			disp = 1;
		else
			disp = 0;

		try {
			
			foto = (InputStream) new FileInputStream(archivo);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		p = new Producto(codigo, nombre, precio, disp, foto);

		try {
			dao = new DAOProducto();
			if (dao.existeProducto(p) == true)
				error += "\n Producto con el mismo codigo";

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		if (error.equals("")) {

			if (dao.insertProducto(p)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.initOwner(this.btnCrear.getScene().getWindow());
				alert.setHeaderText(null);
				alert.setTitle("Info");
				alert.setContentText("Producto agregado correctamente");
				alert.showAndWait();

				recargarTabla();
				clear();
			}

		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText(error);
			alert.showAndWait();
		}
	}

	// Event Listener on Button[#btnActualizar].onAction
	@FXML
	public void actualizar(ActionEvent event) {
		// TODO Autogenerated

		String codigo = txtCodigo.getText();
		String nombre = txtNombre.getText();
		double precio = 0;
		int disp = 0;

		String error = "";

		if (nombre.isEmpty()) {
			error += "\n El campo Nombre no puede estar vacio";
		}

		if (codigo.isEmpty()) {
			error += "\n El campo Codigo no puede estar vacio";
		}

		if (codigo.length() != 5) {
			error += "\n El campo Codigo tiene que ser de 5 caracteres";
		}

		try {
			precio = Double.parseDouble(txtPrecio.getText());

		} catch (Exception e) {
			error += "\n El Precio tiene que se un decimal";
		}

		if (checkDisponible.isSelected())
			disp = 1;
		else
			disp = 0;

		p = new Producto(codigo, nombre, precio, disp, foto);

		if (error.equals("")) {

			if (dao.updateProducto(p)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.initOwner(this.btnCrear.getScene().getWindow());
				alert.setHeaderText(null);
				alert.setTitle("Info");
				alert.setContentText("Producto actualizado correctamente");
				alert.showAndWait();

				recargarTabla();
				clear();
				txtCodigo.setDisable(false);
				btnActualizar.setDisable(true);
				btnCrear.setDisable(false);
			}

		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText(error);
			alert.showAndWait();
		}
	}

	// Event Listener on Button[#btnLimpiar].onAction
	@FXML
	public void Limpiar(ActionEvent event) {
		// TODO Autogenerated
		clear();

	}

	public void clear() {
		txtCodigo.clear();
		txtNombre.clear();
		txtPrecio.clear();
	}

	// Event Listener on TableView[#tasble].onContextMenuRequested
	@FXML
	public void abrirContexMenu(ContextMenuEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on TableView[#tasble].onMouseClicked
	@FXML
	public void selectProducto(MouseEvent event) {
		// TODO Autogenerated

		p = (Producto) tasble.getSelectionModel().getSelectedItem();

		txtCodigo.setText(p.getCodigo());
		txtNombre.setText(p.getNombre());
		txtPrecio.setText(Double.toString(p.getPrecio()));

		if (p.getDisponible() == 1)
			checkDisponible.setSelected(true);
		else
			checkDisponible.setSelected(false);

		
		try {
			imagenPeque.setImage(new Image(p.getFoto()));
		} catch (Exception e) {
			// TODO: handle exception
			imagenPeque.setImage(null);
		}
		

		txtCodigo.setDisable(true);
		btnActualizar.setDisable(false);
		btnCrear.setDisable(true);

	}

	// Event Listener on MenuItem[#verImagenMenuContex].onAction
	@FXML
	public void verImagenGrande(ActionEvent event) {
		// TODO Autogenerated

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Imaagen.fxml"));
			Parent root = loader.load();
			ImaagenController controller = loader.getController();
			controller.setImagen(imagenPeque);
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.setResizable(false);
			newStage.setScene(newScene);
			newStage.setTitle("Imagen");
			newStage.showAndWait();

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

	// Event Listener on MenuItem[#borrarProdcuto].onAction
	@FXML
	public void borrar(ActionEvent event) {
		// TODO Autogenerated

		p = (Producto) tasble.getSelectionModel().getSelectedItem();

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Borrar Deportista");
		alert.setContentText("Seguro que desea BORRAR este Deportista?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			try {
				if (dao.deleteProducto(p)) {

					alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle("Info");
					alert.setContentText("Producto borrado correctamente");
					alert.showAndWait();
					recargarTabla();
					clear();
					txtCodigo.setDisable(false);
					btnActualizar.setDisable(true);
					btnCrear.setDisable(false);
				} else {
					alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle("Error");
					alert.setContentText("No se puede borrar, existen dependencias");
					alert.showAndWait();
				}

			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}

	// Event Listener on CheckBox[#checkDisponible].onAction
	@FXML
	public void disponible(ActionEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on Button[#btnImagen].onAction
	@FXML
	public void selecionarImagen(ActionEvent event) {
		// TODO Autogenerated

		importar(new Stage());


	}

	private void importar(Stage stage) {

		FileChooser fileDialog = new FileChooser();
		fileDialog.setTitle("Open CSV");
		fileDialog.setInitialDirectory(new File("src/images"));
		archivo = fileDialog.showOpenDialog(stage);
		
		try {
			
			foto = (InputStream) new FileInputStream(archivo);
			imagenPeque.setImage(new Image(foto));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		

	}

	public void recargarTabla() {
		productos = FXCollections.observableArrayList();
		dao = new DAOProducto();
		ArrayList<Producto> productoDAO;

		productoDAO = dao.selectProductos();

		productoDAO.forEach(producto -> productos.add(producto));

		tasble.setItems(productos);

		columNombre.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));
		columCodigo.setCellValueFactory(new PropertyValueFactory<Producto, String>("codigo"));
		columPrecio.setCellValueFactory(new PropertyValueFactory<Producto, String>("precio"));
		columDisponible.setCellValueFactory(new PropertyValueFactory<Producto, String>("disponible"));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		productos = FXCollections.observableArrayList();
		dao = new DAOProducto();
		ArrayList<Producto> productoDAO;

		productoDAO = dao.selectProductos();

		productoDAO.forEach(producto -> productos.add(producto));

		tasble.setItems(productos);

		columNombre.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));
		columCodigo.setCellValueFactory(new PropertyValueFactory<Producto, String>("codigo"));
		columPrecio.setCellValueFactory(new PropertyValueFactory<Producto, String>("precio"));
		columDisponible.setCellValueFactory(new PropertyValueFactory<Producto, String>("disponible"));

		btnActualizar.setDisable(true);
	}
}
