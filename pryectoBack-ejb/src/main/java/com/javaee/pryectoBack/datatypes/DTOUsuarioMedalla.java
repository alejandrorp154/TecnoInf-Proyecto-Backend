package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;

import com.javaee.pryectoBack.model.rangos;

public class DTOUsuarioMedalla implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String idPersona;
	private String email;
	private String nombre;
	private String apellido;
	private String nickname;
	private int idMedalla;
	private float cantidadPuntos;
	private String logros;
	private rangos rango;
	
	public DTOUsuarioMedalla(String idPersona, String email, String nombre, String apellido, String nickname,
			int idMedalla, float cantidadPuntos, String logros, rangos rango) {
		super();
		this.idPersona = idPersona;
		this.email = email;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nickname = nickname;
		this.idMedalla = idMedalla;
		this.cantidadPuntos = cantidadPuntos;
		this.logros = logros;
		this.rango = rango;
	}

	public DTOUsuarioMedalla() {
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

	public int getIdMedalla() {
		return idMedalla;
	}

	public void setIdMedalla(int idMedalla) {
		this.idMedalla = idMedalla;
	}

	public float getCantidadPuntos() {
		return cantidadPuntos;
	}

	public void setCantidadPuntos(float cantidadPuntos) {
		this.cantidadPuntos = cantidadPuntos;
	}

	public String getLogros() {
		return logros;
	}

	public void setLogros(String logros) {
		this.logros = logros;
	}

	public rangos getRango() {
		return rango;
	}

	public void setRango(rangos rango) {
		this.rango = rango;
	}
}
