package model;

public class Participacion {

	private Deportista deportista;
	private Evento evento;
	private Equipo equipo;
	private int edad;
	private String medalla;
	
	public Participacion(Deportista deportista, Evento evento, Equipo equipo, int edad, String medalla) {
		super();
		this.deportista = deportista;
		this.evento = evento;
		this.equipo = equipo;
		this.edad = edad;
		this.medalla = medalla;
	}

	public Participacion() {
		super();
	}
	
	public Deportista getDeportista() {
		return deportista;
	}

	public Evento getEvento() {
		return evento;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public int getEdad() {
		return edad;
	}

	public String getMedalla() {
		return medalla;
	}

	@Override
	public String toString() {
		String str = deportista.getNombre() + " (" + equipo.getIniciales() + ") con edad: " + edad;
		
		if(medalla.equals("Gold")) 
			str += ". Medalla: Oro";
		
		else if(medalla.equals("Silver")) 
			str += ". Medalla: Plata";
		
		else if(medalla.equals("Bronze")) 
			str += ". Medalla: Bronce";
		
		else
			str += ". sin medalla";
		
		return str;
	}
	
}
