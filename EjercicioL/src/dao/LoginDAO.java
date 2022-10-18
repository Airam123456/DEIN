package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionDB;
import model.Login;

public class LoginDAO {

	private ConexionDB conexion;

	public boolean validarLogin(Login log) throws SQLException {

		String sql = "Select * from usuarios where usuario = '" + log.getUsuario() + "' and password = '" + log.getPassword() + "'";
		PreparedStatement ps;
		
		conexion = new ConexionDB();
		Connection conn = conexion.getConexion();
		
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
			return false;
			
		
		
	}

}
