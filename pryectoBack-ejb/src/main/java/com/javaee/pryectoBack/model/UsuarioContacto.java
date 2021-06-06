package com.javaee.pryectoBack.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(UsuarioContactoId.class)
public class UsuarioContacto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String idPersona;
	@Id
	private String contactoIdPersona;

	private Date fechaContactos;
	
	public UsuarioContacto() {
	}

	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}

	public Date getFechaContactos() {
		return fechaContactos;
	}

	public void setFechaContactos(Date fechaContactos) {
		this.fechaContactos = fechaContactos;
	}

	public String getContactoIdPersona() {
		return contactoIdPersona;
	}

	public void setContactoIdPersona(String contactoIdPersona) {
		this.contactoIdPersona = contactoIdPersona;
	}
}
