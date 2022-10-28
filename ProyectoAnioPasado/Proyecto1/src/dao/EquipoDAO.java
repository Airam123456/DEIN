package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.ConexionDB;
import model.Deporte;
import model.Deportista;
import model.Equipo;
import model.Olimpiada;

public class EquipoDAO {

	private ConexionDB cn;

	public EquipoDAO () {
		cn =  new ConexionDB();
	}

	public ArrayList<Equipo> selectEquipos() {
		PreparedStatement ps;
		ArrayList <Equipo> lstEquipos = new ArrayList <Equipo>();
		
		try {
			ps=cn.getConexion().prepareStatement("select *  from Equipo");
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				lstEquipos.add(new Equipo (rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return lstEquipos;
	}
	
	public Equipo selectIdEquipo (String nombre, String iniciales) {
		PreparedStatement ps;
		Equipo equipo =null;
		try {
			ps= cn.getConexion().prepareStatement("select * from Equipo where nombre = ? and iniciales = ?");
			ps.setString(1, nombre);
			ps.setString(2, iniciales);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				equipo = new Equipo (rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return equipo;
	}
	
	public Equipo selectEquipoPorId(int id) {
		PreparedStatement ps;
		try {
			ps = cn.getConexion().prepareStatement("select * from Equipo where id_equipo = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return new Equipo(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		} catch(SQLException e) {
			return null;
		}
		return null;
	}
	
	public void insertEquipo (Equipo equipo) {
		PreparedStatement ps;
		Connection conexion = cn.getConexion();
		
		try {
			ps= cn.getConexion().prepareStatement("insert into Equipo (nombre, iniciales) values (?, ?)");
			ps.setString(1, equipo.getNombre());
			ps.setString(2, equipo.getIniciales());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateEquipo (Equipo equipo) {
		PreparedStatement ps;
		Connection conexion = cn.getConexion();
		
		try {
			ps= cn.getConexion().prepareStatement("update Equipo set nombre = ?, iniciales = ? where id_equipo = ?");
			ps.setString(1, equipo.getNombre());
			ps.setString(2, equipo.getIniciales());
			ps.setInt(3, equipo.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean existeEquipo (Equipo equipo) {
		PreparedStatement ps;
		try {
			ps= cn.getConexion().prepareStatement("select * from Equipo where nombre = ? and iniciales = ?");
			ps.setString(1, equipo.getNombre());
			ps.setString(2, equipo.getIniciales());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			return true;
		}
		return false;
	}
	
	public boolean deleteEquipo (Equipo equipo)
	{
		PreparedStatement ps;
		
		
		try {
			ps=cn.getConexion().prepareStatement("delete from Equipo where id_Equipo = ?");
			ps.setInt(1, equipo.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public void cerrarConexion () {
		cn.cerrarConexion();
	}
	
}
