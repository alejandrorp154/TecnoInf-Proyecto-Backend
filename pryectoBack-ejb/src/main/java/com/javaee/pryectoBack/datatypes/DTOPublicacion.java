package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.javaee.pryectoBack.model.Tipo;

public class DTOPublicacion implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int idPublicacion;
	private String contenido;
	private Date fecha;
	private List<Tipo> tipos = new ArrayList<>();
	protected String idPersona;
	
	public DTOPublicacion(int idPublicacion, String contenido, Date fecha, List<Tipo> tipos, String idPersona) {
		super();
		this.idPublicacion = idPublicacion;
		this.contenido = contenido;
		this.fecha = fecha;
		this.tipos = tipos;
		this.idPersona = idPersona;
	}

	public DTOPublicacion() {
	}

	public int getIdPublicacion() {
		return idPublicacion;
	}

	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<Tipo> getTipos() {
		return tipos;
	}

	public void setTipos(List<Tipo> tipos) {
		this.tipos = tipos;
	}

	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}
}
