package com.javaee.pryectoBack.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Medalla implements Serializable
{
	private static final long serialVersionUID = 1L;	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idMedalla;
	private float cantidadPuntos;
	private String logros;
	private rangos rango;
	@ManyToOne
	private Usuario usuario;
	
	public Medalla() {
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
