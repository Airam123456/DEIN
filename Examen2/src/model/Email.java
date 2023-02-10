package model;

public class Email {

	private String dni, email;
	private int id;
	public Email(int id,String dni, String email) {
		super();
		this.dni = dni;
		this.email = email;
		this.id = id;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
		return email;
	}
	
}
