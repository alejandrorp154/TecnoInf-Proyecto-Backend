package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;
import java.util.Date;

import org.bson.Document;

public class DTOComentario implements Serializable
{
	private static final long serialVersionUID = 1L;
	protected String idComentarioReaccion;
	private String contenido;
	private Date fecha;
	private int idPublicacion;
	private String idComentarioPadre;
	protected String idPersona;
	
	public DTOComentario(String idComentarioReaccion, String contenido, Date fecha, int idPublicacion, String idPersona, String idComentarioPadre) {
		super();
		this.idComentarioPadre = idComentarioPadre;
		this.idComentarioReaccion = idComentarioReaccion;
		this.contenido = contenido;
		this.fecha = fecha;
		this.idPublicacion = idPublicacion;
		this.idPersona = idPersona;
	}

	public DTOComentario() {
	}

	public String getIdComentarioReaccion() {
		return idComentarioReaccion;
	}

	public void setIdComentarioReaccion(String idComentarioReaccion) {
		this.idComentarioReaccion = idComentarioReaccion;
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

	public int getIdPublicacion() {
		return idPublicacion;
	}

	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}

	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}

	public String getIdComentarioPadre() {
		return idComentarioPadre;
	}

	public void setIdComentarioPadre(String idComentarioPadre) {
		this.idComentarioPadre = idComentarioPadre;
	}	
	
	public Document getDocument() {
		Document newDocument = new Document();
		newDocument.append("idPublicacion", this.idPublicacion);
		newDocument.append("idComentarioPadre", this.idComentarioPadre);
		newDocument.append("idPersona", this.idPersona);
		newDocument.append("contenido", this.contenido);
		newDocument.append("fecha", this.fecha);
		return newDocument;
	}
}
