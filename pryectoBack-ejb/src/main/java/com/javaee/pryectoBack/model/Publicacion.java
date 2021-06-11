package com.javaee.pryectoBack.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.javaee.pryectoBack.datatypes.DTOPublicacion;

@Entity
public class Publicacion implements Serializable
{
	private static final long serialVersionUID = 1L;	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idPublicacion;
	@Column(columnDefinition="text", length=10485760)
	private String contenido;
	private String extension;
	private String nombre;
	private Date fecha;
	private Tipo tipo;
	@ManyToOne
	private Evento evento;
	@ManyToOne
	private PerfilUsuario perfil;
	
	public Publicacion() {
	}
	
	public Publicacion(DTOPublicacion newPublicacion) {
		this.idPublicacion = newPublicacion.getIdPublicacion();
		this.contenido = newPublicacion.getContenido();
		this.fecha = newPublicacion.getFecha();
		this.tipo = new Tipo(newPublicacion.getTipo());
		this.extension = newPublicacion.getExtension();
		this.nombre = newPublicacion.getNombre();
		this.perfil = new PerfilUsuario(newPublicacion.getPerfil());
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
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public PerfilUsuario getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilUsuario perfil) {
		this.perfil = perfil;
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
