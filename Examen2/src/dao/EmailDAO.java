package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.ConexionDB;
import javafx.scene.control.Alert;
import model.Email;

public class EmailDAO {
private ConexionDB cn;
	
	public EmailDAO () throws SQLException {
		cn = new ConexionDB();
	}
	
	public ArrayList<Email> selectEmail (String dni){
		PreparedStatement ps;
		ArrayList<Email> lstEmail = new ArrayList<Email>();
		
		try {
			ps = cn.getConexion().prepareStatement("select id, dni, email from examen2.email where dni = ?");
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				lstEmail.add(new Email(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("No se han podido cargar los Emails");
			alert.showAndWait();
			e.printStackTrace();
		}
		
		return lstEmail;
	}
	
	public boolean existeEmail (String dni, String email){
		PreparedStatement ps;
		
		try {
			ps= cn.getConexion().prepareStatement("select email from email where dni = ? and email = ?");
			ps.setString(1, dni);
			ps.setString(2, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Error de base de datos");
			alert.showAndWait();
			return true;
		}
		return false;
	
	}
	
	public void insertEmail (Email email){
		PreparedStatement ps;
		
		try {
			ps = cn.getConexion().prepareStatement("insert into email (dni, email) values (?,?)");
			ps.setString(1, email.getDni());
			ps.setString(2, email.getEmail());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("No se ha podido insertar el Email");
			alert.showAndWait();
			e.printStackTrace();
		}
	}
	
	public void deleteEmail (Email email){
		PreparedStatement ps;
		
		try {
			ps=cn.getConexion().prepareStatement("delete from email where id = ?");
			ps.setInt(1, email.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("No se ha podido borrar el Email");
			alert.showAndWait();
			e.printStackTrace();
		}
	}
}
