package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import conexion.ConexionDB;
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
				lstDeportistas.add(new Deportista (rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return lstDeportistas;
	}
	
	public Deportista selectDeportistaPorId (int id) {
		PreparedStatement ps;
		Deportista deportista =null;
		try {
			ps= conexion.getConexion().prepareStatement("select * from Deportista where id_deportista = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				deportista = new Deportista (id, rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deportista;
	}
	
}
