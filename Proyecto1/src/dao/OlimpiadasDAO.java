package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.ConexionDB;
import model.Olimpiada;

public class OlimpiadasDAO {
	
	private ConexionDB conexion;
	
	public OlimpiadasDAO () throws SQLException {
		conexion = new ConexionDB();
	}
	
	public ArrayList <Olimpiada> selectOlimpiadas() {
		PreparedStatement ps;
		ArrayList <Olimpiada> lstOlimpiadas= new ArrayList<Olimpiada>();
		try {
			ps=conexion.getConexion().prepareStatement("select * from Olimpiada");
			ResultSet rs = ps.executeQuery();
			while  (rs.next()) {
				lstOlimpiadas.add(new Olimpiada(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lstOlimpiadas;
	}
	
	public ArrayList <Olimpiada> selectOlimpiadasInvierno (){
		PreparedStatement ps;
		ArrayList <Olimpiada> lstOlimpiadas= new ArrayList<Olimpiada>();
		try {
			ps=conexion.getConexion().prepareStatement("select * from Olimpiada where temporada = ?");
			ps.setString(1, "Winter");
			ResultSet rs = ps.executeQuery();
			while  (rs.next()) {
				lstOlimpiadas.add(new Olimpiada(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lstOlimpiadas;
	}
	
	public ArrayList <Olimpiada> selectOlimpiadasVerano (){
		PreparedStatement ps;
		ArrayList <Olimpiada> lstOlimpiadas= new ArrayList<Olimpiada>();
		try {
			ps=conexion.getConexion().prepareStatement("select * from Olimpiada where temporada = ?");
			ps.setString(1, "Summer");
			ResultSet rs = ps.executeQuery();
			while  (rs.next()) {
				lstOlimpiadas.add(new Olimpiada(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lstOlimpiadas;
	}

}
