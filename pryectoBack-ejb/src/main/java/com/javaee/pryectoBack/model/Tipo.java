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
	@ManyToOne
	@JoinColumn(name="idPublicacion")
	private Publicacion publicacion;
	private tipos tipo;
	
	public Tipo() {
	}
	
	public Publicacion getPublicacion() {
		return publicacion;
	}
	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}
	public tipos getTipo() {
		return tipo;
	}
	public void setTipo(tipos tipo) {
		this.tipo = tipo;
	}
}
