package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;

public class DTOMultimedia implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int idMedalla;
	private String contenido;
	private String nombre;
	private String extension;
	private String idPersona;
	
	public DTOMultimedia(int idMedalla, String contenido, String nombre, String extension, String idPersona) {
		super();
		this.idMedalla = idMedalla;
		this.contenido = contenido;
		this.nombre = nombre;
		this.extension = extension;
		this.idPersona = idPersona;
	}
	
	public DTOMultimedia() {
	}

	public int getIdMedalla() {
		return idMedalla;
	}

	public void setIdMedalla(int idMedalla) {
		this.idMedalla = idMedalla;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}
}
