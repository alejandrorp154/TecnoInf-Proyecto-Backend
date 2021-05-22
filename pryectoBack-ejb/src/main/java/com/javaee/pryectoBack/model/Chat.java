package com.javaee.pryectoBack.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Chat implements Serializable
{
	private static final long serialVersionUID = 1L;	
	@Id
	private String idChat;
	private Date updatedAt;
	@ManyToMany
	private List<Usuario> usuarios = new ArrayList<>();
	@OneToMany(mappedBy = "chat")
	private List<Mensaje> mensajes = new ArrayList<>();
	
	public Chat() {
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
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public List<Mensaje> getMensajes() {
		return mensajes;
	}
	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}
}
