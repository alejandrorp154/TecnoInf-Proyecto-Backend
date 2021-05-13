package com.javaee.pryectoBack.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Interes implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idInteres;
	@ManyToMany
	private List<PerfilUsuario> perfiles = new ArrayList<>();
	
	public Interes() {
	}

	public int getIdInteres() {
		return idInteres;
	}

	public void setIdInteres(int idInteres) {
		this.idInteres = idInteres;
	}

	public List<PerfilUsuario> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<PerfilUsuario> perfiles) {
		this.perfiles = perfiles;
	}
}
