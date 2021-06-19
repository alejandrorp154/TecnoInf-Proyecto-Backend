package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

public class DTOComentario implements Serializable
{
	private static final long serialVersionUID = 1L;
	protected String idComentario;
	private String internalId;
	private String contenido;
	private Date fecha;
	private int idPublicacion;
	private String idComentarioPadre;
	protected String idPersona;
	private List<DTOComentario> comentariosHijos;
	
	public DTOComentario(String idComentario, String contenido, Date fecha, int idPublicacion, String idPersona, String idComentarioPadre) {
		super();
		this.idComentarioPadre = idComentarioPadre;
		this.idComentario = idComentario;
		this.contenido = contenido;
		this.fecha = fecha;
		this.idPublicacion = idPublicacion;
		this.idPersona = idPersona;
	}

	public DTOComentario() {
	}

	public String getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(String idComentario) {
		this.idComentario = idComentario;
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
		newDocument.append("internalId", this.internalId);
		return newDocument;
	}
	
	public DTOComentario(Document comentario) {
		ObjectId idObj = (ObjectId) comentario.get("_id");
		if (idObj != null) {
			this.idComentario = (String) idObj.toString();		
		} else {
			this.internalId = (String) comentario.get("internalId"); 
		}
		this.idPersona = (String) comentario.get("idPersona");
		this.contenido = (String) comentario.get("contenido");
		this.fecha = (Date) comentario.get("fecha");
		this.idComentarioPadre = (String) comentario.get("idComentarioPadre");		
		this.comentariosHijos = new ArrayList<DTOComentario>();
	}

	public List<DTOComentario> getComentariosHijos() {
		return comentariosHijos;
	}

	public void setComentariosHijos(List<DTOComentario> comentariosHijos) {
		this.comentariosHijos = comentariosHijos;
	}

	public String getInternalId() {
		return internalId;
	}

	public void setInternalId(String internalId) {
		this.internalId = internalId;
	}
}
