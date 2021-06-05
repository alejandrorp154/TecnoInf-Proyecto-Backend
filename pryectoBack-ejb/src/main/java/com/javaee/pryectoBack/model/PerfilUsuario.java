package com.javaee.pryectoBack.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class PerfilUsuario implements Serializable
{
	private static final long serialVersionUID = 1L;	
	@Id
	@OneToOne
	@JoinColumn(name = "idPersona")
	private Usuario usuario;

	@ManyToMany(mappedBy = "perfiles")
	private List<Interes> intereses = new ArrayList<>();
	@OneToMany(mappedBy = "perfil")
	private List<Multimedia> galerias = new ArrayList<>();
	@OneToMany(mappedBy = "perfil")
	private List<Publicacion> publicaciones = new ArrayList<>();

	private String imagenPerfil;

	
	public PerfilUsuario() {
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Interes> getIntereses() {
		return intereses;
	}

	public void setIntereses(List<Interes> intereses) {
		this.intereses = intereses;
	}

	public List<Multimedia> getGalerias() {
		return galerias;
	}

	public void setGalerias(List<Multimedia> galerias) {
		this.galerias = galerias;
	}

	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}

	public String getImagenPerfil() {
		return imagenPerfil;
	}

	public void setImagenPerfil(String imagenPerfil) {
		this.imagenPerfil = imagenPerfil;
	}
}
