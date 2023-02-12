package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class ConexionDB {
	private static Connection conexion;


    public ConexionDB() {}

    public static Connection getConexion() {
    	try {
    		String host = "localhost";
    		String baseDatos = "libros";
    		String usuario = "admin";
    		String password = "password";
    		String cadenaConexion = "jdbc:mysql://" + host + "/" + baseDatos+ "?serverTimezone=" + TimeZone.getDefault().getID();
    		conexion = DriverManager.getConnection(cadenaConexion, usuario, password);
    		conexion.setAutoCommit(true);
    	}catch (SQLException e) {
    		System.out.println("ConexionDB.SQLException: "+e);
    	}
        return conexion;
    }
    
    public void cerrarConexion () {
    	try {
			this.conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void main(String[] args) {
		ConexionDB gbd=new ConexionDB();
		gbd.getConexion();
	}
}
