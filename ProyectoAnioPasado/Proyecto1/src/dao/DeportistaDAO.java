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

public class DeportistaDAO {

	private ConexionDB cn;
	
	public DeportistaDAO() {
		cn = new ConexionDB();
	}

	public ArrayList<Deportista> selectDeportista() {
		PreparedStatement ps;
		ArrayList <Deportista> lstDeportistas = new ArrayList <Deportista>();
		
		try {
			ps=cn.getConexion().prepareStatement("select *  from Deportista");
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
			ps= cn.getConexion().prepareStatement("select * from Deportista where id_deportista = ?");
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
	
	public void insertDeportista (Deportista deportista) {
		PreparedStatement ps;
		Connection conexion = cn.getConexion();
		
		try {
			ps= cn.getConexion().prepareStatement("insert into Deportista (nombre, sexo, peso, altura) values (?,?,?,?)");
			ps.setString(1, deportista.getNombre());
			ps.setString(2, deportista.getSexo());
			ps.setInt(3, deportista.getPeso());
			ps.setInt(4, deportista.getAltura());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateDeportista (Deportista deportista) {
		PreparedStatement ps;
		Connection conexion = cn.getConexion();
		
		try {
			ps= cn.getConexion().prepareStatement("update Deportista set nombre = ?, sexo= ?, peso = ?, altura = ? where id_deportista = ?");
			ps.setString(1, deportista.getNombre());
			ps.setString(2, deportista.getSexo());
			ps.setInt(3, deportista.getPeso());
			ps.setInt(4, deportista.getAltura());
			ps.setInt(5, deportista.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean existeDeportista (Deportista deportista) {
		PreparedStatement ps;
		try {
			ps= cn.getConexion().prepareStatement("select * from Deportista where nombre = ? and sexo = ? and peso = ? and altura = ?");
			ps.setString(1, deportista.getNombre());
			ps.setString(2, deportista.getSexo());
			ps.setInt(3, deportista.getPeso());
			ps.setInt(4, deportista.getAltura());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			return true;
		}
		return false;
	}
	
	public boolean deleteDeportista (Deportista deportista)
	{
		PreparedStatement ps;
		
		
		try {
			ps=cn.getConexion().prepareStatement("delete from Deportista where id_deportista = ?");
			ps.setInt(1, deportista.getId());
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
