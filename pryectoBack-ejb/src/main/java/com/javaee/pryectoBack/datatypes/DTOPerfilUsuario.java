package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DTOPerfilUsuario implements Serializable
{
	private static final long serialVersionUID = 1L;
	private DTOUsuario usuario;
	private List<DTOInteres> intereses = new ArrayList<>();
	private List<DTOMultimedia> galerias = new ArrayList<>();
	private List<DTOPublicacion> publicaciones = new ArrayList<>();
	
	public DTOPerfilUsuario() {
	}

	public DTOPerfilUsuario(DTOUsuario usuario, List<DTOInteres> intereses, List<DTOMultimedia> galerias,
			List<DTOPublicacion> publicaciones) {
		super();
		this.usuario = usuario;
		this.intereses = intereses;
		this.galerias = galerias;
		this.publicaciones = publicaciones;
	}

	public DTOUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(DTOUsuario usuario) {
		this.usuario = usuario;
	}

	public List<DTOInteres> getIntereses() {
		return intereses;
	}

	public void setIntereses(List<DTOInteres> intereses) {
		this.intereses = intereses;
	}

	public List<DTOMultimedia> getGalerias() {
		return galerias;
	}

	public void setGalerias(List<DTOMultimedia> galerias) {
		this.galerias = galerias;
	}

	public List<DTOPublicacion> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(List<DTOPublicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}
}
