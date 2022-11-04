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
	
	public ParticipacionDAO () throws SQLException{
		this.conexion = new ConexionDB();
		cargarDeportista = new DeportistaDAO();
		cargarEquipo = new EquipoDAO();
		
	}

	public ArrayList<Participacion> selectParticipacionesPorEvento(Evento evento) {
		ArrayList<Participacion> participaciones = new ArrayList<Participacion>();
		if(evento != null) {
			PreparedStatement ps;
			try {
				ps = conexion.getConexion().prepareStatement("select * from Participacion where id_evento = ?");
				ps.setInt(1, evento.getId_evento());
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					String medalla = rs.getString(5);
					if(rs.wasNull()) {
						medalla = "Sin medalla";
					}
					Participacion part = new Participacion(
							cargarDeportista.selectDeportistaPorId(rs.getInt(1)), 
							evento, 
							cargarEquipo.selectEquipoPorId(rs.getInt(3)), 
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
	
	
}
