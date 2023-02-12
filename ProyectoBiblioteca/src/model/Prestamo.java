package model;

import java.time.LocalDate;


public class Prestamo {
	private int id_prestamo;
	private Libro lib;
	private Alumno alum;
	private LocalDate fecha_prestamo;
	
	public Prestamo(int id_prestamo, Alumno alum, Libro lib, LocalDate fecha_prestamo) {
		super();
		this.id_prestamo = id_prestamo;
		this.alum = alum;
		this.lib = lib;
		this.fecha_prestamo = fecha_prestamo;
	}
	
	public int getId_prestamo() {
		return id_prestamo;
	}
	
	public void setId_prestamo(int id_prestamo) {
		this.id_prestamo = id_prestamo;
	}
	
	
	public Libro getLib() {
		return lib;
	}

	public void setLib(Libro lib) {
		this.lib = lib;
	}

	public Alumno getAlum() {
		return alum;
	}

	public void setAlum(Alumno alum) {
		this.alum = alum;
	}

	public LocalDate getFecha_prestamo() {
		return fecha_prestamo;
	}
	
	public void setFecha_prestamo(LocalDate fecha_prestamo) {
		this.fecha_prestamo = fecha_prestamo;
	}
	
	@Override
	public String toString() {
		return "(" + id_prestamo + ") " + alum.getDni() + " -> " + lib.getCodigo()
				+ ", desde " + fecha_prestamo;
	}
	
	
}
