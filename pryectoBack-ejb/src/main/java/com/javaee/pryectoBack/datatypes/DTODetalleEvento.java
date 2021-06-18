package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.javaee.pryectoBack.model.Evento;
import com.javaee.pryectoBack.model.estados;
import com.javaee.pryectoBack.model.estadosContactos;

public class DTODetalleEvento implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idEvento;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	private estados estado;
	private int idPublicacion;
	protected String idPersona;
	private String idChat;
	private String nombre;
	private DTOUbicacion ubicacion;
	private String nombreImagen;
	private String imagen;
	private String extension;
	private boolean isOwner;
	private estadosContactos estadoSolicitud;
	private List<DTOUsuarioEvento> invitados = new ArrayList<>();

	public DTODetalleEvento() {
	}

	public DTODetalleEvento(Evento evento, List<DTOUsuarioEvento> invitados) {
		this.idEvento = evento.getIdEvento();
		this.descripcion = evento.getDescripcion();
		this.fechaInicio = evento.getFechaInicio();
		this.fechaFin = evento.getFechaFin();
		this.estado = evento.getEstado();
		this.idPersona = evento.getUsuarioCreador().getIdPersona();
		this.nombre = evento.getNombre();
		this.idChat = evento.getIdChat();
		this.ubicacion = evento.getUbicacion() != null ? new DTOUbicacion(evento.getUbicacion(), this.idPersona) : new DTOUbicacion();
		this.nombreImagen = evento.getNombreImagen();
		this.extension = evento.getExtension();
		this.imagen = evento.getImagen();
		this.invitados = invitados;
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
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

	public String getIdChat() {
		return idChat;
	}

	public void setIdChat(String idChat) {
		this.idChat = idChat;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public DTOUbicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(DTOUbicacion dtoUbicacion) {
		this.ubicacion = dtoUbicacion;
	}

	public String getNombreImagen() {
		return nombreImagen;
	}

	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public boolean isOwner() {
		return isOwner;
	}

	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}

	public estadosContactos getEstadoSolicitud() {
		return estadoSolicitud;
	}

	public void setEstadoSolicitud(estadosContactos estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	public List<DTOUsuarioEvento> getInvitados() {
		return invitados;
	}

	public void setInvitados(List<DTOUsuarioEvento> invitados) {
		this.invitados = invitados;
	}
}
