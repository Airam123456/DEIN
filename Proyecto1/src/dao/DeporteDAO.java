package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.ConexionDB;
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
			e.printStackTrace();
		}
		return deporte;
	}
	
	
	

}
