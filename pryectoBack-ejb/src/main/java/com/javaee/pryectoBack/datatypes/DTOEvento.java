package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;
import java.util.Date;

import com.javaee.pryectoBack.model.estados;

public class DTOEvento implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int idEvento;
	private String ubicacion;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	private estados estado;
	private int idPublicacion;
	protected String idPersona;
	
	public DTOEvento(int idEvento, String ubicacion, String descripcion, Date fechaInicio, Date fechaFin,
			estados estado, int idPublicacion, String idPersona) {
		super();
		this.idEvento = idEvento;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estado = estado;
		this.idPublicacion = idPublicacion;
		this.idPersona = idPersona;
	}

	public DTOEvento() {
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public estados getEstado() {
		return estado;
	}

	public void setEstado(estados estado) {
		this.estado = estado;
	}

	public int getIdPublicacion() {
		return idPublicacion;
	}

	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}

	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}
}
