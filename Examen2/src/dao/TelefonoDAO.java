package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.ConexionDB;
import javafx.scene.control.Alert;
import model.Telefono;

public class TelefonoDAO {
	private ConexionDB cn;
	
	public TelefonoDAO () throws SQLException {
		cn = new ConexionDB();
	}
	
	public ArrayList<Telefono> selectTelefonos (String dni){
		PreparedStatement ps;
		ArrayList<Telefono> lstTelefonos= new ArrayList<Telefono>();
		
		try {
			ps = cn.getConexion().prepareStatement("select id, dni, telefono from telefono where dni = ?");
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				lstTelefonos.add(new Telefono(rs.getInt(1), rs.getString(2), rs.getString(3)));
				
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("No se han podido cargar los Telefonos");
			alert.showAndWait();
			e.printStackTrace();
		}
		
		return lstTelefonos;
	}
	
	public boolean existeTelefono (String dni, String tlf) {
		PreparedStatement ps;
		
		try {
			ps= cn.getConexion().prepareStatement("select telefono from telefono where dni = ? and telefono = ?");
			ps.setString(1, dni);
			ps.setString(2, tlf);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Error de base de datos");
			alert.showAndWait();
			return true;
		}
		return false;
	
	}
	
	
	public void insertTelefono (Telefono tlf)  {
		PreparedStatement ps;
		
		try {
			ps = cn.getConexion().prepareStatement("insert into telefono (dni, telefono) values (?,?)");
			ps.setString(1, tlf.getDni());
			ps.setString(2, tlf.getTelf());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("No se ha podido insertar el Telefono");
			alert.showAndWait();
			e.printStackTrace();
		}

		
	}
	
	public void deleteTelefono (Telefono tlf)  {
		PreparedStatement ps;
		
		try {
			ps=cn.getConexion().prepareStatement("delete from telefono where id = ?");
			ps.setInt(1, tlf.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("No se ha podido borrar el Telefono");
			alert.showAndWait();
			e.printStackTrace();
		}

	}
}
