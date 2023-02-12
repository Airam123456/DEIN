package model;

import java.time.LocalDate;


public class Historico_prestamo {
	private int id_prestamo, codigo_libro;
	private String dni_alumno;
	private LocalDate fecha_prestamo, fecha_devolucion;
	
	public Historico_prestamo(int id_prestamo, int codigo_libro, String dni_alumno, LocalDate fecha_prestamo,
			LocalDate fecha_devolucion) {
		super();
		this.id_prestamo = id_prestamo;
		this.codigo_libro = codigo_libro;
		this.dni_alumno = dni_alumno;
		this.fecha_prestamo = fecha_prestamo;
		this.fecha_devolucion = fecha_devolucion;
	}
	
	public int getId_prestamo() {
		return id_prestamo;
	}
	
	public void setId_prestamo(int id_prestamo) {
		this.id_prestamo = id_prestamo;
	}
	
	public int getCodigo_libro() {
		return codigo_libro;
	}
	
	public void setCodigo_libro(int codigo_libro) {
		this.codigo_libro = codigo_libro;
	}
	
	public String getDni_alumno() {
		return dni_alumno;
	}
	
	public void setDni_alumno(String dni_alumno) {
		this.dni_alumno = dni_alumno;
	}
	
	public LocalDate getFecha_prestamo() {
		return fecha_prestamo;
	}
	
	public void setFecha_prestamo(LocalDate fecha_prestamo) {
		this.fecha_prestamo = fecha_prestamo;
	}
	
	public LocalDate getFecha_devolucion() {
		return fecha_devolucion;
	}
	
	public void setFecha_devolucion(LocalDate fecha_devolucion) {
		this.fecha_devolucion = fecha_devolucion;
	}
	
	@Override
	public String toString() {
		return "Historico_prestamo [id_prestamo=" + id_prestamo + ", codigo_libro=" + codigo_libro + ", dni_alumno="
				+ dni_alumno + ", fecha_prestamo=" + fecha_prestamo + ", fecha_devolucion=" + fecha_devolucion + "]";
	}
	
	
}
