package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import conexion.ConexionDB;
import model.Alumno;
import model.Libro;
import model.Prestamo;

public class PrestamoDAO {
	private ConexionDB cn;
	private AlumnoDAO cAlumno;
	private LibroDAO cLibro;
	
	public PrestamoDAO () {
		try {
			cn = new ConexionDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cAlumno = new AlumnoDAO();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cLibro= new LibroDAO();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Prestamo> selectPrestamos (){
		PreparedStatement ps;
		ArrayList<Prestamo> lstPrest = new ArrayList<Prestamo>();
		
		try {
			ps = cn.getConexion().prepareStatement("select id_prestamo, dni_alumno, codigo_libro, fecha_prestamo from Prestamo");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Alumno alum = cAlumno.selectAlumnoPorDni(rs.getString(2));
				Libro lib = cLibro.selectLibroPorCod (rs.getInt(3));
				lstPrest.add(new Prestamo(rs.getInt(1), alum, lib,rs.getObject(4, LocalDate.class)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstPrest;
	}
	
	public ArrayList<Prestamo> selectPrestamosPorAlumno (Alumno a){
		PreparedStatement ps;
		ArrayList<Prestamo> lstPrest = new ArrayList<Prestamo>();
		
		try {
			ps = cn.getConexion().prepareStatement("select id_prestamo, dni_alumno, codigo_libro, fecha_prestamo from Prestamo where dni_alumno = ?");
			ps.setString(1, a.getDni());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Alumno alum = cAlumno.selectAlumnoPorDni(rs.getString(2));
				Libro lib = cLibro.selectLibroPorCod (rs.getInt(3));
				lstPrest.add(new Prestamo(rs.getInt(1), alum, lib,rs.getObject(4, LocalDate.class)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstPrest;
	}
	
	public int insertPrestamo (Prestamo p) throws SQLException {
		PreparedStatement ps;
		ps = cn.getConexion().prepareStatement("insert into Prestamo (dni_alumno, codigo_libro, fecha_prestamo) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, p.getAlum().getDni());
		ps.setInt(2, p.getLib().getCodigo());
		ps.setObject(3, p.getFecha_prestamo());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		if(rs.next()) {
			return rs.getInt(1);
		}
		return 1;
	}
	
	public void deletePrestamo (Prestamo p) throws SQLException {
		PreparedStatement ps;
		ps = cn.getConexion().prepareStatement("delete from Prestamo where id_prestamo = ?");
		ps.setInt(1, p.getId_prestamo());
		
		ps.executeUpdate();
	}
	
	public boolean existeAlumno (Alumno a){
		PreparedStatement ps;
	
		
		try {
			ps = cn.getConexion().prepareStatement("select id_prestamo, dni_alumno, codigo_libro, fecha_prestamo from Prestamo where dni_alumno = ?");
			ps.setString(1, a.getDni());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			return true;
		}
		return false;
	}
	public boolean existeLibro (Libro l){
		PreparedStatement ps;

		
		try {
			ps = cn.getConexion().prepareStatement("select id_prestamo, dni_alumno, codigo_libro, fecha_prestamo from Prestamo where codigo_libro = ?");
			ps.setInt(1, l.getCodigo());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			return true;
		}
		return false;
	}
	
	
}
