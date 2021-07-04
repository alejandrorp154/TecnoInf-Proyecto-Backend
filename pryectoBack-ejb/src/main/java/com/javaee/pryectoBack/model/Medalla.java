package com.javaee.pryectoBack.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.javaee.pryectoBack.datatypes.DTOMedalla;

@Entity
public class Medalla implements Serializable
{
	private static final long serialVersionUID = 1L;	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idMedalla;
	private int cantidadPuntos;
	private String logros;
	private rangos rango;

	@OneToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "idPersona")
	private Usuario usuario;
	
	public Medalla() {
		this.idMedalla = 0;
		this.cantidadPuntos = 0;
		this.logros = null;
		this.rango = null;
		this.usuario = new Usuario();
	}
	
	public Medalla(DTOMedalla medalla) {
		if (medalla == null) {
			new Medalla();
		}
		else {
			this.idMedalla = medalla.getIdMedalla();
			this.cantidadPuntos = medalla.getCantidadPuntos();
			this.logros = medalla.getLogros();
			this.rango = medalla.getRango();
			this.usuario = new Usuario(medalla.getUsuario());
		}
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
	public int getCantidadPuntos() {
		return cantidadPuntos;
	}
	public void setCantidadPuntos(int cantidadPuntos) {
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
