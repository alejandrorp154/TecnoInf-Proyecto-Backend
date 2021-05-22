package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;
import java.util.Date;

public class DTOComentario implements Serializable
{
	private static final long serialVersionUID = 1L;
	protected int idComentarioReaccion;
	private String contenido;
	private Date fecha;
	private int idPublicacion;
	protected String idPersona;
	
	public DTOComentario(int idComentarioReaccion, String contenido, Date fecha, int idPublicacion, String idPersona) {
		super();
		this.idComentarioReaccion = idComentarioReaccion;
		this.contenido = contenido;
		this.fecha = fecha;
		this.idPublicacion = idPublicacion;
		this.idPersona = idPersona;
	}

	public DTOComentario() {
	}

	public int getIdComentarioReaccion() {
		return idComentarioReaccion;
	}

	public void setIdComentarioReaccion(int idComentarioReaccion) {
		this.idComentarioReaccion = idComentarioReaccion;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getIdPublicacion() {
		return idPublicacion;
	}

	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}

	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}
}
