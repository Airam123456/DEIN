package dao;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import conexion.ConexionDB;
import javafx.scene.control.Alert;
import model.Deporte;
import model.Deportista;
import model.Equipo;

public class DeportistaDAO {

	private ConexionDB conexion;
	
	public DeportistaDAO() throws SQLException {
		conexion = new ConexionDB();
	}

	public ArrayList<Deportista> selectDeportista() {
		PreparedStatement ps;
		ArrayList <Deportista> lstDeportistas = new ArrayList <Deportista>();
		
		try {
			ps=conexion.getConexion().prepareStatement("select *  from Deportista");
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				lstDeportistas.add(new Deportista (rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getBinaryStream(6)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("No se ha podido cargar los Deportistas");
			alert.showAndWait();
			e.printStackTrace();
		}
		return lstDeportistas;
	}
	
	public Deportista selectDeportistaPorId (int id) {
		PreparedStatement ps;
		Deportista deportista = null;
		try {
			ps= conexion.getConexion().prepareStatement("select * from Deportista where id_deportista = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				deportista = new Deportista (id, rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getBinaryStream(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("No se ha podido cargar los Deportistas");
			alert.showAndWait();
			e.printStackTrace();
		}
		return deportista;
	}
	
	public InputStream selectFoto (int id) {
		InputStream foto = null;
		PreparedStatement ps;
		
		try {
			ps= conexion.getConexion().prepareStatement("select foto from Deportista where id_deportista = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				foto = rs.getBinaryStream(1); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("No se ha podido cargar la foto");
			alert.showAndWait();
			e.printStackTrace();
		}
		return foto;
	}
	
	
	public void insertDeportista (Deportista deportista) {
		String sql = "insert into Deportista (nombre, sexo, peso, altura, foto) values (?,?,?,?,?)";
		PreparedStatement ps;
		Connection conn;
		
		try {
			conn = conexion.getConexion();
			ps = conn.prepareStatement(sql);
			ps.setString(1, deportista.getNombre());
			ps.setString(2, deportista.getSexo());
			ps.setInt(3, deportista.getPeso());
			ps.setInt(4, deportista.getAltura());
			ps.setBlob(5, deportista.getFoto());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("No se insertar el Deportista");
			alert.showAndWait();
			e.printStackTrace();
		}
	}
	
	public boolean existeDeportista (Deportista deportista) {
		String sql = "select * from Deportista where nombre = ?";
		PreparedStatement ps;
		Connection conn;
		
		try {
			conn = conexion.getConexion();
			ps = conn.prepareStatement(sql);
			ps.setString(1, deportista.getNombre());
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
	
	public void updateDeportista (Deportista deportista) {
		String sql = "update Deportista set nombre = ?, sexo= ?, peso = ?, altura = ?, foto = ? where id_deportista = ?";
		PreparedStatement ps;
		Connection conn;
		
		try {
			conn = conexion.getConexion();
			ps = conn.prepareStatement(sql);
			ps.setString(1, deportista.getNombre());
			ps.setString(2, deportista.getSexo());
			ps.setInt(3, deportista.getPeso());
			ps.setInt(4, deportista.getAltura());
			ps.setBlob(5, deportista.getFoto());
			ps.setInt(6, deportista.getId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("No se ha podido actualizar el Deportistas");
			alert.showAndWait();
			e.printStackTrace();
		}
	}
	
	public boolean deleteDeportista (Deportista deportista){
		String sql = "delete from Deportista where id_deportista = ?";
		PreparedStatement ps;
		Connection conn;
		
		try {
			conn = conexion.getConexion();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, deportista.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	
	
}
