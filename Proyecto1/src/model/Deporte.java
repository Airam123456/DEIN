package model;

public class Deporte {

	private int id;
	private String nombre;
	
	public Deporte(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return  nombre;
	}
	
	
}
