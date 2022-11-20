package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.ConexionDB;
import javafx.scene.control.Alert;
import model.Deporte;
import model.Equipo;

public class DeporteDAO {
	
	private ConexionDB conexion;
	
	public DeporteDAO () throws SQLException {
		conexion = new ConexionDB();
	}

	public ArrayList<Deporte> selectDeportes() {
		PreparedStatement ps;
		ArrayList <Deporte> lstDeportes = new ArrayList <Deporte>();
		try {
			ps=conexion.getConexion().prepareStatement("select * from Deporte");
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				lstDeportes.add(new Deporte (rs.getInt(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("No se ha podido cargar los Deportes");
			alert.showAndWait();
			e.printStackTrace();
		}
		return lstDeportes;
	}
	
	public Deporte selectDeportePorId (int id) {
		PreparedStatement ps;
		Deporte deporte = null;
		try {
			ps = conexion.getConexion().prepareStatement("select * from Deporte where id_deporte = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				deporte = new Deporte (id, rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("No se ha podido cargar los Deportes");
			alert.showAndWait();
			e.printStackTrace();
		}
		return deporte;
	}
	
	public void insertDeporte (Deporte deporte) {
		String sql = "insert into Deporte (nombre) values (?)";
		PreparedStatement ps;
		Connection conn;
		
		try {
			conn = conexion.getConexion();
			ps = conn.prepareStatement(sql);
			ps.setString(1, deporte.getNombre());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("No se ha insertar el Deportes");
			alert.showAndWait();
			e.printStackTrace();
		}
	}
	
	public boolean existeDeporte (Deporte deporte) {
		String sql = "select * from Deporte where nombre = ?";
		PreparedStatement ps;
		Connection conn;
		
		try {
			conn = conexion.getConexion();
			ps = conn.prepareStatement(sql);
			ps.setString(1, deporte.getNombre());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
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
	
	public void updateDeporte (Deporte deporte) {
		String sql = "update Deporte set nombre = ? where id_deporte = ?";
		PreparedStatement ps;
		Connection conn;
		
		try {
			conn = conexion.getConexion();
			ps = conn.prepareStatement(sql);
			ps.setString(1, deporte.getNombre());
			ps.setInt(2, deporte.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("No se ha modificar el Deportes");
			alert.showAndWait();
			e.printStackTrace();
		}
	}
	
	public boolean deleteDeporte (Deporte deporte) {
		String sql = "delete from Deporte where id_deporte = ?";
		PreparedStatement ps;
		Connection conn;
		
		try {
			conn = conexion.getConexion();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, deporte.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

}
