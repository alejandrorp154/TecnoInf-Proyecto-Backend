package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;
import java.util.Date;

import com.javaee.pryectoBack.model.UsuarioContacto;
import com.javaee.pryectoBack.model.estadosContactos;

public class DTOUsuarioContacto implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String idPersona;
	private String contactoIdPersona;
	private Date fechaContactos;
	private estadosContactos estadoContactos;
	
	public DTOUsuarioContacto() {
	}

	public DTOUsuarioContacto(UsuarioContacto usuarioContacto1) {
		this.idPersona = usuarioContacto1.getIdPersona();
		this.contactoIdPersona = usuarioContacto1.getContactoIdPersona();
		this.fechaContactos = usuarioContacto1.getFechaContactos();
		this.estadoContactos = usuarioContacto1.getEstadoContactos();
	}

	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}

	public String getContactoIdPersona() {
		return contactoIdPersona;
	}

	public void setContactoIdPersona(String contactoIdPersona) {
		this.contactoIdPersona = contactoIdPersona;
	}

	public Date getFechaContactos() {
		return fechaContactos;
	}

	public void setFechaContactos(Date fechaContactos) {
		this.fechaContactos = fechaContactos;
	}

	public estadosContactos getEstadoContactos() {
		return estadoContactos;
	}

	public void setEstadoContactos(estadosContactos estadoContactos) {
		this.estadoContactos = estadoContactos;
	}
}
