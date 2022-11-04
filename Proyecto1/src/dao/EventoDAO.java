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
	
	public EventoDAO () throws SQLException{
		this.conexion = new ConexionDB();
		this.cargarDeportes= new DeporteDAO();
		this.cargarParticipaciones= new ParticipacionDAO();
		
	}

	public ArrayList<Evento> selectEventosPorOlimpiada(Olimpiada olimpiada) {
		PreparedStatement ps;
		ArrayList <Evento> lstEventos = new ArrayList <Evento>();
		
		try {
			ps=conexion.getConexion().prepareStatement("select *  from Evento where id_olimpiada = ?");
			ps.setInt(1, olimpiada.getId());
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				Deporte deporte = cargarDeportes.selectDeportePorId(rs.getInt(4));
				lstEventos.add(new Evento (rs.getInt(1), rs.getString(2), olimpiada, deporte));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return lstEventos;
	}
	

	
}
