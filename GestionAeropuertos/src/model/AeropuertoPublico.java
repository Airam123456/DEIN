package model;

public class AeropuertoPublico {

	private int id;
	private String nombre;
	private int anio_inauguracion;
	private int capacidad;
	private int id_direccion;
	private float financiacion;
	private int num_trabajadores;
	private String pais;
	private String ciudad;
	private String calle;
	private int numero;
	
	public AeropuertoPublico(int id, String nombre, int anio_inauguracion, int capacidad, int id_direccion,
			float financiacion, int num_trabajadores, String pais, String ciudad, String calle,
			int numero) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.anio_inauguracion = anio_inauguracion;
		this.capacidad = capacidad;
		this.id_direccion = id_direccion;
		this.financiacion = financiacion;
		this.num_trabajadores = num_trabajadores;
		this.pais = pais;
		this.ciudad = ciudad;
		this.calle = calle;
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "AeropuertoPublico [id=" + id + ", nombre=" + nombre + ", anio_inauguracion=" + anio_inauguracion
				+ ", capacidad=" + capacidad + ", id_direccion=" + id_direccion
				+ ", financiacion=" + financiacion + ", num_trabajadores=" + num_trabajadores + ", pais=" + pais
				+ ", ciudad=" + ciudad + ", calle=" + calle + ", numero=" + numero + "]";
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

	public float getFinanciacion() {
		return financiacion;
	}

	public void setFinanciacion(float financiacion) {
		this.financiacion = financiacion;
	}

	public int getNum_trabajadores() {
		return num_trabajadores;
	}

	public void setNum_trabajadores(int num_trabajadores) {
		this.num_trabajadores = num_trabajadores;
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
		String[] campos = {"nombre", "anio_inauguracion", "capacidad", "financiacion", "num_trabajadores", "pais", "ciudad", "calle", "numero"};
		return campos;
	}
	
	
}
