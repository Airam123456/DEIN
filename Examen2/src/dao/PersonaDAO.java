package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.ConexionDB;
import model.Persona;

public class PersonaDAO {
	private ConexionDB cn;
	
	public PersonaDAO () throws SQLException {
		cn = new ConexionDB();
	}
	
	public ArrayList<Persona> selectPersonas (){
		PreparedStatement ps;
		ArrayList<Persona> lstPersona= new ArrayList<Persona>();
		
		try {
			ps = cn.getConexion().prepareStatement("select dni, nombre, apellido1, apellido2, anio_nacimiento from persona");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				lstPersona.add(new Persona(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
				
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lstPersona;
	}
}
