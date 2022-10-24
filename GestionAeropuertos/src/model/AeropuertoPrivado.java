package model;

public class AeropuertoPrivado {

	private int id;
	private String nombre;
	private int anio_inauguracion;
	private int capacidad;
	private int id_direccion;
	private float numero_socios;
	private String pais;
	private String ciudad;
	private String calle;
	private int numero;
	
	public AeropuertoPrivado(int id, String nombre, int anio_inauguracion, int capacidad, int id_direccion,
			float numero_socios, String pais, String ciudad, String calle, int numero) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.anio_inauguracion = anio_inauguracion;
		this.capacidad = capacidad;
		this.id_direccion = id_direccion;
		this.numero_socios = numero_socios;
		this.pais = pais;
		this.ciudad = ciudad;
		this.calle = calle;
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "AeropuertoPrivado [id=" + id + ", nombre=" + nombre + ", anio_inauguracion=" + anio_inauguracion
				+ ", capacidad=" + capacidad + ", id_direccion=" + id_direccion + ", numero_socios=" + numero_socios
				+ ", pais=" + pais + ", ciudad=" + ciudad + ", calle=" + calle + ", numero=" + numero + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAnio_inauguracion() {
		return anio_inauguracion;
	}

	public void setAnio_inauguracion(int anio_inauguracion) {
		this.anio_inauguracion = anio_inauguracion;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public int getId_direccion() {
		return id_direccion;
	}

	public void setId_direccion(int id_direccion) {
		this.id_direccion = id_direccion;
	}

	public float getNumero_socios() {
		return numero_socios;
	}

	public void setNumero_socios(float numero_socios) {
		this.numero_socios = numero_socios;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public static String[] getCampos() {
		String[] campos = {"nombre", "anio_inauguracion", "capacidad", "numero_socios", "pais", "ciudad", "calle", "numero"};
		return campos;
	}
	
}
