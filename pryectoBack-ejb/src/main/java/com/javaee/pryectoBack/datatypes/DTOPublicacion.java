package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;
import java.util.Date;

import com.javaee.pryectoBack.model.Publicacion;
import com.javaee.pryectoBack.model.Tipo;

public class DTOPublicacion implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int idPublicacion;
	private String contenido;
	private Date fecha;
	private Tipo tipo;
	protected String idPersona;
	private String extension;
	private String nombre;
	
	public DTOPublicacion(int idPublicacion, String contenido, Date fecha, Tipo tipo, String idPersona) {
		super();
		this.idPublicacion = idPublicacion;
		this.contenido = contenido;
		this.fecha = fecha;
		this.tipo = tipo;
		this.idPersona = idPersona;
	}

	public DTOPublicacion() {
	}

	public DTOPublicacion(Publicacion publicacion) {
		this.idPublicacion = publicacion.getIdPublicacion();
		this.contenido = publicacion.getContenido();
		this.fecha = publicacion.getFecha();
		this.tipo = publicacion.getTipo();
//		this.idPersona = publicacion.getPerfil().getUsuario().getIdPersona();
		this.extension = publicacion.getExtension();
		this.nombre = publicacion.getNombre();
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

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
