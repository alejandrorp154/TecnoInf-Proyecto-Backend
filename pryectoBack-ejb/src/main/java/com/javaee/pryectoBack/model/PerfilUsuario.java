package com.javaee.pryectoBack.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.javaee.pryectoBack.datatypes.DTOPerfilUsuario;
import com.javaee.pryectoBack.datatypes.DTOUsuario;

@Entity
public class PerfilUsuario implements Serializable
{
	private static final long serialVersionUID = 1L;	
	
	@Id
	private String idPersona;
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPersona")
	private Usuario usuario;

	@ManyToMany(mappedBy = "perfiles")
	private List<Interes> intereses = new ArrayList<>();

	@OneToMany(mappedBy = "perfil")
	private List<Multimedia> galerias = new ArrayList<>();

	@OneToMany(mappedBy = "perfil")
	private List<Publicacion> publicaciones = new ArrayList<>();

	@Column(columnDefinition="text", length=10485760)
	private String imagenPerfil;
	private String nombreImagen;
	private String extensionImagen;

	
	public PerfilUsuario() {
	}

	public PerfilUsuario(Usuario user, DTOUsuario dtoUsuario) {
		this.idPersona = user.getIdPersona();
		this.usuario = user;
		this.intereses = new ArrayList<>();
		this.galerias = new ArrayList<>();
		this.publicaciones = new ArrayList<>();
		this.imagenPerfil = dtoUsuario.getImagenPerfil();
		this.nombreImagen = dtoUsuario.getNombreImagen();
		this.extensionImagen = dtoUsuario.getExtension();
	}

	public PerfilUsuario(DTOPerfilUsuario perfil) {
		this.idPersona = perfil.getUsuario().getIdPersona();
		this.usuario = new Usuario(perfil.getUsuario());

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


	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}

	public String getImagenPerfil() {
		return imagenPerfil;
	}

	public void setImagenPerfil(String imagenPerfil) {
		this.imagenPerfil = imagenPerfil;
	}

	public String getNombreImagen() {
		return nombreImagen;
	}

	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}

	public String getExtension() {
		return extensionImagen;
	}

	public void setExtension(String extensionImagen) {
		this.extensionImagen = extensionImagen;
	}
}
