package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.ConexionDB;
import model.Deporte;
import model.Equipo;
import model.Evento;
import model.Olimpiada;

public class EventoDAO {

	private ConexionDB conexion;
	private DeporteDAO cargarDeportes;
	private ParticipacionDAO cargarParticipaciones;

	public EventoDAO() throws SQLException {
		this.conexion = new ConexionDB();
		this.cargarDeportes = new DeporteDAO();
		this.cargarParticipaciones = new ParticipacionDAO();

	}

	public ArrayList<Evento> selectEventosPorOlimpiada(Olimpiada olimpiada) {
		PreparedStatement ps;
		ArrayList<Evento> lstEventos = new ArrayList<Evento>();

		try {
			ps = conexion.getConexion().prepareStatement("select *  from Evento where id_olimpiada = ?");
			ps.setInt(1, olimpiada.getId());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Deporte deporte = cargarDeportes.selectDeportePorId(rs.getInt(4));
				lstEventos.add(new Evento(rs.getInt(1), rs.getString(2), olimpiada, deporte));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstEventos;
	}

	public void insertEvento(Evento evento) {
		String sql = "insert into Evento (nombre, id_olimpiada, id_deporte) values (?,?,?)";
		PreparedStatement ps;
		Connection conn;

		try {
			conn = conexion.getConexion();
			ps = conn.prepareStatement(sql);
			ps.setString(1, evento.getNombre());
			ps.setInt(2, evento.getOlimpiada().getId());
			ps.setInt(3, evento.getDeporte().getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		}
	}

	public void updateEvento(Evento evento) {
		String sql = "update Evento set nombre = ?, id_olimpiada = ?, id_deporte = ? where id_evento = ?";
		PreparedStatement ps;
		Connection conn;

		try {
			conn = conexion.getConexion();
			ps = conn.prepareStatement(sql);
			ps.setString(1, evento.getNombre());
			ps.setInt(2, evento.getOlimpiada().getId());
			ps.setInt(3, evento.getDeporte().getId());
			ps.setInt(4, evento.getId_evento());
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean deleteEvento(Evento evento) {
		String sql = "Delete from Evento where id_evento = ?";
		PreparedStatement ps;
		Connection conn;

		try {
			conn = conexion.getConexion();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, evento.getId_evento());
			ps.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

}
