package model;

public class Evento {

	private int id_evento;
	private String nombre;
	private Olimpiada olimpiada;
	private Deporte deporte;
	
	public Evento(int id_evento, String nombre, Olimpiada olimpiada, Deporte deporte) {
		super();
		this.id_evento = id_evento;
		this.nombre = nombre;
		this.olimpiada = olimpiada;
		this.deporte = deporte;
	}

	public int getId_evento() {
		return id_evento;
	}

	public String getNombre() {
		return nombre;
	}

	public Olimpiada getOlimpiada() {
		return olimpiada;
	}

	public Deporte getDeporte() {
		return deporte;
	}

	@Override
	public String toString() {
		return nombre + ": " + deporte;
	}

	
	
}
