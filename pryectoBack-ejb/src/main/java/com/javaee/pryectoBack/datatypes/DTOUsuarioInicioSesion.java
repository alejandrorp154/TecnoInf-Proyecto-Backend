package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;

public class DTOUsuarioInicioSesion implements Serializable {

    private static final long serialVersionUID = 1L;
    private String idPersona;
    private String email;
    private String nombre;
    private String apellido;
    private String nickname;
    private String imagen;

    public DTOUsuarioInicioSesion() {
    }

    public DTOUsuarioInicioSesion ( String idPersona, String email, String nombre, String apellido, String nickname, String imagen){
        this.idPersona = idPersona;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nickname = nickname;
        this.imagen = imagen;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
