package dao;

import java.util.ArrayList;

import conexion.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Producto;

public class DAOProducto {

	public ArrayList<Producto> selectProductos(){
		ArrayList<Producto> productos = new ArrayList<Producto>();		
		try (Connection conexion = new ConexionDB().getConexion();) {//Al hacer la conexión en el try, se cierra sola
			String sql = "SELECT * FROM examen1.productos;";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Producto producto = new Producto(rs.getString("codigo"), rs.getString("nombre"), rs.getFloat("precio"), rs.getInt("disponible"), rs.getBinaryStream("imagen"));
				productos.add(producto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return productos;
	}
	
	
	public boolean existeProducto(Producto p) {
		try (Connection conexion = new ConexionDB().getConexion();) {//Al hacer la conexión en el try, se cierra sola
			String sql = "select * FROM examen1.productos where codigo = ?";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, p.getCodigo());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
		return false;
	}
	
	
	public boolean insertProducto(Producto p) {
		try (Connection conexion = new ConexionDB().getConexion();) {//Al hacer la conexión en el try, se cierra sola
			String sql = "INSERT INTO examen1.productos (codigo, nombre, precio, disponible, imagen) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, p.getCodigo());
			ps.setString(2, p.getNombre());
			ps.setDouble(3, p.getPrecio());
			ps.setInt(4, p.getDisponible());
			ps.setBlob(5, p.getFoto());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean updateProducto(Producto p) {
		try (Connection conexion = new ConexionDB().getConexion();) {//Al hacer la conexión en el try, se cierra sola
			String sql = "UPDATE examen1.productos SET nombre = ?, precio = ?, disponible = ?, imagen = ? WHERE codigo = ?";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, p.getNombre());
			ps.setDouble(2, p.getPrecio());
			ps.setInt(3, p.getDisponible());
			ps.setBlob(4, p.getFoto());
			ps.setString(5, p.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean deleteProducto(Producto p) {
		try (Connection conexion = new ConexionDB().getConexion();) {//Al hacer la conexión en el try, se cierra sola
			String sql = "DELETE FROM examen1.productos WHERE codigo = ?";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, p.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}