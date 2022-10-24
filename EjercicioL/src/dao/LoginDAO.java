package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionDB;
import model.Login;

public class LoginDAO {

	private ConexionDB conexion;

	public boolean validarLogin(Login login) throws SQLException {

		String sql = "Select password from aeropuertos.usuarios where usuario = '" + login.getUsuario() + "'";

		PreparedStatement ps;
		conexion = new ConexionDB();
		Connection conn = conexion.getConexion();
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			if (rs.getString("password").equals(login.getPassword())) {
				rs.close();
				ps.close();
				conn.close();
				return true;
			}
		}
		rs.close();
		ps.close();
		conn.close();
		return false;

	}

}
