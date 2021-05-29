package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;

public class DTOComentarioReaccion implements Serializable
{
	private static final long serialVersionUID = 1L;
	protected int idComentarioReaccion;
	private DTOPublicacion dtoPublicacion;
	protected DTOUsuario dtoUsuario;
	
	public DTOComentarioReaccion() {
	}

	public DTOComentarioReaccion(int idComentarioReaccion, DTOPublicacion dtoPublicacion, DTOUsuario dtoUsuario) {
		super();
		this.idComentarioReaccion = idComentarioReaccion;
		this.dtoPublicacion = dtoPublicacion;
		this.dtoUsuario = dtoUsuario;
	}

	public int getIdComentarioReaccion() {
		return idComentarioReaccion;
	}

	public void setIdComentarioReaccion(int idComentarioReaccion) {
		this.idComentarioReaccion = idComentarioReaccion;
	}

	public DTOPublicacion getDtoPublicacion() {
		return dtoPublicacion;
	}

	public void setDtoPublicacion(DTOPublicacion dtoPublicacion) {
		this.dtoPublicacion = dtoPublicacion;
	}

	public DTOUsuario getDtoUsuario() {
		return dtoUsuario;
	}

	public void setDtoUsuario(DTOUsuario dtoUsuario) {
		this.dtoUsuario = dtoUsuario;
	}
}
