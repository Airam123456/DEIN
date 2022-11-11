package model;

import java.io.InputStream;

public class Producto {

	private String codigo;
	private String nombre;
	private double precio;
	private int disponible;
	private InputStream foto;
	
	public Producto() {
		super();
	}
	
	public Producto(String codigo, String nombre, double precio, int disponible, InputStream foto) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.disponible = disponible;
		this.foto = foto;
	}
	

	
	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", disponibilidad="
				+ disponible + "]";
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getDisponible() {
		return disponible;
	}

	public void setDisponible(int disponible) {
		this.disponible = disponible;
	}

	public InputStream getFoto() {
		return foto;
	}

	public void setFoto(InputStream foto) {
		this.foto = foto;
	}
	
	
	
	
	
}