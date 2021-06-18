package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;

import com.javaee.pryectoBack.model.Usuario;
import com.javaee.pryectoBack.model.estadosContactos;

public class DTOUsuarioEvento implements Serializable {
	private static final long serialVersionUID = 1L;
	private String idPersona;
	private String nombre;
	private String apellido;
	private String nickname;
	private String imagenPerfil;
	private String nombreImagen;
	private String extensionImagen;
	private estadosContactos estadoContactos;
	private boolean isOwner;
	
	public DTOUsuarioEvento() {
	}

	public DTOUsuarioEvento(Usuario usuario, estadosContactos estadoContactos) {
		this.idPersona = usuario.getIdPersona();
		this.nombre = usuario.getNombre();
		this.apellido = usuario.getApellido();
		this.nickname = usuario.getNickname();
		this.imagenPerfil = usuario.getPerfil().getImagenPerfil();
		this.nombreImagen = usuario.getPerfil().getNombreImagen();
		this.extensionImagen = usuario.getPerfil().getExtension();
		this.estadoContactos = estadoContactos;
	}

	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public String getExtensionImagen() {
		return extensionImagen;
	}

	public void setExtensionImagen(String extensionImagen) {
		this.extensionImagen = extensionImagen;
	}

	public estadosContactos getEstadoContactos() {
		return estadoContactos;
	}

	public void setEstadoContactos(estadosContactos estadoContactos) {
		this.estadoContactos = estadoContactos;
	}

	public boolean isOwner() {
		return isOwner;
	}

	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}
}
