package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;

import com.javaee.pryectoBack.model.Medalla;
import com.javaee.pryectoBack.model.rangos;

public class DTOMedalla implements Serializable
{
	private static final long serialVersionUID = 1L;

	private int idMedalla;
	private int cantidadPuntos;
	private String logros;
	private rangos rango;
	private DTOUsuario usuario;
	
	public DTOMedalla(int idMedalla, int cantidadPuntos, String logros, rangos rango, DTOUsuario usuario) {
		super();
		this.idMedalla = idMedalla;
		this.cantidadPuntos = cantidadPuntos;
		this.logros = logros;
		this.rango = rango;
		this.usuario = usuario;
	}

	public DTOMedalla() {
	}

	public DTOMedalla(Medalla medalla) {
		this.idMedalla = medalla.getIdMedalla();
		this.cantidadPuntos = medalla.getCantidadPuntos();
		this.logros = medalla.getLogros();
		this.rango = medalla.getRango();
		this.usuario = new DTOUsuario();
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

	public DTOUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(DTOUsuario usuario) {
		this.usuario = usuario;
	}
}
