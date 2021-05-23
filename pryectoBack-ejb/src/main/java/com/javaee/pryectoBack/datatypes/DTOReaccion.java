package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;

import com.javaee.pryectoBack.model.reacciones;

public class DTOReaccion implements Serializable
{
	private static final long serialVersionUID = 1L;
	protected int idComentarioReaccion;
	private int idPublicacion;
	protected String idPersona;
	private reacciones reaccion;
	
	public DTOReaccion(int idComentarioReaccion, int idPublicacion, String idPersona, reacciones reaccion) {
		super();
		this.idComentarioReaccion = idComentarioReaccion;
		this.idPublicacion = idPublicacion;
		this.idPersona = idPersona;
		this.reaccion = reaccion;
	}

	public DTOReaccion() {
	}

	public int getIdComentarioReaccion() {
		return idComentarioReaccion;
	}

	public void setIdComentarioReaccion(int idComentarioReaccion) {
		this.idComentarioReaccion = idComentarioReaccion;
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

	public reacciones getReaccion() {
		return reaccion;
	}

	public void setReaccion(reacciones reaccion) {
		this.reaccion = reaccion;
	}
}
