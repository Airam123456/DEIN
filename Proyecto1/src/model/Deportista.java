package model;

import java.io.InputStream;

public class Deportista {
	private int id;
	private String nombre;
	private String sexo;
	private int peso;
	private int altura;
	private InputStream foto;
	
	public Deportista(int id, String nombre, String sexo, int peso, int altura, InputStream foto) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.sexo = sexo;
		this.peso = peso;
		this.altura = altura;
		this.foto = foto;
	}

	public Deportista() {
		super();
	}
	
	public Deportista(int id) {
		super();
		this.id = id;
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
	
	public InputStream getFoto() {
		return foto;
	}
	
	@Override
	public String toString() {
		
		if(peso!=0 && altura!=0) {
			return nombre + "; Peso: " + peso + "Kg, Altura: " + altura + "cm";
		}else {
			if(peso!=0 && altura==0) {
				return nombre + "; Peso: " + peso + "Kg, Altura no definida";
			}
			if(peso==0 && altura!=0) {
				return nombre + "; Peso no definido, Altura: " + altura + "cm";
			}
			return nombre + "; Peso no definido, Altura no definida";

		}
		
		
	}
	

}
