package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;
import java.util.Date;

import com.javaee.pryectoBack.model.tipos;

public class DTOMensaje implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String idMensaje;
	private Date fecha;
	private tipos tipo;
	private String path;
	private String contenido;
	private DTOChat chat;
	
	public DTOMensaje(String idMensaje, Date fecha, tipos tipo, String path, String contenido, DTOChat chat) {
		super();
		this.idMensaje = idMensaje;
		this.fecha = fecha;
		this.tipo = tipo;
		this.path = path;
		this.contenido = contenido;
		this.chat = chat;
	}

	public DTOMensaje() {
	}

	public String getIdMensaje() {
		return idMensaje;
	}

	public void setIdMensaje(String idMensaje) {
		this.idMensaje = idMensaje;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public tipos getTipo() {
		return tipo;
	}

	public void setTipo(tipos tipo) {
		this.tipo = tipo;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public DTOChat getChat() {
		return chat;
	}

	public void setChat(DTOChat chat) {
		this.chat = chat;
	}
}
