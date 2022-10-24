package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TimeZone;

public class ConexionDB {
    private Connection conexion;
    
    public ConexionDB() {
    	try {
    		String host = "localhost";
            String baseDatos = "aeropuertos";
            String usuario = "admin";
            String password = "password";
            String cadenaConexion = "jdbc:mysql://" + host + "/" + baseDatos+ "?serverTimezone=" + TimeZone.getDefault().getID();
            conexion = DriverManager.getConnection(cadenaConexion, usuario, password);
            conexion.setAutoCommit(true);
    	}catch (SQLException e) {
			System.out.println("ConexionDB.SQLException: "+e);
		}
        
    }

    public ResultSet ejecutarConsulta(String SQL) throws SQLException {
        Statement statement = this.conexion.createStatement();
        return statement.executeQuery(SQL);
    }
    
    public ResultSet insert(String sql) {
    	try {
    		Statement statement = this.conexion.createStatement();
    		statement.executeUpdate(sql);
    		if(statement.getUpdateCount()>0) {
    			return statement.getGeneratedKeys();
    		}
    	}catch (SQLException e){
    		return null;
    	}
    	return null;
    }

    public void cerrarConexion() throws SQLException {
        this.conexion.close();
    }

    public Connection getConexion() {
        return conexion;
    }

}