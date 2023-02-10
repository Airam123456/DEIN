package model;

public class Telefono {
	private String dni, telefono;
	private int id;
	public Telefono(int id, String dni,String telefono) {
		super();
		this.dni = dni;
		this.telefono = telefono;
		this.id = id;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getTelf() {
		return telefono;
	}
	public void setTelf(String telf) {
		this.telefono = telf;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return telefono;
	}
	
}
