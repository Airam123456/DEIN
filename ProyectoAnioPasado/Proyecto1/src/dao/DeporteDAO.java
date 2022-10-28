package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.ConexionDB;
import model.Deporte;
import model.Equipo;

public class DeporteDAO {
	
	private ConexionDB cn;
	
	public DeporteDAO () {
		cn = new ConexionDB();
	}

	public ArrayList<Deporte> selectDeportes() {
		PreparedStatement ps;
		ArrayList <Deporte> lstDeportes = new ArrayList <Deporte>();
		try {
			ps=cn.getConexion().prepareStatement("select * from Deporte");
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				lstDeportes.add(new Deporte (rs.getInt(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstDeportes;
	}
	
	public Deporte selectDeportePorId (int id) {
		PreparedStatement ps;
		Deporte deporte = null;
		try {
			ps = cn.getConexion().prepareStatement("select * from Deporte where id_deporte = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				deporte = new Deporte (id, rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deporte;
	}
	
	
	
	public void insertDeporte (Deporte deporte) {
		PreparedStatement ps;
		Connection conexion = cn.getConexion();
		
		try {
			ps= cn.getConexion().prepareStatement("insert into Deporte (nombre) values (?)");
			ps.setString(1, deporte.getNombre());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateDeporte (Deporte deporte) {
		PreparedStatement ps;
		Connection conexion = cn.getConexion();
		
		try {
			ps= cn.getConexion().prepareStatement("update Deporte set nombre = ? where id_deporte = ?");
			ps.setString(1, deporte.getNombre());
			ps.setInt(2, deporte.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean deleteDeporte (Deporte deporte) {
		PreparedStatement ps;
		try {
			ps=cn.getConexion().prepareStatement("delete from Deporte where id_deporte = ?");
			ps.setInt(1, deporte.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public boolean existeDeporte (Deporte deporte) {
		PreparedStatement ps;
		try {
			ps= cn.getConexion().prepareStatement("select * from Deporte where nombre = ?");
			ps.setString(1, deporte.getNombre());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			return true;
		}
		return false;
	}
	
	
	public void cerrarConexion () {
		cn.cerrarConexion();
	}
}
