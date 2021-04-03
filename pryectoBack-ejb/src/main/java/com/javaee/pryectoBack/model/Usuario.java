package com.javaee.pryectoBack.model;

import java.io.Serializable;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idUsuario;
	private String nombre;
	private String email;
	
	public Usuario()
	{
	}
	
	public Usuario(int idUsuario, String nombre, String email) {
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.email = email;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
