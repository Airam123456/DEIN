package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.ConexionDB;
import model.Alumno;

public class AlumnoDAO {
	private ConexionDB cn;
	
	public AlumnoDAO () {
		cn = new ConexionDB();
	}
	
	public ArrayList<Alumno> selectAlumnos(){
		PreparedStatement ps;
		
		ArrayList<Alumno> lstAlumno = new ArrayList<Alumno>();
		try {
			ps= cn.getConexion().prepareStatement("select dni, nombre, apellido1, apellido2 from Alumno");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				lstAlumno.add(new Alumno(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lstAlumno;
	}
	
	public Alumno selectAlumnoPorDni(String dni){
		PreparedStatement ps;
		
		Alumno alum = null;
		try {
			ps= cn.getConexion().prepareStatement("select dni, nombre, apellido1, apellido2 from Alumno where dni = ?");
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				alum =new Alumno(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return alum;
	}
	
	public void insertAlumno (Alumno alum) throws SQLException {
		PreparedStatement ps;
		
		ps = cn.getConexion().prepareStatement("insert into Alumno (dni, nombre, apellido1, apellido2) values (?,?,?,?)");
		ps.setString(1, alum.getDni());
		ps.setString(2, alum.getNombre());
		ps.setString(3, alum.getApellido1());
		ps.setString(4, alum.getApellido2());
		ps.executeUpdate();
		
	}
	
	public void updateAlumno (Alumno alum, String dni) throws SQLException {
		PreparedStatement ps;
		
		ps=cn.getConexion().prepareStatement("update Alumno set dni = ?, nombre = ?, apellido1 = ?, apellido2 = ? where dni = ?");
		
		ps.setString(1, alum.getDni());
		ps.setString(2, alum.getNombre());
		ps.setString(3, alum.getApellido1());
		ps.setString(4, alum.getApellido2());
		ps.setString(5, dni);
		
		ps.executeUpdate();
			
	}
	
	public void deleteAlumno (Alumno alum) throws SQLException {
		PreparedStatement ps;
		
		ps=cn.getConexion().prepareStatement("delete from Alumno where dni = ?");
		ps.setString(1, alum.getDni());
		
		ps.executeUpdate();
	}
	
	
	
	public void cerrarConexion () {
		cn.cerrarConexion();
	}
}
