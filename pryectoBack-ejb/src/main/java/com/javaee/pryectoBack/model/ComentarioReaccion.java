package com.javaee.pryectoBack.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public abstract class ComentarioReaccion implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int idComentarioReaccion;
	@ManyToOne
	private Publicacion publicacion;
	
	@ManyToOne
	protected Usuario usuario;

	public ComentarioReaccion() {
	}

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getIdComentarioReaccion() {
		return idComentarioReaccion;
	}

	public void setIdComentarioReaccion(int idComentarioReaccion) {
		this.idComentarioReaccion = idComentarioReaccion;
	}

}
