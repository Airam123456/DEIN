package conexion;

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
    		String baseDatos = "personas";
    		String usuario = "admin";
    		String password = "password";
    		String cadenaConexion = "jdbc:mysql://" + host + "/" + baseDatos+ "?serverTimezone=" + TimeZone.getDefault().getID();
    		conexion = DriverManager.getConnection(cadenaConexion, usuario, password);
    		conexion.setAutoCommit(true);
    	} catch (SQLException e) {
			e.printStackTrace();
		}
        return conexion;
    }

}
