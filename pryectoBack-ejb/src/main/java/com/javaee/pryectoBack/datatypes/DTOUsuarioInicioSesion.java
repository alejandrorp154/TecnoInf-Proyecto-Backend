package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;

public class DTOUsuarioInicioSesion implements Serializable {

    private static final long serialVersionUID = 1L;
    private String idPersona;
    private String email;
    private String nombre;
    private String apellido;
    private String nickname;
    private String imagenPerfil;
    private String extension;
    private String nombreImagen;
    private boolean isAdministrador;
    private boolean isBloqueado;

    public DTOUsuarioInicioSesion() {
    }

    public DTOUsuarioInicioSesion ( String idPersona, String email, String nombre, String apellido, String nickname, String imagen, String extension, String nombreImagen, boolean isAdministrador, boolean isBloqueado){
        this.idPersona = idPersona;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nickname = nickname;
        this.imagenPerfil = imagen;
        this.extension = extension;
        this.nombreImagen = nombreImagen;
        this.isAdministrador = isAdministrador;
        this.isBloqueado = isBloqueado;
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

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getNombreImagen() {
		return nombreImagen;
	}

	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}

	public boolean isAdministrador() {
		return isAdministrador;
	}

	public void setAdministrador(boolean isAdministrador) {
		this.isAdministrador = isAdministrador;
	}

	public boolean isBloqueado() {
		return isBloqueado;
	}

	public void setBloqueado(boolean isBloqueado) {
		this.isBloqueado = isBloqueado;
	}
}
