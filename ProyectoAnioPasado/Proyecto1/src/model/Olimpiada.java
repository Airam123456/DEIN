package model;

public class Olimpiada {

	private int id;
	private String nombre;
	private int anio;
	private String temporada;
	private String ciudad;
	
	public Olimpiada(int id, String nombre, int anio, String temporada, String ciudad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.anio = anio;
		this.temporada = temporada;
		this.ciudad = ciudad;
	}

	public Olimpiada() {
		super();
	}
	@Override
	public String toString() {
		
		return nombre+ ", " + ciudad;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getAnio() {
		return anio;
	}

	public String getTemporada() {
		return temporada;
	}

	public String getCiudad() {
		return ciudad;
	}
	
	
	
	
}
