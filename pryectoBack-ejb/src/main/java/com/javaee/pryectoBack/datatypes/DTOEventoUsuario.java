package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;

import com.javaee.pryectoBack.model.estadosContactos;

public class DTOEventoUsuario implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String idPersona;
	private int idEvento;
	private estadosContactos estadoContactos;
	
	public DTOEventoUsuario() {
	}

	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public estadosContactos getEstadoContactos() {
		return estadoContactos;
	}

	public void setEstadoContactos(estadosContactos estadoContactos) {
		this.estadoContactos = estadoContactos;
	}
}
