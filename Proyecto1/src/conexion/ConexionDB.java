package conexion;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

import util.Propiedades;

public class ConexionDB {

	private Connection conexion;

//	public ConexionDB() throws SQLException {
//
//		String user = "admin";
//
//		String password = "password";
//
//		String url = "jdbc:mysql://localhost/personas?serverTimezone=Europe/Madrid";
//
//		conexion = DriverManager.getConnection(url, user, password);
//
//		conexion.setAutoCommit(true);
//
//	}

	public Connection getConexion() throws SQLException {

		//return conexion;
		
		
		String url = Propiedades.getValor("url");
	    String user = Propiedades.getValor("user");
	    String password = Propiedades.getValor("password");
		conexion = DriverManager.getConnection(url, user, password);

		conexion.setAutoCommit(true);
		
		return conexion;

	}

}