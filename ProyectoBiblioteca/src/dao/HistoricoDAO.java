package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import config.ConexionDB;
import model.Alumno;
import model.Historico;
import model.Libro;


public class HistoricoDAO {
	private ConexionDB cn;
	private PrestamoDAO cPrestamo;
	
	public HistoricoDAO () {
		cn = new ConexionDB();
		cPrestamo = new PrestamoDAO();
	}
	
	public ArrayList<Historico> selectHistoricos (){
		PreparedStatement ps;
		ArrayList<Historico> lstHistorico = new ArrayList<Historico>();
		
		try {
			ps = cn.getConexion().prepareStatement("select id_prestamo, dni_alumno, codigo_libro, fecha_prestamo, fecha_devolucion from Historico_prestamo");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				lstHistorico.add(new Historico(rs.getInt(1), rs.getString(2), rs.getInt(3),rs.getObject(4, LocalDate.class), rs.getObject(5, LocalDate.class)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstHistorico;
	}
	
	public ArrayList <Historico> selectHistoricosPorAlumno (Alumno a){
		PreparedStatement ps;
		ArrayList<Historico> lstHistorico = new ArrayList<Historico>();
		
		try {
			ps = cn.getConexion().prepareStatement("select id_prestamo, dni_alumno, codigo_libro, fecha_prestamo, fecha_devolucion from Historico_prestamo where dni_alumno = ?");
			ps.setString(1, a.getDni());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				lstHistorico.add(new Historico(rs.getInt(1), rs.getString(2), rs.getInt(3),rs.getObject(4, LocalDate.class), rs.getObject(5, LocalDate.class)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstHistorico;
	}
	
	public ArrayList <Historico> selectHistoricosPorLibro (Libro l){
		PreparedStatement ps;
		ArrayList<Historico> lstHistorico = new ArrayList<Historico>();
		
		try {
			ps = cn.getConexion().prepareStatement("select id_prestamo, dni_alumno, codigo_libro, fecha_prestamo, fecha_devolucion from Historico_prestamo where codigo_libro = ?");
			ps.setInt(1, l.getCodigo());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				lstHistorico.add(new Historico(rs.getInt(1), rs.getString(2), rs.getInt(3),rs.getObject(4, LocalDate.class), rs.getObject(5, LocalDate.class)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstHistorico;
	}
	
	public void insertHistorico (Historico h) throws SQLException {
		PreparedStatement ps;
		
		ps = cn.getConexion().prepareStatement("insert into Historico_prestamo (id_prestamo, dni_alumno, codigo_libro, fecha_prestamo, fecha_devolucion) values (?,?,?,?,?)");
		ps.setInt(1, h.getId_prestamo());
		ps.setString(2, h.getDni_alumno());
		ps.setInt(3, h.getCodigo_libro());
		ps.setObject(4, h.getFecha_prestamo());
		ps.setObject(5, h.getFecha_devolucion());
		
		ps.executeUpdate();
		
		
	}
}
