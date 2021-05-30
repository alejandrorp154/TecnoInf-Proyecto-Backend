package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;

import com.javaee.pryectoBack.model.Multimedia;

public class DTOMultimedia implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int idMultimedia;
	private String contenido;
	private String nombre;
	private String extension;
	private String idPersona;
	
	public DTOMultimedia(int idMultimedia, String contenido, String nombre, String extension, String idPersona) {
		super();
		this.idMultimedia = idMultimedia;
		this.contenido = contenido;
		this.nombre = nombre;
		this.extension = extension;
		this.idPersona = idPersona;
	}
	
	public DTOMultimedia() {
	}

	public DTOMultimedia(Multimedia galeria) {
		this.idMultimedia = galeria.getIdMultimedia();
		this.contenido = galeria.getContenido();
		this.nombre = galeria.getNombre();
		this.extension = galeria.getExtension();
		this.idPersona = galeria.getPerfil().getUsuario().getIdPersona();
	}

	public int getidMultimedia() {
		return idMultimedia;
	}

	public void setidMultimedia(int idMultimedia) {
		this.idMultimedia = idMultimedia;
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
