package model;

public class Equipo {

	private int id;
	private String nombre;
	private String iniciales;
	
	public Equipo(int id, String nombre, String iniciales) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.iniciales = iniciales;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getIniciales() {
		return iniciales;
	}

	@Override
	public String toString() {
		
		return nombre + " (" + iniciales + ")";
	}
	
}
