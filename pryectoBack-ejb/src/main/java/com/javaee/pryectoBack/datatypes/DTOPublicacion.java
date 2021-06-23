package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.javaee.pryectoBack.model.Publicacion;

public class DTOPublicacion implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int idPublicacion;
	private String contenido;
	private Date fecha;
	private DTOTipo tipo;
	protected String idPersona;
	private String extension;
	private String nombre;
	private List<DTOComentario> comentarioReacciones = new ArrayList<>();
	private DTOEvento evento;
	private DTOPerfilUsuario perfil;
	private Integer cantidadLikes;
	private Integer cantidadDislikes;
	private Integer cantidadComentarios;


	public DTOPublicacion(int idPublicacion, String contenido, Date fecha, DTOTipo dtoTipo, String idPersona,
			String extension, String nombre, List<DTOComentario> dtoComentarioReacciones, DTOEvento dtoEvento,
			DTOPerfilUsuario dtoPerfil) {
		super();
		this.idPublicacion = idPublicacion;
		this.contenido = contenido;
		this.fecha = fecha;
		this.tipo = dtoTipo;
		this.idPersona = idPersona;
		this.extension = extension;
		this.nombre = nombre;
		this.comentarioReacciones = dtoComentarioReacciones;
		this.evento = dtoEvento;
		this.perfil = dtoPerfil;
	}

	public DTOPublicacion() {
	}

	public DTOPublicacion(Publicacion publicacion) {
		this.idPublicacion = publicacion.getIdPublicacion();
		this.contenido = publicacion.getContenido();
		this.fecha = publicacion.getFecha();
		this.tipo = new DTOTipo(publicacion.getTipo());
		this.idPersona = publicacion.getPerfil().getUsuario().getIdPersona();
		this.extension = publicacion.getExtension();
		this.nombre = publicacion.getNombre();
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

	public DTOTipo getTipo() {
		return tipo;
	}

	public void setTipo(DTOTipo dtoTipo) {
		this.tipo = dtoTipo;
	}

	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<DTOComentario> getComentarioReacciones() {
		return comentarioReacciones;
	}

	public void setComentarioReacciones(List<DTOComentario> dtoComentarioReacciones) {
		this.comentarioReacciones = dtoComentarioReacciones;
	}

	public DTOEvento getEvento() {
		return evento;
	}

	public void setEvento(DTOEvento dtoEvento) {
		this.evento = dtoEvento;
	}

	public DTOPerfilUsuario getPerfil() {
		return perfil;
	}

	public void setPerfil(DTOPerfilUsuario dtoPerfil) {
		this.perfil = dtoPerfil;
	}

	public Integer getCantidadLikes() {
		return cantidadLikes;
	}

	public void setCantidadLikes(Integer cantidadLikes) {
		this.cantidadLikes = cantidadLikes;
	}

	public Integer getCantidadDislikes() {
		return cantidadDislikes;
	}

	public void setCantidadDislikes(Integer cantidadDislikes) {
		this.cantidadDislikes = cantidadDislikes;
	}

	public Integer getCantidadComentarios() {
		return cantidadComentarios;
	}

	public void setCantidadComentarios(Integer cantidadComentarios) {
		this.cantidadComentarios = cantidadComentarios;
	}
}
