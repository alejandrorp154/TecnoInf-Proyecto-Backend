package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DTOChat implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String idChat;
	private Date updatedAt;
	private List<DTOUsuario> usuarios = new ArrayList<>();
	private List<DTOMensaje> mensajes = new ArrayList<>();
	
	public DTOChat(String idChat, Date updatedAt, List<DTOUsuario> usuarios, List<DTOMensaje> mensajes) {
		super();
		this.idChat = idChat;
		this.updatedAt = updatedAt;
		this.usuarios = usuarios;
		this.mensajes = mensajes;
	}

	public DTOChat() {
	}

	public String getIdChat() {
		return idChat;
	}

	public void setIdChat(String idChat) {
		this.idChat = idChat;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<DTOUsuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<DTOUsuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<DTOMensaje> getMensajes() {
		return mensajes;
	}

	public void setMensajes(List<DTOMensaje> mensajes) {
		this.mensajes = mensajes;
	}
}
