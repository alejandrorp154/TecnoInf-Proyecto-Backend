package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;

import com.javaee.pryectoBack.model.Usuario;

public class DTOUsuarioPerfil implements Serializable {

    private static final long serialVersionUID = 1L;
    private String idPersona;
    private String email;
    private String nombre;
    private String apellido;
    private String nickname;
    private String imagenPerfil;
    private String nombreImagen;
	private String pais;
	private String extensionImagen;
	private String direccion;
	private String celular;
	
	public DTOUsuarioPerfil() {
	}
	
	public DTOUsuarioPerfil(Usuario usuario) {
		this.idPersona = usuario.getIdPersona();
		this.email = usuario.getEmail();
		this.nombre = usuario.getNombre();
		this.apellido = usuario.getApellido();
		this.nickname = usuario.getNickname();
		this.imagenPerfil = usuario.getPerfil().getImagenPerfil();
		this.nombreImagen = usuario.getPerfil().getNombreImagen();
		this.extensionImagen = usuario.getPerfil().getExtension();
		this.pais = usuario.getPais();
		this.direccion = usuario.getDireccion();
		this.celular = usuario.getCelular();
	}

	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getExtensionImagen() {
		return extensionImagen;
	}

	public void setExtensionImagen(String extensionImagen) {
		this.extensionImagen = extensionImagen;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
}
