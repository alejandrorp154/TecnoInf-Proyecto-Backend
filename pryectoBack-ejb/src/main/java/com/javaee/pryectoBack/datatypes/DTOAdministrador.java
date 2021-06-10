package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;

public class DTOAdministrador implements Serializable {

    private static final long serialVersionUID = 1L;
    private String idPersona;
    private String email;
    private String nombre;
    private String apellido;

    public DTOAdministrador(){}

    public DTOAdministrador(String idPersona, String email, String nombre, String apellido){
        super();
        this.idPersona = idPersona;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
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
}
