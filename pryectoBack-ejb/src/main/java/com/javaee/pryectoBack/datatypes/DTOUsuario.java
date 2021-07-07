package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;

import com.javaee.pryectoBack.model.Usuario;

public class DTOUsuario implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String idPersona;
	private String email;
	private String nombre;
	private String apellido;
	private String nickname;
	private String direccion;
	private String celular;
	private DTOMedalla medalla;
	private String pais;
	private String imagenPerfil;
	private String nombreImagen;
	private String extensionImagen;
	private boolean estaBloqueado;
	
	public DTOUsuario(String idPersona, String email, String nombre, String apellido, String nickname,
			String direccion, String celular, String pais, String imagenPerfil, String nombreImagen, String extensionImagen) {
		super();
		this.idPersona = idPersona;
		this.email = email;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nickname = nickname;
		this.direccion = direccion;
		this.celular = celular;
		this.pais = pais;
		this.imagenPerfil = imagenPerfil;
		this.nombreImagen = nombreImagen;
		this.extensionImagen = extensionImagen;

	}
	
	public DTOUsuario() {
	}
	
	public DTOUsuario(Usuario usuario) {
		this.idPersona = usuario.getIdPersona();
		this.email = usuario.getEmail();
		this.nombre = usuario.getNombre();
		this.apellido = usuario.getApellido();
		this.nickname = usuario.getNickname();
		this.direccion = usuario.getDireccion();
		this.medalla = new DTOMedalla(usuario.getMedalla());
		this.pais = usuario.getPais();
		this.extensionImagen = usuario.getPerfil().getExtension();
		this.imagenPerfil = usuario.getPerfil().getImagenPerfil();
		this.nombreImagen = usuario.getPerfil().getNombreImagen();
		this.celular = usuario.getCelular();
		this.estaBloqueado = usuario.getEstaBloqueado();
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

	public DTOMedalla getMedalla() {
		return medalla;
	}

	public void setMedalla(DTOMedalla medalla) {
		this.medalla = medalla;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
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

	public void setExtension(String extension) {
		this.extensionImagen = extension;
	}

	public String getExtensionImagen() {
		return extensionImagen;
	}

	public void setExtensionImagen(String extensionImagen) {
		this.extensionImagen = extensionImagen;
	}

	public boolean isEstaBloqueado() {
		return estaBloqueado;
	}

	public void setEstaBloqueado(boolean estaBloqueado) {
		this.estaBloqueado = estaBloqueado;
	}
}
