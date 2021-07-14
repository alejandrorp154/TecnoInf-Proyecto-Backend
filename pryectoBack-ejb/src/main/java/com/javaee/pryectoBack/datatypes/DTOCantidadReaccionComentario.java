package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;

public class DTOCantidadReaccionComentario implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int cantidadLikes;
	private int cantidadDislikes;
	private int cantidadComentarios;
	private int idPublicacion;
	
	public DTOCantidadReaccionComentario() {
	}

	public int getCantidadLikes() {
		return cantidadLikes;
	}

	public void setCantidadLikes(int cantidadLikes) {
		this.cantidadLikes = cantidadLikes;
	}

	public int getCantidadDislikes() {
		return cantidadDislikes;
	}

	public void setCantidadDislikes(int cantidadDislikes) {
		this.cantidadDislikes = cantidadDislikes;
	}

	public int getCantidadComentarios() {
		return cantidadComentarios;
	}

	public void setCantidadComentarios(int cantidadComentarios) {
		this.cantidadComentarios = cantidadComentarios;
	}

	public int getIdPublicacion() {
		return idPublicacion;
	}

	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}

}
