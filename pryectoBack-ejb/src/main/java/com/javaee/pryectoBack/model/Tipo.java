package com.javaee.pryectoBack.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Tipo implements Serializable
{
	private static final long serialVersionUID = 1L;	
	@Id
	private int idPublicacion;
	private tipos tipo;
	
	public Tipo() {
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
