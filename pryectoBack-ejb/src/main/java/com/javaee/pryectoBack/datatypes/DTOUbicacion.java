package com.javaee.pryectoBack.datatypes;

import java.io.Serializable;
import java.util.Date;

import com.javaee.pryectoBack.model.Ubicacion;

public class DTOUbicacion implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int idUbicacion;
	private float longitud;
	private float latitud;
	private Date fecha;
	private String descripcion;
	private String idPersona;
	private String pais;
	
	public DTOUbicacion(int idUbicacion, float longitud, float latitud, Date fecha, String descripcion,
			String idPersona, String pais) {
		super();
		this.idUbicacion = idUbicacion;
		this.longitud = longitud;
		this.latitud = latitud;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.idPersona = idPersona;
		this.pais = pais;
	}
	
	public DTOUbicacion(int idUbicacion, float longitud, float latitud, Date fecha, String descripcion, String pais) {
		super();
		this.idUbicacion = idUbicacion;
		this.longitud = longitud;
		this.latitud = latitud;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.pais = pais;
	}

	public DTOUbicacion() {
	}

	public DTOUbicacion(Ubicacion ubicacion, String idPersona) {
		this.idUbicacion = ubicacion.getIdUbicacion();
		this.longitud = ubicacion.getLongitud();
		this.latitud = ubicacion.getLatitud();
		this.fecha = ubicacion.getFecha();
		this.descripcion = ubicacion.getDescripcion();
		this.idPersona = idPersona;
		this.pais = ubicacion.getPais();
	}

	public DTOUbicacion(Ubicacion ubicacion) {
		this.idUbicacion = ubicacion.getIdUbicacion();
		this.longitud = ubicacion.getLongitud();
		this.latitud = ubicacion.getLatitud();
		this.fecha = ubicacion.getFecha();
		this.descripcion = ubicacion.getDescripcion();
		this.pais = ubicacion.getPais();
	}

	public int getIdUbicacion() {
		return idUbicacion;
	}

	public void setIdUbicacion(int idUbicacion) {
		this.idUbicacion = idUbicacion;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
}
