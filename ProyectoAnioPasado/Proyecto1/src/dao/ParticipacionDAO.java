package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.ConexionDB;
import model.Deportista;
import model.Equipo;
import model.Evento;
import model.Olimpiada;
import model.Participacion;

public class ParticipacionDAO {

	private ConexionDB cn;
	private DeportistaDAO cDeportista;
	private EquipoDAO cEquipo;
	
	public ParticipacionDAO (){
		cDeportista = new DeportistaDAO();
		cEquipo = new EquipoDAO();
		this.cn = new ConexionDB();
	}

	public ArrayList<Participacion> selectParticipacionesPorEvento(Evento evento) {
		ArrayList<Participacion> participaciones = new ArrayList<Participacion>();
		if(evento != null) {
			PreparedStatement ps;
			try {
				ps = cn.getConexion().prepareStatement("select * from Participacion where id_evento = ?");
				ps.setInt(1, evento.getId_evento());
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					String medalla = rs.getString(5);
					if(rs.wasNull()) {
						medalla = "Sin medalla";
					}
					Participacion part = new Participacion(
							cDeportista.selectDeportistaPorId(rs.getInt(1)), 
							evento, 
							cEquipo.selectEquipoPorId(rs.getInt(3)), 
							rs.getInt(4), 
							medalla);
					participaciones.add(part);
				} 
			} catch(SQLException e) {
				return participaciones;
			}
		}
		return participaciones;
	}
	
	public boolean existeParticipacionConEvento(Evento evento) {
		PreparedStatement ps;
		try {
			ps = cn.getConexion().prepareStatement("select * from Participacion where id_evento = ?");
			ps.setInt(1, evento.getId_evento());
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			return true;
		}
	}
	
	public boolean existeParticipacion(Equipo equipo, Deportista deportista, Evento evento) {
		PreparedStatement ps;
		try {
			ps = cn.getConexion().prepareStatement("select * from Participacion where id_equipo = ? and id_evento = ? and id_deportista = ?");
			ps.setInt(1, equipo.getId());
			ps.setInt(2, evento.getId_evento());
			ps.setInt(3, deportista.getId());
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			return true;
		}
	}
	
	public boolean existeParticipacionConDeportista(Deportista deportista) {
		PreparedStatement ps;
		try {
			ps = cn.getConexion().prepareStatement("select * from Participacion where id_deportista = ?");
			ps.setInt(1, deportista.getId());
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			return true;
		}
	}
	
	public boolean borrarParticipacion(Participacion part) {
		PreparedStatement ps;
		try {
			ps = cn.getConexion().prepareStatement("delete from Participacion where id_deportista = ? and id_evento = ?");
			ps.setInt(1, part.getDeportista().getId());
			ps.setInt(2, part.getEvento().getId_evento());
			ps.executeUpdate();
			return true;
		} catch(SQLException e) {
			return false;
		}
	}
	
	public boolean existeParticipacion(Participacion part) {
		PreparedStatement ps;
		try {
			ps = cn.getConexion().prepareStatement("select * from Participacion where id_deportista = ? and id_evento = ?");
			ps.setInt(1, part.getDeportista().getId());
			ps.setInt(2, part.getEvento().getId_evento());
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch(SQLException e) {
			return true;
		}
	}
	public void insertarParticipacion(Participacion part)throws SQLException {
		PreparedStatement ps;
		
		ps = cn.getConexion().prepareStatement("insert into Participacion values (?,?,?,?,?)");
		ps.setInt(1, part.getDeportista().getId());
		ps.setInt(2, part.getEvento().getId_evento());
		ps.setInt(3, part.getEquipo().getId());
		ps.setInt(4, part.getEdad());
		
		if(part.getMedalla().equals("Sin medalla")) {
			ps.setInt(5, java.sql.Types.NULL);
		}
		if(part.getMedalla().equals("Gold")) {
			ps.setString(5, "Gold");
		}
		if(part.getMedalla().equals("Silver")) {
			ps.setString(5, "Silver");
		}
		if(part.getMedalla().equals("Bronze")) {
			ps.setString(5, "Bronze");
		}
		ps.executeUpdate();
		
	}
	
	public void updateParticipacion(Participacion part, Deportista anterior) throws SQLException {
		PreparedStatement ps;
		ps = cn.getConexion().prepareStatement("update Participacion set id_deportista = ?, id_equipo = ?, edad = ?, medalla = ? where id_deportista = ? and id_evento = ?");
		ps.setInt(1, part.getDeportista().getId());
		ps.setInt(2, part.getEquipo().getId());
		ps.setInt(3, part.getEdad());
		
		if(part.getMedalla().equals("Sin medalla")) {
			ps.setInt(4, java.sql.Types.NULL);
		}
		if(part.getMedalla().equals("Gold")) {
			ps.setString(4, "Gold");
		}
		if(part.getMedalla().equals("Silver")) {
			ps.setString(4, "Silver");
		}
		if(part.getMedalla().equals("Bronze")) {
			ps.setString(4, "Bronze");
		}
		ps.setInt(5, anterior.getId());
		ps.setInt(6, part.getEvento().getId_evento());
		ps.executeUpdate();
	}

	
	
	public void cerrarConexion() {
		cDeportista.cerrarConexion();
		cEquipo.cerrarConexion();
		cn.cerrarConexion();
	}
}
