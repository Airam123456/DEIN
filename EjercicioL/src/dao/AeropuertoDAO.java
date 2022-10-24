package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.ConexionDB;
import model.AeropuertoPrivado;
import model.AeropuertoPublico;

public class AeropuertoDAO {

	private ConexionDB conexion;

	public AeropuertoDAO() throws SQLException {

	}

	public ArrayList<AeropuertoPublico> getAeropuertosPublicos() throws SQLException {

		String sql = "SELECT * \n" + "FROM aeropuertos, aeropuertos_publicos, direcciones \n"
				+ "WHERE aeropuertos.id = aeropuertos_publicos.id_aeropuerto \n"
				+ "AND direcciones.id = aeropuertos.id_direccion;";
		PreparedStatement ps;
		conexion = new ConexionDB();
		Connection conn = conexion.getConexion();
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		ArrayList<AeropuertoPublico> data = new ArrayList();
		while (rs.next()) {
			AeropuertoPublico aeropuerto = new AeropuertoPublico(rs.getInt("aeropuertos.id"),
					rs.getString("aeropuertos.nombre"), rs.getInt("aeropuertos.anio_inauguracion"),
					rs.getInt("aeropuertos.capacidad"), rs.getInt("aeropuertos.id_direccion"),
					rs.getFloat("aeropuertos_publicos.financiacion"),
					rs.getInt("aeropuertos_publicos.num_trabajadores"), rs.getString("direcciones.pais"),
					rs.getString("direcciones.ciudad"), rs.getString("direcciones.calle"),
					rs.getInt("direcciones.numero"));
			// System.out.println(aeropuerto.toString());
			data.add(aeropuerto);
		}
		rs.close();
		ps.close();
		conn.close();
		return data;

	}

	public ArrayList<AeropuertoPrivado> getAeropuertosPrivados() throws SQLException {
		String sql = "SELECT * \n" + "FROM aeropuertos, aeropuertos_privados, direcciones\n"
				+ "where aeropuertos.id = aeropuertos_privados.id_aeropuerto\n"
				+ "AND direcciones.id = aeropuertos.id_direccion;";
		PreparedStatement ps;
		conexion = new ConexionDB();
		Connection conn = conexion.getConexion();
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		ArrayList<AeropuertoPrivado> data = new ArrayList();

		while (rs.next()) {
			AeropuertoPrivado aeropuerto = new AeropuertoPrivado(rs.getInt("aeropuertos.id"),
					rs.getString("aeropuertos.nombre"), rs.getInt("aeropuertos.anio_inauguracion"),
					rs.getInt("aeropuertos.capacidad"), rs.getInt("aeropuertos.id_direccion"),
					rs.getFloat("aeropuertos_privados.numero_socios"), rs.getString("direcciones.pais"),
					rs.getString("direcciones.ciudad"), rs.getString("direcciones.calle"),
					rs.getInt("direcciones.numero"));
			data.add(aeropuerto);
		}
		return data;
	}

}
