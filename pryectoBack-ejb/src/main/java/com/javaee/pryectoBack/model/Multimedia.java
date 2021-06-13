package com.javaee.pryectoBack.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.javaee.pryectoBack.datatypes.DTOMultimedia;

@Entity
public class Multimedia implements Serializable
{
	private static final long serialVersionUID = 1L;	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idMultimedia;
	@Column(columnDefinition="text", length=10485760)
	private String contenido;
	private String nombre;
	private String extension;
	@ManyToOne
	private PerfilUsuario perfil;
	
	public Multimedia() {
	}
	
	public Multimedia(DTOMultimedia dtoMultimedia, PerfilUsuario perfil) {
		this.contenido = dtoMultimedia.getContenido();
		this.nombre = dtoMultimedia.getNombre();
		this.extension = dtoMultimedia.getExtension();
		this.perfil = perfil;
	}

	public int getIdMultimedia() {
		return idMultimedia;
	}
	public void setIdMultimedia(int idMultimedia) {
		this.idMultimedia = idMultimedia;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public PerfilUsuario getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilUsuario perfil) {
		this.perfil = perfil;
	}
}
