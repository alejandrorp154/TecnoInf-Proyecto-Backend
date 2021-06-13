package com.javaee.pryectoBack.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.javaee.pryectoBack.datatypes.DTOUbicacion;

@Entity
public class Ubicacion implements Serializable
{
	private static final long serialVersionUID = 1L;	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idUbicacion;
	private float longitud;
	private float latitud;
	private Date fecha;
	private String descripcion;
	private String pais;
	@ManyToOne
	private Usuario usuario;
	
	@OneToOne(mappedBy = "ubicacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private Evento evento;

	public Ubicacion() {
	}
	
	public Ubicacion(DTOUbicacion dtoUbicacion) {
		this.longitud = dtoUbicacion.getLongitud();
		this.latitud = dtoUbicacion.getLatitud();
		this.fecha = dtoUbicacion.getFecha();
		this.descripcion = dtoUbicacion.getDescripcion();
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
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public DTOUbicacion getDTO() {
		return new DTOUbicacion(this.idUbicacion,this.longitud,this.latitud,this.fecha,this.descripcion, this.pais);
	}
	
	public double getDistancia(Ubicacion usuarioLogueado, Ubicacion sugerenciaAmigo) 
	{
		double radioTierra = 6371;
		double dLat = Math.toRadians(sugerenciaAmigo.getLatitud() - usuarioLogueado.getLatitud());
		double dLng = Math.toRadians(sugerenciaAmigo.getLongitud() - usuarioLogueado.getLongitud());  
        double sindLat = Math.sin(dLat / 2);  
        double sindLng = Math.sin(dLng / 2); 
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
        * Math.cos(Math.toRadians(usuarioLogueado.getLatitud())) * Math.cos(Math.toRadians(sugerenciaAmigo.getLatitud()));  
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
        double distancia = radioTierra * va2;  

        return distancia;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
}
