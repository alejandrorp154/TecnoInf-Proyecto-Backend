package com.javaee.pryectoBack.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.javaee.pryectoBack.datatypes.DTOComentario;

@Entity
public class Comentario extends ComentarioReaccion implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String contenido;
	private Date fecha;
	
	@OneToMany
	private List<Comentario> comentarios = new ArrayList<>();
	
	public Comentario() {
		super();
	}
	
	public Comentario(DTOComentario dtoComentario) {
		this.contenido = dtoComentario.getContenido();
		this.fecha = dtoComentario.getFecha();
	}
	
	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
