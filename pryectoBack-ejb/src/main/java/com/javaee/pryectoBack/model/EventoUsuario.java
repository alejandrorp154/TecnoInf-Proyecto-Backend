package com.javaee.pryectoBack.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(EventoUsuarioId.class)
public class EventoUsuario implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String idPersona;
	@Id
	private int idEvento;
	
	private String idPersonaInvitador;

	@Column(length=32, columnDefinition = "varchar(32) default 'pendiente'")
	@Enumerated(value = EnumType.STRING)
	private estadosContactos estadoContactos;

	public EventoUsuario() {
	}

	public EventoUsuario(String idPersona, int idEvento, estadosContactos estado, String idPersonaInvitador) {
		this.idPersona = idPersona;
		this.idEvento = idEvento;
		this.estadoContactos = estado;
		this.idPersonaInvitador = idPersonaInvitador;
	}

	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public estadosContactos getEstadoContactos() {
		return estadoContactos;
	}

	public void setEstadoContactos(estadosContactos estadoContactos) {
		this.estadoContactos = estadoContactos;
	}

	public String getIdPersonaInvitador() {
		return idPersonaInvitador;
	}

	public void setIdPersonaInvitador(String idPersonaInvitador) {
		this.idPersonaInvitador = idPersonaInvitador;
	}
}
