package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.ConexionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Persona;

public class PersonasDAO {
	private ConexionDB conexion;

	public ArrayList<Persona> getPersonas() throws SQLException {
		String sql = "SELECT * FROM Persona;";
		PreparedStatement ps;
		ArrayList<Persona> personas = new ArrayList<Persona>();
		conexion = new ConexionDB();
		Connection conn = conexion.getConexion();

		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			personas.add(
					new Persona(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"), rs.getInt("edad")));
		}

		return personas;
	}

	public void insertPersona(Persona p) {
		String sql = "INSERT INTO Persona (nombre, apellidos, edad) VALUES (?, ?, ?)";
		PreparedStatement ps;
		Connection conn = conexion.getConexion();
		try {
			ps =conn.prepareStatement(sql);
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getApellido());
			ps.setInt(3, p.getEdad());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updatePersona(Persona p) {
		String sql = "UPDATE Persona set nombre=?, apellidos=?, edad=? WHERE id = ?";
		PreparedStatement ps;
		Connection conn = conexion.getConexion();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getApellido());
			ps.setInt(3, p.getEdad());
			ps.setInt(4, p.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deletePersona(Persona p) {
		String sql = "DELETE FROM Persona WHERE id = ?";
		PreparedStatement ps;
		Connection conn = conexion.getConexion();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, p.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
