package model;

public class Deportista {
	private int id;
	private String nombre;
	private String sexo;
	private int peso;
	private int altura;
	
	public Deportista(int id, String nombre, String sexo, int peso, int altura) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.sexo = sexo;
		this.peso = peso;
		this.altura = altura;
	}

	public Deportista() {
		super();
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getSexo() {
		return sexo;
	}

	public int getPeso() {
		return peso;
	}

	public int getAltura() {
		return altura;
	}
	@Override
	public String toString() {
		
		return nombre;
	}
	

}
