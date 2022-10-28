package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.ConexionDB;
import model.Deporte;
import model.Equipo;
import model.Evento;
import model.Olimpiada;

public class EventoDAO {

	private ConexionDB cn;
	private DeporteDAO cDeportes;
	private ParticipacionDAO cParticipaciones;
	
	public EventoDAO () {
		this.cn = new ConexionDB();
		this.cDeportes= new DeporteDAO();
		this.cParticipaciones= new ParticipacionDAO();
		
	}

	public ArrayList<Evento> selectEventosPorOlimpiada(Olimpiada olimpiada) {
		PreparedStatement ps;
		ArrayList <Evento> lstEventos = new ArrayList <Evento>();
		
		try {
			ps=cn.getConexion().prepareStatement("select *  from Evento where id_olimpiada = ?");
			ps.setInt(1, olimpiada.getId());
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				Deporte deporte = cDeportes.selectDeportePorId(rs.getInt(4));
				lstEventos.add(new Evento (rs.getInt(1), rs.getString(2), olimpiada, deporte));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return lstEventos;
	}
	
	public void insertEvento (Evento evento) {
		PreparedStatement ps;
		try {
			ps = cn.getConexion().prepareStatement("insert into Evento (nombre, id_olimpiada, id_deporte) values (?,?,?)");
			ps.setString(1, evento.getNombre());
			ps.setInt(2, evento.getOlimpiada().getId());
			ps.setInt(3, evento.getDeporte().getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
	}
	
	public void updateEvento (Evento evento) {
		PreparedStatement ps;
		try {
			ps = cn.getConexion().prepareStatement("update Evento set nombre = ?, id_olimpiada = ?, id_deporte = ? where id_evento = ?");
			ps.setString(1, evento.getNombre());
			ps.setInt(2, evento.getOlimpiada().getId());
			ps.setInt(3, evento.getDeporte().getId());
			ps.setInt(4, evento.getId_evento());
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean deleteEvento (Evento evento) {
		PreparedStatement ps;
		try {
			ps= cn.getConexion().prepareStatement("Delete from Evento where id_evento = ?");
			ps.setInt(1, evento.getId_evento());
			ps.executeUpdate();
		}catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public void cerrarConexion() {
		cDeportes.cerrarConexion();
		cParticipaciones.cerrarConexion();
		cn.cerrarConexion();
	}
	
}
