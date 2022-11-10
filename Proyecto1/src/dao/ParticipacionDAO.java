package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.ConexionDB;
import model.Deportista;
import model.Equipo;
import model.Evento;
import model.Olimpiada;
import model.Participacion;

public class ParticipacionDAO {

	private ConexionDB conexion;
	private DeportistaDAO cargarDeportista;
	private EquipoDAO cargarEquipo;

	public ParticipacionDAO() throws SQLException {
		this.conexion = new ConexionDB();
		cargarDeportista = new DeportistaDAO();
		cargarEquipo = new EquipoDAO();

	}

	public ArrayList<Participacion> selectParticipacionesPorEvento(Evento evento) {
		ArrayList<Participacion> participaciones = new ArrayList<Participacion>();
		if (evento != null) {
			PreparedStatement ps;
			try {
				ps = conexion.getConexion().prepareStatement("select * from Participacion where id_evento = ?");
				ps.setInt(1, evento.getId_evento());
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					String medalla = rs.getString(5);
					if (rs.wasNull()) {
						medalla = "Sin medalla";
					}
					Participacion part = new Participacion(cargarDeportista.selectDeportistaPorId(rs.getInt(1)), evento,
							cargarEquipo.selectEquipoPorId(rs.getInt(3)), rs.getInt(4), medalla);
					participaciones.add(part);
				}
			} catch (SQLException e) {
				return participaciones;
			}
		}
		return participaciones;
	}

	public void insertParticipacion(Participacion part) throws SQLException {
		PreparedStatement ps;

		ps = conexion.getConexion().prepareStatement("insert into Participacion values (?,?,?,?,?)");
		ps.setInt(1, part.getDeportista().getId());
		ps.setInt(2, part.getEvento().getId_evento());
		ps.setInt(3, part.getEquipo().getId());
		ps.setInt(4, part.getEdad());

		if (part.getMedalla().equals("Sin medalla")) {
			ps.setInt(5, java.sql.Types.NULL);
		}
		if (part.getMedalla().equals("Gold")) {
			ps.setString(5, "Gold");
		}
		if (part.getMedalla().equals("Silver")) {
			ps.setString(5, "Silver");
		}
		if (part.getMedalla().equals("Bronze")) {
			ps.setString(5, "Bronze");
		}
		ps.executeUpdate();

	}

	public boolean existeParticipacion(Participacion part) {
		String sql = "select * from Participacion where id_deportista = ? and id_evento = ?";
		PreparedStatement ps;
		Connection conn;

		try {
			conn = conexion.getConexion();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, part.getDeportista().getId());
			ps.setInt(2, part.getEvento().getId_evento());
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			return true;
		}
	}
	
	public void updateParticipacion(Participacion part) throws SQLException {
		PreparedStatement ps;
		ps = conexion.getConexion().prepareStatement("update Participacion set  id_equipo = ?, edad = ?, medalla = ? where id_deportista = ? and id_evento = ?");
		ps.setInt(1, part.getEquipo().getId());
		ps.setInt(2, part.getEdad());
		
		if(part.getMedalla().equals("Sin medalla")) {
			ps.setInt(3, java.sql.Types.NULL);
		}
		if(part.getMedalla().equals("Gold")) {
			ps.setString(3, "Gold");
		}
		if(part.getMedalla().equals("Silver")) {
			ps.setString(3, "Silver");
		}
		if(part.getMedalla().equals("Bronze")) {
			ps.setString(3, "Bronze");
		}
		ps.setInt(4, part.getDeportista().getId());
		ps.setInt(5, part.getEvento().getId_evento());
		ps.executeUpdate();
	}
	
	public boolean deleteParticipacion(Participacion part) {
		PreparedStatement ps;
		try {
			ps = conexion.getConexion().prepareStatement("delete from Participacion where id_deportista = ? and id_evento = ?");
			ps.setInt(1, part.getDeportista().getId());
			ps.setInt(2, part.getEvento().getId_evento());
			ps.executeUpdate();
			return true;
		} catch(SQLException e) {
			return false;
		}
	}

}
