package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;

import org.bson.Document;

import com.javaee.pryectoBack.model.reacciones;

public class DTOReaccion implements Serializable
{
	private static final long serialVersionUID = 1L;
	protected String idComentario;
	private int idPublicacion; 
	protected String idPersona;
	private reacciones reaccion;
	
	public DTOReaccion(String idComentario, int idPublicacion, String idPersona, reacciones reaccion) {
		super();
		this.idComentario = idComentario;
		this.idPublicacion = idPublicacion;
		this.idPersona = idPersona;
		this.reaccion = reaccion;
	}

	public DTOReaccion() {
	}

	public String getIdComentario() {
		return idComentario;
	}

	public void setIdComentarioReaccion(String idComentario) {
		this.idComentario = idComentario;
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

	public reacciones getReaccion() {
		return reaccion;
	}

	public void setReaccion(reacciones reaccion) {
		this.reaccion = reaccion;
	}
	
	public Document getDocumentPublicacion() {
		Document newDocument = new Document();
		newDocument.append("idPublicacion", this.idPublicacion);
		newDocument.append("idPersona", this.idPersona);
		newDocument.append("reaccion", String.valueOf(this.reaccion));
		return newDocument;
	}
	
	public Document getDocumentComentario() {
		Document newDocument = new Document();
		newDocument.append("idComentario", this.idComentario);
		newDocument.append("idPersona", this.idPersona);
		newDocument.append("reaccion", String.valueOf(this.reaccion));
		return newDocument;
	}
}
