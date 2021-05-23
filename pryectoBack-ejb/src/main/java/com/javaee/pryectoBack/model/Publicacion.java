package com.javaee.pryectoBack.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Publicacion implements Serializable
{
	private static final long serialVersionUID = 1L;	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idPublicacion;
	private String contenido;
	private Date fecha;
	@OneToMany(mappedBy = "publicacion")
	private List<Tipo> tipos = new ArrayList<>();
	@OneToMany(mappedBy = "publicacion")
	private List<ComentarioReaccion> comentarioReacciones = new ArrayList<>();
	@ManyToOne
	private Evento evento;
	@ManyToOne
	private PerfilUsuario perfil;
	
	public Publicacion() {
	}
	
	public int getIdPublicacion() {
		return idPublicacion;
	}
	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
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
	public List<Tipo> getTipos() {
		return tipos;
	}
	public void setTipos(List<Tipo> tipos) {
		this.tipos = tipos;
	}
	public List<ComentarioReaccion> getComentarioReacciones() {
		return comentarioReacciones;
	}
	public void setComentarioReacciones(List<ComentarioReaccion> comentarioReacciones) {
		this.comentarioReacciones = comentarioReacciones;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public PerfilUsuario getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilUsuario perfil) {
		this.perfil = perfil;
	}
}
