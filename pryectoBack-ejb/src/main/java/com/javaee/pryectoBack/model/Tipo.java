package com.javaee.pryectoBack.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.javaee.pryectoBack.datatypes.DTOTipo;

@Entity
public class Tipo implements Serializable
{
	private static final long serialVersionUID = 1L;	
	@Id
	private int idPublicacion;
	private tipos tipo;
	
	public Tipo() {
	}
	
	public Tipo(DTOTipo dtoTipo) {
		this.idPublicacion = dtoTipo.getIdPublicacion();
		this.tipo = dtoTipo.getTipo();
	}

	public int getIdPublicacion() {
		return idPublicacion;
	}

	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}


	public tipos getTipo() {
		return tipo;
	}
	public void setTipo(tipos tipo) {
		this.tipo = tipo;
	}
}
