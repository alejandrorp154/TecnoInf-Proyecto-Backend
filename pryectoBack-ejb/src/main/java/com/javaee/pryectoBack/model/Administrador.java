package com.javaee.pryectoBack.model;

import com.javaee.pryectoBack.datatypes.DTOAdministrador;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Administrador extends Usuario implements Serializable
{
	private static final long serialVersionUID = 1L;

	public Administrador() {
	}

	public Administrador(DTOAdministrador dtoAdministrador){
		this.idPersona = dtoAdministrador.getIdPersona();
		this.email = dtoAdministrador.getEmail();
		this.nombre = dtoAdministrador.getNombre();
		this.apellido = dtoAdministrador.getApellido();
	}
}
