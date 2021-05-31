package com.javaee.pryectoBack.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.javaee.pryectoBack.datatypes.DTOEvento;

@Entity
public class Evento implements Serializable
{
	private static final long serialVersionUID = 1L;	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idEvento;
	private String ubicacion;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	private estados estado;
	@ManyToMany
	private List<Usuario> usuarios = new ArrayList<>();
	private String idChat;
	@OneToMany(mappedBy = "evento")
	private List<Publicacion> publicaciones = new ArrayList<>();
	
	public Evento() {
	}
	
	public Evento(DTOEvento dtoEvento) {
		this.ubicacion = dtoEvento.getUbicacion();
		this.descripcion = dtoEvento.getDescripcion();
		this.fechaInicio = dtoEvento.getFechaInicio();
		this.fechaFin = dtoEvento.getFechaFin();
		this.estado = dtoEvento.getEstado();
		
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public estados getEstado() {
		return estado;
	}

	public void setEstado(estados estado) {
		this.estado = estado;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getIdChat() {
		return idChat;
	}

	public void setIdChat(String chat) {
		this.idChat = chat;
	}

	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}
}
