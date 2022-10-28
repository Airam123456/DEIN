package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.ConexionDB;
import model.Evento;
import model.Olimpiada;

public class OlimpiadaDAO {

	private ConexionDB cn;
	
	public OlimpiadaDAO () {
		cn = new ConexionDB();
	}

	public ArrayList <Olimpiada> selectOlimpiadas() {
		PreparedStatement ps;
		ArrayList <Olimpiada> lstOlimpiadas= new ArrayList<Olimpiada>();
		try {
			ps=cn.getConexion().prepareStatement("select * from Olimpiada");
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
			ps=cn.getConexion().prepareStatement("select * from Olimpiada where temporada = ?");
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
			ps=cn.getConexion().prepareStatement("select * from Olimpiada where temporada = ?");
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
	
	public boolean existeOlimpiada(Olimpiada olimp) {
		PreparedStatement ps;
		try {
			ps = cn.getConexion().prepareStatement("select * from Olimpiada where anio = ? and ciudad = ? and temporada = ?");
			ps.setInt(1, olimp.getAnio());
			ps.setString(2, olimp.getCiudad());
			ps.setString(3, olimp.getTemporada());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			return true;
		}	
		return false;
	}
	
	public void insertOlimpiada (Olimpiada olimpiada) {
		
		PreparedStatement ps;
		Connection conexion = cn.getConexion();
		try {
			ps=cn.getConexion().prepareStatement("insert into Olimpiada (nombre, anio, temporada, ciudad) values (?,?,?,?)");
			ps.setString(1, olimpiada.getNombre());
			ps.setInt(2, olimpiada.getAnio());
			ps.setString(3, olimpiada.getTemporada());
			ps.setString(4, olimpiada.getCiudad());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void updateOlimpiada (Olimpiada olimpiada) {
		PreparedStatement ps;
		
		
		try {
			ps= cn.getConexion().prepareStatement("update Olimpiada set nombre = ?, anio = ?, temporada = ?, ciudad = ? where id_olimpiada = ?");
			ps.setString(1, olimpiada.getNombre());
			ps.setInt(2, olimpiada.getAnio());
			ps.setString(3, olimpiada.getTemporada());
			ps.setString(4, olimpiada.getCiudad());
			ps.setInt(5, olimpiada.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean deleteOlimpiada (Olimpiada olimpiada)
	{
		PreparedStatement ps;
		
		
		try {
			ps=cn.getConexion().prepareStatement("delete from Olimpiada where id_Olimpiada = ?");
			ps.setInt(1, olimpiada.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}
	
	public void cerrarConexion () {
		cn.cerrarConexion();
	}
	
}
